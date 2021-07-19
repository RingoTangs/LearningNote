# 一、Mongo基础

## 1. 基本使用

### 1.1. docker安装

```shell
# 1. docker 拉取镜像
docker pull mongo:latest

[root@Sweety ~] docker images
REPOSITORY   TAG            IMAGE ID       CREATED         SIZE
mongo        latest         0e120e3fce9a   12 days ago     449MB


# 2. 运行 mongo 容器
# 挂载到容器中的/data/db, 作为 mongodb 的存储目录
# --auth表示连接MongoDB需要认证
docker run -itd -p 27017:27017 -v /root/mongo/data:/data/db --name mongoDB mongo:latest --auth

# 3. 进入容器
# mongo admin表示以 admin 用户身份进入mongo，相当于oracle的用户也就是库，mysql的库
[root@Sweety data]# docker exec -it mongoDB mongo admin

# 创建admin管理员账号
>db.createUser({ user:'admin',pwd:'123',roles:[ { role:'userAdminAnyDatabase', db: 'admin'},"readWriteAnyDatabase"]});

# 尝试使用上面创建的用户信息进行连接。
> db.auth('admin', '123');
1
```



### 1.2. 权限介绍

MongoDB安装完成后，默认是没有权限验证的，默认是不需要输入用户名密码即可登录的，但是往往数据库方面我们会出于安全性的考虑而设置用户名密码。

启用访问控制前，请确保在 admin 数据库中拥有 userAdmin 或 userAdminAnyDatabase 角色的用户。
该用户可以管理用户和角色，例如：创建用户，授予或撤销用户角色，以及创建或修改定义角色。

**用户权限**：

1. mongodb是没有默认管理员账号，所以要先添加管理员账号，在开启权限认证。

2. 切换到admin数据库，添加的账号才是管理员账号。

3. 用户只能在用户所在数据库登录，包括管理员账号。

4. mongo的用户是以数据库为单位来建立的，每个数据库有自己的管理员。

5. 管理员可以管理所有数据库，但是不能直接管理其他数据库，要先在admin数据库认证后才可以。

   **注：帐号是跟着库走的，所以在指定库里授权，必须也在指定库里验证**。

------

**权限说明**：

```
Built-In Roles（内置角色）：
1. 数据库用户角色：read、readWrite; 
2. 数据库管理角色：dbAdmin、dbOwner、userAdmin；
3. 集群管理角色：clusterAdmin、clusterManager、clusterMonitor、hostManager； 
4. 备份恢复角色：backup、restore； 
5. 所有数据库角色：readAnyDatabase、readWriteAnyDatabase、userAdminAnyDatabase、dbAdminAnyDatabase 
6. 超级用户角色：root 
// 这里还有几个角色间接或直接提供了系统超级用户的访问（dbOwner 、userAdmin、userAdminAnyDatabase）
```

**权限具体说明**：

```
Read：允许用户读取指定数据库。
readWrite：允许用户读写指定数据库。
dbAdmin：允许用户在指定数据库中执行管理函数，如索引创建、删除，查看统计或访问system.profile。
userAdmin：允许用户向system.users集合写入，可以找指定数据库里创建、删除和管理用户。
clusterAdmin：只在admin数据库中可用，赋予用户所有分片和复制集相关函数的管理权限。
readAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的读权限。
readWriteAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的读写权限。
userAdminAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的userAdmin权限。
dbAdminAnyDatabase：只在admin数据库中可用，赋予用户所有数据库的dbAdmin权限。
root：只在admin数据库中可用。超级账号，超级权限。
```



### 1.3. 用户管理

**创建root超级管理员**：

创建完admin管理员，创建一个超级管理员 root 角色：root
root角色用于 关闭数据库 db.shutdownServer()。

```shell
> use admin
switched to db admin
> db.createUser({user: "root",pwd: "123",roles: [ { role: "root", db: "admin" } ]})
```

