<template>
  <div class="category">
    <h3>{{ title }}分类</h3>

    <!-- 使用插槽占好位置 -->
    <!-- 当填充内容时, 会直接把这里的 slot 直接被指定组件替换 -->
    <slot>展示一些默认值</slot>
  </div>
</template>

<script>
  export default {
    name: 'Category',
    props: { title: String },
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
</style>