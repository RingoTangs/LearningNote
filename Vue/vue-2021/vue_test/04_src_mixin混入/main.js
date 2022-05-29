// 引入 Vue
import Vue from 'vue'
// 引入 App 组件
import App from './App.vue'
// 全局引入混入, 所有的 vc 和 vm 都会加入这些配置项
// import { mixin, mixin2 } from './mixin.js'
// Vue.mixin(mixin)
// Vue.mixin(mixin2)

// 关闭 Vue 生产提示
Vue.config.productionTip = false

const vm = new Vue({ render: h => h(App) })
vm.$mount('#app')