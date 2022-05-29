<template>
  <div>
    <nested-rules class="box-line" />
    <referencing-parent-selectors class="box-line" />
    <nested-properties />
  </div>
</template>

<script>
import NestedRules from '@/components/CSSExtensions/NestedRules.vue'
import ReferencingParentSelectors from '@/components/CSSExtensions/ReferencingParentSelectors.vue'
import NestedProperties from '@/components/CSSExtensions/NestedProperties.vue'
export default {
  name: 'CSSExtensions',
  components: { NestedRules, ReferencingParentSelectors, NestedProperties }
}
</script>
