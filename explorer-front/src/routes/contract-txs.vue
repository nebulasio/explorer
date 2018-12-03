<style>
    .vue-txs {
        background-color: white;
    }
    .vue-txs .tip a {
        color: rgb(76, 32, 133);
    }

    .vue-txs .info-and-pagination .info a {
        color: inherit;
    }

    .vue-txs td,
    .vue-txs th {
        border-top-color: #ddd;
    }

    .vue-txs .fail {
        background: url(../../static/img/warning_icon.png)no-repeat 0 10px;
        padding-left: 28px;
    }

    .vue-txs .fail a {
        display: inline-block;
        max-width: 142px;
        overflow: hidden;
        text-overflow: ellipsis;
    }
    .hash-normal {
        height: 20px;
        font-size: 14px;
        font-family: OpenSans;
        color: rgba(0, 87, 255, 1);
        line-height: 20px;
    }

    .hash-failed {
        height: 20px;
        font-size: 14px;
        font-family: OpenSans;
        line-height: 20px;
        color: rgba(240, 68, 52, 1);
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

    .txs-from-to {
        max-width: 168px;
    }

    .txs-from-to a {
        max-width: 134px;
    }

    .fromTo {
        max-width: 134px;
        height: 20px;
        margin-left: 10px;
        font-size: 14px;
        font-family: OpenSans;
        color: rgba(0, 87, 255, 1);
        line-height: 20px;
    }
</style>
<template>
    <!-- https://etherscan.io/txs -->
    <div class=vue-txs>
        <vue-bread v-bind:arr=breadcrumb v-bind:title=transactionsTitle></vue-bread>

        <div class="container mt20">
            <div class="align-items-center info-and-pagination mt20 row">
                <div class="col info font-color-000000 font-size-24-bold">{{ totalTxs }} transactions found</div>
            </div>

            <table class="mt20 explorer-table list-table">
                <tr class="list-header font-size-12-bold font-color-000000">
                    <th></th>
                    <th>TxHash</th>
                    <th>Block</th>
                    <th>Age</th>
                    <th>From</th>
                    <th></th>
                    <th>To</th>
                    <th class=text-right>Value</th>
                    <th class=text-right>TxFee</th>
                </tr>

                <tr v-for="(o, i) in arr" :key="i">
                    <td>
                        <img v-if="o.status===0" class="icon40" src="../../static/img/ic_tx_failed.png"/>
                    </td>
                    <td class="txs-hash">
                        <router-link v-bind:to='fragApi + "/tx/" + o.hash'>
                            <span v-bind:class="[o.status===0 ? 'hash-failed' : 'hash-normal']">{{ o.hash }}</span>
                        </router-link>
                    </td>

                    <td class="txs-block">
                        <router-link class="font-size-14-normal font-color-4560E6" v-if=o.blockHeight v-bind:to='fragApi + "/block/" + o.blockHeight'>
                            <span class="font-size-14-normal font-color-4560E6">{{ o.blockHeight }}</span>
                        </router-link>
                        <i class="font-size-14-normal font-color-000000" v-else>pending</i>
                    </td>
                    <td class="time font-size-14-normal font-color-555555">
                        <div>{{ timeConversion(Date.now() - o.timestamp) }} ago</div>
                        <div>{{ new Date(o.timestamp).toString() }} | {{ o.timestamp }}</div>
                    </td>
                    <td class="tdxxxwddd txs-from-to">
                        <router-link v-bind:to='fragApi + "/address/" + o.from'>
                            <span class="fromTo font-size-14-normal font-color-0057FF">{{ o.from }}</span>
                        </router-link>
                    </td>
                    <td>
                        <img style="width:40px;height:40px" src="../../static/img/ic_arrow_right.png"/>
                    </td>
                    <td class="tdxxxwddd txs-from-to">
                        <router-link v-bind:to='fragApi + "/address/" + o.to'>
                            <span class="fromTo font-size-14-normal font-color-0057FF">{{ o.to }}</span>
                        </router-link>
                    </td>
                    <td class="text-right font-color-000000 font-size-14-normal">{{ tokenAmount(o.contractValue) }} {{ o.tokenName }}</td>
                    <td class="text-right font-size-14-normal font-color-555555">{{ toWei(o.txFee) }}</td>
                </tr>
            </table>

            <vue-pagination v-bind:current=currentPage right=1 v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext
                v-on:prev=onPrev v-on:to=onTo></vue-pagination>
        </div>
    </div>
</template>
<script>
    var api = require("@/assets/api"),
        utility = require("@/assets/utility"),
        BigNumber = require("bignumber.js");

    module.exports = {
        components: {
            "vue-bread": require("@/components/vue-bread").default,
            "vue-pagination": require("@/components/vue-pagination").default
        },
        data() {
            return {
                arr: [],
                currentPage: 0,
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
                maxDisplayCnt: 0,
                totalPage: 0,
                totalTxs: 0,
                tokenName: null
            };
        },
        computed: {
            transactionsTitle: function () {
                return (this.tokenName ? this.tokenName + ' ' : '') + "Transactions";
            },
            breadcrumb: function () {
                return [
                    { text: "Home", to: "/" },
                    { text: this.transactionsTitle, to: "" }
                ];
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

                api.getContractTx({
                    contract: this.$route.query.contract,
                    p: this.$route.query.p || 1,
                    isPending: this.$route.query.isPending
                }, o => {
                    this.$root.showModalLoading = false;
                    this.arr = o.txnList;
                    this.currentPage = o.currentPage;
                    this.maxDisplayCnt = o.maxDisplayCnt;
                    this.totalPage = o.totalPage;
                    this.totalTxs = o.txnCnt;
                    this.tokenName = o.tokenName;
                }, xhr => {
                    console.log(xhr);
                    this.$root.showModalLoading = false;
                    this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404!" + this.$route.fullPath);
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
            tokenAmount(n) {
                BigNumber.config({ DECIMAL_PLACES: 18 })
                var amount = BigNumber(n);
                var decimals = BigNumber('1e+18');
                return amount.div(decimals).toFormat();
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
