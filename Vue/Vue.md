# 一：vue基础入门

## 1. vue安装

**方式一：直接CDN引入**

```html
<!-- 对于制作原型或学习，你可以这样使用最新版本：-->
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

<!-- 对于生产环境，我们推荐链接到一个明确的版本号和构建文件，以避免新版本造成的不可预期的破坏：-->
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
```

**方式二：下载和引入**

```html
<!-- 直接下载并用 <script> 标签引入，Vue 会被注册为一个全局变量。-->
<!-- 提前下载好 vue.js -->
<script src="../js/vue.js"></script>
```

**方式三：NPM安装**



## 2. vue.js的使用

```xml
<!-- vue实例中的选项 -->
<!--
 1、el: 类型：string。作用：决定之后Vue实例会管理哪一个DOM。
 2、data：类型：Object|Function(组件中data必须是函数)。作用：Vue实例对应的数据对象。
 3、methods：类型：Function。作用：定义属于Vue的一些方法，可以在其他地方调用，也可以在命令中使用。	
-->
<script>
    const app = new Vue({
    el: '',
    data: {
    }.
    methods: {
    }
    })
</script>
```



### 2.1. 响应式编程

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>001-Hello</title>
</head>

<body>
    <!-- 拿到data中的数据 -->
    <div id="app">{{message}}</div>
</body>

<!-- 引入vue.js -->
<script src="../js/vue.js"></script>

<script>
    const app = new Vue({
        // 1、用于挂载要管理的元素
        el: '#app',

        // 2、定义数据
        data: {
            message: 'Hello, vue.js!'
        }
    })
</script>
</html>
```

![image-20210105125634270](E:\Typora\image\image-20210105125634270.png)



### 2.2. 列表展示

`v-for`：遍历

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>002-列表展示</title>
</head>

<body>
    <div id="app">
        <ul>
            <!-- v-for遍历 -->
            <li v-for="(item, index) in movies" :key="index">
                {{item}} index = {{index}}
            </li>
        </ul>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data: {
            message: 'Hello',
            movies: ['海王', '亲王绕柱', '大话西游', '回家的诱惑']
        }
    })
</script>

</html>
```

![image-20210105130933849](E:\Typora\image\image-20210105130933849.png)



### 2.3. 计数器

`v-on:click、@click`：监听点击事件。

`methods: {}`：这里可以定义方法。

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>003-计数器</title>
</head>

<body>
    <div id="app">
        <!-- 方式一： -->
        <h2>当前计数: {{counter1}}</h2>
        <!-- v-on:click事件点击监听,这里的counter就是data中的counter -->
        <button v-on:click="counter1++">+</button>
        <button v-on:click="counter1--">-</button>

        <h2>-----------------------------------------</h2>

        <!-- 方式二： -->
        <h2>当前计数: {{counter2}}</h2>
        <button @click="increment">+</button>
        <button @click="decrement">-</button>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',

        // 定义数据
        data: {
            counter1: 0,
            counter2: 0
        },

        // 定义方法
        methods: {
            increment() {
                this.counter2++
            },
            decrement() {
                this.counter2--
            }
        },
    })
</script>

</html>
```

<img src="E:\Typora\image\image-20210105133037743.png" alt="image-20210105133037743" style="zoom:150%;" />



## 3. vue生命周期

<img src="E:\Typora\image\image-20210105185012057.png" alt="image-20210105185012057" style="zoom:150%;" />

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>004-生命周期函数</title>
</head>

<body>
    <div id="app">
        <h1>{{msg}}</h1>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                msg: 'Hello World!',
            }
        },
        beforeCreate() {
            console.log('beforeCreate()...');
        },
        created() {
            console.log('create()...');
        },
        beforeMount() {
            console.log('beforeMount()...');
        },
        mounted() {
            console.log('mounted()...');
        },
        beforeUpdate() {
            console.log('beforeUpdate()...');
        },
        updated() {
            console.log('updated()...');
        },
        beforeDestroy() {
            console.log('beforeDestroy()...');
        },
        destroyed() {
            console.log('destroyed()...');
        },
    })
</script>

</html>
```

![image-20210105190342046](E:\Typora\image\image-20210105190342046.png)



## 4. 插值表达式

### 4.1. 双大括号语法

```html
<!-- {{}} 双大括号会解析vue实例的data中的数据 -->
<body>
    <div id="app">
        <!-- 1、直接使用 -->
        <h1>1、{{msg}}</h1>

        <!-- 2、可以进行拼接 -->
        <h1>2、{{msg}}, Ringo</h1>

        <!-- 3、这样也是可以解析的！ 中间还可以加字符串 -->
        <h2>3、{{firstName + ' ' + lastName}}</h2>

        <!-- 4、分开解析,并且显示中间有空格 -->
        <h2>4、{{firstName}} {{lastName}}</h2>

        <!-- 5、大括号内部可以做计算 -->
        <h2>5、{{number * 2}}</h2>
    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                msg: '你好呀Vue!',
                firstName: 'Ringo',
                lastName: 'Lency',
                number: 100,
            }
        },
    })
</script>
```

![image-20210105192839033](E:\Typora\image\image-20210105192839033.png)



### 4.2. v-once指令

```html
<body>
    <div id="app">
        <!-- v-once 就会禁用数据的响应式 -->
        <h2 v-once>{{msg}}</h2>
    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                msg: '你好呀',
            }
        },
    })
</script>
```

![image-20210105193621049](E:\Typora\image\image-20210105193621049.png)



### 4.3. v-html指令

```html
<body>
    <div id="app">
        <!-- 1、{{}} 直接展示msg -->
        <h1>{{msg}}</h1>

        <!-- 2、v-html 将msg中的HTML标签解析出来 -->
        <h1 v-html="msg" ></h1>
    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                msg: '<a>你好呀</a>',
            }
        },
    })
</script>
```

<img src="E:\Typora\image\image-20210105194347895.png" alt="image-20210105194347895" style="zoom:150%;" />



### 4.4. v-text指令

```html
<body>
    <div id="app">
        <!-- v-text会覆盖标签中的文本 -->
        <h1 v-text="msg">, Ringo</h1>
        
        <!-- 双大括号语法就比较灵活 -->
        <h1>{{msg}}, Ringo</h1>
    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                msg: '<a>你好呀</a>',
            }
        },
    })
</script>
```

<img src="E:\Typora\image\image-20210105194927384.png" alt="image-20210105194927384" style="zoom:150%;" />



### 4.5. v-pre指令

```html
<body>
    <div id="app">
        <h1>{{msg}}</h1>

        <!-- v-pre将标签中的内容直接显示(vue不会解析) -->
        <h1 v-pre>{{msg}}</h1>
    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                msg: '你好呀',
            }
        },
    })
</script>
```

![image-20210105195336904](E:\Typora\image\image-20210105195336904.png)





### 4.6. v-cloak指令

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
    <!-- 这里来设置css样式 -->
    <style>
        [v-cloak] {
            display: none;
        }
    </style>
</head>

<body>
    <!-- 在vue解析之前，div中有一个属性v-cloak -->
    <!-- 在vue解析之后，div中没有一个属性v-cloak -->
    <!-- v-cloak防止因为网络的问题vue没有解析出数据 界面出现{{msg}}的问题 -->
    <div id="app" v-cloak>
        <h1>{{msg}}</h1>
    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                msg: '你好呀',
            }
        },
    })
</script>
```



## 5. v-bind动态绑定属性

**(1) v-bind的简单使用**

```html
<body>
    <div id="app">
        <!-- 1、错误的使用{{}}语法 -->
        <!-- <img src="{{msg}}" alt=""> -->

        <!-- 2、正确的使用v-bind -->
        <!-- v-bind:属性="data中的数据", 可以将属性和变量动态绑定 -->
        <img v-bind:src="url" />

        <!-- 3、:href="" 绑定href(v-bind的简写)  -->
        <a :href="href">百度一下</a>
    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                url: 'https://cn.vuejs.org/images/logo.png',
                href: 'https://www.baidu.com',
            }
        },
    })
</script>
```

<img src="E:\Typora\image\image-20210105203039554.png" alt="image-20210105203039554" style="zoom:150%;" />

**(2) v-bind动态绑定class(对象语法1)**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>002-v-bind绑定class</title>

    <!-- css设置颜色 -->
    <style>
        .active {
            color: red;
        }
    </style>
</head>

<body>
    <div id="app">
        <h2 class="active">{{msg}}</h2>
        
        <!-- :class="{key:value}" value一般使用bool值 -->
        <!-- 当 value = true 时, class="active" -->
        <!-- 当 value = false 时, active 就不会加在class上 -->
        <h2 :class="{active:isActive}">{{msg}}</h2>
    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
               msg: '你好呀', 
               isActive: true,
            }
        },
    })
</script>
```

![image-20210105204416301](E:\Typora\image\image-20210105204416301.png)



**(3) v-bind动态绑定class(对象语法2—绑定方法)**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>002-v-bind绑定class</title>

    <!-- css设置颜色 -->
    <style>
        .active {
            color: red;
        }
    </style>
</head>

<body>
    <div id="app">
        <!-- :class="{key:value}" value一般使用bool值 -->
        <!-- 当 value = true 时, class="active" -->
        <!-- 当 value = false 时, active 就不会加在class上 -->
        <!-- getkeyValue() 这里要加() -->
        <h2 :class="getkeyValue()">{{msg}}</h2>

        <!-- 按钮点击变色 -->
        <!-- change之所以不加括号，是因为被省略了 -->
        <!-- 真实调用函数的时候最好加上() -->
        <button v-on:click="change">按钮</button></button>
    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                msg: '你好呀',
                isActive: true,
            }
        },
        methods: {
            // 点击按钮变色
            change() {
                this.isActive = !this.isActive
            },

            // 动态绑定class
            getkeyValue() {
                return {
                    active: this.isActive
                }
            }
        },
    })
</script>
```

<img src="E:\Typora\image\image-20210105210541165.png" alt="image-20210105210541165" style="zoom:150%;" />



**(4) v-bind 动态绑定class(数组语法)**

```html
<body>
    <div id="app">
        <!-- 对象语法、数组语法和普通的class都是可以共存的 -->
        <h1 class="title" :class="[active, line]">Tom</h1>
        <h1 :class="getArray()">Jack</h1>
    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                active: 'aaaa',
                line: 'bbb',
            }
        },
        methods: {
            getArray() {
                return [this.active, this.line]
            }
        },
    })
</script>
```

![image-20210105211620337](E:\Typora\image\image-20210105211620337.png)



**(5) v-bind 和 v-for 结合使用案例**

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>004-v-for和v-bind案例</title>

    <!-- css 使字体变红 -->
    <style>
        .active {
            color: red;
        }
    </style>
</head>

<body>
    <div id="app">
        <ul>
            <!-- 初始让movies[0]显示红色,之后点击哪个<li></li>,就让其变红色 -->
            <li v-for="(item, index) in movies" :key="index" v-on:click="change(index)"
                :class="{active:isActive(index)}">
                {{item}} {{index}}
            </li>
        </ul>
    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                movies: ['海王', '火影', '泰坦尼克号', '柯南'],
                current: 0,
            }
        },
        methods: {
            // 点击后将index的值赋给current
            change(index) {
                this.current = index
            },

            // 判断 current 是否等于 index
            isActive(index) {
                return this.current == index
            },
        },
    })
</script>
```

![image-20210105214809480](E:\Typora\image\image-20210105214809480.png)



**(6) v-bind动态绑定style(对象语法)**

