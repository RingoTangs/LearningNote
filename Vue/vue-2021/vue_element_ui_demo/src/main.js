import Vue from 'vue'
import App from './App.vue'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import './styles/index.scss'

import { nanoid } from 'nanoid'

Vue.prototype.nanoid = nanoid

Vue.use(ElementUI)

Vue.config.productionTip = false

new Vue({
  render: (h) => h(App)
}).$mount('#app')
