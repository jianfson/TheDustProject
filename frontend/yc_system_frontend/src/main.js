// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import './assets/common.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/icons/iconfont.css'
import Echarts from 'echarts'
import axios from 'axios'

Vue.prototype.$axios = axios
Vue.prototype.echarts = Echarts
// 区域标号转中文
Vue.prototype.$areaBelong = function (areaNum) {
  switch (areaNum) {
    case 'allcity':
      return '全市'
    case 510104:
      return '锦江区'
    case 510105:
      return '青羊区'
    case 510106:
      return '金牛区'
    case 510107:
      return '武侯区'
    case 510108:
      return '成华区'
    case 510109:
      return '高新区'
  }
}
Vue.use(Echarts)
Vue.use(ElementUI)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
