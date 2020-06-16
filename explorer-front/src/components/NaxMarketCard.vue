<template>
  <div class="market-price nax-price-card flex-item col-12 col-lg-6 row1-item">
    <div class="item-bg">
      <div class="item-title">
        <img class="logo" src="/static/img/nax-logo.png" />
        NAX Price
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
            <div>{{ marketCap }}</div>
          </div>
          <div class="col-6">
            Total Supply:
            <div>{{ totalSupply }}</div>
          </div>
        </div>
        <div class="row">
          <div class="col-6">
            {{ $t("dashboardNasMarketVol") }}
            <div>{{ volume24h }}</div>
          </div>
          <div class="col-6">
            Circulating Supply:
            <div>{{ totalCirculation }}</div>
          </div>
        </div>

        <div class="row border-top">
          <div class="col-6">
            dStaking NAS:
            <!-- <div v-if="market">${{ numberAddComma(market.volume24h) }}</div> -->
            <div>{{ totalStaking }}</div>

            <a target="__blank" href="https://dstaking.nebulas.io/"
              >dStake NAS and mint NAX now &gt;
            </a>
          </div>
          <div class="col-6">
            dStaking Rate:
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
.logo {
  width: 25px;
  height: auto;
  margin-right: 0.5rem;
}

.item-title {
  display: flex;
  align-items: center;
}

.market {
  .row {
    & > div {
      & > div {
        font-size: 20px;
      }
    }
  }
}
</style>
