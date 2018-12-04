package com.forezp.configclient.interfilter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.configclient.interfilter
 * @ClassName: MyInterfilter
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/26 9:37
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/26 9:37
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class MyInterfilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("拦截器初始化");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("拦截器开启");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("拦截器结束");
    }
}
