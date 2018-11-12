package com.forezp.rabbitmq.springbootRabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
        System.out.println("简单模式发送:"+message);
        rabbitTemplate.setReturnCallback((RabbitTemplate.ReturnCallback) this);
        this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                System.out.println("HelloSender消息发送失败" + cause + correlationData.toString());
            } else {
                System.out.println("HelloSender 消息发送成功 ");
            }
        });
        amqpTemplate.convertAndSend(Simple_Name,message);
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
}
