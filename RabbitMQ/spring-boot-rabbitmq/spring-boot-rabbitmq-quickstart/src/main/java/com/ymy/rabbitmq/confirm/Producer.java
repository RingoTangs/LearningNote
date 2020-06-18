package com.ymy.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * 生产者 测试消息发送到Broker的Confirm机制
 * 第一步：声明消息的确认模式 channel.confirmSelect();
 * 第二步：添加监听 channel.addConfirmListener();
 */
public class Producer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_confirm_exchange";
    public static final String ROUTING_KEY = "test.confirm";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);

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
        String msg = "Hello Message Confirm...";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.getBytes());

        /**
         * 6、添加一个确认监听
         */
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("***********消息成功发送到Broker！***************");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("**************消息发送失败！********************");
            }
        });
    }
}
