package com.ymy.spring.boot.rabbitmq.producer;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Component
public class RabbitSender {

    private static final String EXCHANGE_NAME = "exchange-object";
    private static final String ROUTING_KEY = "springboot.hello";


    @Autowired
    private RabbitTemplate rabbitTemplate;

    final ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.out.println("*************消息发送成功**************");
            System.out.println("correlationData：" + correlationData);
            System.out.println("ack：" + ack);
            System.out.println("cause：" + cause);
        }
    };

    final ReturnCallback returnCallback = new ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message,
                                    int replyCode, String replyText,
                                    String exchange, String routingKey) {
            System.out.println("**************Return********************");
            System.out.println("replyCode：" + replyCode);
            System.out.println("replyText：" + replyText);
            System.out.println("exchange：" + exchange);
            System.out.println("routingKey：" + routingKey);
        }
    };

    public void send(Object message, Map<String, Object> properties) {
        MessageHeaders messageHeaders = new MessageHeaders(properties);
        Message msg = MessageBuilder.createMessage(message, messageHeaders);

        // 设置消息确认
        rabbitTemplate.setConfirmCallback(confirmCallback);

        // 消息的序列化使用Json
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        // 设置消息返回
        rabbitTemplate.setReturnCallback(returnCallback);

        // id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString().substring(0, 6) + "_" + LocalDateTime.now());
        // convertAndSend(String exchange, String routingKey, Object message, CorrelationData correlationData)
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, msg, correlationData);
    }

    public void sendObject(Object message) {
        // 设置消息确认
        rabbitTemplate.setConfirmCallback(confirmCallback);

        // 设置消息返回
        rabbitTemplate.setReturnCallback(returnCallback);

        // id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString().substring(0, 6) + "_" + LocalDateTime.now());
        // convertAndSend(String exchange, String routingKey, Object message, CorrelationData correlationData)
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message, correlationData);
    }
}
