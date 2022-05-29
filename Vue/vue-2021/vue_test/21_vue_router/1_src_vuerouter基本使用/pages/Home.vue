<template>
  <div>
    <base-content title="我是Home的内容" />
  </div>
</template>

<script>
  import BaseContent from '../components/BaseContent.vue'
  export default {
    name: 'Home',
    components: { BaseContent },
    // mounted() {
    //   console.log('Home')
    // },
  }
</script>