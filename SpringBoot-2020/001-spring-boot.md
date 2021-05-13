# 一：spring-boot2-基础入门

## 1. 环境配置

**(1)系统要求**

[Java 8](https://www.java.com/) & 兼容java14 .

Maven 3.3+

idea 2019.1.2





**(2)Maven配置**

```xml
<mirrors>
      <mirror>
        <id>nexus-aliyun</id>
        <mirrorOf>central</mirrorOf>
        <name>Nexus aliyun</name>
        <url>http://maven.aliyun.com/nexus/content/groups/public</url>
      </mirror>
  </mirrors>
 
  <profiles>
         <profile>
              <id>jdk-1.8</id>
              <activation>
                <activeByDefault>true</activeByDefault>
                <jdk>1.8</jdk>
              </activation>
              <properties>
                <maven.compiler.source>1.8</maven.compiler.source>
                <maven.compiler.target>1.8</maven.compiler.target>
                <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
              </properties>
         </profile>
  </profiles>
```



**(3) IDE中Maven配置**

![image-20201227221337800](C:\Users\14666\AppData\Roaming\Typora\typora-user-images\image-20201227221337800.png)



## 2. 创建项目

**(1) POM文件**

```xml
<!-- 
导入父项目：
1.The spring-boot-starter-parent is a special starter that provides useful Maven defaults. It also provides a dependency-management section so that you can omit(省略) version tags for “blessed” dependencies.

2.You can see that spring-boot-starter-parent provides no dependencies by itself.
parent这个启动器本身不会提供任何依赖，它做的是资源(依赖、版本version)的管理者(management)，添加具体的stater就会下载相应的依赖。比如，做web应用的开发，就添加 spring-boot-starter-web 就会下载web相关的模块！

3、虽然 parent 对依赖的版本有管理的作用，但是我们可以重写依赖的版本来替换 spring boot 推荐的版本
-->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.4.1</version>
</parent>

<!-- 导入依赖 -->
<dependencies>
    <!-- import web component -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```



**完整的POM**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.ymy</groupId>
    <artifactId>001-spring-boot-helloworld</artifactId>
    <version>1.0-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.1</version>
    </parent>

    <dependencies>

        <!-- import web component -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>

    </dependencies>

    <!--
    1、To create an executable jar, we need to add the spring-boot-maven-plugin to our pom.xml.
    spring-boot-maven-plugin (插件帮助我们创建可执行的jar)。
    2、The spring-boot-starter-parent POM includes <executions> configuration to bind the
    repackage goal.
    这个插件和 spring-boot-starter-parent 是绑定在一起的。
    3、使用 mvn package 就可以进行打包,变成可执行的 jar。
	4、防止spring-boot-configuration-processor打包
    -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-configuration-processor</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```





**（2) 主启动类**

```java
package com.ymy.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动类
 * @SpringBootApplication： 这是一个SpringBoot应用
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
```



**(3) 编写业务类**

```java
package com.ymy.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1、@RestController: 相当于@ResponseBody 和 @Controller的组合注解。
 * 如果只是使用@RestController注解，则Controller中的方法无法返回jsp页面，或者html，配置
 * 的视图解析器 InternalResourceViewResolver 不起作用，返回的内容就是 Return 中的字符串。
 *
 * 2、如果要跳转到指定页面，则需要 @Controller 配合试图解析器 InternalResourceViewResolver。
 * 如果需要返回 JSON， 需要在对应的方法上添加 @ResponseBody
 */
@RestController
public class HelloController {

    /**
     * The @RequestMapping annotation provides “routing” information.(提供了路由的信息)
     * It tells Spring that any HTTP request with the / path should be mapped to the home method.
     */
    @RequestMapping("/hello")
    public String handle01() {
        return "HELLO!";
    }
}

```



**(4) 创建一个可执行的 jar：Spring Boot简化项目部署**

```xml
<!--
1、To create an executable jar, we need to add the spring-boot-maven-plugin to our pom.xml.
spring-boot-maven-plugin (插件帮助我们创建可执行的jar)。
2、The spring-boot-starter-parent POM includes <executions> configuration to bind the
repackage goal.
这个插件和 spring-boot-starter-parent 是绑定在一起的。
3、使用 mvn package 就可以进行打包,变成可执行的 jar。
-->
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```



## 3. 了解自动配置原理

### 3.1. 依赖管理

```xml
<!-- 1、我们的项目继承的父项目 -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.4.1</version>
</parent>

<!-- 2、spring-boot-starter-parent继承的父项目  -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.4.1</version>
</parent>

<!-- spring-boot-dependencies声明了许多依赖的版本号，所以 starter—parent 可以管理依赖的版本 -->
<!-- spring-boot几乎声明了所有开发中常用的依赖版本号，这就是spring-boot的版本仲裁机制 -->
<properties>
    <activemq.version>5.16.0</activemq.version>
    <antlr2.version>2.7.7</antlr2.version>
    <appengine-sdk.version>1.9.83</appengine-sdk.version>
    <artemis.version>2.15.0</artemis.version>
    <aspectj.version>1.9.6</aspectj.version>
    <assertj.version>3.18.1</assertj.version>
    <atomikos.version>4.0.6</atomikos.version>
    <awaitility.version>4.0.3</awaitility.version>
    <bitronix.version>2.1.4</bitronix.version>
    ....
</properties>

<!-- 我们自定义依赖的版本，要用spring-boot规定的依赖版本标签,在我们自己的pom中properties重写即可-->
<!-- 在我们自己的 pom 文件中声明 activemq 的版本号，注意和 spring-boot-dependencies 中一致-->
<!-- 如果我们添加的依赖 spring-boot 根本就没有声明版本号，那我们就必须手动添加版本信息了！ -->
<properties>
	 <activemq.version>5.20.1</activemq.version>
</properties>
```



### 3.2. starters

```xml
<!-- All official starters follow a similar naming pattern; spring-boot-starter-*, 
where * is a particular type of application -->
1、spring-boot-starter-*：*就是代表了某个场景(模块)启动器。
2、只要引入starter，这个场景相关的依赖都会被加载进我们的项目中。

<!-- third party starters should not start with spring-boot, as it is reserved for official Spring Boot artifacts. Rather, a third-party starter typically starts with the name of the project. For example, a third-party starter project called thirdpartyproject would typically be named thirdpartyproject-spring-boot-starter. -->
3、第三方的 starter 不应该用 spring-boot 开头来命名，因为这个前缀是官方用的！
*-spring-boot-starter 一般都是指第三方插件。

4、所有的 starter 都会加载最底层的依赖
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <version>2.4.1</version>
</dependency>
```



### 3.3. 自动配置

- 自动配置了Tomcat。`spring-boot-starter-web` 启动器中加入了 tomcat 的依赖。同时还引入了 `spring-web spring-webmvc` 等模块。

  ```xml
  <!-- spring-boot-starter-web 中引入的tomcat依赖 -->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-tomcat</artifactId>
      <version>2.4.1</version>
      <scope>compile</scope>
  </dependency>
  
  <!-- spring-boot-starter-web 整合了springMVC常用的组件并自动配置了 -->
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>5.3.2</version>
      <scope>compile</scope>
  </dependency>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.3.2</version>
      <scope>compile</scope>
  </dependency>
  ```

  

- spring-boot 自动配置了 `spring mvc` 全套组件。

  ```java
  package com.ymy.boot;
  
  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;
  import org.springframework.context.ConfigurableApplicationContext;
  
  /**
   * 主启动类
   *
   * @SpringBootApplication： 这是一个SpringBoot应用
   */
  @SpringBootApplication
  public class MainApplication {
      public static void main(String[] args) {
          // 1、返回的就是 IOC 容器
          ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
  
          // 2、查看容器里面的组件,如字符编码、文件上传、视图解析器等等...
          String[] beanDefinitionNames = run.getBeanDefinitionNames();
          for (String name : beanDefinitionNames) {
              System.out.println(name);
          }
      }
  }
  ```

  ![image-20201228105305806](C:\Users\14666\AppData\Roaming\Typora\typora-user-images\image-20201228105305806.png)



- 默认的包结构：主程序所在的包及其下面的所有子包里面的组件都会被默认扫描进容器。

  ![image-20201228110804902](C:\Users\14666\AppData\Roaming\Typora\typora-user-images\image-20201228110804902.png)

  

  ```java
  /**
   * 主启动类
   * @SpringBootApplication： 这是一个SpringBoot应用
   * scanBasePackages：自定义包扫描路径,就可以扫描其他包的组件了！
   */
  @SpringBootApplication(scanBasePackages = {"com.ymy"})
  public class MainApplication {
  }
  
  /** 
  * 或者使用@ComponentScan来指定扫描路径
  */
  @ComponentScan(basePackages = {"com.ymy"})
  @SpringBootApplication
  public class MainApplication {
  }
  
  /**
  * @SpringBootApplication 是一个组合注解
  */
  @SpringBootApplication
  等同于
  @SpringBootConfiguration
  @EnableAutoConfiguration
  @ComponentScan("com.ymy.boot")
  ```



- 按需加载自动配置项：spring-boot 引入了很多的 starter，只有我们把具体的 starter 添加到依赖中，相关的自动配置就会生效。

## 4. 组件添加

### 4.1. @Configuration

**(1) @Configuration的使用**。

```java
package com.ymy.boot.config;

import com.ymy.boot.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration： 告诉SpringBoot这是配置类
 * 1、配置类本身也是组件
 * 2、proxyBeanMethods：代理 bean 方法
 *    proxyBeanMethods = true  保证多次调用 user01() 获得的都是单实例 直接去IOC容器中拿组件。
 *    proxyBeanMethods = false 每次调用都会产生一个对象。
 * 3、模式的选择：
 * (1)如果配置类组件之间无依赖关系，proxyBeanMethods设置为 false 加速容器启动过程；
 * (2)如果配置类组件之间有依赖关系，proxyBeanMethods设置为 true 来获得单实例组件。
 */
@Configuration
public class ApplicationConfig {

    /**
     * @Bean: 给容器中添加组件。
     *
     * 1、以方法名作为组件(component)的id；
     * 2、返回类型就是组件类型。
     * 3、返回的值就是组件在容器中的实例。
     */
    @Bean
    public User user01() {
        return new User("张三", 18);
    }
}
```

![image-20201228114634334](C:\Users\14666\AppData\Roaming\Typora\typora-user-images\image-20201228114634334.png)



**(2) 注册到容器中的组件默认全部都是单实例的**。

```java
package com.ymy.boot;

import com.ymy.boot.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 主启动类
 *
 * @SpringBootApplication： 这是一个SpringBoot应用
 * scanBasePackages：自定义包扫描路径,就可以扫描其他包的组件了！
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        // 1、返回的就是 IOC 容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        // 2、查看容器里面的组件,如字符编码、文件上传、视图解析器等等...
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }

        // 3、从容器中获取组件 getBean()会返回bean的实例
        User user01 = run.getBean("user01", User.class);
        User user02 = run.getBean("user01", User.class);
        System.out.println("user01 " +(user01 == user01)+ " user02");
    }
}
```

![image-20201228115217330](C:\Users\14666\AppData\Roaming\Typora\typora-user-images\image-20201228115217330.png)



### 4.2. @Import

```java
/** 
* @Import：给容器中自动创建出这两个类型的组件。
*/
@Import(value = {User.class, DBHelper.class})
@Configuration(proxyBeanMethods = true)
public class ApplicationConfig {

    /**
     * @Bean: 给容器中添加组件。
     *
     * 1、以方法名作为组件(component)的id；
     * 2、返回类型就是组件类型。
     * 3、返回的值就是组件在容器中的实例。
     */
    @Bean
    public User user01() {
        return new User("张三", 18);
    }
}
```

![image-20201228122905515](C:\Users\14666\AppData\Roaming\Typora\typora-user-images\image-20201228122905515.png)



### 4.3. @Conditional

**@Conditional(可以在方法上用)：条件装配，当满足 Conditional 指定的条件，则进行组件注册。**

![image-20201228123258377](C:\Users\14666\AppData\Roaming\Typora\typora-user-images\image-20201228123258377.png)

```java
@Import(value = {User.class, DBHelper.class})
@Configuration(proxyBeanMethods = true)
public class ApplicationConfig {

    @Bean
    public Pet tom01() {
        return new Pet("tom", "男");
    }

    /**
     * @ConditionalOnBean(name = {"tom01"})
     * 当容器中有名字为tom01的Bean的时候 user01才会被注册进容器
     */
    @Bean
    @ConditionalOnBean(name = {"tom01"})
    public User user01() {
        return new User("张三", 18);
    }
}
```

```java
@Import(value = {User.class, DBHelper.class})
@Configuration(proxyBeanMethods = true)
public class ApplicationConfig {
    
    public Pet tom01() {
        return new Pet("tom", "男");
    }

    /**
     * @ConditionalOnMissingBean(name = {"tom01"})
     * 当容器没有tom01的时候才注册user01
     */
    @Bean
    @ConditionalOnMissingBean(name = {"tom01"})
    public User user01() {
        return new User("张三", 18);
    }
}
```



### 4.4. @ImportResource

```shell
If you absolutely must use XML based configuration, we recommend that you still start with a @Configuration class. You can then use an @ImportResource annotation to load XML configuration files.

使用@ImportResource可以将XML中注册的组件加载进Spring Boot应用。
```



## 5. 配置绑定

**(1) @ConfigurationProperties + @Component**

**如何使用Java读取properties配置文件中的内容，并且把它封装到JavaBean中，以便于随时使用？**

```java
/**
 * 封装实体类使用@ConfigurationProperties进行配置绑定
 * 只有在容器中的组件才能使用spring的配置绑定功能
 */
@Component
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private Integer price;

    public Car() {
    }

    public Car(String brand, Integer price) {
        this.brand = brand;
        this.price = price;
    }
}
```

```xml
<!-- 使用@ConfigurationProperties需要添加依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
</dependency>
```

```xml
<!-- 防止打包的时候把spring-boot-configuration-processor也放进去 -->
<!--If you have defined @ConfigurationProperties in your application, make sure to configure the spring-boot-maven-plugin to prevent the repackage goal from adding the dependency into the fat jar:-->

<project>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-configurationprocessor</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

```properties
# application.properties 中可以配置信息了并且会封装到JavaBean中
mycar.brand=BYD
mycar.price=100000
```

![image-20201228133011776](C:\Users\14666\AppData\Roaming\Typora\typora-user-images\image-20201228133011776.png)



**(2) @ConfigurationProperties + @EnableConfigurationProperties**

该方法用于将第三方包中的JavaBean和配置文件进行绑定。

```java
/**
 * 只有在容器中的组件才能使用spring的配置绑定功能
 */
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private Integer price;

    public Car() {
    }

    public Car(String brand, Integer price) {
        this.brand = brand;
        this.price = price;
    }
}
```

```xml
<!-- 使用@ConfigurationProperties需要添加依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
</dependency>
```

```properties
# application.properties 中可以配置信息了并且会封装到JavaBean中
mycar.brand=Audi
mycar.price=800000
```

```java
/**
 * @EnableConfigurationProperties：
 * 1、必须在配置类上使用
 * 2、开启Car的属性配置功能
 * 3、将Car这个组件自动注册到容器中
 */
@Configuration
@EnableConfigurationProperties(value = {Car.class})
public class PropertiesConfig {
}
```

![image-20201228134341344](C:\Users\14666\AppData\Roaming\Typora\typora-user-images\image-20201228134341344.png)



## 6. 自动配置原理入门

### 6.1. 引导加载自动配置类

```java
@SpringBootApplication：主启动类

等价于
    
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
```

**(1) @SpringBootConfiguration：代表当前是一个配置类**。

**(2) @ComponentScan：包扫描**。

**(3) @EnableAutoConfiguration**

```java
// @EnableAutoConfiguration 也是组合注解 
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {
```



- **@AutoConfigurationPackage**：自动配置包？

```java
// @Import 给容器中导入组件
@Import(AutoConfigurationPackages.Registrar.class)
public @interface AutoConfigurationPackage {
}

// 1、利用Registrar给容器导入一系列组件
// 2、Registrar将指定包下的所有组件注册到IOC容器中。
// 指定包 === MainApplication所在的包 == 标注SpringBootApplicaion所在的包
```





