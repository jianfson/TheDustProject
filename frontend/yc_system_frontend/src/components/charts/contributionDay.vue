<template>
  <div id="contributionDay">
    <div class="daySelector">
      <el-date-picker @change="changePieDay" v-model="daySelect" type="date"></el-date-picker>
    </div>
    <div id="contriGraphday"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dayPieGraphObj: null,
      grapOptionsInit: null,
      daySelect: this.dealDate(new Date())
    }
  },
  mounted() {
    var now = new Date()
    var nowStr = this.dealDate(now)
    this.dayPieGraphObj = this.echarts.init(document.getElementById('contriGraphday'))
    this.grapOptionsInit = {
      title: {
        text: '随时间变化的影响因子',
        left: 'center',
        top: 20,
        subtext: nowStr
      },

      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },

      // visualMap: {
      //   show: false,
      //   min: 0,
      //   max: 1,
      //   inRange: {
      //     colorLightness: [0, 1]
      //   }
      // },
      series: [
        {
          name: '访问来源',
          type: 'pie',
          radius: '55%',
          center: ['50%', '50%'],
          data: [
            { value: 0.079, name: '成华区' },
            { value: 0.252, name: '双流区' },
            { value: 0.07, name: '高新区' },
            { value: 0.16, name: '武侯区' },
            { value: 0.08, name: '青羊区' },
            { value: 0.058, name: '金牛区' },
            { value: 0.22, name: '天府新区' }
          ].sort(function (a, b) { return a.value - b.value }),
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
              shadowBlur: 200,
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
    this.dayPieGraphObj.setOption(this.grapOptionsInit)
  },
  methods: {
    changePieDay(pieRequest) {
      // 获取选取的时间点，发出请求，更新饼图
      const currentOption = JSON.parse(JSON.stringify(this.grapOptionsInit))
      currentOption.title.subtext = this.dealDate(pieRequest)
      currentOption.series[0].data = [
        { value: Math.floor(Math.random() * 100) / 100, name: '成华区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '双流区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '高新区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '武侯区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '青羊区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '金牛区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '天府新区' }
      ].sort(function (a, b) { return a.value - b.value })
      this.dayPieGraphObj.setOption(currentOption)
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
#contributionDay {
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
