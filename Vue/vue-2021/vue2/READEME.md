# 一、初识Vue

## 1. MVVM

![](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/vue/xxx.2cb2jijsm8bo.png)

`MVVM` 落实到`vue` 代码中：

```html
<!-- view -->
<div id="app">
    
</div>

<script>
    // vm
	const vm = new Vue({
        el: '#app',
        // model
        data() {
            return {
                name: 'zs'
            }
        }
    });
</script>
```



打印出`vue`实例：

![](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/vue/xxx.551w6ajbu9s0.png)



**总结**：

- **MVVM模型**：
  1. M：模型（Model），data中的数据。
  2. V：视图（View），模板代码。
  3. VM：视图模型（ViewModel），Vue实例。
- **视图渲染**：
  1. data中所有的属性，最后都出现在了 vm 身上。
  2. vm 身上所有的属性以及 Vue 原型上所有的属性，在 Vue 模板中都可以直接渲染。

## 2. 数据代理

### 2.1. Object.defineProperty()

`Object.defineProperty()` 基本使用。

```javascript
let person = {
    name: 'zs',
};

Object.defineProperty(person, 'sex', {
    value: '男',			 // 设置 sex 的属性值
    enumerable: true,     // 控制属性是否可以枚举（遍历）默认 false
    writable: true,       // 控制属性是否可以被修改 默认 false
    configurable: true    // 控制属性是否可以被删除 默认 false
});
```



`Object.defineProperty()` 高级使用。

> 假设现有一个需求：number 变量的值是18，将number和对象的某个属性关联，当number的值修改后，对象中的值也修改。当对象中关联的值修改后，number值同样发生改变。

```javascript
let number = 18;

let student = {
    name: 'zs',
};

Object.defineProperty(student, 'age', {
    // 当读取 person 对象的 age 属性时, get函数(getter)就会被调用
    get() {
        // 当把 number 改成 19, person 中的 age 就会变成 19
        console.log('有人读取 age 属性了');
        return number;
    },

    // 当修改 person 对象的 age 属性时, set函数(setter)就会被调用
    set(age) {
        console.log('有人修改 age 属性了');
        number = age;
    }
});
```

可以在控制台测试：

![](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/vue/image.3ecwgs94kvs0.png)



### 2.2. 何为数据代理

```javascript
let obj1 = { x: 100 };
let obj2 = { y: 100 };

// 当修改 obj1 的 x 值, obj2 中 x 的值会变。
// 当修改 obj2 的 x 值, obj1 中 x 的值也会变。
Object.defineProperty(obj2, 'x', {
    get() {
        return obj1.x;
    },
    set(value) {
        obj1.x = value;
    }
});
```



### 2.3. Vue的数据代理

```javascript
const vm = new Vue({
    el: '#app',
    data() {
        return {
            name: 'home',
            address: 'beijing'
        }
    }
});

console.log(vm);
```

![](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/vue/image.20ecweptrj9c.png)



> vm 中的 `address、name` 引用的是配置对象中的 data 中的 `address、name`。 
>
> 配置对象中的 data 保存在了 vm 中的 `_data` ，以下是验证方法：

```javascript
let data = {
    name: 'home',
    address: 'beijing'
};

const vm = new Vue({
    el: '#app',
    data,
});

console.log(vm);

// 这就证明 vm._data 就是传入 Vue 中的 data
console.log(vm._data === data);        // true
```

![](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/vue/image.20rqza3cc38g.png)



`Vue` 模板中同样可以使用 `_data.name` 进行渲染，例如：

```html
<div id="app">
    <h1>名称: {{name}} {{_data.name}}</h1>
    <h1>地址：{{address}}</h1>
</div>

<script>
	const vm = new Vue({
        el: '#app',
        data: {
            name: 'home',
            address: 'beijing'
        }
    });
</script>
```



**数据代理图示**：

注意：只有 `data` 中的数据才会做**数据代理**，`methods` 中的方法并不会做**数据代理**。

