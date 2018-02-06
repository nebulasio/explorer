package io.nebulas.explorer.controller;

import io.nebulas.explorer.config.YAMLConfig;
import io.nebulas.explorer.core.BaseController;
import io.nebulas.explorer.domain.BlockSummary;
import io.nebulas.explorer.domain.NebBlock;
import io.nebulas.explorer.service.NebBlockService;
import io.nebulas.explorer.service.NebMarketCapitalizationService;
import io.nebulas.explorer.service.NebTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-22
 */
@Slf4j
@Controller
public class IndexController extends BaseController {
    private final NebBlockService nebBlockService;
    private final NebTransactionService nebTransactionService;
    private final NebMarketCapitalizationService nebMarketCapitalizationService;


    public IndexController(YAMLConfig config,
                           NebBlockService nebBlockService,
                           NebTransactionService nebTransactionService,
                           NebMarketCapitalizationService nebMarketCapitalizationService) {
        super(config);
        this.nebBlockService = nebBlockService;
        this.nebTransactionService = nebTransactionService;
        this.nebMarketCapitalizationService = nebMarketCapitalizationService;
    }


    /**
     * nebulas home page
     *
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        execute(model);

        List<NebBlock> blkList = nebBlockService.findNebBlockOrderByHeight(1, 10);
        if (CollectionUtils.isNotEmpty(blkList)) {
            List<Long> blkHeightList = blkList.stream().map(NebBlock::getHeight).collect(Collectors.toList());
            Map<Long, BlockSummary> txCntMap = nebTransactionService.calculateTxnSummaryInBlock(blkHeightList,false);
            model.addAttribute("txCntMap", txCntMap);
        }
        model.addAttribute("blkList", blkList);
        model.addAttribute("txList", nebTransactionService.findTxnOrderById(1, 10));
        model.addAttribute("market", nebMarketCapitalizationService.getLatest());

        Map<String, Long> kvs = nebTransactionService.countTxCntGroupMapByTimestamp(LocalDate.now().plusDays(-8).toDate(), LocalDate.now().toDate());
        List<String> dateList = new ArrayList<>(kvs.size());
        List<Long> valueList = new ArrayList<>(kvs.size());
        for (String date : kvs.keySet()) {
            dateList.add(date);
            valueList.add(kvs.get(date));
        }
        model.addAttribute("dateList", dateList);
        model.addAttribute("valueList", valueList);

        return "index";
    }

}
