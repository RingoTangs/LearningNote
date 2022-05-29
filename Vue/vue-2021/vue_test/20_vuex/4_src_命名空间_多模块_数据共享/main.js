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
