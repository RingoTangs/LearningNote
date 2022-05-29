import Vue from 'vue'
import Vuex from 'vuex'

// Vue.use 安装 Vuex 插件后, 就可以在 vm 中添加 store 配置项了
Vue.use(Vuex)

// 准备 state —— 用于存储数据
const state = {
  sum: 0,
  technology: 'vue.js',
  plugin: 'vuex',
  personList: JSON.parse(localStorage.getItem('personList')) || [],
}

// 准备 mutations —— 用于操作 state 中的数据
// mutations 中的函数名一般用大写
const mutations = {
  INCREMENT(state, n) {
    state.sum += n
  },
  DECREMENT(state, n) {
    state.sum -= n
  },
  // 添加 person 到 personList 数组
  ADD_PERSON({ personList }, person) {
    personList.push(person)
    localStorage.setItem('personList', JSON.stringify(personList))
  }
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

// 有点类似于 computed 计算属性
const getters = {
  // 返回 sum 乘以 k 后的函数
  sumMultiplyBy10(state, k) {
    return (k) => state.sum * k
  },
}

// 创建 store
const store = new Vuex.Store({ state, mutations, actions, getters })

// 导出 store
export default store
