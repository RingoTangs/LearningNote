package com.ymy.spring.boot.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {

    private static final String EXCHANGE_NAME = "exchange-1";
    private static final String ROUTING_KEY = "springboot.*";
    private static final String QUEUE_NAME = "queue-1";

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = QUEUE_NAME,
                    durable = "true"),
            exchange = @Exchange(value = EXCHANGE_NAME,
                    type = ExchangeTypes.TOPIC,
                    durable = Exchange.TRUE),
            key = ROUTING_KEY
    ))
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        // 获得DeliveryTag
        Long delivery_tag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

        // 获得PayLoad
        byte[] payload = (byte[]) message.getPayload();

        System.out.println("delivery_tag ----> " + delivery_tag);
        System.out.println("timestamp ----> " + message.getHeaders().getTimestamp());
        System.out.println("payload ---> " + new String(payload));

        // 手动签收
        channel.basicAck(delivery_tag, false);
    }
}
