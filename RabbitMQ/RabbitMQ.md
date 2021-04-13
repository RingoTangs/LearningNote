# 一、消息队列基础

## 1. AMQP协议

### 1.1. 简介

**AMQP协议简介**：

- AMQP全称：`Advanced Message Queuing Protocol` 高级消息队列协议。
- AMQP定义：是具有现代特征的二进制协议。是一个提供统一消息服务的应用层标准高级消息队列协议，是**应用层协议的一个开放标准，为面向消息的中间件设计**。

<img src=".\image\2.3-1-AMQP协议模型.jpg" alt="AMQP协议模型"  />



### 1.2. 核心概念

**AMQP概念**：

- **Server**：又称作Broker，接受客户端的连接，实现AMQP实体服务。
- **Connection**：连接，应用程序与Broker的网络连接， TCP/IP 三次握手和四次挥手。
- **Channel**：网络信道，几乎所有的操作都在Channel中进行，Channel是进行消息读写的通道。客户端可以建立多个Channel，每个Channel代表一个会话任务。
- **Message**：消息。服务器和应用程序之间传送的数据，由Properties和Body组成。
  - Properties可以对消息进行修饰，比如消息的优先级、延迟等高级特性；
  - Body就是消息体内容。
- **Virtual Host**：虚拟主机，用于进行逻辑隔离，最上层的消息路由。
  - 一个Virtual Host里面可以有若干个Exchange和Queue；
  - 同一个Virtual Host里面不能有相同名称的Exchange和Queue。
- **Exchange**：交换机，接收消息。根据Routing Key转发消息到绑定的队列。
- **Binding**：Exchange和Queue之间的虚拟连接，Binding中可以包含Routing Key。
- **Routing Key**：一个路由规则，虚拟机可以用它来确定如何路由一个特点消息。
- **Queue**：也成为了Message Queue，消息队列，保存消息并转发给消费者。



### 1.3. RabbitMQ架构

![2.5-1-RabbitMQ的整体架构图](.\image\2.5-1-RabbitMQ的整体架构图.jpg)





### 1.4. MQ消息流转

![2.6-1-RabbitMQ消息流转图](.\image\2.6-1-RabbitMQ消息流转图.jpg)



## 2. RabbitMQ安装

**官网地址：https://www.rabbitmq.com/**

**Erlang和RabbitMQ版本对照表：https://www.rabbitmq.com/which-erlang.html**

**相关软件包和资料：链接：https://pan.baidu.com/s/1uFP2YU7xPK2KBkaP4gXvLw** 
**提取码：s6z8**



### 2.1. 官网安装

```shell
# 1、将需要的安装包下载到Linux系统
erlang-18.3-1.el7.centos.x86_64.rpm # Erlang语言安装包
rabbitmq-server-3.6.5-1.noarch.rpm # RabbitMQ安装包
socat-1.7.3.2-1.1.el7.x86_64.rpm # 秘钥

# 2、安装Erlang环境
rpm -ivh erlang-18.3-1.el7.centos.x86_64.rpm

# 3、安装socat
rpm -ivh socat-1.7.3.2-1.1.el7.x86_64.rpm

# 4、安装RabbitMQ
rpm -ivh rabbitmq-server-3.6.5-1.noarch.rpm

# 5、RabbitMQ配置文件的修改
路径： vim /usr/lib/rabbitmq/lib/rabbitmq_server-3.6.5/ebin/rabbit.app  # rabbit.app是json格式的配置文件

42          {loopback_users, [guest]}, # 修改42行 去掉guest两边的引号和尖括号，最后结果和左边代码保持一致
```



### 2.2. docker安装

```shell
# rabbmitmq.conf
loopback_users.guest = false
listeners.tcp.default = 5672
management.tcp.port = 15672
```



```shell
docker run -d \
--name rabbimtmq -p 5672:5672 \
-p 15672:15672 \
-v /root/rabbitmq/conf/rabbitmq.conf:/etc/rabbitmq/rabbitmq.conf \
rabbitmq:3-management
```

**浏览器访问15672端口就可以进入到控制台了**。

### 2.3. 启动和停止

```shell
# Rabbitmq服务的启动和停止

# 1、查看Rabbitmq可以使用的命令 
[root@centos-7-test1 ~]# rabbitmq
rabbitmqctl       rabbitmq-plugins  rabbitmq-server

# 2、启动Rabbitmq服务
rabbitmq-server start &  # & 代表后台启动服务

# 启动RabbitMQ服务
[root@centos-7-test1 ~]# rabbitmq-server start &
[1] 3293
[root@centos-7-test1 ~]# 
              RabbitMQ 3.6.5. Copyright (C) 2007-2016 Pivotal Software, Inc.
  ##  ##      Licensed under the MPL.  See http://www.rabbitmq.com/
  ##  ##
  ##########  Logs: /var/log/rabbitmq/rabbit@centos-7-test1.log
  ######  ##        /var/log/rabbitmq/rabbit@centos-7-test1-sasl.log
  ##########
              Starting broker...
 completed with 0 plugins.

# 3、查看Rabbitmq是否启动成功？ 看到下面的结果就证明RabbitMQ启动成功了！  
[root@centos-7-test1 ~]# lsof -i:5672
COMMAND   PID     USER   FD   TYPE DEVICE SIZE/OFF NODE NAME
beam.smp 2586 rabbitmq   52u  IPv6  36396      0t0  TCP *:amqp (LISTEN)

# 4、Rabbitmq服务的停止
rabbitmqctl stop_app

# 5、安装Rabbitmq管理插件，可以通过浏览器控制台访问
rabbitmq-plugins list # 可以查看Rabbitmq提供的插件

rabbitmq-plugins enable rabbitmq_management # 安装Rabbitmq控制台插件 控制台默认端口是15672

# 6、浏览器访问Rabbitmq控制台 默认用户名和密码都是guest
http://192.168.110.133:15672
```





## 3. 命令行操作

### 3.1. 基础操作

```shell
# 1、启动应用
rabbitmqctl start_app

# 2、关闭应用
rabbitmqctl stop_app

# 3、节点状态
rabbitmqctl status

# 4、添加用户
rabbitmqctl add_user [username] [password]

# 4、列出所有的用户列表
rabbitmqctl list_users

# 5、删除用户
rabbitmqctl delete_user [username]

# 6、清除用户权限
rabbitmqctl clear_permissions -p [vhostpath] [username]

# 7、列出用户权限
rabbitmqctl list_user_permissions [username]

# 8、修改密码
rabbitmqctl change_password [username] [newpassword]

# 9、设置用户权限
rabbitmqctl set_permissions -p [vhostpath] [username] ".*" ".*" ".*"

# 设置用户角色
rabbitmqctl set_user_tags [username] none/management/policymaker/monitoring  /administrator

# 10、创建虚拟主机
rabbitmqctl add_vhost [vhostpath]

# 11、列出所有的虚拟主机
rabbitmqctl list_vhosts

# 12、列出虚拟主机上所有权限
rabbitmqctl list_permissions -p [vhostpath]

# 13、删除虚拟主机
rabbitmqctl delete_vhost [vhostpath]

# 14、查看所有队列消息
rabbitmqctl list_queues

# 15、清除队列里的消息
rabbitmqctl -p [vhostpath] purge_queue blue
```



### 3.2. 高级操作

```shell
# 1、移除所有数据，要在 rabbitmqctl stop_app 之后使用
rabbitmqctl reset

# 2、组成集群命令
rabbitmqctl join_cluster <clusternode> [--ram]

# 3、查看集群状态
rabbitmqctl cluster_status

# 4、修改集群节点的存储形式
rabbitmqctl change_cluster_node_type disc | ram

# 5、摘除节点
rabbitmqctl forget_cluster_node [--offline]

# 6、修改节点名称
rabbitmqctl rename_cluster_node [oldnode1] [newnode1]...
```



