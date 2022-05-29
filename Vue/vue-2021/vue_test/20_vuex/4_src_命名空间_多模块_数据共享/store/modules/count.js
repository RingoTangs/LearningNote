export default {
    // 添加命名空间 
    // 就可以使用 ...mapState('countModule', ['sum', 'technology', 'plugin']) 这种模式了
    namespaced: true,
    state: {
        sum: 0,
        technology: 'vue.js',
        plugin: 'vuex',
    },
    mutations: {
        INCREMENT(state, n) {
            state.sum += n
        },
        DECREMENT(state, n) {
            state.sum -= n
        },
    },
    actions: {
        sumIsOddThenAdd({ state, commit }, n) {
            if (state.sum % 2) {
                commit('INCREMENT', n)
            }
        },
        incrementAwait({ commit }, n) {
            setTimeout(() => commit('INCREMENT', n), 1000)
        },
    },
    getters: {
        // 返回 sum 乘以 k 后的函数
        sumMultiplyBy10(state, k) {
            return (k) => state.sum * k
        },
    },
}