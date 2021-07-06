# 一、Node基础

## 1. 模块化

### 1.1. QuickStart

**模块化**：

1. 在Node中，一个js文件就是一个模块。

2. 在Node中，每个js文件中的js代码都是独立运行在一个函数中, 不是全局作用域（在其他模块中无法访问）。

现在问题是：如果其他js文件需要访问模块中的变量或者函数？？

`exports.xxx` 可以向外部暴露属性/方法。

```js
// hello.js
// 使用 exports 来暴露属性/方法
exports.x = 10;
exports.y = 20;
exports.car = {
    name: 'AODI'
}
exports.fn = () => {
    console.log('暴露函数');
}
```

```javascript
// app.js
// 使用require()来引入hello.js文件，返回值hello代表的就是当前模块
// require中写的是 “相对路径”
const hello = require('./hello');
console.log(hello);										// 打印模块中的全部内容

console.log(hello.x);
console.log(hello.y);
hello.fn();
```

运行结果：

```js
PS E:\学习\NodeJS\01-node> node .\app.js					// node xxx.js 可以执行js文件了
{ x: 10, y: 20, car: { name: 'AODI' }, fn: [Function] }
10
20
暴露函数
```



### 1.2. 模块化详解

使用`require()`引入模块以后，该函数会返回一个对象，这个对象代表的就是引入的模块。

模块分为两大类：    

- 核心模块：由Node提供的模块。**模块标识就是模块的名字**。
- 文件模块：用户自己创建的模块。

```javascript
// 核心模块

// 引入node提供的 "文件模块"
const fs = require('fs');
console.log(fs);

// 文件模块
require('./hello');
```

------

如何证明 node 中每个js文件都运行在函数中呢？

**一**、node 中提供了 `global`，其中保存了全局变量和全局函数。

```javascript
let a = 10;

/**
 * 在 node 中有一个全局对象 global。
 * 在全局中创建的变量/函数都会保存到 global 中
 */
console.log(global.a);					// 结果：undefined => 证明 js 中的变量是局部变量
```

```javascript
// 当去掉let
a = 10;									// 定义全局变量
console.log(global.a);					// 10 证明现在的变量a是全局变量了
```



**二**、js中可以使用 `arguments` 获得函数的参数（只有函数才有arguments，全局里没有）。

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/NodeJS/arguments.4ey922yf0zs0.png" alt="arguments"  />

```javascript
// 在node中打印arguments
console.log(arguments);
```

运行结果：

```javascript
// 有 5 个参数
[Arguments] {
  '0': {},
  '1': [Function: require] {
    resolve: [Function: resolve] { paths: [Function: paths] },
    main: Module {
      id: '.',
      path: 'E:\\学习\\NodeJS\\01-node',
      exports: {},
      parent: null,
      filename: 'E:\\学习\\NodeJS\\01-node\\math.js',
      loaded: false,
      children: [],
      paths: [Array]
    },
    extensions: [Object: null prototype] {
      '.js': [Function],
      '.json': [Function],
      '.node': [Function]
    },
    cache: [Object: null prototype] {
      'E:\\学习\\NodeJS\\01-node\\math.js': [Module]
    }
  },
  '2': Module {
    id: '.',
    path: 'E:\\学习\\NodeJS\\01-node',
    exports: {},
    parent: null,
    filename: 'E:\\学习\\NodeJS\\01-node\\math.js',
    loaded: false,
    children: [],
    paths: [
      'E:\\学习\\NodeJS\\01-node\\node_modules',
      'E:\\学习\\NodeJS\\node_modules',
      'E:\\学习\\node_modules',
      'E:\\node_modules'
    ]
  },
  '3': 'E:\\学习\\NodeJS\\01-node\\math.js',
  '4': 'E:\\学习\\NodeJS\\01-node'
}
```



**三**、直接查看函数？`arguments.callee`可以返回当前函数结构。

```javascript
console.log(arguments.callee + "");   // 返回当前函数
```

运行结果：

```javascript
// 这个函数是 node.js 添加的
// node.js会给我们写的代码包装到函数中，所以定义的变量都是局部变量
function (exports, require, module, __filename, __dirname) {
    console.log(arguments.callee + "");
}
```



**实际上，参数中的 exports 就是 module.exports**。

```javascript
console.log(exports == module.exports);						// true
```

所以，`exports、module.exports` 都可以导出，本质上没有区别。



```javascript
// __filename 当前文件的完整路径
// __dirname 当前文件所在文件夹的路径
console.log(__filename);    		// E:\学习\NodeJS\01-node\math.js
console.log(__dirname);     		// E:\学习\NodeJS\01-node 
```



### 1.3. 两种导出方式

```javascript
// hello.js
module.exports = {
    name: 'zs',
    age: 18,
    toString() {
        return 'name: ' + this.name + ', ' + 'age: ' + this.age;
    }
}
```

```javascript
// app.js
const hello = require('./hello')
console.log(hello.name);
console.log(hello.age);
console.log(hello.toString());
```

运行结果：

```javascript
PS E:\学习\NodeJS\01-node> node .\app.js
zs
18
name: zs, age: 18

// 但是去掉 module ，那么就会报错~
```

**总结**：

- `module.exports` 用于导出对象。
- `exports.xxx` 用于导出属性/函数。



## 2. package

`package`实际上就是一个压缩文件，解压后还原为目录。符合规范的目录，应该包含如下文件：

- `package.json`：包描述文件（重要）。用于表达非代码相关的信息，它是一个JSON格式的文件。**位于包的根目录下，是包的重要组成部分，JSON文件中不能写注释**。
- `bin`：可执行二进制文件。
- `lib`：js代码。
- `doc`：文档。
- `test`：单元测试。



