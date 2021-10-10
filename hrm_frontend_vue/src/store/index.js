import { createStore } from 'vuex'
import createPersistedState from "vuex-persistedstate";
import router from '../router'

export default createStore({
  state: {
    isLogin:false,
    user_name: "",
  },
  mutations: {
    login(state, param) {
      state.isLogin = true;
      state.user_name = param.user_name;
    },
    logout(state) {
      state.isLogin = false;
      state.user_name = '';
    }
  },
  actions: {
    login({ commit }, user_name) {
      const params = {
        user_name: user_name
      }
      if(user_name){
        commit('login', params);
        //動的ルートマッチング
        router.push({
          name:"user",
          params: {
            user_name: user_name
          }
        })
      }else{
        alert("名前を入力してください")
      }
    },
    logout({ commit }) {
      commit('logout');
      router.replace({path:"/"})
    }
  },
  modules: {
  },
  plugins: [createPersistedState({
    key: 'hrmApp',
    storage: window.sessionStorage
})]
})
