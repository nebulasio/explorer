
module.exports = {
    ajax: ajax,
    ajaxSplitAction: ajaxSplitAction,
    millisecondsToMinutesAndSeconds: millisecondsToMinutesAndSeconds,
    numberAddComma: numberAddComma,
    parseQueryString: parseQueryString,
    q: q,
    randomInt: randomInt,
    shuffle: shuffle,
    timeConversion: timeConversion,
    timeConversionSec: timeConversionSec,
    toWei: toWei,
    ua: ua,
    yyyymmdd: yyyymmdd
};

////////////////////////////////////////////////////////////
//
// 函数

// ajax.all, 重试
function ajax(action, args, done, fail) {
    var a = ajaxSplitAction(action), i,
        method = a[0], url = a[1],
        xhr = new XMLHttpRequest();

    if (method == "get") {
        url += url.indexOf("?") == -1 ? "?" : "&";
        for (i in args) args[i] === undefined || (url += encodeURIComponent(i) + "=" + encodeURIComponent(args[i]) + "&");
        url = url.slice(0, -1);
        args = null;
    }

    xhr.open(method, url);
    xhr.onload = function (e) {
        if (this.status < 300)
            typeof done == "function" && done(this.response, e);
        else
            typeof fail == "function" && fail(e);
    };
    xhr.onabort = xhr.onerror = xhr.ontimeout = fail;

    if (args)
        xhr.setRequestHeader("content-type", "application/json; charset=utf-8");
    else
        args = undefined; // 把 null 之类的统一成 undefined, JSON.stringify(undefined) 不产生字符串

    // wtf - webpack 要求 window.JSON.stringify
    xhr.send(window.JSON.stringify(args));
    return xhr;
}

function ajaxSplitAction(s) {
    var i = s.indexOf(" "), method, url;

    if (i == -1) {
        method = "get";
        url = s;
    } else {
        method = s.slice(0, i).toLowerCase();
        url = s.slice(i + 1);
    }

    return [method, url];
}

function millisecondsToMinutesAndSeconds(ms) {
    var seconds = Math.round(ms / 1000),
        min = ("00" + Math.floor(seconds / 60)).slice(-2),
        sec = ("00" + seconds % 60).slice(-2);

    return min + "'" + sec + '"';
}

// https://stackoverflow.com/questions/2901102/how-to-print-a-number-with-commas-as-thousands-separators-in-javascript
function numberAddComma(n) {
    n = +n || 0;

    var parts = n.toString().split(".");

    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    return parts.join(".");
}

// https://stackoverflow.com/questions/901115/how-can-i-get-query-string-values-in-javascript
// Andy E
function parseQueryString(s) {
    var ret = {}, match, search = /([^&=]+)=?([^&]*)/g;

    while (match = search.exec(s))
        ret[decode(match[1])] = decode(match[2]);

    return ret;
    function decode(s) {
        var pl = /\+/g; // Regex for replacing addition symbol with a space
        return decodeURIComponent(s.replace(pl, " "));
    }
}

function q(elementOrSelector, selectorOrAll, all) {
    if (typeof elementOrSelector == "string")
        return selectorOrAll ? document.querySelectorAll(elementOrSelector) : document.querySelector(elementOrSelector);
    else
        return all ? elementOrSelector.querySelectorAll(selectorOrAll) : elementOrSelector.querySelector(selectorOrAll);
}

// http://stackoverflow.com/questions/1527803/generating-random-numbers-in-javascript-in-a-specific-range
// 修改使结果不包含 max
function randomInt(min, max) {
    return Math.floor(Math.random() * (max - min)) + min;
}

// https://stackoverflow.com/questions/2450954/how-to-randomize-shuffle-a-javascript-array
function shuffle(array) {
    var currentIndex = array.length, temporaryValue, randomIndex;

    // While there remain elements to shuffle...
    while (currentIndex) {

        // Pick a remaining element...
        randomIndex = Math.floor(Math.random() * currentIndex);
        currentIndex -= 1;

        // And swap it with the current element.
        temporaryValue = array[currentIndex];
        array[currentIndex] = array[randomIndex];
        array[randomIndex] = temporaryValue;
    }

    return array;
}

// https://stackoverflow.com/questions/19700283/how-to-convert-time-milliseconds-to-hours-min-sec-format-in-javascript
// Nofi
function timeConversion(millisec) {
    var seconds = (millisec / 1000).toFixed(0),
        minutes = (millisec / (1000 * 60)).toFixed(0),
        hours = (millisec / (1000 * 60 * 60)).toFixed(0),
        days = (millisec / (1000 * 60 * 60 * 24)).toFixed(0),
        years = (millisec / (1000 * 60 * 60 * 24 * 365)).toFixed(0);

    if (seconds < 60)
        return seconds + " Sec";
    else if (minutes < 60)
        return minutes + " Min";
    else if (hours < 24)
        return hours + " Hrs";
    else if (days < 365)
        return days + " Days";
    else
        return years + " Years";
}

function timeConversionSec(millisec) {
    return (millisec / 1000).toFixed(1) + " Sec";
}

function toWei(n) {
    // "kNas", "MNas", "GNas", "TNas", "PNas", "ENas", "ZNas", "YNas"
    var arr = ["Wei", "kWei", "MWei", "GWei", "TWei", "PWei", "Nas"],
        i, len = arr.length - 1;

    for (i = 0, n = +n || 0; i < len && n >= 1000; ++i, n /= 1000);

    n = n.toFixed();
    return (i == len ? numberAddComma(n) : n) + " " + arr[i];
}

// https://stackoverflow.com/questions/9847580/how-to-detect-safari-chrome-ie-firefox-and-opera-browser
function ua() {
    // Opera 8.0+
    var isOpera = (!!window.opr && !!opr.addons) || !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0;

    // Firefox 1.0+
    var isFirefox = typeof InstallTrigger !== 'undefined';

    // Safari 3.0+ "[object HTMLElementConstructor]" 
    var isSafari = /constructor/i.test(window.HTMLElement) || (function (p) { return p.toString() === "[object SafariRemoteNotification]"; })(!window['safari'] || (typeof safari !== 'undefined' && safari.pushNotification));

    // Internet Explorer 6-11
    var isIE = /*@cc_on!@*/false || !!document.documentMode;

    // Edge 20+
    var isEdge = !isIE && !!window.StyleMedia;

    // Chrome 1+
    var isChrome = !!window.chrome && !!window.chrome.webstore;

    // Blink engine detection
    var isBlink = (isChrome || isOpera) && !!window.CSS;

    return {
        isBlink: isBlink,
        isChrome: isChrome,
        isEdge: isEdge,
        isFirefox: isFirefox,
        isIE: isIE,
        isOpera: isOpera,
        isSafari: isSafari
    };
}

// https://stackoverflow.com/questions/3066586/get-string-in-yyyymmdd-format-from-js-date-object
// D-Money, Hero Qu
function yyyymmdd(dateNow) {
    var d = new Date(dateNow);
    return 10000 * d.getFullYear() + 100 * d.getMonth() + 100 + d.getDate();
}
