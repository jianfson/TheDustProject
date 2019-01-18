<template>
  <div class="control-container">
    <div class="control-allCity-container">
      <span class="control-title">全市概况</span>
      <span class="control-subtitle">{{currentDate}}</span>
      <div class="control-zone-middle">
        <div class="control-zone-middle-tran">
          <div class="zone-work-sum">
            <ul class="control-data-panel">
              <li>{{chengdu.red}}</li>
              <li>红色预警</li>
            </ul>
          </div>
          <div class="zone-max-work">
            <ul class="control-data-panel">
              <li>{{chengdu.orange}}</li>
              <li>橙色预警</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{chengdu.yellow}}</li>
              <li>黄色预警</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="control-zone-middle">
        <div class="control-zone-middle-tran">
          <div class="zone-work-sum">
            <ul class="control-data-panel">
              <li>{{chengdu.current}}</li>
              <li>当前开工数量</li>
            </ul>
          </div>
          <div class="zone-max-work">
            <ul class="control-data-panel">
              <li>{{chengdu.max}}</li>
              <li>工地最大开工数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{chengdu.remain}}</li>
              <li>剩余可开工数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{chengdu.stop}}</li>
              <li>需强制停工数量</li>
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
              <li>{{qingyang.current}}</li>
              <li>当前开工数量</li>
            </ul>
          </div>
          <div class="zone-max-work">
            <ul class="control-data-panel">
              <li>{{qingyang.max}}</li>
              <li>工地最大开工数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{qingyang.remain}}</li>
              <li>剩余可开工数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{qingyang.stop}}</li>
              <li>需强制停工数量</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="control-zone-right">
        <!-- <span @click="toggleZoneDetail">查看区域点位详情<i class="iconfront-yc icon-yc-jiantouarrowheads3"></i></span> -->
        <div class="zoneDetailTable">
          <!-- <span class="zoneTableTitle">区域点位详情</span> -->
          <el-table :data="qingyangTable" size="mini" border height="210">
            <el-table-column label="点位名称" prop="companyName"></el-table-column>
            <el-table-column label="工程位置" prop="companyAddress"></el-table-column>
            <el-table-column label="开工日期" prop="startTime"></el-table-column>
            <el-table-column label="竣工日期" prop="stopTime"></el-table-column>
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
              <li>{{chenghua.current}}</li>
              <li>当前开工数量</li>
            </ul>
          </div>
          <div class="zone-max-work">
            <ul class="control-data-panel">
              <li>{{chenghua.max}}</li>
              <li>工地最大开工数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{chenghua.remain}}</li>
              <li>剩余可开工数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{chenghua.stop}}</li>
              <li>需强制停工数量</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="control-zone-right">
        <!-- <span @click="toggleZoneDetail">查看区域点位详情<i class="iconfront-yc icon-yc-jiantouarrowheads3"></i></span> -->
        <div class="zoneDetailTable">
          <!-- <span class="zoneTableTitle">区域点位详情</span> -->
          <el-table :data="chenghuaTable" size="mini" border height="210">
            <el-table-column label="点位名称" prop="companyName"></el-table-column>
            <el-table-column label="工程位置" prop="companyAddress"></el-table-column>
            <el-table-column label="开工日期" prop="startTime"></el-table-column>
            <el-table-column label="竣工日期" prop="stopTime"></el-table-column>
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
              <li>{{jinniu.current}}</li>
              <li>当前开工数量</li>
            </ul>
          </div>
          <div class="zone-max-work">
            <ul class="control-data-panel">
              <li>{{jinniu.max}}</li>
              <li>工地最大开工数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{jinniu.remain}}</li>
              <li>剩余可开工数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{jinniu.stop}}</li>
              <li>需强制停工数量</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="control-zone-right">
        <!-- <span @click="toggleZoneDetail">查看区域点位详情<i class="iconfront-yc icon-yc-jiantouarrowheads3"></i></span> -->
        <div class="zoneDetailTable">
          <!-- <span class="zoneTableTitle">区域点位详情</span> -->
          <el-table :data="jinniuTable" size="mini" border height="210">
            <el-table-column label="点位名称" prop="companyName"></el-table-column>
            <el-table-column label="工程位置" prop="companyAddress"></el-table-column>
            <el-table-column label="开工日期" prop="startTime"></el-table-column>
            <el-table-column label="竣工日期" prop="stopTime"></el-table-column>
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
              <li>{{wuhou.current}}</li>
              <li>当前开工数量</li>
            </ul>
          </div>
          <div class="zone-max-work">
            <ul class="control-data-panel">
              <li>{{wuhou.max}}</li>
              <li>工地最大开工数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{wuhou.remain}}</li>
              <li>剩余可开工数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{wuhou.stop}}</li>
              <li>需强制停工数量</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="control-zone-right">
        <!-- <span @click="toggleZoneDetail">查看区域点位详情<i class="iconfront-yc icon-yc-jiantouarrowheads3"></i></span> -->
        <div class="zoneDetailTable">
          <!-- <span class="zoneTableTitle">区域点位详情</span> -->
          <el-table :data="wuhouTable" size="mini" border height="210">
            <el-table-column label="点位名称" prop="companyName"></el-table-column>
            <el-table-column label="工程位置" prop="companyAddress"></el-table-column>
            <el-table-column label="开工日期" prop="startTime"></el-table-column>
            <el-table-column label="竣工日期" prop="stopTime"></el-table-column>
          </el-table>
        </div>
      </div>
    </div>
    <!-- 第六行 -->
    <div class="control-zone-container">
      <span class="control-title">锦江区</span>
      <span class="control-subtitle">{{currentDate}}</span>
      <div class="control-zone-middle">
        <div class="control-zone-middle-tran">
          <div class="zone-work-sum">
            <ul class="control-data-panel">
              <li>{{jinjiang.current}}</li>
              <li>当前开工数量</li>
            </ul>
          </div>
          <div class="zone-max-work">
            <ul class="control-data-panel">
              <li>{{jinjiang.max}}</li>
              <li>工地最大开工数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{jinjiang.remain}}</li>
              <li>剩余可开工数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{jinjiang.stop}}</li>
              <li>需强制停工数量</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="control-zone-right">
        <!-- <span @click="toggleZoneDetail">查看区域点位详情<i class="iconfront-yc icon-yc-jiantouarrowheads3"></i></span> -->
        <div class="zoneDetailTable">
          <!-- <span class="zoneTableTitle">区域点位详情</span> -->
          <el-table :data="jinjiangTable" size="mini" border height="210">
            <el-table-column label="点位名称" prop="companyName"></el-table-column>
            <el-table-column label="工程位置" prop="companyAddress"></el-table-column>
            <el-table-column label="开工日期" prop="startTime"></el-table-column>
            <el-table-column label="竣工日期" prop="stopTime"></el-table-column>
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
              <li>{{gaoxin.current}}</li>
              <li>当前开工数量</li>
            </ul>
          </div>
          <div class="zone-max-work">
            <ul class="control-data-panel">
              <li>{{gaoxin.max}}</li>
              <li>工地最大开工数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{gaoxin.remain}}</li>
              <li>剩余可开工数量</li>
            </ul>
          </div>
          <div class="zone-left-work">
            <ul class="control-data-panel">
              <li>{{gaoxin.stop}}</li>
              <li>需强制停工数量</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="control-zone-right">
        <!-- <span @click="toggleZoneDetail">查看区域点位详情<i class="iconfront-yc icon-yc-jiantouarrowheads3"></i></span> -->
        <div class="zoneDetailTable">
          <!-- <span class="zoneTableTitle">区域点位详情</span> -->
          <el-table :data="gaoxinTable" size="mini" border height="210">
            <el-table-column label="点位名称" prop="companyName"></el-table-column>
            <el-table-column label="工程位置" prop="companyAddress"></el-table-column>
            <el-table-column label="开工日期" prop="startTime"></el-table-column>
            <el-table-column label="竣工日期" prop="stopTime"></el-table-column>
          </el-table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentDate: null, // 当前显示的数据的日期,
      chengdu: {},
      chenghua: {
        current: null,
        max: null,
        remain: null
      },
      chenghuaTable: null,
      qingyang: {
        current: null,
        max: null,
        remain: null
      },
      qingyangTable: null,
      wuhou: {
        current: null,
        max: null,
        remain: null
      },
      wuhouTable: null,
      jinjiang: {
        current: null,
        max: null,
        remain: null
      },
      jinjiangTable: null,
      gaoxin: {
        current: null,
        max: null,
        remain: null
      },
      gaoxinTable: null,
      jinniu: {
        current: null,
        max: null,
        remain: null
      },
      jinniuTable: null
    }
  },
  mounted() {
    // 全市的情况
    this.$axios.get('/dust/webresourcses/control/allcity').then(res => {
      this.chengdu = res.data.data
      this.currentDate = res.data.data.season
    })

    // 青羊区
    this.$axios.get('/dust/webresourcses/control/510105').then(res => {
      this.qingyang = res.data.data
    })
    this.$axios.get('/dust/webresourcses/control/company/510105').then(res => {
      this.qingyangTable = res.data.data
    })

    // 锦江区
    this.$axios.get('/dust/webresourcses/control/510104').then(res => {
      this.jinjiang = res.data.data
    })
    this.$axios.get('/dust/webresourcses/control/company/510104').then(res => {
      this.jinjiangTable = res.data.data
    })

    // 金牛区
    this.$axios.get('/dust/webresourcses/control/510106').then(res => {
      this.jinniu = res.data.data
    })
    this.$axios.get('/dust/webresourcses/control/company/510106').then(res => {
      this.jinniuTable = res.data.data
    })

    // 武侯区
    this.$axios.get('/dust/webresourcses/control/510107').then(res => {
      this.wuhou = res.data.data
    })
    this.$axios.get('/dust/webresourcses/control/company/510107').then(res => {
      this.wuhouTable = res.data.data
    })

    // 成华区
    this.$axios.get('/dust/webresourcses/control/510108').then(res => {
      this.chenghua = res.data.data
    })
    this.$axios.get('/dust/webresourcses/control/company/510108').then(res => {
      this.chenghuaTable = res.data.data
    })

    // 高新区
    this.$axios.get('/dust/webresourcses/control/510109').then(res => {
      this.gaoxin = res.data.data
    })
    this.$axios.get('/dust/webresourcses/control/company/510109').then(res => {
      this.gaoxinTable = res.data.data
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
