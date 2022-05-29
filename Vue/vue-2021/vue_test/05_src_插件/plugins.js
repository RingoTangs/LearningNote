export default {
    // install 的参数是 Vue 的构造函数, 而且还能自己携带配置项
    install(Vue, options) {
        console.log('options:', options)
        // 全局过滤器: 字符串截取
        Vue.filter('sliceStr', (value, start = 0, end = value.length) => {
            console.log('sliceStr filter')
            return value.slice(start, end)
        })
        // 全局自定义指令: 修改字体
        // element 是真实的 DOM 元素
        Vue.directive('font', (element, binding) => {
            console.dir(element)
            console.log(binding)
            element.style[binding.arg] = binding.value
        })
        // 定义全局混入
        Vue.mixin({
            mounted() {
                console.log('mounted')
            },
        })
        // Vue 原型对象上添加方法（vm 和 vc 就都能用了）
        Vue.prototype.$hello = () => alert('hello')

    }
}

