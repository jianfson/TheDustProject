<template>
  <div id="contributionMonth">
    <div class="daySelector">
      <el-date-picker @change="changePieDay" v-model="daySelect" type="month" size="mini"></el-date-picker>
    </div>
    <div id="contriGraphday"></div>
  </div>
</template>

<script>
// import qs from 'qs'
export default {
  data() {
    return {
      monthPieGraphObj: null,
      grapOptionsInit: null,
      daySelect: this.dealDate(new Date())
    }
  },
  mounted() {
    this.monthPieGraphObj = this.echarts.init(document.getElementById('contriGraphday'))
    this.grapOptionsInit = {
      title: {
        text: '随月度变化的影响因子',
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
          name: '区域贡献',
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
              length: 7,
              length2: 12
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
    changePieDay(pieRequest) {
      /* 时间选择器的时间发生变化，发起请求，POST，携带时间戳， 改成get 带时间字符串
      ** 需要的数据的格式 {
      **   data: data: [
            { value: 0.079, name: '成华区' },
            { value: 0.252, name: '双流区' },
            { value: 0.07, name: '高新区' },
            { value: 0.16, name: '武侯区' },
            { value: 0.08, name: '青羊区' },
            { value: 0.058, name: '金牛区' }
          ]
      ** }
      */
      let dateInString = this.dateFormateMonth(pieRequest)
      this.$axios.get('/dust/webresourcses/effect/month/' + dateInString).then(res => {
        let dataAddLabel = null
        let dateAfterFormat = []
        this.grapOptionsInit.title.subtext = this.dealDate(pieRequest)
        dataAddLabel = res.data.data
        for (let i = 0; i < dataAddLabel.length; i++) {
          let currentWork = dataAddLabel[i].workSite
          let currentName = this.$areaBelong(Number(dataAddLabel[i].ID))
          let currenValue = dataAddLabel[i].effect
          // dataAddLabel[i].label = { formatter: '{b}' + '\n' + '开工数:' + currentWork }
          let currentLabel = { formatter: '{b}' + '\n' + '开工数:' + currentWork }
          // 解析成饼图可用的数据
          dateAfterFormat.push({
            name: currentName, value: currenValue, label: currentLabel
          })
        }
        this.grapOptionsInit.series[0].data = dateAfterFormat.sort(function(a, b) {
          return a.value - b.value
        })
        // this.grapOptionsInit.series[0].data = res.data.data
        this.monthPieGraphObj.setOption(this.grapOptionsInit)
      })
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
#contributionMonth {
  margin: 20px 0 0 0;
  height: 100%;
  width: 100%;
}
#contriGraphday {
  width: 100%;
  height: 100%;
}
.daySelector {
  height: 50px;
  line-height: 40px;
  text-align: center;
}
</style>
