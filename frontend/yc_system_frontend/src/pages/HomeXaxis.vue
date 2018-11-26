<template>
  <div class="home-page">
    <div class="left-container">
      <div class="search-bar" :class="{actived: showSearch}">
        <location-search></location-search>
      </div>
      <div class="middle-container" :class="{actived: showSearch}">
        <div class="map-container">
          <map-area></map-area>
        </div>
        <div class="graph-container">
          <line-charts></line-charts>
        </div>
      </div>
      <div class="arrow" :class="{actived: showSearch}" @click="toggleSearchBar">
        <i class="iconfront-yc icon-yc-jiantou4"></i>
      </div>
    </div>
    <div class="right-container">
      <div class="range-table">
        <rank-table></rank-table>
      </div>
    </div>
  </div>
</template>

<script>
import locationSearch from '../components/locationSearch'
import mapArea from '../components/mapArea'
import lineCharts from '../components/lineCharts'
import rankTable from '../components/rankTable'
export default {
  data() {
    return {
      showSearch: false
    }
  },
  components: {
    locationSearch,
    mapArea,
    lineCharts,
    rankTable
  },
  methods: {
    toggleSearchBar() {
      this.showSearch = !this.showSearch
    }
  }
}
</script>

<style>
.home-page {
  width: 100%;
  height: calc(100% - 50px);
  display: flex;
  flex-wrap: nowrap;
}
.left-container {
  height: 100%;
  width: 100%;
  flex-grow: 2;
  overflow: hidden;
  position: relative;
}
/* }组件的高度和父元素的关系？ */

.right-container {
  width: 300px;
  height: calc(100% - 40px);
}
.range-table {
  width: 100%;
  height: 100%;
  padding: 10px;
  padding: 20px 10px;
}
/*
* debug tansition done
*/
.search-bar {
  position: absolute;
  width: 300px;
  height: 100%;
  left: 0px;
  transition: transfrom 1s ease-in;
  padding: 20px 10px;
}
.search-bar.actived {
  left: -300px;
}
.middle-container {
  position: absolute;
  left: 300px;
  right: 0px;
  height: 100%;
  z-index: 100;
  display: flex;
  flex-direction: column;
  transition: left 0.4s ease;
}
.middle-container.actived {
  left: 0px;
}
.map-container {
  flex-grow: 2;
  height: calc(100% - 200px);
}
.graph-container {
  height: 200px;
}
.arrow {
  position: absolute;
  left: 270px;
  top: 50%;
  cursor: pointer;
  z-index: 1000;
  text-shadow: 1px 1px 6px rgba(100, 100, 100, 0.8);
}
.arrow:hover {
  transform: translateY(-2px);
  text-shadow: 1px 2px 10px rgba(100, 100, 100, 0.7);
}
.arrow i {
  font-size: 30px;
}
.arrow {
  transition: all 0.3s ease-in;
}
.arrow.actived {
  transform: rotateZ(180deg);
  left: 10px;
  z-index: 1000;
}
</style>
