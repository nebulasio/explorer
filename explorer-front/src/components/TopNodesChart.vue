<template>
  <div class="dashboard-card col-12 col-lg-6">
    <div class="item-bg">
      <div class="header">
        <div class="title">
          Top 21 Nodes
        </div>
        <div v-if="data" class="details">
          <span>Current Polling Cycle: {{ currentPeriod }}</span>
        </div>
      </div>

      <div class="chart-container">
        <vchart
          class="top-nodes-chart"
          :options="chartOptions"
          :autoResize="true"
        ></vchart>
      </div>
    </div>
  </div>
</template>

<script>
import ECharts from "vue-echarts/components/ECharts";
import "echarts/lib/chart/bar";
import "echarts/lib/chart/line";
import "echarts/lib/component/tooltip";

import {
  convert2NasNumber,
  convert2NaxNumber,
  convert2NasStr,
  convert2NaxStr
} from "@/utils/neb";

import { toBigNumString, toLocaleString } from "@/utils/number";

import _ from "lodash";

export default {
  name: "TopNodesChart",
  components: {
    vchart: ECharts
  },
  data() {
    return {
      data: null
    };
  },
  async mounted() {
    // get top nodes data
    this.data = await this.$api.home.getTopNodes();
  },

  computed: {
    currentPeriod() {
      return this.data && toLocaleString(this.data[0].period);
    },
    chartOptions() {
      if (!this.data) {
        return null;
      }

      let chartData = _.sortBy(this.data, ["currentRanking"]);

      console.log("chartData", chartData);

      const limit = 21;
      let topNodesData = chartData.slice(0, limit).reverse();

      let nodes_data = [];
      let vote_nax_data = [];
      let reward_nas_data = [];

      topNodesData = topNodesData.map(d => {
        nodes_data.push(d.info.name);

        vote_nax_data.push(convert2NaxNumber(d.voteValue));

        d["total_reward"] =
          d.blockCount * 1.18912 + convert2NasNumber(d.govReward);
        reward_nas_data.push(d["total_reward"]);

        return d;
      });

      const reward_nas_max = _.max(reward_nas_data);
      const reward_nas_min = _.min(reward_nas_data);

      const vote_nax_max = _.max(vote_nax_data);
      const vote_nax_min = _.min(vote_nax_data);

      const tooltipFormatter = (params, ticket, callback) => {
        const findItem = _.find(topNodesData, o => {
          return o.info.name === params.name;
        });

        const vote = convert2NaxStr(findItem.voteValue);
        const total_reward = toLocaleString(findItem.total_reward);

        const text = `
            <div>${findItem.info.name}</div>
            <div>Vote: ${vote}</div>
            <div>Total Rewards: ${total_reward} NAS</div>
            <div class=echart-down-arrow></div>
          `;

        return text;
      };

      const option = {
        // tooltip: {
        //   trigger: "axis",
        //   axisPointer: {
        //     type: "shadow",
        //     crossStyle: {
        //       color: "#999"
        //     }
        //   }
        // },
        grid: {
          top: "5%",
          right: "20%"
        },

        yAxis: [
          {
            type: "category",
            data: nodes_data,
            axisLabel: {
              inside: true,
              textStyle: {
                color: "#fff"
              }
            },
            axisTick: {
              show: false
            },
            splitLine: {
              show: false
            },
            axisLine: {
              show: false
            },
            z: 10
          },
          // node order index
          {
            type: "category",
            name: "Order",
            data: _.rangeRight(1, limit + 1),
            position: "left",
            axisLabel: {
              textStyle: {
                color: "#fff"
              }
            },
            axisTick: {
              show: false
            },
            splitLine: {
              show: false
            },
            axisLine: {
              show: false
            }
          }
        ],
        xAxis: [
          {
            type: "value",
            name: "Voted",
            nameGap: 10,
            nameTextStyle: {
              color: "#B2B2B2"
            },
            position: "bottom",
            min: 0,
            max: vote_nax_max,
            interval: 50000000,
            axisLine: {
              show: false
            },
            axisLabel: {
              textStyle: {
                color: "#B2B2B2"
              },
              formatter: function(value, index) {
                return toBigNumString(value) + " NAX";
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
            name: "Reward",
            nameGap: 10,
            nameTextStyle: {
              color: "#3DCC85"
            },
            position: "top",
            // min: 0,
            // max: reward_nas_max,
            interval: 10000,
            axisLine: {
              show: false
            },
            axisLabel: {
              textStyle: {
                color: "#3DCC85"
              },
              formatter: function(value, index) {
                return `${toLocaleString(value)} NAS`;
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
            name: "voted",
            type: "bar",
            data: vote_nax_data,
            itemStyle: {
              color: "#595C63"
            }
          },
          {
            name: "reward",
            type: "line",
            xAxisIndex: 1,
            data: reward_nas_data,
            itemStyle: {
              color: "#3DCC85"
            }
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

      return option;
    }
  }
};
</script>

<style lang="scss" scoped>
// .item-bg {
//   @include media("<=desktop") {
//     padding: 0 1rem;
//   }
// }
.chart-container {
  margin-top: 0;
}
.top-nodes-chart {
  height: 600px;
  width: calc(100%);
}
</style>
