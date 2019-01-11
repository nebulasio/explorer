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

    .vue-contracts .hash {
        max-width: 200px;
        overflow: hidden;
        text-overflow: ellipsis;
        vertical-align: center;
        white-space: nowrap;
    }

    .vue-contracts .hash>* {
        display: inline;
    }

    .vue-contracts .block {
        margin-right: 8px;
    }

    @media (max-width: 767.98px) {
        .vue-contracts .title {
            font-size: 20px;
        }
    }

</style>
<template>
    <!-- https://etherscan.io/txs -->
    <div class="vue-contracts fullfill">
        <vue-bread title="Smart Contracts"></vue-bread>

        <div v-if="arr" class="container mt20">
            <div class="align-items-center info-and-pagination mt20 row">
                <div class="col info font-color-000000 font-24 font-bold title">{{ numberAddComma(totalCts) }} smart contracts found</div>
                <!--(showing the last {{ maxDisplayCnt }} records)-->
            </div>

            <div class="explorer-table-container font-14">
                <table class="mt20 explorer-table list-table">
                    <tr class="list-header font-12 font-bold font-color-000000">
                        <th style="padding-left: 24px;">Smart Contract</th>
                        <th>Creator Address</th>
                        <th>Type</th>
                        <th class=text-right style="padding-right: 24px; width: 120px">Date Created</th>
                    </tr>

                    <tr v-for="(o, i) in arr" :key="i">
                        <td style="padding-left: 24px;" class="hash">
                            <vue-blockies v-bind:address='o.hash'></vue-blockies>
                            <router-link v-bind:to='fragApi + "/address/" + o.hash'>
                                <span class="hash-normal monospace">{{ o.hash }}</span>
                            </router-link>
                        </td>
                        <td class="hash">
                            <vue-blockies v-bind:address='o.creator'></vue-blockies>
                            <router-link v-bind:to='fragApi + "/address/" + o.hash'>
                                <span class="hash-normal monospace">{{ o.creator }}</span>
                            </router-link>
                        </td>
                        <td class="font-color-000000">{{ o.contractType === 'NORMAL' ? 'Contract' : 'Token Contract' }} </td>
                        <td class="text-right font-color-555555" style="padding-right: 24px;">{{ new Date(o.createdAt).toLocaleDateString('en', { year: 'numeric', month: 'short', day: 'numeric' }) }}</td>
                    </tr>
                </table>
            </div>

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
                arr: null,
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
