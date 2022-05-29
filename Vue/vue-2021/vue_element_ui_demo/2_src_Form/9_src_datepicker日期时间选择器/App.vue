<template>
  <div id="app">
    <h1 class="box-line">DatePicker 日期选择器</h1>
    <base-day />
  </div>
</template>

<script>
import BaseDay from '@/components/BaseDay.vue'
export default {
  name: 'App',
  components: { BaseDay }
}
</script>
