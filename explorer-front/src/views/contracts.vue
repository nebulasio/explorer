<style>
.vue-bread {
  background-color: #f7f7f7;
  overflow: auto;
  padding: 10px 0;
}
.vue-contracts {
  background-color: white;
}

.vue-contracts .info-and-pagination .info a {
  color: inherit;
}

.vue-contracts td,
.vue-contracts th {
  border-top-color: #ddd;
}

.vue-contracts .hash {
  max-width: 200px;
  overflow: hidden;
  text-overflow: ellipsis;
  vertical-align: center;
  white-space: nowrap;
}

.vue-contracts .hash > * {
  display: inline;
}

.vue-contracts .block {
  margin-right: 8px;
}

@media (max-width: 767.98px) {
  .vue-contracts .title {
    font-size: 20px;
  }
}
</style>
<template>
  <!-- https://etherscan.io/txs -->
  <div class="vue-contracts ">
    <div class="vue-bread">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-auto bread-title font-40 font-bold font-color-000000">
            {{ $t("contractsTitle") }}
          </div>
          <div
            class="col-auto bread-subtitle font-16 font-bold font-color-000000 align-baseline"
          ></div>
        </div>
      </div>
    </div>

    <div v-if="arr" class="container mt20">
      <div class="align-items-center info-and-pagination mt20 row">
        <div class="col info font-color-000000 font-24 font-bold title">
          {{ numberAddComma(totalCts) }}
          <span>
            {{ $t("contractslocalizable") }}
          </span>
        </div>
        <!--(showing the last {{ maxDisplayCnt }} records)-->
      </div>

      <div class="explorer-table-container font-14">
        <table class="mt20 explorer-table list-table">
          <tr class="list-header font-12 font-bold font-color-000000">
            <th style="padding-left: 24px;">
              {{ $t("contractsTableSmartContracts") }}
            </th>
            <th v-if="$route.params.api === 'testnet'">
              {{ $t("contractsTableCreatorAddress") }}
            </th>
            <th>Type</th>
            <th class="text-right" style="padding-right: 24px; width: 120px">
              {{ $t("contractsTableDateCreated") }}
            </th>
          </tr>

          <tr v-for="(o, i) in arr" :key="i">
            <td style="padding-left: 24px;" class="hash">
              <vue-blockies v-bind:address="o.hash"></vue-blockies>
              <router-link v-bind:to="fragApi + '/address/' + o.hash">
                <span class="hash-normal monospace">{{ o.hash }}</span>
              </router-link>
            </td>
            <td class="hash" v-if="$route.params.api === 'testnet'">
              <vue-blockies v-bind:address="o.creator"></vue-blockies>
              <router-link v-bind:to="fragApi + '/address/' + o.hash">
                <span class="hash-normal monospace">{{ o.creator }}</span>
              </router-link>
            </td>
            <td class="font-color-000000">
              <span v-if="o.contractType === 'NORMAL'">
                {{ $t("contractsTypeContract") }} </span
              ><span v-else>
                {{ $t("contractsTypeTokenContract") }}
              </span>
            </td>
            <td
              class="text-right font-color-555555"
              style="padding-right: 24px;"
            >
              {{
                new Date(o.createdAt).toLocaleDateString("en", {
                  year: "numeric",
                  month: "short",
                  day: "numeric"
                })
              }}
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
      arr: null,
      currentPage: 0,
      fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
      maxDisplayCnt: 0,
      totalPage: 0,
      totalCts: 0
    };
  },
  methods: {
    // removeTempInterval() {
    //   clearInterval(this.tempInterval);
    // },
    // checkStaticTranslations() {
    //   // Unique elements, identified by id attr
    //   var myLocalizableElements = document.getElementsByClassName(
    //     "contractslocalizable"
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
    //     "contractsmultilocalizable"
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

      api.getContracts(
        {
          p: this.$route.query.p || 1
        },
        o => {
          this.$root.showModalLoading = false;
          this.arr = o.contracts;
          this.currentPage = o.currentPage;
          // this.maxDisplayCnt = o.maxDisplayCnt;
          this.totalPage = o.totalPage;
          this.totalCts = o.total;
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
    // }, 2000);
    this.nthPage();
  },
  watch: {
    $route() {
      this.nthPage();
    }
  }
};
</script>
