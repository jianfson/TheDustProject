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

router.get('/', function(req, res) {
  res.json({
    data: [
      {
        id: 'Y0028152017154',
        color: 'red',
        location: 'xxx区新兴路yy号',
        rank: '3级',
        positionX: 104.07795346,
        positionY: 30.67994585
      },
      {
        id: '1440-0028-sclw-2859',
        color: 'red',
        location: 'xxx区新兴路yy号',
        rank: '3级',
        positionX: 104.02795346,
        positionY: 30.62994585
      },
      {
        id: 'Y0028141914002',
        color: 'orange',
        location: 'xxx区新兴路yy号',
        rank: '2级',
        positionX: 104.04795346,
        positionY: 30.64994585
      },
      {
        id: 'Y0028091510021',
        color: 'yellow',
        location: 'xxx区新兴路yy号',
        rank: '1级',
        positionX: 104.04795346,
        positionY: 30.68994585
      },

      {
        id: '1440-0028-sclw-2814',
        color: 'yellow',
        location: 'xxx区新兴路yy号',
        rank: '1级',
        positionX: 104.17795346,
        positionY: 30.67994585
      },
      {
        id: 'Y0028152618102',
        color: 'yellow',
        location: 'xxx区新兴路yy号',
        rank: '1级',
        positionX: 104.05795346,
        positionY: 30.67924585
      },
      {
        id: 'XHHB-Y0017040100175',
        color: 'yellow',
        location: 'xxx区新兴路yy号',
        rank: '1级',
        positionX: 104.01795346,
        positionY: 30.61994585
      },
      {
        id: 'Y0028152017154',
        color: 'blue',
        location: 'xxx区新兴路yy号',
        rank: '0级',
        positionX: 104.03795346,
        positionY: 30.62994585
      },
      {
        id: 'Y0028152017154',
        color: 'blue',
        location: 'xxx区新兴路yy号',
        rank: '0级',
        positionX: 104.05795346,
        positionY: 30.64994585
      },
      {
        id: 'Y0028152017154',
        color: 'blue',
        location: 'xxx区新兴路yy号',
        rank: '0级',
        positionX: 104.11795346,
        positionY: 30.70994585
      },
      
    ]
  })
}) 


module.exports = router