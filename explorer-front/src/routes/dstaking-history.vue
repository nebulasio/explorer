<style>
.vue-bread {
  background-color: #f7f7f7;
  overflow: auto;
  padding: 10px 0;
}
.vue-txs {
  background-color: white;
}
.vue-txs .tip a {
  color: rgb(76, 32, 133);
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

.vue-txs .fromTo {
  line-height: 24px;
}

.vue-txs .block {
  margin-right: 8px;
}

@media (max-width: 767.98px) {
  .vue-txs .title {
    font-size: 20px;
  }
}
</style>
<template>
  <div class="staking-history fullfill">
    <div class="vue-bread">
      <div class="container">
        <div class="row align-items-center">
          <div
            class="col-auto bread-title font-40 font-bold font-color-000000 dshistorylocalizable"
            id="dshistoryTitle"
          ></div>
          <div
            class="col-auto bread-subtitle font-16 font-bold font-color-000000 align-baseline"
          ></div>
        </div>
      </div>
    </div>

    <div v-if="arr && arr.length" class="container mt20">
      <div class="d-block d-md-flex flex-row align-items-center mt20">
        <span
          class="col-auto pl-0 pr-2 info font-color-000000 font-24 font-bold title"
        >
          <span class="dshistorylocalizable" id="dshistoryTotalText"></span>
          {{ totalTxs }}
          <span
            v-if="totalTxs > 1"
            class="dshistorylocalizable"
            id="dshistoryPeriodPluralText"
          ></span
          ><span
            v-else
            class="dshistorylocalizable"
            id="dshistoryPeriodSingularText"
          ></span>
        </span>
      </div>

      <div class="explorer-table-container text-center">
        <table class="mt20 explorer-table list-table">
          <tr class="list-header font-12 font-bold font-color-000000">
            <th
              class="text-center dshistorylocalizable"
              id="dstakingTablePeriodNumber"
            ></th>
            <th
              class="text-center dshistorylocalizable"
              id="dstakingTableDstakingRate"
            ></th>
            <th
              class="text-center dshistorylocalizable"
              id="dstakingTableIssuanceQuantity"
            ></th>
            <th
              class="text-center dshistorylocalizable"
              id="dstakingTableBurnedQuantity"
            ></th>
            <th
              class="text-center pr-3 dshistorylocalizable"
              id="dstakingTablePledgeQuantity"
            ></th>
          </tr>

          <tr v-for="(o, i) in arr" :key="i">
            <td>
              <span>{{ o.stage }}</span>
            </td>

            <td>
              <span>{{ pledgeRate(o) }}</span>
            </td>

            <td>
              <span>{{ naxAmount(o.distributedNax) + " NAX" }}</span>
            </td>

            <td>
              <span>{{ naxAmount(o.destroyedNax) + " NAX" }}</span>
            </td>

            <td>
              <span>{{ tokenAmount(o.pledgeNas) + " NAS" }}</span>
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
    <vue-nothing
      v-if="arr && arr.length === 0"
      title="0 transaction found"
    ></vue-nothing>
  </div>
</template>
<script>
import { EventBus } from "../events.js";
import { jsonStrings } from "../l10nstrings.js";
var api = require("@/assets/api"),
  utility = require("@/assets/utility"),
  BigNumber = require("bignumber.js");

module.exports = {
  components: {
    "vue-bread": require("@/components/vue-bread").default,
    "vue-pagination": require("@/components/vue-pagination").default,
    "vue-nothing": require("@/components/vue-nothing").default
  },
  data() {
    return {
      arr: null,
      currentPage: 0,
      fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
      totalPage: 0,
      totalTxs: 0
    };
  },
  methods: {
    removeTempInterval() {
      clearInterval(this.tempInterval);
    },
    checkStaticTranslations() {
      // Unique elements, identified by id attr
      var myLocalizableElements = document.getElementsByClassName(
        "dshistorylocalizable"
      );
      var totalElements = myLocalizableElements.length;
      var i;
      for (i = 0; i < totalElements; i++) {
        var elementId = myLocalizableElements[i].getAttribute("id");
        if (myLocalizableElements[i].getAttribute("localize")) {
          var elementAttribute = myLocalizableElements[i].getAttribute(
            "localize"
          );
          myLocalizableElements[i].setAttribute(
            elementAttribute,
            jsonStrings[this.$selectedLanguage][elementId]
          );
        } else {
          myLocalizableElements[i].innerText =
            jsonStrings[this.$selectedLanguage][elementId];
        }
      }
    },
    checkDynamicTranslations() {
      // Multiple elements, identified with name attr
      var myMultiLocalizableElements = document.getElementsByClassName(
        "dshistorymultilocalizable"
      );
      var totalElements = myMultiLocalizableElements.length;
      var i;
      for (i = 0; i < totalElements; i++) {
        var elementName = myMultiLocalizableElements[i].getAttribute("name");
        if (myMultiLocalizableElements[i].getAttribute("localize")) {
          var elementAttribute = myMultiLocalizableElements[i].getAttribute(
            "localize"
          );
          myMultiLocalizableElements[i].setAttribute(
            elementAttribute,
            jsonStrings[this.$selectedLanguage][elementName]
          );
        } else {
          myMultiLocalizableElements[i].innerText =
            jsonStrings[this.$selectedLanguage][elementName];
        }
      }
    },
    nav(n) {
      var query = JSON.parse(window.JSON.stringify(this.$route.query));

      query.p = n;
      this.$router.push({ path: this.$route.path, query });
    },
    nthPage() {
      this.$root.showModalLoading = true;

      api.getDstakingHistory(
        {
          page: this.$route.query.p || 1,
          size: 20
        },
        o => {
          this.$root.showModalLoading = false;
          this.arr = o.list;
          this.currentPage = o.currentPage;
          this.totalPage = o.totalPage;
          this.totalTxs = o.total;
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
    },
    naxAmount(n) {
      BigNumber.config({ DECIMAL_PLACES: 9 });
      var amount = BigNumber(n);
      var decimals = BigNumber("1e+9");
      return amount
        .div(decimals)
        .toFormat()
        .shortAmount();
    },
    isNatVoteTransfer(tx) {
      try {
        if (
          tx.type === "call" &&
          tx.to.hash === "n1pADU7jnrvpPzcWusGkaizZoWgUywMRGMY" &&
          JSON.parse(tx.data).Function === "vote" &&
          JSON.parse(JSON.parse(tx.data).Args).length >= 4
        ) {
          return true;
        }
      } catch (error) {}
      return false;
    },
    natAmount(tx) {
      BigNumber.config({ DECIMAL_PLACES: 18 });
      var amount = BigNumber(JSON.parse(JSON.parse(tx.data).Args)[3]);
      var decimals = BigNumber("1e+18");
      return amount
        .div(decimals)
        .toFormat()
        .shortAmount();
    },
    pledgeRate(o) {
      BigNumber.config({ DECIMAL_PLACES: 18 });
      var rate = BigNumber(o.pledgeNas).div(BigNumber(o.totalNas)) * 100;
      return rate.toFixed(4) + "%";
    }
  },
  mounted() {
    EventBus.$on("changeLanguage", foo => {
      this.checkStaticTranslations();
    });
    if (typeof this.$selectedLanguage != "undefined") {
      this.checkStaticTranslations();
    }
    this.translationsInterval = setInterval(() => {
      this.checkDynamicTranslations();
    }, 750);
    this.tempInterval = setInterval(() => {
      this.checkStaticTranslations();
      this.removeTempInterval();
    }, 2000);
    this.nthPage();
  },
  watch: {
    $route() {
      this.nthPage();
    }
  }
};
</script>
