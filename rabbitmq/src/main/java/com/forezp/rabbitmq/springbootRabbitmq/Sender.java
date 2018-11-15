package com.forezp.rabbitmq.springbootRabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.springbootRabbitmq
 * @ClassName: Sender
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/02 13:49
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/02 13:49
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Component
public class Sender {

    private static String Simple_Name="simple";
    private static String Simple_Name1="simple1";
    private static String Fanout_Exchange="fanouts";
    private static String Direct_Exchange="directs";
    private static String Topic_Exchange="topic";

    @Resource
    private AmqpTemplate amqpTemplate;
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void simple1Send(String message){
        Logger log = LoggerFactory.getLogger(Sender.class);
        System.out.println("简单模式发送:"+message);
        //rabbitTemplate.convertAndSend(Simple_Name,message);
        rabbitTemplate.convertAndSend(Direct_Exchange,"route",message);
    }

    public void simpleSend(String message){
        System.out.println("简单模式发送:"+message);
        amqpTemplate.convertAndSend(Simple_Name,message);
    }

    public void simpleSend(Map map){
        System.out.println("简单模式发送:"+map);
        amqpTemplate.convertAndSend(Simple_Name,map);
    }

    public void psSend(String message){
        System.out.println("订阅/发送模式:"+message);
        amqpTemplate.convertAndSend(Fanout_Exchange,"",message);
    }

    public void routeKeySend(String message){
        System.out.println("指定路由键模式:"+message);
        amqpTemplate.convertAndSend(Direct_Exchange,"route",message);
    }
    public void topicSend(String message){
        System.out.println("模糊路由键模式:"+message);
        amqpTemplate.convertAndSend(Topic_Exchange,"route.ter.ter",message);
    }

    /**
    * @Description:  延时队列测试
    * @Param: [message]
    * @return: void
    * @Author: zhoumiaode
    * @Date: 2018/11/15
    */
    public void topicSendDelay(String message){
        System.out.println("消息模式:"+message);
        rabbitTemplate.convertAndSend(Topic_Exchange, "delay.delay", message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("10000");
                return message;
            }
        });
    }
}
