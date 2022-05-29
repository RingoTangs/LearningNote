<template>
  <div>
    <h1>基础链接</h1>
    <el-row>
      <el-col :span="4">
        <el-link href="https://element.eleme.io" target="_blank">默认链接</el-link>
      </el-col>
      <el-col :span="4">
        <el-link href="javascript:void(0)" type="primary">主要链接</el-link>
      </el-col>
      <el-col :span="4">
        <el-link type="success">成功链接</el-link>
      </el-col>
      <el-col :span="4">
        <el-link type="warning">警告链接</el-link>
      </el-col>
      <el-col :span="4">
        <el-link type="danger">危险链接</el-link>
      </el-col>
      <el-col :span="4">
        <el-link type="info">信息链接</el-link>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'BasicLink'
}
</script>

<style lang="scss" scoped>
.el-row {
  .el-col {
    // border: 1px solid red;
    text-align: center;
  }
}
</style>
