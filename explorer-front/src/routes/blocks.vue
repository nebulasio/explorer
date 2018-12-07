<style>
    .vue-blocks {
        background-color: white;
    }
</style>

<template>
    <!-- https://etherscan.io/blocks -->
    <div class="vue-blocks fullfill">
        <vue-bread v-bind:arr=breadcrumb title="Blocks"></vue-bread>

        <div class="container mt20 explorer-table-container">
            <div class="align-items-center info-and-pagination row" style="margin-top: 60px; margin-bottom: 30px;">
                <div class="col font-color-000000 font-size-24-bold">Showing Block (#{{ heightFrom }} to #{{ heightTo }}) out of {{ totalBlocks }} total blocks</div>
            </div>
            <table class="mt20 explorer-table list-table">
                <tr class="list-header font-size-12-bold font-color-000000">
                    <th style="width: 20px;"></th>
                    <th style="width: 130px;">Height</th>
                    <th style="width: 130px;">Age</th>
                    <th class="text-right">txn</th>
                    <th style="padding-left: 60px">Minted</th>
                    <th class=text-right>Gas Reward</th>
                    <th class=text-right>GasLimit</th>
                    <th class=text-right>Avg.GasPrice</th>
                    <th style="width: 20px;"></th>
                </tr>
                <tr v-for="(o, i) in arr" :key="i">
                    <td></td>
                    <td>
                        <router-link v-bind:to='fragApi + "/block/" + o.height'>
                            <span class="font-size-14-normal font-color-0057FF">{{ o.height }}</span>
                        </router-link>
                    </td>
                    <td class=time>
                        <div class="font-color-000000 font-size-14-normal">{{ timeConversion( Date.now() - o.timestamp) }} ago</div>
                        <div>{{ new Date(o.timestamp).toString() }} | {{ o.timestamp }}</div>
                    </td>
                    <td class="text-right">
                        <router-link v-bind:to='fragApi + "/txs?block=" + o.height'>
                            <span class="font-size-14-normal font-color-0057FF">{{ o.txnCnt }}</span>
                        </router-link>
                    </td>
                    <td class=monospace  style="padding-left: 60px">
                        <router-link v-bind:to='fragApi + "/address/" + o.miner.hash'>
                            <span class="font-size-14-normal font-color-0057FF">{{ o.miner.alias || o.miner.hash }}</span>
                        </router-link>
                    </td>
                    <td class=text-right>
                        <span class="font-size-14-normal font-color-555555">{{ toWei(o.gasReward) }}</span>
                    </td>
                    <td class=text-right>
                        <span class="font-size-14-normal font-color-000000">{{ numberAddComma(o.gasLimit) }}</span>
                    </td>
                    <td class=text-right>
                        <span class="font-size-14-normal font-color-555555">{{ toWei(o.avgGasPrice) }}</span>
                    </td>
                    <td></td>
                </tr>
            </table>
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
            "vue-pagination": require("@/components/vue-pagination").default
        },
        data() {
            return {
                arr: [],
                breadcrumb: [
                    { text: "Home", to: "/" },
                    { text: "Blocks", to: "" }
                ],
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

                    api.getBlock({ p }, o => {

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
                        console.log(xhr);
                        this.$root.showModalLoading = false;
                        this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404!" + this.$route.fullPath);
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
