<template>
  <div>
    <h1 style="text-align: center">使用作用域插槽</h1>
    <div class="container1">
      <!-- 以下3个组件数据都是一样的, 只是结构不一样！ -->

      <!-- 游戏组件1 -->
      <category title="游戏">
        <!-- v-slot:main="{ games }" 这里使用了ES6 解构表达式 -->
        <template v-slot:main="{ games, x, y, z }">
          <ul>
            <li v-for="(game, index) in games" :key="index">
              {{ game }}
            </li>
          </ul>
          {{ typeof x }}
          {{ typeof y }}
          {{ typeof z }}
        </template>
      </category>

      <!-- 游戏组件2 -->
      <category title="游戏">
        <!-- 具名插槽也有缩写, v-slot <==> # -->
        <template #main="{ games }">
          <ol>
            <li v-for="(game, index) in games" :key="index">
              {{ game }}
            </li>
          </ol>
        </template>
      </category>

      <!-- 游戏组件3 -->
      <category title="游戏">
        <!-- 旧版具名插槽和作用域插槽写法 -->
        <template slot="main" slot-scope="{ games }">
          <h5 v-for="(game, index) in games" :key="index">
            {{ game }}
          </h5>
        </template>
      </category>
    </div>
  </div>
</template>

<script>
  import Category from './components/Category'
  export default {
    name: 'App',
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
    /* border: 1px solid red; */
  }

  li {
    margin: 10px 0 10px 30px;
  }

  img {
    width: 200px;
    height: 150px;
  }

  video {
    width: 100%;
  }

  .footer {
    display: flex;
    justify-content: space-around;
  }

  h4 {
    text-align: center;
  }
</style>