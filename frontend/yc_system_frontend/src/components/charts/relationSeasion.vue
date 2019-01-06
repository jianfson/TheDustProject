<template>
  <div id="relationSeasion">
    <div class="relationSessionSelector">
      <el-cascader
        @change="changePieSession"
        :options="selectOptions"
        v-model="selectedInit"
        size="mini"
      ></el-cascader>
    </div>
    <div id="relationSeasionGraph"></div>
  </div>
</template>

<script>
/* 现在时间选择器的时间是写死的，注意应该根据时间变换而发生变化
**
*/
import qs from 'qs'
export default {
  data() {
    const seletChildren = [{ value: 'spring', label: '春季' }, { value: 'summer', label: '夏季' }, { value: 'autumn', label: '秋季' }, { value: 'winter', label: '冬季' }]
    return {
      PieGraphObj: null,
      grapOptionsInit: null,
      selectedInit: null,
      currentArea: null,
      selectOptions: [
        {
          value: '2010',
          label: '2010',
          children: seletChildren
        },
        {
          value: '2011',
          label: '2011',
          children: seletChildren
        },
        {
          value: '2012',
          label: '2012',
          children: seletChildren
        },
        {
          value: '2013',
          label: '2013',
          children: seletChildren
        },
        {
          value: '2014',
          label: '2014',
          children: seletChildren
        },
        {
          value: '2015',
          label: '2015',
          children: seletChildren
        },
        {
          value: '2016',
          label: '2016',
          children: seletChildren
        },
        {
          value: '2017',
          label: '2017',
          children: seletChildren
        },
        {
          value: '2018',
          label: '2018',
          children: seletChildren
        },
        {
          value: '2019',
          label: '2019',
          children: seletChildren
        }
      ]
    }
  },
  mounted() {
    this.sessionPieGraphObj = this.echarts.init(document.getElementById('relationSeasionGraph'))
    this.grapOptionsInit = {
      title: {
        text: '随季节变化的关联性',
        left: 'center',
        top: 20,
        subtext: null
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
            { value: 0.25, name: '成华区' },
            { value: 0.172, name: '双流区' },
            { value: 0.22, name: '高新区' },
            { value: 0.13, name: '武侯区' },
            { value: 0.12, name: '青羊区' },
            { value: 0.028, name: '金牛区' },
            { value: 0.09, name: '天府新区' }
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
    changePieSession(pieRequest) {
      console.log(pieRequest)
      // 获取选取的时间点，发出请求，更新饼图
      // 获取选取的时间点，发出请求，更新饼图
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
      this.$axios.post('http://localhost:3000/relation/session/', qs.stringify({ date: pieRequest })).then(res => {
        this.grapOptionsInit.title.subtext = pieRequest[0] + this.formatSession(pieRequest[1])
        this.grapOptionsInit.series[0].data = res.data.data
        this.sessionPieGraphObj.setOption(this.grapOptionsInit)
      })
    },
    drawGraphSession(res) {
      let date = res.date[0] + this.formatSession(res.date[1])
      let data = res.data
      this.selectedInit = res.date
      this.grapOptionsInit.title.subtext = date
      this.grapOptionsInit.series[0].data = data
      this.sessionPieGraphObj.setOption(this.grapOptionsInit)
    },
    formatSession(str) {
      var result = null
      switch (str) {
        case 'spring':
          result = '春季'
          break
        case 'summer':
          result = '夏季'
          break
        case 'atum':
          result = '秋季'
          break
        case 'winter':
          result = '冬季'
          break
      }
      return result
    }
  }
}
</script>

<style>
#relationSeasion {
  margin: 20px 0 0 0;
  width: 100%;
  height: 100%;
}
#relationSeasionGraph {
  width: 100%;
  height: 100%;
}
.relationSessionSelector {
  height: 50px;
  line-height: 40px;
  text-align: center;
}
</style>
