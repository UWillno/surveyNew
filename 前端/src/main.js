import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//导入路由配置
import router from './router/index.js'
//导入cookie
import VueCookie from 'vue-cookie'
import App from './App.vue'

Vue.config.productionTip = false
/* 使用element-ui */
Vue.use(ElementUI);
/* 使用cookie */
Vue.use(VueCookie);

new Vue({
  render: h => h(App),
  router  //使用路由
}).$mount('#app')
