package com.forezp.rabbitmq.springbootRabbitmq;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.springbootRabbitmq
 * @ClassName: TestController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/20 10:42
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/20 10:42
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
public class TestController {

    @GetMapping(value = "asd")
    public String test(){
        return "get asd";
    }

    @PostMapping(value = "asd")
    public String test1(){
        return "post asd";
    }
}
