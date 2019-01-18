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

router.post('/init', function (req, res) {
  res.json({
    year: {
      date: '2018',
      data: [
        { value: 0.66, name: '成华区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '双流区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '高新区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '武侯区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '青羊区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '金牛区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '天府新区' }
      ].sort(function (a, b) { return a.value - b.value })
    },
    session: {
      date: ['2018', 'spring'],
      data: [
        { value: 0.33, name: '成华区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '双流区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '高新区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '武侯区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '青羊区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '金牛区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '天府新区' }
      ].sort(function (a, b) { return a.value - b.value })
    },
    month: {
      date: '2018-01',
      data: [
        { value: 0.33, name: '成华区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '双流区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '高新区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '武侯区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '青羊区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '金牛区' },
        { value: Math.floor(Math.random() * 100) / 100, name: '天府新区' }
      ].sort(function (a, b) { return a.value - b.value })
    }
  })
})
router.get('/year/:num/:date', function (req, res) {
  console.log(req.params)
  res.json(
    {
      data: [
        {
          "ID": " 510104",
          "relation": 0.2,
        },
        {
          "ID": " 510105",
          "relation": 0.5,
        },
        {
          "ID": " 510106",
          "relation": 0.1,
        }, {
          "ID": " 510107",
          "relation": 0.2,
        }, {
          "ID": " 510108",
          "relation": 0.3,
        }
      ]
    }
  )
})

router.get('/season/:num/:year/:date', function (req, res) {
  console.log(req.params)
  res.json(
    {
      data: [
        {
          "ID": " 510104",
          "relation": 0.2,
        },
        {
          "ID": " 510105",
          "relation": 0.5,
        },
        {
          "ID": " 510106",
          "relation": 0.1,
        }, {
          "ID": " 510107",
          "relation": 0.2,
        }, {
          "ID": " 510108",
          "relation": 0.3,
        }
      ]
    }
  )
})

router.get('/month/:num/:date', function (req, res) {
  console.log(req.params)
  res.json(
    {
      data: [
        {
          "ID": " 510104",
          "relation": 0.2,
        },
        {
          "ID": " 510105",
          "relation": 0.5,
        },
        {
          "ID": " 510106",
          "relation": 0.1,
        }, {
          "ID": " 510107",
          "relation": 0.2,
        }, {
          "ID": " 510108",
          "relation": 0.3,
        }
      ]
    }
  )
})

module.exports = router