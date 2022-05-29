<template>
  <div class="todo-header">
    <input type="text" @keyup.enter="addTodoItem" placeholder="请输入你的任务名称，按回车键确认" />
  </div>
</template>

<script>
  import { nanoid } from 'nanoid'
  export default {
    name: 'TodoListHeader',
    methods: {
      // 添加待办事项
      addTodoItem(event) {
        // 拿到用户的输入
        event.target.value = event.target.value.trim()
        const title = event.target.value
        // 校验数据
        if (!title) {
          alert('请输入待办事项')
          return
        }
        // 将用户的输入包装成一个 todo 对象
        const todoItem = { id: nanoid(), title, done: false }
        // 通知 App 组件添加一个待办事项
        this.$emit('add-todo-event', todoItem)
        event.target.value = ''
      },
    },
  }
</script>

<style scoped>
  .todo-header input {
    width: 580px;
    font-size: 14px;
    border: 1px solid var(--c);
    border-radius: 4px;
    padding: 8px 10px;
  }

  .todo-header input:focus {
    /* outline 和 border 类似, 但是 outline 不占据空间 */
    outline: none;
    border-color: rgba(82, 168, 236, 0.8);
    /* 设置 input 输入框内外阴影, inset 内阴影 */
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
  }
</style>