- **@Import(AutoConfigurationImportSelector.class)**

```shell
1、AutoConfigurationImportSelector：向IOC容器中批量注册组件。
2、从META-INF/spring.factories位置来加载文件。
	默认扫描我们当前系统里面所有的META-INF/spring.factories位置的文件
3、要加载的这些组件名称在spring.factories中是写好的。
位置：spring-boot-autoconfigure-2.4.1.jar/META-INF/spring.factories
```

![image-20201228141330589](C:\Users\14666\AppData\Roaming\Typora\typora-user-images\image-20201228141330589.png)



### 6.2. 按需开启配置项

```shell
虽然130个场景在spring-boot启动的时候就默认全部加载进来了，但是我们可以设置按需配置。

最终生效的自动配置是看我们具体导入了哪些包！！！
使用@Conditionalxxx注解的条件装配规则。
```



### 6.3. 修改默认配置

```java
/**
* 配置类作用：给容器中加入了文件上传解析器。
* 
* 配置生效规则：
* 1、@ConditionalOnBean(MultipartResolver.class)：容器中有这个类型组件配置类生效。
* 2、@ConditionalOnMissingBean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
*	MULTIPART_RESOLVER_BEAN_NAME = "multipartResolver"	
*	容器如果中没有"multipartResolver"这个名字的组件配置类生效。
*/
@Bean
@ConditionalOnBean(MultipartResolver.class)
@ConditionalOnMissingBean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
public MultipartResolver multipartResolver(MultipartResolver resolver) {
    // Detect if the user has created a MultipartResolver but named it incorrectly
    // 如果用户注册了文件上传解析器的组件但是命名不正确(不是multipartResolver)就给他它返回出去。
    // 防止用户配置的文件上传解析器名字不符合规范，返回出去名字就规范了！
    return resolver;
}
```



**自动配置规则：spring-boot默认会在底层配好所有的组件，但是如果用户自己配置了，就以用户的配置优先**。



**总结**：

- spring-boot 先加载所有的自动配置类；
- 每个自动配置类按照条件生效，一般默认都会绑定配置文件(properties)指定的值。
- 生效的配置类就会给容器中装配很多的组件；
- 只要容器中有这些组件，相当于这些功能就有了；
- 只要用户有自己配置的，就以用户配置的优先。
- 定制化配置：
  - 用户直接自己`@Bean`替换原生组件；
  - 用户修改配置文件绑定的值。

**xxxAutoConfiguration ---> 注册组件 ----> 从xxxProperties里面拿值 ----> 绑定applicaiton.properties**



### 6.4. 查看自动配置

**(1) 查看自动配置了那些**？

- 自己分析，引入场景对应的启动器，自动配置一般都生效了。
- **application.properties 可以将 debug 设置为 true 开启自动配置报告**。
  - Positive——生效的组件；
  - Negative——不生效的组件。

**(2) 是否要修改原生的配置**？

- 参照文档修改配置项。
  - 官方文档；
  - 自己分析xxxProperties绑定了配置文件的那些前缀。
- 自定义加入或者替换组件。



## 7. 开发工具

### 7.1. Lombok简化开发

**(1) spring-boot 已经对 Lombok 进行了版本控制，直接引入依赖即可**。

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```



**(2) Idea 中安装 Lombok 的插件**。

![image-20201228155952165](C:\Users\14666\AppData\Roaming\Typora\typora-user-images\image-20201228155952165.png)



### 7.2. Devtools

```xml
<!-- ctrl + F9 自动重启 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
</dependency>
```



# 二：spring-boot2-核心功能

## 1. 配置文件

### 1.1. YAML语法

- `key: value` kv之间有空格。
- 大小写敏感。
- 使用缩进表示层级关系。
- **缩进不允许使用tab，必须使用空格**。
- 缩进的空格数不重要，只要相同层级的元素左对齐即可。
- '#' 表示注释。
- **字符串一般无需加引号**。
  - 单引号和不加引号一样直接输出字符串；
  - 双引号会转义并输出。



### 1.2. 数据类型

- 字面量：单个的、不可再分的值。date、boolean、string、number、null。

```yaml
k: v
```

- 对象：键值对的集合。

```yaml
k:
	k1: v1
	k2: v2
	k3: v3
```

- 数组：一组按照次序排列的值。

```yaml
k:
 - v1
 - v2
 - v3
```



### 1.3. 示例

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private String name;
    private Integer weight;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "person")
public class Person {
    private String username;
    private Boolean boss;
    private Date birth;
    private Integer age;
    private Pet pet;
    private String[] interests;
    private List<String> animals;
    private Map<String, Object> score;
    private Set<Double> salary;
    private Map<String, List<Pet>> allPets;
}
```

```yaml
person:
  username: Ringo
  boss: true
  birth: 1997/11/13
  age: 21
  pet:
    name: cat
    weight: 5
  interests:
    - basketball
    - football
    - ping-pong
  animals:
    - rabbit
    - cat
    - dog
  score:
    Math: 80
    Chinsese: 90
    English: 80
  salary:
    - 1200.9
    - 1392.3
  all-pets:
    household:
      - name: Tom
        weight: 3
      - name: cat
        weight: 4
    non-household:
      - name: fish
        weight: 1
      - name: monkey
        weight: 50
```

![image-20201228175727662](C:\Users\14666\AppData\Roaming\Typora\typora-user-images\image-20201228175727662.png)



## 2. Web开发

### 2.1. 静态资源访问

```shell
# By default, Spring Boot serves static content from a directory called /static (or /public or /resources or /META-INF/resources) in the classpath
Maven的类路径(classpath) 就是 resources文件夹
```

![image-20201228201034486](C:\Users\14666\AppData\Roaming\Typora\typora-user-images\image-20201228201034486.png)

```properties
# By default, resources are mapped on /**, but you can tune that with the spring.mvc.static-pathpattern property.
# 1、默认情况下，访问这些静态资源需要匹配的路径是 /**，但是可以在配置文件中修改。

# 2、修改静态资源默认匹配路径
spring.mvc.static-path-pattern=/static/content/**

# 3、因为静态资源处理器默认规则是 /**, 所以所有不能处理的请求都交给静态资源处理器。
```

![image-20201228201425438](C:\Users\14666\AppData\Roaming\Typora\typora-user-images\image-20201228201425438.png)



### 2.2. 静态资源配置原理

```java
// 1、Spring MVC的自动配置类
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
    
// 2、资源处理的默认规则
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#addResourceHandlers()
```



**静态资源配置规则**：

```properties
# 1、默认是true 设置为false就禁用了静态资源访问
spring.web.resources.add-mappings=false

# 2、配置静态资源的缓存时间
spring.web.resources.cache.period=10000

# 3、修改静态资源映射
spring.mvc.static-path-pattern=/static/content/**
```



### 2.3. 请求参数处理

**(1)注解：@PathVariable、@RequestHeader、@RequestParam、@RequestBody**。

```java
package com.ymy.boot.controller;

import com.ymy.boot.bean.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    /**
     * 1、@PathVariable： 获取路径变量
     *    (1)@PathVariable("id") Integer id：获取单个变量，封装到参数中
     *    (2)@PathVariable Map<String, String> pv：获取所有路径变量封装到Map中
     *
     * 2、@RequestHeader：获取请求头
     *    (1)@RequestHeader("User-Agent") String userAgent： 获取请求头
     *    (2)@RequestHeader Map<String, String> userAgents)：获取所有请求头封装到Map中
     *
     * 3、@RequestParam：获取请求参数
     *    (1)@RequestParam("age") Integer age：获取单个请求参数
     *    (2)@RequestParam Map<String, String> info：将所有请求参数封装到Map中
     */
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id,
                                      @PathVariable("username") String username,
                                      @PathVariable Map<String, String> pv,
                                      @RequestHeader("User-Agent") String userAgent,
                                      @RequestHeader Map<String, String> userAgents,
                                      @RequestParam("age") Integer age,
                                      @RequestParam("interests") List<String> interests,
                                      @RequestParam Map<String, String> info) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("username", username);
        map.put("pv", pv);
        map.put("User-Agent", userAgent);
        map.put("User-Agents", userAgents);
        map.put("age", age);
        map.put("interests", interests);
        map.put("info", info);
        return map;
    }

    /**
     * @RequestBody： 获取请求体(只有post请求才有)
     *  可以接收JSON
     */
    @PostMapping("/user")
    public Map<String, Object> saveUser(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return map;
    }
}
```



**(2)矩阵变量：@MatrixVariable**

- 开启SpringBoot对矩阵变量的支持

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 开启SpringBoot矩阵变量的支持
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper=new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }
}
```



- 使用矩阵变量

```java
@RestController
public class HelloController {

    /**
     * 矩阵变量语法：/user;user=1;age=20
     * 注意：Spring Boot默认禁用了矩阵变量语法
     */
    @GetMapping("/user/{path}")
    public Map<String, Object> getUser(@MatrixVariable("id") Integer id,
                                       @MatrixVariable("age") Integer age,
                                       @PathVariable("path") String path) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("age", age);
        map.put("path", path);
        return map;
    }
}
```



**(3) 自定义Converter**

- **实体类**

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private String name;
    private Integer weight;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String id;
    private String name;
    private Pet pet;
}
```



- **处理表单数据提交**

```java
@RestController
public class PersonController {

    @PostMapping("/person")
    public Map<String, Object> info(Person person) {
        Map<String, Object> map = new HashMap<>();
        map.put("response", person);
        return map;
    }
}
```



- **自定义转换器：对请求参数进行的转换(不是JSON串)**

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    // 自定义转换器
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new Converter<String, Pet>() {
            @Override
            public Pet convert(String source) {
                if(StringUtils.hasLength(source)) {
                    Pet pet = new Pet();
                    String[] split = source.split(",");
                    pet.setName(split[0]);
                    pet.setWeight(Integer.parseInt(split[1]));
                    return pet;
                }
                return null;
            }
        });
    }
}
```

![image-20201229152713666](E:\Typora\image\image-20201229152713666.png)



### 2.4. 响应处理

**(1) 响应JSON**

```java
@RestController
public class ResponseTestController {
    @GetMapping("/test/person")
    public Person getPerson() {
        Person person = new Person("1", "zs", new Pet("Tom", 5));
        return person;
    }
}
```



**(2)spring MVC 支持哪些返回值**

```shell
ModelAndView
Model
View
ResponseEntity
StreamingResponseBody
HttpEntity
HttpHeaders
Callable
DeferredResult
ListenableFuture
有 @ModelAttribute 注解
有 @ResponseBody 注解
```



**(3)基于请求头的内容协商：根据客户端接收能力的不同，返回不同类型的媒体数据**。

- 引入XML依赖。

```xml
<!-- 引入依赖后重新启动项目 -->
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```

- postman分别测试返回 json 和 xml：只需要改变请求头中的 Accept。

![image-20201229161020010](E:\Typora\image\image-20201229161020010.png)

![image-20201229161053110](E:\Typora\image\image-20201229161053110.png)



**(4)自定义MessageConverter(基于请求头实现)**。

```java
// 1、Controller
@RestController
public class ResponseTestController {

    /**
     * @RestController 中集成了 @ResponseBody 表示响应数据而不是跳转页面
     *
     * 内容协商(一个请求适配多种媒体类型)：
     *  1、浏览器发请求返回xml     [application/xml]       JacksonXmlConverter
     *  2、Ajax请求返回json        [application/json]     JacksonJsonConverter
     *  3、自定义协商数据类型      [application/myself]  xxxConverter
     *
     * 步骤：
     *  1、添加自定义的MessageConverter进入系统底层
     *  2、系统底层就会统计出所有的MessageConverter能操作哪些类型
     */
    @GetMapping("/test/person")
    public Person getPerson() {
        Person person = new Person("1", "zs", new Pet("Tom", 5));
        return person;
    }
}

// 2、自定义MessageConverter
public class SelfCustomMessageConverter implements HttpMessageConverter<Person> {
 
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.isAssignableFrom(Person.class);
    }

    /**
     * 服务器要统计所有 MessageConverter 都能写出哪些内容类型
     *
     * application/myself
     * @return
     */
    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return MediaType.parseMediaTypes("application/myself");
    }

    @Override
    public Person read(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    // 自定义协议写出
    @Override
    public void write(Person person, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        // 我们自定义的数据类型
        String content =  person.getId() + ";" + person.getName() + ";" + person.getPet().getName() + ";" + person.getPet().getWeight();

        // 输出流
        OutputStream outputStream = outputMessage.getBody();
        outputStream.write(content.getBytes());
    }
}


// 3、扩展SpringBoot的MessageConverter配置
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 自定义 MessageConverter 可以响应 application/myself类型的数据
     * 扩展原来 MessageConverter
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new SelfCustomMessageConverter());
    }
}
```

![image-20201229165610433](E:\Typora\image\image-20201229165610433.png)



**(5)基于请求参数的内容协商**

```properties
# 4、支持基于参数的内容协商
spring.mvc.contentnegotiation.favor-parameter=true
# 默认就是format
#spring.mvc.contentnegotiation.parameter-name=format
```

![image-20201229170801279](E:\Typora\image\image-20201229170801279.png)



**(6) 自定义基于参数的内容协商：SpringMVC基于参数的内容协商只支持 xml 和 json 两种内容数据类型的内容协商**。

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 自定义基于参数的内容协商策略
     * 1、这会导致SpringBoot的默认配置不生效
     * 2、配置完基于参数的内容协商策略，还要把基于请求头的内容协商策略加上!
     *
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        // 1、基于参数的内容协商策略
        Map<String, MediaType> mediaTypes = new HashMap<>();
        mediaTypes.put("json", MediaType.APPLICATION_JSON);
        mediaTypes.put("xml", MediaType.APPLICATION_XML);
        // 添加自定义媒体类型
        mediaTypes.put("myself", MediaType.parseMediaType("application/myself"));
        ParameterContentNegotiationStrategy paramStrategy = new ParameterContentNegotiationStrategy(mediaTypes);

        // 2、基于请求头的内容协商策略：因为我们修改了SpringBoot的默认配置，要把请求头的内容协商加上！
        HeaderContentNegotiationStrategy headerStrategy = new HeaderContentNegotiationStrategy();

        configurer.strategies(Arrays.asList(paramStrategy, headerStrategy));
    }
}
```



### 2.5. 拦截器

```java
/**
 * 登录拦截
 * 1、配置好拦截器要拦截哪些请求
 * 2、把这些配置放在容器中
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser != null) {
            System.out.println("被LoginInterceptor放行");
            return true;
        }
        System.out.println("LoginInterceptor拦截");
        return false;
    }
}

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    // 配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new LoginInterceptor();
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")                     // 拦截路径
                .excludePathPatterns("/static/content/**"); // 放行路径
    }
}
```



## 3. 数据访问

### 3.1. 数据源的自动配置

```xml
<!--
1、Production database connections can also be auto-configured by using a pooling DataSource.(Spring BOOt 官方推荐  HikariCP 这个DataSource)。
2、If you use the spring-boot-starter-jdbc or spring-boot-starter-data-jpa “starters”, you automatically get a dependency to HikariCP. (以上两个starter就会自动添加 HikariCP 数据源)。-->

<!-- 导入JDBC依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jdbc</artifactId>
</dependency>
```

![image-20201230145758530](E:\Typora\image\image-20201230145758530.png)

```xml
<!-- 1、为什么SpringBoot官方没有给我们导入数据库驱动？-->
因为官方不知道我们接下来要操作什么数据库，要操作什么数据库就导入什么驱动就OK了！

<!-- 2、导入mysql驱动 SpringBoot 默认的驱动版本 8.0.22 我们使用的mysql为5.7 -->
<!-- 数据库版本和驱动的版本要一致 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```



### 3.2. 自动配置的类

```shell
1、DataSourceAutoConfiguration：数据源的自动配置
 (1) 修改DataSource的配置绑定类：DataSourceProperties；前缀：spring.datasource
 (2) 数据库连接池的配置，是容器中没有DataSource，SpringBoot才自动配置
 (3) 底层配置好的DataSource：Hikari

2、DataSourceTransactionManagerAutoConfiguration：事务管理器的自动配置

3、JdbcTemplateAutoConfiguration：JdbcTemplate的自动配置，可以对数据库进行CRUD
 (1) 修改JdbcTemplate的配置绑定类：JdbcProperties； 前缀：spring.jdbc
 (2) SpringBoot 在容器中加入了 JdbcTemplate 组件 可以自动注入
```



