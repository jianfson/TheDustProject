var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');
var homeRouter = require('./routes/home');
var historyRouter = require('./routes/history');
var mapRouter = require('./routes/map');
var correction = require('./routes/correction');
var control = require('./routes/control');
var predict = require('./routes/predict')
var contribution = require('./routes/contribution')
var relation = require('./routes/relation')
var effect = require('./routes/effect')


var bodyParser = require('body-parser')

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())


app.use('/', indexRouter);
app.use('/users', usersRouter);
app.use('/database.device', homeRouter)
app.use('/database.dayavg', historyRouter)
app.use('/map', mapRouter)
app.use('/correction', correction)
app.use('/control', control)
app.use('/predict', predict)
app.use('/contribution', contribution)
app.use('/relation', relation)
app.use('/effect', effect)





// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
