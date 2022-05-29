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
            component: () => import('../pages/MessageDetails')
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
