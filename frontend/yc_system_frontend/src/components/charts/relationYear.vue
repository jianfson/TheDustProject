<template>
  <div id="relationYear">
    <div class="yearSelector">
      <el-form :rules="relationRule" :model="relationForm" ref="relationForm">
        <el-form-item prop="pickedYear">
          <el-date-picker
            @change="changePieYear"
            v-model="relationForm.pickedYear"
            type="year"
            size="mini"
          ></el-date-picker>
        </el-form-item>
      </el-form>
    </div>
    <div id="relationGraphYear"></div>
  </div>
</template>

<script>
export default {
  props: ['selectedArea'],
  data() {
    var validatePass = (rule, value, callback) => {
      if (!this.selectedArea) {
        callback(new Error('请先选择区域并提交'))
      } else {
        callback()
      }
    }
    return {
      yearPieGraphObj: null,
      grapOptionsInit: null,
      relationForm: {
        pickedYear: null
      },
      currentArea: null,
      relationRule: {
        pickedYear: [
          { validator: validatePass, trigger: 'change' }
        ]
      }
    }
  },
  mounted() {
    this.yearPieGraphObj = this.echarts.init(document.getElementById('relationGraphYear'))
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
    dateFormateYear(date) {
      var baseDate = new Date(date)
      let Y = baseDate.getFullYear()
      return Y
    },
    changePieYear(pieRequest) {
      // 获取选取的时间点，发出请求，更新饼图
      /* 时间选择器的时间发生变化，发起请求，POST，携带时间戳,和选取的地区！！！！！
      ** 返回数据的格式 {
      **   data: data: [
            { value: 0.079, name: '成华区' },
            { value: 0.252, name: '双流区' },
            { value: 0.07, name: '高新区' },
            { value: 0.16, name: '武侯区' },
            { value: 0.08, name: '青羊区' },
            { value: 0.058, name: '金牛区' }
          ]
      ** }  area: this.selectedArea,
            date: dateAfterFormate
      */
      // 首先要验证区域是否已经选择上了，如果没有选择区域不做处理
      if (this.selectedArea) {
        let dateAfterFormate = this.dateFormateYear(this.selectedArea)
        this.$axios.get('/dust/webresourcses/relation/year/' + this.selectedArea + '/' + dateAfterFormate).then(res => {
          let dateAfterFormat = []
          let dataAddLabel = null
          this.grapOptionsInit.title.subtext = pieRequest.getFullYear() + '年度'
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
          this.yearPieGraphObj.setOption(this.grapOptionsInit)
        })
      }
    },
    drawGraphYear(res) {
      let date = new Date(res.date).getFullYear()
      let data = res.data
      this.pickedYear = new Date(res.date)
      this.grapOptionsInit.title.subtext = date + '年度'
      this.grapOptionsInit.series[0].data = data
      this.yearPieGraphObj.setOption(this.grapOptionsInit)
    }
  }
}
</script>

<style>
#relationYear {
  margin: 20px 0 0 0;
  height: 100%;
  width: 100%;
}
#relationGraphYear {
  width: 100%;
  height: 100%;
}
.yearSelector {
  height: 50px;
  line-height: 40px;
  text-align: center;
}
</style>
