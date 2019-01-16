var fs = require('fs')
var express = require('express');
var router = express.Router();

router.all('*', function (req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "X-Requested-With");
  res.header("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
  res.header("X-Powered-By", ' 3.2.1')
  res.header("Content-Type", "application/json;charset=utf-8");
  next();
})





// 创建点位信息，并保存为json文件，用于调用，每次运行默认不运行
// var create = function () {
//   let result = []
//   var colors = ['yellow', 'red', 'orange', 'blue']
//   var areas = [510104, 510105, 510106, 510107, 510108]
//   var maxWidth = 10417795346 - 10401795346
//   var maxHeight = 3070994585 - 3061994585
//   for (var i = 0; i < 80; i++) {
//     result.push({
//       deviceAddress: "成华区圣灯街道办事处圣灯村二组",
//       deviceName: "成华区圣灯街道办事处（朗基少东家）",
//       color: colors[Math.floor(Math.random() * 4)],
//       deviceId: 'xy-m89x' + Math.floor(Math.random() * 10000) + 'sc',
//       lng: (10401795346 + Math.floor(Math.random() * maxWidth) + 1) / 100000000,
//       lat: (3061994585 + Math.floor(Math.random() * maxHeight) + 1) / 100000000,
//       regionalId: areas[Math.floor(Math.random() * 5)]
//     })
//   }
//   fs.writeFile('./points.json', JSON.stringify(result))
//   return result
// }
// create()


router.get('/init', function (req, res) {
  var data = fs.readFile('./points.json', function (err, data) {
    if (err) {
      return console.log(err)
    }
    res.json(
      JSON.parse(data)
    )
  })
})
var areaTransform = function (str) {
  let Zh = null
  switch (str) {
    case 'wuhou':
      Zh = '武侯区'
      break
    case 'chenghua':
      Zh = '成华区'
      break
    case 'gaoxin':
      Zh = '高新区'
      break
    case 'shuangliu':
      Zh = '双流区'
      break
    case 'jingniu':
      Zh = '金牛区'
      break
  }
  return Zh
}

router.get('/spec/:area/*', function (req, res) {
  if (req.params.area == 'allcity') {
    var data = fs.readFile('./points.json', function (err, data) {
      var toStr = JSON.parse(data)
      res.json(
        toStr
      )
    })
  } else {
    var resultData = []
    var data = fs.readFile('./points.json', function (err, data) {
      var toStr = JSON.parse(data)
      if (err) {
        return console.log(err)
      }
      toStr.forEach(item => {
        if (item.location == req.params.area) {
          resultData.push(item)
        }
      })
      res.json(
        resultData
      )
    })
  }

  // 现在读取真实数据，临时用
  // locations.forEach(ele => {
  //   if(ele.pm10 <= 100) {
  //     ele.color = "blue"
  //   } else if(ele.pm10 > 100 && ele.pm10 <= 200) {
  //     ele.color = 'yellow'
  //   } else if (ele.pm10 > 200 && ele.pm10 <= 300) {
  //     ele.color = 'orange'
  //   } else {
  //     ele.color = 'red'
  //   }
  // })
  // res.json(
  //   JSON.stringify(locations)
  // )
})


module.exports = router