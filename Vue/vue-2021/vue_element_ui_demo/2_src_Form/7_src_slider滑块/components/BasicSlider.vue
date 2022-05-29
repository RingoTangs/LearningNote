<template>
  <div>
    <h1>基础用法</h1>
    <el-row>
      <span>value1: {{ value1 }}</span>
      <el-slider v-model="value1" />
    </el-row>

    <el-row>
      <span>隐藏 Tooltip</span>
      <el-slider v-model="value2" :show-tooltip="false" />
    </el-row>

    <el-row>
      <span>格式化 Tooltip</span>
      <el-slider v-model="value3" :format-tooltip="(val) => val / 100" />
    </el-row>

    <el-row>
      <h2>选项可以是离散的</h2>
      <p>改变step的值可以改变步长，通过设置show-stops属性可以显示间断点。</p>
      <el-row>
        <span>不显示间断点</span>
        <el-slider
          v-model="val1"
          :step="10"
          :format-tooltip="(val) => val / 10"
          @change="onSliderChange"
        />
      </el-row>
      <el-row>
        <span>显示间断点</span>
        <el-slider v-model="val2" :step="10" :format-tooltip="(val) => val / 10" show-stops />
      </el-row>

      <el-row>
        <h2>范围选择</h2>
        <p>设置range即可开启范围选择，此时绑定值是一个数组，其元素分别为最小边界值和最大边界值</p>
        <el-slider v-model="val3" :max="6" show-stops range :step="2" />
      </el-row>
    </el-row>

    <el-row>
      <h2>展示标记</h2>
      <span>设置 marks 属性可以展示标记</span>
      <el-slider v-model="val4" range :marks="marks" @change="onSliderChange" />
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'BasicSlider',
  data() {
    return {
      value1: 10,
      value2: 80,
      value3: 20,
      val1: 10,
      val2: 20,
      val3: [2, 4],
      val4: [30, 60],
      marks: {
        0: '0°C',
        8: '8°C',
        37: '37°C',
        50: {
          style: {
            color: '#1989FA'
          },
          label: this.$createElement('strong', '50%')
        }
      }
    }
  },
  methods: {
    onSliderChange(val) {
      console.log(val)
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
