<template>
  <div class="zoneOrigin">
    <div class="asidebar">
      <div class="left-table">
        <div class="title">
          <span class="crossline"></span>
          <span class="subtitle">标题1</span>
        </div>
        <el-table size="mini" :data="tableData" max-height="200" border style="width: 100%">
          <el-table-column label="序号" prop="id"></el-table-column>
          <el-table-column label="所属区域" prop="zone"></el-table-column>
          <el-table-column label="地理位置" prop="location"></el-table-column>
        </el-table>
      </div>
      <div class="submitspot">
        <div class="title">
          <span class="crossline"></span>
          <span class="subtitle">标题2</span>
        </div>
        <el-form label-width="60px" size="small" :model="formdata">
          <el-form-item label="经度">
            <el-input v-model="formdata.x"></el-input>
          </el-form-item>
          <el-form-item label="纬度">
            <el-input v-model="formdata.y"></el-input>
          </el-form-item>
          <el-form-item label="区域">
            <el-select v-model="formdata.zone">
              <el-option label="全市" value="allCity"></el-option>
              <el-option label="成华区" value="chenghua"></el-option>
              <el-option label="武侯区" value="wuhou"></el-option>
              <el-option label="高新区" value="gaoxin"></el-option>
              <el-option label="双流区" value="shuangliu"></el-option>
              <el-option label="金牛区" value="jingniu"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button>提交</el-button>
            <el-button>清除</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="mapInRight">
      <map-area></map-area>
    </div>
  </div>
</template>

<script>
import mapArea from '../../components/mapArea'
export default {
  name: 'ZoneMange',
  components: {
    mapArea
  },
  data() {
    return {
      tableData: [
        {
          id: '0x123',
          zone: 'changhua',
          location: 'xxxxx-yyyyy'
        },
        {
          id: '0x1dsf',
          zone: 'fa',
          location: 'xxxxx-yyyyy'
        },
        {
          id: '0x1xx321',
          zone: 'chsfsua',
          location: 'xxxxx-yyyyy'
        }
      ],
      formdata: {
        x: null,
        y: null,
        zone: null
      }
    }
  }
}
</script>

<style>
.zoneOrigin {
  position: relative;
  height: 100%;
}
.asidebar {
  width: 280px;
  position: absolute;
  z-index: 100;
  background-color: #fff;
  height: 100%;
  padding: 0 20px;
}
.submitspot{
  margin-top: 20px;
}
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
  background-color: white;
  left: 50%;
  transform: translateX(-50%);
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
.mapInRight {
  width: 100%;
  height: 100%;
}
</style>
