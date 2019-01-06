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

router.post('/bar', function(req, res) {
  res.json({
    category: ['2018', '2019'],
    spring: [123,251],
    summer: [321,134],
    autumn: [233,412,100],
    winter: [321,123]
  })
})

router.post('/line', function(req, res) {
  let range = req.body.range
  if(range == 'month') {
    res.json({
      category: ['2018-04-01',
      '2018-04-02',
      '2018-04-03',
      '2018-04-04',
      '2018-04-05',
      '2018-04-06',
      '2018-04-07',
      '2018-04-08',
      '2018-04-09',
      '2018-04-10',
      '2018-04-11',
      '2018-04-12',
      '2018-04-13',
      '2018-04-14',
      '2018-04-15',
      '2018-04-16',
      '2018-04-17',
      '2018-04-18',
      '2018-04-19',
      '2018-04-20',
      '2018-04-21',
      '2018-04-22',
      '2018-04-23',
      '2018-04-24',
      '2018-04-25',
      '2018-04-26',
      '2018-04-27',
      '2018-04-28',
      '2018-04-29',
      '2018-04-30'],
      data: randomData.randomArr(100,321, 30)
    })
  } else if(range == 'year') {
    res.json({
      category: ['2018-01',
    '2018-02',
    '2018-03',
    '2018-04',
    '2018-05',
    '2018-06',
    '2018-07',
    '2018-08',
    '2018-09',
    '2018-10',
    '2018-11',
    '2018-12'],
    data: randomData.randomArr(100,321, 12)
    })  
  } else {
    res.json({
      category: ['2018', '2019', '2020'],
    data: randomData.randomArr(100,321, 3)
    })
  }
  
})
module.exports = router