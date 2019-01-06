
var randomData = {
  howmanyMonth: 0,
  randomArr: function (start, end, length) {
    var result = []
    for (let i = 0; i < length; i++) {
      result.push(Math.floor(Math.random() * (end - start)) + start)
    }
    this.howmanyMonth = 0
    return result
  },
  createDateArr: function (start, end) {

    var result = []
    var currentStart = new Date(start)
    var realStart = currentStart.getTime()
    var currentEnd = new Date(end)
    var realEnd = currentEnd.getTime()
    var between = (realEnd - realStart) / 86400000
    var current = null
    for (let i = 0; i <= between; i++) {
      current = new Date(Number(realStart) + i * 86400000)
      var Y = current.getFullYear()
      var M = current.getMonth() + 1 < 10 ? '0' + (current.getMonth() + 1) : (current.getMonth() + 1)
      var D = current.getDate() < 10 ? '0' + current.getDate() : current.getDate()
      result.push(Y + '-' + M + '-' + D)
    }
    return result
  },
  createMonthArr: function (start, end) {
    var result = []
    var currentStart = new Date(start)
    var realStart = currentStart.getTime()
    var currentEnd = new Date(end)
    var realEnd = currentEnd.getTime()
    var current = realStart

    while(current <= realEnd) {
      this.howmanyMonth ++
      current = new Date(current)
      var Y = current.getFullYear()
      var M = current.getMonth() + 1 < 10 ? '0' + (current.getMonth() + 1) : (current.getMonth() + 1)
      result.push(Y + '-' + M)
      current.setMonth(current.getMonth() + 1)
    }
    return result
  },
  computeLength(start, end) {
    var currentStart = new Date(start)
    var realStart = currentStart.getTime()
    var currentEnd = new Date(end)
    var realEnd = currentEnd.getTime()
    return (realEnd - realStart) / 86400000 + 1
  }
}
// var category = randomData.createMonthArr('Thu Feb 01 2018 00:00:00 GMT+0800 (中国标准时间)','Sun Jul 01 2018 00:00:00 GMT+0800 (中国标准时间)')
//   var data = randomData.randomArr(20, 280,randomData.howmanyMonth)
//   console.log(category)
//   console.log(data)
module.exports = randomData;