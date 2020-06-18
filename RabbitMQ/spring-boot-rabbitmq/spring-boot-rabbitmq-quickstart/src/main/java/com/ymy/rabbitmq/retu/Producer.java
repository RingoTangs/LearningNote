package com.ymy.rabbitmq.retu;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 生产者 测试消息的Return机制
 * 当有不可路由的消息时候发送到Broker，来监听这些不可达的消息。
 * 第一步：mandatory设置为true监听不可达的消息。
 * 第二步：channel.addReturnListener()添加消息返回机制的监听者。
 */
public class Producer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_confirm_exchange"; //不存在的Exchange
    public static final String ROUTING_KEY = "TangShi"; //不存在的Routing Key

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、发送消息
         * basicPublish(String exchange, String routingKey, boolean mandatory, BasicProperties props, byte[] body)
         * mandatory：如果为true，则监听器会收到路由不可达的消息，然后进行后续处理；如果为false，那么Broker会自动删除该消息。
         */
        String msg = "Hello Message Return....";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, true, null, msg.getBytes());

        /**
         * 5、监控不可路由的消息
         * handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body)
         * replyCode：响应码
         * replyText：文本
         * exchange：具体的Exchange
         * routingKey：具体的Routing Key
         * properties：消息的属性
         * body：实际的消息体的内容。
         */
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("replyCode：" + replyCode);
                System.out.println("replyText：" + replyText);
                System.out.println("exchange：" + exchange);
                System.out.println("routingKey：" + routingKey);
                System.out.println("body：" + msg);
            }
        });
    }
}
