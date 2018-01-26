package io.nebulas.explorer.controller;

import io.nebulas.explorer.config.YAMLConfig;
import io.nebulas.explorer.core.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String txsInternal(Model model) {
        execute(model);
        return "txsInternal";
    }

    @RequestMapping("/txs")
    public String txs(Model model) {
        execute(model);
        return "txs";
    }

    @RequestMapping("/txsPending")
    public String txsPending(Model model) {
        execute(model);
        return "txsPending";
    }

}
