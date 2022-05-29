<template>
  <div>
    <h1>当前求和为: {{ sum }}</h1>
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
  import { mapMutations, mapActions, mapState } from 'vuex'
  export default {
    name: 'Count',
    data() {
      return {
        n: 1, // 用户选择的数字
      }
    },
    methods: {
      // 将 this.INCREMENT(n) 映射为 this.$store.commit('INCREMENT', n)
      ...mapMutations(['INCREMENT', 'DECREMENT']),
      // 将 this.sumIsOddThenAdd(n) 映射为 this.$store.dispatch('sumIsOddThenAdd', n)
      ...mapActions(['sumIsOddThenAdd', 'incrementAwait']),
    },
    computed: {
      // this.sum 映射为 this.$store.state.sum
      ...mapState(['sum']),
    },
  }
</script>

<style scoped>
  button {
    margin-left: 8px;
  }
</style>
