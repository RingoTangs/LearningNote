<template>
  <div>
    <h1 style="color: red">personList的长度为: {{ personList.length }}</h1>
    <h1>当前求和为: {{ sum }}</h1>
    <!-- getters 中的数据要这样获取 -->
    <h5>当前求和放大10倍后为: {{ $store.getters['countModule/sumMultiplyBy10'](10) }}</h5>
    <h5>当前求和放大 {{ k }} 倍后为: {{ sumMultiplyBy10(k) }}</h5>
    <h5>我正在学习 {{ technology }} 的 {{ plugin }}</h5>
    <div class="ops">
      <select v-model.number="n">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
      </select>
      <button @click="INCREMENT(n)">+</button>
      <button @click="DECREMENT(n)">-</button>
      <button @click="sumIsOddThenAdd(n)">当前求和为奇数再加</button>
      <button @click="incrementAwait(n)">等一等再加</button>
    </div>
  </div>
</template>

<script>
  import { mapMutations, mapActions, mapState, mapGetters } from 'vuex'
  export default {
    name: 'Count',
    data() {
      return {
        n: 1, // 用户选择的数字
        k: 8, // sum 扩大的倍数
      }
    },
    methods: {
      // 要想使用这种写法, 一定要在模块内部声明 namespaced
      ...mapMutations('countModule', ['INCREMENT', 'DECREMENT']),
      ...mapActions('countModule', ['sumIsOddThenAdd', 'incrementAwait']),
    },
    computed: {
      ...mapState('countModule', ['sum', 'technology', 'plugin']),
      ...mapState('personModule', ['personList']),
      ...mapGetters('countModule', ['sumMultiplyBy10']),
    },
  }
</script>

<style scoped>
  button {
    margin-left: 8px;
  }
</style>
