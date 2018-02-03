<template>
    <!-- https://etherscan.io/accounts  -->
    <div class=vue-accounts>
        <vue-bread v-bind:arr=breadcrumb title="All Accounts"></vue-bread>

        <div class="container mt20">
            <div class="align-items-center info-and-pagination row">
                <div class=col>
                    A total of {{ totalAccounts }} accounts found ( {{ totalBalance }} )
                    <br>
                    <!-- <em>Displaying the last %2 records only</em> -->
                </div>
                <vue-pagination class=col-auto v-bind:current=currentPage v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext v-on:prev=onPrev></vue-pagination>
            </div>

            <table class="mt20 table">
                <tr>
                    <th>Rank</th>
                    <th>Address</th>
                    <th>Balance</th>
                    <th>Percentage</th>
                    <th>TxCount</th>
                </tr>
                <tr v-for="o in arr">
                    <td>{{ o.rank }}</td>
                    <td>
                        <router-link v-bind:to='"/address/" + o.hash'>{{ o.hash }}</router-link>
                        <span v-show=o.alias> | {{ o.alias }}</span>
                    </td>
                    <td>{{ o.balance }}</td>
                    <td>{{ o.percentage }}</td>
                    <td>{{ o.txCnt }}</td>
                </tr>
            </table>
            <vue-pagination v-bind:current=currentPage right=1 v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext v-on:prev=onPrev></vue-pagination>
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
        data() {
            return {
                ajaxing: false,
                arr: [],
                breadcrumb: [
                    { text: "Home", to: "/" },
                    { text: "Accounts", to: "" }
                ],
                currentPage: 0,
                totalAccounts: 0,
                totalBalance: 0,
                totalPage: 1 // 为了允许 mounted 调用 nthPage
            };
        },
        methods: {
            nthPage(p) {
                if (p)
                    if (0 < p && p < this.totalPage + 1)
                        if (p == this.currentPage)
                            console.log("nthPage - 请求的第", p, "页正是当前页, 忽略此次调用");
                        else if (this.ajaxing)
                            console.log("nthPage - 上一个 ajax 还未返回, 忽略此次调用");
                        else {
                            this.ajaxing = true;

                            api.getAccount(p, o => {
                                console.log(o);
                                this.ajaxing = false;
                                this.arr = o.addressList;
                                this.currentPage = o.page;
                                this.totalAccounts = o.totalAccountsCnt;
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
                                // this.ajaxing = false; // 由于跳转了所以不需要修改 ajaxing
                                this.$router.replace("/404");
                            });
                        }
                    else
                        console.log("nthPage - 请求的第", p, "页不在 [ 1,", this.totalPage, "] 内, 忽略此次调用");
                else
                    console.log("nthPage - 无效的 p", p, ", 忽略此次调用");
            },
            onFirst() {
                this.nthPage(1);
            },
            onLast() {
                this.nthPage(this.totalPage);
            },
            onNext() {
                this.nthPage(this.currentPage + 1);
            },
            onPrev() {
                this.nthPage(this.currentPage - 1);
            }
        },
        mounted() {
            this.nthPage(1);
            this.totalPage = 0;
        }
    };
</script>