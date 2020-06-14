<template>
  <div class="chart-container">
    <vchart
      class="dstaking-chart"
      :options="chartOptions"
      :autoResize="true"
    ></vchart>
  </div>
</template>

<script>
import ECharts from "vue-echarts/components/ECharts";
import "echarts/lib/chart/bar";
import "echarts/lib/component/tooltip";

import api from "@/assets/api";

import _ from "lodash";

export default {
  name: "DStakingChart",
  components: {
    vchart: ECharts
  },
  data() {
    return {
      data: null
    };
  },
  mounted() {
    // get dStaking chart data
  },

  computed: {
    chartOptions() {
      const chartData = [120, 200, 150, 80, 70, 110, 130];
      const dates = ["6.1", "6.2", "6.3", "6.4", "6.5", "6.6", "6.7"];

      const fetch_data = [
        {
          date: "6.1",
          dstaking_nas: 10,
          mint_nax: 101,
          burned: 5,
          rate: 0.12,
          nax_price: 0.01
        },
        {
          date: "6.2",
          dstaking_nas: 11,
          mint_nax: 1021,
          burned: 5,
          rate: 0.43,
          nax_price: 0.01
        },
        {
          date: "6.3",
          dstaking_nas: 12,
          mint_nax: 1011,
          burned: 5,
          rate: 0.44,
          nax_price: 0.01
        },
        {
          date: "6.4",
          dstaking_nas: 10,
          mint_nax: 101,
          burned: 5,
          rate: 0.4,
          nax_price: 0.01
        },
        {
          date: "6.5",
          dstaking_nas: 11,
          mint_nax: 1021,
          burned: 5,
          rate: 0.4,
          nax_price: 0.01
        },
        {
          date: "6.6",
          dstaking_nas: 12,
          mint_nax: 1011,
          burned: 5,
          rate: 0.4,
          nax_price: 0.01
        },
        {
          date: "6.7",
          dstaking_nas: 12,
          mint_nax: 1011,
          burned: 5,
          rate: 0.4,
          nax_price: 0.01
        }
      ];

      let dataShadow = [];

      for (var i = 0; i < chartData.length; i++) {
        dataShadow.push(chartData[i] * 10);
      }

      const tooltipFormatter = (params, ticket, callback) => {
        const findItem = _.find(fetch_data, { date: params.name });

        const text = `
        <div>${params.name}</div>
        <div>Minted:${findItem.mint_nax}</div>
        <div>Burned:${findItem.burned}</div>
        <div>dStaking NAS:${findItem.dstaking_nas}</div>
        <div>dStaking rate:${findItem.rate}</div>
        <div>NAX Price:${findItem.nax_price}</div>
        <div class=echart-down-arrow></div>
      `;

        return text;
      };

      const options = {
        xAxis: {
          data: dates,
          axisLabel: {
            textStyle: {
              color: "#B2B2B2"
            }
          },
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          }
        },
        yAxis: [
          {
            type: "value",
            // name: "dstaking NAS",
            min: 0,
            max: 250,
            position: "left",
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
          {
            type: "value",
            // name: "mint NAX",
            min: 0,
            max: 2000,
            position: "right",
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
          }
        ],
        series: [
          {
            name: "dstaking NAS",
            type: "bar",
            data: chartData,
            itemStyle: {
              color: "#595C63"
            }
          },
          {
            type: "bar",
            name: "mint NAX",
            data: dataShadow,
            yAxisIndex: 1,
            itemStyle: {
              color: "rgba(255,255,255,0.05)"
            },
            barGap: "-100%",
            barCategoryGap: "40%"
          }
        ],
        tooltip: {
          trigger: "item",
          transitionDuration: 0,
          position: "top",
          formatter: tooltipFormatter,
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
.dstaking-chart {
  position: absolute;
  top: 30px;
  height: 500px;
  width: calc(100% - 20px);
  margin-left: 10px;
}
</style>
