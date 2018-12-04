package com.forezp.configclient.interfilter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.configclient.interfilter
 * @ClassName: MyWebMvcConfigurerAdapter
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/26 9:50
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/26 9:50
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Configuration
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterfilter()).addPathPatterns("/**");
    }
}
