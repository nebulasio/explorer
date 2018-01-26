package io.nebulas.explorer.controller;

import io.nebulas.explorer.config.YAMLConfig;
import io.nebulas.explorer.core.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-26
 */
@Slf4j
@Controller
public class TxController extends BaseController {
    public TxController(YAMLConfig config) {
        super(config);
    }

    @RequestMapping("/txsInternal")
    public String txsInternal(@RequestParam(value = "block", required = false) Long block, Model model) {
        execute(model);
        return "txsInternal";
    }

    @RequestMapping("/txs")
    public String txs(@RequestParam(value = "block", required = false) Long block, Model model) {
        execute(model);
        return "txs";
    }

    @RequestMapping("/tx/{tx}")
    public String tx(@PathVariable("tx") String tx, Model model) {
        execute(model);
        return "tx";
    }

    @RequestMapping("/txsPending")
    public String txsPending(Model model) {
        execute(model);
        return "txsPending";
    }

}
