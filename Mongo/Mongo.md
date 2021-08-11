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

## 4.更新操作符

### 4.1. 属性: $currentDate

`$currentDate`：将字段的值设置为current date，或者作为 Date 或 timestamp。

```java
// 语法：
{ $currentDate: { <field1>: <typeSpecification1>, ... } }
```

`<typeSpecification1>`可以是：

- 布尔值true，用于将字段值设置为当前日期作为日期（ISO/GMT时间）。
- `$type: "timestamp or $type: "date"`明确地指定了类型。这个操作符对大小写十分敏感，只能接受小写的 `timestamp、date`。
- 如果在嵌入在文档或者数组中指定 `field` 字段，使用点表示法。

注意：如果 field 字段不存在。`$currentDate` 就会将该属性添加到文档中。

#### 4.1.1. 示例

```javascript
// 创建customers文档
db.customers.insertOne(
   // ISO时间 GMT时间
   { _id: 1, status: "a", lastModified: ISODate("2013-10-02T01:11:18.965Z") }
);
```

下面的操作更新 `lastModified` 字段作为为 current date。`"cancellation.date"`字段作为 current timestamp。

```javascript
// lastModified字段保存当前日期
// cancellation.date保存当前的时间戳
// $set 去更新指定字段
db.customers.updateOne(
   { _id: 1 },
   {
     $currentDate: {
        // 使用当前OSI时间作为字段的值
        lastModified: true,		
        // 使用当前时间戳作为字段的值 
        "cancellation.date": { $type: "timestamp" }
     },
     $set: {
        "cancellation.reason": "user request",
        status: "D"
     }
   }
)
```

查询操作来查看操作结果：

```javascript
db.customers.find().pretty();

{
	"_id" : 1,
	"status" : "D",
	"lastModified" : ISODate("2021-07-29T16:06:46.074Z"),
	"cancellation" : {
		"date" : Timestamp(1627574806, 1),
		"reason" : "user request"
	}
}
```
#### 4.1.2. 聚合替代$currentDate

从Mongodb 4.2开始，更新方法就能接受聚合管道操作了。

例如，上一个例子就可以被重写为：

- 使用聚合阶段操作符 `$set` 和 聚合变量 `NOW`修改为 current datetime。
- `CLUSTER_TIME` 来修改为 current timestamp。

注意：

- 要使用聚合变量 `NOW、CLUSTER_TIME...` 必须添加前缀 `$$`。

- `CLUSTER_TIME` 仅仅使用于副本集和分片集群。
- `NOW 、 CLUSTER_TIME` 在整个管道（流）中的值相同。

```javascript
// $$NOW 是 GMT时间
// $$CLUSTER_TIME 是 当前时间戳
db.customers.updateOne(
  { _id: 1 },
  [
   { $set: { lastModified: "$$NOW", cancellation: {date: "$$CLUSTER_TIME", reason: "user request"}, status: "D" } }
  ]
);
```

更新结果：

```javascript
> db.customers.find().pretty();
{
	"_id" : 1,
	"status" : "D",
	"lastModified" : ISODate("2021-07-30T00:49:59.348Z"),
	"cancellation" : {
		"date" : Timestamp(1627574806, 1),
		"reason" : "user request"
	}
}
```


### 4.2. 属性: $inc

`$inc`：通过指定的amount来增加字段的值。

```javascript
// 语法
{ $inc: { <field1>: <amount1>, <field2>: <amount2>, ... } }
```

- `$inc`接受正值和负值。
- 如果字段不存在，则`$inc`创建该字段并将该字段设置为指定值。
- `$inc`在具有空值的字段上使用运算符将产生错误。
- `$inc`是单个文档中的原子操作。

```javascript
// 创建 products 集合
db.products.insert({
    _id: 1,
    sku: "abc123",
    quantity: 10,
    metrics: {
        orders: 2,
        ratings: 3.5
    }
});
```

下面 update 操作使用 `$inc` 操作符去减少 `quantity ` 属性，增加 `"metrics.orders"`属性。

```javascript
// $inc 将 quantity 字段的值减少2，并且使"metrics.orders"字段增加1
db.products.updateOne(
	{ sku: "abc123" }, 
	{ $inc: { quantity: -2, "metrics.orders": 1 } }
);
```

更新后文档：

```javascript
> db.products.find().pretty();
{
	"_id" : 1,
	"sku" : "abc123",
	"quantity" : 8,
	"metrics" : {
		"orders" : 3,
		"ratings" : 3.5
	}
}
```



### 4.3. 属性: $min

`$min`：如果指定值是**小于**该字段的当前值，则执行更新操作。

