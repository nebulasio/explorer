import Vue from "vue";
// import VueRouter from "vue-router";
import VueI18n from "vue-i18n";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import { i18n } from "./i18n";
import router from "./router";
import { Trans } from "./plugins/Translation";
import App from "./App.vue";
import api from "./api";
import VueMobileDetection from "vue-mobile-detection";

Vue.prototype.$i18nRoute = Trans.i18nRoute.bind(Trans);
Vue.config.productionTip = false;

// 将api挂载到vue的原型上
Vue.prototype.$api = api;

// Expose jQuery to the global object
const jQuery = require("jquery");
window.jQuery = window.$ = jQuery;

// import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import "font-awesome/css/font-awesome.min.css";

import "./scss/style.scss";

// Install BootstrapVue
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

// detect mobile view
Vue.use(VueMobileDetection);

// Install vue i18n
Vue.use(VueI18n);

function isIE() {
  if (!!window.ActiveXObject || "ActiveXObject" in window) return true;
  else return false;
}
window.isIE = isIE;

const isProd = process.env.NODE_ENV === "production";
const VueAnalytics = require("vue-analytics").default;
Vue.use(VueAnalytics, {
  id: "UA-101203737-4",
  customResourceURL: "https://www.google-analytics.com/analytics.js",
  debug: {
    enabled: !isProd,
    sendHitTask: isProd
  }
});

Number.prototype.pad = function(size) {
  var s = String(this);
  while (s.length < (size || 2)) {
    s = "0" + s;
  }
  return s;
};

String.prototype.shortAmount = function() {
  let dot_index = this.indexOf(".");
  if (dot_index === -1) return this + ".0000";
  if (this.length - 1 - dot_index > 4) {
    return this.slice(0, dot_index + 4 + 1);
  } else if (this.length - 1 - dot_index < 4) {
    return this.padEnd(5 + dot_index, "0");
  }
  return this;
};

String.prototype.padDecimal = function() {
  let dot_index = this.indexOf(".");
  if (dot_index === -1) return this + ".0000";
  if (this.length - 1 - dot_index > 4) {
    return this;
  } else if (this.length - 1 - dot_index < 4) {
    return this.padEnd(5 + dot_index, "0");
  }
  return this;
};

String.prototype.shortHash = function() {
  if (this.length > 12) {
    return this.slice(0, 6) + "..." + this.slice(-6);
  }
  return this;
};

Date.prototype.getWeekNumber = function() {
  var d = new Date(
    Date.UTC(this.getFullYear(), this.getMonth(), this.getDate())
  );
  var dayNum = d.getUTCDay() || 7;
  d.setUTCDate(d.getUTCDate() + 4 - dayNum);
  var yearStart = new Date(Date.UTC(d.getUTCFullYear(), 0, 1));
  return Math.ceil(((d - yearStart) / 86400000 + 1) / 7);
};

new Vue({
  data: {
    showModalLoading: false
  },
  i18n,
  router,
  render: h => h(App)
}).$mount("#app");
