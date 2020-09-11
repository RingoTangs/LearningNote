# 1.计算机网络体系结构

## 1.1.OSI模型

`OSI`是`Open System Interconnection`，开放系统互联。

**`OSI`七层模型：**

- 7应用层(Application)：能够产生网络流量能够和用户交互的应用程序。

- 6表示层(Presentation)：数据加密压缩？ (开发人员需要考虑的问题）。

- 5会话层(Session)：服务和客户端建立的会话 (查木马 netstat -nb)。

- 4运输层(Transport): 可靠传输(建立会话)、不可靠传输(不建立会话)、流量控制。

- 3网络层(Network)：IP地址编址 选择最佳路径。

- 2数据链路层(Data Link)：数据如何封装 添加物理层地址(MAC地址)。

- 1物理层(Physical)：电压 接口标准。

程序开发人员需要考虑：应用层、表示层、会话层。

网络工程师需要考虑：运输层、网络层、数据链路层、物理层。



进行网络排错时：要从低层向高层来排错。



## 1.2.TCP/IP模型

**`TCP/IP`五层模型：**

- 5应用层(Appplication)：传输数据单元。

- 4运输层(Transport)：运输层报文。

- 3网络层(Network)：IP数据报(IP分组)。

- 2数据链路层(Data Link)：数据帧。

- 1物理层(Physical)：比特流011010101。

  

> TCP/IP协议通信流程

![TCP/IP协议发送数据流程](https://img-blog.csdnimg.cn/20200807223010316.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

# 2.物理层

## 2.1.物理层基本概念

物理层解决如何在连接各种计算机的**传输媒体**上传输**数据比特流**，而不是指具体的传输媒体。

物理层的主要任务描述为：确定与传输媒体的接口的一些特性，即：

- 机械特性：例如接口形状，大小，引线数目。
- 电气特性：例如规定电压范围（-5V到+5V）。
- 功能特性：例如规定-5V表示0，+5V表示1。
- 过程特性：也称规程特性，规定建立连接时各个相关部件的工作步骤。

## 2.2.数据通信基础知识

> 1、典型的数据通信模型

![数据通信模型](https://img-blog.csdnimg.cn/20200807224108424.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

**相关术语：**

- 通信的目的是传送消息。
- 数据(data)：运送消息的实体。
- 信号(signal)：数据的电气的或电磁的表现。
- 模拟信号：代表消息的参数的取值是连续的。
- 数字信号：代表消息的参数的取值是离散的。

# 3.数据链路层

## 3.1.基本概念

**数据链路层的信道类型：**

- **点对点信道。**这种信道使用一对一的点对点通信方式。
- **广播信道。**这种信道使用一对多的广播通信方式，因此过程比较复杂。广播信道上连接的主机很多，因此必须使用专用的共享信道协议来协调这些主机的数据发送。

**链路与数据链路：**

- **链路：**是一条点对点的物理线路段，中间没有任何其他断点。**一条链路只是一条通路的一个组成部分。**
- **数据链路：**除了物理线路外，还必须有通信协议来控制这些数据的传输。若把实现这些协议的硬件和软件加到链路上，就构成了数据链路。**现在最常用的方法是使用适配器（即网卡）来实现这些协议的硬件和软件。一般的适配器都包括了数据链层和物理层这两层的功能。**



**帧：**数据链路层传送的是帧。

**数据链路层像个数字管道：**常常在两个対等的数据链层之间画一个数字管道，而在这条数字管道上传输的数据单位是帧。

![数据链路层传送的是帧](https://img-blog.csdnimg.cn/20200808230959258.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

## 3.2.三个基本问题

> 问题1：封装成帧

**封装成帧：**

- 就是在一段数据前后分别添加首部和尾部,然后就构成了一个帧。确定帧的界限。
- 首部和尾部的一个重要作用就是进行帧定界。

![封装成帧](https://img-blog.csdnimg.cn/20200809223349567.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)



问题：为什么要有帧首部和帧尾部？只有帧首部不行吗？

计算机在接收帧的时候，只有同时收到帧首部和帧尾部才认为这是一个完整的帧，如果只有帧首部或者只有帧尾部，那么接收端计算机会将该帧丢弃，要求发送端计算机重新发送帧。



> 问题2：透明传输

**透明传输**：数据在传输过程中，数据部分可能会有帧开始符和帧结束符。这时就会出现问题，如下图所示：

![透明传输](https://img-blog.csdnimg.cn/20200809224938957.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)



**用字节填充法解决透明传输的问题**：

- 发送端的数据链路层在数据中出现控制字符`SOH`或者`EOT`的前面插入转义字符`ESC`。
- 接收端的数据链路层在将数据送往网络层之前删除插入的**转义字符**。
- 如果转义字符也出现数据当中，那么应在转义字符之前插入一个转义字符。当接收端收到连续的两个转义字符时，就删除其中前面的一个。

![字节填充](https://img-blog.csdnimg.cn/20200809230050268.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)



> 问题3：差错控制

传输过程中可能会产生**比特差错**：1可能会变成0，而0也可能变成1。为了保证数据传输的可靠性，在计算机网络传输数据时，必须采用各种**差错检测措施**。

**循环冗余检验CRC可以用于差错检测**。CRC差错检测技术只能做到**无差错接收**。

- 无差错接收：凡是接收端数据链路层接收的帧都没有传输差错（有差错的帧就被抛弃而不被接收）。

**要做到可靠传输,计算机端就必须加上确认和重传机制。**

## 3.3.PPP协议

**PPP协议**：`Point-to-Point Protocol `，现在全世界使用的最多的数据链路层协议是**点对点协议**。用户使用拨号上网，接入到因特网时，一般都是使用`PPP`协议。

**PPP协议具有以下功能**：

（1）PPP具有动态分配[IP](https://baike.baidu.com/item/IP)地址的能力，允许在连接时刻协商IP地址；

（2）PPP支持多种[网络协议](https://baike.baidu.com/item/网络协议)，比如[TCP/IP](https://baike.baidu.com/item/TCP%2FIP)、[NetBEUI](https://baike.baidu.com/item/NetBEUI)、[NWLINK](https://baike.baidu.com/item/NWLINK)等；

（3）PPP具有错误检测能力，但不具备纠错能力，所以**PPP协议是不可靠传输协议**；

（4）无重传的机制，网络开销小，速度快。

（5）PPP具有[身份验证](https://baike.baidu.com/item/身份验证)功能。

（6） PPP可以用于多种类型的物理介质上，包括串口线、电话线、移动电话和光纤（例如SDH），PPP也用于Internet接入。

**PPP协议的组成**：

- 网络控制协议(NCP)：允许在点到点上连接上使用多种网络层协议。
- 链路控制协议(LCP)：建立并维护数据链路连接。
- 高级数据链路控制协议(HDLC)。

<img src="https://img-blog.csdnimg.cn/20200809233809811.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70" alt="PPP协议组成" style="zoom:150%;" />



> PPP协议帧格式

![PPP协议帧格式](https://img-blog.csdnimg.cn/20200810225701704.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

`PPP`协议有2个字节的协议字段。

- `0x0021`——PPP帧的信息字段就是IP数据报。
- `0xC021`——信息字段是PPP链路控制数据。
- `0x8021`——表示信息部分是网络控制数据。
- `0xC023`——信息字段是安全性认证PAP。
- `0xC025`——信息字段是LQR。
- `0xC233`——信息字段是安全性认证`CHAP`。



**字节填充问题**：如果信息字段中出现了标志字段的值，可能会误认为“结束标志”怎么办？？？？

- 将信息字段中出现的每个`0x7E`字节转变成为2字节序列（`0x7D`和`0x5E`）。

- 若信息字段中出现一个`0x7D`字节，则将其转变为2个字节序列（`0x7D`和`0x5D`）。

## 3.4.以太网

**以太网是一种计算机局域网技术**。

局域网的主要特点是：网络为一个单位所拥有，并且地理范围和站点数目均有限。

局域网具有如下的一些主要优点：

- 具有广播功能，从一个站点可很方便地访问全网。局域网上的主机可共享连接在局域网上的各种硬件和软件资源。
- 便于系统的扩展和逐渐地演变，各设备的位置可以灵活调整和改变。
- 提高了系统的可靠性、可用性和生存性。

# 4.网络层

## 4.1.虚拟互联网

> 网络互连的设备

**中间设备**又称为中间系统或者中继系统。

- 物理层中继系统：转发器`repeater`。
- 数据链路层中继系统：网桥或桥接器`bridge`。
- 网络层中继系统：路由器`router`。
- 网络层以上的中继系统：网关`gateway`。

> 互连网络与虚拟互连网络

![虚拟互联网](https://img-blog.csdnimg.cn/20200811160234284.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

虚拟互连网络：

- 所谓虚拟互连网络，它的意思就是互连起来的各种物理网络的异构性本来是客观存在的，但是我们利用IP协议就可以把这些性能各异的网络**在网络层看起来好像是一个统一的网络。**
- 这种使用IP协议的虚拟互连网络可简称为IP网。
- 使用IP网通信的好处是：**当IP网上的主机进行通信时，就好像在一个单个网络上通信一样，他们看不见互连的各网络的具体异构细节**（如具体的编址方案、路由选择协议、等等）。

> IP协议简介

网际协议IP是`TCP/IP`体系中两个最主要的协议之一。

与IP协议配合使用的还有四个协议：

- `ARP`：地址解析协议、
- `RARP`：逆地址解析协议。
- `ICMP`：网际控制报文协议。
- `IGMP`：网际组管理协议。

![IPv4协议栈](https://img-blog.csdnimg.cn/2020081116114692.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

**网络层IP协议：RIP、OSPF、BGP协议动态路由最佳路径这些协议统称为IP协议**。



## 4.2.IP地址

> IP地址中的网络号字段和主机号字段

<img src="https://img-blog.csdnimg.cn/20200811165711601.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70" alt="IP地址" style="zoom:150%;" />



常用的三种类比的网络IP地址：

| 网络类别 |  最大网络数  | 第一个可用的网络号 | 最后一个可用的网络号 | 每个网络中最大的主机数 |
| :------: | :----------: | :----------------: | :------------------: | :--------------------: |
|    A     | 126(2^7 - 2) |         1          |         126          |       16,777,214       |
|    B     |   2^14 - 1   |       128.1        |       191.255        |         65,534         |
|    C     |   2^21 - 1   |      192.0.1       |     223.255.255      |          254           |

**特殊的地址**：

- `127.0.0.1`：本机地址。

**保留的私网地址**：在互联网上是没有这些地址的，只能在企业的内网中可以访问。

- `10.0.0.0`。
- `172.16.0.0`——`172.31.0.0`。
- `192.168.0.0`——`192.168.255.0`。

## 4.3.子网掩码

> 子网掩码的作用

![子网掩码的作用](https://img-blog.csdnimg.cn/20200811190724937.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

**子网掩码主要作用**：

- 用于屏蔽IP地址的一部分以区别网络标识和主机标识，并说明该IP地址是在局域网上，还是在远程网上。
- 是用于将一个大的IP网络划分为若干小的子网络。



## 4.4.子网划分

> 1、将一个C类网等分成两个子网

![子网划分](https://img-blog.csdnimg.cn/20200813220953867.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70#pic_center)

**通常来说，某个网段的第一个主机地址，被设置为网关。**

- A子网第一个可用的主机地址：`192.168.0.1`。

- B子网第一个可用的主机地址：`192.168.0.129`。



理解：如果说，将`192.168.0.0 255.255.255.0`这个C类网络划分为4个子网，那么子网掩码就是`255.255.255.192`。



## 4.4.IP地址和MAC地址

> IP地址和MAC地址的关系

![IP地址和MAC地址](https://img-blog.csdnimg.cn/20200814153630632.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70#pic_center)

> 计算机之间通信过程

![计算机通信过程](https://img-blog.csdnimg.cn/20200814152240487.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70#pic_center)



## 4.5.ARP协议

> ARP协议

`ARP`：地址解析协议，是根据IP地址获取计算机物理MAC地址的协议。主机发送信息时将包含目标IP地址的ARP请求**广播**到局域网络上的所有主机，并接收返回消息，以此确定目标的物理地址。

**一句话：通过目标设备的IP地址，查询目标设备的MAC地址，以保证通信的进行**。

> ARP欺骗

常见的ARP欺骗手法：同时对局域网内的一台主机和网关进行ARP欺骗，更改这台主机和网关的ARP缓存表。如下图（PC2是攻击主机，PC1是被攻击主机）所示：

<img src="https://img-blog.csdnimg.cn/2020081415515981.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70#pic_center" alt="ARP欺骗" style="zoom:150%;" />

攻击主机PC2发送ARP应答包给被攻击主机PC1和网关，分别修改它们的ARP缓存表, 将它们的IP地址所对应的MAC地址，全修改为攻击主机PC2的MAC地址，这样它们之间数据都被攻击主机PC2截获。

> 如何查看ARP欺骗

```shell
# 使用arp -a 命令来查看当前计算机连接到网关的MAC地址
# 如果查询到的MAC地址就是我们路由器网关的MAC地址,证明没有发生ARP欺骗
# 如果查询到的MAC地址就是其他计算机的MAC地址，说明发生了ARP欺骗，当前计算机的数据会被拦截
C:\Users\14666>arp -a

接口: 192.168.136.1 --- 0x2
  Internet 地址         物理地址              类型
  192.168.136.254       00-50-56-f0-d3-f2     动态
  192.168.136.255       ff-ff-ff-ff-ff-ff     静态
  224.0.0.2             01-00-5e-00-00-02     静态
  224.0.0.22            01-00-5e-00-00-16     静态
  224.0.0.251           01-00-5e-00-00-fb     静态
  224.0.0.252           01-00-5e-00-00-fc     静态
  239.255.255.250       01-00-5e-7f-ff-fa     静态
  255.255.255.255       ff-ff-ff-ff-ff-ff     静态
```

## 4.6.IP数据报首部

**一个IP数据报由首部和数据两部分组成**。

- 首部的前一部分是固定长度，共20字节，是所有IP数据报必须具有的。
- 在首部的固定部分的后面试一些可选字段，其长度是可变的！

![IP数据报首部](https://img-blog.csdnimg.cn/20200814173721701.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70#pic_center)

IP数据报首部：

- 版本：占4位，指IP协议的版本，目前的IP协议版本号为4（即IPv4）。

- 首部长度：占4位，可表示的最大数值，是15个单位（一个单位为4字节），因此IP数据报首部长度的最大值是60字节。

- 区分服务（QoS服务质量）：占8位，用来获取更好的服务，只有在使用区分服务时，这个字段才起作用。在一般的情况下都不使用这个字段。

- 总长度：占16位，指首部和数据部分之和的长度，单位为字节，因此IP数据报的最大长度为`2^16 - 1 = 65535`字节。总长度必须不超过最大传送单元MTU。

- 标识：占16位，它是一个计数器，用来产生数据报的标识，不是序号，每产生一个数据报，就增加1。

- 片偏移：占13位，较长的分组在分片后，某片在原分组中的相对位置。片偏移以8个字节为偏移单位。

- 生存时间：`TTL Time to Live`。IP数据报每过一个路由器TTL就会减1。永远到达不了的IP数据报就会超时。

  ```shell
  # ping 百度的服务器 使TTL = 5 数据报都到不了服务器就过期了！
  C:\Users\14666>ping www.baidu.com -i 5
  
  正在 Ping www.a.shifen.com [39.156.66.18] 具有 32 字节的数据:
  请求超时。
  来自 221.183.47.205 的回复: TTL 传输中过期。
  来自 221.183.47.205 的回复: TTL 传输中过期。
  来自 221.183.47.205 的回复: TTL 传输中过期。
  
  39.156.66.18 的 Ping 统计信息:
      数据包: 已发送 = 4，已接收 = 3，丢失 = 1 (25% 丢失)，
  ```

- 协议：占8位，指出此数据报携带的数据使用何种协议，以便目的主机的IP层将数据部分上交给哪个处理过程。

- 首部检验和：检查首部有没有错误。
- 源地址和目标地址：发送端IP地址和接收端IP地址。
- 可变部分：IP首部的可变长部分就是一个选项字段，用来支持排错、测量以及安全等措施，内容很丰富。

## 4.7.ICMP协议

> ICMP简介

ICMP（`Internet Control Message Protocol`）：

- ICMP允许主机或路由器报告差错情况和提供有关异常情况的报告。
- **ICMP不是高层协议，而是IP层的协议。**
- ICMP报文作为IP层数据报的数据，加上数据报的首部，组成IP数据报发送出来。

> ICMP报文类型

- `ICMP`报文的种类有两种，即**ICMP差错报告报文**和**ICMP询问报文**。
- `ICMP`报文的前4个字节是统一的格式，共有三个字段：即类型、代码和检验和。接着的4个字节的内容与ICMP的类型有关。
- 差错报告报文有五种：终点不可达、源点抑制、时间超过、参数问题、改变路由（重定向）。
- 询问报文有两种：回送请求和回答报文，时间戳请求和回答报文。

![ICMP](https://img-blog.csdnimg.cn/20200815143203651.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70#pic_center)

```shell
# pathping 跟踪路由转发 可以查看哪里丢包多
C:\Windows\System32>pathping www.baidu.com

通过最多 30 个跃点跟踪
到 www.a.shifen.com [39.156.66.18] 的路由:
  0  LAPTOP-19OEB0UT [192.168.31.254]
  1  XiaoQiang [192.168.31.1]
  2  100.93.64.1
  3  111.63.223.193
  4  111.11.64.21
  5     *     221.183.47.205
  6  221.183.37.189
  7     *        *        *
正在计算统计信息，已耗时 150 秒...
            指向此处的源   此节点/链接
跃点  RTT    已丢失/已发送 = Pct  已丢失/已发送 = Pct  地址
  0                                           LAPTOP-19OEB0UT [192.168.31.254]
                                0/ 100 =  0%   |
  1    4ms     0/ 100 =  0%     0/ 100 =  0%  XiaoQiang [192.168.31.1]
                                0/ 100 =  0%   |
  2    8ms     0/ 100 =  0%     0/ 100 =  0%  100.93.64.1
                                0/ 100 =  0%   |
  3    5ms     0/ 100 =  0%     0/ 100 =  0%  111.63.223.193
                                0/ 100 =  0%   |
  4   10ms     0/ 100 =  0%     0/ 100 =  0%  111.11.64.21
                                0/ 100 =  0%   |
  5    7ms     0/ 100 =  0%     0/ 100 =  0%  221.183.47.205
                              100/ 100 =100%   |
  6  ---     100/ 100 =100%     0/ 100 =  0%  221.183.37.189

跟踪完成。
```

# 5.运输层

## 5.1.运输层和应用层的关系

```shell
# netstat -an 可以查看计算机侦听的端口
C:\Users\14666>netstat -an

活动连接

  协议  本地地址          外部地址        状态
  TCP    0.0.0.0:135            0.0.0.0:0              LISTENING
  TCP    0.0.0.0:443            0.0.0.0:0              LISTENING
  TCP    0.0.0.0:445            0.0.0.0:0              LISTENING
  TCP    0.0.0.0:902            0.0.0.0:0              LISTENING
  TCP    0.0.0.0:912            0.0.0.0:0              LISTENING
  TCP    0.0.0.0:3306           0.0.0.0:0              LISTENING
  TCP    0.0.0.0:5040           0.0.0.0:0              LISTENING
  TCP    0.0.0.0:49664          0.0.0.0:0              LISTENING
  TCP    0.0.0.0:49665          0.0.0.0:0              LISTENING
  TCP    0.0.0.0:49666          0.0.0.0:0              LISTENING
  TCP    0.0.0.0:49667          0.0.0.0:0              LISTENING
  TCP    0.0.0.0:49668          0.0.0.0:0              LISTENING
  TCP    0.0.0.0:49680          0.0.0.0:0              LISTENING
  TCP    127.0.0.1:8307         0.0.0.0:0              LISTENING
  TCP    127.0.0.1:28317        0.0.0.0:0              LISTENING
  TCP    127.0.0.1:50094        127.0.0.1:65001        ESTABLISHED
  TCP    127.0.0.1:50119        0.0.0.0:0              LISTENING
  TCP    127.0.0.1:65000        0.0.0.0:0              LISTENING
  TCP    127.0.0.1:65001        0.0.0.0:0              LISTENING
  TCP    127.0.0.1:65001        127.0.0.1:50094        ESTABLISHED
  TCP    192.168.31.254:139     0.0.0.0:0              LISTENING
  TCP    192.168.31.254:50124   52.139.250.253:443     ESTABLISHED
  TCP    192.168.31.254:50339   60.210.8.160:443       ESTABLISHED
  TCP    192.168.31.254:50340   60.210.8.160:443       ESTABLISHED
  TCP    192.168.31.254:50341   60.210.8.160:443       ESTABLISHED
  TCP    192.168.31.254:50358   23.41.64.91:80         ESTABLISHED
  TCP    192.168.31.254:50372   23.207.174.68:443      ESTABLISHED
  TCP    192.168.31.254:50911   111.62.72.10:443       ESTABLISHED
  TCP    192.168.31.254:51172   111.62.79.254:443      ESTABLISHED
  TCP    192.168.31.254:51297   120.92.79.46:7823      ESTABLISHED
  TCP    192.168.31.254:51366   111.62.241.131:443     ESTABLISHED
  TCP    192.168.31.254:51369   111.62.72.6:443        ESTABLISHED
  TCP    192.168.31.254:51375   202.89.233.101:443     ESTABLISHED
  TCP    192.168.31.254:51376   202.89.233.101:443     ESTABLISHED
  TCP    192.168.31.254:51377   40.90.22.185:443       ESTABLISHED
  TCP    192.168.31.254:51378   204.79.197.222:443     ESTABLISHED
  TCP    192.168.31.254:51380   13.107.4.254:443       ESTABLISHED
  TCP    192.168.31.254:51381   13.107.246.254:443     ESTABLISHED
  TCP    192.168.31.254:51382   13.107.6.254:443       ESTABLISHED
  TCP    192.168.31.254:51384   109.244.23.87:80       TIME_WAIT
  TCP    192.168.31.254:51389   112.34.111.123:443     ESTABLISHED
  TCP    192.168.110.1:139      0.0.0.0:0              LISTENING
  TCP    192.168.136.1:139      0.0.0.0:0              LISTENING
  TCP    [::]:135               [::]:0                 LISTENING
  TCP    [::]:443               [::]:0                 LISTENING
  TCP    [::]:445               [::]:0                 LISTENING
  TCP    [::]:49664             [::]:0                 LISTENING
  TCP    [::]:49665             [::]:0                 LISTENING
  TCP    [::]:49666             [::]:0                 LISTENING
  TCP    [::]:49667             [::]:0                 LISTENING
  TCP    [::]:49668             [::]:0                 LISTENING
  TCP    [::]:49680             [::]:0                 LISTENING
  TCP    [::1]:8307             [::]:0                 LISTENING
  TCP    [::1]:49916            [::]:0                 LISTENING
```





TCP：分段、编号、流量控制、建立会话 ，**可靠传输**。

UDP：一个数据包就能完成数据通信，不建立会话，**不可靠传输**。



