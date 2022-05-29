<template>
  <div>
    <h1>选择日</h1>
    <p>以「日」为基本单位，基础的日期选择控件</p>
    <p>
      基本单位由type属性指定。快捷选项需配置picker-options对象中的shortcuts，禁用日期通过
      disabledDate 设置，传入函数
    </p>
    <el-row>
      <p>默认</p>
      <el-date-picker v-model="value1" type="date" placeholder="选择日期" format="yyyy-MM-dd" />
    </el-row>
    <el-row>
      <p>带快捷键</p>
      <el-date-picker
        v-model="value2"
        align="right"
        type="date"
        placeholder="选择日期"
        :picker-options="pickerOptions"
        :editable="false"
        value-format="yyyy-MM-dd HH:mm:ss"
      />
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'BaseDay',
  data() {
    return {
      value1: '',
      value2: '',
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now()
        },
        shortcuts: [
          {
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date())
            }
          },
          {
            text: '昨天',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() - 3600 * 1000 * 24)
              picker.$emit('pick', date)
            }
          },
          {
            text: '一周前',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7)
              picker.$emit('pick', date)
            }
          }
        ]
      }
    }
  }
}
</script>

<style>
</style>
