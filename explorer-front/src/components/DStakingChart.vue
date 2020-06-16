<template>
  <div
    class="daily-transactions market-price nax-price-card flex-item col-12 col-lg-6 row1-item"
  >
    <div class="item-bg">
      <div class="item-title">
        dStaking NAS and Minting NAX
      </div>
      <div class="details">
        <span v-if="nextMintBlock">Next Minted Block: {{ nextMintBlock }}</span>
        <span v-if="leftTime">Time Left: {{ leftTime }}</span>
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
      let estimateDate = [];
      let mintData = [];
      let dates = [];

      data_limit = data_limit.map(d => {
        let dstaking_day = convert2NasNumber(d.pledged_nas);
        let mint_day = convert2NaxNumber(d.distributed_nax);
        let estimate_day = convert2NaxNumber(d.estimate_nax);
        dstakingData.push(dstaking_day);
        estimateDate.push(estimate_day);
        mintData.push(mint_day);

        d["date"] = moment(d.end_timestamp).format("MMM D");

        dates.push(d["date"]);

        return d;
      });

      const min_mint = _.min(mintData);
      const min_dstaking = _.min(dstakingData);
      const min_estimate = _.min(estimateDate);

      const max_mint = _.max(mintData);
      const max_dstaking = _.max(dstakingData);
      const max_estimate = _.max(estimateDate);

      const tooltipFormatter = (params, ticket, callback) => {
        const findItem = _.find(data_limit, { date: params.name });

        const dstaking_rate =
          (findItem.pledged_nas / findItem.total_supplied_nas) * 100;

        const minted = toBigNumString(
          convert2NaxNumber(findItem.distributed_nax),
          2
        );

        const burned = toBigNumString(
          convert2NaxNumber(findItem.destroyed_nax),
          2
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
            // name: "estimate NAX",
            show: false,
            min: 0,
            max: max_estimate,
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
            name: "NAX",
            nameTextStyle: {
              color: "#B2B2B2"
            },
            min: 0,
            max: max_estimate,
            position: "right",
            axisLine: {
              show: false
            },
            axisLabel: {
              textStyle: {
                color: "#B2B2B2"
              },
              //   margin: 0,
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
            name: "estimate NAX",
            type: "bar",
            data: estimateDate,
            itemStyle: {
              color: "rgba(255,255,255,0.05)"
            }
          },
          {
            type: "bar",
            name: "mint NAX",
            data: mintData,
            yAxisIndex: 1,
            itemStyle: {
              color: "#595C63"
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
  margin-left: 0;
}
</style>
