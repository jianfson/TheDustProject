<template>
  <div class="analysis-container">
    <div class="analysis-predict-container">
      <span class="predict-title">{{pickedArea}}预测趋势</span>
      <div class="predict-left">
        <div class="predict-form">
          <el-form
            :model="analysisPredictForm"
            size="mini"
            label-position="left"
            label-width="70px"
          >
            <el-form-item label="区域" prop="area">
              <el-cascader
                expand-trigger="hover"
                :options="analysisOptions"
                v-model="analysisPredictForm.area"
              ></el-cascader>
            </el-form-item>
            <el-form-item label="预测时间" prop="range">
              <el-tooltip effect="dark" content="预测趋势折线图的预测范围" placement="right-start">
                <el-cascader
                  v-model="analysisPredictForm.range"
                  :options="timeMethodOptions"
                  expand-trigger="hover"
                ></el-cascader>
              </el-tooltip>
            </el-form-item>
            <el-form-item>
              <el-button class="predict-btn" @click="showPredict" type="primary">提交</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <div class="predict-middle">
        <span>{{pickedArea}}随季节变化趋势预测</span>
        <all-predict ref="allPredict"></all-predict>
      </div>
      <div class="predict-right">
        <span>{{pickedArea}}{{pickedRange}}预测</span>
        <week-predict ref="weekPredict"></week-predict>
      </div>
    </div>
    <div class="analysis-contribute-container">
      <span class="predict-title">区域排放贡献比重</span>
      <div class="contribute-left-container">
        <contribution-year></contribution-year>
      </div>
      <div class="contribute-middle-container">
        <contribution-session></contribution-session>
      </div>
      <div class="contribute-right-container">
        <contribution-month></contribution-month>
      </div>
    </div>
    <div class="analysis-relation-container">
      <span class="predict-title">区域间关联性</span>
      <div class="relation-firstline">
        <div class="relation-inputs-section">
          <el-form :model="analysisRelationForm" size="mini" :inline="true">
            <el-form-item label="区域" prop="area">
              <el-select v-model="analysisRelationForm.area" placeholder="请选择">
                <el-option
                  v-for="item in analysisRelationOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="changeRelationArea">提交</el-button>
            </el-form-item>
            <el-form-item label>
              <span>当前区域：{{currentAreaCN}}</span>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <div class="relation-secondline">
        <div class="realtion-left">
          <relation-year ref="relationYear" :selectedArea="pickedRelationArea"></relation-year>
        </div>
        <div class="relation-middle-container">
          <relation-seasion ref="relationSession" :selectedArea="pickedRelationArea"></relation-seasion>
        </div>
        <div class="relation-right-container">
          <relation-month ref="relationMonth" :selectedArea="pickedRelationArea"></relation-month>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import allPredict from '../components/charts/allpredict'
import weekPredict from '../components/charts/weekPredict'
import contributionSession from '../components/charts/contributionSession'
import contributionMonth from '../components/charts/contributionMonth'
import contributionYear from '../components/charts/contributionYear'
import relationMonth from '../components/charts/relationMonth'
import relationSeasion from '../components/charts/relationSeasion'
import relationYear from '../components/charts/relationYear'

