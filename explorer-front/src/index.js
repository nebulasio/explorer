var Vue = require("vue").default,
    VueRouter = require("vue-router").default,
    vApp = {},
    vAppConfig = require("@/assets/app-config"),
    vRouter = new VueRouter({ routes: require("@/assets/routes") }),
    gaPage = require('vue-analytics').page;

// Expose jQuery to the global object
const jQuery = require('jquery');
window.jQuery = window.$ = jQuery;

require("bootstrap");
require("bootstrap/dist/css/bootstrap.min.css");
require("font-awesome/css/font-awesome.min.css");
require("./index.css");

function isIE() {
    if (!!window.ActiveXObject || "ActiveXObject" in window)
        return true;
    else
        return false;
}
window.isIE = isIE;

const isProd = process.env.NODE_ENV === 'production';
const VueAnalytics = require('vue-analytics').default;
Vue.use(VueAnalytics, {
    id: 'UA-101203737-1',
    debug: {
        enabled: !isProd,
        sendHitTask: isProd
    }
});

Vue.config.productionTip = false;
Vue.use(VueRouter);
vRouter.beforeEach(onBeforeEach);
vRouter.afterEach(onAfterEach);

Number.prototype.pad = function (size) {
    var s = String(this);
    while (s.length < (size || 2)) { s = "0" + s; }
    return s;
}

String.prototype.shortAmount = function () {
    let dot_index = this.indexOf('.');
    if (dot_index === -1) return this;
    if (this.length - 1 - dot_index > 4) {
        return this.slice(0, dot_index + 4 + 1);
    }
    return this;
}

vApp = new Vue({
    components: {
        //"vue-popmsg": require("@/components/vue-popmsg").default,
        "vue-footer": require("@/components/vue-footer").default,
        "vue-header": require("@/components/vue-header").default,
        "vue-modal": require("@/components/vue-modal").default
    },
    data: {
        search: "",
        showModalLoading: false,
        showAtpAds: true
    },
    el: ".vue",
    router: vRouter
});

////////////////////////////////////////////////////////////
//
// api prefix

function onBeforeEach(to, from, next) {
    vApp.showModalLoading = false;

    var apiPrefix, first, path;

    for (first in vAppConfig.apiPrefixes) break;

    if (to.name == "*") {
        path = (from.params.api ? "/" + from.params.api : "") + "/404";
    } else if (to.params.api)
        if (to.params.api in vAppConfig.apiPrefixes)
            if (to.params.api == first) {
                // mainnet/xxx -> /xxx
                to.params.api = undefined;
                path = vRouter.resolve({ params: to.params }, to).resolved.fullPath;
            } else
                apiPrefix = vAppConfig.apiPrefixes[to.params.api].url;
        else {
            path = (from.params.api ? "/" + from.params.api : "") + "/404";
        }
    else {
        apiPrefix = vAppConfig.apiPrefixes[first].url;
    }

    sessionStorage.apiPrefix = apiPrefix;
    next(path);
}

function onAfterEach(to, from) {
    if (to.meta && to.meta.uaview) {
        gaPage(to.meta.uaview);
    }
}