**创建用户自己的数据库的角色**：

当账号管理员和超级管理员，可以为自己的数据库创建用户了。
（坑）这时候一定，一定要切换到所在数据库上去创建用户，不然创建的用户还是属于admin。

```shell
> use position
> db.createUser({user: "position",pwd: "123",roles: [ { role: "dbOwner", db: "position" } ]})

# 登录用户自己的数据库(进入position数据库)
docker exec -it mongoDB mongo position
> db.auth('position', '123')
1											# 登录成功
```

**查看用户**：

```shell
# 查看全局所有用户，要切换到 admin 数据库中
> use admin
switched to db admin
> db.auth('admin','123') 				# 验证用户，相当于登录
1
> db.system.users.find()
```

```shell
# 查看当前数据库下的用户
> use position
> show users
```

**删除用户**：在admin中登录的用户必须有能删除用户的权限！

```shell
> use admin
# 根据id删除用户
> db.system.users.remove({_id:"XXX.XXX"})
# 根据用户名删除用户
> db.system.users.remove({user:"XXXXXX"})
```





### 1.2. 基本概念

### 1.2. 基本指令

```shell
# 1. 显示所有的数据库
show dbs

# 2. use + “数据库名” 表示进入到指定数据库(数据库不存在, 也可以创建, 第一次插入文档时自动创建数据库)
use test

# 3. db命令: 查看当前所在数据库(默认是test)
> db
test

# 4. 查看数据库中所有的集合
show collections/tables

# 5. 删除当前数据库
db.dropDatabase()

# 6. 删除当前数据库下的指定集合
db.collection.drop()
```

**注意:** *在 MongoDB 中，集合只有在内容插入后才会创建! 就是说，创建集合(数据表)后要再插入一个文档(记录)，集合才会真正创建。*

## 2. 插入文档

### 2.1. insertOne()

```shell
# 语法：
db.collection.insertOne(
   <document>,
   {
      writeConcern: <document>
   }
)
```

------

一、插入不带`_id`的文档：

```shell
> db.products.insertOne( { item: "card", qty: 15 } );
{
	"acknowledged" : true,
	"insertedId" : ObjectId("60e529107b7d5637e9d71226")
}
```

由于我们插入的文档中并没有`_id`字段，mongo会自用创建并添加**唯一的** `ObjectId()`值。

------

二、插入带`_id`属性的文档：

```shell
> db.products.insertOne( { _id: 10, item: "box", qty: 20 } );
{ "acknowledged" : true, "insertedId" : 10 }
```

文档中含有`_id`属性，插入后就会使用我们的id。

但是这个 `_id` 的值必须唯一，否则会发生key重复错误。



`_id: 10` 已经存在，然后再添加相同key的文档就会报错 WriteError。

```shell
> try {
... db.products.insertOne( { _id: 10, "item" : "packing peanuts", "qty" : 200 } );
... } catch (e) {
...    print (e);
... }
```

报错如下：

```shell
WriteError({
	"index" : 0,
	"code" : 11000,
	"errmsg" : "E11000 duplicate key error collection: test.products index: _id_ dup key: { _id: 10.0 }",
	"op" : {
		"_id" : 10,
		"item" : "packing peanuts",
		"qty" : 200
	}
})
```

------

三、添加 Write Concern（写入安全机制）

```shell
> db.products.insertOne(
	{"item": "envelops", "qty": 100, type: "self-sealing"}, 
	{writeConcern: {w: "majority", wtimeout: 100} }    
);
{
	"acknowledged" : true,
	"insertedId" : ObjectId("60e52df07b7d5637e9d71228")
}
```

如果该插入操作的时间超过`wtimeout`的限制，就会抛出异常：

```shell
WriteConcernError({
   "code" : 64,
   "errmsg" : "waiting for replication timed out",
   "errInfo" : {
     "wtimeout" : true,
     "writeConcern" : {    // Added in MongoDB 4.4
       "w" : "majority",
       "wtimeout" : 100,
       "provenance" : "getLastErrorDefaults"
     }
   }
})
```



