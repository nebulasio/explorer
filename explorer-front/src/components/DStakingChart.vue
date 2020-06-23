<template>
  <div class="dashboard-card col-12 col-lg-6">
    <div class="item-bg">
      <div class="header">
        <div class="title">
          NAX Distribution
        </div>
        <div class="details">
          <span v-if="nextMintBlock">Next Mint Block: {{ nextMintBlock }}</span>
          <span v-if="leftTime">Time Left: {{ leftTime }}</span>
        </div>
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
  name: "DStakingChart",
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

    this.summary = await this.$api.home.getDStakingSummary();

    this.newBlock = await this.$api.home.getNewBlock();
  },

  computed: {
    nextMintBlock() {
      return this.summary && toLocaleString(this.summary.endHeight);
    },

    leftTime() {
      if (!this.summary || !this.newBlock) {
        return null;
      }

      let nextIssueBlockHeight = this.summary.endHeight;
      let currentBlockHeight = this.newBlock[0].height;

      if (nextIssueBlockHeight - currentBlockHeight <= 0) {
        return "Distributing NAX Now";
      }
      var duration = moment.duration(
        (nextIssueBlockHeight - currentBlockHeight) * 15000,
        "milliseconds"
      );
      return (
        (duration.days() * 24 + duration.hours()).pad(2) +
        ":" +
        duration.minutes().pad(2) +
        ":" +
        duration.seconds().pad(2)
      );
    },

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

      let dates = [];

      data_limit = data_limit.map(d => {
        let dstaking_day = convert2NasNumber(d.pledged_nas);
        let mint_day = convert2NaxNumber(d.distributed_nax);
        let estimate_day = convert2NaxNumber(d.estimate_nax);

        let total_burned_day = convert2NaxNumber(d.total_destroyed_nax);
        let total_minted_day = convert2NaxNumber(d.total_distributed_nax);
        let voted_day = convert2NaxNumber(d.total_vote_nax);

        total_burned_data.push(total_burned_day);
        total_minted_data.push(total_minted_day);
        voted_data.push(voted_day);

        d["date"] = moment(d.end_timestamp).format("MMM D");

        dates.push(d["date"]);

        return d;
      });

      // const min_mint = _.min(mintData);
      // const min_dstaking = _.min(dstakingData);
      // const min_estimate = _.min(estimateData);

      // const max_mint = _.max(mintData);
      // const max_dstaking = _.max(dstakingData);
      // const max_estimate = _.max(estimateData);

      const tooltipFormatter = (params, ticket, callback) => {
        const findItem = _.find(data_limit, { date: params.name });

        const dstaking_rate =
          (findItem.pledged_nas / findItem.total_supplied_nas) * 100;

        const total_minted = toBigNumString(
          convert2NaxNumber(findItem.total_distributed_nax),
          2
        );

        const total_burned = toBigNumString(
          convert2NaxNumber(findItem.total_destroyed_nax),
          2
        );

        const total_voted = toBigNumString(
          convert2NaxNumber(findItem.total_vote_nax),
          2
        );

        const dstaking_amount = toBigNumString(
          convert2NasNumber(findItem.pledged_nas)
        );

        const text = `
            <div>${params.name}</div>
            <div>Total Minted:${total_minted} NAX</div>
            <div>Total Burned:${total_burned} NAX</div>
            <div>Total Voted:${total_voted} NAX</div>
            <div>dStaking NAS:${dstaking_amount} NAS</div>
            <div>dStaking rate:${dstaking_rate.toFixed(2)}%</div>
            <div class=echart-down-arrow></div>
          `;

        return text;
      };

      const legends = ["Total Burned NAX", "Total Minted NAX", "Voted NAX"];

      const options = {
        grid: {
          left: "12%",
          right: "3%"
        },
        // title: {
        //   text: "堆叠区域图"
        // },
        legend: {
          left: "center",
          top: "top",
          icon: "rect",
          textStyle: {
            color: "#fff",
            padding: [0, 15, 0, 0]
          },
          data: legends
        },
        color: ["#343B4B", "#3DCC85", "#0BB160"],
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
            // name: "mint NAX",
            // name: "NAX",
            nameTextStyle: {
              color: "#B2B2B2"
            },
            // min: 0,
            // max: max_estimate,
            position: "left",
            axisLine: {
              show: false
            },
            axisLabel: {
              textStyle: {
                color: "#B2B2B2"
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
          }
        ],
        series: [
          {
            name: legends[0],
            type: "line",
            areaStyle: {},
            data: total_burned_data
          },
          {
            name: legends[1],
            type: "line",
            areaStyle: {},
            data: total_minted_data
          },
          {
            name: legends[2],
            type: "line",
            areaStyle: {},
            data: voted_data
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
