package io.nebulas.explorer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import io.nebulas.explorer.domain.NaxStage;
import io.nebulas.explorer.mapper.NaxStageMapper;
import io.nebulas.explorer.model.JsonResult;
import io.nebulas.explorer.model.vo.NaxStageVo;
import io.nebulas.explorer.service.blockchain.NaxService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/dstaking")
public class DStakingController {

    private static final int DEFAULT_PAGE_SIZE = 20;

    @Autowired
    private NaxService naxService;

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public JsonResult list(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE + "") int pageSize) {
        List<NaxStageVo> stageList = naxService.getStageList(page, pageSize);
        long total = naxService.countCompletedStage();
        long totalPage = total / pageSize;
        if (total % pageSize != 0) {
            totalPage++;
        }
        JsonResult result = JsonResult.success();
        result.put("list", stageList);
        result.put("total", total);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        return result;
    }

    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    public JsonResult summary(){
        long endHeight = naxService.getCurrentStageEndHeight();
        BigDecimal lastDistributedNax = naxService.getLastDistributeNax();
        if (lastDistributedNax==null){
            lastDistributedNax = BigDecimal.ZERO;
        }
        BigDecimal estimateNax = naxService.getEstimateNax();
        if (estimateNax==null){
            estimateNax = BigDecimal.ZERO;
        }
        BigDecimal totalDistributedNax = naxService.getTotalDistributeNax();
        if (totalDistributedNax==null){
            totalDistributedNax = BigDecimal.ZERO;
        }
        BigDecimal currentPledgedNas = naxService.getCurrentPledgedNas();
        if (currentPledgedNas==null){
            currentPledgedNas = BigDecimal.ZERO;
        }
        BigDecimal currentTotalNas = naxService.getCurrentTotalNas();
        if (currentTotalNas==null){
            currentTotalNas = BigDecimal.ZERO;
        }
        List<NaxStageVo> list = naxService.getLast15StageList();
        JsonResult result = JsonResult.success();
        result.put("lastDistributedNax", lastDistributedNax.stripTrailingZeros().toPlainString());
        result.put("totalDistributedNax", totalDistributedNax.stripTrailingZeros().toPlainString());
        result.put("currentPledgedNas", currentPledgedNas.stripTrailingZeros().toPlainString());
        result.put("currentTotalNas", currentTotalNas.stripTrailingZeros().toPlainString());
        result.put("estimateNax", estimateNax.stripTrailingZeros().toPlainString());
        result.put("endHeight", endHeight);
        result.put("list", list);
        return result;
    }
}