![](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/vue/image.505jly5r34s0.png)



## 3. Vue中的 this

### 3.1. this指向的谁？

```javascript
const vm = new Vue({
    el: '#app',
    methods: {
        // 正常函数中的 this 就是 Vue 实例
        f1() {
            console.log(this);
        },
        // 箭头函数中没有 this 会向外找, 找的是 Window 对象。
        f2: () => {
            console.log(this);
        },
        f3() {
            // 箭头函数中没有 this 会向外找, 找的是 Vue 对象。
            setTimeout(() => {
                console.log(this);
            }, 1000);
        },
        f4() {
            setTimeout(function () {
                console.log(this);
            }, 1000);
        }
    },
});

vm.f1();        // vue 实例
vm.f2();        // Window 对象
vm.f3();        // vue 实例
vm.f4();        // Window 对象
```



### 3.2. 两个重要的原则

1. 被 Vue 管理的函数，最好写成普通函数，这样 this 指向的才是 `vm` 或者 `Vue` 实例。
2. 所有不被 Vue 所管理的函数（定时器的回调函数、Ajax的回调函数、Promise的回调函数等），最好写成箭头函数，这样 this 的指向才是 `vm` 或 `Vue` 实例。



## 4. 事件处理

### 4.1. 事件基本使用

```html
<div id="app">
    <!-- 只传递 event 就不用加括号 -->
    <button type="button" @click="showInfo1">按钮1</button>
    <!-- 传参又要保留 event 就需要使用 $event 占位符 -->
    <button type="button" @click="showInfo2($event, 66)">按钮2</button>
</div>

<script>
    const vm = new Vue({
        el: '#app',
        methods: {
            showInfo1(event) {
                console.log(event);
            },
            showInfo2(event, num) {
                console.log(event);
                console.log(num);
            }
        },
    });
</script>
```

**注意**：`methods` 中的方法并不会有**数据代理**，只有 `data` 中的数据才会有**数据代理**。

因此，方法不要写在 `data` 中，否则，方法也会有**数据代理**，实际上，方法并不需要。

**总结**：

- 需要变化的数据才需要数据代理！

### 4.2. 事件修饰符

**Vue中的事件修饰符**：

1. `.prevent`：阻止默认事件（常用）。
2. `.stop`：阻止事件冒泡（常用）。
3. `.once`：事件只触发一次（常用）。
4. `.capture`：使用事件的捕获模式。
5. `.self`：只有 `event.target` 是当前操作的元素才触发事件。
6. `.passive`：事件的默认行为立即执行，无需等待事件回调执行完毕（主要适用移动端）。



`.capture` 使用方法：

```html
<!-- 4. capture -->
<!-- 
事件的执行顺序：默认 先捕获 再冒泡
 1) 捕获（不处理事件）：嵌套关系由外到内
 2) 冒泡（事件处理）：嵌套关系由内到外

使用 capture, 在事件捕获阶段就处理事件
-->
<div class="box1" @click.capture="showMsg(1)">
    <span>box1</span>
    <div class="box2" @click="showMsg(2)">box2</div>
</div>

<script>
    const vm = new Vue({
        el: '#app',
        methods: {
            showMsg(msg) {
                console.log(msg + '\t 你好！');
            }
        },
    });
</script>
```



`.self` 使用方法：

```html
<!-- 5. self -->
<!-- 
.self 什么意思？
当触发点击事件, 并且是由于 div 被点击而引起的事件, 
div 上的 @click="showDetails" 才会生效。

.self 修饰符一定程度上也能阻止冒泡！
-->
<div @click.self="showDetails" class="box1">
    <button type="button" @click="showDetails">.self测试按钮</button>
</div>

<script>
    const vm = new Vue({
        el: '#app',
        methods: {
            showDetails(event) {
                // <button type="button">.self测试按钮</button>
                console.log(event.target);    
            }
        },
    });
</script>
```





## 5. 计算属性vs监听属性

### 5.1. 如何选择？

