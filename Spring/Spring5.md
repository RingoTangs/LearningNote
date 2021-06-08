# 一、Sring5基础

## 1. Spring系统架构

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/spring5/spring系统架构.6o36peluqq40.png" alt="spring系统架构" style="zoom:150%;" />



## 2. 入门案例

**（1）四个核心一个依赖**：

- 导入Spring5的核心 jar：`Beans、Core、Context、Expression`。

- 导入一个依赖 jar：`commons-logging`。

```xml
<!-- 1: spring 的四个核心包 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-beans</artifactId>
    <version>5.2.6.RELEASE</version>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>5.2.6.RELEASE</version>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.2.6.RELEASE</version>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-expression</artifactId>
    <version>5.2.6.RELEASE</version>
</dependency>

<!-- 2: 依赖日志包 -->
<dependency>
    <groupId>commons-logging</groupId>
    <artifactId>commons-logging</artifactId>
    <version>1.1.1</version>
</dependency>
```



**（2）创建测试类**。

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    static {
        System.out.println("loading user....");
    }
    private String username;
    private String password;
}
```



**（3）创建Spring配置文件**：在 `classPath` 下创建 spring-config.xml（名字可以随便起）。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
    <!-- 将 User 类加入到 IoC 容器 -->
    <!-- id：bean的名字; class: User类的全限定类名 -->
    <bean id="user" class="com.ymy.spring5.User">
        <property name="username" value="lisi"></property>
        <property name="password" value="123"></property>
    </bean>
</beans>
```



**（4）测试代码**。

```java
@Test
void test1() {
    // 1: 加载 spring 配置文件
    // ApplicationContext 是 BeanFactory 的子接口
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:bean1.xml");

    // 2: 获取配置重创建的对象
    // 等价于按照 bean 的名字和类型, 从容器重取出 bean
    User user = applicationContext.getBean("user", User.class);

    // 3: 调用方法
    System.out.println(user);
}
```

```java
// 测试结果
loading user....
User(username=lisi, password=123)
```



## 3. IoC容器

### 3.1. IoC实现

**Spring 提供 IoC 容器两种实现方式**：

- `BeanFactory`：Spring 内部的使用接口，不提供给开发人员使用。
  - 加载 spring 配置文件的时候不会创建对象，getBean() 要使用对象的时候才会创建对象。
- `ApplicationContext`：该接口是 `BeanFactory` 的子接口，面向开发人员进行使用。
  - 加载 spring 配置文件的时候就会创建对象。



### 3.2. XML管理bean

**（1）基于无参构造器创建对象**。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    <!-- 将 User 类加入到 IoC 容器 -->
    <!-- id：bean的名字; class: User类的全限定类名 -->
    <!-- 基于无参构造器来创建对象 -->
    <bean id="user" class="com.ymy.spring5.User"></bean>
</beans>
```



**（2）注入属性：依赖注入**。

```xml
<bean id="user" class="com.ymy.spring5.User">
    <!-- 通过 set 方法注入 -->
    <property name="username" value="lisi"></property>
    <property name="password" value="123"></property>
</bean>
```

```xml
<bean id="user" class="com.ymy.spring5.User">
    <!-- 通过有参构造器注入 --> 
    <constructor-arg name="username" value="abc"></constructor-arg>
    <constructor-arg name="password" value="123"></constructor-arg>
</bean>
```

```xml
<bean id="userMapper" class="com.ymy.spring5.UserMapper"></bean>
<bean id="userService" class="com.ymy.spring5.UserService">
	<!-- ref 注入外部 bean -->   
    <property name="userMapper" ref="userMapper"></property>
</bean>
```

```xml

```

