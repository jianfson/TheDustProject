<template>
  <div class="control-container">
    <div class="control-allCity-container">
      <span class="control-title">全市概况</span>
      <div class="control-allCity-left">
        <div class="control-discription">
          <span>时间：<p>2018年秋季</p></span>
        </div>
      </div>
      <div class="control-allCity-middle">
        <div class="control-allCity-middle-tran">
          <div class="allCity-red">
            <ul class="control-data-panel">
              <li>0</li>
              <li>红色预警</li>
            </ul>
          </div>
          <div class="allCity-orange">
            <ul class="control-data-panel">
              <li>22</li>
              <li>橙色预警</li>
            </ul>
          </div>
          <div class="allCity-yellow">
            <ul class="control-data-panel">
              <li>12</li>
              <li>黄色预警</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="control-allCity-right">
         <ul class="control-data-panel">
            <li>0.34<span>%</span></li>
            <li>主城区污染影响因子</li>
          </ul>
      </div>
    </div>
    <!-- 第二行 -->
    <div class="control-zone-container">
        <span class="control-title">青羊区</span>
        <div class="control-zone-left">
          <div class="control-discription">
            <span>时间：<p>2018年秋季</p></span>
          </div>
        </div>
        <div class="control-zone-middle">
          <div class="control-zone-middle-tran">
            <div class="zone-work-sum">
              <ul class="control-data-panel">
                <li>10</li>
                <li>当前开工数量</li>
              </ul>
            </div>
            <div class="zone-max-work">
              <ul class="control-data-panel">
                <li>13</li>
                <li>最大的开工工地数量</li>
              </ul>
            </div>
            <div class="zone-left-work">
              <ul class="control-data-panel">
                <li>7</li>
                <li>剩余可开工数量</li>
              </ul>
            </div>
          </div>
        </div>
        <div class="control-zone-right">
          <!-- <span @click="toggleZoneDetail">查看区域点位详情<i class="iconfront-yc icon-yc-jiantouarrowheads3"></i></span> -->
          <div class="zoneDetailTable">
            <span class="zoneTableTitle">区域点位详情</span>
            <el-table :data="tableData" size="mini" style="width: 400px;" border >
              <el-table-column label="点位名称" prop="name" width="80"></el-table-column>
              <el-table-column label="工程位置" prop="location" width="150"></el-table-column>
              <el-table-column label="污染等级" prop="rank"></el-table-column>
              <el-table-column label="变化趋势" prop="trend"></el-table-column>
            </el-table>
          </div>
        </div>
    </div>
    <div class="control-zone-container">
        <span class="control-title">成华区</span>
        <div class="control-zone-left">
          <div class="control-discription">
            <span>时间：<p>2018年秋季</p></span>
          </div>
        </div>
        <div class="control-zone-middle">
          <div class="control-zone-middle-tran">
            <div class="zone-work-sum">
              <ul class="control-data-panel">
                <li>2</li>
                <li>当前开工数量</li>
              </ul>
            </div>
            <div class="zone-max-work">
              <ul class="control-data-panel">
                <li>5</li>
                <li>最大的开工工地数量</li>
              </ul>
            </div>
            <div class="zone-left-work">
              <ul class="control-data-panel">
                <li>7</li>
                <li>剩余可开工数量</li>
              </ul>
            </div>
          </div>
        </div>
        <div class="control-zone-right">
          <!-- <span @click="toggleZoneDetail">查看区域点位详情<i class="iconfront-yc icon-yc-jiantouarrowheads3"></i></span> -->
          <div class="zoneDetailTable">
            <span class="zoneTableTitle">区域点位详情</span>
            <el-table :data="tableData" size="mini" style="width: 400px;" border >
              <el-table-column label="点位名称" prop="name" width="80"></el-table-column>
              <el-table-column label="工程位置" prop="location" width="150"></el-table-column>
              <el-table-column label="污染等级" prop="rank"></el-table-column>
              <el-table-column label="变化趋势" prop="trend"></el-table-column>
            </el-table>
          </div>
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
      tableData: [
        {
          trend: '2016-05-02',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          rank: '****'
        }, {
          trend: '2016-05-04',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          rank: '****'
        }, {
          trend: '2016-05-01',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          rank: '****'
        }, {
          trend: '2016-05-03',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          rank: '****'
        }
      ]
    }
  },
  methods: {
    toggleZoneDetail() {

    }
  }
}
</script>

