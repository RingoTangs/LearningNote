<template>
  <div v-if="$route.params.id && $route.query.title">
    <h5>消息编号: {{ $route.params.id }}</h5>
    <h5>消息标题: {{ $route.query.title }}</h5>
  </div>
</template>

<script>
  export default {
    name: 'MessageDetails',
  }
</script>

<style>
</style>