<template>
  <div class="allInOne-con">
    <div id="allInOne"></div>
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  props: ['graphData'],
  data() {
    return {
      initdata: null,
      selectedList: {
        '成华区': false, '高新区': false, '双流区': false, '金牛区': false, '武侯区': false, '天府新区': false, '青羊区': false, '全市': true
      }
    }
  },
  created() {
    // 请求全市所有历史,时间精度月
    Axios.get('http://localhost:3000/home/chengdu').then((data) => {
      var category = ['2015/12', '2016/1', '2016/2', '2016/3', '2016/4', '2016/5', '2016/6', '2016/7', '2016/8', '2016/9', '2016/10', '2016/11', '2016/12', '2017/9', '2017/10', '2017/11', '2017/12', '2018/1', '2018/2', '2018/3', '2018/4', '2018/5', '2018/6', '2018/7']
      this.grapmaker(data.data.allCity, category)
      this.initdata = {
        data: data.data.allCity,
        category: category
      }
    })
  },
  watch: {
    graphData(newVal, oldVal) {
      // console.log(newVal)
      // console.log(oldVal)
    }
  },
  methods: {
    grapmaker(data, category) {
      var allLieChart = this.echarts.init(document.getElementById('allInOne'))
      const option = {
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: category
        },
        yAxis: {
          type: 'value',
          name: 'μg/m³'
        },
        series: [
          {
            name: '全市',
            type: 'line',
            data: data
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
  height: 100%;
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
