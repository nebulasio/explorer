<style lang="scss" scoped>
.dstaking {
    color: #000000;
    label {
        color: rgba(0, 0, 0, 0.5);
    }
    .row {
        margin-top: 20px;
        align-items: stretch;
        .item {
            height: 100%;
            background-color: #ffffff;
            padding: 30px;
            box-shadow:-10px 10px 20px 0px rgba(30,30,30,0.05);
        }
    }

    .token-value {
        display: inline-block;
    }

    .item-1, .item-2 {
        label {
            display: block;
        }
    }

    .item-2 {
        .user-pie {
            display: block;
            position: relative;
            height: 154px;
            // margin-left: 50px;
            // margin-top: 50px;
            background-color: gray;
            box-shadow:-10px 10px 20px 0px rgba(30,30,30,0.05);
        }

        .old-user {
            position: absolute;
            top: 20px;
            /* left: 20px; */
            width: 114px;
            height: 114px;
            background-color: rgba(89, 146, 255, 1);
            border-style: solid;
            border-width: 0px;
            border-radius: 50%;
        }

        .new-user-container {
            position: absolute;
            left: 57px;
            width: 77px;
            height: 154px;
            overflow: hidden;
        }

        .new-user {
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
            transition-duration: 0.5s;
            transition-timing-function: ease;
        }

        .new-user-indicator {
            position: absolute;
            top: 20px;
            left: 57px;
            background: transparent;
            fill: none;
            stroke: none;
        }

        .new-user-indicator .line {
            fill: none;
            stroke: #000000;
            stroke-width: 0.5px;
            stroke-dasharray: 0px, 202px;
            animation: lineMove 1s ease-out 0.5s forwards;
        }

        @keyframes lineMove {
            0%{
                stroke-dasharray: 0px, 202px;
            }
            100%{
                stroke-dasharray: 202px, 202px;
            }
        }

        .new-user-indicator .labels {
            position: absolute;
            top: -20px;
            right: 0px;
            text-align: right;
        }

        .new-user-indicator .labels>div:first-child {
            margin-top: 31px;
            font-size: 18px;
            font-weight: 400;
            transform: translateY(12px);
            opacity: 0;
            animation: label 1s ease-out 1.5s forwards;
        }

        .new-user-indicator .labels>div:last-child {
            font-size: 12px;
            color: #555555;
            transform: translateY(-10px);
            opacity: 0;
            animation: label 1s ease-out 1.5s forwards;
        }

        @keyframes label {
            to {
                transform: translateY(0px);
                opacity: 1;
            }
        }

        .detail {
            display: flex;
            flex-flow: column nowrap;
            justify-content: flex-end;
            align-items: flex-end;
            // position: absolute;
            // top: 74px;
            // right: 30px;
        }
    }

    .item-3 {
        .form-check {
            display: flex;
            align-items: center;
            padding: 0;
            .form-check-input {
                margin: 0;
            }
            .form-check-label {
                margin-left: 1.2rem;
            }
        }
        .trend-chart {
            width: calc(100% - 30px);
            height: 240px;
        }
        .form-check-input:checked + label {
            color: #0057FF;
        }
    }
}
</style>
<template>
    <div class="dstaking fullfill">
        <vue-bread title='dStaking Dashboard' nextdao=true>
            <a href="">nextdao.io</a>
        </vue-bread>
        <div v-if="nextIssueBlockHeight" class="container">
            <div class="row">
                <div class="col-12 col-md-6">
                    <div class="item item-1">
                        <h1>{{leftTime}}</h1>
                        <label>{{keyEstimatedLeftTime}}</label>
                        <div class="d-flex mt-5">
                            <div class="w-50">
                                <h4>{{numberAddComma(currentBlockHeight)}}</h4>
                                <router-link :to='fragApi + "/block/" + currentBlockHeight'>{{ keyCurrentBlockHeight }}</router-link>
                            </div>
                            <div class="ml-3">
                                <h4>{{numberAddComma(nextIssueBlockHeight)}}</h4>
                                <label>{{keyNextIssueBlockHeight}}</label>
                            </div>
                        </div>
                        <div class="d-flex mt-3">
                            <div class="w-50">
                                <h4 class="token-value">{{naxAmount(lastNaxDistribution)}}</h4>
                                <span>NAX</span>
                                <label>{{keyLastNaxDistribution}}</label>
                            </div>
                            <div class="ml-3">
                                <h4 class="token-value">{{naxAmount(totalNaxDistribution)}}</h4>
                                <span>NAX</span>
                                <label>{{keyTotalNaxDistribution}}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-6 mt-3 mt-md-0">
                    <div class="item item-2 d-flex flex-column">
                        <div class="d-flex align-items-center justify-content-between">
                            <h4>{{keyStakeRate}}</h4>
                            <label>Update Time: 15 Secs ago</label>
                        </div>
                        <div class="flex-fill d-flex align-items-center justify-content-between">
                            <div class="user-pie">
                                <div class="old-user"></div>
                                <div class="new-user-container">
                                    <transition name="new-user-pie">
                                        <div class="new-user" :style='stakedAmount ? "transform: rotate(-" + stakedAmount / nasCirculation * 360 + "deg)" : ""'></div>
                                    </transition>
                                </div>
                                <div class="new-user-indicator d-none d-sm-block">
                                    <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" xml:space="preserve" class="svg" width="195" height="114" viewbox="0 0 195 114">
                                        <polyline points="5 67, 35 37, 195 37" class="line" id="svg-line"/>
                                    </svg>
                                    <div class="labels">
                                        <!-- 四舍五入并保留一位小数 -->
                                        <div>{{ (Math.round(100 * stakedAmount / nasCirculation * 10) / 10).toFixed(2) + '%' }}</div>
                                        <div>{{keyStakeRate}}</div>
                                    </div>
                                </div>
                            </div>
                            <div class="detail">
                                <!-- <div class="font-12 text-light-gray data-source">Data Sources: Nebulas</div> -->
                                <div>
                                    <h4 class="token-value">{{ nasAmount(stakedAmount) }}</h4>
                                    <span>NAS</span>
                                </div>
                                <label>{{keyStakedAmount}}</label>
                                <div>
                                    <h4 class="token-value mt-3">{{ nasAmount(nasCirculation) }}</h4>
                                    <span>NAS</span>
                                </div>
                                <label>{{keyNasCirculation}}</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div v-if="trendList.length" class="row">
                <div class="col-12">
                    <div class="item item-3">
                        <div class="d-md-flex align-items-center justify-content-between">
                            <h4>{{keyDstakingTrend}}</h4>
                            <router-link :to='fragApi + "/dstaking-history"'>View More ></router-link>
                        </div>
                        <div class="d-flex mt-1">
                            <div class="form-check mr-4">
                                <input class="form-check-input" type="radio" id="exampleRadios1" :checked="trendTab === 0" @click="trendTab = 0">
                                <label class="form-check-label" for="exampleRadios1">
                                    {{keyStakeRate}}
                                </label>
                            </div>
                            <div class="form-check mr-4">
                                <input class="form-check-input" type="radio" id="exampleRadios2" :checked="trendTab === 1" @click="trendTab = 1">
                                <label class="form-check-label" for="exampleRadios2">
                                    {{keyDistributionAmount}}
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" id="exampleRadios3" :checked="trendTab === 2" @click="trendTab = 2">
                                <label class="form-check-label" for="exampleRadios3">
                                    {{keyDestroyedAmount}}
                                </label>
                            </div>
                        </div>
                        <vchart class="trend-chart mt-5" v-if="dailyTxChartOptions" :options="dailyTxChartOptions" :autoResize='true'></vchart>
                    </div>
                </div>
            </div>
            <div v-else style="left: 0;right:0;text-align:center; padding-top: 76px; padding-bottom: 80px;">
                <img style="width: 131px; height: 142px;" src="/static/img/no_content.png?v=20190117"/>
                <br/>
                <div style="margin-top: 12px;">
                    <span class="text-no-content">Waiting for the first issuance</span>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
