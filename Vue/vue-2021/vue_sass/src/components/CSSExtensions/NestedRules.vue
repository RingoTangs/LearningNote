<template>
  <div>
    <h1>1.嵌套规则</h1>
    <div class="main">
      <p>
        <span>Sass 允许将一套 CSS 样式嵌套进另一套样式中，</span>
        <span>内层的样式将它外层的选择器作为父选择器，例如：</span>
      </p>
      <div id="main">
        <p>
          你好
          <span class="red-box">Hello World</span>
        </p>
      </div>
      <p>嵌套功能避免了重复输入父选择器，而且令复杂的 CSS 结构更易于管理</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'NestedRules'
}
</script>

<style lang="scss" scoped>
#main p {
  color: #00ff00;
  .red-box {
    background-color: skyblue;
    color: #000000;
  }
}
</style>
