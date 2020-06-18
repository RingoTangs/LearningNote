## 1.主流消息中间件的介绍

### 1.1.ActiveMQ

> ActiveMQ的特点

- ActiveMQ是Apache出品，最流行的，能力强劲的开源消息总线，并且完全支持JMS规范的消息中间件。
- 有丰富的API，多种集群架构模式使得ActiveMQ成为业界老牌消息中间件，在中小型企业中应用广泛。
- 面对超大规模并发的时候ActiveMQ会造成阻塞等问题。。？？

### 1.2.Kafka

> Kafka的特点

- Kafka是开源的分布式发布—订阅消息系统，目前属于Apache的顶级项目。
- Kafka追求高吞吐量，一开始的目的就是用于日志收集和传输。
- 0.8版本开始支持复制，不支持事务，対消息重复、丢失、错误没有严格的要求。
- Kafka适合产生大量数据的互联网服务的数据收集业务。

### 1.3.RocketMQ

> RocketMQ特点

- RocketMQ是阿里开源的消息中间件，纯Java开发，有高吞吐量、高可用性、适合大规模分布式系统应用的特点。
- RocketMQ思路取源于Kafka，対消息的可靠传输及事务性做了优化，在阿里内部被广泛用于交易、充值、流计算、消息推送、日志流式处理等场景。
- 但是RocketMQ收费。。？？？？



### 1.4.RabbitMQ

> RabbitMQ特点

- RabbitMQ是使用Erlang语言开发的开源消息队列系统，基于AMQP协议来实现。
- AMQP的主要特征是面向消息、队列、路由（包括点对点和发布—订阅）、可靠性、安全。
- AMQP协议更多用在企业系统内，対数据一致性、稳定性和可靠性要求很高的场景，対性能和吞吐量的要求排在其次。性能和Kafka没发比但是比ActiveMQ要好很多。

## 2.RabbitMQ核心概念以及AMQP协议

### 2.1.互联网大厂为什么选择RabbitMQ？

- RabbitMQ使用Erlang语言来编写的，并且是基于AMQP协议的。
- 开源、性能优秀，稳定性保障。
- 提供 可靠性消息投递模式(confirm)、返回模式(return)。
- 与SpringAMQP完美的整合、API丰富。
- 集群模式丰富，表达式配置，HA模式，镜像队列模型。
- 保障数据不丢失的前提下做到高可靠性、可用性。

### 2.2.RabbitMQ的高性能是如何做到的？

- Erlang语言最初在于交换机领域的架构模式，这样使得RabbitMQ在Broker之间进行数据交互的性能是非常优秀的。
- Erlang的优点：Erlang有着和原生Socket一样的延迟。

### 2.3.什么是AMQP高级协议？

> AMQP协议简介

- AMQP全称：Advanced Message Queuing Protocol 高级消息队列协议。
- AMQP定义：是具有现代特征的二进制协议。是一个提供统一消息服务的应用层标准高级消息队列协议，是应用层协议的一个开放标准，为面向消息的中间件设计。

> AMQP协议模型

<img src=".\image\2.3-1-AMQP协议模型.jpg" alt="AMQP协议模型"  />

### 2.4.AMQP核心概念是什么？

- Server：又称作Broker，接受客户端的连接，实现AMQP实体服务。
- Connection：连接，应用程序与Broker的网络连接。
- Channel：网络信道，几乎所有的操作都在Channel中进行，Channel是进行消息读写的通道。客户端可以建立多个Channel，每个Channel代表一个会话任务。
- Message：消息。服务器和应用程序之间传送的数据，由Properties和Body组成。Properties可以对消息进行修饰，比如消息的优先级、延迟等高级特性；Body就是消息体内容。
- Virtual Host：虚拟主机，用于进行逻辑隔离，最上层的消息路由。一个Virtual Host里面可以有若干个Exchange和Queue，同一个Virtual Host里面不能有相同名称的Exchange和Queue。
- Exchange：交换机，接收消息。根据Routing Key转发消息到绑定的队列。
- Binding：Exchange和Queue之间的虚拟连接，Binding中可以包含Routing Key。
- Routing Key：一个路由规则，虚拟机可以用它来确定如何路由一个特点消息。
- Queue：也成为了Message Queue，消息队列，保存消息并转发给消费者。

### 2.5.RabbitMQ整体架构模型

![2.5-1-RabbitMQ的整体架构图](.\image\2.5-1-RabbitMQ的整体架构图.jpg)





