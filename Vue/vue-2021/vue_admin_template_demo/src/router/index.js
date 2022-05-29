import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login')
  }
]

function createRouter() {
  return new Router({
    // scrollBehavior: () => ({ y: 0 }),
    routes: constantRoutes
  })
}

const router = createRouter()

export default router
