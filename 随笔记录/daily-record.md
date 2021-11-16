# 2021-11-8 Linux磁盘挂载和分区

[参考文档](https://zhuanlan.zhihu.com/p/87595311)

```shell
# 查看磁盘容量
df -h

# 卸载磁盘 -v执行时显示详细的信息
# umount -v /dev/sda1          通过设备名卸载 
# umount -v /mnt/mymount/      通过挂载点卸载  
umount -v /home

# 将磁盘重新分配给10G，以前是47G
lvreduce -L 10G /dev/mapper/centos-home

# 让上面的操作生效
mkfs.xfs /dev/mapper/centos-home -f

# 挂载磁盘磁盘（这时候只有10G的空间挂载到了 /home）
# -a：将 /etc/fstab 中定义的所有档案系统挂上。
mount -a /home

# 重新查看磁盘容量
df -h

# 整理磁盘，查看空闲容量，里面有free，表示可以挂在到需要扩容的磁盘上面去
vgdisplay

# 将空闲的放到需要需要扩容的磁盘上面去
lvextend -L +36G /dev/mapper/centos-root

# 让上面一步生效
xfs_growfs /dev/mapper/centos-root

# 重新查看磁盘，成功
df -h
```



# 2021-11-9 日期序列化和反序列化

`spring-boot-starter-web` 中依赖了 `spring-boot-starter-json`。

但是，在 spring 配置文件中配置日期的序列化和反序列化只是针对 `Date` 类型有效。

对于 `LocalDateTime` 类型需要手动配置。



定义 `Jackson2ObjectMapperBuilderCustomizer` 加入到 Spring 容器，实现自定义的日期格式化策略。

当前策略：

- `LocalDateTime` 默认序列化为**时间戳**。
- 前端传的**时间戳** 反序列化为 `LocalDateTime`。

```java
@Configuration
public class JacksonCustomConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer())
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer());
    }

    /**
     * 序列化: {@link LocalDateTime} => timestamp(long)
     */
    private static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

        @Override
        public void serialize(LocalDateTime localDateTime, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (localDateTime != null) {
                long timestamp = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
                gen.writeNumber(timestamp);
            }
        }
    }

    /**
     * 反序列化: timestamp(long) => {@link LocalDateTime}
     */
    private static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            long timestamp = p.getValueAsLong();
            if (timestamp > 0) {
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
            }
            return null;
        }
    }

}
```



# 2021-11-11 @PropertySource使用

**注意**：`*.yaml` 配置中的内容不能读到。

加载指定的属性文件 `*.properties` 到 Spring 的 Environment 中。

可以配合 `@Value` 和 `@ConfigurationProperties` 使用。

- `@PropertySource` 和 `@Value` 组合使用，可以将自定义属性文件中的属性变量值注入到当前类的使用`@Value` 注解的成员变量中。
- `@PropertySource `和 `@ConfigurationProperties` 组合使用，可以将属性文件与一个Java类绑定，将属性文件中的变量值注入到该Java类的成员变量中。



在 `resources` 资源文件夹下添加 `person.properties`。

实际上，Maven项目打包后，`resources` 文件夹下的文件会打包到 `target/classes` 目录下。

![](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/spring5/image.7kten1ye5yc0.png)

```properties
# person.properties
person.name=zs
person.age=18
```



`@PropertySource `和 `@ConfigurationProperties` 组合使用，这样就可以读到指定配置文件中的内容。

```java
@Component
@ConfigurationProperties(prefix = "person")
@PropertySource("classpath:person.properties")
public class PersonProperties {

    private String name;

    private Integer age;
	
    // getter and setter
}
```

# 2021-11-16 SpringBoot的Jackson配置

配置参考一：https://my.oschina.net/davisky/blog/1814911。

`spring.jackson`配置说明：https://www.chendalei.com/json-properties.html。

功能：Empty字段不返回给前端。



方式一：

```java
spring.jackson.default-property-inclusion=non_empty
```





方式二：

```java
@Configuration
public class JacksonConfig {
    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        // 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
        // Include.Include.ALWAYS 默认
        // Include.NON_DEFAULT 属性为默认值不序列化
        // Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的json是没有这个字段的。这样对移动端会更省流量
        // Include.NON_NULL 属性为NULL 不序列化,就是为null的字段不参加序列化
        // 等价于在application.properties中写 spring.jackson.default-property-inclusion=non_empty
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper;
    }
}
```

**使用第二种方式，第一种自动失效**。