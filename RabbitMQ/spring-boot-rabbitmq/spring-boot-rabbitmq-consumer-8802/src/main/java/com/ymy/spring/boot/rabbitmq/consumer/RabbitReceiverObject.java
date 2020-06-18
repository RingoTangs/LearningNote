package com.ymy.spring.boot.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.ymy.spring.boot.rabbitmq.entity.Order;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 消费端监听队列 如果Message是java对象一定要写无参构造器！！
 */
@Component
public class RabbitReceiverObject {

    private static final String EXCHANGE_NAME = "exchange-object";
    private static final String ROUTING_KEY = "springboot.*";
    private static final String QUEUE_NAME = "queue-object";

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = QUEUE_NAME,
                    durable = "true"),
            exchange = @Exchange(value = EXCHANGE_NAME,
                    type = ExchangeTypes.TOPIC,
                    durable = Exchange.FALSE),
            key = ROUTING_KEY
    ))
    @RabbitHandler
    public void onOrderMessage(@Payload Order order,
                               @Headers Map<String, Object> headers, Channel channel) throws Exception {
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        System.out.println("order id---> " + order.getId());
        System.out.println("order name---> " + order.getName());
        System.out.println(" deliveryTag ----> " + deliveryTag);
        channel.basicAck(deliveryTag, false);
    }
}