## 4. RabbitMQ角色

1、`none`：不能访问 management plugin，无法登录web界面。

2、`management`：查看自己相关结点信息。用户可以通过 AMQP 做的任何事外加：

- 列出自己可以通过 AMQP 登入的 virtual hosts；

- 查看自己的 virtual hosts 中的 queues, exchanges 和 bindings；
- 查看和关闭自己的 channels 和 connections；
- 查看有关自己的 virtual hosts 的“全局”的统计信息，包含其他用户在这些 virtual hosts 中的活动。

3、`policymaker`：management 可以做的任何事外加：

- 查看、创建和删除自己的 virtual hosts 所属的 policies 和 parameters。

4、`monitoring `：management 可以做的任何事外加：

- 列出所有 virtual hosts，包括他们不能登录的 virtual hosts；
-  查看其他用户的 connections 和 channels；
-  查看节点级别的数据如 clustering 和 memory 使用情况；
-  查看真正的关于所有 virtual hosts 的全局的统计信息。

5、`administrator `：policymaker 和 monitoring 可以做的任何事外加:

- 创建和删除 virtual hosts；
- 查看、创建和删除 users；
- 查看创建和删除 permissions；
- 关闭其他用户的 connections；

- **创建用户并设置角色：**可以创建管理员用户，负责整个 MQ 的运维。



## 5. 入门案例

```xml
<!-- Java原生依赖 -->
<dependency>
	<groupId>com.rabbitmq</groupId>
	<artifactId>amqp-client</artifactId>
    <version>3.6.5</version>
</dependency>
```



### 5.1. simple模式

**simple简单模式**

<img src="https://www.rabbitmq.com/img/tutorials/python-one.png" alt="simple模式"  />



```java
/**
 * 工具类：获取RabbitMQ连接
 *
 * @author Ringo
 * @since 2021/4/9 22:54
 */
public class RabbitUtils {

    /**
     * 创建 Connection
     *
     * @author Ringo
     * @date 2021/4/9
     */
    public static Connection newConnection(String connectionName) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(xxxxx);
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("Ringo");
        connectionFactory.setPassword("123");
        connectionFactory.setVirtualHost("/");
        return connectionFactory.newConnection(connectionName);
    }
}
```



> 消费者

**（1）方法参数**

```java
/**
 * 声明队列
 *
 * Declare a queue
 * @param queue the name of the queue 队列的名字。
 * @param durable true if we are declaring a durable queue (the queue will survive a server restart) 队列是否持久化，非持久化队列MQ重启后该队列会被删除。
 * @param exclusive true if we are declaring an exclusive queue (restricted to this connection) 队列是否排他（独占），独占的意思就是这个Queue只能有我这一个Channel监听。
 * @param autoDelete true if we are declaring an autodelete queue (server will delete it when no longer in use) 队列是否自动删除，true队列没人用的时候会自动删除。
 * @param arguments other properties (construction arguments) for the queue 扩展参数。
 * @return a declaration-confirm method to indicate the queue was successfully declared
 */
Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,
                             Map<String, Object> arguments) throws IOException;

/**
* 创建一个非本地，非独占的消费者，并且有服务器创建的 "消费者标签"。
* Start a non-nolocal, non-exclusive consumer, with
* a server-generated consumerTag.
* @param queue the name of the queue 队列的名字。
* @param autoAck true if the server should consider messages
* acknowledged once delivered; false if the server should expect
* explicit acknowledgements  ture 队列就会自动签收
* @param callback an interface to the consumer object 回调消费者对象的接口
* @return the consumerTag generated by the server 返回服务器创建的 "消费者标签" 
*/
String basicConsume(String queue, boolean autoAck, Consumer callback);
```


**（2）消费者代码** 

```java
/**
 * 消费者
 *
 * @author Ringo
 * @since 2021/4/9 22:13
 */
public class Consumer {

    public static final String QUEUE_NAME = "queue_simple";

    public static void main(String[] args) throws Exception {
        Connection connection = null;
        Channel channel = null;

        // 1: 创建连接工厂

        // 2: 创建连接 Connection
        connection = RabbitUtils.newConnection("test_simple_consumer");


        // 3: 通过 Connection 获取通道 Channel
        channel = connection.createChannel();

        // 4: 通过Channel创建 Exchange、Queen、Bindings、Routing Key、接收消息
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 5: 创建消费者，在RabbitMQ中不需要定义QueueingProducer
        // 只需要定义QueueingConsumer即可
        QueueingConsumer consumer = new QueueingConsumer(channel);
        String consumerTag = channel.basicConsume(QUEUE_NAME, true, consumer);
        System.out.println("consumerTag: " + consumerTag);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            System.out.println("接收到的消息: " + new String(delivery.getBody()));
        }
    }
}
```





> 生产者

**（1）方法参数**

```java
/**
     * Publish a message.
     *
     * Publishing to a non-existent exchange will result in a channel-level
     * protocol exception, which closes the channel.
     * 发布到不存在的exchange将导致通道级协议异常，从而关闭通道。
     *
     * @param exchange the exchange to publish the message to 交换机名字
     * @param routingKey the routing key 路由key的名字
     * @param props other properties for the message - routing headers etc 其他属性
     * @param body the message body 消息
     */
void basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body);
```



**（2）生产者代码**

```java
/**
 * 生产者
 *
 * @author Ringo
 * @since 2021/4/9 22:13
 */
public class Producer {

    public static final String QUEUE_NAME = "queue_simple";

    public static void main(String[] args) throws Exception {

        Connection connection = null;
        Channel channel = null;

        // 1: 创建连接工厂

        // 2: 创建连接 Connection
        connection = RabbitUtils.newConnection("test_simple_producer");

        // 3: 通过 Connection 获取通道 Channel
        channel = connection.createChannel();

        // 4: 通过Channel创建 Exchange、Queen、Bindings、Routing Key、发送消息、接收消息
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 5: 准备消息内容
        String message = "Hello World！";

        // 6: 发送消息给队列
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        System.out.println("消息发送成功！");

        // 7: 关闭通道
        channel.close();

        // 8：关闭连接
        connection.close();
    }
}
```



### 5.2. 问: 不绑定交换机？

> 问题：生产者发送消息，没有指定交换机但是routingKey为"test001"就可以路由到"test001"队列，为什么？
>
> 回答：RabbitMQ生产者投递消息如果**不指定Exchange**，那么就会默认使用**AMQP.default**这个Exchange，它的路由规则就是根据生产者指定的RoutingKey和Queue的名字去对比，如果名字一致就将生产者的消息发送给该队列。

![image-20210410192213311](E:\Typora\image\image-20210410192213311.png)



## 6. Exchange交换机

```java
/**
     * Declare an exchange, via an interface that allows the complete set of
     * arguments.
     * @see com.rabbitmq.client.AMQP.Exchange.Declare
     * @see com.rabbitmq.client.AMQP.Exchange.DeclareOk
     * @param exchange the name of the exchange
     * @param type the exchange type
     * @param durable true if we are declaring a durable exchange (the exchange will survive a server restart)
     * @param autoDelete true if the server should delete the exchange when it is no longer in use
     * @param internal true if the exchange is internal, i.e. can't be directly
     * published to by a client.
     * @param arguments other properties (construction arguments) for the exchange
     * @return a declaration-confirm method to indicate the exchange was successfully declared
     * @throws java.io.IOException if an error is encountered
     */
Exchange.DeclareOk exchangeDeclare(String exchange,
                                   String type,
                                   boolean durable,
                                   boolean autoDelete,
                                   boolean internal,
                                   Map<String, Object> arguments) throws IOException;
```

