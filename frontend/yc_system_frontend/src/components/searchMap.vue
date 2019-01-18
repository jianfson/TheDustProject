/*
import { constants } from 'http2';
 * @Author: mikey.zhaopeng
 * @Date: 2018-12-12 11:05:56
 * @Last Modified by: mikey.zhaopeng
 * @Last Modified time: 2019-01-17 16:08:12
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
    this.initMap = this.drawMap()
  },
  methods: {
    drawMap() {
      var map = new window.BMap.Map('allmap')
      var point = new window.BMap.Point(104.072205, 30.66346)
      map.centerAndZoom(point, 13)
      map.setMapStyle({ style: 'googlelite' })
      map.enableScrollWheelZoom(true)

      return map
    },
    showPoints(area) {
      if (this.currentMaker) {
        this.initMap.clearOverlays()
      }
      this.$axios.get('dust/webresourcses/database.device/find/' + area).then(res => {
        this.allPoints = []
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
    whichColor(pmNum) {
    },
    addMarker(point, options) {
      var cleanIcon = new window.BMap.Icon(`../../static/imgs/blue.png`, new window.BMap.Size(20, 20), {
      })
      cleanIcon.imageSize = new window.BMap.Size(20, 20)
      this.currentMaker = new window.BMap.Marker(point, { icon: cleanIcon })
      this.currentMaker.id = options.id

      // 每个点位添加点击事件，点击弹出点位详细信息文本框。
      this.currentMaker.addEventListener('click', (e) => {
        let opts = {
          width: 250,
          height: 150,
          title: '点位信息',
          offset: new window.BMap.Size(0, 20)
        }
        var infoWindow = new window.BMap.InfoWindow(`<span>设备名称: ${options.name}<span></br>
        <span>id: ${options.id}<span></br>
        <span>区域：${options.area}</span></br>
        <span>地址: ${options.address}<span>`, opts)
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
