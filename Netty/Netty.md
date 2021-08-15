# 一、I/O模型

## 1. I/O模型基本说明

I/O模型的简单理解：用什么样的通道进行数据的发送和接收，很大程度上决定了程序之间通信的性能。

Java 共支持3中网络编程模型：BIO、NIO、AIO。

**Java BIO**：同步并阻塞模型（传统的IO）。服务器实现模式为一个连接一个线程，即：`client` 有连接请求时 `server` 需要启动一个线程进行处理，如果这个连接不做任何事情就会造成不必要的网络开销。

![BIO示意图](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/Netty/BIO.33dddvxdvam0.png)



**Java NIO**：同步非阻塞模型。服务器实现模式为一个线程处理多个请求（连接），即：`client` 发送的连接请求都会注册到多路复用器上，多路复用器轮询连接有 `I/O` 请求就进行处理。

![NIO模型](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/Netty/NIO.3isbc6j6nxc0.png)



**Java AIO（NIO.2）**：异步非阻塞模型。引入了异步通道的概念。目前没有被广泛应用。



## 2. I/O模型适用场景

![IO模型适用场景](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/Netty/IO模型适用场景.zcyigfm0r2o.png)

## 3. BIO

### 3.1. BIO编程简单流程

1. 服务器端启动一个 `ServerSocket`。
2. 客户端启动 `Socket` 对服务器进行通信，默认情况下 `server` 需要对每个 `client`  建立一个线程与之通信。
3. 客户端发出请求后，先咨询服务器是否有线程响应，如果没有则会等待，或者请求被拒绝。
4. 如果客户端收到服务器的响应，客户端线程会等待请求结束后，才继续执行。

![BIO模型](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/Netty/BIO.33dddvxdvam0.png)

### 3.2. 代码示例

```java
public class BIOServer {
    public static void main(String[] args) throws IOException {
        // 1: 创建线程池
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(1);
        final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, Runtime.getRuntime().availableProcessors(),
                60L, TimeUnit.SECONDS, blockingQueue, Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        // 2: 创建服务器, 监听8080端口
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器启动, 监听 " + serverSocket.getLocalPort());

        // 监听
        while (true) {
            // 3: 等待客户端连接
            System.out.println("等待连接....");
            // 该方法会阻塞, 直到有连接。
            Socket socket = serverSocket.accept();
            System.out.println("客户端连接到服务器");
            // 4: 创建一个线程, 与之通信
            threadPool.execute(() -> handler(socket));
        }
    }

    // 和客户端通信
    private static void handler(Socket socket) {
        byte[] bytes = new byte[1024];
        int off = 0;
        // 通过 socket 获取输入流
        try (InputStream inputStream = socket.getInputStream()) {
            System.out.println("处理连接的线程名字: "
                    + Thread.currentThread().getName());
            // 循环读取client发送的数据
            while (true) {
                // 该方法会阻塞直到有数据输入
                int count = inputStream.read(bytes, off, 10);
                if (count == -1) break;
                off += count;
                System.out.println("客户端发送的数据: "
                        + new String(bytes, 0, bytes.length)
                        + " 处理线程：" +
                        Thread.currentThread().getName());
                System.out.println(Arrays.toString(bytes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

在 `cmd` 中使用 `telnet` 连接服务器。

```java
telnet 127.0.0.1 8080
```



## 4. NIO

### 4.1. NIO基本介绍

1. NIO 是同步非阻塞I/O。
2. NIO 有三大核心组件：Channel（通道）、Buffer（缓冲区）、Selector（选择器）。
3. NIO 是面向**缓冲区**，或者面向块编程的。数据读取到一个稍后处理的缓冲区，需要时可在缓冲区中前后移动，这就增加了处理过程中的灵活性，使用 `NIO` 可以提供非阻塞式的高伸缩性网络。
4. 通俗理解：NIO 是可以做到用一个线程来处理多个操作。假设有 10000 个请求过来，根据实际情况，可以分配 50 或者 100 个线程处理。不像之前的 BIO 那样，必须分配 10000 个线程。
5. Java NIO 的非阻塞模式，使一个线程从某个通道发送请求或者读取数据，但是它仅能得到目前可用的数据，如果目前没有数据可用时，就什么都不会获取，而不是保持线程阻塞，所以直到数据变的可以读取之前，该线程可以继续做其他的事情。非阻塞写也是如此，一个线程请求写入数据到某通道，不需要等待它完全写入，这个线程同时可以去做别的事情。

![NIO模型](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/Netty/NIO.3isbc6j6nxc0.png)



### 4.2. Buffer基本使用

```java
/**
 * 举例说明 Buffer 的使用
 *
 * @author Ringo
 * @date 2021/8/15 21:47
 */
public class BasicBuffer {
    public static void main(String[] args) {
        // 1: 创建一个 Buffer, 大小为 5, 即：可以存放 5 个 int。
        IntBuffer intBuffer = IntBuffer.allocate(5);

        // 2:向 buffer 中存放数据。
        intBuffer.put(new int[]{1, 2, 3, 4, 5});

        // 3: 将 buffer 转换（读写切换）
        intBuffer.flip();

        // 4: 读取 Buffer 中的数据
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
```



### 4.3. BIO和NIO的比较

1. BIO 以 `Stream` 的方式处理数据，而 NIO 以 `Buffer` 的方式处理数据，面向缓冲区的I/O比面向流的I/O效率高很多。
2. BIO 是同步阻塞的，NIO是同步非阻塞的。
3. BIO 基于字节流和字符流进行操作，而 NIO 基于缓冲区（Buffer）和通道（Channel）进行操作，数据总是从通道读取到缓冲区中，或者从缓冲区写到通道中。Selector（选择器）用于监听多个通道的事件（比如：连接请求，数据到达等），因此使用**单个线程就可以监听多个客户端通道**。



### 4.4. NIO核心概念之间关系

1. 每个 `Channel` 都会对应一个 `Buffer`。
2. 一个 `Selector` 对应一个线程，一个线程对应多个 `Channel`（通道/通俗理解：连接）。
3. 下图反应了有2个 `Channel` 注册到了 `Selector`。
4. 程序切换到哪个 `channel` 是由事件决定的，`Event` 是非常重要的事件。
5. `Selector` 会根据不同的事件，在各个通道上切换。
6. `Buffer` 是一个内存块，底层是一个数组。
7. NIO数据的读写是通过 `Buffer`，这个和 BIO 是不同的。BIO 中要么是 InputStream，要么是 OutputStream，不能是双向的。但是 NIO 的读写需要进行切换，调用 `flip()` 方法。
8. NIO的 `Channel` 是双向的，可以返回底层操作系统的情况，比如 Linux 底层操作系统通道就是双向的。

![NIO模型](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/Netty/NIO.3isbc6j6nxc0.png)

