<template>
    <div class="school">
        <h2>学校名称: {{ name }}</h2>
        <h2>学校地址: {{ address }}</h2>
        <button @click="showName">点我提示学校名</button>
    </div>
</template>

<script>
// 正规写法应该是:
// export default Vue.extend({
// 	// ..
// })
// 但是 Vue.extend() 可以省略
export default {
    name: 'School',
    data() {
        return {
            name: 'xxx学校',
            address: '北京',
        };
    },
    methods: {
        showName() {
            alert(this.name);
        },
    },
};
</script>

<style scoped>
.school {
    background-color: pink;
}
</style>
