package com.ymy.rabbitmq.limit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.ymy.rabbitmq.custom.CustomConsumer;

/**
 * 消费端 测试消费端消息的限流
 * 注意：消息限流的前提是设置手动ACK！
 */
public class QosConsumer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_qos_exchange";
    public static final String EXCHANGE_TYPE = "direct";
    public static final String ROUTING_KEY = "test.qos";
    public static final String QUEUE_NAME = "test_qos_queue";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、声明Exchange
         * exchangeDeclare(String exchange,String type,boolean durable,boolean autoDelete,boolean internal,Map<String, Object> arguments)
         */
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true, false, false, null);

        /**
         * 5、声明队列
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        /**
         * 6、Exchange和Queue的绑定关系
         * queueBind(String queue, String exchange, String routingKey, Map<String, Object> arguments)
         */
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY, null);

        /**
         * 7、消息限流
         * ACK一定要设置为false！
         * prefetchCount设置为1，消费端处理完一条消息之后Broker再推送一条消息
         */
        channel.basicQos(0, 1, false);
        channel.basicConsume(QUEUE_NAME, false, new CustomConsumer(channel));
    }
}
