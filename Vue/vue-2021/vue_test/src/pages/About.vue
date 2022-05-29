<template>
  <div>
    <base-content title="我是About的内容" />
  </div>
</template>

<script>
  import BaseContent from '../components/BaseContent.vue'
  export default {
    name: 'About',
    components: { BaseContent },
    // 通过路由规则, 进入该组件时调用
    // 不！能！获取组件实例 `this`
    beforeRouteEnter(to, from, next) {
      console.log('beforeRouteEnter')
      next()
    },

    // beforeRouteUpdate(to, from, next) {
    //   console.log('beforeRouteUpdate');
    //   next()
    // },

    // 通过路由规则, 离开该组件时调用
    // 可以访问组件实例 `this`
    // 这个离开守卫通常用来禁止用户在还未保存修改前突然离开。该导航可以通过 next(false) 来取消。
    beforeRouteLeave(to, from, next) {
      console.log('beforeRouteLeave')
      next()
    },
  }
</script>
