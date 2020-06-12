<style scoped>
.clickable {
  cursor: pointer;
}

.mainnet-dip-banner {
  background: url(/static/img/dip_home_banner_bg.jpg?v=20190116) no-repeat
    bottom;
  background-size: cover;
}

.nova-logo {
  margin-top: 30px;
  margin-bottom: 30px;
  margin-left: 40px;
  margin-right: 30px;
  width: 120px;
  height: 120px;
}

.mainnet-dip-banner .detail {
  background: linear-gradient(
    to left,
    rgba(48, 35, 174, 1) 0%,
    rgba(139, 57, 180, 0) 60%
  );
}

.mainnet-dip-banner .title {
  margin-top: 38px;
}

.mainnet-dip-banner .mainnet-date {
  padding: 8px 30px 8px 74px;
  background: linear-gradient(
    to left,
    rgba(48, 35, 174, 1) 0%,
    rgba(200, 109, 215, 0) 100%
  );
}

.top3-notyet {
  height: 230px;
  margin-top: 21px;
  width: 100%;
  background: url(/static/img/dip_warmup_bg.jpg) no-repeat center;
  background-size: contain;
}

.viewall {
  border: 1px solid rgba(0, 0, 0, 1);
  margin-top: 40px;
  padding: 9px 48px;
  width: 225px;
  display: block;
  text-align: center;
}

.viewall:hover {
  color: white;
  text-decoration: none;
  background-color: black;
}

@media (max-width: 767.98px) {
  .top3-notyet {
    height: 170px;
  }

  .top3-notyet * {
    font-size: 18px;
  }
}

@media (max-width: 1199.98px) {
  .mainnet-dip-banner {
    background: url(/static/img/dip_home_banner_bg.jpg?v=20190116) no-repeat
      right;
    background-size: cover;
  }

  .mainnet-dip-banner .detail {
    background: linear-gradient(
      to left,
      rgba(48, 35, 174, 1) 0%,
      rgba(139, 57, 180, 0) 100%
    );
  }

  .mainnet-dip-banner .title {
    margin-top: 0px;
    font-size: 26px;
  }

  .mainnet-dip-banner .detail {
    padding: 30px 20px 20px 30px;
  }

  .mainnet-dip-banner .mainnet-date {
    padding: 0px;
    background: none;
    margin-top: 24px;
    font-size: 10px;
  }
}
</style>

<template>
  <div class="vue-dip-banner">
    <!-- <div v-if="$route.params.api != 'testnet'" class="clickable mainnet-dip-banner d-flex" @click=join>
            <img class="nova-logo d-none d-md-block" src="/static/img/nova_logo.png?v=20190116" alt="nova logo">
            <div class="detail flex-fill">
                <div class="title font-color-FFFFFF font-30 font-bold">Testnet Developer Incentive Program is in Progress</div>
                <div class="sub-detail d-block d-xl-flex align-items-center" style="margin-top: 24px;">
                    <div class="date font-color-00FFFF font-16 font-bold" style="margin-right: 18px;">Jan 21 - Mar 31 2019</div>
                    <div class="join font-color-00FFFF font-16 mr-auto d-inline-block mt-3 mt-xl-0" style="border: 1px #00FFFF solid; padding: 6px 30px;">{{ $root.testnetGotDipWinners? 'View DIP Winners' : 'Join Now' }}</div>
                    <div class="mainnet-date font-color-FFFFFF font-14">Mainnet DIP will release by end of March</div>
                </div>
            </div>
        </div> -->
    <!-- <div v-if="$route.params.api !== 'testnet' && !$root.mainnetGotDipWinners" class="testnet-dip-banner position-relative" style="padding: 28px 33px 22px 33px;">
            <div class="font-color-000000 font-20 font-bold">Mainnet Developer Incentive Program ( Jan 21 - Mar 31 2019 )</div>
            <div class="font-color-000000 font-16" style="margin-top: 6px;">Top 3 Contracts ( Jan 21 - Jan 28 2019 UTC+8 )</div>
            <div class="top3-notyet d-flex flex-column align-items-center justify-content-center">
                <div class="date font-color-000000 font-30 font-bold">Open on Jan 28, 2019</div>
                <a class="join font-color-0057FF font-20 font-bold mt-2 mt-md-4" href=# @click=join>Join Now ></a>
            </div>
        </div> -->
    <div
      v-if="$route.params.api !== 'testnet' && $root.mainnetGotDipWinners"
      class="testnet-dip-banner-winners font-color-000000"
      style="padding: 28px 33px 40px 33px;"
    >
      <div class="font-20 font-bold">
        Native Developer Incentive Protocol Awards
      </div>
      <div class="font-16" style="margin-top: 6px;">{{ subtitle }}</div>
      <div
        v-if="list && list.length > 0"
        class="top3 d-flex flex-column flex-md-row justify-content-around mt mt-md-5"
        style="amargin-top: 40px;"
      >
        <div
          v-for="(item, index) in list"
          :key="index"
          class="text-center mt-5 mt-md-0 col-12 col-md-4"
        >
          <img
            :src="'/static/img/dip_medal_' + index + '.png?v=20190116'"
            width="107px"
            alt="winner medal"
          />
          <div
            class="font-26 font-bold"
            style="margin-top: 12px; margin-bottom: 0px;"
          >
            {{ tokenAmount(item.award) }} NAS
          </div>
          <!-- <div class="font-color-4C4C4C font-16">{{ item.contract.shortHash() }}</div> -->
          <router-link
            class="font-color-4C4C4C font-16"
            v-bind:to="fragApi + '/address/' + item.contract"
            >{{ item.contract.shortHash() }}</router-link
          >
        </div>
      </div>
      <div v-else class="top3-notyet"></div>
      <router-link
        class="viewall font-color-000000 font-16 mx-auto"
        v-bind:to="fragApi + '/dip-leaderboard'"
        >View All Winners</router-link
      >
    </div>

    <div
      v-if="$route.params.api == 'testnet' && !$root.testnetGotDipWinners"
      class="testnet-dip-banner position-relative"
      style="padding: 28px 33px 22px 33px;"
    >
      <div class="font-color-000000 font-20 font-bold">
        Testnet Developer Incentive Program ( Jan 21 - Mar 31 2019 )
      </div>
      <div class="font-color-000000 font-16" style="margin-top: 6px;">
        Top 3 Contracts ( Jan 21 - Jan 28 2019 UTC+8 )
      </div>
      <div
        class="top3-notyet d-flex flex-column align-items-center justify-content-center"
      >
        <div class="date font-color-000000 font-30 font-bold">
          Open on Jan 28, 2019
        </div>
        <a
          class="join font-color-0057FF font-20 font-bold mt-2 mt-md-4"
          href="#"
          @click="join"
          >Join Now ></a
        >
      </div>
    </div>
    <div
      v-if="$route.params.api == 'testnet' && $root.testnetGotDipWinners"
      class="testnet-dip-banner-winners font-color-000000"
      style="padding: 28px 33px 40px 33px;"
    >
      <div class="font-20 font-bold">
        Native Developer Incentive Protocol Awards
      </div>
      <div class="font-16" style="margin-top: 6px;">{{ subtitle }}</div>
      <div
        v-if="list && list.length > 0"
        class="top3 d-flex flex-column flex-md-row justify-content-around mt mt-md-5"
        style="amargin-top: 40px;"
      >
        <div
          v-for="(item, index) in list"
          :key="index"
          class="text-center mt-5 mt-md-0 col-12 col-md-4"
        >
          <img
            :src="'/static/img/dip_medal_' + index + '.png?v=20190116'"
            width="107px"
            alt="winner medal"
          />
          <div
            class="font-26 font-bold"
            style="margin-top: 12px; margin-bottom: 0px;"
          >
            {{ tokenAmount(item.award) }} NAS
          </div>
          <!-- <div class="font-color-4C4C4C font-16">{{ item.contract.shortHash() }}</div> -->
          <router-link
            class="font-color-4C4C4C font-16"
            v-bind:to="fragApi + '/address/' + item.contract"
            >{{ item.contract.shortHash() }}</router-link
          >
        </div>
      </div>
      <div v-else class="top3-notyet"></div>
      <router-link
        class="viewall font-color-000000 font-16 mx-auto"
        v-bind:to="fragApi + '/dip-leaderboard'"
        >View All Winners</router-link
      >
    </div>
  </div>
