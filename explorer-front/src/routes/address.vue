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
        font-size: 14px;
        font-family: OpenSans;
        color: rgba(85, 85, 85, 1);
        line-height: 20px;
    }

    .vue-address .tab a {
        font-size: 16px;
    }

    .vue-address .fail {
        background: url(../../static/img/warning_icon.png) no-repeat 0 10px;
        padding-left: 28px;
    }

    .vue-address .fail a {
        display: inline-block;
        max-width: 142px;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .vue-address .tdxxxwddd {
        padding: .75rem 0.4rem;
    }

    .vue-address .tdxxxwddd img {
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

    #dropdown-tokens a {
        margin-right: 5px;
    }

    .c000 {
        font-size: 16px;
        font-family: OpenSans-Semibold;
        font-weight: 600;
        color: rgba(0, 0, 0, 1);
        line-height: 20px;
    }

    .link-text-16px {
        font-size: 16px;
        font-family: OpenSans;
        color: rgba(0, 87, 255, 1);
        line-height: 20px;
    }

    .txs-hash {
        max-width: 185px;
        overflow: hidden;
        text-overflow: ellipsis;
        vertical-align: center;
        padding: 0;
    }

    .txs-block {
        max-width: 120px;
        overflow: hidden;
        text-overflow: ellipsis;
        vertical-align: center;
        padding: 0;
    }

    .txs-from-to {
        max-width: 168px;
    }

    .txs-from-to a {
        max-width: 134px;
    }

    .hash-normal {
        height: 20px;
        font-size: 14px;
        font-family: OpenSans;
        color: rgba(0, 87, 255, 1);
        line-height: 20px;
    }

    .hash-failed {
        height: 20px;
        font-size: 14px;
        font-family: OpenSans;
        line-height: 20px;
        color: rgba(240, 68, 52, 1);
    }

    .fromTo {
        max-width: 134px;
        margin-left: 10px;
        height: 20px;
        font-size: 14px;
        font-family: OpenSans;
        color: rgba(0, 87, 255, 1);
        line-height: 20px;
    }

    .amount {
        font-size: 14px;
        font-family: OpenSans;
        color: rgba(0, 0, 0, 1);
        line-height: 20px;
    }

    .overview {
        margin-top: 60px;
        margin-bottom: 30px;
        height: 30px;
        font-size: 24px;
        font-family: OpenSans-Semibold;
        font-weight: 600;
        color: rgba(0, 0, 0, 1);
        line-height: 30px;
    }

    .base-info-key {
        width: 23%;
    }

    .base-info-value-normal {
        height: 20px;
        font-size: 16px;
        font-family: OpenSans;
        color: rgba(0, 0, 0, 1);
        line-height: 20px;
    }

    .base-info-value-num-of-tx {
        height: 20px;
        font-size: 16px;
        font-family: OpenSans;
        color: rgba(0, 87, 255, 1);
        line-height: 20px;
    }

    .base-info-value-token-name {
        height: 20px;
        font-size: 16px;
        font-family: OpenSans-Semibold;
        font-weight: 600;
        color: rgba(0, 87, 255, 1);
        line-height: 20px;
    }

    .text-no-content {
        height: 17px;
        font-size: 12px;
        font-family: OpenSans-Semibold;
        font-weight: 600;
        color: rgba(155, 155, 155, 1);
        line-height: 17px;
    }

</style>
<template>
    <!-- https://etherscan.io/address/0xea674fdde714fd979de3edf0f56aa9716b898ec8 -->
    <div class="vue-address fullfill" v-bind:triggerComputed=urlChange>
        <vue-bread v-bind:title='navTitle'
                   v-bind:subtitle="$route.params.id"></vue-bread>
        <div class="container explorer-table-container" v-if=obj>

            <table class="explorer-table">
                <div class="font-size-24-bold font-color-000000 table-title">
                    Overview
                    <span class=c777 v-show=obj.address.alias> | {{ obj.address.alias }}</span>
                </div>
                <tr>
                    <td class="base-info-key font-size-16-normal font-color-555555">NAS Balance:
                    </td>
                    <td class="font-size-16-normal font-color-000000">
                        {{tokenAmount(obj.address.balance, decimal) }} NAS
                    </td>
                </tr>
                <tr v-if="isContract && contract">
                    <td class="base-info-key font-size-16-normal font-color-555555">
                        Contract Creator:
                    </td>
                    <td v-if="contract.hash && contract.from"
                        class="contract-creator font-size-16-normal font-color-000000">
                        <router-link v-bind:to='fragApi + "/address/" + contract.from'
                                     title="Creator Address">
                            <span class="font-color-0057FF">{{ toShortStr(contract.from) }}</span>
                            <div class="popover">Creator Address</div>
                        </router-link>
                        at tx
                        <router-link v-bind:to='fragApi + "/tx/" + contract.hash'
                                     title="Creator TxHash">
                            <span class="font-color-0057FF">{{ toShortStr(contract.hash) }}</span>
                            <div class="popover">Creator TxHash</div>
                        </router-link>
                    </td>
                    <td v-else></td>
                </tr>
                <tr>
                    <td class="base-info-key font-size-16-normal font-color-555555">Nonce:</td>
                    <td class="font-size-16-normal font-color-000000">{{ obj.address.nonce }}</td>
                </tr>
                <tr>
                    <td class="base-info-key font-size-16-normal font-color-555555">Number Of
                        Transactions:
                    </td>
                    <td class="font-size-16-normal font-color-0057FF">{{ obj.txCnt }}</td>
                </tr>
                <tr>
                    <td class="base-info-key font-size-16-normal font-color-555555">Minted:</td>
                    <td class="font-size-16-normal font-color-000000">{{ obj.mintedBlkCnt }}</td>
                </tr>
                <tr v-if="obj.tokenName">
                    <td class="base-info-key font-size-16-normal font-color-555555">Token Tracker:
                    </td>
                    <td class="font-size-16-normal font-color-000000">
                        <router-link v-bind:to='fragApi + "/contract/" + $route.params.id'>
                            <span class="font-color-0057FF">{{obj.tokenName }}</span>
                        </router-link>
                    </td>
                </tr>
                <tr v-for="token in tokens" :key="token.tokenName" v-if="token.tokenName === 'ATP'">
                    <td class="base-info-key font-size-16-normal font-color-555555">NRC20 Tokens:
                    </td>
                    <td>
                        <div id="dropdown-tokens"
                             :class='[{"dropdown-toggle": validTokens.length > 1}]'
                             data-toggle=dropdown>
                            <router-link v-bind:to='fragApi + "/contract/" + token.contract'>
                                <span class="font-size-16-bold font-color-0057FF">{{token.tokenName }}</span>
                            </router-link>
                            <span class="font-size-16-normal font-color-000000">{{ tokenAmount(token.balance, token.decimal) }}</span>
                        </div>
                        <div v-if="validTokens.length > 1" class="dropdown-menu">
                            <div class="dropdown-item" v-for="(token, i) in validTokens" :key=i>
                                <router-link v-bind:to='fragApi + "/contract/" + token.contract'>
                                    {{ token.tokenName }}
                                </router-link>
                                {{ tokenAmount(token.balance, token.decimal) }}
                            </div>
                        </div>
                    </td>
                </tr>
            </table>

            <vue-tab-buttons class=mt50 v-bind:arr=tabButtons v-bind:tab.sync=tab></vue-tab-buttons>
            <div class=mt20></div>

            <!--    Transactions
                ============================================================ -->
            <div class="tab explorer-table-container" v-show="tab == 1">
                <div class="align-items-center row title">
                    <div class=col>
                        <span class="font-size-16-bold font-color-000000">
                        Latest {{ txs.length }} txns from a total of {{ obj.txCnt }} transactions ( + {{ obj.pendingTxCnt == 0? 0 : obj.pendingTxCnt }} PendingTxn )
                        </span>
                    </div>
                    <div class=col-auto>
                        <router-link class="btn btn-link link-text-16px"
                                     v-bind:to='fragApi + "/txs?a=" + $route.params.id'>View All
                            {{obj.txCnt }} Txn
                        </router-link>
                        |
                        <router-link class="btn btn-link link-text-16px"
                                     v-bind:to='fragApi + "/txs?a=" + $route.params.id + "&isPending=true" '>
                            View All {{ obj.pendingTxCnt == 0? 0 : obj.pendingTxCnt }} PendingTxn
                        </router-link>
                    </div>
                </div>

                <table class="mt20 explorer-table list-table">
                    <tr class="font-size-12-bold font-color-000000" style="height: 46px; background-color: #e8e8e8;">
                        <th v-if="isContract"></th>
                        <th v-else style="width: 50px;"></th>
                        <th>TxHash</th>
                        <th>Block</th>
                        <th>Age</th>
                        <th>From</th>
                        <th></th>
                        <th>To</th>
                        <th class="align-right">Value</th>
                        <th class="align-right">TxFee</th>
                    </tr>

                    <tr v-for="(o, i) in txs" :key="i" v-bind:class="{'tr-dark' : isDark(i)}">
                        <td v-if="isContract">
                            <img v-if="o.status===0" class="icon40"
                                 src="../../static/img/ic_tx_failed.png"/>
                        </td>
                        <td v-else>
                            <img class="icon40" v-bind:src="statusIcon(o,o.status)"/>
                        </td>
                        <td class="txs-hash">
                            <router-link v-bind:to='fragApi + "/tx/" + o.hash'>
                                <span v-bind:class="[o.status===0 ? 'hash-failed' : 'hash-normal']">{{ o.hash }}</span>
                            </router-link>
                        </td>
                        <td class="txs-block">
                            <router-link class="font-size-14-normal font-color-4560E6"
                                         v-if=o.block.height
                                         v-bind:to='fragApi + "/block/" + o.block.height'>
                                <span class="font-size-14-normal font-color-4560E6">{{ o.block.height }}</span>
                            </router-link>
                            <i class="font-size-14-normal font-color-000000" v-else>pending</i>
                        </td>
                        <td class="time font-color-555555 font-size-14-normal">
                            <div>{{ timeConversion(Date.now() - o.timestamp) }} ago</div>
                            <div>{{ new Date(o.timestamp).toString() }} | {{ o.timestamp }}</div>
                        </td>
                        <td class="tdxxxwddd txs-from-to" style="padding: 0;">
                            <vue-blockies v-bind:address='o.from.alias || o.from.hash'></vue-blockies>
                            <span class="fromTo" v-if="o.from.hash == $route.params.id">{{ o.from.alias || o.from.hash }}</span>
                            <router-link v-else v-bind:to='fragApi + "/address/" + o.from.hash'>
                                <span class="fromTo">{{ o.from.alias || o.from.hash }}</span>
                            </router-link>
                        </td>
                        <td style="padding: 0;">
                            <img style="width:40px;height:40px" src="../../static/img/ic_arrow_right.png"/>
                        </td>
                        <td class="tdxxxwddd txs-from-to" style="padding: 0;">
                            <vue-blockies v-bind:address='o.to.alias || o.to.hash'></vue-blockies>
                            <span class="fromTo" v-if="o.to.hash == $route.params.id">{{ o.to.alias || o.to.hash }}</span>
                            <router-link v-else v-bind:to='fragApi + "/address/" + o.to.hash'>
                                <span class="fromTo">{{ o.to.alias || o.to.hash }}</span>
                            </router-link>
                        </td>
                        <td class="amount align-right">{{ tokenAmount(o.value, o.decimal) }} NAS</td>
                        <td class="txfee align-right">
                            <span v-if=o.block.height>{{ toWei(o.txFee) }}</span>
                            <i v-else>(pending)</i>
                        </td>
                    </tr>
                </table>


                <div v-if="txs.length===0" v-show="tab===1"
                     style="left: 0;right:0;text-align:center; padding-top: 76px; padding-bottom: 80px;">
                    <img style="width: 131px; height: 142px;" src="/static/img/no_content.png"/>
                    <br/>
                    <div style="margin-top: 12px;">
                        <span class="text-no-content">No Content</span>
                    </div>
                </div>
            </div>

            <!--    NRC20 Transactions
                ============================================================ -->
            <div class="tab explorer-table-container" v-show="tab === 2">
                <div class="align-items-center row title">
                    <div class=col>
                        <span class="c000">
                        Latest {{ nrc20TxList.length }} txns from a total of {{ nrc20TxCnt }} transactions
                        </span>
                    </div>
                    <div class=col-auto>
                        <router-link class="btn btn-link link-text-16px"
                                     v-bind:to='fragApi + "/txs-nrc20?a=" + $route.params.id'>View
                            All {{ nrc20TxCnt }} Txn
                        </router-link>
                    </div>
                </div>

                <table class="mt20 explorer-table list-table">
                    <tr class="font-size-12-bold font-color-000000" style="height: 46px; background-color: #e8e8e8;">
                        <th style="width: 50px;"></th>
                        <th>TxHash</th>
                        <th>Block</th>
                        <th>Age</th>
                        <th>From</th>
                        <th></th>
                        <th>To</th>
                        <th class="align-right">Value</th>
                        <th class="align-right">TxFee</th>
                    </tr>

                    <tr v-for="(o, i) in nrc20TxList" :key="i">
                        <td><img class="icon40" v-bind:src="statusIcon(o,o.status)"/></td>
                        <td class="txs-hash">
                            <router-link v-bind:to='fragApi + "/tx/" + o.hash'>
                                <span v-bind:class="[o.status===0 ? 'hash-failed' : 'hash-normal']">{{ o.hash }}</span>
                            </router-link>
                        </td>

                        <td class="txs-block">
                            <router-link class="font-size-14-normal font-color-4560E6"
                                         v-if=o.block.height
                                         v-bind:to='fragApi + "/block/" + o.block.height'>
                                <span class="font-size-14-normal font-color-4560E6">{{ o.block.height }}</span>
                            </router-link>
                            <i class="font-size-14-normal font-color-000000" v-else>pending</i>
                        </td>
                        <td class="time font-color-555555 font-size-14-normal">
                            <div>{{ timeConversion(Date.now() - o.timestamp) }} ago</div>
                            <div>{{ new Date(o.timestamp).toString() }} | {{ o.timestamp }}</div>
                        </td>
                        <td class="tdxxxwddd txs-from-to" style="padding: 0;">
                            <vue-blockies v-bind:address='o.from.alias || o.from.hash'></vue-blockies>
                            <span class="fromTo" v-if="o.from.hash == $route.params.id">{{ o.from.alias || o.from.hash }}</span>
                            <router-link v-else v-bind:to='fragApi + "/address/" + o.from.hash'>
                                <span class="fromTo">{{ o.from.alias || o.from.hash }}</span>
                            </router-link>
                        </td>
                        <td style="padding: 0;">
                            <img style="width:40px;height:40px" src="../../static/img/ic_arrow_right.png"/>
                        </td>
                        <td class="tdxxxwddd txs-from-to" style="padding: 0;">
                            <vue-blockies v-bind:address='o.to.alias || o.to.hash'></vue-blockies>
                            <span class="fromTo" v-if="o.to.hash == $route.params.id">{{ o.to.alias || o.to.hash }}</span>
                            <router-link v-else v-bind:to='fragApi + "/address/" + o.to.hash'><span
                                class="fromTo">{{ o.to.alias || o.to.hash }}</span></router-link>
                        </td>
                        <td class="amount align-right">{{ tokenAmount(o.value, o.decimal) }} {{ o.tokenName || '' }}
                        </td>
                        <td class="txfee align-right">
                            <span v-if=o.block.height>{{ toWei(o.txFee) }}</span>
                            <i v-else>(pending)</i>
                        </td>
                    </tr>
                </table>

                <div v-if=isNoNrc20Tx v-show="tab===2"
                     style="left: 0;right:0;text-align:center; padding-top: 76px; padding-bottom: 80px;">
                    <img style="width: 131px; height: 142px;" src="/static/img/no_content.png"/>
                    <br/>
                    <div style="margin-top: 12px;">
                        <span class="text-no-content">No Content</span>
                    </div>
                </div>
            </div>


            <!-- code
             ============================================================ -->
            <div class=tab v-show="tab === 3">
                <table class="mt20 table">
                    <tr>
                        <pre><code class=language-javascript v-html=formatCode></code></pre>
                    </tr>
                </table>
            </div>

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
                this.contract = null;
                this.isContract = false;
                this.nrc20TxList = [];
                this.nrc20TxCnt = 0;
                this.$root.showModalLoading = true;
                api.getAddress(this.$route.params.id, o => {
                    this.$root.showModalLoading = false;
                    this.minted = o.mintedBlkList;
                    this.obj = o;
                    this.decimal = o.decimal;
                    this.tokens = o.tokens;
                    if (o.address.type == 1) {// this is a smart contract address
                        this.isContract = true;
                        api.getTransactionByContract({address: o.address.hash}, this.$route.params.api, (data) => {
                            var data = JSON.parse(data);
                            this.contract = data.result ? data.result : {};
                            this.obj.contractCode = base64.decode(this.contract.data);
                        })
                    }
                    this.txs = o.txList;
                    this.contractCode = o.contractCode;
                }, xhr => {
                    this.$root.showModalLoading = false;
                    this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404");
                });
            },
            navTitle() {
                return this.isContract ? "Contract" : "Address";
            },
            validTokens() {
                return this.tokens.filter(token => token.balance > 0);
            }
        },
        data() {
            return {
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
                minted: [],
                obj: null,
                tab: 0,
                txs: [],
                tokens: [],
                decimal: null,
                isContract: false,
                contract: null,
                nrc20TxList: [],
                nrc20TxCnt: 0,
                isNoNrc20Tx: false
            };
        },
        methods: {
            isDark(i) {
                return (i % 2 === 0);
            },
            statusIcon(tx, status) {
                let imgPath = "/static/img/";
                var inOrOut;
                if (tx.from.hash === this.$route.params.id)
                    inOrOut = "out";
                else if (tx.to.hash === this.$route.params.id)
                    inOrOut = "in";
                else
                    inOrOut = "";

                if (status === 1) {
                    if (inOrOut === "out") {
                        return imgPath + "ic_tx_sent.png"
                    } else if (inOrOut === "in") {
                        return imgPath + "ic_tx_received.png"
                    } else {
                        return ""
                    }
                } else if (status === null) {
                    if (inOrOut === "out") {
                        return imgPath + "ic_tx_send_pending.png"
                    } else if (inOrOut === "in") {
                        return imgPath + "ic_tx_receive_pending.png"
                    } else {
                        return ""
                    }
                } else {
                    return imgPath + "ic_tx_failed.png"
                }
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
            tokenAmount(n, decimals) {
                BigNumber.config({DECIMAL_PLACES: decimals || 18})
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
                        this.isNoNrc20Tx = this.nrc20TxCnt === 0;
                    }, xhr => {
                        console.log(xhr);
                        this.$root.showModalLoading = false;
                    });
                }
            }
        }
    };
</script>
