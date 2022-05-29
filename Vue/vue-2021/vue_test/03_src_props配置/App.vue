<template>
  <div>
    <!-- 
      配置项: props
        1. 传数据 <student name="xxx" :age="19" /> 
           不加 v-bind 全是字符串, 加了 v-bind 就按 js 表达式来解析
        2. 接收数据:
           (1) 方式一:
               props: ['name', 'age', 'gender'],
           (2) 方式二:
               props: {
                 name: String,
                 age: Number,
               }
           (3) 方式三:
               props: {
                 name: {
                   type: String,
                   required: false,
                   default: 'Hello'
                 }
               }
        
        3. 备注: props 是只读的, Vue 底层会检测对 props 的修改, 如果进行了修改, 会有错误警告.
            如果业务需求确实需要修改传入的数据, 复制 props 的内容到 data 一份, 然后去修改 data
            中的数据.(特别注意: props 的数据优先加载, data 中就可以拿到 props 中的数据了！)
     -->
    <student name="张三" :age="19" :gender="'女'" />
  </div>
</template>

<script>
import Student from './components/Student.vue'
export default {
  name: 'App',
  components: {
    Student,
  },
}
</script>

<style>
</style>