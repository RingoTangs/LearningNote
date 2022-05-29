<template>
  <div>
    <h1>基础用法</h1>
    <p>Transfer 的数据通过 data 属性传入。</p>
    <el-transfer v-model="value" :data="data" />
  </div>
</template>

<script>
import { nanoid } from 'nanoid'
export default {
  name: 'BaseTransfer',
  data() {
    return {
      data: this.generateData(),
      value: []
    }
  },
  methods: {
    generateData: () => {
      const data = []
      for (let i = 1; i <= 15; ++i) {
        data.push({ key: nanoid(), label: `备选项 ${i}` })
      }
      return data
    }
  }
}
</script>

<style>
</style>
