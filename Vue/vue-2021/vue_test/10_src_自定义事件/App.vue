<template>
  <div>
    <h1 class="own-bottom-line">{{ msg }}</h1>
    <!-- 通过父组件给子组件传递Function类型的props实现：子给父传递数据 -->
    <school :obtain-school-name-function="obtainSchoolName" />

    <!-- 通过 v-on 实现子组件给父组件传递数据 -->
    <!-- <student v-on:send-student-name="obtainStudentName" /> -->

    <!-- 通过 ref 监听子组件的自定义事件（非常灵活） -->
    <!-- 组件销毁后, 组件身上绑定的所有自定义事件都解绑了 -->
    <!-- @click.native 监听原生 DOM 点击事件 -->
    <student ref="student" v-if="isShow" @click.native="studentClick" />
  </div>
</template>

<script>
  import School from './components/School.vue'
  import Student from './components/Student.vue'
  export default {
    name: 'App',
    data() {
      return {
        msg: 'App.vue say Hello World',
        isShow: true,
      }
    },
    components: { School, Student },
    methods: {
      // 拿到 School 组件中学校的名字
      obtainSchoolName(name) {
        console.log('App收到了学校名:', name)
      },
      // 拿到 Student 组件中学生的名字
      obtainStudentName(name) {
        console.log('App收到了学生名:', name)
      },
      // 监听 student 组件的原生的 DOM 点击事件
      studentClick() {
        console.log('Student 组件被点击');
      },
    },
    mounted() {
      // 3秒后给 Student 组件绑定事件
      setTimeout(() => {
        // 推荐写法
        this.$refs['student'].$on('send-student-name', this.obtainStudentName)

        // this.$refs['student'].$on('send-student-name', (name) => {
        //   console.log(this)  // App.vue
        //   console.log(name);
        // })

        // this.$refs['student'].$on('send-student-name', function () {
        //   console.log(this)   // Student.vue
        // })
      }, 3000)
    },
  }
</script>

<style>
  .own-bottom-line {
    border-bottom: 1px solid rgba(0, 0, 0, 0.3);
  }
</style>