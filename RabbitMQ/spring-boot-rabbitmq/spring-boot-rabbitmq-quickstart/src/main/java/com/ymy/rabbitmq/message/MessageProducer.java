package com.ymy.rabbitmq.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 生产者 测试给消息添加Properties
 */
public class MessageProducer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_message_exchange";
    public static final String ROUTING_KEY = "test.message";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、发送消息
         * basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
         */
        // 设置消息的Properties
        Map<String, Object> headers = new HashMap<>();
        headers.put("my1", "111");
        headers.put("my2", "222");
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)    //消息的投递模式 2代表持久化消息 1代表非持久化消息
                .contentEncoding("UTF-8")   //字符集
                .expiration("20000")    //如果20S没有被消费者消费 就会被自动清除
                .headers(headers)  // 自定义属性
                .build();
        String msg = "Hello Message Properties Test...";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, properties, msg.getBytes());

        // 5、关闭资源
        channel.close();
        connection.close();

        System.out.println("***Set Message Properties 消息发送成功***");
    }
}
