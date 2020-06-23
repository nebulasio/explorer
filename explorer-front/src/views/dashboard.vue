<style lang="scss">
$dark-card-height: 420px;
$nax-price-card-height: 520px;

.vue-dashboard {
  display: block;
  color: black;
  position: relative;
  background-color: #f5f6fa;
  padding-top: 1px; // for full fill bg color
}

.vue-dashboard .black-header {
  position: absolute;
  top: -40px;
  width: 100%;
  height: 200px;
  z-index: -1;
}

.vue-dashboard > .container {
  margin-top: 40px;
}

.vue-dashboard .flex-row-container {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-flow: row wrap;
  justify-content: space-between;
  align-content: space-between;
  flex: none;
}

.dashboard-card {
  .item-bg {
    padding: 0 2rem;
    display: flex;
    flex-direction: column;
  }
  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 1rem 0;

    @include media("<=desktop") {
      flex-direction: column;
      align-items: flex-start;

      .title {
        font-size: 18px !important;
      }

      .details {
        margin-top: 0.5rem;
      }
    }

    .title {
      font-size: 20px;
      font-weight: 600;
    }

    .details {
      color: #bcbec1;
    }
  }

  .detail {
    // margin-top: 2rem;
    font-size: 60px;
    display: flex;
    align-items: baseline;

    @include media("<=desktop") {
      margin-top: 0;
      font-size: 50px;

      .price-up,
      .price-down {
        font-size: 20px !important;
        margin-left: 6px;
      }
    }

    .prefix,
    .suffix {
      font-size: 28px;
    }

    .price-up,
    .price-down {
      font-size: 28px;
      margin-left: 1rem;

      .suffix {
        font-size: 20px;
      }
    }

    .price-up {
      color: #3dcc85;
    }

    .price-down {
      color: #ff4060;
    }
  }

  .market {
    margin-top: auto;
    .row {
      label {
        color: #bcbec1;
      }

      a {
        color: #798bdf;
        font-size: 14px;
      }

      .prefix,
      .suffix {
        font-size: 14px;
      }

      .col-4,
      .col-md-4 {
        padding: 0;

        & > div {
          font-size: 16px;
          @include media("<=desktop") {
            font-size: 14px;
          }
        }
      }

      .col-6,
      .col-md-6 {
        @include media("<=desktop") {
          padding: 0;
        }

        & > div {
          font-size: 20px;
          @include media("<=desktop") {
            font-size: 16px;
          }
        }
      }

      .col-12 {
        & > div {
          font-size: 20px;
        }
      }
    }
  }

  .chart-container {
    margin-top: 0;
    @include media("<=desktop") {
      margin-top: 1rem;
    }
  }
}

.vue-dashboard .text-light-gray {
  color: #a6a6a6;
}

.vue-dashboard .text-gray {
  color: #8d8d8d;
}

.vue-dashboard .w590 {
  width: 590px;
}

.vue-dashboard .w285 {
  width: 285px;
}

.vue-dashboard .ml30 {
  margin-left: 30px;
}

.vue-dashboard .row1 .flex-item {
  height: $dark-card-height;
}

// .vue-dashboard .row2 .flex-item {
//   height: 160px;
// }

// .vue-dashboard .row3 .flex-item {
//   height: 110px;
// }

// .vue-dashboard .row4 .flex-item {
//   height: 330px;
// }

.vue-dashboard .row5 .flex-item {
  height: 476px;
}

.vue-dashboard .flex-item {
  position: relative;
}

.vue-dashboard .row > * {
  margin-bottom: 30px;
}

.vue-dashboard .atlaspAds-bottom > a > img {
  margin-bottom: 30px;
}

.vue-dashboard #atlaspAds-middle-mobile > a > img {
  margin-bottom: 30px;
}

