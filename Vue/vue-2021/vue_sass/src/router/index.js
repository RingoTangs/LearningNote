import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/dashboard',
    meta: { title: 'sass' }
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    meta: { title: 'dashboard' },
    component: () => import('@/views/Dashboard.vue')
  },
  {
    path: '/css-extensions',
    name: 'CSSExtensions',
    meta: { title: 'css-extensions' },
    component: () => import('@/views/CSSExtensions.vue')
  },
  {
    path: '/sass-script',
    name: 'SassScript',
    meta: { title: 'sass-script' },
    component: () => import('@/views/SassScript.vue')
  },
  {
    path: '/roles-and-directives',
    name: 'RulesAndDirectives',
    meta: { title: 'roles-and-directives' },
    component: () => import('@/views/RulesAndDirectives.vue')
  }
]

const router = new VueRouter({ routes })

router.afterEach((to) => {
  document.title = to.meta.title + '-study'
})

export default router
