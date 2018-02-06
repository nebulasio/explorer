<style>
    .vue-tx .table tbody tr td {
        border: 0;
    }

    .vue-tx textarea {
        width: 100%;
    }

    .vue-tx tr>td:first-child::after {
        content: ":";
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
                    <td>TxHash</td>
                    <td>{{ tx.hash }}</td>
                </tr>
                <tr>
                    <td>TxReceipt Status</td>
                    <td>Success</td>
                </tr>
                <tr>
                    <td>Block Height</td>
                    <td>
                        <router-link v-bind:to="/block/ +  tx.block.height">{{ tx.block.height }}</router-link>
                        (210 block confirmations)
                    </td>
                </tr>
                <tr>
                    <td>TimeStamp</td>
                    <td>{{ timeConversion(tx.timeDiff) }} ago</td>
                </tr>
                <tr>
                    <td>From</td>
                    <td>
                        <router-link v-bind:to="/address/ +  tx.from.hash">{{ tx.from.hash }}</router-link>
                    </td>
                </tr>
                <tr>
                    <td>To</td>
                    <td>
                        <router-link v-bind:to="/address/ +  tx.to.hash">{{ tx.to.hash }}</router-link>
                    </td>
                </tr>
                <tr>
                    <td>Value</td>
                    <td>{{ tx.value }}</td>
                </tr>
                <tr>
                    <td>Gas Limit</td>
                    <td>{{ tx.gasLimit }} NAS</td>
                </tr>
                <tr>
                    <td>Gas Used By Txn</td>
                    <td>{{ tx.gasReward }} NAS</td>
                </tr>
                <tr>
                    <td>Gas Price</td>
                    <td>{{ tx.gasPrice }} NAS</td>
                </tr>
                <tr>
                    <td>Actual Tx Cost/Fee</td>
                    <td>{{ tx.txFee }}</td>
                </tr>
                <tr>
                    <td>Nonce</td>
                    <td>{{ tx.nonce }}</td>
                </tr>
                <tr>
                    <td>Input Data</td>
                    <td>
                        <textarea disabled rows="7">{{ tx.data   }}</textarea>
                    </td>
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
    var api = require("@/assets/api"),
        utility = require("@/assets/utility");

    module.exports = {
        components: {
            "vue-tab-buttons": require("@/components/vue-tab-buttons").default
        },
        methods: {
            timeConversion(ms) {
                return utility.timeConversion(ms / 1000);
            }
        },
        computed: {
            urlChange() {
                api.getTx(this.$route.params.id, o => {
                    this.tx = o;
                }, xhr => {
                    this.$router.replace("/404");
                });
            }
        },
        data() {
            return {
                tab: 0,
                tabButtons: ["Overview"],
                tx: null
            };
        }
    };
</script>