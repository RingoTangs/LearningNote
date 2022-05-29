<template>
  <div>
    <h1>按钮样式的单选框组</h1>
    <p>只需要把el-radio元素换成el-radio-button元素即可，此外，Element 还提供了size属性。</p>
    <p>单选框组可以改变选中时的颜色, 特性比较多，建议使用 el-radio-group</p>
    <el-row>
      <el-radio-group v-model="direct" text-color="#fff" fill="#000" @change="onRadioBtnChange">
        <el-radio-button label="left">左边</el-radio-button>
        <el-radio-button label="middle">中间</el-radio-button>
        <el-radio-button label="right">右边</el-radio-button>
      </el-radio-group>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'ButtonStyleRadioGroup',
  data() {
    return {
      direct: 'left'
    }
  },
  methods: {
    onRadioBtnChange(value) {
      console.log(value)
    }
  }
}
</script>

