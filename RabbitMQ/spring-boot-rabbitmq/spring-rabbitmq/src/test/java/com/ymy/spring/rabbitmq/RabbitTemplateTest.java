package com.ymy.spring.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitTemplateTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_NAME = "topic01";
    private static final String ROUTING_KEY = "spring.template";

    @Test
    public void testSendMessage() {
        // 1、spring封装的消息属性
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().put("describe", "信息描述");
        messageProperties.getHeaders().put("type", "自定义信息类型");

        // 2、创建消息
        Message message = new Message("Hello RabbitMQ...".getBytes(), messageProperties);

        /**
         * 3、发送消息
         * convertAndSend(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor)
         * message：Object类型不是只能传Message，也可以传其他对象。
         * messagePostProcessor：在消息发送到Broker之前対Message进行修改。
         */
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                System.out.println("************消息发送到Broker之前再对消息进行额外的设置**************");
                message.getMessageProperties().getHeaders().put("describe", "额外的信息修改描述");
                message.getMessageProperties().getHeaders().put("attribute", "额外新加的attribute");
                return message;
            }
        });
    }
}
