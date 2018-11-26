<template>
  <div id="historySearch">
    <div class="history-left-container">
      <div class="history-right-form-title history-left-top-container">
        <span class="history-subtitle">历史数据查询</span>
      </div>
      <div class="history-map-left">
        <map-area></map-area>
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
              <el-cascader expand-trigger="hover" :options="historyOptions" v-model="historyForm.area" size="mini"></el-cascader>
            </el-form-item>
            <el-form-item label="日期" prop="date">
              <el-date-picker v-model="historyForm.date" type="daterange" range-separator="至" size="mini"></el-date-picker>
            </el-form-item>
            <el-form-item><el-button class="home-btn" size="mini" type="primary">查 询</el-button></el-form-item>
          </el-form>
        </div>
      </div>
      <div class="history-bottom-graph">
        <history-date></history-date>
      </div>
    </div>
  </div>
</template>

<script>
import historyDate from '../components/charts/historyDate'
import mapArea from '../components/mapArea'
export default {
  components: {
    historyDate,
    mapArea
  },
  data() {
    return {
      historyForm: {
        area: null,
        date: null
      },
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
        }, {
          value: 'points',
          label: '点位查询',
          children: [
            {
              label: '0x123', value: '0x123'
            }, {
              label: '0x124', value: '0x123'
            }, {
              label: '0x153', value: '0x123'
            }, {
              label: '0x1x3', value: '0x123'
            }, {
              label: '0x1v3', value: '0x123'
            }
          ]
        }
      ]
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
  width:  100%;
  height: 200px;
}
.history-bottom-graph {
  width: 100%;
  height: calc(100% - 200px);
}

/* 左边地图部分 */
.history-left-top-container {
}
.history-map-left {
  height: calc(100% - 30px);
  width: 100%;
}
</style>
