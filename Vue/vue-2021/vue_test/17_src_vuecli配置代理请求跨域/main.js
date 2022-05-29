// 引入 Vue
import Vue from 'vue'
// 引入 App 组件
import App from './App.vue'

import { request } from './http.js'

// 关闭 Vue 生产提示
Vue.config.productionTip = false
Vue.prototype.$http = request

const vm = new Vue({
    render: h => h(App),
    beforeCreate() {
        // 注册全局事件总线
        Vue.prototype.$bus = this
    },
})
vm.$mount('#app')