```html
<body>
    <div id="app">
        <!-- 动态绑定style对象语法 :style="{key:value}" -->
        <!-- key是属性名 value是属性值 -->
        <!-- 这里不能使用font-size，必须使用fontSize -->
        <h1 :style="{fontSize:getFontSize()}">{{msg}}</h1>

        <!-- 这样写也是可以的, 不能直接写50px,要加上单引号'50px' -->
        <!-- 这里的value如果不加'',vue解析的时候就会解析data中的变量,加上''才表示是一个整体 -->
        <h1 :style="{fontSize:'50px', color:'red'}">{{msg}}</h1>

        <!-- 这里的value没有加'', 就是直接去data中找名字为fontSize的变量 -->
        <h1 :style="{fontSize:fontSize, color:'green'}">{{msg}}</h1>

    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    // 这里一定要有const app 才有响应式！！！！
    const app = new Vue({
        el: '#app',
        data: {
            msg: '你好呀',
            fontSize: '50px',
        },
        methods: {
            getFontSize() {
                return this.fontSize
            }
        },
    })
</script>
```



## 6. 计算属性

**(1)  计算属性基本使用**

```html
<body>
    <div id="app">
        <!-- 这里使用计算属性不加()-->
        <!-- 这里用{{fullName()}} 会报错-->
        <h1>{{fullName}}</h1>
    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                firstName: 'Ringo',
                lastName: 'Lency',
            }
        },

        // 计算属性 --> 归根到底是属性
        // 计算属性起名字的时候按照规矩就直接起名就可以了，不需要加动词
        computed: {
            fullName() {
                return this.firstName + ' ' + this.lastName
            }
        },
    })
</script>
```

<img src="E:\Typora\image\image-20210106131955770.png" alt="image-20210106131955770" style="zoom:150%;" />



**(2) 计算属性复杂操作：计算图书总价**

```html
<body>
    <div id="app">
        <table>
            <thead>
                <th>编号</th>
                <th>书名</th>
                <th>价格</th>
            </thead>
            <tbody>
                <tr v-for="(item, index) in books" :key="index">
                    <td>{{item.id}}</td>
                    <td>{{item.name}}</td>
                    <td>{{item.price}}</td>
                </tr>
            </tbody>
            <tfoot>
                <th>总价</th>
                <th>---></th>
                </th>
                <th>{{totalPrice}}</th>
            </tfoot>
        </table>
    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                books: [
                    { id: 110, name: 'Linux编程', price: 119 },
                    { id: 111, name: '算法', price: 89 },
                    { id: 112, name: '操作系统', price: 150 },
                    { id: 113, name: '计算机组成原理', price: 120 },
                ],
            }
        },
        computed: {
            // 计算所有书的价钱
            totalPrice() {
                let price = 0;
                this.books.forEach(element => {
                    price += element.price
                });
                return price;
            },
        },
    })
</script>
```

<img src="E:\Typora\image\image-20210106133810410.png" alt="image-20210106133810410" style="zoom:150%;" />



**(3) 计算属性的getter和setter**

```html
<body>
    <div id="app">
        <h1>{{fullName}}</h1>
    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                firstName: 'Ringo',
                lastName: 'Lency',
            }
        },

        // 计算属性其实是默认调用了对象中的get()
        // 计算属性一般没有set(),我们不希望给计算属性设置值
        // 由于计算属性中只有get(),就可以简写为 ==> fullName(){}
        computed: {
            fullName: {
                get() {
                    return this.firstName + ' ' + this.lastName;
                },
            }
        },
    })
</script>
```

![image-20210106135242489](E:\Typora\image\image-20210106135242489.png)



**(4) 计算属性和methods的对比**

```html
<body>
    <div id="app">
        <!-- 1、调用methods中的方法要加() -->
        <!-- 多次调用methods中的方法,这个方法会被调用多次 -->
        <h1>{{getFullName()}}</h1>
        <h1>{{getFullName()}}</h1>
        <h1>{{getFullName()}}</h1>

        <!-- 2、computed中的计算属性,直接写属性名就行了,不需要加() -->
        <!-- 多次调用计算属性,该计算属性只会被调用一次！ -->
        <h1>{{fullName}}</h1>
        <h1>{{fullName}}</h1>
        <h1>{{fullName}}</h1>
    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                firstName: 'Ringo',
                lastName: 'Lency',
            }
        },

        methods: {
            getFullName() {
                console.log('getFullName()....');
                return this.firstName + ' ' + this.lastName;
            }
        },

        computed: {
            fullName() {
                console.log('fullName()....');
                return this.firstName + ' ' + this.lastName;
            }
        },
    })
</script>
```

![image-20210106140650789](E:\Typora\image\image-20210106140650789.png)



## 7. ES6语法

### 7.1. 块级作用域

```html
<script>
    // 变量作用域：变量在什么范围内可以使用。
    // 1、var没有块级作用域——if和for中都没有块级作用域
    {
        var name = 'zs';
        console.log('var {} 里面' + name);
    }
    console.log('var {} 外面' + name);

    // 2、var在函数中是有块级作用域的
    function testVar() {
        var price = '10元';
        console.log('price testVar() 内' + price);
    }

    testVar();
    console.log('price testVar() 外' + price);

    // 3、let有块级作用域
    {
        let age = 18;
        console.log('let {} 里面' + age);
    }
    console.log('let {} 外面' + age);

</script>
```

![image-20210106142610190](E:\Typora\image\image-20210106142610190.png)



### 7.2. const的用法

**在开发中，优先使用const，只有需要改变某一个标识符的时候才使用 let**。

```html
<script>
    // 注意一：const定义常量必须初始化
    // const name;

    // 注意二：一旦定义了const常量,该常量不可修改      
    // const age = 18;
    // age = 20; 

    // 注意三：const定义的对象不可修改,但是可以修改对象内的属性
    const person = {
        name: 'zs',
        age: '18',
    };
    person.age = 19;
    console.log(person);
</script>
```

<img src="E:\Typora\image\image-20210106143836455.png" alt="image-20210106143836455" style="zoom: 200%;" />



### 7.3. 对象增强写法

```html
<script>
    // 1、对象属性的增强写法
    const name = 'zs';
    const age = 18;
    const gender = '男';

    // ES5 语法
    const person1 = {
        name: name,
        age: age,
        gender: gender,
    };

    // ES6 语法
    const person2 = {
        name,
        age,
        gender,
    }

    console.log(person1);
    console.log(person2);

    // 2、函数的增强写法
    // ES5 语法
    const cat1 = {
        eat: function () {
            console.log('cat1 eats fish...');
        },
    };
    cat1.eat();

    // ES6 语法
    const cat2 = {
        eat() {
            console.log('cat2 eats fish...');
        },
    };
    cat2.eat();
</script>
```

![image-20210106145216734](E:\Typora\image\image-20210106145216734.png)



## 8. 事件监听

### 8.1. v-on：基本使用

```html
<body>
    <div id="app">
        <h1>计数器: {{counter}}</h1>

        <!-- v-on:click 监听事件的点击 -->
        <button v-on:click="increment()">+</button>

        <!-- @click：v-on:click的简写 -->
        <button @click="decrement()">-</button>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                counter: 0,
            }
        },
        methods: {
            // 计数器 + 1
            increment() {
                this.counter += 1;
            },

            // 计数器 - 1
            decrement() {
                this.counter -= 1;
            },
        },
    });
</script>
```



### 8.2. v-on：传递参数

```html
<body>
    <div id="app">
        <!-- 按钮1点击调用方法没有传递参数 -->
        <button @click="btn1Click()">按钮1</button>

        <!-- 按钮2点击调用方法传递参数 -->
        <button @click="btn2Click(123)">按钮2</button>

        <!-- 按钮3点击调用方法应该有参数,但是没有传递 -->
        <button @click="btn3Click()">按钮3</button>

        <!-- 按钮4点击调用方法应该有参数,但是没有传递并且方法没有加() -->
        <!-- 这里应该是 <==> @click="btn4Click($event)" -->
        <button @click="btn4Click">按钮4</button>

        <!-- 按钮5点击调用方法同时需要参数和event对象 -->
        <button @click="btn5Click(8848, $event)">按钮5</button>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        methods: {
            btn1Click() {
                console.log('btn1Click...');
            },
            btn2Click(param) {
                console.log('btn2Click...' + param);
            },
            btn3Click(param) {
                console.log('btn3Click...' + param);
            },
            btn4Click(event) {
                console.log('--------------------');
                console.log('btn4Click...');
                console.log(event);
            },
            btn5Click(param, event) {
                console.log('--------------------');
                console.log('btn5Click.....' + param);
                console.log(event);
            },
        },
    });
</script>
```

![image-20210106152522955](E:\Typora\image\image-20210106152522955.png)



### 8.3. v-on：修饰符

```html
<body>
    <div id="app">
        <!-- 1、.stop修饰符的使用 -->
        <div @click="divClick()">
            <!-- @click.stop防止事件冒泡 -->
            <button @click.stop="btnClick()">按钮</button>
        </div>

        <!-- 2、.prevent阻止默认事件 -->
        <form action="https://www.baidu.com" method="POST">
            <input type="submit" value="提交" @click.prevent="submitClick()"/>
        </form>

        <!-- 3、@keyup监听键盘的按键 -->
        <!-- @keyup.enter只监听Enter回车键 -->
        <input type="text" @keyup.enter="keyupLinstener()" />

        <!-- 4、.once只监听一次回调 -->
        <button @click.once="testOnce()">只监听一次回调</button>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {

            }
        },
        methods: {
            btnClick() {
                console.log('点击button...');
            },
            divClick() {
                console.log('点击div...');
            },
            submitClick() {
                console.log('点击submit..');
            },
            keyupLinstener() {
                console.log('监听键盘点击...');
            },
            testOnce() {
                console.log('test .once ....');
            },
        },
    });
</script>
```



## 9. 条件判断

### 9.1. v-if 和 v-else

```html
<body>
    <div id="app">
        <!-- 1、v-if="condition" 这个condition是bool类型的值 -->
        <h1 v-if="isShow">{{msg}}</h1>

        <!-- 2、v-if和v-else联合使用 -->
        <h1 v-else>abc</h1>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                msg: '你好呀',
                isShow: true,
            }
        },
    });
</script>
```

![image-20210106173834036](E:\Typora\image\image-20210106173834036.png)



### 9.2. v-else-if

`v-else-if`很少用。

```html
<body>
    <div id="app">
        <h1 v-if="score>=90">优秀</h1>
        <h1 v-else-if="score>=80">良好</h1>
        <h1 v-else-if="score>=60">及格</h1>
        <h1 v-else>不及格</h1>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return { 
                score: 90,
            }
        },
    });
</script>
```

![image-20210106175138682](E:\Typora\image\image-20210106175138682.png)



### 9.3. 用户登录切换案例

```html
<body>
    <div id="app">
        <span v-if="isUser">
            <!-- label中的 for="username",当点击用户名的时候可以聚焦到input上 -->
            <!-- input中加入key点击切换的时候就不会复用 -->
            <label for="username">用户账号</label>
            <input type="text" id="username" placeholder="用户账号" key="username"/>
        </span>
        <span v-else>
            <label for="email">用户邮箱</label>
            <input type="text" id="email" placeholder="用户邮箱" key="email">
        </span>
        <button @click="switchLoginType()">切换类型</button>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                isUser: true,
            }
        },
        methods: {
            switchLoginType() {
                this.isUser = !this.isUser;
            }, 
        },
    });
</script>
```

<img src="E:\Typora\image\image-20210106185500942.png" alt="image-20210106185500942" style="zoom:150%;" />



### 9.4. v-show

