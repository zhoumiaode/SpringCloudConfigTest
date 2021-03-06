package com.forezp.rabbitmq.customer;

import com.forezp.rabbitmq.util.RabbitMqConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.customer
 * @ClassName: CustomerPublishOrder1
 * @Description: Rabbitmq中topic模式的消息队列：customer
 * @Author: zhoumiaode
 * @CreateDate: 2018/10/31 17:13
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/10/31 17:13
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class CustomerTopic1 {

    public static String QUEUE_NAME="topic";
    public static String CHANGE_TYPE="exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection= RabbitMqConnectionUtil.getConnection("localhost",5672,"guest","guest");
        Channel channel=connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列到交换机,同时绑定路由键,topic模式下采用.作为分隔符进行模糊查询，其中*匹配一个单词，#匹配0个或多个单词
        channel.queueBind(QUEUE_NAME,CHANGE_TYPE,"topic.*");
        //设置接受消息条数
        channel.basicQos(1);
        //监听队列
        channel.basicConsume(QUEUE_NAME,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routeKey=envelope.getRoutingKey();
                String message=new String(body);
                System.out.println("[消费者1] received message : '"+message+"'");
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
