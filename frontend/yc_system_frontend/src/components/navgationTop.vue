<template>
  <div class="nav-container clearfix">
    <div class="nav-left float-l">
      <span class="nav-title">成都市扬尘污染大数据分析系统</span>
      <span class="nav-logo">logo is here</span>
    </div>
    <div class="nav-middle">
      <ul class="nav-list clearfix" @click="jump" >
        <li v-for="(item,index) in classfication" :data-index="index" :key="index" :class="{active: index === Number(activeIndex)}">{{ item.name }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'navgationTop',
  data() {
    return {
      activeIndex: null,
      classfication: null
    }
  },
  methods: {
    jump(e) {
      // console.log(e.target.dataset.index)
      this.classfication.forEach((item, index) => {
        if (Number(index) === Number(e.target.dataset.index)) {
          this.activeIndex = index
          console.log(e.target.dataset.index)
          this.$router.push({ path: item.path })
        }
      })
    }
  },
  created() {
    // console.log(this.$router.path)
    this.classfication = this.$router.options.routes.slice(1)
    const currentIndex = sessionStorage.getItem('activeIndex')
    this.activeIndex = currentIndex === undefined ? 0 : currentIndex
    this.classfication.forEach((item, idx) => {
      if (idx === this.activeIndex) {
        this.activeIndex = idx
      }
    })
  },
  watch: {
    activeIndex: function (newValue, oldValue) {
      sessionStorage.setItem('activeIndex', this.activeIndex)
    }
  }
}
</script>

<style>
.nav-container {
  width: 100%;
  background-color: #545c64;
  color: #fff;
  height: 50px;
  font-size: 1.2em;
  line-height: 50px;
}
.nav-title {
  display: inline-block;
  margin: 0 30px 0 20px;
}
.nav-logo {
  display: inline-block;
  margin-right: 100px;
}
.nav-list {
  margin-left: 400px;
  width: 50%;
  min-width: 400px;
}
.nav-list li {
  float: left;
  position: relative;
  width: 15%;
  text-align: center;
}
.nav-list li:hover {
  cursor: pointer;
  color: #ffd04b;
}
.nav-list li.active {
  color: #ffd04b;
}
.nav-list li.active::before {
  content: "";
  position: absolute;
  margin: 0 50%;
  transform: translateX(-50%);
  width: 70%;
  height: 2px;
  background-color: #ffd04b;
  top: 48px;
  left: 0;
}
</style>