```html
<body>
    <div id="app">
        <!-- v-if 条件为false时 根本就没有DOM -->
        <h1 v-if="isShow" id="aaa">{{msg}}</h1>

        <!-- v-show 条件为false时 是添加了style style="display: none;"-->
        <h1 v-show="isShow" id="bbb">{{msg}}</h1>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                msg: '你好呀',
                isShow: true,
            }
        },
    });
</script>
```

<img src="E:\Typora\image\image-20210106190345322.png" alt="image-20210106190345322" style="zoom:150%;" />

`v-show、v-if`如何选择？

- 当需要在显示和隐藏之间切换很频繁时，使用`v-show`。
- 当只有一次切换时，使用`v-if`。



## 10. 遍历数组和对象

```html
<body>
    <div id="app">
        <!-- 1、遍历数组：在名字前面加上序号 -->
        <ul>
            <!-- v-for尽量绑定 key，并且保证绑定key的元素是唯一的，index下标是唯一的！-->
            <li v-for="(name, index) in names" :key="index">
                {{(index + 1) + ' ' + name}}
            </li>
        </ul>

        <!-- 2、遍历对象 -->
        <!-- 2.1、获取对象的key、value -->
        <ul>
            <li v-for="(value, key, index) in info" :key="index">
                {{key + '：' + value}}
            </li>
        </ul>

        <!-- 2.2、获取对象的key、value、index -->
        <ul>
            <li v-for="(value, key, index) in info" :key="index">
                {{(index + 1) + ' ' + key + '：' + value}}
            </li>
        </ul>
    </div>
</body>
<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                names: ['张三', '李四', '王五'],
                info: {
                    name: 'Ringo',
                    age: 18,
                    gender: '男',
                },
            }
        },
    });
</script>
```

![image-20210106192417231](E:\Typora\image\image-20210106192417231.png)



## 11. 数组中的响应式方法

```html
<body>
    <div id="app">
        <ul>
            <li v-for="(letter, index) in letters" :key="index">
                {{letter}}
            </li>
        </ul>
        <button @click="btnClick()">响应式方法测试</button>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                letters: ['A', 'B', 'C', 'D', 'E'],
            }
        },
        methods: {
            btnClick() {
                // 1、push()：从数组尾部添加元素 并且length + 1
                this.letters.push('A');
                console.log(this.letters);

                // 注意：push()可以一次添加多个元素
                this.letters.push('8', '9');
                console.log(this.letters);

                // 2、通过索引值修改数组中的值(最好用splice()来修改数组的值)
                this.letters[1] = 'CCCCCCCCCCCC';
                console.log(this.letters);

                // 3、pop()：弹出数组最后一个元素 并且length - 1
                let p = this.letters.pop();
                console.log(p);
                console.log(this.letters);

                // 4、shift()：弹出数组第一个元素 并且length - 1
                this.letters.shift();
                console.log(this.letters);

                // 5、unshift()：在数组第一个元素前添加元素 并且length + 1
                this.letters.unshift('8848');
                console.log(this.letters);

                // 注意：unshift()也可以一次添加多个元素
                this.letters.unshift('8849', '8850');
                console.log(this.letters);

                // 6、splice(): 删除元素/插入元素/替换元素 
                // splice(index,howmany,item1,.....,itemX)
                // index: 必需。整数，规定添加/删除项目的位置，使用负数可从数组结尾处规定位置。
                // howmany: 可选。要删除的项目数量。如果设置为 0，则不会删除项目。
                // item1, ..., itemX: 可选。向数组添加的新项目。
                // splice(index, howmany)：删除元素, 从index开始删除howmany个元素
                // splice(index, howmany, item..)：替换元素,替换从index开始, 将howmany个元素替换为 item..
                // splice(index, 0, item..)：插入元素,在下边index之前插入元素
                this.letters.splice(0, 0, '1');
                console.log(this.letters);

                // 7、sort()：排序
                this.letters.sort();
                console.log(this.letters);

                // 8、reverse()：反转
                this.letters.reverse();
                console.log(this.letters);

                // 9、Vue.set(object, index, value)：可以修改数组
                Vue.set(this.letters, 0, 'qwer');
                console.log(this.letters);
            },
        },
    });
</script>
```



## 12. 购物车案例(filters)

- `toFixed(n)`：保留小数点后 n 位小数。
- Vue实例中可以使用过滤器 `filter`。

```html
<body>
    <!-- 使用过滤器 -->
    {{price|finalPrice}}
</body>

<script>
	const app = new Vue({
        el: '#app',
        data() {
            return {
                price: 50,
            }
        },
        fiters: {
          finalPrice(price) {
          	return '￥' + (price.tofixed(2));  
          },  
        },
    });
</script>
```

Vue.js 允许你自定义过滤器，可被用于一些常见的文本格式化。过滤器可以用在两个地方：**双花括号插值和 `v-bind` 表达式** (后者从 2.1.0+ 开始支持)。过滤器应该被添加在 JavaScript 表达式的尾部，由“管道”符号指示：

```html
<!-- 在双花括号中 -->
{{ message | capitalize }}

<!-- 在 `v-bind` 中 -->
<div v-bind:id="rawId | formatId"></div>
```



**购物车案例**：

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>购物车案例</title>

    <style>
        table {
            border: 1px solid #e9e9e9;
            border-collapse: collapse;
            border-spacing: 0;
        }

        th,
        td {
            padding: 8px 16px;
            border: 1px solid #e9e9e9;
            text-align: left;
        }

        th {
            background-color: #f7f7f7;
            color: #5c6b77;
            font-weight: 600;
        }
    </style>

</head>

<body>
    <div id="app">
        <table v-if="books.length > 0">
            <tbody>
                <tr>
                    <th>编号</th>
                    <th>书籍名称</th>
                    <th>出版日期</th>
                    <th>价格</th>
                    <th>购买数量</th>
                    <th>操作</th>
                </tr>
            </tbody>
            <tbody>
                <tr v-for="(book, index) in books" :key="index">
                    <td>{{index + 1}}</td>
                    <td>{{book.name}}</td>
                    <td>{{book.time}}</td>
                    <!-- {{book.price | finalPrice}}使用过滤器 -->
                    <td>{{book.price | finalPrice}}</td>
                    <td>
                        <button @click="decrement(index)">-</button>
                        <span>{{book.number}}</span>
                        <button @click="increment(index)">+</button>
                    </td>
                    <td>
                        <button @click="remove(index)">移除</button>
                    </td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <th>总价</th>
                    <th colspan="5">{{totoal | finalPrice}}</th>
                </tr>
            </tfoot>
        </table>
        <h1 v-else>购物车记录为空！</和>
    </div>
</body>

<script src="./js/vue.js"></script>

<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                books: [
                    { name: '算法导论', time: '2006-9', price: 85.00, number: 1 },
                    { name: 'Unix编程', time: '2006-2', price: 59.00, number: 1 },
                    { name: '编程珠玑', time: '2008-10', price: 39.00, number: 1 },
                    { name: '代码大全', time: '2006-3', price: 128.00, number: 1 },
                ],
            }
        },
        methods: {
            // 下标为index的图书购买数量增加
            increment(index) {
                this.books[index].number++;
            },

            // 下标为index的图书购买数量减少
            decrement(index) {
                if (this.books[index].number > 1) {
                    this.books[index].number--;
                }
            },

            // 移除数组中下标为index的元素
            remove(index) {
                this.books.splice(index, 1);
            },
        },

        computed: {
            // 计算书的总价
            totoal() {
                let sum = 0.00;
                this.books.forEach(book => {
                    sum += book.price * book.number;
                });
                return sum;
            }
        },

        // 过滤器
        filters: {
            finalPrice(price) {
                // tofixed(n)：保留n位小数
                return '￥' + (price.toFixed(2));
            },
        },
    });
</script>
```

![image-20210107140716595](E:\Typora\image\image-20210107140716595.png)



## 13. JS高阶函数

```html
<script>
    const numbers = [1, 3, 5, 7, 9];

    console.log('-------------for(let i in array)-------------');
    // 1、for(let i in array)：这里的i拿到的是数组的下标
    for (let i in numbers) {
        console.log(numbers[i]);
    }
    
    const obj = {
        username: '123',
        password: '456',
    };

    // 遍历对象 这里的 i 拿到的是对象的 key
    for(let i in obj) {
        console.log(i);              // username  123
        console.log(obj[i]);         // 123       456
    }

    console.log('-------------for(let element of array)-------------');
    // 2、for (let number of numbers)：这里拿到的是数组中每个元素
    for (let number of numbers) {
        console.log(number);
    }

    console.log('-------------arrays.filter(function())-------------');
    // 3、arrays.filter(function())
    // filter()中的回调函数必须返回一个boolean值
    const a1 = numbers.filter(number => number > 3);
    console.log(a1);

    console.log('-------------arrays.map(function())-------------');
    // 4、array.map(function(currentValue,index,arr), thisValue)
    // currentValue：必须。当前元素的值
    // index：可选。当前元素的索引值
    // arr:可选。当前元素属于的数组对象 ==> 就是当前数组
    // thisValue：可选。对象作为该执行回调时使用，传递给函数，用作 "this" 的值。如果省略了 thisValue，或者传入 null、undefined，那么回调函数的 this 为全局对象
    // map()中的回调函数会对数组中元素做操作
    const a2 = numbers.map(number => number * 2);
    console.log(a2);

    console.log('-------------reduce(function)-------------');
    // 5、array.reduce(function(total, currentValue, currentIndex, arr), initialValue)
    // total：必需。初始值, 或者计算结束后的返回值
    // currentValue：必需。当前元素
    // currentIndex：可选。当前元素的索引
    // arr：可选。当前元素所属的数组对象 ==> 就是当前数组
    // initialValue：可选。传递给函数的初始值
    // reduce：对数组中所有内容进行汇总

    // 5.1、数组求和
    const sum1 = numbers.reduce((total, currentValue) => total + currentValue);
    console.log('sum1 = ' + sum1);

    // 5.2. 数组求和然后再 + 5
    const sum2 = numbers.reduce((total, currentValue) => total + currentValue, 5);
    console.log('sum2 = ' + sum2);

</script>
```

![image-20210107145923481](E:\Typora\image\image-20210107145923481.png)



## 14. v-model指令

### 14.1. 基本使用和原理

`v-model`实现了数据的双向绑定！经常和表单一起使用！

```html
<body>
    <div id="app">
        <h1>msg：{{msg}}</h1>
        <!-- 1、v-bind:value="" 单向绑定：数据 ==> DOM -->
        <input type="text" :value="msg" /><br />

        <!-- 2、v-model双向绑定：数据 <==> DOM -->
        <!-- v-model经常和表单一起使用 -->
        <!-- v-model的使用限制input(checkbox,radio,text),select,textarea,components -->
        <input type="text" v-model="msg"><br />

        <!-- 3、v-model的原理：v-bind:value(动态绑定value) 和 v-on:input(input事件监听) -->
        <!-- v-bind:value + v-on:input 也可以实现双向绑定 -->
        <input type="text" :value="msg" v-on:input="valueChange($event)">

    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                msg: '你好呀',
            }
        },
        methods: {
            valueChange(event) {
                console.log('valueChange()..');
                console.log(event);
                this.msg = event.target.value;
            },
        },
    });
</script>
```



### 14.2. 结合radio使用

```html
<body>
    <div id="app">
        <h1>gender：{{gender}}</h1>
        <label for="male">
            <!-- name：保证互斥 -->
            <!-- id：label的引用 -->
            <!-- value：name所对应的值 -->
            <!-- 注意：v-model绑定同一个值，name可以不写 -->
            <input type="radio" name="gender" id="male" value="male" v-model="gender">男
        </label>

        <label for="female">
            <input type="radio" name="gender" id="female" value="female" v-model="gender">女
        </label>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                // 打开页面默认就会选择男
                gender: 'male',
            }
        },
    });