### 3.3. 数据源的配置

```properties
# 连接MySQL
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=333
spring.datasource.url=jdbc:mysql://39.97.3.60:3306/mybatis_plus?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
# 默认的数据源就是 hikari
spring.datasource.type=com.zaxxer.hikari.HikariDataSource  
```

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
</dependency>
```

```java
/**
 * 测试连接 MySQL
 */
@Slf4j
@SpringBootTest
public class TestDataBaseConnection {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    void test01() {
        String sql = "select id, name from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println(maps);
        Long count = jdbcTemplate.queryForObject("select count(*) from user", Long.class);
        log.info("记录数量：" + count);
    }
}
```



### 3.4. 整合Druid数据源

整合第三方技术的两种方式：

- 自定义。
- 找startr。

官网：https://github.com/alibaba/druid



> 一、自定义方式

```xml
<!-- 1、引入Druid DataSource -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.4</version>
</dependency>
```

```java
/**
 * 配置Druid DataSource
 */
@Configuration
@EnableConfigurationProperties(value = {LoginProperties.class})
public class DruidDataSourceConfig {

    /**
     * 将 Druid 的登录username、password抽取到配置文件中了
     */
    @Resource
    private LoginProperties login;

    /**
     * 1、DataSource的自动配置：
     *
     * @ConditionalOnMissingBean({ DataSource.class, XADataSource.class })
     * 当容器中没有DataSource的时候(我们自己没有配) 默认的Hikari 数据源才生效
     * <p>
     *
     * 2、设置 Druid DataSource:
     * druidDataSource.setUrl();
     * druidDataSource.setUsername();
     * druidDataSource.setPassword();
     * 我们发现Druid的 user、username、password和 spring.datasource下的配置是一样的，
     * 所以，直接和spring.datasource配置绑定即可
     *
     * 3、说明：在DruidDataSource中可以用setxxx来设置的都可以在配置文件中配置
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        /**
         * 1、统计监控信息 参考官方文档 配置_StatFilter
         * 2、配置防火墙
         */
        druidDataSource.setFilters("stat,wall");
        return druidDataSource;
    }

    /**
     * 配置 Druid 的监控页面
     * 参照官方文档：https://github.com/alibaba/druid
     */
    @Bean
    public ServletRegistrationBean DruidStatView() {
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean(statViewServlet, "/druid/*");

        // 设置 Druid登录的 username 和 password
        registrationBean.addInitParameter("resetEnable", "true");
        registrationBean.addInitParameter("loginUsername", login.getUsername());
        registrationBean.addInitParameter("loginPassword", login.getPassword());
        registrationBean.setUrlMappings(Arrays.asList("/druid/*"));

        return registrationBean;
    }

    /**
     * 配置WebStatFilter：用于采集web-jdbc关联监控的数据(参考官方文档)
     */
    @Bean
    public FilterRegistrationBean DruidWebStatFilter() {
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> registrationBean = new FilterRegistrationBean(webStatFilter);
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return registrationBean;
    }
}
```

```properties
# Druid数据源和其他数据源的url driver-class-name username password 是一样的
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=333
spring.datasource.url=jdbc:mysql://39.97.3.60:3306/mybatis_plus?characterEncoding=utf8&useSSL=false&serverTimezone=UTC
spring.datasource.druid.username=Ringo
spring.datasource.druid.password=123

# 默认的数据源就是 hikari
#spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
```

  

> 二、导入Starter

**(1) 引入依赖**

```xaml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.17</version>
</dependency>
```



**(2) 分析自动配置**

```java
DruidDataSourceAutoConfigure

1、扩展配置项：spring.datasource.druid

2、分析自动配置
@Configuration
@ConditionalOnClass({DruidDataSource.class})
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
@EnableConfigurationProperties({DruidStatProperties.class, DataSourceProperties.class})
@Import({DruidSpringAopConfiguration.class, DruidStatViewServletConfiguration.class, DruidWebStatFilterConfiguration.class, DruidFilterConfiguration.class})
public class DruidDataSourceAutoConfigure {
    
}

(1) DruidSpringAopConfiguration：用来监控Spring组件
(2) DruidStatViewServletConfiguration：监控页的配置
(3) DruidStatViewServletConfiguration：web监控配置
(4) DruidFilterConfiguration：所有Druid自己的Filter的配置
```

```yaml
# 数据源的配置
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://39.97.3.60:3306/mybatis_plus?characterEncoding=utf8&useSSL=false&serverTimezone=UTC
    username: root
    password: 333
#   参考：https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter
    druid:
      aop-patterns: com.ymy.boot.*            # 监控SpringBean
      filters: stat,wall                      # stat(sql监控) wall(防火墙)
      
      stat-view-servlet:                      # 监控页配置
        enabled: true
        login-username: admin
        login-password: 123
        reset-enable: false
      
      web-stat-filter:                        # 监控Web
        enabled: true
        url-pattern: /*
        exclusions: '*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*'
     
     filter:
        stat:                                 # 对上面filter的详细配置
          slow-sql-millis: 1000
          log-slow-sql: true
          enabled: true
        wall:
          enabled: true
```



### 3.5. MyBatis Plus

**(1) 依赖**

```xml
<!-- 依赖 -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.4.1</version>
</dependency>
```



**(2) 分析自动配置**

```shell
1、MybatisPlusAutoConfiguration：配置类; MybatisPlusProperties：配置项的绑定。
prefix = mybatis-plus

2、SqlSessionFactory：myBatisPlus 已经在容器中注册好了，该组件是myBatis的核心组件。使用的是SpringBoot中配置的数据源

3、mapperLocations：自动配置好的。Locations of MyBatis mapper files.
默认是：classpath*:/mapper/**/*.xml

4、SqlSessionTemplate：容器中自动配置好。

5、AutoConfiguredMapperScannerRegistrar：@Mapper标注的接口也会被自动扫描
```



**(3) 使用myBatis Plus**

在 Spring Boot 启动类中添加 `@MapperScan` 注解，扫描 Mapper 文件夹：

```java
/**
 * @MapperScan： 全局扫描,有这个就不用在接口上写 @Mapper 了
 */
@MapperScan("com.ymy.boot.mapper")
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
```



创建用户类

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String email;
}
```



创建 mapper

```java
/**
 * 只需要我们的 Mapper 继承BaseMapper 就可以拥有CRUD能力
 */
public interface UserMapper extends BaseMapper<User> {
}
```



测试

```java
@Slf4j
@SpringBootTest
public class TestMyBatisPlusMapper {

    @Resource
    private UserMapper userMapper;

    // 测试查询
    @Test
    void testQuery() {
        User user = userMapper.selectById(1);
        log.info("用户信息：" + user);
    }
}
```



### 3.6. Redis

```shell
docker run --name redis -d -p 6379:6379 \
-v /root/redis/data:/data \
-v /root/redis/conf/redis.conf:/usr/local/etc/redis/redis.conf \
redis:6.0.4 \
redis-server /usr/local/etc/redis/redis.conf 
```

**(1) 依赖**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

**(2) 自动配置**

```shell
1、RedisAutoConfiguration：自动配置类；RedisProperties：配置绑定 prefix = spring.redis。

2、@Import({ LettuceConnectionConfiguration.class, JedisConnectionConfiguration.class })：连接工厂是准备好的，同时支持两种客户端来操作Redis

3、SpringBoot自动装配了 RedisTemplate、StringRedisTemplate。
RedisTemplate：k-v都是Object。
StringRedisTemplate：k-v都是String。
```

**(3) 配置文件**

```yaml
spring:
  redis:
    host: 39.97.3.60
    port: 6379
    client-type: lettuce
    database: 0
```



**(4) Redis操作与统计小实验**

自己定义拦截器

```java
@Slf4j
@Component
public class RedisUrlInterceptor implements HandlerInterceptor {

    @Resource
    private StringRedisTemplate template;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        log.info("url: " + uri);
        Long size = template.opsForValue().size(uri);
        if(size == 0) {
            template.opsForValue().set(uri, "1");
        }
        template.opsForValue().increment(uri);
        return true;
    }
}
```



添加拦截器

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private HandlerInterceptor redisUrlInterceptor;

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(redisUrlInterceptor)
                .addPathPatterns("/**");
    }
}
```



测试

![image-20210101120721485](E:\Typora\image\image-20210101120721485.png)



## 4. 单元测试

### 4.1. JUnit5的变化

**Spring Boot 2.2.0 版本开始引入 JUnit 5 作为单元测试默认库**

作为最新版本的JUnit框架，JUnit5与之前版本的Junit框架有很大的不同。由三个不同子项目的几个不同模块组成。

> **JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage**

**JUnit Platform**: Junit Platform是在JVM上启动测试框架的基础，不仅支持Junit自制的测试引擎，其他测试引擎也都可以接入。

**JUnit Jupiter**: JUnit Jupiter提供了JUnit5的新的编程模型，是JUnit5新特性的核心。内部 包含了一个**测试引擎**，用于在Junit Platform上运行。

**JUnit Vintage**: 由于JUint已经发展多年，为了照顾老的项目，JUnit Vintage提供了兼容JUnit4.x,Junit3.x的测试引擎。

![image-20210101122145310](E:\Typora\image\image-20210101122145310.png)



依赖：

```xml
<!-- 测试 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

![image-20210101122911960](E:\Typora\image\image-20210101122911960.png)

注意：

**SpringBoot 2.4 以上版本移除了默认对 Vintage 的依赖。如果需要兼容junit4需要自行引入（不能使用junit4的功能 @Test）**

**JUnit 5’s Vintage Engine Removed from** **`spring-boot-starter-test,如果需要继续兼容junit4需要自行引入vintage`**

```xml
<!-- 兼容Junit4 -->
<dependency>
    <groupId>org.junit.vintage</groupId>
    <artifactId>junit-vintage-engine</artifactId>
    <scope>test</scope>
    <exclusions>
        <exclusion>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest-core</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```



Spring Boot 2.4以后我们使用的 `@Test` 注解都是 `Junit5` 支持的。

![image-20210101123512328](E:\Typora\image\image-20210101123512328.png)



现在版本：

```java
@SpringBootTest
class Boot05WebAdminApplicationTests {


    @Test
    void contextLoads() {

    }
}
```



### 4.2. JUnit5常用注解

官网：https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations

**@Test :**表示方法是测试方法。但是与JUnit4的@Test不同，他的职责非常单一不能声明任何属性，拓展的测试将会由Jupiter提供额外测试

**@ParameterizedTest :**表示方法是参数化测试，下方会有详细介绍

**@RepeatedTest :**表示方法可重复执行，下方会有详细介绍

**@DisplayName :**为测试类或者测试方法设置展示名称

**@BeforeEach :**表示在每个单元测试之前执行

**@AfterEach :**表示在每个单元测试之后执行

**@BeforeAll :**表示在所有单元测试之前执行

**@AfterAll :**表示在所有单元测试之后执行

**@Tag :**表示单元测试类别，类似于JUnit4中的@Categories

**@Disabled :**表示测试类或测试方法不执行，类似于JUnit4中的@Ignore

**@Timeout :**表示测试方法运行如果超过了指定时间将会返回错误

**@ExtendWith :**为测试类或测试方法提供扩展类引用

```java
/**
 * @SpringBootTest: 这是个组合注解，使用了 Spring 来做测试
 *
 * @BootstrapWith(SpringBootTestContextBootstrapper.class)
 * @ExtendWith(SpringExtension.class)
 *
 * 如果不用 Spring 来做测试 可以使用 @ExtendWith 来整合其他平台来做开发
 */
@Slf4j
@SpringBootTest
public class TestJunit5 {

    /**
     * @DisplayName： 自定义测试类或测试方法的名字
     */
    @Test
    @DisplayName(value = "测试@DisplayName")
    void testDisplayName() {
        log.info("测试1");
    }


    /**
     * @Disabled: 禁用测试方法
     */
    @Test
    @Disabled(value = "不想让test2执行")
    @DisplayName(value = "测试2")
    void test2() {
        log.info("测试2");
    }

    /**
     * @RepeatedTest：重复测试
     * 1、value：设置重复的次数
     */
    @RepeatedTest(value = 3)
    void repeatedTest() {
        log.info("重复测试..");
    }

    /**
     * @BeforeEach: 在每个单元测试之前执行。
     * 例如：执行testDisplayName()之前，先执行testBeforeEach()。
     */
    @BeforeEach
    void testBeforeEach() {
        log.info("测试就要开始了...");
    }

    /**
     * @AfterEach： 在每个单元测试运行完成后执行
     */
    @AfterEach
    void testAfterEach() {
        log.info("测试就要结束了...");
    }


    /**
     * @BeforeAll： 在所有单元测试执行之前运行
     * 1、必须是静态方法
     * 2、直接运行测试类即可
     */
    @BeforeAll
    static void testBeforeAll() {
        log.info("在所有单元测试运行之前执行...");
    }

    /**
     * @AfterAll： 在所有单元测试执行完成后运行
     * 1、必须是静态方法
     * 2、直接运行测试类即可
     */
    @AfterAll
    static void testAfterAll() {
        log.info("在所有单元测试运行之后执行...");
    }

    /**
     * @Timeout： 设置超时时间，超时就报错
     * 1、value：时间
     * 2、unit：单位
     */
    @Test
    @Timeout(value = 1L, unit = TimeUnit.MILLISECONDS)
    void testTimeout() throws InterruptedException {
        Thread.sleep(600);
    }
}
```



### 4.3. 断言机制

断言（assertions）是测试方法中的核心部分，用来对测试需要满足的条件进行验证。**这些断言方法都是 org.junit.jupiter.api.Assertions 的静态方法**。JUnit 5 内置的断言可以分成如下几个类别：

**检查业务逻辑返回的数据是否合理。**

**所有的测试运行结束以后，会有一个详细的测试报告；**

**注意：断言失败，后面的代码就不会执行了！**

**(1) 简单断言**

用来对单个值进行简单的验证。如：

| 方法            | 说明                                 |
| --------------- | ------------------------------------ |
| assertEquals    | 判断两个对象或两个原始类型是否相等   |
| assertNotEquals | 判断两个对象或两个原始类型是否不相等 |
| assertSame      | 判断两个对象引用是否指向同一个对象   |
| assertNotSame   | 判断两个对象引用是否指向不同的对象   |
| assertTrue      | 判断给定的布尔值是否为 true          |
| assertFalse     | 判断给定的布尔值是否为 false         |
| assertNull      | 判断给定的对象引用是否为 null        |
| assertNotNull   | 判断给定的对象引用是否不为 null      |

```java
// Assertions种都是静态方法，直接静态导入就可以直接使用静态方法了
import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试断言
 */
@SpringBootTest
public class TestAssertion {


    /**
     * 测试简单断言
     */
    @Test
    @DisplayName(value = "测试简单断言")
    void testSimpleAssertion() {
        // 判断两个对象或者原始类型是否相等
        User user1 = new User(1, "zs", 18, "zs@qq.com");
        User user2 = new User(1, "zs", 18, "zs@qq.com");
        User user3 = new User(1, "zs", 20, "zs@qq.com");

        // 1、user1 和 user2 是相等的 因此没有报错
        assertEquals(user1, user2);

        /**
         * 2、org.opentest4j.AssertionFailedError:
         * expected: not equal but was: <User(id=1, name=zs, age=18, email=zs@qq.com)>
         */
        assertNotEquals(user1, user2);

        // user1 和 user3 确实不等，这里没有报错
        assertNotEquals(user1, user3);

        /**
         * 3、assertSame() 判断两个对象引用是否相等
         * user1 和 user2 显然指向两个对象 引用不相等
         */
        assertSame(user1, user2);
        assertSame(user1, user1);
    }
}
```



**(2) 数组断言**

通过 `assertArrayEquals `方法来判断两个对象或原始类型的数组是否相等

