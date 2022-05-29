<template>
  <div>
    <h1>@extend</h1>
    <div ref="el-error" class="error">Oh no! You've been hacked!</div>
    <button @click="onSwitchBtnClick((isSerious = !isSerious))">切换</button>
    <div class="box" />
    <div class="box1" />
  </div>
</template>

<script>
export default {
  name: 'AtExtend',
  data() {
    return {
      isSerious: false
    }
  },
  methods: {
    onSwitchBtnClick(isSerious) {
      const classList = this.$refs['el-error'].classList
      if (isSerious) {
        classList.remove('error')
        classList.add('seriousError')
      } else {
        classList.remove('seriousError')
        classList.add('error')
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.error {
  border: 1px dotted #f00;
  background-color: skyblue;
}
.seriousError {
  @extend .error;
  border-width: 3px;
}

// % 作为占位符是 extend-only 的写法, 只通过 @extend 指令使用

// 盒子样式
%box-style {
  width: 100px;
  height: 100px;
  background-color: pink;
  margin-top: 50px;
}

// 盒子伪元素选择器样式
%box-before-style {
  width: 30px;
  height: 30px;
  background-color: powderblue;
}

// 相对定位
// 混合样式
@mixin relative-position {
  position: relative;
}

// flex 布局水平垂直居中
%flex-horizontal-vertical-center {
  display: flex;
  align-items: center;
  justify-content: center;
}

// 使用定位使得水平垂直居中
%position-horizontal-vertical-center {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.box {
  @extend %flex-horizontal-vertical-center, %box-style;
  &::before {
    content: '';
    @extend %box-before-style;
  }
}

.box1 {
  @extend %box-style;
  @include relative-position;
  &::before {
    content: '';
    @extend %box-before-style, %position-horizontal-vertical-center;
  }
}
</style>
