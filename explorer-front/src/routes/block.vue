<style>
    .vue-block {
        width: 100%;
        background-color: white;
    }
    .vue-block .table tbody tr td {
        border: 0;
        vertical-align: middle;
    }

    .vue-block td .pagination {
        margin: 0;
        vertical-align: top;
    }

    .vue-block tr>td:first-child::after {
        content: ":";
    }

    .vue-block .dynasty {
        height: 78px;
        overflow: hidden;
    }

    .vue-block .dynasty a {
        display: block;
    }

    .vue-block .dynasty button {
        background-color: darkgray;
        border-color: none;
    }

    .vue-block .card {
        border: 0;
    }
</style>
<template>
    <!-- https://etherscan.io/block/4951841 -->
    <div class="vue-block fullfill" v-bind:triggerComputed=urlChange>
        <div class="container">
            <div class=mt20></div>
            <div class="tab explorer-table-container">
                <table class="explorer-table font-size-16-normal">
                    <tr>
                        <div class="font-size-24-bold font-color-000000 table-title">
                            Overview
                        </div>
                        <td class="font-color-555555">Height</td>
                        <td class="font-color-000000">
                            <nav aria-label="Page navigation" class=navgation-tab>
                                <ul class=pagination>
                                    <li>
                                        <router-link v-bind:to='fragApi + "/block/" + (+$route.params.id - 1)' aria-label=Previous>
                                            <span class="font-color-0057FF" aria-hidden=true>&lt; Prev</span>
                                        </router-link>
                                    </li>
                                    <li>&nbsp; {{ block.height }} &nbsp;</li>
                                    <li>
                                        <router-link v-bind:to='fragApi + "/block/" + (+$route.params.id + 1)' aria-label=Next>
                                            <span class="font-color-0057FF" aria-hidden=true>Next &gt;</span>
                                        </router-link>
                                    </li>
                                </ul>
                            </nav>
                        </td>
                    </tr>
                    <tr>
                        <td class="font-color-555555">TimeStamp</td>
                        <td class="font-color-000000">{{ timeConversion(Date.now() - block.timestamp ) }} ago ({{ new Date(block.timestamp).toString() }} | {{ block.timestamp }})</td>
                    </tr>
                    <tr>
                        <td class="font-color-555555">Transactions</td>
                        <td class="font-color-000000">
                            <router-link v-bind:to='fragApi + "/txs?block=" + block.height'>
                                <span class="font-color-0057FF">{{ block.blkSummary.txCnt }}</span>
                            </router-link>
                            tx in this block
                        </td>
                    </tr>
                    <tr>
                        <td class="font-color-555555">Hash</td>
                        <td class="font-color-000000">{{ block.hash }}</td>
                    </tr>
                    <tr>
                        <td class="font-color-555555">Parent Hash</td>
                        <td>
                            <router-link v-bind:to='fragApi + "/block/" + block.parentHash'>
                                <span class="font-color-0057FF">{{ block.parentHash }}</span>
                            </router-link>
                        </td>
                    </tr>
                    <tr>
                        <td class="font-color-555555">Minted</td>
                        <td>
                            <router-link v-bind:to='fragApi + "/address/" + block.miner.hash'>
                                <span class="font-color-0057FF">{{ block.miner.hash }}</span>
                            </router-link>
                            <span v-if=block.miner.alias> | {{ block.miner.alias }}</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="font-color-555555">Coinbase</td>
                        <td>
                            <router-link v-bind:to='fragApi + "/address/" + block.coinbase'>
                                <span class="font-color-0057FF">{{ block.coinbase }}</span>
                            </router-link>
                        </td>
                    </tr>
                    <tr>
                        <td class="font-color-555555" style="vertical-align: top; padding-top: 12px;">Dynasty</td>
                        <td style="vertical-align: top; padding-top: 12px;">
                            <span class="font-color-0057FF">Show Dynasty</span>
                            <img style="width: 12px; height: 7px;" src="../../static/img/ic_arrow_right.png" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"/>
                            <div class="collapse" id="collapseExample">
                                <div class="card card-body">
                                    <template v-for="dynasty in block.dynasty">
                                        <router-link v-bind:key=dynasty v-bind:to='fragApi + "/address/" + dynasty'>
                                            <span class="font-size-16-bold font-color-0057FF"> {{ dynasty }}</span>
                                        </router-link>
                                        <br v-bind:key=dynasty>
                                    </template>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="font-color-555555">Gas Reward</td>
                        <td class="font-color-000000">{{ toWei(block.blkSummary.gasReward) }}</td>
                    </tr>
                </table>
                <div style="height: 60px;"></div>
            </div>
        </div>
    </div>
</template>
<script>
    var api = require("@/assets/api"),
        utility = require("@/assets/utility");

    module.exports = {
        components: {
            "vue-tab-buttons": require("@/components/vue-tab-buttons").default
        },
        computed: {
            urlChange() {
                api.getBlock(this.$route.params.id, o => {
                    this.block = o;
                }, xhr => {
                    this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404!" + this.$route.fullPath);
                });
            }
        },
        methods: {
            timeConversion(ms) {
                return utility.timeConversion(ms);
            },
            timeConversionSec(ms) {
                return utility.timeConversionSec(ms);
            },
            toWei(n) {
                return utility.toWei(n);
            }
        },
        data() {
            return {
                block: null,
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
                tab: 0,
                tabButtons: ["Overview"]
            };
        }
    };
</script>
