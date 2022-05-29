<template>
  <div class="todo-footer">
    <label :style="{ color: isAll ? 'skyblue' : '#000' }">
      <!-- checkbox 不写 value 绑定 bool 值, 绑定是 checked 属性值 -->
      <!-- 这种需要数据绑定还需要进行操作的可以使用 v-model -->
      <input type="checkbox" v-model="isAll" />
      <span>完成情况: {{ doneCount }}</span> /
      <span>{{ allCount }}</span>
    </label>
    <button class="btn btn-danger" @click="onRemoveDoneItemBtnClick">清除已完成任务</button>
  </div>
</template>

<script>
  export default {
    name: 'TodoListFooter',
    props: {
      todoList: Array,
      removeAllDoneItemFunction: Function,
      updateAllItemsDoneFieldFunction: Function,
    },
    computed: {
      // 统计已完成的任务数量
      doneCount() {
        // 这里使用 reduce 来做统计
        return this.todoList.reduce((total, cur) => total + (cur.done ? 1 : 0), 0)
      },
      // 统计所有的任务数量
      allCount() {
        return this.todoList.length
      },
      // v-model 绑定 checkbox
      isAll: {
        get() {
          return this.doneCount === this.allCount && this.allCount > 0
        },
        set(value) {
          this.updateAllItemsDoneFieldFunction(value)
        },
      },
    },
    methods: {
      // 监听【清除已完成任务】 按钮点击
      onRemoveDoneItemBtnClick() {
        if (confirm('确定要清除已完成任务吗？')) {
          this.removeAllDoneItemFunction()
        }
      },
    },
  }
</script>

<style scoped>
  .todo-footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 580px;
    border-radius: 4px;
    margin-top: 20px;
    padding: 8px 10px;
    font-size: 16px;
  }

  .todo-footer label {
    cursor: pointer;
  }

  .todo-footer label input {
    vertical-align: middle;
    margin-right: 6px;
  }
</style>