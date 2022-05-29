<template>
  <div id="app">
    <h1 class="box-line">Checkbox 多选框</h1>
    <basic-checkbox class="box-line" />
    <checkbox-group class="box-line" />
    <indeterminate-checkbox />
  </div>
</template>

<script>
import BasicCheckbox from '@/components/BasicCheckbox.vue'
import CheckboxGroup from '@/components/CheckboxGroup.vue'
import IndeterminateCheckbox from '@/components/IndeterminateCheckbox.vue'
export default {
  name: 'App',
  components: { BasicCheckbox, CheckboxGroup, IndeterminateCheckbox }
}
</script>
