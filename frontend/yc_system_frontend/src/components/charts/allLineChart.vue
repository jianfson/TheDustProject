<template>
  <div class="allInOne-con">
    <div id="allInOne"></div>
  </div>
</template>

<script>
export default {
  props: ['graphData'],
  data() {
    return {
    }
  },
  methods: {
    grapmaker(data, category) {
      var allLieChart = this.echarts.init(document.getElementById('allInOne'))
      const option = {
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: category
        },
        yAxis: {
          type: 'value',
          name: 'μg/m³'
        },
        series: [
          {
            name: '全市',
            type: 'line',
            data: data
          }
        ]
      }
      allLieChart.setOption(option)
    }
  }
}
</script>

<style scoped>
#allInOne {
  width: 100%;
  height: 100%;
}
.allInOne-con {
  height: 100%;
}
</style>
