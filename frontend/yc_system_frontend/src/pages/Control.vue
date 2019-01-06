<template>
  <div class="control-container">
    <div class="control-allCity-container">
      <span class="control-title">全市概况</span>
      <span class="control-subtitle">{{currentDate}}</span>
      <div class="control-zone-middle">
        <div class="control-zone-middle-tran">
          <div class="zone-work-sum">
            <ul class="control-data-panel">
              <li>{{chengdu.alert[0]}}</li>
              <li>红色预警</li>
            </ul>
          </div>
          <div class="zone-max-work">
            <ul class="control-data-panel">
              <li>{{chengdu.alert[1]}}</li>
              <li>橙色预警</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{chengdu.alert[2]}}</li>
              <li>黄色预警</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="control-zone-middle">
        <div class="control-zone-middle-tran">
          <div class="zone-work-sum">
            <ul class="control-data-panel">
              <li>{{chengdu.currentWorkSite}}</li>
              <li>当前开工数量</li>
            </ul>
          </div>
          <div class="zone-max-work">
            <ul class="control-data-panel">
              <li>{{chengdu.leftWorksite}}</li>
              <li>最大的开工工地数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{chengdu.maxWorkSite}}</li>
              <li>剩余可开工数量</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <!-- 第二行 -->
    <div class="control-zone-container">
      <span class="control-title">青羊区</span>
      <span class="control-subtitle">{{currentDate}}</span>
      <div class="control-zone-middle">
        <div class="control-zone-middle-tran">
          <div class="zone-work-sum">
            <ul class="control-data-panel">
              <li>{{qingyang.currentWorkSite}}</li>
              <li>当前开工数量</li>
            </ul>
          </div>
          <div class="zone-max-work">
            <ul class="control-data-panel">
              <li>{{qingyang.maxWorkSite}}</li>
              <li>最大的开工工地数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{qingyang.leftWorksite}}</li>
              <li>剩余可开工数量</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="control-zone-right">
        <!-- <span @click="toggleZoneDetail">查看区域点位详情<i class="iconfront-yc icon-yc-jiantouarrowheads3"></i></span> -->
        <div class="zoneDetailTable">
          <!-- <span class="zoneTableTitle">区域点位详情</span> -->
          <el-table :data="qingyang.detailTable" size="mini" border height="210">
            <el-table-column label="点位名称" prop="name"></el-table-column>
            <el-table-column label="工程位置" prop="location"></el-table-column>
            <el-table-column label="开工日期" prop="startDate"></el-table-column>
            <el-table-column label="竣工日期" prop="endDate"></el-table-column>
          </el-table>
        </div>
      </div>
    </div>
    <!-- 第三行 -->
    <div class="control-zone-container">
      <span class="control-title">成华区</span>
      <span class="control-subtitle">{{currentDate}}</span>
      <div class="control-zone-middle">
        <div class="control-zone-middle-tran">
          <div class="zone-work-sum">
            <ul class="control-data-panel">
              <li>{{chenghua.currentWorkSite}}</li>
              <li>当前开工数量</li>
            </ul>
          </div>
          <div class="zone-max-work">
            <ul class="control-data-panel">
              <li>{{chenghua.maxWorkSite}}</li>
              <li>最大的开工工地数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{chenghua.leftWorksite}}</li>
              <li>剩余可开工数量</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="control-zone-right">
        <!-- <span @click="toggleZoneDetail">查看区域点位详情<i class="iconfront-yc icon-yc-jiantouarrowheads3"></i></span> -->
        <div class="zoneDetailTable">
          <!-- <span class="zoneTableTitle">区域点位详情</span> -->
          <el-table :data="chenghua.detailTable" size="mini" border height="210">
            <el-table-column label="点位名称" prop="name"></el-table-column>
            <el-table-column label="工程位置" prop="location"></el-table-column>
            <el-table-column label="开工日期" prop="startDate"></el-table-column>
            <el-table-column label="竣工日期" prop="endDate"></el-table-column>
          </el-table>
        </div>
      </div>
    </div>
    <!-- 第四行 -->
    <div class="control-zone-container">
      <span class="control-title">金牛区</span>
      <span class="control-subtitle">{{currentDate}}</span>
      <div class="control-zone-middle">
        <div class="control-zone-middle-tran">
          <div class="zone-work-sum">
            <ul class="control-data-panel">
              <li>{{jinniu.currentWorkSite}}</li>
              <li>当前开工数量</li>
            </ul>
          </div>
          <div class="zone-max-work">
            <ul class="control-data-panel">
              <li>{{jinniu.maxWorkSite}}</li>
              <li>最大的开工工地数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{jinniu.leftWorksite}}</li>
              <li>剩余可开工数量</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="control-zone-right">
        <!-- <span @click="toggleZoneDetail">查看区域点位详情<i class="iconfront-yc icon-yc-jiantouarrowheads3"></i></span> -->
        <div class="zoneDetailTable">
          <!-- <span class="zoneTableTitle">区域点位详情</span> -->
          <el-table :data="jinniu.detailTable" size="mini" border height="210">
            <el-table-column label="点位名称" prop="name"></el-table-column>
            <el-table-column label="工程位置" prop="location"></el-table-column>
            <el-table-column label="开工日期" prop="startDate"></el-table-column>
            <el-table-column label="竣工日期" prop="endDate"></el-table-column>
          </el-table>
        </div>
      </div>
    </div>
    <!-- 第五行 -->
    <div class="control-zone-container">
      <span class="control-title">武侯区</span>
      <span class="control-subtitle">{{currentDate}}</span>
      <div class="control-zone-middle">
        <div class="control-zone-middle-tran">
          <div class="zone-work-sum">
            <ul class="control-data-panel">
              <li>{{wuhou.currentWorkSite}}</li>
              <li>当前开工数量</li>
            </ul>
          </div>
          <div class="zone-max-work">
            <ul class="control-data-panel">
              <li>{{wuhou.maxWorkSite}}</li>
              <li>最大的开工工地数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{wuhou.leftWorksite}}</li>
              <li>剩余可开工数量</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="control-zone-right">
        <!-- <span @click="toggleZoneDetail">查看区域点位详情<i class="iconfront-yc icon-yc-jiantouarrowheads3"></i></span> -->
        <div class="zoneDetailTable">
          <!-- <span class="zoneTableTitle">区域点位详情</span> -->
          <el-table :data="wuhou.detailTable" size="mini" border height="210">
            <el-table-column label="点位名称" prop="name"></el-table-column>
            <el-table-column label="工程位置" prop="location"></el-table-column>
            <el-table-column label="开工日期" prop="startDate"></el-table-column>
            <el-table-column label="竣工日期" prop="endDate"></el-table-column>
          </el-table>
        </div>
      </div>
    </div>
    <!-- 第六行 -->
    <div class="control-zone-container">
      <span class="control-title">双流区</span>
      <span class="control-subtitle">{{currentDate}}</span>
      <div class="control-zone-middle">
        <div class="control-zone-middle-tran">
          <div class="zone-work-sum">
            <ul class="control-data-panel">
              <li>{{shuangliu.currentWorkSite}}</li>
              <li>当前开工数量</li>
            </ul>
          </div>
          <div class="zone-max-work">
            <ul class="control-data-panel">
              <li>{{shuangliu.maxWorkSite}}</li>
              <li>最大的开工工地数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{shuangliu.leftWorksite}}</li>
              <li>剩余可开工数量</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="control-zone-right">
        <!-- <span @click="toggleZoneDetail">查看区域点位详情<i class="iconfront-yc icon-yc-jiantouarrowheads3"></i></span> -->
        <div class="zoneDetailTable">
          <!-- <span class="zoneTableTitle">区域点位详情</span> -->
          <el-table :data="shuangliu.detailTable" size="mini" border height="210">
            <el-table-column label="点位名称" prop="name"></el-table-column>
            <el-table-column label="工程位置" prop="location"></el-table-column>
            <el-table-column label="开工日期" prop="startDate"></el-table-column>
            <el-table-column label="竣工日期" prop="endDate"></el-table-column>
          </el-table>
        </div>
      </div>
    </div>
    <!-- 第七行 -->
    <div class="control-zone-container">
      <span class="control-title">高新区</span>
      <span class="control-subtitle">{{currentDate}}</span>
      <div class="control-zone-middle">
        <div class="control-zone-middle-tran">
          <div class="zone-work-sum">
            <ul class="control-data-panel">
              <li>{{gaoxin.currentWorkSite}}</li>
              <li>当前开工数量</li>
            </ul>
          </div>
          <div class="zone-max-work">
            <ul class="control-data-panel">
              <li>{{gaoxin.maxWorkSite}}</li>
              <li>最大的开工工地数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{gaoxin.leftWorksite}}</li>
              <li>剩余可开工数量</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="control-zone-right">
        <!-- <span @click="toggleZoneDetail">查看区域点位详情<i class="iconfront-yc icon-yc-jiantouarrowheads3"></i></span> -->
        <div class="zoneDetailTable">
          <!-- <span class="zoneTableTitle">区域点位详情</span> -->
          <el-table :data="gaoxin.detailTable" size="mini" border height="210">
            <el-table-column label="点位名称" prop="name"></el-table-column>
            <el-table-column label="工程位置" prop="location"></el-table-column>
            <el-table-column label="开工日期" prop="startDate"></el-table-column>
            <el-table-column label="竣工日期" prop="endDate"></el-table-column>
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
import relationSeasion from '../components/charts/relationSeasion'

