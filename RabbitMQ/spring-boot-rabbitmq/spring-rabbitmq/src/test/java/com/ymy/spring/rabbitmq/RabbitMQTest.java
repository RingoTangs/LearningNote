package com.ymy.spring.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * RabbitMQ 测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTest {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Test
    public void testAdmin() {
        /**
         * 1、RabbitAdmin声明Exchange
         */
        // DirectExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments)
        rabbitAdmin.declareExchange(new DirectExchange("test.direct", false, false, null));
        // TopicExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments)
        rabbitAdmin.declareExchange(new TopicExchange("test.topic", false, false, null));
        // FanoutExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments)
        rabbitAdmin.declareExchange(new FanoutExchange("test.fanout", false, false, null));

        /**
         * 2、RabbitAdmin声明Queue
         */
        // Queue(String name, boolean durable, boolean exclusive, boolean autoDelete, @Nullable Map<String, Object> arguments)
        rabbitAdmin.declareQueue(new Queue("test.direct.queue", false, false, false, null));
        rabbitAdmin.declareQueue(new Queue("test.topic.queue", false, false, false, null));
        rabbitAdmin.declareQueue(new Queue("test.fanout.queue", false, false, false, null));

        /**
         * 3、RabbitAdmin绑定Exchange和Queue
         */
        // Binding(String destination, DestinationType destinationType, String exchange, String routingKey, @Nullable Map<String, Object> arguments)
        rabbitAdmin.declareBinding(new Binding("test.direct.queue", Binding.DestinationType.QUEUE, "test.direct", "admin.direct", null));


        /**
         * 4、RabbitAdmin声明Queue和Exchange并进行绑定
         */
        rabbitAdmin.declareBinding(BindingBuilder
                .bind(new Queue("test.topic.queue", false, false, false, null))
                .to(new TopicExchange("test.topic", false, false, null))
                .with("user.#")); // Topic Exchange这里有Routing KeyAPI

        // Fanout Exchange 这里没有Routing Key API
        rabbitAdmin.declareBinding(BindingBuilder
                .bind(new Queue("test.fanout.queue", false, false, false, null))
                .to(new FanoutExchange("test.fanout", false, false, null)));

        /**
         * 5、RabbitAdmin 清空指定队列的消息
         */
        rabbitAdmin.purgeQueue("test.direct.queue", false);
    }
}
