<template>
  <div class="predictGraph">
    <div id="predictLineSession"></div>
    <div class="backoff-btn" v-show="needbackoff" @click="backTop"><el-button size="mini" type="primary">上一层</el-button></div>
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  data() {
    return {
      sessionLineChart: null,
      sessionOption: null,
      needbackoff: false
    }
  },
  mounted() {
    this.sessionLineChart = this.echarts.init(document.getElementById('predictLineSession'))
    Axios.get('http://localhost:8080/static/json/predictAllYears.json').then(data => {
      var currentData = data.data.data
      console.log(currentData)
      this.sessionOption = {
        title: {
          text: '全市随季节变化趋势预测'
        },
        color: ['#003366', '#006699', '#4cabce', '#e5323e'],
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          // data: ['春季', '夏季', '秋季', '冬季'],
          data: ['Growth', 'Budget 2011', 'Budget 2012'],
          width: '300px'
        },
        xAxis: [
          {
            type: 'category',
            data: currentData.category
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: 'spring',
            type: 'bar',
            data: currentData.spring
          },
          {
            name: 'summer',
            type: 'bar',
            data: currentData.summer
          },
          {
            name: 'atum',
            type: 'bar',
            data: currentData.atum
          },
          {
            name: 'winter',
            type: 'bar',
            data: currentData.winter
          }
        ]
      }

      this.sessionLineChart.setOption(this.sessionOption)
      this.sessionLineChart.on('click', para => {
        // console.log(para)
        Axios.get('http://localhost:8080/static/json/predictParone.json').then(data => {
          var innerData = data.data
          console.log(innerData)
          // var currentOption = JSON.parse(JSON.stringify(this.sessionOption))
          this.sessionLineChart.clear()
          this.sessionLineChart.setOption({
            title: {
              text: '全市随季节变化趋势预测',
              padding: [40]
            },
            xAxis: [
              {
                type: 'category',
                data: ['春季', '夏季', '秋季', '冬季']
              }
            ],
            yAxis: [
              {
                type: 'value'
              }
            ],
            series: [
              {
                name: innerData.name,
                type: 'bar',
                data: innerData.data
              }
            ]
          })
          this.needbackoff = true
        })
      })
    })
  },
  methods: {
    backTop() {
      this.sessionLineChart.setOption(this.sessionOption)
      this.needbackoff = false
    }
  }
}
</script>

<style>
#predictLineSession {
  width: 100%;
  height: 100%;
}
.predictGraph {
  position: relative;
  width: 100%;
  height: 100%;
}
.backoff-btn {
  position: absolute;
  top: 10px;
  left: 20px;
}
</style>
