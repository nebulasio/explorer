"use strict";

// var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;
// var Neb = require("../dist/neb-node");
//
// var neb = new Neb();
//
// console.log(neb.api.accounts());
// var state = neb.api.getAccountState("8a209cec02cbeab7e2f74ad969d2dfe8dd24416aa65589bf");
// console.log(state);
// var result = neb.admin.unlockAccount("8a209cec02cbeab7e2f74ad969d2dfe8dd24416aa65589bf", "passphrase");
// console.log(result);
// result = neb.api.sendTransaction("8a209cec02cbeab7e2f74ad969d2dfe8dd24416aa65589bf", "22ac3a9a2b1c31b7a9084e46eae16e761f83f02324092b09", neb.nasToBasic(5), parseInt(state.nonce)+1);
// console.log(result);
// state = neb.api.getAccountState("22ac3a9a2b1c31b7a9084e46eae16e761f83f02324092b09");
// console.log(state);

var Account = require("../lib/account");

//var account = Account.NewAccount();
//console.log(account.getPrivateKeyString());
//console.log(account.getPublicKeyString());
//console.log(account.getAddressString());
//console.log(Account.isValidAddress(account.getAddressString()));
// var key = account.toKey("passphrase");
var key='{"version":3,"id":"634ab470-e0dc-48d1-a53a-7d30fcd7d7f3","address":"c5cc5cd8e065e609752c9cb92f7e532dcac0dda3d52978d9","crypto"{"ciphertext":"f1b05e51a0da0b680fe8fd4d9022414396c59b0425e096de86b618758c272a01","cipherparams":{"iv":"e89527859944a8b08bc814f1e7218eb1"},"cipher":"aes-128-ctr","kdf":"scrypt","kdfparams":"dklen":32,"salt":"93ff6fd387a773c6312ddedf822d6050b78d845b6fe37b934c2c368d228fd30d","n":4096,"r":8,"p":1},"mac":"ff38faba4e56a15e01f6b846aefb4e3e6c410c670e88065dc33316e842cff80c","machash":"sha3256"}}';
console.log(JSON.stringify(key));
console.log("********************");
var a1 = new Account();
a1 = a1.fromKey(key, "passphrase");
console.log(a1.getPrivateKeyString());

//var Transaction = require("../lib/transaction");

//var tx = new Transaction(100, account, account, "10", 1);
//tx.signTransaction();
//console.log("hash:" + tx.hash.toString("hex"));
//console.log("sign:" + tx.sign.toString("hex"));
//console.log(JSON.stringify(tx));
//var data = tx.toProto();
//console.log(data);
//tx.fromProto(data);
//console.log(JSON.stringify(tx));
//console.log("address:"+tx.from.getAddressString());



//var cryptoUtils = require("../lib/utils/crypto-utils");
//console.log("black：" + cryptoUtils.sha3("").toString("hex"));
//console.log("Hello, world：" + cryptoUtils.sha3("Hello, world").toString("hex"));
