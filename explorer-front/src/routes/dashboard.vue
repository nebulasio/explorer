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

    .vue-dashboard>.container {
        margin-top: 40px;
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
        vertical-align: bottom;
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

    .vue-dashboard .nas-price .detail .text-red {
        color: red;
    }

    .vue-dashboard .nas-price .market {
        position: absolute;
        margin-top: 234px;
        flex-flow: row nowrap;
        justify-content: flex-start;
        font-size: 12px;
        color: rgba(255, 255, 255, 0.7);
    }

    .vue-dashboard .nas-price .market .row div div {
        font-size: 28px;
        color: white;
    }

    .vue-dashboard .nas-price .container {
        padding: 0 30px;
    }

    .vue-dashboard .row2 .subtitle {
        position: absolute;
        margin-left: 30px;
        margin-top: 56px;
    }

    .vue-dashboard .realtime-blocks {
        position: absolute;
        top: 30px;
        left: 170px;
        right: 40px;
        height: 100px;
        /* overflow: hidden; */
        display: flex;
        flex-flow: row-reverse nowrap;
        justify-content: flex-start;
    }

    .vue-dashboard .realtime-block {
        position: relative;
        width: 15px;
        height: 100px;
        margin-left: 10px;
        background-color: #6C9EFF;
        transition: all 1000ms;
        display: none;
    }

    .vue-dashboard .realtime-block .blockheight {
        transition: all 1s;
    }

    .vue-dashboard .realtime-block:first-child {
        background-color: #0057FF;
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
        box-shadow: 0 10px 30px 0 rgba(207,214,230,0.5);
        transform: translateX(-50%);
        white-space: nowrap;
        z-index: 1;
        display: none;
    }

    .vue-dashboard .realtime-block .block-popover::before {
        content: "";
        width: 0;
        height: 0;
        border-top: 8px solid #0057FF;
        border-right: 8px solid transparent;
        border-left: 8px solid transparent;
        position: absolute;
        bottom: -4px;
        left: calc(50% - 8px);
        z-index: 2;
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

    .vue-dashboard .row5 table {
        margin-top: 47px;
        width: 100%;
        font-size: 16px;
    }

    .vue-dashboard .row5 table tr {
        border: solid 1px;
        border-color: #F2F6F7;
    }

    .vue-dashboard .row5 tr {
        height: 74px;
    }

    .vue-dashboard .row5 td:first-child {
        width: 70px;
    }

    .vue-dashboard .row5 td:last-child {
        text-align: right;
    }

    .vue-dashboard .row5 td .time::after {
        content:'\A ';
        white-space:pre;
    }

    .vue-dashboard .row5 .flex-item .item-bg {
        overflow: scroll;
    }

    .vue-dashboard .row5 tr {
        transition: all 1000ms;
    }

    .vue-dashboard .row5 tr:nth-of-type(n+6) {
        opacity: 0;
    }

    @media (max-width: 575.98px) {
        .vue-dashboard .realtime-block:nth-of-type(-n+13) {
            display: block;
        }

        .vue-dashboard .realtime-blocks {
            overflow: hidden;
        }
    }

     /* Small devices (landscape phones, 576px and up) */
    @media (min-width: 576px) {
        .vue-dashboard .realtime-block:nth-of-type(-n+13) {
            display: block;
        }
    }

    /* Medium devices (tablets, 768px and up) */
    @media (min-width: 768px) {
        .vue-dashboard .realtime-block:nth-of-type(-n+20) {
            display: block;
        }
    }

    /* Large devices (desktops, 992px and up) */
    @media (min-width: 992px) {
        .vue-dashboard .realtime-block:nth-of-type(-n+30) {
            display: block;
        }
    }

    /* Extra large devices (large desktops, 1200px and up) */
    @media (min-width: 1200px) {
        .vue-dashboard .realtime-block:nth-of-type(-n+37) {
            display: block;
        }
    }

    /* Large devices (desktops, 992px and up) */
    @media (min-width: 992px) and (max-width: 1199.98px) {
        .vue-dashboard .row3 .flex-item .item-bg div:nth-child(1) {
            font-size: 20px;
        }
    }

    .row2-list-enter {
        opacity: 0;
        transform: translateX(25px);
    }

    .list-enter-active {
        transition: all 1s;
    }

    .list-enter {
        opacity: 0;
        transform: translateX(100px);
    }

</style>
<template>
    <div class="vue-dashboard">
        <div class="bg-black black-header"></div>
        <div class="container">
            <!-- ====================1==================== -->
            <div class="row row1">
                <div class="daily-transactions flex-item col-12 col-lg-6 row1-item">
                    <div class="item-bg">
                        <div class="item-title">Daily Transactions</div>
                        <div class="details">
                            <div>Data Sources: Nebulas</div>
                            <div>Today</div>
                            <div>{{ todayTxCnt }}</div>
                        </div>
                        <vchart class="daily-chart" v-if="dailyTxChartOptions" :options="dailyTxChartOptions" :autoResize='true'></vchart>
                    </div>
                </div>
                <div class="nas-price flex-item col-12 col-lg-6 row1-item">
                    <div class="item-bg">
                        <div class="item-title">NAS Price</div>
                        <div class="update-time">Update time : {{ timeConversion(Date.now() - market.createdAt) }} ago</div>
                        <div class="detail">
                            <span>$</span>
                            <span>{{ market.price }}</span>
                            <span :class="{'text-red': market.trends > 0}">({{ market.trends > 0 ? '+' : '-' }}{{ market.change24h }}%)</span>
                        </div>
                        <div class="market container">
                            <div class="row">
                                <div class="col-6">
                                    Market Cap
                                    <div>${{ numberAddComma(market.marketCap) }}</div>
                                </div>
                                <div class="col-6">
                                    24h Volume
                                    <div>${{ numberAddComma(market.volume24h) }}</div>
                                </div>
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
                        <transition-group v-on:after-enter="afterEnter" name="row2-list" class="realtime-blocks">
                            <div class="realtime-block" v-for="block in blocks" :key="block.height">
                                <div class="blockheight" style="height: 100%" :data-txnCnt="block.txnCnt"></div>
                                <div class="block-popover">
                                    <div>Block Height: {{ block.height }}</div>
                                    <div>Transactions: {{ block.txnCnt }}</div>
                                </div>
                            </div>
                        </transition-group>
                    </div>
                </div>
            </div>
            <!-- ===================3===================== -->
            <div class="row row3">
                <div class="col-lg-3 col-md-6 col-12 flex-item w285">
                    <div class="item-bg item-shadow">
                        <div>{{ blockheight }}</div>
                        <router-link class="link" :to='fragApi + "/blocks/"'>Block Heigth ></router-link>
                        <img src=/static/img/dashboard-1.png width=44 alt="">
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-12 flex-item w285">
                    <div class="item-bg item-shadow">
                        <div>{{ staticInfo.txnCnt }}</div>
                        <router-link class="link" :to='fragApi + "/txs/"'>Total Transations ></router-link>
                        <img src=/static/img/dashboard-2.png width=44 alt="">
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-12 flex-item w285">
                    <div class="item-bg item-shadow">
                        <div>{{ staticInfo.totalContractCount }}</div>
                        <div class="link">Total Smart Contract</div>
                        <img src=/static/img/dashboard-3.png width=44 alt="">
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-12 flex-item w285">
                    <div class="item-bg item-shadow">
                        <div>{{ staticInfo.totalAddressCount }}</div>
                        <router-link class="link" :to='fragApi + "/accounts/"'>Total Address ></router-link>
                        <img src=/static/img/dashboard-4.png width=44 alt="">
                    </div>
                </div>
            </div>
            <!-- ===================4===================== -->
            <div class="row row4">
                <div class="flex-item col-12 col-lg-6 row4-item user-data">
                    <div class="item-bg item-shadow">
                        <div class="item-title">新用户占比</div>
                        <div class="subtitle text-light-gray ml30 fs12">新用户为90日内创建星云账户者</div>
                        <div class="user-pie">
                            <div class="old-user"></div>
                            <div class="new-user-container">
                                <div class="new-user" :style='"transform: rotate(-" + staticInfo.newAddressCount / (staticInfo.newAddressCount + staticInfo.oldAddressCount) * 360 + "deg)"'></div>
                            </div>
                        </div>
                        <div class="detail">
                            <div class="fs12 text-light-gray data-source">Data Sources: Nebulas</div>
                            <div class="title">{{ staticInfo.newAddressCount }}</div>
                            <div class="fs12 text-gray">New Users</div>
                            <div class="title">{{ staticInfo.oldAddressCount }}</div>
                            <div class="fs12 text-gray">Old Users</div>
                        </div>
                    </div>
                </div>
                <div class="flex-item col-12 col-lg-6 row4-item accounts-data">
                    <div class="item-bg item-shadow">
                        <div class="item-title">主网账户数量变化</div>
                        <div class="fs12 text-light-gray data-source">Data Sources: Nebulas</div>
                        <vchart class="accounts-chart" v-if="accountsChartOptions" :options="accountsChartOptions" :autoResize='true'></vchart>
                    </div>
                </div>
            </div>
            <!-- ===================5===================== -->
            <div class="row row5">
                <div class="flex-item col-12 col-lg-6 row5-item">
                    <div class="item-bg item-shadow">
                        <div class="item-title">Blocks</div>
                        <transition-group name="list" tag="table" frame=hsides rules=rows>
                            <tr class="list-item" v-for="(block, i) in blocks" v-if="i < 5" :key="block.height">
                                <td>
                                    <img src="/static/img/icon-block.png" width="50">
                                </td>
                                <td>
                                    Block#
                                    <router-link :to='fragApi + "/block/" + block.height'>{{ block.height }}</router-link>
                                    <br>
                                    <router-link :to='fragApi + "/txs?block=" + block.height'>{{ block.txnCnt }} {{ block.txnCnt > 1 ? "transactions" : "transaction" }}</router-link>
                                </td>
                                <td>
                                    <div class="time">{{ timeConversion(Date.now() - block.timestamp) }} ago</div>
                                </td>
                            </tr>
                        </transition-group>
                    </div>
                </div>
                <div class="flex-item col-12 col-lg-6 row5-item">
                    <div class="item-bg item-shadow">
                        <div class="item-title">Transactions</div>
                        <transition-group name="list" tag="table" frame=hsides rules=rows>
                            <tr v-for="(tx, i) in txs" v-if="i < 5" :key="tx.hash">
                                <td>
                                    <img src="/static/img/icon-tx.png" width="50">
                                </td>
                                <td>
                                    Tx#
                                    <router-link :to='fragApi + "/tx/" + tx.hash'>{{ shortStr(6, tx.hash) }}</router-link>
                                    <br>
                                    From <router-link :to='fragApi + "/address/" + tx.from.hash'>{{ shortStr(4, tx.from.hash) }}</router-link> To <router-link :to='fragApi + "/address/" + tx.from.hash'>{{ shortStr(4, tx.to.hash) }}</router-link>
                                </td>
                                <td>
                                    <div class="time">{{ timeConversion(Date.now() - tx.timestamp) }} ago</div>
                                </td>
                            </tr>
                        </transition-group>
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
    // require('echarts/lib/chart/pie'),
    require('echarts/lib/component/tooltip');

    Number.prototype.pad = function(size) {
        var s = String(this);
        while (s.length < (size || 2)) {s = "0" + s;}
        return s;
    }

    module.exports = {
        components: {
            'vchart': ECharts
        },
        data() {
            return {
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
                todayTxCnt: 0,
                dailyTxData: null,
                market: {},
                blocks: [],
                staticInfo: {},
                txs: [],
                realtimeBlocks: [],
                realtimeBlocksInited: false
            }
        },
        computed: {
            dailyTxChartOptions() {
                if (!this.dailyTxData) return null;
                var arr = [],
                    dates = [], 
                    nums = [];
                for (k in this.dailyTxData) {
                    arr.push([k, this.dailyTxData[k]]);
                }
                arr.sort(function (a, b) { return a[0] > b[0]; });
                for (i in arr) {
                    dates.push(arr[i][0]);
                    nums.push(arr[i][1]);
                }

                var options = {
                    grid: { x: '10', y: '100', width: '96%', height: '210', containLabel: true },
                    xAxis: {
                        data: dates,
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
                        // max: 7000,
                        axisLine: {
                            show: false
                        },
                        axisLabel: {
                            textStyle: {
                                color: '#B2B2B2'
                            },
                            // margin: -10,
                            // formatter: function (value) {
                            //     return value / 1000 + 'k';
                            // }
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
                        data: nums,
                        smooth: true,
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
                };
                return options;
            },
            accountsChartOptions() {
                if (!this.staticInfo.addressWeekList || this.staticInfo.addressWeekList.length == 0) {
                    return null;
                }
                var arr = this.staticInfo.addressWeekList;
                var dates = [], 
                    nums = [];
                arr.sort(function (a, b) { return a.timestamp > b.timestamp; });
                for (i in arr) {
                    nums.push(arr[i].addressCount);
                    let date = new Date(arr[i].timestamp);
                    console.log(date.toDateString());
                    dates.push((date.getMonth() + 1).pad(2) + '-' + date.getDate().pad(2));
                }

                var options = {
                    grid: { x: '10', y: '100', width: '98%', height: '210', containLabel: true },
                    xAxis: {
                        data: dates,
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
                        min: 240000,
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
                        data: nums,
                        smooth: true,
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
                };
                return options;
            },
            blockheight() {
                if (this.blocks.length > 0) return this.numberAddComma(this.blocks[0].height);
                return '...';
            }
        },
        mounted() {
            api.getTx("cnt_static", o => this.dailyTxData = o);                     //近期每日交易量
            api.getMarketCap(o => this.market = o);                                 //币价和市值
            api.getBlock({ type: "latest" }, o => this.blocks = o);                 //最新一波 block
            api.getTx({ type: "latest" }, o => this.txs = o);                       //最新一波 tx
            api.getTodayTxCnt(o => this.todayTxCnt = this.numberAddComma(o));       //今日交易量
            api.getStaticInfo(o => this.staticInfo = o);                            //合约数量、地址数量。。。

            setInterval(() => {
                this.realtimeBlocksInited = true;
                api.getTx({ type: "latest" }, o => this.txs = o);                   //最新一波 tx
                api.getTodayTxCnt(o => this.todayTxCnt = this.numberAddComma(o));   //今日交易量

                api.getBlock({ type: "newblock" }, o => {                           //获取最新一个 block
                    try {
                        if (o[0].height != this.blocks[0].height) {
                            this.blocks.splice(0, 0, o[0]);
                        }
                    } catch(error) {}

                    try {
                        if (o.data[0].height != this.blocks[0].height) {
                            this.blocks.splice(0, 0, o.data[0]);
                        }
                    } catch(error) {}
                });
            }, 8000);

            setInterval(() => {
                api.getMarketCap(o => this.market = o);                             //币价和市值
                api.getStaticInfo(o => this.staticInfo = o);                        //合约数量、地址数量。。。
            }, 60000);
        },
        methods: {
            numberAddComma(n) {
                return utility.numberAddComma(n);
            },
            timeConversion(ms) {
                return utility.timeConversion(ms);
            },
            calBlockHeight(txnCnt) {
                return (1 - Math.min(5, Math.max(0, txnCnt)) / 5.0) * 100 + 'px';
            },
            shortStr(n, s) {
                return utility.shortStr(n, s);
            },
            afterEnter: function (el) {
                $(el.firstElementChild).css('height', this.calBlockHeight(el.firstElementChild.dataset.txncnt));
            }
        }
    }
</script>

