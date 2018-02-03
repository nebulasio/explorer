
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
        vueFooter: require("@/components/vue-footer").default,
        vueHeader: require("@/components/vue-header").default
    },
    data: {
        search: ""
    },
    el: ".vue",
    router: new VueRouter({ routes: require("@/assets/routes") })
});
