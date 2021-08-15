# 一、I/O模型

## 1. I/O模型基本说明

I/O模型的简单理解：用什么样的通道进行数据的发送和接收，很大程度上决定了程序之间通信的性能。

Java 共支持3中网络编程模型：BIO、NIO、AIO。

**Java BIO**：同步并阻塞模型（传统的IO）。服务器实现模式为一个连接一个线程，即：`client` 有连接请求时 `server` 需要启动一个线程进行处理，如果这个连接不做任何事情就会造成不必要的网络开销。

![BIO示意图](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/Netty/BIO.33dddvxdvam0.png)



**Java NIO**：同步非阻塞模型。服务器实现模式为一个线程处理多个请求（连接），即：`client` 发送的连接请求都会注册到多路复用器上，多路复用器轮询连接有 `I/O` 请求就进行处理。

![NIO模型](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/Netty/BIO.33dddvxdvam0.png)



**Java AIO（NIO.2）**：异步非阻塞模型。引入了异步通道的概念。目前没有被广泛应用。



## 2. I/O模型适用场景

![IO模型适用场景](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/Netty/IO模型适用场景.zcyigfm0r2o.png)

## 3. BIO

### 3.1. BIO编程简单流程

1. 服务器端启动一个 `ServerSocket`。
2. 客户端启动 `Socket` 对服务器进行通信，默认情况下 `server` 需要对每个 `client`  建立一个线程与之通信。
3. 客户端发出请求后，先咨询服务器是否有线程响应，如果没有则会等待，或者请求被拒绝。
4. 如果客户端收到服务器的响应，客户端线程会等待请求结束后，才继续执行。