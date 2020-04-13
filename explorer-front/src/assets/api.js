var {ajax, ajaxSplitAction, getNebulasNetHost} = require("@/assets/utility");

module.exports = {
    // get api/account?
    // - p      - 页码, 默认 1
    getAccount(p, done, fail) {

        var host = "http://localhost:8000";
        ajax("GET " + host + "/holders", {page: p, page_size: 25}, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    // get api/address?
    // - p      - 页码, 默认 1
    // - part   - mine
    // get api/address/
    // - <hash>
    getAddress(t, done, fail) {
        var host = "http://localhost:8000";
        ajax("GET " + host + "/explorer/address", t, d, fail);

        // wtf - webpack 对 if (typeof t == "object") 报异常
        // if (eval('typeof t == "object"')) ajax1("address", t, d, fail);
        // else ajax1("address/" + t, null, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },
    getTransactionByContract(t, netname, done, fail) {
        var host = getNebulasNetHost(netname);
        ajax("POST " + host + "/user/getTransactionByContract", t, done, fail);
    },

    // get api/block?
    // - p      - 页码, 默认 1
    // - m      - miner hash
    // - type   - 目前只有 latest, newblock
    // get api/block/
    // - <id or hash>
    getBlock(t, done, fail) {
        // var host = "http://54.184.64.237:8000";
        var host = "http://localhost:8000";
        ajax("GET " + host + "/block/detail", t, d, fail);

        // wtf - webpack 对 if (typeof t == "object") 报异常
        // if (eval('typeof t == "object"')) ajax1("block", t, d, fail);
        // else ajax1("block/" + t, null, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    getLatestBlockHeight(t, done, fail) {
        // var host = "http://54.184.64.237:8000";
        var host = "http://localhost:8000";
        ajax("GET " + host + "/block/max", t, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    getBlocks(t, done, fail) {
        // var host = "http://54.184.64.237:8000";
        var host = "http://localhost:8000";
        ajax("GET " + host + "/explorer/block/list", t, d, fail);

        // // wtf - webpack 对 if (typeof t == "object") 报异常
        // if (eval('typeof t == "object"')) ajax1("blocks", t, d, fail);
        // else ajax1("blocks/" + t, null, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    // get api/market_cap
    getMarketCap(done, fail) {
        ajax1(
            "market_cap",
            null,
            function (s, xhr) {
                var o = JSON.parse(s);

                if (o.code == 0) done(o.data);
                else if (typeof fail == "function") fail(xhr);
            },
            fail
        );
    },

    getSearch(q, done, fail) {

        var host = "http://localhost:8000";
        ajax("GET " + host + "/explorer/search", {q: q}, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },


    getTxDailyCount(done, fail) {
        var host = "http://localhost:8000";
        ajax("GET " + host + "/explorer/tx/count", null, d, fail);
        // wtf - webpack 对 if (typeof t == "object") 报异常
        // if (eval('typeof t == "object"')) ajax1("tx", t, d, fail);
        // else ajax1("tx/" + t, null, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    getTx(t, done, fail) {
        var host = "http://localhost:8000";
        ajax("GET " + host + "/explorer/tx/detail", t, d, fail);
        // wtf - webpack 对 if (typeof t == "object") 报异常
        // if (eval('typeof t == "object"')) ajax1("tx", t, d, fail);
        // else ajax1("tx/" + t, null, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    getTxLatest(done, fail) {
        var host = "http://localhost:8000";
        ajax("GET " + host + "/explorer/tx/latest", null, d, fail);
        // wtf - webpack 对 if (typeof t == "object") 报异常
        // if (eval('typeof t == "object"')) ajax1("tx", t, d, fail);
        // else ajax1("tx/" + t, null, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    getPendingTxList(t, done, fail) {
        // var host = "http://54.184.64.237:8000";
        var host = "http://localhost:8000";
        ajax("GET " + host + "/tx/list/pending", t, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    getTxList(t, done, fail) {
        // var host = "http://54.184.64.237:8000";
        var host = "http://localhost:8000";
        var path = "/tx/list";
        if (t.address) {
            path = "/tx/listByAddress"
        }
        ajax("GET " + host + path, t, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    // get api/contract?
    // - c      - contract address
    getContract(c, done, fail) {
        var host = "http://localhost:8000";
        ajax("GET " + host + "/explorer/contract", c, d, fail);

        // if (eval('typeof t == "object"')) ajax1("contract", c, d, fail);
        // else ajax1("contract/" + c, null, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    // get api/contract/tx?
    // - contract   - contract address
    // - isPending  - 默认 false
    // - p          - 页码, 默认 1
    getContractTx(t, done, fail) {
        var host = "http://localhost:8000";
        ajax("GET " + host + "/token/tx/list", t, d, fail);

        // if (eval('typeof t == "object"')) ajax1("contract/tx", t, d, fail);
        // else ajax1("contract/tx/" + t, null, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    // get api/contract/holders?
    // - contract   - contract address
    // - p          - 页码, 默认 1
    getContractHolders(t, done, fail) {
        var host = "http://localhost:8000";
        ajax("GET " + host + "/explorer/token/holders", t, d, fail);
        // if (eval('typeof t == "object"')) ajax1("contract/holders", t, d, fail);
        // else ajax1("contract/holders/" + t, null, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    // get address/nrc20/{hash}/{page}
    // - address   - address
    // - page   - 页码, 默认 1
    getNrc20Txs(address, page, done, fail) {
        var host = "http://localhost:8000";
        ajax("GET " + host + "/tx/nrc20", {address: address, page: page, page_size: 25}, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    // get nat/list
    // - address   - address
    // - page   - 页码, 默认 1
    getNatChanges(address, page, size, done, fail) {
        ajax1(
            "nat/list?address=" +
            address +
            "&page=" +
            page +
            "&pageSize=" +
            size,
            null,
            d,
            fail
        );

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    // get nax/list
    // - address   - address
    // - page   - 页码, 默认 1
    getNaxChanges(address, page, size, done, fail) {
        ajax1(
            "nax/list?address=" +
            address +
            "&page=" +
            page +
            "&pageSize=" +
            size,
            null,
            d,
            fail
        );

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    getTodayTxCnt(done, fail) {
        var host = "http://localhost:8000";
        ajax("GET " + host + "/tx/count/today", null, d, fail);

        // ajax1("tx/cnt_today", null, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    getStaticInfo(done, fail) {
        var host = "http://localhost:8000";
        ajax("GET " + host + "/explorer/nasinfo", null, d, fail);

        // ajax1("nasinfo", null, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    getContracts(t, done, fail) {
        var host = "http://localhost:8000";
        ajax("GET " + host + "/contracts", t, d, fail);

        // ajax1("contracts", t, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    getDipList(t, done, fail) {
        ajax1("dip/list", t, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    getDstakingSummary(t, done, fail) {
        var host = "http://localhost:8000";
        ajax("GET " + host + "/nax/summary", t, d, fail);

        // ajax1("dstaking/summary", t, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    },

    getDstakingHistory(t, done, fail) {
        var host = "http://localhost:8000";
        ajax("GET " + host + "/nax/history", t, d, fail);

        // ajax1("dstaking/history", t, d, fail);

        function d(s, xhr) {
            var o = JSON.parse(s);

            if (o.code == 0) done(o.data);
            else if (typeof fail == "function") fail(xhr);
        }
    }
};

function ajax1(action, args, done, fail) {
    var a = ajaxSplitAction(action);

    return ajax(a[0] + " " + sessionStorage.apiPrefix + a[1], args, done, fail);
}
