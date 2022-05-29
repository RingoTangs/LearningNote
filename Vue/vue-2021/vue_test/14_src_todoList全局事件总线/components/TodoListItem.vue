<template>
  <div>
    <li class="todo-main-item" :style="{ color: itemObj.done ? 'skyblue' : '#000' }">
      <label>
        <!-- 这里可以使用 @change/@click 事件 -->
        <!-- 这个 itemObj 是有 Getter 和 Setter 方法的 -->
        <!-- 证明这里改 itemObj 属性, 改的就是 App.vue 中 todoList -->
        <!-- Vue.js 官网 Prop 这节的文档也有说明, 数组和对象是以引用来传递的 -->
        <!-- <input type="checkbox" :checked="itemObj.done" @change="itemObj.done = !itemObj.done" /> -->

        <!-- checkbox 没有写 value, v-model 默认绑定 checked 的值(布尔值) -->
        <input type="checkbox" v-model="itemObj.done" />
        <span>{{ itemObj.title }}</span>
      </label>
      <button class="btn btn-danger" @click="onDeleteBtnClick(itemObj)">删除</button>
    </li>
  </div>
</template>

<script>
  export default {
    name: 'TodoListItem',
    data() {
      return {
        // props 比 data 优先加载, 所以可以在 data 拿到 props 中的数据
        // itemObj 也是引用, 指向的是 App.vue 中的数据
        itemObj: this.item,
      }
    },
    props: { item: Object },
    methods: {
      onDeleteBtnClick(itemObj) {
        const tip = `确定要删除【${itemObj.title}】吗？`
        if (confirm(tip)) {
          // 向全局事件总线发送 "移除指定待办事项" 事件
          this.$bus.$emit('remove-todo-item', new Array(itemObj.id))
        }
      },
    },
  }
</script>

<style scoped>
  .todo-main-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid var(--c);
    padding: 8px 10px;
    font-size: 18px;
    transition: all 0.3s ease-in-out;
  }

  .todo-main-item .btn {
    transition: all 0.1s ease-in-out;
  }

  .todo-main-item label {
    cursor: pointer;
  }

  .todo-main-item label input {
    /* 指定行内元素垂直对齐方式 */
    vertical-align: middle;
    margin-right: 6px;
  }

  .todo-main-item:hover {
    background-color: rgba(0, 0, 0, 0.1);
  }

  .todo-main-item:hover .btn {
    opacity: 1;
  }

  .todo-main-item .btn {
    opacity: 0;
  }
</style>