**Exchange属性**：

- **Name**：Exchange名称。
- **Type**：Exchange的类型。`direct、topic、fanout、headers`。
- **Durability**：是否需要持久化，true为持久化。false代表重启服务器后该交换机会被删除。
- **Auto Delete**：当最后一个绑定到Exchange上的队列删除后，自动删除该Exchange。
- **Internal**：当前Exchange是否用于RabbitMQ内部使用，默认为false。**(很少使用)**
- **Arguments**：扩展参数，用于扩展AMQP协议自制定化使用。



### 6.2. Direct Exchange

**（1）基本概念**

- **所有发送到Direct Exchange的消息被转发到Routing key中指定的Queue。**
- **一句话：直连的方式，生产者发送消息的Routing Key和Direct Exchange的Routing Key必须完全匹配，才会路由到绑定的Queue。**

![direct](https://www.rabbitmq.com/img/tutorials/python-four.png)



**（2）消费者代码**

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 消费者 接收和Direct Exchange绑定的Queue的消息
 * 消费者先启动：声明Exchange、Queue和绑定关系
 */
public class ConsumerDirectExchange {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_direct_exchange";
    public static final String EXCHANGE_TYPE = "direct";
    public static final String ROUTING_KEY = "test.direct";
    public static final String QUEUE_NAME = "test_direct_queue";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、声明Exchange
         * exchangeDeclare(String exchange,String type,boolean durable,boolean autoDelete,boolean internal,Map<String, Object> arguments)
         * exchange：交换机名字。
         * type：交换机类型。
         * durable：是否持久化。
         * autoDelete：true表示当没有队列绑定到Exchange上时，自动删除该Exchange。
         * internal：当前Exchange是否用于RabbitMQ内部使用，一般是false。
         * arguments：其他参数。
         */
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true, false, false, null);

        /**
         * 5、声明队列
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        /**
         * 6、Exchange和Queue的绑定关系
         * queueBind(String queue, String exchange, String routingKey, Map<String, Object> arguments)
         */
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY, null);

        // 7、创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        /**
         * 8、channel的设置
         * basicConsume(String queue, boolean autoAck, Consumer callback)
         */
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);

        while (true) {
            //9、接收消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("*******消费端接收到的消息为=====>" + msg);
        }
    }
}
```



**（3）生产者代码**

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 生产者 向Direct Exchange发送消息
 */
public class ProducerDirectExchange {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_direct_exchange";
    public static final String ROUTING_KEY = "test.direct";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        // 4、发送消息
        String msg = "Hello RabbitMQ Direct Exchange Message...";
        // basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.getBytes());

        // 5、关闭资源
        channel.close();
        connection.close();

        System.out.println("***ProducerDirectExchange 消息发送成功***");
    }
}
```

### 6.3. Topic Exchange

**（1）基本概念**

- 所有发送到Topic Exchange的消息被转发到所有关心Routing Key中指定Topic的Queue上。
- Exchange将Routing Key和某个Topic进行模糊匹配，此时队列需要绑定一个Topic。
- **一句话：Topic Exchange和Queue绑定Routing Key可以使用通配符，生产者发送消息的Routing Key只要和Topic Exchange的Routing Key匹配就能路由到Topic Exchange绑定的队列。**

![topic](https://www.rabbitmq.com/img/tutorials/python-five.png)

> 模糊匹配可以使用通配符
>
> - **符号 "#" 匹配0个或多个词。**
> - **符号 "*" 匹配一个词。**
> - **例如："log.#" 能够匹配到 "log.info.aa"。"log.*" 只能匹配到 "log.err"。**



**（2）消费者代码**

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 消费者 接收Topic Exchange绑定的Queue的消息
 * 消费者先启动：声明Exchange、Queue和绑定关系
 */
public class ConsumerTopicExchange {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_topic_exchange";
    public static final String EXCHANGE_TYPE = "topic";
    public static final String ROUTING_KEY = "user.#";
    public static final String QUEUE_NAME = "test_topic_queue";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         *  4、声明Exchange
         *  exchangeDeclare(String exchange,String type,boolean durable,boolean autoDelete,boolean internal,Map<String, Object> arguments)
         */
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true, false, false, null);

        /**
         * 5、声明队列
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,Map<String, Object> arguments)
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        /**
         * 6、Exchange和Queue的绑定关系
         * queueBind(String queue, String exchange, String routingKey, Map<String, Object> arguments)
         */
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY, null);

        // 7、创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        /**
         * 8、channel的设置
         * basicConsume(String queue, boolean autoAck, Consumer callback)
         */
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);

        while (true) {
            // 9、接收消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("*******消费端接收到的消息为=====>" + msg);
        }
    }
}
```



**（3）生产者代码**

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 生产者向 Topic Exchange发送消息
 */
public class ProducerTopicExchange {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_topic_exchange";
    public static final String ROUTING_KEY = "user.name.zs";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、发送消息
         * basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
         */
        String msg = "Hello Topic Exchange Message...";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.getBytes());

        // 5、关闭资源
        channel.close();
        connection.close();

        System.out.println("***Producer Topic Exchange 消息发送成功***");
    }
}
```

### 6.4. Fanout Exchange

**（1）基本概念**：

- Fanout Exchange不处理Routing Key，只需要简单的将Queue绑定到Exchange上。
- 发送到Exchange的消息都会被转发到与该Exchange绑定的所有Queue上。
- Fanout Exchange转发消息是最快的。

**（2）发布订阅模式**

![发布订阅模式](https://www.rabbitmq.com/img/tutorials/python-three.png)

**（3）消费者代码**

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 消费者 接收Fanout Exchange 绑定的Queue的消息
 * 消费者先启动：声明Exchange、Queue和绑定关系
 * 注意：Fanout Exchange的Routing Key不能设置为null
 */
public class ConsumerFanoutExchange {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_fanout_exchange";
    public static final String EXCHANGE_TYPE = "fanout";
    public static final String ROUTING_KEY = "";
    public static final String QUEUE_NAME = "test_fanout_queue";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、声明Exchange
         * exchangeDeclare(String exchange,String type,boolean durable,boolean autoDelete,boolean internal,Map<String, Object> arguments)
         */
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true, false, false, null);

        /**
         * 5、声明队列
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,Map<String, Object> arguments)
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        /**
         * 6、Exchange和Queue的绑定关系
         * queueBind(String queue, String exchange, String routingKey, Map<String, Object> arguments)
         */
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY, null);

        // 7、创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        /**
         * 8、channel的设置
         * basicConsume(String queue, boolean autoAck, Consumer callback)
         */
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);

        while (true) {
            // 9、接收消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("*******消费端接收到的消息为=====>" + msg);
        }
    }
}
```



**（4）生产者代码**

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 生产者 向Fanout Exchange发送消息 根本就不走Routing Key的！
 * Routing Key不能为null，随便写一个字符串即可。
 */
public class ProducerFanoutExchange {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_fanout_exchange";
    public static final String ROUTING_KEY = "TangShi";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、发送消息
         * basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
         */
        String msg = "Hello Fanout Exchange Message...";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.getBytes());

        // 5、关闭资源
        channel.close();
        connection.close();

        System.out.println("***Producer Fanout Exchange 消息发送成功***");
    }
}
```



### 6.5. Headers Exchange

**1: 不处理路由键**。而是根据发送的消息内容中的 `headers`属性进行匹配。

- 在绑定Queue与Exchange时指定一组键值对；
- 当消息发送到RabbitMQ时会取到该消息的headers与Exchange绑定时指定的键值对进行匹配；
- 如果完全匹配则消息会路由到该队列，否则不会路由到该队列。
- headers属性是一个键值对，可以是Hashtable，键值对的值可以是任何类型。而fanout，direct，topic 的路由键都需要要字符串形式的。

