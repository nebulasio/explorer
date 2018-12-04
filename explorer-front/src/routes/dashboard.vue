<style>
    .vue-dashboard {
        display: block;
        color: black;
        position: relative;
    }

    .vue-dashboard .black-header {
        position: absolute;
        top: -40px;
        width: 100%;
        height: 200px;
        z-index: -1;
    }

    .vue-dashboard .container {
        /* width: 1200px; */
        /* height: 1476px; */
        /* margin: 0 auto; */
        margin-top: 40px;
    }

    .vue-dashboard .flex-column-container {
        display: -webkit-flex; /* Safari */
        display: flex;
        flex-flow: column wrap;
        justify-content: space-between;
        align-content: space-between;
        flex: none;
    }

    .vue-dashboard .flex-row-container {
        display: -webkit-flex; /* Safari */
        display: flex;
        flex-flow: row wrap;
        justify-content: space-between;
        align-content: space-between;
        flex: none;
    }

    .vue-dashboard .text-light-gray {
        color: #A6A6A6;
    }

    .vue-dashboard .text-gray {
        color: #8D8D8D;
    }

    .vue-dashboard .w590 {
        width: 590px;
    }

    .vue-dashboard .w285 {
        width: 285px;
    }

    .vue-dashboard .ml30 {
        margin-left: 30px;
    }

    .vue-dashboard .fs12 {
        font-size: 12px;
    }

    .vue-dashboard .row1 .flex-item {
        height: 320px;
    }

    .vue-dashboard .row2 .flex-item {
        height: 160px;
    }

    .vue-dashboard .row3 .flex-item {
        height: 110px;
    }

    .vue-dashboard .row4 .flex-item {
        height: 330px;
    }

    .vue-dashboard .row5 .flex-item {
        height: 476px;
    }

    .vue-dashboard .flex-item {
        position: relative;
    }

    .vue-dashboard .row>* {
        margin-bottom: 30px;
    }

    .vue-dashboard .item-bg {
        background-color: white;
        width: 100%;
        height: 100%;
    }

    .vue-dashboard .item-shadow {
        box-shadow:-10px 10px 20px 0px rgba(30,30,30,0.05);
    }

    .vue-dashboard .row1 .flex-item {
        box-shadow: none;
        background-color: initial;
    }

    .vue-dashboard .row1 .item-bg {
        position: relative;
        background:linear-gradient(135deg,rgba(48,58,76,1) 0%,rgba(15,19,26,1) 100%);
    }

    .vue-dashboard .item-title {
        position: absolute;
        left: 30px;
        top: 25px;
        /* margin-left: 30px;
        margin-top: 25px; */
        font-size: 20px;
        font-weight: bold;
    }

    .vue-dashboard .row1 {
        color: white;
    }

    .vue-dashboard .row1 .item-title {
        color: white;
    }

    .vue-dashboard .daily-transactions .details {
        position: absolute;
        top: 32px;
        right: 30px;
        display: flex;
        flex-flow: row wrap;
        justify-content: flex-end;
        align-items: baseline;
        width: 150px;
        font-size: 12px;
        color: rgba(255, 255, 255, 0.7);
    }

    .vue-dashboard .daily-transactions .details *:nth-child(3) {
        font-size: 28px;
        color: white;
        font-weight: bolder;
        margin-left: 10px;
        margin-top: 10px;
    }

    .vue-dashboard .daily-chart {
        position: absolute;
        top: 0px;
        height: 320px;
        width: 100%;
    }

    .vue-dashboard .nas-price .update-time {
        position: absolute;
        top: 32px;
        right: 30px;
        font-size: 12px;
        color: rgba(255, 255, 255, 0.7);
    }

    .vue-dashboard .nas-price .detail {
        position: absolute;
        margin-top: 96px;
        margin-left: 30px;
    }

    .vue-dashboard .nas-price .detail *:nth-child(1) {
        font-size: 28px;
    }

    .vue-dashboard .nas-price .detail *:nth-child(2) {
        font-size: 60px;
    }

    .vue-dashboard .nas-price .detail *:nth-child(3) {
        font-size: 20px;
        color: #2CEE8C;
    }

    .vue-dashboard .nas-price .flex-row-container {
        position: absolute;
        margin-top: 234px;
        flex-flow: row nowrap;
        justify-content: flex-start;
        font-size: 12px;
        color: rgba(255, 255, 255, 0.7);
    }

    .vue-dashboard .nas-price .flex-row-container div div {
        font-size: 28px;
        color: white;
    }

    .vue-dashboard .nas-price .flex-row-container>div:first-child {
        margin-left: 30px;
        margin-right: 84px;
    }

    .vue-dashboard .row2 .subtitle {
        position: absolute;
        margin-left: 30px;
        margin-top: 56px;
    }

    .vue-dashboard .realtime-blocks {
        /* background-color: gray; */
        position: absolute;
        top: 30px;
        left: 170px;
        right: 40px;
        /* width: 990px; */
        height: 100px;
        /* overflow: hidden; */
        display: flex;
        flex-flow: row nowrap;
        justify-content: flex-end;
    }

    .vue-dashboard .realtime-block {
        position: relative;
        width: 15px;
        height: 100px;
        margin-left: 10px;
        background-color: rgba(0, 87, 255, 1);
        transition: all 250ms linear;
        display: none;
    }

    .vue-dashboard .realtime-block .blockheight {
        display: block;
        width: 15px;
        background-color: rgba(217, 230, 255, 1);
        transition: all 250ms linear;
    }

    .vue-dashboard .realtime-block .block-popover {
        position: absolute;
        left: calc(-50% + 15px);
        top: -60px;
        padding: 8px;
        font-size: 12px;
        color: white;
        background-color: #0057FF;
        border-radius: 2px;
        transform: translateX(-50%);
        white-space: nowrap;
        z-index: 1;
        display: none;
    }

    .vue-dashboard .realtime-block:hover .block-popover {
        display: block;
    }

    .vue-dashboard .row3 .flex-item {
        position: relative;
    }

    .vue-dashboard .row3 .flex-item .item-bg div:nth-child(1) {
        position: absolute;
        margin-top: 23px;
        margin-left: 30px;
        font-size: 28px;
        font-weight: bold;
    }

    .vue-dashboard .row3 .link {
        position: absolute;
        margin-top: 65px;
        margin-left: 30px;
        color: rgba(0, 0, 0, 0.5);
        font-size: 12px;
    }

    .vue-dashboard .row3 img {
        position: absolute;
        top: 35px;
        right: 33px;
    }

    .vue-dashboard .row3 .flex-item:hover .link {
        color: rgba(0, 87, 255, 1)
    }

    .vue-dashboard .row4 .item-bg {
        position: relative;
    }

    .vue-dashboard .row4 .subtitle {
        position: absolute;
        margin-left: 30px;
        margin-top: 56px;
    }

    .vue-dashboard .user-pie {
        display: block;
        position: absolute;
        margin-left: 30px;
        margin-top: 108px;
        background-color: gray;
        box-shadow:-10px 10px 20px 0px rgba(30,30,30,0.05);
    }

    .vue-dashboard .old-user {
        position: absolute;
        top: 20px;
        left: 20px;
        width: 114px;
        height: 114px;
        background-color: rgba(89, 146, 255, 1);
        border-style: solid;
        border-width: 0px;
        border-radius: 50%;
    }

    .vue-dashboard .new-user-container {
        position: absolute;
        left: 77px;
        width: 77px;
        height: 154px;
        overflow: hidden;
    }

    .vue-dashboard .new-user {
        position: absolute;
        top: 0px;
        left: -77px;
        width: 154px;
        height: 154px;
        background-image: linear-gradient(to left, transparent 50%, #0057FF 0);
        border-style: solid;
        border-width: 0px;
        border-radius: 50%;
        transition-property: transform;
        transition-duration: 2s;
        transition-timing-function: ease;
    }

    .vue-dashboard .row4 .detail {
        display: flex;
        flex-flow: column nowrap;
        justify-content: flex-end;
        align-items: flex-end;
        position: absolute;
        top: 0px;
        right: 30px;
    }

    .vue-dashboard .row4 .user-data .data-source {
        margin-top: 30px;
        margin-bottom: 26px;
    }

    .vue-dashboard .row4 .title {
        font-size: 28px;
        margin-top: 40px;
        font-weight: bold;
    }

    .vue-dashboard .row4 .accounts-data .data-source {
        position: absolute;
        top: 30px;
        right: 30px;
    }

    .vue-dashboard .accounts-chart {
        position: absolute;
        top: 0px;
        width: 100%;
        height: 100%;
    }

    .vue-dashboard .row5 .item-bg {
        position: relative;
        padding: 23px 30px;
    }

    .vue-dashboard .row5 .item-title {
        position: absolute;
        margin: 0;
    }

    .vue-dashboard table {
        margin-top: 47px;
        width: 100%;
        font-size: 16px;
    }

    .vue-dashboard table tr {
        border: solid 1px;
        border-color: #F2F6F7;
    }

    .vue-dashboard tr {
        height: 74px;
    }

    .vue-dashboard td:first-child {
        width: 70px;
    }

    .vue-dashboard td:last-child {
        text-align: right;
    }

    .vue-dashboard td .time::after {
        content:'\A ';
        white-space:pre;
    }

    @media (max-width: 575.98px) {
        .vue-dashboard .realtime-block:nth-last-of-type(-n+13) {
            display: block;
        }

        .vue-dashboard .realtime-blocks {
            overflow: hidden;
        }
    }

     /* Small devices (landscape phones, 576px and up) */
    @media (min-width: 576px) {
        .vue-dashboard .realtime-block:nth-last-of-type(-n+13) {
            display: block;
        }
    }

    /* Medium devices (tablets, 768px and up) */
    @media (min-width: 768px) {
        .vue-dashboard .realtime-block:nth-last-of-type(-n+20) {
            display: block;
        }
    }

    /* Large devices (desktops, 992px and up) */
    @media (min-width: 992px) {
        .vue-dashboard .realtime-block:nth-last-of-type(-n+30) {
            display: block;
        }
    }

    /* Extra large devices (large desktops, 1200px and up) */
    @media (min-width: 1200px) {
        .vue-dashboard .realtime-block:nth-last-of-type(-n+37) {
            display: block;
        }
    }

    @media (max-width: 575.98px) {
        .vue-dashboard .nas-price .flex-row-container>div:first-child {
            margin-right: 50px;
        }
    }

    /* Large devices (desktops, 992px and up) */
    @media (min-width: 992px) and (max-width: 1199.98px) {
        .vue-dashboard .row3 .flex-item .item-bg div:nth-child(1) {
            font-size: 20px;
        }
    }

</style>
<template>
    <div class="vue-dashboard">
        <div class="bg-black black-header"></div>
        <div class="container">
            <!-- ====================1==================== -->
            <div class="row row1">
                <div class="daily-transactions flex-item col-md-12 col-lg-6 row1-item">
                    <div class="item-bg">
                        <div class="item-title">Daily Transactions</div>
                        <div class="details">
                            <div>Data Sources: Nebulas</div>
                            <div>Today</div>
                            <div>6,578</div>
                        </div>
                        <vchart class="daily-chart" :options="dailyTxData" :autoResize='true'></vchart>
                    </div>
                </div>
                <div class="nas-price flex-item col-md-12 col-lg-6 row1-item">
                    <div class="item-bg">
                        <div class="item-title">NAS Price</div>
                        <div class="update-time">Update time : 2 Min ago</div>
                        <div class="detail">
                            <span>$</span>
                            <span>1.87</span>
                            <span>(+13.97%)</span>
                        </div>
                        <div class="flex-row-container">
                            <div>
                                Market Cap
                                <div>$74,008,322</div>
                            </div>
                            <div>
                                24h Volume
                                <div>$4,832,615</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ====================2==================== -->
            <div class="row row2">
                <div class="col">
                    <div class="flex-item item-bg item-shadow">
                        <div class="item-title">Blocks</div>
                        <div class="subtitle fs12 text-gray">Block status</div>
                        <div class="realtime-blocks">
                            <div class="realtime-block" v-for="(block, i) in realtimeBlocks" :key="i">
                                <div class="blockheight" :style="calBlockHeight(block)"></div>
                                <div class="block-popover">
                                    <div>Block Height: 168276</div>
                                    <div>Transac tions: 33</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ===================3===================== -->
            <div class="row row3">
                <div class="col-lg-3 col-md-6 col-sm-12 col-12 flex-item w285">
                    <div class="item-bg item-shadow">
                        <div>1,189,786</div>
                        <router-link class="link" :to='fragApi + "/blocks/"'>Block Heigth ></router-link>
                        <img src=/static/img/dashboard-1.png width=44 alt="">
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 col-12 flex-item w285">
                    <div class="item-bg item-shadow">
                        <div>1,189,786</div>
                        <router-link class="link" :to='fragApi + "/txs/"'>Total Transations ></router-link>
                        <img src=/static/img/dashboard-2.png width=44 alt="">
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 col-12 flex-item w285">
                    <div class="item-bg item-shadow">
                        <div>1,189,786</div>
                        <div class="link">Total Smart Contract</div>
                        <img src=/static/img/dashboard-3.png width=44 alt="">
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 col-12 flex-item w285">
                    <div class="item-bg item-shadow">
                        <div>1,189,786</div>
                        <router-link class="link" :to='fragApi + "/accounts/"'>Total Address ></router-link>
                        <img src=/static/img/dashboard-4.png width=44 alt="">
                    </div>
                </div>
            </div>
            <!-- ===================4===================== -->
            <div class="row row4 flex-row-container">
                <div class="flex-item col-md-12 col-lg-6 row4-item user-data">
                    <div class="item-bg item-shadow">
                        <div class="item-title">新用户占比</div>
                        <div class="subtitle text-light-gray ml30 fs12">新用户为90日内创建星云账户者</div>
                        <div class="user-pie">
                            <div class="old-user"></div>
                            <div class="new-user-container">
                                <div class="new-user"></div>
                            </div>
                        </div>
                        <div class="detail">
                            <div class="fs12 text-light-gray data-source">Data Sources: Nebulas</div>
                            <div class="title">42462</div>
                            <div class="fs12 text-gray">New Users</div>
                            <div class="title">132,696</div>
                            <div class="fs12 text-gray">Old Users</div>
                        </div>
                    </div>
                </div>
                <div class="flex-item col-md-12 col-lg-6 row4-item accounts-data">
                    <div class="item-bg item-shadow">
                        <div class="item-title">主网账户数量变化</div>
                        <div class="fs12 text-light-gray data-source">Data Sources: Nebulas</div>
                        <vchart class="accounts-chart" :options="accountsData" :autoResize='true'></vchart>
                    </div>
                </div>
            </div>
            <!-- ===================5===================== -->
            <div class="row row5 flex-row-container">
                <div class="flex-item col-12 col-sm-12 col-lg-6 row5-item">
                    <div class="item-bg item-shadow">
                        <div class="item-title">Blocks</div>
                        <table frame=hsides rules=rows>
                            <tr v-for="(block, i) in blocks" :key="i">
                                <td>
                                    <img src="/static/img/icon-block.png" width="50">
                                </td>
                                <td>
                                    Block#
                                    <router-link to="asdf">272990</router-link>
                                    <br>
                                    <router-link to="asdf">20 transactions</router-link>
                                </td>
                                <td>
                                    <div class="time">5 Sec ago</div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="flex-item col-12 col-sm-12 col-lg-6 row5-item">
                    <div class="item-bg item-shadow">
                        <div class="item-title">Transactions</div>
                        <table frame=hsides rules=rows>
                            <tr v-for="(block, i) in blocks" :key="i">
                                <td>
                                    <img src="/static/img/icon-tx.png" width="50">
                                </td>
                                <td>
                                    Tx#
                                    <router-link to="asdf">0aed…03f4</router-link>
                                    <br>
                                    From <router-link to="asdf">0aed…03f4</router-link> To <router-link to="asdf">0aed…03f4</router-link>
                                </td>
                                <td>
                                    <div class="time">5 Sec ago</div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    var api = require("@/assets/api"),
        utility = require("@/assets/utility"),
        BigNumber = require("bignumber.js"),
        ECharts = require('vue-echarts/components/ECharts').default,
        jQuery = require('jquery');

    require('echarts/lib/chart/line'),
    require('echarts/lib/chart/pie'),
    require('echarts/lib/component/tooltip');

    module.exports = {
        components: {
            'vchart': ECharts
        },
        data() {
            return {
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
                dailyTxData: {
                    grid: { x: '10', y: '100', width: '98%', height: '210', containLabel: true },
                    xAxis: {
                        data: ["08-26", "08-27", "08-28", "08-29", "08-30", "08-31", "09-01", "09-02", "09-03", "09-04"],
                        axisLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                        axisLabel: {
                            textStyle: {
                                color: '#B2B2B2'
                            },
                            margin: 18
                        }
                    },
                    yAxis: {
                        max: 7000,
                        axisLine: {
                            show: false
                        },
                        axisLabel: {
                            textStyle: {
                                color: '#B2B2B2'
                            },
                            margin: -10,
                            formatter: function (value) {
                                return (value+'').replace('000', 'k');
                            }
                        },
                        axisTick: {
                            show: false
                        },
                        splitLine: {
                            show: false
                        }
                    },
                    series: {
                        type: 'line',
                        data: [2947, 3898, 3239, 3838, 3767, 3098, 3267, 3893, 3100, 3987],
                        symbol: 'circle',
                        symbolSize: 5,
                        lineStyle: {
                            color: '#FFDD0D00',
                            width: 3
                        },
                        itemStyle: {
                            color: '#FFFFFF',
                            borderWidth: 3
                        },
                        areaStyle: {
                            color: '#595C63'
                        }
                    },
                    tooltip: {
                        
                    }
                },
                accountsData: {
                    grid: { x: '10', y: '100', width: '98%', height: '210', containLabel: true },
                    xAxis: {
                        data: ["08-26", "08-27", "08-28", "08-29", "08-30", "08-31", "09-01", "09-02", "09-03", "09-04"],
                        axisLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                        axisLabel: {
                            textStyle: {
                                color: '#B2B2B2'
                            },
                            margin: 18
                        }
                    },
                    yAxis: {
                        max: 500000,
                        axisLine: {
                            show: false
                        },
                        axisLabel: {
                            textStyle: {
                                color: '#B2B2B2'
                            },
                            margin: -10,
                            formatter: function (value) {
                                return value / 1000 + 'k';
                            }
                        },
                        axisTick: {
                            show: false
                        },
                        splitLine: {
                            show: false
                        }
                    },
                    series: {
                        type: 'line',
                        data: [120000, 130000, 141000, 152000, 170000, 190000, 215000, 241000, 268000, 300000],
                        symbol: 'emptyCircle',
                        symbolSize: 7,
                        lineStyle: {
                            color: '#FFDD0D00',
                            width: 10
                        },
                        itemStyle: {
                            normal: {
                                color: '#FFFFFF',
                                borderWidth: 3,
                                borderColor: '#0057FF'
                            },
                            emphasis: {
                                color: '#FFFFFF',
                                borderWidth: 3,
                                borderColor: '#0057FF'
                            }
                        },
                        areaStyle: {
                            color: '#0057FF'
                        }
                    },
                    tooltip: {
                        
                    }
                },
                realtimeBlocks: [],
                blocks: [1, 1, 1, 1, 1]
            }
        },
        mounted() {
            for (var i=0; i<40; i++) {
                var block = {hash: '', height: Math.floor(Math.random() * 10)};
                this.realtimeBlocks.push(block);
            }
            setInterval(() => {
                var block = {hash: '', height: Math.floor(Math.random() * 10)};
                this.realtimeBlocks.push(block);
            }, 15000);

            $('.new-user').css({
                "transform": "rotate(-120deg)"
            });
        },
        methods: {
            calBlockHeight(b) {
                return 'height: ' + Math.min(10, Math.max(0, b.height)) / 10.0 * 100 + 'px;';
            }
        }
    }
</script>

