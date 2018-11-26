<template>
  <div class="history-container">
    <div class="h-leftbar">
      <div class="h-select">
        <div class="h-select-one">
            <el-radio-group v-model="historyRadio">
            <el-radio label="allchengdu">全成都</el-radio>
            <el-radio label="onlyzone">区域数据</el-radio>
            <el-radio label="spotdata">点位数据</el-radio>
          </el-radio-group>
        </div>
        <div class="h-select-two">
          <el-date-picker type="daterange" range-separator="至" start-placeholde="开始日期" end-placeholde="结束日期" v-model="historyTime"></el-date-picker>
        </div>
      </div>
      <div class="h-graph">
        <bar-charts></bar-charts>
      </div>
    </div>
    <div class="h-rightbar">
      <map-area></map-area>
    </div>
  </div>
</template>

<script>
import mapArea from '../../components/mapArea'
import barCharts from '../../components/barCharts'
export default {
  name: 'HistoryData',
  components: {
    mapArea,
    barCharts
  },
  data() {
    return {
      historyRadio: null,
      historyTime: null
    }
  }
}
</script>

<style>
.history-container {
  height: 100%;
  display: flex;
}
.h-leftbar {
  width: 40%;
  height: 100%;
  display: flex;
  flex-direction: column;
}
.h-select {
  margin: 0px auto;
  height: 120px;
}
.h-graph {
  flex-grow: 2;
  height: calc(100% - 80px);
  width: 100%;
}
.h-select-two {
  margin-top: 20px;
}
.h-rightbar {
  width: 60%;
  height: 100%;
}
</style>
