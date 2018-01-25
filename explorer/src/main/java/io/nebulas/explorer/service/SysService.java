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
import static io.nebulas.explorer.util.BlockUtil.collectTxs;

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

            final Long goalHeight = block.getHeight();
            final Long lastHeightO = nebBlockService.getMaxHeight();
            long lastHeight = lastHeightO == null ? 0L : lastHeightO;
            for (long h = lastHeight + 1; h <= goalHeight; h++) {
                NebBlock nebBlock = nebBlockService.getByHeight(h);
                if (nebBlock != null) {
                    continue;
                }
                Block blk;
                try {
                    blk = grpcClientService.getBlockByHeight(h, true);
                } catch (StatusRuntimeException e) {
                    log.error("get block by goalHeight error", e);
                    continue;
                }

                NebBlock nebBlk = NebBlock.builder()
                        .id(IdGenerator.getId())
                        .height(blk.getHeight())
                        .hash(blk.getHash())
                        .parentHash(blk.getParentHash())
                        .timestamp(new Date(blk.getTimestamp() * 1000))
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
                List<NebTransaction> nebTxsList = new ArrayList<>(txs.size());

                List<String> gasUsedList = new ArrayList<>(txs.size());

                for (Transaction tx : txs) {
                    String gasUsed = grpcClientService.getGasUsed(tx.getHash());
                    if (gasUsed != null) {
                        log.info("gas used: {}", gasUsed);
                        gasUsedList.add("");
                    } else {
                        gasUsedList.add(gasUsed);
                    }
                }
                collectTxs(block, txs, nebTxsList, gasUsedList);
                if (nebTxsList.size() > 0) {
                    nebTransactionService.batchSaveNebTransaction(nebTxsList);
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
