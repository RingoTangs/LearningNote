<template>
  <div>
    <h1 style="text-align: center">不使用插槽</h1>
    <div class="container1">
      <category title="美食" :list-data="foods" />
      <category title="游戏" :list-data="games" />
      <category title="电影" :list-data="films" />
    </div>
  </div>
</template>

<script>
  import Category from './components/Category'
  export default {
    name: 'App',
    data() {
      return {
        foods: ['火锅', '烧烤', '小龙虾', '牛排'],
        games: ['红色警戒', '穿越火线', '魔兽世界', 'DNF'],
        films: ['让子弹飞', '大话西游'],
      }
    },
    components: { Category },
  }
</script>

<style>
  * {
    margin: 0;
    padding: 0;
  }

  .container1 {
    display: flex;
    justify-content: space-around;
    border-bottom: 2px solid black;
    padding-bottom: 10px;
    margin: 20px 0;
  }
</style>