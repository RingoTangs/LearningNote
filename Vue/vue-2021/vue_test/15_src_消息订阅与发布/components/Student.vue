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
        // 发布消息
        this.$PubSub.publish('send-student-name-topic',  this.name)
      },
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