**2: 匹配规则x-match有下列两种类型**：

- x-match = all ：表示所有的键值对都匹配才能接受到消息；
- x-match = any ：表示只要有键值对匹配就能接受到消息。

![headers](https://imgconvert.csdnimg.cn/aHR0cDovL2ltZy5ibG9nLmNzZG4ubmV0LzIwMTcxMTI2MTkwNzM5NjM0)



## 7. work工作模式

![work模式](https://www.rabbitmq.com/img/tutorials/python-two.png)

### 7.1. 轮询模式

- **特点**：当有多个消费者监听同一队列时，消息的分配模式是一个消费者分配一条，直至消息消费完成！
- **RabbitMQ默认是轮询模式**。

```java
Connection connection = null;
Channel channel = null;

// 1: 创建连接工厂

// 2: 创建连接 Connection
connection = RabbitUtils.newConnection("test_work_consumer");

// 3: 通过 Connection 获取通道 Channel
channel = connection.createChannel();

// 4: 创建消费者
QueueingConsumer consumer = new QueueingConsumer(channel);
String consumerTag = channel.basicConsume(queueName, true, consumer);
System.out.println("consumerTag: " + consumerTag);

while (true) {
    QueueingConsumer.Delivery delivery = consumer.nextDelivery();
    System.out.println(new java.lang.String(delivery.getBody()));
}
```





### 7.2. 公平分配(能者多劳)

**公平分配模式要注意**：

- `Qos`默认为0，需要自己设置；
- 自己设置手动ACK。
- `basicConsume(autoAck)`需要设置为 false ，代表手动签收。

**（1）自定义消费者**

```java
/**
 * 自定义消费者 ACK - 手动签收
 *
 * @author Ringo
 * @since 2021/4/10 23:16
 */
public class ManualAckQueueingConsumer extends DefaultConsumer {

    private Channel channel;

    private long time;

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public ManualAckQueueingConsumer(Channel channel, long time) {
        super(channel);
        this.channel = channel;
        this.time = time;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("收到的消息: " + new String(body));
        
        // 设置超时时间是为了模拟不同的消费者有不同的消费能力
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 手动签收
        channel.basicAck(envelope.getDeliveryTag(), false);
    }
}
```



**（2）消费者代码**

```java
public class ConsumeUtils {

    public static void consume(String queueName, long time) throws Exception {
        Connection connection = null;
        Channel channel = null;

        // 1: 创建连接工厂

        // 2: 创建连接 Connection
        connection = RabbitUtils.newConnection("test_work_consumer");

        // 3: 通过 Connection 获取通道 Channel
        channel = connection.createChannel();

        // 服务器将传递的最大邮件数，如果没有限制，则为 0
        // 手动ACK，公平分配模式（能者多劳模式）
        // Qos 设置为 0, 无论是自动ACK还是手动ACK, 都是轮询分发
        channel.basicQos(1);

        // 4: 创建消费者
        ManualAckQueueingConsumer consumer = new ManualAckQueueingConsumer(channel, time);
        String consumerTag = channel.basicConsume(queueName, false, consumer);
        System.out.println(consumerTag);
    }
}
```



**（3）设置两个worker**

```java
// worker1 消费能力强
public class Worker1 {
    public static void main(String[] args) throws Exception {
        ConsumeUtils.consume("work.queue", 500);
    }
}

// worker2 消费能力弱
public class worker2 {
    public static void main(String[] args) throws Exception {
        ConsumeUtils.consume("work.queue", 1500);
    }
}
```



**（3）运行结果**

```java
// work1
amq.ctag-X0uMrkGKwuh1XKCACcjysg
收到的消息: Hello worker0
收到的消息: Hello worker2
收到的消息: Hello worker3
收到的消息: Hello worker4
收到的消息: Hello worker6
收到的消息: Hello worker7
收到的消息: Hello worker8

// work2
amq.ctag-2iPHF0Zhumvz3K3oXljrdg
收到的消息: Hello worker1
收到的消息: Hello worker5
收到的消息: Hello worker9
```



## 8. 整合spring-boot

### 8.1. 公共配置和实体类

```yaml
# application.yaml
# 配置：连接到RabbitMQ服务器，发送端和接收端都需要该配置
spring:
  rabbitmq:
    host: xxxxxx
    port: 5672
    username: Ringo
    password: 123
    virtual-host: /
```

```java
// 实体类
// 需要实现Serializable接口
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Order implements Serializable {

    // 流水号
    private String id;

    // 用户
    private String username;

    // 商品id
    private String productId;

    // 购买数量
    private Integer count;

    // 下单时间
    private LocalDateTime time;
}
```



### 8.2. 消费端

> RabbitMQ的配置类中需要定义：
>
> - 交换机
> - 队列
> - 绑定关系
> - 消息的序列化和反序列化！

![image-20210411120645114](E:\Typora\image\image-20210411120645114.png)

```java
// 配置类
@Configuration
public class RabbitMQConfig {

    /**
     * 1: 注册 fanout 模式的交换机
     * FanoutExchange的接口是 {@link Exchange} 接口，顶级接口是 {@link Declarable}。
     * Exchange 接口的实现类中有各种类型的交换机。
     *
     * @author Ringo
     * @date 2021/4/11
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout_order_exchange", true, false, null);
    }

    /**
     * 2: 声明队列
     * smsQueue()：供短信服务消费
     * emailQueue()：供邮件服务消费
     *
     * @author Ringo
     * @date 2021/4/11
     */
    @Bean
    public Queue smsQueue() {
        return new Queue("sms.fanout.queue");
    }

    @Bean
    public Queue emailQueue() {
        return new Queue("email.fanout.queue");
    }


    /**
     * 3: 完成 交换机-队列 绑定关系
     * 在 {@link Binding} 中, 有提示可以配合 {@link BindingBuilder} 构造 Binding！
     *
     * @author Ringo
     * @date 2021/4/11
     */
    @Bean
    public Binding fanoutSmsBinding() {
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutEmailBinding() {
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }

    /**
     * 消息序列化 - 
     * 实体类中有 LocalDateTime 则需要对Jackson2JsonMessageConverter增强
     * 
     * @author Ringo
     * @date 2021/4/11
     */
    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(om);
    }
}
```



> @RabbitListener 和 @RabbitHandler 搭配使用：
>
> - @RabbitListener 可以标注在类上面，需配合 @RabbitHandler 注解一起使用；
> - @RabbitListener 标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法处理，具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型。
>
> 使用 @Payload 和 @Headers 注解可以消息中的 body 与 headers 信息。

```java
@Service  // 注意：这里一定要加入到 spring 容器中！
@RabbitListener(queues = {"email.fanout.queue"})
public class EmailConsumer {

    @RabbitHandler
    public void receive(@Payload Order order, @Headers Map<String, Object> headers) {
        System.out.println("短信服务接收订单....");
        System.out.println("body: " + order);
        System.out.println("headers: " + headers);
    }
}
```

```java
// 输出信息
短信服务接收订单....
    
body: Order(id=9df15e82-194f-4df3-997a-41ff0ceeb4c9, username=Ringo, productId=10, count=100, time=2021-04-11T13:38:32.376)
    
headers: {amqp_receivedDeliveryMode=PERSISTENT, amqp_contentEncoding=UTF-8, amqp_receivedExchange=fanout_order_exchange, amqp_deliveryTag=1, amqp_consumerQueue=sms.fanout.queue, amqp_redelivered=false, id=1a1423c9-bad5-43a3-89ac-a016defc1024, amqp_consumerTag=amq.ctag-OsLXAbsZzFvx3V87VKsN2g, amqp_lastInBatch=false, contentType=application/json, __TypeId__=com.ymy.boot.entity.Order, timestamp=1618119512665}
```



### 8.3. 生产端

```java
// rabbitmq配置
@Configuration
public class RabbitMQConfig {

    /**
     * 消息序列化
     *
     * @author Ringo
     * @date 2021/4/11
     */
    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(om);
    }
}
```



> **RabbitTemplate** 在自动配置中已经加入到 Spring 容器了，可以直接使用。
>
> 在发送端，将消息发送给 exchange即可！

```java
@Service
public class OrderService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 模拟用户下单
     *
     * @author Ringo
     * @date 2021/4/11
     */
    public Order makeOrder(String username, String productId, int count) {

        // 1: 创建订单
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId, username, productId, count, LocalDateTime.now());

        // 2: 发送消息
        String exchange = "fanout_order_exchange";
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchange, routingKey, order);

        return order;
    }
}
```







## 9. Message(消息)

> Message基本概念

- Message是服务器和应用程序之前传送的数据。
- **Message本质上就是一段数据，由Properties和Payload(Body)组成。**

> Message的属性

```java
private String contentType;	
private String contentEncoding;
private Map<String,Object> headers;	// 自定义属性
private Integer deliveryMode;	// 消息的送达模式
private Integer priority;	// 消息的优先级
private String correlationId;	// 消息的唯一ID
private String replyTo;	// 消息失败返回的队列
private String expiration;	// 消息的过期时间
private String messageId;	// 消息的ID
private Date timestamp;	// 时间戳
private String type; 
private String userId;
private String appId;
private String clusterId;
```

> 消费者接收消息Properties

```java
import com.rabbitmq.client.*;

/**
 * 消费者 测试获得消息的Properties
 */
public class MessageConsumer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_message_exchange";
    public static final String EXCHANGE_TYPE = "direct";
    public static final String ROUTING_KEY = "test.message";
    public static final String QUEUE_NAME = "test_message_queue";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、声明Exchange
         * exchangeDeclare(String exchange,String type,boolean durable,boolean autoDelete,boolean internal,Map<String, Object> arguments)
         */
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true, false, false, null);

        /**
         * 5、声明队列
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        /**
         * 6、Exchange和Queue的绑定关系
         * queueBind(String queue, String exchange, String routingKey, Map<String, Object> arguments)
         */
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY, null);

        // 7、创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        /**
         * 8、channel的设置
         * basicConsume(String queue, boolean autoAck, Consumer callback)
         */
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);

        while (true) {
            //9、接收消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            //接收消息体
            String msg = new String(delivery.getBody());
            //接收消息的Properties
            AMQP.BasicProperties properties = delivery.getProperties();
            System.out.println("*******消费端接收到的消息为=====>" + msg);
            System.out.println("*****自定义属性1*******" + properties.getHeaders().get("my1"));
            System.out.println("*****自定义属性2*******" + properties.getHeaders().get("my2"));
            System.out.println("*****DeliveryMode*********" + properties.getDeliveryMode());
            System.out.println("*****Expiration********" + properties.getExpiration());
            System.out.println("*****ContentEncoding********" + properties.getContentEncoding());
        }
    }
}
```

> 生产者设置消息Properties

```java
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 生产者 测试给消息添加Properties
 */
public class MessageProducer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_message_exchange";
    public static final String ROUTING_KEY = "test.message";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、发送消息
         * basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
         */
        // 设置消息的Properties
        Map<String, Object> headers = new HashMap<>();
        headers.put("my1", "111");
        headers.put("my2", "222");
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)    //消息的投递模式 2代表持久化消息 1代表非持久化消息
                .contentEncoding("UTF-8")   //字符集
                .expiration("20000")    //如果20S没有被消费者消费 就会被自动清除
                .headers(headers)  // 自定义属性
                .build();
        String msg = "Hello Message Properties Test...";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, properties, msg.getBytes());

        // 5、关闭资源
        channel.close();
        connection.close();

        System.out.println("***Set Message Properties 消息发送成功***");
    }
}
```





# 二、消息队列高级特性
## 1. TTL

### 1.1. TTL介绍

- TTL：`Time To Live`，也就是生存时间。
- RabbitMQ支持消息的过期时间，在消息发送时可以指定。
- RabbitMQ支持队列的过期时间，从消息入队开始计算，只要超过了队列的超时时间配置，那么消息会自动清除。

### 1.2. 过期队列

> 在声明队列的时候，可以给队列增加属性来设置队列的过期时间。
>
> - `Map<String, Object> args = new HashMap<>();` 
> -  `args.put("x-message-ttl", 5000);`     
> - 声明 队列内消息有5秒过期时间， 这里的过期时间必须要使用 整数。

```java
@Configuration
public class TTLQueueConfig {

    /**
     * 1: 声明交换机
     *
     * @author Ringo
     * @date 2021/4/11
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("ttl_direct_order_exchange");
    }

    /**
     * 2: 声明 ttl 队列
     *
     * @author Ringo
     * @date 2021/4/11
     */
    @Bean
    public Queue ttlQueue() {
        Map<String, Object> args = new HashMap<>();
        
        // 声明 队列内消息有5秒过期时间
        // 这里的 过期时间必须要使用 整数
        args.put("x-message-ttl", 5000);
        return new Queue("ttl_queue", true, false, false, args);
    }

    /**
     * 3: 绑定 交换机 - 队列
     *
     * @author Ringo
     * @date 2021/4/11
     */
    @Bean
    public Binding directTtlQueueBinding() {
        return BindingBuilder.bind(ttlQueue()).to(directExchange()).with("ttl");
    }
}
```

![image-20210411144440700](E:\Typora\image\image-20210411144440700.png)



### 1.3.过期消息

```java
// 在发送消息之前，设置消息的过期时间
// setExpiration(String)：参数是String类型
// 2.1: 设置消息的过期时间
rabbitTemplate.convertAndSend(exchange, routingKey, order, message -> {
    message.getMessageProperties().setExpiration("5000");
    return message;
});
```



### 1.4. 总结

过期时间TTL表示可以对消息设置过期的时间，在这个时间内都可以被消费者接收获取，过了这段时间消息将会被自动删除。

RabbitMQ 可以对消息和队列设置TTL，目前有两种方式可以设置：

- 第一种方法是通过队列属性设置，队列中每条消息都有相同的过期时间。
- 第二种方法是对消息进行单独设置，每条消息TTL可以不同。



> 如果上述两种方法同时使用，则消息的过期时间以两者TTL较小的那个数值为准。
>
> 消息在队列的生存时间一旦超过设置的TTL值，就成为 dead message被投递到死信队列，消费者将无法收道该消息！
>
> **注意：第一种方式可以将消息转移到死信队列中；第二种方式消息过期会直接被删除**。



## 2. 死信队列

### 2.1. DLX介绍

- 利用`DLX（Dead-Letter-Exchange）`，当消息在一个队列中变成Dead Message后，它会被重新publish到另一个Exchange，这个Exchange就是DLX。
- DLX也是一个正常的Exchange，和一般的Exchange没有区别，它能在任何的队列上被指定，实际上就是设置某个队列的属性。
- 当这个队列中有Dead Message时，RabbitMQ就会自动的将这个消息重新发布到设置的Exchange上去，进而被路由到另一个队列。
- 可以监听这个死信队列中消息做相应的处理，这个特性可以弥补RabbitMQ3.0以前支持的immediate参数的功能。

> **消息变成Dead Message的情况**：
>
> - 消息被消费者拒绝（basicReject/basicNack）并且不能重回队列requeue=false。
> - 消息TTL过期。
> - 队列达到最大长度。



### **2.2. 代码实现**

**（1）定义死信队列**

```java
@Configuration
public class DeadQueueConfig {
    /**
     * 1: 声明交换机
     *
     * @author Ringo
     * @date 2021/4/11
     */
    @Bean
    public DirectExchange deadDirectExchange() {
        return new DirectExchange("dead_direct_order_exchange");
    }

    /**
     * 2: 声明死信队列
     *
     * @author Ringo
     * @date 2021/4/11
     */
    @Bean
    public Queue deadQueue() {
        return new Queue("dead_queue");
    }

    /**
     * 3: 绑定 交换机 - 队列
     *
     * @author Ringo
     * @date 2021/4/11
     */
    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadDirectExchange()).with("dead");
    }
}
```



**（2）带有过期时间的队列与死心队列绑定**

```java
@Configuration
public class TTLQueueConfig {

    /**
     * 1: 声明交换机
     *
     * @author Ringo
     * @date 2021/4/11
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("ttl_direct_order_exchange");
    }

    /**
     * 2: 声明 ttl 队列
     *
     * @author Ringo
     * @date 2021/4/11
     */
    @Bean
    public Queue ttlQueue() {
        Map<String, Object> args = new HashMap<>();
        // 声明 队列内消息有5秒过期时间
        args.put("x-message-ttl", 5000);

        // 绑定死信队列
        args.put("x-dead-letter-exchange", "dead_direct_order_exchange");
        args.put("x-dead-letter-routing-key", "dead");
        return new Queue("ttl_queue", true, false, false, args);
    }

    /**
     * 3: 绑定 交换机 - 队列
     *
     * @author Ringo
     * @date 2021/4/11
     */
    @Bean
    public Binding directTtlQueueBinding() {
        return BindingBuilder.bind(ttlQueue()).to(directExchange()).with("ttl");
    }
}
```

![image-20210411161321566](E:\Typora\image\image-20210411161321566.png)

- `TTL`：带有过期时间的队列。
- `DLX`：连接了死信交换机。
- `DLK`：需要死信交换机的routing key。



## 3. 内存磁盘监控

参考文档：https://www.rabbitmq.com/configure.html。

### 3.1. 命令行方式

```shell
# 命令方式 相对/绝对 二选一
rabiitmqctl set_vm_memory_high_watermark <fraction>   # 默认就是内存的0.4
rabiitmqctl set_vm_memory_high_watermark absolute 2GB
```

**通过命令修改内存阈值，服务器重启后会失效，通过配置文件修改将会永久修改**！



### 3.2. 配置文件修改

> 配置文件位置：/etc/rabbitmq/rabbitmq.conf

```shell
# 默认
# vm_memory_high_watermark.relative = 0.4
# 使用relative相对值进行设置, 建议取值在 0.4~0.7之间, 不建议超过0.7
vm_memory_high_watermark.relative = 0.6
# 使用absolute绝对值进行设置，单位KB、MB、GB, 对应的命令如下
vm_memory_high_watermark.absolute = 2GB
```

**内存不够就会出现这种情况**：

![image-20210411170353776](E:\Typora\image\image-20210411170353776.png)



### 3.3. 内存换页

在某个Broker结点及内存阻塞生产者之前，它会尝试将队列中的消息换页到磁盘以释放内存空间，持久化和非持久化的消息都会写入磁盘中，其中持久化的消息本身就在磁盘中有一个副本，所以在转移的过程中持久化的消息会先从内存中清除。

> **默认情况下，内存到大的阈值是50%时就会换页处理**。
>
> **也就是说，在默认情况下该内存的阈值是0.4的情况下，当内存超过 0.4 *0.5 = 0.2时，会进行换页动作**。

可以通过设置 `vm_memory_high_watermark_paging_ratio` 来进行调整。

```shell
vm_memory_high_watermark_paging_ratio = 0.5 (设置小于1的值)
```

为什么设置小于1，如果设置为1的话，内存已经到达了极限。再去内存换页就没有什么意义了！



## 4. 消息的可靠性投递 

![image-20210412002739401](E:\Typora\image\image-20210412002739401.png)



参考：https://github.com/RingoTangs/spring-boot-rabbitmq-delivery







4. 分布式事务

> 分布式事务：事务的操作位于不同的结点上，需要保证事务ACID的特性。
>
> 例如在下单场景下，库存和订单如果不在同一个结点上，就涉及到分布式事务。



## 5.消息的限流

> 什么是消费端的限流？

- 假设一个场景，首先我们的Rabbitmq服务器上有上万条未处理的消息，我们随便打开一个消费者客户端，会出现以下情况：
  - 巨量的消息瞬间全部推送过来，但是我们单个客户端无法同时处理这么多数据！
- **RabbitMQ提供一种qos（服务质量保证）功能，即在非自动确认消息（一定不能设置AutoACK）的前提下，如果一定数目的消息（通过基于consume或者channel设置Qos的值）未被确认前，不进行消费新的消息。**

> void basicQos(int prefetchSize, int prefetchCount, boolean global)

```java
/**
* prefetchSize：0 不做限制。
* prefetchCount：会告诉RabbitMQ不要同时给一个消费者推送多于N个消息，即一旦有N个消息还没有ACK，则该consumer将block， * 直到有消息ACK。
* global：true应用与channel，false应用于单个consumer。一个channel可以有多个consumer监听。
*/
void basicQos(int prefetchSize, int prefetchCount, boolean global)
```

- **注意：在AutoACK的情况下，Qos是不会生效的，一定要设置手动签收。**

> 自定义消费者手动ACK

```java
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * 自定义的消费者
 * 需要继承 com.rabbitmq.client.DefaultConsumer
 * 重写handleDelivery()方法
 */
public class CustomConsumer extends DefaultConsumer {

    private Channel channel;

    public CustomConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String msg = new String(body);
        System.out.println("-----------------------------------------------");
        System.out.println("consumerTag：" + consumerTag);
        System.out.println("envelope：" + envelope.toString());
        System.out.println("body：" + msg);
        System.out.println("-----------------------------------------------");

        // 设置2S的延迟
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        /**
         * 手动ACK
         * basicAck(long deliveryTag, boolean multiple)
         * multiple：false。因为我们是逐条消息ACK的所以设置为false，不批量签收。
         */
        channel.basicAck(envelope.getDeliveryTag(), false);
    }
}
```

> 消费端

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.ymy.rabbitmq.custom.CustomConsumer;

/**
 * 消费端 测试消费端消息的限流
 * 注意：消息限流的前提是设置手动ACK！
 */
public class QosConsumer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_qos_exchange";
    public static final String EXCHANGE_TYPE = "direct";
    public static final String ROUTING_KEY = "test.qos";
    public static final String QUEUE_NAME = "test_qos_queue";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、声明Exchange
         * exchangeDeclare(String exchange,String type,boolean durable,boolean autoDelete,boolean internal,Map<String, Object> arguments)
         */
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true, false, false, null);

        /**
         * 5、声明队列
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        /**
         * 6、Exchange和Queue的绑定关系
         * queueBind(String queue, String exchange, String routingKey, Map<String, Object> arguments)
         */
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY, null);

        /**
         * 7、消息限流
         * ACK一定要设置为false！
         * prefetchCount设置为1，消费端处理完一条消息之后Broker再推送一条消息
         */
        channel.basicQos(0, 1, false);
        channel.basicConsume(QUEUE_NAME, false, new CustomConsumer(channel));
    }
}
```

## 6. 消费端ACK与重回队列

#### 3.7.1.消费端的手工ACK和NACK

- 消费端进行消费的时候，如果由于业务异常我们可以进行日志记录，然后进行补偿！
- 如果由于服务器宕机等严重问题，那我们就需要手工进行ACK保障消费端消费成功。

#### 3.7.2.消费端的重回队列

- 消费端重回队列是为了对没有处理成功的消息，把消息重新会递给Broker！
- **一般在实际应用中，都会关闭重回队列，也就是设置为false。**

> 自定义消费者ACK、NACK和重回队列 

```java
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * 自定义消费者 测试ACK和NACK
 */
