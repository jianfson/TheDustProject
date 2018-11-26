<template>
  <div class="search">
    <header>
      <el-tabs type="card" @tab-click="jump">
        <el-tab-pane v-for="(item, index) in subnavContent" :key="index" :label="item.name"></el-tab-pane>
      </el-tabs>
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
        { name: '区域管理', routerName: '/compresearch/zonemanage' },
        { name: '历史数据', routerName: '/compresearch/history' }
      ]
    }
  },
  methods: {
    jump(component) {
      let index = component.index
      console.log(this.subnavContent[index].routerName)
      this.$router.push({path: this.subnavContent[index].routerName})
    }
  },
  mounted() {
    this.$router.push({path: '/compresearch/zonemanage'})
  }
}
</script>

<style>
.search {
  height: 500px;
  height: calc(100% - 56px);
}
.search header {
  margin-top: 5px;
}
#inner-container {
  padding: 10px 20px 0px 20px;
}
.search section {
  height: calc(100% - 56px);
}
</style>
