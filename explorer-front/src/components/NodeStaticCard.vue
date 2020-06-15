<template>
  <div class="market-price top-node-card flex-item col-12 col-lg-6 row1-item">
    <div class="item-bg">
      <div class="item-title">
        Avg. Annualized Rate of Return
      </div>
      <div class="details">
        {{ $t("dashboardNasPriceUpdateTimePrefix") }}
        <span v-if="market">{{
          timeConversion(Date.now() - market.createdAt)
        }}</span>

        {{ $t("dashboardNasPriceUpdateTimeSuffix") }}
      </div>
      <div v-if="market" class="detail">
        <span> </span>
        <span>{{ this.avgRewardRate }}</span>
      </div>
      <!-- market realtime data -->
      <div class="market container">
        <div class="row">
          <div class="col-6">
            Total Rewards:
            <div>9999999 NAS</div>
          </div>
          <div class="col-6">
            Total Nodes:
            <div>60</div>
            <a href="#">View all nodes &gt; </a>
          </div>
        </div>
        <div class="row">
          <div class="col-6">
            Voted for Nodes:
            <div>9999999 NAX</div>
          </div>
          <div class="col-6">
            Voting Rate:
            <div>40%</div>
          </div>
        </div>

        <div class="row border-top">
          <div class="col-6">
            Current period:
            <div>99455</div>
          </div>
          <div class="col-6">
            Current Govern period:
            <div>2</div>
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
    // this.$api.home.getNodeSummary().then(res => (this.summary = res));
  },
  computed: {
    updatedPass() {
      return moment(this.market.updatedAt).fromNow();
    },
    avgRewardRate() {
      return this.summary.avgRewardRate7 * 100 + "%";
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
