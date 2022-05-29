<template>
  <div>
    <!-- 
      mixin(混入)
        1. 功能：可以把多个组件公用的配置提取成一个混入对象。
        2. 使用方式:
            (1) 第一步: 定义混入, 例如:
              {
                data() {....},
                methods: {....},
              }
            (2) 第二步: 使用混入, 例如:
              i.  局部混入: mixins: ['xxx']
              ii. 全局混入: Vue.mixin({...})
        3. 选项合并
            (1) 数据对象在内部会进行递归合并，并在发生冲突时以组件数据优先。
            (2) 同名钩子函数将合并为一个数组，因此都将被调用。另外，混入对象的钩子将在组件自身钩子之前调用。
            (3) 值为对象的选项，例如 methods、components 和 directives，将被合并为同一个对象。两个对象键名冲突时，取组件对象的键值对。
     -->
    <student />
    <school />
  </div>
</template>

<script>
import Student from './components/Student.vue'
import School from './components/School.vue'
export default {
  name: 'App',
  components: {
    Student,
    School,
  },
}
</script>

<style>
</style>