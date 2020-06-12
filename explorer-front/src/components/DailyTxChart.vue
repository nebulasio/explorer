<template>
  <div class="chart-container">
    <vchart
      class="daily-chart"
      v-if="dailyTxChartOptions"
      :options="dailyTxChartOptions"
      :autoResize="true"
    ></vchart>
  </div>
</template>

<script>
import ECharts from "vue-echarts/components/ECharts";
import "echarts/lib/chart/line";
import "echarts/lib/component/tooltip";

import api from "@/assets/api";

import utility from "@/assets/utility";

export default {
  name: "DailyTxChart",
  components: {
    vchart: ECharts
  },
  data() {
    return {
      dailyTxData: null
    };
  },
  mounted() {
    api.getTx("cnt_static", o => (this.dailyTxData = o)); //近期每日交易量
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
    dailyTxChartOptions() {
      if (!this.dailyTxData) return null;
      var arr = [],
        dates = [],
        nums = [];
      for (var k in this.dailyTxData) {
        arr.push([k, this.dailyTxData[k]]);
      }
      arr.sort(function(a, b) {
        return Date.parse(a[0]) - Date.parse(b[0]);
      });
      // if (arr.length > 13) {
      //	 arr.splice(0, arr.length - 13);
      // }
      for (var i in arr) {
        dates.push(arr[i][0]);
        nums.push(arr[i][1]);
      }

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
            margin: 18,
            formatter: function(value) {
              return vm.shortDate(value);
            }
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
            let date = new Date(params.name);
            let dateStr = date.toLocaleDateString("en", {
              year: "numeric",
              month: "short",
              day: "numeric"
            });
            return (
              dateStr +
              "<div>" +
              vm.$t("dashboardDailyTransactionsSubtitle") +
              utility.numberAddComma(params.value) +
              "</div><div class=daily-echart-down-arrow></div>"
            );
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
.daily-chart {
  position: absolute;
  top: 90px;
  height: 300px;
  width: calc(100% - 30px);
  margin-left: 30px;
}
</style>
