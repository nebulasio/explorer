
var Vue = require("vue").default,
    VueRouter = require("vue-router").default;

require("bootstrap");
require("bootstrap/dist/css/bootstrap.min.css");
require("font-awesome/css/font-awesome.min.css");
require("./index.css");

Vue.config.productionTip = false;
Vue.use(VueRouter);

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
    router: new VueRouter({ routes: require("@/assets/routes") })
});
