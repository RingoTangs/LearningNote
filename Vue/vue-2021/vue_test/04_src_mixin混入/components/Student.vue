<template>
  <div>
    <h2 @click="showName" class="pointer">学生姓名: {{ name }}</h2>
    <h2 @click="showAge" class="pointer">学生年龄: {{ age }}</h2>
  </div>
</template>

<script>
import { mixin, mixin2 } from '../mixin.js'
export default {
  name: 'Student',
  data() {
    return {
      name: '张三',
      age: 18,
    }
  },
  methods: {
    showAge(event) {
      alert('age = ' + this.age)
    },
  },
  // 混入中的生命周期函数与组件的生命周期函数冲突时, 将两个函数合并, 并且混入的生命周期函数优先执行
  mounted() {
    console.log('Hello World！！！！')
  },
  mixins: [mixin, mixin2],
}
</script>

<style scoped>
.pointer:hover {
  cursor: pointer;
}
</style>