1. 当计算属性和监听属性都能完成一个功能时，使用计算属性。
2. 当要开启异步任务时，使用监听属性！



## 6. 绑定class

```html
<div id="app">
    <!-- 1. 绑定 class 样式 => 字符串写法, 
适用于: 要绑定的样式的类名不确定, 需要动态指定 
-->
    <div class="box" :class="curMood" @click="changeMood">
        {{text}}
    </div>

    <!-- 2. 绑定 class 样式 => 数组写法, 适用于：要绑定的样式个数不确定、名字也不确定 -->
    <div class="box" :class="classArr">
        {{text}}
    </div>

    <!-- 3. 绑定 class 样式 => 对象写法, 适用于：要绑定的样式个数确定、名字也确定, 
但是需要动态决定用不用 
-->
    <div class="box" :class="classObj">
        {{text}}
    </div>
</div>

<script>
	// 工具类
    class utils {
        // 生成 [m, n] 的伪随机数
        static randInt(m, n) {
            return parseInt(Math.random() * (n - m + 1) + m, 10);
        }
    }

    const vm = new Vue({
        el: '#app',
        data() {
            return {
                text: 'Hello World',
                moods: ['normal', 'happy', 'sad'],
                curMood: 'normal',
                classArr: ['box-radius', 'text-scale'],
                classObj: {
                    'box-radius': false,
                    'text-scale': true,
                }
            }
        },
        methods: {
            // 监听 box 的点击事件
            changeMood(event) {
                this.curMood = 
                    this.moods[utils.randInt(0, this.moods.length - 1)];
            },
        },
    });
</script>
```



## 7. 条件渲染

### 7.1. v-if配合template标签

```html
<div id="app">
    <!-- 
		template 标签不会渲染到 DOM 结点上。
		template 上只能添加 v-if, 添加 v-show 会失效。
	-->
    <template v-if="show">
        <h1>Hello</h1>
    </template>
    <button @click="show = !show">点我切换</button>
</div>

<script>
    const vm = new Vue({
        el: '#app',
        data() {
            return {
                show: true,
            }
        }
    });
</script>
```



### 7.2. v-if配合key使用

Vue 会尽可能高效地渲染元素，通常会复用已有元素而不是从头开始渲染。

```html
<div id="app">
    <!-- 如果不加 key vue 在渲染 view 的时候就会进行复用 -->
    <!-- 有不想复用的组件需要加上 key -->
    <template v-if="switchOption === 'username'">
        <label for="username">username</label>
        <!-- 这里添加 key -->
        <input type="text" id="username" 
               placeholder="please input your username" key="username" />
    </template>
    <template v-else>
        <label for="email">email</label>
        <!-- 这里添加 key -->
        <input type="text" id="email"
               placeholder="please input your email" key="email" />
    </template>
    <br />
    <button @click="++cnt">点我计数器加1</button>
</div>

<script>
    const vm = new Vue({
        el: '#app',
        data() {
            return {
                cnt: 0,     // 计数器
            };
        },
        computed: {
            switchOption() {
                switch (this.cnt % 2 === 0 ? 1 : 0) {
                    case 1:
                        return 'username';
                    case 0:
                        return 'email';
                    default:
                        return 'username';
                }
            }
        },
    });
</script>
```



## 8. 列表渲染

### 8.1. key的原理

Vue 中的 `key` 有什么作用？`key` 的内部原理。

- 虚拟 DOM 中 `key` 的作用：`key` 是虚拟 DOM 对象的标识，当数据发生变化时，Vue 会根据【新数据】生成【新的虚拟DOM】，随后 Vue 进行【新虚拟DOM】与【旧虚拟DOM】的差异比较。

- 虚拟DOM对比规则：

  1. 旧虚拟DOM中找到了与新虚拟DOM相同的 `key`：
     1. 若虚拟DOM中内容没变，直接使用之前的真实DOM。
     2. 若虚拟DOM中内容变了，则生成新的真实DOM，随后替换掉页面中之前的真是DOM。
  2. 旧虚拟DOM中未找到与新虚拟DOM相同的 `key`：
     1. 创建新的真实 DOM，随后渲染到页面。

