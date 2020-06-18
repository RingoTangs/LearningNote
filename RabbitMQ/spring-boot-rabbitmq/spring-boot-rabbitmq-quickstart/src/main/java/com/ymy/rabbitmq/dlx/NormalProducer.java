package com.ymy.rabbitmq.dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 生产者 向正常的Exchange投递消息
 */
public class NormalProducer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "normal_exchange";
    public static final String ROUTING_KEY = "test.normal";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        for (int i = 0; i < 5; i++) {
            String msg = "Hello Rabbitmq Message.." + i;
            Map<String, Object> headers = new HashMap<>();
            headers.put("flag", i);
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .headers(headers)
                    .expiration("10000")
                    .build();
            // 发送消息 basicPublish(String exchange, String routingKey, AMQP.BasicProperties props, byte[] body)
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, properties, msg.getBytes());
        }
    }
}
