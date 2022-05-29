<template>
  <div id="app">
    <h1 class="box-line">Cascader 级联选择器</h1>
    <basic-cascader class="box-line" />
    <dynamic-loading class="box-line" />
    <searched-cascader class="box-line" />
    <cascader-panel />
  </div>
</template>

<script>
import BasicCascader from '@/components/BasicCascader.vue'
import DynamicLoading from '@/components/DynamicLoading.vue'
import SearchedCascader from '@/components/SearchedCascader.vue'
import CascaderPanel from '@/components/CascaderPanel.vue'
export default {
  name: 'App',
  components: { BasicCascader, DynamicLoading, SearchedCascader, CascaderPanel }
}
</script>