### 2.6.RabbitMQ消息是如何进行流转的？

![2.6-1-RabbitMQ消息流转图](.\image\2.6-1-RabbitMQ消息流转图.jpg)



### 2.7.RabbitMQ安装和使用

**官网地址：https://www.rabbitmq.com/**

**Erlang和RabbitMQ版本对照表：https://www.rabbitmq.com/which-erlang.html**

**相关软件包和资料：链接：https://pan.baidu.com/s/1uFP2YU7xPK2KBkaP4gXvLw** 
**提取码：s6z8**

> 安装Rabbitmq

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

> Rabbitmq服务的启动和停止

```shell
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

### 2.8.命令行和管控台

> 基础操作

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

> 高级操作

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

### 2.9.RabbitMQ消息生产与消费

> Pom依赖

```java
<dependency>
	<groupId>com.rabbitmq</groupId>
	<artifactId>amqp-client</artifactId>
    <version>3.6.5</version>
</dependency>
```

> 消费者

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Rabbitmq 消费者
 * 先启动消费者创建队列，生产者再投递。
 */
public class Consumer {
    public static final String HOST = "192.168.110.133";
    public static final String QUEUE_NAME = "test001";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、声明队列
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,Map<String, Object> arguments)
         * queue：队列的名字。
         * durable：是否持久化。
         * exclusive：是否独占 独占的意思就是这个Queue只能有我这一个Channel监听。
         * autoDelete：设置为true,没有消费者监听该队列，该队列就会自动删除。
         * arguments：扩展参数。
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // 5、创建消费者，在RabbitMQ中不需要定义QueueingProducer 只需要定义QueueingConsumer即可
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        /**
         * 6、channel的设置
         * basicConsume(String queue, boolean autoAck, Consumer callback)
         * queue：队列的名字
         * autoAck：是否自动签收
         * callback：具体的消费者对象
         */
        String consumerTag = channel.basicConsume(QUEUE_NAME, true, queueingConsumer);
        System.out.println("******consumerTag*****" + consumerTag);

        while (true) {
            /**
             * 7、获取消息
             * nextDelivery()：如果获取不到消息就一直阻塞
             */
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            // 8、获得消息体
            String msg = new String(delivery.getBody());
            System.out.println("*******消费端接收到的消息为=====>" + msg);
        }
    }
}
```

> 生产者

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Rabbitmq 生产者
 */
public class Producer {

    public static final String HOST = "192.168.110.133";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        // 4、通过Channel发送数据
        // basicPublish(String exchange,String routingKey,BasicProperties props,byte[] body)
        channel.basicPublish("", "test001", null, "hello rabbitmq".getBytes());

        // 5、关闭连接
        channel.close();
        connection.close();

