<template>
  <div id="app">
    <basic-layout class="box-line" />
    <gutter-layout class="box-line" />
    <offset-layout class="box-line" />
    <align-layout class="box-line" />
    <reactive-layout />
  </div>
</template>

<script>
import BasicLayout from '@/components/BasicLayout.vue'
import GutterLayout from '@/components/GutterLayout.vue'
import OffsetLayout from '@/components/OffsetLayout.vue'
import AlignLayout from '@/components/AlignLayout.vue'
import ReactiveLayout from '@/components/ReactiveLayout.vue'
export default {
  name: 'App',
  components: { BasicLayout, GutterLayout, OffsetLayout, AlignLayout, ReactiveLayout }
}
</script>

<style lang="scss">
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
  .el-col {
    .grid-content {
      border-radius: 10px;
      min-height: 36px;
    }
    .bg-purple {
      background: #d3dce6;
    }
    .bg-purple-dark {
      background: #99a9bf;
    }
    .bg-purple-light {
      background: #e5e9f2;
    }
  }
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
