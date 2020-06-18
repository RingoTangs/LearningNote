package com.ymy.spring.cloud.stream.receive;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@EnableBinding(Sink.class)
@Component
public class RabbitReceiver {

    @StreamListener(Sink.INPUT)
    public void receiver(Message message) throws Exception{
        Channel channel = (Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        System.out.println(message.getPayload().getClass().getSimpleName());
        channel.basicAck(deliveryTag, false);
    }
}
