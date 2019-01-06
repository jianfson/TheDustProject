<template>
  <div id="predictLineSession"></div>
</template>

<script>
export default {
  data() {
    return {
      sessionLineChart: null,
      sessionOption: null
    }
  },
  mounted() {
    this.sessionLineChart = this.echarts.init(document.getElementById('predictLineSession'))
    this.sessionOption = {
      color: ['#003366', '#006699', '#4cabce', '#e5323e'],
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow'
        }
      },
      legend: {
        data: ['春季', '夏季', '秋季', '冬季']
      },
      xAxis: [
        {
          type: 'category',
          data: null
        }
      ],
      yAxis: {
        type: 'value',
        name: 'μg/m³'
      },
      series: null
    }
  },
  methods: {
    drawAllPredict(data) {
      this.sessionOption.xAxis[0] = {
        type: 'category',
        data: data.category
      }
      this.sessionOption.series = [{
        name: '春季',
        type: 'bar',
        data: data.spring
      },
      {
        name: '夏季',
        type: 'bar',
        data: data.summer
      },
      {
        name: '秋季',
        type: 'bar',
        data: data.autumn
      },
      {
        name: '冬季',
        type: 'bar',
        data: data.winter
      }]
      this.sessionLineChart.setOption(this.sessionOption)
    }
  }
}
</script>

<style>
#predictLineSession {
  width: 100%;
  height: 100%;
}
</style>
