var fs = require('fs')
var express = require('express');
var router = express.Router();


var randomData = require('./randomData')

/* GET home page. */
router.all('*', function (req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "X-Requested-With");
  res.header("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
  res.header("X-Powered-By", ' 3.2.1')
  res.header("Content-Type", "application/json;charset=utf-8");
  next();
})


// ************
// home 点位信息接口
router.get('/region/:num/:from/:to', function (req, res) {
  // console.log(req.params)
  // res.json([{
  //  "device_id" : "1440-0028-sclw-2803",
  // 	"lng" : 104.13328828752,
  // 	"lat" : 30.628542467496,
  //   "location" : "510108",
  //   "color": 'red',
  // 	"pm10" : 218.5
  // }])
  // console.log(req.params)

  // 接口实际格式
  // {
  //   deviceAddress: "成华区圣灯街道理工大学片区地块二",
  //   deviceId: "1440-0028-sclw-2857",
  //   deviceName: "成华区圣灯街道办事处（铁建广场4#）",
  //   id: 19,
  //   lat: 30.683570377566,
  //   lng: 104.14053311354,
  //   regionalId: 510108
  //   }
  if (req.params.num == "allcity" || !req.params.num) {
    // console.log('全市选择上了')
    fs.readFile('./routes/points.json', function (err, data) {
      var toStr = JSON.parse(data)
      res.json(
        toStr
      )
    })
  } else {
    var resultData = []
    var data = fs.readFile('./routes/points.json', function (err, data) {
      var toStr = JSON.parse(data)
      if (err) {
        return console.log(err)
      }
      toStr.forEach(item => {
        if (item.regionalId == req.params.num) {
          resultData.push(item)
        }
      })
      res.json(
        resultData
      )
    })
  }
})

// home 表格用
router.get('/table/:num/:from/:to', function (req, res) {
  res.json({
    data: [{
      "deviceAddress": "成华区圣灯街道办事处圣灯村二组",
      "deviceName": "成华区圣灯街道办事处（朗基少东家）",
      "color": "red",
      "deviceId": "xy-m89x9030sc",
      "lng": 104.10198848,
      "lat": 30.62738274,
      "pollutionRank": "middle",
      "pm10": 104
    },
    {
      "deviceAddress": "成华区圣灯街道办事处圣灯村二组",
      "deviceName": "成华区圣灯街道办事处（朗基少东家）",
      "color": "red",
      "deviceId": "xy-m89x9030sc",
      "lng": 104.10198848,
      "lat": 30.62738274,
      "pollutionRank": "bad",
      "pm10": 172
    },
    {
      "deviceAddress": "成华区圣灯街道办事处圣灯村二组",
      "deviceName": "成华区圣灯街道办事处（朗基少东家）",
      "color": "red",
      "deviceId": "xy-m89x9030sc",
      "lng": 104.10198848,
      "lat": 30.62738274,
      "pollutionRank": "excellent",
      "pm10": 10
    },
    {
      "deviceAddress": "成华区圣灯街道办事处圣灯村二组",
      "deviceName": "成华区圣灯街道办事处（朗基少东家）",
      "color": "yellow",
      "deviceId": "xy-m89x1788sc",
      "lng": 104.05894342,
      "lat": 30.62571377,
      "pollutionRank": "middle",
      "pm10": 110
    },
    {
      "deviceAddress": "成华区圣灯街道办事处圣灯村二组",
      "deviceName": "成华区圣灯街道办事处（朗基少东家）",
      "color": "yellow",
      "deviceId": "xy-m89x396sc",
      "lng": 104.11444129,
      "lat": 30.6796686,
      "pollutionRank": "good",
      "pm10": 70
    },
    ]
  })
})
// home 折线图用
router.get('/:num/:from/:to', function (req, res) {
  console.log(req.params)
  res.json({
    data: [
      {
        "avgTime": "2016-01",
        "pm10": 251.28000
      },
      {
        "avgTime": "2016-02",
        "pm10": 251.28000
      }, {
        "avgTime": "2016-03",
        "pm10": 251.28000
      }, {
        "avgTime": "2016-04",
        "pm10": 251.28000
      }

    ]
  })
})


