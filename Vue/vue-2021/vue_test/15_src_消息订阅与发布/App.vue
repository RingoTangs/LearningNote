<template>
  <div class="container">
    <h1 class="title">App.vue</h1>
    <school />
    <student />
  </div>
</template>

<script>
  import School from './components/School.vue'
  import Student from './components/Student.vue'
  export default {
    name: 'App',
    components: { School, Student },
  }
</script>

<style>
  .title {
    text-align: center;
  }
  .container {
    background-color: rgba(0, 0, 0, 0.5);
    padding: 30px;
    border-radius: 8px;
    width: 500px;
  }
</style>