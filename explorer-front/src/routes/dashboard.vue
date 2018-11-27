<style>
    .vue-dashboard {
        display: block;
    }

    .black-header {
        position: absolute;
        top: 60px;
        width: 100%;
        height: 200px;
        z-index: -1;
    }

    .containter {
        top: -160px;
        width: 1200px;
        height: 1476px;
        margin: 0 auto;
        margin-top: 40px;
    }

    .flex-column-container {
        display: -webkit-flex; /* Safari */
        display: flex;
        flex-flow: column wrap;
        justify-content: space-between;
        align-content: space-between;
        flex: none;
    }

    .flex-row-container {
        display: -webkit-flex; /* Safari */
        display: flex;
        flex-flow: row wrap;
        justify-content: space-between;
        align-content: space-between;
        flex: none;
    }

    .w590 {
        width: 590px;
    }

    .w285 {
        width: 285px;
    }

    .row1 {
        height: 320px;
    }

    .row2 {
        height: 160px;
    }

    .row3 {
        height: 110px;
    }

    .row4 {
        height: 330px;
    }

    .row5 {
        height: 476px;
    }

    .row1-item {
        height: 320px;
    }

    .row2-item {
        height: 160px;
    }

    .row3-item {
        height: 110px;
    }

    .row4-item {
        height: 330px;
    }

    .row5-item {
        height: 476px;
    }

    .flex-item {
        position: relative;
        background-color: white;
        box-shadow:-10px 10px 20px 0px rgba(30,30,30,0.05);
    }

    .row1 .flex-item {
        background:linear-gradient(135deg,rgba(48,58,76,1) 0%,rgba(15,19,26,1) 100%);
        box-shadow: none;
    }

    @media (max-width: 1199px) {
        .containter {
            width: 590px;
            height: 2792px;
        }

        .row1 {
            height: 660px;
        }

        .row2 {
            height: 160px;
        }

        .row3 {
            height: 240px;
        }

        .row4 {
            height: 680px;
        }

        .row5 {
            height: 972px;
        }
    }

    .item-title {
        position: absolute;
        left: 30px;
        top: 25px;
        color: black;
        font-size: 20px;
        font-weight: semibold;
    }

    .row1 .item-title {
        color: white;
    }

    .row3 .item-title {
        top: 23px;
        font-size: 28px;
        /* font-weight: medium; */
    }

</style>
<template>
    <div class="vue-dashboard">
        <div class="bg-black black-header"></div>
        <div class="containter flex-column-container">
            <div class="row1 flex-row-container">
                <div class="flex-item w590 row1-item">
                    <div class="item-title">Daily Transactions</div>
                </div>
                <div class="flex-item w590 row1-item">
                    <div class="item-title">NAS Price</div>
                </div>
            </div>
            <div class="row2 flex-item">
                <div class="item-title">NAS Price</div>
            </div>
            <div class="row3 flex-row-container">
                <div class="flex-row-container w590 row3-item">
                    <div class="flex-item w285">
                        <div class="item-title">1,189,786</div>
                    </div>
                    <div class="flex-item w285">
                        <div class="item-title">1,189,786</div>
                    </div>
                </div>
                <div class="flex-row-container w590 row3-item">
                    <div class="flex-item w285">
                        <div class="item-title">1,189,786</div>
                    </div>
                    <div class="flex-item w285">
                        <div class="item-title">1,189,786</div>
                    </div>
                </div>
            </div>
            <div class="row4 flex-row-container">
                <div class="flex-item w590 row4-item">
                    <div class="item-title">NAS Price</div>
                </div>
                <div class="flex-item w590 row4-item">
                    <div class="item-title">NAS Price</div>
                </div>
            </div>
            <div class="row5 flex-row-container">
                <div class="flex-item w590 row5-item">
                    <div class="item-title">NAS Price</div>
                </div>
                <div class="flex-item w590 row5-item">
                    <div class="item-title">NAS Price</div>
                </div>
            </div>
        </div>

    </div>
</template>
<script>
    var api = require("@/assets/api"),
        utility = require("@/assets/utility"),
        BigNumber = require("bignumber.js");

    module.exports = {

    }
</script>

