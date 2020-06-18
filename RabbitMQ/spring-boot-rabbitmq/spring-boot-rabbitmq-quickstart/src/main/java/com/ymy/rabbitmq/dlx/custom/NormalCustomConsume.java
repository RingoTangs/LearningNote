package com.ymy.rabbitmq.dlx.custom;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;


import java.io.IOException;

/**
 * 自定义的消费者
 */
public class NormalCustomConsume extends DefaultConsumer {

    private Channel channel;

    public NormalCustomConsume(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String msg = new String(body);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("********body*******：" + body);
        if ((Integer) properties.getHeaders().get("flag") == 0) {
            System.out.println("********NACK************");
            // basicNack(long deliveryTag, boolean multiple, boolean requeue)
            channel.basicNack(envelope.getDeliveryTag(), false, false);
        } else {
            System.out.println("********ACK************");
            // basicAck(long deliveryTag, boolean multiple)
            channel.basicAck(envelope.getDeliveryTag(), false);
        }
    }
}
