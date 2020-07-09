# 1.SpringBoot入门

## 1.1.POM文件

### 1.1.1.父项目

```xml
<!--1、SpringBoot的pom文件中导入父项目-->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.4.RELEASE</version>
</parent>

<!--2、spring-boot-starter-parent的父项目是spring-boot-dependencies
spring-boot-dependencies 定义很多jar的版本，它是真正来管理SpringBoot应用里所有依赖的版本！
-->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.2.4.RELEASE</version>
    <relativePath>../../spring-boot-dependencies</relativePath>
</parent>
```

- `spring-boot-dependencies`是SpringBoot应用版本仲裁中心！以后我们导入依赖默认是不需要写版本的！
- 没有在`spring-boot-dependencies`管理的依赖我们自然需要声明版本号。

### 1.1.1.启动器(starter)

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

- **spring-boot-starter**-==web==：spring-boot-starter是SpringBoot场景启动器。`spring-boot-starter-web`帮我导入了Web模块需要正常运行的组件。

- **一句话：SpringBoot将所有的功能场景都抽取出来，做成一个个的starter(启动器)，只需要在项目中引入这些starter相关场景的所有依赖都会导入进来！要用什么功能就导入什么starter(启动器)即可。**

## 1.2.自动配置

### 1.2.1.@SpringBootApplication

> 主配置类

```java
/**
 * @SpringBootApplication 来标注一个主程序类，说明这是一个SpringBoot应用
 */
@SpringBootApplication
public class ApplicationJD {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationJD.class, args);
    }
}
```

**@SpringBootApplication**：该注解标注在某个类上说明，这个类是SpringBoot应用的主配置类，就可以运行主配置类的main()方法来启动SpringBoot应用！

**@SpringBootApplication 是一个组合注解,主要由@SpringBootConfiguration和@EnableAutoConfiguration组成。**



### 1.2.2.@SpringBootConfiguration

```java
/**
 * @SpringBootApplication 是一个组合注解，该注解表示SpringBoot的配置类。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
    

/**
 * @SpringBootConfiguration 是由@Configuration组成的
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
public @interface SpringBootConfiguration {
```

- **@SpringBootConfiguration：**该注解表示SpringBoot的配置类。
  - @SpringBootConfiguration标注在某个类上，表示这是一个SpringBoot配置类。
  - @SpringBootConfiguration由@Configuration注解组成的，@Configuration也是代表配置类。
  - 只不过@SpringBootConfiguration是SpringBoot定义的注解，@Configuration是Spring定义的注解。
  - **注意：@Configuration是由@Component组成的，标注@Configuration的类也是Spring容器的一个组件。**

### 1.2.3.@EnableAutoConfiguration

```java
/**
 * @EnableAutoConfiguration：开启自动配置功能
 * 以前我们需要配置的东西,SpringBoot帮我们配置,@EnableAutoConfiguration告诉SpringBoot开启自动配置功能,这样自动置才能生效。
 * @EnableAutoConfiguration 由 @AutoConfigurationPackage 和 @Import(AutoConfigurationImportSelector.class)组成。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {

/**
* @AutoConfigurationPackage
*/ 
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(AutoConfigurationPackages.Registrar.class)
public @interface AutoConfigurationPackage {
```

- @AutoConfigurationPackage：自动配置包。
  - @Import(AutoConfigurationPackages.Registrar.class)：@import是Spring的底层注解，给容器导入组件。
  - **一句话：@AutoConfigurationPackage就是将主配置类(@SpringBootApplication标注的这个类)的所在包及下面所有子包和所有组件扫描到SpringBoot容器中。**
