<template>
  <div>
    <h1>2.数据类型</h1>
    <p>SassScript 支持 6 种主要的数据类型：</p>
    <ul>
      <li>数字，1, 2, 13, 10px</li>
      <li>字符串，有引号字符串与无引号字符串，"foo", 'bar', baz</li>
      <li>颜色，blue, #04a3f9, rgba(255,0,0,0.5)</li>
      <li>布尔型，true, false</li>
      <li>空值，null</li>
      <li>数组 (list)，用空格或逗号作分隔符，1.5em 1em 0 2em, Helvetica, Arial, sans-serif</li>
      <li>maps, 相当于 JavaScript 的 object，(key1: value1, key2: value2)</li>
    </ul>
    <p>SassScript 也支持其他 CSS 属性值，比如 Unicode 字符集，或 !important 声明。</p>
    <p>然而Sass 不会特殊对待这些属性值，一律视为无引号字符串。</p>
  </div>
</template>

<script>
export default {
  name: 'DataTypes'
}
</script>

<style lang="scss" scoped>
</style>
