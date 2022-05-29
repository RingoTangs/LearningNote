<template>
  <div>
    <h1>基础用法</h1>

    <p>默认按钮</p>
    <el-row>
      <el-button>默认按钮</el-button>
      <el-button type="primary">主要按钮</el-button>
      <el-button type="success">成功按钮</el-button>
      <el-button type="info">信息按钮</el-button>
      <el-button type="warning">警告按钮</el-button>
      <el-button type="danger">危险按钮</el-button>
    </el-row>

    <p>朴素按钮</p>
    <el-row>
      <el-button plain>朴素按钮</el-button>
      <el-button type="primary" plain>主要按钮</el-button>
      <el-button type="success" plain>成功按钮</el-button>
      <el-button type="info" plain>信息按钮</el-button>
      <el-button type="warning" plain>警告按钮</el-button>
      <el-button type="danger" plain>危险按钮</el-button>
    </el-row>

    <p>圆角按钮</p>
    <el-row>
      <el-button round>圆角按钮</el-button>
      <el-button type="primary" round>主要按钮</el-button>
      <el-button type="success" round>成功按钮</el-button>
      <el-button type="info" round>信息按钮</el-button>
      <el-button type="warning" round>警告按钮</el-button>
      <el-button type="danger" round>危险按钮</el-button>
    </el-row>

    <p>朴素圆角按钮</p>
    <el-row>
      <el-button round>圆角按钮</el-button>
      <el-button type="primary" round plain>主要按钮</el-button>
      <el-button type="success" round plain>成功按钮</el-button>
      <el-button type="info" round plain>信息按钮</el-button>
      <el-button type="warning" round plain>警告按钮</el-button>
      <el-button type="danger" round plain>危险按钮</el-button>
    </el-row>

    <p>圆形按钮</p>
    <el-row>
      <el-button icon="el-icon-search" circle />
      <el-button type="primary" icon="el-icon-edit" circle />
      <el-button type="success" icon="el-icon-check" circle />
      <el-button type="info" icon="el-icon-message" circle />
      <el-button type="warning" icon="el-icon-star-off" circle />
      <el-button type="danger" icon="el-icon-delete" circle />
    </el-row>

    <p>朴素圆形按钮</p>
    <el-row>
      <el-button icon="el-icon-search" circle />
      <el-button type="primary" icon="el-icon-edit" plain circle />
      <el-button type="success" icon="el-icon-check" plain circle />
      <el-button type="info" icon="el-icon-message" plain circle />
      <el-button type="warning" icon="el-icon-star-off" plain circle />
      <el-button type="danger" icon="el-icon-delete" plain circle />
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'BasicButton'
}
</script>

<style lang="scss" scoped>
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
  .el-button {
    box-shadow: inset 0 0 5px 1px rgb(0, 0, 0, 0.2);
  }
  .is-circle {
    box-shadow: none !important;
  }
}
</style>
