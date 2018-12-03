<style>
    .vue-bread {
        background-color: #f7f7f7;
        border-bottom: 1px solid #eee;
        overflow: auto;
        padding: 10px 0;
    }

    .vue-bread a {
        color: inherit;
    }

    .vue-bread .breadcrumb {
        background-color: initial;
        margin: 0;
    }

    .vue-bread ol > :last-child {
        color: #3498db;
    }

    .vue-bread .title {
        display: inline;
        vertical-align: middle;
    }

    .vue-bread .subtitle {
        display: inline;
        vertical-align: middle;
    }

</style>
<template>
    <div class=vue-bread>
        <div class=container>
            <div class="title font-size-40-bold font-color-000000">{{ title }}</div>
            <div class="subtitle font-size-16-bold font-color-000000" style="margin-left: 20px;">{{ subtitle }}</div>
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
        props: ["arr", "title", "subtitle"]
    };
</script>
