<template>
  <div>
    <h1>基础Radio</h1>
    <p>由于选项默认可见，不宜过多，若选项过多，建议使用 Select 选择器。</p>
    <p>
      要使用 Radio 组件，只需要设置v-model绑定变量，选中意味着变量的值为相应 Radio
      label属性的值，label可以是String、Number或Boolean。
    </p>

    <el-row>
      <el-col :span="1">
        <el-radio v-model.number="sex" :label="1">男</el-radio>
      </el-col>
      <el-col :span="1">
        <el-radio v-model.number="sex" :label="2">女</el-radio>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'BasicRadio',
  data() {
    return {
      sex: 1
    }
  }
}
</script>
