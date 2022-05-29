<template>
  <div>
    <!-- 给子组件传了两个属性, schoolName 被 props 接收了, 就不会出现到 $attrs 中 -->
    <school :student-info="studentInfo" :school-name="schoolName" />
  </div>
</template>

<script>
  import School from './components/School.vue'
  export default {
    name: 'App',
    components: { School },
    data() {
      return {
        studentInfo: {
          name: '李四',
          age: 19,
        },
        schoolName: 'abc',
      }
    },
  }
</script>

<style>
</style>