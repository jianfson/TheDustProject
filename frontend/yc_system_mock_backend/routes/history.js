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

/* GET home page. */
// router.get('/:area', function (req, res, next) {
//   let datetime = randomData.createDateArr('2017-10-21', '2018-11-17')
//   let length = randomData.computeLength('2017-10-21', '2018-11-17')
//   if (req.params.area == 'chengdu') {
//     let data = randomData.randomArr(100, 500, length)
//     res.json({
//       name: 'allcity',
//       category: datetime,
//       data
//     })
//   }
// });(

var  areaTransform = function(str) {
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
  
router.post('/', function(req, res) {
  // console.log(req.body)
  var area = req.body.area
  var method = req.body.method
  var date = new Date(req.body.date)
  var session = ['spring' ,'summer' ,'autumn' ,'winter']
  console.log(req.body.area)
  console.log(req.body.method)
  console.log(req.body.date)
  console.log('************************8')
  if (method === 'year') {
    console.log('选择了年')

      res.json({
        category: ['2015', '2016', '2017', '2018'],
        data: randomData.randomArr(100, 500, 4)
      })
    } else if(session.indexOf(method) != -1) {
      console.log('选择了季度')
      method = areaTransform(method)
      res.json({
      category: ['2015'+ '-' + method, '2016'+ '-' + method, '2017'+ '-' + method, '2018'+ '-' + method],
      data: randomData.randomArr(100, 500, 4)
    })
    } else if(method === 'month') {

      // 选择的时间为月，产生一个月的数据
      console.log('选择了月')

      var start = new Date(date)
      var end = new Date(new Date(date.setMonth(date.getMonth() + 1)).setDate(-1))
      var length = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24) + 1

      res.json({
        category: randomData.createDateArr(start, end),
        data: randomData.randomArr(100, 500, length)

      })
    }
    
  
})
router.get('/cdmonth', function (req, res) {
  let datetime = randomData.createDateArr('2016-03-01', '2016-03-31')
  res.json({
    category: datetime,
    data: [105.77097,
      107.60323,
      270.075,
      107.86129,
      127.97119,
      128.53667,
      96.73462,
      80.51935,
      77.47742,
      103.93226,
      128.88065,
      110.71774,
      171.35484,
      109.70968,
      110.01353,
      188,
      129.22097,
      101.77593,
      106.28667,
      114.69286,
      140.62903,
      139.23548,
      107.47742,
      213.73548,
      85.14839,
      157.5129,
      117.55161,
      231.31935,
      178.63333,
      233.99355,
      200.15667
    ]
  })
})
router.get('/cdsession', function (req, res) {
  res.json({
    category: ['2016/春','2017/春','2018/春'],
    'spring': [
      // '2016': [106.56808, 198.93536, 153.32776],
      153.32776,
      129.69376,
      // '2017': [92.12068, 129.69376, 145.76647],
      // '2018': [64.29779, 58.69084, 50.64688]
      58.69084
  ]
  })
})
router.get('/cdyear', function(req, res) {
  res.json({
    category:['2015','2016','2017','2018'],
    data:[251.28, 123.2217,102.4321,51.22345]
  })
})
router.get('/cdyear', function (req, res) {
  res.json({
    category: ['2015-12', '2016-01', '2016-02', '2016-03', '2016-04', '2016-05', '2016-06', '2016-07', '2016-08', '2016-09', '2016-10', '2016-11', '2016-12', '2017-09', '2017-10', '2017-11', '2017-12', '2018-01', '2018-02', '2018-03', '2018-04', '2018-05', '2018-06', '2018-07'],
    data: [
      251.28,
      106.56808,
      198.93536,
      153.32776,
      91.15123,
      88.08438,
      86.46801,
      79.05367,
      93.72295,
      96.1394,
      92.12068,
      129.69376,
      145.76647,
      50.67986,
      53.60038,
      67.0841,
      85.21299,
      64.29779,
      58.69084,
      50.64688,
      50.33612,
      40.71247,
      34.80464,
      24.66052
    ]
  })
})
router.get('/cdyear', function (req, res) {

})

router.get('/all/:start/:end', function (req, res) {
  var start = req.params.start
  var end = req.params.end
  let datetime = randomData.createDateArr(start, end)
  let length = randomData.computeLength(start, end)
  let data = randomData.randomArr(100, 500, length)
  res.json({
    name: 'allcityByTime',
    category: datetime,
    data
  })
})

router.get('/zones/:start/:end', function (req, res) {
  var start = req.params.start
  var end = req.params.end
  let datetime = randomData.createDateArr(start, end)
  let length = randomData.computeLength(start, end)
  let chenghua = randomData.randomArr(100, 300, length)
  let gaoxing = randomData.randomArr(100, 300, length)
  let shuangliu = randomData.randomArr(100, 300, length)
  let jinniu = randomData.randomArr(100, 300, length)
  let wuhou = randomData.randomArr(100, 300, length)
  let qingyang = randomData.randomArr(100, 300, length)

  res.json({
    name: 'eachZones',
    category: datetime,
    data: {
      chenghua,
      gaoxing,
      shuangliu,
      jinniu,
      wuhou,
      qingyang
    }
  })
})


router.get('/init', function(req, res) {
  res.json({
    category:['2015','2016','2017','2018'],
    data:[251.28, 123.2217,102.4321,51.22345]
  })
})
module.exports = router;
