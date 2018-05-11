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

    .vue-address .tab a{
        font-size: 13px;
    }
</style>
<template>
    <!-- https://etherscan.io/address/0xea674fdde714fd979de3edf0f56aa9716b898ec8 -->
    <div class=vue-address v-bind:triggerComputed=urlChange>
        <vue-bread v-bind:arr=breadcrumb v-bind:title='$lang.messages.address_title + " " + $route.params.id'></vue-bread>
        <div class=container v-if=obj>
            <table class="c333 table">
                <tr>
                    <th>
                        Overview
                        <span class=c777 v-show=obj.address.alias> | {{ obj.address.alias }}</span>
                    </th>
                    <th class=text-right>
                        <!-- * uncomment this img tag -->
                        <!-- <img src=%qrcode> -->
                    </th>
                </tr>
                <tr>
                    <td>{{$lang.messages.address_balance}}</td>
                    <td>{{ easyNumber(obj.address.balance/1000000000000000000) }} NAS </td>
                </tr>
                <tr>
                    <td>{{$lang.messages.address_minted}}</td>
                    <td>{{ obj.mintedBlkCnt }}</td>
                </tr>
                <tr>
                    <td>{{$lang.messages.address_numtransactions}}</td>
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
                        {{$lang.messages.address_latest_trans_of_total.replace("{0}", txs.length)}}
                        <router-link v-bind:to='fragApi + "/txs?a=" + $route.params.id'>{{$lang.messages.address_latest_trans_of_total2.replace("{0}", obj.txCnt)}} </router-link>
                        <router-link v-bind:to='fragApi + "/txs?a=" + $route.params.id + "&isPending=true" '>( + {{ obj.pendingTxCnt == 0? 0 : obj.pendingTxCnt }} {{$lang.messages.address_latest_trans_of_total_pending}} )</router-link>
                    </div>
                    <div class=col-auto>
                        <router-link class="btn btn-link" v-bind:to='fragApi + "/txs?a=" + $route.params.id'>{{$lang.messages.address_view_all_transactions.replace("{0}", obj.txCnt)}}</router-link>
                        |<router-link class="btn btn-link" v-bind:to='fragApi + "/txs?a=" + $route.params.id + "&isPending=true" ' >{{$lang.messages.address_view_all_transactions_pending.replace("{0}", obj.pendingTxCnt == 0? 0 : obj.pendingTxCnt)}} </router-link>
                    </div>
                </div>

                <table class="mt20 table">
                    <tr>
                        <th>{{$lang.messages.address_table_txhash}}</th>
                        <th>{{$lang.messages.address_table_block}}</th>
                        <th>{{$lang.messages.address_table_age}}</th>
                        <th>{{$lang.messages.address_table_from}}</th>
                        <th></th>
                        <th>{{$lang.messages.address_table_to}}</th>
                        <th>{{$lang.messages.address_table_value}}</th>
                        <th class=txfee>{{$lang.messages.address_table_txfee}}</th>
                    </tr>

                    <tr v-for="o in txs">
                        <td class=tdxxxwddd>
                            <router-link v-bind:to='fragApi + "/tx/" + o.hash'>{{ o.hash }}</router-link>
                        </td>
                        <td>
                            <router-link v-if=o.block.height v-bind:to='fragApi + "/block/" + o.block.height'>{{ o.block.height }}</router-link>
                            <i v-else>{{$lang.messages.address_pending}}</i>
                        </td>
                        <td class=time>
                            <div>{{ timeConversion(Date.now() - o.timestamp) }} ago</div>
                            <div>{{ new Date(o.timestamp).toString() }} | {{ o.timestamp }}</div>
                        </td>
                        <td class=tdxxxwddd>
                            <span v-if="o.from.hash == $route.params.id">{{ o.from.alias || o.from.hash }}</span>
                            <router-link v-else v-bind:to='fragApi + "/address/" + o.from.hash'>{{ o.from.alias || o.from.hash }}</router-link>
                        </td>
                        <td class=text-uppercase v-bind:class=inOutClass(o)></td>
                        <td class=tdxxxwddd>
                            <span v-if="o.to.hash == $route.params.id">{{ o.to.alias || o.to.hash }}</span>
                            <router-link v-else v-bind:to='fragApi + "/address/" + o.to.hash'>{{ o.to.alias || o.to.hash }}</router-link>
                        </td>
                        <td>{{ easyNumber(o.value/1000000000000000000) }} NAS</td>
                        <td class=txfee>
                            <span v-if=o.block.height>{{ toWei(o.txFee) }}</span>
                            <i v-else>{{$lang.messages.address_pending}}</i>
                        </td>
                    </tr>
                </table>
            </div>

            <!--    Minted Blocks
                ============================================================ -->
            <div class=tab v-show="tab == 2">
                <div class="align-items-center row title">
                    <div class=col>
                        <span class="c333 fa fa-sort-amount-desc" aria-hidden=true></span>
                        Latest {{ minted.length }} blocks (From a total of
                        <router-link v-bind:to='fragApi + "/blocks?m=" + $route.params.id'>{{ obj.mintedBlkCnt}}</router-link>)
                    </div>
                    <div class=col-auto>
                        <router-link class="btn btn-link" v-bind:to='fragApi + "/blocks?m=" + $route.params.id'>{{$lang.messages.address_view_all}}</router-link>
                    </div>
                </div>

                <table class="mt20 table">
                    <tr>
                        <th>{{$lang.messages.address_table_block}}</th>
                        <th>{{$lang.messages.address_table_age}}</th>
                        <th>{{$lang.messages.address_table_txn}}</th>
                        <th>{{$lang.messages.address_table_reward}}</th>
                    </tr>
                    <tr v-for="o in minted">
                        <td>
                            <router-link v-bind:to='fragApi + "/block/" + o.height'>{{ o.height }}</router-link>
                        </td>
                        <td class=time>
                            <div>{{ timeConversion(Date.now() - o.timestamp) }} ago</div>
                            <div>{{ new Date(o.timestamp).toString() }} | {{ o.timestamp }}</div>
                        </td>
                        <td>{{ o.txnCnt }}</td>
                        <td>{{ toWei(o.gasReward) }}</td>
                    </tr>
                </table>
            </div>

            <!--    Minted Uncles
                ============================================================ -->
            <!-- <div class=tab v-show="tab == 3">
                <div class="align-items-center row title">
                    <div class=col>
                        <span class="c333 fa fa-sort-amount-desc" aria-hidden=true></span>
                        Latest %1 uncles (From a total of
                        <a href="uncles.html?id=%id-from-url">%2</a> with %3 minted)
                    </div>
                    <div class=col-auto>
                        <router-link class="btn btn-link" v-bind:to='fragApi + "/uncles?m=" + $route.params.id'>View All</router-link>
                    </div>
                </div>

                <table class="mt20 table">
                    <tr>
                        <th>{{$lang.messages.address_table_block}}</th>
                        <th>{{$lang.messages.address_table_age}}</th>
                        <th>{{$lang.messages.address_table_unclenumber}}</th>
                        <th>{{$lang.messages.address_table_difficulty}}</th>
                        <th>{{$lang.messages.address_table_gasreward}}</th>
                        <th>{{$lang.messages.address_table_reward}}</th>
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
    var api = require("@/assets/api"),
        utility = require("@/assets/utility");

    module.exports = {
        components: {
            "vue-bread": require("@/components/vue-bread").default,
            "vue-pagination": require("@/components/vue-pagination").default,
            "vue-tab-buttons": require("@/components/vue-tab-buttons").default
        },
        computed: {
            tabButtons() {
                return this.obj && this.obj.mintedBlkCnt ? [this.$lang.messages.address_tab_transactions, this.$lang.messages.address_tab_mintedblocks] : [this.$lang.messages.address_tab_transactions];
            },
            urlChange() {
                api.getAddress(this.$route.params.id, o => {
                    this.minted = o.mintedBlkList;
                    this.obj = o;
                    this.txs = o.txList;
                }, xhr => {
                    this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404!" + this.$route.fullPath);
                });
            }
        },
        data() {
            return {
                breadcrumb: [
                    { text: this.$lang.messages.breadcrumb_home, to: "/" },
                    { text: this.$lang.messages.breadcrumb_normal_accounts, to: "/accounts" },
                    { text: this.$lang.messages.breadcrumb_address, to: "" }
                ],
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
                minted: [],
                obj: null,
                tab: 0,
                txs: []
            };
        },
        methods: {
            inOutClass(o) {
                if (o.from.hash == this.$route.params.id)
                    return "out";
                else if (o.to.hash == this.$route.params.id)
                    return "in";
                else
                    return "";
            },
            numberAddComma(n) {
                return utility.numberAddComma(n);
            },
            timeConversion(ms) {
                return utility.timeConversion(ms);
            },
            timeConversionSec(ms) {
                return utility.timeConversionSec(ms);
            },
            toWei(n) {
                return utility.toWei(n);
            },
            easyNumber(n){
                return utility.easyNumber(n);
            }
        }
    };
</script>