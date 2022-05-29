'use strict'
const path = require('path')

function resolve(dir) {
  return path.join(__dirname, dir)
}

module.exports = {
  pages: {
    index: {
      // page 的入口
      entry: 'src/main.js',
    },
  },
  lintOnSave: false, // 关闭语法检查
  configureWebpack: {
    // 配置 webpack 路径解析别名
    resolve: {
      alias: {
        components: resolve('./src/components'),
        pages: resolve('./src/pages'),
        '@': resolve('/src'),
      },
    },
  },
  // 开启代理服务器
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8081',
        ws: true, // 支持 websocket
        // 用于控制请求头中的 host 值
        changeOrigin: true, // 是否跨域，虚拟的站点需要更管origin
        pathRewrite: {
          // '^/api'是一个正则表达式，表示要匹配请求的url中，全部'http://localhost:8081/api' 转接为 http://localhost:8081/
          '^/api': '',
        },
      },
    },
  },
}