public class CstConsumer extends DefaultConsumer {

    private Channel channel;

    public CstConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String msg = new String(body);
        System.out.println("---------------------------------------");
        System.out.println("consumerTag：" + consumerTag);
        System.out.println("envelope：" + envelope.toString());
        System.out.println("body：" + msg);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if ((Integer) properties.getHeaders().get("flag") == 0) {
            System.out.println("********NACK***************");
            /**
             * basicNack(long deliveryTag, boolean multiple, boolean requeue)
             * multiple：是否批量NACK
             * requeue：是否重回队列
             */
            channel.basicNack(envelope.getDeliveryTag(), false, true);
        } else {
            System.out.println("********ACK***************");
            // basicAck(long deliveryTag, boolean multiple)
            channel.basicAck(envelope.getDeliveryTag(), false);
        }
    }
}
```

> 消费端

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AckConsumer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_ack_exchange";
    public static final String EXCHANGE_TYPE = "direct";
    public static final String ROUTING_KEY = "test.ack";
    public static final String QUEUE_NAME = "test_ack_queue";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、声明Exchange
         * exchangeDeclare(String exchange,String type,boolean durable,boolean autoDelete,boolean internal,Map<String, Object> arguments)
         */
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true, false, false, null);

        /**
         * 5、声明队列
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        /**
         * 6、Exchange和Queue的绑定关系
         * queueBind(String queue, String exchange, String routingKey, Map<String, Object> arguments)
         */
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY, null);

        /**
         * 7、消息消费
         * ACK一定要设置为false！
         * basicConsume(String queue, boolean autoAck, Consumer callback)
         */
        channel.basicConsume(QUEUE_NAME, false, new CstConsumer(channel));
    }
}
```

