<template>
  <div>
    <h1>indeterminate属性实现全选</h1>
    <el-row class="reset-checkbox">
      <!-- 全选按钮 -->
      <el-checkbox
        v-model="checkedAll"
        :indeterminate="isIndeterminate"
        @change="onCheckedAllCities"
      >
        全选
      </el-checkbox>

      <!-- checkbox group -->
      <el-checkbox-group v-model="checkedCities" @change="onCheckedCity">
        <el-checkbox v-for="city in cities" :key="city.id" :label="city.id">
          {{ city.name }}
        </el-checkbox>
      </el-checkbox-group>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'IndeterminateCheckbox',
  data() {
    return {
      isIndeterminate: false, // 通过更改 "全选" 按钮使得 checkbox 全部勾选
      checkedAll: false, // 通过更改 checkbox 触发全选按钮的激活
      checkedCities: [],
      cities: [
        { id: '001', name: '北京' },
        { id: '002', name: '天津' },
        { id: '003', name: '上海' },
        { id: '004', name: '重庆' }
      ]
    }
  },
  computed: {
    cityIds() {
      return this.cities.map((city) => city.id)
    }
  },
  methods: {
    // 监听 checkbox 的变化
    onCheckedCity(checkedList) {
      this.checkedAll = checkedList.length === this.cities.length
      this.isIndeterminate = checkedList.length > 0 && checkedList.length < this.cities.length
    },
    // 监听 "全选按钮" 的变化
    onCheckedAllCities(isCheckedAll) {
      console.log(isCheckedAll)
      this.checkedCities = isCheckedAll ? this.cityIds : []
      this.isIndeterminate = false
    }
  }
}
</script>

<style lang="scss" scoped>
$color: purple;
.reset-checkbox {
  ::v-deep .el-checkbox__input.is-checked + .el-checkbox__label {
    color: $color;
  }
  ::v-deep .el-checkbox__input.is-checked .el-checkbox__inner,
  ::v-deep .el-checkbox__input.is-indeterminate .el-checkbox__inner {
    background-color: $color;
    border-color: $color;
  }
}
</style>
