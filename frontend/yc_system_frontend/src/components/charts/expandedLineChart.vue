<template>
  <div id="linemain"></div>
</template>

<script>
export default {
  name: 'lineCharts',
  props: ['chartData'],
  data() {
    return {
      linechart: null
    }
  },
  mounted() {
    var linechart = this.echarts.init(document.getElementById('linemain'))
    var option = {
      xAxis: {
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
      },
      yAxis: {
        type: 'value'
      },
      series: [{
        data: [820, 932, 901, 934, 1290, 1330, 1320],
        type: 'line'
      }]
    }
    linechart.setOption(option)
  },
  methods: {
    drawLineChart(data) {
      // var chart = this.echarts.init(document.getElementById('linemain'))
      // chart.setOption({
      //   xAxis: {
      //     type: 'category',
      //     data: data.category
      //   },
      //   yAxis: {
      //     type: 'value'
      //   },
      //   series: [{
      //     data: data.data,
      //     type: 'line'
      //   }]
      // })
    }
  },
  watch: {
    chartData(newval, oldval) {
      var linechart = this.echarts.init(document.getElementById('linemain'))
      var option = {
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [820, 932, 901, 934, 1290, 1330, 1320],
          type: 'line'
        }]
      }
      linechart.setOption(option)
    }
  }
}
</script>

<style>
#linemain {
  width: 300px;
  height: 300px;
}
</style>
