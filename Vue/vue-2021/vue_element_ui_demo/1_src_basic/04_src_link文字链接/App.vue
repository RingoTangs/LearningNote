<template>
  <div id="app">
    <h1 class="box-line">Link 文字链接</h1>
    <basic-link class="box-line" />
    <icon-link />
  </div>
</template>

<script>
import BasicLink from '@/components/BasicLink.vue'
import IconLink from '@/components/IconLink.vue'
export default {
  name: 'App',
  components: { BasicLink, IconLink }
}
</script>
