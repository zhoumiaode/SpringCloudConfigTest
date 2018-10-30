package com.forezp.configclient.controller;

import com.forezp.configclient.service.TestService;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.configclient.controller
 * @ClassName: HiControlloer
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/10/12 14:42
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/10/12 14:42
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
public class HiControlloer {

    @Value("${foo.name}")
    String foo;

    @Resource
    public TestService testService;

    @RequestMapping(value = "/hi")
    public String hi(){
        return foo;
    }

    @GetMapping(value = "/test2")
    public String test2(){
        return testService.test2();
    }
}
