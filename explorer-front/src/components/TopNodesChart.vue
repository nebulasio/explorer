<template>
  <div class="chart-container">
    <vchart
      class="top-nodes-chart"
      :options="chartOptions"
      :autoResize="true"
    ></vchart>
  </div>
</template>

<script>
import ECharts from "vue-echarts/components/ECharts";
import "echarts/lib/chart/bar";
import "echarts/lib/chart/line";
import "echarts/lib/component/tooltip";

import api from "@/assets/api";

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
  mounted() {
    // get dStaking chart data
  },

  computed: {
    chartOptions() {
      const fetch_data = [
        {
          id: "gateio",
          vote_nax: 100003,
          reward_nas: 321
        },
        {
          id: "tech",
          vote_nax: 100001,
          reward_nas: 34
        },
        {
          id: "naxone01",
          vote_nax: 10000,
          reward_nas: 166
        },
        {
          id: "gateio1",
          vote_nax: 8967,
          reward_nas: 124
        },
        {
          id: "tech2",
          vote_nax: 76754,
          reward_nas: 345
        },
        {
          id: "naxone013",
          vote_nax: 23478,
          reward_nas: 223
        },
        {
          id: "gateio13",
          vote_nax: 7895,
          reward_nas: 189
        },
        {
          id: "tech24",
          vote_nax: 2435,
          reward_nas: 280
        },
        {
          id: "naxone0135",
          vote_nax: 5667,
          reward_nas: 120
        }
      ];

      let nodes_data = [];
      let vote_nax_data = [];
      let reward_nas_data = [];
      let vote_nax_max = 0;

      for (let i = 0; i < fetch_data.length; i++) {
        nodes_data.push(fetch_data[i].id);
        vote_nax_data.push(fetch_data[i].vote_nax);
        reward_nas_data.push(fetch_data[i].reward_nas);

        if (fetch_data[i].vote_nax > vote_nax_max) {
          vote_nax_max = fetch_data[i].vote_nax;
        }
      }

      const option = {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
            crossStyle: {
              color: "#999"
            }
          }
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
            z: 10
          }
        ],
        xAxis: [
          {
            type: "value",
            // name: "vote nax",
            min: 0,
            max: vote_nax_max,
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
            // name: "reward nas",
            min: 0,
            max: 350,
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
            name: "vote nax",
            type: "bar",
            data: vote_nax_data,
            itemStyle: {
              color: "#595C63"
            }
          },
          {
            name: "reward nas",
            type: "line",
            xAxisIndex: 1,
            data: reward_nas_data,
            itemStyle: {
              color: "rgba(255,255,255,0.2)"
            }
          }
        ]
      };

      return option;
    }
  }
};
</script>

<style lang="scss" scoped>
.top-nodes-chart {
  position: absolute;
  top: 30px;
  height: 500px;
  width: calc(100% - 40px);
}
</style>