- @Import(AutoConfigurationImportSelector.class)：给容器导入组件。
  - AutoConfigurationImportSelector：这个类是给容器导入组件的选择器。将需要导入的组件以全类名的方式返回，这些组件就会被添加到容器中。
  - **一句话：(AutoConfigurationImportSelector.class会给SpringBoot导入非常多的配置类(xxxAutoConfiguration)，这些自动配置类会给容器中导入这个场景需要的所有组件，并配置好这些组件。**



### 1.2.4.总结

- `@SpringBootApplication`：主配置类 + 包扫描 +  导入xxxAutoConfiguration配置类。
  - `@SpringBootConfiguration`：SpringBoot声明的配置类。
    - `@Configuration`：Spring声明的配置类，也是一个组件@Component。
  - `@EnableAutoConfiguration`：包扫描 +  导入xxxAutoConfiguration配置类。
    - `@AutoConfigurationPackage`：包扫描。
    - `@Import(AutoConfigurationImportSelector.class)`：**SpringBoot在启动时从类路径下的`META-INF/spring.factories`中获取EnableAutoConfiguration指定的值，再将这些值作为自动配置类导入到容器中，自动配置类就生效了。**

# 2.配置文件

## 2.1.配置文件介绍 

- SpringBoot使用一个全局的配置文件，配置文件的名字是固定的：`application.properties`和`application.yml`。
- 配置文件的作用：修改SpringBoot自动配置的默认值。
- `application.properties`和`application.yml`一般都放在src/main/resources目录下。

## 2.2.YAML语法

> 基本语法

```yaml
# 1、key: value表示一对键值对(中间的空格必须有！)

# 2、以空格的缩进来控制层级关系，只要是左对齐的一列数据，都是同一个层级的。
# 属性和值也是大小写敏感的！
server:
  port: 1997
```

> 值的写法

```yaml
# 1、普通的值(数字、字符串、布尔)
# key: value 直接写就可以
# 字符串默认不用加单引号/双引号。

# 双引号：会转义字符串里面的特殊字符。
name: "zhangsan \n lisi"   # zhangsan 换行 lisi

# 单引号：不会转义字符串里面的特殊字符。单引号就是原样输出！
name: 'zhangsan \n lisi'   # zhangsan \n lisi

# 2、对象和Map(属性和值/键值对)

# 对象就是k: v Map和对象的写法相似
# 只是注意空格和缩进就可以了！
friends:
	name: zhangsan
	age: 18
	addr: beijing

# 3、数组(List、Set)

# 数组用"- "来表示数组中的一个元素
pets: 
	- cat:
		name: tom
	- dog
		name: ErHa
```

## 2.3.配置文件值的注入

### 2.3.1.@ConfigurationProperties

> Person类

```java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ConfigurationProperties默认从全局配置文件中获取值。
 * @ConfigurationProperties 将配置文件中每一个属性的值,映射到这个组件中。
 * @ConfigurationProperties 告诉SpringBoot将实体类中的所有属性和配置文件中相关的配置进行绑定
 * prefix = "person"：配置文件中哪个下面的所有属性进行映射
 *
 * @Component: 需要把entity加入到容器中才能把yml的值映射到实体类中
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {

    private String name;

    private Integer age;

    private boolean sex;

    private Date birth;

    private Map<String, Object> map;

    private List<Object> list;

    private Dog dog;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", birth=" + birth +
                ", map=" + map +
                ", list=" + list +
                ", dog=" + dog +
                '}';
    }
}
```

> Dog类

```java
public class Dog {

    private String name;

    private Integer age;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

> 依赖

```xml
 <!--导入配置文件处理器-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```

> application.yaml

```yaml
person:
# 普通的key-value
  name: zhangsan
  age: 18
  sex: true
  birth: 2020/7/7
# Map
  map:
    k1: v1
    k2: v2
    k3: v3
# List
  list:
    - list01
    - list02
    - list03
# 对象
  dog:
    name: tom
    age: 3
```

> 测试

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigurationMappingTest {

    @Autowired
    private Person person;

    /**
     * 测试application.yaml配置文件值映射到整个实体类中的字段
     */
    @Test
    public void yamlToEntityAll() {
        System.out.println(person);
    }
}
```

### 2.3.2@Value

> @Value的使用

```java
public class Person {

    /**
     * 从配置文件中获取值
     */
    @Value("${person.name}")
    private String name;

    /**
     * Spring计算表达式
     */
    @Value("#{11 * 2}")
    private Integer age;

    /**
     * 直接赋值
     */
    @Value("true")
    private boolean sex;
   
}
```

### 2.3.3.@ConfigurationProperties和@Value的区别

|                     | @ConfigurationProperties | @Value     |
| ------------------- | ------------------------ | ---------- |
| 功能                | 批量注入配置文件中的属性 | 一个个指定 |
| 松散绑定            | 支持                     | 不支持     |
| SpEL                | 不支持                   | 支持       |
| JSR303数据校验      | 支持                     | 不支持     |
| 复杂数据类型(Map等) | 支持                     | 不支持     |

松散绑定：firstName可以在配置文件中使用first-name。

**一句话：如果我们只是在某个业务逻辑中需要获取配置文件中的某项值，就使用@Value。如果我们专门编写了一个JavaBean来和配置文件进行映射，那么就直接使用@ConfigurationProperties。**

### 2.3.4.JSR303数据校验

```java
@Component
@ConfigurationProperties(prefix = "person")
@Validated // 开始JSR303数据校验
public class Person {

    /**
     * @Email 表示name字段必须填写成邮箱格式
     */
    @Email
    private String name;
```

### 2.3.5.@PropertySource

**@PropertySource：用于加载指定的配置文件。**

**@ConfigurationProperties默认从全局配置文件(application.yml)中获取值。**

> 写person.properties配置文件

```properties
person.name=zhangsan
person.age=18
person.sex=false
```

> 使用@PropertySource注解

```java
/**
 * @PropertySource 只能读取到properties文件中的配置,不能读取到yaml文件的配置
 * @PropertySource 的value是一个数组,可以读取多个指定的配置文件
 */
@Component
@PropertySource(value = {"classpath:person.properties"})
@ConfigurationProperties(prefix = "person")
public class Person {

    private String name;

    private Integer age;

    private boolean sex;
}
```

### 2.3.6.@ImportResource

**@ImportResource：导入Spring的配置文件，让配置文件里面的内容生效。**

SpringBoot中没有Spring的配置文件，我们自己编写的配置文件，SpringBoot也不能自动识别。想让Spring的配置文件加载进SpringBoot中，使用@ImportResource标注在配置上即可。

==这种为SpringBoot添加组件的方式SpringBoot并不推荐。==

```java
// 导入Spring的配置文件使其生效
@ImportResource(locations = {"classpath:beans.xml"})
```

### 2.3.7.@Bean

SpringBoot推荐使用全注解的方式来给Spring容器添加组件！

```java
/**
 * @Configuration： 该注解指明当前类是一个配置类，替代Spring的xml配置文件！
 * 在Spring的xml配置文件中使用<bean><bean/>标签来添加组件。
 * 在@Configuration配置类中使用@Bean注解就可以添加组件。
 */
@Configuration
public class AppMainConf {
    /**
     * @Bean： 将方法的返回值添加到Spring容器中。这个组件默认的id就是方法名！
     */
    @Bean
    public HelloService helloService() {
        System.out.println("*********配置类给容器中添加helloService组件了*********");
        return new HelloService();
    }
}
```

## 2.4.配置文件占位符

```yaml
# 1、占位符可以使用随机数
# 2、占位符也可以引用之前的值
person:
  name: zhangsan${random.uuid} # 随机字符串
  age: ${random.int} # 随机的整数
  dog:
    name: ${person.name} # 引用上面的值
```

## 2.5.profile多环境支持

profile是Spring対不同环境提供不同配置功能的支持，可以通过激活、指定参数等方式快速切换环境。

> 多Profile文件

```yaml
# 我们在主配置文件编写的时候，文件名可以是 application-{profile}.properties/yml
# 在默认情况下使用的application.yml的配置

# application-dev.yml开发环境配置
server:
  port: 8081
  
# application-prod.yml生产环境配置
server:
  port: 8082

# application.yml主配置文件中激活指定配置文件，这样SpringBoot项目就会使用dev环境的配置文件了
spring:
  profiles:
    active: dev 
```

> yaml文件支持多文档块方式

```yaml
server:
  port: 8080
# 激活dev开发环境
spring:
  profiles:
    active: dev
---
# 开发环境
server:
  port: 8081
spring:
  profiles: dev
---
# 生产环境
server:
  port: 8082
spring:
  profiles: prod
```

> 命令行方式指定配置文件

```java
java -jar [jar的名字] --spring.profiles.active=dev
```

## 2.6.配置文件的加载顺序

```shell
# 1、配置文件的优先级从高到低
# 所有位置的配置文件SpringBoot都会被加载，高优先级配置内容会覆盖低优先级配置的内容(覆盖的相同的配置)
# 这四个配置文件都会被SpringBoot加载，不同的配置会形成互补配置！
file:/config/application.yml       # 当前项目下的config文件夹下的配置文件
file:/application.yml              # 当前项目下的配置文件
classpath:/config/application.yml  # resources目录下的config文件夹下的配置文件
classpath:/application.yml         # resources目录下的配置文件

# 2、项目打包之后可以通过spring.config.location命令行来改变默认的配置文件位置
# 所有的配置文件都会被加载并且都会起作用，形成互补配置
java -jar [jar的名字] --spring.config.location=C:\Users\14666\Desktop
```

# 3.自动配置

## 3.1.自动配置原理

```java
//以HttpEncodingAutoConfiguration为例来学习SpringBoot的自动配置

@Configuration(proxyBeanMethods = false) // 表示这是一个配置类
@EnableConfigurationProperties(HttpProperties.class)  // 启用ConfigurationProperties功能，将配置文件中对应的值和HttpProperties绑定起来。

@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET) // Spring底层注解@Conditional，如果满足指定的条件，整个配置类才会生效。判断当前应用是否是Web应用！如果是web应用，当前配置类生效。

@ConditionalOnClass(CharacterEncodingFilter.class) // 判断当前项目是否有CharacterEncodingFilter这个类
@ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true) //判断配置文件中是否存在"spring.http.encoding"这个个配置，matchIfMissing = true如果不存在也是默认存在的。如果配置文件中不配置spring.http.encoding，这个配置类也是生效的！
public class HttpEncodingAutoConfiguration {
```

- 所有在配置文件中能配置的属性都是在【xxxProperties】类中封装，配置文件能配置什么就可以参照某个功能对应的这个属性类。
- **XXXAutoConfiguration是自动配置类，它会给容器中添加组件，然后也会对应的XXXProperties，封装配置文件的相关属性。**

## 3.2.@Conditional

**@Conditional是Spring中的注解，作用是@Conditional指定的条件成立，才给容器中添加组件，配置类里面的所有内容才会生效。**

| @Conditional扩展注解            | 作用（判断是否满足当前指定条件）                 |
| ------------------------------- | ------------------------------------------------ |
| @ConditionalOnJava              | 系统的Java版本是否符合要求                       |
| @ConditionalOnBean              | 容器中存在指定的Bean                             |
| @ConditionalOnMissingBean       | 容器中不存在指定的Bean                           |
| @ConditionalOnExpression        | 满足SpEL表达式                                   |
| @ConditionalOnClass             | 系统中有指定的类                                 |
| @ConditionalOnMissingClass      | 系统中没有指定的类                               |
| @ConditionalOnSingleCondidate   | 容器中只有一个指定的Bean，或者这个Bean是首选Bean |
| @ConditionalOnProperty          | 系统中指定的属性是否有指定的值                   |
| @ConditionalOnResource          | 类路径下是否存在指定资源文件                     |
| @ConditionalOnWebApplication    | 当前是Web环境                                    |
| @ConditionalOnNotWebApplication | 当前不是Web环境                                  |

**一句话：自动配置类必须在一定条件下才能生效。**

> 我们怎么知道哪些自动配置类生效？？

```yaml
# 1、在SpringBoot的配置文件中加入如下配置就可以打印出自动配置报告
debug: true

# 2、在应用启动的时候就会出现"项目评估报告"，就可以查看启用了哪些AutoConfiguration
============================
CONDITIONS EVALUATION REPORT
============================


Positive matches:
-----------------

   AopAutoConfiguration matched:
      - @ConditionalOnProperty (spring.aop.auto=true) matched (OnPropertyCondition)

   AopAutoConfiguration.ClassProxyingConfiguration matched:
      - @ConditionalOnMissingClass did not find unwanted class 'org.aspectj.weaver.Advice' (OnClassCondition)
      - @ConditionalOnProperty (spring.aop.proxy-target-class=true) matched (OnPropertyCondition)

   DispatcherServletAutoConfiguration matched:
      - @ConditionalOnClass found required class 'org.springframework.web.servlet.DispatcherServlet' (OnClassCondition)
      - found 'session' scope (OnWebApplicationCondition)

   DispatcherServletAutoConfiguration.DispatcherServletConfiguration matched:
      - @ConditionalOnClass found required class 'javax.servlet.ServletRegistration' (OnClassCondition)
      - Default DispatcherServlet did not find dispatcher servlet beans (DispatcherServletAutoConfiguration.DefaultDispatcherServletCondition)

```

# 4.日志

## 4.1.日志框架

左边选一个日志门面（抽象层），右边选一个来实现！

| 日志门面(日志的抽象层)                                       | 日志的实现                  |
| ------------------------------------------------------------ | --------------------------- |
| ~~**JCL(Jakarta Commons Logging)**~~、SLF4j(Simple Logging  Facade For Java)、~~**jboss-logging**~~ | Log4j、JUL、Log4j2、Logback |

- 日志框架的选择：
  - 日志门面：SLF4j。
  - 日志实现：Logback。

- SpringBoot底层是Spring框架，Spring框架默认使用`JCL`；SpringBoot选用的日志框架是`SLF4j`和`Logback`。

## 4.2.SLF4j的使用

> 基本使用

以后开发的时候，日志记录方法的调用，不应该直接调用日志的实现类，而是调用日志抽象层的方法。

给系统中导入SLF4j的jar和Logback的jar。

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    logger.info("Hello World");
  }
}
```

## 4.3.SLF4j的使用原理

> SLF4j适配其他日志框架

每一个日志的实现框架都有自己的配置文件。使用SLF4j以后，**配置文件还是做成日志实现框架的配置文件。**

![SLF4j使用原理](http://www.slf4j.org/images/concrete-bindings.png)

## 4.4.统一日志框架

问题：开发A系统使用SLF4j + Logback，但是使用了Spring（Commons-logging）、Hibernate（jBoss-logging）、MyBatis。。。。所以，要统一日志框架！即使是别的日志框架，和A系统一起使用SLF4j和Logback一起输出。

![统一日志框架](http://www.slf4j.org/images/legacy.png)

**如何让系统所有的日志框架都统一到SLF4j？**

1、将系统中其他日志框架先排除。

2、用中间的jar来替换原有的日志框架。

3、我们再来导入SLF4j其他的日志实现。

## 4.4.SpringBoot日志关系

> SpringBoot日志依赖

![SpringBoot日志依赖](https://img-blog.csdnimg.cn/20200708152815377.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

- `log4j-to-slf4j`、`logback-classic`、`jul-to-slf4j`把其他的日志框架转成`slf4j`。
- `slf4j-api`是SpringBoot导入了日志抽象层SLF4J。

**如果我们要引入其他框架？一个要把这个框架默认的日志依赖移除掉？**

**SpringBoot能自动适配所有的日志，而且底层使用slf4j + logback的方式记录日志，引入其他框架的时候，只需要把这个框架依赖的日志框架排除掉即可。**

## 4.5.SpringBoot日志使用

> 日志级别

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 日志的级别由低到高：trace < debug < info < warn < error
     * 可以调整项目日志级别,日志就只会在这个级别以后的级别生效。
     * SpringBoot默认的日志级别是Info
     */
    @Test
    public void testLogLevel() {
        logger.trace("*****trance日志*****");
        logger.debug("*****debug日志*****");
        logger.info("*****info日志*****");
        logger.warn("*****warn日志*****");
        logger.error("*****error日志*****");
    }
}
```

