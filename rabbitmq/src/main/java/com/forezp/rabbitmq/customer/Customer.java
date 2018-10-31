package com.forezp.rabbitmq.customer;

import com.forezp.rabbitmq.util.RabbitMqConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.customer
 * @ClassName: Customer
 * @Description: Rabbitmq中简单模式1对1的消息队列：customer
 * @Author: zhoumiaode
 * @CreateDate: 2018/10/31 10:43
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/10/31 10:43
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Customer {

    private static String QUEUE_NAME="hello";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection= RabbitMqConnectionUtil.getConnection("localhost",5672,"guest","guest");
        Channel channel=connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicConsume(QUEUE_NAME,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException
            {
                String routingKey = envelope.getRoutingKey();
                String contentType = properties.getContentType();
                System.out.println(routingKey);
                System.out.println(new String(body));
                long deliveryTag = envelope.getDeliveryTag();
                // (process the message components here ...)
                channel.basicAck(deliveryTag, false);
            }
        });

    }
}
