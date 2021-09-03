# 一、Spring Data Repositories

`Spring Data repository` 目标是减少为各种持久性存储实现数据访问层所需的样板代码量。

由此可见`Spring Data repository `是一个很抽象的概念，既可以操作 `MySQL`，又可以操作`NoSQL`。

```yaml
# spring-boot 连接 mongo 配置
# db.auth('admin', '123'); 这种类型登录。
spring:
  data:
    mongodb:
      uri: mongodb://admin:123@192.1.3.60:27017/?authSource=admin
      database: test
```

```properties
# 配置日志
logging.level.org.springframework.data.mongodb.core=DEBUG
```

## 1. 核心概念

Spring Data的核心接口是`Repository`。它的泛型需要 `domain` 和主键 `id` 作为类型参数。

此接口主要用作 标记接口，以捕获要使用的类型并帮助您发现扩展此接口的接口。

该接口下的 `CrudRepository` 接口为正在管理的实体类提供复杂的 CRUD 功能。

> Spring Data还提供了 `JpaRepository`或 `MongoRepository`。
>
> 这些接口扩展`CrudRepository`，并添加了新的功能。

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/Repository类继承图.4toz0hyz00o0.png" alt="继承图" style="zoom:150%;" />

### 1.1. CrudRepository接口

```shell
# 测试 CrudRepository 接口
# 测试用例
db.students.insertMany([
	{name: "张三", age: 18, gender: "男"},
	{name: "李四", age: 17, gender: "男"},
	{name: "王五", age: 19, gender: "男"},
	{name: "赵六", age: 20, gender: "男"},
	{name: "小红", age: 18, gender: "女"},
]);
```

`CrudRepository`提供的方法可以进行基本的CRUD。

---

`PagingAndSortingRepository<T, ID>` 除了CRUD之外还有分页的功能。

```java
// 查询第一页，页面大小为20.
PagingAndSortingRepository<User, Long> repository = // … get access to a bean
Page<User> users = repository.findAll(PageRequest.of(1, 20));
```

---

使用关键字，衍生查询。

```java
public interface StudentRepository extends CrudRepository<Student, String> {
    long countByAge(Integer age);
    List<Student> removeStudentsByAge(Integer age);
}
```



## 2. 定义Repository接口

### 2.1. Repository定义

- 通常情况下，我们自定义的 `xxxRepository` 需要继承 `Repository or CrudRepository or PagingAndSortingRepository`。

- 如果不想继承 Spring Data 接口可以使用`@RepositoryDefinition`。

  ```java
  // 效果和继承 Repository 接口一样
  @RepositoryDefinition(domainClass = Student.class, idClass = String.class)
  public interface DefinedStudentRepository {
      List<Student> findAll();
  }
  ```

- 中间存储库接口用 `@NoRepositoryBean`。 确保将该注释添加到 Spring Data 不应在运行时为其创建实例的所有存储库接口。**有选择地公开 CRUD 方法：将想要展示的方法copy过来即可**。

  ```java
  // Repository 和 CrudRepository都加了 @NoRepository注解，表示中间存储库接口！
  @NoRepositoryBean
  interface MyBaseRepository<T, ID> extends Repository<T, ID> {
  
    Optional<T> findById(ID id);
  
    <S extends T> S save(S entity);
  }
  
  interface UserRepository extends MyBaseRepository<User, Long> {
    User findByEmailAddress(EmailAddress emailA
                            ddress);
}
  ```
  



### 2.2. 多个Spring Data模块

- `xxxxRepository` 扩展了特定接口的存储库，则它是特定 Spring Data 模块的有效候选者。

  ```java
  // MyRepository扩展 JpaRepository。UserRepository间接扩展 JpaRepository。
  // 它们是 Spring Data JPA 模块的有效候选者。
  interface MyRepository extends JpaRepository<User, Long> { }
  
  @NoRepositoryBean
  interface MyBaseRepository<T, ID> extends JpaRepository<T, ID> { … }
  
  interface UserRepository extends MyBaseRepository<User, Long> { … }
  ```

- 使用通用接口的 Repository 定义。

  ```java
  // AmbiguousRepository和AmbiguousUserRepository仅延伸Repository。
  // 但多个模块无法区分这些 Repository 绑定的是 Srping Data JPA 还是 Spring Data Mongodb。
  interface AmbiguousRepository extends Repository<User, Long> { … }
  
  @NoRepositoryBean
  interface MyBaseRepository<T, ID> extends CrudRepository<T, ID> { … }
  
  interface AmbiguousUserRepository extends MyBaseRepository<User, Long> { … }
  ```

