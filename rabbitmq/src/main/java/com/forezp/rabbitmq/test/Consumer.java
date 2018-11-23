package com.forezp.rabbitmq.test;

import com.forezp.rabbitmq.util.RabbitMqConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.test
 * @ClassName: Consumer
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/21 13:04
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/21 13:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Consumer {
    public static String Simple_Name="s1";
    public static String Simple_Names="exchangess";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection= RabbitMqConnectionUtil.getConnection("localhost",5672,"guest","guest");
        Channel channel=connection.createChannel();
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("x-message-ttl",20000);
        channel.queueDeclare(Simple_Name,false,false,false,map);
        channel.exchangeDeclare(Simple_Names,"direct");
        channel.queueBind(Simple_Name,Simple_Names,"wahaha");
        channel.basicConsume(Simple_Name,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                channel.basicAck(envelope.getDeliveryTag(),false);
                System.out.println("第一个为:"+new String(body));
            }
        });
    }
}
