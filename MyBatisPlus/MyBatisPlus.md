# 1.MyBatisPlus概述

> 为什么要学习MyBatisPlus呢？

MyBatisPlus可以节省我们大量工作时间，所有的CRUD代码它都可以自动化完成。

> 是什么？

**官网：https://mp.baomidou.com/**

[MyBatis-Plus](https://github.com/baomidou/mybatis-plus)（简称 MP）是一个 [MyBatis](http://www.mybatis.org/mybatis-3/) 的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。

> 特性

- **无侵入**：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
- **损耗小**：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
- **强大的 CRUD 操作**：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
- **支持 Lambda 形式调用**：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
- **支持主键自动生成**：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
- **支持 ActiveRecord 模式**：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
- **支持自定义全局通用操作**：支持全局通用方法注入（ Write once, use anywhere ）
- **内置代码生成器**：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
- **内置分页插件**：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
- **分页插件支持多种数据库**：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库
- **内置性能分析插件**：可输出 Sql 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
- **内置全局拦截插件**：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作

> 支持数据库

- mysql 、 mariadb 、 oracle 、 db2 、 h2 、 hsql 、 sqlite 、 postgresql 、 sqlserver 、 presto
- 达梦数据库 、 虚谷数据库 、 人大金仓数据库

# 2.QuickStart

> 依赖

```xml
<properties>
    <java.version>1.8</java.version>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <mybatisPlus.starter.version>3.3.2</mybatisPlus.starter.version>
</properties>

<!--web-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!--mysql驱动-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>

<!--mybatisPlus-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>${mybatisPlus.starter.version}</version>
</dependency>
```

> 配置

在`application.yaml`中添加数据源相关配置

```yaml
# 数据源的配置
spring:  
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url:  jdbc:mysql://39.97.3.60:3306/mybatis_plus?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: 333
    type: com.mysql.cj.jdbc.MysqlDataSource

# 配置日志
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

在SpringBoot主配置类上添加`@MapperScan`注解，扫描mapper包下所有映射接口。

```java
@MapperScan(value = {"com.ymy.spring.boot.mapper"})
@SpringBootApplication
public class MybatisPlus01HelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlus01HelloApplication.class, args);
    }
}
```

> 编码

封装实体类`User.java`

```java
public class User {
    private String id;
    private String name;
    private Integer age;
    private String email;
    public User() {

    }
    public User(String id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
```

编写Mapper类`UserMapper.java`，只需要继承`BaseMapper`并传泛型即可。

```java
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ymy.spring.boot.entity.User;

public interface UserMapper extends BaseMapper<User> {
}
```

> 测试

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testCrud() {
        // UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper，所以不填写就是无任何条件
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
        User user = userMapper.selectById(1);
        System.out.println(user);
    }
}
```

通过以上几个简单的步骤，我们就实现了 User 表的 CRUD 功能，甚至连 XML 文件都不用编写！

从以上步骤中，我们可以看到集成`MyBatis-Plus`非常的简单，只需要引入 starter 工程，并配置 mapper 扫描路径即可。

# 3.CRUD扩展

## 3.1.测试插入

测试代码

```java
@Resource
private UserMapper userMapper;

/**
* 测试插入
*/
@Test
public void testInsert() {
    // 这里实例类没有写ID，MyBatisPlus会自动创建ID
    int ret = userMapper.insert(new User("Ringo", 18, "Ringo@126.com")); 
    System.out.println("*********"+ ret +"*********");
}
```

MyBatisPlus日志信息

```java
Creating a new SqlSession
SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@518bfd90] was not registered for synchronization because synchronization is not active
JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@64aad809] will not be managed by Spring
==>  Preparing: INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? ) 
==> Parameters: 1283229632123965441(String), Ringo(String), 18(Integer), Ringo@126.com(String)
<==    Updates: 1
Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@518bfd90]
*********1*********
```

在封装实体类的时候，MyBatisPlus给我们设置一个随机的ID，why？？？？

## 3.2.分布式ID生成策略

**分布式系统唯一ID生成策略：https://www.cnblogs.com/liujianping/p/10401842.html**

**雪花算法：**

snowflake是Twitter开源的分布式ID生成算法，结果是一个long型的ID。其核心思想是：使用41bit作为毫秒数(时间戳)，10bit作为机器的ID（5个bit是数据中心，5个bit的机器ID），12bit作为毫秒内的流水号（意味着每个节点在每毫秒可以产生 4096 个 ID），最后还有1个符号位，永远是0。具体实现的代码可以参看https://github.com/twitter/snowflake。

![雪花算法](https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=76559403,2226856912&fm=26&gp=0.jpg)



**雪花算法工程落地实现：****`Hutool`工具包。官网：https://www.hutool.cn/**

雪花算法可以保证：

- 所有生成的id按时间趋势递增。
- 整个分布式系统内不会产生重复ID（因为有数据中心ID + 机器ID来区分）。

雪花算法优点：

- 不依赖于数据库，灵活方便，且性能优于数据库。

- ID按照时间在单机上是递增的。

雪花算法缺点：

- 在单机上是递增的，但是由于涉及到分布式环境，每台机器上的时钟不可能完全同步，也许有时候也会出现不是全局递增的情况。

## 3.3.测试更新

```java
/**
* 测试修改
*/
@Test
public void testUpdate() {
    User user = new User();
    user.setId(1283263685715890177L);
    user.setAge(20);
    user.setName("abc");

    // updateById()传的是对象 会根据条件自动拼接动态sql
    int ret = userMapper.updateById(user);
    System.out.println("*********"+ ret +"*********");
}
```

## 3.4.自动填充

数据表记录创建时间、修改时间！这些操作一遍都是自动化完成的，我们不希望手动更新。

阿里巴巴开发手册：所有的数据库表：gmt_create、gmt_modified几乎所有的表都要配置上！而且需要自动化！

> 方式一：数据库级别

1、数据库表增加`gmt_create`和`gmt_modified`字段

![数据库表](https://img-blog.csdnimg.cn/20200715135008546.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

2、同步实体类的属性

```java
@TableField(value = "gmt_create")
private LocalDateTime createTime;

@TableField(value = "gmt_modified")
private LocalDateTime updateTime;
```

3、执行更新方法查看数据库结果即可

![数据库更新结果](https://img-blog.csdnimg.cn/20200715135914618.png)



> 方式二：代码级别

1、删除数据库`gmt_create`和`gmt_modified`字段的默认值和更新操作

![初始化表](https://img-blog.csdnimg.cn/20200715140307644.png)

2、在实体类的字段属性上添加注解`@TableField(fill = FieldFill.INSERT)`

```java
// 在插入时更新该字段
@TableField(value = "gmt_create", fill = FieldFill.INSERT)
private LocalDateTime createTime;

// 在插入修改时更新该字段
@TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
private LocalDateTime updateTime;
```

3、编写处理器来处理这个自动填充注解

```java
@Slf4j
@Component // 一定要注意把MyMetaObjectHandler组件加入到Spring容器中
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 插入时的填充策略
    // 插入时需要填充createTime和updateTime两个字段
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insert strategy...");
        // setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

    // 更新时的填充策略
    // 跟新时只需要填充一个字段即可！
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update strategy...");
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
```

4、执行插入和更新方法，数据库表中的日期就会自动填充了！

![代码级别自动填充结果](https://img-blog.csdnimg.cn/20200715142407796.png)

## 3.5.乐观锁

**乐观锁：**顾名思义十分乐观，它总是认为不会出现问题，无论干什么都不会去上锁！如果出现了问题，再次更新值测试。

**悲观锁：**顾名思义十分悲观，它总是认为一定会出现问题，无论做什么操作都会加上锁，然后再去操作。

乐观锁的实现方式：

- 取出记录时，获取当前`version`。
- 更新记录时，带上这个`version`。
- 执行更新时，`set version = newVersion where version = oldVersion`。
- 如果`version`不对，表示当前记录已经被其他线程修改了，此次更新失败。

> 测试mybatisPlus乐观锁插件

1、数据表添加`version`字段

![添加version字段](https://img-blog.csdnimg.cn/20200715144017392.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

2、更新实体类的属性

```java
// @Version 代表这是一个乐观锁的注解
@Version
private Integer version;
```

3、添加`OptimisticLockerInterceptor `插件

```java
@Configuration
public class MybatisPlusConf {
    // 配置mybatis的乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
```

4、单线程下乐观锁更新测试

```java
/**
* 4、测试乐观锁(单线程)
*/
@Test
public void testOptimisticLock() {
    // 1、查询用户信息
    User user = userMapper.selectById(12832451523202L);

    // 2、修改用户信息
    user.setAge(20);

    // 3、执行更新操作
    int ret = userMapper.updateById(user);
    System.out.println("*********"+ ret +"*********");
}
```

数据表`version`会发生改变

![version字段的变化](https://img-blog.csdnimg.cn/2020071515153917.png)

## 3.6.简单查询

按照ID查询

```java
/**
* 测试按照ID查询
*/
@Test
public void testSelectById() {
    // 1、按照ID查询一个
    User user = userMapper.selectById(12832451523202L);

    // 2、同时查询多个ID
    List<User> users = userMapper.selectBatchIds(Arrays.asList("12832451523202", "12832451523203"));
    System.out.println(users);
}
```

使用Map按照条件查询

```java
/**
* 测试条件查询 map
*/
@Test
public void testSelectByCondition() {
    Map<String, Object> column = new HashMap<>();
    column.put("age", 18);
    List<User> users = userMapper.selectByMap(column);
    System.out.println(users);
}
```

## 3.7.分页查询

> 配置分页组件

```java
/**
* 配置mybatisPlus的分页插件
*/
@Bean
public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
    // paginationInterceptor.setOverflow(false);
    // 设置最大单页限制数量，默认 500 条，-1 不受限制
    // paginationInterceptor.setLimit(500);
    // 开启 count 的 join 优化,只针对部分 left join
    paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
    return paginationInterceptor;
}
```

> 测试分页查询

```java
/**
* 测试分页查询
*/
@Test
public void testPageSelect() {
    // Page(当前页, 页面大小)
    // 以前通过写limit的sql，mybatisPlus用Page对象给我们封装了！
    Page<User> page = new Page<>(2, 3);
    userMapper.selectPage(page, null);
    List<User> users = page.getRecords();
    System.out.println(users);
}
```

## 3.8.逻辑删除

**物理删除：**从数据库中直接删除。

**逻辑删除：**在数据库中没有被移除，而是通过一个变量来让他失效。本质是更新操作，不是删除操作。

> 测试逻辑删除

1、在数据表中增加`deleted`字段

![增加deleted字段](https://img-blog.csdnimg.cn/20200715161648490.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

2、实体类字段上添加`@TableLogic`注解

```java
@TableLogic
private Integer deleted;
```

3、`application.yaml`配置逻辑删除

```yaml
mybatis-plus:
# 配置逻辑删除
  global-config:
    db-config:
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
```

4、测试逻辑删除

````java
/**
* 测试逻辑删除
*/
@Test
public void testLogicDelete() {
    // 删除
    int ret = userMapper.deleteById(12832451523206L);
    System.out.println("*********"+ ret +"*********");
}
````

5、数据库显示结果

![逻辑删除结果](https://img-blog.csdnimg.cn/20200715163952949.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

# 4.代码自动生成器

> 依赖

```xml
<!--mybatisPlus自动生成代码需要-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.3.2</version>
</dependency>
<dependency>
    <groupId>org.apache.velocity</groupId>
    <artifactId>velocity-engine-core</artifactId>
    <version>2.0</version>
</dependency>
```

> 配置

```yaml
server:
  port: 1998
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url:  jdbc:mysql://39.97.3.60:3306/mybatis_plus?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: 333
    type: com.mysql.cj.jdbc.MysqlDataSource

mybatis-plus:
  configuration:
    # 配置日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  # 配置逻辑删除
  global-config:
    db-config:
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
```

> 自动生成代码设置

```java
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {

    //数据源
    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://39.97.3.60:3306/mybatis_plus?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "333";

    // 父包的全类名
    public static final String PACKAGE_PARENT = "com.ymy.mybatis.plus";

    // 设置逻辑删除字段名字
    public static final String LOGIC_DELETE_FIELD_NAME = "deleted";

    // 乐观锁字段的名字
    public static final String VERSION_FILED_NAME = "version";

    // 自动填充的字段名字
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";

    // 数据库映射的表名 重点改这个！
    public static final String[] INCLUDE = {"user"};


    public static void main(String[] args) {
        // 1、创建代码生成器对象
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        // 设置代码的生成路径
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/mybatisPlus-02-autoGenerator/src/main/java");

        // 设置作者
        globalConfig.setAuthor("RingoTangs");

        // 设置是否打开资源管理器
        globalConfig.setOpen(false);

        // 是否覆盖原来生成的
        globalConfig.setFileOverride(false);

        // 设置服务名字
        globalConfig.setServiceName("%sService");

        // 生成策略
        globalConfig.setIdType(IdType.ASSIGN_ID);

        // 日期类型
        globalConfig.setDateType(DateType.TIME_PACK);

        // 设置swagger
        globalConfig.setSwagger2(true);

        mpg.setGlobalConfig(globalConfig);

        // 2、配置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName(DRIVER_NAME);
        dataSourceConfig.setUrl(URL);
        dataSourceConfig.setUsername(USERNAME);
        dataSourceConfig.setPassword(PASSWORD);
        dataSourceConfig.setDbType(DbType.MYSQL);

        mpg.setDataSource(dataSourceConfig);

        // 3、包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(PACKAGE_PARENT);

        mpg.setPackageInfo(packageConfig);


        // 4、策略配置
        StrategyConfig strategyConfig = new StrategyConfig();

        // 数据库的表名 重点就是改这个！
        strategyConfig.setInclude(INCLUDE);

        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);

        // 设置逻辑删除字段名字
        strategyConfig.setLogicDeleteFieldName(LOGIC_DELETE_FIELD_NAME);

        // 设置自动填充
        TableFill gmtCreate = new TableFill(GMT_CREATE, FieldFill.INSERT);
        TableFill gmtModified = new TableFill(GMT_MODIFIED, FieldFill.INSERT_UPDATE);
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(gmtCreate);
        tableFillList.add(gmtModified);
        strategyConfig.setTableFillList(tableFillList);

        // 乐观锁配置
        strategyConfig.setVersionFieldName(VERSION_FILED_NAME);

        mpg.setStrategy(strategyConfig);

        // 执行代码生成器
        mpg.execute();
    }
}
```



