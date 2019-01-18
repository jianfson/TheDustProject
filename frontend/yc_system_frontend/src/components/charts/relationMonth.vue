<template>
  <div id="relationMonth">
    <div class="relationDaySelector">
      <el-date-picker @change="changePieMonth" v-model="MonthSelect" type="month" size="mini"></el-date-picker>
    </div>
    <div id="relationGraphMonth"></div>
  </div>
</template>

<script>
export default {
  props: ['selectedArea'],
  data() {
    return {
      monthPieGraphObj: null,
      grapOptionsInit: null,
      MonthSelect: null,
      currentArea: null
    }
  },
  mounted() {
    this.monthPieGraphObj = this.echarts.init(document.getElementById('relationGraphMonth'))
    this.grapOptionsInit = {
      title: {
        text: '随时间变化的关联性',
        left: 'center',
        top: 20,
        subtext: null
      },

      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      series: [
        {
          name: '关联性',
          type: 'pie',
          radius: '55%',
          center: ['50%', '50%'],
          data: null,
          roseType: 'radius',
          label: {
            normal: {
              textStyle: {
                color: 'rgba(0, 0,0,1)'
              }
            }
          },
          labelLine: {
            normal: {
              lineStyle: {
                color: 'rgba(0,0,0,0.7)'
              },
              smooth: 0.2,
              length: 10,
              length2: 20
            }
          },
          itemStyle: {
            normal: {
              color: '#f90',
              shadowBlur: 100,
              shadowColor: 'rgba(0, 0, 0, 0.7)'
            }
          },

          animationType: 'scale',
          animationEasing: 'elasticOut',
          animationDelay: function (idx) {
            return Math.random() * 200
          }
        }
      ]
    }
  },
  methods: {
    dateFormateMonth(date) {
      var baseDate = new Date(date)
      let Y = baseDate.getFullYear()
      let M = baseDate.getMonth() + 1
      M = M < 10 ? '0' + M : M
      return Y + '-' + M
    },
    changePieMonth(pieRequest) {
      // 获取选取的时间点，发出请求，更新饼图
      if (this.selectedArea) {
        let dateAfterFormat = this.dateFormateMonth(pieRequest)
        this.$axios.get('/dust/webresourcses/relation/month/' + this.selectedArea + '/' + dateAfterFormat).then(res => {
          let dateAfterFormat = []
          let dataAddLabel = null
          this.grapOptionsInit.title.subtext = this.dealDate(pieRequest)
          dataAddLabel = res.data.data
          for (let i = 0; i < dataAddLabel.length; i++) {
            let currentName = this.$areaBelong(Number(dataAddLabel[i].ID))
            let currenValue = dataAddLabel[i].relation
            // 解析成饼图可用的数据
            dateAfterFormat.push({
              name: currentName, value: currenValue
            })
          }
          this.grapOptionsInit.series[0].data = dateAfterFormat.sort(function (a, b) {
            return a.value - b.value
          })
          this.grapOptionsInit.series[0].data = dateAfterFormat
          this.monthPieGraphObj.setOption(this.grapOptionsInit)
        })
      }
    },
    drawGraphMonth(res) {
      let date = new Date(res.date)
      let data = res.data
      this.MonthSelect = date
      this.grapOptionsInit.title.subtext = this.dealDate(date)
      this.grapOptionsInit.series[0].data = data
      this.monthPieGraphObj.setOption(this.grapOptionsInit)
    },
    dealDate(date) {
      return date.getFullYear() + '年' + this.addZero(date.getMonth() + 1) + '月'
    },
    addZero(num) {
      return num < 10 ? '0' + num : num
    }
  }
}
</script>

<style>
#relationMonth {
  margin: 20px 0 0 0;
  height: 100%;
  width: 100%;
}
#relationGraphMonth {
  width: 100%;
  height: 100%;
}
.relationDaySelector {
  height: 50px;
  line-height: 40px;
  text-align: center;
}
</style>