- 如果该字段不存在，则`$min`操作员将该字段设置为指定值。
- 对于不同类型的值之间的比较，例如数字和空值，`$min`使用[BSON 比较顺序](https://docs.mongodb.com/manual/reference/bson-type-comparison-order/#std-label-faq-dev-compare-order-for-BSON-types)。

```javascript
// 语法
{ $min: { <field1>: <value1>, ... } }
```

#### 4.3.1. $min比较数字

```javascript
// scores 集合有如下文档
{ _id: 1, highScore: 800, lowScore: 200 }
```

`scores` 集合中 `lowScore` 的值是200， `$min` 操作指定的 `lowScore` 值为150小于200。因此会发生更新操作。

```javascript
db.scores.updateOne({ _id: 1 }, { $min: { lowScore: 150 } });
```

操作结果如下：

```javascript
> db.scores.find().pretty();
{ "_id" : 1, "highScore" : 600, "lowScore" : 150 }
```

#### 4.3.2. $min比较日期

```javascript
// tags集合有如下文档
db.tags.insertOne({
    _id: 1,
    desc: "crafts",
    dateEntered: ISODate("2013-10-01T05:00:00Z"),
    dateExpired: ISODate("2013-10-01T16:38:16Z")
});
```

`$min`操作符中指定的 `dateEntered` 值为 `new Date("2013-09-25")`，小于 `"2013-10-01T05:00:00Z"`，则会发生更新操作。

```javascript
db.tags.updateOne(
	{ _id: 1 }, 
	{ $min: { dateExpired: new Date("2013-9-25") } }
);
```

文档更新结果：

```javascript
> db.tags.find().pretty();
{
	"_id" : 1,
	"desc" : "crafts",
	"dateEntered" : ISODate("2013-10-01T05:00:00Z"),
	"dateExpired" : ISODate("2013-09-25T00:00:00Z")
}
```



### 4.4. 属性: $max

`$max`：如果指定值是**大于**该字段的当前值，则执行更新操作。

参考 $min。

### 4.5. 属性: $mul

`$mul`：将字段的值乘以一个数字。

```javascript
// 语法
{ $mul: { <field1>: <number1>, ... } }
```

注意：

- 更新的目标字段的值必须是数字类型（要更新的字段必须包含一个数值。）。
- 如果该字段在文档中不存在，则`$mul`创建该字段并将值设置为零（与乘数相同的数据类型）。
- `$mul`是单个文档中的原子操作。

**混合类型相乘**：

| 32-bit Integer | 64-bit Integer           | Float          |       |
| :------------- | :----------------------- | :------------- | ----- |
| 32-bit Integer | 32-bit or 64-bit Integer | 64-bit Integer | Float |
| 64-bit Integer | 64-bit Integer           | 64-bit Integer | Float |
| Float          | Float                    | Float          | Float |

> 注意：
>
> - 如果两个 32 位整数的乘积超过 32 位整数的最大值，则结果为 64 位整数。
> - 超过 64 位整数最大值的任何类型的整数运算都会产生错误。



#### 4.5.1. 指定数乘以属性的值

```javascript
// products 集合中有如下文档
db.products.insertOne(
    { "_id": 1, "item": "ABC", "price": NumberDecimal("10.99"), "qty": 25 }
);
```

`$mul`将 `price` 属性原有的值乘以 `1.25`。将 `qty` 原有的值乘以 `2`。

```javascript
db.products.updateOne(
	{ _id: 1 }, 
	{ $mul: { price: NumberDecimal("1.25"), qty: NumberInt(2) } }
);
```

操作结果如下：

```javascript
> db.products.find().pretty();
{
	"_id" : 1,
	"item" : "ABC",
	"price" : NumberDecimal("13.7375"),
	"qty" : 50
}
```



#### 4.5.2. 操作不存在的属性

```javascript
// products 集合中有如下文档
db.products.insertOne({ _id: 2, item: "Unknown" });
```

`$mul` 操作的是不存在的属性：

```javascript
db.products.updateOne({ _id: 2 }, { $mul: { price: NumberDecimal("1.25") } });
```

该操作结果：

```javascript
// price属性不存在，就会得到0。
{ "_id" : 2, "item" : "Unknown", "price" : NumberDecimal("0.00") }
```



#### 4.5.3. 混合相乘

```javascript
// products 集合中有如下文档
db.products.insertOne({ _id: 3, item: "XYZ", price: NumberLong(10) });
```

`price`属性的值是 `NumberLong(10)`，乘的数是 `NumberInt(2)`。

```javascript
db.products.updateOne({ _id: 3 }, { $mul: { price: NumberInt(2) } });
```

该操作结果为：

```javascript
{ "_id" : 3, "item" : "XYZ", "price" : NumberLong(20) }
```



### 4.6. 属性: $rename

`rename`：更新字段的名字。

```javascript
// 语法
{$rename: { <field1>: <newName1>, <field2>: <newName2>, ... } }
```

注意：

- 新字段名称必须与现有字段名称不同。
- 操作可能不会保留文档中字段的顺序；即重命名的字段可以在文档内移动。
- 如果要重命名的字段在文档中不存在，`$rename`则不执行任何操作（即不执行任何操作）。
- 对于嵌入文档的字段，`$rename` 可以重命名这些字段并将其移出和移入嵌入文档。
- 如果要重命名的字段是数组的元素，则 `$renmae` 不起作用。

```javascript
// students 集合包含如下文档
db.students.insertMany([
	{
        "_id": 1,
        "alias": ["The American Cincinnatus", "The American Fabius"],
        "mobile": "555-555-5555",
        "nmae": { "first": "george", "last": "washington" }
    },
    {
        "_id": 2,
        "alias": ["My dearest friend"],
        "mobile": "222-222-2222",
        "nmae": { "first": "abigail", "last": "adams" }
    },
    {
        "_id": 3,
        "alias": ["Amazing grace"],
        "mobile": "111-111-1111",
        "nmae": { "first": "grace", "last": "hopper" }
    }
]);
```



#### 4.6.1. 重命名属性

`$rename`会将文档中所有的 `nmae` 修改为 `name`。

```javascript
db.students.updateMany({}, { $rename: { nmae: "name" } });
```



#### 4.6.2. 重命名嵌入文档的属性

`$rename`会将文档中所有的 `name.first` 修改为 `name.fname`。

```javascript
db.students.updateMany({}, { $rename: { "name.first": "name.fname" } });
```



#### 4.6.3. 重命名不存在的属性

`$rename` 重命名不存在的属性不会发生任何更改。

```javascript
> db.students.updateMany({ _id: 1 }, { $rename: { wife: "Marry" } });
{ "acknowledged" : true, "matchedCount" : 1, "modifiedCount" : 0 }
```



### 4.7. 属性: $set

`$set`: 用指定的值来替换当前值。

```javascript
// 语法
{ $set: { <field1>: <value1>, ... } }
```

注意：

- 如果字段不存在，`$set`将添加具有指定值的新字段，前提是新字段不违反类型约束。
- 如果指定多个字段，`$set`将更新或创建每个字段。

```javascript
// products 集合有如下文档
db.products.insertOne({
  _id: 100,
  sku: "abc123",
  quantity: 250,
  instock: true,
  reorder: false,
  details: { model: "14Q2", make: "xyz" },
  tags: [ "apparel", "clothing" ],
  ratings: [ { by: "ijk", rating: 4 } ]
});
```

#### 4.7.1. 设置Top-Level属性

`$set` 更新了 `quantity、details、tags` 这三个顶层的属性。

```javascript
db.products.updateOne({ _id: 100 }, {
    $set: {
        quantity: 500,
        details: { model: "14Q3", make: "xyz" },
        tags: ["coats", "outerwear", "clothing"]
    }
});
```

该操作结果如下：

```javascript
> db.products.find().pretty();
{
	"_id" : 100,
	"sku" : "abc123",
	"quantity" : 500,
	"instock" : true,
	"reorder" : false,
	"details" : { "model" : "14Q3", "make" : "xyz" },
	"tags" : [ "coats", "outerwear", "clothing" ],
	"ratings" : [ { "by" : "ijk", "rating" : 4 } ]
}
```



#### 4.7.2. 嵌入式文档设置属性

在嵌入式的文档或者数组中指定一个 `field`，使用点表达式。

```javascript
db.products.updateOne({ _id: 100 }, { $set: { "details.make": "zzz" } });
```



#### 4.7.3.数组中设置元素

在嵌入式的文档或者数组中指定一个 `field`，使用点表达式。

`$set` 设置 `tags` 数组中的第二个元素。设置`ratings`数组中第一个元素（是文档）的属性。

```javascript
db.products.updateOne(
    { _id: 100 }, 
    { $set: { "tags.1": "kkkk", "ratings.0.rating": NumberInt(8) } }
);
```



### 4.8. 属性: $unset

`$unset`：删除文档中指定的字段。

```javascript
// 语法
{ $unset: { <field1>: "", ... } }
```

- 如果该字段不存在，则 `$unset` 什么都不做。
- 当用于 `$` 匹配数组元素时，`$unset` 将匹配元素替换为`null`而不是从数组中删除匹配元素。

```javascript
// products 集合中有如下文档
db.products.insertOne({
    _id: 100,
    sku: "abc123",
    quantity: 250,
    instock: true,
    reorder: false,
    details: { model: "14Q2", make: "xyz" },
    tags: ["apparel", "clothing"],
    ratings: [{ by: "ijk", rating: 4 }]
});
```



#### 4.8.1. 移除指定属性

`$unset` 移除了 `quantity、instock`属性。

```javascript
> db.products.updateOne({ _id: 100 }, { $unset: { quantity: "", instock: "" } });
{ "acknowledged" : true, "matchedCount" : 1, "modifiedCount" : 1 }
```

`$unset`移除了数组中指定的属性。

```javascript
> db.products.updateOne(
    { _id: 100 }, 
    { $unset: { "tags.0": "", "ratings.0.by": "" } }
);
{ "acknowledged" : true, "matchedCount" : 1, "modifiedCount" : 1 }
```

#### 4.8.2. $unset和$连用(反例)

查询`_id:100` 并且 `tags` 数组中包含 `apparel` 的文档，`$` 会拿到数组的下标。

```javascript
db.products.updateOne(
	{ _id: 100, tags: "apparel" }, 	
	{ $unset: {"tags.$": ""} }
);
```

该操作会结果如下：

```javascript
"tags" : [
    null,			// 这里变成了 null
    "clothing"
],
```







### 4.9. 属性: $setOnInsert（配合upsert）

`$setOnInsert`： 如果更新导致插入文档，则设置字段的值。对修改现有文档的更新操作没有影响。

注意：

- 如果带有 `unsert: true`的更新操作，导致插入文档，则可以使用该操作符指定的值分配给文档中的字段。

- 如果更新操作未导致插入，`$setOnInsert`不做任何操作。
- `db.collection.update()、db.collection.findAndModify`方法可以指定`upsert`操作。

```javascript
// 语法
db.collection.update(
   <query>,
   { $setOnInsert: { <field1>: <value1>, ... } },
   { upsert: true }
)
```



```js

// 测试用例
db.products.insertOne({ 
    _id: 2, item: "ABC", 
    price: NumberDecimal("10.99"),
     qty: 25 
});

// 名为的集合products包含文档。
// _id:2的文档已经存在了，所以 $setOnInsert 不会插入 defaultQty 属性。
db.products.update(
    { _id: 2 }, 
    {
        $set: { abc: "C" },
        $setOnInsert: { defaultQty: NumberInt(100) }
    }, 
	{ upsert: true }
);

// _id:3的文档不存在了，所以 $setOnInsert 会插入 defaultQty 属性。
db.products.update(
    { _id: 3 }, 
    {
        $set: { abc: "C" },
        $setOnInsert: { defaultQty: NumberInt(100) }
    }, 
	{ upsert: true }
);
```

### 4.10. 数组: $（占位符）

`$`： 充当占位符以更新与查询数组中元素条件匹配的**第一个元素**。

使用 `$` 来更新数组不需要指定数组具体的下标。

```javascript
// 语法
{ "<array>.$" : value }

db.collection.update(
   { <array>: value ... },
   { <update operator>: { "<array>.$" : value } }
)
```

注意：

- `db.collection.update()、db.collection.findAndModify()`  可以和 `$` 一起使用。
- 不要在 `unsert` 操作中使用 `$`，因为插入操作将使用 `$`  作为插入文档中的字段名称。
- `$` 不能用于遍历多个数组的查询。例如遍历嵌套在其他数组中的数组的查询，因为 `$` 占位符替换的是单个值。
- `$unset` 和 `$` 不可以连用，`$` 并不会移除数组中的元素，而是将其设置为 `null`。
- 如果查询使用否定运算符（例如`$ne、$not、$nin`），则不能使用 `$` 更新数组中的值。但是如果查询的否定部分在 `elemMath` 表达式以内，则可以使用 `$`。

#### 4.10.1. 更新数组中的值

```javascript
// students 集合有如下文档
db.students.insertMany([
    { "_id": 1, "grades": [85, 80, 80] },
    { "_id": 2, "grades": [88, 90, 92] },
    { "_id": 3, "grades": [85, 100, 90] }
]);
```

在 `grades` 数组中更新第一个值为80的元素，将其更新为82。如果不知道数组中元素的具体下标，可以使用 `$` 操作符。

> 注意：必须包含数组字段作为为 `<query document>` 的一部分。

```javascript
db.students.updateOne(
    { _id: 1, grades: 80 }, 	 // grades数组字段作为查询文档的一部分
    { $set: { "grades.$": 82 } }
);
```

该操作结果如下：

```javascript
> db.students.find().pretty();
{ "_id" : 1, "grades" : [ 85, 82, 80 ] }
{ "_id" : 2, "grades" : [ 88, 90, 92 ] }
{ "_id" : 3, "grades" : [ 85, 100, 90 ] }
```



#### 4.10.2. 多值匹配更新嵌入文档

使用 `$elemMath` 来指定多个查询条件匹配的第一个数组元素，可以使用`$` 更新。

> 注意：必须包含数组字段作为为 `<query document>` 的一部分。

```javascript
// students 集合有如下文档
db.students.insertOne({
    _id: 5,
    grades: [
        { grade: 80, mean: 75, std: 8 },
        { grade: 85, mean: 90, std: 5 },
        { grade: 90, mean: 85, std: 3 }
    ]
});
```

`$elemMatch`：只要数组中有一个元素符合条件就会查到该条文档。

`grades` 数组中有两个元素都匹配，但是只更新第一个匹配的。

```javascript
db.students.updateOne(
	{ 
        _id: 5, 
        grades: { $elemMatch: { grade: { $lte: 90 }, mean: { $gt: 80 } } } 	   }, 
	{ $set: { "grades.$.std": 8848 } }
);
```

该操作的结果如下：

```javascript
> db.students.find().pretty();
{
	"_id" : 5,
	"grades" : [
		{
			"grade" : 80,
			"mean" : 75,
			"std" : 8
		},
		{
			"grade" : 85,
			"mean" : 90,
			"std" : 8848
		},
		{
			"grade" : 90,
			"mean" : 85,
			"std" : 3
		}
	]
}

```



### 4.11. 数组: $[]（占位符）

`$[]`:  更新修改指定数组属性的所有元素。

#### 4.11.1. 更新数组中的所有元素

```javascript
// students 集合中有如下文档
db.students.insertMany([
    { "_id": 1, "grades": [85, 82, 80] },
    { "_id": 2, "grades": [88, 90, 92] },
    { "_id": 3, "grades": [85, 100, 90] }
]);
```

更新集合所有文档中数组中的全部元素：

```javascript
db.students.updateMany({}, { $inc: { "grades.$[]": 10 } });
```

操作结果如下：

```javascript
> db.students.find().pretty();
{ "_id" : 1, "grades" : [ 95, 92, 90 ] }
{ "_id" : 2, "grades" : [ 98, 100, 102 ] }
{ "_id" : 3, "grades" : [ 95, 110, 100 ] }
```

#### 4.11.2. 更新数组中的所有文档

```javascript
// 集合 students2 中有如下文档
db.students2.insertMany([
	{
	    "_id": 1,
	    "grades": [
	        { "grade": 80, "mean": 75, "std": 8 },
	        { "grade": 85, "mean": 90, "std": 6 },
	        { "grade": 85, "mean": 85, "std": 8 }
	    ]
	}, 
	{
	    "_id": 2,
	    "grades": [
	        { "grade": 90, "mean": 75, "std": 8 },
	        { "grade": 87, "mean": 90, "std": 5 },
	        { "grade": 85, "mean": 85, "std": 6 }
	    ]
	}
]);
```

更新 `grades` 数组中 `std` 字段。

```javascript
> db.students2.updateMany({}, { $inc: { "grades.$[].std": 10 } });
{ "acknowledged" : true, "matchedCount" : 2, "modifiedCount" : 2 }
```

#### 4.11.3.  否定运算符更新数组

```javascript
// result 集合中有如下文档
db.result.insertMany([
	{ "_id": 1, "grades": [85, 82, 80] }, 
	{ "_id": 2, "grades": [88, 90, 92] }, 
	{ "_id": 3, "grades": [85, 100, 90] }
]);
```

除了 `grades` 中包含100的文档，`grades` 数组的所有元素都加10；

```javascript
db.result.update(
	{ "grades": { $ne: 100 } }, 
	{ $inc: { "grades.$[]": 10 } },
	{ multi: true }
);
```

该操作的结果：

```javascript
> db.result.find().pretty();
{ "_id" : 1, "grades" : [ 95, 92, 90 ] }
{ "_id" : 2, "grades" : [ 98, 100, 102 ] }
{ "_id" : 3, "grades" : [ 85, 100, 90 ] }
```

#### 4.11.4. 结合$[identifier]更新嵌套数组

```javascript
// students3 集合中有如下文档
db.students3.insertOne({
    "_id": 1,
    "grades": [
        { type: "quiz", questions: [10, 8, 5] },
        { type: "quiz", questions: [8, 9, 6] },
        { type: "hw", questions: [5, 4, 3] },
        { type: "exam", questions: [25, 10, 23, 0] },
    ]
});
```

将`questions`数组中所有大于8的元素加2。

```javascript
db.students3.updateMany(
	{},  // 这一步其实就是来查询文档的
	{ $inc: { "grades.$[].questions.$[score]": 2 } },
	{ arrayFilters: [{ "score": { $gt: 8 } }] } // 过滤数组元素的
);
```

该操作结果如下所示：

```javascript
{
    "_id" : 1, 
    "grades" : [ 
        { "type" : "quiz", "questions" : [ 12, 8, 5 ] }, 
        { "type" : "quiz", "questions" : [ 8, 11, 6 ] }, 
        { "type" : "hw", "questions" : [ 5, 4, 3 ] }, 
        { "type" : "exam", "questions" : [ 27, 12, 25, 0 ] } 
    ] 
}
```



### 4.12. 数组: $[identifier]（占位符）

`$[identifier]`：充当占位符更新所有的元素。其中要更新的文档要匹配查询条件。待更新的数组元素要匹配 `arrayFilters` 的条件。

```javascript
// 语法
db.collection.updateMany(
   { <query conditions> },
   { <update operator>: { "<array>.$[<identifier>]" : value } },
   { arrayFilters: [ { <identifier>: <condition> } ] }
)
```

> 注意：在`<identifier>` 必须以小写字母开头，并且只包含字母数字字符。



#### 4.12.1. upsert和$[identifier]连用

如果`upsert`操作导致了插入，`query`查询文档必须包含数组字段的精确匹配（equality match），以便于 `<identifier>` 在更新语句中使用。

例如，`$[]` 更新文档中使用的以下 upsert 操作在数组字段上指定了完全相等匹配条件：

```javascript
db.collection.update(
   { myArray: [ 0, 1 ] },			// 等值查询
   { $set: { "myArray.$[element]": 2 } },
   { arrayFilters: [ { element: 0 } ],
     upsert: true }
)
```

如果不存在这样的文档，则该操作将导致插入类似于以下内容的文档：

```javascript
{ "_id" : ObjectId(...), "myArray" : [ 2, 1 ] }
```

如果 upsert 操作不包括完全相等的匹配并且没有找到要更新的匹配文档，则 upsert 操作会出错。例如，如果没有找到要更新的匹配文档，则以下操作将出错：

```javascript
db.array.update(
   { },
   { $set: { "myArray.$[element]": 10 } },
   { arrayFilters: [ { element: 9 } ],
     upsert: true }
)
```

该操作将返回类似于以下内容的错误：

```javascript
WriteResult({
   "nMatched" : 0,
   "nUpserted" : 0,
   "nModified" : 0,
   "writeError" : {
      "code" : 2,
      "errmsg" : "The path 'myArray' must exist in the document in order to apply array updates."
   }
})
```

#### 4.12.2. 更新匹配arrayFilters的所有元素

```javascript
// students 集合中有如下文档
db.students.insertMany([
    { "_id": 1, "grades": [95, 92, 90] },
    { "_id": 2, "grades": [98, 100, 102] },
    { "_id": 3, "grades": [95, 110, 100] }
]);
```

更新`grades`数组中大于等于100的元素。

```javascript
db.students.update(
    { }, 
    { $inc: { "grades.$[score]": 10 } }, 
    {
        arrayFilters: [{ "score": { $gte: 100 } }],
        multi: true
    }
);
```

该操作的结果如下所示：

```javascript
> db.students.find().pretty();
{ "_id" : 1, "grades" : [ 95, 92, 90 ] }
{ "_id" : 2, "grades" : [ 98, 110, 112 ] }
{ "_id" : 3, "grades" : [ 95, 120, 110 ] }
```

#### 4.12.3. 更新在数组中匹配arrayFilters的所有文档

```javascript
// 语法
db.collection.update(
   { <query selector> },
   { <update operator>: { "array.$[<identifier>].field" : value } }, 
   { arrayFilters: [ { <identifier>: <condition> } } ] }
)
```

`$[<identifierr>] `访问嵌入式的文档用点表示法

```javascript
// students2包含以下集合
db.students2.insertMany([
    {
        "_id": 1,
        "grades": [
            { "grade": 80, "mean": 75, "std": 6 },
            { "grade": 85, "mean": 90, "std": 4 },
            { "grade": 85, "mean": 85, "std": 6 }
        ]
    },
    {
        "_id": 2,
        "grades": [
            { "grade": 90, "mean": 75, "std": 6 },
            { "grade": 87, "mean": 90, "std": 3 },
            { "grade": 85, "mean": 85, "std": 4 }
        ]
    }
]);
```

修改`grades`数组中的`mean`属性。数组过滤条件是 `grade` 属性大于等于85。

数组过滤之后，如果对象中的 `mean` 比 80小就执行更新操作。 

```javascript
db.students2.updateMany(
    { }, 
    { $max: { "grades.$[item].mean": 80 } },
    { arrayFilters: [ { "item.grade": { $gte: 85 } } ] }
);
```

该操作结果如下所示：

```javascript
> db.students2.find();
{ 
    "_id" : 1, 
    "grades" : [ 
        { "grade" : 80, "mean" : 75, "std" : 6 }, 
        { "grade" : 85, "mean" : 90, "std" : 4 }, 
        { "grade" : 85, "mean" : 85, "std" : 6 } 
    ] 
}
{ 
    "_id" : 2, 
    "grades" : [ 
        { "grade" : 90, "mean" : 80, "std" : 6 }, // 只更新了这个字段
        { "grade" : 87, "mean" : 90, "std" : 3 }, 
        { "grade" : 85, "mean" : 85, "std" : 4 } 
    ] 
}

```

#### 4.12.4. 多条件匹配更新数组元素

```javascript
// students2 集合中有如下文档
db.students2.insertMany([
    {
        "_id": 1,
        "grades": [
            { "grade": 80, "mean": 75, "std": 6 },
            { "grade": 85, "mean": 100, "std": 4 },
            { "grade": 85, "mean": 100, "std": 6 }
        ]
    },
    {
        "_id": 2,
        "grades": [
            { "grade": 90, "mean": 100, "std": 6 },
            { "grade": 87, "mean": 100, "std": 3 },
            { "grade": 85, "mean": 100, "std": 4 }
        ]
    }
]);
```

`grades`数组的 `std` 需要减一。数组过滤条件 `grade` 属性大于等于80，`std` 属性大于5。

```javascript
db.students2.update(
    { }, 
    { $inc: { "grades.$[elem].std": -1 } }, 
    {
        arrayFilters: [{ "elem.grade": { $gte: 80 }, "elem.std": { $gt: 5 } }],
        multi: true
    }
);
```

以上操作的结果：

```javascript
{  "_id" : 1,
   "grades" : [
      { "grade" : 80, "mean" : 75, "std" : 5 },
      { "grade" : 85, "mean" : 100, "std" : 4 },
      { "grade" : 85, "mean" : 100, "std" : 5 }
   ]
}
{
   "_id" : 2,
   "grades" : [
      { "grade" : 90, "mean" : 100, "std" : 5 },
      { "grade" : 87, "mean" : 100, "std" : 3 },
      { "grade" : 85, "mean" : 100, "std" : 4 }
   ]
}
```



#### 4.12.5. 否定操作符更新所有数组元素

```javascript
// alumni 集合有如下文档
db.alumni.insertMany([
    {
        "_id": 1,
        "name": "Christine Franklin",
        "degrees": [
            {
                "level": "Master",
                "major": "Biology",
                "completion_year": 2010,
                "faculty": "Science"
            },
            {
                "level": "Bachelor",
                "major": "Biology",
                "completion_year": 2008,
                "faculty": "Science"
            }
        ],
        "school_email": "cfranklin@example.edu",
        "email": "christine@example.com"
    },
    {
        "_id": 2,
        "name": "Reyansh Sengupta",
        "degrees": [{
            "level": "Bachelor",
            "major": "Chemical Engineering",
            "completion_year": 2002,
            "faculty": "Engineering"
        }],
        "school_email": "rsengupta2@example.edu"
    }
]);
```

更新所有的文档。`degrees` 中 `level` 属性值不是 `Bachelor` 的再添加一个新属性。

```javascript
db.alumni.updateMany(
    { }, 
    { $set: { "degrees.$[degree].gradcampaign": 1 } },
    { arrayFilters: [{ "degree.level": { $ne: "Bachelor" } }] }
);
```

该操作结果如下所示：

```javascript
{
   "_id" : 1,
   "name" : "Christine Franklin",
   "degrees" : [
      {
         "level" : "Master",
         "major" : "Biology",
         "completion_year" : 2010,
         "faculty" : "Science",
         "gradcampaign" : 1
      },
      {
         "level" : "Bachelor",
         "major" : "Biology",
         "completion_year" : 2008,
         "faculty" : "Science"
      }
   ],
   "school_email" : "cfranklin@example.edu",
   "email" : "christine@example.com"
}
{
   "_id" : 2,
   "name" : "Reyansh Sengupta",
   "degrees" : [
      {
         "level" : "Bachelor",
         "major" : "Chemical Engineering",
         "completion_year" : 2002,
         "faculty" : "Engineering"
      }
   ],
   "school_email" : "rsengupta2@example.edu"
}
```

#### 4.12.6. 配合$[]更新嵌套数组

```javascript
// students3 有如下文档
db.students3.insertOne(
    {
        "_id": 1,
        "grades": [
            { type: "quiz", questions: [10, 8, 5] },
            { type: "quiz", questions: [8, 9, 6] },
            { type: "hw", questions: [5, 4, 3] },
            { type: "exam", questions: [25, 10, 23, 0] },
        ]
    }
);
```

`grades`数组元素命名为 `grade`。

`questions` 数组元素命名为 `question`。

```javascript
db.students3.updateMany(
    { }, 
    { $inc: { "grades.$[grade].questions.$[question]": 2 } }, 
    { arrayFilters: [{ "grade.type": "quiz" }, { "question": { $gte: 8 } }] }
);
```

该操作结果如下所示：

```javascript
{
   "_id" : 1,
   "grades" : [
      { "type" : "quiz", "questions" : [ 12, 10, 5 ] },
      { "type" : "quiz", "questions" : [ 10, 11, 6 ] },
      { "type" : "hw", "questions" : [ 5, 4, 3 ] },
      { "type" : "exam", "questions" : [ 25, 10, 23, 0 ] }
   ]
}
```

---

要更新嵌套 `grades.questions` 数组中大于或等于8的所有值（无论 `type` 如何），请执行以下操作：

```javascript
db.students3.update(
   {},
   { $inc: { "grades.$[].questions.$[score]": 2 } },
   { arrayFilters: [  { "score": { $gte: 8 } } ], multi: true}
)
```



### 4.13. 数组: $addToSet

`$addToSet`：仅当集合中不存在该元素时，才将元素添加到数组中。如果该元素已经存在数组中，`$addToSet`不会发生任何影响。

```javascript
// 语法
{ $addToSet: { <field1>: <value1>, ... } }
```

- `$addToset`只确保没有重复的元素添加到集合中, 并不影响现有的重复的存在的元素。`$addToSet`不能保证元素的特定顺序。

- 如果使用`$addToSet`文档中不存在的字段进行更新，则`$addToSet`具有指定值的数组字段作为其元素。

- `$addToset`在不是数组的字段上使用，操作失败。

- 如果值是一个数组，`$addToSet`则将整个数组作为*单个*元素附加。


#### 4.13.1. 属性不是数组

如果`$addToSet` 操作的属性不是数组，该操作会失败。

```javascript
// 集合 foo 包含如下文档
{ _id: 1, colors: "blue,green,red" }
```

`$addToSet`操作 `colors`字段：操作非数组字段就会出错！

```javascript
db.foo.update(
   { _id: 1 },
   { $addToSet: { colors: "c" } }
);
```

#### 4.13.2. 添加的值是数组

如果`$addToSet`的值是数组，那么会将整个数组作为一个元素追加到原数组上。

```javascript
// 集合 test 包含如下文档
db.test.insertOne({ _id: 1, letters: ["a", "b"] });
```

下面操作，数组`["c", "d"]`追加到 `letters` 中。

```javascript
db.test.update(
   { _id: 1 },
   { $addToSet: { letters: [ "c", "d" ] } }
);
```

该操作结果如下所示：

```javascript
> db.test.find().pretty();
{ "_id" : 1, "letters" : [ "a", "b", [ "c", "d" ] ] }
```

> 注意：要单独添加数组中每个元素到原来的数组，使用 `$each` 修饰符配合 `$addToSet`使用。
>
> ```javascript
> db.test.updateOne(
>     { _id: 1 }, 
>     { $addToSet: { letters: { $each: ["x", "y"] } } }
> );
> ```
>
> 这样数组["x", "y"]就不会作为整体追加了。



#### 4.13.3. 添加的值是文档

如果值是文档，则如果数组中存在的文档与要添加的文档完全匹配，则MongoDB确定该文档是重复的；即现有文档具有完全相同的字段和值，*并且*这些字段的顺序相同。

因此，字段顺序很重要，您不能指定 MongoDB 仅比较文档中字段的子集以确定文档是否与现有数组元素重复。

```javascript
// inventory 集合有如下文档
db.inventory.insertOne(
    {
        _id: 1,
        item: "polarizing_filter",
        tags: [{ k1: "v1", k2: "v2" }]
    }
);
```

（1）如果插入相同的文档（顺序相同），`$addToSet`不会对原数组有任何影响。

```javascript
db.inventory.updateOne(
    { _id: 1 }, 
    { $addToSet: { tags: { k1: "v1", k2: "v2" } } }
);
```

（2）如果插入相同的文档（顺序不同），`$addToSet`会执行插入操作。

```javascript
db.inventory.updateOne(
    { _id: 1 }, 
    { $addToSet: { tags: { k2: "v2", k1: "v1" } } }
);
```

该操作结果如下所示：

```javascript
> db.inventory.find().pretty();
{
	"_id" : 1,
	"item" : "polarizing_filter",
	"tags" : [
		{ "k1" : "v1", "k2" : "v2" },
		{ "k2" : "v2", "k1" : "v1" }
	]
}
```

#### 4.13.4. 结合$each连用

```javascript
// inventory 集合有如下文档
db.inventory.insertOne(
    { _id: 2, item: "cable", tags: ["electronics", "supplies"] }
);
```

使用 `$each` 修饰符将数组中多个元素添加到 `tags`数组中。

`tags`中已经存在了 `"electronics"`， 所以不会重复添加。

```javascript
db.inventory.updateOne(
    { _id: 2 }, 
    { $addToSet: { tags: { $each: ["electronics", "a", "b"] } } }
);
```

该操作结果如下所示：

```javascript
{
	"_id" : 2,
	"item" : "cable",
	"tags" : ["electronics", "supplies", "a", "b"]
}
```



### 4.14. $pop

`$pop`：移除数组中的第一个或者最后一个元素。如果值是`-1`会移除数组的第一个元素。值是`1`会移除数组的最后一个元素。

```javascript
// 语法
{ $pop: { <field>: <-1 | 1>, ... } }
```

注意：

- 操作的字段不是数组，`$pop`会失败。
- 如果`$pop`移除了`<field>`的最后一个元素，这个`<field>` 就会变成空数组。

#### 4.14.1. 移除数组第一个元素

```java
// students 集合中有如下文档
db.students.insertOne({ _id: 1, scores: [8, 9, 10] });
```

移除`scores`数组的第一个元素：

```javascript
db.students.updateOne({ _id: 1 }, { $pop: { scores: -1 } });
```

该操作结果如下所示：

```javascript
> db.students.find().pretty();
{ "_id" : 1, "scores" : [ 9, 10 ] }
```

#### 4.14.2. 数组中无元素？

当`$pop`后，数组的长度===0，也就是数组为空，会有如下显示。

```javascript
> db.students.find().pretty();
{ "_id" : 1, "scores" : [ ] }
```

### 4.15. 数组：$pull

`$poll`：从现有数组中删除一个或多个与指定条件匹配的值的所有实例。

```javascript
// 语法
{ $pull: { <field1>: <value|condition>, <field2>: <value|condition>, ... } }
```

注意：

- 如果指定的删除的 `<value>`是数组，`$pull`则只删除数组中与指定`<value>`完全匹配的元素 ，包括顺序。
- 如果指定的删除的 `<value>`是文档，`$pull`仅删除数组中具有完全相同字段和值的元素。字段的顺序可以不同。

#### 4.15.1. 等值条件下删除所有元素

```javascript
// stores 集合中有如下文档
db.stores.insertMany([{
        _id: 1,
        fruits: ["apples", "pears", "oranges", "grapes", "bananas"],
        vegetables: ["carrots", "celery", "squash", "carrots"]
    },
    {
        _id: 2,
        fruits: ["plums", "kiwis", "oranges", "bananas", "apples"],
        vegetables: ["broccoli", "zucchini", "carrots", "onions"]
    }
]);
```

删除 `fruits` 集合中 `"apples"、"oranges"`。

删除 `vegetables` 集合中 `carrots`。

```javascript
db.stores.update(
    { }, 
    { 
        $pull: { 
            fruits: { $in: ["apples", "oranges"] },
            vegetables: "carrots" 
        } 
    }, 
    { multi: true }
);
```

该操作结果如下：

```java
> db.stores.find();
{ 
    "_id" : 1, 
    "fruits" : [ "pears", "grapes", "bananas" ], 
    "vegetables" : [ "celery", "squash" ] 
}
{ 
    "_id" : 2, 
    "fruits" : [ "plums", "kiwis", "bananas" ], 
    "vegetables" : [ "broccoli", "zucchini", "onions" ] 
}
```

---

如果更新操作是如下：

```javascript
db.stores.update(
    { }, 
    { 
        $pull: { 
            fruits: ["apples", "oranges"],  // 这里不是$in
            vegetables: "carrots" 
        } 
    }, 
    { multi: true }
);
```

这就相当于删除 `fruits: [["apples", "oranges"], "apples", "pears", "oranges", "grapes", "bananas"]`中的嵌套数组。 

#### 4.15.2. 移除所有符合指定$pull条件的元素

```javascript
// profiles 集合中有以下文档
db.profiles.insertOne({ _id: 1, votes: [3, 5, 6, 7, 7, 8] });
```

下面操作将会移除`votes`数组中所有大于等于6的元素：

```javascript
db.profiles.updateMany({ _id: 1 }, { $pull: { votes: { $gte: 6 } } });
```

该操作的结果如下所示：

```javascript
> db.profiles.find().pretty();
{ "_id" : 1, "votes" : [ 3, 5 ] }
```

#### 4.15.3. 从文档数组中移除元素

```javascript
// survey 集合中存在以下文档
db.survey.insertMany([
    {
        _id: 1,
        results: [
            { item: "A", score: 5 },
            { item: "B", score: 8, comment: "Strongly agree" }
        ]
    },
    {
        _id: 2,
        results: [
            { item: "C", score: 8, comment: "Strongly agree" },
            { item: "B", score: 4 }
        ]
    }
]);
```

下面的操作将会从`results`数组移除元素，要移除的元素包含 `score` 属性值大于6，并且 `item` 属性值等于`"B"`。

```javascript
db.survey.updateMany(
    { },
    { $pull: { results: { score: { $gt: 6 }, item: "B" } } }
);
```

该操作结果如下：

```javascript
> db.survey.find();
{ "_id" : 1, "results" : [ { "item" : "A", "score" : 5 } ] }
{ 
    "_id" : 2, 
    "results" : [ 
        { "item" : "C", "score" : 8, "comment" : "Strongly agree" }, 
        { "item" : "B", "score" : 4 } 
    ] 
}
```



#### 4.15.3. 要不要用$elemMatch

该[`$pull`](https://docs.mongodb.com/manual/reference/operator/update/pull/#mongodb-update-up.-pull)表达式将条件应用于`results`数组的每个元素，就好像它是一个顶级文档。

由于[`$pull`](https://docs.mongodb.com/manual/reference/operator/update/pull/#mongodb-update-up.-pull)运算符将其查询应用于每个元素，就好像它是顶级对象一样，表达式不需要使用[`$elemMatch`](https://docs.mongodb.com/manual/reference/operator/query/elemMatch/#mongodb-query-op.-elemMatch)来指定`score` 字段等于`8`和`item`字段等于的条件`"B"`。实际上，下面的操作不会从原始集合中拉取任何元素。

```javascript
// 反例
db.survey.update(
  { },
  { $pull: { results: { $elemMatch: { score: 8 , item: "B" } } } },
  { multi: true }
);
```

但是，如果`survey`集合包含以下文档，其中`results`数组包含也包含数组的嵌入文档：

```javascript
db.survey.insertMany([
    {
        _id: 1,
        results: [
            { item: "A", score: 5, answers: [{ q: 1, a: 4 }, { q: 2, a: 6 }] },
            { item: "B", score: 8, answers: [{ q: 1, a: 8 }, { q: 2, a: 9 }] }
        ]
    },
    {
        _id: 2,
        results: [
            { item: "C", score: 8, answers: [{ q: 1, a: 8 }, { q: 2, a: 7 }] },
            { item: "B", score: 4, answers: [{ q: 1, a: 0 }, { q: 2, a: 8 }] }
        ]
    }
]);
```

然后，您可以使用以下命令在`answers`数组元素上指定多个条件 [`$elemMatch`](https://docs.mongodb.com/manual/reference/operator/query/elemMatch/#mongodb-query-op.-elemMatch)：

该操作从`results`数组中删除了那些嵌入文档的`answers`字段，

```javascript
db.survey.updateMany(
    { }, 
    {
        $pull: {
            results: {
                score: { $gt: 3 },
                item: "B",
                answers: { $elemMatch: { a: 0, q: { $gte: 1 } } }
            }
        }
    }
);
```

该操作的结果如下：

```javascript
{
    _id: 1,
    results: [
        { item: "A", score: 5, answers: [{ q: 1, a: 4 }, { q: 2, a: 6 }] },
        { item: "B", score: 8, answers: [{ q: 1, a: 8 }, { q: 2, a: 9 }] }
    ]
},
{
    _id: 2,
    results: [
        { item: "C", score: 8, answers: [{ q: 1, a: 8 }, { q: 2, a: 7 }] },
    ]
}
```



#### 4.15.4. 移除嵌套数组中的文档

```javascript
// tab 集合中有如下文档
db.tab.insertOne({
    budgetSid: "T2551",
    rowData: [
        {
            wdItem: 2,
            children: [ { wdItem: 21 }, { wdItem: 22 } ]
        },
        {
            wdItem: 3,
            children: [ { wdItem: 31 }, { wdItem: 32 } ]
        }
    ]
}); 
```

删除嵌套数组中的文档：

```javascript
db.tab.updateOne(
    { budgetSid: "T2551" }, 
    { $pull: { "rowData.$[row].children": { wdItem: 21 } } }, 
    { arrayFilters: [{ "row.wdItem": 2 }] }
);
```

> 注意：`$pull: {key: value}`，这里的 key 应该表示的是数组！

该操作后的结果：

```javascript
> db.tab.find();
{ 
    "_id" : ObjectId("6107f41e662f5a3fbe33d009"), 
     "budgetSid" : "T2551", 
     "rowData" : [ 
         { 
             "wdItem" : 2, 
             "children" : [ { "wdItem" : 22 } ] 
         }, 
         { 
             "wdItem" : 3, 
             "children" : [ { "wdItem" : 31 }, { "wdItem" : 32 } ] 
         } 
     ] 
}
```



### 4.16. 数组：$pullAll

`$pullAll`：从现有数组中删除指定值的所有元素。

和`$pull`按照条件删除不同，`$pullAll`会删除匹配列表的所有元素

```javascript
// 语法
// 注意：<field1> 后面跟的是数组
{ $pullAll: { <field1>: [ <value1>, <value2> ... ], ... } }
```

> 注意：如果`<value>`要删除的是数组，则 [`$pullAll`](https://docs.mongodb.com/manual/reference/operator/update/pullAll/#mongodb-update-up.-pullAll)仅删除数组中与指定`<value>`完全匹配的元素，包括顺序。

#### 4.16.1. 删除数组中的嵌套数组

```javascript
// survery 集合中有如下文档
db.survey.insertOne( { _id: 1, tags: [ [0, 1], [3, 4] ] } );
```

下面操作来删除 `tags` 数组中的嵌套数组:

```javascript
// Java里要这么写 Arrays.asList(Arrays.asList(0, 1))
db.survey.updateOne({ _id: 1 }, { $pullAll: { tags: [ [0, 1] ] } });
```

该操作结果如下：

```javascript
> db.survey.find().pretty();
{ "_id" : 1, "tags" : [ [ 3, 4 ] ] }
```

---

```javascript
// 这俩都可以删除
db.survey.updateOne({ _id: 1 }, { $pullAll: { tags: [ [0, 1], [3, 4] ] } });
db.survey.updateOne({ _id: 1 }, { $pullAll: { tags: [ [3, 4], [0, 1] ] } });

// 无法删除（顺序错了）
db.survey.updateOne({ _id: 1 }, { $pullAll: { tags: [ [1, 0] ] } });
```



#### 4.16.2. 删除数组中嵌套文档

```javascript
// survey 集合中有如下文档
db.survey.insertOne({ _id: 2, tags: [{ k1: "v1" }, { k2: "v2" }] });
```

删除`tags`数组中的文档 `{k2: "v2"}`：

```javascript
// Java 中要这么写 Arrays.asList(map) map中存的是 { k2: "v2" }
db.survey.updateOne({ _id: 2 }, { $pullAll: { tags: [{ k2: "v2" }] } });
```

操作结果如下：

```javascript
{ "_id" : 2, "tags" : [ { "k1" : "v1" } ] }
```

#### 4.16.3.  删除数组中多个元素

```javascript
// survey 集合有以下文档
db.survey.insertOne({ _id: 3, scores: [0, 2, 5, 5, 1, 0] });
```

以下操作删除 `scores` 数组中的 `0、5`：

```javascript
db.survey.updateOne({ _id: 3 }, { $pullAll: { scores: [0, 5] } });
```

操作结果如下所示：

```javascript
{ "_id" : 3, "scores" : [ 2, 1 ] }
```

### 4.17. 数组: $push

`$push`：添加元素到数组。

```javascript
// 语法
{ $push: { <field1>: <value1>, ... } }
```

注意：

- 如果更新的文档中 `field` 不存在，`$push` 添加值作为其元素的数组字段。
- 如果 `<field>` 不是数组，操作失败。
- 如果 `<value>` 是数组，会将整个数组作为一个整体加入到原数组中。使用 `$each` 将数组中每个元素追加到原数组中。

```javascript
// $each 修饰符  
// 带有修饰符的语法
{ $push: { <field1>: { <modifier1>: <value1>, ... }, ... } }
```

#### 4.17.1. $push的field不存在？

```javascript
// students 集合中存在如下文档
db.students.insertOne({ _id: 1, scores: [50, 60, 70] });
```

`$push` 下的 `<field>` 不存在？==> 直接创建这个字段，并添加值。

```javascript
db.students.updateOne({ _id: 1 }, { $push: { grades: [90, 91] } });
```

该操作的结果如下所示：

```javascript
> db.students.find();
{ "_id" : 1, "scores" : [ 50, 60, 70 ], "grades" : [ [ 90, 91 ] ] }
```

#### 4.17.2. 数组中添加一个元素

```javascript
// students 集合中存在如下文档
db.students.insertOne({ _id: 2, scores: [50, 60, 70] });
```

向`scores`数组中添加一个元素：

```javascript
db.students.updateOne({ _id: 2 }, { $push: { scores: 30 } });
```

该操作的结果如下：

```javascript
{ "_id" : 2, "scores" : [ 50, 60, 70, 30 ] }
```

---

但是如果下面这个操作呢？

```javascript
db.students.updateOne({ _id: 2 }, { $push: { scores: [30, 40] } });
```

操作结果如下所示：

```javascript
> db.students.find();
{ "_id" : 2, "scores" : [ 50, 60, 70, [ 30, 40 ] ] }
```

#### 4.17.3. 数组中添加多个元素

```javascript
// students 集合中存在如下文档
db.students.insertOne({ _id: 3, scores: [50, 60, 70] });
```

下面操作，向`scores`数组中添加多个元素：

```javascript
db.students.updateOne({ _id: 3 }, { $push: { scores: { $each: [30, 40] } } })
```

操作结果如下所示：

```javascript
{ "_id" : 3, "scores" : [ 50, 60, 70, 30, 40 ] }
```



#### 4.17.4. $push配合多个修饰符

```javascript
// students 集合中有如下文档
db.students.insertOne(
    {
        "_id": 5,
        "quizzes": [
            { "wk": 1, "score": 10 },
            { "wk": 2, "score": 8 },
            { "wk": 3, "score": 5 },
            { "wk": 4, "score": 6 }
        ]
    }
);
```

`$push`配合多个修饰符使用：

- `$each`将多个文档添加到 `quizzes` 数组。
- `$sort` 使得 `quizzes` 数组元素按照倒序排序。
- `$slice`保存 `quizzes` 数组排序后的前三个元素。

```javascript
db.students.updateOne(
    { _id: 5 },
    {
        $push: { 
            quizzes: { 
                $each: [{ wk: 5, score: 8 }, { wk: 6, score: 7 }, { wk: 7, score: 6 } ],
                $sort: { score: -1 },
                $slice: 3
            }
        } 
    }
);
```

该操作结果如下：

```javascript
> db.students.find();
{
    "_id" : 5, 
    "quizzes" : [ 
        { "wk" : 1, "score" : 10 }, 
        { "wk" : 2, "score" : 8 }, 
        { "wk" : 5, "score" : 8 } 
    ]
}
```



### 4.18. 数组修饰符：$each

`$each`：用来修饰 `$push、$addToSet`向数组中添加多个元素。

 ```javascript
// 语法
// 注意：<field> 在这里指的都是数组

// 原数组中没有这个值才添加
{ $addToSet: { <field>: { $each: [ <value1>, <value2> ... ] } } }

// 追到多个元素到数组中
{ $push: { <field>: { $each: [ <value1>, <value2> ... ] } } }
 ```

### 4.19. 数组修饰符: $position

`$position`：用来修饰 `$push`。

指定了`$pusn`在数组中插入元素的位置。没有写`$position`，默认将元素添加到数组末尾。

要使用 `$position` 修饰符，必须和 `$each` 一起用！

```javascript
// 语法
{
  $push: {
    <field>: {
       $each: [ <value1>, <value2>, ... ],
       $position: <num>
    }
  }
}
```

*在3.6版更改*：[`$position`](https://docs.mongodb.com/manual/reference/operator/update/position/#mongodb-update-up.-position)可以接受负数索引值来指示从末尾开始的位置，从（但不包括）数组的最后一个元素开始计数。

#### 4.19.1. 从数组开头添加元素

```javascript
// students 集合中有如下文档
db.students.insertOne({ _id: 1, scores: [10, 20, 30] });
```

在`scores`数组开头添加元素：

```javascript
db.students.updateOne(
    { _id: 1 }, 
    { $push: { scores: { $each: [3, 4, 5], $position: 0} } }
);
```

该操作结果如下所示：

```javascript
{ "_id" : 1, "scores" : [ 3, 4, 5, 10, 20, 30 ] }
```



#### 4.19.2. 在数组中添加元素

```javascript
// students 集合中有如下文档
db.students.insertOne({ _id: 2, scores: [10, 20, 30] });
```

如果 `$position:1`呢？

```javascript
db.students.updateOne(
    { _id: 2 }, 
    { $push: { scores: { $each: [3, 4, 5], $position: 1} } }
);
```

操作结果如下所示：

```javascript
> db.students.find();
{ "_id" : 1, "scores" : [ 10, 3, 4, 5, 20, 30 ] }
```



#### 4.19.3. 负数索引向数组添加元素

```javascript
// students 集合中有如下文档
db.students.insertOne({ _id: 3, scores: [10, 20, 30] });
```

在数组的倒数第一个元素之前添加元素：

```javascript
db.students.updateOne(
    { _id: 3 }, 
    { $push: { scores: { $each: [3, 4, 5], $position: -1} } }
);
```

该操作结果如下所示：

```javascript
{ "_id" : 3, "scores" : [ 10, 20, 3, 4, 5, 30 ] }
```



### 4.20. 数组修饰符：$sort

`$sort`：在 `$push` 的时候会根据数组字段排序。用来修饰`$push`。

> 注意：
>
> - `$sort`必须结合`$each`一起使用！
> - 可以使用一个空数组`[]`来使用 `$each` 修饰符，以便于只使用 `$sort` 排序的作用。

```javascript
// 语法
// 注意：<field> 代表的就是数组
{
  $push: {
     <field>: {
       $each: [ <value1>, <value2>, ... ],
       $sort: <sort specification>
     }
  }
}
```

对于 `<sort specification>`：

- 要对不是文档的数组元素进行排序，或者如果数组元素是文档，要按整个文档排序，请指定 `1`升序或`-1`降序。
- 如果数组元素是文档，要按文档中的字段排序，指定一个带有字段和方向的排序文档，即`{ field: 1 }`or `{ field: -1 }`。如果这样写`{ "arrayField.field": 1 }`是不正确的.

注意：

- `$sort`可以对数组中的非文档元素排序。
- 如果数组元素是文档，则修饰符可以按整个文档或文档中的特定字段进行排序。
- 使用`$sort`没有$each`修饰符会导致错误。 
- `$sort` 修饰符的使用不需要 `$slice`。

#### 4.20.1. 按字段对文档数组排序

```javascript
// students 集合中包含以下文档
db.students.insertOne(
    {
        "_id": 1,
        "quizzes": [
            { "id": 1, "score": 6 },
            { "id": 2, "score": 9 }
        ]
    }
);
```

下面的更新操作向 `quizzes` 数组中添加元素，并且按照 `score` 属性值升序排序，值相同则按照 `id` 字段降序排序。

```javascript
db.students.updateOne({ _id: 1 }, {
    $push: {
        quizzes: {
            $each: [{ id: 3, score: 8 }, { id: 4, score: 7 }, { id: 5, score: 6 }],
            $sort: { score: 1, id: -1 }
        }
    }
});
```

> 注意：
>
> 排序文档直接引用文档中的字段，不引用包含数组的字段 `quizzes`；即`{ score: 1 }`而**不是** `{ "quizzes.score": 1}`。

该操作结果如下所示：

```javascript
> db.students.find();
{ 
    "_id" : 1, 
    "quizzes" : [ 
        { "id" : 5, "score" : 6 }, 
        { "id" : 1, "score" : 6 }, 
        { "id" : 4, "score" : 7 }, 
        { "id" : 3, "score" : 8 }, 
        { "id" : 2, "score" : 9 } 
    ] 
}
```

#### 4.20.2. 排序不是文档的数组元素

```javascript
// students 集合中包含以下文档
db.students.insertOne({ "_id": 2, "tests": [89, 70, 89, 50] });
```

以下操作将另外两个元素添加到`scores`数组中并对数组元素进行升序排序：

```javascript
db.students.updateOne(
    { _id: 2 }, 
    {
        $push: {
            tests: {
                $each: [40, 60],
                $sort: 1
            }
        }
    }
);
```

#### 4.20.3. 仅用排序更新数组

```javascript
// students 集合中有以下文档
db.students.insertOne({ "_id": 3, "tests": [89, 70, 100, 20] });
```

`$each`使用空数组 `[]`，就可以只使用 `$sort` 排序了。

```javascript
db.students.updateOne(
    { _id: 3 }, 
    {
        $push: {
            tests: {
                $each: [],
                $sort: 1
            }
        }
    }
);
```

操作结果如下所示：

```javascript
{ "_id" : 3, "tests" : [ 20, 70, 89, 100 ] }
```



### 4.21. 数组修饰符：$slice

`$slice`：该修饰符会在 `$push` 操作中限制数组元素的数量。

使用 `$slice` 修饰符，必须和`$push`一起用。

```javascript
// 语法
// <field> 指的是数组
{
  $push: {
     <field>: {
       $each: [ <value1>, <value2>, ... ],
       $slice: <num>
     }
  }
}
```

`<num>`可以是：

| Value | value                           |
| :---- | :------------------------------ |
| 零    | 将数组更新为空数组。            |
| 负数  | 更新数组包含最后`<num>`个元素。 |
| 正数  | 更新数组包含前`<num>`个元素。   |

注意：

- 修饰符出现的顺序无关紧要。
- 使用 `$slice` 没有 `$each` 会报错。





## 5. 更新方法

### 5.1. findOneAndReplace()

```javascript
// 语法
db.collection.findOneAndReplace(
   <filter>,
   <replacement>,
   {
     projection: <document>,
     sort: <document>,
     maxTimeMS: <number>,
     upsert: <boolean>,
     returnNewDocument: <boolean>,
     collation: <document>
   }
);
```

注意：

- 返回值：默认返回原始文档，如果没有匹配到原始文档则返回 `null`。设置 `returnNewDocument: true` 返回替换的文档。
- 该方法替换第一个匹配的文档。该`sort`参数可用于影响修改哪个文档。

#### 5.1.1. 替换一个文档

```javascript
// scores 集合中有如下文档
db.scores.insertMany([
   { "_id" : 1, "team" : "Fearful Mallards", "score" : 25000 },
   { "_id" : 2, "team" : "Tactful Mooses", "score" : 23500 },
   { "_id" : 3, "team" : "Aquatic Ponies", "score" : 19250 },
   { "_id" : 4, "team" : "Cuddly Zebras", "score" : 15235 },
   { "_id" : 5, "team" : "Garrulous Bears", "score" : 18000 }
]);
```

下面的操作会匹配 `score` 属性值小于 `20000` 的文档并且 replace it：

```javascript
> db.scores.findOneAndReplace(
...     { score: { $lt: 20000 } }, 
...     { "team": "Observant Badgers", "score": 20000 }
... );

// 默认返回旧的文档（替换前的文档）
// 如果 returnNewDocument: true, 这里就会返回新替换的文档了！
{ "_id" : 3, "team" : "Aquatic Ponies", "score" : 19250 }
```

该操作更新结果如下：

```javascript
> db.scores.find();
{ "_id" : 1, "team" : "Fearful Mallards", "score" : 25000 }
{ "_id" : 2, "team" : "Tactful Mooses", "score" : 23500 }
{ "_id" : 3, "team" : "Observant Badgers", "score" : 20000 }	// 被更新的文档
{ "_id" : 4, "team" : "Cuddly Zebras", "score" : 15235 }
{ "_id" : 5, "team" : "Garrulous Bears", "score" : 18000 }
```

注意：尽管多个文档符合过滤条件，但是 `findOneAndReplace()` 只会替换一个文档。

#### 5.1.2. 排序并替换文档

```javascript
// scores 集合中有如下文档
db.scores.insertMany([
   { "_id" : 1, "team" : "Fearful Mallards", "score" : 25000 },
   { "_id" : 2, "team" : "Tactful Mooses", "score" : 23500 },
   { "_id" : 3, "team" : "Aquatic Ponies", "score" : 19250 },
   { "_id" : 4, "team" : "Cuddly Zebras", "score" : 15235 },
   { "_id" : 5, "team" : "Garrulous Bears", "score" : 18000 }
]);
```

通过 `score` 字段将整个文档正序排序，然后再替换文档。

```javascript
> db.scores.findOneAndReplace(
...     { score: { $lt: 20000 } }, 
...     { "team": "Observant Badgers", "score": 20000 },
...     { sort: { score: 1 } }
... );

// 排序替换
{ "_id" : 4, "team" : "Cuddly Zebras", "score" : 15235 }
```

该操作更新后的文档为：

```javascript
> db.scores.find();
{ "_id" : 1, "team" : "Fearful Mallards", "score" : 25000 }
{ "_id" : 2, "team" : "Tactful Mooses", "score" : 23500 }s
{ "_id" : 3, "team" : "Aquatic Ponies", "score" : 19250 }
{ "_id" : 4, "team" : "Observant Badgers", "score" : 20000 }  // 替换的文档
{ "_id" : 5, "team" : "Garrulous Bears", "score" : 18000 }
```

#### 5.1.3. 返回文档中的投影字段

```javascript
// scores 集合中有如下文档
db.scores.insertMany([
   { "_id" : 1, "team" : "Fearful Mallards", "score" : 25000 },
   { "_id" : 2, "team" : "Tactful Mooses", "score" : 23500 },
   { "_id" : 3, "team" : "Aquatic Ponies", "score" : 19250 },
   { "_id" : 4, "team" : "Cuddly Zebras", "score" : 15235 },
   { "_id" : 5, "team" : "Garrulous Bears", "score" : 18000 }
]);
```

以下操作使用投影仅仅展示 `team` 字段（在返回的文档中）。

```javascript
> db.scores.findOneAndReplace(
...    { "score" : { $lt : 22250 } },
       // 替换文档时这里不建议写 _id
...    { "team" : "Therapeutic Hamsters", "score" : 22250 },
...    { sort : { "score" : 1 }, projection: { "_id" : 0, "team" : 1 } }
... );
{ "team" : "Cuddly Zebras" }
```

该操作返回的结果如下所示：

```javascript
> db.scores.find();
{ "_id" : 1, "team" : "Fearful Mallards", "score" : 25000 }
{ "_id" : 2, "team" : "Tactful Mooses", "score" : 23500 }
{ "_id" : 3, "team" : "Aquatic Ponies", "score" : 19250 }
{ "_id" : 4, "team" : "Therapeutic Hamsters", "score" : 22250 }  // 替换的文档
{ "_id" : 5, "team" : "Garrulous Bears", "score" : 18000 }
```

#### 5.1.4. 有时间限制的替换文档

```javascript
// scores 集合中有如下文档
db.scores.insertMany([
   { "_id" : 1, "team" : "Fearful Mallards", "score" : 25000 },
   { "_id" : 2, "team" : "Tactful Mooses", "score" : 23500 },
   { "_id" : 3, "team" : "Aquatic Ponies", "score" : 19250 },
   { "_id" : 4, "team" : "Cuddly Zebras", "score" : 15235 },
   { "_id" : 5, "team" : "Garrulous Bears", "score" : 18000 }
]);
```

下面的操作需要在5ms内完成：

```javascript
> db.scores.findOneAndReplace(
...   { "score" : { $lt : 25000 } },
...   { "team" : "Emphatic Rhinos", "score" : 25010 },
...   { maxTimeMS: 5 }
... );

// 返回结果
{ "_id" : 2, "team" : "Tactful Mooses", "score" : 23500 }
```

如果操作时间超时了，就会返回：

```javascript
Error: findAndModifyFailed failed: { "ok" : 0, "errmsg" : "operation exceeded time limit", "code" : 50 }
```

#### 5.1.5. 使用upsert替换文档

```javascript
db.scores.insertMany([
   { "_id" : 1, "team" : "Fearful Mallards", "score" : 25000 },
   { "_id" : 2, "team" : "Tactful Mooses", "score" : 23500 },
   { "_id" : 3, "team" : "Aquatic Ponies", "score" : 19250 },
   { "_id" : 4, "team" : "Cuddly Zebras", "score" : 15235 },
   { "_id" : 5, "team" : "Garrulous Bears", "score" : 18000 }
]);
```

如果 `<filter>` 没有匹配到文档，就会在集合中插入 `replacement document`。

```javascript
> db.scores.findOneAndReplace(
...     { "team" : "Fortified Lobsters" },
...     { "_id" : 6019, "team" : "Fortified Lobsters" , "score" : 32000},
...     { upsert : true, returnNewDocument: true }
... );
{ "_id" : 6019, "team" : "Fortified Lobsters", "score" : 32000 } // 集合中新增一条记录！
```

如果 `returnNewDocument: false`，没有文档匹配，这个操作就会返回 `null`。

### 5.2. findAndModify()

`findAndModify()`：修改并返回单个文档。

```javascript
// 语法
db.collection.findAndModify({
    query: <document>,
    sort: <document>,
    remove: <boolean>,
    update: <document or aggregation pipeline>, // Changed in MongoDB 4.2
    new: <boolean>,
    fields: <document>,
    upsert: <boolean>,
    bypassDocumentValidation: <boolean>,
    writeConcern: <document>,
    collation: <document>,
    arrayFilters: [ <filterdocument1>, ... ],
    let: <document> // Added in MongoDB 5.0
});
```

返回数据：

- 对于 `remove` 操作，如果查询匹配了一个文档。`findAndModify()` 会返回移除的文档。如果查询并没有匹配到文档，`findAndModify()`就会返回 null。
- 对于 `update` 操作，`findAndModify()` 会返回以下一个：
  - 如果 `new` 参数没有设置 或者 是 `false`：
    - 如果查询匹配文档，则为修改前文档；
    - 其他情况下返回 null。
  - 如果 `new` 参数是 `true`：
    - 如果查询匹配到文档，返回修改后的文档；
    - 查询没有匹配文档，但是 `upsert:true`，返回新插入的文档；
    - 其他情况下返回null。

#### 5.2.1. 更新和返回

```javascript
// people 集合中有如下文档
db.people.insertMany([
    { name: "Tom", state: "active", rating: 20, score: 10 },
    { name: "Jerry", state: "active", rating: 18, score: 5 },
    { name: "Britha", state: "active", rating: 15, score: 6 },
]);
```

下面的操作有如下几步：

1. 查询方法会根据 `rating` 大于 10来查询匹配的文档。
2. 查询的结果按照`rating`字段升序排序。如果查询匹配的文档有多个，则选择排序后的第一个文档作为更新文档。
3. 将目标文档的`score`字段值加1。
4. 该方法会返回更新之前的值。

```javascript
> db.people.findAndModify({
...     query: { rating: { $gt: 10 } },
...     sort: { rating: 1 },
...     update: { $inc: { score: 1 } }
... });
{
	"_id" : ObjectId("61077dc56dc5e6430ce4a9c8"),
	"name" : "Britha",
	"state" : "active",
	"rating" : 15,
	"score" : 6
}
```

该操作的结果如下所示：

```javascript
> db.people.find().pretty();
{
	"_id" : ObjectId("61077dc56dc5e6430ce4a9c6"),
	"name" : "Tom",
	"state" : "active",
	"rating" : 20,
	"score" : 10
}
{
	"_id" : ObjectId("61077dc56dc5e6430ce4a9c7"),
	"name" : "Jerry",
	"state" : "active",
	"rating" : 18,
	"score" : 5
}
{
	"_id" : ObjectId("61077dc56dc5e6430ce4a9c8"),
	"name" : "Britha",
	"state" : "active",
	"rating" : 15,
	"score" : 7
}
```

**注意**：

```javascript
// 在 Spring Boot 中可以这样写
Update update = new Update().inc("score", 1);
mongoTemplate.findAndModify(query(where("rating").gt(10)).with(Sort.by("rating").ascending()), update, Object.class, "people");
```



#### 5.2.2. Upsert And New

```javascript
// people 集合中有如下文档
db.people.insertMany([
    { name: "Tom", state: "active", rating: 20, score: 10 },
    { name: "Jerry", state: "active", rating: 18, score: 5 },
    { name: "Britha", state: "active", rating: 15, score: 6 },
]);
```

下面的方法中 包含`upsert: true`选项 。

该方法更新匹配的文档并返回匹配到的文档，或者 `<query>` 查询不到匹配的文档，则添加一个新文档。

```javascript
> db.people.findAndModify({
...     query: { name: "Gus", state: "active", rating: 100 },
...     sort: { rating: 1 },
...     update: { $inc: { score: 1 } },
...     // new: true,  					// 这里写的是true, 就会返回更改后的文档了！
...     upsert: true
... });
null     								// 返回的结果
```

该操作的结果如下所示：

```javascript
> db.people.find().pretty();
{
	"_id" : ObjectId("610784e86dc5e6430ce4a9cf"),
	"name" : "Tom",
	"state" : "active",
	"rating" : 20,
	"score" : 10
}
{
	"_id" : ObjectId("610784e86dc5e6430ce4a9d0"),
	"name" : "Jerry",
	"state" : "active",
	"rating" : 18,
	"score" : 5
}
{
	"_id" : ObjectId("610784e86dc5e6430ce4a9d1"),
	"name" : "Britha",
	"state" : "active",
	"rating" : 15,
	"score" : 6
}
// 这里是新增的文档
{
	"_id" : ObjectId("61078509e32b793ade309af0"),
	"name" : "Gus",
	"rating" : 100,
	"state" : "active",
	"score" : 1
}
```



#### 5.2.3. 排序和删除

```javascript
// people 集合中有如下文档
db.people.insertMany([
    { name: "Tom", state: "active", rating: 20, score: 10 },
    { name: "Jerry", state: "active", rating: 18, score: 5 },
    { name: "Britha", state: "active", rating: 15, score: 6 },
]);
```

下面的操作按照查询结果进行排序，删除排序后的第一个文档。

```javascript
> db.people.findAndModify(
...    {
...      query: { state: "active" },
...      sort: { rating: 1 },
...      remove: true
...    }
... );
{
	"_id" : ObjectId("610789646dc5e6430ce4a9d4"),
	"name" : "Britha",
	"state" : "active",
	"rating" : 15,
	"score" : 6
}
```

该操作后的集合为：

```javascript
> db.people.find().pretty();
{
	"_id" : ObjectId("610789646dc5e6430ce4a9d2"),
	"name" : "Tom",
	"state" : "active",
	"rating" : 20,
	"score" : 10
}
{
	"_id" : ObjectId("610789646dc5e6430ce4a9d3"),
	"name" : "Jerry",
	"state" : "active",
	"rating" : 18,
	"score" : 5
}
```



#### 5.2.4. 更新数组指定元素

```javascript
// students 集合中有如下文档
db.students.insertMany([
   { "_id" : 1, "grades" : [ 95, 92, 90 ] },
   { "_id" : 2, "grades" : [ 98, 100, 102 ] },
   { "_id" : 3, "grades" : [ 95, 110, 100 ] }
]);
```

下面操作查询 `grades` 数组中大于等于100的文档。

然后过滤出 `grades` 数组的元素（大于等于100）。

最后将**第一条文档**中数组的元素修改为100。

```javascript
> db.students.findAndModify({
...     query: { grades: { $gte: 100 } },
...     update: { $set: { "grades.$[item]": 100 } },
...     arrayFilters: [{ "item": { $gte: 100 } }]
... });
{ "_id" : 2, "grades" : [ 98, 100, 102 ] }
```

> 注意：虽然查询结果有多个文档匹配，但是只会更新第一个文档！

该操作更新集合的结果如下：

```javascript
> db.students.find().pretty();
{ "_id" : 1, "grades" : [ 95, 92, 90 ] }
{ "_id" : 2, "grades" : [ 98, 100, 100 ] }	// 更新的文档
{ "_id" : 3, "grades" : [ 95, 110, 100 ] }
```

#### 5.2.5. 更新文档数组的指定元素

>  注意：`arrayFilters`不能用于聚合管道的更新。

```javascript
// students2 集合中有如下文档
db.students2.insert([
   {
      "_id" : 1,
      "grades" : [
         { "grade" : 80, "mean" : 75, "std" : 6 },
         { "grade" : 85, "mean" : 90, "std" : 4 },
         { "grade" : 85, "mean" : 85, "std" : 6 }
      ]
   },
   {
      "_id" : 2,
      "grades" : [
         { "grade" : 90, "mean" : 75, "std" : 6 },
         { "grade" : 87, "mean" : 90, "std" : 3 },
         { "grade" : 85, "mean" : 85, "std" : 4 }
      ]
   }
]);
```

下面操作虽然查询出了多条文档，但是只更新一条文档。

`grades`数组中文档的`grade`属性值大于等于85就会被更新。

```javascript
> db.students2.findAndModify({
...     query: { },
...     update: { $inc: { "grades.$[item].mean": 10 } },
...     arrayFilters: [{ "item.grade": { $gte: 85 } }]
... });
// 返回被更新的文档
{
	"_id" : 1,
	"grades" : [
		{
			"grade" : 80,
			"mean" : 75,
			"std" : 6
		},
		{
			"grade" : 85,
			"mean" : 90,
			"std" : 4
		},
		{
			"grade" : 85,
			"mean" : 85,
			"std" : 6
		}
	]
}
```

该操作得到的文档结果如下所示：

```javascript
> db.students2.find();
{ 
    "_id" : 1, 
    "grades" : [ 
        { "grade" : 80, "mean" : 75, "std" : 6 }, 
        { "grade" : 85, "mean" : 100, "std" : 4 },   // 更新了
        { "grade" : 85, "mean" : 95, "std" : 6 }     // 更新了
    ] 
}
{ 
    "_id" : 2, 
    "grades" : [ 
        { "grade" : 90, "mean" : 75, "std" : 6 }, 
        { "grade" : 87, "mean" : 90, "std" : 3 }, 
        { "grade" : 85, "mean" : 85, "std" : 4 } 
    ] 
}
```

#### 5.2.6. 使用聚合管道更新












## 6.Aggregation stage

在 `db.collection.aggregate()` 方法中，这些 stages 都出现在一个数组中。

文档按照顺序来通过这些 stages。

除了`$out、$mergr、$geoNear`这3个 stages，其他 stages 都可以出现多次。

```javascript
// 语法
db.collection.aggregate( [ { <stage> }, ... ] )
```

###  6.1. $addFields

`$addFields`：向文档中添加新字段。它会对流中的每个文档进行重塑（改变文档结构）。

特别地，通过添加新的属性来输出的document 包含 **已经存在的属性 和 新添加的属性**。

`$set` 是 `$addFields`的别名。

```shell
# 语法
{ $addFields: { <newField>: <expression>, ... } }
```

注意：

- `$addFields` 在已经存在的文档中添加新的属性。可以在一个聚合操作中包含一个或者多个`$addFields`。
- 添加一个属性或者多个属性到嵌入式的文档中（包括数组中的文档），使用点表示法。
- 使用`$addFields`将一个元素添加到现有数组字段，使用`$concatArrays`。

#### 6.1.1. 使用两个$addFields

```json
// score集合包含以下两个文档
db.score.insertMany([{
        _id: 1,
        student: "Maya",
        homework: [10, 5, 10],
        quiz: [10, 8],
        extraCredit: 0
    },
    {
        _id: 2,
        student: "Ryan",
        homework: [5, 6, 5],
        quiz: [8, 8],
        extraCredit: 8
    }
]);
```

以下使用两个`$addFields`阶段添加3个新属性到document中。

```javascript
db.score.aggregate([{
        $addFields: {
            totalHomework: { $sum: "$homework" },
            totalQuiz: { $sum: "$quiz" }
        }
    },
    {
        $addFields: {
            totalScore: { $add: ["$totalHomework", "$totalQuiz", "$extraCredit"] }
        }
    }
]);
```

- `$sum`：计算并返回数值的总和。会忽略非数值字段。
- `$add`：将数字相加，或者将日期相加。如果是数字 + 日期，数字就会作为毫秒增加。

该操作会返回以下文档：

```javascript
{
  "_id" : 1,
  "student" : "Maya",
  "homework" : [ 10, 5, 10 ],
  "quiz" : [ 10, 8 ],
  "extraCredit" : 0,
  "totalHomework" : 25,
  "totalQuiz" : 18,
  "totalScore" : 43
}
{
  "_id" : 2,
  "student" : "Ryan",
  "homework" : [ 5, 6, 5 ],
  "quiz" : [ 8, 8 ],
  "extraCredit" : 8,
  "totalHomework" : 16,
  "totalQuiz" : 16,
  "totalScore" : 40
}
```



#### 6.1.2. 添加属性到嵌入式文档

使用 "点表示法" 向嵌入式文档中添加新的属性。

```javascript
// 集合vehicles包含以下文档
db.vehicles.insertMany([
    { _id: 1, type: "car", specs: { doors: 4, wheels: 4 } },
    { _id: 2, type: "motorcycle", specs: { doors: 0, wheels: 2 } },
    { _id: 3, type: "jet ski" }
]);
```

下面的聚合操作添加一个新的属性`fuel_type`到嵌入式的文档`specs`中。

```javascript
db.vehicles.aggregate([{ $addFields: { "specs.fuel_type": "unleaded" } }]);
```

该操作返回的结果：

```javascript
{ _id: 1, type: "car",
   specs: { doors: 4, wheels: 4, fuel_type: "unleaded" } }
{ _id: 2, type: "motorcycle",
   specs: { doors: 0, wheels: 2, fuel_type: "unleaded" } }
{ _id: 3, type: "jet ski",
   specs: { fuel_type: "unleaded" } }
```



#### 6.1.3. 重写一个现有的属性

在`$addFields`操作中指定一个现有属性的名字，会导致原有的属性被替换。

```javascript
// animals 集合中包含以下字段
db.animals.insertOne({ _id: 1, dogs: 10, cats: 15 });
```

下面`$addFields`操作指定 `cats` 属性。

```javascript
db.animals.aggregate( [
  {
    $addFields: { "cats": 20 }
  }
] )
```

该操作返回以下文档：

```javascript
{ _id: 1, dogs: 10, cats: 20 }
```

---

可以用一个属性来替代另一个属性。下面的例子中`item`属性替换了`_id`属性。

```javascript
db.fruit.insertMany([
    { "_id": 1, "item": "tangerine", "type": "citrus" },
    { "_id": 2, "item": "lemon", "type": "citrus" },
    { "_id": 3, "item": "grapefruit", "type": "citrus" }
]);
```

下面的聚合操作使用 `$addFields` 将`item` 属性的值替换为每个文档中的 `_id` 属性值，并且使用静态的值来替换 `item` 属性值。

```javascript
db.fruit.aggregate([{ $addFields: { _id: "$item", item: "fruit" } }]);
```

该操作返回以下文档：

```javascript
{ "_id" : "tangerine", "item" : "fruit", "type" : "citrus" }
{ "_id" : "lemon", "item" : "fruit", "type" : "citrus" }
{ "_id" : "grapefruit", "item" : "fruit", "type" : "citrus" }
```



#### 6.1.4. 添加元素到数组

```javascript
// scores 集合包含以下文档
db.scores.insertMany([
   { 
       _id: 1, 
       student: "Maya", 
       homework: [ 10, 5, 10 ], 
       quiz: [ 10, 8 ], 
       extraCredit: 0 
   },
   { 
       _id: 2, 
       student: "Ryan", 
       homework: [ 5, 6, 5 ], 
       quiz: [ 8, 8 ], 
       extraCredit: 8 
   }
]);
```

可以使用 `$addFields` 和 `$concatArrays` 向现有的数组中添加元素。

下面的操作使用 `$addFields` 用一个新数组替代了 `homework` 属性，这个新数组的元素是当前 `homework` 数组和 `[7]` 这个数组连接而成的。

```javascript
db.scores.aggregate([
    { $match: { _id: 1 } },
    { $addFields: { homework: { $concatArrays: ["$homework", [7]] } } }
]);
```

- `$concatArrays`：将数组进行连接，返回一个连接好的数组。

该操作返回以下文档：

```javascript
{
    "_id" : 1,
    "student" : "Maya", 
	"homework" : [ 10, 5, 10, 7 ], 
	"quiz" : [ 10, 8 ], 
	"extraCredit" : 0 
}

```



### 6.2. $bucket

`$bucket`：根据指定的表达式和bucket边界将传入文档分类为组（称为bucket），并为每个bucket输出一个文档。每个输出文档都包含一个 `_id` 字段，其值指定bucket的包含下限。output选项指定每个输出文档中包含的字段。

> 注意：`$bucket`阶段有100MB的内存限制。默认情况下如果超过这个限制，`$bucket`就会返回错误。
>
> 要为`bucket`阶段留出更多的空间，请使用 [allowDiskUse](https://docs.mongodb.com/manual/reference/command/aggregate/#std-label-aggregate-cmd-allowDiskUse)选项启用聚合管道阶段以将数据写入临时文件。

```javascript
// 语法
{
  $bucket: {
      groupBy: <expression>,
      boundaries: [ <lowerbound1>, <lowerbound2>, ... ],
      default: <literal>,
      output: {
         <output1>: { <$accumulator expression> },
         ...
         <outputN>: { <$accumulator expression> }
      }
   }
}
```

`$bucket`至少满足下列条件之一，否则会发生错误：

- 每个输入文档将[groupBy](https://docs.mongodb.com/manual/reference/operator/aggregation/bucket/#std-label-bucket-group-by) 表达式解析为[boundaries](https://docs.mongodb.com/manual/reference/operator/aggregation/bucket/#std-label-bucket-boundaries)指定的一个bucket范围内的值。
- 为 `groupBy` 值在 `boundaries` 之外或与 `boundaries` 中的值的 [BSON type](https://docs.mongodb.com/manual/reference/bson-types/) 不同的bucket文档指定 [default](https://docs.mongodb.com/manual/reference/operator/aggregation/bucket/#std-label-bucket-default)  value。

> 如果`groupBy`表达式解析为数组或文档，` $bucket` 使用 `$sort`的比较逻辑将输入文档排列到bucket中。



#### 6.2.1. Bucket by Year and Filter by Bucket Results

```javascript
// artists集合有如下文档
db.artists.insertMany([
    { "_id": 1, "last_name": "Bernard", "first_name": "Emil", "year_born": 1868, "year_died": 1941, "nationality": "France" },
    { "_id": 2, "last_name": "Rippl-Ronai", "first_name": "Joszef", "year_born": 1861, "year_died": 1927, "nationality": "Hungary" },
    { "_id": 3, "last_name": "Ostroumova", "first_name": "Anna", "year_born": 1871, "year_died": 1955, "nationality": "Russia" },
    { "_id": 4, "last_name": "Van Gogh", "first_name": "Vincent", "year_born": 1853, "year_died": 1890, "nationality": "Holland" },
    { "_id": 5, "last_name": "Maurer", "first_name": "Alfred", "year_born": 1868, "year_died": 1932, "nationality": "USA" },
    { "_id": 6, "last_name": "Munch", "first_name": "Edvard", "year_born": 1863, "year_died": 1944, "nationality": "Norway" },
    { "_id": 7, "last_name": "Redon", "first_name": "Odilon", "year_born": 1840, "year_died": 1916, "nationality": "France" },
    { "_id": 8, "last_name": "Diriks", "first_name": "Edvard", "year_born": 1855, "year_died": 1930, "nationality": "Norway" }
]);
```

下面的操作根据`year_born`字段分组，并放入`bucket`中。后续根据 `bucket`中文档的数量进行过滤。

注意：`$sum: 1` 是计数器。

```javascript
db.artists.aggregate( [
  // First Stage
  {
    $bucket: {
      groupBy: "$year_born",                        // Field to group by
      boundaries: [ 1840, 1850, 1860, 1870, 1880 ], // Boundaries for the buckets
      default: "Other",                             // Bucket id for documents which do not fall into a bucket
      output: {                                     // Output for each bucket
        // 如果前面的情况出现一次,就加1, 如果$sum:2 那么每次前面条件满足一次就加2
        "count": { $sum: 1 },
        "artists" :
          {
            $push: {
              "name": { $concat: [ "$first_name", " ", "$last_name"] },
              "year_born": "$year_born"
            }
          }
      }
    }
  },
  // Second Stage
  {
    $match: { count: {$gt: 3} }
  }
] )
```

- `$sum: 1`表示每个bucket的计数器，符合条件就加1。
- `$push`：将元素添加到数组中。
- `boundaries: [ 1840, 1850, 1860, 1870, 1880 ]` 实际上是划分了4个边界范围。

**第一阶段**：`$bucket` 阶段按照 `year_born` 字段来分组放入 `bucket`中，该 `bucket` 包含以下边界：

- [1840, 1850) with inclusive lowerbound `1840` and exclusive upper bound `1850`.
- [1850, 1860) with inclusive lowerbound `1850` and exclusive upper bound `1860`.
- [1860, 1870) with inclusive lowerbound `1860` and exclusive upper bound `1870`.
- [1870, 1880) with inclusive lowerbound `1870` and exclusive upper bound `1880`.
- If a document did not contain the `year_born` field or its `year_born` field was outside the ranges above, it would be placed in the [default](https://docs.mongodb.com/manual/reference/operator/aggregation/bucket/#std-label-bucket-default) bucket with the `_id` value `"Other"`.

该阶段的返回值传到下一阶段：

```javascript
{ 
    "_id" : 1840, 
    "count" : 1, 
    "artists" : [ { "name" : "Odilon Redon", "year_born" : 1840 } ] 
}

{ 
    "_id" : 1850, 
    "count" : 2, 
    "artists" : [ 
        { "name" : "Vincent Van Gogh", "year_born" : 1853 },
        { "name" : "Edvard Diriks", "year_born" : 1855 } 
    ] 
}

{ 
    "_id" : 1860, 
    "count" : 4, 
    "artists" : [ 
        { "name" : "Emil Bernard", "year_born" : 1868 },
        { "name" : "Joszef Rippl-Ronai", "year_born" : 1861 },
        { "name" : "Alfred Maurer", "year_born" : 1868 },
        { "name" : "Edvard Munch", "year_born" : 1863 }
    ]
}

{ 
    "_id" : 1870, 
    "count" : 1, 
    "artists" : [ 
        { "name" : "Anna Ostroumova", "year_born" : 1871 } 
    ] 
}
```

**第二阶段**：该`$match`阶段过滤上一阶段的输出，仅返回包含 3 个以上文档的 `bucket`。

该操作返回以下文档：

```javascript
{ "_id" : 1860, "count" : 4, "artists" :
  [
    { "name" : "Emil Bernard", "year_born" : 1868 },
    { "name" : "Joszef Rippl-Ronai", "year_born" : 1861 },
    { "name" : "Alfred Maurer", "year_born" : 1868 },
    { "name" : "Edvard Munch", "year_born" : 1863 }
  ]
}
```

