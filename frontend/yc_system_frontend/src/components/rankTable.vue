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
        name: 'test1',
        location: 'test1',
        level: '3级',
        trend: '···'
      }, {
        name: 'test2',
        location: 'test2',
        level: '2级',
        trend: '···'
      }, {
        name: 'test3',
        location: 'test3',
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