- 在domain类上添加注解的Repository定义。

  ```java
  interface PersonRepository extends Repository<Person, Long> { … }
  
  // 它用 JPA@Entity注释进行了注释，所以这个存储库显然属于 Spring Data JPA。
  @Entity
  class Person { … }
  
  interface UserRepository extends Repository<User, Long> { … }
  
  // 使用 Spring Data MongoDB 的@Document注解进行注解，显然属于Mongodb。
  @Document
  class User { … }
  ```



## 3. 定义查询方法

有两种方式在`repository`中定义查询方法：

- 直接从方法名派生查询。
- 手动定义查询。

### 3.1. 从方法名创建查询

```java
interface PersonRepository extends Repository<Person, Long> {

    List<Person> findByEmailAddressAndLastname(EmailAddress emailAddress, String lastname);

    // Enables the distinct flag for the query
    List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
    List<Person> findPeopleDistinctByLastnameOrFirstname(String lastname, String firstname);

    // Enabling ignoring case for an individual property
    List<Person> findByLastnameIgnoreCase(String lastname);
    // Enabling ignoring case for all suitable properties
    List<Person> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);

    // Enabling static ORDER BY for a query
    List<Person> findByLastnameOrderByFirstnameAsc(String lastname);
    List<Person> findByLastnameOrderByFirstnameDesc(String lastname);
}
```

- 解析查询方法名称分为主语和谓语。第一部分 ( `find…By`, `exists…By`) 定义查询的主题，第二部分构成谓词。
- `find`（或其他引入关键字）和之间的任何文本都被`By`认为是描述性的，除非使用结果限制关键字之一（如`Distinct`）。

### 3.2. 属性表达式

```java
// 通过定义嵌套的属性来定义约束
List<Person> findByAddressZipCode(String zipCode);
```

假设 `Person`有`Address`属性（其中包含`ZipCode`）。在这种情况下，该方法会创建`x.address.zipCode`属性遍历。

尽管这应该适用于大多数情况，但算法可能会选择错误的属性。假设这个`Person`类也有一个`addressZip`属性。该算法将在第一个分割轮中匹配，选择错误的属性，并失败（因为 的类型`addressZip`可能没有`code`属性）。

要解决这种歧义，您可以`_`在方法名称中使用手动定义遍历点。所以我们的方法名称如下：

```java
List<Person> findByAddress_ZipCode(ZipCode zipCode);
```

因为将下划线字符视为保留字符，所以强烈建议遵循标准的 Java 命名约定（即，不要在属性名称中使用下划线，而是使用驼峰式大小写）。



### 3.3. 特殊参数处理

在查询方法中使用`Pageable`、`Slice`和`Sort`。

```java
// Page 继承自 Slice（分页查询）

// Page 通过基础结构触发计数查询来计算总数，它可能性能损耗比较大，可以用Slice来替代。
Page<User> findByLastname(String lastname, Pageable pageable);

Slice<User> findByLastname(String lastname, Pageable pageable);

// 排序
List<User> findByLastname(String lastname, Sort sort);

// 分页
List<User> findByLastname(String lastname, Pageable pageable);
```

> -  API 接受`Sort`并`Pageable`期望将非`null`值传递给方法。如果您不想应用任何排序或分页，请使用`Sort.unsorted()`和`Pageable.unpaged()`。
> - 排序选项也通过`Pageable`实例处理。如果只需要排序，在方法参数中添加 Sort 即可。

```java
// PageRequest 是 Pageable 接口的实现
public class PageRequest extends AbstractPageRequest {
	private final Sort sort;
    
	protected PageRequest(int page, int size, Sort sort) {

		super(page, size);

		Assert.notNull(sort, "Sort must not be null!");

		this.sort = sort;
	}
    
    // 这个方法就可以实现 "排序" + "分页"
    public static PageRequest of(int page, int size, Sort sort) {
		return new PageRequest(page, size, sort);
	}
}
```

---

**定义排序表达式**

```java
Sort sort = Sort.by("firstname").ascending()
  .and(Sort.by("lastname").descending());
```

**使用类型安全 API 定义排序表达式**

```java
TypedSort<Person> person = Sort.sort(Person.class);

Sort sort = person.by(Person::getFirstname).ascending()
  .and(person.by(Person::getLastname).descending());
```

### 3.4. 限制查询结果

可以使用`first`或`top`关键字来限制查询方法的结果，这两个关键字可以互换使用。

