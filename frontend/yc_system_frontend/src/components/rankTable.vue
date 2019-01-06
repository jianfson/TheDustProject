<template>
  <div>
    <el-table
      :data="polutionRank"
      :row-class-name="tableRowClassName"
      size="mini"
      border
      @expand-change="drawInnerGraph"
    >
      <el-table-column label="点位名称" prop="name"></el-table-column>
      <el-table-column label="工程位置" prop="location"></el-table-column>
      <el-table-column label="污染等级" prop="level"></el-table-column>
      <el-table-column label="变化趋势" prop="trend"></el-table-column>
      <el-table-column type="expand">
        <template>
          <div class="expand-graph-contaienr">
            <expanded-line-chart :chart-data="expandedChartData"></expanded-line-chart>
          </div>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import expandedLineChart from './charts/expandedLineChart'
export default {
  name: 'rankTable',
  components: {
    expandedLineChart
  },
  data() {
    return {
      expandedChartData: null, // 展开表格的数数据
      polutionRank: [{
        name: 'Y0028152017154',
        location: '成都后花园二期B区一标段',
        level: '3级',
        trend: '···'
      }, {
        name: '1440-0028-sclw-2859',
        location: '成都市锦江区娇子立交以南，成龙路以西项目',
        level: '3级',
        trend: '···'
      }, {
        name: 'Y0028141914002',
        location: '五横道北延线工程 ',
        level: '2级',
        trend: '···'
      }, {
        name: 'Y0028091510021',
        location: '成都科学城生态水环境工程项目部EPC总承包',
        level: '1级',
        trend: '···'
      }, {
        name: '1440-0028-sclw-2814',
        location: '绿岛筑三标段15号楼',
        level: '1级',
        trend: '···'
      }, {
        name: 'Y0028152618102',
        location: '锦巷兰台小区',
        level: '1级',
        trend: '···'
      }, {
        name: 'XHHB-Y0017040100175',
        location: '坤力柳河左岸工程（1-5号楼、地下室工程）',
        level: '1级',
        trend: '···'
      }]
    }
  },
  methods: {
    tableRowClassName({ row, rowIndex }) {
      let level = this.polutionRank[rowIndex].level
      let result
      switch (level) {
        case '3级': result = 'level3-row'
          break
        case '2级': result = 'level2-row'
          break
        case '1级': result = 'level1-row'
          break
      }
      // console.log(result)
      return result
    },
    drawInnerGraph(row) {
      // row.name 是请求innergraph的id值，
      // console.log(row.name)
      // setTimeout(() => {
      //   this.$refs.expandLineChart.drawLineChart({
      //     data: [1, 2, 3],
      //     category: [1, 2, 3]
      //   })
      // }, 1000)
      this.expandedChartData = {
        data: [1, 2, 3],
        category: [1, 2, 3]
      }
      // this.$axios.get('http://localhost:3000/home/search/' + row.name + '时间段').then(res => {
      // })
    }
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
  width: 75%;
  height: 300px;
}
</style>
