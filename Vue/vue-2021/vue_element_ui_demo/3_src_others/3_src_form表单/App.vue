<template>
  <div id="app">
    <h1 class="box-line">Form 表单</h1>
    <typical-form class="box-line" />
    <inline-form class="box-line" />
    <align-form class="box-line" />
    <validate-form class="box-line" />
    <custom-validate-form />
  </div>
</template>

<script>
import TypicalForm from '@/components/TypicalForm.vue'
import InlineForm from '@/components/InlineForm.vue'
import AlignForm from '@/components/AlignForm.vue'
import ValidateForm from '@/components/ValidateForm.vue'
import CustomValidateForm from '@/components/CustomValidateForm.vue'
export default {
  name: 'App',
  components: { TypicalForm, InlineForm, AlignForm, ValidateForm, CustomValidateForm }
}
</script>
