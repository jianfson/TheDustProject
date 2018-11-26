<template>
  <div class="historyDate">
    <div id="historyGraphcon"></div>
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  data() {
    return {
      zoneRadio: 'allchengdu',
      pickedDate: null,
      historyGraph: null,
      showedArea: null
    }
  },
  computed: {
    endDate() {
      var currentDate = new Date()
      return this.dealDate(currentDate)
    }
  },
  mounted() {
    this.historyGraph = this.echarts.init(document.getElementById('historyGraphcon'))
    Axios.get('http://localhost:3000/history/chengdu').then((data) => {
      const historyOption = {
        title: {
          text: '各区域扬尘历史数据',
          padding: [5, 20],
          subtext: '全市',
          subtextStyle: {
            color: 'rgba(0,0,0,0.8)',
            fontWeight: 'normal'
          }
        },
        toolbox: {
          feature: {
            dataZoom: {
              yAxisIndex: false
            }
          }
        },
        dataZoom: [
          {
            type: 'slider',
            show: true,
            start: 80,
            end: 100
          },
          {
            type: 'inside',
            start: 80,
            end: 100
          }
        ],
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: data.data.category
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: data.data.data,
          type: 'bar'
        }]
      }
      this.historyGraph.setOption(historyOption)
    })
  },
  methods: {
    submitHistory() {
      switch (this.zoneRadio) {
        case 'allchengdu':
          // console.log(this.pickedDate[0])
          this.drawGraph(`http://localhost:3000/history/all/${this.pickedDate[0]}/${this.pickedDate[1]}`)
          console.log(`http://localhost:3000/history/all/${this.pickedDate[0]}/${this.pickedDate[1]}`)
          break
        case 'particalArea':
          this.drawGraph('http://localhost:3000/history/zones/' + this.pickedDate[0] + '/' + this.pickedDate[1])
          break
        case 'particalSpot':
          this.drawGraph('http://localhost:3000/history/spots/' + this.pickedDate[0] + '/' + this.pickedDate[1])
          break
      }
    },
    drawGraph(url) {
      Axios.get(url).then(data => {
        var realseries = null
        var realLegend = null
        if (data.data.name === 'allcityByTime') {
          realseries = [{
            data: data.data.data,
            type: 'bar'
          }]
        } else if (data.data.name === 'eachZones') {
          realLegend = {
            data: ['成华区', '高新区', '双流区', '金牛区', '武侯区', '青羊区'],
            left: '30%'
          }
          realseries = [{
            name: '成华区',
            data: data.data.data.chenghua,
            type: 'bar'
          },
          {
            name: '高新区',
            data: data.data.data.gaoxing,
            type: 'bar'
          },
          {
            name: '双流区',
            data: data.data.data.shuangliu,
            type: 'bar'
          },
          {
            name: '金牛区',
            data: data.data.data.jinniu,
            type: 'bar'
          },
          {
            name: '武侯区',
            data: data.data.data.wuhou,
            type: 'bar'
          },
          {
            name: '青羊区',
            data: data.data.data.qingyang,
            type: 'bar'
          }]
        } else if (data.data.name === 'eachSpots') {
          realseries = [{
            // 逻辑还没确定
          }]
        }
        const historyOption = {
          xAxis: {
            type: 'category',
            data: data.data.category
          },
          legend: realLegend,
          dataZoom: [
            {
              type: 'slider',
              show: true,
              start: 0,
              end: 100
            },
            {
              type: 'inside',
              start: 0,
              end: 100
            }
          ],
          yAxis: {
            type: 'value'
          },
          series: realseries
        }
        this.historyGraph.setOption(historyOption)
      })
    },
    xAxisNamesCompute(datestr) {
      let inputDate = new Date(datestr)
      let week = [this.dealDate(inputDate)]

      for (let i = 0; i < 6; i++) {
        inputDate.setDate(inputDate.getDate() - 1)
        week.push(this.dealDate(inputDate))
      }
      return week.reverse()
    },
    dealDate(date) {
      return date.getFullYear() + '-' + this.addZero(date.getMonth() + 1) + '-' + this.addZero(date.getDate())
    },
    addZero(num) {
      return num < 10 ? '0' + num : num
    }
  }
}
</script>

<style>
.historyDate {
  width: 100%;
  height: 100%;
}
#historyGraphcon {
  width: 100%;
  height: 100%;
}
</style>
