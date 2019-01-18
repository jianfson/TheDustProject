/*
 * @Author: yang
 * @Date: 2018-12-23 14:18:18
 * @Last Modified by: mikey.zhaopeng
 * @Last Modified time: 2019-01-17 16:14:33
 * description: 此组件是用于综合查询页面的折线图组件
 * 初始页面时，默认加载的是全市按年度的历史数据（2015-2018）
 * 向父组件传drawGraph方法，通过此方法实现切换区域，对应的折线图相应变化。
 */

<template>
  <div class="historyDate">
    <div id="historyGraphcon"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      zoneRadio: 'allchengdu',
      pickedDate: null,
      historyGraph: null,
      showedArea: null
      // graphOption: null
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
    // 页面初始化时，向后端请求： 2015-2018全市年度数据
    // this.$axios.get('/dust/webresourcses/database.dayavg/init').then((data) => {
    //   // console.log(data)
    //   const historyOption = {
    //     // title: {
    //     //   text: '扬尘历史数据',
    //     //   padding: [5, 20]
    //     // },
    //     toolbox: {
    //       feature: {
    //         dataZoom: {
    //           yAxisIndex: false
    //         }
    //       }
    //     },
    //     tooltip: {
    //       trigger: 'axis'
    //     },
    //     xAxis: {
    //       type: 'category',
    //       data: data.data.category
    //     },
    //     yAxis: {
    //       type: 'value',
    //       name: 'μg/m³'
    //     },
    //     series: [{
    //       data: data.data.data,
    //       // data: data.data.spring,
    //       type: 'line'
    //     }]
    //   }
    //   this.graphOption = historyOption
    //   this.historyGraph.setOption(historyOption)
    // })
  },
  methods: {
    dateFormateMonth(date) {
      var baseDate = new Date(date)
      let Y = baseDate.getFullYear()
      let M = baseDate.getMonth() + 1
      M = M < 10 ? '0' + M : M
      return Y + '-' + M
    },
    /*
    * drawGraph(area|string, date|string, method)
    * area: 所选区域，
    * date: 时间范围，
    * method： 显示方式（按年度，季度，月度）
    * return null
    */
    setLinechartsData(category, data) {
      const historyOption = {
        xAxis: {
          type: 'category',
          data: category
        },
        tooltip: {
          trigger: 'axis'
        },
        yAxis: {
          type: 'value',
          name: 'μg/m³'
        },
        series: [{
          data: data,
          type: 'line'
        }]
      }
      this.historyGraph.setOption(historyOption)
    },
    drawGraph(formdata) {
      let startDate = this.dateFormateMonth(formdata.date)
      if (formdata.method === 'year') {
        this.$axios.get('/dust/webresourcses/database.dayavg/year/' + formdata.area).then(res => {
          let dataTransfer = {
            category: [],
            pm10: []
          }
          res.data.data.forEach(element => {
            dataTransfer.category.push(element.avgTime)
            dataTransfer.pm10.push(element.pm10)
          })
          this.setLinechartsData(dataTransfer.category, dataTransfer.pm10)
        })
      } else if (formdata.method === 'month') {
        this.$axios.get('/dust/webresourcses/database.dayavg/month/' + formdata.area + '/' + startDate).then(res => {
          let dataTransfer = {
            category: [],
            pm10: []
          }
          res.data.data.forEach(element => {
            dataTransfer.category.push(element.avgTime)
            dataTransfer.pm10.push(element.pm10)
          })
          this.setLinechartsData(dataTransfer.category, dataTransfer.pm10)
        })
      } else {
        this.$axios.get('/dust/webresourcses/database.dayavg/season/' + formdata.area + '/' + formdata.method).then(res => {
          let dataTransfer = {
            category: [],
            pm10: []
          }
          res.data.data.forEach(element => {
            dataTransfer.category.push(element.avgTime)
            dataTransfer.pm10.push(element.pm10)
          })
          this.setLinechartsData(dataTransfer.category, dataTransfer.pm10)
        })
      }
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