export default {
  components: {
    allPredict,
    weekPredict,
    contributionYear,
    contributionSession,
    contributionMonth,
    relationYear,
    relationMonth,
    relationSeasion
  },
  data() {
    return {
      pickedArea: '全市',
      pickedRelationArea: null,
      pickedRange: null,
      analysisPredictForm: {
        area: null,
        range: null
      },
      analysisRelationForm: {
        area: null
      },
      timeMethodOptions: [
        {
          value: 'month',
          label: '未来一个月'
        },
        {
          value: 'year',
          label: '未来一年'
        },
        {
          value: 'threeyear',
          label: '未来三年'
        }
      ],
      analysisRelationOptions: [
        {
          label: '成华区', value: 510108
        }, {
          label: '武侯区', value: 510107
        }, {
          label: '青羊区', value: 510105
        }, {
          label: '锦江区', value: 510104
        }, {
          label: '金牛区', value: 510106
        }
      ],
      analysisOptions: [
        {
          value: 'allcity',
          label: '全市'
        },
        {
          value: 'zones',
          label: '区域',
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
      ]
    }
  },
  methods: {
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
    showPredict() {
      if (!this.analysisPredictForm.area) {
        this.$message.error('请选择区域，再点击提交')
        return
      }
      let area = this.analysisPredictForm.area[1] ? this.analysisPredictForm.area[1] : this.analysisPredictForm.area[0]
      /* 需要的格式:
      ** {
      **  category: ['2018', '2019'], 始终返回的是未来两年的，密度为季度
      **  spring: [123,251],
      **  summer: [321,134],
      **  autumn: [233,412,100],
      **  winter: [321,123]
      }
      */
      this.$axios.get('/dust/webresourcses/database.dayavg/predict/season/' + area).then(res => {
        let dataAfterFormat = {
          category: [],
          spring: [],
          summer: [],
          autumn: [],
          winter: []
        }
        // 因为后端规定的返回的数据格式不合适，需要转化。
        res.data.data.forEach(element => {
          switch (element.season) {
            case 'spring':
              dataAfterFormat.category.push(element.year)
              dataAfterFormat.spring.push(element.pm10)
              break
            case 'summer':
              dataAfterFormat.summer.push(element.pm10)
              break
            case 'autumn':
              dataAfterFormat.autumn.push(element.pm10)
              break
            case 'winter':
              dataAfterFormat.winter.push(element.pm10)
              break
          }
        })
        // this.$refs.allPredict.drawAllPredict(res.data)
        this.$refs.allPredict.drawAllPredict(dataAfterFormat)
        this.pickedArea = this.areaTransform(area)
      })
      // 预测一个月
      if (this.analysisPredictForm.range) {
        let range = this.analysisPredictForm.range[0]
        if (range === 'month') {
          this.$axios.get('/dust/webresourcses/database.dayavg/predict/month/' + area).then(res => {
            // console.log(res)
            let predictByMonth = {
              category: [],
              data: []
            }
            res.data.data.forEach(ele => {
              predictByMonth.category.push(ele.date)
              predictByMonth.data.push(ele.pm10)
            })
            this.pickedRange = '未来一个月'
            this.$refs.weekPredict.drawLinePredict(predictByMonth)
            // this.pickedArea = this.areaTransform(area)
          })
          // 预测一年
        } else if (range === 'year') {
          this.$axios.get('/dust/webresourcses/database.dayavg/predict/year/' + area).then(res => {
            let predictByYear = {
              category: [],
              data: []
            }
            res.data.data.forEach(ele => {
              predictByYear.category.push(ele.date)
              predictByYear.data.push(ele.pm10)
            })
            this.pickedRange = '未来一年'
            this.$refs.weekPredict.drawLinePredict(predictByYear)
          })
          // 预测三年
        } else if (range === 'threeyear') {
          this.$axios.get('/dust/webresourcses/database.dayavg/predict/threeyear/' + area).then(res => {
            let predictThreeYear = {
              category: [],
              data: []
            }
            res.data.data.forEach(ele => {
              predictThreeYear.category.push(ele.date)
              predictThreeYear.data.push(ele.pm10)
            })
            this.pickedRange = '未来三年'
            this.$refs.weekPredict.drawLinePredict(predictThreeYear)
          })
        }
      }
    },
    changeRelationArea() {
      if (!this.analysisRelationForm.area) {
        this.$message({
          showClose: true,
          message: '选取了区域之后才能提交查看区域关系',
          type: 'error'
        })
      } else if (this.analysisRelationForm.area === this.pickedRelationArea) {
        this.$message({
          showClose: true,
          message: '区域选择没有变化，无效提交',
          type: 'warning'
        })
      } else {
        this.pickedRelationArea = this.analysisRelationForm.area
        /** 数据格式： {
        **  year: {
              date: '2018',
              data: [
                { value: 0.66, name: '成华区' },
                {0.15, name: '双流区' },
                {0.15, name: '高新区' },
                {0.15, name: '武侯区' },
                {0.15, name: '青羊区' },
                {0.15, name: '金牛区' },
                {0.15, name: '天府新区' }
              ]
            },
            session: {
              date: ['2018', 'spring'],
              data: [
                { value: 0.33, name: '成华区' },
                {0.15, name: '双流区' },
                {0.15, name: '高新区' },
                {0.15, name: '武侯区' },
                {0.15, name: '青羊区' },
                {0.15, name: '金牛区' },
                {0.15, name: '天府新区' }
              ]
            },
            month: {
              date: '2018-01',
              data: [
                { value: 0.33, name: '成华区' },
                {0.15, name: '双流区' },
                {0.15, name: '高新区' },
                {0.15, name: '武侯区' },
                {0.15, name: '青羊区' },
                {0.15, name: '金牛区' },
                {0.15, name: '天府新区' }
              ]
            }
        ** }
        */
        // this.$axios.post('/dust/webresourcses/relation/init', qs.stringify({ area: this.pickedRelationArea })).then(res => {
        //   const year = res.data.year
        //   const month = res.data.month
        //   const session = res.data.session
        //   this.$refs.relationYear.drawGraphYear(year)
        //   this.$refs.relationSession.drawGraphSession(session)
        //   this.$refs.relationMonth.drawGraphMonth(month)
        // })
      }
    }
  },
  computed: {
    currentAreaCN: function () {
      return this.$areaBelong(this.pickedRelationArea)
    }
  }
}
</script>

<style>
.analysis-container {
  background-color: rgba(243, 243, 243, 0.712);
  padding: 20px;
}
/* 第一行 */
.analysis-predict-container {
  width: 100%;
  height: 400px;
  border: 1px solid rgba(192, 191, 191, 0.3);
  background-color: #fff;
  padding: 20px;
  padding-bottom: 0px;
  padding-top: 40px;
  display: flex;
  position: relative;
}
.predict-left {
  width: 300px;
  margin: 0 20px 0 0;
}
.predict-title {
  font-size: 1.2em;
  position: absolute;
  width: 100%;
  top: 20px;
}
.predict-title::after {
  content: "";
  height: 1px;
  width: 100%;
  background-color: rgba(192, 191, 191, 0.3);
  position: absolute;
  top: 25px;
  left: -20px;
}
.predict-form {
  margin-top: 40px;
}
.predict-middle {
  flex-grow: 2;
  /* background-color: yellow; */
  margin: 20px 0 0 0;
}
.predict-middle > span {
  display: block;
  margin-bottom: 10px;
  width: 80%;
  text-align: center;
}
.predict-right {
  flex-grow: 2;
  margin: 20px 0 10px 0;
  /* background-color: yellow; */
}
.predict-right > span {
  display: block;
  margin-bottom: 10px;
  width: 80%;
  text-align: center;
}
/* 第二行 */
.analysis-contribute-container {
  width: 100%;
  height: 500px;
  margin-top: 20px;
  border: 1px solid rgba(192, 191, 191, 0.3);
  background-color: #fff;
  display: flex;
  padding: 20px;
  padding-top: 40px;
  position: relative;
}
.contribute-left-container {
  height: 100%;
  margin-right: 20px;
  /* background-color: rgb(146, 127, 255); */
  flex-basis: 300px;
  flex-grow: 1;
}
.contribute-middle-container {
  height: 100%;
  margin-right: 20px;
  /* background-color: rgb(146, 127, 255); */
  flex-basis: 300px;
  flex-grow: 1;
}
.contribute-right-container {
  height: 100%;
  /* background-color: rgb(255, 127, 212); */
  flex-basis: 300px;
  flex-grow: 1;
}

/* 第三行 */
.analysis-relation-container {
  margin-top: 20px;
  width: 100%;
  height: 500px;
  border: 1px solid rgba(192, 191, 191, 0.3);
  background-color: #fff;
  padding: 20px;
  padding-top: 40px;
  position: relative;
  /* display: flex; */
}
.relation-firstline {
  width: 100%;
  height: 20px;
}
.relation-secondline {
  width: 100%;
  display: flex;
  height: 350px;
  margin-top: 10px;
  justify-content: space-between;
}
.relation-firstline::after {
  content: "";
  height: 1px;
  width: 100%;
  background-color: rgba(192, 191, 191, 0.3);
  position: absolute;
  top: 100px;
  left: 0;
}
.relation-inputs-section {
  margin-top: 20px;
}
.relation-btn {
  margin-left: 40px;
  width: 200px;
}
.realtion-left {
  margin: 0 20px 0 0;
  flex-basis: 300px;
  height: 100%;
}
.relation-middle-container {
  margin-right: 20px;
  flex-basis: 300px;
  height: 100%;
}
.relation-right-container {
  flex-basis: 300px;
  height: 100%;
}
</style>
