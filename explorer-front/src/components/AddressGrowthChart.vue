<template>
  <div class="chart-container">
    <vchart
      class="address-chart"
      :options="accountsChartOptions"
      :autoResize="true"
    ></vchart>
  </div>
</template>

<script>
import ECharts from "vue-echarts/components/ECharts";
import "echarts/lib/chart/line";
import "echarts/lib/component/tooltip";

import api from "@/assets/api";

export default {
  name: "AddressGrowthChart",
  components: {
    vchart: ECharts
  },
  data() {
    return {
      staticInfo: null
    };
  },
  mounted() {
    api.getStaticInfo(o => (this.staticInfo = o)); //合约数量、地址数量。。。
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
    accountsChartOptions() {
      if (
        !this.staticInfo ||
        !this.staticInfo.addressWeekList ||
        this.staticInfo.addressWeekList.length == 0
      ) {
        return null;
      }
      var arr = this.staticInfo.addressWeekList;
      var dates = [],
        nums = [];

      arr.sort(function(a, b) {
        return a.timestamp > b.timestamp;
      });
      if (arr.length > 8) {
        arr.splice(0, arr.length - 8);
      }

      for (var i in arr) {
        nums.push(arr[i].addressCount);
        dates.push(arr[i].timestamp);
      }

      let vm = this;
      var options = {
        grid: {
          left: "30",
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
              return vm.shortDate(new Number(value));
            }
          }
        },
        yAxis: {
          min: Math.floor(nums[0] / 1000) * 1000 - 1000,
          axisLine: {
            show: false
          },
          axisLabel: {
            textStyle: {
              color: "#B2B2B2"
            },
            margin: 0,
            formatter: function(value) {
              return value / 1000 + "k";
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: false
          },
          // splitNumber: 5,
          // maxInterval: 3000,
          minInterval: 1000
        },
        series: {
          type: "line",
          data: nums,
          smooth: true,
          symbol: "emptyCircle",
          symbolSize: 7,
          lineStyle: {
            color: "#0057FF",
            width: 3
          },
          itemStyle: {
            normal: {
              color: "#FFFFFF",
              borderWidth: 3,
              borderColor: "#0057FF"
            },
            emphasis: {
              color: "#FFFFFF",
              borderWidth: 3,
              borderColor: "#0057FF"
            }
          },
          areaStyle: {
            color: "#0057FF",
            opacity: 1
          }
        },
        tooltip: {
          trigger: "item",
          transitionDuration: 0,
          position: "top",
          formatter: function(params, ticket, callback) {
            let date = new Date(new Number(params.name));
            let dateStr = date.toLocaleDateString("en", {
              year: "numeric",
              month: "short",
              day: "numeric"
            });
            return (
              dateStr +
              "<div>" +
              dashboardAmountText +
              vm.numberAddComma(params.value) +
              "</div><div class=account-echart-down-arrow></div>"
            );
          },
          backgroundColor: "#0057FF",
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
.address-chart {
  position: absolute;
  top: 90px;
  width: calc(100% - 30px);
  height: 240px;
  margin-left: 30px;
}
</style>
