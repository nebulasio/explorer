<template>
    <!-- https://etherscan.io/txsPending -->
    <div class=vue-txs-pending>
        <vue-bread v-bind:arr=breadcrumb title="Pending Transactions"></vue-bread>
        <div class="container mt20">
            <div class="align-items-center info-and-pagination mt20 row">
                <div class=col>A total of {{ totalTxs }} Pending txns found</div>
                <vue-pagination class=col-auto v-bind:current=currentPage v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext v-on:prev=onPrev></vue-pagination>
            </div>
            <table class="mt20 table">
                <tr>
                    <th>TxHash</th>
                    <th class=text-right>LastSeen</th>
                    <th class=text-right>GasLimit</th>
                    <th class=text-right>GasPrice</th>
                    <th>From</th>
                    <th></th>
                    <th>To</th>
                    <th class=text-right>Value</th>
                </tr>
                <tr v-for="o in arr">
                    <td class="tdxxxwddd monospace">
                        <router-link v-bind:to='"/tx/" + o.hash'>{{ o.hash }}</router-link>
                    </td>
                    <td class=time>
                        <div class=text-right>{{ timeConversion(Date.now() - o.timestamp) }} ago</div>
                        <div>{{ new Date(o.timestamp).toString() }} | {{ o.timestamp }}</div>
                    </td>
                    <td class=text-right>{{ numberAddComma(o.gasLimit) }}</td>
                    <td class=text-right>{{ toWei(o.gasPrice) }}</td>
                    <td class=tdxxxwddd>
                        <router-link v-bind:to='"/address/" + o.from.hash'>{{ o.from.alias || o.from.hash }}</router-link>
                    </td>
                    <td>
                        <span class="fa fa-arrow-right" aria-hidden=true></span>
                    </td>
                    <td class=tdxxxwddd>
                        <router-link v-bind:to='"/address/" + o.to.hash'>{{ o.to.alias || o.to.hash }}</router-link>
                    </td>
                    <td class=text-right>{{ toWei(o.value) }}</td>
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
                    { text: "Pending Transactions", to: "" }
                ],
                currentPage: 0,
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
                        console.log(xhr);
                        this.$root.showModalLoading = false;
                        this.$router.replace("/404!" + this.$route.fullPath);
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
            timeConversion(ms) {
                return utility.timeConversion(ms / 1000);
            },
            numberAddComma(n) {
                return utility.numberAddComma(n);
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