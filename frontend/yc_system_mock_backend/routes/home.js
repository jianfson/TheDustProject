var express = require('express');
var router = express.Router();

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
    chenghuaqu: randomArr(100,500, 10),
    gaoxinqu: randomArr(100,500, 10),
    shuangliuqu: randomArr(100,500, 10),
    jinniuqu: randomArr(100,500, 10),
    wuhouqu: randomArr(100,500, 10),
    qingyangqu: randomArr(100,500, 10),
    quanshi: randomArr(100,500, 10)
  })
})

router.get('/month/:year', function (req, res) {
  res.json({
    name: 'showByMonth',
    year: req.params.year,
    chenghuaqu: randomArr(100,500, 12),
    gaoxinqu: randomArr(100,500, 12),
    shuangliuqu: randomArr(100,500, 12),
    jinniuqu: randomArr(100,800, 12),
    wuhouqu: randomArr(100,500, 12),
    qingyangqu: randomArr(100,800, 12),
    quanshi: randomArr(100,900, 12)
  })
})

function randomArr(start, end, length) {
  var result = []
  for (let i = 0; i < length; i++) {
    result.push(Math.floor(Math.random() * (end - start)) + start)
  }
  return result
}
module.exports = router;
