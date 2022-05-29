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