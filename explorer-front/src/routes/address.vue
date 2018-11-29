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

    .vue-address {
        background-color: white;
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

    .vue-address .tab a {
        font-size: 16px;
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

    /* .contract-creator a {
        max-width: 160px;
        overflow: hidden;
        text-overflow: ellipsis;
    } */

    .contract-creator a {
        position: relative;
    }

    .contract-creator .popover {
        background-color: rgba(0, 0, 0, .8);
        color: white;
        display: none;
        left: 50%;
        top: -50px;
        padding: 10px;
        border-radius: 0px;
        border: none;
        pointer-events: none;
        position: absolute;
        -webkit-transform: translateX(-50%);
                transform: translateX(-50%);
        white-space: nowrap;
        z-index: 1;
    }

    .contract-creator a:hover .popover {
        display: block;
    }

    #dropdown-tokens a{
        margin-right: 5px;
    }

    .c000 {
        font-size:16px;
        font-family:OpenSans-Semibold;
        font-weight:600;
        color:rgba(0,0,0,1);
        line-height:20px;
    }

    .link-text-16px {
        font-size:16px;
        font-family:OpenSans;
        color:rgba(0,87,255,1);
        line-height:20px;
    }

    .nav {
        border: none;
    }

    .nav-tabs .nav-link {
        background: #eeeeee;
        border: none;
        border-top-left-radius:0;
        border-top-right-radius:0;
        margin-right:0;
        font-size:16px;
        font-family:OpenSans-Semibold;
        font-weight:600;
        color:rgba(0,0,0,1);
        line-height:20px;
    }

    .nav-tabs .nav-item .active {
        background-color: black;
        border:none;
        font-size:16px;
        font-family:OpenSans-Semibold;
        font-weight:600;
        color:rgba(255,255,255,1);
        line-height:20px;
    }

    .tr-dark {
        background-color: #f9f9f9;
    }

</style>
<template>
    <!-- https://etherscan.io/address/0xea674fdde714fd979de3edf0f56aa9716b898ec8 -->
    <div class=vue-address v-bind:triggerComputed=urlChange>
        <vue-bread v-bind:arr=breadcrumb v-bind:title='navTitle + " " + $route.params.id'></vue-bread>
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
                    <td>NAS Balance:</td>
                    <td>{{ tokenAmount(obj.address.balance) }} NAS </td>
                </tr>
                <tr v-if="isContract">
                    <td>Contract Creator:</td>
                    <td v-if="contract.hash && contract.from" class="contract-creator">
                        <router-link v-bind:to='fragApi + "/address/" + contract.from' title="Creator Address">
                            {{ toShortStr(contract.from) }}
                            <div class="popover">Creator Address</div>
                        </router-link>
                        at tx
                        <router-link v-bind:to='fragApi + "/tx/" + contract.hash' title="Creator TxHash">
                            {{ toShortStr(contract.hash) }}
                            <div class="popover">Creator TxHash</div>
                        </router-link>
                    </td>
                    <td v-else></td>
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
                <tr v-if="obj.tokenName">
                    <td>Token Tracker:</td>
                    <td><router-link v-bind:to='fragApi + "/contract/" + $route.params.id'>{{ obj.tokenName }}</router-link></td>
                </tr>
                <tr v-for="token in tokens" :key="token.tokenName" v-if="token.tokenName == 'ATP'">
                    <td>NRC20 Tokens:</td>
                    <td>
                        <div id="dropdown-tokens" :class='{"dropdown-toggle": validTokens.length > 1}' data-toggle=dropdown>{{ tokenAmount(token.balance) }} <router-link v-bind:to='fragApi + "/contract/" + token.contract'>{{ token.tokenName }}</router-link></div>
                        <div v-if="validTokens.length > 1" class="dropdown-menu">
                            <div class="dropdown-item" v-for="(token, i) in validTokens" :key=i>
                                {{ tokenAmount(token.balance) }} <router-link v-bind:to='fragApi + "/contract/" + token.contract'>{{ token.tokenName }}</router-link>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>

            <vue-tab-buttons class=mt20 v-bind:arr=tabButtons v-bind:tab.sync=tab></vue-tab-buttons>
            <div class=mt20></div>

            <!--    Transactions
                ============================================================ -->
            <div class=tab v-show="tab == 1">
                <div class="align-items-center row title">
                    <div class=col>
                        <span class="c000">
                        Latest {{ txs.length }} txns from a total of {{ obj.txCnt }} transactions ( + {{ obj.pendingTxCnt == 0? 0 : obj.pendingTxCnt }} PendingTxn )
                        </span>
                    </div>
                    <div class=col-auto>
                        <router-link class="btn btn-link link-text-16px" v-bind:to='fragApi + "/txs?a=" + $route.params.id'>View All {{ obj.txCnt }} Txn</router-link>
                        |
                        <router-link class="btn btn-link link-text-16px" v-bind:to='fragApi + "/txs?a=" + $route.params.id + "&isPending=true" '>View All {{ obj.pendingTxCnt == 0? 0 : obj.pendingTxCnt }} PendingTxn</router-link>
                    </div>
                </div>

                <table class="mt20 table">
                    <tr>
                        <th>...</th>
                        <th>TxHash</th>
                        <th>Block</th>
                        <th>Age</th>
                        <th>From</th>
                        <th></th>
                        <th>To</th>
                        <th>Value</th>
                        <th>TxFee</th>
                    </tr>

                    <tr v-for="(o, i) in txs" :key="i" v-bind:class="{'tr-dark' : isDark(i)}">
                        <td></td>
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
                        <td class=text-uppercase v-bind:class=inOutClass(o)></td>
                        <td class=tdxxxwddd>
                            <vue-blockies v-bind:address='o.to.alias || o.to.hash'></vue-blockies>
                            <span v-if="o.to.hash == $route.params.id">{{ o.to.alias || o.to.hash }}</span>
                            <router-link v-else v-bind:to='fragApi + "/address/" + o.to.hash'>{{ o.to.alias || o.to.hash }}</router-link>
                        </td>
                        <td>{{ tokenAmount(o.value) }} NAS</td>
                        <td class=txfee>
                            <span v-if=o.block.height>{{ toWei(o.txFee) }}</span>
                            <i v-else>(pending)</i>
                        </td>
                    </tr>
                </table>
            </div>

            <!--    NRC20 Transactions
                ============================================================ -->
            <div class=tab v-show="tab == 2">
                <div class="align-items-center row title">
                    <div class=col>
                        <span class="c000">
                        Latest {{ nrc20TxList.length }} txns from a total of {{ nrc20TxCnt }} transactions
                        </span>
                    </div>
                    <div class=col-auto>
                        <router-link class="btn btn-link link-text-16px" v-bind:to='fragApi + "/txs-nrc20?a=" + $route.params.id'>View All {{ nrc20TxCnt }} Txn</router-link>
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
                        <th>TxFee</th>
                    </tr>

                    <tr v-for="(o, i) in nrc20TxList" :key="i" v-bind:class="{'tr-dark' : isDark(i)}">
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
                        <td class=text-uppercase v-bind:class=inOutClass(o)></td>
                        <td class=tdxxxwddd>
                            <vue-blockies v-bind:address='o.to.alias || o.to.hash'></vue-blockies>
                            <span v-if="o.to.hash == $route.params.id">{{ o.to.alias || o.to.hash }}</span>
                            <router-link v-else v-bind:to='fragApi + "/address/" + o.to.hash'>{{ o.to.alias || o.to.hash }}</router-link>
                        </td>
                        <td>{{ tokenAmount(o.value) }} {{ o.tokenName || '' }}</td>
                        <td class=txfee>
                            <span v-if=o.block.height>{{ toWei(o.txFee) }}</span>
                            <i v-else>(pending)</i>
                        </td>
                    </tr>
                </table>
            </div>


               <!-- code
                ============================================================ -->
            <div class=tab v-show="tab == 3">
                <table class="mt20 table">
                    <tr>
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
        utility = require("@/assets/utility"),
        BigNumber = require("bignumber.js"),
        base64 = require("js-base64").Base64;

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
                    var code = JSON.parse(this.obj.contractCode);
                    if (code.Source) {
                        return prism.highlight(jsBeautify(code.Source), lang);
                    }
                }
                return "0x0";
            },
            tabButtons() {
                var buttons = ["Transactions", "NRC20 Token Txns"];
                if (this.obj.contractCode) {
                    buttons.push("Contract Code");
                }
                return buttons;
            },
            urlChange() {
                this.tab = 1;
                this.contract = { hash: null, from : null };
                this.isContract = false;
                this.nrc20TxList = [];
                this.nrc20TxCnt = 0;
                this.$root.showModalLoading = true;
                api.getAddress(this.$route.params.id, o => {
                    this.$root.showModalLoading = false;
                    this.minted = o.mintedBlkList;
                    this.obj = o;
                    this.tokens = o.tokens;
                    if (o.address.type == 1) {// this is a smart contract address
                        this.isContract = true;
                        api.getTransactionByContract({ address: o.address.hash }, this.$route.params.api, (data) => {
                            var data = JSON.parse(data);
                            this.contract = data.result ? data.result : {};
                            this.obj.contractCode = base64.decode(this.contract.data);
                        })
                    }
                    this.txs = o.txList;
                    this.contractCode = o.contractCode;
                }, xhr => {
                    this.$root.showModalLoading = false;
                    this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404!" + this.$route.fullPath);
                });
            },
            navTitle() {
                return this.isContract ? "Contract" :"Address";
            },
            validTokens() {
                return this.tokens.filter(token => token.balance > 0);
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
                tab: 0,
                txs: [],
                tokens: [],
                isContract: false,
                contract: { hash: null, from : null },
                nrc20TxList: [],
                nrc20TxCnt: 0
            };
        },
        methods: {
            isDark(i){
              return (i%2===0);
            },
            inOutClass(o) {
                if (o.from.hash == this.$route.params.id)
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
            },
            tokenAmount(n) {
                BigNumber.config({ DECIMAL_PLACES: 18 })
                var amount = BigNumber(n);
                var decimals = BigNumber('1e+18');
                return amount.div(decimals).toFormat();
            },
            toShortStr(s) {
                if (s.length > 20) {
                    return s.substring(0, 17) + '...';
                }
                return s;
            }
        },
        watch: {
            tab: function (newTab, oldTaB) {
                if (newTab == 2 && this.nrc20TxList.length == 0) {
                    this.$root.showModalLoading = true;
                    api.getNrc20Txs(this.$route.params.id, 1, o => {
                        this.$root.showModalLoading = false;
                        this.nrc20TxList = o.txnList || [];
                        this.nrc20TxCnt = o.txnCnt;
                    }, xhr => {
                        console.log(xhr);
                        this.$root.showModalLoading = false;
                    });
                }
            }
        }
    };
</script>
