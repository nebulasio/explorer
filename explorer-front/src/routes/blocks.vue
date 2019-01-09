<style>
    .vue-blocks {
        background-color: white;
    }

    .vue-blocks .block {
        margin-right: 8px;
    }

    @media (max-width: 767.98px) {
        .vue-blocks .title {
            font-size: 20px;
        }
    }
</style>

<template>
    <!-- https://etherscan.io/blocks -->
    <div class="vue-blocks fullfill">
        <vue-bread title="Blocks"></vue-bread>

        <div v-if="arr" class="container mt20">
            <div class="align-items-center info-and-pagination mt20 row">
                <div class="col info font-color-000000 font-24 font-bold title">
                    {{ numberAddComma(totalBlocks) }} blocks found
                    <!-- <span v-if="totalTxs > 500" class="font-color-555555 font-16" style="vertical-align: text-bottom;">(showing the last 500 records)</span> -->
                </div>
            </div>
            <div class="explorer-table-container">
                <table class="mt20 explorer-table list-table">
                    <tr class="list-header font-12 font-bold font-color-000000">
                        <th style="width: 20px;"></th>
                        <th style="width: 130px;">Height</th>
                        <th style="width: 130px;">Age</th>
                        <th style="padding-left: 20px">txn</th>
                        <th style="padding-left: 30px">Minted</th>
                        <th class=text-right>Gas Reward</th>
                        <th class=text-right>GasLimit</th>
                        <th class=text-right>Avg.GasPrice</th>
                        <th style="width: 20px;"></th>
                    </tr>
                    <tr v-for="(o, i) in arr" :key="i">
                        <td></td>
                        <td>
                            <router-link v-bind:to='fragApi + "/block/" + o.height'>
                                <span class="font-14">{{ o.height }}</span>
                            </router-link>
                        </td>
                        <td class=time>
                            <div>
                                <div class="font-color-000000 font-14">{{ timeConversion(o.timeDiff) }} ago</div>
                                <div class="down-arrow-tip">{{ new Date(o.timestamp).toString().replace('GMT', 'UTC').replace(/\(.+\)/gi, '') }} | {{ o.timestamp }}</div>
                            </div>
                        </td>
                        <td style="padding-left: 20px">
                            <router-link v-bind:to='fragApi + "/txs?block=" + o.height'>
                                <span class="font-14">{{ numberAddComma(o.txnCnt) }}</span>
                            </router-link>
                        </td>
                        <td style="padding-left: 30px">
                            <router-link v-bind:to='fragApi + "/address/" + o.miner.hash'>
                                <vue-blockies class="d-inline" v-bind:address='o.miner.alias || o.miner.hash'></vue-blockies>
                                <span class="font-14 monospace">{{ o.miner.alias || o.miner.hash }}</span>
                            </router-link>
                        </td>
                        <td class=text-right>
                            <span class="font-14 font-color-555555">{{ toWei(o.gasReward) }}</span>
                        </td>
                        <td class=text-right>
                            <span class="font-14 font-color-000000">{{ numberAddComma(o.gasLimit) }}</span>
                        </td>
                        <td class=text-right>
                            <span class="font-14 font-color-555555">{{ toWei(o.avgGasPrice) }}</span>
                        </td>
                        <td></td>
                    </tr>
                </table>
            </div>
            <vue-pagination v-bind:current=currentPage right=1 v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext v-on:prev=onPrev v-on:to=onTo></vue-pagination>
        </div>
    </div>
</template>
<script>
    var api = require("@/assets/api"),
        utility = require("@/assets/utility");

    module.exports = {
        components: {
            "vue-bread": require("@/components/vue-bread").default,
            "vue-pagination": require("@/components/vue-pagination").default,
            "vue-blockies": require("@/components/vue-blockies").default
        },
        data() {
            return {
                arr: null,
                currentPage: 0,
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
                heightFrom: 0,
                heightTo: 0,
                totalBlocks: 0,
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

                    api.getBlocks({ p }, o => {

                        this.$root.showModalLoading = false;
                        this.arr = o.data;
                        this.currentPage = o.page;
                        this.totalPage = o.totalPage;
                        this.totalBlocks = o.totalCount;

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
            toWei(n) {
                return utility.toWei(n);
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
