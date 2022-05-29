<template>
  <div>
    <h1>行内表单</h1>
    <p>设置 inline 属性可以让表单域变为行内的表单域</p>
    <el-form :inline="true" :model="formInline">
      <el-form-item label="审批人">
        <el-input v-model.trim="formInline.user" placeholder="请填写审批人" />
      </el-form-item>

      <el-form-item label="活动区域">
        <el-select v-model="formInline.region" placeholder="活动区域">
          <el-option label="北京" value="beijing" />
          <el-option label="上海" value="shanghai" />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onQueryBtnClick(formInline)">查询</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'InlineForm',
  data() {
    return {
      formInline: {
        user: '',
        region: ''
      }
    }
  },
  methods: {
    onQueryBtnClick(formInline) {
      console.log(formInline)
    }
  }
}
</script>

<style style="scss" scoped>
</style>