- 用 `index` 作为 `key` 可能会引发的问题：

  1. 若对数据进行：逆序添加、逆序删除等破坏顺序操作，会产生没有必要的真实 DOM 更新。界面效果没问题，但是效率低。
  2. 如果结构中还包含输入类的DOM：会产生错误的DOM更新 ==> 界面有问题。

- 开发中如何选择 `key`：

  1. 最好使用每条数据的唯一标识作为 `key`，比如：id、手机号、身份证号、学号等唯一值。
  2. 如果不存在对数据的逆序添加、逆序删除等破坏顺序的操作，仅仅用于渲染列表展示，使用 `index` 作为 `key` 没有问题。


![](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/vue/image.2obmfe81e4u0.webp)



## 9. 更新时的问题

### 9.1. vue感知不到？

```html
<div id="app">
    <button @click="updateMDM">更新马冬梅</button>
    <ul>
        <li v-for="(star, index) in stars" :key="star.id">
            {{star.name}}-{{star.age}}-{{star.gender}}
        </li>
    </ul>
</div>

<script>
    const vm = new Vue({
        el: '#app',
        data() {
            return {
                stars: [
                    { id: '001', name: '周杰伦', age: 19, gender: '男' },
                    { id: '002', name: '温兆伦', age: 20, gender: '男' },
                    { id: '003', name: '周冬雨', age: 21, gender: '女' },
                    { id: '004', name: '马冬梅', age: 22, gender: '女' },
                ],
            }
        },
        methods: {
            updateMDM() {
                // 这种更新方式失效, vue 并不会感知到修改
                this.stars.forEach((cur, index, arr) => {
                    if (cur.name.indexOf('马冬梅') !== -1) {
                        const id = cur.id;
                        // 这样直接赋值就不会走数据代理了！
                        // vm.stars 中存的实际上时一个 Observer 对象
                        arr[index] = 
							{ id, name: 'zs', age: '99', gender: '男' };
                        console.log(arr);
                    }
                });
            }
        },
    });
</script>
```

![](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/vue/image.3mt95aehjf60.png)

### 9.2. vue对象监测原理

```javascript

let data = {
    name: 'zs',
    address: '北京',
};

function Observer(obj) {
    // 拿到对象中所有的 key, 汇总成一个数组
    const keys = Object.keys(obj);
    // 遍历
    keys.forEach(key => {
        Object.defineProperty(this, key, {
            get() {
                return obj[key];
            },
            set(val) {
                console.log(`${key} 被修改了`);
                obj[key] = val;
            },
        })
    });
}

// 创建一个监视的实例对象, 用于监视 data 中属性的变化
const obs = new Observer(data);
console.log(obs);

// 准备一个 vm 实例对象
let vm = {};
vm._data = obs;
console.log(vm);
```



## 10. vm、vc和Vue原型对象

![](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/vue/image.458zzxa5emk0.webp)

`javascript` 中的类（构造函数）必定有一个 `prototype` 属性，指向一个原型对象。

`javascript`中的实例必定有一个 `__proto__` 属性，指向它的类的原型对象。

`javascript` 中只要是对象，必定有 `__proto__` 属性。



## 11. 生命周期图

![](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/vue/生命周期.1lnu192745a8.webp)



# 二、使用Vue CLI

## 1. CLI文件结构

<img src="https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/vue/image.37oy0te2vdk0.webp" style="zoom:200%;" />



## 2. main.js的render函数

