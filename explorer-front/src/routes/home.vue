<style>
    .vue-home .container {
        background-color: #f7f7f7;
    }

    .vue-home .mt16 {
        margin-top: 16px;
    }

    .vue-home .banner {
        margin-top: 20px;
    }

    .vue-home .banner_left {
        height: 800px;
        /* background-color: gray; */
    }

    .vue-home .banner_left .list_left {
        width: 100%;
        height: 725px;
        overflow: auto;
        /* border:2px solid black; */
    }

    .vue-home .banner_left .list_tab {
        width: 100%;
        height: 60px;
        border-bottom: 3px solid #f7f7f7;
        background-color: #fff;
        position: relative;
    }

    .vue-home .banner_left .list_tab .img {
        position: absolute;
        top: 33%;
        left: 2%;
        font-size: 16px;
    }

    .vue-home .banner_left .list_tab .btn {
        position: absolute;
        right: 2%;
        top: 20%;
    }

    .vue-home .list_left li {
        width: 100%;
        height: 120px;
        border-bottom: 4px solid #f7f7f7;
        background-color: #fff;
        position: relative;
    }

    .vue-home .banner_right {
        height: 800px;
        /* background-color: blue; */
    }

    .vue-home .banner_right .list_right {
        width: 100%;
        height: 725px;
        overflow: auto;
        /* border:2px solid black; */
    }

    .vue-home .banner_right .list_tab {
        width: 100%;
        height: 60px;
        border-bottom: 3px solid #f7f7f7;
        background-color: #fff;
        position: relative;
    }

    .vue-home .banner_right .list_tab .img {
        position: absolute;
        top: 33%;
        left: 2%;
        font-size: 16px;
    }

    .vue-home .banner_right .list_tab .btn {
        position: absolute;
        right: 2%;
        top: 20%;
    }

    .vue-home .list_right li {
        width: 100%;
        height: 120px;
        border-bottom: 4px solid #f7f7f7;
        background-color: #fff;
        border-left: 2px solid;
        position: relative;
    }

    /* 右侧列表 状态不同 左边框有不同的状态颜色, 目前不知道啥状态所以是按顺序给颜色 */

    .vue-home .list_right li:nth-of-type(4n + 1) {
        border-left-color: #3cba54;
    }

    .vue-home .list_right li:nth-of-type(4n + 2) {
        border-left-color: #f4c20d;
    }

    .vue-home .list_right li:nth-of-type(4n + 3) {
        border-left-color: #db3236;
    }

    .vue-home .list_right li:nth-of-type(4n + 4) {
        border-left-color: #4885ed;
    }

    .vue-home .list_right .list_2_2 {
        width: 100%;
        height: 120px;
        border-bottom: 4px solid #f7f7f7;
        background-color: #fff;
        border-left: 2px solid gray;
        position: relative;
    }

    .vue-home .list_right .list_2_3 {
        width: 100%;
        height: 120px;
        border-bottom: 4px solid #f7f7f7;
        background-color: #fff;
        border-left: 2px solid red;
        position: relative;
    }

    .vue-home .list_banner_left {
        background: url(/static/img/block_bg.jpg) center no-repeat;
        width: 30%;
        height: 90px;
        position: absolute;
        left: 2%;
        top: 12%;
        color: #fff;
    }

    .vue-home .list_banner_left p {
        margin-left: 38px;
        margin-top: 18px;
    }

    .vue-home .list_banner_left_p1 {
        margin-left: 40px;
    }

    .vue-home .list_banner_left a {
        color: #fff;
    }

    .vue-home .list_banner_right {
        width: 60%;
        height: 90px;
        position: absolute;
        left: 34%;
        top: 12%;
        padding-top: 3%;
        text-overflow: ellipsis;
        overflow: hidden;

    }

    .vue-home .list_right_banner_left {
        width: 10%;
        height: 86px;
        position: absolute;
        top: 28%;
        left: 2%;
    }

    .vue-home .list_right_banner_right {
        width: 80%;
        height: 90px;
        position: absolute;
        left: 14%;
        top: 12%;
    }

    .vue-home .list_right_banner_right p {
        width: 89%;
        height: 28px;
        text-overflow: ellipsis;
        overflow: hidden;
        margin: 0;
    }

    .vue-home .list_right_banner_right p a {
        width: 366px;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .vue-home #chart {
        max-width: 800px;
        height: 278px;
    }

    .vue-home .chart_banner .name,
    .value {
        color: black;
        border-bottom: 1px inset;
        margin-left: 12px;
        margin-right: 12px;
    }

    .vue-home .chart_banner .name {
        font-size: 30px;
    }

    .vue-home .chart_banner .value {
        font-size: 60px;
    }

    .vue-home .chart_banner .msg {
        color: black;
        font-size: 16px;
        margin-top: 24px;
    }

    .vue-home .chart_banner .msg div {
        margin-top: 10px;
    }

    .vue-home .chart_banner .msg .msg_change_right {
        float: right;
        margin-right: 10px;
    }

    .vue-home .chart_banner .msg .msg_change_left {
        margin-left: 10px;
    }

    .vue-home text.highcharts-credits {
        display: none;
    }
