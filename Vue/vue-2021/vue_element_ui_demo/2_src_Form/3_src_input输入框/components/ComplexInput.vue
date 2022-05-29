<template>
  <div class="complex-input">
    <h1>复合型输入框</h1>
    <el-input ref="input1" placeholder="请输入内容">
      <template v-slot:prepend>Http://</template>
    </el-input>

    <el-input placeholder="请输入内容">
      <template v-slot:append>.com</template>
    </el-input>

    <el-input placeholder="请输入内容">
      <el-select slot="prepend" v-model.number="select" placeholder="请选择">
        <el-option label="餐厅名" :value="1"></el-option>
        <el-option label="订单号" :value="2"></el-option>
        <el-option label="用户电话" :value="3"></el-option>
      </el-select>
      <el-button slot="append" icon="el-icon-search"></el-button>
    </el-input>
  </div>
</template>

<script>
export default {
  name: 'ComplexInput',
  data() {
    return {
      select: undefined
    }
  }
}
</script>

<style lang="scss" scoped>
div[class~='complex-input'] {
  width: 500px;
  .el-input {
    margin-bottom: 10px;
    &:last-child {
      margin-bottom: 0;
    }
    .el-select {
      width: 130px;
    }
  }
}
</style>
