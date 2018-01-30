package io.nebulas.explorer.config;

import com.google.common.base.Throwables;
import io.nebulas.explorer.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-30
 */
@Slf4j
@AllArgsConstructor
@ControllerAdvice(basePackages = "io.nebulas.explorer.controller")
public class GlobalExceptionHandler {
    private final YAMLConfig config;

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception)
            throws Exception {
        // Rethrow annotated exceptions or they will be processed here instead.
        if (AnnotationUtils.findAnnotation(exception.getClass(),
                ResponseStatus.class) != null)
            throw exception;

        log.error("Request: " + req.getRequestURI() + " raised " + exception);

        PageConfig pageConfig = config.getPage();

        ModelAndView mav = new ModelAndView();
        mav.addObject("status", 404);
        mav.addObject("error", "Not Found");
        mav.addObject("message", Throwables.getRootCause(exception).getMessage());
        mav.addObject("baseUrl", pageConfig.getBaseUrl());
        mav.addObject("hostUrl", pageConfig.getHostUrl());
        mav.setViewName("error/404");
        return mav;
    }
}
