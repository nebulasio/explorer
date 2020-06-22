<template>
  <div class="dashboard-card  col-12 col-lg-6">
    <div class="item-bg">
      <div class="header">
        <div class="title">
          Avg. Annualized Rate of Return
          <b-icon
            v-b-tooltip.hover
            title="7天出块收益/nax平均投票金额*365/7天"
            icon="question-circle"
          ></b-icon>
        </div>
        <div v-if="updatedPass" class="details">
          {{ $t("dashboardNasPriceUpdateTimePrefix") }}
          <span>{{ updatedPass }}</span>
        </div>
      </div>

      <div class="detail">
        <span>{{ avgRewardRate }} </span>
      </div>
      <!-- market realtime data -->
      <div class="market container">
        <div class="row">
          <div class="col-6 col-md-4">
            <label>Total Rewards</label>
            <div>
              {{ totalRewardValue }}
            </div>
          </div>
          <div class="col-6 col-md-4">
            <label>Nodes</label>
            <div>{{ nodeCount }}</div>
          </div>
          <div class="col-6 col-md-4">
            <label>Polling Cycle</label>
            <div>{{ currentPeriod }}</div>
          </div>
        </div>
        <div class="row">
          <div class="col-6 col-md-4">
            <label>Voted for Nodes</label>
            <div>{{ totalVoteValue }}</div>
            <a href="#">View all nodes &gt; </a>
          </div>
          <div class="col-6 col-md-4">
            <label>Voting Rate</label>
            <div>{{ voteRate }}</div>
          </div>
          <div class="col-6 col-md-4">
            <label>Governance Cycle</label>
            <div>{{ currentGovPeriod }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import moment from "moment";
import { convert2NaxStr, convert2NasStr } from "@/utils/neb";
import { toLocaleString } from "@/utils/number";

export default {
  name: "NodeStaticCard",
  data() {
    return {
      summary: null
    };
  },
  mounted() {
    this.$api.home.getNodeSummary().then(res => (this.summary = res));
  },
  computed: {
    avgRewardRate() {
      return (
        this.summary && toLocaleString(this.summary.avgRewardRate7 * 100) + "%"
      );
    },
    totalRewardValue() {
      return (
        this.summary && toLocaleString(this.summary.totalRewardValue) + " NAS"
      );
    },
    totalVoteValue() {
      return (
        this.summary && toLocaleString(this.summary.totalVoteValue) + " NAX"
      );
    },
    voteRate() {
      return this.summary && toLocaleString(this.summary.voteRate * 100) + "%";
    },
    nodeCount() {
      return this.summary && this.summary.nodeCount;
    },
    currentPeriod() {
      return this.summary && this.summary.currentPeriod;
    },
    currentGovPeriod() {
      return this.summary && this.summary.currentGovPeriod;
    },
    updatedPass() {
      return this.summary && moment(this.summary.updatedTime).fromNow();
    }
  }
};
</script>

<style lang="scss" scoped></style>
