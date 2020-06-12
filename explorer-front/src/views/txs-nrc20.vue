<style>
.vue-bread {
  background-color: #f7f7f7;
  overflow: auto;
  padding: 10px 0;
}
.vue-txs-nrc20 {
  background-color: white;
}
.vue-txs-nrc20 .tip a {
  color: rgb(76, 32, 133);
}

.vue-txs-nrc20 .info-and-pagination .info a {
  color: inherit;
}

.vue-txs-nrc20 td,
.vue-txs-nrc20 th {
  border-top-color: #ddd;
}

.vue-txs-nrc20 .fail {
  background: url(../../static/img/warning_icon.png) no-repeat 0 10px;
  padding-left: 28px;
}

.vue-txs-nrc20 .fail a {
  display: inline-block;
  max-width: 142px;
  overflow: hidden;
  text-overflow: ellipsis;
}

.vue-txs-nrc20 .hash-normal {
  height: 20px;
  font-size: 14px;
  /* font-family: OpenSans; */
  color: rgba(0, 87, 255, 1);
  line-height: 20px;
}

.vue-txs-nrc20 .hash-failed {
  height: 20px;
  font-size: 14px;
  /* font-family: OpenSans; */
  line-height: 20px;
  color: rgba(240, 68, 52, 1);
}

.vue-txs-nrc20 .txs-hash {
  max-width: 185px;
  overflow: hidden;
  text-overflow: ellipsis;
  vertical-align: middle;
}

.vue-txs-nrc20 .txs-block {
  max-width: 120px;
  overflow: hidden;
  text-overflow: ellipsis;
  vertical-align: middle;
}

.vue-txs-nrc20 .fromTo {
  /*max-width: 134px;*/
  height: 20px;
  line-height: 24px;
}

.vue-txs-nrc20 .block {
  margin-right: 8px;
}

@media (max-width: 767.98px) {
  .vue-txs-nrc20 .title {
    font-size: 20px;
  }
}
</style>
<template>
  <!-- https://etherscan.io/txs -->
  <div class="vue-txs-nrc20 ">
    <div class="vue-bread">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-auto bread-title font-40 font-bold font-color-000000">
            {{ $t("nrc20Title") }}
          </div>
          <div
            class="col-auto bread-subtitle font-16 font-bold font-color-000000 align-baseline"
          ></div>
        </div>
      </div>
    </div>

    <div class="container mt20">
      <div class="align-items-center info-and-pagination mt20 row">
        <div class="col info font-color-000000 font-24 font-bold title">
          {{ totalTxs }}
          <span>
            {{ $t("nrc20Subtitle") }}
          </span>
        </div>
      </div>

      <div class="explorer-table-container">
        <table class="mt20 explorer-table list-table">
          <tr
            class="font-12 font-bold font-color-000000"
            style="height: 46px; background-color: #e8e8e8;"
          >
            <th></th>
            <th>{{ $t("nrc20TableTxHash") }}</th>
            <th>{{ $t("nrc20TableBlock") }}</th>
            <th>{{ $t("nrc20TableAge") }}</th>
            <th>{{ $t("nrc20TableFrom") }}</th>
            <th></th>
            <th>{{ $t("nrc20TableTo") }}</th>
            <th class="text-right">
              {{ $t("nrc20TableValue") }}
            </th>
            <th class="text-right pr-3">
              {{ $t("nrc20TableTxFee") }}
            </th>
          </tr>

          <tr v-for="(o, i) in arr" :key="i">
            <td>
              <img
                v-if="o.status === 0"
                class="icon40"
                src="../../static/img/ic_tx_failed.png"
              />
            </td>

            <td class="txs-hash">
              <router-link v-bind:to="fragApi + '/tx/' + o.hash">
                <span
                  v-bind:class="[
                    o.status === 0 ? 'hash-failed' : 'hash-normal',
                    'monospace'
                  ]"
                  >{{ o.hash }}</span
                >
              </router-link>
            </td>

            <td class="txs-block">
              <router-link
                class="font-14"
                v-if="o.block.height"
                v-bind:to="fragApi + '/block/' + o.block.height"
              >
                <span>{{ o.block.height }}</span>
              </router-link>
              <i class="font-14 font-color-000000" v-else>
                {{ $t("nrc20PendingText") }}
              </i>
            </td>

            <td class="time font-14 font-color-555555">
              <div>
                <div>
                  <span> {{ $t("nrc20TimeStampPrefix") }} </span
                  >{{ timeConversion(o.timeDiff)
                  }}<span>
                    {{ $t("nrc20TimeStampSuffix") }}
                  </span>
                </div>
                <div class="down-arrow-tip">
                  {{
                    new Date(o.timestamp)
                      .toString()
                      .replace("GMT", "UTC")
                      .replace(/\(.+\)/gi, "")
                  }}
                  | {{ o.timestamp }}
                </div>
              </div>
            </td>
            <td class="tdxxxwddd txs-from-to">
              <vue-blockies
                v-bind:address="o.from.alias || o.from.hash"
              ></vue-blockies>
              <span
                class="fromTo font-color-000000 font-14"
                v-if="o.from.hash === $route.query.a"
                >{{ o.from.alias || o.from.hash }}</span
              >
              <router-link
                v-else
                v-bind:to="fragApi + '/address/' + o.from.hash"
              >
                <span class="fromTo font-14  monospace">{{ o.from.hash }}</span>
              </router-link>
            </td>
            <td style="padding: 10px;">
              <img class="icon16" src="../../static/img/ic_arrow_right.png" />
            </td>
            <td class="tdxxxwddd txs-from-to">
              <vue-blockies
                v-bind:address="o.to.alias || o.to.hash"
              ></vue-blockies>
              <span
                class="fromTo font-color-000000 font-14"
                v-if="o.to.hash === $route.query.a"
                >{{ o.to.alias || o.to.hash }}</span
              >
              <router-link v-else v-bind:to="fragApi + '/address/' + o.to.hash">
                <span class="fromTo font-14  monospace">{{ o.to.hash }}</span>
              </router-link>
            </td>
            <td class="text-right font-color-000000 font-14">
              {{ tokenAmount(o.value) }} {{ o.tokenName || "" }}
            </td>
            <td class="text-right font-14 font-color-555555 pr-3">
              {{ toWei(o.txFee) }}
            </td>
          </tr>
        </table>
      </div>

      <vue-pagination
        v-bind:current="currentPage"
        right="1"
        v-bind:total="totalPage"
        v-on:first="onFirst"
        v-on:last="onLast"
        v-on:next="onNext"
        v-on:prev="onPrev"
        v-on:to="onTo"
      ></vue-pagination>
    </div>
  </div>
