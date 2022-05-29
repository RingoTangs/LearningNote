<template>
  <div>
    <h1 style="text-align: center">使用默认插槽</h1>
    <div class="container1">
      <category title="美食">
        <img src="./assets/food.jpg" alt="" />
      </category>
      
      <category title="游戏">
        <ul>
          <li v-for="(game, index) in games" :key="index">
            {{ game }}
          </li>
        </ul>
      </category>

      <category title="电影"> 
        <video controls src="http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"></video>
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
    height: 180px;
  }

  video {
    width: 100%;
  }
</style>