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
router.get('/:area', function (req, res, next) {
  let datetime = randomData.createDateArr('2017-10-21', '2018-11-17')
  let length = randomData.computeLength('2017-10-21', '2018-11-17')
  if (req.params.area == 'chengdu') {
    let data = randomData.randomArr(100, 500, length)
    res.json({
      name: 'allcity',
      category: datetime,
      data
    })
  }
});

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

module.exports = router;
