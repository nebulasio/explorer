require=(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){

"use strict";

var utils = require('./utils/utils.js');

var Admin = function (neb) {
    this._request = neb._request;
};

Admin.prototype.setRequest = function (request) {
    this._request = request;
};

Admin.prototype.newAccount = function (passphrase, callback) {
    var params = { "passphrase": passphrase };
    return this.request("post", "/v1/admin/account/new", params, callback);
};

Admin.prototype.unlockAccount = function (address, passphrase, callback) {
    var params = {
        "address": address,
        "passphrase": passphrase
    };
    return this.request("post", "/v1/admin/account/unlock", params, callback);
};

Admin.prototype.lockAccount = function (address, callback) {
    var params = { "address": address };
    return this.request("post", "/v1/admin/account/lock", params, callback);
};

Admin.prototype.changeNetworkID = function (networkId, callback) {
    var params = { "networkId": networkId };
    return this.request("post", "/v1/admin/changeNetworkID", params, callback);
};

Admin.prototype.signTransaction = function (from, to, value, nonce, gasPrice, gasLimit, contract, candidate, delegate, callback) {
    var params = {
        "from": from,
        "to": to,
        "value": utils.toString(value),
        "nonce": nonce,
        "gasPrice": utils.toString(gasPrice),
        "gasLimit": utils.toString(gasLimit),
        "contract": contract,
        "candidate": candidate,
        "delegate": delegate
    };
    return this.request("post", "/v1/admin/sign", params, callback);
};

Admin.prototype.sendTransactionWithPassphrase = function (from, to, value, nonce, gasPrice, gasLimit, contract, candidate, delegate, passphrase, callback) {
    var tx = {
        "from": from,
        "to": to,
        "value": utils.toString(value),
        "nonce": nonce,
        "gasPrice": utils.toString(gasPrice),
        "gasLimit": utils.toString(gasLimit),
        "contract": contract,
        "candidate": candidate,
        "delegate": delegate
    };
    var params = {
        "transaction": tx,
        "passphrase": passphrase
    };
    return this.request("post", "/v1/admin/transactionWithPassphrase", params, callback);
};

Admin.prototype.getDynasty = function (callback) {
    return this.request("get", "/v1/admin/dynasty", null, callback);
};

Admin.prototype.getDelegateVoters = function (delegatee, callback) {
    var params = { "delegatee": delegatee };
    return this.request("post", "/v1/admin/delegateVoters", params, callback);
};

Admin.prototype.startMine = function (passphrase, callback) {
    var params = { "passphrase": passphrase };
    return this.request("post", "/v1/admin/startMine", params, callback);
};

Admin.prototype.stopMine = function (callback) {
    return this.request("get", "/v1/admin/stopMine", null, callback);
};

Admin.prototype.request = function (method, api, params, callback) {
    if (utils.isFunction(callback)) {
        this._request.asyncRequest(method, api, params, callback);
        return callback;
    } else {
        return this._request.request(method, api, params);
    }
};

module.exports = Admin;

},{"./utils/utils.js":7}],2:[function(require,module,exports){

"use strict";

var utils = require('./utils/utils.js');

var API = function (neb) {
    this._request = neb._request;
};

API.prototype.setRequest = function (request) {
    this._request = request;
};

API.prototype.getNebState = function (callback) {
    return this.request("get", "/v1/user/nebstate", null, callback);
};

API.prototype.nodeInfo = function (callback) {
    return this.request("get", "/v1/user/nodeinfo", null, callback);
};

API.prototype.accounts = function (callback) {
    return this.request("get", "/v1/user/accounts", null, callback);
};

API.prototype.blockDump = function (count, callback) {
    var params = { "count": count };
    return this.request("post", "/v1/user/blockdump", params, callback);
};

API.prototype.getAccountState = function (address, block, callback) {
    var params = { "address": address, "block": block };
    return this.request("post", "/v1/user/accountstate", params, callback);
};

API.prototype.sendTransaction = function (from, to, value, nonce, gasPrice, gasLimit, contract, candidate, delegate, callback) {
    var params = {
        "from": from,
        "to": to,
        "value": utils.toString(value),
        "nonce": nonce,
        "gasPrice": utils.toString(gasPrice),
        "gasLimit": utils.toString(gasLimit),
        "contract": contract,
        "candidate": candidate,
        "delegate": delegate
    };
    return this.request("post", "/v1/user/transaction", params, callback);
};

API.prototype.call = function (from, to, value, nonce, gasPrice, gasLimit, contract, callback) {
    var params = {
        "from": from,
        "to": to,
        "value": utils.toString(value),
        "nonce": nonce,
        "gasPrice": utils.toString(gasPrice),
        "gasLimit": utils.toString(gasLimit),
        "contract": contract
    };
    return this.request("post", "/v1/user/call", params, callback);
};

API.prototype.sendRawTransaction = function (data, callback) {
    var params = { "data": data };
    return this.request("post", "/v1/user/rawtransaction", params, callback);
};

API.prototype.getBlockByHash = function (hash, callback) {
    var params = { "hash": hash };
    return this.request("post", "/v1/user/getBlockByHash", params, callback);
};

API.prototype.getTransactionReceipt = function (hash, callback) {
    var params = { "hash": hash };
    return this.request("post", "/v1/user/getTransactionReceipt", params, callback);
};

API.prototype.subscribe = function (topic, callback) {
    var params = { "topic": topic };
    return this.request("post", "/v1/user/subscribe", params, callback);
};

API.prototype.gasPrice = function (callback) {
    return this.request("get", "/v1/user/getGasPrice", null, callback);
};

API.prototype.estimateGas = function (from, to, value, nonce, gasPrice, gasLimit, contract, candidate, delegate, callback) {
    var params = {
        "from": from,
        "to": to,
        "value": utils.toString(value),
        "nonce": nonce,
        "gasPrice": utils.toString(gasPrice),
        "gasLimit": utils.toString(gasLimit),
        "contract": contract,
        "candidate": candidate,
        "delegate": delegate
    };
    return this.request("post", "/v1/user/estimateGas", params, callback);
};

API.prototype.getEventsByHash = function (hash, callback) {
    var params = { "hash": hash };
    return this.request("post", "/v1/user/getEventsByHash", params, callback);
};

API.prototype.request = function (method, api, params, callback) {
    if (utils.isFunction(callback)) {
        this._request.asyncRequest(method, api, params, callback);
        return callback;
    } else {
        return this._request.request(method, api, params);
    }
};

module.exports = API;

},{"./utils/utils.js":7}],3:[function(require,module,exports){

"use strict";

var XMLHttpRequest;

// browser
if (typeof window !== "undefined" && window.XMLHttpRequest) {
	XMLHttpRequest = window.XMLHttpRequest;
	// node
} else {
	XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;
	// XMLHttpRequest = require('xhr2');
}

var HttpRequest = function (host, timeout) {
	this.host = host || "http://localhost:8685";
	this.timeout = timeout || 0;
};

HttpRequest.prototype.setHost = function (host) {
	this.host = host || "http://localhost:8685";
};

HttpRequest.prototype._newRequest = function (method, api, async) {
	var request = new XMLHttpRequest();
	var m = "GET";
	if (method.toUpperCase() === "POST") {
		m = "POST";
	}
	var url = this.host + api;
	request.open(m, url, async);
	return request;
};

HttpRequest.prototype.request = function (method, api, payload) {
	var request = this._newRequest(method, api, false);
	try {
		if (payload === undefined || payload === "") {
			request.send();
		} else {
			request.send(JSON.stringify(payload));
		}
	} catch (error) {
		throw error;
	}

	var result = request.responseText;
	try {
		result = JSON.parse(result);
	} catch (e) {
		throw e;
	}

	return result;
};

HttpRequest.prototype.asyncRequest = function (method, api, payload, callback) {
	var request = this._newRequest(method, api, true);
	request.onreadystatechange = function () {
		if (request.readyState === 4 && request.timeout !== 1) {
			var result = request.responseText;
			var error = null;

			try {
				result = JSON.parse(result);
			} catch (e) {
				var message = !!result && !!result.error && !!result.error.message ? result.error.message : "Invalid response: " + JSON.stringify(result);
				error = new Error(message);
			}

			callback(error, result);
		}
	};

	request.ontimeout = function () {
		callback(new Error("connection timeout"));
	};

	try {
		if (payload === undefined || payload === "") {
			request.send();
		} else {
			request.send(JSON.stringify(payload));
		}
	} catch (error) {
		callback(error);
	}
};

module.exports = HttpRequest;

},{"xmlhttprequest":5}],4:[function(require,module,exports){

"use strict";

var HttpRequest = require("./httprequest.js");

var API = require("./api.js");
var Admin = require("./admin.js");

var Unit = require("./utils/unit.js");

var Neb = function (request) {
	if (request) {
		this._request = request;
	} else {
		this._request = new HttpRequest();
	}

	this.api = new API(this);
	this.admin = new Admin(this);
};

Neb.prototype.setRequest = function (request) {
	this._request = request;
	this.api.setRequest(request);
	this.admin.setRequest(request);
};

Neb.HttpRequest = HttpRequest;

Neb.prototype.toBasic = Unit.toBasic;
Neb.prototype.fromBasic = Unit.fromBasic;
Neb.prototype.nasToBasic = Unit.nasToBasic;

module.exports = Neb;

},{"./admin.js":1,"./api.js":2,"./httprequest.js":3,"./utils/unit.js":6}],5:[function(require,module,exports){
"use strict";

if (typeof XMLHttpRequest === "undefined") {
    exports.XMLHttpRequest = {};
} else {
    exports.XMLHttpRequest = XMLHttpRequest; // jshint ignore:line
}


},{}],6:[function(require,module,exports){

"use strict";

var BigNumber = require('bignumber.js');
var utils = require('./utils.js');

var unitMap = {
    'none':       '0',
    'nis':        '1',
    'knis':       '1000',
    'mnis':       '1000000',
    'nanonas':    '1000000000',
    'micronas':   '1000000000000',
    'millinas':   '1000000000000000',
    'nas':        '1000000000000000000',
 };

var unitValue = function (unit) {
	unit = unit ? unit.toLowerCase() : 'nas';
    var unitValue = unitMap[unit];
    if (unitValue === undefined) {
        throw new Error('The unit undefined, please use the following units:' + JSON.stringify(unitMap, null, 2));
    }
    return new BigNumber(unitValue, 10);
};

var toBasic = function (number, unit) {
	return utils.toBigNumber(number).times(unitValue(unit));
};

var fromBasic = function (number, unit) {
	return utils.toBigNumber(number).dividedBy(unitValue(unit));
};

var nasToBasic = function (number) {
	return utils.toBigNumber(number).times(unitValue("nas"));
};

module.exports = {
	toBasic: toBasic,
	fromBasic: fromBasic,
	nasToBasic: nasToBasic
};

},{"./utils.js":7,"bignumber.js":"bignumber.js"}],7:[function(require,module,exports){

"use strict";

var BigNumber = require('bignumber.js');

var isNull = function (v) {
    return v === null || typeof v === "undefined";
};

var isBrowser = function () {
    return (typeof window !== "undefined");
};

var isBigNumber = function (obj) {
    return obj instanceof BigNumber ||
        (obj && obj.constructor && obj.constructor.name === 'BigNumber');
};

var isString = function (obj) {
    return typeof obj === 'string' && obj.constructor === String;
};

var isObject = function (obj) {
    return obj !== null && typeof obj === 'object';
};

var isFunction = function (object) {
    return typeof object === 'function';
};

var toBigNumber = function (number) {
	number = number || 0;
	if (isBigNumber(number)) {
		return number;
	}
	if (isString(number) && number.indexOf('0x') === 0) {
        return new BigNumber(number.replace('0x',''), 16);
    }
    return new BigNumber(number.toString(10), 10);
};

var toString = function (obj) {
	if (isString(obj)) {
		return obj;
	} else if (isBigNumber(obj)) {
		return obj.toString(10);
	} else if (isObject(obj)) {
		return JSON.stringify(obj);
	} else {
		return obj + "";
	}
};

module.exports = {
    isNull: isNull,
    isBrowser: isBrowser,
	isBigNumber: isBigNumber,
	isString: isString,
	isObject: isObject,
    isFunction: isFunction,
	toBigNumber: toBigNumber,
	toString: toString
};

},{"bignumber.js":"bignumber.js"}],8:[function(require,module,exports){

},{}],"bignumber.js":[function(require,module,exports){
'use strict';

module.exports = BigNumber; // jshint ignore:line


},{}],"neb":[function(require,module,exports){
var Neb = require('./lib/neb');

// dont override global variable
if (typeof window !== 'undefined' && typeof window.Neb === 'undefined') {
    window.Neb = Neb;
}

module.exports = Neb;

},{"./lib/neb":4}]},{},["neb"])
//# sourceMappingURL=neb-light.js.map
