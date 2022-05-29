import Vue from 'vue'
import VueRouter from 'vue-router'

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

const routes = [
  {
    path: '/',
    redirect: '/about',
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('../pages/About.vue'),
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('../pages/Home.vue'),
    children: [
      {
        path: 'news',
        name: 'News',
        component: () => import('../pages/News.vue'),
      },
      {
        path: 'message',
        name: 'Message',
        component: () => import('../pages/Message.vue'),
        children: [
          {
            path: ':id',
            name: 'MessageDetails',
            component: () => import('../pages/MessageDetails'),
            // props 的第一种写法, 值为对象, 该对象中的所有 k-v 都会以 props 的形式传给 MessageDetails 组件
            // props: { a: 1, b: 'hello' },

            // props 的第二种写法, 若值为 true, 就会把该路由组件收到的所有 params 参数, 以 props 的形式传给 Detail 组件
            // props: true,

            // props 的第三种写法, 值为函数
            // 连续解构赋值语句
            props: ({ params, query }) => ({ id: params.id, title: query.title })
          }
        ],
      },
    ],
  },
]

const router = new VueRouter({
  routes,
})

export default router
