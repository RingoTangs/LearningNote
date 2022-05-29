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
        <input class="checkbox" type="checkbox" v-model="itemObj.done" />
        <input
          type="text"
          :value="itemObj.title"
          class="edit-input"
          v-if="isEdit"
          @keyup.enter="onUpdateTodoTitle($event, itemObj)"
          @blur="isEdit = isEdit ?  false : true"
          ref="editInput"
        />
        <span v-else>{{ itemObj.title }}</span>
      </label>
      <div class="btn-container">
        <button class="btn btn-edit" @click="onEditBtnClick" v-show="!isEdit">编辑</button>
        <button class="btn btn-danger" @click="onDeleteBtnClick(itemObj)">删除</button>
      </div>
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
        isEdit: false, // 是否处于编辑模式
      }
    },
    props: { item: Object },
    methods: {
      // 监听 "编辑" 按钮的点击
      onEditBtnClick() {
        this.isEdit = true
        // 等待 DOM 更新后 获取焦点
        this.$nextTick(() => this.$refs['editInput'].focus())
      },

      // 监听 "删除按钮" 点击
      onDeleteBtnClick(itemObj) {
        const tip = `确定要删除【${itemObj.title}】吗？`
        if (confirm(tip)) {
          // 向全局事件总线发送 "移除指定待办事项" 事件
          this.$bus.$emit('remove-todo-item-by-id-event', new Array(itemObj.id))
        }
      },
      // 监听更新 "待办事件Title" 事件
      onUpdateTodoTitle(event, itemObj) {
        if (this.isEdit) {
          const id = itemObj.id
          const title = event.target.value
          if (!title.trim()) {
            alert('待办事项不能为空')
            event.target.value = itemObj.title // 将对象的 "title" 赋值给 "真实DOM"
            return
          }
          if (confirm(`确定将【${itemObj.title}】修改为【${title}】吗?`)) {
            this.$bus.$emit('update-todo-item-title-event', { id, title })
            this.isEdit = false
          }
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
    height: 50px;
    border-bottom: 1px solid var(--c);
    padding: 8px 10px;
    font-size: 18px;
    transition: all 0.3s ease-in-out;
  }

  .todo-main-item label {
    cursor: pointer;
  }

  .todo-main-item label .checkbox {
    /* 指定行内元素垂直对齐方式 */
    vertical-align: middle;
    margin-right: 6px;
  }

  .todo-main-item:hover {
    background-color: rgba(0, 0, 0, 0.1);
  }

  .todo-main-item:hover .btn-container {
    opacity: 1;
  }

  .todo-main-item .btn-container {
    padding: 2px;
    opacity: 0;
    transition: all 0.5s ease-in-out;
  }

  .todo-main-item .btn-container .btn-edit {
    margin-right: 10px;
  }

  .edit-input {
    font-size: 18px;
    padding: 5px 0;
    border: none;
    border-radius: 3px;
    outline: none;
  }

  .edit-input:focus {
    outline: 1px solid skyblue;
  }
</style>