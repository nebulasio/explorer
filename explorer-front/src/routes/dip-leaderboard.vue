<style scoped>
    .dip-leaderboard {
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
        padding: 50px 20px 39px 50px;
        background: url(/static/img/dip_list_banner_bg.jpg?v=20190117) no-repeat bottom;
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
        margin-top: 50px;
    }

    .week-label {
        margin-top: 80px;
        margin-right: 30px;
        width: 300px;
        padding: 11px 13px 11px 24px;
        border:1px solid rgba(230,232,242,1);
        cursor: pointer;
        transition: box-shadow 500ms;
    }

    .week-label:hover {
        box-shadow:0px 10px 20px 0px rgba(30,30,30,0.05);
    }

    #week-selector {
        position: absolute;
        margin-top: 20px;
        width: 300px;
        height: 340px;
        box-shadow:0px 10px 20px 0px rgba(30,30,30,0.05);
        transition: none;
        outline: none;
    }

    @media (max-width: 767.98px) {
        .dip-leaderboard {
            padding-top: 15px;
        }

        .banner {
            padding: 15px 15px;
        }

        .week-label {
            margin-top: 30px;
        }

        #week-selector {
            margin-top: 0px;
            top: 0px;
            left: 0px;
            width: 100%;
            height: 100%;
            background-color: rgba(30,30,30,0.3)
        }

        .explorer-table-container {
            margin-top: 20px;
        }

        .title {
            font-size: 20px;
        }

        .update {
            font-size: 10px;
        }

        .subtitle {
            font-size: 14px;
        }

        .date {
            font-size: 10px;
        }

        .join {
            font-size: 10px;
        }

        .nova {
            font-size: 10px;
        }
    }

</style>
<template>
    <div class="dip-leaderboard fullfill">
        <div class="container">
            <div class="banner font-color-FFFFFF">
                <img class="nova-logo d-none d-xl-block" src="/static/img/nova_logo.png?v=20190116" width="169px" alt="nova">
                <div class="title font-36 font-bold">Native Developer Incentive Protocol Awards</div>
                <div class="update font-16" style="margin-top: 11px; color: rgba(255, 255, 255, 0.7)">*The DIP ranking automatically update every Monday.</div>
                <div class="subtitle font-22 font-bold" style="margin-top: 29px">Testnet Developer Incentive Program is in Progress</div>
                <div class="date font-16" style="margin-top: 4px">Event Period：Jan 21- Mar 31, 2019</div>
                <a class="join font-color-00FFFF font-16 d-inline-block mr-3" style="margin-top: 21px; border: 1px #00FFFF solid; padding: 6px 30px;" href="https://medium.com/nebulasio/nebulas-testnet-developer-incentive-program-dip-event-guide-26a0d69ec76d" target="blank">Join Now</a>
                <a class="nova font-color-00FFFF font-16" href="https://nebulas.io/nova.html" target="blank">Learn about NOVA ></a>
            </div>

            <div class="week-label d-inline-flex justify-content-between align-items-center" data-toggle="collapse" data-target="#week-selector" aria-expanded="false" aria-controls="week-selector" @click="weekLabelClick($event)">
                <span class="font-color-000000 font-14 p-0 mr-auto">{{ weekText }}</span>
                <img class="p-0" src="/static/img/ic_payload_arrow_down.png" alt="" width="16px">
            </div>
            <div id="week-selector" class="collapse">
                <vue-week-selector class="test" :beginDate=beginDate v-model=beginDate tabindex=0 @blur=dismissWeekSelector @change="dismissWeekSelector"></vue-week-selector>
            </div>
            <span v-if="totalContract && totalAward" class="font-14 font-color-666666 d-block d-md-inline mt-4 mt-md-0">
                <span class="font-bold font-color-333333">{{ totalContract }}</span>
                contracts get a reward of
                <span class="font-bold font-color-333333">{{ tokenAmount(totalAward) }}</span>
                NAS
            </span>

            <div v-if="arr && arr.length" class="explorer-table-container">
                <table class="explorer-table list-table">
                    <tr class="list-header font-12 font-bold font-color-000000">
                        <th class="px-3 text-center">Rank</th>
                        <th>Contract</th>
                        <th>Creator Address</th>
                        <th>Awards</th>
                        <th>Txhash</th>
                        <th class="text-right pr-3">Age</th>
                    </tr>

                    <tr v-for="(o, i) in arr" :key="i">
                        <td class="px-3 text-center">
                            <img v-if="o.rank < 3" :src="'../../static/img/dip_list_' + o.rank + '.png'" width="26px"/>
                            <div v-else class="">{{ o.rank + 1 }}</div>
                        </td>
                        <td class="tdxxxwddd txs-from-to">
                            <div class="container-tip">
                                <span class="tip down-arrow-tip font-15 shadow">Smart Contract</span>
                                <img class="icon24" src="../../static/img/icon_tx_type_contract.png" />
                            </div>
                            <vue-blockies v-bind:address='o.contract'></vue-blockies>
                            <router-link v-bind:to='fragApi + "/address/" + o.contract'>
                                <span class="fromTo font-14  monospace">{{ o.contract }}</span>
                            </router-link>
                        </td>
                        <td class="tdxxxwddd txs-from-to">
                            <vue-blockies v-bind:address='o.creator'></vue-blockies>
                            <router-link v-bind:to='fragApi + "/address/" + o.creator'>
                                <span class="fromTo font-14  monospace">{{ o.creator }}</span>
                            </router-link>
                        </td>
                        <td>
                            {{ tokenAmount(o.award) }} NAS
                        </td>
                        <td class="tdxxxwddd txs-from-to">
                            <!-- <vue-blockies v-bind:address='o.txHash'></vue-blockies> -->
                            <router-link v-bind:to='fragApi + "/tx/" + o.txHash'>
                                <span class="fromTo font-14  monospace">{{ o.txHash }}</span>
                            </router-link>
                        </td>
                        <td class="time font-14 font-color-555555 text-right pr-3">
                            <div>
                                <!-- <div>{{ timeConversion(o.timeDiff) }} ago</div> -->
                                <div>{{ timeFormatter(o.txTimestamp) }}</div>
                                <!-- <div class="down-arrow-tip">{{ new Date(o.timestamp).toString().replace('GMT', 'UTC').replace(/\(.+\)/gi, '') }} | {{ o.timestamp }}</div> -->
                            </div>
                        </td>
                    </tr>
                </table>
            </div>

            <vue-pagination v-if="arr && arr.length" v-bind:current=currentPage right=1 v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext
                v-on:prev=onPrev v-on:to=onTo></vue-pagination>
        </div>
        <vue-nothing v-if="arr && arr.length === 0" title="no ranking data"></vue-nothing>
    </div>
