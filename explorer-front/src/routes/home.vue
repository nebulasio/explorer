<style>
    .vue-home .container {
        background-color: #f7f7f7;
    }

    .vue-home .mt16 {
        margin-top: 16px;
    }

    .vue-home .list {
        height: 725px;
        overflow: auto;
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

    .vue-home .li {
        align-items: center;
        background-color: #fff;
        border-bottom: 4px solid #f7f7f7;
        display: flex;
        height: 120px;
    }

    .vue-home li .monospace {
        display: block;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .vue-home .blocks li .monospace {
        width: 350px;
    }

    .vue-home .txs li .monospace {
        width: 400px;
    }

    .vue-home .blocks li>.img {
        background: url(/static/img/block_bg.jpg) center no-repeat;
        flex-shrink: 0;
        margin-right: 20px;
        height: 90px;
        width: 160px;
    }

    .vue-home .blocks li>.img>* {
        color: white;
        display: block;
        text-align: center;
    }

    .vue-home .tab-right {
        height: 60px;
        border-bottom: 3px solid #f7f7f7;
        background-color: #fff;
        position: relative;
    }

    .vue-home .tab-right .img {
        position: absolute;
        top: 33%;
        left: 2%;
        font-size: 16px;
    }

    .vue-home .tab-right .btn {
        position: absolute;
        right: 2%;
        top: 20%;
    }

    .vue-home .txs li {
        /* 右侧列表 状态不同 左边框有不同的状态颜色, 目前不知道啥状态所以是按顺序给颜色 */
        border-left: 2px solid;
    }

    .vue-home .txs li>img {
        flex-shrink: 0;
        margin: 0 20px;
    }

    .vue-home .txs li tr>td:first-child {
        height: 28px;
        width: 40px;
    }

    .vue-home .txs li:nth-of-type(4n + 1) {
        border-left-color: #3cba54;
    }

    .vue-home .txs li:nth-of-type(4n + 2) {
        border-left-color: #f4c20d;
    }

    .vue-home .txs li:nth-of-type(4n + 3) {
        border-left-color: #db3236;
    }

    .vue-home .txs li:nth-of-type(4n + 4) {
        border-left-color: #4885ed;
    }

    /*
        chart
        */

    .vue-home #chart {
        height: 300px;
        max-width: 800px;
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
        font-size: 16px;
        margin-top: 24px;
    }

    .vue-home .chart_banner .msg div {
        margin-top: 10px;
    }

    .vue-home .chart_banner .msg .msg_change_right {
        float: right;
        margin-right: 10px;
        color: black;
    }

    .vue-home .chart_banner .msg .red {
        color: red;
    }

    .vue-home .chart_banner .msg .green {
        color: green;
    }

    .vue-home .chart_banner .msg .msg_change_left {
        margin-left: 10px;
        color: black;

    }

    .vue-home text.highcharts-credits {
        display: none;
    }

    .vue-home .updataTime {
        float: right;
    }
</style>
<template>
    <div class="container vue-home">
        <div class=top>
            <div class=row>
                <div class=col-md-8>
                    <div id=chart></div>
                </div>
                <div class=col-md-4>
                    <div class=chart_banner v-if=market>
                        <div class=name>NAS</div>
                        <div class=value>$ {{ market.price }}</div>
                        <div class=msg>
                            <div class=msg_change>
                                <span class="msg_change_left">24h Change : </span>
                                <span class="msg_change_right red">{{ market.change24h }}%</span>
                            </div>
                            <div class=msg_volume>
                                <span class="msg_change_left">24h Volume :</span>
                                <span class="msg_change_right">$ {{ numberAddComma(market.volume24h) }}</span>
                            </div>
                            <div class=msg_market>
                                <span class=msg_change_left>Market Cap :</span>
                                <span class=msg_change_right>$ {{ numberAddComma(market.marketCap) }}</span>
                            </div>
                        </div>
                        <div class="mt16 updataTime">updata time : {{ timeConversion(Date.now() - market.createdAt) }} ago</div>
                    </div>
                </div>
            </div>
        </div>
        <div class=mt20>
            <div class=row>
                <div class="banner_left col-md-6">
                    <div class=list_tab>
                        <div class=img>
                            <span class="fa fa-th-large" aria-hidden=true></span>
                            Blocks
                        </div>
                        <router-link class="btn btn-default pull-right" to=/blocks role=button>View All</router-link>
                    </div>
                    <ul class="blocks list">
                        <li class=li v-for="o in blocks">
                            <div class=img>
                                <router-link class=mt20 v-bind:to="/block/ + o.height">block {{ o.height }}</router-link>
                                <div class=mt20>{{ timeConversion(msVmReady - o.timestamp) }} ago</div>
                            </div>
                            <div class=right>
                                Minted By
                                <router-link class=monospace v-bind:to="/address/ + o.miner.hash">{{ o.miner.hash }}</router-link>
                                <div class=mt16>
                                    <router-link v-bind:to='"/txs?block=" + o.height'>
                                        <b>{{ o.txnCnt }}</b> transations</router-link>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class=col-md-6>
                    <div class=tab-right>
                        <div class=img>
                            <span class="fa fa-list" aria-hidden=true></span>
                            Transation
                        </div>
                        <router-link class="btn btn-default pull-right" to=/txs role=button>View All</router-link>
                    </div>
                    <ul class="list txs">
                        <li class=li v-for="o in txs">
                            <img src=/static/img/icon.png height=43 width=43 alt="">
                            <div>
                                <table>
                                    <tr>
                                        <td>TX#</td>
                                        <td>
                                            <router-link class=monospace v-bind:to='"/tx/"+ o.hash'>{{ o.hash }}</router-link>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>From</td>
                                        <td>
                                            <router-link class=monospace v-bind:to='"/address/" + o.from.hash'>{{ o.from.hash }}</router-link>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>To</td>
                                        <td>
                                            <router-link class=monospace v-bind:to='"/address/" + o.to.hash'>{{ o.to.hash }}</router-link>
                                        </td>
                                    </tr>
                                </table>
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
                        align: "right",
                        layout: "vertical",
                        verticalAlign: "middle"
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
                        name: "transation"
                        // , type: "area"
                    }],
                    subtitle: {
                        text: "数据来源：Nebulas"
                    },
                    title: {
                        text: "transations"
                    },
                    xAxis: {
                        labels: {
                            format: "{value:%m-%d}",
                            rotation: -30
                        },
                        type: "datetime"
                    },
                    yAxis: {
                        title: {
                            text: "数量"
                        }
                    }
                },
                market: null,
                msVmReady: Date.now(),
                txs: []
            };
        },
        methods: {
            numberAddComma(n) {
                return utility.numberAddComma(n);
            },
            timeConversion(ms) {
                // if a vue directive uses a vue method, this method will get called on any vue data's any change
                //
                // https://github.com/vuejs/vue/issues/5682
                // "In 2.x a component's entire render function is called when it is updated."
                return utility.timeConversion(ms);
            }
        },
        mounted() {
            api.getBlock({ type: "latest" }, o => this.blocks = o);
            api.getTx({ type: "latest" }, o => this.txs = o);
            api.getMarketCap(o => this.market = o);

            api.getTx("cnt_static", o => {
                var i, arr = [];

                for (i in o) arr.push([Date.parse(i), o[i]]);

                arr.sort(function (a, b) {
                    return a[0] - b[0];
                });

                // series 全部是 0 时没法计算纵坐标, highcharts 会把一条线居中显示. 如果想让线靠下就需要给 max 一个非零的值比如 1
                // this.chartConfig.yAxis.max = 1;

                this.chartConfig.series[0].data = arr;
                require("highcharts").chart("chart", this.chartConfig);
            });
        }
    };
</script>