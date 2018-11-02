package com.forezp.rabbitmq.springbootRabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.springbootRabbitmq
 * @ClassName: HelloReceiver
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/01 16:02
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/01 16:02
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Component
@RabbitListener(queues = "springBootMQ")
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }
}
