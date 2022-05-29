// 引入 Vue
import Vue from 'vue'
// 引入 App 组件
import App from './App.vue'

// 关闭 Vue 生产提示
Vue.config.productionTip = false

const vm = new Vue({
    render: h => h(App),
    beforeCreate() {
        Vue.prototype.$bus = this     // 安装全局事件总线
    },
})
vm.$mount('#app')