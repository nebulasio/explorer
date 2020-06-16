<template>
  <div class="market-price top-node-card flex-item col-12 col-lg-6 row1-item">
    <div class="item-bg">
      <div class="item-title">
        Avg. Annualized Rate of Return
      </div>
      <div class="details"></div>
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
            <div>-</div>
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
            <div>-</div>
          </div>
          <div class="col-6">
            Current Govern period:
            <div>-</div>
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
    }

    // stakingRate() {
    //   return this.market.stakingRate * 100 + "%";
    // },
    // marketCap() {
    //   return `$${toLocaleString(this.market.marketCap)}`;
    // },

    // volume24h() {
    //   return `$${toLocaleString(this.market.volume24h)}`;
    // },
    // totalSupply() {
    //   return convert2NaxStr(this.market.totalSupply);
    // },
    // totalCirculation() {
    //   return convert2NaxStr(this.market.totalCirculation);
    // },
    // totalStaking() {
    //   return convert2NasStr(this.market.totalStaking);
    // }
  }
};
</script>

<style></style>
