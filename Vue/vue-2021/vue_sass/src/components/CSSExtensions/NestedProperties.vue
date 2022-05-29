<template>
  <div>
    <h1>3.属性嵌套</h1>
    <p>
      <span>有些 CSS 属性遵循相同的命名空间 (namespace)，</span>
      <span>比如 font-family, font-size, font-weight 都以 font 作为属性的命名空间。</span>
      <span>为了便于管理这样的属性，同时也为了避免了重复输入，Sass 允许将属性嵌套在命名空间中。</span>
      <span style="color: red">（用的不多）</span>
    </p>
    <span class="funky">Hello World</span>
  </div>
</template>

<script>
export default {
  name: 'NestedProperties'
}
</script>

<style lang="scss" scoped>
.funky {
  box-shadow: 0 0 5px skyblue;
  font: {
    family: fantasy;
    size: 10em;
    weight: bold;
  }
}
</style>