```java
// Assertions种都是静态方法，直接静态导入就可以直接使用静态方法了
import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试断言
 */
@SpringBootTest
public class TestAssertion {

    /**
     * 测试数组断言
     */
    @Test
    @DisplayName(value = "测试数组断言")
    void testArraysAssertion() {
        Integer[] a1 = {1, 2, 3, 4, 5};
        Integer[] a2 = {2, 3, 4, 5, 6};

        /**
         * org.opentest4j.AssertionFailedError:
         * 数组不相等 ==> array contents differ at index [0], expected: <1> but was: <2>
         */
        assertArrayEquals(a1, a2, "数组不相等");
    }
}
```



**(3) 组合断言**

assertAll 方法接受多个 org.junit.jupiter.api.Executable 函数式接口的实例作为要验证的断言，可以通过 lambda 表达式很容易的提供这些断言。

```java
// Assertions种都是静态方法，直接静态导入就可以直接使用静态方法了
import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试断言
 */
@SpringBootTest
public class TestAssertion {

    /**
     * 测试组合断言
     *  组合断言：有一个是错的，整个组合就是错的！
     */
    @Test
    @DisplayName(value = "测试组合断言")
    void testAssertAll() {

        /**
         * "Math"：给组合断言起一个名字
         */
        assertAll("Math",
                () -> assertEquals(1 + 2, 4),
                () -> assertEquals(2, 2));
    }
}
```



**(4) 异常断言**

在JUnit4时期，想要测试方法的异常情况时，需要用**@Rule**注解的ExpectedException变量还是比较麻烦的。而JUnit5提供了一种新的断言方式``Assertions.assertThrows()` ,配合函数式编程就可以进行使用。

```java
// Assertions种都是静态方法，直接静态导入就可以直接使用静态方法了
import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试断言
 */
@SpringBootTest
public class TestAssertion {


    /**
     * 测试异常断言
     */
    @Test
    @DisplayName(value = "测试异常断言")
    void testExceptionAssert() {
        /**
         * 期望的是抛运行时异常：异常断言这样就不会报错
         */
        assertThrows(RuntimeException.class,
                () -> assertEquals(2 / 0, 3));

        /**
         * 这样就没有抛异常，异常断言就会报错
         * org.opentest4j.AssertionFailedError:
         * Unexpected exception type thrown ==> expected: <java.lang.RuntimeException>
         * but was: <org.opentest4j.AssertionFailedError>
         */
        assertThrows(RuntimeException.class,
                () -> assertEquals(2, 3));
    }
}
```



**(5) 超时断言**

```java
@Test
@DisplayName("超时测试")
public void timeoutTest() {
    //如果测试方法时间超过1s将会异常
    Assertions.assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(500));
}
```



**(6) 快速失败**

通过 fail 方法直接使得测试失败

```java
// Assertions种都是静态方法，直接静态导入就可以直接使用静态方法了
import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试断言
 */
@SpringBootTest
public class TestAssertion {

    /**
     * 测试快速失败
     */
    @Test
    @DisplayName(value = "测试快速失败")
    void fastFail() {
        int i = 2;
        if (i == 2) {
            fail("快速失败");
        }
    }
}
```



### 4.4. 前置条件

JUnit 5 中的前置条件（**assumptions【假设】**）类似于断言，不同之处在于**不满足的断言会使得测试方法失败**，而不满足的**前置条件只会使得测试方法的执行终止**。前置条件可以看成是测试方法执行的前提，当该前提不满足时，就没有继续执行的必要。

```java
// Assumptions 前置条件【假设】
import static org.junit.jupiter.api.Assumptions.*;

@SpringBootTest
public class TestAssumption {

    /**
     * 测试：简单假设
     * 1、假设成功 ===> 确实是对的 ===> 成功
     * 2、假设失败 ===> 确实失败了 ===> 成功
     * 3、其他均假设失败
     * <p>
     * assumeTrue()/assumeFalse() 可以使用lambda表达式
     */
    @Test
    @DisplayName(value = "测试简单假设")
    void testSimpleAssumption() {
        User user1 = new User(1, "zs", 18, "zs@qq.com");
        User user2 = new User(1, "zs", 18, "zs@qq.com");
        User user3 = new User(2, "zs", 18, "zs@qq.com");

        // 假设成功：user equals user2 ==> 假设成功
        assumeTrue(user1.equals(user2));

        // 假设成功：但是 user1 !equals user3 ==> 假设失败
        assumeTrue(user1.equals(user3));

        // 假设失败：user1 ! equals user3 ==> 假设成功
        assumeFalse(() -> user1.equals(user3));
    }

    /**
     * 测试：assumingThat()
     * assumingThat(boolean assumption, Executable executable)：
     *  当满足条件时，后面的 Executable 才会执行， 同样可使用 lambda 表达式
     */
    @Test
    @DisplayName(value = "assumingThat")
    void testAssumingThat() {
        User user1 = new User(1, "zs", 18, "zs@qq.com");
        User user2 = new User(1, "zs", 18, "zs@qq.com");

        assumingThat(user1.equals(user2),
                () -> System.out.println("success"));
    }
}
```

assumeTrue 和 assumFalse 确保给定的条件为 true 或 false，不满足条件会使得测试执行终止。assumingThat 的参数是表示条件的布尔值和对应的 Executable 接口的实现对象。只有条件满足时，Executable 对象才会被执行；当条件不满足时，测试执行并不会终止。



### 4.5. 嵌套测试

JUnit 5 可以通过 Java 中的内部类和`@Nested `注解实现嵌套测试，从而可以更好的把相关的测试方法组织在一起。在内部类中可以使用`@BeforeEach` 和`@AfterEach` 注解，而且嵌套的层次没有限制。

**注意：嵌套测试外层不可以驱动内层，但是内层可以驱动外层**。

```java
package com.ymy.boot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 嵌套测试
 */
@SpringBootTest
public class TestNest {

    private Stack<Object> stack;

    @Test
    @DisplayName(value = "new stack()")
    void isInstanceWithNew() {
        new Stack<>();

        /**
         * 在嵌套测试的情况下：
         * 外层的Test不能驱动内层的Before(After) Each/All 之类的方法 提前/之后运行
         */
        assertNull(stack);
    }

    @Nested
    @DisplayName("when new")
    class WhenNew {

        @BeforeEach
        void createNewStack() {
            stack = new Stack<>();
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            assertTrue(stack.isEmpty());
        }

        @Test
        @DisplayName("throws EmptyStackException when popped")
        void throwsExceptionWhenPopped() {
            assertThrows(EmptyStackException.class, stack::pop);
        }

        @Test
        @DisplayName("throws EmptyStackException when peeked")
        void throwsExceptionWhenPeeked() {
            assertThrows(EmptyStackException.class, stack::peek);
        }

        @Nested
        @DisplayName("after pushing an element")
        class AfterPushing {

            String anElement = "an element";

            @BeforeEach
            void pushAnElement() {
                stack.push(anElement);
            }

            /**
             * 内层的Test：
             * 可以驱动外层的Before(After) Each/All 之类的方法 提前/之后运行
             */
            @Test
            @DisplayName("it is no longer empty")
            void isNotEmpty() {
                assertFalse(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when popped and is empty")
            void returnElementWhenPopped() {
                assertEquals(anElement, stack.pop());
                assertTrue(stack.isEmpty());
            }

            @Test
            @DisplayName("returns the element when peeked but remains not empty")
            void returnElementWhenPeeked() {
                assertEquals(anElement, stack.peek());
                assertFalse(stack.isEmpty());
            }
        }
    }
}
```



### 4.6. 参数化测试

参数化测试是JUnit5很重要的一个新特性，它使得用不同的参数多次运行测试成为了可能，也为我们的单元测试带来许多便利。

利用**@ValueSource**等注解，指定入参，我们将可以使用不同的参数进行多次单元测试，而不需要每新增一个参数就新增一个单元测试，省去了很多冗余代码。

**@ValueSource**: 为参数化测试指定入参来源，支持八大基础类以及String类型,Class类型

**@NullSource**: 表示为参数化测试提供一个null的入参

**@EnumSource**: 表示为参数化测试提供一个枚举入参

**@CsvFileSource**：表示读取指定CSV文件内容作为参数化测试入参

**@MethodSource**：表示读取指定方法的返回值作为参数化测试入参(注意方法返回需要是一个流)

当然如果参数化测试仅仅只能做到指定普通的入参还达不到让我觉得惊艳的地步。让我真正感到他的强大之处的地方在于他可以支持外部的各类入参。如:CSV,YML,JSON 文件甚至方法的返回值也可以作为入参。只需要去实现**ArgumentsProvider**接口，任何外部文件都可以作为它的入参。

```java
package com.ymy.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 参数化测试
 */
@Slf4j
@SpringBootTest
public class TestParameterized {

    /**
     * @ParameterizedTest： 表示参数化测试
     * @ValueSource： 指明参数源
     */
    @ParameterizedTest
    @ValueSource(strings = {"one", "two", "three", "four", "five"})
    @DisplayName(value = "参数化测试1")
    void testParameter1(String s) {
        log.info(s);
        assertTrue(StringUtils.isNotBlank(s));
    }


    /**
     * @MethodSource： 表示参数来源是方法，该方法必须返回 Stream 且是静态方法
     */
    @ParameterizedTest
    @MethodSource(value = {"method"})
    @DisplayName(value = "参数化测试2")
    void testParameter2(String s) {
        log.info(s);
        assertNotNull(s);
    }

    static Stream<String> method() {
        return Stream.of("one", "two", "three");
    }
}
```



### 4.7. 迁移指南

从`Junit4`迁移的时候需要注意如下的变化：

- 注解在 org.junit.jupiter.api 包中，断言在 org.junit.jupiter.api.Assertions 类中，前置条件在 org.junit.jupiter.api.Assumptions 类中。
- 把@Before 和@After 替换成@BeforeEach 和@AfterEach。
- 把@BeforeClass 和@AfterClass 替换成@BeforeAll 和@AfterAll。
- 把@Ignore 替换成@Disabled。
- 把@Category 替换成@Tag。
- 把@RunWith、@Rule 和@ClassRule 替换成@ExtendWith。



## 5. 指标监控

### 5.1. SpringBoot Actuator

未来每一个微服务在云上部署以后，我们都需要对其进行监控、追踪、审计、控制等。SpringBoot就抽取了Actuator场景，使得我们每个微服务快速引用即可获得生产级别的应用监控、审计等功能。

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

![image-20210102113818324](E:\Typora\image\image-20210102113818324.png)



**1.x 和 2.x 的不同**

![image-20210102112447313](E:\Typora\image\image-20210102112447313.png)



**如何使用**

引入 `spring-boot-starter-actuator` 直接访问 /actuator ，Spring Boot 默认开放了 2 个 `Endpoint：health、info`。

**注：可用的 Endpoints 参考Spring Boot官方文档**。

![image-20210102114357244](E:\Typora\image\image-20210102114357244.png)



**暴露Endpoints**

```yaml
# management 是所有actuator(指标监控)的配置
management:
  endpoints:
    enabled-by-default: true # 默认启用所有的Endpoints
    web:
      exposure:
        include: '*' # 以web方式暴露所有EndPoints
```

测试：

http://localhost:8080/actuator/beans

http://localhost:8080/actuator/configprops

http://localhost:8080/actuator/metrics

http://localhost:8080/actuator/metrics/jvm.gc.pause

[http://localhost:8080/actuator/](http://localhost:8080/actuator/metrics)endpointName/detailPath
。。。。。。



### 5.2. Actuator Endpoint

**(1) 最常使用的Endpoint**

| ID                 | 描述                                                         |
| ------------------ | ------------------------------------------------------------ |
| `auditevents`      | 暴露当前应用程序的审核事件信息。需要一个`AuditEventRepository组件`。 |
| `beans`            | 显示应用程序中所有Spring Bean的完整列表。                    |
| `caches`           | 暴露可用的缓存。                                             |
| `conditions`       | 显示自动配置的所有条件信息，包括匹配或不匹配的原因。         |
| `configprops`      | 显示所有`@ConfigurationProperties`。                         |
| `env`              | 暴露Spring的属性`ConfigurableEnvironment`                    |
| `flyway`           | 显示已应用的所有Flyway数据库迁移。 需要一个或多个`Flyway`组件。 |
| `health`           | 显示应用程序运行状况信息。                                   |
| `httptrace`        | 显示HTTP跟踪信息（默认情况下，最近100个HTTP请求-响应）。需要一个`HttpTraceRepository`组件。 |
| `info`             | 显示应用程序信息。                                           |
| `integrationgraph` | 显示Spring `integrationgraph` 。需要依赖`spring-integration-core`。 |
| `loggers`          | 显示和修改应用程序中日志的配置。                             |
| `liquibase`        | 显示已应用的所有Liquibase数据库迁移。需要一个或多个`Liquibase`组件。 |
| `metrics`          | 显示当前应用程序的“指标”信息。                               |
| `mappings`         | 显示所有`@RequestMapping`路径列表。                          |
| `scheduledtasks`   | 显示应用程序中的计划任务。                                   |
| `sessions`         | 允许从Spring Session支持的会话存储中检索和删除用户会话。需要使用Spring Session的基于Servlet的Web应用程序。 |
| `shutdown`         | 使应用程序正常关闭。默认禁用。                               |
| `startup`          | 显示由`ApplicationStartup`收集的启动步骤数据。需要使用`SpringApplication`进行配置`BufferingApplicationStartup`。 |
| `threaddump`       | 执行线程转储。                                               |





如果您的应用程序是Web应用程序（Spring MVC，Spring WebFlux或Jersey），则可以使用以下附加端点：

| ID           | 描述                                                         |
| ------------ | ------------------------------------------------------------ |
| `heapdump`   | 返回`hprof`堆转储文件。                                      |
| `jolokia`    | 通过HTTP暴露JMX bean（需要引入Jolokia，不适用于WebFlux）。需要引入依赖`jolokia-core`。 |
| `logfile`    | 返回日志文件的内容（如果已设置`logging.file.name`或`logging.file.path`属性）。支持使用HTTP`Range`标头来检索部分日志文件的内容。 |
| `prometheus` | 以Prometheus服务器可以抓取的格式公开指标。需要依赖`micrometer-registry-prometheus`。 |

最常用的Endpoint

- **Health：监控状况**
- **Metrics：运行时指标**
- **Loggers：日志记录**



**(2) Health Endpoint**

健康检查端点，我们一般用于在云平台，平台会定时的检查应用的健康状况，我们就需要Health Endpoint可以为平台返回当前应用的一系列组件健康状况的集合。

重要的几点：

- health endpoint返回的结果，应该是一系列健康检查后的一个汇总报告
- 很多的健康检查默认已经自动配置好了，比如：数据库、redis等
- 可以很容易的添加自定义的健康检查机制

```yaml
# management 是所有actuator(指标监控)的配置
# management.endpoints 配置所有 Endpoints
# management.endpoint.端点名.xxx  配置具体的某个 Endpoints
management:
  endpoints:
    enabled-by-default: true # 默认启用所有的Endpoints
    web:
      exposure:
        include: '*' # 以web方式暴露所有EndPoints
  endpoint:
    health:
      show-details: always
```



**(3) Metrics Endpoint**

提供详细的、层级的、空间指标信息，这些信息可以被pull（主动推送）或者push（被动获取）方式得到；

- 通过Metrics对接多种监控系统
- 简化核心Metrics开发
- 添加自定义Metrics或者扩展已有Metrics

**注：Metrics 查看详细信息需要发送二次请求**。



**(4) 管理 Endpoints**

```yaml
# management 是所有actuator(指标监控)的配置
# management.endpoints 配置所有 Endpoints ===> 相当于总开关
# management.endpoint.端点名.xxx  配置具体的某个 Endpoints  ===> 相当于分开关
management:
  endpoints:
    enabled-by-default: false # 默认关闭所有的Endpoints
    web:
      exposure:
        include: '*' # 以web方式暴露所有EndPoints
  endpoint:
    health:
      show-details: always
      enabled: true        # 上边关闭所有的 Endpoints 这里单独开启 health
    metrics:
      enabled: true        # 这里单独开启 metrics
```



### 5.3. 定制Endpoint

**(1) 定制Health信息**

```java
package com.ymy.boot.endpoint.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 自定义健康检查组件要以HealthIndicator为后缀命名
 * 但是，查看健康信息时，显示的就是 "my"
 */
@Component
public class MyHealthIndicator implements HealthIndicator {

