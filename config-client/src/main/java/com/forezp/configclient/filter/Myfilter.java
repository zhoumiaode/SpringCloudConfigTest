package com.forezp.configclient.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.configclient.filter
 * @ClassName: Myfilter
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/23 11:05
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/23 11:05
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@WebFilter(urlPatterns = "/*",filterName = "myFilter")
@Order(1)
public class Myfilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;

        String url = new String(httpServletRequest.getRequestURI());

        //只过滤/actuator/bus-refresh请求
        if (!url.endsWith("/bus-refresh")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //使用HttpServletRequest包装原始请求达到修改post请求中body内容的目的
        CustomerRequestWrapper requestWrapper = new CustomerRequestWrapper(httpServletRequest);

        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
