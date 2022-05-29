<template>
  <div class="own-bottom-line school">
    <h1>学校名称: {{ name }}</h1>
    <h1>学校地址: {{ address }}</h1>
    <button @click="onSendDataToAppBtnClick">点我给App.vue传送数据</button>
  </div>
</template>

<script>
  export default {
    name: 'School',
    data() {
      return {
        name: 'xxx学校',
        address: '北京',
      }
    },
    props: { obtainSchoolNameFunction: Function },
    methods: {
      // 监听按钮的点击事件
      onSendDataToAppBtnClick() {
        // 如果不写 props, 就可以用 $attrs 来接收父组件传进来的值
        // this.$attrs['obtain-school-name-function'](this.name)
        this.obtainSchoolNameFunction(this.name)
      },
    },
  }
</script>

<style scoped>
  .school {
    padding: 10px;
  }
</style>