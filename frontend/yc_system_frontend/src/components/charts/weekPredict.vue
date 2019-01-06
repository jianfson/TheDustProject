<template>
  <div id="weekPredictCon"></div>
</template>

<script>
export default {
  data() {
    return {
      weekPredictGraph: null,
      linePredictOptions: null
    }
  },
  mounted() {
    this.weekPredictGraph = this.echarts.init(document.getElementById('weekPredictCon'))
    this.linePredictOptions = {
      tooltip: {
        trigger: 'axis'
      },
      xAxis: {
        type: 'category',
        data: null
      },
      yAxis: {
        type: 'value',
        name: 'μg/m³'
      },
      series: [{
        data: null,
        type: 'line'
      }]
    }
  },
  methods: {
    drawLinePredict(data) {
      // console.log(data)
      this.linePredictOptions.xAxis = {
        type: 'category',
        data: data.category
      }
      this.linePredictOptions.series = [{
        data: data.data,
        type: 'line'
      }]
      this.weekPredictGraph.setOption(this.linePredictOptions)
    }
  }
}
</script>

<style>
#weekPredictCon {
  width: 100%;
  height: 100%;
  min-width: 250px;
}
</style>
