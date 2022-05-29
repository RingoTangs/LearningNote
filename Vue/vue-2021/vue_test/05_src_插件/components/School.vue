<template>
  <div>
    <h2 v-font:color="'red'" v-font:fontSize="'30px'">学校名称: {{ name | sliceStr }}</h2>
    <h2>学校地址: {{ address }}</h2>
    <button @click="$hello">点我使用Vue原型对象上的$hello方法</button>
  </div>
</template>

<script>
export default {
  name: 'School',
  data() {
    return {
      name: 'xxx学校',
      address: '北京',
    }
  },
}
</script>
