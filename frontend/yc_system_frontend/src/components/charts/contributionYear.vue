<template>
  <div id="contributionYear">
    <div class="yearSelector">
      <el-date-picker @change="changePieDay" v-model="pickedYear" type="year" size="mini"></el-date-picker>
    </div>
    <div id="contriGraphYear"></div>
  </div>
</template>

<script>
import qs from 'qs'
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
          name: '访问来源',
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
    changePieDay(pieRequest) {
      // 获取选取的时间点，发出请求，更新饼图
      console.log(pieRequest.getFullYear())
      /* 时间选择器的时间发生变化，发起请求，POST，携带时间戳
      ** 返回数据的格式 {
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
      this.$axios.post('http://localhost:3000/contribution/year/', qs.stringify({ date: pieRequest })).then(res => {
        this.grapOptionsInit.title.subtext = pieRequest.getFullYear() + '年度'
        this.grapOptionsInit.series[0].data = res.data.data
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
