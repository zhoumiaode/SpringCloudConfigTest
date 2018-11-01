package com.forezp.rabbitmq.customer;

import com.forezp.rabbitmq.util.RabbitMqConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.customer
 * @ClassName: CustomerPublishOrder1
 * @Description: Rabbitmq中direct模式的消息队列：customer
 * @Author: zhoumiaode
 * @CreateDate: 2018/10/31 17:13
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/10/31 17:13
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class CustomerRouteKey2 {

    public static String QUEUE_NAME="second";
    public static String CHANGE_TYPE="exchange_key";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection= RabbitMqConnectionUtil.getConnection("localhost",5672,"guest","guest");
        Channel channel=connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME,CHANGE_TYPE,"second");
        //设置接受消息条数
        channel.basicQos(1);
        //监听队列
        channel.basicConsume(QUEUE_NAME,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routeKey=envelope.getRoutingKey();
                String message=new String(body);
                System.out.println("[消费者2] received message : '"+message+"'");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long tag=envelope.getDeliveryTag();
                channel.basicAck(tag,false);
            }
        });
    }
}
