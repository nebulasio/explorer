<style>
    .vue-bread {
        background-color: #f7f7f7;
        border-bottom: 1px solid #eee;
        overflow: auto;
        padding: 10px 0;
    }

    .vue-bread .row {
        align-items: center;
    }

    /* .vue-bread .title {
        display: inline;
        vertical-align: middle;
    }

    .vue-bread .subtitle {
        display: inline;
        vertical-align: middle;
    } */

</style>
<template>
    <div class=vue-bread>
        <div class=container>
            <div class="row">
                <div class="col-auto title font-size-40-bold font-color-000000">{{ title }}</div>
                <div class="col-auto subtitle font-size-16-bold font-color-000000">{{ subtitle }}</div>
            </div>
        </div>
    </div>
</template>
<script>
    module.exports = {
        data() {
            return {
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : ""
            };
        },
        props: ["title", "subtitle"]
    };
</script>
