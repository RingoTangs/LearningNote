<template>
  <div>
    <h1>基础用法</h1>
    <el-row>
      <p>
        <span>绑定v-model到一个Boolean类型的变量。</span>
        <span>可以使用active-color属性与inactive-color属性来设置开关的背景色。</span>
      </p>
      <el-switch v-model="value" inactive-color="#ff4949" active-color="#13ce66" />
    </el-row>

    <el-row>
      <p>
        <span>使用active-text属性与inactive-text属性来设置开关的文字描述。</span>
      </p>
      <div style="margin-bottom: 10px">
        <el-switch v-model="value1" active-text="按月付费" inactive-text="按年付费" />
      </div>
      <div class="sw">
        <el-switch
          v-model="value2"
          active-color="#13ce66"
          inactive-color="#ff4949"
          active-text="按月付费"
          inactive-text="按年付费"
        />
      </div>
    </el-row>

    <el-row>
      <p>
        <span>扩展的value类型</span>
      </p>
      <i class="el-icon-chat-round prefix-icon" :class="{ 'switch-active': value3 === 0 }" />
      <el-tooltip :content="'Switch value: ' + value3" placement="top">
        <el-switch
          v-model="value3"
          active-color="#13ce66"
          inactive-color="#ff4949"
          :active-value="100"
          :inactive-value="0"
        />
      </el-tooltip>
      <i class="el-icon-share suffix-icon" :class="{ 'switch-active': value3 === 100 }" />
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'BasicSwitch',
  data() {
    return {
      value: true,
      value1: true,
      value2: true,
      value3: 100
    }
  }
}
</script>

<style lang="scss" scoped>
.el-row {
  margin-bottom: 30px;
}

// 修改 switch text 的默认颜色
.sw {
  ::v-deep .el-switch__label.is-active {
    color: red;
  }
}

.prefix-icon {
  margin-right: 5px;
  vertical-align: middle;
}
.suffix-icon {
  margin-left: 5px;
  vertical-align: middle;
}
.switch-active {
  color: red;
}
</style>
