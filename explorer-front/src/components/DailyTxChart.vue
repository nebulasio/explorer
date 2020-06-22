<template>
  <div class="dashboard-card col-12 col-lg-6">
    <div class="item-bg">
      <div class="header">
        <div class="title">
          Nebulas Transaction History
        </div>
        <div class="details">
          <span v-if="blockHeight">Current Height: {{ blockHeight }}</span>
          <span v-if="tx24h">
            {{ $t("dashboardDailyTransactionsSubtitle") }}: {{ tx24h }}</span
          >
        </div>
      </div>

      <div class="chart-container">
        <vchart
          class="daily-chart"
          v-if="dailyTxChartOptions"
          :options="dailyTxChartOptions"
          :autoResize="true"
        ></vchart>
      </div>
    </div>
  </div>
</template>

<script>
import ECharts from "vue-echarts/components/ECharts";
import "echarts/lib/chart/line";
import "echarts/lib/component/tooltip";

import api from "@/assets/api";

import utility from "@/assets/utility";
import moment from "moment";
import _ from "lodash";

import { toLocaleString } from "@/utils/number";

export default {
  name: "DailyTxChart",
  components: {
    vchart: ECharts
  },
  data() {
    return {
      dailyTxData: null,
      latestBlock: null,
      txToday: null
    };
  },
  async mounted() {
    this.dailyTxData = await this.$api.home.getNetData();
    const latestBlocks = await this.$api.home.getLatestBlock();

    if (latestBlocks) {
      this.latestBlock = latestBlocks[0];
    }

    this.txToday = await this.$api.home.getTxToday();
    console.log("txToday", this.txToday);
  },
  methods: {
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
    }
  },
  computed: {
    blockHeight() {
      return this.latestBlock && toLocaleString(this.latestBlock["height"]);
    },

    tx24h() {
      return this.txToday && toLocaleString(this.txToday);
    },

    dailyTxChartOptions() {
      if (!this.dailyTxData) return null;

      let dates = [];
      let nums = [];

      let data = this.dailyTxData;

      data = data.reverse();

      data = data.map(d => {
        d["date_label"] = moment(d["date"]).format("MMM DD");
        dates.push(d["date_label"]);
        nums.push(d["transaction_count"]);
        return d;
      });

      let vm = this;
      var options = {
        grid: {
          left: "40",
          bottom: "50",
          right: "17",
          top: "10",
          containLabel: false
        },
        xAxis: {
          data: dates,
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            textStyle: {
              color: "#B2B2B2"
            },
            margin: 18
            // formatter: function(value) {
            //   return vm.shortDate(value);
            // }
          }
        },
        yAxis: {
          // min: 'dataMin',
          axisLine: {
            show: false
          },
          axisLabel: {
            textStyle: {
              color: "#B2B2B2"
            },
            margin: 0
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: false
          }
        },
        series: {
          type: "line",
          data: nums,
          smooth: true,
          symbol: "circle",
          symbolSize: 5,
          lineStyle: {
            color: "#595C63",
            width: 3
          },
          itemStyle: {
            color: "#FFFFFF",
            borderWidth: 3
          },
          areaStyle: {
            color: "#595C63",
            opacity: 1
          }
        },
        tooltip: {
          trigger: "item",
          transitionDuration: 0,
          position: "top",
          formatter: function(params, ticket, callback) {
            let findItem = _.find(data, { date_label: params.name });

            let date = new Date(params.name);
            let dateStr = date.toLocaleDateString("en", {
              year: "numeric",
              month: "short",
              day: "numeric"
            });

            const text = `
            ${dateStr}
            <div>${vm.$t("dashboardDailyTransactionsSubtitle")}
            ${utility.numberAddComma(params.value)}</div>
            <div>Nas price: $${findItem["price"]}</div>
            <div class=echart-down-arrow></div>
            `;

            return text;
          },
          backgroundColor: "#595C63",
          padding: 8,
          extraCssText:
            "border-radius: 2px;box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);",
          textStyle: {
            fontFamily: "menlo, consolas",
            fontSize: 12,
            lineHeight: 18
          }
        }
      };
      return options;
    }
  }
};
</script>

<style lang="scss" scoped>
.details {
  display: flex;
  flex-direction: column;
}

// .chart-container {
//   margin-top: 2rem;
//   @include media("<=desktop") {
//     margin-top: 1rem;
//   }
// }

.daily-chart {
  height: 300px;
  width: calc(100%);
}
</style>
