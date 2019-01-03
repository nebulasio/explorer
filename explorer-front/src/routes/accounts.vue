<style>
    .vue-accounts {
        background-color: white;
    }

    .vue-accounts .block {
        margin-right: 8px;
    }

    @media (min-width: 576px) {
        .vue-accounts .tdxxxwddd>* {
            max-width: initial;
        }
    }

</style>
<template>
    <!-- https://etherscan.io/accounts  -->
    <div class="vue-accounts fullfill">
        <vue-bread title="Top Accounts By NAS Balance"></vue-bread>
        <div class="mt20 container">
            <div class="align-items-center info-and-pagination row">
                <div class="col-auto pr-0 font-color-000000 font-24 font-bold">
                    {{ numberAddComma(totalAccounts) }} accounts found 
                </div>
                <span v-if="totalAccounts > 10000" class="col-auto font-color-555555 font-16">(showing the last 10,000 top accounts)</span>
            </div>
            <div class="explorer-table-container">
                <table class="mt20 explorer-table list-table">
                    <tr class="list-header font-12 font-bold font-color-000000">
                        <th style="padding-left: 24px;">Rank</th>
                        <th>Address</th>
                        <th class=text-right>Balance</th>
                        <th class=text-right>Percentage</th>
                        <th class=text-right style="padding-right: 24px;">TxCount</th>
                    </tr>
                    <tr v-for="(o, i) in arr" :key="i" class="font-14">
                        <td style="padding-left: 24px;" class="font-color-000000">{{ o.rank }}</td>
                        <td class="tdxxxwddd">
                            <vue-blockies v-bind:address='o.hash'></vue-blockies>
                            <router-link v-bind:to='fragApi + "/address/" + o.hash'>
                                <span class="font-color-0057FF">{{ o.hash }}</span>
                            </router-link>
                            <span v-show=o.alias> | {{ o.alias }}</span>
                        </td>
                        <td class="text-right font-color-555555">{{ nasAmount(o.balance) }}</td>
                        <td class="text-right font-color-555555">{{ new Number(o.percentage).toFixed(4) }}%</td>
                        <td class="text-right font-color-555555" style="padding-right: 24px;">{{ numberAddComma(o.txCnt) }}</td>
                    </tr>
                </table>
            </div>
            <vue-pagination v-bind:current=currentPage right=1 v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext v-on:prev=onPrev v-on:to=onTo></vue-pagination>
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
            "vue-pagination": require("@/components/vue-pagination").default,
            "vue-blockies": require("@/components/vue-blockies").default
        },
        data() {
            return {
                arr: [],
                currentPage: 0,
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
                totalAccounts: 0,
                totalBalance: 0,
                totalPage: 0
            };
        },
        methods: {
            nthPage() {
                var p = this.$route.query.p || 1;

                if (p == this.currentPage)
                    console.log("nthPage - 请求的第", p, "页正是当前页, 忽略此次调用");
                else {
                    this.$root.showModalLoading = true;

                    api.getAccount(p, o => {
                        this.$root.showModalLoading = false;
                        this.arr = o.addressList;
                        this.currentPage = o.page;
                        this.totalAccounts = o.totalAccountsCnt;
                        this.totalBalance = o.totalBalance;
                        this.totalPage = o.totalPage;

                        if (this.arr.length) {
                            this.heightFrom = this.arr[0].height;
                            this.heightTo = this.arr[this.arr.length - 1].height;
                        } else {
                            this.heightFrom = 0;
                            this.heightTo = 0;
                        }
                    }, xhr => {
                        this.$root.showModalLoading = false;
                        this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404");
                    });
                }
            },
            numberAddComma(n) {
                return utility.numberAddComma(n);
            },
            toWei(n) {
                return utility.toWei(n);
            },
            onFirst() {
                this.$router.push({
                    path: this.$route.path,
                    query: { p: 1 }
                });
            },
            onLast() {
                this.$router.push({
                    path: this.$route.path,
                    query: { p: this.totalPage }
                });
            },
            onNext() {
                this.$router.push({
                    path: this.$route.path,
                    query: { p: this.currentPage + 1 }
                });
            },
            onPrev() {
                this.$router.push({
                    path: this.$route.path,
                    query: { p: this.currentPage - 1 }
                });
            },
            onTo(n) {
                this.$router.push({
                    path: this.$route.path,
                    query: { p: n }
                });
            },
            nasAmount(n) {
                BigNumber.config({DECIMAL_PLACES: 8})
                var amount = BigNumber(n);
                var decimals = BigNumber('1e+18');
                return amount.div(decimals).toFormat().shortAmount() + ' NAS';
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
