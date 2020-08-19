package vip.codehome.springboot.tutorials.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/***
 *@author zyw
 *@createTime 2020/8/17 10:31
 *@description
 *@version 1.0
 */
@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
         HttpServletRequest req=(HttpServletRequest)servletRequest;
         log.info(req.getRequestURI());
         filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