    @Resource
    private DataSource dataSource;

    @Override
    public Health health() {
        if (dataSource != null) {
            return Health.up().build();
        }
        return Health.down().withDetail("没有配置数据源", 8848).build();
    }
}
```

![image-20210102144211610](E:\Typora\image\image-20210102144211610.png)



**(2) 定制info**

方式一：编写配置文件

```yaml
management:
  endpoints:
    enabled-by-default: false # 默认关闭所有的Endpoints
    web:
      exposure:
        include: '*' # 以web方式暴露所有EndPoints
  endpoint:
    info:
      enabled: true
# 设置 info 的信息
info:
  app-name: spring-boot-application
  verson: 2021
  author: Ringo
  email: Ringo@qq.com
  mavenProjectName: @project.artifactId@  #使用@@可以获取maven的pom文件值
  mavenProjectVersion: @project.version@
```

![image-20210102150218722](E:\Typora\image\image-20210102150218722.png)



方式二：编写InfoContributor

```java
package com.ymy.boot.endpoint.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ExampleInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("example", Collections.singletonMap("key", "value"));
    }
}
```

![image-20210102150716086](E:\Typora\image\image-20210102150716086.png)



**(3) 定制metrics**

**SpringBoot支持自动适配的Metrics**

- JVM metrics, report utilization of:

- - Various memory and buffer pools
  - Statistics related to garbage collection
  - Threads utilization
  - Number of classes loaded/unloaded

- CPU metrics
- File descriptor metrics
- Kafka consumer and producer metrics
- Log4j2 metrics: record the number of events logged to Log4j2 at each level
- Logback metrics: record the number of events logged to Logback at each level
- Uptime metrics: report a gauge for uptime and a fixed gauge representing the application’s absolute start time
- Tomcat metrics (`server.tomcat.mbeanregistry.enabled` must be set to `true` for all Tomcat metrics to be registered)
- [Spring Integration](https://docs.spring.io/spring-integration/docs/5.4.1/reference/html/system-management.html#micrometer-integration) metrics



**增加定制Metrics(指标)**

```java
package com.ymy.boot.controller;

// Counter 和 MeterRegistry(Meter注册中心) 都是io.micrometer.core.instrument下的
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private Counter counter;

    public HelloController(MeterRegistry meterRegistry) {
        this.counter = meterRegistry.counter("HelloController.hello().count");
    }

    @GetMapping("/hello")
    public String hello() {
        // 这里就能统计 hello() 被调用的次数
        counter.increment();
        return "Hello!";
    }
}
```



**(4) 自定义监控端点(Endpoint)**

```java
package com.ymy.boot.endpoint.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * 自定义 Endpoint
 *
 * @Endpoint: 1、id：给自己的 Endpoint 命名
 * 2、enableByDefault：默认启动该Endpoint
 */
@Slf4j
@Component
@Endpoint(id = "myPoint", enableByDefault = true)
public class MyEndpoint {

    /**
     * Endpoint 的读操作
     * 发送请求：http://localhost:8080/actuator/myPoint
     */
    @ReadOperation
    public Map<String, Object> getDockerInfo() {
        return Collections.singletonMap("dockerInfo", "docker started");
    }

    @WriteOperation
    public void stopDocker() {
        log.info("Docker stopped...");
    }
}
```

<img src="E:\Typora\image\image-20210102165131383.png" alt="image-20210102165131383" style="zoom:150%;" />



### 5.4.  spring-boot-admin

**(1) 新建一个项目添加依赖**

```xml
<!-- web -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- spring-boot-admin-server -->
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-server</artifactId>
    <version>2.3.1</version>
</dependency>
```



**(2) 主启动类上添加`@EnableAdminServer`**。

```java
@EnableAdminServer
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
```



**(3) 设置 `spring-boot-admin-server` 端口**。

```yaml
server:
  port: 8888
```

![image-20210102210159847](E:\Typora\image\image-20210102210159847.png)



**(4) 在我们的项目上添加客户端依赖 `spring-boot-admin-starter-client`** 。

```xml
<!-- spring-boot-admin-starter-client -->
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client</artifactId>
    <version>2.3.1</version>
</dependency>
```



**(5) 我们的项目添加配置**

```properties
spring.boot.admin.client.url=http://localhost:8888/
spring.boot.admin.client.instance.prefer-ip=true    # 使用IP注册     
spring.application.name=spring-boot-001-mybatis-plus-test

management.endpoints.web.exposure.include=*
```



**(6) 启动我们的项目**。

![image-20210102211507798](E:\Typora\image\image-20210102211507798.png)



## 6. 高级特性

### 6.1. Profile环境切换

**(1) application-profile功能**

- 默认配置文件  application.yaml；任何时候都会加载
- 指定环境配置文件  application-{env}.yaml
- 激活指定环境

- - 配置文件激活
  - 命令行激活：java -jar xxx.jar --**spring.profiles.active=prod  --person.name=haha**

- - - **修改配置文件的任意值，命令行优先**

- 默认配置与环境配置同时生效
- 同名配置项，profile配置优先

![image-20210102215344255](E:\Typora\image\image-20210102215344255.png)



**(2) @Profile条件装配功能：@Profile不仅可以标注在类上，还可以标注在方法上**。

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Profile(value = {"prod"})
@Component
@ConfigurationProperties(prefix = "person")
public class Boss implements Person {
    private String name;
}
```

![image-20210103082849553](E:\Typora\image\image-20210103082849553.png)

```properties
# 默认配置文件永远会加载
# 这里激活目标环境
# 激活哪个配置文件,哪个就会生效
spring.profiles.active=test
```



**(3) Profiles Groups：批量加载，配置文件形成互补**

Occasionally the profiles that you define and use in your application are too fine-grained and become cumbersome to use. For example, you might have proddb and prodmq profiles that you use to enable database and messaging features independently.

有时候在应用中使用的配置文件会很大。例如，可能会使用 `proddb、prodmq`这两个配置文件来独立使用数据库和消息队列的特性。

To help with this, Spring Boot lets you define profile groups. A profile group allows you to define a logical name for a related group of profiles.

为了解决这个问题，spring-boot 提供了自定义 profile groups 的功能。profile group 允许用户定义一个逻辑上的名字，来和profile group相关联。

```properties
# properties
spring.profiles.group.production[0]=proddb
spring.profiles.group.production[1]=prodmq
```



```yaml
spring:
  profiles:
  group:
  production:
  - "proddb"
  - "prodmq"
```



例如：

```yaml
# application-prod-db.yaml
prod:
  db: prod-db生效
```

```yaml
# application-prod-mq.yaml
prod:
  mq: prod-mq生效
```

```properties
# application.properties
# profile group的命名
spring.profiles.group.production[0]=prod-db
spring.profiles.group.production[1]=prod-mq

# 激活组
spring.profiles.active=production
```

```java
@RestController
public class HelloController {

    @Value("${prod.db}")
    private String db;

    @Value("${prod.mq}")
    private String mq;

    @GetMapping("/hello")
    public String hello() {
        return "Hello!" + db + mq;
    }

```

![image-20210103095025452](E:\Typora\image\image-20210103095025452.png)



### 6.2. 外部化配置

**(1) 外部配置源**

Spring Boot lets you externalize your configuration so that you can work with the same application code in different environments. You can use a variety of external configuration sources, include Java properties files, YAML files, environment variables, and command-line arguments.

**常用的外部配置源：Java属性文件、YAML文件、环境变量、命令行参数**。

Spring容器中会加载 操作系统、计算机、JVM、环境变量等等属性。



**(2) 配置文件查找顺序**

```shell
Spring Boot will automatically find and load application.properties and application.yaml files from the following locations when your application starts:

1. The classpath root：类路径的根路径
2. The classpath /config package：类路径下config目录
3. The current directory：jar包当前目录
4. The /config subdirectory in the current directory：jar包当前目录的config目录
5. Immediate child directories of the /config subdirectory：/config子目录的直接子目录(Linux系统中)

The list is ordered by precedence (with values from lower items overriding earlier ones).
# 同名配置后面配置的会覆盖前面的。
```

<img src="E:\Typora\image\image-20210103101158133.png" alt="image-20210103101158133" style="zoom:150%;" />



**(3) 配置文件的加载顺序**

```shell
1、当前jar包内部的application.properties和application.yml
2、当前jar包内部的application-{profile}.properties 和 application-{profile}.yml
3、引用的外部jar包的application.properties和application.yml
4、引用的外部jar包的application-{profile}.properties 和 application-{profile}.yml
```

**总结：指定环境优先，外部优先，后面的可以覆盖前面的同名配置项**



### 6.3. 自定义starter

**(1) starter启动原理**

```shell
1、starter-pom引入 autoconfigurer 包。
2、autoconfigure包中配置使用 META-INF/spring.factories中EnableAutoConfiguration 的值，使得项目启动加载指定的自动配置类。
3、编写自动配置类 xxxAutoConfiguration -> xxxxProperties
    @Configuration
    @Conditional
    @EnableConfigurationProperties
    @Bean
```

**引入starter** **--- xxxAutoConfiguration --- 容器中放入组件 ---- 绑定xxxProperties ----** **配置项**。

![image-20210103104248450](E:\Typora\image\image-20210103104248450.png)

**(2) 步骤**

step1：创建两个项目 `hello-spring-boot-starter、hello-spring-boot-starter-autoconfigure`。

```xml
<!-- hello-spring-boot-starter 中不写代码 
     只需要引入 hello-spring-boot-starter-autoconfigure 即可-->
<groupId>com.ymy</groupId>
<artifactId>hello-spring-boot-starter</artifactId>
<version>1.0-SNAPSHOT</version>
    
<properties>
    <java.version>1.8</java.version>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>

<dependencies>
    <!-- hello-spring-boot-starter 引入 autoconfigure-->
    <dependency>
        <groupId>com.ymy</groupId>
        <artifactId>hello-spring-boot-starter-autoconfigure</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```



step2：编写自动配置`hello-spring-boot-starter-autoconfigure`。

```xml
<!-- hello-spring-boot-starter-autoconfigure 依赖 -->
<groupId>com.ymy</groupId>
<artifactId>hello-spring-boot-starter-autoconfigure</artifactId>
<version>1.0-SNAPSHOT</version>

<properties>
    <java.version>1.8</java.version>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <my.version>2.4.1</my.version>
</properties>

<dependencies>

    <!--引入 spring-boot-starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
        <version>${my.version}</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <version>${my.version}</version>
    </dependency>
</dependencies>
```

```java
/**
 * 业务类：默认不要放在容器中
 */
public class HelloService {

    @Resource
    private HelloProperties helloProperties;

    public String sayHello(String name) {
        return helloProperties.getPrefix() + ":" + name + ">>" + helloProperties.getSuffix();
    }
}
```

```java
/**
 * 自动配置：并且要和Properties绑定
 */
@Configuration
@EnableConfigurationProperties(value = HelloProperties.class)
@ConditionalOnMissingBean(value = HelloService.class)
public class HelloServiceAutoConfiguration {
    
    // 将HelloService加入到容器中
    @Bean
    public HelloService helloService() {
        return new HelloService();
    }
}
```

```java
/**
 * HelloProperties
 */
@ConfigurationProperties(prefix = "ymy.hello")
public class HelloProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
```



step3：`hello-spring-boot-starter-autoconfigure`中配置 META-INF/spring.factories，使容器加载自动配置。

![image-20210103143630714](E:\Typora\image\image-20210103143630714.png)



step4：打包安装到本地。

![image-20210103143759135](E:\Typora\image\image-20210103143759135.png)



step5：其他项目直接引入`hello-spring-boot-starter` 即可使用。

```xml
    <groupId>com.ymy</groupId>
    <artifactId>hello-spring-boot-test</artifactId>
    <version>1.0-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.1</version>
    </parent>

    <dependencies>
        <!-- web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>

        <!-- 引入自己的starter -->
        <dependency>
            <groupId>com.ymy</groupId>
            <artifactId>hello-spring-boot-starter</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
```

![image-20210103144036994](E:\Typora\image\image-20210103144036994.png)









# 三、spring-boot安全管理

## 1. spring-security

### 1.1. spring-security初体验

**(1) 依赖**

```xml
<!-- spring security 引入该依赖项目中的所有接口就被保护起来了！ -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

测试接口：/hello。

![image-20210216171417987](E:\Typora\image\image-20210216171417987.png)



### 1.2. 手动配置用户名和密码

**(1) 配置文件中手动spring-security的用户名和密码**

```properties
# application.properties 修改spring-security 默认的用户名和密码

# name 默认是 "user"
spring.security.user.name=Ringo

# password 默认是 UUID 随机生成的
spring.security.user.password=123

# Ringo 这个用户的 角色 ==> 是个 List 数组
spring.security.user.roles=admin,vip
```



**(2) Java 代码中手动配置**

```java
/**
 * spring-security 配置类 需要继承 WebSecurityConfigurerAdapter
 */
@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String RINGO_PASSWORD = "123";
    private static final String TANGS_PASSWORD = "456";


    /**
     * 密码的 加密 和 解密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode1 = passwordEncoder.encode(RINGO_PASSWORD);
        String encode2 = passwordEncoder.encode(TANGS_PASSWORD);

        // $2a$10$xbIYvPY/y0b.iNpU.jHo1OGu9dNeOjLks0hYsE.Xo3TOva0lt7Wre
        log.info("123 " + encode1);

        // $2a$10$FttTCVowAPMq3rdBXb0qce5LaXCLxkD//FLOFWk8Mj7j8oOjuutpe
        log.info("456 " + encode2);
        return passwordEncoder;
    }

    /**
     * 配置 username 和 password
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // inMemoryAuthentication() 存储在内存中的用户名和密码
        auth.inMemoryAuthentication()

                // 这里是配置了两个用户
                // 用户名：Ringo 密码：123             .withUser("Ringo").password("$2a$10$xbIYvPY/y0b.iNpU.jHo1OGu9dNeOjLks0hYsE.Xo3TOva0lt7Wre").roles("admin")
                .and()

                // 用户名：Tangs 密码：456              .withUser("Tangs").password("$2a$10$FttTCVowAPMq3rdBXb0qce5LaXCLxkD//FLOFWk8Mj7j8oOjuutpe").roles("vip");
    }
}
```

![image-20210216175314616](E:\Typora\image\image-20210216175314616.png)



### 1.3. HttpSecurity配置

```java
/**
 * spring-security 配置类 需要继承 WebSecurityConfigurerAdapter
 */
