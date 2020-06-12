<style>
.vue-bread {
  background-color: #f7f7f7;
  overflow: auto;
  padding: 10px 0;
}
.vue-blocks {
  background-color: white;
}

.vue-blocks .block {
  margin-right: 8px;
}

@media (max-width: 767.98px) {
  .vue-blocks .title {
    font-size: 20px;
  }
}
</style>

<template>
  <!-- https://etherscan.io/blocks -->
  <div class="vue-blocks ">
    <div class="vue-bread">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-auto bread-title font-40 font-bold font-color-000000">
            {{ $t("blocksTitle") }}
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
          {{ numberAddComma(totalBlocks) }}
          <span>
            {{ $t("blocksFound") }}
          </span>
          <!-- <span v-if="totalTxs > 500" class="font-color-555555 font-16" style="vertical-align: text-bottom;">(<span class="blockslocalizable" id="blocksLatestFound"></span>)</span> -->
        </div>
      </div>
      <div class="explorer-table-container">
        <table class="mt20 explorer-table list-table">
          <tr class="list-header font-12 font-bold font-color-000000">
            <th style="width: 20px;"></th>
            <th style="width: 130px;">
              {{ $t("blocksTableHeight") }}
            </th>
            <th style="width: 130px;">
              {{ $t("blocksTableAge") }}
            </th>
            <th style="padding-left: 20px">
              {{ $t("blocksTableTxn") }}
            </th>
            <th style="padding-left: 30px">
              {{ $t("blocksTableMinted") }}
            </th>
            <th class="text-right">
              {{ $t("blocksTableGasReward") }}
            </th>
            <th class="text-right">
              {{ $t("blocksTableGasLimit") }}
            </th>
            <th class="text-right">
              {{ $t("blocksTableAverageGasPrice") }}
            </th>
            <th style="width: 20px;"></th>
          </tr>
          <tr v-for="(o, i) in arr" :key="i">
            <td></td>
            <td>
              <router-link v-bind:to="fragApi + '/block/' + o.height">
                <span class="font-14">{{ o.height }}</span>
              </router-link>
            </td>
            <td>
              <div>
                <div class="font-color-000000 font-14">
                  <span
                    name="transactionsTableAgoPrefix"
                    class="blocksmultilocalizable"
                  ></span>
                  {{ timeConversion(o.timeDiff) }}
                  <span
                    name="transactionsTableAgoSuffix"
                    class="blocksmultilocalizable"
                  ></span>
                </div>
                <div class="down-arrow-tip" style="display: none;">
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
            <td style="padding-left: 20px">
              <router-link v-bind:to="fragApi + '/txs?block=' + o.height">
                <span class="font-14">{{ numberAddComma(o.txnCnt) }}</span>
              </router-link>
            </td>
            <td style="padding-left: 30px">
              <router-link v-bind:to="fragApi + '/address/' + o.miner.hash">
                <vue-blockies
                  class="d-inline"
                  v-bind:address="o.miner.alias || o.miner.hash"
                ></vue-blockies>
                <span class="font-14 monospace">{{
                  o.miner.alias || o.miner.hash
                }}</span>
              </router-link>
            </td>
            <td class="text-right">
              <span class="font-14 font-color-555555">{{
                toWei(o.gasReward)
              }}</span>
            </td>
            <td class="text-right">
              <span class="font-14 font-color-000000">{{
                numberAddComma(o.gasLimit)
              }}</span>
            </td>
            <td class="text-right">
              <span class="font-14 font-color-555555">{{
                toWei(o.avgGasPrice)
              }}</span>
            </td>
            <td></td>
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
  utility = require("@/assets/utility");

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
      heightFrom: 0,
      heightTo: 0,
      totalBlocks: 0,
      totalPage: 0
    };
  },
  methods: {
    //     removeTempInterval() {
    //       clearInterval(this.tempInterval);
    //     },
    //     checkStaticTranslations() {
    //       // Unique elements, identified by id attr
    //       var myLocalizableElements = document.getElementsByClassName(
    //         "blockslocalizable"
    //       );
    //       var totalElements = myLocalizableElements.length;
    //       var i;
    //       for (i = 0; i < totalElements; i++) {
    //         var elementId = myLocalizableElements[i].getAttribute("id");
    //         if (myLocalizableElements[i].getAttribute("localize")) {
    //           var elementAttribute = myLocalizableElements[i].getAttribute(
    //             "localize"
    //           );
    //           myLocalizableElements[i].setAttribute(
    //             elementAttribute,
    //             jsonStrings[this.$selectedLanguage][elementId]
    //           );
    //         } else {
    //           myLocalizableElements[i].innerText =
    //             jsonStrings[this.$selectedLanguage][elementId];
    //         }
    //       }
    //     },
    // checkDynamicTranslations() {
    //   // Multiple elements, identified with name attr
    //   var myMultiLocalizableElements = document.getElementsByClassName(
    //     "blocksmultilocalizable"
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

        api.getBlocks(
          { p },
          o => {
            this.$root.showModalLoading = false;
            this.arr = o.data;
            this.currentPage = o.page;
            this.totalPage = o.totalPage;
            this.totalBlocks = o.totalCount;

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
    timeConversion(ms) {
      return utility.timeConversion(ms);
    },
    toWei(n) {
      return utility.toWei(n);
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
