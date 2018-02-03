var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index');
});
router.get('/index.html', function(req, res, next) {
	  res.render('index');
	});

/*send nas*/
router.get('/sendNas.html',function(req, res, next){
	res.render('sendNas');
});


module.exports = router;
