<template>
  <div class="search">
    <header>
      <ul class="clearfix" id="subnav" @click="jump">
        <li v-for="(item,index) in subnavContent" v-bind:key="index" :data-index="index">{{ item.name }}</li>
        <li class="movebar"></li>
      </ul>
    </header>
    <section>
      <router-view></router-view>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: 0,
      subnavContent: [
        { name: '区域管理' },
        { name: '历史数据' }
      ]
    }
  },
  methods: {
    jump(e) {
      this.activeIndex = e.target.dataset.index
      // let navbarObj = document.getElementById('subnav')
      // navbarObj.style.transform = `translateX(${this.activeIndex * 100}px)`
    }
  }
}
</script>

<style>
.search {
  height: 500px;
}
.search header {
  padding: 0 100px;
}
#subnav {
  width: 200px;
  margin: 20px auto;
  height: 30px;
  line-height: 30px;
  position: relative;
}
/* 滑动块 */
#subnav li {
  display: inline-block;
  position: relative;
  margin: 0 0 0 2px;
  width: 99px;
  text-align: center;
  float: left;
}
li.movebar {
  width: 100%;
  height: 2px;
  background-color: #409eff;
  bottom: 0;
  left: 0;
}
#subnav li:first-child {
  margin: 0;
}
#subnav li::after {
  position: absolute;
  right: -2px;
  content: "";
  width: 2px;
  height: 100%;
  background-color: #e4e7ed;
  z-index: 1;
  padding: 0;
  margin: 0;
}
/* #subnav::after {
  visibility: visible;
  width: 99px;
  height: 2px;
  background-color: #409eff;
  position: absolute;
  bottom: -10px;
  left: 0;
  z-index: 100;
  transform: translateX(0);
  transition: transform 0.3s ease;
} */
#subnav li:last-child::after {
  display: none;
}
</style>
