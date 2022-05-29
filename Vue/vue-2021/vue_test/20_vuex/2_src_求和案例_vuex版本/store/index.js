import Vue from 'vue'
import Vuex from 'vuex'

// Vue.use 安装 Vuex 插件后, 就可以在 vm 中添加 store 配置项了
Vue.use(Vuex)

// 准备 state —— 用于存储数据
const state = {
  sum: 0,
}

// 准备 mutations —— 用于操作 state 中的数据
const mutations = {
  // mutations 中的函数名一般用大写
  INCREMENT(state, n) {
    state.sum += n
  },
  DECREMENT(state, n) {
    state.sum -= n
  },
}

// 准备 actions —— 用于响应组件中的动作
const actions = {
  sumIsOddThenAdd({ state, commit }, n) {
    if (state.sum % 2) {
      commit('INCREMENT', n)
    }
  },
  incrementAwait({ commit }, n) {
    setTimeout(() => commit('INCREMENT', n), 1000)
  },
}

// 创建 store
const store = new Vuex.Store({ state, mutations, actions })

// 导出 store
export default store
