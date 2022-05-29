<template>
  <div id="app">
    <h1 class="box-line">Radio 单选框</h1>
    <basic-radio class="box-line" />
    <radio-group class="box-line" />
    <button-style-radio-group />
  </div>
</template>

<script>
import BasicRadio from '@/components/BasicRadio.vue'
import RadioGroup from '@/components/RadioGroup.vue'
import ButtonStyleRadioGroup from '@/components/ButtonStyleRadioGroup.vue'
export default {
  name: 'App',
  components: { BasicRadio, RadioGroup, ButtonStyleRadioGroup }
}
</script>
