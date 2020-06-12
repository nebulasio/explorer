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
      const option = {
        grid: {
          left: "40",
          bottom: "50",
          right: "17",
          top: "10",
          containLabel: false
        },
        xAxis: {
          type: "category",
          data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"]
        },
        yAxis: {
          type: "value"
        },
        series: [
          {
            data: [120, 200, 150, 80, 70, 110, 130],
            type: "bar",
            showBackground: true,
            backgroundStyle: {
              color: "rgba(220, 220, 220, 0.8)"
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
.dstaking-chart {
  position: absolute;
  top: 90px;
  height: 300px;
  width: calc(100% - 30px);
  margin-left: 30px;
}
</style>
