<style scoped>
    .vue-week-selector {
        width: 100%;
        height: 100%;
        max-width: 300px;
        max-height: 340px;
        background-color: white;
        border:1px solid rgba(230,232,242,1);
        z-index: 1080;
        position: relative;
        outline: none;
    }

    .header {
        min-height: 36px;
        padding: 0px 10px 0px 22px;
        border-bottom:1px solid rgba(230,232,242,1);
    }

    .year {
        height: 36px;
        line-height: 36px;
    }

    .months-container {
        width: 78px;
        /* padding: 18px 0px; */
        border-right:1px solid rgba(230,232,242,1);
    }

    .months-container .updown {
        height: 36px;
        line-height: 36px;
        text-align: center;
    }

    .months {
        max-height: 232px;
        overflow: scroll;
    }

    .month {
        width: 100%;
        height: 39px;
        line-height: 39px;
        text-align: center;
        color: rgba(0, 0, 0, 0.6);
        cursor: pointer;
    }

    .month:hover {
        color: white;
        background-color: lightgray;
    }

    .month.selected, .month.selected:hover {
        color: white;
        background-color: #0057FF;
    }

    .weeks {
        padding: 18px 0px;
    }

    .week {
        color: lightgray;
        width: 100%;
        height: 39px;
        line-height: 39px;
        padding-left: 20px;
    }

    .valid-week {
        color: black;
        cursor: pointer;
    }

    .valid-week:hover {
        color: white;
        background-color: lightgray;
    }

    .valid-week.selected, .valid-week.selected:hover {
        color: white;
        background-color: #0057FF;
    }

    @media (max-width: 767.98px) {
        .vue-week-selector {
            margin: auto;
            margin-top: 50%;
        }
    }

</style>
<template>
    <div class="vue-week-selector d-flex flex-column" @blur="$emit('blur')">
        <div class="header d-flex align-items-center">
            <div class="year font-14 font-bold">2019</div>
            <img class="d-block d-md-none ml-auto" src="/static/img/icon_close.png" width="16px" alt="" @click="$emit('blur')">
        </div>
        <div class="flex-fill d-flex align-items-stretch">
            <div class="months-container d-flex flex-column">
                <div class="updown" @click.prevent="scrollUp">
                    <img src="/static/img/date_up.png" width="16px" alt="">
                </div>
                <div class="months full-fill">
                    <div v-for="(month, index) in months" :key="index" :class="['month', index === selectedMonth ? 'selected' : '' ]" @click="selectedMonth = index">{{ month }}</div>
                </div>
                <div class="updown" @click.prevent="scrollDown">
                    <img src="/static/img/date_down.png" width="16px" alt="">
                </div>
            </div>
            <div class="weeks flex-fill">
                <div v-for="(date, index) in weeks[selectedMonth]" :key="index" 
                    :class="['week', isValidWeek(date) ? 'valid-week' : '', isSameDay(date, beginDate) ? 'selected' : '']" 
                    @click="isValidWeek(date) ? $emit('change', date) : $emit('')">
                    {{ weekIndex(date) }}&nbsp;&nbsp;{{ formatDate(date) }}
                </div>
            </div>
        </div>
    </div>
</template>
<script>
var moment = require("@/assets/utility").moment;
export default {
    model: {
        event: 'change'
    },
    props: ['beginDate'],
    data() {
        return {
            months: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
            selectedMonth: new Date().getMonth(),
            weeks: []
        }
    },
    methods: {
        weekIndex(n) {
            var index = 1, found;
            for (var i in this.weeks) {
                if (found > 0) break;
                for (var j in this.weeks[i]) {
                    if (this.weeks[i][j] == n) {
                        found = index;
                        break;
                    }
                    index = index + 1;
                }
            }
            return '[Week ' + found + ']';
        },
        formatDate(n) {
            let m = moment(n).add(6, 'days');
            return n.format("MMM DD") + " - " + m.format("MMM DD");
        },
        scrollUp() {
            $('.vue-week-selector .months').animate({
                scrollTop: 0
            }, 500);
        },
        scrollDown() {
            $('.vue-week-selector .months').animate({
                scrollTop: $(".vue-week-selector .month:first-child").offset().top
            }, 1000);
        },
        isValidWeek(date) {
            return moment().isAfter(moment(date).add(6, 'days'), 'day');
        },
        isSameDay(a, b) {
            return a.isSame(b, 'day');
        }
    },
    mounted() {
        var date = moment(new Date("21 Jan 2019 GMT+0800"));
        // console.log(date);

        var month = 0, weeks = [];
        while (date.year() === 2019) {
            if (date.month() != month) {
                this.weeks.push(weeks);
                month = date.month();
                weeks = [moment(date)];
            } else {
                weeks.push(moment(date));
            }

            date.add(7, 'days');
        }
        this.weeks.push(weeks);
    },
}
</script>
