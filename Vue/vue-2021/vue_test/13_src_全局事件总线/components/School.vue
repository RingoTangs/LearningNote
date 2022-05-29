<template>
  <div class="school">
    <h1>{{ name }}</h1>
  </div>
</template>

<script>
  export default {
    name: 'School',
    data() {
      return {
        name: 'xxx学校',
      }
    },
    mounted() {
      // 拿到全局消息总线监听消息
      this.$bus.$on('send-student-name', (value) => console.log(value))
    },
    beforeDestroy() {
      // 因为 bus 事件总线一直要用不会进行销毁
      // 所以一定要记得在这里要对自定义事件解绑
      this.$bus.$off('send-student-name')
    },
  }
</script>

<style scoped>
  .school {
    background-color: skyblue;
    padding: 20px;
  }
</style>