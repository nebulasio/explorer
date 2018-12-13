<style>
    div .vue-tx {
        background-color: white;
    }

    .vue-tx .table tbody tr td {
        border: 0;
    }

    .vue-tx td.code {
        background-color: #f8f9fa;
    }

    .vue-tx td.text {
        white-space: pre-line;
    }
    .vue-tx .fail {
        color:red;
    }
    .vue-tx .success {
        color:green;
    }
    .vue-tx .atpAddress{
        width: 134px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        display: inline-block;
        vertical-align: bottom;
        margin: auto 10px;
    }
    .td-left {
        width: 25%;
    }

</style>
<template>
    <div class="vue-tx fullfill" v-bind:triggerComputed=urlChange>
        <vue-bread title='TxHash' :subtitle="$route.params.id"></vue-bread>
        <div v-if="tx" class="container">
            <div class="tab explorer-table-container">
                <table class="explorer-table">
                    <div class="font-size-24-bold font-color-000000 table-title">
                        Overview
                    </div>
                    <tr>
                        <td class="td-left font-size-16-normal font-color-555555" style="padding-left: 24px;">TxHash:</td>
                        <td class="font-size-16-normal font-color-000000">{{ tx.hash }}</td>
                    </tr>
                    <tr class="font-size-16-normal">
                        <td class="font-color-555555" style="padding-left: 24px;">TxReceipt Status:</td>
                        <td v-if="tx.status === 0">
                            <img class="icon18" src="../../static/img/ic_tx_status_failed.png" />
                            <span class="font-color-F04434" style="margin-left: 10px;">fail ( {{ tx.executeError }} )</span>
                        </td>
                        <td v-else-if="tx.status === 1">
                            <img class="icon18" src="../../static/img/ic_tx_status_success.png" />
                            <span class="font-color-4560E6" style="margin-left: 10px;">success</span>
                        </td>
                        <td v-else>
                            <img class="icon18" src="../../static/img/ic_tx_status_pending.png" />
                            <span class="font-color-000000" style="margin-left: 10px;">pending</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="font-size-16-normal font-color-555555" style="padding-left: 24px;">Block Height:</td>
                        <td>
                            <template v-if=tx.isPending>
                                <span class="font-color-000000 font-size-16-normal"> pending </span>
                            </template>
                            <template v-else>
                                <router-link v-if=tx.block v-bind:to='fragApi +"/block/" + tx.block.height'>
                                    <span class="font-color-0057FF font-size-16-normal">{{tx.block.height}}</span>
                                </router-link>
                            </template>
                        </td>
                    </tr>
                    <tr>
                        <td class="font-size-16-normal font-color-555555" style="padding-left: 24px;">TimeStamp:</td>
                        <td class="font-size-16-normal font-color-000000">{{ timeConversion(Date.now() - tx.timestamp) }} ago ({{ new Date(tx.timestamp).toString().replace('GMT', 'UTC').replace(/\(.+\)/gi, '') }} | {{ tx.timestamp }})
                        </td>
                    </tr>
                    <tr>
                        <td class="font-size-16-normal font-color-555555" style="padding-left: 24px;">From:</td>
                        <td>
                            <router-link v-if=tx.from v-bind:to='fragApi +"/address/" + tx.from.hash'>
                                <span class="font-color-0057FF font-size-16-normal">{{ tx.from.hash }}</span>
                            </router-link>
                        </td>
                    </tr>
                    <tr>
                        <td class="font-size-16-normal font-color-555555" style="padding-left: 24px;">To:</td>
                        <td class=monospace v-if=isTokenTransfer>
                            <span class="font-color-000000 font-size-16-normal">Contract</span>
                            <router-link v-if=tx.to v-bind:to='fragApi +"/address/" + tx.to.hash'>
                                <span style="margin-left: 20px;" class="font-size-16-normal font-color-0057FF">{{ tx.to.hash }}</span>
                            </router-link>
                            <div class="token-name font-size-16-normal font-color-000000" style="margin-left: 14px;" v-if="tx.tokenName">{{ '【' + tx.tokenName + '】' }}</div>
                        </td>
                        <td v-else>
                            <router-link v-if=tx.to v-bind:to='fragApi +"/address/" + tx.to.hash'>
                                <span class="font-color-0057FF font-size-16-normal">{{ tx.to.hash }}</span>
                            </router-link>
                        </td>
                    </tr>
                    <tr  v-if=isTokenTransfer class="font-size-16-normal">
                        <td class="font-color-555555" style="padding-left: 24px;">Token Transfered:</td>
                        <td>
                            <span class="font-color-000000">From</span>
                            <router-link class=atpAddress v-if=tx.to v-bind:to='fragApi +"/address/" + tx.from.hash'>
                                <span class="font-color-0057FF">{{ tx.from.hash }}</span>
                            </router-link>
                            <span class="font-color-000000">To </span>
                            <router-link  class=atpAddress v-if=tx.to v-bind:to='fragApi +"/address/" + JSON.parse(JSON.parse(tx.data).Args)[0]'>
                                <span class="font-color-0057FF">{{ JSON.parse(JSON.parse(tx.data).Args)[0] }} </span>
                            </router-link>
                            <span class="font-color-000000">for {{ tokenAmount }}</span>
                            <div class="token-name" v-if="tx.tokenName">
                                <span class="font-color-000000">{{ (tx.tokenName) }}</span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="font-size-16-normal font-color-555555" style="padding-left: 24px;">Value:</td>
                        <td class="font-size-16-normal font-color-000000">{{ nasAmount(tx.value) }} NAS</td>
                    </tr>

                </table>

                <table class="explorer-table">
                    <div class="font-size-24-bold font-color-000000 table-title">
                        Misc
                    </div>
                    <tr>
                        <td class="td-left font-size-16-normal font-color-555555" style="padding-left: 24px;">Gas Limit:</td>
                        <td class="font-color-000000 font-size-16-normal">{{ numberAddComma(tx.gasLimit) }}</td>
                    </tr>
                    <tr>
                        <td class="font-size-16-normal font-color-555555" style="padding-left: 24px;">Gas Used By Txn:</td>
                        <td class="font-color-000000 font-size-16-normal">{{ tx.isPending === true ? 'pending' : numberAddComma(tx.gasUsed) }}</td>
                    </tr>
                    <tr>
                        <td class="font-size-16-normal font-color-555555" style="padding-left: 24px;">Gas Price:</td>
                        <td class="font-color-000000 font-size-16-normal">{{ toWei(tx.gasPrice) }}</td>
                    </tr>
                    <tr>
                        <td class="font-size-16-normal font-color-555555" style="padding-left: 24px;">Actual Tx Cost/Fee:</td>
                        <td class="font-color-000000 font-size-16-normal">{{ toWei(tx.txFee) }}</td>
                    </tr>
                    <tr>
                        <td class="font-size-16-normal font-color-555555" style="padding-left: 24px;">Nonce:</td>
                        <td class="font-color-000000 font-size-16-normal">{{ tx.nonce }}</td>
                    </tr>
                    <tr>
                        <td class="font-size-16-normal font-color-555555" style="padding-left: 24px;">Transaction Type:</td>
                        <td class="font-color-000000 font-size-16-normal" v-if=" tx.type === 'deploy'">{{ txType }} ( contract address: <router-link v-if=tx.to v-bind:to='fragApi +"/address/" + tx.contractAddress'> <span class="font-color-0057FF"> {{tx.contractAddress}}</span> </router-link>)</td>
                        <td class="font-color-000000 font-size-16-normal" v-else>{{ txType }}</td>
                    </tr>
                    <tr>
                        <td class="font-size-16-normal font-color-555555" style="padding-left: 24px;">SourceType:</td>
                        <td class="font-color-000000 font-size-16-normal" v-if=" tx.type === 'deploy'">{{ JSON.parse(tx.data).SourceType }}</td>
                        <td v-else></td>
                    </tr>
                    <tr>
                        <td class="font-size-16-normal font-color-555555" style="padding-left: 24px;">Args:</td>
                        <td class="font-color-000000 font-size-16-normal" v-if=" tx.type === 'deploy'">{{ JSON.parse(tx.data).Args }}</td>
                        <td v-else></td>
                    </tr>
                    <tr>
                        <td class="font-size-16-normal font-color-555555" style="padding-left: 24px;">Payload Data:</td>
                        <td v-if="tx.type === 'binary'" class=text>
                            {{ tx.data }}
                        </td>
                        <td v-else class=code>
                            <div v-on:click="showOrHidePayload()" style="display: flex;">
                                <span class="font-size-16-normal font-color-0057FF">View all</span>
                                <img style="margin-left: 12px; margin-top: 3px; vertical-align: middle;" class="icon16" v-bind:src="isShowPayload ? '../../static/img/ic_payload_arrow_up.png' : '../../static/img/ic_payload_arrow_down.png'" />
                            </div>
                        </td>
                    </tr>
                    <tr v-if="isShowPayload === true">
                        <td></td>
                        <td>
                            <pre><code class=language-javascript v-html=formatCode></code></pre>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</template>
