// 浏览器不认识这行代码, 会报错(这只是 CLI 的基本形式)
import App from "./App.vue";

const vm = new Vue({
  el: "#app",
  template: `<App></App>`, // template 会将 HTML 中的容器全部替换
  components: { App },
});