```javascript
// main.js

/**
 * 该文件是整个 CLI 项目的入口文件
 */

// 引入 Vue
import Vue from 'vue'
// 引入 App 组件, 它是所有组件的父组件
import App from "./App.vue"

// vue 的生产环境提示
Vue.config.productionTip = false

// 创建 vue 实例对象 --- vm
// 之所以 template 不能用, 就是因为引入的 vue 文件是 vue.runtime.esm.js, 缺少模板解析器.
// 引入 vue/dist/vue.js, 就可以使用 template 了
/* new Vue({
  el: '#app',
  template: `<h1>Hello World</h1>`,
}) */

// import Vue from 'vue' 默认引入的是残缺版的 vue.js, 没有模板解析的功能, 
// 所以需要 render 渲染函数来将 App 组件渲染到容器中
/*  new Vue({
  el: '#app',
  // render 函数的功能: 将 App 组件放入容器中
  // render: (h) => h(App),
  // render(createElement) {
  //   return createElement('h1', 'Hello World');
  // }
  render: createElement => createElement('h1', 'Hello World')
})  */

/**
 * 总结关于不同版本的 Vue:
 *  1. vue.js 与 vue.runtime.xxx.js 的区别:
 *    (1) vue.js 是完整版的 Vue, 包含: 核心功能 + 模板解析器。
 *    (2) vue.runtime.xxx.js 是运行版的 Vue, 只包含: 核心功能, 没有模板解析器。
 * 
 *  2. 因为 vue.runtime.xxx.js 没有模板解析器, 所以不能使用 template 配置项, 
 *     需要使用 render 函数接收到的 createElement 函数去指定具体内容。
 */

const vm = new Vue({
  el: '#app',
  render: h => h(App),
})

console.log(vm)
```



## 3. 修改CLI默认配置

使用 `vue insepct > output.js` 命令可以将 `CLI` 中所有默认配置导出。

`vue.config.js` 是一个可选的配置文件，如果项目的 (和 `package.json` 同级的) 根目录中存在这个文件，那么它会被 `@vue/cli-service` 自动加载。

在 `vue.config.js` 修改 CLI 的默认配置。

```javascript
// vue.config.js
module.exports = {
    pages: {
        index: {
            // page 的入口
            entry: 'src/main.js',
        }
    },
    lintOnSave: false,  // 关闭语法检查
}
```



## 4. 标识: ref

```html
<!-- 
        1. ref 被用来给元素或子组件注册引用信息。引用信息将会注册在父组件的 $refs 对象上。
        
        2. ref的使用: 
            (1) 如果在普通的 DOM 元素上使用，引用指向的就是 DOM 元素；
            (2) 如果用在子组件上，引用就指向组件实例。

        3. 使用方式:
            第一步: 打标识。<h1 ref="xxx">...</h1> 或 <school ref="school"></school>     
            第二步: 获取 this.$refs.xxx
-->
```



## 5. 配置项: props

```html
<!-- 
      配置项: props
        1. 传数据 <student name="xxx" :age="19" /> 
           不加 v-bind 全是字符串, 加了 v-bind 就按 js 表达式来解析
        2. 接收数据:
           (1) 方式一:
               props: ['name', 'age', 'gender'],
           (2) 方式二:
               props: {
                 name: String,
                 age: Number,
               }
           (3) 方式三:
               props: {
                 name: {
                   type: String,
                   required: false,
                   default: 'Hello'
                 }
               }
        
        3. 备注: props 是只读的, Vue 底层会检测对 props 的修改, 如果进行了修改, 会有错误警告.
            如果业务需求确实需要修改传入的数据, 复制 props 的内容到 data 一份, 然后去修改 data
            中的数据.(特别注意: props 的数据优先加载, data 中就可以拿到 props 中的数据了！)
-->
```



## 6. mixin：混入

```html
   <!-- 
      mixin(混入)
        1. 功能：可以把多个组件公用的配置提取成一个混入对象。
        2. 使用方式:
            (1) 第一步: 定义混入, 例如:
              {
                data() {....},
                methods: {....},
              }
            (2) 第二步: 使用混入, 例如:
              i.  局部混入: mixins: ['xxx']
              ii. 全局混入: Vue.mixin({...})
        3. 选项合并
            (1) 数据对象在内部会进行递归合并，并在发生冲突时以组件数据优先。
            (2) 同名钩子函数将合并为一个数组，因此都将被调用。
				另外，混入对象的钩子将在组件自身钩子之前调用。
            (3) 值为对象的选项，例如 methods、components 和 directives，将被合并为同一个对象。
				两个对象键名冲突时，取组件对象的键值对。
     -->
```



