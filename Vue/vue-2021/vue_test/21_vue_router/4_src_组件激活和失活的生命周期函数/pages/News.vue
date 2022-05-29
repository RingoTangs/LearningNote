<template>
  <div>
    <h5>News</h5>
    <ul>
      <li :style="{ opacity }">欢迎学习Vue</li>
      <li>1 <input type="text" /></li>
      <li>2 <input type="text" /></li>
      <li>3 <input type="text" /></li>
      <li>4 <input type="text" /></li>
      <li>5 <input type="text" /></li>
    </ul>
  </div>
</template>

<script>
  export default {
    name: 'News',
    data() {
      return {
        opacity: 1,
        state: true, // true 代表 1 -> 0, false 代表 0 -> 1
      }
    },
    beforeCreate() {
      console.log('News组件被创建')
    },
    beforeDestroy() {
      console.log('News组件被销毁')
    },
    // 新的生命周期函数, 组件激活时调用该函数
    activated() {
      this.timer = setInterval(() => {
        if (this.opacity <= 0) {
          this.opacity = 0
          this.state = false
        } else if (this.opacity >= 1) {
          this.opacity = 1
          this.state = true
        }
        if (this.state) {
          this.opacity -= 0.01
        } else {
          this.opacity += 0.01
        }
      }, 50)
    },
    // 新的生命周期函数, 组件失活时调用该函数
    deactivated() {
      clearInterval(this.timer)
    },
  }
</script>