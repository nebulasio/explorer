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

    .vue-dashboard .atlaspAds-bottom>a>img {
        margin-bottom: 30px;
    }

    .vue-dashboard #atlaspAds-middle-mobile>a>img {
        margin-bottom: 30px;
    }

    @media (max-width: 767.98px) {
        .vue-dashboard .row>* {
            margin-bottom: 15px;
        }

        .vue-dashboard #atlaspAds-bottom {
            display: none;
        }

        .vue-dashboard #atlaspAds-middle-mobile>a>img {
            margin-bottom: 15px;
        }
    }

    .vue-dashboard .item-bg {
        background-color: white;
        width: 100%;
        height: 100%;
    }

    .vue-dashboard .item-shadow {
        box-shadow:10px 10px 20px 0px rgba(30,30,30,0.05);
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
        font-size: 20px;
        font-weight: 600;
    }

    .vue-dashboard .row1 {
        color: white;
    }

    .vue-dashboard .row1 .item-title {
        color: white;
    }

    .vue-dashboard .daily-transactions .details {
        position: absolute;
        top: 20px;
        right: 30px;
        align-items: baseline;
        text-align: right;
        font-size: 12px;
        color: rgba(255, 255, 255, 0.7);
    }

    .vue-dashboard .daily-transactions .details *:nth-child(2) {
        font-size: 28px;
        color: white;
        font-weight: 400;
        margin-left: 10px;
        margin-top: 10px;
    }

    .vue-dashboard .daily-chart {
        position: absolute;
        top: 90px;
        height: 230px;
        width: calc(100% - 30px);
        margin-left: 30px;
    }

    .vue-dashboard .daily-echart-down-arrow {
        width: 0;
        height: 0;
        border-top: 4px solid #595C63;
        border-right: 4px solid transparent;
        border-left: 4px solid transparent;
        position: absolute;
        bottom: -4px;
        left: calc(50% - 4px);
        z-index: 1060;
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

    @media (max-width: 575.98px) {
        .vue-dashboard .nas-price .market .row div div {
            font-size: 22px;
        }
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

    .vue-dashboard .realtime-block:first-child {
        background-color: #0057FF;
    }

    .vue-dashboard .realtime-block .blockheight {
        display: block;
        width: 15px;
        background-color: rgba(217, 230, 255, 1);
        transition: all 500ms;
        z-index: 3;
    }

    .vue-dashboard .realtime-block .block-popover {
        position: absolute;
        left: calc(-50% + 15px);
        top: -80px;
        padding: 8px;
        font-size: 12px;
        color: white;
        background-color: #0057FF;
        border-radius: 2px;
        transform: translateX(-50%);
        white-space: nowrap;
        z-index: 1060;
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
        font-weight: 600;
    }

    .vue-dashboard .row3 .link-style {
        position: absolute;
        margin-top: 65px;
        margin-left: 30px;
        color: #7F7F7F;
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

    .vue-dashboard .user-pie {
        display: block;
        position: absolute;
        margin-left: 50px;
        margin-top: 108px;
        background-color: gray;
        box-shadow:-10px 10px 20px 0px rgba(30,30,30,0.05);
    }

    .vue-dashboard .old-user {
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

    .vue-dashboard .new-user-container {
        position: absolute;
        left: 57px;
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
        transition-duration: 0.5s;
        transition-timing-function: ease;
    }

    .vue-dashboard .new-user-indicator {
        position: absolute;
        top: 20px;
        left: 57px;
        background: transparent;
        fill: none;
        stroke: none;
    }

    .vue-dashboard .new-user-indicator .line {
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

    .vue-dashboard .new-user-indicator .labels {
        position: absolute;
        top: 0px;
        right: 0px;
        text-align: right;
    }

    .vue-dashboard .new-user-indicator .labels>div:first-child {
        margin-top: 31px;
        font-size: 18px;
        font-weight: 400;
        transform: translateY(12px);
        opacity: 0;
        animation: label 1s ease-out 1.5s forwards;
    }

    .vue-dashboard .new-user-indicator .labels>div:last-child {
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

    .vue-dashboard .row4 .detail {
        display: flex;
        flex-flow: column nowrap;
        justify-content: flex-end;
        align-items: flex-end;
        position: absolute;
        top: 74px;
        right: 30px;
    }

    /* .vue-dashboard .row4 .user-data .data-source {
        margin-top: 30px;
        margin-bottom: 26px;
    } */

    .vue-dashboard .row4 .title {
        font-size: 28px;
        margin-top: 40px;
        font-weight: 600;
    }

    /* .vue-dashboard .row4 .accounts-data .data-source {
        position: absolute;
        top: 30px;
        right: 30px;
    } */

    .vue-dashboard .accounts-chart {
        position: absolute;
        top: 90px;
        width: calc(100% - 30px);
        height: 240px;
        margin-left: 30px;
    }

    .vue-dashboard .account-echart-down-arrow {
        width: 0;
        height: 0;
        border-top: 4px solid #0057FF;
        border-right: 4px solid transparent;
        border-left: 4px solid transparent;
        position: absolute;
        bottom: -4px;
        left: calc(50% - 4px);
        z-index: 1060;
    }

    .vue-dashboard .row5 .item-bg {
        position: relative;
        padding: 23px 30px;
    }

    .vue-dashboard .row5 .item-title {
        position: absolute;
        margin: 0;
    }

    .vue-dashboard .row5 .showall {
        position: absolute;
        top: 30px;
        right: 30px;
        font-size: 12px;
        font-weight: 400;
        color: #555;
    }

    .vue-dashboard .row5 .showall:hover {
        color: #0057FF
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
        overflow-y: scroll;
    }

    .vue-dashboard .row5 tr {
        transition: all 1s;
    }

    .vue-dashboard .row5 tr:nth-of-type(n+6) {
        opacity: 0;
    }

    .vue-dashboard #atlaspAds-side {
        position: fixed;
        top: 460px;
        left: calc((100% - 1140px) * 0.5 + 1140px + 5px);
        width: 300px;
        max-width: calc((100% - 1140px) * 0.5 - 25px);
    }

    @media (max-width: 320px) {
        .vue-dashboard .realtime-block:nth-of-type(-n+5) {
            display: block;
            opacity: 1;
        }

        .vue-dashboard .realtime-block:nth-of-type(5) {
            opacity: 0;
        }

        .vue-dashboard .row5 .txcnt {
            display: none;
        }
    }


    @media (max-width: 414px) {
        .vue-dashboard .row1 .data-source {
            visibility: hidden;
        }
    }

    @media (max-width: 575.98px) {
        .vue-dashboard .realtime-block:nth-of-type(-n+7) {
            display: block;
            opacity: 1;
        }

        .vue-dashboard .realtime-block:nth-of-type(7) {
            opacity: 0;
        }

        .vue-dashboard .user-pie {
            margin-left: 30px;
            margin-top: 128px;
        }

        .vue-dashboard .row5 td:first-child {
            width: 60px;
        }

        .vue-dashboard .row5 td .time::after {
            content:'';
            white-space:pre;
        }
    }

     /* Small devices (landscape phones, 576px and up) */
    @media (min-width: 576px) {
        .vue-dashboard .realtime-block:nth-of-type(-n+14) {
            display: block;
            opacity: 1;
        }

        .vue-dashboard .realtime-block:nth-of-type(14) {
            opacity: 0;
        }
    }

    /* Medium devices (tablets, 768px and up) */
    @media (min-width: 768px) {
        .vue-dashboard .realtime-block:nth-of-type(-n+21) {
            display: block;
            opacity: 1;
        }

        .vue-dashboard .realtime-block:nth-of-type(21) {
            opacity: 0;
        }
    }

    /* Large devices (desktops, 992px and up) */
    @media (min-width: 992px) {
        .vue-dashboard .realtime-block:nth-of-type(-n+31) {
            display: block;
            opacity: 1;
        }

        .vue-dashboard .realtime-block:nth-of-type(31) {
            opacity: 0;
        }
    }

    /* Extra large devices (large desktops, 1200px and up) */
    @media (min-width: 1200px) {
        .vue-dashboard .realtime-block:nth-of-type(-n+38) {
            display: block;
            opacity: 1;
        }

        .vue-dashboard .realtime-block:nth-of-type(38) {
            opacity: 0;
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
                        <div id="demoi18n3"></div>
                        <div class="details" v-if="dailyTxChartOptions">
									<span v-if="todayTxCnt >= 0">Today</span>
									<span v-if="todayTxCnt >= 0">{{ numberAddComma(todayTxCnt) }}</span>
								</div>
                        <vchart class="daily-chart" v-if="dailyTxChartOptions" :options="dailyTxChartOptions" :autoResize='true'></vchart>
                    </div>
                </div>
                <div class="nas-price flex-item col-12 col-lg-6 row1-item">
                    <div class="item-bg">
                        <div class="item-title">NAS Price</div>
                        <div v-if="market" class="update-time">Update Time : {{ timeConversion(Date.now() - market.createdAt) }} ago</div>
                        <div v-if="market" class="detail">
                            <span>$</span>
                            <span>{{ market.price }}</span>
                            <span :class="{'text-red': market.trends <= 0}">({{ market.trends > 0 ? '+' : '-' }}{{ market.change24h }}%)</span>
                        </div>
                        <div v-if="market" class="market container">
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
            <!-- ====================dip program==================== -->
            <div v-if="$root.testnetDipStarted" class="row">
                <div class="col">
                    <div class="flex-item item-bg item-shadow">
                        <vue-dip-banner></vue-dip-banner>
                    </div>
                </div>
            </div>
            <!-- ====================2==================== -->
            <div class="row row2">
                <div class="col">
                    <div class="flex-item item-bg item-shadow">
                        <div class="item-title">Blocks</div>
                        <div class="subtitle font-12 text-gray">Block Status</div>
                        <transition-group v-on:after-enter="afterEnter" name="row2-list" class="realtime-blocks">
                            <div class="realtime-block" v-for="block in blocks" :key="block.height">
                                <div class="blockheight" style="height: 100%" :data-txncnt="block.txnCnt"></div>
                                <div class="block-popover">
                                    <div class="font-12 font-bold">{{ numberAddComma(block.height) }}</div>
                                    <div>Transactions: {{ block.txnCnt }}</div>
                                    <div>Block Interval: 15s</div>
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
                        <div v-if="staticInfo">{{ blockheight }}</div>
                        <router-link v-if="staticInfo" class="link link-style" :to='fragApi + "/blocks/"'>Block Height ></router-link>
                        <img src=/static/img/dashboard-1.png width=44 alt="">
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-12 flex-item w285">
                    <div class="item-bg item-shadow">
                        <div v-if="staticInfo">{{ numberAddComma(staticInfo.txnCnt) }}</div>
                        <router-link v-if="staticInfo" class="link link-style" :to='fragApi + "/txs/"'>Total Transactions ></router-link>
                        <img src=/static/img/dashboard-2.png width=44 alt="">
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-12 flex-item w285">
                    <div class="item-bg item-shadow">
                        <div v-if="staticInfo">{{ numberAddComma(staticInfo.totalContractCount) }}</div>
                        <router-link v-if="staticInfo" class="link link-style" :to='fragApi + "/contracts/"'>Total Smart Contracts ></router-link>
                        <img src=/static/img/dashboard-3.png width=44 alt="">
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-12 flex-item w285">
                    <div class="item-bg item-shadow">
                        <div v-if="staticInfo">{{ numberAddComma(staticInfo.totalAddressCount) }}</div>
                        <router-link v-if="staticInfo" class="link link-style" :to='fragApi + "/accounts/"'>Total Addresses ></router-link>
                        <img src=/static/img/dashboard-4.png width=44 alt="">
                    </div>
                </div>
            </div>
            <!-- ===================4===================== -->
            <div class="row row4">
                <div class="flex-item col-12 col-lg-6 row4-item user-data">
                    <div class="item-bg item-shadow">
                        <div class="item-title">
                            New Addresses Percentage
                            <div class="text-light-gray font-12 font-regular">New addresses are addresses created within 90 days.</div>
                            <div v-if="staticInfo" class="mt20 d-sm-none">
                                <span class="font-12 font-color-7F7F7F">New Addresses</span>
                                <span class="font-20">{{ (Math.round(100 * staticInfo.newAddressCount / staticInfo.totalAddressCount * 10) / 10).toFixed(1) + '%' }}</span>
                            </div>
                        </div>
                        <div v-show="staticInfo" class="user-pie">
                            <div class="old-user"></div>
                            <div class="new-user-container">
                                <transition name="new-user-pie">
                                    <div class="new-user" :style='staticInfo ? "transform: rotate(-" + staticInfo.newAddressCount / staticInfo.totalAddressCount * 360 + "deg)" : ""'></div>
                                </transition>
                            </div>
                            <div v-if="staticInfo" class="new-user-indicator d-none d-sm-block">
                                <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" xml:space="preserve" class="svg" width="195" height="114" viewbox="0 0 195 114">
                                    <polyline points="5 87, 35 57, 195 57" class="line" id="svg-line"/>
                                </svg>
                                <div class="labels">
                                    <!-- 四舍五入并保留一位小数 -->
                                    <div>{{ (Math.round(100 * staticInfo.newAddressCount / staticInfo.totalAddressCount * 10) / 10).toFixed(1) + '%' }}</div>
                                    <div>New Addresses</div>
                                </div>
                            </div>
                        </div>
                        <div v-if="staticInfo" class="detail">
                            <!-- <div class="font-12 text-light-gray data-source">Data Sources: Nebulas</div> -->
                            <div class="title">{{ numberAddComma(staticInfo.newAddressCount) }}</div>
                            <div class="font-12 text-gray">New Addresses</div>
                            <div class="title">{{ numberAddComma(staticInfo.totalAddressCount) }}</div>
                            <div class="font-12 text-gray">Total Addresses</div>
                        </div>
                    </div>
                </div>
                <div class="flex-item col-12 col-lg-6 row4-item accounts-data">
                    <div class="item-bg item-shadow">
                        <div class="item-title">Growth of Addresses</div>
                        <!-- <div v-if="accountsChartOptions" class="font-12 text-light-gray data-source">Data Sources: Nebulas</div> -->
                        <vchart class="accounts-chart" v-if="accountsChartOptions" :options="accountsChartOptions" :autoResize='true'></vchart>
                    </div>
                </div>
            </div>
            <!--ATP mobile中间广告位-->
            <div class="flex atlaspAds-bottom d-md-none" id="atlaspAds-middle-mobile"></div>
            <!-- ===================5===================== -->
            <div class="row row5">
                <div class="flex-item col-12 col-lg-6 row5-item">
                    <div class="item-bg item-shadow">
                        <div class="item-title">Blocks</div>
                        <router-link :to='fragApi + "/blocks/"' class="showall">View All ></router-link>
                        <transition-group name="list" tag="table" frame=hsides rules=rows>
                            <tr class="list-item" v-for="(block, i) in blocks" v-if="i < 5" :key="block.height">
                                <td>
                                    <img src="/static/img/icon-block.png?v=20190116" width="50" height="50">
                                </td>
                                <td>
                                    Block#
                                    <router-link :to='fragApi + "/block/" + block.height' class="monospace">{{ block.height }}</router-link>
                                    <br>
                                    <span class="txcnt monospace">
                                        <router-link v-if="block.txnCnt" :to='fragApi + "/txs?block=" + block.height'>{{ block.txnCnt }} {{ block.txnCnt > 1 ? "transactions" : "transaction" }}</router-link>
                                        <span v-else>0 transaction</span>
                                    </span>
                                </td>
                                <td>
                                    <div class="time">{{ timeConversion(Date.now() - block.localTimestamp + block.timeDiff) }} ago</div>
                                </td>
                            </tr>
                        </transition-group>
                    </div>
                </div>
                <div class="flex-item col-12 col-lg-6 row5-item">
                    <div class="item-bg item-shadow">
                        <div class="item-title">Transactions</div>
                        <router-link :to='fragApi + "/txs/"' class="showall">View All ></router-link>
                        <transition-group name="list" tag="table" frame=hsides rules=rows>
                            <tr v-for="(tx, i) in txs" v-if="i < 5" :key="tx.hash">
                                <td>
                                    <img src="/static/img/icon-tx.png?v=20190116" width="50" height="50">
                                </td>
                                <td>
                                    Tx#
                                    <router-link :to='fragApi + "/tx/" + tx.hash'>
                                        <span class="monospace">{{ tx.hash.slice(0, 4) }}</span>...<span class="monospace">{{ tx.hash.slice(-4) }}</span>
                                    </router-link>
                                    <br>
                                    <span class="fromto d-none d-sm-inline">
                                        From
                                        <router-link :to='fragApi + "/address/" + tx.from.hash'>
                                            <span class="monospace">{{ tx.from.hash.slice(0, 4) }}</span>...<span class="monospace">{{ tx.from.hash.slice(-4) }}</span>
                                        </router-link>
                                    </span>
                                    <span class="fromto d-none d-sm-inline">
                                        To
                                        <router-link :to='fragApi + "/address/" + tx.from.hash'>
                                            <span class="monospace">{{ tx.to.hash.slice(0, 4) }}</span>...<span class="monospace">{{ tx.to.hash.slice(-4) }}</span>
                                        </router-link>
                                    </span>
                                </td>
                                <td>
                                    <div class="time">{{ timeConversion(Date.now() - tx.localTimestamp + tx.timeDiff) }} ago</div>
                                </td>
                            </tr>
                        </transition-group>
                    </div>
                </div>
            </div>
            <!--ATP底部广告位-->
            <div class="flex atlaspAds-bottom d-none d-md-block" id="atlaspAds-bottom"></div>
        </div>
        <!--ATP侧边栏广告位-->
        <div class="flex atlaspAds" id="atlaspAds-side"></div>
    </div>
</template>
<script>
    var api = require("@/assets/api"),
        utility = require("@/assets/utility"),
        BigNumber = require("bignumber.js");

    var ECharts = require('vue-echarts/components/ECharts').default;
    require('echarts/lib/chart/line');
    require('echarts/lib/component/tooltip');

    module.exports = {
        components: {
            'vchart': ECharts,
            "vue-dip-banner": require("@/components/vue-dip-banner").default
        },
        data() {
            return {
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
                todayTxCnt: -1,
                dailyTxData: null,
                market: null,
                blocks: [],
                staticInfo: null,
                txs: [],
                shortIntervalID: null,
                longIntervalID: null
            }
        },
        computed: {
            dailyTxChartOptions() {
                if (!this.dailyTxData) return null;
                var arr = [],
                    dates = [],
                    nums = [];
                for (var k in this.dailyTxData) {
                    arr.push([k, this.dailyTxData[k]]);
                }
                arr.sort(function (a, b) { return Date.parse(a[0]) - Date.parse(b[0]); });
                // if (arr.length > 13) {
                //     arr.splice(0, arr.length - 13);
                // }
                for (var i in arr) {
                    dates.push(arr[i][0]);
                    nums.push(arr[i][1]);
                }

                let vm = this;
                var options = {
                    grid: { left: '40', bottom: '50', right: '17', top: '10', containLabel: false },
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
                            formatter: function(value) {
                                return vm.shortDate(value);
                            },
                        }
                    },
                    yAxis: {
                        // min: 'dataMin',
                        axisLine: {
                            show: false
                        },
                        axisLabel: {
                            textStyle: {
                                color: '#B2B2B2'
                            },
                            margin: 0,
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
                            color: '#595C63',
                            width: 3
                        },
                        itemStyle: {
                            color: '#FFFFFF',
                            borderWidth: 3
                        },
                        areaStyle: {
                            color: '#595C63',
                            opacity: 1
                        }
                    },
                    tooltip: {
                        trigger: 'item',
                        transitionDuration: 0,
                        position: 'top',
                        formatter: function(params, ticket, callback) {
                            let date = new Date(params.name);
                            let dateStr = date.toLocaleDateString('en', { year: 'numeric', month: 'short', day: 'numeric' });
                            return dateStr + '<div>Transactions: ' + vm.numberAddComma(params.value) + '</div><div class=daily-echart-down-arrow></div>';
                        },
                        backgroundColor: '#595C63',
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
            accountsChartOptions() {
                if (!this.staticInfo || !this.staticInfo.addressWeekList || this.staticInfo.addressWeekList.length == 0) {
                    return null;
                }
                var arr = this.staticInfo.addressWeekList;
                var dates = [],
                    nums = [];

                arr.sort(function (a, b) { return a.timestamp > b.timestamp; });
                if (arr.length > 8) {
                    arr.splice(0, arr.length - 8);
                }

                for (var i in arr) {
                    nums.push(arr[i].addressCount);
                    dates.push(arr[i].timestamp);
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
                            formatter: function(value) {
                                return vm.shortDate(new Number(value));
                            }
                        }
                    },
                    yAxis: {
                        min: Math.floor(nums[0] / 1000) * 1000 - 1000,
                        axisLine: {
                            show: false
                        },
                        axisLabel: {
                            textStyle: {
                                color: '#B2B2B2'
                            },
                            margin: 0,
                            formatter: function (value) {
                                return value / 1000 + 'k';
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
                        minInterval: 1000
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
                            let date = new Date(new Number(params.name));
                            let dateStr = date.toLocaleDateString('en', { year: 'numeric', month: 'short', day: 'numeric' });
                            return dateStr + '<div>Amount: ' + vm.numberAddComma(params.value) + '</div><div class=account-echart-down-arrow></div>';
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
            blockheight() {
                if (this.blocks.length > 0) return this.numberAddComma(this.blocks[0].height);
                return '0';
            }
        },
        mounted() {
            api.getTx("cnt_static", o => this.dailyTxData = o);                     //近期每日交易量
            api.getMarketCap(o => this.market = o);                                 //币价和市值
            api.getBlock({ type: "latest" }, o => this.blocks = this.addLocalTimestamp(o));                 //最新一波 block
            api.getTx({ type: "latest" }, o => this.txs = this.addLocalTimestamp(o));                       //最新一波 tx
            api.getTodayTxCnt(o => this.todayTxCnt = o);                            //今日交易量
            api.getStaticInfo(o => this.staticInfo = o);                            //合约数量、地址数量。。。

            this.shortIntervalID = setInterval(() => {
                api.getTx({ type: "latest" }, o => this.txs = this.addLocalTimestamp(o));                   //最新一波 tx
                api.getBlock({ type: "newblock" }, o => {                           //获取最新一个 block
                    this.addLocalTimestamp(o);
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
            }, 5000);

            this.longIntervalID = setInterval(() => {
                api.getTodayTxCnt(o => this.todayTxCnt = o);                        //今日交易量
                api.getMarketCap(o => this.market = o);                             //币价和市值
                api.getStaticInfo(o => this.staticInfo = o);                        //合约数量、地址数量。。。
            }, 60000);

            if (this.$root.showAtpAds) {
                /*初始化ATPSDK，并设置partnerID (init ATP-SDK ,Set partnerID)*/
                var atpAds = AtlasAds('pbg91eenif2mbsoo3g1qg');

                //获取广告 传入div containerId和广告的宽高（getAd set the containerId and dimension wide high）
                atpAds.getAd('#atlaspAds-bottom', 'nas_1200x100_001');
                atpAds.getAd('#atlaspAds-side', 'nas_360x300_001');
                atpAds.getAd('#atlaspAds-middle-mobile', 'nas_720x200_001');

                //侧栏广告尺寸限制
                window.onresize = function () {
                    if (window.innerWidth >= 1600) {
                        $('#atlaspAds-side').show();
                    } else {
                        $('#atlaspAds-side').hide();
                    }
                }
                window.onresize();
            }
        },
        methods: {
            numberAddComma(n) {
                return utility.numberAddComma(n);
            },
            timeConversion(ms) {
                return utility.timeConversion(ms);
            },
            afterEnter: function (el) {
                let height = (1 - Math.min(5, Math.max(0, el.firstElementChild.dataset.txncnt)) / 5.0) * 100 + 'px';
                $(el.firstElementChild).css('height', height);
            },
            shortDate(value) {
                if (!value || value === 'undefined') {
                    return '';
                }
                let date = new Date(value);
                if (isNaN(date.getMonth())) {
                    return '';
                }
                let str = date.toLocaleDateString('en', { month: 'short', day: 'numeric' });
                if (str.length > 6) {
                    str = date.getMonth() + 1 + '-' + date.getDate();
                }
                return str;
            },
            addLocalTimestamp(n) {
                if (n instanceof Array) {
                    for (var index in n) {
                        if (!n[index].localTimestamp) {
                            n[index].localTimestamp = Date.now();
                        }
                    }
                }
                return n;
            }
        },
        updated() {
            if (window.isIE()) {
                $('#svg-line').css('stroke-dasharray', 'none');
            }
        },
        destroyed() {
            clearInterval(this.shortIntervalID);
            clearInterval(this.longIntervalID);
        }
    }
</script>