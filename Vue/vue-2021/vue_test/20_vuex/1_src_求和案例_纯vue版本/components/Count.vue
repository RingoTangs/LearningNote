<template>
  <div>
    <h1>当前求和为: {{ sum }}</h1>
    <div class="ops">
      <select v-model.number="n">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
      </select>
      <button @click="sum += n">+</button>
      <button @click="sum -= n">-</button>
      <button @click="sum += sum % 2 ? n : 0">当前求和为奇数再加</button>
      <button @click="incrementWait">等一等再加</button>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Count',
    data() {
      return {
        n: 1, // 用户选择的数字
        sum: 0, // 当前的和
      }
    },
    methods: {
      incrementWait() {
        setTimeout(() => (this.sum += this.n), 1000)
      },
    },
  }
</script>

<style scoped>
  button {
    margin-left: 8px;
  }
</style>