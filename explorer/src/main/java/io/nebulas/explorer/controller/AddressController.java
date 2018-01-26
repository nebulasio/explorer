package io.nebulas.explorer.controller;

import io.nebulas.explorer.config.YAMLConfig;
import io.nebulas.explorer.core.BaseController;
import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.service.NebAddressService;
import io.nebulas.explorer.service.NebTransactionService;
import lombok.extern.slf4j.Slf4j;
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

    public AddressController(YAMLConfig config,
                             NebAddressService nebAddressService,
                             NebTransactionService nebTransactionService) {
        super(config);
        this.nebAddressService = nebAddressService;
        this.nebTransactionService = nebTransactionService;
    }

    @RequestMapping("/address/{hash}")
    public String address(@PathVariable("hash") String hash, @RequestParam("type") Model model) {
        NebAddress address = nebAddressService.getNebAddressByHash(hash);
        if (null == address) {
            return "";
        }
        model.addAttribute("address", address);
        return "address/information";
    }

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
