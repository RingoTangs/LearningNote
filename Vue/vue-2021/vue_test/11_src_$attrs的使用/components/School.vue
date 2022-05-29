<template>
  <div>
    <h1>学校名称: {{ schoolName }}</h1>
    <student v-bind="$attrs" />
  </div>
</template>

<script>
  import Student from './Student.vue'
  export default {
    name: 'School',
    props: { schoolName: String },
    components: { Student },
    mounted() {
      console.log(this)
    },
  }
</script>

<style>
</style>