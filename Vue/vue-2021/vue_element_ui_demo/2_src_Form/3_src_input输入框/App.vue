<template>
  <div id="app">
    <h1 class="box-line">Input 输入框</h1>
    <complex-input class="box-line" />
    <input-advisor class="box-line" />
    <icon-input class="box-line" />
  </div>
</template>

<script>
import ComplexInput from '@/components/ComplexInput.vue'
import InputAdvisor from '@/components/InputAdvisor.vue'
import IconInput from '@/components/IconInput.vue'
export default {
  name: 'App',
  components: { ComplexInput, InputAdvisor, IconInput }
}
</script>

<style lang="scss" scoped>
.el-input {
  width: 500px;
}
</style>
