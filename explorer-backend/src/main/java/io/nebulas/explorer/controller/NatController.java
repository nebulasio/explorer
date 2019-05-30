package io.nebulas.explorer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.nebulas.explorer.domain.NatRecord;
import io.nebulas.explorer.mapper.NatRecordMapper;
import io.nebulas.explorer.model.JsonResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/nat")
public class NatController {
    private static final int DEFAULT_PAGE_SIZE = 20;

    @Autowired
    private NatRecordMapper natRecordMapper;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonResult list(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE + "") int pageSize,
                           @RequestParam(value = "address") String address) {
        if (page < 1) {
            page = 1;
        }
        List<NatRecord> records = natRecordMapper.getByAddress(address, (page - 1) * pageSize, pageSize);
        return JsonResult.success(records);
    }
}