## 7. 总结TodoList案例

一、组件化编码流程：

​	（1）拆分静态组件：组件要按照功能点拆分，命名不要与 HTML 元素冲突。

​	（2）实现动态组件：考虑好数据的存放位置，数据是一个组件在用，还是一些组件在用：

​	          i. 一个组件在o用：放在组件自身即可。

​	          ii. 一些组件在用：放在他们共同的父组件上（**状态提升**）。

二、props适用于：

​	  （1）父组件 ==> 子组件 通信。

​	  （2）子组件 ==> 父组件 通信（要求父组件先给子组件一个函数）。

三、使用 `v-model` 时要切记：v-model 绑定的值不能是 props 传过来的值，因为 props 是不可以修改的！

四、props 传过来的若是对象类型的值，修改对象中的属性时 Vue 不会报错，但是不推荐这样做。



## 8. WebStorage

1. `SessionStorage` 存储的内容会随着浏览器窗口关闭而消失。
2. `LocalStorage` 存储的内容，需要手动清除才会消失。
3. `xxxStorage.getItem(xxx)`，如果 `xxx` 对应的 `value` 获取不到，那么 `getItem` 的返回值是 `null`。
4. `JSON.parse(null)` 的结果依然是 `null`。



## 9. 组件的自定义事件

1. 一种组件间通信的方式，适用于：**子组件 ===> 父组件**。

2. 使用场景：A是父组件，B是子组件，B想给A传数据，那么就要在A中给B绑定自定义事件（事件的回调在A中）。

3. 绑定自定义事件：

   1. 第一种方式，在父组件中：`<Demo @my-event="handle"/>` 或 `<Demo v-on:my-event="handle"/>`。

   2. 第二种方式，在父组件中：

      ```vue
      <Demo ref="demo" />
      ......
      mounted() {
      	this.$refs['demo'].$on('my-event', (data) => console.log(data))
      }
      ```

4. 触发自定义事件：`this.$emit('my-event', data)`。

5. 解绑自定义事件：`this.$off('atguigu')`。
6. 组件上也可以绑定原生 DOM 事件，需要使用 `.native` 修饰符。
7. 注意：通过 `this.$refs['refName'].$on('my-event', callback)` 绑定自定义事件时，回调要么配置在 `methods` 中，要么用箭头函数，否则 `this` 指向会出问题。

## 10. 全局事件总线

1. 全局事件总线是一种组件间通信的方式，适用于**任意组件间的通信**。

2. 安装全局事件总线：

   ```javascript
   // main.js
   
   const vm = new Vue({
       render: h => h(App),
       beforeCreate() {
           Vue.prototype.$bus = this     // 安装全局事件总线
       },
   })
   vm.$mount('#app')
   ```

3. 使用全局事件总线：

   ```javascript
   // 发消息
   this.$bus.$emit(name, value)
   
   // 收消息
   this.$bus.$on(name, callback)
   ```

4. 注意：在 `beforeDestroy()` 生命周期函数中，使用 `$off` 去解绑**当前组件所用到的事件**。

## 11. 消息订阅与发布

1. 消息订阅与发布：一种组件间通信的方式，适用于**任意组件间通信**。

2. 使用步骤：

   1. 安装pubsub：`npm i pubsub-js`

   2. 在 `Vue` 中全局引入

      ```javascript
      // main.js
      
      // 引入 Vue
      import Vue from 'vue'
      // 引入 App 组件
      import App from './App.vue'
      // 引入 pubsub-js
      import PubSub from 'pubsub-js'
      // PubSub 全局安装
      Vue.prototype.$PubSub = PubSub
      
      // 关闭 Vue 生产提示
      Vue.config.productionTip = false
      
      const vm = new Vue({ render: h => h(App) })
      vm.$mount('#app')
      ```

   3. 使用方法

      ```javascript
      // 发布
      this.$PubSub.publish(topicName, data)
      
      // 订阅 在 Vue 组件中使用
      mounted() {
        // 订阅消息
        const token = this.$PubSub.subscribe('send-student-name-topic', 
           (msg, data) => console.log(msg, data))
        this.token = token
      },
      beforeDestroy() {
        // 一定要取消消息订阅！
        this.$PubSub.unsubscribe(this.token)
      },
      ```




