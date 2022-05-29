<template>
  <div>
    <h1>可搜索的Select</h1>
    <p>
      <span>为el-select添加filterable属性即可启用搜索功能。</span>
      <span>默认情况下，Select 会找出所有label属性包含输入值的选项。</span>
      <span>如果希望使用其他的搜索逻辑，可以通过传入一个filter-method来实现。</span>
      <span>filter-method为一个Function，它会在输入值发生变化时调用，参数为当前输入值。</span>
    </p>
    <el-select v-model="value" filterable placeholder="请选择">
      <el-option-group v-for="op in options" :key="op.label" :label="op.label">
        <el-option v-for="city in op.options" :key="city.value" :label="city.label" :value="city.value">
          <template v-slot:default>
            <div class="option-content">
              <span>{{ city.label }}</span>
              <span>{{ city.value }}</span>
            </div>
          </template>
        </el-option>
      </el-option-group>
    </el-select>
  </div>
</template>

<script>
export default {
  name: 'SearchSelect',
  data() {
    return {
      options: [
        {
          label: '热门城市',
          options: [
            { value: 'Shanghai', label: '上海' },
            { value: 'Beijing', label: '北京' }
          ]
        },
        {
          label: '城市名',
          options: [
            { value: 'Chengdu', label: '成都' },
            { value: 'Shenzhen', label: '深圳' },
            { value: 'Guangzhou', label: '广州' },
            { value: 'Dalian', label: '大连' }
          ]
        }
      ],
      value: ''
    }
  }
}
</script>

<style lang="scss" scoped>
.option-content {
  display: flex;
  justify-content: space-between;
  span {
    &:last-child {
      color: #8492a6;
      font-size: 13px;
    }
  }
}
</style>
