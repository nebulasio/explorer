<style>
    .vue-address {
        white-space: nowrap;
    }

    .vue-address td.out {
        width: 60px;
    }

    .vue-address td.in::before,
    .vue-address td.out::before,
    .vue-address td.contract::before,
    .vue-address td.self::before,
    .vue-address td.call::before {
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

    .vue-address td.contract::before {
        background-color: var(--blue);
        content: "contract deploy";
    }

    .vue-address td.self::before {
        background-color: var(--red);
        content: "self";
    }

    .vue-address td.call::before {
        background-color: var(--teal);
        content: "contract call";
    }

    .vue-address .container .table th {
        border-top: 0;
    }

    .vue-address .container .c333 tr td:nth-child(1) {
        width: 50%;
    }

    .vue-address .container .title {
        overflow: auto;
    }

    .vue-address .txfee {
        color: silver;
    }

    .vue-address .tab a {
        font-size: 13px;
    }

    .vue-address .fail {
        background: url(../../static/img/warning_icon.png)no-repeat 0 10px;
        padding-left: 28px;
    }

    .vue-address .fail a {
        display: inline-block;
        max-width: 142px;
        overflow: hidden;
        text-overflow: ellipsis;
    }
    .vue-address .tdxxxwddd{
      padding: .75rem 0.4rem;
    }
    .vue-address .tdxxxwddd img{
      margin-right: 5px;
    }
</style>
<template>
    <!-- https://etherscan.io/address/0xea674fdde714fd979de3edf0f56aa9716b898ec8 -->
    <div class=vue-address v-bind:triggerComputed=urlChange>
        <vue-bread v-if=obj v-bind:arr=breadcrumb v-bind:title='addressType + $route.params.id'></vue-bread>
        <div class=container>
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
                    <td>NAS Balance:</td>
                    <td>{{ easyNumber(obj.address.balance/1000000000000000000) }} NAS </td>
                </tr>
                <tr>
                    <td>Nonce:</td>
                    <td>{{ obj.address.nonce }}</td>
                </tr>
                <tr>
                    <td>Number Of Transactions:</td>
                    <td>{{ obj.txCnt }}</td>
                </tr>
                <tr>
                    <td>Minted:</td>
                    <td>{{ obj.mintedBlkCnt }}</td>
                </tr>
                <tr v-if=obj.address.type>
                    <td>Created By:</td>
                    <td><router-link v-bind:to='fragApi + "/address/" + contract.from'>{{ contract.from }}</router-link></td>
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
                        <router-link v-bind:to='fragApi + "/txs?a=" + $route.params.id'>{{ obj.txCnt }} transactions </router-link>
                        <router-link v-bind:to='fragApi + "/txs?a=" + $route.params.id + "&isPending=true" '>( + {{ obj.pendingTxCnt == 0? 0 : obj.pendingTxCnt }} PendingTxn )</router-link>
                    </div>
                    <div class=col-auto>
                        <span v-if="obj.address.type">
                            <router-link class="btn btn-link" v-bind:to='fragApi + "/tx/"+ contract.hash'>View Smart Contract</router-link>
                            |</span>
                        <router-link class="btn btn-link" v-bind:to='fragApi + "/txs?a=" + $route.params.id'>View All {{ obj.txCnt }} Txn</router-link>
                        |
                        <router-link class="btn btn-link" v-bind:to='fragApi + "/txs?a=" + $route.params.id + "&isPending=true" '>View All {{ obj.pendingTxCnt == 0? 0 : obj.pendingTxCnt }} PendingTxn</router-link>
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
                        <td v-if="o.status == 0" class=fail>
                            <router-link v-bind:to='fragApi + "/tx/" + o.hash'>{{ o.hash }}</router-link>
                        </td>
                        <td class=tdxxxwddd v-if="o.status != 0">
                            <router-link v-bind:to='fragApi + "/tx/" + o.hash'>{{ o.hash }}</router-link>
                        </td>
                        <td>
                            <router-link v-if=o.block.height v-bind:to='fragApi + "/block/" + o.block.height'>{{ o.block.height }}</router-link>
                            <i v-else>(pending)</i>
                        </td>
                        <td class=time>
                            <div>{{ timeConversion(Date.now() - o.timestamp) }} ago</div>
                            <div>{{ new Date(o.timestamp).toString() }} | {{ o.timestamp }}</div>
                        </td>
                        <td class=tdxxxwddd>
                            <vue-blockies v-bind:address='o.from.alias || o.from.hash'></vue-blockies>
                            <span v-if="o.from.hash == $route.params.id">{{ o.from.alias || o.from.hash }}</span>
                            <router-link v-else v-bind:to='fragApi + "/address/" + o.from.hash'>{{ o.from.alias || o.from.hash }}</router-link>
                        </td>
                        <td class=text-uppercase v-bind:class=labelClass(o)></td>
                        <td class=tdxxxwddd>
                            <vue-blockies v-bind:address='o.to.alias || o.to.hash'></vue-blockies>
                            <span v-if="o.to.hash == $route.params.id">{{ o.to.alias || o.to.hash }}</span>
                            <router-link v-else v-bind:to='fragApi + "/address/" + o.to.hash'>{{ o.to.alias || o.to.hash }}</router-link>
                        </td>
                        <td>{{ numberAddComma( parseFloat(o.value/1000000000000000000).toPrecision(15) )  }} NAS</td>
                        <td class=txfee>
                            <span v-if=o.block.height>{{ toWei(o.txFee) }}</span>
                            <i v-else>(pending)</i>
                        </td>
                    </tr>
                </table>
            </div>

            <!--    code
                ============================================================ -->
            <div class=tab v-show="tab == 2">
                <table class="mt20 table">
                        <pre><code class=language-javascript v-html=formatCode></code></pre>
                    </tr>
                </table>
            </div>

            <!--    Minted Blocks
                ============================================================ -->
            <!-- <div class=tab v-show="tab == 2">
                <div class="align-items-center row title">
                    <div class=col>
                        <span class="c333 fa fa-sort-amount-desc" aria-hidden=true></span>
                        Latest {{ minted.length }} blocks (From a total of
                        <router-link v-bind:to='fragApi + "/blocks?m=" + $route.params.id'>{{ obj.mintedBlkCnt}}</router-link>)
                    </div>
                    <div class=col-auto>
                        <router-link class="btn btn-link" v-bind:to='fragApi + "/blocks?m=" + $route.params.id'>View All</router-link>
                    </div>
                </div>

                <table class="mt20 table">
                    <tr>
                        <th>Block</th>
                        <th>Age</th>
                        <th>txn</th>
                        <th>Reward</th>
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
            </div> -->

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
    var api = require("@/assets/api"),
        prism = require("prismjs"),
        jsBeautify = require("js-beautify").js_beautify,
        utility = require("@/assets/utility");

    module.exports = {
        components: {
            "vue-bread": require("@/components/vue-bread").default,
            "vue-pagination": require("@/components/vue-pagination").default,
            "vue-tab-buttons": require("@/components/vue-tab-buttons").default,
            "vue-blockies": require("@/components/vue-blockies").default
        },
        computed: {
            formatCode() {
                var lang = prism.languages.javascript;

                if (this.obj.contractCode) {
                    return prism.highlight(jsBeautify(JSON.parse(this.obj.contractCode).Source), lang);
                }
                return "0x0";
            },
            tabButtons() {
                return this.obj && this.obj.contractCode ? ["Transactions", "Contract Code"] : ["Transactions"];
            },
            addressType() {
                return this.obj.address.type ? "Contract " : "Address ";
            },
            urlChange() {
                api.getAddress(this.$route.params.id, o => {
                    this.minted = o.mintedBlkList;
                    this.obj = o;
                    if (o.address.type == 1) {// this is a smart contract address
                        api.getTransactionByContract({ address: o.address.hash }, this.$route.params.api, (transaction) => {
                            var transaction = JSON.parse(transaction)
                            this.contract = transaction.result
                        })
                    }
                    this.txs = o.txList;
                }, xhr => {
                    this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404!" + this.$route.fullPath);
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
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
                minted: [],
                obj: null,
                contract: null,
                tab: 0,
                txs: []
            };
        },
        methods: {
            labelClass(o) {
                if (o.type == "deploy")
                    return "contract";
                else if (o.type == "call")
                    return "call";
                else if (o.from.hash == o.to.hash)
                    return "self";
                else if (o.from.hash == this.$route.params.id)
                    return "out";
                else if (o.to.hash == this.$route.params.id)
                    return "in";
                else
                    return "";
            },
            failClass(o) {
                if (o.status == 0)
                    return "!";
                else
                    return " ";
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
            easyNumber(n) {
                return utility.easyNumber(n);
            }
        }
    };
</script>