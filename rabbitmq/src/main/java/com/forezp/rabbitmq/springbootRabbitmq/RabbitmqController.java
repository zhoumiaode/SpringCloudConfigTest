package com.forezp.rabbitmq.springbootRabbitmq;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.springbootRabbitmq
 * @ClassName: RabbitmqController
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/02 13:59
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/02 13:59
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@RestController
public class RabbitmqController {

    @Resource
    private Sender sender;


    @RequestMapping(value = "/simple0")
    public void Simple1Send(){
        String message="hello";
        sender.simple1Send(message);
    }


    @RequestMapping(value = "/simple")
    public void SimpleSend(){
        String message="hello";
        sender.simpleSend(message);
    }

    @RequestMapping(value = "/simple1")
    public void SimpleSend1(){
        Map map=new HashMap();
        map.put("hhel","je");
        sender.simpleSend(map);
    }

    @RequestMapping(value = "/fanout")
    public void FanoutSend(){
        String message="hello";
        sender.psSend(message);
    }
    @RequestMapping(value = "/direct")
    public void directSend(){
        String message="hello";
        sender.routeKeySend(message);
    }
    @RequestMapping(value = "/topic")
    public void TopicSend(){
        String message="hello";
        sender.topicSend(message);
    }
}