<script>
    var jsBeautify = require("js-beautify").js_beautify,
        prism = require("prismjs"),
        api = require("@/assets/api"),
        utility = require("@/assets/utility"),
        appConfig = require("@/assets/app-config"),
        BigNumber = require("bignumber.js");

    require("prismjs/themes/prism.css");

    module.exports = {
        components: {
            "vue-bread": require("@/components/vue-bread").default,
           "vue-tab-buttons": require("@/components/vue-tab-buttons").default
        },
        computed: {

            formatCode() {
                var lang = prism.languages.javascript;
                if (this.tx.data)
                    if (this.tx.type =="deploy")
                        return prism.highlight(jsBeautify(JSON.parse(this.tx.data).Source), lang);
                    else if (this.tx.type =="call")
                        return prism.highlight(jsBeautify(this.tx.data), lang);

                return"0x0";
            },
            txType() {
                // type=binary      【前端显示：Normal】
                // type=deploy      【前端显示：deploy contract】
                // type=call        【前端显示：call contract】
                // type=candidate   【前端显示：dpos candidate】
                // type=delegate    【前端显示：dpos delegate】
                if (this.tx) switch (this.tx.type) {
                    default:
                    case"binary": return"normal";
                    case"deploy": return"deploy contract";
                    case"call": return"call contract";
                    case"candidate": return"dpos candidate";
                    case"delegate": return"dpos delegate";
                } else
                    return"";
            },
            urlChange() {
                this.$root.showModalLoading = true;
                api.getTx(this.$route.params.id, o => {
                    this.$root.showModalLoading = false;
                    this.tx = o;
                    // if (!o.tokenName || o.tokenName.length == 0) {
                    //     if (o.to.hash == this.atpAddress()) {
                    //         this.tx.tokenName ="ATP";
                    //     }
                    // }
                }, xhr => {
                    this.$root.showModalLoading = false;
                    this.$router.replace((this.$route.params.api ?"/" + this.$route.params.api :"") +"/404");
                });
            },
            isTokenTransfer() {
                try {
                    if (this.tx.type == 'call' && JSON.parse(this.tx.data).Function == 'transfer' && JSON.parse(JSON.parse(this.tx.data).Args).length >= 2) {
                        return true;
                    }
                } catch (error) {
                }
                return false;
            },
            tokenAmount() {
                BigNumber.config({ DECIMAL_PLACES: this.tx.decimal })
                var amount = BigNumber(JSON.parse(JSON.parse(this.tx.data).Args)[1]);
                var decimals = BigNumber('1e+' + this.tx.decimal);
                return amount.div(decimals).toFormat();
            }
        },
        data() {
            return {
                fragApi: this.$route.params.api ?"/" + this.$route.params.api :"",
                tab: 0,
                tabButtons: ["Overview"],
                tx: null,
                isShowPayload: false
            };
        },
        methods: {
            showOrHidePayload(){
                this.isShowPayload = !this.isShowPayload;
            },
            numberAddComma(n) {
                return utility.numberAddComma(n);
            },
            timeConversion(ms) {
                return utility.timeConversion(ms);
            },
            toWei(n) {
                return utility.toWei(n);
            },
            nasAmount(n) {
                BigNumber.config({ DECIMAL_PLACES: 18 })
                var amount = BigNumber(n);
                var decimals = BigNumber('1e+18');
                return amount.div(decimals).toFormat();
            },
            atpAddress() {
                var api = this.$route.params.api ? this.$route.params.api :"mainnet";
                return appConfig.apiPrefixes[api].atp;
            }
        }
    };
</script>
