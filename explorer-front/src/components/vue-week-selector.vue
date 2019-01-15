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

    .months-container a {
        height: 36px;
        line-height: 36px;
        text-align: center;
    }

    .months {
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
        width: 100%;
        height: 39px;
        line-height: 39px;
        padding-left: 20px;
        color: black;
        cursor: pointer;
    }

    .week:hover {
        color: white;
        background-color: lightgray;
    }

    .week.selected, .week.selected:hover {
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
                <a href=# @click="scrollUp">
                    <img src="/static/img/date_up.png" width="16px" alt="">
                </a>
                <div class="months full-fill">
                    <div v-for="(month, index) in months" :key="index" :class="['month', index === selectedMonth ? 'selected' : '' ]" @click="selectedMonth = index">{{ month }}</div>
                </div>
                <a href=# @click="scrollDown">
                    <img src="/static/img/date_down.png" width="16px" alt="">
                </a>
            </div>
            <div class="weeks flex-fill">
                <div 
                    v-for="(date, index) in weeks[selectedMonth]" :key="index" 
                    :class="['week', date - beginDate == 0 ? 'selected' : '']" 
                    @click="$emit('change', date);">
                    {{ weekIndex(date) }}&nbsp;&nbsp;{{ formatDate(date) }}
                </div>
            </div>
        </div>
    </div>
</template>
<script>
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
            let m = new Date(n.getTime() + 6 * 24 * 60 * 60 * 1000);
            return this.months[n.getMonth()] + ' ' + n.getDate().pad(2) + ' - ' + this.months[m.getMonth()] + ' ' + m.getDate().pad(2);
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
        }
    },
    mounted() {
        let firstDay = "21 Jan 2019 GMT+0800";
        var timestamp = Date.parse(firstDay);
        var date = new Date(timestamp);
        // console.log(date);

        var month = 0, weeks = [];
        while (date.getFullYear() === 2019) {
            if (date.getMonth() != month) {
                this.weeks.push(weeks);
                month = date.getMonth();
                weeks = [date];
            } else {
                weeks.push(date);
            }

            timestamp = timestamp + 7 * 24 * 60 * 60 * 1000;
            date = new Date(timestamp);
        }
        this.weeks.push(weeks);
    },
}
</script>