@media (max-width: 767.98px) {
  .vue-dashboard .row > * {
    margin-bottom: 15px;
  }

  .vue-dashboard #atlaspAds-bottom {
    display: none;
  }

  .vue-dashboard #atlaspAds-middle-mobile > a > img {
    margin-bottom: 15px;
  }
}

.vue-dashboard .item-bg {
  background-color: white;
  width: 100%;
  height: 100%;
}

.vue-dashboard .item-shadow {
  box-shadow: 10px 10px 20px 0px rgba(30, 30, 30, 0.05);
}

.row {
  .item-title {
    position: absolute;
    left: 30px;
    top: 25px;
    font-size: 20px;
    font-weight: 600;
  }
}

.row1 {
  color: white;

  .flex-item {
    box-shadow: none;
    background-color: initial;
  }

  .item-bg {
    position: relative;
    background: linear-gradient(
      135deg,
      rgba(48, 58, 76, 1) 0%,
      rgba(15, 19, 26, 1) 100%
    );
  }
}

// .daily-transactions {
//   .details {
//     display: flex;
//     flex-direction: column;
//     position: absolute;
//     top: 20px;
//     right: 30px;
//     align-items: baseline;
//     text-align: right;
//     font-size: 12px;
//     color: rgba(255, 255, 255, 0.7);
//   }
// }

/* .vue-dashboard .daily-transactions .details *:nth-child(2) {
  font-size: 28px;
  color: white;
  font-weight: 400;
  margin-left: 10px;
  margin-top: 10px;
} */

// .vue-dashboard .daily-chart {
//   position: absolute;
//   top: 90px;
//   height: 230px;
//   width: calc(100% - 30px);
//   margin-left: 30px;
// }

// .vue-dashboard .daily-echart-down-arrow {
//   width: 0;
//   height: 0;
//   border-top: 4px solid #595c63;
//   border-right: 4px solid transparent;
//   border-left: 4px solid transparent;
//   position: absolute;
//   bottom: -4px;
//   left: calc(50% - 4px);
//   z-index: 1060;
// }

.vue-dashboard .market-price .update-time {
  position: absolute;
  top: 32px;
  right: 30px;
  font-size: 12px;
  color: rgba(255, 255, 255, 0.7);
}

.vue-dashboard .market-price .details {
  position: absolute;
  top: 20px;
  right: 30px;
  align-items: baseline;
  text-align: right;
  font-size: 12px;
  color: rgba(255, 255, 255, 0.7);
}

.vue-dashboard .market-price .detail {
  position: absolute;
  margin-top: 96px;
  margin-left: 30px;
  vertical-align: bottom;
}

// .vue-dashboard .market-price .detail *:nth-child(1) {
//   font-size: 28px;
// }

// .vue-dashboard .market-price .detail *:nth-child(2) {
//   font-size: 60px;
// }

// .vue-dashboard .market-price .detail *:nth-child(3) {
//   font-size: 20px;
//   color: #2cee8c;
// }

// .vue-dashboard .market-price .detail .text-red {
//   color: red;
// }

.vue-dashboard .market-price .market {
  position: absolute;
  margin-top: 234px;
  flex-flow: row nowrap;
  justify-content: flex-start;
  font-size: 12px;
  color: rgba(255, 255, 255, 0.7);
}

.vue-dashboard .market-price .market .row div div {
  font-size: 20px;
  color: white;
}

@media (max-width: 575.98px) {
  .vue-dashboard .market-price .market .row div div {
    font-size: 22px;
  }
}

.vue-dashboard .market-price .container {
  padding: 0 30px;
}

.nax-price-card {
  height: $nax-price-card-height !important;
}

.top-node-card {
  height: $nax-price-card-height !important;
}

.market-price {
  .detail {
    *:nth-child(1) {
      font-size: 28px;
    }

    *:nth-child(2) {
      font-size: 60px;
    }

    *:nth-child(3) {
      font-size: 20px;
      color: #2cee8c;
    }

    .price-up {
      color: #ff4060;
    }

    .price-down {
      color: #3dcc85;
    }
  }

  .row {
    & > div {
      margin-bottom: 20px;
    }
    &.border-top {
      border-top: 0.5px solid #d6d8db !important;
      padding-top: 20px;
    }
  }

  a {
    color: #798bdf;
    font-size: 14px;
  }
}