可以将一个可选的数值附加到`top`或`first`指定要返回的最大结果大小。如果省略该数字，则假定结果大小为 1。

```java
User findFirstByOrderByLastnameAsc();

User findTopByOrderByAgeDesc();

Page<User> queryFirst10ByLastname(String lastname, Pageable pageable);

Slice<User> findTop3ByLastname(String lastname, Pageable pageable);

List<User> findFirst10ByLastname(String lastname, Sort sort);

List<User> findTop10ByLastname(String lastname, Pageable pageable);
```

限制表达式还支持`Distinct`查询。

```java
List<Person> findDistinctTopBy(Sort sort);
```

此外，对于将结果集限制为一个实例的查询，`Optional`支持将结果用关键字包装。

### 3.5. 返回集合或迭代器

查询方法，返回多个结果可以使用标准的Java `Iterable`，`List`和`Set`。

除此之外，Spring Data 还支持返回 `Streamable` 自定义扩展`Iterable`。

**一、使用 Streamable 作为查询方法返回类型**

可以使用任何集合类型的`Streamable`替代`Iterable`品。它提供了访问非并行`Stream`（缺少 from `Iterable`）的便捷方法，以及直接`….filter(…)`和`….map(…)`覆盖元素并将其连接`Streamable`到其他元素的能力：

**使用 Streamable 组合查询方法结果**：

```java
interface PersonRepository extends Repository<Person, Long> {
    Streamable<Person> findByFirstnameContaining(String firstname);
    Streamable<Person> findByLastnameContaining(String lastname);
}

// 查询结果会合并
// Streamable 可以转成 Stream. 进行流式计算
Streamable<Person> result = repository.findByFirstnameContaining("av")
    .and(repository.findByLastnameContaining("ea"));
```

---

**二、返回自定义 streamable 包装器类型**

spring Data 允许将这些 streamable 的包装器作查询方法返回类型，前提是它们满足以下条件：

- 类型实现`Streamable`接口。
- 包装类中需要有公开的构造器或命名静态工厂法`of(…)`或`valueOf(…)`该取`Streamable`作为参数。

```java
// 实体类
class Product {                                         
  MonetaryAmount getPrice() { … }
}

// streamable 包装类
@Data
@NoArgsConstructor
@AllArgsConstructor
class Products implements Streamable<Product> {         

  private Streamable<Product> streamable;

  public MonetaryAmount getTotal() {                    
    return streamable.stream()
      .map(Priced::getPrice)
      .reduce(Money.of(0), MonetaryAmount::add);
  }


  @Override
  public Iterator<Product> iterator() {                 
    return streamable.iterator();
  }
}

interface ProductRepository implements Repository<Product, Long> {
  // 这里不用返回 Streamable 了， 直接返回 wrapper 即可
  Products findAllByDescriptionContaining(String text); 
}
```



### 3.6. Repository Return NULL？

从 Spring Data 2.0 开始，返回**单个实例**的 `Repository CRUD`方法使用 Java 8`Optional`来指示可能缺少值。

返回Collection、Wrapper 和 Stream 的 Repository 方法保证永远不会返回`null`，而是返回相应的空表示（长度是0）。

```java
public interface StudentRepository extends CrudRepository<Student, String> {
    // 方法返回空值的处理
    Optional<Student> findById(String id);    
}

// 如果返回null 就会执行 orElse();
studentRepository.findById("a").orElse(new Student());
```

---

可以使用 `Spring Framework` 的可空性注解来表达存储库方法的可空性约束：

- `@NonNullApi`: 在包级别上用于声明参数和返回值分别是既不接受也不返回`null`值。
- `@NonNull`：参数或返回值不能是null。
- `@Nullable`：参数或返回值可以是null。

### 3.8. Stream查询结果

```java
Stream<Student> readAllByNameNotNull();
```

> - 并非所有 Spring Data 模块当前都支持`Stream`作为返回类型。
> -  `Stream`潜在地包装了底层数据存储特定的资源，因此必须在使用后关闭。您可以`Stream`使用`close()`方法或使用 Java 7`try-with-resources`块手动关闭，如以下示例所示：

```java
// try-with-resources语法
try (Stream<User> stream = repository.findAllByCustomQueryAndStream()) {
  stream.forEach(…);
}
```



### 3.9. 异步查询结果

通过Spring的异步方法进行查询，这意味着该方法在调用时立即返回，而实际查询发生在已提交给 Spring 的任务中`TaskExecutor`。

异步查询不同于响应式查询，不应混合使用。

