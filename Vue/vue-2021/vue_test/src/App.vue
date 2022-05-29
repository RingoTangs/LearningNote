<template>
  <div>
    <!-- 标题部分 -->
    <banner />

    <!-- 主体 -->
    <div class="row">
      <!-- 左边按钮 -->
      <div class="col-xs-2 col-xs-offset-2">
        <div class="list-group">
          <router-link
            :to="item.path"
            class="list-group-item route-btn"
            active-class="active"
            v-for="(item, index) in btnInfos"
            :key="index"
          >
            {{ item.name }}
          </router-link>
        </div>
      </div>
      <!-- 右边展示的内容 -->
      <router-view class="col-xs-6"></router-view>
    </div>
  </div>
</template>

<script>
  import About from 'pages/About.vue'
  import Home from 'pages/Home.vue'
  import Banner from 'components/Banner.vue'

  export default {
    name: 'App',
    components: { About, Home, Banner },
    data() {
      return {
        btnInfos: [
          { name: 'About', path: '/about' },
          { name: 'Home', path: '/home' },
        ],
      }
    },
  }
</script>

<style>
  .route-btn {
    transition: all 0.3s linear;
  }
</style>