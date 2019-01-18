/*
import { constants } from 'http2';
 * @Author: mikey.zhaopeng
 * @Date: 2018-12-12 11:05:56
 * @Last Modified by: mikey.zhaopeng
 * @Last Modified time: 2019-01-17 21:37:58
 * desciption:
 *  1.实现父组件点击相应区域，能够切换到只显示对应区域的点位信息，通过添加和移除标注点实现。
 */

<template>
  <div id="mapContainer">
    <div id="allmap"></div>
    <div id="rightPanel" v-if="chartBox">
      <div class="pannelheader">
        <span v-on:click="closePanel">
          <i class="iconfront-yc icon-yc-close"></i>
        </span>
      </div>
      <div class="addedline">
        <div class="title">
          <span class="subtitle">点位详情</span>
        </div>
        <table id="detail-table">
          <tr>
            <td>点位名称：</td>
            <td>{{ currentSpot.name}}</td>
          </tr>
          <tr>
            <td>点位ID：</td>
            <td>{{ currentSpot.id}}</td>
          </tr>
          <tr>
            <td>地理位置：</td>
            <td>{{ currentSpot.address}}</td>
          </tr>
          <tr>
            <td>所属区域：</td>
            <td>{{ currentSpot.area}}</td>
          </tr>
        </table>
      </div>
      <div class="fristLine">
        <div class="title">
          <span class="subtitle">日期选择</span>
        </div>
        <div class="correction-form">
          <el-form v-model="correcForm" size="mini" label-position="left" label-width="80px">
            <el-form-item label="显示方式" prop="method">
              <el-cascader
                v-model="correcForm.method"
                @change="whichMethod"
                :options="timeMethodOptions"
                expand-trigger="hover"
              ></el-cascader>
            </el-form-item>
            <el-form-item label="日期" v-if="monthSelected" prop="pickedDate">
              <el-date-picker type="month" v-model="correcForm.pickedDate"></el-date-picker>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="showCorrection(currentSpot.id, correcForm)">查询</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <!-- <div class="2nd-line">
        <div class="title">
          <span class="subtitle">点位详细信息</span>
        </div>
      </div>-->
      <div class="thirdLine">
        <div class="title">
          <span class="subtitle">点位对比图</span>
        </div>
        <div id="correct-line-container">
          <correction-date ref="correctLine"></correction-date>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import correctionDate from '../components/charts/correctionDate'
export default {
  components: {
    correctionDate
  },
  data() {
    return {
      chartBox: false,
      currentSpot: null,
      correcForm: {
        method: null,
        pickedDate: null
      },
      initMap: null,
      allPoints: [],
      currentMaker: null,
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
      monthSelected: false
    }
  },
  name: 'mapArea',
  mounted() {
    // 初始请求全市的所有点位，这里不需要考虑时间段的pm10均值。
    /* 请求回来的数据格式为：
    ** res.data = [{
    **    id: "xy-m89x9810sc"
    **    location: "xx-路-号"
    **    area: '成华区',
    **    positionX: 104.03260665
    **    positionY: 30.64152998
    **},...]
    */
    this.initMap = this.drawMap()
    // 向后端请求： 返回所有点位信息
    this.$axios.get('dust/webresourcses/database.device/find/allcity').then(res => {
      // console.log(res.data)
      // 点位属于哪个区现在是前端的模拟，随机生成的，设计以后端返回的数据中就带上点位属于哪个区，前端不做判断
      // let areas = ['双流区', '武侯区', '金牛区', '高新区']
      // 初始化点位,构建全市所需要的点位，并赋值到全局。
      res.data.data.forEach(element => {
        let point = new window.BMap.Point(element.lng, element.lat)
        let options = {
          name: element.deviceName,
          id: element.deviceId,
          address: element.deviceAddress,
          area: element.region
        }
        this.allPoints.push({
          point,
          options
        })
        this.addMarker(point, options)
      })
    })
  },
  methods: {
    drawMap() {
      var map = new window.BMap.Map('allmap')
      // var point = new window.BMap.Point(104.02795346, 30.62994585)
      map.centerAndZoom('成都', 13)
      map.setMapStyle({ style: 'googlelite' })
      map.enableScrollWheelZoom(true)

      return map
    },
    addMarker(point, options) {
      var cleanIcon = new window.BMap.Icon(`../../static/imgs/blue.png`, new window.BMap.Size(20, 20), {
      })
      cleanIcon.imageSize = new window.BMap.Size(20, 20)
      this.currentMaker = new window.BMap.Marker(point, { icon: cleanIcon })
      this.currentMaker.id = options.id

      // 每个点位添加点击事件，点击弹出点位详细信息文本框。
      this.currentMaker.addEventListener('click', (e) => {
        // 点击显示右边的框
        this.chartBox = true
        // 修改点位详情的数据
        this.currentSpot = options
      })

      this.initMap.addOverlay(this.currentMaker)
    },
    // 显示对应日期的点位信息
    showCorrection(spotid, formData) {
      var resultForm = {
        id: spotid,
        method: null,
        date: null
      }
      if (formData.method[1]) {
        resultForm.method = formData.method[1]
      } else {
        resultForm.method = formData.method[0]
      }
      if (resultForm.method === 'month') {
        resultForm.date = formData.pickedDate
      } else {
        resultForm.date = null
      }
      this.$refs.correctLine.drawWithDate(resultForm)
    },
    closePanel() {
      this.chartBox = false
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
#mapContainer {
  min-height: 800px;
  height: 100%;
  width: 100%;
  position: relative;
}
#allmap {
  width: 100%;
  height: 100%;
}
#rightPanel {
  width: 500px;
  background-color: rgb(255, 255, 255);
  min-height: 580px;
  position: absolute;
  right: 0px;
  top: 50%;
  transform: translateY(-50%);
  z-index: 200;
}
.fristLine {
  padding: 0 20px;
}
#correct-line-container {
  width: 100%;
  height: 380px;
  /* background-color: yellow; */
}
#detail-table {
  padding: 10px 20px;
}
.pannelheader {
  padding: 5px;
}
.pannelheader span {
  cursor: pointer;
}
</style>
