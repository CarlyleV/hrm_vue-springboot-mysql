import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import axios from 'axios'
import VueAxios from 'vue-axios'

import './assets/remedy.css' //reset css
require('./assets/browser_support.scss') // ブラウズ対応



const app = createApp(App);
app.use(store);
app.use(router);
app.use(VueAxios,axios);
app.mount('#app');
