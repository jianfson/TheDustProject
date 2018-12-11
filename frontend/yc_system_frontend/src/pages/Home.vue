<template>
  <div class="home-container">
    <div class="home-firstline">
      <div class="home-leftside">
        <div class="home-leftside-inner">
          <div class="home-maptop">
            <div class="title">
              <span class="subtitle">监测点位置信息</span>
            </div>
            <map-area></map-area>
          </div>
        </div>
      </div>
      <div id="rightside-graph">
        <div class="top-form">
          <div class="title">
            <span class="subtitle">查询扬尘情况</span>
          </div>
          <div class="inputs-section">
            <el-form :model="homeForm" size="mini" ref="homeForm" :rules="searchRules">
              <el-form-item class="home-reaserach" label="区域" prop="area">
                <el-cascader
                  class="home-reaserach"
                  expand-trigger="hover"
                  :options="researchOptions"
                  v-model="homeForm.area"
                ></el-cascader>
              </el-form-item>
              <el-form-item label="日期" prop="date">
                <el-date-picker v-model="homeForm.date.startMonth" type="month"></el-date-picker>&nbsp;-
                <el-date-picker type="month" v-model="homeForm.date.endMonth"></el-date-picker>
              </el-form-item>
              <el-form-item>
                <el-button
                  class="home-btn"
                  size="mini"
                  type="primary"
                  @click="submitSearch('homeForm')"
                >查 询</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
        <div class="bottom-graph">
          <div class="title">
            <span class="subtitle">全市扬尘变化趋势</span>
          </div>
          <div class="home-charts-container">
            <all-line-chart :graph-data="lineChartsData"></all-line-chart>
          </div>
        </div>
      </div>
    </div>
    <div class="home-secondline">
      <div class="home-rankbottom">
        <div class="title">
          <span class="subtitle">监测点污染等级</span>
        </div>
        <rank-table></rank-table>
      </div>
    </div>
  </div>
</template>

<script>
import mapArea from '../components/mapArea'
import lineCharts from '../components/lineCharts'
import rankTable from '../components/rankTable'
import allLineChart from '../components/charts/allLineChart'
export default {
  components: {
    mapArea,
    lineCharts,
    rankTable,
    allLineChart
  },
  data() {
    const datepickerRule = function (rule, value, callback) {
      let start = new Date(value.startMonth)
      let end = new Date(value.endMonth)
      if (!value.startMonth) {
        return callback(new Error('开始日期不能为空'))
      } else if (!value.endMonth) {
        return callback(new Error('结束日期不能为空'))
      } else if (start >= end) {
        return callback(new Error('开始日期必须小于结束日期'))
      } else {
        callback()
      }
    }
    const areapickerRule = function (rule, value, callback) {
      if (!value) {
        callback(new Error('区域不能为空'))
      } else {
        callback()
      }
    }
    return {
      homeForm: {
        area: null,
        date: {
          startMonth: null,
          endMonth: null
        }
      },
      selectedOptions: null,
      researchOptions: [
        {
          value: 'allcity',
          label: '全市'
        },
        {
          value: 'zones',
          label: '区域查询',
          children: [
            {
              label: '成华区', value: 'chenghua'
            }, {
              label: '武侯区', value: 'wuhou'
            }, {
              label: '高新区', value: 'gaoxin'
            }, {
              label: '双流区', value: 'shuangliu'
            }, {
              label: '金牛区', value: 'jingniu'
            }
          ]
        }
      ],
      searchRules: {
        area: [
          { validator: areapickerRule, trigger: 'blur' }
        ],
        date: [
          {
            validator: datepickerRule,
            trigger: 'blur'
          }
        ]
      },
      lineChartsData: null
    }
  },
  methods: {
    submitSearch(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          // 请求，查询对应时间的区域的历史数据
          this.$axios.get('http://localhost:3000/home/search/' + JSON.stringify(this.homeForm)).then(res => {
            // let data = res.data
          })
        } else {
          console.log('error submit')
          return false
        }
      })
    }
  },
  mounted() {
    this.$axios.get('http://localhost:3000/home/years').then(res => {
      this.lineChartsData = res
    })
  }
}
</script>

<style>
.home-container {
  width: 100%;
  background-color: rgba(243, 243, 243, 0.712);
}
.home-firstline {
  width: 100%;
  display: flex;
  height: 600px;
}
.home-leftside {
  flex-grow: 2;
}
.home-leftside-inner {
  display: flex;
  flex-direction: column;
  height: 100%;
}
.home-maptop {
  flex-grow: 2;
  height: 100%;
  margin: 20px;
  padding-top: 10px;
  background-color: #fff;
  border: 1px solid rgba(192, 191, 191, 0.3);
}

/* 污染等级表 */
.home-rankbottom {
  background-color: #fff;
  border: 1px solid rgba(192, 191, 191, 0.3);
  padding: 20px;
  margin: 0 20px 20px 20px;
  /* box-shadow: 1px 1px 7px rgba(84, 92, 100, 0.6); */
}
#rightside-graph {
  width: 40%;
  right: 0;
  top: 0;
  height: calc(100% - 40px);
  /* background-color: #545c64; */
  margin: 20px 20px 20px 0;
  padding-top: 10px;
  background-color: #fff;
  border: 1px solid rgba(192, 191, 191, 0.3);
}

.top-form {
  height: 162px;
}

/* 带横线标题 */
.title {
  width: 100%;
  position: relative;
  height: 30px;
  margin-bottom: 10px;
}
.subtitle {
  text-align: middle;
  height: 30px;
  line-height: 30px;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  /* background-color: #545c64; */
}
.subtitle::before {
  position: absolute;
  top: 50%;
  content: "";
  right: 100%;
  width: 100px;
  height: 1px;
  background-color: #ddd;
  margin-right: 10px;
}
.subtitle::after {
  position: absolute;
  top: 50%;
  content: "";
  left: 100%;
  width: 100px;
  height: 1px;
  background-color: #ddd;
  margin-left: 10px;
}

.bottom-graph {
  height: calc(100% - 220px);
  padding-top: 10px;
}
.inputs-section {
  padding: 0 30px;
}
.el-form-item.home-reaserach label {
}
.el-button.home-btn {
  margin: 0 0 0 40px;
  width: 180px;
}
.home-charts-container {
  padding: 20px 5px 0 5px;
  width: 100%;
  height: 100%;
  min-height: 300px;
}
</style>