<style>
.control-container {
  background-color: rgba(243, 243, 243, 0.712);
  padding: 20px;
}
/* 第一行 */
.control-allCity-container {
  width: 100%;
  height: 300px;
  border: 1px solid rgba(192, 191, 191, 0.3);
  background-color: #fff;
  padding: 20px;
  padding-bottom: 0px;
  padding-top: 40px;
  display: flex;
  position: relative;
  margin-bottom: 20px;
}
.control-allCity-left {
  width: 200px;
  margin: 0 20px 0 0;
}
.control-title {
  font-size: 1.2em;
  position: absolute;
  width: 100%;
  top: 20px;
}
.control-title::after {
  content: "";
  height: 1px;
  width: 100%;
  background-color: rgba(192, 191, 191, 0.3);
  position: absolute;
  top: 25px;
  left: -20px;
}
.control-allCity-middle {
  flex-grow: 2;
  min-width: 450px;
  /* background-color: yellow; */
  margin: 20px 20px 0 0;
  padding: 0 20px 20px 0;
}
.control-allCity-middle-tran,
.control-zone-middle-tran {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: center;
}
.allCity-red,
.zone-work-sum {
  background-color: rgba(192, 191, 191, 0.3);
  flex-grow: 2;
  min-width: 100px;
  margin-right: 10px;
}

.allCity-orange,
.zone-max-work {
  background-color: rgba(192, 191, 191, 0.3);
  flex-grow: 2;
  min-width: 100px;
  margin-right: 10px;
}

.allCity-yellow,
.zone-left-work {
  background-color: rgba(192, 191, 191, 0.3);
  flex-grow: 2;
  min-width: 100px;
  margin-right: 10px;
}
.control-allCity-right {
  flex-grow: 2;
  margin: 20px 0 30px 0;
  background-color: rgba(192, 191, 191, 0.3);
}
.control-discription {
  margin-top: 40px;
}

/* 数据panel面板的样式 */
.control-data-panel {
  height: 100%;
}
.control-data-panel li {
  display: block;
  text-align: center;
}
.control-data-panel li:first-child {
  height: 70%;
  line-height: 150px;
  font-size: 6em;
  vertical-align: bottom;
}
.control-data-panel li:first-child span {
  font-size: 16px;
}
.control-data-panel li:last-child {
  height: 30%;
  line-height: 60px;
}

/* 第二行 */
.control-zone-right {
  min-width: 300px;
  height: 100%;
  flex-grow: 1;
  /* text-align: right;
  right: 20px;
  line-height: 450px;
  vertical-align: bottom;
  cursor: pointer;
  position: relative; */
}
.control-zone-right i {
  position: absolute;
  display: inline-block;
  margin: 0 0 0 10px;
  transform: rotate(180deg);
  font-size: 22px;
}
.zoneDetailTable {
  padding-top: 20px;
  width: 100%;
  height: 100%;
  /* background-color: yellow; */
}
.control-zone-middle {
  flex-grow: 2;
  min-width: 360px;
  /* background-color: yellow; */
  margin: 20px 20px 0 0;
  padding: 0 20px 20px 0;
}
.control-zone-container {
  width: 100%;
  height: 300px;
  border: 1px solid rgba(192, 191, 191, 0.3);
  background-color: #fff;
  padding: 20px;
  padding-bottom: 0px;
  padding-top: 40px;
  display: flex;
  position: relative;
  margin-bottom: 20px;
  flex-wrap: nowrap;
}
.control-zone-left {
  width: 100px;
}
.zoneTableTitle {
  display: block;
  margin-bottom: 10px;
}
</style>
