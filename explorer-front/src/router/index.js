import Vue from "vue";
import VueRouter from "vue-router";
// import { Trans } from "@/plugins/Translation";
import routes from "./routes";

import { page } from "vue-analytics";
import { apiPrefixesConfig } from "@/config";

// function load(component) {
//   // '@' is aliased to src/components
//   return () => import(`@/views/${component}.vue`);
// }

Vue.use(VueRouter);

// const routes = [
//   {
//     path: "/:locale",
//     component: {
//       template: "<router-view></router-view>"
//     },
//     beforeEnter: Trans.routeMiddleware,
//     children: [
//       {
//         path: "",
//         name: "Home",
//         component: load("Home")
//       },
//       {
//         path: "about",
//         name: "About",
//         component: load("About")
//       }
//     ]
//   },
//   {
//     path: "*",
//     redirect() {
//       return Trans.defaultLocale;
//     }
//   }
// ];

const router = new VueRouter({
  routes
});

function onBeforeEach(to, from, next) {
  window.scrollTo(0, 0);

  // if vue instance already initial
  if (
    window.hasOwnProperty("$root") &&
    window.$root.hasOwnProperty("showModalLoading")
  ) {
    window.$root.showModalLoading = false;
  }

  var apiPrefix, first, path;

  for (first in apiPrefixesConfig) break;

  if (to.name == "*") {
    path = (from.params.api ? "/" + from.params.api : "") + "/404";
  } else if (to.params.api) {
    if (to.params.api in apiPrefixesConfig) {
      if (to.params.api == first) {
        // mainnet/xxx -> /xxx
        to.params.api = undefined;
        path = vRouter.resolve({ params: to.params }, to).resolved.fullPath;
      } else {
        apiPrefix = apiPrefixesConfig[to.params.api].url;
      }
    } else {
      path = (from.params.api ? "/" + from.params.api : "") + "/404";
    }
  } else {
    apiPrefix = apiPrefixesConfig[first].url;
  }

  sessionStorage.apiPrefix = apiPrefix;
  next(path);
}

function onAfterEach(to, from) {
  if (to.meta && to.meta.uaview) {
    page(to.meta.uaview);
  }
}

router.beforeEach(onBeforeEach);
router.afterEach(onAfterEach);

export default router;
