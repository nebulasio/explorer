package io.nebulas.explorer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.nebulas.explorer.domain.NatRecord;
import io.nebulas.explorer.model.JsonResult;
import io.nebulas.explorer.service.blockchain.NatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/nat")
public class NatController {
    private static final int DEFAULT_PAGE_SIZE = 20;

    @Autowired
    private NatService natService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonResult list(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE + "") int pageSize,
                           @RequestParam(value = "address") String address) {
        if (page < 1) {
            page = 1;
        }
        List<NatRecord> records = natService.list((page - 1) * pageSize, pageSize, address);
        long total = natService.total(address);
        long totalPage = total / pageSize;
        if (total % pageSize != 0) {
            totalPage++;
        }
        JsonResult result = JsonResult.success();
        result.put("list", records);
        result.put("total", total);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        return result;
    }
}
