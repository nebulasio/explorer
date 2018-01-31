package io.nebulas.explorer.controller;

import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.domain.NebTransaction;
import io.nebulas.explorer.service.NebAddressService;
import io.nebulas.explorer.service.NebBlockService;
import io.nebulas.explorer.service.NebTransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Desc:
 * User: HaiNan.Wang
 * Date: 2018/1/30
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("")
@Controller
public class SearchController {

    private final NebBlockService nebBlockService;
    private final NebTransactionService nebTransactionService;
    private final NebAddressService nebAddressService;

    @RequestMapping("search")
    public String search(@RequestParam(value = "q") String q) {
        if (StringUtils.isEmpty(q)) {
            return "error/404";
        }
        if (StringUtils.isNumeric(q)) {
            NebBlock block = nebBlockService.getNebBlockByHeight(Long.valueOf(q));
            if (null != block) {
                return "redirect:/block/" + block.getHeight();
            }
        }
        NebBlock block = nebBlockService.getNebBlockByHash(q);
        if (null != block) {
            return "redirect:/block/" + block.getHeight();
        }
        NebAddress address = nebAddressService.getNebAddressByHash(q);
        if (null != address) {
            return "redirect:/address/" + address.getHash();
        }

        NebTransaction transaction = nebTransactionService.getNebTransactionByHash(q);
        if(null != transaction){
            return "redirect:/tx/" + address.getHash();
        }

        return "error/404";
    }


}
