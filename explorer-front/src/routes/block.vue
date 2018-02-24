<style>
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
    <div class="container vue-block" v-bind:triggerComputed=urlChange>
        <vue-tab-buttons class=mt20 v-bind:arr=tabButtons v-bind:tab.sync=tab></vue-tab-buttons>
        <div class=mt20></div>
        <div class=tab v-show="tab == 1">
            <h4>Block Information</h4>
            <table class=table v-if=block>
                <tr>
                    <td>Height</td>
                    <td>
                        <nav aria-label="Page navigation" class=navgation-tab>
                            <ul class=pagination>
                                <li>
                                    <router-link v-bind:to='"/block/" + (+$route.params.id - 1)' aria-label=Previous>
                                        <span aria-hidden=true>&lt; Prev</span>
                                    </router-link>
                                </li>
                                <li>&nbsp; {{ block.height }} &nbsp;</li>
                                <li>
                                    <router-link v-bind:to='"/block/" + (+$route.params.id + 1)' aria-label=Next>
                                        <span aria-hidden=true>Next &gt;</span>
                                    </router-link>
                                </li>
                            </ul>
                        </nav>
                    </td>
                </tr>
                <tr>
                    <td>TimeStamp</td>
                    <td>{{ timeConversion(Date.now() - block.timestamp ) }} ago ({{ new Date(block.timestamp).toString() }} | {{ block.timestamp }})</td>
                </tr>
                <tr>
                    <td>Transactions</td>
                    <td>
                        <router-link v-bind:to='"/txs?block=" + block.height'>{{ block.blkSummary.txCnt }}</router-link>
                         tx in this block
                    </td>
                </tr>
                <tr>
                    <td>Hash</td>
                    <td class=monospace>{{ block.hash }}</td>
                </tr>
                <tr>
                    <td>Parent Hash</td>
                    <td class=monospace>
                        <router-link v-bind:to='"/block/" + block.parentHash'>{{ block.parentHash }}</router-link>
                    </td>
                </tr>

                <tr>
                    <td>Minted</td>
                    <td class=monospace>
                        <router-link v-bind:to='"/address/" + block.miner.hash'>{{ block.miner.hash }}</router-link>
                        <span v-if=block.miner.alias> | {{ block.miner.alias }}</span>
                    </td>
                </tr>

                <tr>
                    <td>Dynasty</td>
                    <td class="dynasty monospace">

                        <p>
                            <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                Show Dynasty
                            </button>
                        </p>
                        <div class="collapse" id="collapseExample">
                            <div class="card card-body">
                                <template v-for="dynasty in block.dynasty">
                                    <router-link v-bind:key=dynasty v-bind:to='"/address/" + dynasty'>{{ dynasty }} </router-link>
                                    <br>
                                </template>
                            </div>
                        </div>

                    </td>
                </tr>

                <tr>
                    <td>Gas Reward</td>
                    <td>{{ toWei(block.blkSummary.gasReward) }}</td>
                </tr>

            </table>
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
                    this.$router.replace("/404!" + this.$route.fullPath);
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
                tab: 0,
                tabButtons: ["Overview"]
            };
        }
    };
</script>