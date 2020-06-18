package com.ymy.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Rabbitmq 生产者
 */
public class Producer {

    public static final String HOST = "192.168.110.133";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        // 4、通过Channel发送数据
        // basicPublish(String exchange,String routingKey,BasicProperties props,byte[] body)
        channel.basicPublish("", "test001", null, "hello rabbitmq".getBytes());

        // 5、关闭连接
        channel.close();
        connection.close();

        System.out.println("*****生产者消息发布成功！****");
    }
}