@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String RINGO_PASSWORD = "123";
    private static final String TANGS_PASSWORD = "456";


    /**
     * 密码的 加密 和 解密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode1 = passwordEncoder.encode(RINGO_PASSWORD);
        String encode2 = passwordEncoder.encode(TANGS_PASSWORD);

        // $2a$10$xbIYvPY/y0b.iNpU.jHo1OGu9dNeOjLks0hYsE.Xo3TOva0lt7Wre
        log.info("123 " + encode1);

        // $2a$10$FttTCVowAPMq3rdBXb0qce5LaXCLxkD//FLOFWk8Mj7j8oOjuutpe
        log.info("456 " + encode2);
        return passwordEncoder;
    }

    /**
     * 配置 username 和 password
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // inMemoryAuthentication() 存储在内存中的用户名和密码
        auth.inMemoryAuthentication()

                // 这里是配置了两个用户
                // 用户名：Ringo 密码：123
                .withUser("Ringo").password("$2a$10$xbIYvPY/y0b.iNpU.jHo1OGu9dNeOjLks0hYsE.Xo3TOva0lt7Wre").roles("admin")
                .and()

                // 用户名：Tangs 密码：456
                .withUser("Tangs").password("$2a$10$FttTCVowAPMq3rdBXb0qce5LaXCLxkD//FLOFWk8Mj7j8oOjuutpe").roles("vip")

                // 用户名：Rain 密码：123 ==> 普通用户
                .and()
                .withUser("Rain").password("$2a$10$xbIYvPY/y0b.iNpU.jHo1OGu9dNeOjLks0hYsE.Xo3TOva0lt7Wre").roles("user");


    }

    /**
     * 配置 Http 请求拦截规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 请求授权
        http.authorizeRequests()
                // Role ==> admin 才能访问 /admin/**
                .antMatchers("/admin/**").hasRole("admin")

                // Role ==> admin 或 vip 中的一个就可以访问 /user/**
                .antMatchers("/user/**").hasAnyRole("admin","vip")

                // 除了 "/admin/**" 和 "/user/**" 这两个路径外，其他请求只要登录就可以访问
                .anyRequest().authenticated()

                // 配置表单登录, 和登录相关的路径都不需要验证
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .permitAll()

                // 关闭 spring-security csrf 保护
                .and()
                .csrf().disable();
    }
}
```

![image-20210216195030500](E:\Typora\image\image-20210216195030500.png)



### 1.4. 登录表单配置

```java
   /**
     * 配置 Http 请求拦截规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 请求授权
        http.authorizeRequests()
                // Role ==> admin 才能访问 /admin/**
                .antMatchers("/admin/**").hasRole("admin")

                // Role ==> admin 或 vip 中的一个就可以访问 /user/**
                .antMatchers("/user/**").hasAnyRole("admin","vip")

                // 除了 "/admin/**" 和 "/user/**" 这两个路径外，其他请求只要登录就可以访问
                .anyRequest().authenticated()

                // 配置表单登录, 和登录相关的路径都不需要验证
                .and()
                .formLogin()

                // 应用没有登录的时候就访问 "/doLogin" 这个接口
                .loginProcessingUrl("/doLogin")

                // 修改 spring-security 默认的登录页面,前后端分离一般不配
                .loginPage("/login")

                // 这里配置请求参数 默认是 username 和 password
                // POST ==> http://localhost:8080/doLogin?uname=Ringo&passwd=123
                .usernameParameter("uname")
                .passwordParameter("passwd")

                // 登录成功和失败处理器
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        // 响应 json
                        response.setContentType("application/json;charset=UTF8");
                        PrintWriter printWriter = response.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("message", authentication.getPrincipal());
                        map.put("details", authentication.getDetails());
                        printWriter.write(new ObjectMapper().writeValueAsString(map));
                        printWriter.flush();
                        printWriter.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        // 响应 json
                        response.setContentType("application/json;charset=UTF8");
                        PrintWriter printWriter = response.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 500);

                        if (exception instanceof LockedException) {
                            map.put("message", "账户被锁定");
                        } else if (exception instanceof BadCredentialsException){
                            map.put("message", "用户名或密码错误");
                        } else if (exception instanceof DisabledException) {
                            map.put("message", "账户被禁用");
                        } else if (exception instanceof AccountExpiredException) {
                            map.put("message", "账户过期,登录失败");
                        } else if (exception instanceof CredentialsExpiredException) {
                            map.put("message", "密码过期,登录失败");
                        } else {
                            map.put("message", "登录失败");

                        }

                        printWriter.write(new ObjectMapper().writeValueAsString(map));
                        printWriter.flush();
                        printWriter.close();
                    }
                })
                .permitAll()

                // 关闭 spring-security csrf 保护
                .and()
                .csrf().disable();
    }
```



### 1.5. 注销登录配置

```java
    /**
     * 配置 Http 请求拦截规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 请求授权
        http.authorizeRequests()
                // Role ==> admin 才能访问 /admin/**
                .antMatchers("/admin/**").hasRole("admin")

                // Role ==> admin 或 vip 中的一个就可以访问 /user/**
                .antMatchers("/user/**").hasAnyRole("admin","vip")

                // 除了 "/admin/**" 和 "/user/**" 这两个路径外，其他请求只要登录就可以访问
                .anyRequest().authenticated()

                // 配置表单登录, 和登录相关的路径都不需要验证
                .and()
                .formLogin()

                // 应用没有登录的时候就访问 "/doLogin" 这个接口
                .loginProcessingUrl("/doLogin")

                // 修改 spring-security 默认的登录页面,前后端分离一般不配
                .loginPage("/login")

                // 这里配置请求参数 默认是 username 和 password
                // POST ==> http://localhost:8080/doLogin?uname=Ringo&passwd=123
                .usernameParameter("uname")
                .passwordParameter("passwd")

                // 登录成功和失败处理器
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        // 响应 json
                        response.setContentType("application/json;charset=UTF8");
                        PrintWriter printWriter = response.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("message", authentication.getPrincipal());
                        map.put("details", authentication.getDetails());
                        printWriter.write(new ObjectMapper().writeValueAsString(map));
                        printWriter.flush();
                        printWriter.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        // 响应 json
                        response.setContentType("application/json;charset=UTF8");
                        PrintWriter printWriter = response.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 500);

                        if (exception instanceof LockedException) {
                            map.put("message", "账户被锁定");
                        } else if (exception instanceof BadCredentialsException){
                            map.put("message", "用户名或密码错误");
                        } else if (exception instanceof DisabledException) {
                            map.put("message", "账户被禁用");
                        } else if (exception instanceof AccountExpiredException) {
                            map.put("message", "账户过期,登录失败");
                        } else if (exception instanceof CredentialsExpiredException) {
                            map.put("message", "密码过期,登录失败");
                        } else {
                            map.put("message", "登录失败");

                        }

                        printWriter.write(new ObjectMapper().writeValueAsString(map));
                        printWriter.flush();
                        printWriter.close();
                    }
                })
                .permitAll()
            
                // 注销登录
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        // 响应 json
                        response.setContentType("application/json;charset=UTF8");
                        PrintWriter printWriter = response.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("message", "注销登录成功！");
                        printWriter.write(new ObjectMapper().writeValueAsString(map));
                        printWriter.flush();
                        printWriter.close();
                    }
                })

                // 关闭 spring-security csrf 保护
                .and()
                .csrf().disable();
    }
```



### 1.6. 多个HttpSecurity配置

多个HttpSecurity配置就不需要继承`WebSecurityConfigurerAdapter`，其中的内部类需要继承，

```java
/**
 * 配置多种 SecurityConfig
 */
@Configuration
public class MultiSecurityConfig {

    private static final String RINGO_PASSWORD = "123";
    private static final String TANGS_PASSWORD = "456";

    /**
     * 密码的 加密 和 解密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    /**
     * 配置用户 username 和 password
     */
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // inMemoryAuthentication() 存储在内存中的用户名和密码
        auth.inMemoryAuthentication()

                // 这里是配置了两个用户
                // 用户名：Ringo 密码：123
                .withUser("Ringo").password("$2a$10$xbIYvPY/y0b.iNpU.jHo1OGu9dNeOjLks0hYsE.Xo3TOva0lt7Wre").roles("admin")
                .and()

                // 用户名：Tangs 密码：456
                .withUser("Tangs").password("$2a$10$FttTCVowAPMq3rdBXb0qce5LaXCLxkD//FLOFWk8Mj7j8oOjuutpe").roles("vip")

                // 用户名：Rain 密码：123 ==> 普通用户
                .and()
                .withUser("Rain").password("$2a$10$xbIYvPY/y0b.iNpU.jHo1OGu9dNeOjLks0hYsE.Xo3TOva0lt7Wre").roles("user");
    }

    /**
     * Role ==> Admin 的配置
     *
     * @Order(1)： 设置加载的优先级 数字越小优先级越高
     */
    @Order(1)
    @Configuration
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // 授权请求
            // Role ==> admin 可以访问 "/admin/**"
            http.antMatcher("/admin/**").authorizeRequests().anyRequest().hasRole("admin");
        }
    }

    /**
     * Role ==> vip 的配置
     */
    @Order(2)
    @Configuration
    public static class VipSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/user/**").authorizeRequests().anyRequest().hasRole("vip");
        }
    }

    /**
     * 其他用户的配置
     */
    @Configuration
    public static class OtherSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().authenticated()
                    // 表单登录配置
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/doLogin")
                    .loginPage("/login")
                    .permitAll()
                    // 登录成功处理器
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                            // 响应 json
                            response.setContentType("application/json;charset=UTF8");
                            PrintWriter printWriter = response.getWriter();
                            Map<String, Object> map = new HashMap<>();
                            map.put("status", 200);
                            map.put("message", authentication.getPrincipal());
                            map.put("details", authentication.getDetails());
                            printWriter.write(new ObjectMapper().writeValueAsString(map));
                            printWriter.flush();
                            printWriter.close();
                        }
                    })

                    // 登录失败处理器
                    .failureHandler(new AuthenticationFailureHandler() {
                        @Override
                        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                            // 响应 json
                            response.setContentType("application/json;charset=UTF8");
                            PrintWriter printWriter = response.getWriter();
                            Map<String, Object> map = new HashMap<>();
                            map.put("status", 500);

                            if (exception instanceof LockedException) {
                                map.put("message", "账户被锁定");
                            } else if (exception instanceof BadCredentialsException) {
                                map.put("message", "用户名或密码错误");
                            } else if (exception instanceof DisabledException) {
                                map.put("message", "账户被禁用");
                            } else if (exception instanceof AccountExpiredException) {
                                map.put("message", "账户过期,登录失败");
                            } else if (exception instanceof CredentialsExpiredException) {
                                map.put("message", "密码过期,登录失败");
                            } else {
                                map.put("message", "登录失败");

                            }

                            printWriter.write(new ObjectMapper().writeValueAsString(map));
                            printWriter.flush();
                            printWriter.close();
                        }
                    })


                    // 注销登录
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(new LogoutSuccessHandler() {
                        @Override
                        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                            // 响应 json
                            response.setContentType("application/json;charset=UTF8");
                            PrintWriter printWriter = response.getWriter();
                            Map<String, Object> map = new HashMap<>();
                            map.put("status", 200);
                            map.put("message", "注销登录成功！");
                            printWriter.write(new ObjectMapper().writeValueAsString(map));
                            printWriter.flush();
                            printWriter.close();
                        }
                    })

                    // 关闭 spring-security csrf 保护
                    .and()
                    .csrf().disable();
        }
    }

}
```



### 1.7. 密码加密

`BCryptPasswordEncoder` 每次加密后的结果都不一样，这样就解决了相同密码加密结果相同的问题。

在配置类中直接配置 `PasswordEncoder` 就可以使用了！

```java
@Slf4j
@SpringBootTest
public class SpringSecurityTest {

    @Test
    void testPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for (int i = 0; i < 5; i++) {
            String ret = passwordEncoder.encode("123");
            log.info(ret);
        }
    }   
}
```

![image-20210216233352026](E:\Typora\image\image-20210216233352026.png)



### 1.8. 方法安全

**(1) 开启方法安全注解**：

`@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)` 要写在配置类上

```java
/**
 * 配置多种 SecurityConfig
 *
 * @EnableGlobalMethodSecurity：
 *  1、prePostEnabled：会解锁 @PreAuthorize 和 @PostAuthorize 两个注解。
 *    从名字就可以看出@PreAuthorize 注解会在方法执行前进行验证，
 *    而 @PostAuthorize 注解会在方法执行后进行验证。
 *  2、securedEnabled：开启 @Secured注解
 *   2.1、@Secured: 注解是用来定义业务方法的安全配置。
 *    在需要安全[角色/权限等]的方法上指定 @Secured，
 *    并且只有那些角色/权限的用户才可以调用该方法。
 *   2.2、@Secured缺点: （限制）就是不支持Spring EL表达式。不够灵活。
 *     并且指定的角色必须以ROLE_开头，不可省略。
 *     该注解功能要简单的多，默认情况下只能基于角色（默认需要带前缀 ROLE_）集合来进行访问控制决策。
 *     该注解的机制是只要其声明的角色集合（value）中包含当前用户持有的任一角色就可以访问。
 *     也就是 用户的角色集合和 @Secured 注解的角色集合要存在非空的交集。
 *     不支持使用 SpEL 表达式进行决策。
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MultiSecurityConfig {
```



**(2) 在 Sevice 中使用注解**

```java
@Service
public class MethodService {

    /**
     * 该方法只有 Role ==> admin 才能执行
     */
    @PreAuthorize("hasRole('admin')")
    public String admin() {
        return "Hello admin";
    }

    /**
     * 该方法 Role ==> user/admin 才能执行
     */
    @Secured({"ROLE_user", "ROLE_admin"})
    public String user() {
        return "Hello user";
    }

    @PreAuthorize("permitAll")
    public String permitAll() {
        return "Hello Everyone";
    }
}
```



**(3) 在 Controller 中调用 Service 中的方法**

```java
@RestController
public class MethodController {

    @Resource
    private MethodService methodService;

    @GetMapping("/method/admin")
    public String admin() {
        return methodService.admin();
    }

    @GetMapping("/method/user")
    public String user() {
        return methodService.user();
    }