// 综合查询地图点位
router.get('/find/:num', function (req, res) {
  console.log(req.params)
  res.json({
    data: [
      {
        "deviceAddress": "武侯区二环]dsxxx",
        "deviceId": "1440-0028-sclw-2800",
        "deviceName": "住宅、商业及配套xdfssfsa目8号地块",
        "lng": 104.133288,
        "lat": 30.628542,
        "region": "成华区"
      },
      {
        "deviceAddress": "武侯区二环路南一段20号",
        "deviceId": "1440-0028-sclw-28xdsa",
        "deviceName": "住宅、商业及配套设施（航校项目8号地块",
        "lng": 104.123188,
        "lat": 30.648552,
        "region": "成华区"
      }
    ]
  })
})
// ************


router.get('/chengdu', function (req, res) {

  res.json({
    name: 'chengduByyear',
    allCity: [
      "251.28000", "106.56808", "198.93536"
      , "153.32776"
      , "91.15123"
      , "88.08438"
      , "86.46801"
      , "79.05367"
      , "93.72295"
      , "96.13940"
      , "92.12068"
      , "129.69376"
      , "145.76647"
      , "50.67986"
      , "53.60038"
      , "67.08410"
      , "85.21299"
      , "64.29779"
      , "58.69084"
      , "50.64688"
      , "50.33612"
      , "40.71247"
      , "34.80464"
      , "24.66052"
    ]
  })
})

// router.get('/month/:year', function (req, res) {
//   res.json({
//     name: 'showByMonth',
//     year: req.params.year,
//     chenghuaqu: randomArr(100, 500, 12),
//     gaoxinqu: randomArr(100, 500, 12),
//     shuangliuqu: randomArr(100, 500, 12),
//     jinniuqu: randomArr(100, 800, 12),
//     wuhouqu: randomArr(100, 500, 12),
//     qingyangqu: randomArr(100, 800, 12),
//     quanshi: randomArr(100, 900, 12)
//   })
// })

router.post('/search', function (req, res) {
  // var area = req.body.area
  var start = req.body.start
  var end = req.body.end
  // console.log(req.body)
  var category = randomData.createMonthArr(start, end)
  var data = randomData.randomArr(20, 280, randomData.howmanyMonth)
  // switch (area) {
  //   case null:
  //     area = '全市'
  //     break;
  //   case 'chenghua':
  //     area = '成华区'
  //     break;
  //   case 'wuhou':
  //     area = '武侯区'
  //     break;
  //   case 'gaoxin':
  //     area = '高新区'
  //     break;
  //   case 'shaungliu':
  //     area = '双流区'
  //     break;
  //   case 'jingliu':
  //     area = '金牛区'
  //     break;
  // }
  res.json({
    // area,
    category,
    data
  })
})

router.post('/postable', function (req, res) {
  // console.log(req.body)
  res.json({
    data: [{
      name: 'Y0028152017154',
      location: '成都后花园二期B区一标段',
      level: '3级',
    }, {
      name: '1440-0028-sclw-2859',
      location: '成都市锦江区娇子立交以南，成龙路以西项目',
      level: '3级',
    }, {
      name: 'Y0028141914002',
      location: '五横道北延线工程 ',
      level: '2级',
    },
    {
      name: 'Y0028091510021',
      location: '成都科学城生态水环境工程项目部EPC总承包',
      level: '1级',
    },
    {
      name: '1440-0028-sclw-2814',
      location: '绿岛筑三标段15号楼',
      level: '1级',
    },
    {
      name: 'Y0028152618102',
      location: '锦巷兰台小区',
      level: '1级',
    }
    ]
  })
})

// function randomArr(start, end, length) {
//   var result = []
//   for (let i = 0; i < length; i++) {
//     result.push(Math.floor(Math.random() * (end - start)) + start)
//   }
//   return result
// }

module.exports = router;