> 生产端

```java
import com.rabbitmq.client.*;
import java.util.HashMap;
import java.util.Map;

public class AckProducer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_ack_exchange";
    public static final String ROUTING_KEY = "test.ack";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        for (int i = 0; i < 5; i++) {
            /**
             * 4、发送消息
             * basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
             */
            String msg = "Hello Rabbitmq Ack Test..." + i;
            Map<String, Object> headers = new HashMap<>();
            headers.put("flag", i);
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .headers(headers)
                    .build();
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, properties, msg.getBytes());
        }
    }
}
```





# 三、RabbitMQ整合Spring

## 1. RabbitMQ整合Spring AMQP

#### 4.1.1.RabbitAdmin

- **注意：autoStartUp必须要设置为true，否则Spring容器不会加载RabbitAdmin类。**
- RabbitAdmin底层实现就是从Spring容器中获取Exchange、Binding、RoutingKey以及Queue的@Bean声明。
- 然后使用RabbitTemplate的execute()方法执行对应的声明、修改、删除等一系列RabbitMQ基础功能操作。例如：添加一个Exchange、删除一个Binding、清空队列里的消息等等。

> pom

```xml
<!--踩坑记：amqp-client低版本和spring-amqp整合会报错-->

<!--amqp-client version 5.4.3-->
<dependency>
    <groupId>com.rabbitmq</groupId>
    <artifactId>amqp-client</artifactId>
</dependency>

<!--spring-amqp version 2.2.2-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

> 配置类

```java
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
```

> RabbitAdmin基础API

```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
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
```

> Spring容器声明Exchange、Queue和Binding

```java
/**
* 1、设置Exchange的类型
* 	DirectExchange
* 	TopicExchange
* 	FanoutExchange
* 2、将Queue绑定到Exchange
* 
* 3、在配置类中声明Exchange、Queue、Binding就可以直接使用了！！！
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
```

#### 4.1.2.RabbitTemplate

- RabbitTemplate是RabbitMQ在与SpringAMQP整合的时候进行发送消息的关键类。
- RabbitTemplate提供了可靠性消息投递方法、回调监听消息接口ConfirmCallback、返回值确认接口ReturnCallback等等。同样我们需要进行注入到spring容器中，然后直接使用。
- 在与Spring整合时需要实例化，但是在与SpringBoot整合时，在配置文件里添加配置即可。

> 配置类

```java
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
```

> RabbitTemplate发送消息

```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitTemplateTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_NAME = "topic01";
    private static final String ROUTING_KEY = "spring.template";

    @Test
    public void testSendMessage() {
        // 1、spring封装的消息属性
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().put("describe", "信息描述");
        messageProperties.getHeaders().put("type", "自定义信息类型");

        // 2、创建消息
        Message message = new Message("Hello RabbitMQ...".getBytes(), messageProperties);

        /**
         * 3、发送消息
         * convertAndSend(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor)
         * message：Object类型不是只能传Message，也可以传其他对象。
         * messagePostProcessor：在消息发送到Broker之前対Message进行修改。
         */
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                System.out.println("************消息发送到Broker之前再对消息进行额外的设置**************");
                message.getMessageProperties().getHeaders().put("describe", "额外的信息修改描述");
                message.getMessageProperties().getHeaders().put("attribute", "额外新加的attribute");
                return message;
            }
        });
    }
}
```

#### 4.1.3.SimpleMessageListenerContainer

- SimpleMessageListenerContainer这个类非常强大，可以监听队列（多个队列）、自动启动、自动声明功能。
- 设置事务特性、事务管理器、事务属性、事务容量、是否开启事务、回滚消息等。
- 设置消费者数量、最小最大数量、批量消费。
- 设置消息确认和消息签收模式、是否重回队列、异常捕获Handler函数。
- 设置消费者标签生成策略、是否独占模式、消费者属性等。
- 设置具体的监听器、消息转换器等等。
- **注意：SimpleMessageListenerContainer可以进行动态设置，比如在运行中的应用可以动态的修改其消费者数量的大小、接收消息的模式等。**
- 很多基于RabbitMQ的自制定化后端管控台在进行动态设置的时候，也是根据这个动态设置特性去实现的。所以可以看出SpringAMQP非常的强大。

> 配置

```java
/**
* SimpleMessageListenerContainer
* 配置好了之后启动SpringBoot主函数即可消费Message
*/
@Bean
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

    // 消息的监听
    container.setMessageListener(new ChannelAwareMessageListener() {
        @Override
        public void onMessage(Message message, Channel channel) throws Exception {
            String msg = new String(message.getBody());
            System.out.println("*******消费者*******：" + msg);
        }
    });

    return container;
}
```

#### 4.1.4.MessageListenerAdapter

- MessageListenerAdapter：消息监听适配器。

> 自定义的消息委托者

```java
/**
 * 这个类是我们自定义的，但是方法名都是固定的。
 */
