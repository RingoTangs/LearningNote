package com.ymy.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Rabbitmq 消费者
 */
public class Consumer {
    public static final String HOST = "192.168.110.133";
    public static final String QUEUE_NAME = "test001";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、声明队列
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,Map<String, Object> arguments)
         * queue：队列的名字。
         * durable：是否持久化。
         * exclusive：是否独占 独占的意思就是这个Queue只能有我这一个Channel监听。
         * autoDelete：设置为true,Queue和Exchange没有绑定关系就会自动删除。
         * arguments：扩展参数。
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // 5、创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        /**
         * 6、channel的设置
         * basicConsume(String queue, boolean autoAck, Consumer callback)
         * queue：队列的名字
         * autoAck：是否自动签收
         * callback：具体的消费者对象
         */
        String consumerTag = channel.basicConsume(QUEUE_NAME, true, queueingConsumer);
        System.out.println("******consumerTag*****" + consumerTag);

        while (true) {
            /**
             * 7、获取消息
             * nextDelivery()：如果获取不到消息就一直阻塞
             */
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            // 8、获得消息体
            String msg = new String(delivery.getBody());
            System.out.println("*******消费端接收到的消息为=====>" + msg);
        }
    }
}
