package com.forezp.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.config
 * @ClassName: RabbitMqConfig
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/01 15:55
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/01 15:55
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue queue(){
        return  new Queue("springBootMQ");
    }
}