## 3. NPM

### 3.1. npm命令

**一、npm配置**

```shell
# 查看 npm 版本
npm -v

# 查看所有模块的版本
npm version

# 查看各个命令的简单用法
npm -l

# 查看 npm 的配置
npm config list -l
```



**二、npm init 初始化包**

1. `npm init` 用来初始化生成一个新的 `package.json` 文件。它会向用户提问一系列问题，如果觉得不用修改默认配置，一路回车就可以了。尾缀带`-f`（代表force）、`-y`（代表yes），则跳过提问阶段，直接生成一个新的`package.json`文件，不带尾缀的话，默认有提问阶段。以上创建好之后就可以在`Package.json`直接看了 还可以在里面修改更新。

2. 然后在当前目录下执行 `npm install xxx` 安装指定包就可以了。
3. 使用安装的包就用 `require()` 导入即可。



**三、npm install 安装包**

```shell
# 安装包
npm install xxx
npm -i xxx

# 安装包并添加到依赖中(用的很多)
npm install xxx --save

# 全局安装（全局安装一般都是工具），一般不用
npm install -g xx 

# 卸载包 
npm uninstall xxx

# 移除包
npm remove/r xxx
```

![npm install](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/NodeJS/npm.1nwh53ngopc0.png)



### 3.2. 配置cnpm

方式一：通过命令配置

```shell
npm config set registry https://registry.npm.taobao.org
npm config get registry
```

如果返回https://registry.npm.taobao.org，说明镜像配置成功。



方式二：安装cnpm

```shell
npm install -g cnpm --registry=https://registry.npm.taobao.org
```



### 3.3. npm搜索包流程

node在使用模块名字来引入模块时，它首先在当前目录的node_modules中寻找是否含有该模块，如果有则直接使用，如果没有则去上一级目录的node_modules中寻找。

直到找到磁盘的根目录依然没有，直接报错。

**当前目录下的包被删了，也有可能可以用，因为上层目录可能存在node_modules，其中存在包**~



## 4. Buffer缓冲区

`Buffer`（缓冲区）：Buffer的结构和数组很像。操作的方法也和数组类似。

**Buffer实际上是对底层内存的直接操作**。

**在buffer中存储的都是二进制数据（用16进制来显示）**。



**一、将字符串存入Buffer缓冲区**。

```javascript
let str = "Hello Buffer";

// 将字符串保存到 Buffer 中
let buf = Buffer.from(str);

// 在buffer中存储的都是二进制数据（用16进制来显示）
console.log(buf);               // <Buffer 48 65 6c 6c 6f 20 42 75 66 66 65 72>
console.log(str.length);        // 12
console.log(buf.length);        // 12
```



**二、创建指定大小的Buffer**。 

`Buffer.alloc(size)` 来创建指定大小的缓冲区，初始化为0。

```javascript
// Buffer 的所有构造函数都废弃了！
let buf = Buffer.alloc(10);
console.log(buf.length);           // 1024
console.log(buf);                  // <Buffer 00 00 00 00 00 00 00 00 00 00>
```

`Buffer.allocUnsafe(size)`：分配指定大小的Buffer。以这种方式创建的 `Buffer` 实例的底层内存不会被初始化。 新创建的 `Buffer` 的内容未知，可能包含敏感数据。

```javascript
const bufUnsafe = Buffer.allocUnsafe(10);
console.log(bufUnsafe);             // <Buffer 48 d8 9d fe b6 01 00 00 00 00>
```



**三、通过索引来操作Buffer中的元素**。

```javascript
let buf = Buffer.alloc(10);
// 每个字节的取值范围是00-FF（0-255）
buf[0] = 0x88;
buf[1] = 88;              // 十进制会转化成16进制
buf[2] = 256;             // 1 0000 0000 缓冲区只能保存8位 => 0000 0000（00）
buf[3] = 556;             // 0010 0010 1100 缓冲区只能保存8位 => 0010 1100（2c）

/**
 * Buffer的大小一旦确定不能修改。Buffer实际上是对底层内存的直接操作~
 */
buf[10] = 0xff;
console.log(buf);           // <Buffer 88 58 00 2c 00 00 00 00 00 00>

// 是要缓冲区的内容在 页面/控制台 输出, 都是十进制
console.log(buf[0]);        // 136

// 转成16进制字符串
console.log(buf[0].toString(16));  // 88
```



## 5. 文件系统

`fs`模块中所有的操作都有两种形式可供选择，同步和异步：

- **同步文件系统会阻塞程序的执行**，除非操作完毕，否则不会向下执行代码。
- **异步文件系统不会阻塞程序的执行**，而是在操作完成时，通过回调函数将结果返回。

### 5.1. 同步文件写入

``fs.openSync(path[, flags[, mode]])``：打开文件。该方法会返回一个描述符作为结果，通过描述符来做各种操作。

- `path`：文件的路径。
- `flags`：打开文件后要做的操作。
- `mode`：设置文件的操作权限，一般不传。

![文件系统的标志](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/NodeJS/文件系统的标志.pq9lw12ge5s.png)



`fs.writeSync(fd, string[, position[, encoding]])`：在文件中写。

- `fd`：写入文件的描述符。
- `string`：要写入的内容。
- `position` ：指从文件开头数据应被写入的偏移量。
- `encoding` ：预期的字符串编码。**默认是utf8**。

```javascript
// 导入 fs 库
const fs = require('fs');

// 打开文件
const fd = fs.openSync('E:/Users/14666/Desktop/Hello.txt', 'a');

// 写入内容
console.log(fs.writeSync(fd, 'Hello Node'));

// 关闭文件
fs.closeSync(fd);
```

