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
            :to="{ name, params: { userId: nanoid() } }"
            class="list-group-item route-btn"
            exact-active-class="active"
            v-for="(name, index) in btnNames"
            :key="index"
          >
            {{ name }}
          </router-link>
        </div>
      </div>
      <!-- 右边展示的内容 -->
      <router-view class="col-xs-6"></router-view>
    </div>
  </div>
</template>

<script>
  import { nanoid } from 'nanoid'
  import About from './pages/About.vue'
  import Home from './pages/Home.vue'
  import Banner from './components/Banner.vue'

  export default {
    name: 'App',
    components: { About, Home, Banner },
    data() {
      return {
        btnNames: ['About', 'Home'],
        nanoid,
      }
    },
  }
</script>

<style>
  .route-btn {
    transition: all 0.3s linear;
  }
</style>