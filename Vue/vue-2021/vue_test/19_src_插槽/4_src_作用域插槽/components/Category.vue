<template>
  <div class="category">
    <h3>{{ title }}分类</h3>

    <!-- 使用插槽占好位置 -->
    <!-- 当填充内容时, 会直接把这里的 slot 直接被指定组件替换 -->
    <!-- 数据在 Category 组件中, 要让父组件通过插槽拿到数据, 可以使用 v-bind:xxx -->
    <slot name="main" :games="games" x="hello world" :y="123" z="123"> main </slot>
  </div>
</template>

<script>
  export default {
    name: 'Category',
    props: { title: String },
    data() {
      return {
        games: ['红色警戒', '穿越火线', '魔兽世界', 'DNF'],
      }
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
</style>