.vue-dashboard .row2 .subtitle {
  position: absolute;
  margin-left: 30px;
  margin-top: 56px;
}

.vue-dashboard .realtime-blocks {
  position: absolute;
  top: 30px;
  left: 170px;
  right: 40px;
  height: 100px;
  /* overflow: hidden; */
  display: flex;
  flex-flow: row-reverse nowrap;
  justify-content: flex-start;
}

.vue-dashboard .realtime-block {
  position: relative;
  width: 15px;
  height: 100px;
  margin-left: 10px;
  background-color: #6c9eff;
  transition: all 1000ms;
  display: none;
}

.vue-dashboard .realtime-block:first-child {
  background-color: #0057ff;
}

.vue-dashboard .realtime-block .blockheight {
  display: block;
  width: 15px;
  background-color: rgba(217, 230, 255, 1);
  transition: all 500ms;
  z-index: 3;
}

.vue-dashboard .realtime-block .block-popover {
  position: absolute;
  left: calc(-50% + 15px);
  top: -80px;
  padding: 8px;
  font-size: 12px;
  color: white;
  background-color: #0057ff;
  border-radius: 2px;
  transform: translateX(-50%);
  white-space: nowrap;
  z-index: 1060;
  display: none;
}

.vue-dashboard .realtime-block .block-popover::before {
  content: "";
  width: 0;
  height: 0;
  border-top: 8px solid #0057ff;
  border-right: 8px solid transparent;
  border-left: 8px solid transparent;
  position: absolute;
  bottom: -4px;
  left: calc(50% - 8px);
  z-index: 2;
}

.vue-dashboard .realtime-block:hover .block-popover {
  display: block;
}

// .vue-dashboard .row3 .flex-item {
//   position: relative;
// }

// .vue-dashboard .row3 .flex-item .item-bg div:nth-child(1) {
//   position: absolute;
//   margin-top: 23px;
//   margin-left: 30px;
//   font-size: 28px;
//   font-weight: 600;
// }

// .vue-dashboard .row3 .link-style {
//   position: absolute;
//   margin-top: 65px;
//   margin-left: 30px;
//   color: #7f7f7f;
//   font-size: 12px;
// }

// .vue-dashboard .row3 img {
//   position: absolute;
//   top: 35px;
//   right: 33px;
// }

// .vue-dashboard .row3 .flex-item:hover .link {
//   color: rgba(0, 87, 255, 1);
// }

// .vue-dashboard .row4 .item-bg {
//   position: relative;
// }

.vue-dashboard .user-pie {
  display: block;
  position: absolute;
  margin-left: 50px;
  margin-top: 108px;
  background-color: gray;
  box-shadow: -10px 10px 20px 0px rgba(30, 30, 30, 0.05);
}

.vue-dashboard .old-user {
  position: absolute;
  top: 20px;
  /* left: 20px; */
  width: 114px;
  height: 114px;
  background-color: rgba(89, 146, 255, 1);
  border-style: solid;
  border-width: 0px;
  border-radius: 50%;
}

.vue-dashboard .new-user-container {
  position: absolute;
  left: 57px;
  width: 77px;
  height: 154px;
  overflow: hidden;
}

