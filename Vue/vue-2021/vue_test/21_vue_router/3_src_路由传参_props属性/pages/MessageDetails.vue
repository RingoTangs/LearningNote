<template>
  <div v-if="$route.params.id && $route.query.title">
    <h5>消息编号: {{ id }}</h5>
    <h5>消息标题: {{ title }}</h5>
  </div>
</template>

<script>
  export default {
    name: 'MessageDetails',
    props: { id: String, title: String },
    mounted() {
      console.log(this)
    },
  }
</script>

<style>
</style>