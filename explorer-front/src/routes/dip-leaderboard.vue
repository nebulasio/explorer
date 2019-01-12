<style scoped>
    .vue-txs {
        background-color: white;
        padding-top: 100px;
    }
    .tip a {
        color: rgb(76, 32, 133);
    }

    .hash-normal {
        height: 20px;
        font-size: 14px;
        color: rgba(0, 87, 255, 1);
        line-height: 20px;
    }

    .txs-hash {
        max-width: 185px;
        overflow: hidden;
        text-overflow: ellipsis;
        vertical-align: center;
        padding: 0;
    }

    .txs-block {
        max-width: 120px;
        overflow: hidden;
        text-overflow: ellipsis;
        vertical-align: center;
        padding: 0;
    }

    .fromTo {
        line-height: 24px;
    }

    .block {
        margin-right: 8px;
    }

    table {
        border:1px solid rgba(230,232,242,1);
    }

    .list-header {
        background-color: #F5F7FA !important;
    }

    tr {
        background-color: white !important;
        border-bottom:1px solid rgba(230,232,242,1);
    }

    .banner {
        position: relative;
        padding: 50px 0 39px 50px;
        background: url(/static/img/dip_list_banner_bg.jpg) no-repeat bottom;
        background-size: cover;
    }

    .banner .join:hover {
        text-decoration: none;
        background-color: #00FFFF;
        color: black;
    }

    .banner .nova:hover {
        color: #00FFFF;
        text-decoration-color: #00FFFF;
    }

    .nova-logo {
        position: absolute;
        top: 60px;
        right: 105px;
    }

    .explorer-table-container {
        margin-top: 30px;
    }

</style>
<template>
    <div class="vue-txs fullfill">
        <div class="container">
            <div class="banner font-color-FFFFFF">
                <div class="font-36 font-bold">Native Developer Incentive Protocol Awards</div>
                <div class="font-16" style="margin-top: 11px">*The DIP ranking automatically update every Monday.</div>
                <div class="font-22 font-bold" style="margin-top: 29px">Testnet Developer Incentive Program is in progress</div>
                <div class="font-16" style="margin-top: 4px">Event Period：Jan 21- Mar 31, 2019</div>
                <a class="join font-color-00FFFF font-16 d-inline-block mr-3" style="margin-top: 21px; border: 1px #00FFFF solid; padding: 6px 30px;" href="https://medium.com/nebulasio/nebulas-testnet-developer-incentive-program-dip-event-guide-26a0d69ec76d" target="blank">Join now</a>
                <a class="nova font-color-00FFFF font-16" href="https://nebulas.io/nova.html" target="blank">Lean about NOVA ></a>
                <img class="nova-logo" src="/static/img/nova_logo.png" width="169px" alt="nova">
            </div>

            <div v-if="arr && arr.length" class="explorer-table-container">
                <table class="mt20 explorer-table list-table">
                    <tr class="list-header font-12 font-bold font-color-000000">
                        <th class="px-3 text-center">Rank</th>
                        <th>Contract</th>
                        <th>Contract Address</th>
                        <th>Awards</th>
                        <th>Txhash</th>
                        <th class="text-right pr-3">Age</th>
                    </tr>

                    <tr v-for="(o, i) in arr" :key="i">
                        <td class="px-3 text-center">
                            <img v-if="i < 3" :src="'../../static/img/dip_list_' + i + '.png'" width="26px"/>
                            <div v-else class="">{{ i + 1 }}</div>
                        </td>
                        <td class="tdxxxwddd txs-from-to">
                            <div class="container-tip">
                                <span class="tip down-arrow-tip font-15 shadow">Smart Contract</span>
                                <img class="icon24" src="../../static/img/icon_tx_type_contract.png" />
                            </div>
                            <vue-blockies v-bind:address='o.to.alias || o.to.hash'></vue-blockies>
                            <!-- <span class="fromTo font-color-000000 font-14" v-if="o.to.hash === $route.query.a">{{ o.to.alias || o.to.hash }}</span> -->
                            <router-link v-bind:to='fragApi + "/address/" + o.to.hash'>
                                <span class="fromTo font-14  monospace">{{ o.to.hash }}</span>
                            </router-link>
                        </td>
                        <td class="tdxxxwddd txs-from-to">
                            <vue-blockies v-bind:address='o.from.alias || o.from.hash'></vue-blockies>
                            <!-- <span class="fromTo font-color-000000 font-14" v-if="o.from.hash === $route.query.a">{{ o.from.alias || o.from.hash }}</span> -->
                            <router-link v-bind:to='fragApi + "/address/" + o.from.hash'>
                                <span class="fromTo font-14  monospace">{{ o.from.hash }}</span>
                            </router-link>
                        </td>
                        <td>
                            122.0000 NAS
                        </td>
                        <td class="tdxxxwddd txs-from-to">
                            <vue-blockies v-bind:address='o.from.alias || o.from.hash'></vue-blockies>
                            <!-- <span class="fromTo font-color-000000 font-14" v-if="o.from.hash === $route.query.a">{{ o.from.alias || o.from.hash }}</span> -->
                            <router-link v-bind:to='fragApi + "/address/" + o.from.hash'>
                                <span class="fromTo font-14  monospace">{{ o.from.hash }}</span>
                            </router-link>
                        </td>
                        <td class="time font-14 font-color-555555 text-right pr-3">
                            <div>
                                <!-- <div>{{ timeConversion(o.timeDiff) }} ago</div> -->
                                <div>{{ timeFormatter(o.timestamp) }}</div>
                                <!-- <div class="down-arrow-tip">{{ new Date(o.timestamp).toString().replace('GMT', 'UTC').replace(/\(.+\)/gi, '') }} | {{ o.timestamp }}</div> -->
                            </div>
                        </td>
                    </tr>
                </table>
            </div>

            <vue-pagination v-bind:current=currentPage right=1 v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext
                v-on:prev=onPrev v-on:to=onTo></vue-pagination>
        </div>
        <vue-nothing v-if="arr && arr.length === 0" title="0 transaction found"></vue-nothing>
    </div>