</template>
<script>
    var api = require("@/assets/api"),
        utility = require("@/assets/utility"),
        BigNumber = require("bignumber.js"),
        moment = require("@/assets/utility").moment;

    module.exports = {
        components: {
            "vue-bread": require("@/components/vue-bread").default,
            "vue-pagination": require("@/components/vue-pagination").default,
            "vue-blockies": require("@/components/vue-blockies").default,
            "vue-nothing": require("@/components/vue-nothing").default,
            "vue-week-selector": require("@/components/vue-week-selector").default
        },
        data() {
            return {
                arr: null,
                currentPage: 0,
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
                totalPage: 0,
                totalContract: 0,
                totalAward: 0,
                beginDate: moment().utc(8).weekday(-7),
                firstFetch: true
            };
        },
        computed: {
            weekText: function () {
                let endDate = moment(this.beginDate).add(6, 'days');
                return this.beginDate.format("MMM DD") + " - " + endDate.format("MMM DD YYYY") + " UTC+8";
            }
        },
        methods: {
            nav(n) {
                var query = JSON.parse(window.JSON.stringify(this.$route.query));

                query.p = n;
                this.$router.push({ path: this.$route.path, query });
            },
            nthPage() {
                this.$root.showModalLoading = true;
                api.getDipList({
                    page: this.$route.query.p || 1,
                    pageSize: 10,
                    week: this.beginDate.week(),
                    year: this.beginDate.year()
                }, o => {
                    if (o.contracts && o.contracts.length > 0) {
                        this.$root.showModalLoading = false;
                        this.arr = o.contracts;
                        this.currentPage = o.currentPage;
                        this.totalPage = o.totalPage;
                        this.totalContract = o.total;
                        this.totalAward = o.totalAward;
                        this.firstFetch = false;
                    } else if (this.firstFetch) {
                        this.beginDate = moment(this.beginDate).subtract(7, 'days');
                    } else {
                        this.$root.showModalLoading = false;
                        this.arr = [];
                        this.totalContract = 0;
                        this.totalAward = 0;
                    }
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
                return moment(n).format('MMM DD, YYYY, HH:mm:ss');
            },
            dismissWeekSelector() {
                $('#week-selector').collapse('hide');
                $('.week-label').css('pointer-events', 'initial');
            },
            weekLabelClick(event) {
                this.$nextTick(function () {
                    
                });

                setTimeout(() => {
                    $('.vue-week-selector').focus();
                    $('.week-label').css('pointer-events', 'none');
                }, 0);
            }
        },
        mounted() {
            this.nthPage();
        },
        watch: {
            $route() {
                this.nthPage();
            },
            beginDate() {
                this.nav(1);
                this.nthPage();
            }
        }
    };
</script>
