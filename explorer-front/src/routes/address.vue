<style>
    .vue-address td.out {
        width: 50px;
    }

    .vue-address td.in::before,
    .vue-address td.out::before {
        border-radius: 4px;
        color: white;
        padding: 3px 5px;
    }

    .vue-address td.in::before {
        background-color: var(--green);
        content: "in";
    }

    .vue-address td.out::before {
        background-color: var(--orange);
        content: "out";
    }

    .vue-address .container .table th {
        border-top: 0;
    }

    .vue-address .container .title {
        overflow: auto;
    }

    .vue-address .txfee {
        color: silver;
    }
</style>
<template>
    <!-- https://etherscan.io/address/0xea674fdde714fd979de3edf0f56aa9716b898ec8 -->
    <div class=vue-address v-bind:triggerComputed=urlChange>
        <vue-bread v-bind:arr=breadcrumb v-bind:title='"Address " + $route.params.id'></vue-bread>
        <div class=container v-if=obj>
            <table class="c333 table">
                <tr>
                    <th>
                        Overview
                        <span class=c777 v-show=obj.address.alias> | {{ obj.address.alias }}</span>
                    </th>
                    <th class=text-right>
                        * uncomment this img tag
                        <!-- <img src=%qrcode> -->
                    </th>
                </tr>
                <tr>
                    <td>NAS Balance:</td>
                    <td>{{ obj.address.currentBalance }}</td>
                </tr>
                <tr>
                    <td>Mined:</td>
                    <td>{{ obj.minedBlkCnt }}</td>
                </tr>
                <tr>
                    <td>No Of Transactions:</td>
                    <td>{{ obj.pendingTxCnt }}</td>
                </tr>
            </table>

            <vue-tab-buttons class=mt20 v-bind:arr=tabButtons v-bind:tab.sync=tab></vue-tab-buttons>
            <div class=mt20></div>

            <!--    Transactions
                ============================================================ -->
            <div class=tab v-show="tab == 1">
                <div class="align-items-center row title">
                    <div class=col>
                        <span class="c333 fa fa-sort-amount-desc" aria-hidden=true></span>
                        Latest {{ txs.length }} txns from a total Of
                        <router-link v-bind:to='"/txs?a=" + $route.params.id'>{{ obj.txCnt }}</router-link>
                    </div>
                    <div class=col-auto>
                        <router-link class="btn btn-link" v-bind:to='"/txs?a=" + $route.params.id'>View All</router-link>
                    </div>
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
                        <th class=txfee>[TxFee]</th>
                    </tr>

                    <tr v-for="o in txs">
                        <td class=tdxxxwddd>
                            <router-link v-bind:to='"/tx/" + $route.params.id'>{{ o.hash }}</router-link>
                        </td>
                        <td>
                            <router-link v-bind:to='"/block/" + o.blockHeight'>{{ o.blockHeight }}</router-link>
                        </td>
                        <td>{{ o.timestamp }}</td>
                        <td class=tdxxxwddd>
                            <span v-if="o.from == $route.params.id">{{ o.from }}</span>
                            <router-link v-else v-bind:to='"/address/" + o.from'>{{ o.from }}</router-link>
                        </td>
                        <td class=text-uppercase v-bind:class=inOutClass(o)></td>
                        <td class=tdxxxwddd>
                            <span v-if="o.to == $route.params.id">{{ o.to }}</span>
                            <router-link v-else v-bind:to='"/address/" + o.to'>{{ o.to }}</router-link>
                        </td>
                        <td>{{ o.value }}</td>
                        <td class=txfee>{{ o.txFee }}</td>
                    </tr>
                </table>
            </div>

            <!--    Mined Blocks
                ============================================================ -->
            <div class=tab v-show="tab == 2">
                <div class="align-items-center row title">
                    <div class=col>
                        <span class="c333 fa fa-sort-amount-desc" aria-hidden=true></span>
                        Latest {{ mined.length }} blocks (From a total of
                        <a href="blocks.html?m=%id-from-url">%2</a> with %3 mined)
                    </div>
                    <div class=col-auto>
                        <router-link class="btn btn-link" v-bind:to='"/blocks?m=" + $route.params.id'>View All</router-link>
                    </div>
                </div>

                <table class="mt20 table">
                    <tr>
                        <th>Block</th>
                        <th>Age</th>
                        <th>txn</th>
                        <th>Reward</th>
                    </tr>
                    <tr v-for="o in mined">
                        <td>
                            <router-link v-bind:to='"/block/" + o.height'>{{ o.height }}</router-link>
                        </td>
                        <td>{{ o.timestamp }}</td>
                        <td>{{ o.txnCnt }}</td>
                        <td>{{ o.gasReward }}</td>
                    </tr>
                </table>
            </div>

            <!--    Mined Uncles
                ============================================================ -->
            <!-- <div class=tab v-show="tab == 3">
                <div class="align-items-center row title">
                    <div class=col>
                        <span class="c333 fa fa-sort-amount-desc" aria-hidden=true></span>
                        Latest %1 uncles (From a total of
                        <a href="uncles.html?id=%id-from-url">%2</a> with %3 mined)
                    </div>
                    <div class=col-auto>
                        <router-link class="btn btn-link" v-bind:to='"/uncles?m=" + $route.params.id'>View All</router-link>
                    </div>
                </div>

                <table class="mt20 table">
                    <tr>
                        <th>Block</th>
                        <th>Age</th>
                        <th>UncleNumber</th>
                        <th>Difficulty</th>
                        <th>GasReward</th>
                        <th>Reward</th>
                    </tr>

                    <tr>
                        <td>
                            <a href="block.html?id=%Block">%Block</a>
                        </td>
                        <td>%Age</td>
                        <td>
                            <a href="uncle.html?id=%perhaps-uncle-id">%UncleNumber</a>
                        </td>
                        <td>%Difficulty</td>
                        <td>%GasReward</td>
                        <td>%Reward</td>
                    </tr>
                </table>
            </div> -->

        </div>
    </div>
</template>
<script>
    var api = require("@/assets/api");

    module.exports = {
        components: {
            "vue-bread": require("@/components/vue-bread").default,
            "vue-pagination": require("@/components/vue-pagination").default,
            "vue-tab-buttons": require("@/components/vue-tab-buttons").default
        },
        computed: {
            urlChange() {
                api.getAddress(this.$route.params.id, o => {
                    this.mined = o.minedBlkList;
                    this.obj = o;
                    this.txs = o.txList;
                }, xhr => {
                    this.$router.replace("/404");
                });
            }
        },
        data() {
            return {
                breadcrumb: [
                    { text: "Home", to: "/" },
                    { text: "Normal Accounts", to: "/accounts" },
                    { text: "Address", to: "" }
                ],
                mined: [],
                obj: null,
                tab: 0,
                tabButtons: ["Transactions", "Mined Blocks"],
                txs: []
            };
        },
        methods: {
            inOutClass(o) {
                if (o.from == this.$route.params.id)
                    return "out";
                else if (o.to == this.$route.params.id)
                    return "in";
                else
                    return "";
            }
        }
    };
</script>