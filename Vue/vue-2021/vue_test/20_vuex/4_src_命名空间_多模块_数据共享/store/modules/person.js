export default {
    // 一定要写命名空间
    namespaced: true,
    state: {
        personList: JSON.parse(localStorage.getItem('personList')) || [],
    },
    // 这里的参数是 state
    mutations: {
        // 添加 person 到 personList 数组
        ADD_PERSON({ personList }, person) {
            personList.push(person)
        }
    },
    // 这里的参数是 context
    actions: {
        // 添加 person 到 personList 数组并保存到 localStorage
        addPersonThenSaveLocal({ commit, state }, person) {
            commit('ADD_PERSON', person)
            localStorage.setItem('personList', JSON.stringify(state.personList))
        }
    },
    getters: {}
}