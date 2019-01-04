<style>
    /*  <tag
            v-bind:arr
            v-bind:tab.sync
        ></tag>
        */
    .vue-tab-buttons .nav-link {
        margin-right: 5px;
    }

    .vue-tab-buttons .nav {
        border: none;
    }

    .vue-tab-buttons .nav-tabs .nav-link {
        background: #eeeeee;
        border: none;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        margin-right: 0;
        font-size: 16px;
        font-weight: 600;
        color: rgba(0, 0, 0, 1);
        line-height: 20px;
    }

    .vue-tab-buttons .nav-tabs .nav-item .active {
        background-color: black;
        border: none;
        font-size: 16px;
        font-weight: 600;
        color: rgba(255, 255, 255, 1);
        line-height: 20px;
    }
</style>
<template>
    <div class=vue-tab-buttons>
        <ul class="nav nav-tabs">
            <li class=nav-item v-for="(o, i) in arr" :key="i">
                <a class=nav-link href=# v-bind:class="{ active: tab == i + 1 }"
                   v-on:click.prevent='$emit("update:tab", i + 1)'>{{ o }}</a>
            </li>
        </ul>
    </div>
</template>
<script>
    module.exports = {
        mounted() {
            this.$emit("update:tab", this.arr.length ? 1 : 0);
        },
        props: ["arr", "tab"]
    };
</script>
