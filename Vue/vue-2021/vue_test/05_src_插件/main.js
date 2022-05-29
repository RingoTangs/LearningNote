// 引入 Vue
import Vue from 'vue'
// 引入 App 组件
import App from './App.vue'

import plugins from './plugins'

// 安装 Vue.js 插件。该方法需要在调用 new Vue() 之前被调用。
Vue.use(plugins, { x: 1, y: 2 })

// 关闭 Vue 生产提示
Vue.config.productionTip = false


const vm = new Vue({ render: h => h(App) })
vm.$mount('#app')