</script>
```

![image-20210107155334420](E:\Typora\image\image-20210107155334420.png)



### 14.3. 结合checkbox使用

**(1) checkbox 单选框：v-model 绑定的是boolean值**。

```html
<body>
    <div id="app">
        <!-- 1、checkbox单选框 -->
        <h1>isAgree：{{isAgree}}</h1>
        <label for="agree">
            <!-- checkbox中的v-model要绑定boolean值 -->
            <input type="checkbox" name="agree" id="agree" v-model="isAgree">同意协议
        </label>
        <br />

        <!-- 不点同意下一步就不能点 -->
        <button :disabled="!isAgree">下一步</button>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                // 默认是false
                isAgree: false,
            }
        },
    });
</script>
```

![image-20210107161031901](E:\Typora\image\image-20210107161031901.png)



**(2) checkbox复选框：checkbox复选框v-model绑定的是数组**。

```html
<body>
    <div id="app">
        <h1>interests:{{interests}}</h1>
        <label for="basketball">
            <input type="checkbox" name="interest" id="basketball" value="basketball" v-model="interests">篮球
        </label>

        <label for="football">
            <input type="checkbox" name="interest" id="football" value="football" v-model="interests">足球
        </label>

        <label for="badminton">
            <input type="checkbox" name="interest" id="badminton" value="badminton" v-model="interests">羽毛球
        </label>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                interests: [],
            }
        },
    });
</script>
```

![image-20210107162443596](E:\Typora\image\image-20210107162443596.png)



### 14.4. 结合select使用

**(1) select 单选：v-model绑定的是值**。

```html
<body>
    <div id="app">
        <h1>interest: {{interest}}</h1>
        <!-- select选择一个 -->
        <!-- v-model="interest" 绑定interest属性 -->
        <select name="interest" v-model="interest">
            <option value="basketball">篮球</option>
            <option value="football">足球</option>
            <option value="badminton">羽毛球</option>
        </select>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                // 默认显示football
                interest: 'football',
            }
        },
    });
</script>
```

![image-20210107164407940](E:\Typora\image\image-20210107164407940.png)



**(2) select选择多个：v-model绑定的是数组**。

```html
<body>
    <div id="app">
        <h1>interests: {{interests}}</h1>
        <!--v-model="interests" 绑定的是数组 -->
        <!-- select上添加multiple即可选中多个 -->
        <select name="interest" v-model="interests" multiple>
            <option value="basketball">篮球</option>
            <option value="football">足球</option>
            <option value="badminton">羽毛球</option>
            <option value="ping-pang">乒乓球</option>
        </select>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                // v-model绑定数组
                interests: [],
            }
        },
    });
</script>
```

![image-20210107165131143](E:\Typora\image\image-20210107165131143.png)



### 14.5. 值绑定

```html
<body>
    <div id="app">
        <h1>interests：{{interests}}</h1>
        <label v-for="(item, index) in originalInterests" :key="index" :for="item.value">
            <input type="checkbox" name="interest" :id="item.value" :value="item.value" v-model="interests" >{{item.name}}
        </label>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                // 从服务器请求到的数据
                originalInterests: [
                    { name: '篮球', value: 'basketball' },
                    { name: '足球', value: 'football' },
                    { name: '羽毛球', value: 'badminton' },
                ],

                // 用户选择的数据
                interests: [],
            }
        },
    });
</script>
```

![image-20210107191953925](E:\Typora\image\image-20210107191953925.png)



### 14.6. v-model修饰符

```html
<body>
    <div id="app">
        <!-- 1、lazy：等用户输入完成后,按Enter/input失去焦点的时候再绑定数据 -->
        <!-- 如果不用lazy, 默认是实时绑定,写一个字母就会修改数据 -->
        <h1>msg：{{msg}}</h1>
        <input type="text" v-model.lazy="msg" />
        <br />

        <!-- 2、number：input中的数字会被解析成number类型 -->
        <!-- 默认情况下 input 输入框内无论输入什么都是string类型 -->
        <h1>age：{{age}}</h1>
        <h1>typeof age：{{typeof age}}</h1>
        <input type="number" v-model.number="age" />

        <!-- 3、trim：消除字符串左右两边的空格 -->
        <h1>name.length: {{name.length}}</h1>
        <input type="text" v-model.trim="name" />
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                msg: '你好呀',
                age: 18,
                name: 'zhangsan',
            }
        },
    });
</script>
```



### 15. watch的使用

```html
<body>
    <div id="app">
        <h1>{{fullName}}</h1>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                firstName: 'zhang',
                lastName: 'san',
                fullName: 'zhang san',
            }
        },

        watch: {
            // 监听 firstName 的变化
            firstName(val) {
                this.fullName = val + ' ' + this.lastName;
            },

            // 监听 lastName 的变化
            lastName(val) {
                this.fullName = this.firstName + ' ' + val;
            }
        },
    });
</script>
```

![image-20210213180542495](E:\Typora\image\image-20210213180542495.png)



# 二、组件化开发

## 1. 组件化的实现

**(1) 注册组件的基本步骤**：

- 创建组件构造器。
- 注册组件。
- 使用组件。

![image-20210107194240512](E:\Typora\image\image-20210107194240512.png)

```html
<body>
    <div id="app">
        <!-- 3、使用组件 -->
        <cpn></cpn>
        <cpn></cpn>
        <cpn></cpn>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    // 使用组件的步骤
    // 1、注册组件构造器
    const cpn = Vue.extend({
        template: `<div><h2>组件模板</h2></div>`
    });

    // 2、注册组件
    // Vue.component(组件标签名, 组件构造器)
    Vue.component('cpn', cpn);


    const app = new Vue({
        el: '#app',
    });
</script>
```

<img src="E:\Typora\image\image-20210107195536279.png" alt="image-20210107195536279" style="zoom:150%;" />



## 2. 全局组件和局部组件

```html
<body>
    <div id="app">
        <cpn></cpn>
        <cpn></cpn>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    // 1、创建组件构造器
    const cpn = Vue.extend({
        template: `
        <div>
            <h1>我是标题</h1>
            <p>我是内容</p>
        </div>
        `
    });

    // 2、注册组件(全局组件)
    // 全局组件：可以在多个vue实例中使用
    // Vue.component('cpn', cpn);

    const app = new Vue({
        el: '#app',
        // 注册局部组件,只能在当前vue实例中使用
        components: {
            cpn,
        },
    });
</script>
```

<img src="E:\Typora\image\image-20210107204240063.png" alt="image-20210107204240063" style="zoom:150%;" />



## 3. 父子组件

组件中除了有`template`属性之外，还有`components`属性。

```html
<body>
    <div id="app">
        <cpn2></cpn2>
        
        <!-- 没有在vue实例中注册cpn1,所以不能用cpn1 -->
        <cpn1></cpn1>
    </div>
</body>

<script src="../js/vue.js"></script>
<script>
    // 1、注册组件构造器
    const cpn1 = Vue.extend({
        template: `
        <div>
            <h1>我是cpn1</h1>
        </div>
        `
    });

    const cpn2 = Vue.extend({
        template: `
        <div>
            <h1>我是cpn2</h1>
            <cpn1></cpn1>
        </div>
        `,

        // 可以在组件cpn2中注册组件
        // 我们就可以在cpn2中使用cpn1
        components: {
            cpn1,
        },
    });

    const app = new Vue({
        el: '#app',

        // 2、注册局部组件,将cpn2注册到vue实例中
        components: {
            cpn2,
        },
    });
</script>
```

![image-20210108135415225](E:\Typora\image\image-20210108135415225.png)



## 4. 组件模板抽离

```html
<body>
    <div id="app">
        <!-- 3、使用组件 -->
        <cpn></cpn>
    </div>
</body>

<template id="template-cpn">
    <div>
        <h1>我是cpn组件</h1>
    </div>
</template>

<script src="../js/vue.js"></script>
<script>
    // 1、注册组件构造器
    const cpn = Vue.extend({
        template: '#template-cpn'
    });

    const app = new Vue({
        el: '#app',

        // 2、注册局部组件
        components: {
            cpn,
        },
    });
</script>
```

![image-20210108152923027](E:\Typora\image\image-20210108152923027.png)



## 5. 组件中的data属性

**注：组件不能直接访问vue实例中的数据**。

**组件中的data必须是函数**。

```html
<body>
    <div id="app">
        <!-- 3、使用组件 -->
        <cpn></cpn>
    </div>
</body>

<template id="cpn">
    <div>
        <!-- 注意：组件内部不能只能访问vue实例中的数据 -->
        <!-- <h1>{{msg}}</h1> -->
        <h1>{{title}}</h1>
    </div>
</template>

<script src="../js/vue.js"></script>
<script>

    // 1、注册组件构造器
    const cpn = Vue.extend({
        template: '#cpn',

        // 组件中的data可以存放数据
        // 组件中的data必须是函数
        data() {
            return {
                title: 'abc',
            }
        },
    });

    const app = new Vue({
        el: '#app',
        data() {
            return {
                msg: '你好呀',
            }
        },

        // 2、注册局部组件
        components: {
            cpn,
        },
    });
</script>
```

![image-20210108180911623](E:\Typora\image\image-20210108180911623.png)



## 6. 父子组件通信

### 6.1. 父传子props

**(1) props的基本使用**

```html
<body>
    <div id="app">
        <!-- 3、使用组件 -->
        <!-- 父组件中的数据传递到子组件中, 使用v-bind来绑定属性 -->
        <!-- :movies绑定的是vue实例中的数据 -->
        <cpn :movies="movies"></cpn>
    </div>
</body>

<template id="cpn">
    <div>
        <h1>{{msg}}</h1>
        <ul>
            <li v-for="(movie, index) in movies" :key="index">
                <span>{{movie}}</span>
            </li>
        </ul>
    </div>
</template>

<script src="../js/vue.js"></script>
<script>
    // 1、注册组件构造器
    const cpn = Vue.extend({
        template: '#cpn',
        data() {
            return {
                msg: '你好呀',
            }
        },

        // props用于父组件数据 ==> 子组件
        props: {
            // 这样可以指定每个prop的类型
            movies: {
                type: Array,
                default() {
                    return [1, 2, 3];
                },
            },
        },
    });

    const app = new Vue({
        el: '#app',
        data() {
            return {
                movies: ['海王', '火影', '柯南'],
            }
        },

        // 2、在vue实例中注册局部组件
        components: {
            cpn,
        },
    });
</script>
```

![image-20210108184518532](E:\Typora\image\image-20210108184518532.png)



**子组件中props应该怎么写？？**

官网：https://cn.vuejs.org/v2/guide/components-props.html

```html
<script>
Vue.component('my-component', {
  props: {
    // 基础的类型检查 (`null` 和 `undefined` 会通过任何类型验证)
    propA: Number,
    // 多个可能的类型
    propB: [String, Number],
    // 必填的字符串
    propC: {
      type: String,
      required: true
    },
    // 带有默认值的数字
    propD: {
      type: Number,
      default: 100
    },
    // 带有默认值的对象
    propE: {
      type: Object,
      // 对象或数组默认值必须从一个工厂函数获取
      default: function () {
        return { message: 'hello' }
      }
    },
    // 自定义验证函数
    propF: {
      validator: function (value) {
        // 这个值必须匹配下列字符串中的一个
        return ['success', 'warning', 'danger'].indexOf(value) !== -1
      }
    }
  }
})
</script>
```



**(2) v-bind不支持驼峰**

```html
<body>
    <div id="app">
        <!-- 子组件中prop是myMovies, 在HTML标签中属性要写my-movies -->
        <!-- vue的v-bind不支持驼峰标识 -->
        <cpn :my-movies="myMovies"></cpn>
    </div>
