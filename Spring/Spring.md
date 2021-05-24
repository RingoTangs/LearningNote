# 1. AOP

## 1.1. 通知注解

![通知注解](https://img2018.cnblogs.com/blog/758949/201905/758949-20190529225613898-1522094074.png)

> 面试题：
>
> - AOP的全部通知顺序，SpringBoot1或SpringBoot2对AOP执行顺序的影响?
> - 在使用AOP的过程中碰到的坑？



## 1.2. 构造切面

```java
// 目标类
@Component
public class Calculate {
    public int division(int x, int y) {
        return x / y;
    }
}

// 切面类
@Aspect
@Component
public class CalculateAspect {

    @Pointcut("execution(public * com.ymy.Calculate.*(..))")
    public void cutPoint() {
    }

    @Before("cutPoint()")
    public void before() {
        System.out.println("before...");
    }

    @After("cutPoint()")
    public void after() {
        System.out.println("after..");
    }

    @Around("cutPoint()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around...before");
        Object ret = pjp.proceed();
        System.out.println("方法执行结果==>" + ret);
        System.out.println("around...after");
        return ret;
    }

    @AfterReturning("cutPoint()")
    public void afterReturning() {
        System.out.println("afterReturning...");
    }

    @AfterThrowing("cutPoint()")
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }
}
```



## 1.3. 测试

### 1.3.1. Spring4

**测试结果**：

```java
// 1. 正常情况
around...before
before...
方法执行结果==>3
around...after
after..
afterReturning...
    
// 2. 异常情况    
around...before
before...
after..
afterThrowing
```



**总结**：

```java
try {
    @Before
    method.invoke(obj, args);
    @AfterReturning // return 
} catch() {
    @AfterThrowing
} finally {
    @After
}
```

- **正常执行**：@Before（前置通知）===> @After（后置通知）===> @AfterReturning（正常返回）。
- **异常执行**：@Before（前置通知）===> @After（后置通知）===>  @AfterThrowing（方法异常）。



### 1.3.2. Spring5

```java
// 1. 正常情况
around...before
before...
afterReturning...
after..
around...after

// 2. 异常情况
around...before
before...
afterThrowing
after..
```





# 2. 循环依赖

> **循环依赖**：多个 bean 之间相互依赖，形成了一个闭环。
>
> 比如：A依赖于B、B依赖于C、C依赖于A。

```java
// 产生循环依赖的异常
BeanCurrentlyInCreationException: 
Error creating bean with name 'rememberMeServices': Requested bean is currently in creation: Is there an unresolvable circular reference?
```