        System.out.println("*****生产者消息发布成功！****");
    }
}
```

> 问题：生产者发送消息，没有指定交换机但是routingKey为"test001"就可以路由到"test001"队列，为什么？

RabbitMQ生产者投递消息如果**不指定Exchange**，那么就会默认使用**AMQP.default**这个Exchange，它的路由规则就是根据生产者指定的RoutingKey和队列的名字去对比，如果名字一致就将生产者的消息发送给该队列。

### 2.10.RabbitMQ交换机详解

#### 2.10.1.Exchange的概念

Exchange：接收消息，并根据RoutingKey转发消息所绑定的队列。

#### 2.10.2.Exchange属性

- Name：Exchange名称。
- Type：Exchange的类型。direct、topic、fanout、headers。
- Durability：是否需要持久化，true为持久化。
- Auto Delete：当最后一个绑定到Exchange上的队列删除后，自动删除该Exchange。
- Internal：当前Exchange是否用于RabbitMQ内部使用，默认为false。**(很少使用)**
- Arguments：扩展参数，用于扩展AMQP协议自制定化使用。

#### 2.10.3.Direct Exchange

> Direct Exchange基本概念

- **所有发送到Direct Exchange的消息被转发到Routing key中指定的Queue。**
- **一句话：直连的方式，生产者发送消息的Routing Key和Direct Exchange的Routing Key必须完全匹配，才会路由到绑定的Queue。**

> 消费者

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

> 生产者

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

#### 2.10.4.Topic Exchange

> Topic Exchange基本概念

- 所有发送到Topic Exchange的消息被转发到所有关心Routing Key中指定Topic的Queue上。
- Exchange将Routing Key和某个Topic进行模糊匹配，此时队列需要绑定一个Topic。
- **一句话：Topic Exchange和Queue绑定Routing Key可以使用通配符，生产者发送消息的Routing Key只要和Topic Exchange的Routing Key匹配就能路由到Topic Exchange绑定的队列。**

> 模糊匹配可以使用通配符

- **符号 "#" 匹配一个或多个词。**
- **符号 "*" 匹配不多不少一个词。**
- **例如："log.#" 能够匹配到 "log.info.aa"。"log.*" 只能匹配到 "log.err"。**

> 消费者

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

> 生产者

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

#### 2.10.5.Fanout Exchange

> Fanout Exchange基本概念

- **Fanout Exchange不处理Routing Key，只需要简单的将Queue绑定到Exchange上。**
- **发送到Exchange的消息都会被转发到与该Exchange绑定的所有Queue上。**
- **Fanout Exchange转发消息是最快的。**

> 消费者

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

> 生产者

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

### 2.11.RabbitMQ队列、绑定、虚拟主机、消息

#### 2.11.1.Binding(绑定)

> Binding基本概念

- Exchange和Exchange、Queue之间的绑定关系。
- Binding中可以包含Routing Key或者参数。

#### 2.11.2.Queue(队列)

> Queue基本概念

- 消息队列，实际存储消息数据。
- Durability：是否持久化。Durable：是，Transient：否。
- Auto Delete：如果选true，代表当最后一个监听被移除之后，该Queue会自动删除。

#### 2.11.3.Message(消息)

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

#### 2.11.4.Virtual Host(虚拟主机)

> Virtual Host基本概念

- 虚拟地址，用于进行逻辑隔离，最上层的消息路由。
- 一个Virtual Host里面可以有若干个Exchange和Queue。
- 同一个Virtual Host里面不能有相同名称的Exchange和Queue。



## 3.RabbitMQ的高级特性

### 3.1.如何保障消息100%的投递成功？

#### 3.1.1.什么是消息的可靠性传递？

- 保障消息的成功发出。
- 保障MQ节点的成功接收。
- 发送端收到MQ节点（Broker）的应答。
- 完善的消息补偿机制。

#### 3.1.2.生产端—可靠性投递解决方案

##### 3.1.2.1.方案一：消息落库，対消息状态进行打标。

> 消息落库图示

![3.1.2-1-消息落库](.\image\3.1.2-1-消息落库.jpg)



> 思考：如果使用消息落库的可靠性投递，在高并发场景是否合适？



##### 3.1.2.2.方案二：消息的延迟投递，做二次确认，回调检查。

> 消息回调检查图示

![3.1.2-2-消息延迟投递回调检查](.\image\3.1.2-2-消息的回调检查.jpg)



### 3.2.消息幂等性概念

> 幂等性是什么？

- **一句话：用户对于同一操作发起的一次或者多次请求，最后的结果都是相同的，这就是幂等性。在MQ中就是保障消息不要被重复消费。**

### 3.3.在海量订单产生的业务高峰期，如何避免消息的重复消费？

#### 3.3.1.消费端的幂等性

- 消费端实现幂等性，就意味着，我们的消息永远不会消费多次，即使我们收到了多条一样的消息。

#### 3.3.2.业界主流的幂等性操作

##### 3.3.2.1.方案一：唯一ID + 指纹码机制，利用数据库主键去重。

- select count(1) from t_order where id = 唯一ID+指纹码
- 好处：实现简单
- 坏处：高并发下数据库写入性能瓶颈。
- 解决方案：跟进ID进行分库分表进行算法路由。

##### 3.3.2.2.方案二：利用Redis的原子性去实现。

> 使用Redis进行幂等，需要考虑的问题

1. 我们是否要进行数据落库，如果落库的话，关键解决的问题是数据库和缓存如何做到原子性？
2. 如果不进行落库，那么都存储到Redis缓存中，如何设置定时同步策略。

### 3.4.两种消息投递方式：Confirm确认消息、Return返回消息

#### 3.4.1.Confirm确认消息（生产端）

> Confirm消息确认机制

- 消息的确认，是指生产者投递消息后，如果Broker收到消息，会给生产者一个应答。
- 生产者进行接收应答，用来确认这条消息是否发送到Broker，这种方式也是消息的可靠性投递的核心保障。

#### 3.4.2.实现Confirm确认消息

>消费者

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 消费者 用来测试Rabbitmq的Confirm机制
 */
public class Consumer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_confirm_exchange";
    public static final String EXCHANGE_TYPE = "direct";
    public static final String ROUTING_KEY = "test.confirm";
    public static final String QUEUE_NAME = "test_confirm_queue";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);

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
            String msg = new String(delivery.getBody());
            System.out.println("*******消费端接收到的消息为=====>" + msg);
        }
    }
}
```

