<template>
  <div class="student">
    <h2>学生姓名: {{ name }}</h2>
    <h2>学生年龄: {{ age }}</h2>
    <button @click="sendData">点我发送学生姓名给App.vue</button>
    <button @click="unbind">点我解绑自定义事件</button>
  </div>
</template>

<script>
  export default {
    name: 'Student',
    data() {
      return {
        name: '张三',
        age: 18,
      }
    },
    methods: {
      sendData() {
        this.$emit('send-student-name', this.name)
      },
      unbind() {
        this.$off('send-student-name')
      },
    },
  }
</script>

<style scoped>
  .student {
    background-color: pink;
  }
  .student:hover {
    cursor: pointer;
  }
</style>