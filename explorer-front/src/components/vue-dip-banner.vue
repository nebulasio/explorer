<style scoped>
    .clickable {
        cursor: pointer;
    }

    .mainnet-dip-banner {
        background: url(/static/img/dip_home_banner_bg.jpg) no-repeat bottom;
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

    .top3-notyet {
        height: 230px;
        margin-top: 21px;
        width: 100%;
        background: url(/static/img/dip_warmup_bg.png) no-repeat bottom;
        background-size: contain;
    }

    .viewall {
        border:1px solid rgba(0,0,0,1);
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

</style>

<template>
    <div class="vue-dip-banner">
        <div v-if="$route.params.api != 'testnet'" class="clickable mainnet-dip-banner d-flex" @click=join>
            <img class="nova-logo" src="/static/img/nova_logo.png" alt="nova logo">
            <div class="flex-fill" style="background:linear-gradient(to left,rgba(48,35,174,1) 0%,rgba(139,57,180,0) 60%);">
                <div class="title font-color-FFFFFF font-30 font-bold" style="margin-top: 38px;">Testnet Developer Incentive Program is in progress</div>
                <div class="detail d-flex align-items-center" style="margin-top: 24px;">
                    <div class="font-color-00FFFF font-16 font-bold" style="margin-right: 18px;">Jan 21 - Mar 31 2019</div>
                    <div class="font-color-00FFFF font-16 mr-auto" style="border: 1px #00FFFF solid; padding: 6px 30px;">Join now</div>
                    <div class="font-color-FFFFFF font-14" style="padding:8px 30px 8px 74px; background:linear-gradient(to left,rgba(48,35,174,1) 0%,rgba(200,109,215,0) 100%);">Mainnet DIP will release by end of March</div>
                </div>
            </div>
        </div>
        <div v-if="$route.params.api == 'testnet' && !$root.testnetGotDipWinners" class="testnet-dip-banner position-relative" style="padding: 28px 33px 22px 33px;">
            <div class="font-color-000000 font-20 font-bold">Testnet Developer Incentive Program ( Jan 21 - Mar 31 2019 )</div>
            <div class="font-color-000000 font-16" style="margin-top: 6px;">Top 3 Contracts ( Jan 21 - Jan 28 2019 UTC+8 )</div>
            <div class="top3-notyet d-flex flex-column align-items-center justify-content-center">
                <div class="font-color-000000 font-30 font-bold">Open on Jan 28, 2019</div>
                <a class="font-color-0057FF font-20 font-bold mt-4" href="https://medium.com/nebulasio/nebulas-testnet-developer-incentive-program-dip-event-guide-26a0d69ec76d" target="blank">Join now ></a>
            </div>
        </div>
        <div v-if="$route.params.api == 'testnet' && $root.testnetGotDipWinners" class="testnet-dip-banner font-color-000000" style="padding: 28px 33px 40px 33px;">
            <div class="font-20 font-bold">Testnet Developer Incentive Program ( Jan 21 - Mar 31 2019 )</div>
            <div class="font-16" style="margin-top: 6px;">Top 3 Contracts ( Jan 21 - Jan 28 2019 UTC+8 )</div>
            <div class="top3 d-flex justify-content-around" style="margin-top: 40px;">
                <div v-for="(item, index) in list" :key="index" class="text-center">
                    <img :src="'/static/img/dip_medal_' + index + '.png'" width="107px" alt="winner medal">
                    <div class="font-26 font-bold">999.9999 NAS</div>
                    <div class="font-color-4C4C4C font-16">n1SnyY…Yr7f9x</div>
                </div>
            </div>
            <router-link class="viewall font-color-000000 font-16 mx-auto" v-bind:to="fragApi + '/dip-leaderboard'">View All Winners</router-link>
        </div>
    </div>
</template>
<script>
var blockies = require("@/assets/blockies.min.js");
module.exports = {
    data() {
        return {
            list: [1, 2, 3],
            fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
        }
    },
    methods:{
        join: function () {
            window.open('https://medium.com/nebulasio/nebulas-testnet-developer-incentive-program-dip-event-guide-26a0d69ec76d', '_blank');
        }
    }
}
</script>
