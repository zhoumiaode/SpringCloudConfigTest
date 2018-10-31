package com.forezp.rabbitmq.provider;

import com.forezp.rabbitmq.util.RabbitMqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.provider
 * @ClassName: ProviderWorker
 * @Description: Rabbitmq中Work模式，一个队列对应多个消费者，但每次只分发一个
 * @Author: zhoumiaode
 * @CreateDate: 2018/10/31 14:59
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/10/31 14:
 * 59
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class ProviderWorker {

    public static String QUEUE_NAME="workModel";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection= RabbitMqConnectionUtil.getConnection("localhost",5672,"guest","guest");
        Channel channel=connection.createChannel();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("x-message-ttl",100000);
        channel.queueDeclare(QUEUE_NAME,false,false,false,map);
        for(int i=0;i<10;i++) {
            String message="hello ,rabbitmq"+i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("[x] send message is '" + message + "'");
            //6.模拟发送消息延时,便于展示多个消费者竞争接受消息
            Thread.sleep(i * 10);
        }
    }
}
