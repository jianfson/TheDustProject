<template>
  <div id="relationSeasion">
    <div class="relationSessionSelector">
      <el-cascader
        @change="changePieSession"
        :options="selectOptions"
        v-model="pickedSeason"
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
export default {
  props: ['selectedArea'],
  data() {
    const seletChildren = [{ value: 'spring', label: '春季' }, { value: 'summer', label: '夏季' }, { value: 'autumn', label: '秋季' }, { value: 'winter', label: '冬季' }]
    return {
      PieGraphObj: null,
      grapOptionsInit: null,
      pickedSeason: null,
      currentArea: null,
      selectOptions: [
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
    changePieSession(pieRequest) {
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
      area: this.selectedArea,
            date: pieRequest[0],
            season: pieRequest[1]
      */
      if (this.selectedArea) {
        this.$axios.get('/dust/webresourcses/relation/season/' + this.selectedArea + '/' + pieRequest[0] + '/' + pieRequest[1]).then(res => {
          let dateAfterFormat = []
          let dataAddLabel = null
          this.grapOptionsInit.title.subtext = pieRequest[0] + this.formatSession(pieRequest[1])
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
          this.sessionPieGraphObj.setOption(this.grapOptionsInit)
        })
      }
    },
    drawGraphSession(res) {
      let date = res.date[0] + this.formatSession(res.date[1])
      let data = res.data
      this.relationForm.pickedSeason = res.date
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
