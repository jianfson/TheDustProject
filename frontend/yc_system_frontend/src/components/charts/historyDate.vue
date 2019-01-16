/*
 * @Author: yang
 * @Date: 2018-12-23 14:18:18
 * @Last Modified by: mikey.zhaopeng
 * @Last Modified time: 2019-01-15 17:05:18
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
// import qs from 'qs'
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
      // console.log(formdata)
      let startDate = this.dateFormateMonth(formdata.date)
      if (formdata.method === 'year') {
        this.$axios.get('/dust/webresourcses/database.dayavg/year/' + formdata.area).then(res => {
          this.setLinechartsData(res.data.category, res.data.pm10)
        })
      } else if (formdata.method === 'month') {
        this.$axios.get('/dust/webresourcses/database.dayavg/month/' + formdata.area + '/' + startDate).then(res => {
          this.setLinechartsData(res.data.category, res.data.pm10)
          // console.log(res.data)
        })
      } else {
        this.$axios.get('/dust/webresourcses/database.dayavg/season/' + formdata.area + '/' + formdata.method).then(res => {
          // console.log(res.data)
          this.setLinechartsData(res.data.category, res.data.pm10)
        })
      }
      // this.$axios.post('/dust/webresourcses/database.dayavg', qs.stringify(formdata)).then(data => {
      /*
      * formdata: {
      *  area: string,所选区域
      *  date: Date,时间，当method为年度和季度时，为空
      *  method: string 显示方式
      * }
      * 向后端请求，返回的数据格式：
      * 其中的category最好由后端提供，之前考虑了前端计算出来，但是问题是，当我们选取显示方式为季度和年度的时候，
      * 实际上要显示的是所有年份的均值，或者是所有某个季节的均值，所以具体包括哪些年份前端并没有办法独立判断，所
      * 以不如后端直接给出横轴的坐标（数组方式）。
      * {
      * ...
      * data: {
      * data: [pm10值, ...],
      * category: [2015，2016,2017]
      * }
      * ...
      * }
      */
      // console.log(data)
      // const historyOption = {
      //   xAxis: {
      //     type: 'category',
      //     data: data.data.category
      //   },
      //   yAxis: {
      //     type: 'value'
      //   },
      //   series: [{
      //     name: '成华区',
      //     data: data.data.data,
      //     type: 'line'
      //   }]
      // }
      // this.historyGraph.setOption(historyOption)
      // })
    }
    // 横轴category的计算方法，后端返回横轴category数组，此方法废弃。
    // xAxisNamesCompute(datestr) {
    //   let inputDate = new Date(datestr)
    //   let week = [this.dealDate(inputDate)]

    //   for (let i = 0; i < 6; i++) {
    //     inputDate.setDate(inputDate.getDate() - 1)
    //     week.push(this.dealDate(inputDate))
    //   }
    //   return week.reverse()
    // },
    // dealDate(date) {
    //   return date.getFullYear() + '-' + this.addZero(date.getMonth() + 1) + '-' + this.addZero(date.getDate())
    // },
    // addZero(num) {
    //   return num < 10 ? '0' + num : num
    // }
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
