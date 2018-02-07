<style>
    .vue-txs-pending .tip a {
        color: #3498db;
    }
</style>
<template>
    <!-- https://etherscan.io/txsPending -->
    <div class=vue-txs-pending>
        <vue-bread v-bind:arr=breadcrumb title="Pending Transactions"></vue-bread>
        <div class="container mt20">
            <div class=tip>Tip: Check out the
                <a href=#>Pending Transaction Pool - Time Series</a>
            </div>
            <div class="align-items-center info-and-pagination mt20 row">
                <div class=col>A total of {{ totalTxs }} Pending txns found</div>
                <vue-pagination class=col-auto v-bind:current=currentPage v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext v-on:prev=onPrev></vue-pagination>
            </div>
            <table class="mt20 table">
                <tr>
                    <th>TxHash</th>
                    <th>LastSeen</th>
                    <th>GasLimit</th>
                    <th>GasPrice</th>
                    <th>From</th>
                    <th></th>
                    <th>To</th>
                    <th>Value</th>
                </tr>
                <tr v-for="o in arr">
                    <td class=tdxxxwddd>
                        <router-link v-bind:to='"/tx/" + o.hash'>{{ o.hash }}</router-link>
                    </td>
                    <td class=time>
                        <div>{{ timeConversion(o.timeDiff) }} ago</div>
                        <div>{{ Date(o.timestamp) }}</div>
                    </td>
                    <td>{{ numberAddComma(o.gasLimit) }}</td>
                    <td>{{ numberAddComma(o.gasPrice) }} Nas</td>
                    <td class=tdxxxwddd>
                        <router-link v-bind:to='"/address/" + o.from.hash'>{{ o.from.alias || o.from.hash }}</router-link>
                    </td>
                    <td>
                        <span class="fa fa-arrow-right" aria-hidden=true></span>
                    </td>
                    <td class=tdxxxwddd>
                        <router-link v-bind:to='"/address/" + o.to.hash'>{{ o.to.alias || o.to.hash }}</router-link>
                    </td>
                    <td>{{ o.value }} Nas</td>
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
                ajaxing: false,
                arr: [],
                breadcrumb: [
                    { text: "Home", to: "/" },
                    { text: "Pending Transactions", to: "" }
                ],
                currentPage: 0,
                totalPage: 1, // 为了允许 mounted 调用 nthPage
                totalTxs: 0
            };
        },
        methods: {
            nthPage(p) {
                if (p)
                    if (0 < p && p < this.totalPage + 1)
                        if (p == this.currentPage)
                            console.log("nthPage - 请求的第", p, "页正是当前页, 忽略此次调用");
                        else if (this.ajaxing)
                            console.log("nthPage - 上一个 ajax 还未返回, 忽略此次调用");
                        else {
                            this.ajaxing = true;

                            api.getTx({ isPending: true, p }, o => {
                                this.ajaxing = false;
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
                                // this.ajaxing = false; // 由于跳转了所以不需要修改 ajaxing
                                this.$router.replace("/404!" + this.$route.fullPath);
                            });
                        }
                    else
                        console.log("nthPage - 请求的第", p, "页不在 [ 1,", this.totalPage, "] 内, 忽略此次调用");
                else
                    console.log("nthPage - 无效的 p", p, ", 忽略此次调用");
            },
            onFirst() {
                this.nthPage(1);
            },
            onLast() {
                this.nthPage(this.totalPage);
            },
            onNext() {
                this.nthPage(this.currentPage + 1);
            },
            onPrev() {
                this.nthPage(this.currentPage - 1);
            },
            timeConversion(ms) {
                return utility.timeConversion(ms / 1000);
            },
            numberAddComma(n) {
                return utility.numberAddComma(n);
            }
        },
        mounted() {
            this.nthPage(1);
            this.totalPage = 0;
        }
    };
</script>