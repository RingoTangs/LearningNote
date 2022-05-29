<template>
  <div class="category">
    <h3>{{ title }}分类</h3>
    <ul>
      <li v-for="(item, index) in listData" :key="index">{{ item }}</li>
    </ul>
  </div>
</template>

<script>
  export default {
    name: 'Category',
    props: {
      title: String,
      listData: Array,
    },
  }
</script>

<style scoped>
  .category {
    background-color: skyblue;
    width: 200px;
    height: 200px;
  }

  h3 {
    text-align: center;
    background-color: orange;
  }

  li {
    margin: 10px 0 10px 30px;
  }
</style>