</template>
<script>
    var api = require("@/assets/api"),
        utility = require("@/assets/utility"),
        BigNumber = require("bignumber.js");

    module.exports = {
        components: {
            "vue-bread": require("@/components/vue-bread").default,
            "vue-pagination": require("@/components/vue-pagination").default,
            "vue-blockies": require("@/components/vue-blockies").default,
            "vue-nothing": require("@/components/vue-nothing").default
        },
        data() {
            return {
                arr: null,
                currentPage: 0,
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
                maxDisplayCnt: 0,
                totalPage: 0,
                totalTxs: 0
            };
        },
        methods: {
            nav(n) {
                var query = JSON.parse(window.JSON.stringify(this.$route.query));

                query.p = n;
                this.$router.push({ path: this.$route.path, query });
            },
            nthPage() {
                this.$root.showModalLoading = true;

                api.getTx({
                    a: this.$route.query.a,
                    block: this.$route.query.block,
                    p: this.$route.query.p || 1,
                    isPending: this.$route.query.isPending
                }, o => {
                    this.$root.showModalLoading = false;
                    this.arr = o.txnList;
                    this.currentPage = o.currentPage;
                    this.maxDisplayCnt = o.maxDisplayCnt;
                    this.totalPage = o.totalPage;
                    this.totalTxs = o.txnCnt;
                }, xhr => {
                    this.$root.showModalLoading = false;
                    this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404");
                });
            },
            numberAddComma(n) {
                return utility.numberAddComma(n);
            },
            onFirst() {
                this.nav(1);
            },
            onLast() {
                this.nav(this.totalPage);
            },
            onNext() {
                this.nav(this.currentPage + 1);
            },
            onPrev() {
                this.nav(this.currentPage - 1);
            },
            onTo(n) {
                this.nav(n);
            },
            timeConversion(ms) {
                return utility.timeConversion(ms);
            },
            toWei(n) {
                return utility.toWei(n);
            },
            easyNumber(n) {
                return utility.easyNumber(n);
            },
            tokenAmount(n) {
                BigNumber.config({ DECIMAL_PLACES: 18 })
                var amount = BigNumber(n);
                var decimals = BigNumber('1e+18');
                return amount.div(decimals).toFormat().shortAmount();
            },
            timeFormatter(n) {
                var options = { year: 'numeric', month: 'short', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit' };
                return new Date(n).toLocaleDateString('en', options);
            }
        },
        mounted() {
            this.nthPage();
        },
        watch: {
            $route() {
                this.nthPage();
            }
        }
    };
</script>
