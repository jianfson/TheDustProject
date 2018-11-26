import Vue from 'vue'
import Router from 'vue-router'
const Home = () =>
  import('@/pages/Home')
const Analysis = () =>
  import('@/pages/Analysis')
const Control = () =>
  import('@/pages/Control')
const Correction = () =>
  import('@/pages/Correction')
const Helpdoc = () =>
  import('@/pages/Helpdoc')
const test = () =>
  import('@/pages/test')
// const csstest = () =>
//   import('@/pages/csstest')
const HistorySearch = () =>
  import('@/pages/HistorySearch')
// const HistoryData = () =>
//   import('@/pages/search/HistoryData')
// const ZoneManage = () =>
//   import('@/pages/search/ZoneManage')

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/home',
      name: '首页',
      component: Home
    },
    {
      path: '/historysearch',
      name: '综合查询',
      component: HistorySearch
    },
    {
      path: '/analysis',
      name: '统计分析',
      component: Analysis
    },
    {
      path: '/control',
      name: '智能管控',
      component: Control
    },
    {
      path: '/correction',
      name: '修正数据',
      component: Correction
    },
    {
      path: '/helpdoc',
      name: '帮助文档',
      component: Helpdoc
    },
    {
      path: '/test',
      name: 'test',
      component: test
    }
    // {
    //   path: '/csstest',
    //   name: 'csstest',
    //   component: csstest
    // }
  ]
})
