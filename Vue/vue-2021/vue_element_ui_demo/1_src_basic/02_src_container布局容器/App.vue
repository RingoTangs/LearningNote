<template>
  <div id="app">
    <common-layout class="box-line" />
  </div>
</template>

<script>
import CommonLayout from '@/components/CommonLayout.vue'
export default {
  name: 'App',
  components: { CommonLayout }
}
</script>

<style lang="scss">
.el-container {
  color: #333;
  text-align: center;

  .el-header,
  .el-footer {
    background-color: #b3c0d1;
    line-height: 60px;
  }

  .el-main {
    background-color: #e9eef3;
    line-height: 160px;
  }

  .el-aside {
    background-color: #d3dce6;
    text-align: center;
  }
}
</style>
