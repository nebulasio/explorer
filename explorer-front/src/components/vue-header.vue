<style lang="scss" scoped>
.vue-header {
  // height: 70px;
  .container {
    padding-top: 5px;
    padding-bottom: 5px;
    // background-color: #fff;
    color: #333333;
  }

  a {
    color: #333;
  }

  form {
    width: 350px !important;
  }

  .navbar-nav {
    & > li {
      & > a {
        padding: 0;
      }
    }
  }

  .iconfont {
    font-size: 12px;

    &.icon-menu1 {
      font-size: 24px;
    }
  }
}

.navbar-brand {
  img {
    width: 150px;
    height: auto;
  }

  span {
    color: #4460e7;
    font-size: 12px;
  }
}

.navbar-links {
  justify-content: space-around;
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

// search input

form {
  border: 1px solid #d7d7d7;
  border-radius: 5px;
  margin-left: 20px;
  padding: 6px 0 6px 10px;
  background-color: white;

  &.form-inline {
    align-items: center;
    display: flex;
    width: 100%;
    margin: 10px 0;
    img {
      margin-right: 6px;
    }

    input {
      outline: none;
      border: none;
      flex: 1;
    }
  }
}

input::placeholder {
  font-size: 13px;
  line-height: 20px;
}

// .vue-header .navbar-nav .nav-item:nth-child(2) a img {
//   margin-top: -5px;
// }

// .vue-header .navbar-nav .nav-item:nth-child(3) a img {
//   margin-top: -1.5px;
// }

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

  .vue-header .dropdown-menu {
    padding: 0;
    margin-top: 6px;
    min-width: 15rem;

    &.dropdown-menu-long {
      margin-left: 30px;
      min-width: 45rem;
    }

    .row {
      margin: 0;
      .col {
        padding: 0;
      }
    }

    ul {
      padding: 0;
      & > li {
        list-style-type: none;
        display: flex;
        align-items: baseline;
        margin: 0;
        height: 5rem;
        position: relative;
        width: 15rem;

        &.short {
          height: 3rem;

          a {
            font-weight: normal;
          }
        }

        // border-bottom: 1px solid #e9ecef;

        & > a {
          width: 100%;
          height: 100%;
          justify-content: center;
          font-weight: bold;
          font-size: 15px;

          &:hover {
            background-color: transparent;

            &:active {
              background-color: transparent;
            }
          }

          &:active {
            background-color: transparent !important;
          }

          // &:after {
          //   content: "\003E";
          //   color: #999;
          //   position: absolute;
          //   top: 50%;
          //   right: 1rem;
          //   transform: translateY(-50%);
          // }
        }

        &:hover {
          background-color: #f8f9fa;
        }

        &:active {
          background-color: #e8e8e8;
        }

        // &:not(:last-child) {
        //   border-bottom: 1px solid #e9ecef;
        // }
      }
    }

    .dropdown-item {
      padding: 0 1.5rem;
      display: flex;
      flex-direction: column;
      &:active {
        background-color: #e8e8e8;
      }

      span {
        font-size: 15px;
        font-weight: 400;

        &.title {
          // font-size: 18px;
          font-weight: bold;
        }

        &.desc {
          font-size: 12px;
          color: #999;
          font-weight: normal;
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
  <div>
    <!-- navbar desktop -->
    <nav
      v-if="!$isMobile()"
      class="navbar navbar-light navbar-expand-lg vue-header"
    >
      <div class="container">
        <div class="home">
          <router-link v-bind:to="fragApi + '/'" class="navbar-brand">
            <img class="nas-logo" src="/static/img/nebulas-logo.png" alt />
            <span>Explorer</span>
          </router-link>
        </div>

        <div class="collapse navbar-collapse navbar-links mr-28">
          <ul class="navbar-nav">
            <li
              class="dropdown nav-item"
              v-bind:class="{ active: $route.meta.headerActive == 2 }"
              id="blockchain-menu"
            >
              <a
                class="nav-link"
                href="#"
                role="button"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
              >
                Blockchains <i class="iconfont icon-down"></i>
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
                          v-bind:to="linkUrl('/blocks')"
                        >
                          {{ $t("headerBlocksSubmenu") }}
                        </router-link>
                      </li>
                      <li>
                        <router-link
                          class="dropdown-item"
                          v-bind:to="linkUrl('/txs')"
                        >
                          {{ $t("headerTransactionsSubmenu") }}
                        </router-link>
                      </li>
                      <li>
                        <router-link
                          class="dropdown-item"
                          v-bind:to="linkUrl('/txs/pending')"
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
                          v-bind:to="linkUrl('/accounts')"
                        >
                          {{ $t("headerAccountsSubmenu") }}
                        </router-link>
                      </li>
                      <li>
                        <router-link
                          class="dropdown-item"
                          v-bind:to="'/distribution'"
                        >
                          <span class="title">NAS Distribution</span>
                        </router-link>
                      </li>
                      <li>
                        <router-link
                          class="dropdown-item"
                          v-bind:to="linkUrl('/contracts')"
                        >
                          Smart Contract
                        </router-link>
                      </li>
                    </ul>
                  </b-col>
                  <b-col>
                    <ul>
                      <li>
                        <router-link
                          class="dropdown-item"
                          :to="'/token/n1etmdwczuAUCnMMvpGasfi8kwUbb2ddvRJ'"
                        >
                          NAX
                        </router-link>
                      </li>
                      <li>
                        <router-link class="dropdown-item" :to="'/dstaking'">
                          dStaking
                        </router-link>
                      </li>
                    </ul>
                  </b-col>
                </b-row>
              </div>
            </li>

            <li class="nav-item">
              <a
                class="nav-link"
                href="#"
                role="button"
                v-on:click.prevent="apiSwitch()"
              >
                {{ netStatusText }}
                <i class="iconfont icon-switch"></i>
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
                    <a
                      target="__blank"
                      href="https://explorer.nebulas.io/"
                      class="dropdown-item"
                    >
                      <span class="title">Explorer</span>
                      <span class="desc">Blockchain, dStaking</span>
                    </a>
                  </li>

                  <li>
                    <a
                      target="__blank"
                      href="https://node.nebulas.io/"
                      class="dropdown-item"
                    >
                      <span class="title">Nodes</span>
                      <span class="desc">POD Consensus</span>
                    </a>
                  </li>

                  <li>
                    <a
                      target="__blank"
                      href="https://go.nebulas.io"
                      class="dropdown-item"
                    >
                      <span class="title">Go Nebulas</span>
                      <span class="desc">Community Collaboration</span>
                    </a>
                  </li>

                  <li class="short">
                    <a
                      target="__blank"
                      href="https://nano.nebulas.io"
                      class="dropdown-item"
                    >
                      <span>APP</span>
                    </a>
                  </li>

                  <li class="short">
                    <a
                      target="__blank"
                      href="https://wiki.nebulas.io/"
                      class="dropdown-item"
                    >
                      <span>Documents</span>
                    </a>
                  </li>

                  <li class="short">
                    <a
                      target="__blank"
                      href="https://community.nebulas.io"
                      class="dropdown-item"
                    >
                      <span>Forum</span>
                    </a>
                  </li>

                  <li class="short">
                    <a
                      target="__blank"
                      href="https://nebulas.io"
                      class="dropdown-item"
                    >
                      <span>Nebulas.io</span>
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

    <!-- nav mobile -->
    <nav
      v-if="$isMobile()"
      class="navbar navbar-expand-lg navbar-light bg-light"
    >
      <router-link v-bind:to="fragApi + '/'" class="navbar-brand">
        <img class="nas-logo" src="/static/img/nebulas-logo.png" alt />
        <span>Explorer</span>
      </router-link>

      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdown"
              role="button"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Blockchains
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
              <template v-for="(item, index) in menuItems">
                <router-link
                  class="dropdown-item"
                  v-if="item.url"
                  v-bind:key="index"
                  v-bind:to="item.url"
                >
                  {{ item.text }}
                </router-link>
                <div v-else v-bind:key="index" class="dropdown-divider"></div>
              </template>
            </div>
          </li>
          <li class="dropdown nav-item">
            <LocaleSwitcher />
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
      </div>
    </nav>
  </div>
</template>
<script>
// import { EventBus } from "../events.js";
// import { jsonStrings } from "../l10nstrings.js";
import LocaleSwitcher from "@/components/LocaleSwitcher.vue";
var api = require("@/assets/api");

import { apiPrefixesConfig } from "@/config";
import { netStatus, urlPrefix, baseUrl } from "@/utils/neb";

module.exports = {
  components: {
    LocaleSwitcher
  },
  data() {
    return {
      apiPrefixes: null,
      fragApi: "",
      paramsApi: "",
      // MenuMisc: "MAINNET",
      search: "",
      menuItems: [
        {
          url: this.linkUrl("/blocks"),
          text: this.$t("headerBlocksSubmenu")
        },
        {
          url: this.linkUrl("/txs"),
          text: this.$t("headerTransactionsSubmenu")
        },
        {
          url: this.linkUrl("/txs/pending"),
          text: this.$t("headerPendingTransactionsSubmenu")
        },
        {
          url: ""
        },
        {
          url: this.linkUrl("/accounts"),
          text: this.$t("headerAccountsSubmenu")
        },
        {
          url: "/distribution",
          text: "NAS Distribution"
        },
        {
          url: this.linkUrl("/contracts"),
          text: "Smart Contract"
        },
        {
          url: ""
        },
        {
          url: "/token/n1etmdwczuAUCnMMvpGasfi8kwUbb2ddvRJ",
          text: "NAX"
        },
        {
          url: "/dstaking",
          text: "dStaking"
        }
      ]
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

    // if (!(paramsApi in apiPrefixes)) paramsApi = "";

    this.fragApi = urlPrefix(this.$route);
    this.paramsApi = paramsApi;

    if (!!navigator.userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/)) {
      // avoid auto zoom under iOS Safari when font size is less than 16px
      $(".vue-header input").css("font-size", "16px");
    }
  },

  computed: {
    netStatusText() {
      return netStatus(this.$route);
    }
  },
  methods: {
    linkUrl(url) {
      return baseUrl(this.$route, url);
    },
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
    }
    // atpAddress() {
    //   var api = this.$route.params.api ? this.$route.params.api : "mainnet";
    //   return apiPrefixesConfig[api].atp;
    // },
    // showATP() {
    //   // 搜索框进入 ATP 的临时方案！！！
    //   this.$router.push(
    //     (this.$route.params.api ? "/" + this.$route.params.api : "") +
    //       "/token/" +
    //       this.atpAddress()
    //   );
    // }
  }
};
</script>
