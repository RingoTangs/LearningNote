package com.ymy.rabbitmq.custom;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * 生产者
 */
public class Producer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_custom_consumer_exchange";
    public static final String ROUTING_KEY = "test.custom";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        // 4、指定消息的确认模式：Confirm
        channel.confirmSelect();

        /**
         * 5、发送消息
         * basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
         */
        String msg = "Hello Custom Consumer...";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.getBytes());

        /**
         * 6、添加一个确认监听
         */
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("*******send ok!*********");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("********send failure!*********");
            }
        });
    }
}
