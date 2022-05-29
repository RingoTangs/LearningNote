<template>
  <div>
    <h1>分栏间隔</h1>
    <p>Row 组件 提供 gutter 属性来指定每一栏之间的间隔，默认间隔为 0。</p>
    <p>设置 :gutter="40" 本质上是给每个 el-col 添加 padding-left 和 padding-right 20px</p>
    <el-row :gutter="40">
      <el-col :span="6"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="10"><div class="grid-content bg-purple-light" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple-dark" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'GutterLayout'
}
</script>