    @GetMapping("/method/permitAll")
    public String permitAll() {
        return methodService.permitAll();
    }
}
```

- 只有 `Role ==> admin` 才可以访问 `/method/admin`；
- `Role ==> user/admin` 都可以访问 `/method/user`;
- 所有角色都可以访问 `/method/permitAll`。 



### 1.9. 基于数据库的认证

**(1) 实体类要实现 UserDetails 接口**

```java
/**
 * 用户表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "enabled")
    private Integer enabled;

    @TableField(value = "locked")
    private Integer locked;

    @TableField(exist = false)
    private List<Role> roles;


    /**
     * 返回用户的所有角色
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> {
            // spring-security 角色是以 "ROLE_" 开头的
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    /**
     * 账户没有过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户没有被锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return locked == 0;
    }

    /**
     * 密码没有过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用
     * Indicates whether the user is enabled or disabled.
     * A disabled user cannot be authenticated.
     */
    @Override
    public boolean isEnabled() {
        return enabled == 1;
    }
}
```



**(2) UserService 要实现 UserDetailsService**。

```java
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1、user表 ==> 根据 username 查询 user
        QueryWrapper<User> wrapperUser = new QueryWrapper<>();
        wrapperUser.eq("username", username);
        User user = userMapper.selectOne(wrapperUser);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }

        // 2、user_role 表 ==> 根据 user的 id 查询 rid
        QueryWrapper<UserRole> wrapperUserRole = new QueryWrapper<>();
        wrapperUserRole.eq("uid", user.getId());
        List<UserRole> userRoles = userRoleMapper.selectList(wrapperUserRole);

        // 3、Role 表 ==> 根据 rid 查询 role
        List<Role> roles = new ArrayList<>();
        QueryWrapper<Role> wrapperRole = new QueryWrapper<>();
        userRoles.forEach(userRole -> {
            Integer rid = userRole.getRid();
            wrapperRole.eq("id", rid);
            Role role = roleMapper.selectById(rid);
            roles.add(role);
        });

        user.setRoles(roles);
        return user;
    }
}
```



**(3) 编写 SecurityConf 认证配置**

```java
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Resource
    private UserServiceImpl userServiceImpl;

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Resource
    private AppLogoutSuccessHandler appLogoutSuccessHandler;

    /**
     * 密码的加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 登录校验
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl);
    }

    /**
     * http 请求拦截规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                
                // 权限 hasAuthority("ROLE_dba") hasRole("admin") 两种不同的写法
                .antMatchers("/dba/**").hasAuthority("ROLE_dba")
                .antMatchers("/admin/**").hasAnyRole("dba", "admin")
                .antMatchers("/user/**").hasAnyRole("dba", "admin", "user")
                .anyRequest().authenticated()

                // 表单登录
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .permitAll()

                // logout 注销登录
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(appLogoutSuccessHandler)

                .and().csrf().disable();
    }
}
```



### 1.10. 角色继承

**角色继承实际上是一个很常见的需求，因为大部分公司治理可能都是金字塔型的，上司可能具备下属的部分甚至所有权限，反映到代码中就是角色继承了**。

`spring-security`在角色继承上有两种不同的写法，在 Spring Boot2.0.8（对应 Spring Security 5.0.11）上面是一种写法；从 Spring Boot2.1.0（对应 Spring Security 5.1.1）又是另外一种写法。

**(1) 以前的写法**

这里说的以前写法，就是指 SpringBoot2.0.8（含）之前的写法，在之前的写法中，角色继承只需要开发者提供一个 RoleHierarchy 接口的实例即可，例如下面这样：

```java
@Bean
RoleHierarchy roleHierarchy() {
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    String hierarchy = "ROLE_dba > ROLE_admin ROLE_admin > ROLE_user";
    roleHierarchy.setHierarchy(hierarchy);
    return roleHierarchy;
}
```

在这里我们提供了一个 RoleHierarchy 接口的实例，使用字符串来描述了角色之间的继承关系， `ROLE_dba` 具备 `ROLE_admin` 的所有权限，而 `ROLE_admin` 则具备 `ROLE_user` 的所有权限，继承与继承之间用一个空格隔开。提供了这个 Bean 之后，以后所有具备 `ROLE_user` 角色才能访问的资源， `ROLE_dba` 和 `ROLE_admin` 也都能访问，具备 `ROLE_amdin` 角色才能访问的资源， `ROLE_dba` 也能访问。



**(2) 现在的写法**

但是上面这种写法仅限于 Spring Boot2.0.8（含）之前的版本，在之后的版本中，这种写法则不被支持，新版的写法是下面这样：

```java
@Bean
RoleHierarchy roleHierarchy() {
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    String hierarchy = "ROLE_dba > ROLE_admin \n ROLE_admin > ROLE_user";
    roleHierarchy.setHierarchy(hierarchy);
    return roleHierarchy;
}
```

变化主要就是分隔符，将原来用空格隔开的地方，现在用换行符了。这里表达式的含义依然和上面一样，不再赘述。

上面两种不同写法都是配置角色的继承关系，配置完成后，接下来指定角色和资源的对应关系即可，如下：

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/admin/**")
            .hasRole("admin")
            .antMatchers("/db/**")
            .hasRole("dba")
            .antMatchers("/user/**")
            .hasRole("user")
            .and()
            .formLogin()
            .loginProcessingUrl("/doLogin")
            .permitAll()
            .and()
            .csrf().disable();
}
```

这个表示 `/db/**` 格式的路径需要具备 dba 角色才能访问， `/admin/**` 格式的路径则需要具备 admin 角色才能访问， `/user/**` 格式的路径，则需要具备 user 角色才能访问，此时提供相关接口，会发现，dba 除了访问 `/db/**` ，也能访问 `/admin/**` 和 `/user/**` ，admin 角色除了访问 `/admin/**` ，也能访问 `/user/**` ，user 角色则只能访问 `/user/**` 。



### 1.11. 资源放行策略

在 Spring Security 中，有一个资源，如果你希望用户不用登录就能访问，那么一般来说，你有两种配置策略：第一种就是在 configure(WebSecurity web) 方法中配置放行，像下面这样：

```java
// 静态资源放行
// 放行 swagerr-ui 
// "/swagger-ui.html", "/webjars/**", "/v2/**", "/swagger-resources/**"
@Override
public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/css/**", "/js/**", "/index.html", "/img/**", "/fonts/**", "/favicon.ico", "/verifyCode");
}
```



第二种方式是在 configure(HttpSecurity http) 方法中进行配置：

```java
// 接口放行
http.authorizeRequests()
        .antMatchers("/hello").permitAll()
        .anyRequest().authenticated()
```

两种方式最大的区别在于，第一种方式是不走 Spring Security 过滤器链，而第二种方式走 Spring Security 过滤器链，在过滤器链中，给请求放行。

在我们使用 Spring Security 的时候，有的资源可以使用第一种方式额外放行，不需要验证，例如前端页面的静态资源，就可以按照第一种方式配置放行。

有的资源放行，则必须使用第二种方式，例如登录接口。大家知道，登录接口也是必须要暴露出来的，不需要登录就能访问到的，但是我们却不能将登录接口用第一种方式暴露出来，登录请求必须要走 Spring Security 过滤器链，因为在这个过程中，还有其他事情要做。



### 1.12. Remember Me

```java
public class JdbcTokenRepositoryImpl extends JdbcDaoSupport implements PersistentTokenRepository {

    /** Default SQL for creating the database table to store the tokens */
    public static final String CREATE_TABLE_SQL = "create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, "
        + "token varchar(64) not null, last_used timestamp not null)";
```

`JdbcTokenRepositoryImpl` 写好了在数据库中要创建保存 RememberMe 验证信息的表。



`spring-security`配置

注意：在前端登录页面加一行(name必须是**remeber-me**)

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 1、注入DataSource
    @Resource
    private DataSource dataSource;

    // 2、JdbcTokenRepositoryImpl 加入到容器
    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest()
            .authenticated()

            .and()
            .formLogin()
            .loginProcessingUrl("/doLogin")
            // .loginPage("/login")
            .successHandler(loginSuccessHandler)
            .failureHandler(loginFailureHandler)
            .permitAll()

            // 3、配置记住我
            .and()
            .rememberMe()
            .tokenRepository(jdbcTokenRepository())
            .tokenValiditySeconds(3000)
            .userDetailsService(userDetailsService())

            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler(logoutAppSuccessHandler)

            .and()
            .csrf()
            .disable();
    }
}
```

![image-20210222224555645](E:\Typora\image\image-20210222224555645.png)



### 1.13. 自定义过滤器 (json登录)

**(1) spring-security 说明**

 Spring Security 中默认的登录数据格式就是 key/value 的形式，虽然登录请求是一个 POST 请求，但是数据传输格式是 key/value 的形式。

以下是 axios 发送 post 请求，但是是 key-value 数据

```js
// spring-security 登录认证 需要用表单传参数
export function postKeyValueRequest(url, params) {
    // instance8001 是 axios 实例，这里发送 axios 的 post 请求
    return instance8001({
        method: 'post',
        url,
        data: params,

        // transformRequest 允许在向服务器发送数据之前修改请求数据
        // 只能用在 'PUT', 'POST' 和 'PATCH' 这几个请求方法
        transformRequest: [function (data) {
            let ret = '';

            // 遍历对象时,这里的 i 是对象中的 key
            // 遍历数组时,这里的 i 是数组中的 index 下表
            for (let i in data) {
                ret += encodeURIComponent(i) + '=' + encodeURIComponent(data[i]) + '&'
            }
            return ret;
            
        }],
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    });
}
```



**(2) 自定义过滤器**

```java
public class UsernamePasswordLoginJsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // 先判断 "POST" 请求, 不是直接抛异常
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        // 判断用 json 传输的数据
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            System.out.println("json 登录");

            String username = null;
            String password = null;

            // request.getInputStream() ===> 注意："GET" "DELETE" 就没有这种流
            // 一定要有 body 的才有流
            try {
                // readValue 解析 json, 结果封装到 Map 中
                Map<String, String> map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                System.out.println(map);
                
                // 这里使用 父类的 usernameParameter 
                // 当使用继承的 set 方法修改时, 这里也会被修改(因为子类是继承的父类setUsernameParameter())
                username = map.get(super.getUsernameParameter());
                password = map.get(super.getPasswordParameter());
            } catch (IOException e) {
                e.printStackTrace();
            }

            username = (username != null) ? username : "";
            username = username.trim();
            password = (password != null) ? password : "";
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }

        System.out.println("k-v 登录");
        // 否则就是用父类的 key-value 形式登录
        return super.attemptAuthentication(request, response);
    }
}
```



**(3) 配置自定义的过滤器，使之生效**

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserServiceImpl userServiceImpl;

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Resource
    private LogoutAppSuccessHandler logoutAppSuccessHandler;

    /**
     * 密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 登录校验
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl);
    }

    /**
     * 1、将我们自定义的JSON登录的 Filter 加入到容器中
     */
    @Bean
    public UsernamePasswordLoginJsonAuthenticationFilter usernamePasswordLoginJsonAuthenticationFilter() throws Exception {
        UsernamePasswordLoginJsonAuthenticationFilter filter = new UsernamePasswordLoginJsonAuthenticationFilter();
        
        // 这个AuthenticationManager必须设置，
        // 可以选择在这里设置，也可以选择在自定的 Filter 中设置
        filter.setAuthenticationManager(authenticationManagerBean());

        // 登录成功/失败/登录接口 都要在这里配, 如果在 HttpSecurity 中配置自定义过滤器就会失效
        filter.setAuthenticationSuccessHandler(loginSuccessHandler);
        filter.setAuthenticationFailureHandler(loginFailureHandler);
        filter.setFilterProcessesUrl("/doLogin");
        
        // filter.setUsernameParameter("uname");
        // filter.setPasswordParameter("pwd");
        return filter;
    }

    /**
     * http 请求拦截规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 2、使用自定义 Filter 替换掉 UsernamePasswordAuthenticationFilter
        http.addFilterAt(usernamePasswordLoginJsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                .anyRequest()
                .authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()

                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutAppSuccessHandler)

                .and()
                .csrf()
                .disable();
    }
}
```



### 1.14. 自定义认证流程(验证码)

> 1、各组件功能的介绍

**(1)**  `Authentication` 中保存用户的信息，是用户登录的凭证，每一种登录的方式都对应了一个 Token。

![image-20210224151842770](E:\Typora\image\image-20210224151842770.png)



**(2)**  `AuthenticationProvider`  的作用就是用来做校验的，用来校验用户信息；

每一种登录方式，对应一种校验规则，也就对应一个 provider;

**一句话，在 AuthenticationProvider 中校验 Authentication**。

<img src="E:\Typora\image\image-20210224151952429.png" alt="image-20210224151952429" style="zoom:150%;" />



**(3)** `ProviderManager` 用来管理 AuthenticationProvider；

同时，ProviderManager 也是 AuthenticationManager。

```java
public class ProviderManager implements AuthenticationManager, MessageSourceAware, InitializingBean {

	private static final Log logger = LogFactory.getLog(ProviderManager.class);

	private AuthenticationEventPublisher eventPublisher = new NullEventPublisher();

    // 这里就有所有的 AuthenticationProvider
	private List<AuthenticationProvider> providers = Collections.emptyList();
```



> 2、认证流程解析

**(1) 从 UsernamePasswordAuthenticationFilter 入手**

```java
@Override
public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
    throws AuthenticationException {
    
    // 1、先判断是否是 POST 请求
    if (this.postOnly && !request.getMethod().equals("POST")) {
        throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
    }
    
    // 2、从请求中获得用户名和密码
    String username = obtainUsername(request);
    username = (username != null) ? username : "";
    username = username.trim();
    String password = obtainPassword(request);
    password = (password != null) ? password : "";
    
    // 3、将用户信息存放到 Authention 中(封装成Token)
    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
    
    // 4、Allow subclasses to set the "details" property
    // 将 "request" 封装到 “Token” 的 “Details” 中
    setDetails(request, authRequest);
    
    // 5、去相应的 Provider 中去校验
    // this.getAuthenticationManager() 获得其实就是 ProviderManager
    // ProviderManager 实现了 AuthenticationManger 
    // 然后在 ProviderManager 去调用 authenticate(authRequest) 校验封装的 Token
    return this.getAuthenticationManager().authenticate(authRequest);
}
```



**(2) ProviderManger 遍历找到相应的 Provider 来校验之前封装的 Token**。

```java
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		int size = this.providers.size();
        // 在ProviderManager中去遍历所有的Provider，找到相应的 Provider 来校验Token
		for (AuthenticationProvider provider : getProviders()) {
```



```shell
流程：
	# 自定义认证流程，xxxFilter 要自己定义
	1、Filter拦截请求
		1.1、从请求中获得用户信息；
		1.2、封装用户信息到 Authentication(其实就是Token) 中;
		# 自定义认证逻辑的时候, xxxxProvider 是要我们自己定义的
		1.3、调用 this.getAuthenticationManager()，拿到 "ProviderManager"，再去相应的 				provider中校验Token ；
```



> 3、验证码登录流程

**(1) 依赖**

```xml
<!-- 验证码依赖 -->
<dependency>
    <groupId>com.github.penggle</groupId>
    <artifactId>kaptcha</artifactId>
    <version>2.3.2</version>
</dependency>
```



**(2) 验证码的配置**

```java
@Configuration
public class ValidateCodeConfig {

    /**
     * 配置图片验证码
     */
    @Bean
    public Producer imageCodeProducer() {
        Properties properties = new Properties();
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "150");
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT , "50");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING , "0123456789");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH , "4");
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(new Config(properties));
        return defaultKaptcha;
    }

}
```



**(3) 验证码接口**

```java
@RestController
public class HelloController {

    @Resource
    private Producer imageCodeProducer;

    /**
     * 生成验证码的接口
     */
    @GetMapping("/imageCode")
    public void getImageCode(HttpServletResponse response, HttpSession session) {
        // 设置响应的格式 "image/jpeg"
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        // 将图片验证码的文本存到 Session 中
        String text = imageCodeProducer.createText();
        session.setAttribute("imageCodeText", text);

        // 创建图片验证码
        BufferedImage image = imageCodeProducer.createImage(text);

        // 将图片验证码通过输出流来写到前端页面
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

![image-20210225123521978](E:\Typora\image\image-20210225123521978.png)



**(4) 自定义 Filter 支持 JSON和表单登录（加上获取验证码）**

```java
/**
* 自定义过滤器
*/
public class UsernamePasswordLoginJsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // 先判断 "POST" 请求, 不是直接抛异常
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String username = null;
        String password = null;
        String imageCode = null;

        // 如果用 json 传数据
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            System.out.println("json 登录");

            // request.getInputStream() ===> 注意："GET" "DELETE" 就没有这种流
            // 一定要有 body 的才有流
            try {
                // readValue 解析 json, 结果封装到 Map 中
                Map<String, String> map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
                System.out.println(map);

                // 这里使用 父类的 usernameParameter
                // 当使用继承的 set 方法修改时, 这里也会被修改(因为子类是继承的父类setUsernameParameter())
                username = map.get(this.getUsernameParameter());
                password = map.get(this.getPasswordParameter());
                imageCode = map.get(ImageCodeConstant.CODE_IN_REQUEST_KEY);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // 否则就是用父类的 key-value 形式登录
            System.out.println("k-v 登录");

            username = obtainUsername(request);
            password = obtainPassword(request);
            imageCode = request.getParameter(ImageCodeConstant.CODE_IN_REQUEST_KEY);
            System.out.println(imageCode);
        }

        username = (username != null) ? username : "";
        username = username.trim();
        password = (password != null) ? password : "";
        imageCode = (imageCode != null) ? imageCode : "";
        imageCode = imageCode.trim();

        ImageAuthenticationToken authRequest = new ImageAuthenticationToken(username, password, imageCode);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}

/**
* 图片验证码的Token
*/
public class ImageAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private String imageCode;

    public ImageAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public ImageAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public ImageAuthenticationToken(Object principal, Object credentials, String imageCode) {
        super(principal, credentials);
        this.imageCode = imageCode;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }
}
```

`Filter` 的配置在 1.13 中。



**(5) 自定义 provide 验证码校验逻辑** 

```java
// 可以获取 Request
HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
```



```java
/**
 * 图片验证码校验流程：
 * 1、校验验证码
 * 2、校验用户名和密码
 */
public class ImageCodeAuthenticationProvider extends DaoAuthenticationProvider{

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // 1、校验图片验证码

        // 获得 "HttpServletRequest" 新方式
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String codeInSession = (String)request.getSession().getAttribute(ImageCodeConstant.CODE_IN_SESSION_KEY);

        // 校验 图片验证码 只需要拿到即可
        ImageAuthenticationToken auth = (ImageAuthenticationToken)authentication;
        String codeInRequest = auth.getImageCode();

        System.out.println(codeInSession);
        System.out.println(codeInRequest);

        if (codeInRequest == null || codeInSession == null || !StringUtils.equals(codeInRequest, codeInSession)) {
            throw new AuthenticationServiceException("验证码错误");
        }

        // 2、校验用户名和密码
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
```



**(5) 将验证码登录逻辑加入到容器中**

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserServiceImpl userServiceImpl;


    /**
     * 密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 验证码校验逻辑加入到容器中
     */
    @Bean
    public ImageCodeAuthenticationProvider imageCodeAuthenticationProvider() {
        ImageCodeAuthenticationProvider provider = new ImageCodeAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userServiceImpl);
        return provider;
    }

