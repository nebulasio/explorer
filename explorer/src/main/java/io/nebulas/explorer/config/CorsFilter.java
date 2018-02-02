package io.nebulas.explorer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-30
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class CorsFilter implements Filter {
    private static final Set<String> DISALLOWED_METHOD = new HashSet<>();

    static {
        DISALLOWED_METHOD.add("OPTIONS");
        //        DISALLOWED_METHOD.add("PUT");
        //        DISALLOWED_METHOD.add("DELETE");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, x-auth-token, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type,Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");
        if (DISALLOWED_METHOD.contains(request.getMethod())) {
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
