<template>
  <div class="market-price top-node-card flex-item col-12 col-lg-6 row1-item">
    <div class="item-bg">
      <div class="item-title">
        Avg. Annualized Rate of Return
      </div>
      <div v-if="updatedPass" class="details">
        {{ $t("dashboardNasPriceUpdateTimePrefix") }}
        <span>{{ updatedPass }}</span>
      </div>
      <div class="detail">
        <span> </span>
        <span>{{ avgRewardRate }}</span>
      </div>
      <!-- market realtime data -->
      <div class="market container">
        <div class="row">
          <div class="col-6">
            Total Rewards:
            <div>{{ totalRewardValue }}</div>
          </div>
          <div class="col-6">
            Total Nodes:
            <div>{{ nodeCount }}</div>
            <a href="#">View all nodes &gt; </a>
          </div>
        </div>
        <div class="row">
          <div class="col-6">
            Voted for Nodes:
            <div>{{ totalVoteValue }}</div>
          </div>
          <div class="col-6">
            Voting Rate:
            <div>{{ voteRate }}</div>
          </div>
        </div>

        <div class="row border-top">
          <div class="col-6">
            Current period:
            <div>{{ currentPeriod }}</div>
          </div>
          <div class="col-6">
            Current Govern period:
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

<style></style>