**Write Concern用法**：

```shell
# 语法
{ w: <value>, j: <boolean>, wtimeout: <number> }
```

`w`：该选项要求确认操作已经传播到指定数量的mongod实例或指定标签的mongod实例。

- w可选的的值：
  - `w: 1`：应答式写入。要求确认操作已经传播到指定的单个mongod实例或副本集主实例(缺省为1)。
  - `w: 0`：非应答式写入。不返回任何响应，所以无法知道写入是否成功，但是对于尝试向已关闭的套接字写入或者网络故障会返回异常信息。
  - `w: >1`：用于副本集环境。该值用于设定写入节点的数目，包括主节点。
  - `w: majority`：大多数。适用于集群架构，要求写入操作已经传递到绝大多数投票节点以及主节点后进行应答。

`j`：该选项要求确认写操作已经写入journal日志之后应答客户端(需要开启journal功能)。

`wtimeout`：该选项指定一个时间限制，以防止写操作无限制被阻塞导致无法应答给客户端。wtimeout的单位为ms，当w值大于1时生效，该参数即仅适用于集群环境当某个节点写入时超出指定wtimeout之后，mongod将返回一个错误在捕获到超时之前，mongod并不会撤销其他节点已成功完成的写入。wtimeout值为 0 时等同于没有配置wtimeout选项，容易导致由于某个节点挂起而无法应答。

### 2.2. insertMany()

```shell
# 语法：传入的文档是数组
db.collection.insertMany(
   [ <document 1> , <document 2>, ... ],
   {
      writeConcern: <document>,
      ordered: <boolean>			# true：有序插入；false：无序插入
   }
)
```

----

一、批量插入

```shell
db.products.insertMany( [
	{ item: "card", qty: 15 },
	{ item: "envelope", qty: 20 },
	{ item: "stamps" , qty: 30 }
] );
```

返回结果：

```shell
{
	"acknowledged" : true,
	"insertedIds" : [
		ObjectId("60e532487b7d5637e9d7122c"),
		ObjectId("60e532487b7d5637e9d7122d"),
		ObjectId("60e532487b7d5637e9d7122e")
	]
}
```

由于插入的文档没有添加`_id`，会自动添加该属性。

二、无序插入

```shell
db.products.insertMany( [
	{ _id: 10, item: "large box", qty: 20 },
	{ _id: 11, item: "small box", qty: 55 },
	{ _id: 12, item: "envelope", qty: 100},
	{ _id: 13, item: "stamps", qty: 125 },
	{ _id: 14, item: "bubble wrap", qty: 30}
], { ordered: false } );
```



## 3. 查询文档

### 3.1. db.collection.find()

语法：

```shell
db.collection.find(query, projection)
```

- `query<document>`：可选。使用 **查询运算符** 指定选择过滤器。如果要返回集合中的所有文档，这个参数可以省略或者传递一个空的文档 `{}`。
- `projection<document>`：可选。投影，指定要在与查询过滤器匹配的文档中返回的字段。要返回匹配文档中的所有字段，忽略该参数即可。

### 3.2. 查询运算符

**比较运算符**：

- `$eq`：等于。
- `$gt`：greate than 大于。
- `$gte`：greate than equal 大于等于。
- `$in`：匹配数组中的任意值。in ['a', 'b', 'c'] 。`||`。
- `$lt`：less than 小于。
- `$lte`：less than equal 小于等于。
- `$ne`：not equal 不等于。
- `$nin`：not in。不在指定数组范围内。

**逻辑运算符**：

- `$and`：&&。
- `$not`：!。
- `$nor`：两个条件都不匹配。`!(a || b) => !a && !b`。
- `$or`：||。

**Element**：

- `$exists`：匹配具有指定字段的文档。
- `$type`：如果字段类型是指定的类型，就选择这个文档。

