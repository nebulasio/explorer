<style>
.vue-txs {
  background-color: white;
}
.vue-txs .tip a {
  color: rgb(76, 32, 133);
}

.vue-txs .info-and-pagination .info a {
  color: inherit;
}

.vue-txs td,
.vue-txs th {
  border-top-color: #ddd;
}

.vue-txs .fail {
  background: url(../../static/img/warning_icon.png) no-repeat 0 10px;
  padding-left: 28px;
}

.vue-txs .fail a {
  display: inline-block;
  max-width: 142px;
  overflow: hidden;
  text-overflow: ellipsis;
}

.vue-txs .hash-normal {
  height: 20px;
  font-size: 14px;
  /* font-family: OpenSans; */
  color: rgba(0, 87, 255, 1);
  line-height: 20px;
}

.vue-txs .hash-failed {
  height: 20px;
  font-size: 14px;
  /* font-family: OpenSans; */
  line-height: 20px;
  color: rgba(240, 68, 52, 1);
}

.vue-txs .txs-hash {
  max-width: 185px;
  overflow: hidden;
  text-overflow: ellipsis;
  vertical-align: center;
  padding: 0;
}

.vue-txs .txs-block {
  max-width: 120px;
  overflow: hidden;
  text-overflow: ellipsis;
  vertical-align: center;
  padding: 0;
}

/*.txs-from-to {*/
/*max-width: 168px;*/
/*}*/

/* .txs-from-to a {
		max-width: 158px;
	} */

.vue-txs .fromTo {
  /*max-width: 158px;*/
  height: 20px;
  line-height: 20px;
}

.vue-txs .block {
  margin-right: 8px;
}
</style>
<template>
  <!-- https://etherscan.io/txs -->
  <div class="vue-txs fullfill">
    <vue-bread v-bind:title="transactionsTitle"></vue-bread>

    <div class="container mt20">
      <div class="align-items-center info-and-pagination mt20 row">
        <div class="col info font-color-000000 font-24 font-bold">
          {{ numberAddComma(totalTxs) }}
          <span>
            {{ $t("contracttxsTransactionsFound") }}
          </span>
        </div>
      </div>

      <div class="explorer-table-container">
        <table v-if="arr.length" class="mt20 explorer-table list-table">
          <tr class="list-header font-12 font-bold font-color-000000">
            <th class="pl-2"></th>
            <th>{{ $t("contracttxsTableTxHash") }}</th>
            <th>{{ $t("contracttxsTableBlock") }}</th>
            <th>{{ $t("contracttxsTableAge") }}</th>
            <th>{{ $t("contracttxsTableFrom") }}</th>
            <th></th>
            <th>{{ $t("contracttxsTableTo") }}</th>
            <th class="text-right">
              {{ $t("contracttxsTableValue") }}
            </th>
            <th class="text-right pr-3">
              {{ $t("contracttxsTableTxFee") }}
            </th>
          </tr>

          <tr v-for="(o, i) in arr" :key="i">
            <td class="pl-2">
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
                v-if="o.blockHeight"
                v-bind:to="fragApi + '/block/' + o.blockHeight"
              >
                <span>{{ o.blockHeight }}</span>
              </router-link>
              <i class="font-14 font-color-000000" v-else>
                {{ $t("contracttxsPendingText") }}
              </i>
            </td>
            <td class="time font-14 font-color-555555">
              <div>
                <div>
                  <span> {{ $t("contracttxsTimeStampPrefix") }} </span
                  >{{ timeConversion(o.timeDiff)
                  }}<span>
                    {{ $t("contracttxsTimeStampSuffix") }}
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
              <vue-blockies v-bind:address="o.from"></vue-blockies>
              <router-link v-bind:to="fragApi + '/address/' + o.from">
                <span class="fromTo font-14  monospace">{{ o.from }}</span>
              </router-link>
            </td>
            <td>
              <img class="icon16" src="../../static/img/ic_arrow_right.png" />
            </td>
            <td class="tdxxxwddd txs-from-to">
              <vue-blockies v-bind:address="o.to"></vue-blockies>
              <router-link v-bind:to="fragApi + '/address/' + o.to">
                <span class="fromTo font-14  monospace">{{ o.to }}</span>
              </router-link>
            </td>
            <td class="text-right font-color-000000 font-14">
              {{ tokenAmount(o.contractValue) }} {{ o.tokenName }}
            </td>
            <td class="text-right font-14 font-color-555555 pr-3">
              {{ toWei(o.txFee) }}
            </td>
          </tr>
        </table>
      </div>

      <vue-pagination
        v-if="arr.length"
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
    "vue-blockies": require("@/components/vue-blockies").default,
    "vue-pagination": require("@/components/vue-pagination").default
  },
  data() {
    return {
      arr: [],
      currentPage: 0,
      fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
      maxDisplayCnt: 0,
      totalPage: 0,
      totalTxs: 0,
      tokenName: null,
      decimal: 18
    };
  },
  methods: {
    // removeTempInterval() {
    //   clearInterval(this.tempInterval);
    // },
    // checkStaticTranslations() {
    //   // Unique elements, identified by id attr
    //   var myLocalizableElements = document.getElementsByClassName(
    //     "contracttxslocalizable"
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
    //   // Other specific methods for unique elements.

    //   var foo = this.$route.fullPath.split("/");
    //   if (foo[1] == "contract-txs") {
    //     myLocalizableElements = document.getElementsByClassName("bread-title");

    //     totalElements = myLocalizableElements.length;
    //     for (i = 0; i < totalElements; i++) {
    //       myLocalizableElements[i].innerText =
    //         (this.tokenName ? this.tokenName + " " : "") +
    //         jsonStrings[this.$selectedLanguage]["contracttxsTitle"];
    //     }
    //   }
    // },
    // checkDynamicTranslations() {
    //   // Multiple elements, identified with name attr
    //   var myMultiLocalizableElements = document.getElementsByClassName(
    //     "contracttxsmultilocalizable"
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

      api.getContractTx(
        {
          contract: this.$route.query.contract,
          p: this.$route.query.p || 1,
          isPending: this.$route.query.isPending
        },
        o => {
          this.$root.showModalLoading = false;
          this.arr = o.txnList;
          this.currentPage = o.currentPage;
          this.maxDisplayCnt = o.maxDisplayCnt;
          this.totalPage = o.totalPage;
          this.totalTxs = o.txnCnt;
          this.tokenName = o.tokenName;
          this.decimal = o.decimal;
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
    tokenAmount(n) {
      BigNumber.config({ DECIMAL_PLACES: this.decimal });
      var amount = BigNumber(n);
      var decimals = BigNumber("1e+" + this.decimal);
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
