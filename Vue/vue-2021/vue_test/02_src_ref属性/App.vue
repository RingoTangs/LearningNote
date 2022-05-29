<template>
  <div>
    <!-- 
        1. ref 被用来给元素或子组件注册引用信息。引用信息将会注册在父组件的 $refs 对象上。
        
        2. ref的使用: 
            (1) 如果在普通的 DOM 元素上使用，引用指向的就是 DOM 元素；
            (2) 如果用在子组件上，引用就指向组件实例。

        3. 使用方式:
            第一步: 打标识。<h1 ref="xxx">...</h1> 或 <school ref="school"></school>     
            第二步: 获取 this.$refs.xxx
    -->
    <h1 id="title" ref="title">{{ msg }}</h1>
    <button @click="outputDOMElement">点我输出上方DOM元素</button>
    <!-- 自闭合标签在非 CLI 环境下会出现问题 -->
    <school ref="school" id="school" />
    <school />
    <school />
  </div>
</template>

<script>
// 引入 school 组件
import School from './components/School.vue'
export default {
  name: 'App',
  data() {
    return {
      msg: 'Hello World',
    }
  },
  methods: {
    outputDOMElement(event) {
      // 这里的 this 是 vc, 即: VueComponent
      console.log(this)
      console.log(this.$refs)
      // 拿到 DOM 元素
      console.log(this.$refs.title)
      // ref 写在组件上就可以拿到子组件
      let schoolComponent = this.$refs.school
      // 获取子组件中 data 的值
      console.log(schoolComponent.name, schoolComponent.address)
      // 修改子组件中 data 的值
      schoolComponent.name = '123'
      // id 写在组件上, 拿到的就是 DOM 元素了
      console.log(document.getElementById('school'))
    },
  },
  components: {
    School,
  },
}
</script>

<style scoped>
</style>