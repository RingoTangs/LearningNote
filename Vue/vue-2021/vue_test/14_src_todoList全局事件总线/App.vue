<template>
  <div class="root">
    <div class="todo-container">
      <div class="todo-wrap">
        <!-- 父组件传子组件可以传函数, 然后让子组件去调用 -->
        <todo-list-header @add-todo-event="addToList" />
        <todo-list :todo-list="todoList" />
        <todo-list-footer
          :todo-list="todoList"
          @remove-all-done-items-event="removeAllDoneItems"
          @update-all-items-event="updateAllItemsDoneField"
        />
      </div>
    </div>
  </div>
</template>

<script>
  import TodoList from './components/TodoList'
  import TodoListFooter from './components/TodoListFooter'
  import TodoListHeader from './components/TodoListHeader'
  export default {
    name: 'App',
    components: {
      TodoList,
      TodoListFooter,
      TodoListHeader,
    },
    data() {
      return {
        // 如果从 localStorage 中取数据, 取不到会返回 null
        // 如果不加 "|| []", 用户第一次进到页面就会报错
        todoList: JSON.parse(localStorage.getItem('todoList')) || [],
      }
    },
    // 使用 watch 来监视 todoList 中对象内部的变化, 然后存储到 WebStorage 中
    watch: {
      todoList: {
        deep: true,
        handler(newVal) {
          localStorage.setItem('todoList', JSON.stringify(newVal))
        },
      },
    },
    methods: {
      // 将待办事项添加到 todoList
      addToList(todoItem) {
        this.todoList.unshift(todoItem)
      },
      // 根据 id 移除 todoList 中的待办事项, 参数传一个数组
      removeTodoItemsByIds(ids) {
        this.todoList = this.todoList.filter((todo) => ids.indexOf(todo.id) === -1)
      },
      // 移除全部已完成任务
      removeAllDoneItems() {
        this.todoList = this.todoList.filter((todo) => !todo.done)
      },
      // 更新所有的待办事项的 done 属性
      updateAllItemsDoneField(done) {
        this.todoList.forEach((cur) => (cur.done = done))
      },
    },
    mounted() {
      // 监听全局事件总线 "移除指定待办事项" 事件
      this.$bus.$on('remove-todo-item', this.removeTodoItemsByIds)
    },
    beforeDestroy() {
      // 解绑对全局事件总线监听
      this.$bus.$off('remove-todo-item')
    },
  }
</script>

<!-- App.vue 中样式不需要添加 scoped -->
<style>
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }

  ul {
    list-style: none;
  }

  .root {
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #fff;
    min-height: 100vh;
    --c: #ddd;
  }

  .todo-container {
    width: 600px;
  }

  .btn {
    display: inline-block;
    padding: 3px 15px;
    margin-bottom: 0;
    font-size: 14px;
    line-height: 20px;
    text-align: center;
    vertical-align: middle;
    cursor: pointer;
    box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
    border-radius: 4px;
  }

  .btn-danger {
    color: #fff;
    background-color: #da4f49;
    border: 1px solid #bd362f;
  }

  .todo-container .todo-wrap {
    border: 1px solid var(--c);
    padding: 10px;
    border-radius: 5px;
  }
</style>