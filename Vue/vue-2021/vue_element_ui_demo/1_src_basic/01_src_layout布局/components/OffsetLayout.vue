<template>
  <div>
    <h1>分栏偏移</h1>
    <span>通过制定 el-col 组件的 offset 属性可以指定分栏偏移的栏数。</span>
    <el-row :gutter="20">
      <el-col :span="3" :offset="3"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="3" :offset="3"><div class="grid-content bg-purple-light" /></el-col>
      <el-col :span="3" :offset="3"><div class="grid-content bg-purple-dark" /></el-col>
      <el-col :span="3" :offset="3"><div class="grid-content bg-purple" /></el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'OffsetLayout'
}
</script>
