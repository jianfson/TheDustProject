<template>
  <div class="allInOne-con">
    <div id="allInOne"></div>
    <div class="backoff-btn" v-show="needbackoff" @click="backTop"><el-button type="primary" size="mini">上一层</el-button></div>
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  props: ['graphData'],
  data() {
    return {
      needbackoff: false,
      initdata: null,
      selectedList: {
        '成华区': false, '高新区': false, '双流区': false, '金牛区': false, '武侯区': false, '天府新区': false, '青羊区': false, '全市': true
      }
    }
  },
  created() {
    // console.log(this.graphData)
    // Axios.get('http://localhost:3000/home/years').then((data) => {
    //   console.log(data)
    //   this.grapmaker(data.data)
    //   this.initdata = data.data
    // })
    Axios.get('http://localhost:3000/home/chengdu').then((data) => {
      console.log(data)
      this.grapmaker(data.data)
      this.initdata = data.data
    })
    // this.grapmaker(this.graphData)
    // this.initdata = this.graphData
  },
  watch: {
    graphData(newVal, oldVal) {
      // console.log(newVal)
      // console.log(oldVal)
    }
  },
  methods: {
    backTop() {
      this.grapmaker(this.initdata)
      this.needbackoff = false
    },
    grapmaker(data, whichYear = null) {
      var allLieChart = this.echarts.init(document.getElementById('allInOne'))
      if (data.name === 'byyear') {
        allLieChart.on('click', (para) => {
          if (!isNaN(para.name)) {
            Axios.get('http://localhost:3000/home/month/' + para.name).then(data => {
              this.grapmaker(data.data, data.data.year + '年度')
              this.needbackoff = true
            })
          }
        })
      }
      allLieChart.on('legendselectchanged', para => {
        this.selectedList[para.name] = !this.selectedList[para.name]
      })
      const option = {
        title: {
          // text: '区域扬尘变化趋势',
          padding: [5, 20],
          subtext: whichYear,
          subtextStyle: {
            color: 'rgba(0,0,0,0.8)',
            fontWeight: 'normal'
          }
        },
        tooltip: {
          trigger: 'axis'
        },
        // legend: {
        //   data: ['成华区', '高新区', '双流区', '金牛区', '武侯区', '天府新区', '青羊区', '全市'],
        //   type: 'scroll',
        //   selected: this.selectedList,
        //   width: '350px'
        // },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          // feature: {
          //   saveAsImage: {}
          // }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          // data: data.name === 'byyear' ? ['2008', '2009', '2010', '2011', '2012', '2013', '2014', '2015', '2016', '2017'] : ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
          data: ['2015/12', '2016/1', '2016/2', '2016/3', '2016/4', '2016/5', '2016/6', '2016/7', '2016/8', '2016/9', '2016/10', '2016/11', '2016/12', '2017/9', '2017/10', '2017/11', '2017/12', '2018/1', '2018/2', '2018/3', '2018/4', '2018/5', '2018/6', '2018/7']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          // {
          //   name: '成华区',
          //   type: 'line',
          //   data: data.chenghuaqu
          // },
          // {
          //   name: '高新区',
          //   type: 'line',
          //   data: data.gaoxinqu
          // },
          // {
          //   name: '双流区',
          //   type: 'line',
          //   data: data.shuangliuqu
          // },
          // {
          //   name: '金牛区',
          //   type: 'line',
          //   data: data.jinniuqu
          // },
          // {
          //   name: '武侯区',
          //   type: 'line',
          //   data: data.wuhouqu
          // },
          // {
          //   name: '青羊区',
          //   type: 'line',
          //   data: data.qingyangqu
          // },
          // {
          //   name: '全市',
          //   type: 'line',
          //   data: data.quanshi
          // }
          {
            name: '全市',
            type: 'line',
            data: data.allCity
          }
        ]
      }
      allLieChart.setOption(option)
    }
  }
}
</script>

<style scoped>
#allInOne {
  height: 100%
}
.allInOne-con {
  position: relative;
  height: 100%;
}
.backoff-btn {
  position: absolute;
  top: -35px;
  right: 10px;
}
</style>