</template>
<script>
// import { EventBus } from "../events.js";
// import { jsonStrings } from "../l10nstrings.js";
var api = require("@/assets/api"),
  utility = require("@/assets/utility"),
  BigNumber = require("bignumber.js");

module.exports = {
  components: {
    "vue-bread": require("@/components/vue-bread").default,
    "vue-pagination": require("@/components/vue-pagination").default,
    "vue-blockies": require("@/components/vue-blockies").default
  },
  data() {
    return {
      arr: [],
      currentPage: 0,
      fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
      maxDisplayCnt: 0,
      totalPage: 0,
      totalTxs: 0
    };
  },
  methods: {
    // removeTempInterval() {
    //   clearInterval(this.tempInterval);
    // },
    // checkStaticTranslations() {
    //   // Unique elements, identified by id attr
    //   var myLocalizableElements = document.getElementsByClassName(
    //     "nrc20localizable"
    //   );
    //   var totalElements = myLocalizableElements.length;
    //   var i;
    //   for (i = 0; i < totalElements; i++) {
    //     var elementId = myLocalizableElements[i].getAttribute("id");
    //     if (myLocalizableElements[i].getAttribute("localize")) {
    //       var elementAttribute = myLocalizableElements[i].getAttribute(
    //         "localize"
    //       );
    //       myLocalizableElements[i].setAttribute(
    //         elementAttribute,
    //         jsonStrings[this.$selectedLanguage][elementId]
    //       );
    //     } else {
    //       myLocalizableElements[i].innerText =
    //         jsonStrings[this.$selectedLanguage][elementId];
    //     }
    //   }
    // },
    // checkDynamicTranslations() {
    //   // Multiple elements, identified with name attr
    //   var myMultiLocalizableElements = document.getElementsByClassName(
    //     "nrc20multilocalizable"
    //   );
    //   var totalElements = myMultiLocalizableElements.length;
    //   var i;
    //   for (i = 0; i < totalElements; i++) {
    //     var elementName = myMultiLocalizableElements[i].getAttribute("name");
    //     if (myMultiLocalizableElements[i].getAttribute("localize")) {
    //       var elementAttribute = myMultiLocalizableElements[i].getAttribute(
    //         "localize"
    //       );
    //       myMultiLocalizableElements[i].setAttribute(
    //         elementAttribute,
    //         jsonStrings[this.$selectedLanguage][elementName]
    //       );
    //     } else {
    //       myMultiLocalizableElements[i].innerText =
    //         jsonStrings[this.$selectedLanguage][elementName];
    //     }
    //   }
    // },
    nav(n) {
      var query = JSON.parse(window.JSON.stringify(this.$route.query));

      query.p = n;
      this.$router.push({ path: this.$route.path, query });
    },
    nthPage() {
      this.$root.showModalLoading = true;

      api.getNrc20Txs(
        this.$route.query.a,
        this.$route.query.p || 1,
        o => {
          this.$root.showModalLoading = false;
          this.arr = o.txnList;
          this.currentPage = o.currentPage;
          this.maxDisplayCnt = o.maxDisplayCnt;
          this.totalPage = o.totalPage;
          this.totalTxs = o.txnCnt;
        },
        xhr => {
          this.$root.showModalLoading = false;
          this.$router.replace(
            (this.$route.params.api ? "/" + this.$route.params.api : "") +
              "/404"
          );
        }
      );
    },
    numberAddComma(n) {
      return utility.numberAddComma(n);
    },
    onFirst() {
      this.nav(1);
    },
    onLast() {
      this.nav(this.totalPage);
    },
    onNext() {
      this.nav(this.currentPage + 1);
    },
    onPrev() {
      this.nav(this.currentPage - 1);
    },
    onTo(n) {
      this.nav(n);
    },
    timeConversion(ms) {
      return utility.timeConversion(ms);
    },
    toWei(n) {
      return utility.toWei(n);
    },
    easyNumber(n) {
      return utility.easyNumber(n);
    },
    tokenAmount(n) {
      BigNumber.config({ DECIMAL_PLACES: 18 });
      var amount = BigNumber(n);
      var decimals = BigNumber("1e+18");
      return amount
        .div(decimals)
        .toFormat()
        .shortAmount();
    }
  },
  mounted() {
    // EventBus.$on("changeLanguage", foo => {
    //   this.checkStaticTranslations();
    // });
    // if (typeof this.$selectedLanguage != "undefined") {
    //   this.checkStaticTranslations();
    // }
    // this.translationsInterval = setInterval(() => {
    //   this.checkDynamicTranslations();
    // }, 1000);
    // this.tempInterval = setInterval(() => {
    //   this.checkStaticTranslations();
    //   this.removeTempInterval();
    // }, 1000);
    this.nthPage();
  },
  watch: {
    $route() {
      this.nthPage();
    }
  }
};
</script>
