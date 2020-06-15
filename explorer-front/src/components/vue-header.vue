<style lang="scss" scoped>
.vue-header .container {
  padding-top: 5px;
  padding-bottom: 5px;
}

.vue-header a {
  color: inherit;
}

.navbar-collapse {
  justify-content: space-around;
}

.vue-header .dev-version {
  color: #2a88ff;
  left: -15px;
  padding: 5px;
  position: relative;
  top: 10px;
}

.vue-header .btn-group-toggle {
  margin-left: 20px;
}

.vue-header .btn-group-toggle label {
  padding: 0 10px;
}

.vue-header .visibility-hidden {
  visibility: hidden;
}

.vue-header form {
  border: none;
  margin-left: 20px;
  padding: 6px 0 6px 10px;
  width: 350px;
  background-color: white;
}

.vue-header .form-inline img {
  margin-right: 6px;
}

.vue-header .form-inline input {
  outline: none;
  border: none;
  flex: 1;
}

.vue-header input::placeholder {
  font-size: 13px;
  line-height: 20px;
}

.vue-header .navbar-nav .nav-item:nth-child(2) a img {
  margin-top: -5px;
}

.vue-header .navbar-nav .nav-item:nth-child(3) a img {
  margin-top: -1.5px;
}

@media (min-width: 992px) {
  .vue-header .navbar-nav > li > a {
    border-bottom: 2px solid transparent;
  }

  /* .vue-header .navbar-nav>li>a:hover, */
  .vue-header .navbar-nav > li.active > a,
  .vue-header .navbar-nav > li.show > a {
    border-bottom-color: white;
  }

  .vue-header .navbar-nav a:hover img {
    opacity: 0.6;
  }

  .vue-header.navbar.navbar-default .navbar-nav > li > a {
    background-color: initial;
    padding: 0;
  }

  .navbar-nav {
    & > li {
      & > a {
        padding: 0;
      }
    }
  }

  .icon-menu1 {
    font-size: 24px;
  }

  .vue-header .dropdown-menu {
    padding: 0;
    margin-top: 6px;
    min-width: 15rem;

    &.dropdown-menu-long {
      margin-left: 30px;
      width: 34rem;
    }

    .row {
      margin: 0;
    }

    ul {
      padding: 1rem 0;
      & > li {
        list-style-type: none;
        display: flex;
        align-items: baseline;
        margin: 0 1rem;
        height: 5rem;
        position: relative;

        & > a {
          position: absolute;
          top: 50%;
          right: 1rem;
          transform: translateY(-50%);

          &:hover {
            background-color: transparent;
          }
        }

        &:hover {
          background-color: #f8f9fa;
        }

        &:active {
          background-color: #e8e8e8;
        }

        &:not(:last-child) {
          border-bottom: 1px solid #e9ecef;
        }

        &:after {
          content: "\003E";
          color: #999;
          position: absolute;
          top: 50%;
          right: 1rem;
          transform: translateY(-50%);
        }
      }
    }

    .dropdown-item {
      padding: 1.5rem;
      display: flex;
      flex-direction: column;
      &:active {
        background-color: #e8e8e8;
      }

      span {
        &.title {
        }

        &.desc {
          font-size: 12px;
          color: #999;
        }
      }
    }
  }
}

@media (max-width: 991.98px) {
  .vue-header .form-inline {
    align-items: center;
    display: flex;
    width: 100%;
    margin: 10px 0;
  }
}

@media (max-width: 767.98px) {
  input {
    font-size: 16px;
  }
}
</style>