.vue-dashboard .new-user {
  position: absolute;
  top: 0px;
  left: -77px;
  width: 154px;
  height: 154px;
  background-image: linear-gradient(to left, transparent 50%, #0057ff 0);
  border-style: solid;
  border-width: 0px;
  border-radius: 50%;
  transition-property: transform;
  transition-duration: 0.5s;
  transition-timing-function: ease;
}

.vue-dashboard .new-user-indicator {
  position: absolute;
  top: 20px;
  left: 57px;
  background: transparent;
  fill: none;
  stroke: none;
}

.vue-dashboard .new-user-indicator .line {
  fill: none;
  stroke: #000000;
  stroke-width: 0.5px;
  stroke-dasharray: 0px, 202px;
  animation: lineMove 1s ease-out 0.5s forwards;
}

@keyframes lineMove {
  0% {
    stroke-dasharray: 0px, 202px;
  }
  100% {
    stroke-dasharray: 202px, 202px;
  }
}

.vue-dashboard .new-user-indicator .labels {
  position: absolute;
  top: 0px;
  right: 0px;
  text-align: right;
}

.vue-dashboard .new-user-indicator .labels > div:first-child {
  margin-top: 31px;
  font-size: 18px;
  font-weight: 400;
  transform: translateY(12px);
  opacity: 0;
  animation: label 1s ease-out 1.5s forwards;
}

.vue-dashboard .new-user-indicator .labels > div:last-child {
  font-size: 12px;
  color: #555555;
  transform: translateY(-10px);
  opacity: 0;
  animation: label 1s ease-out 1.5s forwards;
}

@keyframes label {
  to {
    transform: translateY(0px);
    opacity: 1;
  }
}

// .vue-dashboard .row4 .detail {
//   display: flex;
//   flex-flow: column nowrap;
//   justify-content: flex-end;
//   align-items: flex-end;
//   position: absolute;
//   top: 74px;
//   right: 30px;
// }

// .vue-dashboard .row4 .title {
//   font-size: 28px;
//   margin-top: 40px;
//   font-weight: 600;
// }

.vue-dashboard .account-echart-down-arrow {
  width: 0;
  height: 0;
  border-top: 4px solid #0057ff;
  border-right: 4px solid transparent;
  border-left: 4px solid transparent;
  position: absolute;
  bottom: -4px;
  left: calc(50% - 4px);
  z-index: 1060;
}

.vue-dashboard .row5 .item-bg {
  position: relative;
  padding: 23px 30px;
}

.vue-dashboard .row5 .item-title {
  position: absolute;
  margin: 0;
}

.vue-dashboard .row5 .showall {
  position: absolute;
  top: 30px;
  right: 30px;
  font-size: 12px;
  font-weight: 400;
  color: #555;
}

.vue-dashboard .row5 .showall:hover {
  color: #0057ff;
}

.vue-dashboard .row5 table {
  margin-top: 47px;
  width: 100%;
  font-size: 16px;
}

.vue-dashboard .row5 table tr {
  border: solid 1px;
  border-color: #f2f6f7;
}

.vue-dashboard .row5 tr {
  height: 74px;
}

.vue-dashboard .row5 td:first-child {
  width: 70px;
}

.vue-dashboard .row5 td:last-child {
  text-align: right;
}

.vue-dashboard .row5 td .time::after {
  content: "\A ";
  white-space: pre;
}

.vue-dashboard .row5 .flex-item .item-bg {
  overflow-y: scroll;
}

.vue-dashboard .row5 tr {
  transition: all 1s;
}

.vue-dashboard .row5 tr:nth-of-type(n + 6) {
  opacity: 0;
}

.vue-dashboard #atlaspAds-side {
  position: fixed;
  top: 460px;
  left: calc((100% - 1140px) * 0.5 + 1140px + 5px);
  width: 300px;
  max-width: calc((100% - 1140px) * 0.5 - 25px);
}

@media (max-width: $dark-card-height) {
  .vue-dashboard .realtime-block:nth-of-type(-n + 5) {
    display: block;
    opacity: 1;
  }

  .vue-dashboard .realtime-block:nth-of-type(5) {
    opacity: 0;
  }

  .vue-dashboard .row5 .txcnt {
    display: none;
  }
}

@media (max-width: 414px) {
  .vue-dashboard .row1 .data-source {
    visibility: hidden;
  }
}

