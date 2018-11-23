package com.forezp.rabbitmq.test;

import com.forezp.rabbitmq.util.RabbitMqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.test
 * @ClassName: Provider
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/21 11:47
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/21 11:47
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Provider {


    public static String Simple_Name="s1";
    public static String Simple_Namess="exchangess";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection= RabbitMqConnectionUtil.getConnection("localhost",5672,"guest","guest");
        Channel channel=connection.createChannel();
/*        Map<String,Object> map=new HashMap<String, Object>();
        map.put("x-message-ttl",20000);
        channel.queueDeclare(Simple_Name,false,false,false,map);*/
        channel.exchangeDeclare(Simple_Namess,"direct");
        String message="wahaha";
        channel.basicPublish(Simple_Namess,"w", MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        System.out.println("发送消息为："+message);
        channel.close();
        connection.close();
    }
}
