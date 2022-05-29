<template>
  <div>
    <h1>多选框组</h1>
    <p>
      <span>checkbox-group元素能把多个 checkbox 管理为一组，</span>
      <span>只需要在 Group 中使用v-model绑定Array类型的变量即可。</span>
    </p>
    <p>
      <span>el-checkbox 的 label属性是该 checkbox 对应的值，若该标签中无内容，</span>
      <span>则该属性也充当 checkbox 按钮后的介绍</span>
    </p>
    <p>label与数组中的元素值相对应，如果存在指定的值则为选中状态，否则为不选中。</p>

    <el-checkbox-group v-model="hobbies">
      <el-checkbox label="smoking">抽烟</el-checkbox>
      <el-checkbox label="drinking">喝酒</el-checkbox>
      <el-checkbox label="firing">烫头</el-checkbox>
      <el-checkbox label="play" disabled>玩</el-checkbox>
    </el-checkbox-group>
  </div>
</template>

<script>
export default {
  name: 'CheckboxGroup',
  data() {
    return {
      hobbies: ['play']
    }
  }
}
</script>
