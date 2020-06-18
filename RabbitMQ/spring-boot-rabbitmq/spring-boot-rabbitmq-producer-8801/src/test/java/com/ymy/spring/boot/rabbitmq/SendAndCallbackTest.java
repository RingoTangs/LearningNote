package com.ymy.spring.boot.rabbitmq;

import com.ymy.spring.boot.rabbitmq.entity.Order;
import com.ymy.spring.boot.rabbitmq.producer.RabbitSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendAndCallbackTest {

    @Resource
    private RabbitSender rabbitSender;

    @Test
    public void send() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("describe", "测试SpringBoot发送消息并测试Confirm机制！");
        String message = "Hello RabbitMQ..";
        rabbitSender.send(message, properties);
    }

    @Test
    public void sendObject() {
        rabbitSender.sendObject(new Order("1", "订单"));
    }
}
