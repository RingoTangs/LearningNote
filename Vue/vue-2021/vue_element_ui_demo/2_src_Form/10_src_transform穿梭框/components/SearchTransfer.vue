<template>
  <div>
    <h1>可搜索</h1>
    <p>设置 filterable 为 true 即可开启搜索模式。</p>
    <el-transfer
      ref="transfer"
      v-model="value"
      :data="data"
      filterable
      filter-placeholder="请输入城市名或拼音"
      :filter-method="filterMethod"
      :titles="['城市列表', '添加的城市列表']"
      :button-texts="['左边', '右边']"
      target-order="unshift"
    >
      <template v-slot:right-footer>
        <el-button type="primary" size="mini" @click="clearQuery('right')">
          清空搜索关键字
        </el-button>
      </template>
    </el-transfer>
  </div>
</template>

<script>
import { nanoid } from 'nanoid'
export default {
  name: 'SearchTransfer',
  data() {
    return {
      data: this.generateData(),
      value: []
    }
  },
  methods: {
    generateData() {
      const data = []
      const cities = [
        { name: '上海', pinyin: 'shanghai' },
        { name: '北京', pinyin: 'beijing' },
        { name: '广州', pinyin: 'guangzhou' },
        { name: '深圳', pinyin: 'shenzhen' },
        { name: '南京', pinyin: 'nanjing' },
        { name: '西安', pinyin: 'xian' },
        { name: '成都', pinyin: 'chengdu' }
      ]
      cities.forEach((city) => data.push({ key: nanoid(), label: city.name, pinyin: city.pinyin }))
      return data
    },
    /**
     * 搜索方法
     * @param {string, object}
     * @return {boolean}
     */
    filterMethod(query, item) {
      query = query.trim()
      if (!query) return true
      return item.label.includes(query) || item.pinyin.includes(query)
    },
    /**
     * 清空搜索关键字
     * @param {string} 'left'/'right'
     * @return {void}
     */
    clearQuery(leftOrRight) {
      this.$refs['transfer'].clearQuery(leftOrRight)
    }
  }
}
</script>

<style lang="scss" scoped>
.el-transfer {
  ::v-deep .el-transfer-panel__footer {
    text-align: center;
  }
}
</style>
