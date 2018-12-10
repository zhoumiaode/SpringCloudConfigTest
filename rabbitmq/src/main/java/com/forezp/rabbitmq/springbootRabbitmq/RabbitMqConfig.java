package com.forezp.rabbitmq.springbootRabbitmq;

/**
 * @ProjectName: scfchapter6
 * @Package: com.forezp.rabbitmq.springbootRabbitmq
 * @ClassName: RabbitMqConfig
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/11/02 14:01
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/11/02 14:01
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */


import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {

    private  static final Logger logger = LoggerFactory.getLogger(RabbitMqConfig.class);

    private static String Simple_Name="simple";
    private static String Simple_Name1="simple1";
    private static String Fanout_Queue1="FQ1";
    private static String Fanout_Queue2="FQ2";
    private static String Direct_Queue1="DQ1";
    private static String Direct_Queue2="DQ2";
    private static String Topic_Queue1="TQ1";
    private static String Topic_Queue2="TQ2";

    private static String Delay_Queue="YQ1";
    private static String Fanout_Exchange="fanouts";
    private static String Direct_Exchange="directs";
    private static String Topic_Exchange="topic";

    /**
    * @Description: 声明队列
    * @Param: []
    * @return: org.springframework.amqp.core.Queue
    * @Author: zhoumiaode
    * @Date: 2018/11/02
    */
    @Bean
    public Queue queue(){
        return new Queue(Simple_Name,false,false,false,null);
    }
    /**
    * @Description:  定义一个队列，同时设置队列生存时间以及消息生存时间
    * @Param: []
    * @return: org.springframework.amqp.core.Queue
    * @Author: zhoumiaode
    * @Date: 2018/11/02
    */
    @Bean
    public Queue queue0(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("x-message-ttl",50000);
        map.put("x-expires",50000);
        return new Queue(Simple_Name1,false,false,false,map);
    }
    @Bean
    public Queue queue1(){
        return new Queue(Fanout_Queue1,false);
    }
    @Bean
    public Queue queue2(){
        return new Queue(Fanout_Queue2,false);
    }
    @Bean
    public Queue queue3(){
        return new Queue(Direct_Queue1,false);
    }
    @Bean
    public Queue queue4(){
        return new Queue(Direct_Queue2,false);
    }
    @Bean
    public Queue queue5(){
        return new Queue(Topic_Queue1,false);
    }
    @Bean
    public Queue queue6(){
        return new Queue(Topic_Queue2,false);
    }

    @Bean
    public Queue queue7(){
        Map<String,Object> map=new HashMap<String,Object>();
        //设置队列的死信队列，包括死信队列的交换机以及路由键
        map.put("x-dead-letter-exchange",Topic_Exchange);
        map.put("x-dead-letter-routing-key","route.*");
        return new Queue(Delay_Queue,false,false,false,map);
    }
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(Fanout_Exchange);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(Direct_Exchange);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(Topic_Exchange);
    }

    /**
    * @Description:  绑定指定队列到指定交换机，此处为Fanout广播模式
    * @Param: []
    * @return: org.springframework.amqp.core.Binding
    * @Author: zhoumiaode
    * @Date: 2018/11/02
    */
    @Bean
    public Binding FQ1(){
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }
    @Bean
    public Binding FQ2(){
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }

    /**
    * @Description:  绑定指定队列到指定交换机，此处为路由键模式
    * @Param: []
    * @return: org.springframework.amqp.core.Binding
    * @Author: zhoumiaode
    * @Date: 2018/11/02
    */
    @Bean
    public Binding FQ3(){
        return BindingBuilder.bind(queue3()).to(directExchange()).with("route");
    }
    @Bean
    public Binding FQ4(){
        return BindingBuilder.bind(queue4()).to(directExchange()).with("route");
    }

    /**
    * @Description:  绑定指定队列到指定交换机，此处为模糊路由键模式
    * @Param: []
    * @return: org.springframework.amqp.core.Binding
    * @Author: zhoumiaode
    * @Date: 2018/11/02
    */
    @Bean
    public Binding FQ5(){
        return BindingBuilder.bind(queue5()).to(topicExchange()).with("route.*");
    }
    @Bean
    public Binding FQ6(){
        return BindingBuilder.bind(queue6()).to(topicExchange()).with("route.#");
    }

    @Bean
    public Binding FQ7(){
        return BindingBuilder.bind(queue7()).to(topicExchange()).with("delay.#");
    }

    @RabbitListener(queues = "FQ1")
    public void FQ1(@Payload String message) {
        logger.info("广播队列(FQ1):" + message );

    }
    @RabbitListener(queues = "FQ2")
    public void FQ2(@Payload String message) {
        logger.info("广播队列(FQ2):" + message );

    }
    @RabbitListener(queues = "DQ1")
    public void FQ3(@Payload String message,Channel channel, Message messages) throws IOException {
        logger.info("HelloReceiver收到  : " + message +"收到时间"+new Date());
        try {
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(messages.getMessageProperties().getDeliveryTag(),false);
            logger.info("receiver success");
        } catch (IOException e) {
            e.printStackTrace();
            //丢弃这条消息
            channel.basicNack(messages.getMessageProperties().getDeliveryTag(), false,false);
            logger.info("receiver fail");
        }
        logger.info("路由键队列(DQ1):" + message );

    }
    @RabbitListener(queues = "DQ2")
    public void FQ4(@Payload String message) {
        logger.info("路由键队列(DQ2):" + message );

    }

    @RabbitListener(queues = "TQ1")

    public void FQ5(@Payload String message,Channel channel,Message messages) throws IOException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        channel.basicAck(messages.getMessageProperties().getDeliveryTag(),false);
        logger.info("模糊路由键队列(TQ1):" + message+"111" );

    }
    /**
    * @Description:  监听队列TQ2
    * @Param: [message]
    * @return: void
    * @Author: zhoumiaode
    * @Date: 2018/11/02
    */
    @RabbitListener(queues = "TQ2")
    public void FQ6(@Payload String message,Channel channel,Message messages) throws IOException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        channel.basicAck(messages.getMessageProperties().getDeliveryTag(),false);
        logger.info("模糊路由键队列(TQ2):" + message );

    }
    /*@RabbitListener(queues = "simple")
    @RabbitHandler
    public void process1(@Payload Map map, Channel channel, Message messages) throws IOException {
        channel.basicAck(messages.getMessageProperties().getDeliveryTag(),false);
        logger.info("Listener收到Map类型mq消息:" + map);
        logger.info("Listener收到String类型mq消息:" + messages.getBody());
    }*/

    /**
    * @Description:  组合键，通过RabbitListener监听指定队列，RabbitHandler指定处理的消息的类型,同时指定消息确认机制
    * @Param: [message]
    * @return: void
    * @Author: zhoumiaode
    * @Date: 2018/11/02
    */
    @RabbitListener(queues = "simple")
    @RabbitHandler
    public void process1(@Payload String message, Channel channel, Message messages) throws IOException {
        logger.info("HelloReceiver收到  : " + message +"收到时间"+new Date());
        try {
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(messages.getMessageProperties().getDeliveryTag(),false);
            logger.info("receiver success");
        } catch (IOException e) {
            e.printStackTrace();
            //消息重新回到队列
            channel.basicNack(messages.getMessageProperties().getDeliveryTag(), false,false);
            logger.info("receiver fail");
        }
    }

    @RabbitListener(queues = "simple1")
    @RabbitHandler
    public void process0(@Payload String message) {

        logger.info("Listener收到String类型mq消息:" + message);
    }


    @RabbitListener(queues = "YQ1")
    @RabbitHandler
    public void process(@Payload String message,Channel channel,Message messages) throws IOException {
        try {
            String[] str=new String[3];
            System.out.println(str[4]);
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(messages.getMessageProperties().getDeliveryTag(),false);
            logger.info("receiver success");
        } catch (Exception e) {
           // e.printStackTrace();
            //拒绝此条消息，使消息成为死信，转发到死信队列,第二个参数为true表示重新放入消息的队列，为false则直接销毁
            //channel.basicReject(messages.getMessageProperties().getDeliveryTag(), false);
            //使消息返回队列,其中第三个参数为true表示返回队列，第二个参数表示是否批量，即是否只针对当前消息
            channel.basicNack(messages.getMessageProperties().getDeliveryTag(), false,false);
            logger.info("receiver fail");
        }
        logger.info("延迟第一层消息:" + message);
    }

    @RabbitListener(queues = "YQ1")
    @RabbitHandler
    public void process11(@Payload String message,Channel channel,Message messages) throws IOException {
        try {
            String[] str = new String[3];
            System.out.println(str[4]);
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(messages.getMessageProperties().getDeliveryTag(), false);
            logger.info("receiver success");
        } catch (Exception e) {
            // e.printStackTrace();
            //拒绝此条消息，使消息成为死信，转发到死信队列,第二个参数为true表示重新放入消息的队列，为false则直接销毁
            //channel.basicReject(messages.getMessageProperties().getDeliveryTag(), false);
            //使消息返回队列,其中第三个参数为true表示返回队列，第二个参数表示是否批量，即是否只针对当前消息
            channel.basicNack(messages.getMessageProperties().getDeliveryTag(), false, false);
            logger.info("receiver fail");
        }
        logger.info("延迟第一层消息（轮询）:" + message);
    }

    @RabbitListener(
            bindings = {
            @QueueBinding(value =@org.springframework.amqp.rabbit.annotation.Queue(value = "q5",durable = "true")
            ,exchange = @org.springframework.amqp.rabbit.annotation.Exchange(value = "zhihao.miao.exchange",durable = "true"),key = "welcome")
            }
     )
    public void process12(){

    }
}
