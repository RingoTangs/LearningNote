<template>
  <div id="app">
    <basic-button class="box-line" />
    <button-group class="box-line" />
    <loading-button />
  </div>
</template>

<script>
import BasicButton from '@/components/BasicButton.vue'
import ButtonGroup from '@/components/ButtonGroup.vue'
import LoadingButton from '@/components/LoadingButton.vue'
export default {
  name: 'App',
  components: { BasicButton, ButtonGroup, LoadingButton }
}
</script>
