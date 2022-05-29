<template>
  <div>
    <student />
    <school />
  </div>
</template>

<script>
import Student from './components/Student.vue'
import School from './components/School.vue'
export default {
  name: 'App',
  components: {
    Student,
    School,
  },
}
</script>

<!-- 一般来说 App.vue 中 style 不加 scoped, 样式全局通用 -->
<!-- scoped 让样式在局部生效, 防止冲突 -->
<style>
.title {
  color: bisque;
}
</style>