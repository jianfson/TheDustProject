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
// var randomSettedPoints = (function () {
//   let result = []
//   var colors = ['yellow','red','orange', 'blue']
//   var areas = ['双流区', '武侯区', '金牛区', '高新区', '成华区']
//   var maxWidth = 10417795346 - 10401795346
//   var maxHeight = 3070994585 - 3061994585
//   for (var i = 0; i < 80; i++) {
//     result.push({

//       location: 'xx-路-号',
//       pm10: Math.floor(Math.random() * 200) + 1,
//       color: colors[Math.floor(Math.random() * 4)],
//       id: 'xy-m89x'+ Math.floor(Math.random() * 10000)+'sc',
//       positionX: (10401795346 + Math.floor(Math.random() * maxWidth) +1) / 100000000 ,
//       positionY: (3061994585 + Math.floor(Math.random() * maxHeight) + 1) / 100000000,
//       area: areas[Math.floor(Math.random() * 5)]
//     })
//   }
//   fs.writeFile('./points.json',JSON.stringify(result))
//   return result
// })()

// 临时用的点位信息
var locations = [{
  "device_id": "1440-0028-sclw-2803",
  "lng": 104.13328828752,
  "lat": 30.628542467496,
  "location": "成华区",
  "pm10": 218.5
},
{
  "device_id": "1440-0028-sclw-2804",
  "lng": 104.13028962697,
  "lat": 30.67376220597,
  "location": "成华区",
  "pm10": 263.3
},
{
  "device_id": "1440-0028-sclw-2803",
  "lng": 104.13328828752,
  "lat": 30.628542467496,
  "location": "成华区",
  "pm10": 174.3
},
{
  "device_id": "1440-0028-sclw-2804",
  "lng": 104.13028962697,
  "lat": 30.67376220597,
  "location": "成华区",
  "pm10": 212.8
},
{
  "device_id": "1440-0028-sclw-2803",
  "lng": 104.13328828752,
  "lat": 30.628542467496,
  "location": "成华区",
  "pm10": 159.9
},
{
  "device_id": "1440-0028-sclw-2804",
  "lng": 104.13028962697,
  "lat": 30.67376220597,
  "location": "成华区",
  "pm10": 197.9
},
{
  "device_id": "1440-0028-sclw-2803",
  "lng": 104.13328828752,
  "lat": 30.628542467496,
  "location": "成华区",
  "pm10": 138.2
},
{
  "device_id": "1440-0028-sclw-2804",
  "lng": 104.13028962697,
  "lat": 30.67376220597,
  "location": "成华区",
  "pm10": 151.4
},
{
  "device_id": "1440-0028-sclw-2803",
  "lng": 104.13328828752,
  "lat": 30.628542467496,
  "location": "成华区",
  "pm10": 152.7
},
{
  "device_id": "1440-0028-sclw-2804",
  "lng": 104.13028962697,
  "lat": 30.67376220597,
  "location": "成华区",
  "pm10": 190.5
},
{
  "device_id": "1440-0028-sclw-2803",
  "lng": 104.13328828752,
  "lat": 30.628542467496,
  "location": "成华区",
  "pm10": 149.2
},
{
  "device_id": "1440-0028-sclw-2804",
  "lng": 104.13028962697,
  "lat": 30.67376220597,
  "location": "成华区",
  "pm10": 189.8
},
{
  "device_id": "1440-0028-sclw-2803",
  "lng": 104.13328828752,
  "lat": 30.628542467496,
  "location": "成华区",
  "pm10": 163.5
},
{
  "device_id": "1440-0028-sclw-2804",
  "lng": 104.13028962697,
  "lat": 30.67376220597,
  "location": "成华区",
  "pm10": 213.9
},
{
  "device_id": "1440-0028-sclw-2803",
  "lng": 104.13328828752,
  "lat": 30.628542467496,
  "location": "成华区",
  "pm10": 82.3
},
{
  "device_id": "1440-0028-sclw-2804",
  "lng": 104.13028962697,
  "lat": 30.67376220597,
  "location": "成华区",
  "pm10": 117.3
},
{
  "device_id": "1440-0028-sclw-2803",
  "lng": 104.13328828752,
  "lat": 30.628542467496,
  "location": "成华区",
  "pm10": 114.4
}]

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
  if (req.params.area === 'allcity') {
    var data = fs.readFile('./points.json', function (err, data) {
      var toStr = JSON.parse(data)
      res.json(
        toStr
      )
    })
  } else {
    var area = areaTransform(req.params.area)
    var resultData = []
    var data = fs.readFile('./points.json', function (err, data) {
      var toStr = JSON.parse(data)
      if (err) {
        return console.log(err)
      }
      toStr.forEach(item => {
        if (item.area == area) {
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