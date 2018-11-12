package com.forezp.rabbitmq.provider;

import com.forezp.rabbitmq.util.RabbitMqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.provider
 * @ClassName: ProviderTest
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/07 14:45
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/07 14:45
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class ProviderTest {

    public static String QUEUE_NAME="test";

    public static String Exchange_Name="wa";
    public static void main(String[] agrs) throws IOException, TimeoutException {
        Connection connection= RabbitMqConnectionUtil.getConnection("localhost",5672,"guest","guest");
        Channel channel=connection.createChannel();
        channel.exchangeDeclare(Exchange_Name,"fanout");
        String me="123";
        channel.basicPublish(Exchange_Name,"",null,me.getBytes());
        channel.close();
        connection.close();
    }
}
