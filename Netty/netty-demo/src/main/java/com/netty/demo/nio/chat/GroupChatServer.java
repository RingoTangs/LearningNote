package com.netty.demo.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * NIO 群聊案例
 * <p>
 * 服务端
 *
 * @author Ringo
 * @date 2021/11/17 21:12
 */
public class GroupChatServer {

    private static final int PORT = 8080;

    private Selector selector;

    private ServerSocketChannel ssc;

    /**
     * 服务端监听
     */
    public void listen() {
        for (; ; ) {
            try {
                doListen();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * 服务端监听
     */
    private void doListen() throws IOException {
        int cnt = selector.select(2000);
        if (cnt > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 监听到连接事件
                if (key.isAcceptable()) {
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    // SocketChannel 注册到 Selector, 关注 OP_READ 读事件
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    // 提示信息
                    System.out.println("客户端" + socketChannel.getRemoteAddress() + " 上线");
                }

                // 监听到读事件
                if (key.isReadable()) {
                    // 读取消息
                    readData(key);
                }
                // 当前的 key 从集合中删除, 防止重复处理
                iterator.remove();
            }
        } else {
            System.out.println("waiting...");
        }
    }

    /**
     * 读取客户端消息
     */
    private void readData(SelectionKey key) {
        // 获取关联的 Channel
        SocketChannel socketChannel = (SocketChannel) key.channel();
        // 创建 Buffer
        ByteBuffer bf = ByteBuffer.allocate(1024);
        // 从 Channel 中读消息到 Buffer
        try {
            int cnt = socketChannel.read(bf);
            if (cnt > 0) {
                // cnt > 0 表示服务端读到了数据
                String message = new String(bf.array());
                System.out.println(message);
                // 向其他客户端转发消息(去掉自己)
                sendMessage2OtherClients(message, socketChannel);
            }
        } catch (IOException e) {
            try {
                System.out.println(socketChannel.getRemoteAddress() + "离线...");
                // 取消注册
                key.cancel();
                // 关闭 SocketChannel
                socketChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    /**
     * 向其他客户端转发消息(去掉自己)
     *
     * @param message 信息
     * @param self    需要排除的 {@link SocketChannel}
     */
    private void sendMessage2OtherClients(String message, SocketChannel self) {
        System.out.println("服务器转发消息...");
        // 过滤掉客户端自己的 SocketChannel
        Set<SelectionKey> notifies = selector.selectedKeys().stream()
                .filter(key -> key.channel() instanceof SocketChannel && key.channel() != self)
                .collect(Collectors.toSet());
        // 通知其他客户端
        for (SelectionKey key : notifies) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            // 将 message 存储到 Buffer
            ByteBuffer bf = ByteBuffer.wrap(message.getBytes());
            try {
                socketChannel.write(bf);
            } catch (IOException e) {
                System.out.println("通知其他客户端失败");
            }
        }
    }

    /**
     * 创建 {@link GroupChatServer} 实例并初始化
     */
    public static GroupChatServer create() {
        GroupChatServer server = new GroupChatServer();
        try {
            // 初始化 Selector
            server.selector = Selector.open();
            // 初始化 ServerSocketChannel
            server.ssc = ServerSocketChannel.open();
            // 绑定端口
            server.ssc.socket().bind(new InetSocketAddress(PORT));
            // 设置 ServerSocketChannel 为非阻塞模式
            server.ssc.configureBlocking(false);
            // 将 ServerSocketChannel 注册到 Selector, 关注 OP_ACCEPT 连接事件
            server.ssc.register(server.selector, SelectionKey.OP_ACCEPT);
            System.out.println("Group Chat Server start port is " + server.ssc.socket().getLocalPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return server;
    }
}
