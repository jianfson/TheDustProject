<template>
  <div class="container">
    <expanded-line-chart ref="chart"></expanded-line-chart>
  </div>
</template>

<script>
import expandedLineChart from '../components/charts/expandedLineChart'
export default {
  name: 'test',
  data() {
    return {}
  },
  components: {
    expandedLineChart
  },
  mounted() {
    this.$refs.chart.drawLineChart({
      data: [1, 2, 3],
      category: [1, 2, 3]
    })
  }
}
</script>

<style>
.container {
  width: 100%;
  height: 1000px;
}
#temppostion {
  width: 100px;
  height: 600px;
}
</style>
