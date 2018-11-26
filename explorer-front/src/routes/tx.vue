<style>
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
        line-height: 18px;
        vertical-align: bottom;
    }

</style>
<template>
    <div class="container vue-tx" v-bind:triggerComputed=urlChange>
        <vue-tab-buttons class=mt20 v-bind:arr=tabButtons v-bind:tab.sync=tab></vue-tab-buttons>
        <div class=mt20></div>

        <div class=tab v-show="tab == 1">
            <h3>Transaction Information</h3>
            <table class=table v-if=tx>
                <tr>
                    <td>TxHash:</td>
                    <td class=monospace>{{ tx.hash }}</td>
                </tr>
                <tr>
                    <td>TxReceipt Status:</td>
                    <td v-if="tx.status == 0">
                        <span class="fail">fail ( {{ tx.executeError }} )</span>
                    </td>
                    <td v-else-if="tx.status == 1">
                        <span class="success">success</span>
                    </td>
                    <td v-else>
                        pending
                    </td>
                </tr>
                <tr>
                    <td>Block Height:</td>
                    <td>
                        <template v-if=tx.isPending>
                            <span> pending </span>
                        </template>
                        <template v-else>
                            <router-link v-if=tx.block v-bind:to='fragApi + "/block/" + tx.block.height'>{{tx.block.height}}</router-link>
                        </template>
                    </td>
                </tr>
                <tr>
                    <td>TimeStamp:</td>
                    <td>{{ timeConversion(Date.now() - tx.timestamp) }} ago ({{ new Date(tx.timestamp).toString() }} | {{ tx.timestamp }})
                    </td>
                </tr>
                <tr>
                    <td>From:</td>
                    <td class=monospace>
                        <router-link v-if=tx.from v-bind:to='fragApi + "/address/" + tx.from.hash'>{{ tx.from.hash }}</router-link>
                    </td>
                </tr>
                <tr>
                    <td>To:</td>
                    <td class=monospace v-if=isTokenTransfer>
                        Contract 
                        <router-link v-if=tx.to v-bind:to='fragApi + "/address/" + tx.to.hash'>{{ tx.to.hash }}</router-link> 
                        <div class="token-name" v-if="tx.tokenName">{{ '(' + tx.tokenName + ')' }}</div>
                    </td>
                    <td class=monospace v-else>
                        <router-link v-if=tx.to v-bind:to='fragApi + "/address/" + tx.to.hash'>{{ tx.to.hash }}</router-link>
                    </td>
                </tr>
                <tr  v-if=isTokenTransfer>
                    <td>Token Transfered:</td>
                    <td class=monospace>
                        From <router-link class=atpAddress v-if=tx.to v-bind:to='fragApi + "/address/" + tx.from.hash'>{{ tx.from.hash }}</router-link> To <router-link  class=atpAddress v-if=tx.to v-bind:to='fragApi + "/address/" + JSON.parse(JSON.parse(tx.data).Args)[0]'>{{ JSON.parse(JSON.parse(tx.data).Args)[0] }} </router-link> for {{ tokenAmount }} <div class="token-name" v-if="tx.tokenName">{{ (tx.tokenName) }}</div>
                    </td>
                </tr>
                <tr>
                    <td>Value:</td>
                    <td>{{ nasAmount(tx.value) }} NAS</td>
                </tr>
                <tr>
                    <td>Gas Limit:</td>
                    <td>{{ numberAddComma(tx.gasLimit) }}</td>
                </tr>
                <tr>
                    <td>Gas Used By Txn:</td>
                    <td>{{ tx.isPending == true ? 'pending' : numberAddComma(tx.gasUsed) }}</td>
                </tr>
                <tr>
                    <td>Gas Price:</td>   
                    <td>{{ toWei(tx.gasPrice) }}</td>
                </tr>
                <tr>
                    <td>Actual Tx Cost/Fee:</td>
                    <td>{{ toWei(tx.txFee) }}</td>
                </tr>
                <tr>
                    <td>Nonce:</td>
                    <td>{{ tx.nonce }}</td>
                </tr>
                <tr>
                    <td>Transaction Type:</td>
                    <td v-if=" tx.type == 'deploy' ">{{ txType }} ( contract address: <router-link v-if=tx.to v-bind:to='fragApi + "/address/" + tx.contractAddress'> {{tx.contractAddress}} </router-link>)</td>
                    <td v-else>{{ txType }}</td>
                </tr>
                <tr>
                    <td>SourceType:</td>
                    <td v-if=" tx.type == 'deploy' ">{{ JSON.parse(tx.data).SourceType }}</td>
                    <td v-else></td>
                </tr>
                <tr>
                    <td>Args:</td>
                    <td v-if=" tx.type == 'deploy' ">{{ JSON.parse(tx.data).Args }}</td>
                    <td v-else></td>
                </tr>
                <tr>
                    <td>Payload Data:</td>
                    <td v-if="tx.type == 'binary'" class=text>
                        {{ tx.data }}
                    </td>
                    <td v-else class=code>
                        <pre><code class=language-javascript v-html=formatCode></code></pre>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </div>

        <div class=tab v-show="tab == 2">
            <h3>Internal Transactions</h3> 
        </div>

        <div class=tab v-show="tab == 3">
            <h3>Event Logs</h3>
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
            "vue-tab-buttons": require("@/components/vue-tab-buttons").default
        },
        computed: {
            
            formatCode() {
                var lang = prism.languages.javascript;
                if (this.tx.data)
                    if (this.tx.type == "deploy")
                        return prism.highlight(jsBeautify(JSON.parse(this.tx.data).Source), lang);
                    else if (this.tx.type == "call")
                        return prism.highlight(jsBeautify(this.tx.data), lang);

                return "0x0";
            },
            txType() {
                // type=binary      【前端显示：Normal】
                // type=deploy      【前端显示：deploy contract】
                // type=call        【前端显示：call contract】
                // type=candidate   【前端显示：dpos candidate】
                // type=delegate    【前端显示：dpos delegate】
                if (this.tx) switch (this.tx.type) {
                    default:
                    case "binary": return "normal";
                    case "deploy": return "deploy contract";
                    case "call": return "call contract";
                    case "candidate": return "dpos candidate";
                    case "delegate": return "dpos delegate";
                } else
                    return "";
            },
            urlChange() {
                this.$root.showModalLoading = true;
                api.getTx(this.$route.params.id, o => {
                    this.$root.showModalLoading = false;
                    this.tx = o;
                    if (!o.tokenName || o.tokenName.length == 0) {
                        if (o.to.hash == this.atpAddress()) {
                            this.tx.tokenName = "ATP";
                        }
                    }
                }, xhr => {
                    this.$root.showModalLoading = false;
                    this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404!" + this.$route.fullPath);
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
                BigNumber.config({ DECIMAL_PLACES: 18 })
                var amount = BigNumber(JSON.parse(JSON.parse(this.tx.data).Args)[1]);
                var decimals = BigNumber('1e+18');
                return amount.div(decimals).toFormat();
            }
        },
        data() {
            return {
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
                tab: 0,
                tabButtons: ["Overview"],
                tx: {tokenName: null}
            };
        },
        methods: {
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
                var api = this.$route.params.api ? this.$route.params.api : "mainnet";
                return appConfig.apiPrefixes[api].atp;
            }
        }
    };
</script>
