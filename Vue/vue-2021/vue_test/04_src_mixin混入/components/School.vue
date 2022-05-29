<template>
  <div>
    <h2 @click="showName" class="name">学校名称: {{ name }}</h2>
    <h2>学校地址: {{ address }}</h2>
  </div>
</template>

<script>
// 引入一个混入
import { mixin, mixin2 } from '../mixin.js'
export default {
  name: 'School',
  data() {
    return {
      name: 'xxx学校',
      address: '北京',
      x: 8848, // 当混入中的数据和组件中数据冲突后, 以组件中的数据为准
    }
  },
  // 使用混入
  mixins: [mixin, mixin2],
}
</script>

<style scoped>
.name:hover {
  cursor: pointer;
}
</style>