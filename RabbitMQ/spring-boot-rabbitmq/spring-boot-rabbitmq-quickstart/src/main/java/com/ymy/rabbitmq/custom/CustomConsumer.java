package com.ymy.rabbitmq.custom;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * 自定义的消费者
 * 需要继承 com.rabbitmq.client.DefaultConsumer
 * 重写handleDelivery()方法
 */
public class CustomConsumer extends DefaultConsumer {

    private Channel channel;

    public CustomConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String msg = new String(body);
        System.out.println("-----------------------------------------------");
        System.out.println("consumerTag：" + consumerTag);
        System.out.println("envelope：" + envelope.toString());
        System.out.println("body：" + msg);
        System.out.println("-----------------------------------------------");

        // 设置2S的延迟
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        /**
         * 手动ACK
         * basicAck(long deliveryTag, boolean multiple)
         * multiple：false。因为我们是逐条消息ACK的所以设置为false，不批量签收。
         */
        channel.basicAck(envelope.getDeliveryTag(), false);
    }
}
