package com.ymy.rabbitmq.ack;

import com.rabbitmq.client.*;

import java.util.HashMap;
import java.util.Map;

public class AckProducer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_ack_exchange";
    public static final String ROUTING_KEY = "test.ack";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        for (int i = 0; i < 5; i++) {
            /**
             * 4、发送消息
             * basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
             */
            String msg = "Hello Rabbitmq Ack Test..." + i;
            Map<String, Object> headers = new HashMap<>();
            headers.put("flag", i);
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .headers(headers)
                    .build();
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, properties, msg.getBytes());
        }
    }
}
