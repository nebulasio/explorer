<template>
    <!-- https://etherscan.io/contractsVerified  -->
    <div class=vue-contracts>
        <vue-bread v-bind:arr=breadcrumb title="All Contracts"></vue-bread>

        <div class="container mt20">
            <div class="align-items-center info-and-pagination row">
                <div class=col>
                    A total of {{ totalContracts }} contracts found
                    <br>
                    <!-- <em>Displaying the last %2 records only</em> -->
                </div>
                <vue-pagination class=col-auto v-bind:current=currentPage v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext v-on:prev=onPrev v-on:to=onTo></vue-pagination>
            </div>

            <table class="mt20 table">
                <tr>
                    <th>Address</th>
                    <th>Programming Language</th>
                    <th class=text-right>Balance</th>
                    <th class=text-right>TxCount</th>
                    <th class=text-right>DateCreated</th>
                </tr>
                <tr v-for="o in arr">
                    <td class=monospace>
                        <router-link v-bind:to='fragApi + "/address/" + o.hash'>{{ o.hash }}</router-link>
                        <span v-show=o.alias> | {{ o.alias }}</span>
                    </td>
                    <td class=monospace>{{ o }}</td>
                    <td class=text-right>{{ toWei(o.balance) }}</td>
                    <td class=text-right>{{ o.percentage }}%</td>
                    <td class=text-right>{{ o.txCnt }}</td>
                </tr>
            </table>
            <vue-pagination v-bind:current=currentPage right=1 v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext v-on:prev=onPrev v-on:to=onTo></vue-pagination>
        </div>
    </div>
</template>
<script>
    var api = require("@/assets/api"),
        utility = require("@/assets/utility");

    module.exports = {
        components: {
            "vue-bread": require("@/components/vue-bread").default,
            "vue-pagination": require("@/components/vue-pagination").default
        },
        data() {
            return {
                arr: [],
                prr: [],
                breadcrumb: [
                    { text: "Home", to: "/" },
                    { text: "Contracts", to: "" }
                ],
                currentPage: 0,
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
                totalContracts: 0,
                totalBalance: 0,
                totalPage: 0,
                tx: null
            };
        },
        methods: {
            nthPage() {
                var p = this.$route.query.p || 1;

                if (p == this.currentPage)
                    console.log("nthPage - 请求的第", p, "页正是当前页, 忽略此次调用");
                else {
                    this.$root.showModalLoading = true;

                    api.getAccount(p, o => {
                        this.$root.showModalLoading = false;
                        this.arr = o.addressList;
                        this.currentPage = o.page;
                        this.totalContracts = o.totalContractsCnt;
                        this.totalBalance = o.totalBalance;
                        this.totalPage = o.totalPage;

                        if (this.arr.length) {
                            this.heightFrom = this.arr[0].height;
                            this.heightTo = this.arr[this.arr.length - 1].height;
                        } else {
                            this.heightFrom = 0;
                            this.heightTo = 0;
                        }
                    }, xhr => {
                        console.log(xhr);
                        this.$root.showModalLoading = false;
                        this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404!" + this.$route.fullPath);
                    });

                    api.getAddress(this.$route.params.id, e => {
                        this.minted = e.mintedBlkList;
                        this.obj = e;
                        this.txs = e.txList;
                    });
                }
            },
            numberAddComma(n) {
                return utility.numberAddComma(n);
            },
            toWei(n) {
                return utility.toWei(n);
            },
            onFirst() {
                this.$router.push({
                    path: this.$route.path,
                    query: { p: 1 }
                });
            },
            onLast() {
                this.$router.push({
                    path: this.$route.path,
                    query: { p: this.totalPage }
                });
            },
            onNext() {
                this.$router.push({
                    path: this.$route.path,
                    query: { p: this.currentPage + 1 }
                });
            },
            onPrev() {
                this.$router.push({
                    path: this.$route.path,
                    query: { p: this.currentPage - 1 }
                });
            },
            onTo(n) {
                this.$router.push({
                    path: this.$route.path,
                    query: { p: n }
                });
            }
        },
        mounted() {
            this.nthPage();
        },
        watch: {
            $route() {
                this.nthPage();
            }
        }
    };
</script>