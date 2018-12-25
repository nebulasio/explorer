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

    .vue-bread .subtitle {
        max-width: 100%;
        overflow-wrap: break-word;
    }

</style>
<template>
    <div class=vue-bread>
        <div class=container>
            <div class="row align-items-center">
                <div class="col-auto title font-size-40-bold font-color-000000">{{ title }}</div>
                <div class="col-auto subtitle font-size-16-bold font-color-000000 align-baseline">
                    <vue-blockies class="d-inline mr-1 align-text-bottom" v-if="blockies" v-bind:address='blockies'></vue-blockies>
                    {{ subtitle }}
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    module.exports = {
        components: {
            "vue-blockies": require("@/components/vue-blockies").default
        },
        data() {
            return {
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : ""
            };
        },
        props: ["title", "subtitle", "blockies"]
    };
</script>
