<template>
  <div class="dashboard-card col-12">
    <div class="item-bg">
      <div class="header">
        <div class="title">
          Nax distribution
        </div>
        <!-- <div class="details">
          <span @click="selectDate(d)" :key="i" v-for="(d, i) in [7, 15, 30]">
            {{ d }} d
          </span>
        </div> -->
      </div>

      <div class="chart-container">
        <vchart
          class="dstaking-chart"
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
import "echarts/lib/component/tooltip";
import "echarts/lib/component/legend";

import api from "@/assets/api";

import _ from "lodash";
import moment from "moment";

import { convert2NasNumber, convert2NaxNumber } from "@/utils/neb";
import { toBigNumString, toLocaleString } from "@/utils/number";

export default {
  name: "NebEcoChart",
  components: {
    vchart: ECharts
  },
  data() {
    return {
      data: null,
      summary: null,
      newBlock: null
    };
  },
  async mounted() {
    // get nax history
    const res = await this.$api.home.getNaxHistory();

    this.data = res.list;

    // console.log("getNaxHistory", this.data);

    // this.summary = await this.$api.home.getDStakingSummary();

    // this.newBlock = await this.$api.home.getNewBlock();
  },
  methods: {
    selectDate(d) {
      console.log(d);
    }
  },

  computed: {
    chartOptions() {
      if (!this.data) return null;

      const show_limit = 14;
      let data_limit = this.data.slice(0, show_limit).reverse();

      let dstakingData = [];
      let estimateData = [];
      let mintData = [];

      let total_burned_data = [];
      let total_minted_data = [];
      let voted_data = [];

      let price_nas_data = [];
      let price_nax_data = [];
      let rate_of_return_data = [];

      let dates = [];

      data_limit = data_limit.map(d => {
        let dstaking_day = convert2NasNumber(d.pledged_nas);
        let mint_day = convert2NaxNumber(d.distributed_nax);
        let estimate_day = convert2NaxNumber(d.estimate_nax);

        let total_burned_day = convert2NaxNumber(d.total_supplied_nax * 2); // data error
        let total_minted_day = convert2NaxNumber(d.total_supplied_nax);
        let voted_day = convert2NaxNumber(d.destroyed_nax); // fake

        let price_nas_day = d.nas_price;
        let price_nax_day = d.nax_price;
        let rate_of_return_day = d.nas_price * 3; //fake

        total_burned_data.push(total_burned_day);
        total_minted_data.push(total_minted_day);
        voted_data.push(voted_day);
        price_nas_data.push(price_nas_day);
        price_nax_data.push(price_nax_day);
        rate_of_return_data.push(rate_of_return_day);

        d["date"] = moment(d.end_timestamp).format("MMM D");
        d["rate_of_return"] = d.nas_price * 3; //fake

        dates.push(d["date"]);

        return d;
      });

      // const min_mint = _.min(mintData);
      // const min_dstaking = _.min(dstakingData);
      // const min_estimate = _.min(estimateData);

      // const max_mint = _.max(mintData);
      // const max_dstaking = _.max(dstakingData);
      // const max_estimate = _.max(estimateData);
      const max_price_nas = _.max(price_nas_data);
      const max_price_nax = _.max(price_nax_data);

      const tooltipFormatter = (params, ticket, callback) => {
        const findItem = _.find(data_limit, { date: params.name });

        // const dstaking_rate =
        //   (findItem.pledged_nas / findItem.total_supplied_nas) * 100;

        // const minted = toBigNumString(
        //   convert2NaxNumber(findItem.distributed_nax),
        //   2
        // );

        // const burned = toBigNumString(
        //   convert2NaxNumber(findItem.destroyed_nax),
        //   2
        // );

        // const dstaking_amount = toBigNumString(
        //   convert2NasNumber(findItem.pledged_nas)
        // );

        let price_nas = findItem.nas_price
          ? findItem.nas_price.toFixed(4)
          : "-";
        let price_nax = findItem.nax_price
          ? findItem.nax_price.toFixed(6)
          : "-";
        let rate_of_return = findItem.rate_of_return
          ? `${(findItem.rate_of_return * 100).toFixed(2)}%`
          : "-";

        const text = `
            <div>${params.name}</div>
            <div>Nas Price:${price_nas} USDT</div>
            <div>Nax Price:${price_nax} USDT</div>
            <div>Avg. Annualized Rate of Return:${rate_of_return}</div>
            <div class=echart-down-arrow></div>
          `;

        return text;
      };

      const legends = [
        "NAS Price USDT",
        "NAX Price USDT",
        "Avg. Annualized Rate of Return"
      ];

      const options = {
        grid: {
          left: "50",
          right: "80"
        },
        // title: {
        //   text: "堆叠区域图"
        // },
        legend: {
          left: "center",
          top: "top",
          icon: "roundRect",
          textStyle: {
            color: "#fff"
          },
          data: legends
        },
        color: ["#3DCC85", "#FF7733", "#595C63"],
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
            position: "right",
            axisLine: {
              show: false
            },
            max: max_price_nas,
            interval: 0.1,
            axisLabel: {
              textStyle: {
                color: "#3DCC85"
              },
              //   margin: 0,
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
            position: "right",
            offset: 30,
            // max: max_price_nax,
            interval: 0.0005,
            axisLine: {
              show: false
            },
            axisLabel: {
              textStyle: {
                color: "#FF7733"
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
            position: "left",
            // max: max_price_nax,
            interval: 0.1,
            axisLine: {
              show: false
            },
            axisLabel: {
              textStyle: {
                color: "#B2B2B2"
              },
              formatter: function(value, index) {
                return `${(value * 100).toFixed(0)}%`;
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
            name: legends[0],
            type: "line",
            smooth: true,
            data: price_nas_data
          },
          {
            name: legends[1],
            type: "line",
            smooth: true,
            data: price_nax_data,
            yAxisIndex: 1
          },
          {
            name: legends[2],
            type: "line",
            smooth: true,
            areaStyle: {},
            data: rate_of_return_data,
            yAxisIndex: 2
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
.details {
  display: flex;
  flex-direction: column;
}

.dstaking-chart {
  height: 600px;
  width: calc(100%);
}
</style>
