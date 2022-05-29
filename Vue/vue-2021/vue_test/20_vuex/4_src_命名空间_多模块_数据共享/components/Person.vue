<template>
  <div>
    <h1 style="color: red">sum: {{ $store.state.countModule.sum }}</h1>
    <h1>人员列表</h1>
    <input type="text" placeholder="请输入名字" v-model.trim="name" @keyup.enter="onAddBtnClick" />
    <button @click="onAddBtnClick">添加</button>
    <ul>
      <li v-for="person in $store.state.personModule.personList" :key="person.id">
        {{ person.name }}
      </li>
    </ul>
  </div>
</template>

<script>
  import { nanoid } from 'nanoid'
  export default {
    name: 'Person',
    data() {
      return {
        name: '',
      }
    },
    methods: {
      // 监听 "添加" 按钮的点击
      onAddBtnClick() {
        if (!this.name) return alert('姓名不能为空')
        this.$store.dispatch('personModule/addPersonThenSaveLocal', { id: nanoid(), name: this.name })
        this.name = ''
      },
    },
    mounted() {
      console.log(this.$store)
    },
  }
</script>

<style scoped>
  button {
    margin-left: 10px;
  }
</style>