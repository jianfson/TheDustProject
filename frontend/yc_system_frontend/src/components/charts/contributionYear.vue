<template>
  <div id="contributionYear">
    <div class="yearSelector">
      <el-date-picker @change="changePieDay" v-model="pickedYear" type="year" size="mini"></el-date-picker>
    </div>
    <div id="contriGraphYear"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      yearPieGraphObj: null,
      grapOptionsInit: null,
      pickedYear: null
    }
  },
  mounted() {
    this.yearPieGraphObj = this.echarts.init(document.getElementById('contriGraphYear'))
    this.grapOptionsInit = {
      title: {
        text: '随年度变化的影响因子',
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
          // data: [
          //   { value: 335, name: '直接访问', label: { formatter: '区域：{b}' + '\n' + '开工数：' + '22' } },
          //   { value: 310, name: '邮件营销' },
          //   { value: 274, name: '联盟广告' },
          //   { value: 235, name: '视频广告' },
          //   { value: 400, name: '搜索引擎' }
          // ],
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
      return Y
    },
    changePieDay(pieRequest) {
      // 应需求变化，接口修改,需要返回对应区域的开工数量 2019-1-9
      // 获取选取的时间点，发出请求，更新饼图
      // console.log(pieRequest.getFullYear())
      /* 时间选择器的时间发生变化，发起请求，POST，携带时间戳
      ** 返回数据的格式 {
      **   data: data: [
            { value: 0.079, name: '成华区', 'workSite': 12 } **（改）,
            { value: 0.252, name: '双流区' },
            { value: 0.07, name: '高新区' },
            { value: 0.16, name: '武侯区' },
            { value: 0.08, name: '青羊区' },
            { value: 0.058, name: '金牛区' }
          ]
      ** }
      */
      let dateInString = this.dateFormateMonth(pieRequest)
      this.$axios.get('/dust/webresourcses/effect/year/' + dateInString).then(res => {
        let dataAddLabel = null
        let dateAfterFormat = []
        this.grapOptionsInit.title.subtext = pieRequest.getFullYear() + '年度'
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
        this.grapOptionsInit.series[0].data = dateAfterFormat.sort(function (a, b) {
          return a.value - b.value
        })
        this.yearPieGraphObj.setOption(this.grapOptionsInit)
      })
    }
  }
}
</script>

<style>
#contributionYear {
  margin: 20px 0 0 0;
  height: 100%;
  width: 100%;
}
#contriGraphYear {
  width: 100%;
  height: 100%;
}
.yearSelector {
  height: 50px;
  line-height: 40px;
  text-align: center;
}
</style>
