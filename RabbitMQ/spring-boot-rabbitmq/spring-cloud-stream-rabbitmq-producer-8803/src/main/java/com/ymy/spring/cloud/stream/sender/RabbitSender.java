package com.ymy.spring.cloud.stream.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@EnableBinding(Source.class) // 一定要启动绑定
@Component
public class RabbitSender {

    @Autowired
    private MessageChannel output;

    /**
     * 发送消息
     */
    public void send(Object message) {
        output.send(MessageBuilder.withPayload(message).build());
    }
}