## 12. Vuex

### 12.1. Vuex是什么？

Vuex 是一个专为 Vue.js 应用程序开发的**状态管理模式**。它采用集中式存储管理应用的所有组件的状态。

`Vuex` 是为 `Vue.js` 开发的一个插件，也是组件间通信的方式，并且可以用于任意组件间的通信。

![](https://cdn.jsdelivr.net/gh/RingoTangs/image-hosting@master/vue/image.497kq9ov8kq0.webp)



### 12.2. 安装Vuex

1. `npm install vuex --save ` 。

2. 创建 `store` 文件夹，然后创建 `index.js`。

   ```javascript
   import Vue from 'vue'
   import Vuex from 'vuex'
   
   // Vue.use 安装 Vuex 插件后, 就可以在 vm 中添加 store 配置项了
   Vue.use(Vuex)
   
   // 准备 state —— 用于存储数据
   const state = { }
   
   // 准备 mutations —— 用于操作 state 中的数据
   const mutataions = { }
   
   // 准备 actions —— 用于响应组件中的动作
   const actions = { }
   
   // 创建 store
   const store = new Vuex.Store({ state, mutations, actions })
   
   // 导出 store
   export default store
   ```

3. 在 `main.js` 导入 `store`。

   ```javascript
   // 引入 Vue
   import Vue from 'vue'
   // 引入 App 组件
   import App from './App.vue'
   // 引入 store
   import store from './store'
   
   // 关闭 Vue 生产提示
   Vue.config.productionTip = false
   
   const vm = new Vue({
     render: (h) => h(App),
     store,
   })
   vm.$mount('#app')
   ```



## 13. VueRouter

### 13.1. 几个注意点

1. 路由组件通常存放在 `pages` 文件夹，一般组件通常存放在 `components` 文件夹。
2. 通过切换，"隐藏" 了的路由组件，默认是被销毁掉的，需要的时候再去挂载。
3. 每个组件都有自己的 `$route` 属性，里面存储着自己的路由信息。
4. 整个应用只有一个 `router`，可以通过组件的 `$router` 属性获取到。



### 13.2. props属性路由组件传参

第一步：在路由配置中传参

```javascript
// router.js

const routes = [
  {
    path: '/home',
    name: 'Home',
    component: () => import('../pages/Home.vue'),
    children: [
      {
        path: 'news',
        name: 'News',
        component: () => import('../pages/News.vue'),
      },
      {
        path: 'message',
        name: 'Message',
        component: () => import('../pages/Message.vue'),
        children: [
          {
            path: ':id',
            name: 'MessageDetails',
            component: () => import('../pages/MessageDetails'),
            // props 的第一种写法, 值为对象, 该对象中的所有 k-v 都会以 props 的形式传给 MessageDetails 组件
            // props: { a: 1, b: 'hello' },

            // props 的第二种写法, 若值为 true, 就会把该路由组件收到的所有 params 参数, 以 props 的形式传给 Detail 组件
            // props: true,

            // props 的第三种写法, 值为函数
            // 连续解构赋值语句
            props: ({ params, query }) => ({ id: params.id, title: query.title })
          }
        ],
      },
    ],
  },
]
```

第二步：在指定组件中就可以用 `props` 属性来接收了，如果没有接收，这些数据就会汇总到 `$attr`。

```vue
<template>
</template>

<script>
  export default {
    name: 'MessageDetails',
    props: { id: String, title: String },
  }    
</script>
```