> 日志级别的调整和日志输出的位置

```yaml
# 1、com.ymy.spring.boot.log 这个包下的日志都会输入出trace和trace以上的日志了！！
logging:
  level:
    com.ymy.spring.boot.log: trace

# 2、默认情况下日志只会打印到控制台上,配置logging.file.name会在当前项目下输出日志
# 不指定路径会在当前项目下生产日志文件,指定路径会在指定路径下生成日志
  file:
    name: C:/Users/14666/Desktop/springboot.log
# 3、logging.file.path 生成的日志文件默认为spring.log，只需要写日志文件路径
  file:
    path: C:/Users/14666/Desktop/log/
```

# 5.Web开发

## 5.1.静态资源映射规则

### 5.1.1.webjars静态资源的映射

关于SpringBoot静态资源的映射规则在`WebMvcAutoConfiguration`中。

```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!this.resourceProperties.isAddMappings()) {
        logger.debug("Default resource handling disabled");
        return;
    }
    Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
    CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
    if (!registry.hasMappingForPattern("/webjars/**")) {
        customizeResourceHandlerRegistration(registry.addResourceHandler("/webjars/**")
                                             .addResourceLocations("classpath:/META-INF/resources/webjars/")
                                             .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
    }
    String staticPathPattern = this.mvcProperties.getStaticPathPattern();
    if (!registry.hasMappingForPattern(staticPathPattern)) {
        customizeResourceHandlerRegistration(registry.addResourceHandler(staticPathPattern)
                                             .addResourceLocations(getResourceLocations(this.resourceProperties.getStaticLocations()))
                                             .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
    }
}
```

