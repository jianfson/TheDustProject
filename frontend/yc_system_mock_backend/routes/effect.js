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

router.get('/year/:date', function (req, res) {
  res.json(
    {
      data: [
        {
          "ID": " 510104",
          "effect": 0.2,
          "workSite": 31

        },
        {
          "ID": " 510105",
          "effect": 0.5,
          "workSite": 31

        },
        {
          "ID": " 510106",
          "effect": 0.1,
          "workSite": 31

        }, {
          "ID": " 510107",
          "effect": 0.2,
          "workSite": 31

        }, {
          "ID": " 510108",
          "effect": 0.3,
          "workSite": 31
        }, {
          "ID": " 510109",
          "effect": 0.3,
          "workSite": 31
        }
      ]
    }
  )
})

router.get('/season/:year/:season', function (req, res) {
  console.log(req.body)
  res.json(
    {
      data: [
        {
          "ID": " 510104",
          "effect": 0.2,
          "workSite": 31

        },
        {
          "ID": " 510105",
          "effect": 0.5,
          "workSite": 31

        },
        {
          "ID": " 510106",
          "effect": 0.1,
          "workSite": 31

        }, {
          "ID": " 510107",
          "effect": 0.2,
          "workSite": 31

        }, {
          "ID": " 510108",
          "effect": 0.3,
          "workSite": 31
        }, {
          "ID": " 510109",
          "effect": 0.3,
          "workSite": 31
        }
      ]
    }
  )
})
router.get('/month/:date', function (req, res) {
  console.log(req.body)
  res.json(
    {
      data: [
        {
          "ID": " 510104",
          "effect": 0.2,
          "workSite": 31

        },
        {
          "ID": " 510105",
          "effect": 0.5,
          "workSite": 31

        },
        {
          "ID": " 510106",
          "effect": 0.1,
          "workSite": 31

        }, {
          "ID": " 510107",
          "effect": 0.2,
          "workSite": 31

        }, {
          "ID": " 510108",
          "effect": 0.3,
          "workSite": 31
        }, {
          "ID": " 510109",
          "effect": 0.3,
          "workSite": 31
        }
      ]
    }
  )
})

module.exports = router