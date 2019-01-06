<template>
  <!-- <div id="correcDateGraph"> -->
  <!-- <div id="datePicker">
      <el-date-picker type="daterange" v-model="pickedDate" range-separator="至" @change="drawWithDate"></el-date-picker>
  </div>-->
  <div id="grapContainer"></div>
  <!-- </div> -->
</template>

<script>
import qs from 'qs'
export default {
  data() {
    return {
      // pickedDate: null,
      correctPie: null
    }
  },
  mounted() {
    this.correctPie = this.echarts.init(document.getElementById('grapContainer'))
  },
  methods: {
    drawWithDate(spotMessage) {
      // console.log(spotMessage)
      // 请求数据的格式
      //  spotMessage = {
      //       id: 点位的id String,
      //       method: 显示方式（月度，季度，年度）,
      //       date: 所选的时间段，String , "2018-12-31T16:00:00.000Z",只有选择月度的时候才有这个值
      //     }
      this.$axios.post('http://localhost:3000/correction', qs.stringify(spotMessage)).then(data => {
        this.correctPie.setOption({
          tooltip: {
            trigger: 'axis'
          },
          xAxis: {
            type: 'category',
            // data: category
            data: data.data.category
          },
          yAxis: {
            type: 'value',
            name: 'μg/m³'
          },
          legend: {
            data: ['原始数据', '国控点数据', '修正后数据']
          },
          series: [{
            // data: mockData.predictData,
            name: '原始数据',
            data: data.data.originData,
            type: 'line'
          },
          {
            // data: mockData.realData,
            name: '国控点数据',
            data: data.data.nationControl,
            type: 'line'
          }, {
            name: '修正后数据',
            data: data.data.correction,
            type: 'line'
          }]
        })
      })
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
/* #correcDateGraph {
  padding: 20px;
  width: 100%;
  height: 100%;
} */
#grapContainer {
  width: 100%;
  height: calc(100% - 20px);
}
</style>
