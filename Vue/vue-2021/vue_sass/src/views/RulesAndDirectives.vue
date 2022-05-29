<template>
  <div>
    <at-extend class="box-line example1" />
  </div>
</template>

<script>
import AtExtend from '@/components/RulesAndDirectives/AtExtend.vue'
export default {
  name: 'RulesAndDirectives',
  components: { AtExtend }
}
</script>

<style lang="scss" scoped>
.example1 {
  padding-bottom: 10px;
}
</style>
