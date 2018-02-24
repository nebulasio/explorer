<template>
    <!-- https://etherscan.io/blocks -->
    <div class=vue-blocks>
        <vue-bread v-bind:arr=breadcrumb title="Blocks"></vue-bread>

        <div class="container mt20">
            <div class="align-items-center info-and-pagination row">
                <div class=col>Showing Block (#{{ heightFrom }} to #{{ heightTo }}) out of {{ totalBlocks }} total blocks</div>
                <vue-pagination class=col-auto v-bind:current=currentPage v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext v-on:prev=onPrev></vue-pagination>
            </div>
            <table class="mt20 table">
                <tr>
                    <th>Height</th>
                    <th>Age</th>
                    <th>txn</th>
                    <th>Minted</th>
                    <th>Gas Reward</th>
                    <th>GasLimit</th>
                    <th>Avg.GasPrice</th>
                </tr>
                <tr v-for="o in arr">
                    <td>
                        <router-link v-bind:to='"/block/" + o.height'>{{ o.height }}</router-link>
                    </td>
                    <td class=time>
                        <div>{{ timeConversion( Date.now() - o.timestamp) }} ago</div>
                        <div>{{ new Date(o.timestamp).toString() }} | {{ o.timestamp }}</div>
                    </td>
                    <td>
                        <router-link v-bind:to='"/"'>{{ o.txnCnt }}</router-link>
                    </td >
                    <td class=monospace>
                        <router-link v-bind:to='"/address/" + o.miner.hash'>{{ o.miner.alias || o.miner.hash }}</router-link>
                    </td>
                    <td>{{ toWei(o.gasReward) }}</td>
                    <td>{{ numberAddComma(o.gasLimit) }}</td>
                    <td>{{ toWei(o.avgGasPrice) }}</td>
                </tr>
            </table>
            <vue-pagination v-bind:current=currentPage right=1 v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext v-on:prev=onPrev></vue-pagination>
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
                        this.$router.replace("/404!" + this.$route.fullPath);
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