```java
// 使用java.util.concurrent.Future作为返回类型
@Async
Future<User> findByFirstname(String firstname);               

// 使用 Java 8java.util.concurrent.CompletableFuture作为返回类型
@Async
CompletableFuture<User> findOneByFirstname(String firstname); 

// 使用 aorg.springframework.util.concurrent.ListenableFuture作为返回类型
@Async
ListenableFuture<User> findOneByLastname(String lastname); 
```



# 二、MongoTemplate

`MongoTemplate`类，位于`org.springframework.data.mongodb.core`包下，是中央级的Spring的MongoDB的支持，并提供了与数据库交互的丰富的功能集。

**一旦配置，MongoTemplate 就是线程安全的，可以跨多个实例重复使用**。

spring-boot的自动配置中，默认帮我们配置了 `MongoTemplate`。

```java
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(MongoDatabaseFactory.class)
class MongoDatabaseFactoryDependentConfiguration { 
    @Bean
    @ConditionalOnMissingBean(MongoOperations.class)
    MongoTemplate mongoTemplate(MongoDatabaseFactory factory, 
                                MongoConverter converter) {
        return new MongoTemplate(factory, converter);
    }	
}
```

## 1. crud操作

### 1.1. 基本操作

```java
// insert
mongoTemplate.insert(new Student("Hello", 50, "男"));

// find
mongoTemplate.findAll(Student.class);

// update
mongoTemplate.updateMulti(query(where("age").gte(15)), 
                          update("gender", "unknown"), Student.class);
// remove
mongoTemplate.remove(Student.class).all();
```



### 1.2. save和insert文档

**一、API使用**

> - insert和save操作之间的区别在于，如果对象尚不存在，则save操作会执行insert操作。
>
> - insert或save时，如果`Id`未设置该属性，则假设其值将由数据库自动生成。因此，`ObjectId`要成功自动生成  ，类中的`Id`属性或字段的类型必须是`String`、 `ObjectId`或  `BigInteger`。

**save**:

- `void` **save** `(Object objectToSave)`：将对象保存到默认集合。
- `void` **save** `(Object objectToSave, String collectionName)`: 对象保存到指定的集合中。

**insert**:

- `void` **insert** `(Object objectToSave)`：将对象插入到默认集合中。
- `void` **insert** `(Object objectToSave, String collectionName)`:对象插入到指定的集合中。

---

**二、文档保存在哪个集合中**？

有两种方法可以管理用于文档的集合名称：

- 使用的默认集合名称是更改为以小写字母开头的类名称。所以一个`com.test.Person`类存储在`person`集合中。可以通过为`@Document`注释提供不同的集合名称来对此进行自定义。
- 还可以通过提供自己的集合名称作为所选`MongoTemplate`方法调用的最后一个参数collectionName来覆盖集合名称。



**三、insert或save单个对象**：

- **insert**：插入一个对象。如果存在具有相同 的现有文档，`id`则会生成错误。
- **insertAll**：将一个`Collection`对象作为第一个参数。此方法根据之前指定的规则检查每个对象并将其插入到适当的集合中。
- **save**: 保存文档，如果 `_id`相同，则会重写该文档，相当于更新！。

### 1.3. 更新文档

更新文档的方法：

- **updateFirst**：用更新的文档更新与查询文档条件匹配的第一个文档。
- **updateMulti**：使用更新的文档更新与查询文档条件匹配的所有对象。

> 注意：updateFirst 不支持排序。

一些更新修饰符，例如`$push`and `$addToSet`，允许嵌套额外的运算符。

```java
// { $push : { "category" : { "$each" : [ "spring" , "data" ] } } }
new Update().push("category").each("spring", "data")

// { $push : { "key" : { "$position" : 0 , "$each" : [ "Arya" , "Arry" , "Weasel" ] } } }
new Update().push("key").atPosition(Position.FIRST).each(Arrays.asList("Arya", "Arry", "Weasel"));

// { $push : { "key" : { "$slice" : 5 , "$each" : [ "Arya" , "Arry" , "Weasel" ] } } }
new Update().push("key").slice(5).each(Arrays.asList("Arya", "Arry", "Weasel"));

// { $addToSet : { "values" : { "$each" : [ "spring" , "data" , "mongodb" ] } } }
new Update().addToSet("values").each("spring", "data", "mongodb");
```



### 1.4. "Upserting"集合中的文档

与执行`updateFirst`操作相关，还可以执行“upsert”操作，如果找不到与查询匹配的文档，它将执行插入操作。插入的文档是查询文档和更新文档的组合。以下示例显示了如何使用该`upsert`方法：

