package io.nebulas.explorer.controller;

import com.google.common.collect.Lists;
import io.nebulas.explorer.config.YAMLConfig;
import io.nebulas.explorer.core.BaseController;
import io.nebulas.explorer.domain.*;
import io.nebulas.explorer.exception.NotFoundException;
import io.nebulas.explorer.model.vo.AddressVo;
import io.nebulas.explorer.service.NebAddressService;
import io.nebulas.explorer.service.NebBlockService;
import io.nebulas.explorer.service.NebTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-29
 */
@Slf4j
@Controller
@RequestMapping("")
public class AddressController extends BaseController {
    private static final int PAGE_SIZE = 25;
    private static final int MAX_PAGE = 400;
    private final NebAddressService nebAddressService;
    private final NebTransactionService nebTransactionService;
    private final NebBlockService nebBlockService;

    public AddressController(YAMLConfig config,
                             NebAddressService nebAddressService,
                             NebTransactionService nebTransactionService,
                             NebBlockService nebBlockService) {
        super(config);
        this.nebAddressService = nebAddressService;
        this.nebTransactionService = nebTransactionService;
        this.nebBlockService = nebBlockService;
    }

    /**
     * address information
     *
     * @param hash  address hash
     * @param part  the related part of address
     * @param model spring model
     */
    @RequestMapping("/address/{hash}")
    public String address(@PathVariable("hash") String hash,
                          @RequestParam(value = "part", required = false) String part,
                          Model model) throws NotFoundException {
        execute(model);
        NebAddress address = nebAddressService.getNebAddressByHash(hash);
        if (null == address) {
            throw new NotFoundException("neb address not found");
        }

        model.addAttribute("address", address);

        long pendingTxCnt = nebTransactionService.countPendingTxnCnt(address.getHash());

        model.addAttribute("pendingTxCnt", pendingTxCnt);
        model.addAttribute("txCnt", nebTransactionService.countTxnCntByFromTo(address.getHash()));
        model.addAttribute("minedBlkCnt", nebBlockService.countBlockCntByMiner(address.getHash()));

        if ("mine".equals(part)) {
            List<NebBlock> blkList = nebBlockService.findNebBlockByMiner(address.getHash(), 1, PAGE_SIZE);
            if (CollectionUtils.isNotEmpty(blkList)) {
                List<Long> blkHeightList = blkList.stream().map(NebBlock::getHeight).collect(Collectors.toList());
                Map<Long, BlockSummary> txCntMap = nebTransactionService.calculateTxnSummaryInBlock(blkHeightList, true);
                model.addAttribute("txCntMap", txCntMap);
            }
            model.addAttribute("minedBlkList", blkList);
            model.addAttribute("part", "mine");
        } else {

            if (pendingTxCnt > 0) {
                List<NebPendingTransaction> pendingTxnList = nebTransactionService.findPendingTxnByCondition(address.getHash(), 1, PAGE_SIZE);

                List<NebTransaction> txList = Lists.newLinkedList();
                for (NebPendingTransaction pTxn : pendingTxnList) {
                    try {
                        NebTransaction tx = new NebTransaction();
                        PropertyUtils.copyProperties(tx, pTxn);
                        tx.setBlockHeight(0L);
                        tx.setBlockHash("");
                        tx.setStatus(0);
                        txList.add(tx);
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                }
                if (pendingTxnList.size() < PAGE_SIZE) {
                    txList.addAll(nebTransactionService.findTxnByFromTo(address.getHash(), 1, PAGE_SIZE - pendingTxnList.size()));
                }
                model.addAttribute("txList", txList);

            } else {
                model.addAttribute("txList", nebTransactionService.findTxnByFromTo(address.getHash(), 1, PAGE_SIZE));
            }

            model.addAttribute("part", "tx");
        }
        return "address/address";
    }

    /**
     * all accounts
     *
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/accounts")
    public String all(@RequestParam(value = "p", required = false, defaultValue = "1") int page, Model model) {
        execute(model);
        if (page < 1) {
            page = 1;
        }
        if (page > MAX_PAGE) {
            page = MAX_PAGE;
        }

        BigDecimal totalBalance = BigDecimal.ONE;//todo
        List<NebAddress> addressList = nebAddressService.findAddressOrderByBalance(page, PAGE_SIZE);
        Map<String, BigDecimal> percentageMap = addressList.stream()
                .collect(Collectors.toMap(NebAddress::getHash, a -> a.getCurrentBalance().divide(totalBalance, 8, BigDecimal.ROUND_DOWN)));
        List<String> addressHashList = addressList.stream().map(NebAddress::getHash).collect(Collectors.toList());
        Map<String, Long> txCntMap = nebTransactionService.countTxnCntByFromTo(addressHashList);

        long totalCnt = nebAddressService.countTotalAddressCnt();

        List<AddressVo> voList = Lists.newLinkedList();
        int i = 1 + (page - 1) * PAGE_SIZE;
        for (NebAddress address : addressList) {
            AddressVo vo = new AddressVo();
            vo.setRank(i);
            vo.setHash(address.getHash());
            vo.setAlias(address.getAlias());
            vo.setBalance(address.getCurrentBalance());
            vo.setPercentage(percentageMap.get(address.getHash()));
            vo.setTxCnt(txCntMap.get(address.getHash()));
            voList.add(vo);
            i++;
        }

        model.addAttribute("totalAccountsCnt", totalCnt);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", totalCnt / PAGE_SIZE + 1);
        model.addAttribute("totalBalance", totalBalance);
        model.addAttribute("addressList", voList);
        return "address/accounts";
    }

}
