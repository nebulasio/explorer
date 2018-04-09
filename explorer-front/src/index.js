
var Vue = require("vue").default,
    VueRouter = require("vue-router").default,
    vAppConfig = require("@/assets/app-config"),
    // temporary - use history for now
    vRouter = new VueRouter({ mode: "history", routes: require("@/assets/routes") });

require("bootstrap");
require("bootstrap/dist/css/bootstrap.min.css");
require("font-awesome/css/font-awesome.min.css");
require("./index.css");

Vue.config.productionTip = false;
Vue.use(VueRouter);
vRouter.beforeEach(onBeforeEach);

new Vue({
    components: {
        "vue-footer": require("@/components/vue-footer").default,
        "vue-header": require("@/components/vue-header").default,
        "vue-modal": require("@/components/vue-modal").default
    },
    data: {
        search: "",
        showModalLoading: false
    },
    el: ".vue",
    router: vRouter
});

////////////////////////////////////////////////////////////
//
// api prefix

function onBeforeEach(to, from, next) {
    var path, i;

    if (to.name == 404) {
        if (to.path == "/")
            for (i in vAppConfig.apiPrefixes) {
                path = "/" + i + "/home";
                break;
            }
    } else {
        if (to.params.api in vAppConfig.apiPrefixes)
            sessionStorage.apiPrefix = vAppConfig.apiPrefixes[to.params.api].url;
        else
            path = "/404";
    }

    next(path);
}
