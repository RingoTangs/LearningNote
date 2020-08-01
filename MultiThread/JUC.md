# [目录]

- [1.基础回顾](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#1%E5%9F%BA%E7%A1%80%E5%9B%9E%E9%A1%BE)

  - [1.1.什么是JUC？](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#11%E4%BB%80%E4%B9%88%E6%98%AFjuc)
  - [1.2.线程和进程？](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#12%E7%BA%BF%E7%A8%8B%E5%92%8C%E8%BF%9B%E7%A8%8B)
  - [1.3.并发和并行](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#13%E5%B9%B6%E5%8F%91%E5%92%8C%E5%B9%B6%E8%A1%8C)
  - [1.4.线程有几个状态？](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#14%E7%BA%BF%E7%A8%8B%E6%9C%89%E5%87%A0%E4%B8%AA%E7%8A%B6%E6%80%81)
  - [1.5.wait和sleep的区别](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#15wait%E5%92%8Csleep%E7%9A%84%E5%8C%BA%E5%88%AB)

- [2.Lock锁](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#2lock%E9%94%81)

  - [2.1.Synchronized](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#21synchronized)
  - [2.2.公平锁和非公平锁](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#22%E5%85%AC%E5%B9%B3%E9%94%81%E5%92%8C%E9%9D%9E%E5%85%AC%E5%B9%B3%E9%94%81)
  - [2.3.Lock锁](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#23lock%E9%94%81)

- [3.生产者和消费者问题](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#3%E7%94%9F%E4%BA%A7%E8%80%85%E5%92%8C%E6%B6%88%E8%B4%B9%E8%80%85%E9%97%AE%E9%A2%98)

  - [3.1.防止线程的虚假唤醒](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#31%E9%98%B2%E6%AD%A2%E7%BA%BF%E7%A8%8B%E7%9A%84%E8%99%9A%E5%81%87%E5%94%A4%E9%86%92)
  - [3.2.Synchronized版本](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#32synchronized%E7%89%88%E6%9C%AC)
  - [3.3.Lock版本精准通知](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#33lock%E7%89%88%E6%9C%AC%E7%B2%BE%E5%87%86%E9%80%9A%E7%9F%A5)

- [4.集合类不安全](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#4%E9%9B%86%E5%90%88%E7%B1%BB%E4%B8%8D%E5%AE%89%E5%85%A8)

  - [4.1.List](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#41list)
  - [4.2.Set](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#42set)
  - [4.3.Map](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#43map)

- [5.Callable](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#5callable)

- [6.常用辅助类](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#6%E5%B8%B8%E7%94%A8%E8%BE%85%E5%8A%A9%E7%B1%BB)

  - [6.1.CountDownLatch](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#61countdownlatch)
  - [6.2.CyclicBarrier](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#62cyclicbarrier)
  - [6.3.Semaphore](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#63semaphore)

- [7.读写锁](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#7%E8%AF%BB%E5%86%99%E9%94%81)

- [8.阻塞队列](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#8%E9%98%BB%E5%A1%9E%E9%98%9F%E5%88%97)

  - [8.1.BlockingQueue](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#81blockingqueue)
  - [8.2.SynchronousQueue](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#82synchronousqueue)

- [9.线程池](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#9%E7%BA%BF%E7%A8%8B%E6%B1%A0)

  - [9.1.线程池介绍](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#91%E7%BA%BF%E7%A8%8B%E6%B1%A0%E4%BB%8B%E7%BB%8D)
  - [9.2.三大方法](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#92%E4%B8%89%E5%A4%A7%E6%96%B9%E6%B3%95)
  - [9.3.七大参数](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#93%E4%B8%83%E5%A4%A7%E5%8F%82%E6%95%B0)
  - [9.4.四种拒绝策略](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#94%E5%9B%9B%E7%A7%8D%E6%8B%92%E7%BB%9D%E7%AD%96%E7%95%A5)

  - [9.5.小结](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#95%E5%B0%8F%E7%BB%93)

- [10.四大函数式接口](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#10%E5%9B%9B%E5%A4%A7%E5%87%BD%E6%95%B0%E5%BC%8F%E6%8E%A5%E5%8F%A3)

  - [10.1.函数式接口](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#101%E5%87%BD%E6%95%B0%E5%BC%8F%E6%8E%A5%E5%8F%A3)
  - [10.2.断定型接口](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#102%E6%96%AD%E5%AE%9A%E5%9E%8B%E6%8E%A5%E5%8F%A3)
  - [10.3.消费型接口](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#103%E6%B6%88%E8%B4%B9%E5%9E%8B%E6%8E%A5%E5%8F%A3)
  - [10.4.供给型接口](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#104%E4%BE%9B%E7%BB%99%E5%9E%8B%E6%8E%A5%E5%8F%A3)

- [11.Stream流式计算](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#11stream%E6%B5%81%E5%BC%8F%E8%AE%A1%E7%AE%97)

- [12.ForkJoin](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#12forkjoin)

  - [12.1.什么是ForkJoin？](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#121%E4%BB%80%E4%B9%88%E6%98%AFforkjoin)
  - [12.2.ForkJoin的使用](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#122forkjoin%E7%9A%84%E4%BD%BF%E7%94%A8)

- [13.异步回调](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#13%E5%BC%82%E6%AD%A5%E5%9B%9E%E8%B0%83)

- [14.JMM](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#14jmm)

- [15.volatile](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#15volatile)

- [16.单例模式](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#16%E5%8D%95%E4%BE%8B%E6%A8%A1%E5%BC%8F)

- [17.CAS](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#17cas)

- [18.原子引用ABA问题](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#18%E5%8E%9F%E5%AD%90%E5%BC%95%E7%94%A8aba%E9%97%AE%E9%A2%98)

- [19.各种锁的理解](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#19%E5%90%84%E7%A7%8D%E9%94%81%E7%9A%84%E7%90%86%E8%A7%A3)

  - [19.1.公平锁和非公平锁](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#191%E5%85%AC%E5%B9%B3%E9%94%81%E5%92%8C%E9%9D%9E%E5%85%AC%E5%B9%B3%E9%94%81)
  - [19.2.可重入锁](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#192%E5%8F%AF%E9%87%8D%E5%85%A5%E9%94%81)

- [20.死锁排查](https://github.com/RingoTangs/LearningNote/blob/master/MultiThread/JUC.md#20%E6%AD%BB%E9%94%81%E6%8E%92%E6%9F%A5)

# 1.基础回顾

## 1.1.什么是JUC？

![什么是JUC](https://img-blog.csdnimg.cn/2020072313134863.png)

`java.util`工具包。

## 1.2.线程和进程？

> 进程和线程

**进程：**一个程序，QQ.exe Music.exe 程序的集合。任务管理里面可以看到进程。一个进程往往可以包含多个线程。

**线程**：开了一个进程Typora，写字，自动保存（线程负责的）。Java默认有2个线程，main线程和GC守护线程。

对于Java而言，`Thread`、`Runnable`、`Callable`可以创建线程。

**Java真的可以开启线程吗？？开不了！！!**

```java
public synchronized void start() {
    /**
         * This method is not invoked for the main method thread or "system"
         * group threads created/set up by the VM. Any new functionality added
         * to this method in the future may have to also be added to the VM.
         *
         * A zero status value corresponds to state "NEW".
         */
    if (threadStatus != 0)
        throw new IllegalThreadStateException();

    /* Notify the group that this thread is about to be started
         * so that it can be added to the group's list of threads
         * and the group's unstarted count can be decremented. */
    group.add(this);

    boolean started = false;
    try {
        start0();
        started = true;
    } finally {
        try {
            if (!started) {
                group.threadStartFailed(this);
            }
        } catch (Throwable ignore) {
            /* do nothing. If start0 threw a Throwable then
                  it will be passed up the call stack */
        }
    }
}

// 本地方法，调用Java Native Interface
private native void start0();
```

## 1.3.并发和并行

**并发：**多线程操作同一个资源。单核CPU，模拟出来多条线程，CPU的快速交替。

**并行：**多个人一起行走。多核CPU，多个线程可以同时执行。可以使用线程池操作。

```java
// 获取CPU的内核数量
System.out.println(Runtime.getRuntime().availableProcessors());
```

**并发编程的本质：充分利用CPU的资源。**

## 1.4.线程有几个状态？

```java
public enum State {

    // 新建线程
    NEW,

    // 运行
    RUNNABLE,

    // 阻塞
    BLOCKED,

    // 等待，死等
    WAITING,

    // 超时等待，过时不候
    TIMED_WAITING,

    // 终止
    TERMINATED;
}
```

## 1.5.wait和sleep的区别

**1、来自不同的类**

wait => Object

sleep => Thread

**2、关于锁的释放**

wait 会释放锁，sleep 睡觉了，抱着锁睡觉了，不会释放！

**3、使用的范围是不同的**

wait必须在同步代码块中使用。

sleep可以在任何地方使用。

# 2.Lock锁

## 2.1.Synchronized

```java
/**
 * 真正的多线程开发，公司中的开发
 * 线程就是一个单独的资源类，没有任何附属的操作！
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        // 并发：多个线程操作同一个资源类
        Ticket ticket = new Ticket();
		
        // 创建多个线程
        new Thread(() -> {
            for (int i = 1; i <= 40; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 40; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 40; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

// 资源类
class Ticket {
    // 属性
    private Integer number = 30;

    // 买票的方法
    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "\t卖出了第" + (number--) + "张票,剩余" + number + "张票");
        }
    }
}
```

## 2.2.公平锁和非公平锁

```java
// ReentrantLock构造方法可以传布尔值，默认是非公平锁
public ReentrantLock(boolean fair) {
    sync = fair ? new FairSync() : new NonfairSync();
}
```

公平锁：十分公平，先来后到。

非公平锁：十分不公平，可以插队，CPU调度到就可以插队（默认使用非公平锁）。

## 2.3.Lock锁

```java
package com.ymy.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 真正的多线程开发，公司中的开发
 * 线程就是一个单独的资源类，没有任何附属的操作！
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        // 并发：多个线程操作同一个资源类
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 1; i <= 40; i++) ticket.sale();
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 40; i++) ticket.sale();
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 40; i++) ticket.sale();
        }, "C").start();
    }
}

/**
 * Lock三部曲
 * 1.创建锁Lock lock = new ReentrantLock();
 * 2.加锁 lock.lock();
 * 3.释放锁 lock.unlock();
 */
class Ticket {
    // 属性
    private Integer number = 30;
    // 第一步：创建锁
    Lock lock = new ReentrantLock();

    // 买票的方法
    public void sale() {
        try {
            // 第二步：加锁
            lock.lock();
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t剩余" + number + "张票");
            }
        } finally {
            // 第三步：释放锁
            lock.unlock();
        }
    }
}
```

> Synchronized和Lock的区别

1、Synchronized是内置的Java关键字，Lock是一个Java类。

2、Synchronized无法判断锁的状态，Lock可以判断是否获取到了锁。

3、Synchronized会自动释放锁。Lock需要手动释放锁，如果不释放锁，就会**死锁**。

4、Synchronized 线程1（获得锁，阻塞），线程2（等待，傻傻的等）；Lock锁就可能不会死等。

5、Synchronized 可重入锁，不可以中断，非公平锁；Lock 可重入锁，可以中断锁，非公平（可以自己设置）。

6、Synchronized 适合锁少量的代码同步问题；Lock 适合锁大量的同步代码。

# 3.生产者和消费者问题

## 3.1.防止线程的虚假唤醒

![虚假唤醒](https://img-blog.csdnimg.cn/20200723235731725.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

## 3.2.Synchronized版本

```java
package com.ymy.demo01;

/**
 * 线程之间的通信问题：生产者和消费者问题！
 * 题目：A,B线程交替执行,操作同一个变量 num = 0
 * A num + 1
 * B num - 1
 */
public class Test02 {

    public static void main(String[] args) {
        // 创建资源类
        Resource1 resource1 = new Resource1();
        // 创建 A 线程
        new Thread(() -> {
            try {
                for (int i = 1; i < 20; i++) {
                    resource1.increment();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        // 创建 B 线程
        new Thread(() -> {
            try {
                for (int i = 1; i < 20; i++) {
                    resource1.decrement();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}

class Resource1 {

    private Integer num = 0;

    public synchronized void increment() throws Exception {
        // 这里一定要用while防止虚假唤醒
        while (num != 0) {
            this.wait();
        }
        num++;
        System.out.println("[" + Thread.currentThread().getName() + "] \t" + num);
        // 唤醒其他线程
        this.notify();
    }

    public synchronized void decrement() throws Exception {
        while (num == 0) {
            this.wait();
        }
        num--;
        System.out.println("[" + Thread.currentThread().getName() + "] \t" + num);
        // 唤醒其他线程
        this.notify();
    }
}
```

## 3.3.Lock版本精准通知

```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：
 * A线程打印一句话,打印1次;
 * B线程打印一句话,打印2次;
 * C线程打印一句话,打印3次;
 * 按顺序执行,执行5轮。
 */
public class Test03 {
    public static void main(String[] args) {
        Resource2 resource2 = new Resource2();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    resource2.print1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    resource2.print2();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    resource2.print3();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
    }
}

class Resource2 {
    // 1代表A线程 2代表B线程 3代表C线程
    int flag = 1;

    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void print1() throws Exception {
        // 添加锁
        lock.lock();
        try {
            // 防止虚假唤醒
            while (flag != 1) {
                // A线程等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "\t打印第[" + "1]次");
            // 修改标志位
            flag = 2;
            // 唤醒B线程
            condition2.signal();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public void print2() throws Exception {
        lock.lock();
        try {
            while (flag != 2) {
                condition2.await();
            }
            for (int i = 1; i <= 2; i++) {
                System.out.println(Thread.currentThread().getName() + "\t打印第[" + i + "]次");
            }
            flag = 3;
            condition3.signal();
        } finally {
            lock.unlock();
        }

    }

    public void print3() throws Exception {
        lock.lock();
        try {
            while (flag != 3) {
                condition3.await();
            }
            for (int i = 1; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName() + "\t打印第[" + i + "]次");
            }
            flag = 1;
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }
}
```

# 4.集合类不安全

## 4.1.List

> 测试List集合不安全

```java
public class ListTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            final int temp = i;
            new Thread(() -> {
                list.add(temp);
            }).start();
            new Thread(() -> {
                System.out.println(list);
            }).start();
        }
    }
}

// Exception in thread "Thread-107" java.util.ConcurrentModificationException
// 会报并发修改异常
```

> 解决方案

- `Vector()`。
- `Collections.synchronizedList()`。使用集合工具类。
- `CopyOnWriteArrayList`。写时复制，读写分离。

```java
// CopyOnWriteArrayList 的add()方法
public boolean add(E e) {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        Object[] elements = getArray();
        int len = elements.length;
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = e;
        setArray(newElements);
        return true;
    } finally {
        lock.unlock();
    }
}
```

## 4.2.Set

> 测试Set集合不安全

```java
public class TestSet {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= 20; i++) {
            final int temp = i;
            new Thread(() -> {
                set.add(temp);
            }).start();
            new Thread(() -> System.out.println(set)).start();
        }
    }
}

// java.util.ConcurrentModificationException
```

> 解决方案

- `Collections.synchronizedSet`。
- `CopyOnWriteArraySet`。

> HashSet底层是什么？

```java
// HashSet底层就是HashMap
public HashSet() {
map = new HashMap<>();
}

// add()方法是在map中添加k-v
public boolean add(E e) {
    return map.put(e, PRESENT)==null;
}

// Dummy value to associate with an Object in the backing Map
private static final Object PRESENT = new Object();
```

## 4.3.Map

> 测试Map不安全

```java
public class MapTest {
    public static void main(String[] args) {
        Map<Integer, Object> map = new HashMap<>();
        for (int i = 1; i <= 100; i++) {
            final int temp = i;
            new Thread(() -> {
                map.put(temp, temp);
            }).start();

            new Thread(() -> System.out.println(map)).start();
        }
    }
}
// java.util.ConcurrentModificationException
```

> 解决方案

- `ConcurrentHashMap`。

**ConcurrentHashMap相关知识：https://www.cnblogs.com/huangjuncong/p/9478505.html**

# 5.Callable

![callable](https://img-blog.csdnimg.cn/2020072412263090.png)

- 可以有返回值。
- 可以抛出异常。

> 代码测试

```java
public class CallableTest {
    public static void main(String[] args) throws Exception {
        FutureTask futureTask = new FutureTask(() -> {
            System.out.println("Callable...");
            return "123";
        });

        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start(); // 结果会被缓存，只会打印一次

        // 等待计算完成，然后检索其结果。
        // 这个get()可能会产生阻塞！把它放到最后
        System.out.println(futureTask.get());
    }
}
```

- 有缓存！
- 结果可能需要等待，会阻塞！

# 6.常用辅助类

## 6.1.CountDownLatch

![CountDownLatch](https://img-blog.csdnimg.cn/20200724125227419.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

> 测试代码

```java
/**
 * 案例：
 * 教室中6名学生,当6名学生全部出教室后,教室的门才会关闭,
 * 不关心这6名学生出教室的先后顺序。
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t Go Out");
                countDownLatch.countDown(); // 计数器减1操作
            }, String.valueOf(i)).start();
        }
        countDownLatch.await(); // 等待计数器归0
        System.out.println("Close Door!");
    }
}
```

## 6.2.CyclicBarrier

> 测试代码

```java
/**
 * 案例：
 * 7个领导要开会,必须人来齐了才能开始。
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws Exception {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            // 只要够了7个人立马开会
            System.out.println("开会！");
        });
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "\t 来了");
                    cyclicBarrier.await(); // 人到了,但是不够7个个人就必须等待。
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
```

## 6.3.Semaphore

> 测试代码

```java
/**
 * 案例：
 * 6辆车抢3个车位,只有抢到车位的车释放了之后,后面的车才能抢到。
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 模拟有3个停车位
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    // 获得一个停车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到车位");
                    TimeUnit.SECONDS.sleep(5);
                    
                    System.out.println(Thread.currentThread().getName() + "\t释放车位");
                    // 释放车位
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
```

# 7.读写锁

`ReadWriteLock`读写锁，一个用于只读操作，一个用于写入。

> 测试代码

```java
package com.ymy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁(写锁)：一次只能被一个线程写。
 * 共享锁(读锁)：多个线程可以同时读。
 * ReadWriteLock
 * 读-读 可以共存
 * 读-写 不能共存
 * 写-写 可以共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        // 写
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.write(String.valueOf(temp), temp);
            }).start();
        }

        // 读
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.read(String.valueOf(temp));
            }).start();
        }


    }
}

class MyCache {
    private volatile Map<String, Object> cache = new HashMap<>();

    // 创建读写锁
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 写入 写锁 只有一个线程独占
    public void write(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println("[" + Thread.currentThread().getName() + "]开始写入....." + "\t" + key);
            cache.put(key, value);
            System.out.println("[" + Thread.currentThread().getName() + "]写入OK.....");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    // 读取 读锁 所有线程都可以读
    public void read(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println("[" + Thread.currentThread().getName() + "]开始读.....");
            Object ret = cache.get(key);
            System.out.println("[" + Thread.currentThread().getName() + "]读取OK....." + "\t" + ret);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
```

# 8.阻塞队列

## 8.1.BlockingQueue

> 阻塞和队列

<img src="https://img-blog.csdnimg.cn/20200725134953293.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70" alt="阻塞队列" style="zoom:150%;" />

> 阻塞队列UML图

![BlockQueue](https://img-blog.csdnimg.cn/20200725140543473.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

**什么情况下会使用阻塞队列：多线程并发处理，线程池！**

> 阻塞队列四组API

| 方式         | 抛出异常  | 有返回值，不抛出异常 | 阻塞等待 | 超时等待 |
| ------------ | --------- | -------------------- | -------- | -------- |
| 添加         | add()     | offer()              | put()    | offer()  |
| 移除         | remove()  | poll()               | take()   | poll()   |
| 查看队首元素 | element() | peek()               |          |          |

```java
/**
* 抛出异常
*/
public static void test1() {
    // 阻塞队列的大小为3
    BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
    // 添加
    System.out.println(blockingQueue.add(1)); // true
    System.out.println(blockingQueue.add(2)); // true
    System.out.println(blockingQueue.add(3)); // true
    // Exception in thread "main" java.lang.IllegalStateException: Queue full
    //System.out.println(blockingQueue.add(4));
    
    System.out.println(blockingQueue.element()); // 查看队首元素
    System.out.println("**************************************************");

    // 移除
    System.out.println(blockingQueue.remove()); // 1
    System.out.println(blockingQueue.remove()); // 2
    System.out.println(blockingQueue.remove()); // 3

    // Exception in thread "main" java.util.NoSuchElementException
    //System.out.println(blockingQueue.remove());
}
```

```java
/**
* 不抛出异常,有返回值
*/
public static void test2() {
    // 阻塞队列的大小为3
    BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
    // 添加
    System.out.println(blockingQueue.offer(1)); // true
    System.out.println(blockingQueue.offer(2)); // true
    System.out.println(blockingQueue.offer(3)); // true
    System.out.println(blockingQueue.offer(4)); // false

    System.out.println("****************************************");

    // 移除
    System.out.println(blockingQueue.poll()); // 1
    System.out.println(blockingQueue.poll()); // 2
    System.out.println(blockingQueue.peek()); // 查看队首元素 3
    System.out.println(blockingQueue.poll()); // 3
    System.out.println(blockingQueue.poll()); // null
}
```

```java
/**
* 阻塞等待（一直阻塞）
*/
public static void test3() throws InterruptedException {
    // 阻塞队列的大小为3
    BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);

    // 添加
    blockingQueue.put(1);
    blockingQueue.put(2);
    blockingQueue.put(3);
    //blockingQueue.put(4);  // 会一直阻塞等待

    System.out.println(blockingQueue.take()); // 1
    System.out.println(blockingQueue.take()); // 2
    System.out.println(blockingQueue.take()); // 3
    System.out.println(blockingQueue.take()); // 一直阻塞等待
}
```

```java
/**
* 超时等待（过时不候）
*/
public static void test4() throws InterruptedException {
    // 阻塞队列的大小为3
    BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);

    // 添加
    System.out.println(blockingQueue.offer(1, 5, TimeUnit.SECONDS));
    System.out.println(blockingQueue.offer(2, 5, TimeUnit.SECONDS));
    System.out.println(blockingQueue.offer(3, 5, TimeUnit.SECONDS));

    // 阻塞队列满了 等5s,如果还在阻塞就放弃本次添加
    System.out.println(blockingQueue.offer(4, 5, TimeUnit.SECONDS));

    // 移除
    System.out.println(blockingQueue.poll(5, TimeUnit.SECONDS));
    System.out.println(blockingQueue.poll(5, TimeUnit.SECONDS));
    System.out.println(blockingQueue.poll(5, TimeUnit.SECONDS));

    // 阻塞队列空了 等5S,如果还在阻塞就放弃本次移除
    System.out.println(blockingQueue.poll(5, TimeUnit.SECONDS));
}
```

## 8.2.SynchronousQueue

`SynchronousQueue`同步队列，进去一个元素，必须等待取出来之后，才能往里面再放一个元素。**最多只能放一个元素！**

```java
package com.ymy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * T1线程向同步队列中放元素,必须等T2线程取出元素,T1线程才能继续放元素。
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> synchronousQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                synchronousQueue.put(1);
                System.out.println(Thread.currentThread().getName() + "\t put 2");
                synchronousQueue.put(2);
                System.out.println(Thread.currentThread().getName() + "\t put 3");
                synchronousQueue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t take \t" + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t take \t" + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t take \t" + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }
}
```

# 9.线程池

## 9.1.线程池介绍

在一个应用程序中，我们需要多次使用线程，也就意味着，我们需要多次创建并销毁线程。而创建并销毁线程的过程势必会消耗内存。而在Java中，内存资源是及其宝贵的，所以，我们就提出了线程池的概念。

**线程池的好处：**

- **降低系统资源消耗。**通过重用已存在的线程，降低线程创建和销毁造成的消耗。
- **提高响应速度。**当有任务到达时，通过复用已存在的线程，无需等待新线程的创建便能立即执行。
- **方便线程并发数的管控。**

## 9.2.三大方法

![阿里巴巴开发手册](https://img-blog.csdnimg.cn/20200726123940389.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

```java
package com.ymy.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors 工具类 三大方法
 * 1.一池一线程
 * Executors.newSingleThreadExecutor();
 * <p>
 * 2. 一池固定线程
 * Executors.newFixedThreadPool(5);
 * <p>
 * 3. 一池多线程(可伸缩的,遇强则强,遇弱则弱)
 * Executors.newCachedThreadPool();
 */
public class Demo01 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for (int i = 1; i <= 1000; i++) {
                // 使用了线程池之后,使用线程池来创建线程！
                threadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
            }
        } finally {
            // 程序结束要关闭线程池
            threadPool.shutdown();
        }
    }
}
```

## 9.3.七大参数

**创建线程池的底层使用的是ThreadPoolExecutor**

```java
// SingleThreadPool 一池一线程
public static ExecutorService newSingleThreadExecutor() {
    return new FinalizableDelegatedExecutorService
        (new ThreadPoolExecutor(1, 1,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>()));
}
```

```java
// FixedThreadPool 一池固定线程
public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS,
                                  new LinkedBlockingQueue<Runnable>());
}
```

```java
// CachedThreadPool 一池多线程(线程数量可以伸缩)
public static ExecutorService newCachedThreadPool() {
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,
                                  new SynchronousQueue<Runnable>());
}
```

> ThreadPoolExecutor

```java
public ThreadPoolExecutor(int corePoolSize,  // 核心线程池大小
                          int maximumPoolSize, // 最大核心线程池大小
                          long keepAliveTime, // 超时了,没有人调用就会释放
                          TimeUnit unit, // 超时单位
                          BlockingQueue<Runnable> workQueue, // 阻塞队列
                          ThreadFactory threadFactory,  // 线程工厂，创建线程，一般不动
                          RejectedExecutionHandler handler // 拒绝策略) {
    if (corePoolSize < 0 ||
        maximumPoolSize <= 0 ||
        maximumPoolSize < corePoolSize ||
        keepAliveTime < 0)
        throw new IllegalArgumentException();
    if (workQueue == null || threadFactory == null || handler == null)
        throw new NullPointerException();
    this.acc = System.getSecurityManager() == null ?
        null :
    AccessController.getContext();
    this.corePoolSize = corePoolSize;
    this.maximumPoolSize = maximumPoolSize;
    this.workQueue = workQueue;
    this.keepAliveTime = unit.toNanos(keepAliveTime);
    this.threadFactory = threadFactory;
    this.handler = handler;
}
```

> 银行办理业务模拟线程池

![模拟线程池](https://img-blog.csdnimg.cn/20200726132405853.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

> 手动创建线程池

```java
new ThreadPoolExecutor(5, 10, 5L, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
```

## 9.4.四种拒绝策略

**1、ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。**

```java
    /**
     * A handler for rejected tasks that throws a
     * {@code RejectedExecutionException}.
     */
    public static class AbortPolicy implements RejectedExecutionHandler {
        /**
         * Creates an {@code AbortPolicy}.
         */
        public AbortPolicy() { }

        /**
         * Always throws RejectedExecutionException.
         *
         * @param r the runnable task requested to be executed
         * @param e the executor attempting to execute this task
         * @throws RejectedExecutionException always
         */
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            throw new RejectedExecutionException("Task " + r.toString() +
                                                 " rejected from " +
                                                 e.toString());
        }
    }
```

**2、ThreadPoolExecutor.DiscardPolicy：丢弃任务，但是不抛出异常。**

```java
    /**
     * A handler for rejected tasks that silently discards the
     * rejected task.
     */
    public static class DiscardPolicy implements RejectedExecutionHandler {
        /**
         * Creates a {@code DiscardPolicy}.
         */
        public DiscardPolicy() { }

        /**
         * Does nothing, which has the effect of discarding task r.
         *
         * @param r the runnable task requested to be executed
         * @param e the executor attempting to execute this task
         */
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        }
    }
```

**3、ThreadPoolExecutor.CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务**

```java
    /**
     * A handler for rejected tasks that runs the rejected task
     * directly in the calling thread of the {@code execute} method,
     * unless the executor has been shut down, in which case the task
     * is discarded.
     */
    public static class CallerRunsPolicy implements RejectedExecutionHandler {
        /**
         * Creates a {@code CallerRunsPolicy}.
         */
        public CallerRunsPolicy() { }

        /**
         * Executes task r in the caller's thread, unless the executor
         * has been shut down, in which case the task is discarded.
         *
         * @param r the runnable task requested to be executed
         * @param e the executor attempting to execute this task
         */
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (!e.isShutdown()) {
                r.run();
            }
        }
    }
```

**4、ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新提交被拒绝的任务**

```java
    /**
     * A handler for rejected tasks that discards the oldest unhandled
     * request and then retries {@code execute}, unless the executor
     * is shut down, in which case the task is discarded.
     */
    public static class DiscardOldestPolicy implements RejectedExecutionHandler {
        /**
         * Creates a {@code DiscardOldestPolicy} for the given executor.
         */
        public DiscardOldestPolicy() { }

        /**
         * Obtains and ignores the next task that the executor
         * would otherwise execute, if one is immediately available,
         * and then retries execution of task r, unless the executor
         * is shut down, in which case task r is instead discarded.
         *
         * @param r the runnable task requested to be executed
         * @param e the executor attempting to execute this task
         */
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (!e.isShutdown()) {
                e.getQueue().poll();
                e.execute(r);
            }
        }
    }
```

## 9.5.小结

```java
最大线程到底该如何定义：
1、CPU密集型，电脑是几核，就是几，可以保证CPU的效率最高。   
// 获得处理器的核数
Runtime.getRuntime().availableProcessors()

2、IO密集型，> 判断程序中十分耗IO的线程有多少个
```

# 10.四大函数式接口

## 10.1.函数式接口

> 函数式接口：只有一个方法的接口

```java
@FunctionalInterface
public interface Runnable {
    public abstract void run();
}
// @FunctionalInterface 指明这是函数式接口
// 函数式接口可以简化代码
```

> 代码测试

```java
// Function接口
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
```

```java
/**
 * Function 函数型接口，有输入参数，有输出。
 * 只要是函数型接口，可以用lambda表达式简写。
 */
public class Demo01 {
    public static void main(String[] args) {
        Function<String, String> function = str -> str;
        System.out.println(function.apply("Tangs"));
    }
}
```

## 10.2.断定型接口

> 断定型接口：有一个输入参数,返回值为布尔值。

```java
@FunctionalInterface
public interface Predicate<T> {
 
    boolean test(T t);
}
```

> 代码测试

```java
/**
 * 断定型接口：有一个输入参数,返回值为布尔值。
 */
public class Demo02 {
    public static void main(String[] args) {
        // 判断字符串是否为空
        Predicate<String> predicate = str -> {
            return str.isEmpty();
        };
        String name = "";
        System.out.println(predicate.test(name)); // true 字符串为空
    }
}
```

## 10.3.消费型接口

> 消费型接口：只有输入参数，没有返回值

```java
@FunctionalInterface
public interface Consumer<T> {


    void accept(T t);
}
```

> 代码测试

```java
/**
 * 消费型接口：只有输入参数，没有返回值。
 */
public class Demo03 {
    public static void main(String[] args) {
        Consumer<String> consumer = str -> System.out.println(str);
        consumer.accept("Tangs");
    }
}
```

## 10.4.供给型接口

> 供给型接口：没有输入参数，只有返回值。

```java
@FunctionalInterface
public interface Supplier<T> {
   
    T get();
}
```

> 代码测试

```java
/**
 * 供给型接口：没有输入参数,只有返回值！
 */
public class Demo4 {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "Tangs";
        System.out.println(supplier.get()); // Tangs
    }
}
```

# 11.Stream流式计算

> 什么是stream流式计算？

大数据：存储 + 计算。

集合、MySQL本质就是存储东西的；计算都应该交给流来操作！

> 代码示例

```java
package com.ymy.stream;

import com.ymy.User;

import java.util.Arrays;
import java.util.List;

/**
 * 题目要求：一分钟内完成此题,只能用一行代码来实现！
 * 现在有5个用户！筛选：
 * 1、ID必须是偶数
 * 2、年龄必须大于10岁
 * 3、用户名转成大写字母
 * 4、用户名字母倒着排序
 * 5、只输出一个用户
 * filter(Predicate<? super T> predicate); 断定型接口
 * Stream<R> map(Function<? super T, ? extends R> mapper); 函数型接口
 * sorted(Comparator<? super T> comparator); 函数式接口
 */
public class StreamDemo {
    public static void main(String[] args) {
        User user1 = new User(1, "Tangs", 18);
        User user2 = new User(2, "Ringo", 19);
        User user3 = new User(3, "Jack", 17);
        User user4 = new User(4, "Tom", 14);
        User user5 = new User(5, "Smith", 21);
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5);

        users.stream()
                .filter(user -> user.getId() % 2 == 0)
                .filter(user -> user.getAge() > 10)
                .map(user -> user.getName().toUpperCase())
                .sorted((name1, name2) -> name2.compareTo(name1))
                .forEach(System.out::println); // TOM RINGO
    }
}
```

# 12.ForkJoin

## 12.1.什么是ForkJoin？

`ForkJoin`在`JDK1.7`就有了，并行执行任务，提高效率。大数据量！

大数据：Map、Reduce（把大任务拆分为小任务）。

<img src="https://img-blog.csdnimg.cn/20200727230024699.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70" alt="ForkJoin"  />

> ForkJoin特点：工作窃取！（能者多劳）

**这个里面维护的都是双端队列。**

<img src="https://img-blog.csdnimg.cn/20200727232039800.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70" alt="工作窃取"  />

## 12.2.ForkJoin的使用

> 创建ForkJoinTask

```java
package com.ymy;

import java.util.concurrent.RecursiveTask;

public class MyRecursiveTask extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    private Long threshold = 10_0000L;

    public MyRecursiveTask(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if ((end - start) < threshold) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            Long middle = (start + end) >>> 1;
//            System.out.println("middle=" + middle);
            MyRecursiveTask task1 = new MyRecursiveTask(start, middle);
            MyRecursiveTask task2 = new MyRecursiveTask(middle + 1, end);
            task1.fork();
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}
```

> 测试

```java
package com.ymy;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinTest {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        Long ret = test1(1L, 10_0000_0000L);
        long end = System.currentTimeMillis();
        System.out.println("ret = " + ret); // 500000000500000000
        System.out.println("time = " + (end - start)); // 5396ms
    }

    /**
     * ForkJoin
     */
    public static Long test1(Long start, Long end) throws Exception {
        MyRecursiveTask task = new MyRecursiveTask(start, end);
        // 创建ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 提交ForkJoinTask
        return forkJoinPool.submit(task).get();
    }
}
```

# 13.异步回调

```java
package com.ymy.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用：CompletableFuture
 * 异步执行
 * 成功回调
 * 失败回调
 */
public class Demo01 {
    public static void main(String[] args) throws Exception {
        // 没有返回值的异步回调
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        });

        System.out.println("11111111111111");

        completableFuture.get(); // 获取阻塞执行结果

        System.out.println("=============================================================");

        // 有返回的异步回调
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            int i = 1 / 0;
            return 1024;
        });
        System.out.println(integerCompletableFuture.whenComplete((param1, param2) -> {
            System.out.println("param1 " + param1); // 正常的返回结果
            System.out.println("param2 " + param2); // 错误信息
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return 233;  // 可以获取到错误的返回结果
        }).get());
    }
}
```

# 14.JMM

> 请你谈谈対volatile的理解

`volatile`是Java虚拟机提供的**轻量级的同步机制**。

1、保证可见性。

2、不保证原子性。

3、禁止指令重排。

> 什么是JMM？

`JMM`：Java线程共享内存模型，不存在的东西，概念！约定！

**关于JMM的一些同步的约定：**

1、线程解锁前，必须把共享变量**立刻**刷新回主物理内存。

2、线程加锁前，必须读取主物理内存中的最新的值到工作内存中。

3、加锁和解锁是同一把锁。

> Java共享内存模型的8种操作

![JMM](https://img-blog.csdnimg.cn/20200728231225604.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

关于主内存与工作内存之间的交互协议，即一个变量如何从主内存拷贝到工作内存。如何从工作内存同步到主内存中的实现细节。java内存模型定义了8种操作来完成。**这8种操作每一种都是原子操作**。8种操作如下：

- lock(锁定)：作用于主内存，它把一个变量标记为一条线程独占状态；
- read(读取)：作用于主内存，它把变量值从主内存传送到线程的工作内存中，以便随后的load动作使用；
- load(载入)：作用于工作内存，它把read操作的值放入工作内存中的变量副本中；
- use(使用)：作用于工作内存，它把工作内存中的值传递给执行引擎，每当虚拟机遇到一个需要使用这个变量的指令时候，将会执行这个动作；
- assign(赋值)：作用于工作内存，它把从执行引擎获取的值赋值给工作内存中的变量，每当虚拟机遇到一个给变量赋值的指令时候，执行该操作；
- store(存储)：作用于工作内存，它把工作内存中的一个变量传送给主内存中，以备随后的write操作使用；
- write(写入)：作用于主内存，它把store传送值放到主内存中的变量中。
- unlock(解锁)：作用于主内存，它将一个处于锁定状态的变量释放出来，释放后的变量才能够被其他线程锁定；

Java内存模型还规定了执行上述8种基本操作时必须满足如下规则:

- 不允许read和load、store和write操作之一单独出现（即不允许一个变量从主存读取了但是工作内存不接受，或者从工作内存发起会写了但是主存不接受的情况），以上两个操作必须按顺序执行，但没有保证必须连续执行，也就是说，read与load之间、store与write之间是可插入其他指令的。

- 不允许一个线程丢弃它的最近的assign操作，即变量在工作内存中改变了之后必须把该变化同步回主内存。

- 不允许一个线程无原因地（没有发生过任何assign操作）把数据从线程的工作内存同步回主内存中。

- 一个新的变量只能从主内存中“诞生”，不允许在工作内存中直接使用一个未被初始化（load或assign）的变量，换句话说就是对一个变量实施use和store操作之前，必须先执行过了assign和load操作。

- 一个变量在同一个时刻只允许一条线程对其执行lock操作，但lock操作可以被同一个条线程重复执行多次，多次执行lock后，只有执行相同次数的unlock操作，变量才会被解锁。

- 如果对一个变量执行lock操作，将会清空工作内存中此变量的值，在执行引擎使用这个变量前，需要重新执行load或assign操作初始化变量的值。

- 如果一个变量实现没有被lock操作锁定，则不允许对它执行unlock操作，也不允许去unlock一个被其他线程锁定的变量。

- 对一个变量执行unlock操作之前，必须先把此变量同步回主内存（执行store和write操作）。

# 15.volatile

> 1、保证可见性

```java
package com.ymy.volat;

import java.util.concurrent.TimeUnit;

/**
 * 测试可见性
 * 如果变量num不加volatile,那么当main线程修改了num的值,
 * A线程还是会一直执行循环,加上volatile,当num被其他线程
 * 修改后,A线程会去重新读num的值,从而推出循环！
 */
public class Demo01 {
    public volatile static int num = 0;

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            while (num == 0) {

            }
        }, "A").start();

        TimeUnit.SECONDS.sleep(3);

        num = 1;
        System.out.println(num);
    }
}
```

> 2、不保证原子性

原子性：不可分割！线程A在执行任务的时候，是不能被打扰的，也不能被分割，要么同时成功，要么同时失败。

```java
package com.ymy.volat;

/**
 * 测试不保证原子性
 */
public class Demo02 {

    private volatile static int num = 0;

    public static void add() {
        num++;
    }

    public static void main(String[] args) {

        // 理论上执行完num应该为20000
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t" + num); // 实际上num小于20000
    }
}
```

> 3、禁止指令重排

指令重排：写的Java程序，**计算机并不是按照我们写的那样去执行的。**

**处理器在进行指令重排的时候，考虑：数据之间的依赖性！**

```java
int x = 1; // 1
int y = 2; // 2
x = x + 5; // 3
y = x * x; // 4

我们期望的执行顺序：1234
但是计算机执行的时候可能是 2143    
```

# 16.单例模式

> 懒汉单例DCL

```java
package com.ymy.single;

import java.lang.reflect.Constructor;

/**
 * 懒汉单例
 */
public class Lazy {

    // 加上volatile禁止指令重排
    private volatile static Lazy lazy = null;

    private Lazy() {
    }

    // 双重检测 DCL懒汉式
    public static Lazy getInstance() {
        if (lazy == null) {
            synchronized (Lazy.class) {
                if (lazy == null) {
                    lazy = new Lazy();
                }
            }
        }
        return lazy;
    }

    // 反射
    public static void main(String[] args) throws Exception{
        // 创建一个实例
        Lazy instance1 = Lazy.getInstance();

        // 拿到无参构造器去创建对象
        Constructor<Lazy> declaredConstructor = Lazy.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        Lazy instance2 = declaredConstructor.newInstance();

        System.out.println(instance1 == instance2); // false
    }
}
```

> 静态内部类

```java
package com.ymy.single;

/**
 * 静态内部类
 */
public class Holder {

    private Holder() {
    }

    private static class InnerClass {
        private static Holder holder = new Holder();
    }

    public static Holder getInstance() {
        return InnerClass.holder;
    }

    public static void main(String[] args) {
        
    }
}
```

> 枚举单例

```java
package com.ymy.single;

import java.lang.reflect.Constructor;

public enum EnumSingle {
    INSTANCE;

    public static EnumSingle getInstance() {
        return INSTANCE;
    }
}

// 测试反射！
class Test {
    public static void main(String[] args) throws Exception {
        EnumSingle instance1 = EnumSingle.getInstance();

        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);

        // java.lang.IllegalArgumentException: Cannot reflectively create enum objects
        EnumSingle instance2 = declaredConstructor.newInstance();

        System.out.println(instance1 == instance2);
    }
}
```

# 17.CAS

`AtomicInteger`这个原子类引入了`Unsafe`类。

```java
public class AtomicInteger extends Number implements java.io.Serializable {
    private static final long serialVersionUID = 6214790243416807050L;

    // Unsafe类中有大量的native方法,java是无法操作的,要调用底层的C++接口来直接操作内存
    // Unsafe类相当于Java的后门,可以通过C++来操作内存
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    
    // valueOffset获取内存地址的偏移值
    private static final long valueOffset;
    
    // AtomicInteger类保存的具体值,使用volatile保证线程间的可见性，禁止指令重排
    private volatile int value;
```

下面来看`AtomicInteger`原子类中`getAndIncrement()`方法。

```java
// AtomicInteger
public final int getAndIncrement() {
    return unsafe.getAndAddInt(this, valueOffset, 1);
}


// Unsafe
/**
* var1：当前对象
* var2：地址的偏移量
* var4：需要增加的值
*/
public final int getAndAddInt(Object var1, long var2, int var4) {
    int var5;
    do {
        // 通过当前对象var1和地址偏移量var2获得内存中存储的值var5
        var5 = this.getIntVolatile(var1, var2);
        
    /** 如果现在通过var1和地址偏移量var2拿到的值是var5,那么就将(var5 + var4)的值写回内存中
    * 并且返回true，直接退出循环，否则就返回false，一直循环。
    */
    } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

    return var5;
}

public native int getIntVolatile(Object var1, long var2);

public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
```



`CAS`：`compareAndSwap`比较当前内存中的值和主内存中的值，如果这个值是期望的值，那么就执行操作！否则就一直自旋。



**CAS缺点：**

- 循环会耗时。
- 一次性只能保证一个共享变量的原子性。
- `ABA问题。



# 18.原子引用ABA问题

**什么是`ABA`问题？一句话：`ABA`问题就是狸猫换太子。**

假设有一条记录`Record1`，`Thread-A`去修改了这条记录，把`Record1`修改成了`Record2`，过了一会儿`Thread-A`又把`Record2`修改了`Record1`，然后`Thread-A`就执行结束了。接着`Thread-B`来修改记录，`Thread-B`期望内存中存储的记录是`Record1`，这时候内存中存储的记录也确实是`Record1`，于是`Thread-B`就将`Record1`修改成了`Record3`，实际上这种操作是有问题的！这就是原子引用的`ABA`问题！

```java
package com.ymy.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ABA问题测试代码
 */
public class ABADemo {

    public static void main(String[] args) throws Exception {
        Record record1 = new Record(1, "第一次提交");
        AtomicReference<Record> recordAtomicReference = new AtomicReference<>(record1);

        Record record2 = new Record(2, "A线程修改记录");
        Record record3 = new Record(3, "B线程修改记录");

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "修改记录...");

            // A线程将record1修改为了record2
            recordAtomicReference.compareAndSet(record1, record2);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // A线程又将record2修改成了record1
            recordAtomicReference.compareAndSet(record2, record1);
        }, "A").start();

        TimeUnit.SECONDS.sleep(3);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "修改记录...");

            // B线程又去修改记录,期望的值是record1,B线程以为,A线程没有被其他线程修改！
            // 实际上该记录已经被A线程修改了,这就是ABA问题
            recordAtomicReference.compareAndSet(record1, record3);
        }, "B").start();

        while (Thread.activeCount() > 2) {
            // main线程放弃CPU的执行权
            Thread.yield();
        }

        System.out.println(recordAtomicReference.get()); // Record(id=3, content=B线程修改记录)
    }

}

/**
 * Record：封装记录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class Record {
    private Integer id;
    private String content;
}
```



> 那么ABA问题有什么解决办法呢？？？

**使用`AtomicStampedReference`来添加时间戳（版本号）来解决ABA问题。**

```java
package com.ymy.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicStampedReference解决ABA问题测试代码
 */
public class ABADemo {

    public static void main(String[] args) throws Exception {

        Record record1 = new Record(1, "第一次提交");
        
        // AtomicStampedReference(V initialRef, int initialStamp) 
        AtomicStampedReference<Record> recordAtomicStampedReference = new AtomicStampedReference<>(record1, 1);

        Record record2 = new Record(2, "A线程修改记录");
        Record record3 = new Record(3, "B线程修改记录");

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "修改记录...");
            
            /* compareAndSet(V   expectedReference,
                                 V   newReference,
                                 int expectedStamp,
                                 int newStamp)
            */
            // A线程将record1修改为了record2
            recordAtomicStampedReference.compareAndSet(record1, record2, 1, 2);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // A线程又将record2修改成了record1
            recordAtomicStampedReference.compareAndSet(record2, record1, 2, 3);
        }, "A").start();

        TimeUnit.SECONDS.sleep(3);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "修改记录...");

            // 如果时间戳(版本号)被修改了就证明之前有其他线程修改了记录,B线程需要重新从内存中读取到最新的记录才可以修改
            recordAtomicStampedReference.compareAndSet(record1, record3, 1, 2);
        }, "B").start();

        while (Thread.activeCount() > 2) {
            // main线程放弃CPU的执行权
            Thread.yield();
        }

        System.out.println(recordAtomicStampedReference.getReference()); // Record(id=1, content=第一次提交)
        System.out.println(recordAtomicStampedReference.getStamp()); // 3  version的初始值是1被A线程修改两次后变成3 B线程没有修改记录

    }
}

/**
 * Record：封装记录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class Record {
    private Integer id;
    private String content;
}
```

# 19.各种锁的理解

## 19.1.公平锁和非公平锁

- 公平锁：非常公平，不能够插队，来了就排队，必须先来后来。
- 非公共锁：非常不公平，可以插队，后面来的线程只要抢到锁就可以占有资源**（默认都是非公平锁）**。

```java
// ReentrantLock 默认就是非公平锁
public ReentrantLock() {
    sync = new NonfairSync();
}

// 可以将ReentrantLock 默认的非公平锁修改为公平锁
public ReentrantLock(boolean fair) {
    sync = fair ? new FairSync() : new NonfairSync();
}
```



## 19.2.可重入锁

**可重入锁（递归锁）：**可重入就是说某个线程已经获得某个锁，可以再次获取相同的锁而不会出现死锁。

> Synchronized版本

```java
package com.ymy.reentant;

/**
 * 可重入锁（递归锁）:可重入就是说某个线程已经获得某个锁，
 * 可以再次获取相同的锁而不会出现死锁。
 *
 * 程序执行结果：
 * A	sms...
 * A	call...
 * B	call...
 */
public class ReentrantDemo01 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sms();
        }, "A").start();

        new Thread(() -> {
            phone.call();
        }, "B").start();
    }
}

/**
 * sms() 和 call()两个方法的synchronized锁的都是当前Phone实例
 */
class Phone {
    public synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + "\t" + "sms...");
        call();
    }

    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + "\t" + "call...");
    }
}
```

当`Thread-A`调用`sms()`方法获得`Phone`的对象锁时，由于`sms()`方法还没有执行完又调用`call()`方法，恰好`sms()`和`call()`都是同一把锁，`Thread-A`就不用再去获取锁了，直接就可以进入到`call()`方法中！！！

> Lock版本

```java
package com.ymy.reentant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock锁和Synchronized锁的区别：lock和unlock必须成对出现，否则就会造成死锁
 * <p>
 * 程序运行结果：
 * A	sms()...
 * A	call()...
 * B	call()...
 */
public class ReentrantDemo02 {
    public static void main(String[] args) {
        Iphone iphone = new Iphone();

        new Thread(() -> {
            iphone.sms();
        }, "A").start();

        new Thread(() -> {
            iphone.call();
        }, "B").start();
    }
}

class Iphone {
    private Lock lock = new ReentrantLock();

    public void sms() {
        lock.lock();
        lock.lock(); //如果这里lock两次，finally中只unlock一次，就会造成死锁
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "sms()...");
            call();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public void call() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "call()...");
        } finally {
            lock.unlock();
        }
    }
}
```

`Lock`锁很灵活，需要手动加锁`lock()`，也需要手动解锁`unlock()`，如果`lock()`和`unlock()`不是成对出现的就会造成死锁。在`sms()`方法中，`lock()`和`unlock()`对应上锁和释放锁的是`sms()`方法；在`call()`方法中，`lock()`和`unlock()`对应上锁和释放锁的是`call()`方法，虽然`sms()`方法中调用了`call()`方法，但是两个方法中的`lock()`和`unlock()`一样不能混淆！！！

# 20.死锁排查

> 什么是死锁？

![DeadLock](https://img-blog.csdnimg.cn/20200801121005905.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

死锁：指两个或两个以上的进程在执行过程中，由于竞争资源或者由于彼此通信而造成的一种阻塞的现象，若无外力作用，它们都将无法推进下去。此时称系统处于死锁状态或系统产生了死锁，这些永远在互相等待的进程称为死锁进程。

> 死锁代码测试

```java
package com.ymy.dead;

import java.util.concurrent.TimeUnit;

/**
 * 死锁
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        Resource1 resource1 = new Resource1();
        Resource2 resource2 = new Resource2();

        new Thread(() -> {
            resource1.getRes();
        }, "A").start();

        new Thread(() -> {
            resource2.getRes();
        }, "B").start();
    }
}

// 资源类Resource1
class Resource1 {
    public void getRes() {
        synchronized (Resource1.class) {
            System.out.println(Thread.currentThread().getName() + "\t" + "获取到Resource1的锁");

            // 设置延迟3S保证Thread-A获得Resource1锁的同时，Thread-B获得Resource2锁
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Resource2.class) {
                System.out.println(Thread.currentThread().getName() + "\t" + "获取到Resource2的锁");
            }
        }
    }
}

// 资源类Resource2
class Resource2 {
    public synchronized void getRes() {
        synchronized (Resource2.class) {
            System.out.println(Thread.currentThread().getName() + "\t" + "获取到Resource2的锁");
            synchronized (Resource1.class) {
                System.out.println(Thread.currentThread().getName() + "\t" + "获取到Resource1的锁");
            }
        }
    }
}
```

> 死锁的排查

1、使用`jps-l`定位进程号。

![jps-l](https://img-blog.csdnimg.cn/2020080112352315.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

2、使用`jstack + 进程号` 打印堆栈信息

![jstack](https://img-blog.csdnimg.cn/2020080112395455.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)