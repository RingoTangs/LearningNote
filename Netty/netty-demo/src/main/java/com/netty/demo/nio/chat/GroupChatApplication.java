package com.netty.demo.nio.chat;

/**
 * 群聊应用
 *
 * @author Ringo
 * @date 2021/11/17 22:14
 */
public class GroupChatApplication {
    public static void main(String[] args) {
        GroupChatServer.create().listen();
    }
}
