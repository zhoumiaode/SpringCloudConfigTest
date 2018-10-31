package com.forezp.rabbitmq.customer;

import com.forezp.rabbitmq.util.RabbitMqConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.customer
 * @ClassName: CustomerWork2
 * @Description: Rabbitmq中Work模式，一个队列对应多个消费者，但每次只分发一个
 * @Author: zhoumiaode
 * @CreateDate: 2018/10/31 15:31
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/10/31 15:31
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class CustomerWork2 {

    public static String QUEUE_NAME="workModel";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection= RabbitMqConnectionUtil.getConnection("localhost",5672,"guest","guest");
        Channel channel=connection.createChannel();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("x-message-ttl",100000);
        channel.basicQos(1);
        channel.queueDeclare(QUEUE_NAME,false,false,false,map);
        channel.basicConsume(QUEUE_NAME,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String routeKey=envelope.getRoutingKey();
                String message=new String(body);
                System.out.println("[x] received message : '"+message+"'");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long tag=envelope.getDeliveryTag();
                channel.basicAck(tag,false);
            }
        });
    }
}
