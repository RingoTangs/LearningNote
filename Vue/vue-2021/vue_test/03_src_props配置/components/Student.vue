<template>
  <div>
    <h2>学生姓名: {{ name }}</h2>
    <h2>学生年龄: {{ newAge + 1 }}</h2>
    <h2>学生性别: {{ gender }}</h2>
    <!-- 传入的 prop 尽量不要直接更改, 可以修改 data 中的数据 -->
    <button @click="newAge = 99">尝试修改收到的年龄</button>
  </div>
</template>

<script>
export default {
  name: 'Student',
  // 如果 data 和 props 中的 key 重名了, props 的优先级更高
  // 结论: props 中的数据优先被加载, 所以 data 中才能 this.age.
  data() {
    return {
      newAge: this.age,
    }
  },
  props: {
    name: {
      type: String,
      required: true, // 必填项
    },
    age: {
      type: Number,
      default: 18, // 默认是18
    },
    gender: {
      type: String,
      // 传入数据校验规则
      validator(value) {
        return ['男', '女'].indexOf(value) !== -1
      },
    },
  },
}
</script>