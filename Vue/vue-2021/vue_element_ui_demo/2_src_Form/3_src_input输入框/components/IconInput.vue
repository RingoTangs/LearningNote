<template>
  <div>
    <h1>带有 Icon 的输入框</h1>

    <!-- 属性方式 -->
    <el-row :gutter="10">
      <el-col :span="2" class="tip"> 属性方式：</el-col>
      <el-col :span="5">
        <el-input v-model="input1" placeholder="请选择日期" suffix-icon="el-icon-date" />
      </el-col>
      <el-col :span="5">
        <el-input v-model="input2" placeholder="请输入内容" prefix-icon="el-icon-search" />
      </el-col>
    </el-row>

    <!-- 插槽方式 -->
    <el-row :gutter="10">
      <el-col :span="2" class="tip"> slot方式：</el-col>
      <el-col :span="5">
        <el-input v-model="input1" placeholder="请选择日期">
          <template v-slot:suffix>
            <i class="el-input__icon el-icon-search"></i>
          </template>
        </el-input>
      </el-col>
      <el-col :span="5">
        <el-input v-model="input2" placeholder="请输入内容">
          <template v-slot:prefix>
            <i class="el-input__icon el-icon-search"></i>
          </template>
        </el-input>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'IconInput',
  data() {
    return {
      input1: '',
      input2: ''
    }
  }
}
</script>

<style lang="scss" scoped>
.el-row {
  margin-bottom: 10px;
  &:last-child {
    margin-bottom: 0;
  }
  .tip {
    text-align: center;
    line-height: 40px;
  }
}
</style>
