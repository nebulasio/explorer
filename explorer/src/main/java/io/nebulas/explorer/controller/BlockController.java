package io.nebulas.explorer.controller;

import io.nebulas.explorer.domain.BlockSummary;
import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.model.PageIterator;
import io.nebulas.explorer.service.NebAddressService;
import io.nebulas.explorer.service.NebBlockService;
import io.nebulas.explorer.service.NebTransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
 * @since 2018-01-24
 */
@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("")
public class BlockController {
    private static final Integer PAGE_SIZE = 20;
    private final NebBlockService nebBlockService;
    private final NebTransactionService nebTransactionService;
    private final NebAddressService nebAddressService;

    /**
     * Generate block information page
     *
     * @param height
     * @return
     */
    @RequestMapping("/block/{height}")
    public String detail(@PathVariable("height") Long height, Model model) {
        NebBlock block = nebBlockService.getByHeight(height);
        if (null == block) {
            return "";
        }

        model.addAttribute("block", block);
        model.addAttribute("txCnt", nebTransactionService.countNormalTxCntByBlockHeight(block.getHeight()));
//        model.addAttribute("contractInternalTxCnt", 0); //todo

        NebAddress nebAddress = nebAddressService.getNebAddressByHash(block.getMiner());
        if (null != nebAddress) {
            model.addAttribute("miner", nebAddress);
        }
        return "block/information";
    }

    /**
     * all block list
     *
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/blocks")
    public String blocks(@RequestParam(value = "p", required = false, defaultValue = "1") int page, Model model) {
        PageIterator<NebBlock> blockPageIterator = nebBlockService.findNebBlockPageIterator(page, PAGE_SIZE);

        if (CollectionUtils.isNotEmpty(blockPageIterator.getData())) {
            List<Long> blkHeightList = blockPageIterator.getData().stream().map(NebBlock::getHeight).collect(Collectors.toList());
            Map<Long, BlockSummary> txCntMap = nebTransactionService.countNormalTxInBlock(blkHeightList);
            model.addAttribute("txCntMap", txCntMap);
        }
        model.addAttribute("blockPageIterator", blockPageIterator);
        return "block/all";
    }


}
