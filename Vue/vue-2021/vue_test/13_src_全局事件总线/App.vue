<template>
  <div class="container">
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
  .container {
    background-color: rgba(0, 0, 0, 0.5);
    padding: 30px;
    border-radius: 8px;
  }
</style>