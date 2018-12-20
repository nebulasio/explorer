<style>
    .vue-contracts {
        background-color: white;
    }

    .vue-contracts .info-and-pagination .info a {
        color: inherit;
    }

    .vue-contracts td,
    .vue-contracts th {
        border-top-color: #ddd;
    }

    .vue-contracts .hash-normal {
        height: 20px;
        font-size: 14px;
        /* font-family: OpenSans; */
        color: rgba(0, 87, 255, 1);
        line-height: 20px;
    }

    .vue-contracts .contract-hash {
        max-width: 200px;
        overflow: hidden;
        text-overflow: ellipsis;
        vertical-align: center;
        padding: 0;
    }

    .vue-contracts .contract-hash>* {
        display: inline;
    }

    .vue-contracts .block {
        margin-right: 10px;
    }

    /* @media (min-width: 768px) {
        .vue-contracts .contract-hash {
            max-width: initial;
        }
    } */

</style>
<template>
    <!-- https://etherscan.io/txs -->
    <div class="vue-contracts fullfill">
        <vue-bread title="Smart Contracts"></vue-bread>

        <div class="container mt20 explorer-table-container">
            <div class="align-items-center info-and-pagination mt20 row">
                <div class="col info font-color-000000 font-size-24-bold">{{ numberAddComma(totalCts) }} smart contracts found</div>
                <!--(showing the last {{ maxDisplayCnt }} records)-->
            </div>

            <table class="mt20 explorer-table list-table">
                <tr class="list-header font-size-12-bold font-color-000000">
                    <th style="padding-left: 24px;">Address</th>
                    <th class=text-right>Balance</th>
                    <th class=text-right>Type</th>
                    <th class=text-right style="padding-right: 24px;">Date Created</th>
                </tr>

                <tr v-for="(o, i) in arr" :key="i">
                    <td style="padding-left: 24px;" class="contract-hash">
                        <vue-blockies v-bind:address='o.hash'></vue-blockies>
                        <router-link v-bind:to='fragApi + "/address/" + o.hash'>
                            <span class="hash-normal, monospace">{{ o.hash }}</span>
                        </router-link>
                    </td>
                    <td class="text-right font-color-000000 font-size-14-normal">{{ tokenAmount(o.balance) }} NAS</td>
                    <td class="text-right font-color-000000 font-size-14-normal">{{ o.contractType === 'NORMAL' ? 'Contract' : 'Token Contract' }} </td>
                    <td class="text-right font-size-14-normal font-color-555555" style="padding-right: 24px;">{{ new Date(o.createdAt).toLocaleDateString('en', { year: 'numeric', month: 'short', day: 'numeric' }) }}</td>
                </tr>
            </table>

            <vue-pagination v-bind:current=currentPage right=1 v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext
                v-on:prev=onPrev v-on:to=onTo></vue-pagination>
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
                maxDisplayCnt: 0,
                totalPage: 0,
                totalCts: 0
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

                api.getContracts({
                    p: this.$route.query.p || 1,
                }, o => {
                    this.$root.showModalLoading = false;
                    this.arr = o.contracts;
                    this.currentPage = o.currentPage;
                    // this.maxDisplayCnt = o.maxDisplayCnt;
                    this.totalPage = o.totalPage;
                    this.totalCts = o.total;
                }, xhr => {
                    this.$root.showModalLoading = false;
                    this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404");
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
            onTo(n) {
                this.nav(n);
            },
            timeConversion(ms) {
                return utility.timeConversion(ms);
            },  
            toWei(n) {
                return utility.toWei(n);
            },
            easyNumber(n) {
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