**静态资源映射规则：**

- 所有`/webjars/**`的请求都去`classpath:/META-INF/resources/webjars/`这个目录下找资源。webjars就是以jar的方式引入静态资源。可以百度搜索webjars，在pom中添加相应的依赖就可以了。

### 5.1.2.ResourceProperties静态资源映射

> ResourceProperties

```java
@ConfigurationProperties(prefix = "spring.resources", ignoreUnknownFields = false)
public class ResourceProperties {

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
			"classpath:/resources/", "classpath:/static/", "classpath:/public/" };
```

**静态资源映射规则：**

- 所有访问`/**`的请求都会去`classpath:/META-INF/resources/`、`classpath:/resources/`、`classpath:/static/`、`classpath:/public/`这些路径下找资源。

> 测试访问静态资源



<img src="https://img-blog.csdnimg.cn/20200708203418711.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70" alt="创建静态资源文件夹" style="zoom: 150%;" />

**浏览器输入http://localhost:8080/asserts/css/bootstrap.min.css就可以访问静态资源。**

### 5.1.3.欢迎页映射规则

静态资源文件夹下的所有`index.html`页面，被`/**`映射。

静态资源文件夹：`classpath:/META-INF/resources/`、`classpath:/resources/`、`classpath:/static/`、`classpath:/public/`。

