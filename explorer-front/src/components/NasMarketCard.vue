<template>
  <div class="market-price flex-item col-12 col-lg-6 row1-item">
    <div class="item-bg">
      <div class="item-title">
        <img class="logo" src="/static/img/nas-logo.png" />
        {{ $t("dashboardNasPriceTitle") }}
      </div>
      <div class="details">
        {{ $t("dashboardNasPriceUpdateTimePrefix") }}
        <span v-if="market">{{ this.updatedPass }}</span>
      </div>
      <div v-if="market" class="detail">
        <span>$</span>
        <span>{{ market.price }}</span>
        <span
          :class="{
            'price-down': market.trends <= 0,
            'price-up': market.trends > 0
          }"
          >{{ market.trends > 0 ? "+" : "-" }}{{ market.change24h }}%</span
        >
      </div>
      <!-- market realtime data -->
      <div class="market container">
        <div class="row">
          <div class="col-6">
            {{ $t("dashboardNasMarketCap") }}
            <div>{{ this.marketCap }}</div>
          </div>
          <div class="col-6">
            Total Supply:
            <div>{{ this.totalSupply }}</div>
          </div>
        </div>
        <div class="row">
          <div class="col-6">
            {{ $t("dashboardNasMarketVol") }}
            <div>{{ this.volume24h }}</div>
          </div>
          <div class="col-6">
            Circulating Supply:
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
.logo {
  width: 32px;
  margin-right: 0.5rem;
}

.item-title {
  display: flex;
}
</style>