@media (max-width: 575.98px) {
  .vue-dashboard .realtime-block:nth-of-type(-n + 7) {
    display: block;
    opacity: 1;
  }

  .vue-dashboard .realtime-block:nth-of-type(7) {
    opacity: 0;
  }

  .vue-dashboard .user-pie {
    margin-left: 30px;
    margin-top: 128px;
  }

  .vue-dashboard .row5 td:first-child {
    width: 60px;
  }

  .vue-dashboard .row5 td .time::after {
    content: "";
    white-space: pre;
  }
}

/* Small devices (landscape phones, 576px and up) */
@media (min-width: 576px) {
  .vue-dashboard .realtime-block:nth-of-type(-n + 14) {
    display: block;
    opacity: 1;
  }

  .vue-dashboard .realtime-block:nth-of-type(14) {
    opacity: 0;
  }
}

/* Medium devices (tablets, 768px and up) */
@media (min-width: 768px) {
  .vue-dashboard .realtime-block:nth-of-type(-n + 21) {
    display: block;
    opacity: 1;
  }

  .vue-dashboard .realtime-block:nth-of-type(21) {
    opacity: 0;
  }
}

/* Large devices (desktops, 992px and up) */
@media (min-width: 992px) {
  .vue-dashboard .realtime-block:nth-of-type(-n + 31) {
    display: block;
    opacity: 1;
  }

  .vue-dashboard .realtime-block:nth-of-type(31) {
    opacity: 0;
  }
}

/* Extra large devices (large desktops, 1200px and up) */
@media (min-width: 1200px) {
  .vue-dashboard .realtime-block:nth-of-type(-n + 38) {
    display: block;
    opacity: 1;
  }

  .vue-dashboard .realtime-block:nth-of-type(38) {
    opacity: 0;
  }
}

/* Large devices (desktops, 992px and up) */
@media (min-width: 992px) and (max-width: 1199.98px) {
  .vue-dashboard .row3 .flex-item .item-bg div:nth-child(1) {
    font-size: 20px;
  }
}

.row2-list-enter {
  opacity: 0;
  transform: translateX(25px);
}

