<style>
    .vue-txs-pending {
        background-color: white;
    }
</style>
<template>
    <!-- https://etherscan.io/txsPending -->
    <div class="vue-txs-pending fullfill">
        <vue-bread title="Pending Transactions"></vue-bread>
        <div class="container mt20">
            <div class="align-items-center info-and-pagination mt20 row">
                <div class="col info font-color-000000 font-size-24-bold">A total of {{ numberAddComma(totalTxs) }} Pending txns found</div>
            </div>
            <table class="mt20 explorer-table list-table">
                <tr class="list-header font-size-12-bold font-color-000000">
                    <th>TxHash</th>
                    <th>LastSeen</th>
                    <th>GasLimit</th>
                    <th>GasPrice</th>
                    <th>From</th>
                    <th></th>
                    <th>To</th>
                    <th class=text-right>Value</th>
                </tr>
                <tr v-for="(o, i) in arr" :key="i">
                    <td class="tdxxxwddd monospace">
                        <router-link v-bind:to='fragApi + "/tx/" + o.hash'>
                            <span class="font-color-0057FF font-size-14-normal">{{ o.hash }}</span>
                        </router-link>
                    </td>
                    <td class="time font-size-14-normal font-color-555555">
                        <div>{{ timeConversion(o.timeDiff) }} ago</div>
                        <div>{{ new Date(o.timestamp).toString().replace('GMT', 'UTC').replace(/\(.+\)/gi, '') }} | {{ o.timestamp }}</div>
                    </td>
                    <td class="font-size-14-normal font-color-555555">{{ numberAddComma(o.gasLimit) }}</td>
                    <td class="font-size-14-normal font-color-555555">{{ toWei(o.gasPrice) }}</td>
                    <td class=tdxxxwddd>
                        <vue-blockies v-bind:address='o.from.alias || o.from.hash'></vue-blockies>
                        <router-link v-bind:to='fragApi + "/address/" + o.from.hash'>
                            <span class="font-size-14-normal font-color-0057FF">{{ o.from.alias || o.from.hash }}</span>
                        </router-link>
                    </td>
                    <td>
                        <img class="icon16" src="../../static/img/ic_arrow_right.png"/>
                    </td>
                    <td class=tdxxxwddd>
                        <div style="width: 10px;"></div>
                        <div class="container-tip">
                            <div class="tip">
                                <div class="content">
                                    <div class="arrow">
                                        <em></em>
                                    </div>
                                    <span class="font-size-15-normal">Contract</span>
                                </div>
                            </div>
                            <img class="icon24" v-if="o.type==='call'" src="../../static/img/ic_tx_type_contract.png" />
                        </div>
                        <vue-blockies v-bind:address='o.to.alias || o.to.hash'></vue-blockies>
                        <router-link v-bind:to='fragApi + "/address/" + o.to.hash'>
                            <span class="font-size-14-normal font-color-0057FF">{{ o.to.alias || o.to.hash }}</span>
                        </router-link>
                    </td>
                    <td class="text-right font-size-14-normal font-color-000000">
                        {{ tokenAmount(o.value) }} NAS
                    </td>
                </tr>
            </table>
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
                totalPage: 0,
                totalTxs: 0
            };
        },
        methods: {
            nthPage() {
                var p = this.$route.query.p || 1;

                if (p == this.currentPage)
                    console.log("nthPage - 请求的第", p, "页正是当前页, 忽略此次调用");
                else {
                    this.$root.showModalLoading = true;

                    api.getTx({ isPending: true, p }, o => {
                        this.$root.showModalLoading = false;
                        this.arr = o.txnList;
                        this.currentPage = o.currentPage;
                        this.totalPage = o.totalPage;
                        this.totalTxs = o.txnCnt;

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
            timeConversion(ms) {
                return utility.timeConversion(ms);
            },
            numberAddComma(n) {
                return utility.numberAddComma(n);
            },
            toWei(n) {
                return utility.toWei(n);
            },
            easyNumber(n){
                return utility.easyNumber(n);
            },
            tokenAmount(n) {
                BigNumber.config({ DECIMAL_PLACES: 18 })
                var amount = BigNumber(n);
                var decimals = BigNumber('1e+18');
                return amount.div(decimals).toFormat().shortAmount();
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
