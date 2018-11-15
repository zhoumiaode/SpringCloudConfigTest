package com.forezp.rabbitmq.provider;

import com.forezp.rabbitmq.util.RabbitMqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.provider
 * @ClassName: ProviderRouteKey
 * @Description:  Rabbitmq中路由模式，通过key来匹配
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/01 10:09
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/01 10:09
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class ProviderRouteKey {

    public static String Exchange_Name="exchange_key";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection= RabbitMqConnectionUtil.getConnection("localhost",5672,"guest","guest");
        Channel channel=connection.createChannel();
        channel.exchangeDeclare(Exchange_Name,"direct",true,false,false,null);
        String message="first";
        channel.basicPublish(Exchange_Name,"second",null,message.getBytes());
        System.out.println("[x] send'" + message + "'");
        //6.关闭通道和连接
        channel.close();
        connection.close();
    }

}
