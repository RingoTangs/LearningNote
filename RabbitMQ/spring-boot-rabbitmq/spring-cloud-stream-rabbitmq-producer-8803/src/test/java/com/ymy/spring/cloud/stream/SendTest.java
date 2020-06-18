package com.ymy.spring.cloud.stream;

import com.ymy.spring.cloud.stream.sender.RabbitSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendTest {
    @Resource
    private RabbitSender rabbitSender;

    @Test
    public void send() {
        rabbitSender.send("hello rabbitmq!");
    }
}