浏览器访问`localhost:8080/index.html`会去静态资源文件夹将下找这个html页面。

### 5.1.4.图标映射规则

所有的`**/favicon.ico`都是在静态资源文件夹下找。

## 5.2.thymeleaf

### 5.2.1.引入thymeleaf

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

### 5.2.2.thymeleaf的使用

**ThymeleafProperties可以修改thymeleaf的配置。**

```java
@ConfigurationProperties(prefix = "spring.thymeleaf")
public class ThymeleafProperties {

	private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;

    // 只要我们把HTML页面放到"classpath:/templates/"下，thymeleaf就能找到
	public static final String DEFAULT_PREFIX = "classpath:/templates/";

    // 并且配置了默认的后缀是.html
	public static final String DEFAULT_SUFFIX = ".html";
```

> thymeleaf语法

```html
<!--1、导入thymeleaf名称空间-->
<html lang="en" xmlns:th="http://www.thymeleaf.org"></html>

<!--2、thymeleaf语法-->

<!--
th:text 将div里面的文本内容设置为我们指定的值;
可以使用[th:任意属性]来替换原生HTML属性的值.
-->
<div th:text="${hello}"></div>

<!--片段包含-->
th:insert   
th:replcae

<!--遍历-->
th:each

<!--判断-->
th:if
th:unless
th:switch
th:case

<!--声明变量-->
th:object
th:with

<!--属性修改-->
th:attr
th:attrprepend
th:attrappend

<!--修改指定属性默认值-->
th:value
th:href
th:src
...

<!--修改标签体内容-->
th:text
th:utext   不转义特殊字符

<!--声明片段-->
th:fragment

<!--移除片段-->
th:remove
```