export default {
  components: {
    allPredict,
    weekPredict,
    contributionSession,
    relationSeasion
  },
  data() {
    return {
      tableData: [
        {
          endDate: '2016-05-02',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }, {
          endDate: '2016-05-04',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }, {
          endDate: '2016-05-01',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }, {
          endDate: '2016-05-03',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }, {
          endDate: '2016-05-03',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }, {
          endDate: '2016-05-03',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }, {
          endDate: '2016-05-03',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }, {
          endDate: '2016-05-03',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }
      ],
      currentDate: null, // 当前显示的数据的日期,
      chengdu: {
        alert: []
      },
      chenghua: {
        currentWorkSite: null,
        maxWorkSite: null,
        leftWorksite: null
      },
      qingyang: {
        currentWorkSite: null,
        maxWorkSite: null,
        leftWorksite: null
      },
      wuhou: {
        currentWorkSite: null,
        maxWorkSite: null,
        leftWorksite: null
      },
      shuangliu: {
        currentWorkSite: null,
        maxWorkSite: null,
        leftWorksite: null
      },
      gaoxin: {
        currentWorkSite: null,
        maxWorkSite: null,
        leftWorksite: null
      },
      jinniu: {
        currentWorkSite: null,
        maxWorkSite: null,
        leftWorksite: null
      }
    }
  },
  mounted() {
    // 页面初始时，返回全市的各项数据，以及各区域的所需数据
    /* 返回的数据格式：
    ** data: {
      date: String | Date 显示的是哪个季度
      chengdu: {
        alert: [Number, Number, Number] 分别红色预警，橙色预警，红色预警,
        currentWorkSite: Number 当前开工数量
        maxWorkSite: Number 最大开工数量
        leftWorksite: Number 剩余开工数量,
      },
      qingyang: {
        currentWorkSite: Number 当前开工数量
        maxWorkSite: Number 最大开工数量
        leftWorksite: Number 剩余开工数量,
        detailTable:[{
          endDate: '2016-05-02',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }, {
          endDate: '2016-05-04',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }]
      },
      chenghua: {
        currentWorkSite: Number 当前开工数量
        maxWorkSite: Number 最大开工数量
        leftWorksite: Number 剩余开工数量,
        detailTable:[{
          endDate: '2016-05-02',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }, {
          endDate: '2016-05-04',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }]
      },
      jinniu: {
        currentWorkSite: Number 当前开工数量
        maxWorkSite: Number 最大开工数量
        leftWorksite: Number 剩余开工数量,
        detailTable:[{
          endDate: '2016-05-02',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }, {
          endDate: '2016-05-04',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }]
      },
      wuhou: {
        currentWorkSite: Number 当前开工数量
        maxWorkSite: Number 最大开工数量
        leftWorksite: Number 剩余开工数量,
        detailTable:[{
          endDate: '2016-05-02',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }, {
          endDate: '2016-05-04',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }]
      },
      shuangliu: {
        currentWorkSite: Number 当前开工数量
        maxWorkSite: Number 最大开工数量
        leftWorksite: Number 剩余开工数量,
        detailTable:[{
          endDate: '2016-05-02',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }, {
          endDate: '2016-05-04',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }]
      }
      gaoxing: {
        currentWorkSite: Number 当前开工数量
        maxWorkSite: Number 最大开工数量
        leftWorksite: Number 剩余开工数量,
        detailTable:[{
          endDate: '2016-05-02',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }, {
          endDate: '2016-05-04',
          name: '王小虎',
          location: '上海市普陀区金沙江路',
          startDate: '2016-05-02'
        }]
      }
    }
    **
    */
    this.$axios.get('http://localhost:3000/control/init').then(data => {
      console.log(data.data.gaoxin)
      this.currentDate = data.data.date
      this.chengdu = data.data.chengdu
      this.chenghua = data.data.chenghua
      this.qingyang = data.data.qingyang
      this.wuhou = data.data.wuhou
      this.shuangliu = data.data.shuangliu
      this.gaoxin = data.data.gaoxin
      this.jinniu = data.data.jinniu
    })
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
  height: 320px;
  border: 1px solid rgba(192, 191, 191, 0.3);
  background-color: #fff;
  padding: 20px;
  padding-bottom: 0px;
  padding-top: 50px;
  display: flex;
  position: relative;
  margin-bottom: 20px;
}
.control-allCity-left {
  width: 200px;
  margin: 0 20px 0 0;
}
.control-title {
  font-size: 1.4em;
  position: absolute;
  width: 100%;
  top: 20px;
}
.control-subtitle {
  font-size: 0.4em;
  color: rgba(192, 191, 191, 1);
  position: absolute;
  top: 30px;
  right: 60px;
}
.control-title::after {
  content: "";
  height: 1px;
  width: 100%;
  background-color: rgba(192, 191, 191, 0.3);
  position: absolute;
  top: 35px;
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
  width: 50%;
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
  flex-grow: 1;
  min-width: 360px;
  width: 50%;
  /* background-color: yellow; */
  margin: 20px 20px 0 0;
  padding: 0 20px 20px 0;
}
.control-zone-container {
  width: 100%;
  height: 320px;
  border: 1px solid rgba(192, 191, 191, 0.3);
  background-color: #fff;
  padding: 60px 20px 20px 20px;
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
