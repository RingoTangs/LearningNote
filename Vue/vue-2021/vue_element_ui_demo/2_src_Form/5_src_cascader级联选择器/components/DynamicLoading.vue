<template>
  <div>
    <h1>动态加载</h1>
    <p>
      <span>通过lazy开启动态加载，并通过lazyload来设置加载数据源的方法。</span>
      <span>
        lazyload方法有两个参数，第一个参数node为当前点击的节点，第二个resolve为数据加载完成的回调(必须调用)。
      </span>
      <span>
        为了更准确的显示节点的状态，还可以对节点数据添加是否为叶子节点的标志位
        (默认字段为leaf，可通过props.leaf修改)，否则会简单的以有无子节点来判断是否为叶子节点。
      </span>
    </p>
    <el-cascader v-model="op" :props="props" placeholder="请选择城市"></el-cascader>
  </div>
</template>

<script>
export default {
  name: 'DynamicLoading',
  data() {
    return {
      op: [],
      props: {
        lazy: true,
        expandTrigger: 'click',
        lazyLoad: (node, resolve) => {
          const { level, value: provinceId } = node
          console.log('node', node, 'provinceId', provinceId)
          if (level === 0) {
            // Object.assign(item, { leaf: level >= 1 })
            resolve(this.loadProvinces().map((item) => Object.assign(item, { leaf: level >= 1 })))
            return
          }
          setTimeout(() => {
            const nodes = this.loadCities(provinceId).map((item) =>
              Object.assign(item, { leaf: level >= 1 })
            )
            // 通过调用resolve将子节点数据返回，通知组件数据加载完成
            resolve(nodes)
          }, 1000)
          return
        }
      }
    }
  },
  methods: {
    loadProvinces() {
      return [
        { value: 1, label: '北京市' },
        { value: 2, label: '河北省' }
      ]
    },
    loadCities(provinceId) {
      switch (provinceId) {
        case 1:
          return [
            { value: 4, label: '海淀' },
            { value: 5, label: '朝阳' }
          ]
        case 2:
          return [
            { value: 6, label: '石家庄' },
            { value: 7, label: '保定' }
          ]
      }
    }
  }
}
</script>

<style>
</style>
