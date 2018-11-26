<template>
  <div id="correcDateGraph">
    <div id="datePicker">
      <el-date-picker type="daterange" v-model="pickedDate" range-separator="至" @change="drawWithDate"></el-date-picker>
    </div>
    <div id="grapContainer"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pickedDate: null,
      correctPie: null
    }
  },
  mounted() {
    this.correctPie = this.echarts.init(document.getElementById('grapContainer'))
  },
  methods: {
    drawWithDate() {
      let startDate = new Date(this.pickedDate[0])
      let time = (this.pickedDate[1].getTime() - this.pickedDate[0].getTime()) / (1000 * 60 * 60 * 24)
      let category = [this.dealDate(startDate)]

      let mockData = {
        predictData: [103],
        realData: [140]
      }

      for (let i = 0; i < time; i++) {
        startDate.setDate(startDate.getDate() + 1)
        category.push(this.dealDate(startDate))
        mockData.predictData.push(Math.floor(Math.random() * 200))
        mockData.realData.push(Math.floor(Math.random() * 200))
      }
      // console.log(category)
      this.correctPie.setOption({
        xAxis: {
          type: 'category',
          data: category
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: mockData.predictData,
          type: 'line'
        },
        {
          data: mockData.realData,
          type: 'line'
        }]
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
#correcDateGraph {
  padding: 20px;
  width: 100%;
  height: 100%;
}
#grapContainer {
  width: 100%;
  height: calc(100% - 20px);
}
</style>
