package com.forezp.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.util
 * @ClassName: RabbitMqConnectionUtil
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/10/30 17:00
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/10/30 17:00
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class RabbitMqConnectionUtil {

    public static Connection getConnection(String host,int port,String username,String password) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPort(port);
        Connection connection=connectionFactory.newConnection();
        return connection;
    }
}
