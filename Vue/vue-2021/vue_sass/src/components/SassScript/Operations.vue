<template>
  <div>
    <h1>3.运算</h1>
    <p>所有数据类型均支持相等运算 == 或 !=，此外，每种数据类型也有其各自支持的运算方式。</p>
    <p>SassScript 支持数字的加减乘除、取整等运算 (+, -, *, /, %)，如果必要会在不同单位间转换值。</p>
    <h2>3.1.除法运算</h2>
    <p>以下三种情况 / 将被视为除法运算符号：</p>
    <ul>
      <li>如果值，或值的一部分，是变量或者函数的返回值</li>
      <li>如果值被圆括号包裹</li>
      <li>如果值是算数表达式的一部分</li>
    </ul>
    <p>如果需要使用变量，同时又要确保 / 不做除法运算而是完整地编译到 CSS 文件中，只需要用 #{} 插值语句将变量包裹。</p>
    <p class="hello">Hello World</p>
  </div>
</template>

<script>
export default {
  name: 'Operations'
}
</script>

<style lang="scss" scoped>
.hello {
  background-color: pink;
  font: 10px/8px; // Plain CSS, no division

  $width: 1000px;
  width: $width / 2; // Uses a variable, does division
  width: round(1.5) / 2; // Uses a function, does division
  height: (500px/2); // Uses parentheses, does division
  margin-left: 5px + 8px/2px; // Uses +, does division

  $font-size: 12px;
  $line-height: 30px;
  font-size: #{$font-size}/#{$line-height}; // 这样就会编译为 font-size: 12px/30px
}
</style>
