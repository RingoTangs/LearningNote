<template>
  <div>
    <h1>1.变量(Variables)</h1>
    <p>SassScript 最普遍的用法就是变量，变量以美元符号开头，赋值方法与 CSS 属性的写法一样。</p>
    <p>
      <span>变量支持块级作用域，嵌套规则内定义的变量只能在嵌套规则内使用（局部变量），不在嵌套规则内定义的变量则可在任何地方使用（全局变量）。</span>
    </p>
    <p>
      <span>将局部变量转换为全局变量可以添加 !global 声明。</span>
    </p>
    <div class="main">main</div>
    <div class="sidebar">sidebar</div>
  </div>
</template>

<script>
export default {
  name: 'Variables'
}
</script>

<style lang="scss" scoped>
.main {
  $width: 5em !global; // 定义变量（!global将局部变量转为全局变量）
  width: $width;
  background-color: pink;
}
.sidebar {
  width: $width;
  background-color: plum;
}
</style>
