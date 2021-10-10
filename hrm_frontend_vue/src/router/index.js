import { createRouter, createWebHashHistory } from 'vue-router'
import store from '../store'

import login from '../views/login.vue'
import user from '../views/user.vue'


const routes = [
  {
    path: '/',
    name: 'login',
    component: login,
  },
  {
    path: '/user/:user_name',
    name: 'user',
    component: user,
  },
]



const router = createRouter({
  history: createWebHashHistory(),
  routes
})


//グローバルビフォーガード
router.beforeEach((to,from,next) => {

  //未ログイン時のuserページ接続制限
  if(to.name !== 'login'&& !store.state.isLogin){
    next('/')
  //ログイン済みの時のloginページ接続制限
  }else if(to.name == 'login'&&store.state.isLogin){
    next(`/user/${store.state.user_name}`)
  }else{
    next()
  }



});


export default router
