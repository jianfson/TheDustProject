
var randomData = {
  randomArr: function (start, end, length) {
    var result = []
    for (let i = 0; i < length; i++) {
      result.push(Math.floor(Math.random() * (end - start)) + start)
    }
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
  computeLength(start, end) {
    var currentStart = new Date(start)
    var realStart = currentStart.getTime()
    var currentEnd = new Date(end)
    var realEnd = currentEnd.getTime()
    return (realEnd - realStart) / 86400000 + 1
  }
}

module.exports = randomData;