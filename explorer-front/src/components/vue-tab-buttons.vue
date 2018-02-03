<style>
    /*  <tag
            v-bind:arr
            v-bind:tab.sync
        ></tag>
        */
</style>
<template>
    <div class=vue-tab-buttons>
        <ul class="nav nav-tabs">
            <li class=nav-item v-for="(o, i) in arr">
                <a class=nav-link href=# v-bind:class="{ active: tab == i + 1 }" v-on:click.prevent='$emit("update:tab", i + 1)'>{{ o }}</a>
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