<template>
  <div class="father">
    <div class="aside" :class="{active: actived}">aside</div>
    <div class="main" :class="{active: actived}">main</div>
    <i class="iconfront-yc icon-yc-jiantou4 arrowcc" @click="toggle"></i>
  </div>
</template>

<script>
export default {
  name: 'csstest',
  data() {
    return {
      actived: false
    }
  },
  methods: {
    toggle() {
      this.actived = !this.actived
    }
  }
}
</script>

<style>
.father {
  width: 100%;
  height: calc(100% - 50px);
  position: relative;
}
.aside {
  height: 100%;
  position: absolute;
  width: 300px;
  background-color: darkgreen;
  transition: left 1s ease;
}
.main {
  height: 100%;
  overflow: hidden;
  left: 300px;
  right: 0;
  position: absolute;
  background-color: aqua;
  transition: all 1s ease;
}
.aside.active {
  left: -300px;
}
.main.active {
  left: 0px;
}
.arrowcc{
  position: absolute;
  z-index: 10000;
  color: white;
  left: 280px;
  top: 50%;
}
</style>
