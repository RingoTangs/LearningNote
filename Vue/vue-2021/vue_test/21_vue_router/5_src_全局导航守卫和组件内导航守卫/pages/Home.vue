<template>
  <div>
    <base-content title="我是Home的内容" />
    <ul class="nav nav-tabs">
      <li>
        <router-link class="list-group-item route-btn" to="/home/news" active-class="active"> News </router-link>
      </li>
      <li>
        <router-link class="list-group-item route-btn" to="/home/message" active-class="active"> Message </router-link>
      </li>
    </ul>
    <!-- 
      keep-alive 的属性：
        1. include: 只有名称匹配的组件会被缓存。
        2. exclude: 任何名称匹配的组件都不会被缓存。
        3. max:  数字。最多可以缓存多少组件实例。

        当组件在 <keep-alive> 内被切换，它的 activated 和 deactivated 这两个生命周期钩子函数将会被对应执行。
     -->
    <!-- 使用 v-bind 和 数组的形式可以缓存多个组件 -->
    <keep-alive :include="['News', 'Message']">
      <router-view></router-view>
    </keep-alive>
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