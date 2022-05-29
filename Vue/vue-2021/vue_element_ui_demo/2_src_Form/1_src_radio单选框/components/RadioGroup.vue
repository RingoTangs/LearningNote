<template>
  <div>
    <h1>单选框组</h1>
    <p>
      <span>结合el-radio-group元素和子元素el-radio可以实现单选组，</span>
      <span>在el-radio-group中绑定v-model，在el-radio中设置好label即可，</span>
      <span>
        无需再给每一个el-radio绑定变量，另外，还提供了change事件来响应变化，它会传入一个参数value。
      </span>
    </p>

    <div class="rg">
      <el-radio-group v-model="city" @change="onCityRadioChange">
        <el-radio label="beijing">北京</el-radio>
        <el-radio label="shanghai">上海</el-radio>
        <el-radio label="tianjin">天津</el-radio>
      </el-radio-group>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RadioGroup',
  data() {
    return {
      city: 'beijing'
    }
  },
  methods: {
    onCityRadioChange(value) {
      console.log(value)
    }
  }
}
</script>

<style lang="css" scoped>
/* 修改 element-ui 的默认颜色 */
.rg {
  --color: pink;
}
.rg >>> .el-radio__input.is-checked + .el-radio__label {
  color: var(--color);
}

.rg >>> .el-radio__input.is-checked .el-radio__inner {
  border-color: var(--color);
  background: var(--color);
}
</style>

<style lang="scss" scoped>
.rg {
  margin-top: 20px;
}
</style>
