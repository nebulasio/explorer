<template>
  <div class="dashboard-card col-12 col-lg-6">
    <div class="item-bg">
      <div class="header">
        <div class="title">
          <img class="logo" src="/static/img/nax-logo.png" />
          NAX Price
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
          <div class="col-6">
            <label>{{ $t("dashboardNasMarketCap") }}</label>
            <div>{{ marketCap }}</div>
          </div>
          <div class="col-6">
            <label>Total Supply</label>
            <div>{{ totalSupply }}</div>
          </div>
        </div>
        <div class="row">
          <div class="col-6">
            <label>{{ $t("dashboardNasMarketVol") }}</label>
            <div>{{ volume24h }}</div>
          </div>
          <div class="col-6">
            <label>Circulating Supply</label>
            <div>{{ totalCirculation }}</div>
          </div>
        </div>

        <div class="row bg-black">
          <div class="col-6">
            <label>dStaking NAS</label>
            <!-- <div v-if="market">${{ numberAddComma(market.volume24h) }}</div> -->
            <div>{{ totalStaking }}</div>

            <a target="__blank" href="https://dstaking.nebulas.io/"
              >dStake NAS and mint NAX now &gt;
            </a>
          </div>
          <div class="col-6">
            <label>dStaking Rate</label>
            <div>{{ stakingRate }}</div>
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
  name: "NaxMarketCard",
  data() {
    return {
      market: null
    };
  },
  mounted() {
    this.$api.home.getNaxMarket().then(res => (this.market = res));
  },
  computed: {
    updatedPass() {
      return moment(this.market.updatedAt).fromNow();
    },
    stakingRate() {
      return (this.market.stakingRate * 100).toFixed(2) + "%";
    },
    priceChange() {
      return this.market && `${toLocaleString(this.market.change24h)}%`;
    },
    marketCap() {
      return `$${toLocaleString(this.market.marketCap)}`;
    },

    volume24h() {
      return `$${toLocaleString(this.market.volume24h)}`;
    },
    totalSupply() {
      return convert2NaxStr(this.market.totalSupply);
    },
    totalCirculation() {
      return convert2NaxStr(this.market.totalCirculation);
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
    width: 25px;
    height: 25px;
    margin-right: 6px;
  }
}
.bg-black {
  background-color: black;
  padding: 1rem;
  margin: 0 -2rem;

  .col-6 {
    margin-bottom: 15px;
  }
}
</style>
