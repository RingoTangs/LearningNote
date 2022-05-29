import router from './router'

// 全局前置导航首位
router.beforeEach((to, from, next) => {
  console.log('全局前置路由守卫');
  next()
})

// 全局后置导航首位来修改网站标题
router.afterEach((to, from) => {
  console.log('全局后置路由守卫');
  document.title = `${to.meta.title} - vue_test`
})
