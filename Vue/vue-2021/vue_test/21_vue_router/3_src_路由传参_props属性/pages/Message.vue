<template>
  <div class="message">
    <ul>
      <li v-for="msg in msgArr" :key="msg.id">
        <!-- params 必须和 name 一起用, 不能和 path 一起用 -->
        <router-link
          :to="{ name: 'MessageDetails', params: { id: msg.id }, query: { title: msg.title } }"
        >
          <span>消息{{ msg.id }}</span>
        </router-link>
      </li>
    </ul>
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'Message',
    data() {
      return {
        msgArr: [
          { id: '001', title: '消息标题1' },
          { id: '002', title: '消息标题2' },
          { id: '003', title: '消息标题3' },
        ],
      }
    },
  }
</script>

<style scoped>
  li {
    margin: 10px 0;
  }
</style>