<template>
  <div>
    <h1>分组显示</h1>
    <el-select v-model="value" placeholder="请选择">
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
  name: 'GroupSelect',
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
