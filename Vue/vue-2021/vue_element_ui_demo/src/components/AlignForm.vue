<template>
  <div>
    <h1>表单域对齐方式</h1>
    <p>
      通过设置 label-position 属性可以改变表单域标签的位置，可选值为 top、left，当设为 top
      时标签会置于表单域的顶部
    </p>

    <el-radio-group v-model="labelPosition" size="mini">
      <el-radio-button label="left">左对齐</el-radio-button>
      <el-radio-button label="right">右对齐</el-radio-button>
      <el-radio-button label="top">顶部对齐</el-radio-button>
    </el-radio-group>

    <el-form :model="formLabelAlign" label-width="80px" :label-position="labelPosition">
      <el-form-item label="名称">
        <el-input v-model="formLabelAlign.name" placeholder="请输入名称" />
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'AlignForm',
  data() {
    return {
      labelPosition: 'right',
      formLabelAlign: {
        name: '',
        region: '',
        type: ''
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.el-form {
  width: 70%;
  margin-top: 20px;
  border: 1px solid red;
  padding: 20px;
}
</style>
