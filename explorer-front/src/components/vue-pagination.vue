<style>
    /*  <tag
            v-bind:current
            v-bind:right
            v-bind:total
            v-on:first
            v-on:prev
            v-on:next
            v-on:last
        ></tag>
        */

    .vue-pagination .current {
        color: inherit;
        position: relative;
    }

    .vue-pagination .current > span {
        margin-right: 4.5em;
    }

    .vue-pagination .current > input {
        left: 3.6em;
        position: absolute;
        top: .4em;
        width: 4em;
        text-align: center;
    }

    .nav-item {
        width: 34px;
        height: 34px;
        text-align: center;
        vertical-align: middle;
    }

    .nav-item span {
        height: 100%;
        font-size: 15px;
        color: #555555;
        vertical-align: middle;
        display: inline-block;
    }

    .nav-item img {
        width: 16px;
        height: 16px;
        vertical-align: middle;
    }

    .nav-item input {
        width: 36px;
        height: 22px;
        text-align: center;
        color: #000000;
        vertical-align: middle;
        display: inline-block;
    }

    .pagination a {
        text-decoration: none;
    }
</style>
<template>
    <nav class=vue-pagination aria-label="Page navigation">
        <ul class=pagination v-bind:class='{ "justify-content-end": right }'>

            <li>
                <a href=# aria-label=First v-on:click.prevent="$emit('first')">
                    <div class="nav-item">
                        <span></span>
                        <img src="../../static/img/ic_pagination_first.png"/>
                    </div>
                </a>
            </li>

            <li>
                <a href=# aria-label=Previous v-on:click.prevent="$emit('prev')">
                    <div class="nav-item">
                        <span></span>
                        <img src="../../static/img/ic_pagination_previous.png"/>
                    </div>
                </a>
            </li>
            <div class="nav-item" style="width: auto; margin-left: 16px; margin-right: 16px;">
                <span style="line-height: 34px;">Page</span>
                <input style="margin-left: 10px;margin-right: 10px;" v-bind:value=current
                       v-on:keyup.enter="$emit('to', $event.target.value)">
                <span style="line-height: 34px;">/{{ total }}</span>
            </div>

            <li>
                <a href=# aria-label=Next v-on:click.prevent="$emit('next')">
                    <div class="nav-item">
                        <span></span>
                        <img src="../../static/img/ic_pagination_next.png"/>
                    </div>
                </a>
            </li>

            <li>
                <a href=# aria-label=Last v-on:click.prevent="$emit('last')">
                    <div class="nav-item">
                        <span></span>
                        <img src="../../static/img/ic_pagination_last.png"/>
                    </div>
                </a>
            </li>


            <!--<li v-if="current > 1" class=page-item>-->
            <!--<a class=page-link href=# aria-label=First v-on:click.prevent="$emit('first')">-->
            <!--<span aria-hidden=true>&laquo;</span>-->
            <!--<span class=sr-only>First</span>-->
            <!--</a>-->
            <!--</li>-->
            <!--<li v-else class="disabled page-item">-->
            <!--<a class=page-link href=# tabindex=-1 aria-label=First>-->
            <!--<span aria-hidden=true>&laquo;</span>-->
            <!--<span class=sr-only>First</span>-->
            <!--</a>-->
            <!--</li>-->

            <!--<li v-if="current > 1" class=page-item>-->
            <!--<a class=page-link href=# aria-label=Previous v-on:click.prevent="$emit('prev')">-->
            <!--<span aria-hidden=true>&lt;</span>-->
            <!--<span class=sr-only>Previous</span>-->
            <!--</a>-->
            <!--</li>-->
            <!--<li v-else class="disabled page-item">-->
            <!--<a class=page-link href=# tabindex=-1 aria-label=Previous>-->
            <!--<span aria-hidden=true>&lt;</span>-->
            <!--<span class=sr-only>Previous</span>-->
            <!--</a>-->
            <!--</li>-->

            <!--<li class=page-item>-->
            <!--<div class="current page-link">-->
            <!--<span>Page</span>-->
            <!--<input v-bind:value=current v-on:keyup.enter="$emit('to', $event.target.value)">-->
            <!--of-->
            <!--<b>{{ total }}</b>-->
            <!--</div>-->
            <!--</li>-->

            <!--<li v-if="current == total" class="disabled page-item">-->
            <!--<a class=page-link href=# tabindex=-1 aria-label=Next>-->
            <!--<span aria-hidden=true>&gt;</span>-->
            <!--<span class=sr-only>Next</span>-->
            <!--</a>-->
            <!--</li>-->
            <!--<li v-else class=page-item>-->
            <!--<a class=page-link href=# aria-label=Next v-on:click.prevent="$emit('next')">-->
            <!--<span aria-hidden=true>&gt;</span>-->
            <!--<span class=sr-only>Next</span>-->
            <!--</a>-->
            <!--</li>-->

            <!--<li v-if="current == total" class="disabled page-item">-->
            <!--<a class=page-link href=# tabindex=-1 aria-label=Last>-->
            <!--<span aria-hidden=true>&raquo;</span>-->
            <!--<span class=sr-only>Last</span>-->
            <!--</a>-->
            <!--</li>-->
            <!--<li v-else class=page-item>-->
            <!--<a class=page-link href=# aria-label=Last v-on:click.prevent="$emit('last')">-->
            <!--<span aria-hidden=true>&raquo;</span>-->
            <!--<span class=sr-only>Last</span>-->
            <!--</a>-->
            <!--</li>-->
        </ul>
    </nav>
</template>
<script>
    module.exports = {
        props: ["current", "right", "total"]
    };
</script>
