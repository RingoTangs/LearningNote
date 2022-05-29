<template>
  <div>
    <ul class="todo-main">
      <!-- 将 todoList 数组遍历的对象传递给子组件 -->
      <todo-list-item v-for="todoItem in todoList" :key="todoItem.id" :item="todoItem" />
    </ul>
  </div>
</template>

<script>
  import TodoListItem from './TodoListItem'
  export default {
    name: 'TodoList',
    components: { TodoListItem },
    props: { todoList: Array },
  }
</script>

<style scoped>
  .todo-main {
    width: 580px;
    border: 1px solid var(--c);
    border-bottom: none;
    border-radius: 4px;
    margin-top: 20px;
  }
</style>