> 生产者设置消息的确认模式和监听

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * 生产者 测试消息发送到Broker的Confirm机制
 * 第一步：声明消息的确认模式 channel.confirmSelect();
 * 第二步：添加监听 channel.addConfirmListener();
 */
public class Producer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_confirm_exchange";
    public static final String ROUTING_KEY = "test.confirm";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        // 4、指定消息的确认模式：Confirm
        channel.confirmSelect();

        /**
         * 5、发送消息
         * basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
         */
        String msg = "Hello Message Confirm...";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.getBytes());

        /**
         * 6、添加一个确认监听
         */
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("***********消息成功发送到Broker！***************");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("**************消息发送失败！********************");
            }
        });
    }
}
```

#### 3.4.3.Return返回消息（生产端）

> Return消息返回机制

- Return Listener用于处理一些不可路由的消息。
- 在某些情况下，如果我们在发送消息的时候，当前的Exchange不存在或者指定的Routing Key不存在，这个时候我们需要监听这种不可达的消息，就要使用Return Listener！

> Return机制基础API的关键配置项

- Mandatory：如果为true，则监听器会收到路由不可达的消息，然后进行后续处理；如果为false，那么Broker会自动删除该消息。

#### 3.4.4.实现Return 消息返回

> 生产者测试Return机制

```java
import com.rabbitmq.client.*;

import java.io.IOException;
/**
 * 生产者 测试消息的Return机制
 * 当有不可路由的消息时候发送到Broker，来监听这些不可达的消息。
 * 第一步：mandatory设置为true监听不可达的消息。
 * 第二步：channel.addReturnListener()添加消息返回机制的监听者。
 */
public class Producer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_confirm_exchange";
    public static final String ROUTING_KEY = "TangShi"; //不存在的Routing Key

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、发送消息
         * basicPublish(String exchange, String routingKey, boolean mandatory, BasicProperties props, byte[] body)
         * mandatory：如果为true，则监听器会收到路由不可达的消息，然后进行后续处理；如果为false，那么Broker会自动删除该消息。
         */
        String msg = "Hello Message Return....";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, true, null, msg.getBytes());

        /**
         * 5、监控不可路由的消息
         * handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body)
         * replyCode：响应码
         * replyText：文本
         * exchange：具体的Exchange
         * routingKey：具体的Routing Key
         * properties：消息的属性
         * body：实际的消息体的内容。
         */
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("replyCode：" + replyCode);
                System.out.println("replyText：" + replyText);
                System.out.println("exchange：" + exchange);
                System.out.println("routingKey：" + routingKey);
                System.out.println("body：" + msg);
            }
        });
    }
}

// 控制台打印结果为：
replyCode：312
replyText：NO_ROUTE
exchange：test_confirm_exchange
routingKey：TangShi
body：Hello Message Return....
```

### 3.5.自定义消费者监听

> 自定义消费者

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
    public CustomConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String msg = new String(body);
        System.out.println("consumerTag：" + consumerTag);
        System.out.println("envelope：" + envelope.toString());
        System.out.println("body：" + msg);
    }
}
```

>消费端

```java
package com.ymy.rabbitmq.custom;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 消费者 测试自定义的消费者
 */
public class Consumer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_custom_consumer_exchange";
    public static final String EXCHANGE_TYPE = "direct";
    public static final String ROUTING_KEY = "test.custom";
    public static final String QUEUE_NAME = "test_custom_consumer_queue";

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
         * 7、channel的设置
         * basicConsume(String queue, boolean autoAck, Consumer callback)
         */
        channel.basicConsume(QUEUE_NAME, true, new CustomConsumer(channel)); //这里的消费端用我们自定义的消费者
    }
}

// 控制台打印结果：
consumerTag：amq.ctag-hDLldSV5KZuRFkdGJLoYng
envelope：Envelope(deliveryTag=1, redeliver=false, exchange=test_custom_consumer_exchange, routingKey=test.custom)
body：Hello Custom Consumer...
```

### 3.6.消息的限流

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

