<template>
  <div id="allmap"></div>
</template>

<script>
export default {
  name: 'mapArea',
  mounted() {
    var map = new window.BMap.Map('allmap')
    // var point = new window.BMap.Point(104.02795346, 30.62994585)
    map.centerAndZoom('成都', 13)
    map.setMapStyle({style:'googlelite'})
    map.enableScrollWheelZoom(true)
    this.$axios.get('http://localhost:3000/map').then(res=> {
      res.data.data.forEach(element => {
        let point = new window.BMap.Point(element.positionX, element.positionY)
        let options = {
          id: element.id,
          color: element.color,
          location: element.location,
          rank: element.rank
        }
        console.log(point)
        this.addMarker(map, point, options)
      })
    })
  },
  methods: {
    addMarker(map, point, options) {
      var cleanIcon = new window.BMap.Icon(`../../static/imgs/${options.color}.png`, new window.BMap.Size(40, 40), {

      })
      cleanIcon.imageSize = new window.BMap.Size(40, 40)
      var cleanMarker = new window.BMap.Marker(point, { icon: cleanIcon })
      cleanMarker.id = options.id

      cleanMarker.addEventListener('click', function (e) {
        // console.log(e.currentTarget.id)
        let opts = {
          width: 250,
          height: 100,
          title: '点位信息',
          offset: new window.BMap.Size(0, 20)
        }
        var infoWindow = new window.BMap.InfoWindow(`<span>id: ${options.id}<span></br><span>地理位置：${options.location}</span></br><span>污染等级:${options.rank}</span>`, opts)
        map.openInfoWindow(infoWindow, point)
      })
      map.addOverlay(cleanMarker)
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
