package io.nebulas.explorer.controller;

import io.nebulas.explorer.config.YAMLConfig;
import io.nebulas.explorer.core.BaseController;
import io.nebulas.explorer.domain.BlockSummary;
import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.service.NebAddressService;
import io.nebulas.explorer.service.NebBlockService;
import io.nebulas.explorer.service.NebTransactionService;
import lombok.extern.slf4j.Slf4j;
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
 * Desc:
 * User: HaiNan.Wang
 * Date: 2018/1/26
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
                          Model model) {
        NebAddress address = nebAddressService.getNebAddressByHash(hash);
        if (null == address) {
            return "";
        }

        model.addAttribute("address", address);
        model.addAttribute("txCnt", nebTransactionService.countNormalTxnCntByFromTo(address.getHash()));
        model.addAttribute("minedBlkCnt", nebBlockService.countBlockCntByMiner(address.getHash()));

        if ("mine".equals(part)) {
            List<NebBlock> blkList = nebBlockService.findNebBlockByMiner(address.getHash(), 1, 25);
            if(CollectionUtils.isNotEmpty(blkList)) {
                List<Long> blkHeightList = blkList.stream().map(NebBlock::getHeight).collect(Collectors.toList());
                Map<Long, BlockSummary> txCntMap = nebTransactionService.countNormalTxInBlock(blkHeightList);
                model.addAttribute("txCntMap", txCntMap);
            }
            model.addAttribute("minedBlkList", blkList);
            model.addAttribute("part", "mine");
        } else {
            model.addAttribute("txList", nebTransactionService.findNormalTxnByFromTo(address.getHash(), 1, 25));
            model.addAttribute("part", "tx");
        }
        return "address/information";
    }

    /**
     * all accounts
     *
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/accounts")
    public String all(@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
        if (page < 1) {
            page = 1;
        }
        if (page > MAX_PAGE) {
            page = MAX_PAGE;
        }

        BigDecimal totalBalance = BigDecimal.ONE;//todo
        List<NebAddress> addressList = nebAddressService.findAddressOrderByBalance((page - 1) * PAGE_SIZE, PAGE_SIZE);
        Map<String, BigDecimal> percentageMap = addressList.stream()
                .collect(Collectors.toMap(NebAddress::getHash, a -> a.getCurrentBalance().divide(totalBalance, 8, BigDecimal.ROUND_DOWN)));
        List<String> addressHashList = addressList.stream().map(NebAddress::getHash).collect(Collectors.toList());

        model.addAttribute("totalAccountsCnt", nebAddressService.countTotalAddressCnt());
        model.addAttribute("totalNas", totalBalance);
        model.addAttribute("page", page);
        model.addAttribute("addressList", addressList);
        model.addAttribute("percentageMap", percentageMap);
        model.addAttribute("txCntMap", nebTransactionService.countNormalTxnCntByFromTo(addressHashList));

        return "address/accounts";
    }

}