> thymeleaf表达式语法

```properties
1.Simple expressions(表达式语法):
	1.1.Variable Expressions: ${...}              # 获取变量表达式
		(1)获取对象的属性、调用方法；
		(2)使用内置的基本对象(${#locale.country})
			#ctx : the context object. 
			#vars: the context variables. 
			#locale : the context locale. 
			#request : (only in Web Contexts) the HttpServletRequest object. 
			#response : (only in Web Contexts) the HttpServletResponse object. 
			#session : (only in Web Contexts) the HttpSession object. 
			#servletContext : (only in Web Contexts) the ServletContext object.
		(3)使用内置的工具对象
			#execInfo : information about the template being processed. 
			#messages : methods for obtaining externalized messages inside variables expressions, in the same way as they would be obtained using #{…} syntax. 
			#uris : methods for escaping parts of URLs/URIs #conversions : methods for executing the configured conversion service (if any). 
			#dates : methods for java.util.Date objects: formatting, component extraction, etc. 
			#calendars : analogous to #dates , but for java.util.Calendar objects. 
			#numbers : methods for formatting numeric objects. 
			#strings : methods for String objects: contains, startsWith, prepending/appending, etc. 
			#objects : methods for objects in general. 
			#bools : methods for boolean evaluation. #arrays : methods for arrays. 
			#lists : methods for lists. 
			#sets : methods for sets. 
			#maps : methods for maps. 
			#aggregates : methods for creating aggregates on arrays or collections. 
			#ids : methods for dealing with id attributes that might be repeated (for example, as a result of an iteration).
	
	1.2.Selection Variable Expressions: *{...}    # 变量的选择表达式,和${}在功能上是一样的，只不过有补充
		(1)补充：配合 th:object="${session.user}" 使用
	
	1.3.Message Expressions: #{...}    # 获取国际化内容的        
	1.4.Link URL Expressions: @{...}   # 定义URL链接 
	1.5.Fragment Expressions: ~{...}       # 片段引用表达式 

2.Literals(字面量)：
    2.1.Text literals: 'one text' , 'Another one!' ,… 
    2.2.Number literals: 0 , 34 , 3.0 , 12.3 ,… 
    2.3.Boolean literals: true , false 
    2.4.Null literal: null 
    2.5.Literal tokens: one , sometext , main ,…

3.Text operations(文本操作):
    3.1.String concatenation: + 
    3.2.Literal substitutions: |The name is ${name}|

4.Arithmetic operations(数学运算):
    4.1.Binary operators: + , - , * , / , % 
    4.2.Minus sign (unary operator): -

5.Boolean operations(布尔操作):
    5.1.Binary operators: and , or 
    5.2.Boolean negation (unary operator): ! , not

6.Comparisons and equality(比较运算):
	6.1.Comparators: > , < , >= , <= ( gt , lt , ge , le ) 
	6.2.Equality operators: == , != ( eq , ne )

7.Conditional operators(条件运算):
	7.1.If-then: (if) ? (then) 
	7.2.If-then-else: (if) ? (then) : (else) 
	7.3.Default: (value) ?: (defaultvalue)

8.Special tokens(特殊操作):
	8.1No-Operation: _
```

