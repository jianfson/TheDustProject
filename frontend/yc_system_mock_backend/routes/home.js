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

router.get('/years', function (req, res) {
  res.json({
    name: "byyear",
    // chenghuaqu: randomArr(100, 500, 10),
    // gaoxinqu: randomArr(100, 500, 10),
    // shuangliuqu: randomArr(100, 500, 10),
    // jinniuqu: randomArr(100, 500, 10),
    // wuhouqu: randomArr(100, 500, 10),
    // qingyangqu: randomArr(100, 500, 10),
    // quanshi: randomArr(100, 500, 10)
  })
})

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

router.get('/search/:area/:date', function (req, res) {
  var area = req.params.area
  var date = JSON.parse(req.params.date)
  var category = randomData.createMonthArr(date.startMonth, date.endMonth)
  var data = randomData.randomArr(20, 280, randomData.howmanyMonth)
  switch (area) {
    case 'allcity':
      area = '全市'
      break;
    case 'chenghua':
      area = '成华区'
      break;
    case 'wuhou':
      area = '武侯区'
      break;
    case 'gaoxin':
      area = '高新区'
      break;
    case 'shaungliu':
      area = '双流区'
      break;
    case 'jingliu':
      area = '金牛区'
      break;
  }
  res.json({
    area,
    category,
    data
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
