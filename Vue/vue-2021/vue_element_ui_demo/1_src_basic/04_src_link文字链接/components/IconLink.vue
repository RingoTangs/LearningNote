<template>
  <div>
    <h1>带有图标的文字链接</h1>
    <el-row>
      <el-col :span="2">
        <el-link icon="el-icon-edit">编辑</el-link>
      </el-col>

      <el-col :span="2">
        <el-link :underline="false">
          查看
          <i class="el-icon-view el-icon--right" />
        </el-link>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'IconLink'
}
</script>

<style lang="scss" scoped>
</style>