## 5.3.SpringMVC自动配置原理

**官网链接：https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-auto-configuration**

Spring Boot provides auto-configuration for Spring MVC that works well with most applications.

The auto-configuration adds the following features on top of Spring’s defaults:

- Inclusion of `ContentNegotiatingViewResolver` and `BeanNameViewResolver` beans.

  - SpringBoot自动配置了ViewResolver，视图解析器，根据方法的返回值得到视图对象，决定如何渲染(转发、重定向)。
  - `ContentNegotiatingViewResolver`：作用就是组合所有的视图解析器。
  - 如何定制视图解析器：我们可以在容器中添加一个`ViewResolver`视图解析器，`ContentNegotiatingViewResolver` 就会自动的将我们的视图解析器组合进来

- Support for serving static resources, including support for WebJars (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-static-content))).

- Automatic registration of `Converter`, `GenericConverter`, and `Formatter` beans.

  - `Converter`：转换器，对象类型转换使用`Converter`组件。
  - `Formatter` ：格式化器，2020-12-17需要转化成日期类型，就需要使用格式化器。
  - 自己添加的`Converter`和`Formatter` 只需要放在容器中即可。

- Support for `HttpMessageConverters` (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-message-converters)).

  - `HttpMessageConverters`：SpringMVC用来转换Http请求和响应的`HttpMessageConverter集合，可以获取容器中所有的`HttpMessageConverter`。
  - 添加自己的`HttpMessageConverter`，只需要将组件加入到容器即可。																		

- Automatic registration of `MessageCodesResolver` (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-message-codes)).

  - `MessageCodesResolver` ：用来定义错误代码的生成规则。

- Static `index.html` support.

- Custom `Favicon` support (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-favicon)).

- Automatic use of a `ConfigurableWebBindingInitializer` bean (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-web-binding-initializer)).

  - 我们可以配置一个`ConfigurableWebBindingInitializer` 添加到容器来替换默认的。

    ```java
    @Override
    protected ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer(
        FormattingConversionService mvcConversionService, Validator mvcValidator) {
        try {
            // 从容器中拿ConfigurableWebBindingInitializer
            return this.beanFactory.getBean(ConfigurableWebBindingInitializer.class);
        }
        catch (NoSuchBeanDefinitionException ex) {
            // 从容器中拿不到就调用父类的方法
            return super.getConfigurableWebBindingInitializer(mvcConversionService, mvcValidator);
        }
    }
    ```

  - `ConfigurableWebBindingInitializer` ：数据绑定器的作用是**将请求数据绑定到JavaBean中**。

**扩展SpringMVC：**If you want to keep those Spring Boot MVC customizations and make more [MVC customizations](https://docs.spring.io/spring/docs/5.2.3.RELEASE/spring-framework-reference/web.html#mvc) (interceptors, formatters, view controllers, and other features), you can add your own `@Configuration` class of type `WebMvcConfigurer` but **without** `@EnableWebMvc`.

**完全接管SpringMVC：**If you want to take complete control(完全控制) of Spring MVC, you can add your own `@Configuration` annotated with `@EnableWebMvc`, or alternatively(或者)add your own `@Configuration`-annotated `DelegatingWebMvcConfiguration` as described in the Javadoc of `@EnableWebMvc`.

If you want to provide custom instances of `RequestMappingHandlerMapping`, `RequestMappingHandlerAdapter`, or `ExceptionHandlerExceptionResolver`, and still keep the Spring Boot MVC customizations, you can declare a bean of type `WebMvcRegistrations` and use it to provide custom instances of those components.

## 5.4.国际化

### 5.4.1.编写国际化配置文件

![国际化配置文件](https://img-blog.csdnimg.cn/20200709124522243.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)



### 5.4.2.国际化相关的组件

> SpringBoot自动配置好了国际化相关的组件

```java
@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean(name = AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME, search = SearchStrategy.CURRENT)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Conditional(ResourceBundleCondition.class)
@EnableConfigurationProperties
public class MessageSourceAutoConfiguration {

