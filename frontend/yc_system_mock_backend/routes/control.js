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

router.get('/init', function (req, res) {
  res.json({
    date: '2018 春季',
    chengdu: {
      alert: [7, 2, 12],
      currentWorkSite: 72,
      maxWorkSite: 130,
      leftWorksite: 58,
    },
    qingyang: {
      currentWorkSite: 4,
      maxWorkSite: 12,
      leftWorksite: 8,
      detailTable: [{
        endDate: '2016-05-02',
        name: 'Y0028091510021',
        location: '成都科学城生态水环境工程项目部EPC总承包',
        startDate: '2016-05-02'
      }, {
        endDate: '2016-05-04',
        name: 'Y0028091510021',
        location: '锦巷兰台小区',
        startDate: '2016-05-02'
      },
      {
        endDate: '2016-05-04',
        name: 'Y0028091510021',
        location: '锦巷兰台小区',
        startDate: '2016-05-02'
      },
      {
        endDate: '2016-05-04',
        name: 'Y0028091510021',
        location: '锦巷兰台小区',
        startDate: '2016-05-02'
      },
      {
        endDate: '2016-05-04',
        name: 'Y0028091510021',
        location: '锦巷兰台小区',
        startDate: '2016-05-02'
      },{
        endDate: '2016-05-04',
        name: 'Y0028091510021',
        location: '锦巷兰台小区',
        startDate: '2016-05-02'
      },{
        endDate: '2016-05-04',
        name: 'Y0028091510021',
        location: '锦巷兰台小区',
        startDate: '2016-05-02'
      }]
    },
    chenghua: {
      currentWorkSite: 9,
      maxWorkSite: 10,
      leftWorksite: 1,
      detailTable: [{
        endDate: '2016-05-02',
        name: '王小虎',
        location: '上海市普陀区金沙江路',
        startDate: '2016-05-02'
      }, {
        endDate: '2016-05-04',
        name: '王小虎',
        location: '上海市普陀区金沙江路',
        startDate: '2016-05-02'
      }]
    },
    jinniu: {
      currentWorkSite: 10,
      maxWorkSite: 21,
      leftWorksite: 11,
      detailTable: [{
        endDate: '2016-05-02',
        name: '王小虎',
        location: '上海市普陀区金沙江路',
        startDate: '2016-05-02'
      }, {
        endDate: '2016-05-04',
        name: '王小虎',
        location: '上海市普陀区金沙江路',
        startDate: '2016-05-02'
      }]
    },
    wuhou: {
      currentWorkSite: 31,
      maxWorkSite: 41,
      leftWorksite: 10,
      detailTable: [{
        endDate: '2016-05-02',
        name: '王小虎',
        location: '上海市普陀区金沙江路',
        startDate: '2016-05-02'
      }, {
        endDate: '2016-05-04',
        name: '王小虎',
        location: '上海市普陀区金沙江路',
        startDate: '2016-05-02'
      }]
    },
    shuangliu: {
      currentWorkSite: 1,
      maxWorkSite: 7,
      leftWorksite: 6,
      detailTable: [{
        endDate: '2016-05-02',
        name: '王小虎',
        location: '上海市普陀区金沙江路',
        startDate: '2016-05-02'
      }, {
        endDate: '2016-05-04',
        name: '王小虎',
        location: '上海市普陀区金沙江路',
        startDate: '2016-05-02'
      }]
    },
    gaoxin: {
      currentWorkSite: 11,
      maxWorkSite: 15,
      leftWorksite: 4,
      detailTable: [{
        endDate: '2016-05-02',
        name: '王小虎',
        location: '上海市普陀区金沙江路',
        startDate: '2016-05-02'
      }, {
        endDate: '2016-05-04',
        name: '王小虎',
        location: '上海市普陀区金沙江路',
        startDate: '2016-05-02'
      }]
    }
  })
})


module.exports = router