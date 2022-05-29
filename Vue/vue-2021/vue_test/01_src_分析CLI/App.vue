<!-- 
    App 组件是用来汇总所有组件的！
-->
<template>
  <div>
    <school-component />
  </div>
</template>

<script>
// 引入组件
import School from './components/School'

export default {
  name: 'App',
  components: {
    'school-component': School,
  },
}
</script>

<style>
</style>