</body>

<template id="cpn">
    <div>
        <h1>{{title}}</h1>
        <ul>
            <li v-for="(myMovie, index) in myMovies" :key="index">
                <span>{{myMovie}}</span>
            </li>
        </ul>
    </div>
</template>

<script src="../js/vue.js"></script>
<script>
    // 1、注册组件构造器
    const cpn = Vue.extend({
        template: '#cpn',
        data() {
            return {
                title: '这是子组件',
            }
        },

        // props用于父 ==> 子
        props: {
            myMovies: {
                type: Array,
                default() {
                    return [1, 2, 3];
                },
            },
        },
    });


    const app = new Vue({
        el: '#app',
        data() {
            return {
                myMovies: ['海王', '火影', '柯南'],
            }
        },

        // 2、在vue实例中注册局部组件
        components: {
            cpn,
        },
    });
</script>
```

![image-20210108190831619](E:\Typora\image\image-20210108190831619.png)



### 6.2. 子传父自定义事件emit()

```html
<body>
    <div id="app">
        <!-- 3、使用组件 -->
        <!-- 父组件通过 v-on 来监听子组件的自定义事件 -->
        <!-- 监听自定义的事件 btnClickLinster($event) 参数直接监听该事件 -->
        <cpn v-on:btnclick="btnClickLinster($event)"></cpn>
    </div>
</body>

<template id="cpn">
    <div>
        <h1>{{title}}</h1>
        <ul>
            <li v-for="(category, index) in categories" :key="index">
                <button @click="btnClick(category)">
                    {{category.name}}
                </button>
            </li>
        </ul>

    </div>
</template>

<script src="../js/vue.js"></script>
<script>
    // 1、注册组件构造器
    const cpn = Vue.extend({
        template: '#cpn',
        data() {
            return {
                title: '你好呀',
                categories: [
                    { id: 101, name: '热门推荐' },
                    { id: 102, name: '电脑办公' },
                ],
            }
        },

        methods: {
            // 监听按钮点击
            btnClick(category) {
                // $emit(事件名称, 参数)：自定义事件
                this.$emit('btnclick', category);
            },
        },
    });

    const app = new Vue({
        el: '#app',
        // 2、在vue实例中注册局部组件
        components: {
            cpn,
        },

        methods: {
            // 父组件监听子组件的btnClick事件
            btnClickLinster(event) {
                console.log('父组件监听到点击事件');
                console.log(event);
            },
        },
    });
</script>
```

![image-20210109134505521](E:\Typora\image\image-20210109134505521.png)



### 6.3. 父子通信结合双向绑定

```html
<body>
    <div id="app">
        <h1>父组件中的number1：{{number1}}</h1>
        </h1>
        <!-- 1、:number1="number1" ==> 父组件给子组件传值(props) -->
        <!-- 2、v-on:valuechange="valuechangeListener($event)" ==> 子组件给父组件传值(自定义事件) -->
        <cpn :number1="number1" v-on:valuechange="valuechangeListener($event)"></cpn>
    </div>
</body>

<template id="cpn">
    <div>
        <h1>子组件中props的number1: {{number1}}</h1>
        <h1>子组件中data的dnumber1: {{dnumber1}}</h1>
        <!-- v-model不要绑定props -->
        <!-- 因为props用于父组件修改子组件中的值，v-model再修改props, 代码会变得很乱 -->
        <!-- 不要绑定props, 可以绑定data或computed来代替 -->
        <!-- v-model <==> v-bind:value + v-on监听inputa事件 -->
        <input type="text" :value="dnumber1" v-on:input="valueChange($event)" />
    </div>
</template>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                number1: 10,
            }
        },
        methods: {
            // 监听自定义事件
            valuechangeListener(event) {
                // 类型转换 string --> number
                this.number1 = parseInt(event);
            },
        },
        components: {
            // 在vue实例中注册局部组件
            cpn: {
                template: '#cpn',
                data() {
                    return {
                        dnumber1: this.number1,
                    }
                },
                methods: {
                    valueChange(event) {
                        this.dnumber1 = event.target.value;
                        // 自定义事件
                        this.$emit('valuechange', this.dnumber1);
                    },
                },
                props: {
                    number1: {
                        type: Number,
                        default: 0,
                    },
                },
            },
        },
    });
</script>
```

![image-20210109144147296](E:\Typora\image\image-20210109144147296.png)



## 7. 组件访问

### 7.1. 父访问子$children/$refs

**$children和$refs会拿到所有的子组件，返回的是数组**。

**用 $refs 之前必须要先给子组件添加 ref**。

```html
<body>
    <div id="app">
        <!-- 给子组件添加ref -->
        <cpn1 ref="cpn1"></cpn1>
        <cpn2 ref="cpn2"></cpn2>
        <button @click="btnClick()">按钮</button>
    </div>
</body>

```



**$children 和 $refs 的使用**

```html
<body>
    <div id="app">
        <!-- 给子组件添加ref -->
        <cpn1 ref="cpn1"></cpn1>
        <cpn2 ref="cpn2"></cpn2>
        <button @click="btnClick()">按钮</button>
    </div>
</body>

<template id="cpn1">
    <div>
        <h1>我是cpn1</h1>
    </div>
</template>

<template id="cpn2">
    <div>
        <h1>我是cpn2</h1>
    </div>
</template>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {

            }
        },
        methods: {
            // 监听按钮点击事件
            btnClick() {
                console.log('------------this.$children------------');
                // 1、this.$children 拿到所有的子组件 ==> 返回的是数组
                console.log(this.$children);

                // this.$children要通过数组下标来子组件
                console.log(this.$children[0].title);


                // 2、用的比较多的是用this.$refs来获取指定子组件
                console.log('------------this.$refs------------');
                // this.$refs获取的也是数组
                console.log(this.$refs);

                // 但是this.$refs可以通过名字来拿取指定的子组件
                console.log(this.$refs.cpn1);
            },
        },
        components: {
            cpn1: {
                template: '#cpn1',
                data() {
                    return {
                        title: '我是cpn1的标题',
                    }
                },
            },
            cpn2: {
                template: '#cpn2',
            },
        },
    });
</script>
```

![image-20210109155947376](E:\Typora\image\image-20210109155947376.png)



### 7.2. 子访问父$parent/$root

`$parent`：拿到的是当前组件的上级组件。

`$root`：拿到的是根组件，一般是我们的vue实例。

```html
<body>
    <div id="app">
        <cpn></cpn>
    </div>
</body>

<template id="cpn">
    <div>
        <h1>我是组件cpn</h1>
        <!-- 在组件cpn中使用组件<cpn1></cpn1> -->
        <cpn1></cpn1>
    </div>
</template>

<template id="cpn1">
    <div>
        <h1>我是组件cpn1</h1>
        <button @click="btnClick()">按钮</button>
    </div>
</template>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data() {
            return {
                name: '我是vue实例',
            }
        },
        components: {
            cpn: {
                template: '#cpn',
                data() {
                    return {
                        name: '我是cpn',
                    }
                },
                components: {
                    cpn1: {
                        template: '#cpn1',
                        methods: {
                            btnClick() {
                                console.log('----------------this.$parent----------------');
                                console.log(this.$parent);
                                console.log(this.$parent.name);

                                console.log('----------------this.$root----------------');
                                console.log(this.$root);
                                console.log(this.$root.name);
                            },
                        },
                    },
                },
            },
        },
    });
</script>
```

![image-20210109172859739](E:\Typora\image\image-20210109172859739.png)





## 8. 插槽slot

### 8.1. 插槽的基本使用

**使用插槽的目的：是为了让我们的组件有更强的扩展性**！

**如果组件中只有一个插槽，那么所有的标签都会插入到这个插槽**。

```html
<body>
    <div id="app">
        <!-- 如果组件中只有一个插槽那么所有的标签都会插入到这个插槽中 -->
        <cpn>
            <h1>使用插槽1</h1>
            <i>哈哈哈</i>
            <span>呵呵呵</span>
        </cpn>
    </div>
</body>

<template id="cpn">
    <div>
        <h1>我是组件cpn</h1>
        <slot></slot>
    </div>
</template>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        components: {
            cpn: {
                template: '#cpn',
            },
        },
    });
</script>
```

![image-20210109175816820](E:\Typora\image\image-20210109175816820.png)



### 8.2. 具名插槽

**具名插槽：具名插槽只会替换指定名字的插槽**。

```html
<body>
    <div id="app">
        <cpn>
            <!-- 具名插槽只会替换指定名字的插槽 -->
            <span slot="left">span使用左边</span>
        </cpn>
    </div>
</body>

<template id="cpn">
    <div>
        <h1>我是组件cpn</h1>
        <slot name="left"><span>左边</span></slot>
        <slot name="center"><span>中间</span></slot>
        <slot name="right"><span>右边</span></slot>
    </div>
</template>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        components: {
            cpn: {
                template: '#cpn',
            },
        },
    });
</script>

```

![image-20210109181401543](E:\Typora\image\image-20210109181401543.png)





### 8.3. 作用域插槽

**作用域插槽：作用在父组件中用到子组件中的数据**。

```html
<body>
    <div id="app">
        <cpn></cpn>

        <cpn>
            <!-- v-slot:插槽的名字="存放数据的名字" -->
            <!-- 这样就可以拿到子组件中的数据了 -->
            <template v-slot:up="up">
                <span v-for="(language, index) in up.planguages" :key="index">
                    <i>{{language}}</i>
                </span>
            </template>
            <template v-slot:down="down">
                <table>
                    <tr>
                        <td v-for="(movie, index) in down.movies" :key="index">
                            {{movie}}
                        </td>
                    </tr>
                </table>
            </template>
        </cpn>
    </div>
</body>

<template id="cpn">
    <div>
        <h1>我是组件cpn</h1>

        <!-- 在slot中可以自定义属性，这里的 :PLanguages 是自己起的名字 -->
        <slot :PLanguages="PLanguages" name="up">
            <ul>
                <li v-for="(language, index) in PLanguages" :key="index">
                    <span>{{language}}</span>
                </li>
            </ul>
        </slot>

        <!-- 展示movies -->
        <slot :movies="movies" name="down">
            <ul>
                <li v-for="(movie, index) in movies" :key="index">
                    <span>{{movie}}</span>
                </li>
            </ul>
        </slot>
    </div>
</template>

<script src="../js/vue.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        components: {
            cpn: {
                template: '#cpn',
                data() {
                    return {
                        PLanguages: ['Java', 'C', 'C++', 'Python', 'JavaScript'],
                        movies: ['海王', '火影', '柯南'],
                    }
                },
            },
        },
    });
</script>
```

![image-20210109212430092](E:\Typora\image\image-20210109212430092.png)



# 三、模块化开发

## 1. vue/cli

### 1.1. 基本介绍

`vue-cli3`与`vue-cli2`有很大的区别：

- `vue-cli3`是基于`webpack4`打造，`vue-cli2`还是`webpack3`。
- `vue-cli3`的设计原则是**"0配置"**，移除配置文件根目录下的，`build`和`config`等目录。
- `vue-cli3`提供了`vue ui`命令，提供了可视化配置，更加人性化。
- 移除了`static`文件夹，新增了`public`文件夹，并且`index.html`移动到`public`中。

### 1.2. vue/cli2

**(1) 安装和使用vue/cli2**

```shell
# 1、安装vue/cli2
npm install -g @vue/cli-init

