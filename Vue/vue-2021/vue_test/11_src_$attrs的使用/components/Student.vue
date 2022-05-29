<template>
  <div>
    <h1>学生名字: {{ $attrs['student-info'].name }}</h1>
    <h1>学生年龄: {{ $attrs['student-info'].age }}</h1>
  </div>
</template>

<script>
  export default {
    name: 'Student',
  }
</script>

<style>
</style>