	private static final Resource[] NO_RESOURCES = {};

	@Bean
	@ConfigurationProperties(prefix = "spring.messages")
	public MessageSourceProperties messageSourceProperties() {
        /* MessageSourceProperties中的basename默认是"message"
        * 我们项目中的basename就是login，去掉后面的"语言名"和"国家名"
        */
		return new MessageSourceProperties();
	}

	@Bean
	public MessageSource messageSource(MessageSourceProperties properties) {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		if (StringUtils.hasText(properties.getBasename())) {
            // 设置国际化文件的basename
			messageSource.setBasenames(StringUtils
					.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(properties.getBasename())));
		}
		if (properties.getEncoding() != null) {
			messageSource.setDefaultEncoding(properties.getEncoding().name());
		}
		messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
		Duration cacheDuration = properties.getCacheDuration();
		if (cacheDuration != null) {
			messageSource.setCacheMillis(cacheDuration.toMillis());
		}
		messageSource.setAlwaysUseMessageFormat(properties.isAlwaysUseMessageFormat());
		messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
		return messageSource;
	}
```

> 在application.yml中修改SpringBoot国际化相关的basename

```yaml
spring:
  messages:
# 修改basename
    basename: i18n.login 
```

> 去HTML页面获取国际化的值

```html
<h1 class="h3 mb-3 font-weight-normal" th:text="#{login.tip}">Please sign in</h1>

<label class="sr-only" th:text="#{login.username}">Username</label>
<input type="text" class="form-control" th:placeholder="#{login.username}" required="" autofocus="">
<label class="sr-only" th:text="#{login.password}">Password</label>
<input type="password" class="form-control" th:placeholder="#{login.password}" required="">

<label>
    <input type="checkbox" value="remember-me"> [[#{login.remember}]]
</label>

<button class="btn btn-lg btn-primary btn-block" type="submit" th:text="#{login.button}">Sign in</button>
```

### 5.4.3.区域信息解析器

> 默认LocaleResolver

```java
// WebMvcAutoConfiguration中配置的区域信息解析器
// 默认的区域信息解析器是根据Http请求的请求头来获取Locale进行国际化
@Bean
@ConditionalOnMissingBean
@ConditionalOnProperty(prefix = "spring.mvc", name = "locale")
public LocaleResolver localeResolver() {
    if (this.mvcProperties.getLocaleResolver() == WebMvcProperties.LocaleResolver.FIXED) {
        return new FixedLocaleResolver(this.mvcProperties.getLocale());
    }
    AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
    localeResolver.setDefaultLocale(this.mvcProperties.getLocale());
    return localeResolver;
}
```

> 自定义LocaleResolver

```java
/**
 * 自定义的login.html的区域信息解析器
 * 根据请求来解析地区，可以实现点击切换语言的功能
 */
public class LoginLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        Locale locale = Locale.getDefault();
        // LoginConstant.PARAM_KEY 是 language
        String language = request.getParameter(LoginConstant.PARAM_KEY);
        if (!StringUtils.isEmpty(language)) {
            String[] split = language.split("_");
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }
}
```

login.html页面中发送的请求

```html
<a class="btn btn-sm" th:href="@{/index.html(language=zh_CN)}">中文</a>
<a class="btn btn-sm" th:href="@{/index.html(language=en_US)}">English</a>
```

在配置类中添加自定义的区域信息解析器

```java
/**
     * 自定义区域信息解析器
     * @return
     */
@Bean
public LocaleResolver localeResolver() {
    return new LoginLocaleResolver();
}
```

## 5.5.登录拦截器

