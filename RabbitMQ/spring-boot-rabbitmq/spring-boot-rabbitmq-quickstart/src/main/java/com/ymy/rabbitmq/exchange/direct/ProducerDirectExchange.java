package com.ymy.rabbitmq.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 生产者 向Direct Exchange发送消息
 */
public class ProducerDirectExchange {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_direct_exchange";
    public static final String ROUTING_KEY = "test.direct";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        // 4、发送消息
        String msg = "Hello RabbitMQ Direct Exchange Message...";
        // basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.getBytes());

        // 5、关闭资源
        channel.close();
        connection.close();

        System.out.println("***ProducerDirectExchange 消息发送成功***");
    }
}
