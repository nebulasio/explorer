<template>
  <div class="monitor">
    <vue-bread title="Monitor Address"></vue-bread>

    <b-container>
      <h2 class="subtitle">
        NAS distribution and Addresses hold by Nebulas Foundation
      </h2>
      <div class="chart-container">
        <h3 class="title">
          总量: 12932103 NAS | 流通量: 123123 NAS ｜ 币价: 0.25 USDT
        </h3>
        <vchart
          class="pie-chart mt-5"
          :options="nasVolOption"
          :autoResize="true"
        ></vchart>
      </div>
      <h2 class="subtitle">
        Addresses (Hold by Nebulas Foundation)
      </h2>
      <b-table
        striped
        hover
        :fields="foundationAddress.fields"
        :items="foundationAddress.items"
      >
        <template v-slot:cell(address)="data">
          <a href="#">{{ data.value }}</a>
        </template>
      </b-table>
    </b-container>
  </div>
</template>

<script>
import vueBread from "@/components/vue-bread";

import ECharts from "vue-echarts/components/ECharts";
import "echarts/lib/chart/pie";
import "echarts/lib/component/tooltip";
import "echarts/lib/component/legend";

export default {
  name: "Monitor",
  components: {
    "vue-bread": vueBread,
    vchart: ECharts
  },
  data() {
    return {
      nasVolOption: {
        color: [
          "#E6EAFB",
          "#4460E7",
          "#6A81EB",
          "#0BB160",
          "#2FBF79",
          "#59CC93",
          "#82D9AE",
          "#9FEBC5"
        ],
        tooltip: {
          trigger: "item",
          formatter: "{b}: {c} ({d}%)"
        },
        legend: {
          left: "center",
          top: "bottom",
          data: [
            "社区预留已销毁",
            "DPoS 增发超出部分已销毁",
            "团队预留",
            "dStaking NAS",
            "NAT 质押池",
            "其余流通中"
          ]
        },
        series: [
          {
            name: "访问来源",
            type: "pie",
            radius: ["25%", "55%"],
            label: {
              formatter: "{a|{b}}\n{b|{c} NAS} {per|{d}%}  ",
              rich: {
                a: {
                  color: "#000",
                  fontSize: 15,
                  lineHeight: 25
                },
                b: {
                  fontSize: 14,
                  color: "#000"
                },
                per: {
                  color: "#666",
                  backgroundColor: "#ddd",
                  padding: [2, 4],
                  borderRadius: 2
                }
              }
            },
            data: [
              { value: 35000000, name: "社区预留已销毁" },
              { value: 901356.75, name: "DPoS 增发超出部分已销毁" },
              { value: 11565577.38, name: "团队预留" },
              { value: 27143630, name: "dStaking NAS" },
              { value: 95609, name: "NAT 质押池" },
              { value: 52971302.23, name: "其余流通中" }
            ]
          }
        ]
      },
      foundationAddress: {
        fields: [
          {
            key: "address",
            label: "Address"
          },
          {
            key: "info",
            label: "Info"
          },
          {
            key: "nas",
            label: "NAS"
          },
          {
            key: "percent",
            label: "%"
          },
          {
            key: "status",
            label: "Status"
          }
        ],
        items: [
          {
            address: "n1ZbXBzCqmSRsidsD27RL2qcJa4DdwghX5t",
            info: "Nas Reserved for the Nebulas Team",
            nas: "4,999,999.91",
            percent: "4.6%",
            status: "Unreleased"
          },
          {
            address: "n1ZbXBzCqmSRsidsD27RL2qcJa4DdwghX5t",
            info: "Nas Reserved for the Nebulas Team",
            nas: "4,999,999.91",
            percent: "4.6%",
            status: "Unreleased"
          },
          {
            address: "n1ZbXBzCqmSRsidsD27RL2qcJa4DdwghX5t",
            info: "Nas Reserved for the Nebulas Team",
            nas: "4,999,999.91",
            percent: "4.6%",
            status: "Unreleased"
          }
        ]
      }
    };
  }
};
</script>

<style lang="scss" scoped>
.subtitle {
  margin-top: 30px;
  font-size: 24px;
  font-weight: 600;
  color: #000;
}

.chart-container {
  .title {
    margin-top: 15px;
    font-size: 20px;
  }

  .pie-chart {
    margin: 0 auto;
  }

  .echarts {
    width: 800px;
    height: 500px;
  }
}
</style>