</template>
<script>
var api = require("@/assets/api");
var BigNumber = require("bignumber.js");
var moment = require("@/assets/utility").moment;
module.exports = {
  data() {
    return {
      list: null,
      fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
      now: moment().utc(8),
      timer: null
    };
  },
  computed: {
    weekNumber() {
      return this.now.week();
    },
    subtitle() {
      if (!this.list || this.list.length === 0) {
        return "Top 3 Contracts";
      }
      let lastMonday = moment(this.now).weekday(-7);
      let lastSunday = moment(lastMonday).add(6, "days");
      return (
        "Top 3 Contracts ( " +
        lastMonday.format("MMM DD") +
        " - " +
        lastSunday.format("MMM DD YYYY") +
        " UTC+8 )"
      );
    }
  },
  methods: {
    join: function() {
      if (
        this.$route.params.api !== "testnet" &&
        this.$root.mainnetGotDipWinners
      ) {
        this.$router.push("/dip-leaderboard");
        location.reload();
      } else if (
        this.$route.params.api === "testnet" &&
        this.$root.testnetGotDipWinners
      ) {
        this.$router.push("/testnet/dip-leaderboard");
        location.reload();
      } else {
        let lang =
          navigator.languages && navigator.languages.length
            ? navigator.languages[0]
            : navigator.userLanguage ||
              navigator.language ||
              navigator.browserLanguage ||
              "en";
        if (lang.indexOf("zh") >= 0) {
          window.open(
            "https://blog.nebulas.io/2019/01/09/nebulas-testnet-developer-incentive-protocol-dip-event-guide",
            "_blank"
          );
        } else {
          window.open(
            "https://medium.com/nebulasio/nebulas-testnet-developer-incentive-program-dip-event-guide-26a0d69ec76d",
            "_blank"
          );
        }
      }
    },
    tokenAmount(n) {
      BigNumber.config({ DECIMAL_PLACES: 18 });
      var amount = BigNumber(n);
      var decimals = BigNumber("1e+18");
      return amount
        .div(decimals)
        .toFormat()
        .shortAmount();
    },
    getWinners() {
      if (
        (this.$route.params.api !== "testnet" &&
          this.$root.mainnetGotDipWinners) ||
        (this.$route.params.api === "testnet" &&
          this.$root.testnetGotDipWinners)
      ) {
        api.getDipList(
          {
            page: 1,
            pageSize: 3,
            week: this.weekNumber - 1,
            year: this.now.year()
          },
          o => {
            this.list = o.contracts;
            if (this.list.length === 0) {
              this.now = moment(this.now).subtract(7, "days");
            }
          }
        );
      }
    }
  },
  mounted() {
    this.getWinners();

    if (this.$root.mainnetGotDipWinners || this.$root.testnetGotDipWinners) {
      this.timer = setInterval(() => {
        this.now = moment().utc(8);
      }, 600000);
    }
  },
  watch: {
    weekNumber() {
      this.getWinners();
    }
  },
  destroyed() {
    clearInterval(this.timer);
  }
};
</script>
