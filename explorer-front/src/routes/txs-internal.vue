<style>
    .vue-txs-internal .info-and-pagination .info a {
        color: inherit;
    }

    .vue-txs-internal tr>.ne:nth-child(1),
    .vue-txs-internal tr>.ne:nth-child(2) {
        background-color: gainsboro;
    }

    .vue-txs-internal tr>td:nth-child(3) {
        background-color: #eaeaea;
    }

    .vue-txs-internal td,
    .vue-txs-internal th {
        border-top-color: #ddd;
    }
</style>
<template>
    <!-- https://etherscan.io/txsInternal -->
    <div class=vue-txs-internal v-bind:triggerComputed=urlChange>
        <vue-bread v-bind:arr=breadcrumb title="Contract Internal Transactions"></vue-bread>

        <div class="container mt20">
            <div class="align-items-center info-and-pagination row">
                <div class="col info">
                    <div>A Total Of %1 Internal Transactions found</div>
                    <div>
                        (showing the last %2 records only)
                        <label>
                            <a href="?type=all">
                                <input type=radio name=type value=all>
                                <b>All Types</b>
                            </a>
                        </label>
                        <label>
                            <a href="?type=noerror">
                                <input type=radio name=type value=noerror>
                                <b>No Errors</b>
                            </a>
                        </label>
                        <label>
                            <a href="?type=error">
                                <input type=radio name=type value=error>
                                <b>With Errors</b>
                            </a>
                        </label>
                    </div>
                </div>
                <vue-pagination class=col-auto></vue-pagination>
            </div>

            <!--    the table
                ============================================================ -->
            <table class="mt20 table">
                <tr>
                    <th>Block</th>
                    <th>Age</th>
                    <th>ParentTxHash</th>
                    <th>Type</th>
                    <th>From</th>
                    <th></th>
                    <th>To</th>
                    <th>Value</th>
                </tr>

                <tr>
                    <!-- ne = none empty, this class is required to set background-color -->
                    <td class=ne>
                        <a href="block.html?id=%123456">%Block</a>
                    </td>
                    <td class=ne>%Age</td>
                    <td>
                        <span class="fa fa-check" aria-hidden="true"></span>
                        <a href="tx.html?id=%ParentTxHash">%ParentTxHash</a>
                    </td>
                    <td>%Type</td>
                    <td>
                        <a href="address.html?id=%From">%From</a>
                    </td>
                    <td>
                        <span class="fa fa-arrow-right" aria-hidden="true"></span>
                    </td>
                    <td>
                        <a href="address.html?id=%To">%To</a>
                    </td>
                    <td>%Value</td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>
                        <span class="fa fa-btc" aria-hidden="true"></span>
                        <a href="tx.html?id=%ParentTxHash">%ParentTxHash</a>
                    </td>
                    <td>%Type</td>
                    <td>
                        <a href="address.html?id=%From">%From</a>
                    </td>
                    <td>
                        <span class="fa fa-arrow-right" aria-hidden="true"></span>
                    </td>
                    <td>
                        <a href="address.html?id=%To">%To</a>
                    </td>
                    <td>%Value</td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>
                        <span class="fa fa-check" aria-hidden="true"></span>
                        <a href="tx.html?id=%ParentTxHash">%ParentTxHash</a>
                    </td>
                    <td>%Type</td>
                    <td>
                        <a href="address.html?id=%From">%From</a>
                    </td>
                    <td>
                        <span class="fa fa-arrow-right" aria-hidden="true"></span>
                    </td>
                    <td>
                        <a href="address.html?id=%To">%To</a>
                    </td>
                    <td>%Value</td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>
                        <span class="fa fa-check" aria-hidden="true"></span>
                        <a href="tx.html?id=%ParentTxHash">%ParentTxHash</a>
                    </td>
                    <td>%Type</td>
                    <td>
                        <a href="address.html?id=%From">%From</a>
                    </td>
                    <td>
                        <span class="fa fa-arrow-right" aria-hidden="true"></span>
                    </td>
                    <td>
                        <a href="address.html?id=%To">%To</a>
                    </td>
                    <td>%Value</td>
                </tr>

                <tr>
                    <td class=ne>
                        <a href="block.html?b=123456">%Block</a>
                    </td>
                    <td class=ne>%Age</td>
                    <td>
                        <span class="fa fa-remove" aria-hidden="true"></span>
                        <a href="tx.html?id=%ParentTxHash">%ParentTxHash</a>
                    </td>
                    <td>%Type</td>
                    <td>
                        <a href="address.html?id=%From">%From</a>
                    </td>
                    <td>
                        <span class="fa fa-arrow-right" aria-hidden="true"></span>
                    </td>
                    <td>
                        <a href="address.html?id=%To">%To</a>
                    </td>
                    <td>%Value</td>
                </tr>

                <tr>
                    <td class=ne>
                        <a href="block.html?b=123456">%Block</a>
                    </td>
                    <td class=ne>%Age</td>
                    <td>
                        <span class="fa fa-check" aria-hidden="true"></span>
                        <a href="tx.html?id=%ParentTxHash">%ParentTxHash</a>
                    </td>
                    <td>%Type</td>
                    <td>
                        <a href="address.html?id=%From">%From</a>
                    </td>
                    <td>
                        <span class="fa fa-arrow-right" aria-hidden="true"></span>
                    </td>
                    <td>
                        <a href="address.html?id=%To">%To</a>
                    </td>
                    <td>%Value</td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>
                        <span class="fa fa-check" aria-hidden="true"></span>
                        <a href="tx.html?id=%ParentTxHash">%ParentTxHash</a>
                    </td>
                    <td>%Type</td>
                    <td>
                        <a href="address.html?id=%From">%From</a>
                    </td>
                    <td>
                        <span class="fa fa-arrow-right" aria-hidden="true"></span>
                    </td>
                    <td>
                        <a href="address.html?id=%To">%To</a>
                    </td>
                    <td>%Value</td>
                </tr>

            </table>

            <vue-pagination right=1></vue-pagination>
        </div>
    </div>
</template>
<script>
    var api = require("@/assets/api");

    module.exports = {
        components: {
            "vue-bread": require("@/components/vue-bread").default,
            "vue-pagination": require("@/components/vue-pagination").default
        },
        computed: {
            urlChange() {
                console.log("在这里下载 txs-internal 信息, 目前的 txs-internal id 是", this.$route.params.id);

                // api.getShowcases(this.$root.apiLang, t => {
                //     this.arr = t;
                // });
            }
        },
        data() {
            return {
                breadcrumb: [
                    { text: "Home", to: "/" },
                    { text: "Internal Transactions", to: "" }
                ],
                type: "all" // all, ok, error
            };
        }
    };
</script>