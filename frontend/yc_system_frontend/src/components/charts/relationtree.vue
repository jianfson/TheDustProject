<template>
  <div id="relationtree"></div>
</template>

<script>
import Axios from 'axios'
export default {
  mounted() {
    var relationtree = this.echarts.init(document.getElementById('relationtree'))
    relationtree.showLoading()
    Axios.get('http://localhost:8080/static/json/spots.json').then((data) => {
      relationtree.hideLoading()
      var allpointdata = data.data
      // this.echarts.util.each(data.children, function (datum, index) {
      //   index % 2 === 0 && (datum.collapsed = true)
      // })
      var option = {
        tooltip: {
          trigger: 'item',
          triggerOn: 'mousemove'
        },
        series: [
          {
            type: 'tree',

            data: [allpointdata],

            top: '1%',
            left: '7%',
            bottom: '1%',
            right: '20%',

            symbolSize: 7,

            label: {
              normal: {
                position: 'left',
                verticalAlign: 'middle',
                align: 'right',
                fontSize: 9
              }
            },

            leaves: {
              label: {
                normal: {
                  position: 'right',
                  verticalAlign: 'middle',
                  align: 'left'
                }
              }
            },

            expandAndCollapse: true,
            animationDuration: 550,
            animationDurationUpdate: 750
          }
        ]
      }

      relationtree.setOption(option)
    })
  }
}
</script>

<style>
#relationtree {
  width: 500px;
  height: 700px;
  padding: 20px;
}
</style>
