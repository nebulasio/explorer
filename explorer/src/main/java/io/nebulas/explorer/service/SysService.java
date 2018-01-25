package io.nebulas.explorer.service;

import io.grpc.StatusRuntimeException;
import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.grpc.Const;
import io.nebulas.explorer.grpc.GrpcClientService;
import io.nebulas.explorer.model.Block;
import io.nebulas.explorer.model.NebState;
import io.nebulas.explorer.model.Transaction;
import io.nebulas.explorer.util.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-24
 */
@Slf4j
@AllArgsConstructor
@Service
public class SysService {
    private final GrpcClientService grpcClientService;
    private final NebBlockService nebBlockService;
    private final NebTransactionService nebTransactionService;

    public void init() {
        log.info("sys init starting...");
        final long start = System.currentTimeMillis();
        try {
            NebState nebState = grpcClientService.getNebState();
            log.info("neb state: {}", toJSONString(nebState));
            Block block = grpcClientService.getBlockByHash(nebState.getTail(), true);
            log.info("top block: {}", toJSONString(block));

            final Long height = block.getHeight();
            for (long pos = 1L; pos <= height; pos++) {
                Block blk;
                try {
                    blk = grpcClientService.getBlockByHeight(pos, true);
                } catch (StatusRuntimeException e) {
                    log.error("get block by height error", e);
                    continue;
                }
                log.info("iterate Block {} : {}", pos, toJSONString(blk));

                // TODO: insert into neb_block
                NebBlock nebBlk = NebBlock.builder()
                        .id(IdGenerator.getId())
                        .height(blk.getHeight())
                        .hash(blk.getHash())
                        .parentHash(blk.getParentHash())
                        .timestamp(new Date(blk.getTimestamp()))
                        .miner(blk.getMiner())
                        .coinbase(blk.getCoinbase())
                        .nonce(blk.getNonce())
                        .build();

                boolean blkSaveResult = nebBlockService.saveNebBlock(nebBlk);
                if (!blkSaveResult) {
                    log.error("save nebulas block error");
                    continue;
                }


                List<Transaction> txs = blk.getTransactions();
                List<NebTransaction> nebTxsList = new ArrayList<>(100);
                for (Transaction tx : txs) {
                    String gasUsed = grpcClientService.getGasUsed(tx.getHash());
                    if (gasUsed != null) {
                        log.info("gas used: {}", gasUsed);
                    }

                    // TODO: insert into neb_transaction
                    NebTransaction nebTxs = NebTransaction.builder()
                            .hash(tx.getHash())
                            .blockHeight(blk.getHeight())
                            .blockHash(blk.getHash())
                            .from(tx.getFrom())
                            .to(tx.getTo())
                            .status(tx.getStatus())
                            .value(tx.getValue())
                            .nonce(tx.getNonce())
                            .timestamp(new Date(tx.getTimestamp()))
                            .type(1111111) //todo
                            .data(tx.getData())
                            .gasPrice(tx.getGasPrice())
                            .gasLimit(tx.getGasLimit())
                            .gasUsed(gasUsed)
                            .build();
                    nebTxsList.add(nebTxs);

                    if (100 == nebTxsList.size()) {
                        nebTransactionService.batchSaveNebTransaction(nebTxsList);
                        nebTxsList.clear();
                    }
                }
                if (0 < nebTxsList.size()) {
                    nebTransactionService.batchSaveNebTransaction(nebTxsList);
                    nebTxsList.clear();
                }
            }

            grpcClientService.subscribe(Const.TopicSendTransaction);
            grpcClientService.subscribe(Const.TopicDeploySmartContract);
            grpcClientService.subscribe(Const.TopicCallSmartContract);
            grpcClientService.subscribe(Const.TopicDelegate);
            grpcClientService.subscribe(Const.TopicCandidate);
            grpcClientService.subscribe(Const.TopicPendingTransaction);
            grpcClientService.subscribe(Const.TopicLinkBlock);

            long elapsed = System.currentTimeMillis() - start;
            log.info("{} millis elapsed", elapsed);
            log.info("sys init end");
        } catch (StatusRuntimeException | UnsupportedEncodingException e) {
            log.error("sys init error", e);
        }
    }
}
