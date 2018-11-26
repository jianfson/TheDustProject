<template>
  <div id="weekPredict">
    <div class="weekpredict-date-selector">
      <el-select size="mini" v-model="rangeSeleted"  @change="submitPredictRange">
        <el-option  v-for="(item, index) in seletorRange" :key="index" :label="item.label" :value="item.value"></el-option>
      </el-select>
    </div>
    <div id="weekPredictCon">
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      weekPredictGraph: null,
      rangeSeleted: null,
      seletorRange: [{ value: 'oneweek', label: '未来一周' }, { value: 'twoweek', label: '未来两周' }]
    }
  },
  mounted() {
    this.weekPredictGraph = this.echarts.init(document.getElementById('weekPredictCon'))
  },
  methods: {
    submitPredictRange(selected) {
      // console.log(selected)
      let range = this.rangeSeleted
      let dateNow = new Date()
      let rangeNum = range === 'oneweek' ? 7 : 14
      let rangeResult = []
      for (let i = 1; i <= rangeNum; i++) {
        var currentdate = new Date(dateNow.setDate(dateNow.getDate() + i))
        rangeResult.push(this.dateFomatter(currentdate))
      }
      this.weekPredictGraph.setOption({
        'title': {
          text: '随时间变化的预测趋势'
        },
        xAxis: {
          type: 'category',
          data: rangeResult
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: rangeNum === 7 ? [820, 932, 901, 934, 1290, 1330, 1320] : [934, 1290, 20, 932, 901, 934, 1290, 934, 1290, 20, 932, 901, 934, 1290],
          type: 'line'
        }]
      })
    },
    dateFomatter(dateObj) {
      return dateObj.getFullYear() + '-' + this.addZero(dateObj.getMonth() + 1) + '-' + this.addZero(dateObj.getDate())
    },
    addZero(num) {
      return num < 10 ? '0' + num : num
    }
  }
}
</script>

<style>
#weekPredict {
  width: 100%;
  height: 100%;
}
#weekPredictCon {
  width: 100%;
  height: calc(100% - 40px);
}
.weekpredict-date-selector {
  height: 40px;
}
</style>
