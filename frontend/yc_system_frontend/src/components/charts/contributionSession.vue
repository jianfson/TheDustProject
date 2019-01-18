<template>
  <div id="contributionSession">
    <div class="sessionSelector">
      <el-cascader
        @change="changePieSession"
        size="mini"
        :options="selectOptions"
        v-model="selectedInit"
      ></el-cascader>
    </div>
    <div id="contriGraph"></div>
  </div>
</template>

<script>
export default {
  data() {
    const seletChildren = [{ value: 'spring', label: '春季' }, { value: 'summer', label: '夏季' }, { value: 'autumn', label: '秋季' }, { value: 'winter', label: '冬季' }]
    return {
      PieGraphObj: null,
      grapOptionsInit: null,
      selectedInit: null,
      selectOptions: [
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
        }
      ]
    }
  },
  mounted() {
    this.PieGraphObj = this.echarts.init(document.getElementById('contriGraph'))
    this.grapOptionsInit = {
      title: {
        text: '随季节变化的影响因子',
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
    changePieSession(pieRequest) {
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
      this.$axios.get('/dust/webresourcses/effect/season/' + pieRequest[0] + '/' + pieRequest[1]).then(res => {
        let dataAddLabel = null
        let dateAfterFormat = []
        this.grapOptionsInit.title.subtext = pieRequest[0] + this.formatSession(pieRequest[1])
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
        console.log(dateAfterFormat)
        this.grapOptionsInit.series[0].data = dateAfterFormat.sort(function (a, b) {
          return a.value - b.value
        })
        this.PieGraphObj.setOption(this.grapOptionsInit)
      })
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
        case 'autumn':
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
#contributionSession {
  margin: 22px 0 0 0;
  width: 100%;
  height: 100%;
}
#contriGraph {
  width: 100%;
  height: 100%;
}
.sessionSelector {
  height: 50px;
  line-height: 40px;
  text-align: center;
}
</style>
