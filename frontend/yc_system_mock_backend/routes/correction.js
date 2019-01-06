var express = require('express');
var router = express.Router();
var randomData = require('./randomData')

router.all('*', function (req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "X-Requested-With");
  res.header("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
  res.header("X-Powered-By", ' 3.2.1')
  res.header("Content-Type", "application/json;charset=utf-8");
  next();
})

var areaTransform = function (str) {
  let Zh = null
  switch (str) {
    case 'spring':
      Zh = '春'
      break
    case 'summer':
      Zh = '夏'
      break
    case 'autumn':
      Zh = '秋'
      break
    case 'winter':
      Zh = '冬'
      break
  }
  return Zh
}
router.post('/', function (req, res) {
  var session = ['spring', 'summer', 'autumn', 'winter']
  var id = req.body.id
  var time = req.body.date
  var method = req.body.method
  var result = {
    category: null,
    originData: null,
    nationControl: null,
    correction: null
  }
  if (method == 'month') {
    result.category = ['2018-12-01',
      '2018-12-02',
      '2018-12-03',
      '2018-12-04',
      '2018-12-05',
      '2018-12-06',
      '2018-12-07',
      '2018-12-08',
      '2018-12-09',
      '2018-12-10',
      '2018-12-11',
      '2018-12-12',
      '2018-12-13',
      '2018-12-14',
      '2018-12-15',
      '2018-12-16',
      '2018-12-17',
      '2018-12-18',
      '2018-12-19',
      '2018-12-20',
      '2018-12-21',
      '2018-12-22',
      '2018-12-23',
      '2018-12-24',
      '2018-12-25',
      '2018-12-26',
      '2018-12-27',
      '2018-12-28',
      '2018-12-29',
      '2018-12-30',
      '2018-12-31']
    result.originData = [
      100,
      107,
      113,
      152,
      165,
      136,
      152.55,
      173.22,
      137.46,
      151.91,
      120.31,
      128.554,
      119.38,
      117.2708333,
      149.0041667,
      125.1708333,
      196.0916667,
      174.0583333,
      188.475,
      204.1625,
      153.0208333,
      57.17916667,
      47.98333333,
      54.99166667,
      50.81666667,
      74.36666667,
      54.87916667,
      63.2625,
      69.19166667,
      83.25,
      88.8
    ]
    result.nationControl = [
      85,
      87,
      91,
      76,
      80,
      106,
      107,
      142,
      144,
      104,
      110,
      91,
      93,
      99,
      87,
      90,
      103,
      161,
      201,
      206,
      142,
      55,
      45,
      41,
      70,
      55,
      31,
      82,
      80,
      85,
      62
    ]
    result.correction = [
      74.20358628,
      73.27141089,
      80.07142413,
      62.21762484,
      69.64631812,
      92.74991883,
      92.98297625,
      128.2554193,
      132.0802234,
      91.36644424,
      96.75829754,
      80.15414314,
      80.92632046,
      86.74861804,
      76.58596832,
      76.42919509,
      91.78600115,
      149.7686455,
      187.9022292,
      193.8391082,
      127.3792714,
      40.26975059,
      32.65398777,
      29.89583615,
      59.67005342,
      40.64953104,
      18.8441183,
      69.34695321,
      65.05951035,
      71.68561322,
      47.81091154
    ]
  } else if (session.indexOf(method) != -1) {
    method = areaTransform(method)
    result.category = ['2015' + '-' + method, '2016' + '-' + method, '2017' + '-' + method, '2018' + '-' + method],
    result.originData = randomData.randomArr(100, 200, 4)
    result.nationControl = randomData.randomArr(200, 300, 4)
    result.correction = randomData.randomArr(200, 500, 4)
  } else {
    result.category = ['2015', '2016', '2017', '2018'],
      result.originData = randomData.randomArr(100, 200, 4)
    result.nationControl = randomData.randomArr(200, 300, 4)
    result.correction = randomData.randomArr(200, 500, 4)
  }
  res.json(result)
})





module.exports = router;