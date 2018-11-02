package com.forezp.rabbitmq.springbootRabbitmq;

import org.springframework.amqp.core.AmqpTemplate;

import javax.annotation.Resource;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.springbootRabbitmq
 * @ClassName: HelloProvider
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/01 15:58
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/01 15:58
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class HelloProvider {

    @Resource
    public AmqpTemplate amqpTemplate;

    public void send(){
        String message="hello SpingBoot";
        System.out.println("send ："+message);
        amqpTemplate.convertAndSend("springBootMQ",message);

    }
}