public class MessageDelegate {
    public void handleMessage(byte[] messageBody) {
        System.out.println("默认方法，消息内容：" + new String(messageBody));
    }
}
```

> 配置

```java
/**
* SimpleMessageListenerContainer
* 配置好了之后启动SpringBoot主函数即可消费Message
*/
@Bean
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

    // 通过MessageListenerAdapter进行消息的监听
    MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
    container.setMessageListener(adapter);
    return container;
}
```

#### 4.1.5.MessageConverter

- 在进行发送消息的时候，正常情况下消息体为二进制的数据方式进行传输，如果希望内部帮我们进行转换，或者指定自定义的转换器，就需要用到MessageConverter。
- **自定义常用转换器：MessageConverter，一般来讲都需要实现这个接口。**
- **重写下面两个方法：**
  - **toMessage()：java对象转换为Message。**
  - **fromMessage()：Message对象转换为java对象。**
- Json转换器：Jackson2JsonMessageConverter：可以将java对象转为json。
- DefaultJackson2JavaTypeMapper映射器：可以进行Java对象的映射。
- 自定义二进制转换器：比如图片类型、PDF、PPT、流媒体。

### 4.2.RabbitMQ整合Spring Boot

#### 4.2.1.生产端

> pom

```xml
<!--spring-amqp version 2.2.2-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

