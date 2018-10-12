package com.forezp.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "/hi")
    public String hi(){
        return foo;
    }
}
