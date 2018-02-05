<style>
    .vue-txs .tip a {
        color: rgb(76, 32, 133);
    }

    .vue-txs .info-and-pagination .info a {
        color: inherit;
    }

    .vue-txs td,
    .vue-txs th {
        border-top-color: #ddd;
    }
</style>
<template>
    <!-- https://etherscan.io/txs -->
    <div class=vue-txs>
        <vue-bread v-bind:arr=breadcrumb title=Transactions></vue-bread>

        <div class="container mt20">
            <div class=tip>Sponsored Link: Winding Tree: Decentralized Travel Distribution. Trusted by Lufthansa, SWISS, Air New Zealand, and more!
                <a href="#">Join Now!</a>
            </div>

            <div class="align-items-center info-and-pagination mt20 row">
                <div class="col info">{{ totalTxs }} transactions found (showing the last {{ arr.length * totalPage }} records)</div>
                <vue-pagination class=col-auto v-bind:current=currentPage v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext v-on:prev=onPrev></vue-pagination>
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
                    <th>[TxFee]</th>
                </tr>

                <tr v-for="o in arr">
                    <td class=tdxxxwddd>
                        <router-link v-bind:to='"/tx/" + o.hash'>{{ o.hash }}</router-link>
                    </td>
                    <td>
                        <router-link v-bind:to='"/block/" + o.block.height'>{{ o.block.height }}</router-link>
                    </td>
                    <td class=time>
                        <div>{{ timeConversion(o.timeDiff) }} ago</div>
                        <div>{{ Date(o.timestamp) }}</div>
                    </td>
                    <td class=tdxxxwddd>
                        <router-link v-bind:to='"/address/" + o.from.hash'>{{ o.from.hash }}</router-link>
                    </td>
                    <td>
                        <span class="fa fa-arrow-right" aria-hidden=true></span>
                    </td>
                    <td class=tdxxxwddd>
                        <router-link v-bind:to='"/address/" + o.to.hash'>{{ o.to.hash }}</router-link>
                    </td>
                    <td>{{ numberAddComma(o.value) }} Nas</td>
                    <td>{{ numberAddComma(o.txFee) }}</td>
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
                ajaxParam: {},
                arr: [],
                breadcrumb: [
                    { text: "Home", to: "/" },
                    { text: "Transactions", to: "" }
                ],
                currentPage: 0,
                totalPage: 0,
                totalTxs: 0
            };
        },
        methods: {
            changePage() {
                var p = this.ajaxParam.p;

                if (p)
                    if (0 < p && p < this.totalPage + 1)
                        if (p == this.currentPage)
                            console.log("changePage - 请求的第", p, "页正是当前页, 忽略此次调用");
                        else if (this.ajaxing)
                            console.log("changePage - 上一个 ajax 还未返回, 忽略此次调用");
                        else {
                            this.ajaxing = true;

                            api.getTx(this.ajaxParam, o => {
                                this.ajaxing = false;
                                this.arr = o.txnList;
                                this.currentPage = o.currentPage;
                                this.totalPage = o.totalPage;
                                this.totalTxs = o.txnCnt;
                            }, xhr => {
                                console.log(xhr);
                                // this.ajaxing = false; // 由于跳转了所以不需要修改 ajaxing
                                this.$router.replace("/404");
                            });
                        }
                    else
                        console.log("changePage - 请求的第", p, "页不在 [ 1,", this.totalPage, "] 内, 忽略此次调用");
                else
                    console.log("changePage - 无效的 p", p, ", 忽略此次调用");
            },
            initByRoute() {
                this.ajaxParam = {
                    a: this.$route.query.a,
                    block: this.$route.query.block,
                    p: 1
                };

                this.currentPage = 0;
                this.totalPage = 1;

                this.changePage();
                this.totalPage = 0;
            },
            numberAddComma(n) {
                return utility.numberAddComma(n);
            },
            onFirst() {
                this.ajaxParam.p = 1;
                this.changePage();
            },
            onLast() {
                this.ajaxParam.p = this.totalPage;
                this.changePage();
            },
            onNext() {
                this.ajaxParam.p = this.currentPage + 1;
                this.changePage();
            },
            onPrev() {
                this.ajaxParam.p = this.currentPage - 1;
                this.changePage();
            },
            timeConversion(ms) {
                return utility.timeConversion(ms / 1000);
            }
        },
        mounted() {
            this.initByRoute();
        },
        watch: {
            $route() {
                this.initByRoute();
            }
        }
    };
</script>