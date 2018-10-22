<style>
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

</style>
<template>
    <!-- https://etherscan.io/address/0xea674fdde714fd979de3edf0f56aa9716b898ec8 -->
    <div class=vue-contract v-bind:triggedComputed=urlChange>
        <vue-bread v-if=obj v-bind:arr=breadcrumb v-bind:title='obj.tokenName' subtitle="(NRC20 Token)"></vue-bread>
        <div class=container v-if=obj>
            <table class="c333 table">
                <tr>
                    <th>
                        Overview
                    </th>
                    <th class=text-right>
                        <!-- * uncomment this img tag -->
                        <!-- <img src=%qrcode> -->
                    </th>
                </tr>
                <tr>
                    <td>Contract:</td>
                    <td>{{ obj.contract }} </td>
                </tr>
                <!-- <tr>
                    <td>Total supply:</td>
                    <td>{{ tokenAmount(obj.total)) }} {{ obj.tokenName }} </td>
                </tr> -->
                <tr>
                    <td>Holders:</td>
                    <td>{{ numberAddComma(obj.holderCount) }} addresses</td>
                </tr>
                <tr>
                    <td>Transfer:</td>
                    <td>{{ numberAddComma(obj.transactionCount) }}</td>
                </tr>
            </table>

            <vue-tab-buttons class=mt20 v-bind:arr=tabButtons v-bind:tab.sync=tab></vue-tab-buttons>
            <div class=mt20></div>

            <!--    Transactions
                ============================================================ -->
            <div class=tab v-show="tab == 1">
                <div class="align-items-center row title">
                    <div class=col>
                        <span class="c333 fa fa-sort-amount-desc" aria-hidden=true></span>
                        Latest {{ txs.length }} txns from a total Of
                        <router-link v-bind:to='fragApi + "/contract-txs?contract=" + $route.params.id'>{{ obj.transactionCount }} transactions </router-link>
                        <router-link v-bind:to='fragApi + "/contract-txs?contract=" + $route.params.id + "&isPending=true" '>( + {{ obj.pendingTransactionCount }} PendingTxn )</router-link>
                    </div>
                    <div class=col-auto>
                        <router-link class="btn btn-link" v-bind:to='fragApi + "/contract-txs?contract=" + $route.params.id'>View All {{ obj.transactionCount }} Txn</router-link>
                        |
                        <router-link class="btn btn-link" v-bind:to='fragApi + "/contract-txs?contract=" + $route.params.id + "&isPending=true" '>View All {{ obj.pendingTransactionCount }} PendingTxn</router-link>
                    </div>
                </div>

                <table class="mt20 table">
                    <tr>
                        <th>TxHash</th>
                        <th>Block</th>
                        <th>Age</th>
                        <th>From</th>
                        <th></th>
                        <th>To</th>
                        <th>Value</th>
                        <th class=txfee>[TxFee]</th>
                    </tr>

                    <tr v-for="o in txs" v-if="o" :key="o.hash">
                        <td v-if="o.status == 0" class=fail>
                            <router-link v-bind:to='fragApi + "/tx/" + o.hash'>{{ o.hash }}</router-link>
                        </td>
                        <td class=tdxxxwddd v-if="o.status != 0">
                            <router-link v-bind:to='fragApi + "/tx/" + o.hash'>{{ o.hash }}</router-link>
                        </td>
                        <td>
                            <router-link v-if=o.blockHeight v-bind:to='fragApi + "/block/" + o.blockHeight'>{{ o.blockHeight }}</router-link>
                            <i v-else>(pending)</i>
                        </td>
                        <td class=time>
                            <div>{{ timeConversion(Date.now() - o.timestamp) }} ago</div>
                            <div>{{ new Date(o.timestamp).toString() }} | {{ o.timestamp }}</div>
                        </td>
                        <td class=tdxxxwddd>
                            <vue-blockies v-bind:address='o.from'></vue-blockies>
                            <span v-if="o.from == $route.params.id">{{ o.from }}</span>
                            <router-link v-else v-bind:to='fragApi + "/address/" + o.from'>{{ o.from }}</router-link>
                        </td>
                        <td class=text-uppercase v-bind:class=inOutClass(o)></td>
                        <td class=tdxxxwddd>
                            <vue-blockies v-if="o.to" v-bind:address='o.to'></vue-blockies>
                            <span v-if="o.to == $route.params.id">{{ o.to }}</span>
                            <router-link v-else v-bind:to='fragApi + "/address/" + o.to'>{{ o.to }}</router-link>
                        </td>
                        <td>{{ tokenAmount(o.contractValue) }} {{ obj.tokenName }}</td>
                        <td class=txfee>
                            <span v-if=o.blockHeight>{{ toWei(o.txFee) }}</span>
                            <i v-else>(pending)</i>
                        </td>
                    </tr>
                </table>
            </div>

            <!-- =============== Holders =============== -->
            <div class=tab v-show="tab == 2">
                <div class="align-items-center info-and-pagination mt20 row">
                    <div class="col info">{{ totalHolderCount }} holders found</div>
                    <vue-pagination class=col-auto v-bind:current=currentPage v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext v-on:prev=onPrev v-on:to=onTo></vue-pagination>
                </div>

                <table class="mt20 table">
                    <tr>
                        <th>Rank</th>
                        <th>Address</th>
                        <th>Quantity</th>
                        <th>Percentage</th>
                    </tr>

                    <tr v-for="o in holders" :key="o.address">
                        <td>{{ o.rank }}</td>
                        <td class=tdxxxwddd>
                            <router-link v-bind:to='fragApi + "/address/" + o.address'>{{ o.address }}</router-link>
                        </td>
                        <td>{{ tokenAmount(o.balance) }}</td>
                        <td>{{ o.percentage }}%</td>
                    </tr>
                </table>

                <vue-pagination v-bind:current=currentPage right=1 v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext v-on:prev=onPrev v-on:to=onTo></vue-pagination>
            </div>

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
            formatCode() {
                var lang = prism.languages.javascript;

                if (this.obj.contractCode) {
                    return prism.highlight(jsBeautify(JSON.parse(this.obj.contractCode).Source), lang);
                }
                return "0x0";
            },
            breadcrumb() {
                return [
                    { text: "Home", to: "/" },
                    { text: "Normal Accounts", to: "/accounts" },
                    { text: this.obj.tokenName, to: "" }
                ];
            },
            tabButtons() {
                return ["Transfers"];//["Transfers", "Holders"]
            },
            urlChange() {
                this.$root.showModalLoading = true;
                api.getContract(this.$route.params.id, o => {
                    this.$root.showModalLoading = false;
                    this.obj = o.contract;
                    this.txs = o.txList;
                }, xhr => {
                    this.$root.showModalLoading = false;
                    this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404!" + this.$route.fullPath);
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
                totalHolderCount: 0
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
                    console.log(xhr);
                    this.$root.showModalLoading = false;
                    this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404!" + this.$route.fullPath);
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