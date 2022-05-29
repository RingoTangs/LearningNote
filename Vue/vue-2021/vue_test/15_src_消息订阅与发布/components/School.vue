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
  // 订阅消息
  const token = this.$PubSub.subscribe('send-student-name-topic', (msg, data) =>
    console.log(msg, data)
  )
  this.token = token
},
beforeDestroy() {
  // 一定要取消消息订阅！
  this.$PubSub.unsubscribe(this.token)
},
  }
</script>

<style scoped>
  .school {
    background-color: skyblue;
    padding: 20px;
  }
</style>