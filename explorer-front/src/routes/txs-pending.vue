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
                <div class=col>A total of {{ totalBlocks }} Pending txns found</div>
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
                    <td>{{ o.timestamp }}</td>
                    <td>{{ o.gasLimit }}</td>
                    <td>{{ o.avgGasPrice }}</td>
                    <td class=tdxxxwddd>
                        ???
                    </td>
                    <td>
                        <span class="fa fa-arrow-right" aria-hidden="true"></span>
                    </td>
                    <td class=tdxxxwddd>
                        ???
                    </td>
                    <td>???</td>
                </tr>
            </table>
            <vue-pagination v-bind:current=currentPage right=1 v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext v-on:prev=onPrev></vue-pagination>
        </div>
    </div>
</template>
<script>
    var api = require("@/assets/api");

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
                totalBlocks: 0,
                totalPage: 1 // 为了允许 mounted 调用 nthPage
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

                            api.getBlock({ isPending: 1, p }, o => {
                                console.log(o);

                                this.ajaxing = false;
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
                                // this.ajaxing = false; // 由于跳转了所以不需要修改 ajaxing
                                this.$router.replace("/404");
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
            }
        },
        mounted() {
            this.nthPage(1);
            this.totalPage = 0;
        }
    };
</script>