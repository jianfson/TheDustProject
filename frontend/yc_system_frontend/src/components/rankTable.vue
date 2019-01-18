<template>
  <div>
    <el-table :data="contextForTable" size="mini" border :cell-class-name="tableCellClassName"
     :default-sort = "{prop: 'pm10', order: 'descending'}">
      <!-- :row-class-name="tableRowClassName" -->
      <el-table-column label="点位名称" prop="deviceName"></el-table-column>
      <el-table-column label="点位ID" prop="deviceId" sortable></el-table-column>
      <el-table-column label="工程位置" prop="deviceAddress"></el-table-column>
      <el-table-column label="pm10" prop="pm10" sortable></el-table-column>
      <el-table-column label="污染等级" prop="pollutionRank"></el-table-column>
      <!-- <el-table-column type="expand">
        <template>
          <div class="expand-graph-contaienr" id="expandGraph">
            <expanded-line-chart :chart-data="expandedChartData"></expanded-line-chart>
          </div>
        </template>
      </el-table-column>-->
    </el-table>
  </div>
</template>

<script>
import expandedLineChart from './charts/expandedLineChart'
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
    tableCellClassName({ row, column, rowIndex, columnIndex }) {
      if (columnIndex === 4 && row.pollutionRank === '差') {
        return 'level3-cell'
      } else if (columnIndex === 4 && row.pollutionRank === '中') {
        return 'level2-cell'
      }
    }
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
.el-table .level3-cell {
  background-color: red;
}
.el-table .level2-cell {
  background-color: yellow;
}
.expand-graph-contaienr {
  min-width: 400px;
  width: 50%;
  height: 300px;
}
</style>
