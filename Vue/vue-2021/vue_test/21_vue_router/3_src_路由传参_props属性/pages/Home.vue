<template>
  <div>
    <base-content title="我是Home的内容" />
    <ul class="nav nav-tabs">
      <li>
        <router-link class="list-group-item route-btn" to="/home/news" active-class="active">
          News
        </router-link>
      </li>
      <li>
        <router-link class="list-group-item route-btn" to="/home/message" active-class="active">
          Message
        </router-link>
      </li>
    </ul>
    <router-view></router-view>
  </div>
</template>

<script>
  import Message from './Message.vue'
  import News from './News.vue'
  import BaseContent from '../components/BaseContent.vue'
  export default {
    name: 'Home',
    components: { BaseContent, Message, News },
  }
</script>

<style scoped>
</style>