<template>
  <nav class="bg-black navbar navbar-expand-lg navbar-dark vue-header">
    <div class="container">
      <div>
        <router-link v-bind:to="fragApi + '/'" class="navbar-brand">
          <img src="/static/img/logo_beta.png?v=20190117" width="210" alt />
        </router-link>
      </div>

      <button
        class="navbar-toggler"
        localize="aria-label"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
        id="headerToggleNavigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse mr-28" id="navbarSupportedContent">
        <ul class="navbar-nav">
          <li
            class="dropdown nav-item"
            v-bind:class="{ active: $route.meta.headerActive == 2 }"
            id="blockchain-menu"
          >
            <a
              class="nav-link"
              href="#"
              id="header-dropdown-blockchain"
              role="button"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Blockchains <img src=/static/img/icon_arrow_down.png width=12
              alt="" >
            </a>
            <div
              class="dropdown-menu dropdown-menu-long"
              aria-labelledby="header-dropdown-blockchain"
            >
              <b-row>
                <b-col>
                  <ul>
                    <li>
                      <router-link
                        class="dropdown-item"
                        v-bind:to="fragApi + '/blocks'"
                      >
                        {{ $t("headerBlocksSubmenu") }}
                      </router-link>
                    </li>
                    <li>
                      <router-link
                        class="dropdown-item"
                        v-bind:to="fragApi + '/txs'"
                      >
                        {{ $t("headerTransactionsSubmenu") }}
                      </router-link>
                    </li>
                    <li>
                      <router-link
                        class="dropdown-item"
                        v-bind:to="fragApi + '/txs/pending'"
                      >
                        {{ $t("headerPendingTransactionsSubmenu") }}
                      </router-link>
                    </li>
                  </ul>
                </b-col>
                <b-col>
                  <ul>
                    <li>
                      <router-link
                        class="dropdown-item"
                        v-bind:to="fragApi + '/accounts'"
                      >
                        {{ $t("headerAccountsSubmenu") }}
                      </router-link>
                    </li>
                    <li>
                      <router-link
                        class="dropdown-item"
                        v-bind:to="fragApi + '/monitor'"
                      >
                        <span class="title">Monitor Address</span>
                        <span class="desc">Hold by Nebulas Foundation</span>
                      </router-link>
                    </li>
                    <li>
                      <router-link
                        class="dropdown-item"
                        v-bind:to="fragApi + '/contracts'"
                      >
                        Smart Contract
                      </router-link>
                    </li>
                  </ul>
                </b-col>
              </b-row>
            </div>
          </li>
          <!-- // Menú Blockchain -->
          <!-- <li v-if="($route.params.api !== 'testnet' && $root.mainnetGotDipWinners) || ($route.params.api == 'testnet' && $root.testnetGotDipWinners)" class=nav-item v-bind:class="{ active: $route.meta.headerActive == 3 }">
						<router-link class=nav-link v-bind:to="fragApi + '/dip-leaderboard'"><span id="headerDipWinners" class="headerlocalizable"></span></router-link>
          </li>-->
          <li class="nav-item">
            <a
              class="nav-link"
              href="#"
              role="button"
              v-on:click.prevent="apiSwitch()"
            >
              {{ MenuMisc }}
              <img src=/static/img/icon_switcher.png width=12 alt="" >
            </a>
          </li>
        </ul>

        <form class="form-inline" v-on:submit.prevent="onSubmit">
          <img src=/static/img/icon_search.png width=16 alt="" />
          <input
            class="mr-sm-2 font-12"
            type="search"
            :placeholder="$t('headerSearchTool')"
            v-model="search"
          />
        </form>

        <ul class="navbar-nav">
          <li class="dropdown nav-item">
            <a
              class="nav-link"
              href="#"
              id="header-dropdown-blockchain"
              role="button"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              <i class="iconfont icon-menu1"></i>
            </a>
            <div class="dropdown-menu">
              <ul>
                <li>
                  <a href="#" class="dropdown-item">
                    <span class="title">Explorer</span>
                    <span class="desc">Blockchain, dStaking</span>
                  </a>
                </li>

                <li>
                  <a href="#" class="dropdown-item">
                    <span class="title">Nodes</span>
                    <span class="desc">POD Consensus</span>
                  </a>
                </li>

                <li>
                  <a href="#" class="dropdown-item">
                    <span class="title">Go Nebulas</span>
                    <span class="desc">Community Collaboration</span>
                  </a>
                </li>

                <li>
                  <a href="#" class="dropdown-item">
                    <span class="title">APP</span>
                  </a>
                </li>

                <li>
                  <a href="#" class="dropdown-item">
                    <span class="title">Documents</span>
                  </a>
                </li>

                <li>
                  <a href="#" class="dropdown-item">
                    <span class="title">Forum</span>
                  </a>
                </li>

                <li>
                  <a href="#" class="dropdown-item">
                    <span class="title">Nebulas.io</span>
                  </a>
                </li>
              </ul>
            </div>
          </li>

          <li class="dropdown nav-item">
            <LocaleSwitcher />
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>
<script>
// import { EventBus } from "../events.js";
// import { jsonStrings } from "../l10nstrings.js";
import LocaleSwitcher from "@/components/LocaleSwitcher.vue";
var api = require("@/assets/api");

import { apiPrefixesConfig } from "@/config";

module.exports = {
  components: {
    LocaleSwitcher
  },
  data() {
    return {
      apiPrefixes: null,
      fragApi: "",
      paramsApi: "",
      MenuMisc: "MAINNET",
      search: ""
    };
  },
  mounted() {
    var paramsApi = this.$route.params.api,
      apiPrefixes = {},
      i,
      first = true;
    for (i in apiPrefixesConfig)
      if (first) {
        apiPrefixes[""] = apiPrefixesConfig[i];
        first = false;
      } else apiPrefixes[i] = apiPrefixesConfig[i];

    if (!(paramsApi in apiPrefixes)) paramsApi = "";

    if (paramsApi === "testnet") {
      this.MenuMisc = "TESTNET";
    } else {
      this.MenuMisc = "MAINNET";
    }

    this.apiPrefixes = apiPrefixes;
    this.fragApi = paramsApi ? "/" + paramsApi : "";
    this.paramsApi = paramsApi;

    if (!!navigator.userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/)) {
      // avoid auto zoom under iOS Safari when font size is less than 16px
      $(".vue-header input").css("font-size", "16px");
    }
  },
  methods: {
    onSubmit() {
      this.$root.showModalLoading = true;
      api.getSearch(
        this.search.trim(),
        o => {
          this.$root.showModalLoading = false;
          this.search = "";

          if (o.type == "block")
            this.$router.push(this.fragApi + "/block/" + o.q);
          else if (o.type == "address")
            this.$router.push(this.fragApi + "/address/" + o.q);
          else if (o.type == "tx")
            this.$router.push(this.fragApi + "/tx/" + o.q);
          else if (o.type == "contract")
            this.$router.push(this.fragApi + "/token/" + o.q);
          else {
            this.$root.search = o.q;
            this.$router.push(
              (this.$route.params.api ? "/" + this.$route.params.api : "") +
                "/nothing"
            );
          }
        },
        () => {
          this.$root.search = this.search;
          this.$root.showModalLoading = false;
          this.search = "";
          this.$router.push(
            (this.$route.params.api ? "/" + this.$route.params.api : "") +
              "/404"
          );
        }
      );
    },
    apiSwitch() {
      if (this.$route.params.api === "testnet") {
        this.$router.replace("/");
      } else {
        this.$router.replace("/testnet");
      }
      location.reload();
    },
    atpAddress() {
      var api = this.$route.params.api ? this.$route.params.api : "mainnet";
      return apiPrefixesConfig[api].atp;
    },
    showATP() {
      // 搜索框进入 ATP 的临时方案！！！
      this.$router.push(
        (this.$route.params.api ? "/" + this.$route.params.api : "") +
          "/token/" +
          this.atpAddress()
      );
    }
  }
};
</script>
