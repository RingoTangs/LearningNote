package com.ymy.spring.rabbitmq.conf;

/**
 * 这个类是我们自定义的，但是方法名都是固定的。
 */
public class MessageDelegate {
    public void handleMessage(byte[] messageBody) {
        System.out.println("默认方法，消息内容：" + new String(messageBody));
    }
}
