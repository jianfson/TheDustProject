<template>
  <div class="analysis-container">
    <div class="analysis-predict-container">
      <span class="predict-title">全市预测趋势</span>
      <div class="predict-left">
        <div class="inputs-section">
          <el-form :model="analysisPredictForm">
            <el-form-item label="区域" prop="area">
              <el-cascader expand-trigger="hover" :options="analysisOptions" v-model="analysisPredictForm.area"></el-cascader>
            </el-form-item>
            <!-- <el-form-item label="日期" prop="date">
              <el-date-picker v-model="analysisPredictForm.date" type="daterange" range-separator="至" size="mini"></el-date-picker>
            </el-form-item> -->
            <el-form-item><el-button class="predict-btn" type="primary">提交</el-button></el-form-item>
          </el-form>
        </div>
      </div>
      <div class="predict-middle">
        <all-predict></all-predict>
      </div>
      <div class="predict-right">
        <week-predict></week-predict>
      </div>
    </div>
    <div class="analysis-contribute-container">
      <span class="predict-title">区域排放贡献比重</span>
      <div class="contribute-left-container">
        <contribution-session></contribution-session>
      </div>
      <div class="contribute-right-container">
        <contribution-day></contribution-day>
      </div>
    </div>
    <div class="analysis-relation-container">
      <span class="predict-title">区域间关联性</span>
      <div class="realtion-left">
        <div class="relation-inputs-section">
          <el-form :model="analysisRelationForm">
            <el-form-item label="区域" prop="area">
              <el-select v-model="analysisRelationForm.area">
                <el-option v-for="item in analysisRelationOptions" :key="item.label" :label="item.lebel" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
            <!-- <el-form-item label="日期" prop="date">
              <el-date-picker v-model="analysisPredictForm.date" type="daterange" range-separator="至" size="mini"></el-date-picker>
            </el-form-item> -->
            <el-form-item><el-button class="relation-btn" type="primary">提交</el-button></el-form-item>
          </el-form>
        </div>
      </div>
      <div class="relation-middle-container">
        <relation-seasion></relation-seasion>
      </div>
      <div class="relation-right-container">
        <relation-day></relation-day>
      </div>
    </div>
  </div>
</template>

<script>
import allPredict from '../components/charts/allpredict'
import weekPredict from '../components/charts/weekPredict'
import contributionSession from '../components/charts/contributionSession'
import contributionDay from '../components/charts/contributionDay'
import relationDay from '../components/charts/relationDay'
import relationSeasion from '../components/charts/relationSeasion'

export default {
  components: {
    allPredict,
    weekPredict,
    contributionSession,
    contributionDay,
    relationDay,
    relationSeasion
  },
  data() {
    return {
      analysisPredictForm: {
        area: null,
        date: null
      },
      analysisRelationForm: {
        area: null
      },
      analysisRelationOptions: [
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
.inputs-section {
  margin-top: 40px;
}
.predict-btn {
  margin-left: 40px;
  width: 200px;
}
.predict-middle {
  flex-grow: 2;
  /* background-color: yellow; */
  margin: 20px 20px 0 0;
}
.predict-right {
  flex-grow: 2;
  margin: 20px 0 0 0;
  /* background-color: yellow; */
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
  display: flex;
}
.relation-inputs-section {
  margin-top: 40px;
}
.relation-btn {
  margin-left: 40px;
  width: 200px;
}
.realtion-left {
  width: 300px;
  margin: 0 20px 0 0;
}
.relation-middle-container {
  flex-grow: 2;
}
.relation-right-container {
  flex-grow: 2;
}
</style>
