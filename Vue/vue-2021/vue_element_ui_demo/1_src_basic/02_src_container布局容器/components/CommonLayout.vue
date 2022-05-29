<template>
  <div>
    <h1>常见页面布局</h1>
    <el-container class="box-line">
      <el-header>Header</el-header>
      <el-main>Main</el-main>
    </el-container>

    <el-container class="box-line">
      <el-header>Header</el-header>
      <el-main>Main</el-main>
      <el-footer>Footer</el-footer>
    </el-container>

    <el-container class="box-line">
      <el-aside width="200px">Aside</el-aside>
      <el-main>Main</el-main>
    </el-container>

    <el-container class="box-line">
      <el-header>Header</el-header>
      <el-container>
        <el-aside width="200px">Aside</el-aside>
        <el-main>Main</el-main>
      </el-container>
    </el-container>

    <el-container class="box-line">
      <el-header>Header</el-header>
      <el-container>
        <el-aside width="200px">Aside</el-aside>
        <el-container>
          <el-main>Main</el-main>
          <el-footer>Footer</el-footer>
        </el-container>
      </el-container>
    </el-container>

    <el-container class="box-line">
      <el-aside width="200px">Aside</el-aside>
      <el-container>
        <el-header>Header</el-header>
        <el-main>Main</el-main>
      </el-container>
    </el-container>

    <el-container>
      <el-aside width="200px">Aside</el-aside>
      <el-container>
        <el-header>Header</el-header>
        <el-main>Main</el-main>
        <el-footer>Footer</el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'CommonLayout'
}
</script>
