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
 * @since 2018-01-22
 */
@Slf4j
@Controller
public class IndexController extends BaseController {

    public IndexController(YAMLConfig config) {
        super(config);
    }

    @RequestMapping("/")
    public String index(Model model) {
        execute(model);
        return "index";
    }

}
