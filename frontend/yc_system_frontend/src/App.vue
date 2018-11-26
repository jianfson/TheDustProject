<template>
  <div id="app">
    <!-- <navgation-top></navgation-top>
    <keep-alive>
      <router-view/>
    </keep-alive> -->
    <el-container id="height-outter">
      <el-header>
        <div class="topHeader">
          <ul class="topBanner">
            <li class="top-title">成都市扬尘污染大数据分析系统</li>
            <li>logo</li>
          </ul>
        </div>
      </el-header>
      <el-container id="height-container">
        <el-aside class="leftside-nav" :width="navWidth">
          <div id="hideNavBtn" @click="toggleCollapse" :class="{collapesd: isCollapse}"><i class="iconfront-yc icon-yc-gengduo"></i></div>
          <el-menu class="leftside-nav-menu" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b" default-active="/home" :collapse="isCollapse" :router="true">
            <el-menu-item index="/home">
              <i class="iconfront-yc icon-yc-shouye"></i>
              <span>首页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </el-menu-item>
            <el-menu-item index="/historysearch">
              <i class="iconfront-yc icon-yc-chaxun"></i>
              <span>综合查询</span>
            </el-menu-item>
            <el-menu-item index="/analysis">
              <i class="iconfront-yc icon-yc-shujutongji1"></i>
              <span>统计分析</span>
            </el-menu-item>
            <el-menu-item index="/control">
              <i class="iconfront-yc icon-yc-strategy"></i>
              <span>智能管控</span>
            </el-menu-item>
            <el-menu-item index="/correction">
              <i class="iconfront-yc icon-yc-xiugaiziliao"></i>
              <span>修正数据</span>
            </el-menu-item>
            <el-menu-item index="/helpdoc">
              <i class="iconfront-yc icon-yc-bangzhu"></i>
              <span>帮助文档</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
      <el-main id="router-container">
        <router-view></router-view>
      </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import navgationTop from './components/navgationTop'
export default {
  name: 'App',
  components: {
    navgationTop
  },
  data() {
    return {
      isCollapse: false,
      navWidth: '160px'
    }
  },
  mounted() {
    // console.log(this.$router.currentRoute)
  },
  methods: {
    toggleCollapse() {
      this.isCollapse = !this.isCollapse
      this.navWidth = this.navWidth === '160px' ? '70px' : '160px'
    }
  }
}
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  height: 100%;
}
#height-outter {
  height: 100%;
}
#height-container {
  height: 100%;
}
.el-header {
  padding: 0;
}
.topHeader {
  background-color: #545c64;
  height: 60px;
  line-height: 60px;
  color: white;
}
.topBanner {
  float: left;
  *zoom: 1;
}
.topBanner::after {
  content: "";
  clear: both;
  height: 0;
  display: block;
}
.topBanner li {
  display: inline-block;
}
.top-title {
  width: 300px;
  height: 60px;
  text-align: center;
  font-size: 1.2em;
}

.leftside-nav {
  text-align: center;
  background-color: #545c64;
  transition: all 0.3s;
}

.leftside-nav-menu {
  border: none;
}
#hideNavBtn {
  font-size: 20px;
  color: white;
  text-align: right;
  cursor: pointer;
}
#hideNavBtn i {
  font-size: 20px;
  padding-right: 10px;
}
#hideNavBtn.collapesd {
  text-align: center;
  padding: 0 0 0 2px;
}
/* 不要设置用类设置elementui的组件，此时设置的可能会在后面继续用次组件的时候就不是初始的状态了 */
/* .el-main {
  height: 100%;
} */
#router-container {
  padding: 0;
}
</style>
