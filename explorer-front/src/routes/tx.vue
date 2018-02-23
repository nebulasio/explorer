<style>
    .vue-tx .table tbody tr td {
        border: 0;
    }

    .vue-tx td.code {
        background-color: #f8f9fa;
    }
</style>
<template>
    <div class="container vue-tx" v-bind:triggerComputed=urlChange>
        <vue-tab-buttons class=mt20 v-bind:arr=tabButtons v-bind:tab.sync=tab></vue-tab-buttons>
        <div class=mt20></div>

        <div class=tab v-show="tab == 1">
            <h3>Transation Information</h3>
            <table class=table v-if=tx>
                <tr>
                    <td>TxHash:</td>
                    <td class=monospace>{{ tx.hash }}</td>
                </tr>
                <tr>
                    <td>TxReceipt Status:</td>
                    <td>Success</td>
                </tr>
                <tr>
                    <td>Block Height:</td>
                    <td>
                        <router-link v-if=tx.block v-bind:to='"/block/" + tx.block.height'>{{ tx.block.height }}</router-link>
                    </td>
                </tr>
                <tr>
                    <td>TimeStamp:</td>
                    <td>{{ timeConversion(Date.now() - tx.timestamp) }} ago ({{ new Date(tx.timestamp).toString() }} | {{ tx.timestamp }})</td>
                </tr>
                <tr>
                    <td>From:</td>
                    <td class=monospace>
                        <router-link v-if=tx.from v-bind:to='"/address/" + tx.from.hash'>{{ tx.from.hash }}</router-link>
                    </td>
                </tr>
                <tr>
                    <td>To:</td>
                    <td class=monospace>
                        <router-link v-if=tx.to v-bind:to='"/address/" + tx.to.hash'>{{ tx.to.hash }}</router-link>
                    </td>
                </tr>
                <tr>
                    <td>Value:</td>
                    <td>{{ toWei(tx.value) }}</td>
                </tr>
                <tr>
                    <td>Gas Limit:</td>
                    <td>{{ numberAddComma(tx.gasLimit) }}</td>
                </tr>
                <tr>
                    <td>Gas Used By Txn:</td>
                    <td>{{ tx.gasReward }} Nas</td>
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
                    <td>Transation Type:</td>
                    <td>{{ txType }}</td>
                </tr>
                <tr>
                    <td>Input Data:</td>
                    <td class=code>
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
        utility = require("@/assets/utility");

    require("prismjs/themes/prism.css");

    module.exports = {
        components: {
            "vue-tab-buttons": require("@/components/vue-tab-buttons").default
        },
        computed: {
            formatCode() {
                var o, lang, code;

                if (this.txType == "normal" && this.tx.data) {
                    o = JSON.parse(this.tx.data),
                        lang = o.SourceType,
                        code = o.Source;

                    // if (lang == "js")
                    lang = prism.languages.javascript;

                    code = jsBeautify(code);
                    // return code;
                    return prism.highlight(code, lang);
                } else
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
                api.getTx(this.$route.params.id, o => {
                    this.tx = o;
                }, xhr => {
                    this.$router.replace("/404!" + this.$route.fullPath);
                });
            }
        },
        data() {
            return {
                tab: 0,
                tabButtons: ["Overview"],
                tx: null
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
            }
        }
    };
</script>