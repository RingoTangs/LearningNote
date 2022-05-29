<template>
  <div>
    <h1>基础用法</h1>
    <el-select v-model="value" placeholder="请选择" clearable>
      <el-option v-for="op in options" :key="op.value" :label="op.label" :value="op.value">
        <template v-slot:default>
          <span>{{ op.label }}</span>
          <span>({{ op.value }})</span>
        </template>
      </el-option>
    </el-select>
  </div>
</template>

<script>
export default {
  name: 'BasicSelect',
  data() {
    return {
      options: [
        { value: 'op1', label: '黄金糕' },
        { value: 'op2', label: '双皮奶' },
        { value: 'op3', label: '蚵仔煎' },
        { value: 'op4', label: '龙须面' },
        { value: 'op5', label: '北京烤鸭' }
      ],
      value: ''
    }
  }
}
</script>

<style lang="scss" scoped>
.options-content {
  height: 100px;
  // display: flex;
  // flex-direction: column;
  // justify-content: space-center;
}
</style>
