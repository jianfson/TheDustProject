<template>
  <div id="historySearch">
    <div class="history-left-container">
      <div class="history-right-form-title history-left-top-container">
        <span class="history-subtitle">{{ pickedArea }}点位信息</span>
      </div>
      <div class="history-map-left">
        <map-search-area ref="historyPoints"></map-search-area>
      </div>
    </div>
    <div class="history-right-container">
      <div class="history-right-form-container">
        <div class="history-right-form-title">
          <span class="history-subtitle">历史数据查询</span>
        </div>
        <div class="history-inputs-section">
          <el-form :model="historyForm" size="mini">
            <el-form-item label="区域" prop="area">
              <el-cascader
                expand-trigger="hover"
                :options="historyOptions"
                v-model="historyForm.area"
                size="mini"
              ></el-cascader>
            </el-form-item>
            <el-form-item label="显示方式" prop="method">
              <el-cascader
                v-model="historyForm.method"
                @change="whichMethod"
                :options="timeMethodOptions"
                expand-trigger="hover"
              ></el-cascader>
            </el-form-item>
            <el-form-item label="日期" prop="date" v-if="monthSelected">
              <el-date-picker type="month" v-model="historyForm.date"></el-date-picker>
            </el-form-item>
            <el-form-item>
              <el-button class="home-btn" size="mini" type="primary" @click="showMethods">查 询</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <div class="history-bottom-graph">
        <div class="history-right-form-title history-left-top-container">
          <span class="history-subtitle">{{ pickedArea }}区域扬尘历史数据</span>
        </div>
        <history-date ref="historyLineChart"></history-date>
      </div>
    </div>
  </div>
</template>

<script>
import historyDate from '../components/charts/historyDate'
import mapSearchArea from '../components/searchMap'
export default {
  components: {
    historyDate,
    mapSearchArea
  },
  data() {
    return {
      pickedArea: '全市',
      monthSelected: false, // 显示方式为月份是为true
      historyForm: {
        area: null,
        date: null,
        method: null
      },
      timeMethodOptions: [
        {
          value: 'month',
          label: '月'
        },
        {
          value: 'session',
          label: '季度',
          children: [
            {
              label: '春季',
              value: 'spring'
            },
            {
              label: '夏季',
              value: 'summer'
            },
            {
              label: '秋季',
              value: 'autumn'
            },
            {
              label: '冬季',
              value: 'winter'
            }
          ]
        },
        {
          value: 'year',
          label: '年度'
        }
      ],
      historyOptions: [
        {
          value: 'allcity',
          label: '全市'
        },
        {
          value: 'zones',
          label: '区域查询',
          children: [
            {
              label: '成华区', value: 510108
            }, {
              label: '武侯区', value: 510107
            }, {
              label: '锦江区', value: 510104
            }, {
              label: '青羊区', value: 510105
            }, {
              label: '金牛区', value: 510106
            }, {
              label: '高新区', value: 510109
            }
          ]
        }
      ]
    }
  },
  methods: {
    showMethods() {
      // 格式化向子组件传递的参数
      const dataTransform = {}
      dataTransform.area = this.historyForm.area[1] ? this.historyForm.area[1] : this.historyForm.area[0]
      dataTransform.method = this.historyForm.method[1] ? this.historyForm.method[1] : this.historyForm.method[0]
      if (this.historyForm.date) {
        dataTransform.date = this.historyForm.date
      }

      // 引用折线图组件的方法，根据所选，绘出折线图。
      this.$refs.historyLineChart.drawGraph(dataTransform)
      this.$refs.historyPoints.showPoints(dataTransform.area)

      // 切换本页面的标题
      this.pickedArea = this.$areaBelong(dataTransform.area)
    },
    areaTransform(str) {
      let Zh = null
      switch (str) {
        case 'allcity':
          Zh = '全市'
          break
        case 'wuhou':
          Zh = '武侯区'
          break
        case 'chenghua':
          Zh = '成华区'
          break
        case 'gaoxin':
          Zh = '高新区'
          break
        case 'shuangliu':
          Zh = '双流区'
          break
        case 'jingniu':
          Zh = '金牛区'
          break
      }
      return Zh
    },
    // 判断当前选中的显示方式，确定是否显示日期选择器
    whichMethod(el) {
      if (el[0] === 'month') {
        this.monthSelected = true
      } else {
        this.monthSelected = false
      }
    }
  }
}
</script>

<style>
/* 基础结构，弹性布局，水平 */
#historySearch {
  height: 100%;
  display: flex;
  background-color: rgba(243, 243, 243, 0.712);
}
.history-left-container {
  width: 50%;
  margin: 20px;
  border: 1px solid rgba(192, 191, 191, 0.3);
  background-color: #fff;
}
.history-right-container {
  width: 50%;
  margin: 20px 20px 20px 0;
  border: 1px solid rgba(192, 191, 191, 0.3);
  padding: 0 20px 20px 20px;
  background-color: #fff;
}

/* 带横线标题 */
.history-right-form-title {
  width: 100%;
  position: relative;
  height: 30px;
  margin: 10px 0px;
}
.history-subtitle {
  text-align: middle;
  height: 30px;
  line-height: 30px;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}
.history-subtitle::before {
  position: absolute;
  top: 50%;
  content: "";
  right: 100%;
  width: 100px;
  height: 1px;
  background-color: #ddd;
  margin-right: 10px;
}
.history-subtitle::after {
  position: absolute;
  top: 50%;
  content: "";
  left: 100%;
  width: 100px;
  height: 1px;
  background-color: #ddd;
  margin-left: 10px;
}

/* 右边表单+图表部分 */
.history-right-form-container {
  width: 100%;
  height: 280px;
}
.history-bottom-graph {
  width: 100%;
  height: calc(100% - 280px);
}

/* 左边地图部分 */
.history-left-top-container {
}
.history-map-left {
  height: calc(100% - 30px);
  width: 100%;
}
</style>