.list-enter {
  opacity: 0;
  transform: translateX(100px);
}
</style>
<template>
  <div class="vue-dashboard">
    <div class="bg-black black-header"></div>
    <div class="container">
      <!-- row 1: nas data -->
      <div class="row row1">
        <DailyTxChart />

        <!-- avg. annualized rate fo return -->
        <NodeStaticCard />
      </div>

      <!-- row 2: nax data -->
      <div class="row row1">
        <!-- nas price -->
        <NasMarketCard />

        <!-- nax price card -->
        <NaxMarketCard />
      </div>

      <div class="row row1">
        <NebEcoChart />
      </div>

      <!-- row 3: node data -->
      <div class="row row1">
        <TopNodesChart />

        <!-- dStaking NAS and Mint NAX -->
        <DStakingChart />
      </div>

      <div class="row row5">
        <div class="flex-item col-12 col-lg-6 row5-item">
          <div class="item-bg item-shadow">
            <div class="item-title">
              {{ $t("dashboardBlocksIndicatorTitle") }}
            </div>
            <router-link :to="fragApi + '/blocks/'" class="showall">
              {{ $t("dashboardIndicatorViewAll") }}
            </router-link>
            <transition-group
              name="list"
              tag="table"
              frame="hsides"
              rules="rows"
            >
              <tr
                class="list-item"
                v-for="block in blocks.slice(0, 6)"
                :key="block.height"
              >
                <td>
                  <img
                    src="/static/img/icon-block.png?v=20190116"
                    width="50"
                    height="50"
                  />
                </td>
                <td>
                  {{ $t("dashboardBlockNumber") }}
                  <router-link
                    :to="fragApi + '/block/' + block.height"
                    class="monospace"
                    >{{ block.height }}</router-link
                  >
                  <br />
                  <span class="txcnt monospace">
                    <router-link
                      v-if="block.txnCnt == 0"
                      :to="fragApi + '/txs?block=' + block.height"
                    >
                      {{ $t("dashboardBlocksNoTransaction") }}
                    </router-link>
                    <router-link
                      v-if="block.txnCnt == 1"
                      :to="fragApi + '/txs?block=' + block.height"
                      >{{ block.txnCnt }}

                      {{ $t("dashboardBlocksOneTransaction") }}
                    </router-link>
                    <router-link
                      v-if="block.txnCnt > 1"
                      :to="fragApi + '/txs?block=' + block.height"
                      >{{ block.txnCnt }}

                      {{ $t("dashboardBlocksSeveralTransactions") }}
                    </router-link>
                  </span>
                </td>
                <td>
                  <div class="time">
                    {{
                      timeConversion(
                        Date.now() - block.localTimestamp + block.timeDiff
                      )
                    }}
                  </div>
                </td>
              </tr>
            </transition-group>
          </div>
        </div>
        <div class="flex-item col-12 col-lg-6 row5-item">
          <div class="item-bg item-shadow">
            <div class="item-title">
              {{ $t("dashboardTransactionsTitle") }}
            </div>
            <router-link :to="fragApi + '/txs/'" class="showall">
              {{ $t("dashboardIndicatorViewAll") }}
            </router-link>
            <transition-group
              name="list"
              tag="table"
              frame="hsides"
              rules="rows"
            >
              <tr v-for="tx in txs.slice(0, 6)" :key="tx.hash">
                <td>
                  <img
                    src="/static/img/icon-tx.png?v=20190116"
                    width="50"
                    height="50"
                  />
                </td>
                <td>
                  {{ $t("dashboardTransactionNumber") }}

                  <router-link :to="fragApi + '/tx/' + tx.hash">
                    <span class="monospace">{{ tx.hash.slice(0, 4) }}</span
                    >...<span class="monospace">{{ tx.hash.slice(-4) }}</span>
                  </router-link>
                  <br />
                  <span class="fromto d-none d-sm-inline">
                    {{ $t("dashboardTransactionFromText") }}

                    <router-link :to="fragApi + '/address/' + tx.from.hash">
                      <span class="monospace">{{
                        tx.from.hash.slice(0, 4)
                      }}</span
                      >...<span class="monospace">{{
                        tx.from.hash.slice(-4)
                      }}</span>
                    </router-link>
                  </span>
                  <span class="fromto d-none d-sm-inline">
                    {{ $t("dashboardTransactionToText") }}

                    <router-link :to="fragApi + '/address/' + tx.from.hash">
                      <span class="monospace">{{ tx.to.hash.slice(0, 4) }}</span
                      >...<span class="monospace">{{
                        tx.to.hash.slice(-4)
                      }}</span>
                    </router-link>
                  </span>
                </td>
                <td>
                  <div class="time">
                    {{
                      timeConversion(
                        Date.now() - tx.localTimestamp + tx.timeDiff
                      )
                    }}
                  </div>
                </td>
              </tr>
            </transition-group>
          </div>
        </div>
      </div>
      <!--ATP底部广告位-->
      <div
        class="flex atlaspAds-bottom d-none d-md-block"
        id="atlaspAds-bottom"
      ></div>
    </div>
    <!--ATP侧边栏广告位-->
    <div class="flex atlaspAds" id="atlaspAds-side"></div>
  </div>
</template>
<script>
var api = require("@/assets/api"),
  utility = require("@/assets/utility"),
  BigNumber = require("bignumber.js");

var dashboardTransactionsGraphicPointsTitle = "";
var dashboardBlocksTransactions = "";
var dashboardAmountText = "";

