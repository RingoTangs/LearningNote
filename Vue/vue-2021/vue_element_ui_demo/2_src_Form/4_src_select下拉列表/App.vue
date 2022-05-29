<template>
  <div id="app">
    <h1 class="box-line">Select 下拉列表</h1>
    <basic-select class="box-line" />
    <group-select class="box-line" />
    <search-select class="box-line" />
  </div>
</template>

<script>
import BasicSelect from '@/components/BasicSelect.vue'
import GroupSelect from '@/components/GroupSelect.vue'
import SearchSelect from '@/components/SearchSelect.vue'
export default {
  name: 'App',
  components: { BasicSelect, GroupSelect, SearchSelect }
}
</script>
