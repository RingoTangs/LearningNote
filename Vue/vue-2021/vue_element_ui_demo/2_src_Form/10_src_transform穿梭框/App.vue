<template>
  <div id="app">
    <h1 class="box-line">Transfer 穿梭框</h1>
    <base-transfer class="box-line" />
    <search-transfer />
  </div>
</template>

<script>
import BaseTransfer from '@/components/BaseTransfer.vue'
import SearchTransfer from '@/components/SearchTransfer.vue'
export default {
  name: 'App',
  components: { BaseTransfer, SearchTransfer }
}
</script>
