<template>
  <div>
    <h1>基础布局</h1>
    <p>
      <span>通过 row 和 col 组件，并通过 col 组件的 span</span>
      <span>属性我们就可以自由地组合布局。</span>
    </p>
    <!-- span 是 24 就代表占满这一行 -->
    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple-dark" />
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="12">
        <div class="grid-content bg-purple" />
      </el-col>
      <el-col :span="12">
        <div class="grid-content bg-purple-light" />
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="8"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="8"><div class="grid-content bg-purple-light" /></el-col>
      <el-col :span="8"><div class="grid-content bg-purple" /></el-col>
    </el-row>

    <el-row>
      <el-col :span="6"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="6"><div class="grid-content bg-purple-light" /></el-col>
      <el-col :span="6"><div class="grid-content bg-purple-dark" /></el-col>
      <el-col :span="6"><div class="grid-content bg-purple" /></el-col>
    </el-row>

    <el-row>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple-light" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple-dark" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple-light" /></el-col>
      <el-col :span="4"><div class="grid-content bg-purple-dark" /></el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'BasicLayout'
}
</script>
