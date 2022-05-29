<template>
  <div>
    <variables class="box-line" />
    <data-types class="box-line" />
    <operations class="box-line" />
  </div>
</template>

<script>
import Variables from '@/components/SassScript/Variables.vue'
import DataTypes from '@/components/SassScript/DataTypes.vue'
import Operations from '@/components/SassScript/Operations.vue'

export default {
  name: 'SassScript',
  components: { Variables, DataTypes, Operations }
}
</script>

