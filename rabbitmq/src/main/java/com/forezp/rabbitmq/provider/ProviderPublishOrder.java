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
 * @ClassName: ProviderPublishOrder
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/10/31 17:02
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/10/31 17:02
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class ProviderPublishOrder {

    public static String QUEUE_NAME="PublishOrderModel";
    public static String CHANGE_TYPE="fanout_exchange11";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection= RabbitMqConnectionUtil.getConnection("localhost",5672,"guest","guest");
        Channel channel=connection.createChannel();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("x-expires",10000000);
        map.put("x-message-ttl",10000000);
        channel.exchangeDeclare(CHANGE_TYPE,"fanout");
        String message="PublishOrder123";
        channel.basicPublish(CHANGE_TYPE,"",null,message.getBytes());
        System.out.println("[x] send'" + message + "'");
        //6.关闭通道和连接
        channel.close();
        connection.close();

    }
}