</style>
<template>
    <div class="container vue-home boxShadow">
        <div class=top>
            <div class=row>
                <div class=col-md-8>
                    <div id=chart style="max-width:800px;height:300px;"></div>
                </div>
                <div class=col-md-4>
                    <div class=chart_banner>
                        <div class=name>NAS</div>
                        <div class=value>$ {{ market.price }}</div>
                        <div class=msg>
                            <div class=msg_change>
                                <span class="msg_change_left">24h Change : </span>
                                <span class="msg_change_right red">{{ market.change24h }}%</span>
                            </div>
                            <div class=msg_volume>
                                <span class="msg_change_left">24h Volume :</span>
                                <span class="msg_change_right">$ {{ market.volume24h }}</span>
                            </div>
                            <div class=msg_market>
                                <span class=msg_change_left>Market Cap :</span>
                                <span class=msg_change_right>$ {{ market.marketCap }}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class=banner>
            <div class=row>
                <div class="banner_left col-md-6">
                    <div class=list_tab>
                        <div class=img>
                            <span class="fa fa-th-large" aria-hidden=true></span>
                            Blocks
                        </div>
                        <router-link class="btn btn-default pull-right" to=/blocks role=button>View All</router-link>
                    </div>
                    <ul class=list_left>
                        <li v-for="o in blocks">
                            <div class=list_banner_left>
                                <p>
                                    <router-link v-bind:to="/block/ + o.height">block {{ o.height }}</router-link>
                                </p>
                                <p class=list_banner_left_p1>{{ timeConversion(o.timeDiff) }} ago</p>
                            </div>
                            <div class=list_banner_right>
                                Minted By
                                <router-link v-bind:to="/address/ + o.miner.hash">{{ o.miner.hash }}</router-link>
                                <div class=mt16>
                                    <router-link v-bind:to='"/txs?block=" + o.height'>
                                        <b>{{ o.txnCnt }}</b> transations</router-link>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="col-md-6 banner_right">
                    <div class=list_tab>
                        <div class=img>
                            <span class="fa fa-list" aria-hidden=true></span>
                            Transation
                        </div>
                        <router-link class="btn btn-default pull-right" to=/txs role=button>View All</router-link>
                    </div>
                    <ul class=list_right>
                        <li v-for="o in txs">
                            <div class=list_right_banner_left>
                                <img src=/static/img/icon.png height=43 width=43 alt=" ">
                            </div>
                            <div class=list_right_banner_right>
                                <p>
                                    TX#&nbsp;&nbsp;&nbsp;<router-link v-bind:to="/tx/ + o.hash">{{ o.hash }}</router-link>
                                </p>
                                <p>
                                    From<router-link v-bind:to="/address/ + o.from.hash">{{ o.from.hash }}</router-link>
                                </p>
                                <p>
                                    To&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<router-link v-bind:to="/address/ + o.to.hash">{{ o.to.hash }} </router-link>
                                </p>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    var api = require("@/assets/api"),
        utility = require("@/assets/utility");

    module.exports = {
        data() {
            return {
                blocks: [],
                chartConfig: {
                    legend: {
                        align: 'right',
                        layout: 'vertical',
                        verticalAlign: 'middle'
                    },
                    // plotOptions: {
                    //     area: {
                    //         fillColor: {
                    //             linearGradient: {
                    //                 x1: 0,
                    //                 y1: 0,
                    //                 x2: 0,
                    //                 y2: 1
                    //             },
                    //             stops: [
                    //                 [0, "#2233ee"],
                    //                 [1, "#2266ee00"]
                    //             ]
                    //         },
                    //         marker: {
                    //             radius: 2
                    //         },
                    //         lineWidth: 1,
                    //         states: {
                    //             hover: {
                    //                 lineWidth: 1
                    //             }
                    //         },
                    //         threshold: null
                    //     }
                    // },
                    series: [{
                        data: null,
                        name: 'transation'
                        // , type: "area"
                    }],
                    subtitle: {
                        text: '数据来源：Nebulas'
                    },
                    title: {
                        text: 'transations'
                    },
                    xAxis: {
                        labels: {
                            format: '{value:%m-%d}',
                            rotation: -30
                        },
                        type: 'datetime'
                    },
                    yAxis: {
                        title: {
                            text: '数量'
                        }
                    }
                },
                market: [],
                txs: []
            };
        },
        methods: {
            timeConversion(ms) {
                return utility.timeConversion(ms / 1000);
            }
        },
        mounted() {
            var vm = this;

            api.getBlock({ type: "latest" }, o => {
                this.blocks = o;
            });

            api.getTx({ type: "latest" }, o => {
                this.txs = o;
            });

            api.getMarketCap(o => {
                this.market = o;
            });

            api.getTx("cnt_static", function (o) {
                var i, arr = [];

                for (i in o) arr.push([Date.parse(i), o[i]]);

                arr.sort(function (a, b) {
                    return a[0] - b[0];
                });

                vm.chartConfig.series[0].data = arr;
                require("highcharts").chart("chart", vm.chartConfig);
            });
        }

    };
</script>