# 2、创建项目 vue init webpack 项目名
vue init webpack 001-vuecli2test
```



**package.json**：

```json
// 3、package.json
{
  "name": "001-vuecli2test",
  "version": "1.0.0",
  "description": "test vue cli2",
  "author": "Ringo",
  "private": true,
  "scripts": {
    "dev": "webpack-dev-server --inline --progress --config build/webpack.dev.conf.js",
      
    // npm run start <==> npm run dev 启动项目
    "start": "npm run dev",
    
    // npm run build：打包命令 
    "build": "node build/build.js"
  },
}
```





**vuecli2项目结构**：

![image-20210110124213507](E:\Typora\image\image-20210110124213507.png)



### 1.3. vue/cli3

**(1)安装和使用vue/cli3**

```shell
# 1、安装vue/cli3
npm install -g @vue/cli

# 2、查看vue/cli的版本
vue --version

# 3、vue/cli3初始化项目
# vue create 项目名
vue create hello-world

# 4、选择手动安装插件/使用默认选择的插件
? Please pick a preset: (Use arrow keys)
> coderymy (babel)   # 之前已经保存过的
  default (babel, eslint) # vue/cli3默认的插件babel和eslint
  Manually select features # 手动添加插件

# 5、手动选择插件
? Please pick a preset: Manually select features
? Check the features needed for your project:
>(*) Babel       
 ( ) TypeScript
 ( ) Progressive Web App (PWA) Support
 ( ) Router
 ( ) Vuex
 ( ) CSS Pre-processors
 ( ) Linter / Formatter
 ( ) Unit Testing
 ( ) E2E Testing                                                                              
 # 6、配置文件选择
 ? Please pick a preset: Manually select features
 ? Check the features needed for your project: Babel
 ? Where do you prefer placing config for Babel, ESLint, etc.? (Use arrow keys)
 > In dedicated config files # 可以选择一个专门的配置文件
 In package.json  # 也可以选择放在package.json中
 
 # 7、之前的配置是否保存？
 # 如果选择保存要删除可以去C:\Users\14666\.vuerc文件中删除配置
 ? Please pick a preset: Manually select features
 ? Check the features needed for your project: Babel
 ? Where do you prefer placing config for Babel, ESLint, etc.? In dedicated config files
 ? Save this as a preset for future projects? (y/N)   # 选择之前的配置是否保存
 
 # 8、运行vue/cli3项目
 npm run serve
```

**`vue/cli`官网：https://cli.vuejs.org/zh/guide/**



### 1.4. vue/cli3配置

> UI界面的启动

```shell
# 启动 vue ui
vue ui
```

> 导入`vue/cli3`创建的项目，使用`dashboard`来管理项目。

![vue ui](https://img-blog.csdnimg.cn/20200724150705368.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)



> 项目导入成功后，就可以对项目配置进行管理了。

![Dashboard管理项目](C:\Users\14666\AppData\Roaming\Typora\typora-user-images\image-20200724151008199.png)



> 2.2. 创建vue.config.js

`vue.config.js` 是一个可选的配置文件，如果项目的 (和 `package.json` 同级的) 根目录中存在这个文件，那么它会被 `@vue/cli-service` 自动加载。你也可以使用 `package.json` 中的 `vue` 字段，但是注意这种写法需要你严格遵照 JSON 的格式来写。

**`vue.config.js`配置官网地址：https://cli.vuejs.org/zh/config/#vue-config-js**

## 2.箭头函数

> 箭头函数基本使用

```html
<script>
    // 1.定义函数：function
    const method = function () {

    }

    // 2.对象字面量中定义函数
    const obj = {
        fun1: function () {

        },
        fun2() {

        }
    }

    // 3.ES6中箭头函数
    const fun3 = (parm1, parm2) => {
        return parm1 + parm2;
    }
    console.log(fun3(1, 2)); // 3
</script>
```

> 箭头函数参数和返回值

```html
<script>
    // 1.参数问题
    // 1.1.放入两个参数
    const sum = (num1, num2) => {
        return num1 + num2;
    }

    // 1.2.放入一个参数
    const power = num => {
        return num * num;
    }
    console.log(power(5)); // 25

    // 2.函数代码块中只有一行代码
    // JavaScript会将这行代码作为箭头函数的返回值
    const fun1 = num => num * num 
    console.log(fun1(9)); // 81
</script>
```

> 箭头函数this的指向

```html
<script>
    // 箭头函数中this的使用
    // 什么时候使用箭头函数？当我们把函数当做参数传到另一个函数的时候

    /**
     * 问题：箭头函数中的this是如何查找的？
     * 答案：箭头函数中的this引用的是最近作用域中的this！向外层作用域中一层层查找this,直到有this的定义。
    */
    const obj = {
        fileds: {
            name: 'zs'
        },
        get() {
            setTimeout(function () {
                setTimeout(function () {
                    console.log(this); // Window
                });
                setTimeout(() => {
                    console.log(this); // window
                })
            });
            setTimeout(() => {
                setTimeout(function () {
                    console.log(this); // window
                });
                setTimeout(() => {
                    console.log(this); // obj对象
                })
            });
        }
    }
    obj.get();
</script>
```



## 3.vue-router

### 3.1.安装router

**(1) 手动安装router**

> 安装

```shell
# 1、安装
npm install vue-router --save

# 2、导入路由对象并且调用Vue.use(VueRouter)

# 3、创建路由实例，并且传入路由映射配置

# 4、在Vue实例中挂载创建的路由实例
```

> router在index.js中配置

```javascript
// 先在src目录下创建router文件夹，并创建index.js文件

// 1.导入vue和vue-router
import Vue from 'vue'
import VueRouter from 'vue-router'

// 2.使用VueRouter
Vue.use(VueRouter)

// 3.配置路径和组件之间的映射关系
const routes = [
    {
        path: '/about',
        name: 'About',
        component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
})

export default router
```

> 将router挂载到`vue`实例，在main.js中配置

```javascript
import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false

new Vue({
  // 将router挂载到vue实例中
  router,
  render: h => h(App)
}).$mount('#app')

```

> 在`App.vue`中添加`<router-view/>`就可以显示路由映射的组件了

```html
<template>
  <div id="app">
     <!-- <router-link to="/about">About</router-link> -->
     <router-view/>
  </div>
</template>
```



**(2) 在vue/cli中安装router**

注意：为了让一个 `CLI `插件在` Vue CLI `项目中被正常使用，它必须遵循 `vue-cli-plugin-` 或者 `@scope/vue-cli-plugin-` 这样的命名惯例。这样你的插件才能够：

- 被 `@vue/cli-service` 发现；
- 被其他开发者通过搜索发现；
- 通过 `vue add ` 或者 `vue invoke ` 安装。
- 确保插件的名字是正确的，否则他将不能通过 `vue add` 安装并且不能在 UI 插件中搜索得到！

```shell
# 安装vue-router插件
vue add router # 该命令执行后以上一系列操作都会自动创建！
```



### 3.2.router-link其他属性

> tag属性

```html
<template>
  <div id="app">
    <div id="nav">
      <!-- router-link 默认渲染出来的是<a></a>
           使用tag属性就可以改变默认渲染出来的样式
       -->
      <router-link to="/" tag="button">Home</router-link> |
      <router-link to="/about" tag="button">About</router-link>
    </div>
    <router-view/>
  </div>
</template>
```

> router-link-exact-active

![router-link-exact-active](https://img-blog.csdnimg.cn/20200724175402439.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

**CSS样式如下**

```html
<style>
/*
  router-link 中有默认class值为router-link-exact-active，
  点击哪个router-link则router-link-exact-active就会动态的赋值到哪个router-link上，
  可以用来设置点击切换颜色的功能
 */
