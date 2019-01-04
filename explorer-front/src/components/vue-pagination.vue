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

    .vue-pagination {
        padding: 20px 0px;
    }

    .vue-pagination .current {
        color: inherit;
        position: relative;
    }

    .vue-pagination .current > span {
        margin-right: 4.5em;
    }

    .page_nav-item {
        width: 34px;
        height: 34px;
        text-align: center;
        vertical-align: middle;
    }

    .page_nav-item span {
        height: 100%;
        font-size: 15px;
        color: #555555;
        vertical-align: middle;
        display: inline-block;
        text-decoration: none;
    }

    .page_nav-item img {
        width: 16px;
        height: 16px;
        vertical-align: middle;
        margin-top: 9px;
    }

    .page_nav-item input {
        width: 36px;
        height: 22px;
        text-align: center;
        color: #000000;
        vertical-align: middle;
        display: inline-block;
        outline: none;
    }
    
    .page_nav-item input:focus {
        box-shadow: none;
        border: 1px solid black;
    }

    .pagination a {
        text-decoration: none;
        text-decoration-color: transparent;
    }

    .pagination .page_nav-item .highlight {
        display: none;
    }

    .pagination .enable-item:hover .origin {
        display: none;
    }

    .pagination .enable-item:hover .highlight {
        display: inline;
    }

</style>
<template>
    <nav class=vue-pagination aria-label="Page navigation">
        <ul class=pagination v-bind:class='{ "justify-content-end": right }'>

            <li>
                <a href=# aria-label=First v-on:click.prevent="$emit(current != 1 ? 'first' : '')">
                    <div :class='{"page_nav-item": true, "enable-item": current != 1}'>
                        <img class="origin" :src="current != 1 ? '../../static/img/ic_pagination_first.png' : '../../static/img/ic_pagination_first_disable.png'"/>
                        <img class="highlight" src="../../static/img/ic_pagination_first_highlight.png">
                    </div>
                </a>
            </li>

            <li>
                <a href=# aria-label=Previous v-on:click.prevent="$emit(current != 1 ? 'prev' : '')">
                    <div :class='{"page_nav-item": true, "enable-item": current != 1}'>
                        <img class="origin" :src="current != 1 ? '../../static/img/ic_pagination_previous.png' : '../../static/img/ic_pagination_previous_disable.png'"/>
                        <img class="highlight" src="../../static/img/ic_pagination_previous_highlight.png">
                    </div>
                </a>
            </li>
            <div class="page_nav-item" style="width: auto; margin-left: 16px; margin-right: 16px;">
                <span style="line-height: 34px;">Page</span>
                <input style="margin-left: 10px;margin-right: 10px;" v-bind:value=current
                       v-on:keyup.enter="$emit('to', $event.target.value)">
                <span style="line-height: 34px;">/{{ total }}</span>
            </div>

            <li>
                <a href=# aria-label=Next v-on:click.prevent="$emit(current != total ? 'next' : '')">
                    <div :class='{"page_nav-item": true, "enable-item": current != total}'>
                        <img class="origin" :src="current != total ? '../../static/img/ic_pagination_next.png' : '../../static/img/ic_pagination_next_disable.png'"/>
                        <img class="highlight" src="../../static/img/ic_pagination_next_highlight.png">
                    </div>
                </a>
            </li>

            <li>
                <a href=# aria-label=Last v-on:click.prevent="$emit(current != total ? 'last' : '')">
                    <div :class='{"page_nav-item": true, "enable-item": current != total}'>
                        <img class="origin" :src="current != total ? '../../static/img/ic_pagination_last.png' : '../../static/img/ic_pagination_last_disable.png'"/>
                        <img class="highlight" src="../../static/img/ic_pagination_last_highlight.png">
                    </div>
                </a>
            </li>
        </ul>
    </nav>
</template>
<script>
    module.exports = {
        props: ["current", "right", "total"]
    };
</script>
