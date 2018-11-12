package com.forezp.rabbitmq.provider;

import com.forezp.rabbitmq.util.RabbitMqConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.provider
 * @ClassName: CustomerTest
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/07 15:08
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/07 15:08
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class CustomerTest {

    public static String QUEUE_NAME="test";
    public static String Exchange_Name="wa";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection= RabbitMqConnectionUtil.getConnection("localhost",5672,"guest","guest");
        Channel channel=connection.createChannel();
        channel.queueBind(QUEUE_NAME,Exchange_Name,"");
        channel.basicConsume("",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });

    }
}
