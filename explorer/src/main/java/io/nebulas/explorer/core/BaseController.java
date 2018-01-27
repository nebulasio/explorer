package io.nebulas.explorer.core;

import io.nebulas.explorer.config.PageConfig;
import io.nebulas.explorer.config.YAMLConfig;
import io.nebulas.explorer.util.PageStringUtil;
import org.springframework.ui.Model;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-25
 */
public abstract class BaseController {
    private final YAMLConfig config;

    public BaseController(YAMLConfig config) {
        this.config = config;
    }

    protected void execute(Model model) {
        PageConfig pageConfig = config.getPage();
        model.addAttribute("baseUrl", pageConfig.getBaseUrl());
        model.addAttribute("hostUrl", pageConfig.getHostUrl());
        model.addAttribute("pageStringUtil", new PageStringUtil());
    }

}
