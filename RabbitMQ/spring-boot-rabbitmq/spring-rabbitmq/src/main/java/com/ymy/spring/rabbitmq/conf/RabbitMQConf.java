package com.ymy.spring.rabbitmq.conf;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class RabbitMQConf {

    private static final String ADDRESSES = "192.168.110.133:5672";
    private static final int PORT = 5672;
    private static final String USERNAME = "guest";
    private static final String PASSWORD = "guest";
    private static final String VIRTUAL_HOST = "/";

    /**
     * ConnectionFactory
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setAddresses(ADDRESSES);
        cachingConnectionFactory.setPort(PORT);
        cachingConnectionFactory.setUsername(USERNAME);
        cachingConnectionFactory.setPassword(PASSWORD);
        cachingConnectionFactory.setVirtualHost(VIRTUAL_HOST);
        return cachingConnectionFactory;
    }

    /**
     * RabbitAdmin
     * 注意：该方法的参数要和上面ConnectionFactory的Bean名字一致
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);  // autoStartUp要设置为true
        return rabbitAdmin;
    }

    /**
     * RabbitTemplate
     * 注意：该方法的参数要和上面ConnectionFactory的Bean名字一致
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    /**
     * SimpleMessageListenerContainer
     * 配置好了之后启动SpringBoot主函数即可消费Message
     */
//    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);

        // setQueues(Queue... queues) 设置队列的监听！
        container.setQueues(queue01());

        // 设置消费者数量
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(5);

        // 设置是否重回队列
        container.setDefaultRequeueRejected(false);

        // 设置消息的签收模式
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);

        // 生成消费端的标签策略
        container.setConsumerTagStrategy(new ConsumerTagStrategy() {
            @Override
            public String createConsumerTag(String queue) {
                return queue + "_" + UUID.randomUUID().toString().substring(0, 6);
            }
        });

        // 1、适配器方式监听Queue：默认有自己方法的名字：handleMessage
        MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
        container.setMessageListener(adapter);


        return container;
    }

    /**
     * 1、设置Exchange的类型
     * DirectExchange
     * TopicExchange
     * FanoutExchange
     * 2、将Queue绑定到Exchange
     */
    @Bean
    public TopicExchange topicExchange01() {
        // TopicExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments)
        return new TopicExchange("topic01", true, false, null);
    }

    @Bean
    public Queue queue01() {
        // Queue(String name, boolean durable, boolean exclusive, boolean autoDelete)
        return new Queue("queue01", true, false, false);
    }

    @Bean
    public Binding binding01() {
        return BindingBuilder.bind(queue01()).to(topicExchange01()).with("spring.*");
    }
}
