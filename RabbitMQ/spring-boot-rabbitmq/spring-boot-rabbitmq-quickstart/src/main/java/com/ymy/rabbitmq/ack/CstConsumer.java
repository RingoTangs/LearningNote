package com.ymy.rabbitmq.ack;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * 自定义消费者 测试ACK和NACK
 */
public class CstConsumer extends DefaultConsumer {

    private Channel channel;

    public CstConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String msg = new String(body);
        System.out.println("---------------------------------------");
        System.out.println("consumerTag：" + consumerTag);
        System.out.println("envelope：" + envelope.toString());
        System.out.println("body：" + msg);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if ((Integer) properties.getHeaders().get("flag") == 0) {
            System.out.println("********NACK***************");
            /**
             * basicNack(long deliveryTag, boolean multiple, boolean requeue)
             * multiple：是否批量NACK
             * requeue：是否重回队列
             */
            channel.basicNack(envelope.getDeliveryTag(), false, true);
        } else {
            System.out.println("********ACK***************");
            // basicAck(long deliveryTag, boolean multiple)
            channel.basicAck(envelope.getDeliveryTag(), false);
        }
    }
}