> application.yml

```yaml
server:
  port: 8801
spring:
  application:
    name: spring-boot-rabbitmq-producer
  rabbitmq:
    host: 192.168.110.133
    port: 5672
    username: guest
    password: guest
    virtual-host: /
    publisher-confirm-type: simple # 开启消息确认
    publisher-returns: true # 开启消息返回
    template:
      mandatory: true # 设置true 监听器会收到路由不可达消息
```

> 配置类

```java
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    /**
     * 消息序列化
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    /**
    * 如果是LocalDate类型的数据需要增强
    */
    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(om);
    }
}
```





> 发送消息并设置Confirm和Return

```java
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Component
public class RabbitSender {

    private static final String EXCHANGE_NAME = "exchange-1";
    private static final String ROUTING_KEY = "springboot.hello";


    @Autowired
    private RabbitTemplate rabbitTemplate;

    final ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.out.println("*************消息发送成功**************");
            System.out.println("correlationData：" + correlationData);
            System.out.println("ack：" + ack);
            System.out.println("cause：" + cause);
        }
    };

    final ReturnCallback returnCallback = new ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message,
                                    int replyCode, String replyText,
                                    String exchange, String routingKey) {
            System.out.println("**************Return********************");
            System.out.println("replyCode：" + replyCode);
            System.out.println("replyText：" + replyText);
            System.out.println("exchange：" + exchange);
            System.out.println("routingKey：" + routingKey);
        }
    };

    public void sendObject(Object message) {
        // 设置消息确认
        rabbitTemplate.setConfirmCallback(confirmCallback);

        // 设置消息返回
        rabbitTemplate.setReturnCallback(returnCallback);

        // id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString().substring(0, 6) + "_" + LocalDateTime.now());
        // convertAndSend(String exchange, String routingKey, Object message, CorrelationData correlationData)
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message, correlationData);
    }
```

> 测试

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class SendAndCallbackTest {

    @Resource
    private RabbitSender rabbitSender;
    
    @Test
    public void sendObject() {
        rabbitSender.sendObject(new Order("1", "订单"));
    }
}
```

#### 4.2.2.消费端

> 消费端配置详解

- 首先配置手工确认模式，用于ACK的手工处理，这样我们可以保证消息的可靠性送达，或者再消费端消费失败的时候可以做到重回队列、根据业务记录日志等处理。
- 可以设置消费端的监听个数和最大个数，用于控制消费端的并发情况。
- **消费端监听@RabbitMQListener注解，这个在实际工作中非常好用。**
- @RabbitMQListener是一个组合注解，里面可以注解配置@QueueBinding、@Queue、@Exchange直接通过这个组合注解一次性搞定消费端Exchange、Queue、Binding、Routing Key，并且配置监听功能等。

> pom

```xml
<!--web-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!--amqp-client version 5.4.3-->
<dependency>
    <groupId>com.rabbitmq</groupId>
    <artifactId>amqp-client</artifactId>
</dependency>

<!--spring-amqp version 2.2.2-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

> application.yml

```yaml
server:
  port: 8802
spring:
  application:
    name: spring-boot-rabbitmq-consumer
  rabbitmq:
    host: 39.97.3.60
    port: 5672
    username: guest
    password: guest
    virtual-host: /
    listener:
      simple:
        acknowledge-mode: manual # 手工签收
        concurrency: 5 # 最小的监听者数量
        max-concurrency: 10 # 最大的监听者数量
```

> 配置类

```java
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConf {
    /**
     * 消息序列化
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
   /**
    * 如果是LocalDate类型的数据需要增强
    */
    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(om);
    }
}
```

> 消息的监听

```java
import com.rabbitmq.client.Channel;
import com.ymy.spring.boot.rabbitmq.entity.Order;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 消费端监听队列 如果Message是jav ！！
 */
@Component
public class RabbitReceiverObject {

    private static final String EXCHANGE_NAME = "exchange-object";
    private static final String ROUTING_KEY = "springboot.*";
    private static final String QUEUE_NAME = "queue-object";

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = QUEUE_NAME,
                    durable = "true"),
            exchange = @Exchange(value = EXCHANGE_NAME,
                    type = ExchangeTypes.TOPIC,
                    durable = Exchange.FALSE),
            key = ROUTING_KEY
    ))
    @RabbitHandler
    public void onOrderMessage(@Payload Order order,
                               @Headers Map<String, Object> headers, Channel channel) throws Exception {
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        System.out.println("order id---> " + order.getId());
        System.out.println("order name---> " + order.getName());
        System.out.println(" deliveryTag ----> " + deliveryTag);
        channel.basicAck(deliveryTag, false);
    }
}
```

### 4.3.RabbitMQ整合Spring Cloud Stream

#### 4.3.1.Stream的基本介绍

- @Output：输出注解，用于定义消息生产者接口。
- @Input：输入注解，用于定义消息的消费者接口。
- @StreamListener：用于定义监听方法的注解。
- Spring Cloud Stream框架在实现高性能消息的生产和消费的场景非常合适，但是有一个非常大的问题就是不能实现可靠性的投递，也就是没办法保证消息的100%可靠性，会存在少量消息丢失的问题。

#### 4.3.2.生产端

> pom

```xml
<dependencies>
    <!--web-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!--监控-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

    <!--stream rabbit -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
    </dependency>

    <!--test-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

> application.yml

```yaml
server:
  port: 8803
spring:
  application:
    name: spring-cloud-stream-rabbitmq-producer
  cloud:
    stream:
      binders:
        rabbit:
          type: rabbit
          environment:
            spring:
              rabbitmq:
                host: 39.97.3.60
                port: 5672
                username: guest
                password: guest
                virtual-host: /
      bindings:
        output:
          destination: exchange_stream
          group: queue_stream
          binder: rabbit # 这里和binders.rabbit对应
```

> 发送消息

```java
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
```

> 测试

```java
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
```

#### 4.3.3.消费端

> pom

```xml
<dependencies>
    <!--web-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!--监控-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

    <!--stream rabbit -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
    </dependency>

    <!--test-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

> application.yml

```yaml
server:
  port: 8804
spring:
  application:
    name: spring-cloud-stream-rabbitmq-consumer
  cloud:
    stream:
      binders:
        rabbit:
          type: rabbit
          environment:
            spring:
              rabbitmq:
                host: 39.97.3.60
                port: 5672
                username: guest
                password: guest
                virtual-host: /
      bindings:
        input:
          destination: exchange_stream
          group: queue_stream
          binder: rabbit # 这里和binders.rabbit对应
          consumer:
            concurrency: 1
      rabbit:
        bindings:
          input:
            consumer:
              acknowledge-mode: MANUAL
              max-concurrency: 5
```

> 消费端监听

```java
package com.ymy.spring.cloud.stream.receive;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@EnableBinding(Sink.class)
@Component
public class RabbitReceiver {

    @StreamListener(Sink.INPUT)
    public void receiver(Message message) throws Exception{
        Channel channel = (Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        System.out.println(message.getPayload().getClass().getSimpleName());
        channel.basicAck(deliveryTag, false);
    }
}
```



