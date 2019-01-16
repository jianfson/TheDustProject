<template>
  <div>
    <el-table :data="contextForTable" size="mini" border>
      <!-- :row-class-name="tableRowClassName" -->
      <el-table-column label="点位名称" prop="device_id"></el-table-column>
      <el-table-column label="工程位置" prop="device_address"></el-table-column>
      <el-table-column label="污染等级" prop="level"></el-table-column>
      <!-- <el-table-column label="变化趋势" prop="trend"></el-table-column> -->
      <!-- <el-table-column type="expand">
        <template>
          <div class="expand-graph-contaienr" id="expandGraph">
            <expanded-line-chart :chart-data="expandedChartData"></expanded-line-chart>
          </div>
        </template>
      </el-table-column> -->
    </el-table>
  </div>
</template>

<script>
import expandedLineChart from './charts/expandedLineChart'
// import qs from 'qs'
export default {
  name: 'rankTable',
  props: ['dataForTable', 'contextForTable'],
  components: {
    expandedLineChart
  },
  data() {
    return {
      // expandedChartData: null, // 展开表格的数据
      // expandedOptions: null
    }
  },
  // mounted() {
  //   this.expandedChartData = this.echarts.init(document.getElementById('expandGraph'))
  //   this.expandedOptions = {
  //     tooltip: {
  //       trigger: 'axis'
  //     },
  //     xAxis: {
  //       type: 'category',
  //       data: null
  //     },
  //     yAxis: {
  //       type: 'value',
  //       name: 'μg/m³'
  //     },
  //     series: [{
  //       data: null,
  //       type: 'line'
  //     }]
  //   }
  // },
  methods: {
    // 绘出表格展开折线图的方法。
    // drawExpandLinePredict(data) {
    //   // console.log(data)
    //   this.expandedOptions.xAxis = {
    //     type: 'category',
    //     data: ['22','33']
    //   }
    //   this.expandedOptions.series = [{
    //     data: [10, 20],
    //     type: 'line'
    //   }]
    //   this.expandedChartData.setOption(this.expandedOptions)
    // },
    // tableRowClassName({ row, rowIndex }) {
    //   let level = this.polutionRank[rowIndex].level
    //   let result
    //   switch (level) {
    //     case '3级': result = 'level3-row'
    //       break
    //     case '2级': result = 'level2-row'
    //       break
    //     case '1级': result = 'level1-row'
    //       break
    //   }
    //   // console.log(result)
    //   return result
    // },
    // drawInnerGraph(row) {
    //   // 点击展开按钮，发起请求，携带点位id，时间段（包含开始时间，结束时间），返回此时间段的此点位的变化数据。
    //   /* 数据格式 {
    //     category: ['2017-01','2017-02', ...],
    //     data: [22,33,...]
    //   }
    //   **
    //   */
    //  this.drawExpandLinePredict({
    //    category: ['22','33'],
    //    data: [10, 20]
    //  })
    // }
  }
}
</script>

<style>
.el-table .level3-row {
  background-color: red;
}
.el-table .level2-row {
  background-color: orange;
}
.el-table .level1-row {
  background-color: yellow;
}
.expand-graph-contaienr {
  min-width: 400px;
  width: 50%;
  height: 300px;
}
</style>
