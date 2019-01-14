package io.nebulas.explorer.controller;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.nebulas.explorer.domain.NebAddress;
import io.nebulas.explorer.model.JsonResult;
import io.nebulas.explorer.service.blockchain.NebAddressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * API for nebulas dip
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/dip")
public class DipController {

    private static final int DEFAULT_PAGE_SIZE = 20;

    @Autowired
    private NebAddressService nebAddressService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonResult list(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE+"") int pageSize,
                           @RequestParam(value = "year", required = false, defaultValue = "0") int year,
                           @RequestParam(value = "week", required = false, defaultValue = "0") int week){
        DateTime now = DateTime.now();
        if (year==0){
            year = now.getWeekyear(); //不传年份的时候，默认取当前的WeekYear
        }
        if (week==0){
            week = now.getWeekOfWeekyear() - 1; //默认取上一周的数据
        }
        if (page>2){
            page = 2;
        }
        final int finalWeek = week;
        final int finalYear = year;
        List<Dip> contracts = nebAddressService.getContractList(page, pageSize)
                .stream()
                .map(nebAddress -> {
                    Dip dip = new Dip();
                    dip.contract = nebAddress.getHash();
                    dip.creator = nebAddress.getCreator();
                    dip.award = nebAddress.getCreatedAt().getTime();
                    dip.txHash = nebAddress.getDeployTxHash();
                    dip.txTimestamp = nebAddress.getCreatedAt();
                    dip.week = finalWeek;
                    dip.year = finalYear;
                    return dip;
                })
                .collect(Collectors.toList());

        final int total = pageSize*2;
        JsonResult result =  JsonResult.success();
        result.put("total", total);
        result.put("totalPage", 2);
        result.put("currentPage", page);
        result.put("contracts", contracts);
        return result;
    }

    static class Dip{
        public String contract;
        public String creator;
        public long award;
        public String txHash;
        public Date txTimestamp;
        public int week;
        public int year;
    }

}
