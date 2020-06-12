<style>
.vue-bread {
  background-color: #f7f7f7;
  overflow: auto;
  padding: 10px 0;
}
.vue-accounts {
  background-color: white;
}

.vue-accounts .block {
  margin-right: 8px;
}

@media (min-width: 576px) {
  .vue-accounts .tdxxxwddd > * {
    max-width: initial;
  }
}

@media (max-width: 767.98px) {
  .vue-accounts .title {
    font-size: 20px;
  }
}
</style>
<template>
  <!-- https://etherscan.io/accounts  -->
  <div class="vue-accounts fullfill">
    <div class="vue-bread">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-auto bread-title font-40 font-bold font-color-000000">
            {{ $t("accountsTitle") }}
          </div>
          <div
            class="col-auto bread-subtitle font-16 font-bold font-color-000000 align-baseline"
          ></div>
        </div>
      </div>
    </div>
    <div v-if="arr && arr.length" class="mt20 container">
      <div class="d-block d-md-flex flex-row align-items-center mt20">
        <div
          class="col-auto pl-0 pr-2 info font-color-000000 font-24 font-bold title"
        >
          {{ numberAddComma(totalAccounts) }}
          <span>
            {{ $t("accountsAmountFound") }}
          </span>
        </div>
        <span
          v-if="totalAccounts > 10000"
          class="col-auto pl-0 font-color-555555 font-16 align-text-bottom subtitle"
          >(<span> {{ $t("accountsShowingLatestFound") }} </span>)</span
        >
      </div>
      <div class="explorer-table-container">
        <table class="mt20 explorer-table list-table">
          <tr class="list-header font-12 font-bold font-color-000000">
            <th style="padding-left: 24px;">
              <span>
                {{ $t("accountsTableRank") }}
              </span>
            </th>
            <th>
              <span>
                {{ $t("accountsTableAddress") }}
              </span>
            </th>
            <th class="text-right">
              <span>
                {{ $t("accountsTableBalance") }}
              </span>
            </th>
            <th class="text-right">
              <span>
                {{ $t("accountsTablePercentage") }}
              </span>
            </th>
            <th class="text-right" style="padding-right: 24px;">
              <span>
                {{ $t("accountsTableTxCount") }}
              </span>
            </th>
          </tr>
          <tr v-for="(o, i) in arr" :key="i" class="font-14">
            <td style="padding-left: 24px;" class="font-color-000000">
              {{ o.rank }}
            </td>
            <td class="tdxxxwddd">
              <vue-blockies v-bind:address="o.hash"></vue-blockies>
              <router-link v-bind:to="fragApi + '/address/' + o.hash">
                <span class="monospace">{{ o.hash }}</span>
              </router-link>
              <span v-show="o.alias"> | {{ o.alias }}</span>
            </td>
            <td class="text-right font-color-555555">
              {{ nasAmount(o.balance) }}
            </td>
            <td class="text-right font-color-555555">
              <span v-if="o.hash === 'n1gczhpkT54RaT4PB55CNoYbqmEQcfo4hqq'">
                -
              </span>
              <span v-else> {{ new Number(o.percentage).toFixed(4) }}% </span>
            </td>
            <td
              class="text-right font-color-555555"
              style="padding-right: 24px;"
            >
              {{ numberAddComma(o.txCnt) }}
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
      totalAccounts: 0,
      totalBalance: 0,
      totalPage: 0
    };
  },
  methods: {
    // removeTempInterval() {
    //   clearInterval(this.tempInterval);
    // },
    // checkStaticTranslations() {
    //   // Unique elements, identified by id attr
    //   var myLocalizableElements = document.getElementsByClassName(
    //     "accountslocalizable"
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
    //     "accountsmultilocalizable"
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
    //   // Other specific methods for unique elements.
    // },
    nthPage() {
      var p = this.$route.query.p || 1;

      if (p == this.currentPage)
        console.log("nthPage - 请求的第", p, "页正是当前页, 忽略此次调用");
      else {
        this.$root.showModalLoading = true;

        api.getAccount(
          p,
          o => {
            this.$root.showModalLoading = false;
            this.arr = o.addressList;
            this.currentPage = o.page;
            this.totalAccounts = o.totalAccountsCnt;
            this.totalBalance = o.totalBalance;
            this.totalPage = o.totalPage;

            if (this.arr.length) {
              this.heightFrom = this.arr[0].height;
              this.heightTo = this.arr[this.arr.length - 1].height;
            } else {
              this.heightFrom = 0;
              this.heightTo = 0;
            }
          },
          xhr => {
            this.$root.showModalLoading = false;
            this.$router.replace(
              (this.$route.params.api ? "/" + this.$route.params.api : "") +
                "/404"
            );
          }
        );
      }
    },
    numberAddComma(n) {
      return utility.numberAddComma(n);
    },
    toWei(n) {
      return utility.toWei(n);
    },
    onFirst() {
      this.$router.push({
        path: this.$route.path,
        query: { p: 1 }
      });
    },
    onLast() {
      this.$router.push({
        path: this.$route.path,
        query: { p: this.totalPage }
      });
    },
    onNext() {
      this.$router.push({
        path: this.$route.path,
        query: { p: this.currentPage + 1 }
      });
    },
    onPrev() {
      this.$router.push({
        path: this.$route.path,
        query: { p: this.currentPage - 1 }
      });
    },
    onTo(n) {
      this.$router.push({
        path: this.$route.path,
        query: { p: n }
      });
    },
    nasAmount(n) {
      BigNumber.config({ DECIMAL_PLACES: 8 });
      var amount = BigNumber(n);
      var decimals = BigNumber("1e+18");
      return (
        amount
          .div(decimals)
          .toFormat()
          .shortAmount() + " NAS"
      );
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
    //   //this.removeTempInterval();
    // }, 1500);
    this.nthPage();
  },
  watch: {
    $route() {
      this.nthPage();
    }
  }
};
</script>
