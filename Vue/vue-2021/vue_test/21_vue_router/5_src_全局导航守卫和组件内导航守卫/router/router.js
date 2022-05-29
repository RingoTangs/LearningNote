import Vue from 'vue'
import VueRouter from 'vue-router'
import routes from './routes'

Vue.use(VueRouter)

// 解决重复点同一个路由报错问题
// const originalPush = VueRouter.prototype.push
// const originalReplace = VueRouter.prototype.replace
// VueRouter.prototype.push = function push(location) {
//   return originalPush.call(this, location).catch((err) => err)
// }
// VueRouter.prototype.replace = function replace(location) {
//   return originalReplace.call(this, location).catch((err) => err)
// }

const router = new VueRouter({
  routes,
})

export default router
