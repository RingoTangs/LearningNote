<template>
  <div class="student">
    <h1>{{ name }}</h1>
    <button @click="sendName">发送学生的名字</button>
  </div>
</template>

<script>
  export default {
    name: 'Student',
    data() {
      return {
        name: '张三',
      }
    },
    methods: {
      sendName() {
        // 拿到全局消息总线发送消息
        this.$bus.$emit('send-student-name', this.name)
      },
    },
    mounted() {
      console.log('Student', this.$EventBus)
    },
  }
</script>

<style  scoped>
  .student {
    margin-top: 10px;
    background-color: pink;
    padding: 20px;
  }
</style>