    /**
     * 将我们自定义的 ImageCodeAuthenticationProvider 加到 ProviderManager 中去
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(imageCodeAuthenticationProvider());
    }
}
```



### 1.15. AuthenticationDetails

**(1) 系统默认的 Details**

```java
public interface Authentication extends Principal, Serializable {

    /**
	 * Stores additional details about the authentication request. These might be an IP
	 * address, certificate serial number etc.
	 * @return additional details about the authentication request, or <code>null</code>
	 * if not used
	 */
    Object getDetails();
}
```



```java
// 可以获得 Authentication ==> 其实就是对应的 xxxAuthenticationToken
SecurityContextHolder.getContext().getAuthentication();
```





测试接口

```java
@GetMapping("/details")
public Object details() {
    // 这里的 getDetails()实际上是 WebAuthenticationDetails
    WebAuthenticationDetails details = (WebAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getDetails();
    System.out.println(details.getClass());
    return details;
}
```

![image-20210225175028285](E:\Typora\image\image-20210225175028285.png)

**这个 Details 就是属于 xxxAuthenticationToken 的一部分**。



### 1.16. 踢掉前一个用户

**(1) Session 并发控制**

```java
/**
* 监听Session，通知 Spring 容器
*/
@Bean
public HttpSessionEventPublisher httpSessionEventPublisher() {
    return new HttpSessionEventPublisher();
}

@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .anyRequest()
        .authenticated()

        .and()
        .formLogin()
        .loginProcessingUrl("/doLogin")
        .successHandler(loginSuccessHandler)
        .failureHandler(loginFailureHandler)
        .permitAll()

        // 配置记住我
        .and()
        .rememberMe()
        .tokenRepository(jdbcTokenRepository())

        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessHandler(logoutAppSuccessHandler)

        .and()
        .csrf()
        .disable()

        // 设置并发数
        // 一定要重写 实体类的 Equals 和 HashCode 方法
        .sessionManagement()
        .maximumSessions(1)

        // 有人登录了, 后面的人就无法登录了
        .maxSessionsPreventsLogin(true);
}
```



**(2) 并发控制原理**

`AbstractAuthenticationProcessingFilter 中 doFilter() 方法`

```java
// this.sessionStrategy 实际上是 ConcurrentSessionControlAuthenticationStrategy 
this.sessionStrategy.onAuthentication(authenticationResult, request, response);
```



```java
@Override
public void onAuthentication(Authentication authentication, HttpServletRequest request,HttpServletResponse response) {
    List<SessionInformation> sessions = this.sessionRegistry.getAllSessions(authentication.getPrincipal(), false);
    int sessionCount = sessions.size();
    int allowedSessions = getMaximumSessionsForThisUser(authentication);
    if (sessionCount < allowedSessions) {
        // They haven't got too many login sessions running at present
        return;
    }
    if (allowedSessions == -1) {
        // We permit unlimited logins
        return;
    }
    if (sessionCount == allowedSessions) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Only permit it though if this request is associated with one of the
            // already registered sessions
            for (SessionInformation si : sessions) {
                if (si.getSessionId().equals(session.getId())) {
                    return;
                }
            }
        }
        // If the session is null, a new one will be created by the parent class,
        // exceeding the allowed number
    }
    allowableSessionsExceeded(sessions, allowedSessions, this.sessionRegistry);
}
```



### 1.17. 请求失败处理

如果未登录就访问接口，spring-security 默认会重定向到 `loginPage("/login")`，如果想要设置，访问接口不重定向，直接返回数据，可以添加配置。

**(1) RequestFailureHandler**

```java
@Component
public class RequestFailureHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        ResponseBean responseBean;
        if (authException instanceof InsufficientAuthenticationException) {
            responseBean = ResponseBean.failure("尚未登录, 请登录!");
        } else {
            responseBean = ResponseBean.failure("访问失败!");
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(new ObjectMapper().writeValueAsString(responseBean));
        printWriter.flush();
        printWriter.close();
    }
}
```



**(2) 配置信息**

```java
@Resource
private RequestFailureHandler requestFailureHandler;

@Override
protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(SecurityConfigConstant.LOGIN_PAGE)
        .permitAll()
        .anyRequest()
        .authenticated()

        .and()
        .formLogin()
        // 表单登录使用该接口
        .loginProcessingUrl(SecurityConfigConstant.LOGIN_PROCESSING_URL)

        // 如果未登录则重定向 ==> /login 接口；spring-security 默认 /login 是 html 页面
        // 这里如果返回 json 需要我们在 Controller 中自定义该接口
        //                .loginPage(SecurityConfigConstant.LOGIN_PAGE)
        .successHandler(loginSuccessHandler)
        .failureHandler(loginFailureHandler)
        .permitAll()

        .and()
        .logout()
        .logoutUrl(SecurityConfigConstant.LOGOUT_URL)
        .logoutSuccessHandler(logoutAppSuccessHandler)

        // 请求失败时处理器 ==> 默认重定向到 "/login" ==> 现在不让重定向了, 直接返回提示
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(requestFailureHandler)

        .and()
        .csrf().disable();
}
```


# 三、Spring相关

## 1. 代理模式

> 代理，就是你委托别人帮你办事，所以代理模式也有人称作委托模式的。
>
> 比如领导要做什么事，可以委托他的秘书去帮忙做，这时就可以把秘书看做领导的代理。
>
> 代理模式又分为静态代理和动态代理。

[参考文档](https://www.cnblogs.com/joy99/p/10865391.html)

### 1.1. 静态代理

**静态代理的使用**：

静态代理，代理类和被代理的类实现了同样的接口，代理类同时持有被代理类的引用，这样，当我们需要调用被代理类的方法时，可以通过调用代理类的方法来做到。

举例如下：假设领导的工作是开会和给员工考评。

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/spring-aop/leader-secretary.png" alt="leader-secretary" style="zoom:150%;" />

**先定义接口**：

```java
public interface Work {

    void meeting();

    int evaluate(String name);
}
```

**然后定义领导类**：

```java
public class Leader implements Work {

    @Override
    public void meeting() {
        System.out.println("领导早上要组织会议");
    }

    @Override
    public int evaluate(String name) {
        // 生成 0-20 的随机数 + 80
        int score = new Random(System.currentTimeMillis()).nextInt(20) + 80;
        System.out.println(String.format("领导给%s的考评为%s分", name, score));
        return score;
    }
}
```

**再定义秘书类**：

```java
public class Secretary implements Work {

    private Leader leader = new Leader();

    @Override
    public void meeting() {
        System.out.println("秘书先给老板准备材料");
        leader.meeting();
    }

    @Override
    public int evaluate(String name) {
        return leader.evaluate(name);
    }
}
```

**测试**：

```java
@Test
public void secretaryProxyLeader() {
    Secretary secretary = new Secretary();
    secretary.meeting();
    secretary.evaluate("Tom");
}
```

```java
// 运行结果
秘书先给老板准备材料
领导早上要组织会议
领导给Tom的考评为83分
```



> **代理模式与装饰着模式的区别**？
>
> 实际上，在装饰器模式和代理模式之间还是有很多差别的。
>
> - 装饰器模式关注于在一个对象上动态的添加方法，然而代理模式关注于控制对对象的访问。
> - 换句话说，用代理模式，代理类（proxy class）可以对它的客户隐藏一个对象的具体信息。因此，当使用代理模式的时候，我们常常在一个代理类中创建一个对象的实例。并且，当我们使用装饰器模式的时候，我们通常的做法是将原始对象作为一个参数传给装饰者的构造器。
>
> 我们可以用另外一句话来总结这些差别：静态代理是一种编译期增强，还没运行就已经知道增强的目标对象。装饰者是运行时增强，只有运行时才知道具体增强的目标。

其实代理模式和装饰者模式侧重点不一样，代理模式重点在于明确了被代理的类。如上例中，秘书很明确要代理的是的领导。而装饰者模式侧重于拓展类的方法。



### 1.2. JDK动态代理

动态代理的根据实现方式的不同可以分为 JDK 动态代理和 CGlib 动态代理。

1. JDK 动态代理：利用反射机制生成一个实现代理接口的类，在调用具体方法前调用InvokeHandler来处理。

2. CGlib 动态代理：利用ASM（开源的Java字节码编辑库，操作字节码）开源包，将代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。

区别：JDK代理只能对实现接口的类生成代理；CGlib是针对类实现代理，对指定的类生成一个子类，并覆盖其中的方法，这种通过继承类的实现方式，不能代理final修饰的类。

还是以上面的例子为例：
**首先，定一个类实现 InvocationHandler 接口，并实现 invoke 方法**：

```java
/**
 * InvocationHandler是由代理实例的调用处理程序实现的接口 。
 * 每个代理实例都有一个关联的调用处理程序。
 * 当在代理实例上调用方法时，方法调用将分派到其调用处理程序的invoke()方法。
 *
 * @author Ringo
 * @date 2021/4/29 19:21
 */
@Slf4j
public class WorkInvocationHandler implements InvocationHandler {

    // 被代理对象
    private Leader leader;

    public WorkInvocationHandler(Leader leader) {
        this.leader = leader;
    }

    /**
     * 执行被代理对象每个方法前都会调用 invoke
     * proxy: 代理对象。
     * method: 接口中的方法。
     * args：方法的参数。
     *
     * @author Ringo
     * @date 2021/4/29
     */
    @Override
    public Object invoke(Object proxy, Method method, 
                         Object[] args) throws Throwable {
        if ("meeting".equals(method.getName())) {
            // 在执行目标方法前的操作
            System.out.println("代理先准备会议材料...");
            
            // 执行目标方法
            return method.invoke(leader, args);
        }

        if ("evaluate".equals(method.getName())) {
            log.info("evaluate(String name)的name:" + args[0]);
            return method.invoke(leader, args);
        }

        return null;
    }
}
```

**测试**：

```java
@Test
public void dynamicProxy() {
    Leader leader = new Leader();
    Work proxy = (Work) Proxy.newProxyInstance(
        Leader.class.getClassLoader(),
        new Class[]{Work.class},
        new WorkInvocationHandler(leader));
    proxy.meeting();
    proxy.evaluate("Tom");
}

// loader: 类加载器
// interfaces: 被代理对象实现的接口
// h: InvocationHandler 实现类, 用于增强被代理对象
public static Object newProxyInstance(ClassLoader loader,
                                      Class<?>[] interfaces,
                                      InvocationHandler h);
```

```java
// 测试结果
代理先准备会议材料...
领导早上要组织会议
com.ymy.proxy.dynamic.WorkInvocationHandler - evaluate(String name)的name:Tom
领导给Tom的考评为81分
```



### 1.3. CGlib动态代理

**添加 cglib 依赖**：

```xml
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>3.3.0</version>
</dependency>
```



**cglib 针对类进行代理，我们以上面的 Leader 类为例，先创建一个类实现 MethodInterceptor 接口**:

```java
/**
 * cglib动态代理
 *
 * @author Ringo
 * @date 2021/4/29 20:23
 */
@Slf4j
public class LeaderMethodInterceptor implements MethodInterceptor {
    /**
     * obj: 增强的代理对象(继承自被代理对象)
     * method: 拦截的方法(被代理对象需要执行的方法)
     * args: 拦截方法的参数
     * proxy: 触发父类的方法对象(执行父类的方法)
     * 需要注意的是，实际调用是 methodProxy.invokeSuper()。
     * 如果使用 invoke() 方法，则需要传入被代理的类对象，否则出现死循环，造成 stackOverflow 。
     *
     * @author Ringo
     * @date 2021/4/29
     */
    @Override
    public Object intercept(Object obj, 
                            Method method, Object[] args, 
                            MethodProxy proxy) throws Throwable {
        if ("meeting".equals(method.getName())) {
            // 执行目标方法前的业务
            System.out.println("代理先准备会议材料...");

            // 执行目标方法
            return proxy.invokeSuper(obj, args);
        }

        if ("evaluate".equals(method.getName())) {
            log.info("evaluate(String name)的name:" + args[0]);
            return proxy.invokeSuper(obj, args);
        }
        return null;
    }
}
```

**测试**：

```java
@Test
public void cglibDynamicProxy() {
    //1. 创建字节码增强器, 用来对被代理的类扩展。
    Enhancer enhancer = new Enhancer();
    //2. 告诉cglib,生成的子类需要继承那个父类。
    enhancer.setSuperclass(Leader.class);
    //3. 设置回调
    enhancer.setCallback(new LeaderMethodInterceptor());
    //4. 生成源代码,编译成class文件,加载到jvm,并返回代理对象。
    Leader proxy = (Leader)enhancer.create();

    proxy.meeting();
    proxy.evaluate("Tom");
}
```

```java
// 测试结果
代理先准备会议材料...
领导早上要组织会议
com.ymy.proxy.dynamic.LeaderMethodInterceptor - 
    evaluate(String name)的name:Tom
领导给Tom的考评为99分

```



## 2. AOP

### 2.1. AOP介绍

[参考文档](https://www.cnblogs.com/joy99/p/10941543.html)

AOP 领域中的特性术语：

- 通知（Advice）: AOP 框架中的增强处理。通知描述了切面何时执行以及如何执行增强处理。
- 连接点（join point）: 连接点表示应用执行过程中能够插入切面的一个点，这个点可以是方法的调用、异常的抛出。在 Spring AOP 中，连接点总是方法的调用。
- 切点（PointCut）: 可以插入增强处理的连接点。
- 切面（Aspect）: 切面是通知和切点的结合。
- 引入（Introduction）：引入允许我们向现有的类添加新的方法或者属性。
- 织入（Weaving）: 将增强处理添加到目标对象中，并创建一个被增强的对象，这个过程就是织入。

> AOP 框架有很多种， AOP 框架的实现方式有可能不同， Spring 中的 AOP 是通过动态代理实现的。不同的 AOP 框架支持的连接点也有所区别，例如，AspectJ 和 JBoss, 除了支持方法切点，它们还支持字段和构造器的连接点。而 Spring AOP 不能拦截对对象字段的修改，也不支持构造器连接点,我们无法在 Bean 创建时应用通知。



### 2.2. Spring AOP通知

**Spring AOP 中有 5 中通知类型，分别如下**：

![通知类型](https://img2018.cnblogs.com/blog/758949/201905/758949-20190529225613898-1522094074.png)

> **spring aop execution表达式:**
>
> execution **中第一个\*表示任何返回类型.** 
>
> **要注意一点,类名需要用一个.占位**。

![execution表达式](https://images0.cnblogs.com/blog/442092/201409/271640360761290.jpg)

### 2.3. 注解配置Spring AOP

**声明一个接口和它的实现类**：

```java
public interface IBuy {
    void buy();
}

@Component
public class Boy implements IBuy {

    @Override
    public void buy() {
        System.out.println("男孩买了一个游戏机！");
    }
}

@Component
public class Girl implements IBuy {

    @Override
    public void buy() {
        System.out.println("女孩买了一条裙子！");
    }
}
```

**声明切面**：

```java
@Aspect
@Component
public class BuyAspectJ {
    
    // 切入点
    @Pointcut("execution(* com.ymy.aop.IBuy.buy(..))")
    public void point() {
    }

    // 前置通知
    @Before("point()")
    public void before() {
        System.out.println("before");
    }

    // 后置通知
    @After("point()")
    public void after() {
        System.out.println("after");
    }

    // 环绕通知
    @Around("point()")
    public void around(ProceedingJoinPoint pjp) {
        try {
            System.out.println("around before");
            pjp.proceed();
            System.out.println("around after");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
```

**测试**：

```java
@SpringBootTest
public class TestAop {
    @Resource
    private Boy boy;

    @Resource
    private Girl girl;

    @Test
    public void test() {
        boy.buy();
        System.out.println("---- 分隔符 ---- ");
        girl.buy();
    }
}
```

```java
// 测试结果
around before
before
男孩买了一个游戏机！
after
around after
---- 分隔符 ---- 
around before
before
女孩买了一条裙子！
after
around after
```



```java
@Pointcut("execution(* com.sharpcj.aopdemo.test1.IBuy.buy(..))")
public void point(){}
```

声明了一个切点表达式，该方法 point 的内容并不重要，方法名也不重要，实际上它只是作为一个标识，供通知使用。

### 2.4. 注解处理通知的参数

```java
@Aspect
@Component
public class BuyAspectJ {
    @Pointcut("execution(* com.ymy.aop.IBuy.buy(double)) && args(price)")
    public void point1(double price) {

    }

    @Before("point1(price)")
    public void before(double price) {
        System.out.println(price);
    }
}
```