**Evaluation**：

- `$expr`：在查询语句中使用聚合表达式。
- `$jsonSchema`：根据给定的json模式校验文档。
- `$mod`：对字段值进行模运算并选择具有指定结果的文档。
- `$regex`：正则表达式匹配。
- `$text`：文本搜索。
- `$where`：匹配满足 JavaScript 表达式的文档。

**数组**：

- `$all`：匹配包含查询中指定的所有元素的数组。
- `$elemMatch`：如果数组字段中的元素与所有指定[`$elemMatch`](https://docs.mongodb.com/manual/reference/operator/query/elemMatch/#mongodb-query-op.-elemMatch)条件匹配，则选择文档。
- `$size`：如果数组字段是指定大小，则选择文档。

----

#### 3.2.1. $eq

```shell
# 语法：
{ <field>: { $eq: <value> } }
```
```shell
# 测试用例
db.inventory.insertMany([
{ _id: 1, item: { name: "ab", code: "123" }, qty: 15, tags: [ "A", "B", "C" ] },
{ _id: 2, item: { name: "cd", code: "123" }, qty: 20, tags: [ "B" ] },
{ _id: 3, item: { name: "ij", code: "456" }, qty: 25, tags: [ "A", "B" ] },
{ _id: 4, item: { name: "xy", code: "456" }, qty: 30, tags: [ "B", "A" ] },
{ _id: 5, item: { name: "mn", code: "000" }, qty: 20, tags: [ [ "A", "B" ], "C" ] }
]);
```

```shell
# 1: 直接等于
# 以下这两个写法效果是一样的！
db.inventory.find( { qty: { $eq: 20 } } );
db.inventory.find( { qty: 20 } );

# 2: 嵌入文档中的字段等于
# 注意：嵌入的的<field>双引号不能丢。
db.inventory.find( { "item.name": { $eq: "ab" } } );
db.inventory.find( { "item.name": "ab" } );

# 3：文档中的数组元素等于1个值
# 数组中包含"B"的文档都会被差出来
# 但是不会查出 _id:5 的文档, 因为数组中的 "B" 是 ["A", "B"]的形式
db.inventory.find( { tags: { $eq: "B"} } );
db.inventory.find( { tags: "B" } );

# 4: 文档中的数组元素等于一个数组值
# 数组中包含 ["A", "B"] 的文档都会差出来
# 注意：这里表示的是 [数组] 中查找另外一个 [数组]！
# 这里只会查出 _id:3 和 _id:5 的文档
db.inventory.find( { tags: { $eq: ["A", "B"] } } );
db.inventory.find( { tags: ["A", "B"] } );
```

---

**正则表达式匹配**：隐式和显式正则表达式匹配之间的行为差异。

```shell
# 测试用例
db.collection.insertMany( [
	{ _id: 001, company: "MongoDB" },
	{ _id: 002, company: "MongoDB2" },
] );
```

```shell
# 1: $eq 匹配字符串
# 无论是隐式匹配还是显式使用 ，字符串都会扩展以返回相同的值 $eq
db.collection.find({ company: { $eq: "MongoDB" } }, { _id: 0 } );
db.collection.find( { company: "MongoDB" }, { _id: 0 } )

# 2: $eq 匹配正则表达式
# 使用 $eq 和 正则表达式的显式查询将仅匹配也是正则表达式的对象。
db.collection.find( { company: { $eq: /MongoDB/ } } );  # 不会按正则表达式匹配


# 3：正则表达式匹配
# 以下两种方法等价
db.collection.find( { company: /MongoDB/ } );			# 会按正则表达式匹配
db.collection.find( { company: { $regex: /MongoDB/ } } );
```

---

#### 3.2.2. $gt

```shell
# 测试用例
db.inventory.insertMany([
{ _id: 1, item: { name: "ab", code: "123" }, qty: 15, tags: [ "A", "B", "C" ] },
{ _id: 2, item: { name: "cd", code: "123" }, qty: 20, tags: [ "B" ] },
{ _id: 3, item: { name: "ij", code: "456" }, qty: 25, tags: [ "A", "B" ] },
{ _id: 4, item: { name: "xy", code: "456" }, qty: 30, tags: [ "B", "A" ] },
{ _id: 5, item: { name: "mn", code: "000" }, qty: 20, tags: [ [ "A", "B" ], "C" ] }
]);
```

```shell
# 查询 qit > 20 的文档
db.inventory.find( { qty: { $gt: 20 } } );
```

---

#### 3.2.3. $in

```shell
# 语法 ==> in: 只要数组中有一个值和目标值匹配就查询出来! <==> or
{ field: { $in: [<value1>, <value2>, ... <valueN> ] } }
```

测试用例使用上边的 `db.inventory`。

```shell
db.inventory.find( { qty: { $in: [5, 15] } } );
```

`$in` 可以和 `/pattern/` 形式的正则表达式匹配。

不能使用`$regex`操作符表达式中的`$in`。

```shell
db.inventory.find( { tags: { $in: [/^A/, /^B/] } } );
```

---

#### 3.2.4. $and

```shell
# 语法
# $and <==> 逻辑与 && 
# 注意：short circuit evaluate 短路计算：第一个expression是false, 后面的表达式就不用执行了。
{ $and: [ { <expression1> }, { <expression2> } , ... , { <expressionN> } ] }
```

```shell
# 测试用例
db.inventory.insertMany([
{ _id: 1, item: { name: "ab", code: "123" }, qty: 15, tags: [ "A", "B", "C" ] },
{ _id: 2, item: { name: "cd", code: "123" }, qty: 20, tags: [ "B" ] },
{ _id: 3, item: { name: "ij", code: "456" }, qty: 25, tags: [ "A", "B" ] },
{ _id: 4, item: { name: "xy", code: "456" }, qty: 30, tags: [ "B", "A" ] },
{ _id: 5, item: { name: "mn", code: "000" }, qty: 20, tags: [ [ "A", "B" ], "C" ] }
]);
```

```shell
# 1、$and简单使用
db.inventory.find( { 
	$and: [ 
		{ tags: "A" }, 
		{ qty: { $gte: 20 } },
		{ item: { $exists: 1 } } 
	] 
} );

# 2、$and隐式写法
db.inventory.find( {
	$and: [
		{ qty: {$exists: 1} },
		{ qty: 15}
	]
} );

# 等价于 
db.inventory.find( {
	qty: {
		$exists: 1,
		$eq: 15
	}
} );

# 3、$and 具有指定相同运算符的多个表达式的查询
# 此查询不能使用隐式AND操作构造，因为它$or多次使用运算符
# 等价于 (qty >= 20 && qty <= 25) && ( tags || tags == "A" )
db.inventory.find( {
	$and: [
		{ $and: [ { qty: { $gte: 20 } }, { qty: { $lte: 25 } } ] },
		{ $or: [ { tags: { $exists: 1 } }, { tags: "A" } ] }
	]
} );

# 输出结果
{ "_id" : 2, "item" : { "name" : "cd", "code" : "123" }, "qty" : 20, "tags" : [ "B" ] }
{ "_id" : 3, "item" : { "name" : "ij", "code" : "456" }, "qty" : 25, "tags" : [ "A", "B" ] }
{ "_id" : 5, "item" : { "name" : "mn", "code" : "000" }, "qty" : 20, "tags" : [ [ "A", "B" ], "C" ] }
```

---

#### 3.2.5. $not 非

```shell
# 语法 <==> ！<==> if (!a)
{ field: { $not: { <operator-expression> } } }

# 在集合中查询文档
# 1. price < 1.99 or
# 2. price == 1.99 or
# 3. price field does not exist
db.inventory.find( { price: { $not: { $gt: 1.99 } } } )
```

- `{ $not: { $gt: 1.99 } }` 和 `{ $lte: 1.99 }`不同。后者查询的文档属性必须 exists  并且值小于1.99或等于1.99。
- `$not` 仅仅影响其他操作符，不会独立地检查文档和属性。
- 使用`$not`进行逻辑取反；使用`$ne` 直接测试属性的值。

```shell
# $not的使用
db.products.insertMany( [
	{ price: 10, name: "苹果" },
	{ price: null, name: "xxx"},
	{ price: 30, name: "西红柿" }
] );

# 会搜索出null值
db.products.find( {
	price: { $not: { $gt: 30} } 
} );

# 会过滤出null值
db.products.find( {
	price: { $lte: 30 } 
} );

```

---

`$not`和正则表达式一起使用。

```shell
# 查询选择的所有文档 inventory集合，其中的item.name字段值并没有以字母a开始。
db.inventory.find( { "item.name": { $not: /^a.*/ } } )

#  (Starting in MongoDB 4.0.7)
db.inventory.find( { "item.name": { $not: { $regex: "^a.*" } } } )
db.inventory.find( { "item.name": { $not: { $regex: /^a.*/ } } } )
```

---

#### 3.2.6. $nor 既不也不

```shell
# 语法
{ $nor: [ { <expression1> }, { <expression2> }, ...  { <expressionN> } ] }
```



```shell
# !a && !b ==> 不存在的key或null都会查询出来
db.inventory.find( { $nor: [ { price: 1.99 }, { sale: true } ]  } )
```

- `price`字段的值不等于1.99 并且 `sale` 字段的值不是 true。
- `price`字段的值不等于1.99 但是 `sale` 字段的值不存在。
- `price` 字段的值不存在 但是 `sale` 字段的值不是true。
- `price、sale`两个字段都不存在。

---

`$nor` 和 `$exists` 搭配使用：

```shell
db.inventory.find( { $nor: [ { price: 1.99 }, { price: { $exists: false } },
                             { sale: true }, { sale: { $exists: false } } ] } )
```

- `price、sale`这两个字段一定存在。
- `price` 不是 1.99（可以为null），`sale`不是true（可以为null）。



#### 3.2.7. $exists

```shell
# 语法
{ field: { $exists: <boolean> } }
```

- `<boolean> is true`：查询返回的文档中必须有这个字段（属性/key）。值可以是null。
- `<boolean> is false`：查询仅返回不包含该字段的文档。
- 注意：MongoDB的`$exists`确实**不**对应于SQL操作 `exists`。
- 对于 SQL  `exists`，请参阅`$in`运算符。

```shell
# 简单使用(以下三种写法等价)
# in [a, b] <==> a || b
# not in [a, b] <==> ! (a || b)  <==> !a && !b <==> 既不也不
db.inventory.find( { qty: { $exists: true, $nin: [ 5, 15 ] } } );
db.inventory.find( {
	$and: [
		{ qty: { $exists: true } },
		{ qty: { $nin: [5, 15] } }
	]
} );
db.inventory.find( {
	$and: [
		{ qty: { $exists: true } },
		{ $nor: [ { qty: 5 }, { qty: 15 } ] }
	]
} );
```

- `qty`字段必须存在，该字段的值既不等于5也不等于15。



### 3.3. 投影运算符

- `$`：投影数组中与查询条件匹配的第一个元素。
- `$elemMatch`：投影数组中与指定[`$elemMatch`](https://docs.mongodb.com/manual/reference/operator/projection/elemMatch/#mongodb-projection-proj.-elemMatch)条件匹配的第一个元素。
- `$meta`：投影 `$text`操作期间分配的文档分数（score）。
- `$slice`：限制从数组投影的元素数量。支持跳过和限制切片。

### 3.4. 杂项运算符

- `$comment`：向查询谓词添加注释。
- `$rand`：生成 0 到 1 之间的随机浮点数。