import DailyTxChart from "@/components/DailyTxChart";
import DStakingChart from "@/components/DStakingChart";
import TopNodesChart from "@/components/TopNodesChart";
import AddressGrowthChart from "@/components/AddressGrowthChart";
import NaxMarketCard from "@/components/NaxMarketCard";
import NasMarketCard from "@/components/NasMarketCard";
import NodeStaticCard from "@/components/NodeStaticCard";
import NebEcoChart from "@/components/NebEcoChart";

module.exports = {
  components: {
    DailyTxChart,
    DStakingChart,
    TopNodesChart,
    AddressGrowthChart,
    NaxMarketCard,
    NasMarketCard,
    NodeStaticCard,
    NebEcoChart
  },
  data() {
    return {
      fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
      todayTxCnt: -1,
      dailyTxData: null,
      market: null,
      blocks: [],
      staticInfo: null,
      txs: [],
      shortIntervalID: null,
      longIntervalID: null
    };
  },
  computed: {
    blockheight() {
      if (this.blocks.length > 0)
        return this.numberAddComma(this.blocks[0].height);
      return "0";
    }
  },
  mounted() {
    // api.getTx("cnt_static", o => (this.dailyTxData = o)); //近期每日交易量
    // api.getMarketCap(o => (this.market = o)); //币价和市值
    api.getBlock(
      { type: "latest" },
      o => (this.blocks = this.addLocalTimestamp(o))
    ); //最新一波 block
    api.getTx({ type: "latest" }, o => (this.txs = this.addLocalTimestamp(o))); //最新一波 tx
    // api.getTodayTxCnt(o => (this.todayTxCnt = o)); //今日交易量
    api.getStaticInfo(o => (this.staticInfo = o)); //合约数量、地址数量。。。

    this.shortIntervalID = setInterval(() => {
      api.getTx(
        { type: "latest" },
        o => (this.txs = this.addLocalTimestamp(o))
      ); //最新一波 tx
      api.getBlock({ type: "newblock" }, o => {
        //获取最新一个 block
        this.addLocalTimestamp(o);
        try {
          if (o[0].height != this.blocks[0].height) {
            this.blocks.splice(0, 0, o[0]);
          }
        } catch (error) {}

        try {
          if (o.data[0].height != this.blocks[0].height) {
            this.blocks.splice(0, 0, o.data[0]);
          }
        } catch (error) {}
      });
    }, 5000);

    this.longIntervalID = setInterval(() => {
      //   api.getTodayTxCnt(o => (this.todayTxCnt = o)); //今日交易量
      //   api.getMarketCap(o => (this.market = o)); //币价和市值
      api.getStaticInfo(o => (this.staticInfo = o)); //合约数量、地址数量。。。
    }, 60000);
  },

  methods: {
    numberAddComma(n) {
      return utility.numberAddComma(n);
    },
    timeConversion(ms) {
      return utility.timeConversion(ms);
    },
    afterEnter: function(el) {
      let height =
        (1 -
          Math.min(5, Math.max(0, el.firstElementChild.dataset.txncnt)) / 5.0) *
          100 +
        "px";
      $(el.firstElementChild).css("height", height);
    },
    shortDate(value) {
      if (!value || value === "undefined") {
        return "";
      }
      let date = new Date(value);
      if (isNaN(date.getMonth())) {
        return "";
      }
      let str = date.toLocaleDateString("en", {
        month: "short",
        day: "numeric"
      });
      if (str.length > 6) {
        str = date.getMonth() + 1 + "-" + date.getDate();
      }
      return str;
    },
    addLocalTimestamp(n) {
      if (n instanceof Array) {
        for (var index in n) {
          if (!n[index].localTimestamp) {
            n[index].localTimestamp = Date.now();
          }
        }
      }
      return n;
    }
  },
  updated() {
    if (window.isIE()) {
      $("#svg-line").css("stroke-dasharray", "none");
    }
  },
  destroyed() {
    clearInterval(this.shortIntervalID);
    clearInterval(this.longIntervalID);
  }
};
</script>
