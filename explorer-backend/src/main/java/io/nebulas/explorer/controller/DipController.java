package io.nebulas.explorer.controller;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.nebulas.explorer.domain.NebDipAward;
import io.nebulas.explorer.model.JsonResult;
import io.nebulas.explorer.service.blockchain.NebAddressService;
import io.nebulas.explorer.service.blockchain.NebDipAwardService;
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

    @Autowired
    private NebDipAwardService nebDipAwardService;

    @Qualifier("customRedisTemplate")
    private final RedisTemplate<String, List<NebDipAward>> listRedisTemplate;
    @Qualifier("customStringTemplate")
    private final StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/top", method = RequestMethod.GET)
    public JsonResult topThree() {
        DateTime now = DateTime.now();
        DateTime sevenDaysAgo = now.minusDays(7);
        int year = sevenDaysAgo.getWeekyear(); //不传年份的时候，默认取当前的WeekYear
        int week = sevenDaysAgo.getWeekOfWeekyear(); //默认取上一周的数据
        List<NebDipAward> contracts = nebDipAwardService.getDipAwardByWeek(week, year, 1, 3);
        JsonResult result = JsonResult.success();
        result.put("contracts", contracts);
        result.put("week", week);
        result.put("year", year);
        return result;
    }

    @RequestMapping(value = "/list_test", method = RequestMethod.GET)
    public JsonResult listTest(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE + "") int pageSize,
                           @RequestParam(value = "year", required = false, defaultValue = "0") int year,
                           @RequestParam(value = "week", required = false, defaultValue = "0") int week) {
        DateTime now = DateTime.now();
        DateTime sevenDaysAgo = now.minusDays(7);
        if (year == 0) {
            year = sevenDaysAgo.getWeekyear(); //不传年份的时候，默认取当前的WeekYear
        }
        if (week == 0) {
            week = sevenDaysAgo.getWeekOfWeekyear(); //默认取上一周的数据
        }
        if (pageSize == 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        //假数据 - Start
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

        final int totalCount = pageSize*2;
        final int totalPage = 2;
        final long totalAward = now.getMillis();
        //假数据 - End

        JsonResult result = JsonResult.success();
        result.put("total", totalCount);
        result.put("totalPage", totalPage);
        result.put("totalAward", totalAward);
        result.put("currentPage", page);
        result.put("contracts", contracts);
        return result;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonResult list(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE + "") int pageSize,
                           @RequestParam(value = "year", required = false, defaultValue = "0") int year,
                           @RequestParam(value = "week", required = false, defaultValue = "0") int week) {
        DateTime now = DateTime.now();
        DateTime sevenDaysAgo = now.minusDays(7);
        if (year == 0) {
            year = sevenDaysAgo.getWeekyear(); //不传年份的时候，默认取当前的WeekYear
        }
        if (week == 0) {
            week = sevenDaysAgo.getWeekOfWeekyear(); //默认取上一周的数据
        }
        if (pageSize == 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

//        //Use Redis(待确定是否使用Redis) - Start
//        List<NebDipAward> contracts;
//
//        String contractsKeyInRedis = "dip-contracts:" + year + "-" + week + ":p" + page + "-s" + pageSize;
//        List<NebDipAward> cachedContracts = listRedisTemplate.opsForValue().get(contractsKeyInRedis);
//        if (cachedContracts != null) {
//            contracts = cachedContracts;
//        } else {
//            contracts = nebDipAwardService.getDipAwardByWeek(week, year, page, pageSize);
//            listRedisTemplate.opsForValue().set(contractsKeyInRedis, contracts, 7, TimeUnit.DAYS);
//        }
//        DateTime step1End = DateTime.now();
//        System.out.println("Step1 - 耗时: " + (step1End.getMillis() - now.getMillis()));
//
//
//        String totalAwardKeyInRedis = "dip-total-award:" + year + "-" + week;
//        long totalAward;
//        String cachedTotalAward = stringRedisTemplate.opsForValue().get(totalAwardKeyInRedis);
//        if (cachedTotalAward != null) {
//            try{
//                totalAward = Long.parseLong(cachedTotalAward);
//            }catch (Exception e){
//                totalAward = 0;
//            }
//        } else {
//            totalAward = nebDipAwardService.getTotalAwardByWeek(week, year);
//            stringRedisTemplate.opsForValue().set(totalAwardKeyInRedis, ""+totalAward, 7, TimeUnit.DAYS);
//        }
//        DateTime step2End = DateTime.now();
//        System.out.println("Step2 - 耗时: " + (step2End.getMillis() - step1End.getMillis()));
//
//        String totalCountKeyInRedis = "dip-total-count:" + year + "-" + week;
//        int totalCount;
//        String cachedTotalCount = stringRedisTemplate.opsForValue().get(totalCountKeyInRedis);
//        if (cachedTotalCount!=null){
//            try{
//                totalCount = Integer.parseInt(cachedTotalCount);
//            }catch (Exception e){
//                totalCount = 0;
//            }
//        } else {
//            totalCount = nebDipAwardService.getCountByWeek(week, year);
//            stringRedisTemplate.opsForValue().set(totalCountKeyInRedis, ""+totalCount, 7, TimeUnit.DAYS);
//        }
//        DateTime step3End = DateTime.now();
//        System.out.println("Step3 - 耗时: " + (step3End.getMillis() - step2End.getMillis()));
//        //Use Redis(待确定是否使用Redis) - End

        List<NebDipAward> contracts = nebDipAwardService.getDipAwardByWeek(week, year, page, pageSize);
        long totalAward = nebDipAwardService.getTotalAwardByWeek(week, year);
        int totalCount = nebDipAwardService.getCountByWeek(week, year);

        int totalPage = totalCount / pageSize;
        if (totalCount % pageSize != 0) {
            totalPage++;
        }
        JsonResult result = JsonResult.success();
        result.put("total", totalCount);
        result.put("totalPage", totalPage);
        result.put("totalAward", totalAward);
        result.put("currentPage", page);
        result.put("contracts", contracts);
        return result;
    }

    static class Dip {
        public String contract;
        public String creator;
        public long award;
        public String txHash;
        public Date txTimestamp;
        public int week;
        public int year;
    }

}