.router-link-exact-active {
  color: red;
}
</style>
```

### 3.3.代码路由跳转

```vue
<template>
  <div id="app">
    <div id="nav">
      <button v-on:click="homeClick">Home</button>
      <button v-on:click="aboutClick">About</button>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  methods: {
    homeClick() {
      console.log("点击跳转到Home");
      // vue的所有component中都有$router属性！
      this.$router.push("/").catch((err) => {
        err;
      });
    },
    aboutClick() {
      console.log("点击跳转到About");
      this.$router.push("/about").catch((err) => {
        err;
      });
    },
  },
};
</script>
```

### 3.4.动态路由

我们经常需要把某种模式匹配到的所有路由，全都映射到同个组件。例如，我们有一个 `User` 组件，对于所有 ID 各不相同的用户，都要使用这个组件来渲染。那么，我们可以在 `vue-router` 的路由路径中使用“动态路径参数”(dynamic segment) 来达到这个效果：

```javascript
const router = new VueRouter({
  routes: [
    // 动态路径参数 以冒号开头
    { path: '/user/:id', component: User }
  ]
```

可以在一个路由中设置多段“路径参数”，对应的值都会设置到 `$route.params` 中。例如：

| 模式                          | 匹配路径            | $route.params                          |
| ----------------------------- | ------------------- | -------------------------------------- |
| /user/:username               | /user/evan          | `{ username: 'evan' }`                 |
| /user/:username/post/:post_id | /user/evan/post/123 | `{ username: 'evan', post_id: '123' }` |

> index.js中配置动态路由

```javascript
  {
    path: '/user/:id',
    name: 'User',
    component: () => import(/* webpackChunkName: "user" */'../components/User.vue')
  }
```

> 路由的跳转

```vue
<template>
  <div id="app">
    <div id="nav">
      <!-- 使用计算属性来v-bind路径 -->
      <router-link :to="userPath">USER</router-link>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  data() {
    return {
      userId: 1,
    };
  },
  computed: {
    userPath() {
      return "/user/" + this.userId;
    },
  },
};
</script>
```

> 使用$route.params，在路由跳转到User.vue后拿到数据

```vue
<template>
  <div>
    <h1>我们的User.vue组件</h1>
    <!-- 路由跳转后拿到userId -->
    <h1>userId: {{userId}}</h1>
  </div>
</template>

<script>
export default {
  computed: {
    userId() {
      /**
       * 拿到$route.params对象 router中index.js中 动态路由 path: '/user/:id',
       * 这里就要使用id,this.$route.params.id
       */
      return this.$route.params.id;
    },
  },
};
</script>
```

![image-20210110154240702](E:\Typora\image\image-20210110154240702.png)



### 3.5.路由的懒加载

当打包构建应用时，JavaScript 包会变得非常大，影响页面加载。如果我们能把不同路由对应的组件分割成不同的代码块，然后当路由被访问的时候才加载对应组件，这样就更加高效了。

```javascript
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
```

### 3.6.路由的嵌套使用

实际生活中的应用界面，通常由多层嵌套的组件组合而成。同样地，URL 中各段动态路径也按某种结构对应嵌套的各层组件，例如：

```text
/user/foo/profile                     /user/foo/posts
+------------------+                  +-----------------+
| User             |                  | User            |
| +--------------+ |                  | +-------------+ |
| | Profile      | |  +------------>  | | Posts       | |
| |              | |                  | |             | |
| +--------------+ |                  | +-------------+ |
+------------------+                  +-----------------+
```

借助 `vue-router`，使用嵌套路由配置，就可以很简单地表达这种关系。

> 嵌套路由的使用

`App.vue`中`<router-view>`的是最顶层的出口，渲染最高级路由匹配到的组件。同样地，一个被渲染组件同样可以包含自己的嵌套 。**例如，在 `User.vue` 组件的模板添加一个 `<router-view>`**。

```javascript
  {
    path: '/user/:id',
    name: 'User',
    component: () => import(/* webpackChunkName: "user" */ '../components/User.vue'),
    children: [
      {
        // 当 /user/:id/profile 匹配成功
        // Profile 会被渲染在 User 的 <router-view> 中
        path: 'profile',
        component: () => import(/* webpackChunkName: "profile" */ '../components/Profile.vue')
      }
    ]
  }
```

**要注意，以 `/` 开头的嵌套路径会被当作根路径。 这让你充分的使用嵌套组件而无须设置嵌套的路径。**

你会发现，`children` 配置就是像 `routes` 配置一样的路由配置数组，所以呢，你可以嵌套多层路由。

> 路由嵌套的默认路径

```javascript
  {
    path: '/home',
    name: 'Home',
    component: Home,
    children: [
      {
        // 嵌套的默认路径显示News.vue
        path: '',
        component: () => import('../components/News.vue')
      },
      {
        // /home/news显示News.vue
        path: 'news',
        component: () => import('../components/News.vue')
      },
      {
        // /home/message显示Message.vue
        path: 'message',
        component: () => import('../components/Message.vue')
      },
    ]
  }
```

### 3.7.路由组件传参

传递参数主要有两种类型：`params`和`query`。

**`params`的类型(动态路由)：**

- 配置路由格式：`/router/:id`。
- 传递的方式：在path后面跟上对应的值。
- 传递后形成的路径：`/router/123`，`/router/abc`。

**`query`的类型：**

- 配置路由格式：`/router`，也就是普通配置。
- 传递的方式：在对象中使用`query`的`key`作为传递方式。
- 传递后形成的路径：`/router?id=123`，`/router?id=abc`。

> 路由跳转传参

```vue
<template>
  <div id="app">
    <div id="nav">
       <!-- v-bind单向绑定 -->
      <router-link :to="userProfile">档案</router-link>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  data() {
    return {
      userProfile: {
        path: '/profile',
        // url就会变成这样 http://localhost:8080/profile?name=RingTangs&age=18&height=1.88
        query: {
          name: 'RingTangs',
          age: 18,
          height: 1.88
        }
      } 
    }
  }
</script>
```

**当路由发生跳转的时候浏览器的地址栏URL就会变成`http://localhost:8080/profile?name=RingTangs&age=18&height=1.88`。**

> **`$route.query`**路由跳转后取出参数的值

```vue
<template>
  <div>
    <h1>Profile.vue</h1>
    <!-- 路由跳转后取出参数的值 -->
    <h2>{{$route.query.name}}</h2>
    <h2>{{$route.query.age}}</h2>
    <h2>{{$route.query.height}}</h2>
  </div>
</template>
```

### 3.8.$router和$route的区别

![区别](https://img-blog.csdnimg.cn/20200725170737664.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)



路由的嵌套，路径匹配成功可以拿到 **当前活跃路由(this.$route)** 的全部信息.

```javascript
const routes = [{
  {
    path: '/home',
    name: 'Home',
    component: () => import('@/views/Home.vue'),
    meta: {
      title: '首页',
    },
    children: [{
      // 这里不加 '/' 才会匹配 '/home/test1' 路由的嵌套
      // 这里加 '/' 匹配的是 '/test1' 路由；加不加 '/' 都可以拿到父子组件的 title
      // 嵌套路由中 就可以在 Home 页面中添加 <router-view /> 
      // 这样就可以在 Home 页面中显示 Test1 组件了
      path: 'test1',   
      name: 'Test1',
      component: () => import('@/views/Test1.vue'),
      meta: {
        title: '测试1',
      }
    }],
  },
]
```

![image-20210223185557337](E:\Typora\image\image-20210223185557337.png)



### 3.9.导航守卫

**“导航”表示路由正在发生改变。正如其名，`vue-router` 提供的导航守卫主要用来通过跳转或取消的方式守卫导航。**

```js
const router = new VueRouter({ ... })

router.beforeEach((to, from, next) => {
  // ...
})
```

**全局前置导航守卫`beforeEach()`方法接收三个参数：**

- **`to: Route`**: 即将要进入的目标 路由对象。
- **`from: Route`**: 当前导航正要离开的路由。
- **`next: Function`**: 一定要调用该方法来 **resolve** 这个钩子。执行效果依赖 `next` 方法的调用参数。
  - **`next()`**: 进行管道中的下一个钩子。如果全部钩子执行完了，则导航的状态就是 **confirmed** (确认的)。
  - **`next(false)`**: 中断当前的导航。如果浏览器的 URL 改变了 (可能是用户手动或者浏览器后退按钮)，那么 URL 地址会重置到 `from` 路由对应的地址。
  - **`next('/')` 或者 `next({ path: '/' })`**: 跳转到一个不同的地址。当前的导航被中断，然后进行一个新的导航。你可以向 `next` 传递任意位置对象，且允许设置诸如 `replace: true`、`name: 'home'` 之类的选项以及任何用在 [`router-link` 的 `to` prop](https://router.vuejs.org/zh/api/#to) 或 [`router.push`](https://router.vuejs.org/zh/api/#router-push) 中的选项。
  - **`next(error)`**: (2.4.0+) 如果传入 `next` 的参数是一个 `Error` 实例，则导航会被终止且该错误会被传递给 [`router.onError()`](https://router.vuejs.org/zh/api/#router-onerror) 注册过的回调。

> 题目：SPA页面如何动态修改网页的标题？

```javascript
// 1、在配置路由的时候加上meta元素
  {
    path: '/user',
    name: 'User',
    meta: {
      title: 'User'
    },
    component: () => import('../components/User.vue')
  }

// 2、使用全局前置导航守卫
// 在router目录下的index.js中使用beforeEach()即可！
router.beforeEach((to, from, next) => {
  console.log("to", to);
  console.log("from", from);
  // 在配置路由的时候可以加上meta属性
  // 将meta中的title赋值给DOM元素中的title
  document.title = to.meta.title;
  next();
})
```

![image-20210222150840593](E:\Typora\image\image-20210222150840593.png)

**也可以注册全局后置钩子，然而全局前置守卫不同的是，这些钩子不会接受 `next` 函数也不会改变导航本身**

```js
router.afterEach((to, from) => {
  // ...
})
```



### 3.10. keep-alive

- **keep-alive是vue内置的一个组件，可以使得被包含的组件保留状态，避免重新渲染**。
- **router-view也是一个组件，如果直接被包在keep-alive里面，所有路径匹配到的视图组件都会被缓存**。



# 4.vuex

## 4.1.vuex是什么？

Vuex 是一个专为 Vue.js 应用程序开发的**状态管理模式**。它采用集中式存储管理应用的所有组件的状态，并以相应的规则保证状态以一种可预测的方式发生变化。**一句话，Vuex就是一个响应式的全局状态管理工具。**

<img src="https://vuex.vuejs.org/vuex.png" alt="vuex" style="zoom:150%;" />

> 什么情况下我应该使用 Vuex？

Vuex 可以帮助我们管理共享状态，并附带了更多的概念和框架。这需要对短期和长期效益进行权衡。

如果您不打算开发大型单页应用，使用 Vuex 可能是繁琐冗余的。确实是如此——如果您的应用够简单，您最好不要使用 Vuex。一个简单的 [store 模式](https://cn.vuejs.org/v2/guide/state-management.html#简单状态管理起步使用)就足够您所需了。但是，如果您需要构建一个中大型单页应用，您很可能会考虑如何更好地在组件外部管理状态，Vuex 将会成为自然而然的选择。

> store仓库

每一个 Vuex 应用的核心就是 store（仓库）。“store”基本上就是一个容器，它包含着你的应用中大部分的**状态 (state)**。Vuex 和单纯的全局对象有以下两点不同：

- **Vuex 的状态存储是响应式的**。当 Vue 组件从 store 中读取状态的时候，若 store 中的状态发生变化，那么相应的组件也会相应地得到高效更新。

- 你不能直接改变 store 中的状态。改变 store 中的状态的唯一途径就是显式地**提交 (commit) mutation**。这样使得我们可以方便地跟踪每一个状态的变化，从而让我们能够实现一些工具帮助我们更好地了解我们的应用。

> Vuex管理什么状态？有什么状态是需要我们在多个组件间共享的呢？

- 用户的登录状态、用户名称、头像、地址位置信息等等。
- 商品的收藏、购物车中的物品等等。
- 这些状态信息，我们都可以放在统一的地方，对它进行保存和管理，而且它们还是**响应式**的！

## 4.2.vuex的基础使用

> store目录下index.js中state定义count变量，mutations中用来修改state的变量

```javascript
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    // state定义变量
    count: 0
  },
  mutations: {
    // mutations用来修改变量
    increment(state) {
      state.count++;
    }
  },
  actions: {
  },
  modules: {
  }
})
```

> 可以在Count.vue组件中通过 `store.state` 来获取状态对象，以及通过 `store.commit` 方法触发状态变更

```vue
<template>
  <div>
    <button @click="increment">计数器加1</button>
  </div>
</template>
<script>
export default {
  methods: {
    increment() {
      // 通过提交mutations来修改state的状态
      this.$store.commit("increment");
      console.log(this.$store.state.count);
    },
  },
};
</script>
```

## 4.3.getters(计算属性)

Vuex 允许我们在 store 中定义“getter”（可以认为是 store 的计算属性）。就像计算属性一样，getter 的返回值会根据它的依赖被缓存起来，且只有当它的依赖值发生了改变才会被重新计算。

```javascript
export default new Vuex.Store({
  state: {
    todos: [
      { id: 1, done: true },
      { id: 2, done: false },
      { id: 3, done: true }
    ]
  },
  getters: {
    // Getter 接受 state 作为其第一个参数
    doneTodos: state => {
      return state.todos.filter(todo => todo.done);
    }
  }
})
```

> 通过属性访问getters

```vue
<template>
  <div id="app">
      <h2>-------App内容getters相关信息-------</h2>
      <!-- [ { "id": 1, "done": true }, { "id": 3, "done": true } ] -->
      <h3>{{$store.getters.doneTodos}}</h3>
  </div>
</template>
```

## 4.4.mutations(同步)

可以向 `store.commit` 传入额外的参数，即 mutation 的 **载荷（payload）**：

```js
mutations: {
  increment (state, n) {
    state.count += n
  }
}
```

```js
store.commit('increment', 10)
```

在大多数情况下，载荷应该是一个对象，这样可以包含多个字段并且记录的 mutation 会更易读：

```javascript
mutations: {
  increment (state, payload) {
    state.count += payload.amount
  }
}
```

```js
store.commit('increment', {
  amount: 10
})
```

> 使用常量替代 Mutation 事件类型

使用常量替代 mutation 事件类型在各种 Flux 实现中是很常见的模式。这样可以使 linter 之类的工具发挥作用，同时把这些常量放在单独的文件中可以让你的代码合作者对整个 app 包含的 mutation 一目了然：

```javascript
// mutation-types.js
export const INCREMENT = 'INCREMENT';
```

```js
// store.js
import Vuex from 'vuex'
import { SOME_MUTATION } from './mutation-types'

const store = new Vuex.Store({
  state: { ... },
  mutations: {
    // 我们可以使用 ES2015 风格的计算属性命名功能来使用一个常量作为函数名
    INCREMENT(state, n) {
      state.count += n;
    }
  }
})
```

**用不用常量取决于你——在需要多人协作的大型项目中，这会很有帮助。但如果你不喜欢，你完全可以不这样做。**

> Mutation 需遵守 Vue 的响应规则

既然 **Vuex 的 store 中的状态是响应式的**，那么当我们变更状态时，监视状态的 Vue 组件也会自动更新。这也意味着 Vuex 中的 mutation 也需要与使用 Vue 一样遵守一些注意事项：

1. 最好提前在你的 store 中初始化好所有所需属性。
2. 当需要在对象上添加新属性时，你应该

- 使用 `Vue.set(obj, 'newProp', 123)`, 或者
- 以新对象替换老对象。例如，利用[对象展开运算符](https://github.com/tc39/proposal-object-rest-spread)我们可以这样写：

```js
state.obj = { ...state.obj, newProp: 123 }
```



**案例：**

```javascript
// store.js中state定义好student对象
state: {
  student: {
    id: 1,
    name: 'RingTangs',
    age: 18
  }
}
```

```javascript
// store.js
import Vue from 'vue'
import Vuex from 'vuex'

mutations: {
  addProp(state) {
    // 给student对象添加新的属性
    // 第一种方式：Vue.set(state.student, 'address', '北京');
    // 第二种方式
    state.student = { ...state.student, address: '北京' }
  }
}
```

> Mutation 必须是同步函数

```js
mutations: {
  someMutation (state) {
    api.callAsyncMethod(() => {
      state.count++
    })
  }
}
```

现在想象，我们正在 debug 一个 app 并且观察 devtool 中的 mutation 日志。每一条 mutation 被记录，devtools 都需要捕捉到前一状态和后一状态的快照。然而，在上面的例子中 mutation 中的异步函数中的回调让这不可能完成：因为当 mutation 触发的时候，回调函数还没有被调用，devtools 不知道什么时候回调函数实际上被调用——实质上任何在回调函数中进行的状态的改变都是不可追踪的。

## 4.5.actions(异步)

Action 类似于 mutation，不同在于：

- Action 提交的是 mutation，而不是直接变更状态。
- Action 可以包含任意异步操作。

> actions的基本使用

```javascript
// export const INCREMENT = 'INCREMENT';
export const INCREMENT = 'INCREMENT';
```

```javascript
// store.js
import { INCREMENT } from './mutation-types.js'

state: {
  count: 0
},
mutations: {
  INCREMENT(state, n) {
    state.count += n;
  },
},
actions: {
  increment(context) {
    context.commit('INCREMENT', 10);
  }
}    
```

Action 函数接受一个与 store 实例具有相同方法和属性的 context 对象，因此你可以调用 `context.commit` 提交一个 mutation，或者通过 `context.state` 和 `context.getters` 来获取 state 和 getters。

实践中，我们会经常用到 ES2015 的 **参数解构**来简化代码（特别是我们需要调用 `commit` 很多次的时候）：

```javascript
actions: {
  increment({ commit }) {
    commit('INCREMENT', 10);
  }
},
```

> 分发actions

Action 通过 `store.dispatch` 方法触发：

```javascript
this.$store.dispatch('increment');
```

乍一眼看上去感觉多此一举，我们直接分发 mutation 岂不更方便？实际上并非如此，还记得 **mutation 必须同步执行**这个限制么？Action 就不受约束！我们可以在 action 内部执行**异步**操作：

```js
actions: {
  incrementAsync({ commit }, n) {
    // setTimeout() 1S后才执行commit()方法
    setTimeout(() => {
      commit('INCREMENT', n);
    }, 1000);
  }
}
```

```javascript
// 分发actions
this.$store.dispatch("incrementAsync", 8);
```

> 组合actions

Action 通常是异步的，那么如何知道 action 什么时候结束呢？更重要的是，我们如何才能组合多个 action，以处理更加复杂的异步流程？

首先，你需要明白 `store.dispatch` 可以处理被触发的 action 的处理函数返回的 Promise，并且 `store.dispatch` 仍旧返回 Promise：

```js
actions: {
  actionA ({ commit }) {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        commit('someMutation')
        resolve()
      }, 1000)
    })
  }
}
```

现在你可以：

```java
store.dispatch('actionA').then(() => {
  // ...
})
```

最后，如果我们利用 [async / await](https://tc39.github.io/ecmascript-asyncawait/)，我们可以如下组合 action：

```javascript
// 假设 getData() 和 getOtherData() 返回的是 Promise

// await + Promise
actions: {
  async actionA ({ commit }) {
    commit('gotData', await getData())
  },
  async actionB ({ dispatch, commit }) {
    await dispatch('actionA') // 等待 actionA 完成
    commit('gotOtherData', await getOtherData())
  }
}
```

**一个 `store.dispatch` 在不同模块中可以触发多个 action 函数。在这种情况下，只有当所有触发函数完成后，返回的 Promise 才会执行。**

## 4.6.modules

由于使用单一状态树，应用的所有状态会集中到一个比较大的对象。当应用变得非常复杂时，store 对象就有可能变得相当臃肿。

为了解决以上问题，Vuex 允许我们将 store 分割成**模块（module）**。**每个模块拥有自己的 state、mutation、action、getter、甚至是嵌套子模块——从上至下进行同样方式的分割：**

```js
const moduleA = {
  state: () => ({ ... }),
  mutations: { ... },
  actions: { ... },
  getters: { ... }
}

const moduleB = {
  state: () => ({ ... }),
  mutations: { ... },
  actions: { ... }
}

const store = new Vuex.Store({
  modules: {
    a: moduleA,
    b: moduleB
  }
})

store.state.a // -> moduleA 的状态
store.state.b // -> moduleB 的状态
```

> 模块的局部状态

对于模块内部的 mutation 和 getter，接收的第一个参数是**模块的局部状态对象**。

```js
const moduleA = {
  state: () => ({
    count: 0
  }),
  mutations: {
    increment (state) {
      // 这里的 `state` 对象是模块的局部状态
      state.count++
    }
  },

  getters: {
    doubleCount (state) {
      return state.count * 2
    }
  }
}
```

同样，对于模块内部的 action，局部状态通过 `context.state` 暴露出来，根节点状态则为 `context.rootState`：

```js
const moduleA = {
  // ...
  actions: {
    incrementIfOddOnRootSum ({ state, commit, rootState }) {
      if ((state.count + rootState.count) % 2 === 1) {
        commit('increment')
      }
    }
  }
}
```

对于模块内部的 getter，根节点状态会作为第三个参数暴露出来：

```js
const moduleA = {
  // ...
  getters: {
    sumWithRootCount (state, getters, rootState) {
      return state.count + rootState.count
    }
  }
}
```

## 4.7.vuex项目目录结构

Vuex 并不限制你的代码结构。但是，它规定了一些需要遵守的规则：

1. 应用层级的状态应该集中到单个 store 对象中。
2. 提交 **mutation** 是更改状态的唯一方法，并且这个过程是同步的。
3. 异步逻辑都应该封装到 **action** 里面。

只要你遵守以上规则，如何组织代码随你便。如果你的 store 文件太大，只需将 action、mutation 和 getter 分割到单独的文件。

对于大型应用，我们会希望把 Vuex 相关代码分割到模块中。下面是项目结构示例：

```bash
├── index.html
├── main.js
├── api
│   └── ... # 抽取出API请求
├── components
│   ├── App.vue
│   └── ...
└── store
    ├── index.js          # 我们组装模块并导出 store 的地方
    ├── actions.js        # 根级别的 action
    ├── mutations.js      # 根级别的 mutation
    └── modules
        ├── cart.js       # 购物车模块
        └── products.js   # 产品模块
```

## 4.8. 前端可以存放数据的地方

前端数据有几个可以存放的地方：

1. sessionStorage
2. localStorage
3. vuex状态管理（响应式）。



# 5.axios

## 5.1.vue整合axios

```shell
# 1、安装axios
npm install axios --save

# 2、将下面代码加入js文件
import Vue from 'vue'
import axios from 'axios'
```

> 使用axios发送网络请求

```javascript
// 设置全局默认的URL
axios.defaults.baseURL = 'http://123.207.32.32:8000/'
// 设置默认的超时时间
axios.defaults.timeout = 5000

axios({
  url: '/home/multidata',
  method: 'get',
  params: {
    type: 'pop',
    page: 1
  }
}).then(ret => console.log(ret));
```

## 5.2.axios的实例和封装

> 创建axios实例

```javascript
// 创建axios实例
const instance1 = axios.create({
  baseURL: 'http://123.207.32.32:8000/',
  timeout: 5000
})

// 使用axios实例
instance1({
  url: '/home/multidata'
}).then(ret => console.log(ret))

instance1({
  url: '/home/data',
  params: {
    type: 'pop',
    page: 1
  }
}).then(ret => console.log(ret))
```

> 封装axios

```javascript
// request.js
import axios from 'axios'

export function request(config) {
    // 1.创建axios实例
    const instance = axios.create({
        baseURL: 'http://123.207.32.32:8000/',
        timeout: 5000
    });

    // 2.发送网络请求
    // instance本身返回的就是Promise
    return instance(config);
}
```

```java
// 使用封装的axios实例
import { request } from './network/request'
const config = {
  url: '/home/multidata',
  type: 'get'
}
request(config).then(ret => console.log(ret))
```

## 5.3.axios拦截器

```javascript
// request.js
import axios from 'axios'

export function request(config) {
    // 1.创建axios实例
    const instance = axios.create({
        baseURL: 'http://123.207.32.32:8000/',
        timeout: 5000
    });

    /**
     * 什么时候需要使用请求拦截器呢？
     * 1.比如config中的一些信息符合服务器的要求。
     * 2.比如每次发送网络请求时都希望在界面中显示一个网络请求的图标。
     * 3.某些网络请求比如token，必须携带一些特殊的信息。
     */
    // 实例请求拦截器
    instance.interceptors.request.use(config => {
        console.log(config);
        // 拦截了请求一定要把请求返回出去
        return config;
    }, err => {
        console.log(err);
    })

    // 实例响应拦截器
    instance.interceptors.response.use(ret => {
        console.log(ret);
        return ret;
    }, err => {
        console.log(err);
    })

    // 2.发送网络请求
    // instance本身返回的就是Promise
    return instance(config);
}
```



# 四、vue相关

## 1. font-awesome

```shell
1、在项目中安装fontawesome：npm install font-awesome --save

2、在main.js中引入相关的文件：import 'font-awesome/css/font-awesome.min.css'

3、在需要使用的地方，class=“fa 类名”，请注意一定要在前面添加fa类，否则无法正常显示
```



## 2. vscode

### 2.1. 取消代码自动换行

（1）vscode–>首选项–>设置，之后在出现的界面输入`settings.json`

![image-20210330172423481](E:\Typora\image\image-20210330172423481.png)



（2）在`settings.json`中加入以下代码

```json
"vetur.format.defaultFormatterOptions": {
    "js-beautify-html": {
         "wrap_line_length": 900, // 数值越大，一行放的属性越多
         "wrap_attributes": "auto",
         "end_with_newline": false
     },
     "prettyhtml": {
         "printWidth": 100,
         "singleQuote": false,
         "wrapAttributes": false,
         "sortAttributes": false
     }
 },
 "vetur.format.defaultFormatter.html": "js-beautify-html",
 "vetur.format.defaultFormatter.js": "vscode-typescript",
```

![image-20210330172610486](E:\Typora\image\image-20210330172610486.png)



## 3. bus消息总线

**(1) 配置**

```javascript
// main.js
// 将挂载到prototype单独抽离成一个文件
import plugin from './utils/Bus'

Vue.use(plugin)
```

```javascript
// Bus.js
import Bus from 'vue';
let install = function (Vue) {
    // 设置eventBus
    Vue.prototype.bus = new Bus();
}
 
export default { install };
```

**(2) 发送消息**

```javascript
this.bus.$emit("confirm", 1);
```

**(3) 接收消息**

```javascript
mounted() { 
    // 注册事件
    this.bus.$on("confirm", content => {
        this.getList();
    });
},

beforeDestroy() {
	// 注册的总线事件要在组件销毁时卸载，否则会多次挂载，造成触发一次但多个响应的情况
	this.bus.$off("confirm", content => console.log(content));
}
```

