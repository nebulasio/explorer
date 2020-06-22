<template>
  <div class="dashboard-card col-12 col-lg-6">
    <div class="item-bg">
      <div class="header">
        <div class="title">
          <img class="logo" src="/static/img/nas-logo.png" />
          <span>{{ $t("dashboardNasPriceTitle") }}</span>
        </div>
        <div class="details">
          {{ $t("dashboardNasPriceUpdateTimePrefix") }}
          <span v-if="market">{{ this.updatedPass }}</span>
        </div>
      </div>

      <div v-if="market" class="detail">
        <span class="prefix">$</span>
        <span>{{ market.price }}</span>
        <span
          :class="{
            'price-down': market.trends <= 0,
            'price-up': market.trends > 0
          }"
          >{{ market.trends > 0 ? "+" : "-" }}{{ priceChange }}</span
        >
      </div>
      <!-- market realtime data -->
      <div class="market">
        <div class="row">
          <div class="col-6 col-md-6">
            <label>{{ $t("dashboardNasMarketCap") }}</label>
            <div>{{ this.marketCap }}</div>
          </div>
          <div class="col-6 col-md-6">
            <label>Total Supply</label>
            <div>{{ this.totalSupply }}</div>
          </div>
        </div>
        <div class="row">
          <div class="col-6 col-md-6">
            <label>{{ $t("dashboardNasMarketVol") }}</label>
            <div>{{ this.volume24h }}</div>
          </div>
          <div class="col-6 col-md-6">
            <label>Circulating Supply</label>
            <div>{{ this.totalCirculation }}</div>
            <router-link class="link link-style" :to="fragApi + '/monitor/'">
              View NAS Distribution &gt;
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import moment from "moment";
import { convert2NasStr } from "@/utils/neb";
import { toLocaleString } from "@/utils/number";

export default {
  name: "NasMarketCard",
  data() {
    return {
      market: null
    };
  },
  mounted() {
    this.$api.home.getNasMarket().then(res => (this.market = res));
  },
  computed: {
    updatedPass() {
      return moment(this.market.updatedAt).fromNow();
    },
    stakingRate() {
      return this.market.stakingRate * 100 + "%";
    },
    marketCap() {
      return `$${toLocaleString(this.market.marketCap)}`;
    },
    priceChange() {
      return this.market && `${toLocaleString(this.market.change24h)}%`;
    },

    volume24h() {
      return `$${toLocaleString(this.market.volume24h)}`;
    },
    totalSupply() {
      return convert2NasStr(this.market.totalSupply);
    },
    totalCirculation() {
      return convert2NasStr(this.market.totalCirculation);
    },
    totalStaking() {
      return convert2NasStr(this.market.totalStaking);
    }
  }
};
</script>

<style lang="scss" scoped>
.title {
  display: flex;
  align-items: center;

  .logo {
    width: 32px;
    height: 32px;
    margin-right: 6px;
  }
}
</style>
