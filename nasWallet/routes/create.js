var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
var Neb = require("../neb/lib/wallet").Neb;
var Account = require("../neb/lib/wallet").Account;
var Transaction = require("../neb/lib/wallet").Transaction;
var neb = new Neb();
neb.setRequest(new Neb.HttpRequest("https://testnet.nebulas.io"));
/* create new wallet */
router.get('/:password', function(req, res, next) {
	var account = Account.NewAccount();
	password = req.params.password;
	address= account.getAddressString();
	privateKey = account.getPrivateKeyString();
	publicKey = account.getPublicKeyString();
	key = account.toKey(password);
	res_json = {
			"address":address,
			"privateKey":privateKey,
			"publicKey":publicKey,
			"key":key
	};
	res.json(res_json);
});


module.exports = router;
