<template>
  <div>
    <h1>2.父选择器</h1>
    <p>
      <span>在嵌套 CSS 规则时，有时也需要直接使用嵌套外层的父选择器，</span>
      <span> 例如，当给某个元素设定 hover 样式时，或者当 body 元素有某个 classname 时，可以用 & 代表嵌套规则外层的父选择器。 </span>
    </p>
    <a href="javascript:void(0)" class="click-me" :class="{ active }" @mousedown="active = true" @mouseup="active = false"> 点我 </a>

    <p>
      <span>编译后的 CSS 文件中 & 将被替换成嵌套外层的父选择器，如果含有多层嵌套，</span>
      <span>最外层的父选择器会一层一层向下传递：</span>
    </p>
    <div class="main">
      <span>Fine</span>
      <a href="javascript:void(0)" class="link"> Link </a>
    </div>

    <p>& 必须作为选择器的第一个字符，其后可以跟随后缀生成复合的选择器，</p>
    <div class="side">
      <span>side</span>
      <span class="side-bar">side-bar</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ReferencingParentSelectors',
  data() {
    return {
      active: false
    }
  }
}
</script>

<style lang="scss" scoped>
.click-me {
  text-decoration: none;
  color: red;
  transition: all 200ms ease-in-out;
  &:hover {
    color: skyblue;
  }
}

.active {
  color: yellow !important;
}

.main {
  color: pink;
  .link {
    display: inline-block;
    margin-left: 10px;
    text-decoration: none;
    font-weight: bolder;
    transition: all 0.3s ease-in-out;
    &:hover {
      transform: scale(1.5) translateY(-5px);
    }
  }
}

.side {
  color: pink;
  &-bar {
    color: skyblue;
    margin-left: 10px;
  }
}
</style>
