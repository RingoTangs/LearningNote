<template>
  <div id="app">
    <h1 class="box-line">TimePicker 时间选择器</h1>
    <el-row>
      <h2>固定时间点</h2>
      <p>提供几个固定的时间点供用户选择。</p>
      <p>使用 el-time-select 标签，分别通过start、end和step指定可选的起始时间、结束时间和步长。</p>
      <el-time-select
        v-model="fixTime.value"
        :picker-options="fixTime.pickerOptions"
        placeholder="请选择时间"
      />
    </el-row>

    <el-row>
      <h2>任意时间点</h2>
      <p>使用 el-time-picker 标签，通过selectableRange限制可选时间范围。</p>
      <p>
        提供了两种交互方式：默认情况下通过鼠标滚轮进行选择，打开arrow-control属性则通过界面上的箭头进行选择。
      </p>
      <el-row>
        <p>鼠标滚轮选择</p>
        <el-time-picker v-model="chooseTime1.value" :picker-options="chooseTime1.pickerOptions" />
      </el-row>
      <el-row>
        <p>使用箭头选择</p>
        <el-time-picker
          v-model="chooseTime2.value"
          arrow-control
          :picker-options="chooseTime2.pickerOptions"
        />
      </el-row>
    </el-row>

    <el-row>
      <h2>固定时间范围</h2>
      <p>若先选择开始时间，则结束时间内备选项的状态会随之改变</p>
      <el-time-select
        v-model="fixTimeRange.startTime"
        :picker-options="{ start: '08:30', step: '00:15', end: '18:30' }"
        placeholder="起始时间"
        class="start-time"
      />
      <el-time-select
        v-model="fixTimeRange.endTime"
        :editable="false"
        :picker-options="{
          start: '08:30',
          step: '00:15',
          end: '18:30',
          minTime: fixTimeRange.startTime
        }"
        placeholder="结束时间"
      />
    </el-row>

    <el-row>
      <h2>任意时间范围</h2>
      <P> 添加is-range属性即可选择时间范围，同样支持arrow-control属性。 </P>
      <el-row>
        <span>滚轮选择时间</span>
        <el-time-picker
          v-model="value1"
          is-range
          range-separator="至"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          placeholder="选择时间范围"
        />
      </el-row>
      <el-row style="margin-top: 10px">
        <span>箭头选择时间</span>
        <el-time-picker
          v-model="value2"
          arrow-control
          :clearable="false"
          is-range
          range-separator="至"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          placeholder="选择时间范围"
          popper-class="aa"
        />
      </el-row>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      fixTime: {
        value: '',
        pickerOptions: {
          start: '08:30',
          step: '00:15',
          end: '18:30'
        }
      },
      chooseTime1: {
        value: new Date(2021, 1, 25, 11, 12),
        pickerOptions: {
          selectableRange: '18:30:00 - 20:30:00'
        }
      },
      chooseTime2: {
        value: new Date(2021, 1, 25, 11, 12),
        pickerOptions: {
          selectableRange: '18:30:00 - 20:30:00'
        }
      },
      fixTimeRange: {
        startTime: '',
        endTime: ''
      },
      value1: [new Date(2016, 9, 10, 8, 40), new Date(2016, 9, 10, 9, 40)],
      value2: [new Date(2016, 9, 10, 8, 40), new Date(2016, 9, 10, 9, 40)]
    }
  }
}
</script>

<style lang="scss" scoped>
.start-time {
  margin-right: 20px;
}
</style>
