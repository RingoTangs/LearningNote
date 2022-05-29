<template>
  <div>
    <h1 style="text-align: center">使用具名插槽</h1>
    <div class="container1">
      <!-- 美食组件 -->
      <category title="美食">
        <!-- 注意 v-slot 只能添加在 <template> 上 -->
        <template v-slot:main>
          <img src="./assets/food.jpg" alt="" />
        </template>
        <template v-slot:footer>
          <div class="footer">
            <a href="javascript:void(0)">更多美食</a>
          </div>
        </template>
      </category>

      <!-- 游戏组件 -->
      <category title="游戏">
        <template v-slot:main>
          <ul>
            <li v-for="(game, index) in games" :key="index">
              {{ game }}
            </li>
          </ul>
        </template>
        <template v-slot:footer>
          <div class="footer">
            <a href="javascript:void(0)">单机游戏</a>
            <a href="javascript:void(0)">网络游戏</a>
          </div>
        </template>
      </category>

      <!-- 电影组件 -->
      <category title="电影">
        <template v-slot:main>
          <video controls src="http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"></video>
        </template>
        <template v-slot:footer>
          <div class="footer">
            <a href="javascript:void(0)">经典</a>
            <a href="javascript:void(0)">热门</a>
            <a href="javascript:void(0)">推荐</a>
          </div>
          <h4>欢迎前来观影</h4>
        </template>
      </category>
    </div>
  </div>
</template>

<script>
  import Category from './components/Category'
  export default {
    name: 'App',
    data() {
      return {
        games: ['红色警戒', '穿越火线', '魔兽世界', 'DNF'],
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