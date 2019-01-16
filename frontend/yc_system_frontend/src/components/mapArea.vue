/*
import { constants } from 'http2';
 * @Author: mikey.zhaopeng
 * @Date: 2018-12-12 11:05:56
 * @Last Modified by: mikey.zhaopeng
 * @Last Modified time: 2019-01-16 00:26:21
 * desciption:
 *  1.实现父组件点击相应区域，能够切换到只显示对应区域的点位信息，通过添加和移除标注点实现。
 */

<template>
  <div id="allmap"></div>
</template>

<script>
export default {
  data() {
    return {
      initMap: null,
      allPoints: null,
      currentMaker: null
    }
  },
  name: 'mapArea',
  mounted() {
    // 初始化地图，不用添加点位，点位信息只有在点击了查询按钮后请求返回
    this.initMap = this.drawMap()
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
    // 根据区域切换对应的点位，传到home父组件，用于调用改变区域切换后点位显示。
    showPoints(area) {
      var that = this
      if (this.currentMaker) {
        this.initMap.clearOverlays()
      }
      // 向后端请求： 携带参数area，时间，返回对应区域对应时间的点位信息
      /* 请求回来的数据格式为：
   ** res.data = [{
   **    deviceAddress: "成华区圣灯街道办事处圣灯村二组",
deviceId: "1440-0028-sclw-2850",
deviceName: "成华区圣灯街道办事处（朗基少东家）",
color： 'red',
id: 20,
lat: 30.679834374681,
lng: 104.11902334135,
regionalId: 510108,...]
   */
      this.$axios.get('dust/webresourcses/database.device/region/' + area).then(res => {
        // 初始化点位,构建全市所需要的点位，并赋值到全局。
        this.allPoints = []
        res.data.forEach(element => {
          let point = new window.BMap.Point(element.lng, element.lat)
          let options = {
            name: element.deviceName,
            id: element.deviceId,
            color: element.color,
            address: element.deviceAddress,
            area: that.$areaBelong(element.regionalId)
          }
          this.allPoints.push({
            point,
            options
          })
          this.addMarker(point, options)
        })
      })
      // this.allPoints.forEach(ele => {
      //   if (ele.options.area === areaZH) {
      //     this.addMarker(ele.point, ele.options)
      //   }
      // })
    },
    whichColor(pmNum) {
    },
    addMarker(point, options) {
      var cleanIcon = new window.BMap.Icon(`../../static/imgs/${options.color}.png`, new window.BMap.Size(20, 20), {
      })
      cleanIcon.imageSize = new window.BMap.Size(20, 20)
      this.currentMaker = new window.BMap.Marker(point, { icon: cleanIcon })
      this.currentMaker.id = options.id

      // 每个点位添加点击事件，点击弹出点位详细信息文本框。
      this.currentMaker.addEventListener('click', (e) => {
        // console.log(e.currentTarget.id)
        let opts = {
          width: 250,
          height: 150,
          title: '点位信息',
          offset: new window.BMap.Size(0, 20)
        }
        var infoWindow = new window.BMap.InfoWindow(`<span>设备名称: ${options.name}<span></br>
        <span>地址: ${options.address}<span></br>
        <span>id: ${options.id}<span></br>
        <span>区域：${options.area}</span>`, opts)
        this.initMap.openInfoWindow(infoWindow, point)
      })

      this.initMap.addOverlay(this.currentMaker)
    }
  }
}
</script>

<style>
#allmap {
  width: 100%;
  height: calc(100% - 40px);
}
</style>
