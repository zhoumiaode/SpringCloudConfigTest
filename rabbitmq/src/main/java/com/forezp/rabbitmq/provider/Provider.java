package com.forezp.rabbitmq.provider;

import com.forezp.rabbitmq.util.RabbitMqConnectionUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.provider
 * @ClassName: Provider
 * @Description: Rabbitmq中简单模式1对1的消息队列：provider
 * @Author: zhoumiaode
 * @CreateDate: 2018/10/30 17:03
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/10/30 17:03
 * @UpdateRemark: The modified co
 * ntent
 * @Version: 1.0
 */
public class Provider {

    private static String QUEUE_NAME="hello";
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection= RabbitMqConnectionUtil.getConnection("localhost",5672,"guest","guest");
        //声明通道
        Channel channel=connection.createChannel();
        //声明队列
        //第一个参数为队列名称，第二个为是否持久化，即服务器重启后是否存在，存放于elg的数据库读取，第三个表示是否独立队列，场景：每个队列只能被一个消费
        //者访问，第四个表示是否自动删除，即消费者为0时，第五个表示其他参数集
        Map<String,Object> map=new HashMap<String,Object>();
        //设置队列中消息的生存时间
        //map.put("x-message-ttl",3000);
        //设置队列的生存时间
        //map.put("x-expires",12000);
        channel.queueDeclare(QUEUE_NAME,true,false,false,map);
        String message = "hello12334";
        //5.发布消息
        //设置单条信息的生存时间
       // AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        //builder.expiration("3000");
        //AMQP.BasicProperties build = builder.build();
        //第一个表示交换机名称，第二个表示路由键，第三个表示属性设置，第四个表示消息实体
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("[x] send'"+message+"'");
        //6.关闭通道和连接
        channel.close();
        connection.close();

    }
}
