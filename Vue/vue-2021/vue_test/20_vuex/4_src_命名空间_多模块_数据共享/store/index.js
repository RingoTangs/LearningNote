import Vue from 'vue'
import Vuex from 'vuex'
import countModule from './modules/count'
import personModule from './modules/person'

// Vue.use 安装 Vuex 插件后, 就可以在 vm 中添加 store 配置项了
Vue.use(Vuex)

// 创建 store
const store = new Vuex.Store({
  modules: {
    countModule,
    personModule,
  }
})

console.log(store);

// 导出 store
export default store
