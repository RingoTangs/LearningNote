# 一、@Async为什么不建议直接使用？

对于异步方法调用，从 spring3 开始提供了 `@Async` 注解，该注解可以被标注在方法上，以便异步地调用该方法。调用者将在调用时立即返回，方法的实际执行将交给 Spring TaskExecutor 的任务中，由指定的线程池中的线程执行。

在项目应用中，`@Async` 调用线程池，推荐使用自定义线程池的模式。

自定义线程池常用方案：重新实现接口 `AsyncConfigurer`。

## 1. Spring实现的线程池

- `SimpleAsyncTaskExecutor`：不是真的线程池，这个类不重用线程，默认每次调用都会创建一个新的线程。
- `SyncTaskExecutor`：这个类没有实现异步调用，只是一个同步操作。只适用于不需要多线程的地方。
- `ConcurrentTaskExecutor`：Executor的适配类，不推荐使用。如果 `ThreadPoolTaskExecutor`不满足要求时，才用考虑使用这个类。
- `SimpleThreadPoolTaskExecutor`：是Quartz的 `SimpleThreadPool` 的类。线程池同时被quartz和非quartz使用，才需要使用此类。
- `ThreadPoolTaskExecutor `：最常使用，推荐。其实质是对 `java.util.concurrent.ThreadPoolExecutor` 的包装。

异步的方法有：

1. 最简单的异步调用，返回值为void。
2. 带参数的异步调用，异步方法可以传入参数。
3.  存在返回值，常调用返回Future。

## 2. Spring中启用@Async

```java
@EnableAsync
@Configuration
public class SpringAsyncConfig {
    // ...
}
```



## 3. @Async使用默认线程池

Spring应用默认的线程池，指在 `@Async` 注解在使用时，不指定线程池的名称。

查看源码，`@Async` 的默认线程池为 `SimpleAsyncTaskExecutor`。

### 3.1. 无返回值调用

基于 `@Async` 无返回值调用，直接在使用类，使用方法（建议在使用方法）上，加上注解。若需要抛出异常，需手动new一个异常抛出。

```java
/**
* 带参数的异步调用, 异步方法可以传入参数。
* 对于返回值是 void, 异常会被 AsyncUncaughtExceptionHandler 处理掉。
*/
@Async
public void asyncInvokedWithException(String s) throws InterruptedException {
    throw new IllegalArgumentException(s);
}
```

### 3.2. 有返回值Future调用

```java
/**
* 异常调用返回 Future
* 对于返回值是 Future, 不会被 AsyncUncaughtExceptionHandler 处理，
* 需要我们自己手动处理异常, 或者调用 Future.get() 时处理异常。
*/
@Async
public Future<String> asyncInvokeReturnFuture(int i) {
    Future<String> future;
    try {
        Thread.sleep(1000);
        future = new AsyncResult<>("success:" + i);
        throw new IllegalArgumentException("a");
    } catch (InterruptedException e) {
        future = new AsyncResult<>("error");
    } catch (IllegalArgumentException e) {
        future = new AsyncResult<>("error-IllegalArgumentException");
    }
    return future;
}
```



## 4. @Async自定义线程池

自定义线程池，可对系统中线程池更加细粒度的控制，方便调整线程池大小配置，线程执行异常控制和处理。在设置系统自定义线程池代替默认线程池时，虽可通过多种模式设置，但替换默认线程池最终产生的线程池有且只能设置一个（不能设置多个类继承 AsyncConfigurer）自定义线程池有如下模式：

- 重新实现接口AsyncConfigurer。
- 继承AsyncConfigurerSupport。
- 配置由自定义的TaskExecutor替代内置的任务执行器。

通过查看Spring源码关于@Async的默认调用规则，会优先查询源码中实现AsyncConfigurer这个接口的类，实现这个接口的类为AsyncConfigurerSupport。但默认配置的线程池和异步处理方法均为空，所以，无论是继承或者重新实现接口，都需指定一个线程池。且重新实现 public Executor getAsyncExecutor()方法。

### 4.1. 实现接口AsyncConfigurer

```java
@EnableAsync
@Configuration
public class AsyncConfiguration implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        return new ThreadPoolExecutor(1, Runtime.getRuntime().availableProcessors(),
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> System.out.println(ex);
    }
}
```

### 4.2. 继承AsyncConfigurerSupport

和实现接口基本一致。



## 5. @Async限制

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/MultiThreadConcurrent/@Async限制.geemg54r6qg.png" alt="@Async限制"  />