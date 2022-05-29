<template>
  <div>
    <h1>App</h1>
    <count />
    <count />
  </div>
</template>

<script>
  import Count from './components/Count.vue'
  export default {
    name: 'App',
    components: { Count },
  }
</script>

<style>
</style>