```java
template.update(Person.class)
  .matching(query(where("ssn").is(1111).and("firstName").is("Joe").and("Fraizer").is("Update"))
.apply(update("address", addr))
.upsert(); // .all() .first()
```

> unsert()不支持排序。请使用[findAndModify](https://docs.spring.io/spring-data/mongodb/docs/3.0.9.RELEASE/reference/html/#mongo-template.find-and-upsert)申请`Sort`。



### 1.5. Finding and Upserting集合中的文档

```java
template.insert(new Person("Tom", 21));
template.insert(new Person("Dick", 22));
template.insert(new Person("Harry", 23));

Query query = new Query(Criteria.where("firstName").is("Harry"));
Update update = new Update().inc("age", 1);

// 更新返回旧值
Person oldValue = template.update(Person.class)
  .matching(query)
  .apply(update)
  .findAndModifyValue(); // return's old person object

// 更新返回新值
Person newestValue = template.update(Person.class)
  .matching(query)
  .apply(update)
  .withOptions(FindAndModifyOptions.options().returnNew(true)) // Now return the newly updated document when updating
  .findAndModifyValue();
```

该`FindAndModifyOptions`方法可以让你设置的选项`returnNew`，`upsert`以及`remove`从前面的代码片段延伸.

```java
Person upserted = template.update(Person.class)
  .matching(new Query(Criteria.where("firstName").is("Mary")))
  .apply(update)
  .withOptions(FindAndModifyOptions.options().upsert(true).returnNew(true))
  .findAndModifyValue()
```



### 1.6. 聚合管道更新

更新可以包括以下阶段：

- `AggregationUpdate.set(…).toValue(…)` → `$set : { … }`
- `AggregationUpdate.unset(…)` → `$unset : [ … ]`
- `AggregationUpdate.replaceWith(…)` → `$replaceWith : { … }`

```java
AggregationUpdate update = Aggregation.newUpdate()
    .set("average").toValue(ArithmeticOperators.valueOf("tests").avg())     
    .set("grade").toValue(ConditionalOperators.switchCases(                 
        when(valueOf("average").greaterThanEqualToValue(90)).then("A"),
        when(valueOf("average").greaterThanEqualToValue(80)).then("B"),
        when(valueOf("average").greaterThanEqualToValue(70)).then("C"),
        when(valueOf("average").greaterThanEqualToValue(60)).then("D"))
        .defaultTo("F")
    );

template.update(Student.class)                                              
    .apply(update)
    .all();   
```

```js
db.students.update(                                                         
   { },
   [
     { $set: { average : { $avg: "$tests" } } },                            
     { $set: { grade: { $switch: {                                          
                           branches: [
                               { case: { $gte: [ "$average", 90 ] }, then: "A" },
                               { case: { $gte: [ "$average", 80 ] }, then: "B" },
                               { case: { $gte: [ "$average", 70 ] }, then: "C" },
                               { case: { $gte: [ "$average", 60 ] }, then: "D" }
                           ],
                           default: "F"
     } } } }
   ],
   { multi: true }                                                          
)
```



### 1.7. 查找和替换文档

```java
Optional<User> result = template.update(Person.class)      
    .matching(query(where("firstame").is("Tom")))          
    .replaceWith(new Person("Dick"))
    .withOptions(FindAndReplaceOptions.options().upsert()) 
    .as(User.class) // 用于映射运算结果的可选投影类型。如果没有给出初始域类型，则使用。       
    .findAndReplace();      
```

> 它`findAndReplace`只会根据可能给定的排序顺序替换与查询条件匹配的第一个文档。



### 1.8. 删除文档的方法

```java
template.remove(tywin, "GOT");                                              

template.remove(query(where("lastname").is("lannister")), "GOT");           

template.remove(new Query().limit(3), "GOT");                               

template.findAllAndRemove(query(where("lastname").is("lannister"), "GOT");  

template.findAllAndRemove(new Query().limit(3), "GOT");   
```



## 2. 查询文件

### 2.1. 查询集合中的文档

```java
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

// ...

List<Person> result = template.query(Person.class)
  .matching(query(where("age").lt(50).and("accounts.balance").gt(1000.00d)))
  .all();
```

所有的 find 方法都以一个`Query`对象作为参数。

此对象定义用于执行查询的条件和选项。

这些条件是通过使用一个`Criteria`对象来指定的，该对象具有一个名为`where`实例化新`Criteria`对象的静态工厂方法。

建议使用import static`org.springframework.data.mongodb.core.query.Criteria.where`和`Query.query`使查询更具可读性。

