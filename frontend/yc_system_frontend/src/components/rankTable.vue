<template>
  <div>
    <el-table :data="polutionRank" :row-class-name="tableRowClassName" size="mini" border>
      <el-table-column label="点位名称" prop="name"></el-table-column>
      <el-table-column label="工程位置" prop="location"></el-table-column>
      <el-table-column label="污染等级" prop="level"></el-table-column>
      <el-table-column label="变化趋势" prop="trend"></el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'rankTable',
  data() {
    return {
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
    tableRowClassName({row, rowIndex}) {
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
</style>