### 3.7.消费端的ACK与重回队列机制

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



### 3.8.TTL消息

> TTL是什么？

- TTL：Time To Live，也就是生存时间。
- RabbitMQ支持消息的过期时间，在消息发送时可以指定。
- RabbitMQ支持队列的过期时间，从消息入队开始计算，只要超过了队列的超时时间配置，那么消息会自动清除。

### 3.9.死信队列

#### 3.9.1.DLE(Dead-Letter-Exchange)

- 利用DLX，当消息在一个队列中变成Dead Message后，它会被重新publish到另一个Exchange，这个Exchange就是DLX。
- **DLX也是一个正常的Exchange，和一般的Exchange没有区别，它能在任何的队列上被指定，实际上就是设置某个队列的属性。**
- 当这个队列中有Dead Message时，RabbitMQ就会自动的将这个消息重新发布到设置的Exchange上去，进而被路由到另一个队列。
- 可以监听这个死信队列中消息做相应的处理，这个特性可以弥补RabbitMQ3.0以前支持的immediate参数的功能。

#### **3.9.2.消息变成Dead Message的情况**

- 消息被消费者拒绝（basicReject/basicNack）并且不能重回队列requeue=false。
- 消息TTL过期。
- 队列达到最大长度。

#### **3.9.3.死信队列的代码实现**

> 定义DLX

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * 死信队列：DLX其实是正常的Exchange
 * 注：DLX的Exchange Type必须是topic。Routing Key为"#"表示全部匹配。
 */
public class DLX {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "dlx_exchange";
    public static final String EXCHANGE_TYPE = "topic";
    public static final String ROUTING_KEY = "#";
    public static final String QUEUE_NAME = "dlx_queue";

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
    }
}
```

> 消费端

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.ymy.rabbitmq.dlx.custom.NormalCustomConsume;
import java.util.HashMap;
import java.util.Map;
/**
 * 消费者 接收正常Queue的消息
 * 声明队列的时候加上arguments属性指定DLX
 */
public class NormalConsumer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "normal_exchange";
    public static final String DLX_EXCHANGE_NAME = "dlx_exchange";
    public static final String EXCHANGE_TYPE = "direct";
    public static final String ROUTING_KEY = "test.normal";
    public static final String QUEUE_NAME = "normal_queue";

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
        // 在属性中添加DLX
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", DLX_EXCHANGE_NAME);
        channel.queueDeclare(QUEUE_NAME, true, false, false, arguments);

        /**
         * 6、Exchange和Queue的绑定关系
         * queueBind(String queue, String exchange, String routingKey, Map<String, Object> arguments)
         */
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY, null);

        /**
         * 7、消费者
         * basicConsume(String queue, boolean autoAck, Consumer callback)
         */
        channel.basicConsume(QUEUE_NAME, false, new NormalCustomConsume(channel));
    }
}
```

> 自定义的消费者

```java
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
/**
 * 自定义的消费者
 */
public class NormalCustomConsume extends DefaultConsumer {

    private Channel channel;

    public NormalCustomConsume(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String msg = new String(body);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("********body*******：" + body);
        if ((Integer) properties.getHeaders().get("flag") == 0) {
            System.out.println("********NACK************");
            // basicNack(long deliveryTag, boolean multiple, boolean requeue)
            channel.basicNack(envelope.getDeliveryTag(), false, false);
        } else {
            System.out.println("********ACK************");
            // basicAck(long deliveryTag, boolean multiple)
            channel.basicAck(envelope.getDeliveryTag(), false);
        }
    }
}
```

> 生产端

```java
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;
/**
 * 生产者 向正常的Exchange投递消息
 */
public class NormalProducer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "normal_exchange";
    public static final String ROUTING_KEY = "test.normal";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        for (int i = 0; i < 5; i++) {
            String msg = "Hello Rabbitmq Message.." + i;
            Map<String, Object> headers = new HashMap<>();
            headers.put("flag", i);
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .headers(headers)
                    .expiration("10000") // 10S没有消费者处理该消息就过期
                    .build();
            // 发送消息 basicPublish(String exchange, String routingKey, AMQP.BasicProperties props, byte[] body)
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, properties, msg.getBytes());
        }
    }
}
```



## 4.RabbitMQ高级整合应用

### 4.1.RabbitMQ整合Spring AMQP

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
 * 消费端监听队列 如果Message是java对象一定要写无参构造器！！
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



