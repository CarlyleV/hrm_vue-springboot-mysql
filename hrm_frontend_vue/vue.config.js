module.exports = {
    //scss グローバルインポート 
    css: {
      loaderOptions: {
        sass: {
          prependData: `@import "./src/assets/browser_support.scss"; `
        }
      }
    },
    devServer: {
      port: '8080',
      open: true,
      proxy: {
        '/api': {
          target: 'http://localhost:3000',
          changeOrigin: true,
          pathRewrite: {'^/api': ''}
        }
      }
  }
}