# 1.Vue/CLI3

## 1.1.基本介绍

`vue-cli3`与`vue-cli2`有很大的区别：

- `vue-cli3`是基于`webpack4`打造，`vue-cli2`还是`webpack3`。
- `vue-cli3`的设计原则是**"0配置"**，移除配置文件根目录下的，`build`和`config`等目录。
- `vue-cli3`提供了`vue ui`命令，提供了可视化配置，更加人性化。
- 移除了`static`文件夹，新增了`public`文件夹，并且`index.html`移动到`public`中。

## 1.2.创建项目

```shell
# 1、安装vue/cli
npm install -g @vue/cli

# 2、查看vue/cli的版本
vue --version

# 3、vue/cli3初始化项目
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
 > In dedicated config files # 可以选择一个独立的配置文件
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

## 1.3.vue/cli3配置

### 1.3.1.UI管理项目

> UI界面的启动

```shell
# 启动 vue ui
vue ui
```

> 导入`vue/cli3`创建的项目，使用`dashboard`来管理项目。

![vue ui](https://img-blog.csdnimg.cn/20200724150705368.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)



> 项目导入成功后，就可以对项目配置进行管理了。

![Dashboard管理项目](C:\Users\14666\AppData\Roaming\Typora\typora-user-images\image-20200724151008199.png)



### 1.3.2.创建vue.config.js

`vue.config.js` 是一个可选的配置文件，如果项目的 (和 `package.json` 同级的) 根目录中存在这个文件，那么它会被 `@vue/cli-service` 自动加载。你也可以使用 `package.json` 中的 `vue` 字段，但是注意这种写法需要你严格遵照 JSON 的格式来写。

**`vue.config.js`配置官网地址：https://cli.vuejs.org/zh/config/#vue-config-js**

# 2.箭头函数

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

# 3.vue-router

## 3.1.安装router

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



注意：为了让一个 `CLI `插件在` Vue CLI `项目中被正常使用，它必须遵循 `vue-cli-plugin-` 或者 `@scope/vue-cli-plugin-` 这样的命名惯例。这样你的插件才能够：

- 被 `@vue/cli-service` 发现；
- 被其他开发者通过搜索发现；
- 通过 `vue add ` 或者 `vue invoke ` 安装。
- 确保插件的名字是正确的，否则他将不能通过 `vue add` 安装并且不能在 UI 插件中搜索得到！

```shell
# 安装vue-router插件
vue add router # 该命令执行后以上一系列操作都会自动创建！
```

## 3.2.router-link其他属性

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

## 3.3.代码路由跳转

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

## 3.4.动态路由

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

## 3.5.路由的懒加载

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

## 3.6.路由的嵌套使用

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

`App.vue`中`<router-view>`的是最顶层的出口，渲染最高级路由匹配到的组件。同样地，一个被渲染组件同样可以包含自己的嵌套 。例如，在 `User.vue` 组件的模板添加一个 `<router-view>`。

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

## 3.7.路由组件传参

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

## 3.8.$router和$route的区别

![区别](https://img-blog.csdnimg.cn/20200725170737664.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

## 3.9.导航守卫

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

**也可以注册全局后置钩子，然而全局前置守卫不同的是，这些钩子不会接受 `next` 函数也不会改变导航本身**

```js
router.afterEach((to, from) => {
  // ...
})
```

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

# 5.axios

## 5.1.vue整合axios

```shell
# 1、安装axios
npm install axios --save

# 2、将下面代码加入入口文件
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









