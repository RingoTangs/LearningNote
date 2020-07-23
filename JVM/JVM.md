# 1.JVM探究

- 请谈谈你对JVM的理解？java8虚拟机和之前的变化更新？
- 什么是OOM，什么是栈溢出StackOVerFlowError？怎么分析？
- JVM常用的调优参数有哪些？
- 内存快照如何抓取？怎么分析Dump文件？
- 谈谈JVM中，类加载器你的认识？`rt.jar`

# 2.JVM的体系结构

> JVM的位置

![JVM体系结构](https://mmbiz.qlogo.cn/mmbiz_png/puJI9KpRbPWHSPQicfOCnYDx8S7VDY2P2CPLdaRJ6TanPtms2ibbtDh1AfVbREVN653roqp2o3dgwqEWOlkBtj5w/0?wx_fmt=png)

> JVM结构

![JVM结构](https://pics0.baidu.com/feed/37d12f2eb9389b50828b0dfd61271ed9e6116ec5.jpeg?token=d636526e80bfc081f77f2572a772fe0f&s=B1B25D32819E4DCA56C9C0CE020090B2)

# 3.类加载器和双亲委派

## 3.1.对象实例化过程

![对象实例化](https://img-blog.csdnimg.cn/20200722054641601.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

**类加载器就是用来加载`.class`文件，到`JVM`中变成`Class`文件**。



## 3.2.类加载器类型

![类加载器](https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=977934582,3403249916&fm=26&gp=0.jpg)

- 启动类加载器：这个类加载器负责放在`<JAVA_HOME>\lib`目录中的，或者被`-Xbootclasspath`参数所指定的路径中的，并且是虚拟机识别的类库。==用户无法直接使用==。

- 扩展类加载器：这个类加载器由`sun.misc.Launcher$ExtClassLoader`r实现。它负责`<JAVA_HOME>\lib\ext`目录中的，或者被`java.ext.dirs`系统变量所指定的路径中的所有类库。==用户可以直接使用==。

- 应用程序类加载器：这个类由`sun.misc.Launcher$AppClassLoader`实现。是`ClassLoader中getSystemClassLoader()`方法的返回值。它负责用户路径（ClassPath）所指定的类库。用户可以直接使用。==如果用户没有自己定义类加载器，默认使用这个==。

- 自定义加载器：用户自己定义的类加载器。

```java
package com.ymy.jvm;

public class Car {
	public static void main(String[] args) {
		// 类是模板,对象是具体的
		
		Car car1 = new Car();
		Car car2 = new Car();
		Car car3 = new Car();

		System.out.println(car1.hashCode()); // 366712642
		System.out.println(car2.hashCode()); // 1829164700
		System.out.println(car3.hashCode()); // 2018699554

		Class<? extends Car> class1 = car1.getClass();
		Class<? extends Car> class2 = car2.getClass();
		Class<? extends Car> class3 = car3.getClass();

		System.out.println(class1.hashCode()); // 705927765
		System.out.println(class2.hashCode()); // 705927765
		System.out.println(class3.hashCode()); // 705927765
		
		ClassLoader classLoader1 = class1.getClassLoader();
		
		System.out.println(classLoader1); // sun.misc.Launcher$AppClassLoader@73d16e93
		System.out.println(classLoader1.getParent()); // sun.misc.Launcher$ExtClassLoader@4e25154f
		System.out.println(classLoader1.getParent().getParent()); // null 这里是启动类加载器,用户根本就获取不到，所以返回的是null
	}
}
```

## 3.3.双亲委派机制

- 第一步：类加载器收到类加载的请求。
- 第二步：将这个请求向上委托给父类加载器去完成，一直向上委托，直到启动类加载器。
- 第三步：启动类加载器检查是否能够加载当前这个类，能加载就结束，使用当前加载器；否则，抛出异常，通知子加载器。
- 第四步：重复步骤三，一直找到不到就会抛出`ClassNotFoundException`异常。

# 4.JVM各区域

![JVM](https://img-blog.csdnimg.cn/20200722172211603.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

## 4.1.Native Method Stack

- **凡是带了`native`关键字的，说明java的作用范围达不到了，需要回去调用本地C语言的库。这些本地方法会进入到本地方法栈(Native Method Stack)，调用本地方法接口(Java Native Interface)，就可以调用本地方法库了！**
- **JNI的作用：扩展Java的使用，融合不同的编程语言为Java所用！最初是融合C、C++。Java诞生的时候C、C++横行，Java想要立足，必须要有调用C、C++的方法。**
- **在内存区域中专门开辟了一块标记区域：Native Method Stack，登记 native方法，最终执行的时候，通过JNI加载本地方法库中的方法。**
- **Java程序需要驱动硬件会调用本地方法，现在用的很少，掌握即可！**

## 4.2.Program Counter Register

程序计数器：Program Counter Register。

**==每个线程都有一个程序计数器，是线程私有的==**，就是一个指针，指针方法区中的方法字节（用来存储指向一条指令的地址，也即将要执行的指令代码），在执行引擎读取下一条指令，是一个非常小的内存空间，几乎可以忽略不计。

## 4.3.Method Area

Method Area 方法区

**==方法区是被所有线程 共享==**，所有字段和方法字节码，以及一些特殊的方法，如构造函数，接口代码也在此定义，简单说，所有定义的方法的信息都保存在该区域，**此区域属于共享区间**。

**==静态变量（static）、常量（final）、类信息（构造方法、接口定义）、运行时的常量池存在方法区中，但是实例变量存在堆内存中，和方法区无关。==**

**运行时常量池：**属于方法区一部分，用于存放编译期生成的各种字面量和符号引用。编译器和运行期(String 的 intern() )都可以将常量放入池中。内存有限，无法申请时抛出 OutOfMemoryError。

## 4.4.Stack

栈：栈内存，主管程序的运行，生命周期和线程同步；线程结束，栈内存也就释放，==**对于栈来说，不存在垃圾回收。**==

**栈中存放的数据：8大基本数据类型(byte、short、int、long、float、double、boolean、char)、对象引用、实例的方法。**

> 栈运行流程

```java
package com.ymy.jvm;
/**
 * 执行顺序：
 * main方法开始...
 * add()....
 * increment()....
 * test()....
 * main方法结束...
 * 
 * 执行流程：
 * 1.打印"main方法开始...",main()进栈;
 * 2.add()进栈,打印"add()....";
 * 3.increment()进栈,打印"increment()....";
 * 4.increment()执行完毕出栈;
 * 5.add()执行完毕出栈;
 * 6.test()进栈,打印"test()....";
 * 7.test()执行完毕出栈;
 * 8.打印"main方法结束...",main()出栈。
 */
public class Stack {
	public static void main(String[] args) {
		System.out.println("main方法开始...");
		
		Stack stack = new Stack();
		stack.add();
		stack.test();
		
		System.out.println("main方法结束...");
	}
	
	public void test() {
		System.out.println("test()....");
	}
	
	public void add() {
		System.out.println("add()....");
		this.increment();
	}
	
	public void increment() {
		System.out.println("increment()....");
	}
}
```

- 程序正在执行的方法，一定在栈的顶部，方法执行完毕之后，就会弹出栈。
- 栈满了就会有`StackOverflowError`。

## 4.5.Heap

对于绝大多数应用来说，这块区域是 JVM 所管理的内存中最大的一块。线程共享，主要是存放对象实例和数组。内部会划分出多个线程私有的分配缓冲区(Thread Local Allocation Buffer, TLAB)。可以位于物理上不连续的空间，但是逻辑上要连续。

`OutOfMemoryError`：如果堆中没有内存完成实例分配，并且堆也无法再扩展时，抛出该异常。



**JVM中的堆一般分为三大部分：新生代、老年代、元空间（逻辑存在），其大致的占比如下：**

![Heap](https://img-blog.csdnimg.cn/20200722184148151.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

### 4.5.1.新生代

​        新生代主要用来存放新生的对象。一般占据堆空间的`1/3`。在新生代中，保存着大量的刚刚创建的对象，但是大部分的对象都是朝生夕死，所以在新生代中会频繁的进行`MinorGC`，进行垃圾回收。新生代又细分为三个区：`Eden`区、`SurvivorFrom`、`ServivorTo`区，三个区的默认比例为：`8：1：1`。

- **Eden区：**Java新创建的对象绝大部分会分配在`Eden`区（如果对象太大，则直接分配到老年代）。当Eden区内存不够的时候，就会触发`MinorGC`（新生代采用的是==**复制算法**==），对新生代进行一次垃圾回收。

- **SurvivorFrom区和To区：**在`GC`开始的时候，对象只会存在于`Eden`区和名为`From`的`Survivor`区，`To`区是空的，一次`MinorGC`过后，`Eden`区和`From`区存活的对象会移动到`SurvivorTo`区中，然后会清空`Eden`区和`From`区，并对存活的对象的年龄+1，如果对象的年龄达到15，则直接分配到老年代。`MinorGC`完成后，`From`区和`To`区的功能进行互换**==(谁空是是To区)==**。下一次`MinorGC`时，会把`To`区和`Eden`区存活的对象放入`From`区中，并计算对象存活的年龄。

### 4.5.2.老年代

　　老年代主要存放应用中生命周期长的内存对象。老年代比较稳定，不会频繁的进行`MajorGC`。而在`MaiorGC`之前才会先进行一次`MinorGC`，使得新生的对象进入老年代而导致空间不够才会触发。当无法找到足够大的连续空间分配给新创建的较大对象也会提前触发一次`MajorGC`进行垃圾回收腾出空间。

　　在老年代中，`MajorGC`采用了==**标记—清除算法**==：首先扫描一次所有老年代里的对象，标记出存活的对象，然后回收没有标记的对象。`MajorGC`的耗时比较长。因为要扫描再回收。`MajorGC`会产生内存碎片，当老年代也没有内存分配给新来的对象的时候，就会抛出OOM（Out of Memory）异常。

### 4.5.3.元空间

　　永久代指的是永久保存区域。主要存放Class和Meta（元数据）的信息。Class在被加载的时候被放入永久区域，它和存放的实例的区域不同，在Java8中，永久代已经被移除，取而代之的是一个称之为“元数据区”（元空间）的区域。元空间和永久代类似，都是对JVM中规范中方法的实现。不过元空间与永久代之间最大的区别在于：元空间并不在虚拟机中，而是使用本地内存。因此，默认情况下，元空间的大小仅受本地内存的限制。类的元数据放入`native memory`，字符串池和类的静态变量放入java堆中。这样可以加载多少类的元数据就不再由`MaxPermSize`控制，而由系统的实际可用空间来控制。

采用元空间而不用永久代的原因：

- 为了解决永久代的OOM问题，元数据和`Class`对象存放在永久代中，容易出现性能问题和内存溢出。
- 类及方法的信息等比较难确定其大小，因此对于永久代大小指定比较困难，大小容易出现永久代溢出，太大容易导致老年代溢出（堆内存不变，此消彼长）。
- 永久代会为GC带来不必要的复杂度，并且回收效率偏低。

# 5.分析OOM原因

在一个项目中，突然出现了OOM故障，那么该如何排除错呢？？

- 能够看到第几行代码出错：内存快照分析工具，MAT，JProfiler。
- Debug，一行行分析代码。

**MAT，JProfiler的作用：**

- 分析Dump内存文件，快速定位内存定位问题。
- 获得堆中的数据。
- 获得大的对象。

> 使用JProfiler

1、`Idea`安装`JProfiler`插件

![JProfiler插件](https://img-blog.csdnimg.cn/20200722215849316.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)



2、下载**`JProfiler`** 软件

**官网网址：https://www.ej-technologies.com/download/jprofiler/version_92**



3、`Idea`安装`JProfiler`软件位置

![位置](https://img-blog.csdnimg.cn/20200723095956836.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)



4、加入虚拟机参数并测试代码

![虚拟机参数](https://img-blog.csdnimg.cn/20200723101436565.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)



5、在项目路径下会生成`java_pid15264.hprof`文件，使用`JProfilter`工具打开即可分析了。



- `-Xms`：设置初始化内存分配大小。
- `-Xmx`：设置最大分配内存，默认 1/64。
- `-XX:+PrintGC`：打印GC信息。
- `-XX:+HeapDumpOnOutOfMemoryError`：OOM DUMP。

# 6.GC

- JVM在进行GC时，并不是対这三个区域统一回收。大部分时候，回收都是在新生代。

- GC分为两种类型：`MinorGC`和`MajorGC/FullGC`。
- GC的算法：标记清除法、标记压缩算法、复制算法、引用计数法（用的少）。
- `MinorGC`和`FullGC`分别在什么时候发生？

## 6.1.引用计数法

![引用计数法](https://img-blog.csdnimg.cn/20200723103554674.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

每个对象在创建的时候，就给这个对象绑定一个计数器。每当有一个引用指向该对象时，计数器加一；每当有一个指向它的引用被删除时，计数器减一。这样，当没有引用指向该对象时，该对象死亡，计数器为0，这时就应该对这个对象进行垃圾回收操作。

- 优点：
  - 实时性较高，无需等到内存不够的时候，才开始回收，运行时根据对象的计数器是否为0，就可以直接回收。
  -  在垃圾回收过程中，应用无需挂起。
  - 如果申请内存时，内存不足，则立刻报outofmember 错误。
  - 区域性，更新对象的计数器时，只是影响到该对象，不会扫描全部对象。
-  缺点： 
  - 每次对象被引用时，都需要去更新计数器，有一点时间开销。
  - 浪费CPU资源，即使内存够用，仍然在运行时进行计数器的统计。 
  - 无法解决循环引用问题。（最大的缺点）。

## 6.2.复制算法

　　HotSpot 把新生代分为三个部分：Eden区和两个Survivor区（From区和To区），默认比例8:1:1。对象创建时会被放在Eden区，当Eden区触发GC（Minor GC），GC会对Eden和Survivor区进行垃圾回收，幸存下来的独享会被 “复制” 到Survivor1区（To区），然后清空Eden和From区，最后将To和From交换，让刚才被清空的From作新的To区，让刚才保存对象的To区作新的From区，以保证下一次GC可以扫描到这些对象。这个过程中涉及到了一个 “复制” 的操作，就是 “复制算法” 的实现。顺带一提：当一个对象在多次GC后依然无法被回收，在From区和To区来回复制，每复制一次“年龄”加1，==**一旦“年龄”达到MaxTenuringThreshold的值（默认为15）就会被移动到老年代**==。

　　为了方便描述，这里将minor GC的扫描区域（Eden、From）简称为From区，因为这两块区域的共同特点就是在复制幸存对象到To区后会被清空，唯一的区别就是Eden用来保存第一次new出来的对象，而From区保存的则是经过若干次GC后任然幸存的对象。

![复制算法流程](https://img-blog.csdnimg.cn/2020072311104522.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

　　红色为幸存对象，黄色为被GC回收的对象，绿色表示闲置空间。当触发GC后，Eden区和From区的幸存对象会被复制到To区，然后清空Eden区和From区，最后将From区和To区对调，以保证下一次GC的正常工作流程。需要补充的是 “复制算法” 的优缺点：

- 优点：由于“复制算法”采用了==**复制—清空**==的方法，所以不会导致内存空间的碎片化。

- 缺点：
  - 由于复制算法需要另外的空间来 “周转” 这些幸存的对象，所以内存消耗比较大。
  - 如果存在“极端情况”，比如大量的对象循环引用而导致无法回收的幸存对象占比很大，假设为80%，那么就需要将这些数量庞大的对象都复制一遍，并将所有的引用地址重置一遍，这回耗费比较多的时间。**==所以复制算法的最佳工作环境就是这一块的对象存活率比较低，所以在新生代中采用了这一算法。==**

## 6.3.标记清除算法

**标记-清除**的做法是当堆中的有效内存空间（available memory）被耗尽的时候，就会停止整个程序（也被成为**stop the world**），然后进行两项工作，第一项则是标记，第二项则是清除。

**标记：标记的过程其实就是，遍历所有的GC Roots，然后将所有GC Roots可达的对象标记为存活的对象。**

**清除：清除的过程将遍历堆中所有的对象，将没有标记的对象全部清除掉。**

**一句话：就是当程序运行期间，若可以使用的内存被耗尽的时候，GC线程就会被触发并将程序暂停，随后将依旧存活的对象标记一遍，最终再将堆中所有没被标记的对象全部清除掉，接下来便让程序恢复运行。**

> 标记-清除算法图示

<img src="https://img-blog.csdnimg.cn/20200723121048827.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70" alt="未标记" style="zoom:150%;" />

这张图代表的是程序运行期间所有对象的状态，**它们的标志位全部是0**（也就是未标记，以下默认0就是未标记，1为已标记），假设这会儿**有效内存空间耗尽**了，JVM将会停止应用程序的运行并开启GC线程，然后开始进行标记工作，按照根搜索算法，标记完以后，对象的状态如下图。

<img src="https://img-blog.csdnimg.cn/20200723121550578.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70" alt="标记" style="zoom:150%;" />

可以看到，按照根搜索算法，所有从root对象可达的对象就被标记为了存活的对象，此时已经完成了第一阶段标记。接下来，就要执行第二阶段清除了，那么清除完以后，剩下的对象以及对象的状态如下图所示。

<img src="https://img-blog.csdnimg.cn/20200723122359462.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70" alt="清除" style="zoom:150%;" />

优点：不需要额外的空间。

标记—清除算法缺点：

- 效率不算高。
- 在进行GC的时候,需要停止整个应用程序,导致用户体验差。
- 这种方式清理出来的空闲内存是不连续的,产生内存碎片。需要维护一个空闲列表。

**注意:何为清除?**

这里所谓的清除并不是真的置空,而是把需要清除的对象地址保存在空闲的地址列表里。下次有新对象需要加载时,判断垃圾的位置空间是否够,如果够,就存放。

## 6.4.标记清除压缩算法

![标记—清除—压缩](https://img-blog.csdnimg.cn/20200723123710525.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)



标记压缩其实就是在标记清除后加了一个 “压缩” 操作，将分散的数据压缩到一块连续的内存空间。就是慢，但慢工出细活。

## 6.5.总结

　　针对老年的GC，标记清除和标记压缩都不完美，最好的方式是组合使用，在多次使用标记清除后进行一次压缩。**==总的来说四种方式没有孰优孰劣，只有谁更合适==**。总结一下就是：

执行效率（算法的时间复杂度）：复制算法>标记清除>标记压缩

内存整齐度：复制算法=标记压缩>标记清除

内存利用率：标记清除=标记压缩>复制算法

　　在Java9默认采用了G1垃圾回收器，采用了时间复杂度和空间利用率都非常出色的算法。