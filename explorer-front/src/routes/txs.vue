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
            <div class="align-items-center info-and-pagination mt20 row">
                <div class="col info">{{ totalTxs }} transactions found (showing the last {{ maxDisplayCnt }} records)</div>
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
                        <div>{{ timeConversion(Date.now() - o.timestamp) }} ago</div>
                        <div>{{ new Date(o.timestamp).toString() }} | {{ o.timestamp }}</div>
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
                    <td>{{ toWei(o.value) }}</td>
                    <td>{{ toWei(o.txFee) }}</td>
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
                    { text: "Transactions", to: "" }
                ],
                currentPage: 0,
                maxDisplayCnt: 0,
                totalPage: 0,
                totalTxs: 0
            };
        },
        methods: {
            nav(n) {
                var query = JSON.parse(window.JSON.stringify(this.$route.query));

                query.p = n;
                this.$router.push({ path: this.$route.path, query });
            },
            nthPage() {
                this.$root.showModalLoading = true;

                api.getTx({
                    a: this.$route.query.a,
                    block: this.$route.query.block,
                    p: this.$route.query.p || 1
                }, o => {
                    this.$root.showModalLoading = false;
                    this.arr = o.txnList;
                    this.currentPage = o.currentPage;
                    this.maxDisplayCnt = o.maxDisplayCnt;
                    this.totalPage = o.totalPage;
                    this.totalTxs = o.txnCnt;
                }, xhr => {
                    console.log(xhr);
                    this.$root.showModalLoading = false;
                    this.$router.replace("/404!" + this.$route.fullPath);
                });
            },
            numberAddComma(n) {
                return utility.numberAddComma(n);
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