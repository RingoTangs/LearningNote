<template>
  <div>
    <h1>基础checkbox</h1>
    <p>
      在el-checkbox元素中定义v-model绑定变量，单一的checkbox中，默认绑定变量的值会是Boolean，选中为true。
    </p>
    <el-row class="cb">
      <el-checkbox v-model="agreed">同意该协议</el-checkbox>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'BasicCheckbox',
  data() {
    return {
      agreed: false
    }
  }
}
</script>

<style scoped>
.cb {
  --color: purple;
}

.cb >>> .el-checkbox__input.is-checked + .el-checkbox__label {
  color: var(--color);
}

.cb >>> .el-checkbox__input.is-checked .el-checkbox__inner,
.el-checkbox__input.is-indeterminate .el-checkbox__inner {
  background-color: var(--color);
  border-color: var(--color);
}
</style>

