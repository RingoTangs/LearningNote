<template>
  <div>
    <h1>Hello</h1>
    <button @click="onGetInfoBtnClick">获取信息</button>
  </div>
</template>

<script>
  export default {
    name: 'App',
    methods: {
      onGetInfoBtnClick() {
        this.$http('GET', '/couple/days').then((data) => console.log(data))
      },
    },
  }
</script>

<style>
</style>