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

    .vue-block tr>td:first-child {
        padding-left: 24px;
    }

    .vue-block tr>td:first-child::after {
        content: ":";
    }

    .vue-block .card {
        border: 0;
    }

    .vue-block .dynasty a {
        margin-bottom: 10px;
    }

</style>
<template>
    <!-- https://etherscan.io/block/4951841 -->
    <div class="vue-block fullfill" v-bind:triggerComputed=urlChange>
        <vue-bread title='Block' :subtitle="$route.params.id"></vue-bread>
        <div v-if="block" class="container">
            <div class="font-24 font-bold font-color-000000 table-title">
                Overview
            </div>

            <div class="explorer-table-container d-none d-md-block">
                <table class="explorer-table font-16">
                    <tr>
                        <td class="font-color-555555">Height</td>
                        <td class="font-color-000000">
                            <nav aria-label="Page navigation" class=navgation-tab>
                                <ul class=pagination>
                                    <li>
                                        <router-link v-if="block.height > 1" v-bind:to='fragApi + "/block/" + (+$route.params.id - 1)' aria-label=Previous>
                                            <span aria-hidden=true>&lt; Prev</span>
                                        </router-link>
                                    </li>
                                    <li>&nbsp; {{ block.height }} &nbsp;</li>
                                    <li>
                                        <router-link v-if="$root.timestamp - block.timestamp > 16000" v-bind:to='fragApi + "/block/" + (+$route.params.id + 1)' aria-label=Next>
                                            <span aria-hidden=true>Next &gt;</span>
                                        </router-link>
                                    </li>
                                </ul>
                            </nav>
                        </td>
                    </tr>
                    <tr>
                        <td class="font-color-555555">TimeStamp</td>
                        <td class="font-color-000000">{{ timeConversion(Date.now() - block.localTimestamp + block.timeDiff) }} ago ({{ new Date(block.timestamp).toString().replace('GMT', 'UTC').replace(/\(.+\)/gi, '') }} | {{ block.timestamp }})</td>
                    </tr>
                    <tr>
                        <td class="font-color-555555">Transactions</td>
                        <td class="font-color-000000">
                            <router-link v-bind:to='fragApi + "/txs?block=" + block.height'>
                                <span>{{ block.blkSummary.txCnt }}</span>
                            </router-link>
                            tx in this block
                        </td>
                    </tr>
                    <tr>
                        <td class="font-color-555555">Hash</td>
                        <td class="font-color-000000 monospace">{{ block.hash }}</td>
                    </tr>
                    <tr>
                        <td class="font-color-555555">Parent Hash</td>
                        <td>
                            <router-link v-bind:to='fragApi + "/block/" + block.parentHash'>
                                <span class="monospace">{{ block.parentHash }}</span>
                            </router-link>
                        </td>
                    </tr>
                    <tr>
                        <td class="font-color-555555">Minted</td>
                        <td>
                            <router-link v-bind:to='fragApi + "/address/" + block.miner.hash'>
                                <span class="monospace">{{ block.miner.hash }}</span>
                            </router-link>
                            <span v-if=block.miner.alias> | {{ block.miner.alias }}</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="font-color-555555">Coinbase</td>
                        <td>
                            <router-link v-bind:to='fragApi + "/address/" + block.coinbase'>
                                <span class="monospace">{{ block.coinbase }}</span>
                            </router-link>
                        </td>
                    </tr>
                    <tr>
                        <td class="font-color-555555" style="vertical-align: top; padding-top: 12px;">Dynasty</td>
                        <td style="vertical-align: top; padding-top: 12px;">
                            <a class="d-flex align-items-center" href=# v-on:click="showOrHideDynasty()" style="text-decoration: none;" data-toggle="collapse" data-target="#collapse-mobile" aria-expanded="false" aria-controls="collapseExample">
                                <span>
                                    Show Dynasty
                                </span>
                                <img style="margin-left: 12px; margin-top: 3px; vertical-align: middle;" class="icon16" v-bind:src="isShowDynasty ? '../../static/img/ic_payload_arrow_up.png' : '../../static/img/ic_payload_arrow_down.png'" />
                            </a>
                            <div class="collapse" id="collapse-mobile">
                                <div class="card card-body dynasty">
                                    <router-link v-for="dynasty in block.dynasty" v-bind:key=dynasty v-bind:to='fragApi + "/address/" + dynasty'>
                                        <span class="font-16 font-bold  monospace"> {{ dynasty }}</span>
                                    </router-link>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="font-color-555555">Gas Reward</td>
                        <td class="font-color-000000">{{ toWei(block.blkSummary.gasReward) }}</td>
                    </tr>
                </table>
            </div>

            <div class="mobile-detail d-md-none">
                <div>
                    Height:
                    <div class="detail">
                        <nav aria-label="Page navigation" class=navgation-tab>
                            <ul class=pagination>
                                <li>
                                    <router-link v-if="block.height > 1" v-bind:to='fragApi + "/block/" + (+$route.params.id - 1)' aria-label=Previous>
                                        <span aria-hidden=true>&lt; Prev</span>
                                    </router-link>
                                </li>
                                <li>&nbsp; {{ block.height }} &nbsp;</li>
                                <li>
                                    <router-link v-if="$root.timestamp - block.timestamp > 16000" v-bind:to='fragApi + "/block/" + (+$route.params.id + 1)' aria-label=Next>
                                        <span aria-hidden=true>Next &gt;</span>
                                    </router-link>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div>
                    TimeStamp:
                    <div class="detail">{{ timeConversion(Date.now() - block.localTimestamp + block.timeDiff) }} ago ({{ new Date(block.timestamp).toString().replace('GMT', 'UTC').replace(/\(.+\)/gi, '') }} | {{ block.timestamp }})</div>
                </div>
                <div>
                    Transactions:
                    <div class="detail">
                        <router-link v-bind:to='fragApi + "/txs?block=" + block.height'>
                            <span>{{ block.blkSummary.txCnt }}</span>
                        </router-link>
                        tx in this block
                    </div>
                </div>
                <div>
                    Hash:
                    <div class="detail monospace">{{ block.hash }}</div>
                </div>
                <div>
                    Parent Hash:
                    <div class="detail">
                        <router-link v-bind:to='fragApi + "/block/" + block.parentHash'>
                            <span class="monospace">{{ block.parentHash }}</span>
                        </router-link>
                    </div>
                </div>
                <div>
                    Minted:
                    <div class="detail">
                        <router-link v-bind:to='fragApi + "/address/" + block.miner.hash'>
                            <span class="monospace">{{ block.miner.hash }}</span>
                        </router-link>
                        <span v-if=block.miner.alias> | {{ block.miner.alias }}</span>
                    </div>
                </div>
                <div>
                    Coinbase:
                    <div class="detail">
                        <router-link v-bind:to='fragApi + "/address/" + block.coinbase'>
                            <span class="monospace">{{ block.coinbase }}</span>
                        </router-link>
                    </div>
                </div>
                <div>
                    Dynasty:
                    <div class="detail">
                        <a class="d-flex align-items-center" href=# v-on:click="showOrHideDynasty()" style="text-decoration: none;" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                            <span>
                                Show Dynasty
                            </span>
                            <img style="margin-left: 12px; margin-top: 3px; vertical-align: middle;" class="icon16" v-bind:src="isShowDynasty ? '../../static/img/ic_payload_arrow_up.png' : '../../static/img/ic_payload_arrow_down.png'" />
                        </a>
                        <div class="collapse" id="collapseExample">
                            <div class="card card-body dynasty">
                                <router-link v-for="dynasty in block.dynasty" v-bind:key=dynasty v-bind:to='fragApi + "/address/" + dynasty'>
                                    <span class="font-16 font-bold "> {{ dynasty }}</span>
                                </router-link>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    Gas Reward:
                    <div class="detail">{{ toWei(block.blkSummary.gasReward) }}</div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    var api = require("@/assets/api"),
        utility = require("@/assets/utility");

    module.exports = {
        components: {
            "vue-bread": require("@/components/vue-bread").default,
            "vue-tab-buttons": require("@/components/vue-tab-buttons").default
        },
        computed: {
            urlChange() {
                this.$root.showModalLoading = true;
                api.getBlock(this.$route.params.id, o => {
                    this.$root.showModalLoading = false;
                    if (!o.localTimestamp) {
                        o.localTimestamp = Date.now();
                    }
                    this.block = o;
                }, xhr => {
                    this.$root.showModalLoading = false;
                    this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404");
                });
            }
        },
        methods: {
            showOrHideDynasty(){
                this.isShowDynasty = !this.isShowDynasty;
            },
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
                tabButtons: ["Overview"],
                isShowDynasty: false,
                timestamp: Date.now()
            };
        }
    };
</script>
