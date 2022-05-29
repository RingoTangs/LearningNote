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