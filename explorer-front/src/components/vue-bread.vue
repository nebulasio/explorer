<style>
    .vue-bread {
        background-color: #f7f7f7;
        overflow: auto;
        padding: 10px 0;
    }

    .vue-bread .row {
        align-items: center;
    }

    .vue-bread .bread-subtitle {
        max-width: 100%;
        overflow-wrap: break-word;
    }

    @media (max-width: 767.98px) {
        .vue-bread .bread-title {
            font-size: 24px;
        }

        .vue-bread .bread-subtitle {
            font-size: 14px;
        }
    }

</style>
<template>
    <div class=vue-bread>
        <div class=container>
            <div class="row align-items-center">
                <div class="col-auto bread-title font-40 font-bold font-color-000000">{{ title }}</div>
                <div :class="['col-auto bread-subtitle font-16 font-bold font-color-000000 align-baseline', subtitlemonospaced ? 'monospace' : '']" :style="blockies ? subtitleStyle : null">
                    <vue-blockies class="d-inline mr-0 align-text-bottom" v-if="blockies" v-bind:address='blockies'></vue-blockies>
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
        props: ["title", "subtitle", "subtitlemonospaced", "blockies"],
        data() {
            return {
                fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
                subtitleStyle: { 
                    'white-space': 'nowrap', 
                    'overflow': 'hidden', 
                    'text-overflow': 'ellipsis'
                }
            };
        },
        mounted() {
            if (window.innerWidth == 320) {
                require('jquery')('.vue-bread .bread-title').css('font-size', '20px');
                require('jquery')('.vue-bread .bread-subtitle').css('font-size', '12px');
            }
        }
    };
</script>
