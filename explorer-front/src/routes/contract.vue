<style>
    .vue-contract {
        background-color: white;
    }
    .vue-contract td.out {
        width: 50px;
    }

    .vue-contract td.in::before,
    .vue-contract td.out::before {
        border-radius: 4px;
        color: white;
        padding: 3px 5px;
    }

    .vue-contract td.in::before {
        background-color: var(--green);
        content: "in";
    }

    .vue-contract td.out::before {
        background-color: var(--orange);
        content: "out";
    }

    .vue-contract .container .table th {
        border-top: 0;
    }

    .vue-contract .container .title {
        overflow: auto;
    }

    .vue-contract .txfee {
        color: silver;
    }

    .vue-contract .tab a {
        font-size: 13px;
    }

    .vue-contract .fail {
        background: url(../../static/img/warning_icon.png)no-repeat 0 10px;
        padding-left: 28px;
    }

    .vue-contract .fail a {
        display: inline-block;
        max-width: 142px;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .vue-contract .tdxxxwddd {
      padding: .75rem 0.4rem;
    }

    .vue-contract .tdxxxwddd img {
      margin-right: 5px;
    }
    .td-left {
        width: 20%;
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
    <!-- https://etherscan.io/address/0xea674fdde714fd979de3edf0f56aa9716b898ec8 -->
    <div class="vue-contract fullfill" v-bind:triggedComputed=urlChange>
        <vue-bread v-if=obj v-bind:title='obj.tokenName' subtitle="[ NRC20 Token ]"></vue-bread>
        <div class="container explorer-table-container" v-if=obj>

            <table class="explorer-table font-size-16-normal">
                <div style="margin-top: 60px;margin-bottom: 30px;">
                    <span class="font-color-000000 font-size-24-bold table-title">
                        Overview
                    </span>
                </div>
                <tr>
                    <td class="font-color-555555 td-left" style="padding-left: 24px;">Total supply:</td>
                    <td class="font-color-000000">{{ tokenAmount(obj.total) }} {{ obj.tokenName }} </td>
                </tr>
                <tr v-if="tokenPrice">
                    <td class="font-color-555555" style="padding-left: 24px;">Price:</td>
                    <td>
                        <span class="font-color-000000">${{ tokenPrice.price }}</span>
                        <span :class='{"font-color-07A656": tokenPrice.trends === 1, "font-color-F04434": tokenPrice.trends != 1}'>(</span>

                        <img class="icon16" style="margin-top: -4px" v-if="tokenPrice.trends === 1" src="../../static/img/ic_exchange_rate_up.png" />
                        <img class="icon16" style="margin-top: -4px" v-else src="../../static/img/ic_exchange_rate_down.png" />

                        <span :class='{"font-color-07A656": tokenPrice.trends === 1, "font-color-F04434": tokenPrice.trends != 1}'>{{ tokenPrice.change24h + '%' }})</span>
                    </td>
                </tr>
                <tr>
                    <td class="font-color-555555" style="padding-left: 24px;">Holders:</td>
                    <td class="font-color-000000">{{ numberAddComma(obj.holderCount) }} addresses</td>
                </tr>
                <tr>
                    <td class="font-color-555555" style="padding-left: 24px;">Transfers:</td>
                    <td class="font-color-000000">{{ numberAddComma(obj.transactionCount) }}</td>
                </tr>
                <tr>
                    <td class="font-color-555555" style="padding-left: 24px;">Contract:</td>
                    <td>
                        <router-link v-bind:to='fragApi + "/address/" + obj.contract'>
                            <span class="font-color-0057FF">{{ obj.contract }}</span>
                        </router-link>
                    </td>
                </tr>
            </table>

            <div style="height: 30px;"></div>
            <vue-tab-buttons class=mt50 v-bind:arr=tabButtons v-bind:tab.sync=tab></vue-tab-buttons>
            <div class=mt20></div>

            <!--    Transactions
                ============================================================ -->
            <div class="tab explorer-table-container" v-show="tab === 1">
                <div class="align-items-center row title">
                    <div class=col>
                        <span class="c333 fa fa-sort-amount-desc" aria-hidden=true></span>
                        <span class="font-size-16-bold font-color-000000">
                            Latest {{ txs.length }} txns from a total of {{ obj.transactionCount }} transactions ( + {{ obj.pendingTransactionCount }} PendingTxn )
                        </span>
                    </div>
                    <div class=col-auto>
                        <router-link class="btn btn-link" v-bind:to='fragApi + "/contract-txs?contract=" + $route.params.id'>
                            <span class="font-color-0057FF font-size-16-normal">View All {{ obj.transactionCount }} Txn</span>
                        </router-link>
                        |
                        <router-link class="btn btn-link" v-bind:to='fragApi + "/contract-txs?contract=" + $route.params.id + "&isPending=true" '>
                            <span class="font-color-0057FF font-size-16-normal">View All {{ obj.pendingTransactionCount }} PendingTxn</span>
                        </router-link>
                    </div>
                </div>

                <table class="mt20 explorer-table list-table">
                    <tr class="font-color-000000 font-size-12-bold" style="height: 46px; background-color: #e8e8e8;">
                        <th></th>
                        <th>TxHash</th>
                        <th>Block</th>
                        <th>Age</th>
                        <th>From</th>
                        <th></th>
                        <th>To</th>
                        <th class="text-right">Value</th>
                        <th class="text-right">[TxFee]</th>
                    </tr>

                    <tr v-for="o in txs" v-if="o" :key="o.hash">
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
                        <td class="tdxxxwddd txs-from-to" style="padding: 0;">
                            <vue-blockies v-bind:address='o.from'></vue-blockies>
                            <!--<span class="fromTo" v-if="o.from === $route.params.id">{{ o.from }}</span>-->
                            <router-link v-bind:to='fragApi + "/address/" + o.from'>
                                <span class="fromTo font-size-14-normal font-color-0057FF">{{ o.from }}</span>
                            </router-link>
                        </td>
                        <td style="padding: 0;">
                            <img style="width:40px;height:40px" src="../../static/img/ic_arrow_right.png"/>
                        </td>
                        <td class="tdxxxwddd txs-from-to" style="padding: 0;">
                            <vue-blockies v-if="o.to" v-bind:address='o.to'></vue-blockies>
                            <!--<span class="fromTo" v-if="o.to === $route.params.id">{{ o.to }}</span>-->
                            <router-link v-bind:to='fragApi + "/address/" + o.to'>
                                <span class="fromTo font-size-14-normal font-color-0057FF">{{ o.to }}</span>
                            </router-link>
                        </td>
                        <td class="text-right font-color-000000 font-size-14-normal">{{ tokenAmount(o.contractValue) }} {{ obj.tokenName }}</td>
                        <td  class="text-right font-size-14-normal font-color-555555">
                            <span v-if=o.blockHeight>{{ toWei(o.txFee) }}</span>
                            <i v-else>(pending)</i>
                        </td>
                    </tr>
                </table>
            </div>

            <!-- =============== Holders =============== -->
            <div class="tab explorer-table-container" v-show="tab === 2">
                <div class="align-items-center info-and-pagination mt20 row">
                    <div class="col font-size-16-bold font-color-000000">
                        {{ totalHolderCount }} holders found
                    </div>
                </div>

                <table class="mt20 explorer-table list-table">
                    <tr class="list-header font-size-12-bold font-color-000000">
                        <th style="padding-left: 24px; width: 100px;">Rank</th>
                        <th style="width: 30%">Address</th>
                        <th class="text-right">Quantity</th>
                        <th class="text-right" style="padding-right: 24px;">Percentage</th>
                    </tr>

                    <tr v-for="o in holders" :key="o.address">
                        <td class="font-color-000000 font-size-14-bold" style="padding-left: 24px;">{{ o.rank }}</td>
                        <td class="tdxxxwddd">
                            <router-link style="max-width: 400px;" v-bind:to='fragApi + "/address/" + o.address'>
                                <span class="font-size-14-normal font-color-0057FF">{{ o.address }}</span>
                            </router-link>
                        </td>
                        <td class="text-right font-size-14-normal font-color-555555">{{ tokenAmount(o.balance) }}</td>
                        <td class="text-right font-size-14-normal font-color-000000" style="padding-right: 24px;">{{ o.percentage }}%</td>
                    </tr>
                </table>

                <vue-pagination v-bind:current=currentPage right=1 v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext v-on:prev=onPrev v-on:to=onTo></vue-pagination>
            </div>

            <!--    code
                ============================================================ -->
            <!-- <div class=tab v-show="tab == 3">
                <table class="mt20 table">
                    <tr>
                        <pre><code class=language-javascript v-html=formatCode></code></pre>
                    </tr>
                </table>
            </div> -->

        </div>
    </div>
</template>
<script>
    var api = require("@/assets/api"),
        prism = require("prismjs"),
        jsBeautify = require("js-beautify").js_beautify,
        utility = require("@/assets/utility"),
        BigNumber = require("bignumber.js");

    module.exports = {
        components: {
            "vue-bread": require("@/components/vue-bread").default,
            "vue-pagination": require("@/components/vue-pagination").default,
            "vue-tab-buttons": require("@/components/vue-tab-buttons").default,
            "vue-blockies": require("@/components/vue-blockies").default
        },
        computed: {
            // formatCode() {
            //     var lang = prism.languages.javascript;

            //     if (this.obj.contractCode) {
            //         return prism.highlight(jsBeautify(JSON.parse(this.obj.contractCode).Source), lang);
            //     }
            //     return "0x0";
            // },
            tabButtons() {
                return ["Transfers", "Holders"]
            },
            urlChange() {
                this.tab = 1;
                this.$root.showModalLoading = true;
                api.getContract(this.$route.params.id, o => {
                    this.$root.showModalLoading = false;
                    this.obj = o.contract;
                    this.txs = o.txList;
                    this.tokenPrice =  o.price ? {price: o.price, trends: o.trends, change24h: o.change24h} : null;
                }, xhr => {
                    this.$root.showModalLoading = false;
                    this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404");
                });
            }
        },
        data() {
            return {
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
                obj: null,
                tab: 0,
                txs: [],
                holders: [],
                currentPage: 0,
                totalPage: 0,
                totalHolderCount: 0,
                tokenPrice: null
            };
        },
        methods: {
            inOutClass(o) {
                if (o.from == this.$route.params.id)
                    return "out";
                else if (o.to == this.$route.params.id)
                    return "in";
                else
                    return "";
            },
            failClass(o) {
                if (o.status == 0)
                    return "!";
                else
                    return " ";
            },
            numberAddComma(n) {
                return utility.numberAddComma(n);
            },
            timeConversion(ms) {
                return utility.timeConversion(ms);
            },
            timeConversionSec(ms) {
                return utility.timeConversionSec(ms);
            },
            toWei(n) {
                return utility.toWei(n);
            },
            easyNumber(n) {
                return utility.easyNumber(n);
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
            nav(n) {
                this.$root.showModalLoading = true;

                api.getContractHolders({
                    contract: this.$route.params.id,
                    p: n
                }, o => {
                    this.$root.showModalLoading = false;
                    this.holders = o.holders;
                    this.currentPage = o.page;
                    this.totalPage = o.totalPageCount;
                    this.totalHolderCount = o.totalHolderCount;
                }, xhr => {
                    this.$root.showModalLoading = false;
                    this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404");
                });
            },
            tokenAmount(n) {
                BigNumber.config({ DECIMAL_PLACES: 18 })
                var amount = BigNumber(n);
                var decimals = BigNumber('1e+18');
                return amount.div(decimals).toFormat();
            }
        },
        watch: {
            tab: function (newTab, oldTaB) {
                if (newTab == 2 && this.currentPage == 0) {
                    this.nav(1);
                }
            }
        }
    };
</script>
