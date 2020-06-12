<style>
.vue-bread {
  background-color: #f7f7f7;
  overflow: auto;
  padding: 10px 0;
}
.vue-block {
  width: 100%;
  background-color: white;
}
.vue-block .table tbody tr td {
  border: 0;
  vertical-align: middle;
}

.vue-block td .pagination {
  margin: 0;
  vertical-align: top;
}

.vue-block tr > td:first-child {
  padding-left: 24px;
}

.vue-block tr > td:first-child::after {
  content: ":";
}

.vue-block .card {
  border: 0;
}

.vue-block .dynasty a {
  margin-bottom: 10px;
}
</style>
<template>
  <!-- https://etherscan.io/block/4951841 -->
  <div class="vue-block fullfill" v-bind:triggerComputed="urlChange">
    <div class="vue-bread">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-auto bread-title font-40 font-bold font-color-000000">
            {{ $t("blockTitle") }}
          </div>
          <div
            class="col-auto bread-subtitle font-16 font-bold font-color-000000 align-baseline"
          >
            {{ $route.params.id }}
          </div>
        </div>
      </div>
    </div>
    <div v-if="block" class="container">
      <div class="font-24 font-bold font-color-000000 table-title">
        <span>
          {{ $t("blockOverview") }}
        </span>
      </div>

      <div class="explorer-table-container d-none d-md-block">
        <table class="explorer-table font-16">
          <tr>
            <td class="font-color-555555">
              {{ $t("blockHeight") }}
            </td>
            <td class="font-color-000000">
              <nav aria-label="" class="navgation-tab" localize="aria-label">
                <ul class="pagination">
                  <li>
                    <router-link
                      v-if="block.height > 1"
                      v-bind:to="fragApi + '/block/' + (+$route.params.id - 1)"
                      aria-label=""
                      localize="aria-label"
                    >
                      <span aria-hidden="true">
                        {{ $t("blockButtonPrev") }}
                      </span>
                    </router-link>
                  </li>
                  <li>&nbsp; {{ block.height }} &nbsp;</li>
                  <!-- <li>
                    <router-link
                      v-if="$root.timestamp - block.timestamp > 16000"
                      v-bind:to="fragApi + '/block/' + (+$route.params.id + 1)"
                      aria-label=""
                      localize="aria-label"
                      id="blockRouterNext"
                      class="blocklocalizable"
                    >
                      <span
                        aria-hidden="true"
                        class="blocklocalizable"
                        id="blockButtonNext"
                      ></span>
                    </router-link>
                  </li> -->
                </ul>
              </nav>
            </td>
          </tr>
          <tr>
            <td class="font-color-555555">
              {{ $t("blockTimeStamp") }}
            </td>
            <td class="font-color-000000">
              <span>
                {{ $t("transactionsTableAgoPrefix") }}
              </span>
              {{
                timeConversion(
                  Date.now() - block.localTimestamp + block.timeDiff
                )
              }}
              <span>
                {{ $t("transactionsTableAgoSuffix") }}
              </span>
              ({{
                new Date(block.timestamp)
                  .toString()
                  .replace("GMT", "UTC")
                  .replace(/\(.+\)/gi, "")
              }}
              | {{ block.timestamp }})
            </td>
          </tr>
          <tr>
            <td class="font-color-555555">
              {{ $t("blockTransactionsTitle") }}
            </td>
            <td class="font-color-000000">
              <router-link v-bind:to="fragApi + '/txs?block=' + block.height">
                <span>{{ block.blkSummary.txCnt }}</span>
              </router-link>
              tx {{ $t("blockTxInBlock") }}
            </td>
          </tr>
          <tr>
            <td class="font-color-555555">
              {{ $t("blockHashTitle") }}
            </td>
            <td class="font-color-000000 monospace">{{ block.hash }}</td>
          </tr>
          <tr>
            <td class="font-color-555555">
              {{ $t("blockParentHashTitle") }}
            </td>
            <td>
              <router-link v-bind:to="fragApi + '/block/' + block.parentHash">
                <span class="monospace">{{ block.parentHash }}</span>
              </router-link>
            </td>
          </tr>
          <tr>
            <td class="font-color-555555">
              {{ $t("blockMintedTitle") }}
            </td>
            <td>
              <router-link v-bind:to="fragApi + '/address/' + block.miner.hash">
                <span class="monospace">{{ block.miner.hash }}</span>
              </router-link>
              <span v-if="block.miner.alias"> | {{ block.miner.alias }}</span>
            </td>
          </tr>
          <tr>
            <td class="font-color-555555">
              {{ $t("blockCoinbaseTitle") }}
            </td>
            <td>
              <router-link v-bind:to="fragApi + '/address/' + block.coinbase">
                <span class="monospace">{{ block.coinbase }}</span>
              </router-link>
            </td>
          </tr>
          <tr>
            <td
              class="font-color-555555"
              style="vertical-align: top; padding-top: 12px;"
            >
              {{ $t("blockDinastyTitle") }}
            </td>
            <td style="vertical-align: top; padding-top: 12px;">
              <a
                class="d-flex align-items-center"
                href="#"
                v-on:click="showOrHideDynasty()"
                style="text-decoration: none;"
                data-toggle="collapse"
                data-target="#collapse-mobile"
                aria-expanded="false"
                aria-controls="collapseExample"
              >
                <span>
                  {{ $t("blockShowDinasty") }}
                </span>
                <img
                  style="margin-left: 12px; margin-top: 3px; vertical-align: middle;"
                  class="icon16"
                  v-bind:src="
                    isShowDynasty
                      ? '../../static/img/ic_payload_arrow_up.png'
                      : '../../static/img/ic_payload_arrow_down.png'
                  "
                />
              </a>
              <div class="collapse" id="collapse-mobile">
                <div class="card card-body dynasty">
                  <router-link
                    v-for="dynasty in block.dynasty"
                    v-bind:key="dynasty"
                    v-bind:to="fragApi + '/address/' + dynasty"
                  >
                    <span class="font-16 font-bold  monospace">
                      {{ dynasty }}</span
                    >
                  </router-link>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <td class="font-color-555555">
              {{ $t("blockGasReward") }}
            </td>
            <td class="font-color-000000">
              {{ toWei(block.blkSummary.gasReward) }}
            </td>
          </tr>
        </table>
      </div>

      <div class="mobile-detail d-md-none">
        <div>
          {{ $t("blockHeightTitle") }}
          <div class="detail">
            <nav
              localize="aria-label"
              aria-label="Page navigation"
              class="navgation-tab"
            >
              <ul class="pagination">
                <li>
                  <router-link
                    v-if="block.height > 1"
                    v-bind:to="fragApi + '/block/' + (+$route.params.id - 1)"
                    aria-label="Previous"
                  >
                    <span aria-hidden="true">
                      {{ $t("blockPreviousLink") }}
                    </span>
                  </router-link>
                </li>
                <li>&nbsp; {{ block.height }} &nbsp;</li>
                <!-- <li>
                  <router-link
                    v-if="$root.timestamp - block.timestamp > 16000"
                    v-bind:to="fragApi + '/block/' + (+$route.params.id + 1)"
                    aria-label="Next"
                  >
                    <span
                      aria-hidden="true"
                      class="blocklocalizable"
                      id="blockNextLink"
                    ></span>
                  </router-link>
                </li> -->
              </ul>
            </nav>
          </div>
        </div>
        <div>
          {{ $t("blockTimeStampTitle") }}
          <div class="detail">
            <span> {{ $t("blockTimeStampPrefix") }} </span
            >{{
              timeConversion(Date.now() - block.localTimestamp + block.timeDiff)
            }}
            {{ $t("blockTimeStampSuffix") }} ({{
              new Date(block.timestamp)
                .toString()
                .replace("GMT", "UTC")
                .replace(/\(.+\)/gi, "")
            }}
            | {{ block.timestamp }})
          </div>
        </div>
        <div>
          {{ $t("blockTransactionsTitle") }}
          <div class="detail">
            <router-link v-bind:to="fragApi + '/txs?block=' + block.height">
              <span>{{ block.blkSummary.txCnt }}</span>
            </router-link>
            {{ $t("blockTxInThisBlock") }}
          </div>
        </div>
        <div>
          Hash:
          <div class="detail monospace">{{ block.hash }}</div>
        </div>
        <div>
          {{ $t("blockParentHashTitle") }}
          <div class="detail">
            <router-link v-bind:to="fragApi + '/block/' + block.parentHash">
              <span class="monospace">{{ block.parentHash }}</span>
            </router-link>
          </div>
        </div>
        <div>
          {{ $t("blockMintedTitle") }}
          <div class="detail">
            <router-link v-bind:to="fragApi + '/address/' + block.miner.hash">
              <span class="monospace">{{ block.miner.hash }}</span>
            </router-link>
            <span v-if="block.miner.alias"> | {{ block.miner.alias }}</span>
          </div>
        </div>
        <div>
          {{ $t("blockCoinbaseTitle") }}
          <div class="detail">
            <router-link v-bind:to="fragApi + '/address/' + block.coinbase">
              <span class="monospace">{{ block.coinbase }}</span>
            </router-link>
          </div>
        </div>
        <div>
          {{ $t("blockDinastyTitle") }}
          <div class="detail">
            <a
              class="d-flex align-items-center"
              href="#"
              v-on:click="showOrHideDynasty()"
              style="text-decoration: none;"
              data-toggle="collapse"
              data-target="#collapseExample"
              aria-expanded="false"
              aria-controls="collapseExample"
            >
              <span>
                <span>
                  {{ $t("blockShowDinastyButton") }}
                </span>
              </span>
              <img
                style="margin-left: 12px; margin-top: 3px; vertical-align: middle;"
                class="icon16"
                v-bind:src="
                  isShowDynasty
                    ? '../../static/img/ic_payload_arrow_up.png'
                    : '../../static/img/ic_payload_arrow_down.png'
                "
              />
            </a>
            <div class="collapse" id="collapseExample">
              <div class="card card-body dynasty">
                <router-link
                  v-for="dynasty in block.dynasty"
                  v-bind:key="dynasty"
                  v-bind:to="fragApi + '/address/' + dynasty"
                >
                  <span class="font-16 font-bold "> {{ dynasty }}</span>
                </router-link>
              </div>
            </div>
          </div>
        </div>
        <div>
          {{ $t("blockGasRewardTitle") }}
          <div class="detail">{{ toWei(block.blkSummary.gasReward) }}</div>
        </div>
      </div>
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
    "vue-tab-buttons": require("@/components/vue-tab-buttons").default
  },
  computed: {
    urlChange() {
      this.$root.showModalLoading = true;
      api.getBlock(
        this.$route.params.id,
        o => {
          this.$root.showModalLoading = false;
          if (!o.localTimestamp) {
            o.localTimestamp = Date.now();
          }
          this.block = o;
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
  methods: {
    // removeTempInterval() {
    //   clearInterval(this.tempInterval);
    // },
    // checkStaticTranslations() {
    //   // Unique elements, identified by id attr
    //   var myLocalizableElements = document.getElementsByClassName(
    //     "blocklocalizable"
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
    //     "blockmultilocalizable"
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
    showOrHideDynasty() {
      this.isShowDynasty = !this.isShowDynasty;
    },
    timeConversion(ms) {
      return utility.timeConversion(ms);
    },
    timeConversionSec(ms) {
      return utility.timeConversionSec(ms);
    },
    toWei(n) {
      return utility.toWei(n);
    }
  },
  data() {
    return {
      block: null,
      fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
      tab: 0,
      tabButtons: ["Overview"],
      isShowDynasty: false,
      timestamp: Date.now()
    };
  }
  // mounted() {
  //   EventBus.$on("changeLanguage", foo => {
  //     this.checkStaticTranslations();
  //   });
  //   if (typeof this.$selectedLanguage != "undefined") {
  //     this.checkStaticTranslations();
  //   }
  //   this.translationsInterval = setInterval(() => {
  //     this.checkDynamicTranslations();
  //   }, 1000);
  //   this.tempInterval = setInterval(() => {
  //     this.checkStaticTranslations();
  //     this.removeTempInterval();
  //   }, 1500);
  // }
};
</script>
