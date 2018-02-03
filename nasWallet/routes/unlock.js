var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
var Neb = require("../neb/lib/wallet").Neb;
var Account = require("../neb/lib/wallet").Account;
var Transaction = require("../neb/lib/wallet").Transaction;
var neb = new Neb();
neb.setRequest(new Neb.HttpRequest("https://testnet.nebulas.io"));



/*unlock nas walllet*/
router.post('/',function(req, res, next){
	var account= new Account();
	key = req.body.key;
	password = req.body.password;	
	try
	{
		account = account.fromKey(key,password);
		res.json({"address":account.getAddressString(),"PrivateKey":account.getPrivateKey(),"error":'0'});
	}catch(e){
		res.json({"address":e.message,"error":'1'});
	}
	
});


module.exports = router;