var api = require("@/assets/api"),
    utility = require("@/assets/utility"),
    BigNumber = require("bignumber.js"),
    moment = require("@/assets/utility").moment;

var ECharts = require('vue-echarts/components/ECharts').default;
    require('echarts/lib/chart/line');
    require('echarts/lib/component/tooltip');

module.exports = {
    components: {
        "vue-bread": require("@/components/vue-bread").default,
        'vchart': ECharts,
    },
    data() {
        return {
            fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
            currentBlockHeight: 2888888,
            nextIssueBlockHeight: null,
            lastNaxDistribution: 9999999,
            totalNaxDistribution: 11999999,
            stakedAmount: 7777777,
            nasCirculation: 50000000,
            trendList: [],
            trendTab: 0,
            shortIntervalID: null,
            keyEstimatedLeftTime: "Next Mint Count Down ",
            keyCurrentBlockHeight: "Current Block >",
            keyNextIssueBlockHeight: "Next Mint Block",
            keyLastNaxDistribution: "Prev Minted NAX ",
            keyTotalNaxDistribution: "Total Minted NAX",
            keyStakedAmount: "dStaking NAS",
            keyNasCirculation: "Circulating NAS",
            keyStakeRate: "dStaking Rate",
            keyDstakingTrend: "dStaking Trend",
            keyDistributionAmount: "Minted NAX",
            keyDestroyedAmount: "Burned NAX",
        };
    },
    mounted() {
        BigNumber.config({ DECIMAL_PLACES: 18 })

        this.$root.showModalLoading = true;
        
        this.getSummary();
        this.getNewBlock();
        this.shortIntervalID = setInterval(() => {
            this.getSummary();
            this.getNewBlock();
        }, 15000);
    },
    destroyed() {
        clearInterval(this.shortIntervalID);
    },
    methods: {
        getSummary() {
            api.getDstakingSummary({}, o => {
                this.$root.showModalLoading = false;
                this.nextIssueBlockHeight = o.endHeight;
                this.lastNaxDistribution = o.lastDistributedNax;
                this.totalNaxDistribution = o.totalDistributedNax;
                this.stakedAmount = o.currentPledgedNas;
                this.nasCirculation = o.currentTotalNas;
                this.trendList = o.list;
            }, xhr => {
                this.$root.showModalLoading = false;
                this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404");
            });
        },
        getNewBlock() {
            //获取最新一个 block
            api.getBlock({ type: "newblock" }, o => {                   
                if (o.length > 0) {
                    this.currentBlockHeight = o[0].height;
                }
            });
        },
        numberAddComma(n) {
            return utility.numberAddComma(n);
        },
        timeConversion(ms) {
            return utility.timeConversion(ms);
        },
        nasAmount(n) {
            BigNumber.config({ DECIMAL_PLACES: 0 })
            var amount = BigNumber(n);
            var decimals = BigNumber('1e+18');
            return amount.div(decimals).toFormat();
        },
        naxAmount(n) {
            BigNumber.config({ DECIMAL_PLACES: 18 })
            BigNumber.config({ DECIMAL_PLACES: 0 })
            var amount = BigNumber(n);
            var decimals = BigNumber('1e+9');
            return amount.div(decimals).toFormat();
        },
        bigUnit(n) {
            BigNumber.config({ DECIMAL_PLACES: 18 })
            if (BigNumber(n).isGreaterThanOrEqualTo(BigNumber('1e+6'))) {
                return BigNumber(n).div(BigNumber('1e+6')) + 'M'
            } else if (BigNumber(n).isGreaterThanOrEqualTo(BigNumber('1e+3'))) {
                return BigNumber(n).div(BigNumber('1e+3')) + 'K'
            } 
            return n;
        },
        bigNaxUnit(n) {
            console.log(n);
            return this.bigUnit(BigNumber(n).div(BigNumber('1e+9')));
        }
    },
    computed: {
        leftTime() {
            if (this.nextIssueBlockHeight - this.currentBlockHeight <= 0) {
                return '00:00:00';
            }
            var duration = moment.duration((this.nextIssueBlockHeight - this.currentBlockHeight) * 15000, 'milliseconds');
            return (duration.days() * 24 + duration.hours()).pad(2) + ":" + duration.minutes().pad(2) + ":" + duration.seconds().pad(2);
        },
        dailyTxChartOptions() {
            if (!this.trendList || this.trendList.length == 0) {
                return null;
            }

            var arr = this.trendList.slice(0, 15).reverse();
            var dates = [], 
                nums = [];

            for (var i in arr) {
                var item = arr[i];
                if (this.trendTab === 0) {
                    var stakeRate = BigNumber(item.pledgeNas) / BigNumber(item.totalNas);
                    nums.push(stakeRate);
                } else if (this.trendTab === 1) {
                    var lastDistributed = item.distributedNax;
                    nums.push(lastDistributed);
                } else {
                    var lastDestroyed = item.destroyedNax;
                    nums.push(lastDestroyed);
                }
                dates.push(item.stage);
            }
            
            let vm = this;
            var options = {
                grid: { left: '30', bottom: '50', right: '17', top: '10', containLabel: false },
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
                        margin: 18,
                        // formatter: function(value) {
                        //     return vm.shortDate(new Number(value));
                        // }
                    }
                },
                yAxis: {
                    min: 0,
                    axisLine: {
                        show: false
                    },
                    axisLabel: {
                        textStyle: {
                            color: '#B2B2B2'
                        },
                        margin: 0,
                        formatter: function (value) {
                            if (vm.trendTab === 0) {
                                return value * 100 + '%';
                            } else {
                                return vm.bigNaxUnit(value);
                            }
                            return value;
                        }
                    },
                    axisTick: {
                        show: false
                    },
                    splitLine: {
                        show: false
                    },
                    // splitNumber: 5,
                    // maxInterval: 3000,
                    // minInterval: null
                },
                series: {
                    type: 'line',
                    data: nums,
                    smooth: true,
                    symbol: 'emptyCircle',
                    symbolSize: 7,
                    lineStyle: {
                        color: '#0057FF',
                        width: 3
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
                        color: '#0057FF',
                        opacity: 1
                    }
                },
                tooltip: {
                    trigger: 'item',
                    transitionDuration: 0,
                    position: 'top',
                    formatter: function(params, ticket, callback) {
                        let stageStr = 'Period: ' + params.name;
                        var detailStr = null;
                        if (vm.trendTab === 0) {
                            detailStr = vm.keyStakeRate + ': ' + (params.value * 100).toFixed(2) + '%';
                        } else if (vm.trendTab === 1) {
                            detailStr = vm.keyDistributionAmount + ': ' + vm.naxAmount(params.value);
                        } else {
                            detailStr = vm.keyDestroyedAmount + ': ' + vm.naxAmount(params.value);
                        }
                        return stageStr + '<div>' + detailStr + '</div><div class=account-echart-down-arrow></div>';
                    },
                    backgroundColor: '#0057FF',
                    padding: 8,
                    extraCssText: 'border-radius: 2px;box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);',
                    textStyle: {
                        fontFamily: 'menlo, consolas',
                        fontSize: 12,
                        lineHeight: 18
                    }
                }
            };
            return options;
        },
    },
}
</script>