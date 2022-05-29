<template>
  <div>
    <h1>对齐方式</h1>
    <p>
      <span>将 el-row 的 type 属性赋值为 'flex'，可以启用 flex 布局。</span>
      <span>
        并可通过 justify 属性来指定 start, center, end, space-between, space-around
        其中的值来定义子元素的排版方式。
      </span>
    </p>

    <!-- type="flex" -->
    <el-row class="row-bg" type="flex">
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
    </el-row>

    <p>type="flex" justify="center"</p>

    <!-- type="flex" justify="center" -->
    <el-row class="row-bg" type="flex" justify="center">
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
    </el-row>

    <p>type="flex" justify="start"</p>

    <!-- type="flex" justify="start" -->
    <el-row class="row-bg" type="flex" justify="start">
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
    </el-row>

    <p>type="flex" justify="end"</p>

    <!-- type="flex" justify="end" -->
    <el-row class="row-bg" type="flex" justify="end">
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
    </el-row>

    <p>type="flex" justify=""space-between</p>

    <!-- type="flex" justify="space-between" -->
    <el-row class="row-bg" type="flex" justify="space-between">
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
    </el-row>

    <p>type="flex" justify="space-around"</p>

    <!-- type="flex" justify="space-around" -->
    <el-row class="row-bg" type="flex" justify="space-around">
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4" :push="1"><div class="grid-content bg-purple" /></el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'AlignLayout'
}
</script>

<style lang="scss" scoped>
.el-row {
  margin-top: 20px;
}
</style>
