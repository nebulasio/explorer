<template>
  <div class="monitor">
    <vue-bread title="Monitor Address"></vue-bread>

    <b-container>
      <h2 class="subtitle">
        NAS distribution and Addresses hold by Nebulas Foundation
      </h2>
      <div class="chart-container">
        <h3 class="title">
          Total Supply: {{ totalSupply }} | Total Circulation:
          {{ totalCirculation }} ｜ Price:
          {{ nasPrice }}
        </h3>
        <vchart
          class="pie-chart mt-5"
          :options="chartOption"
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
        :items="foundationAddrData"
      >
        <template v-slot:cell(address)="data">
          <router-link :to="'/address/' + data.value">
            <span class="monospace">{{ data.value }}</span>
          </router-link>
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

import { nebFoundationAddrs } from "@/config";
import { convert2NasStr, convert2NasNumber } from "@/utils/neb";

import { toLocaleString } from "@/utils/number";

import _ from "lodash";
import { chain } from "mathjs";

export default {
  name: "Monitor",
  components: {
    "vue-bread": vueBread,
    vchart: ECharts
  },
  async mounted() {
    const monitor_addresses = [];

    for (let addr of nebFoundationAddrs) {
      monitor_addresses.push(addr["address"]);
    }

    this.nasMarket = await this.$api.home.getNasMarket();

    // get monitor address balance
    this.addressBalance = await this.$api.monitor.getAddressBalance(
      monitor_addresses
    );
  },
  data() {
    return {
      nasMarket: null,
      addressBalance: null,
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
            key: "nasPercent",
            label: "%"
          },
          {
            key: "status",
            label: "Status"
          }
        ]
      }
    };
  },
  computed: {
    nasPrice() {
      return this.nasMarket && `$${this.nasMarket.price}`;
    },

    marketCap() {
      return this.nasMarket && `$${toLocaleString(this.nasMarket.marketCap)}`;
    },

    volume24h() {
      return this.nasMarket && `$${toLocaleString(this.nasMarket.volume24h)}`;
    },
    totalSupply() {
      return this.nasMarket && convert2NasStr(this.nasMarket.totalSupply);
    },
    totalCirculation() {
      return this.nasMarket && convert2NasStr(this.nasMarket.totalCirculation);
    },
    chartOption() {
      const legends = [
        "Nas Reserved for Community Ecosystem (Burned)",
        "DPoS adjustment (Burned)",
        "Nas Reserved for the Nebulas Team",
        "Go Nebulas Fund",
        "rest in circulating"
      ];
      let data = [];

      if (!this.foundationAddrData.length) {
        return null;
      }

      for (let name of legends) {
        let value = 0;

        // "Nas Reserved for Community Ecosystem (Burned)"
        if (name === legends[0]) {
          value = 35000000;
        }

        // "DPoS adjustment (Burned)"
        if (name === legends[1]) {
          value = 901356.75;
        }
        // "Nas Reserved for the Nebulas Team"
        if (name === legends[2]) {
          this.foundationAddrData.forEach(el => {
            if (el["key"] === "team") {
              value = chain(value)
                .add(el["nasAmount"])
                .done();
            }
          });
        }

        // "Go Nebulas Fund"
        if (name === legends[3]) {
          this.foundationAddrData.forEach(el => {
            if (el["key"] === "gn") {
              value = el["nasAmount"];
            }
          });
        }

        // "rest in circulating"
        if (name === legends[4]) {
          const totalCirculation = convert2NasNumber(
            this.nasMarket.totalCirculation
          );
          value = totalCirculation;

          for (let d of data) {
            value -= d["value"];
          }
        }

        data.push({
          value: value.toFixed(2),
          name
        });
      }

      console.log(data);

      const options = {
        color: [
          "#F8F9FE",
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
          formatter: "{b}: {c} NAS ({d}%)"
        },
        legend: {
          left: "center",
          top: "bottom",
          data: legends
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
            data
          }
        ]
      };

      return options;
    },
    foundationAddrData() {
      let data = [];

      for (let addr of nebFoundationAddrs) {
        if (!this.addressBalance) {
          break;
        }

        const findItem = _.find(this.addressBalance, {
          address: addr["address"]
        });

        // add find result to addr obj
        addr = Object.assign(addr, findItem);

        addr["nas"] = convert2NasStr(addr["nasBalance"]);
        addr["nasAmount"] = convert2NasNumber(addr["nasBalance"]);
        addr["nasPercent"] = `${(addr["nasPercent"] * 100).toFixed(2)}%`;

        data.push(addr);
      }

      return data;
    }
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
    width: 1200px;
    height: 500px;
  }
}
</style>
