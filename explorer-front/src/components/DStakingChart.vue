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

import { convert2NasNumber, convert2NaxNumber } from "@/utils/neb";
import { toBigNumString } from "@/utils/number";

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
  async mounted() {
    // get nax history
    const res = await this.$api.home.getNaxHistory();

    this.data = res.list;
    console.log("getNaxHistory", this.data);
  },

  computed: {
    chartOptions() {
      if (!this.data) return null;

      const show_limit = 14;
      const data_limit = this.data.slice(0, show_limit);

      let dstakingData = [];
      let mintData = [];
      let dates = [];

      for (let d of data_limit) {
        let dstaking_day = convert2NasNumber(d.pledged_nas);
        let mint_day = convert2NaxNumber(d.distributed_nax);
        dstakingData.push(dstaking_day);
        mintData.push(mint_day);

        dates.push(d.stage);
      }

      let max_dstaking, min_dstaking;
      let max_mint, min_mint;

      min_mint = _.min(mintData);
      min_dstaking = _.min(dstakingData);

      max_mint = _.max(mintData);
      max_dstaking = _.max(dstakingData);

      const tooltipFormatter = (params, ticket, callback) => {
        const findItem = _.find(data_limit, { stage: parseInt(params.name) });

        const dstaking_rate =
          (findItem.pledged_nas / findItem.total_supplied_nas) * 100;

        const minted = toBigNumString(
          convert2NaxNumber(findItem.distributed_nax),
          2
        );

        const burned = toBigNumString(
          convert2NaxNumber(findItem.destroyed_nax)
        );

        const dstaking_amount = toBigNumString(
          convert2NasNumber(findItem.pledged_nas)
        );

        const text = `
            <div>${params.name}</div>
            <div>Minted:${minted} NAX</div>
            <div>Burned:${burned} NAX</div>
            <div>dStaking NAS:${dstaking_amount} NAS</div>
            <div>dStaking rate:${dstaking_rate.toFixed(2)}%</div>
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
            min: min_dstaking,
            max: max_dstaking,
            position: "left",
            axisLine: {
              show: false
            },
            axisLabel: {
              textStyle: {
                color: "#B2B2B2"
              },
              margin: 0,
              formatter: function(value, index) {
                return toBigNumString(value);
              }
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
            min: min_mint,
            max: max_mint,
            position: "right",
            axisLine: {
              show: false
            },
            axisLabel: {
              textStyle: {
                color: "#B2B2B2"
              },
              margin: 0,
              formatter: function(value, index) {
                return toBigNumString(value, 2);
              }
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
            data: dstakingData,
            itemStyle: {
              color: "#595C63"
            }
          },
          {
            type: "bar",
            name: "mint NAX",
            data: mintData,
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
