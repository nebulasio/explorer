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

    .vue-bread a {
        font-size: 1.4rem;
        margin-left: auto;
    }

    @media (max-width: 767.98px) {
        .vue-bread .bread-title {
            font-size: 24px;
        }

        .vue-bread .bread-subtitle {
            font-size: 14px;
        }

        .vue-bread a {
            font-size: 1rem;
            margin-left: initial;
            padding: 0 15px;
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
                <a v-if="nextdao" href="https://nextdao.io" target="_blank" >nextdao.io ></a>
            </div>
        </div>
    </div>
</template>
<script>
    module.exports = {
        components: {
            "vue-blockies": require("@/components/vue-blockies").default
        },
        props: ["title", "subtitle", "subtitlemonospaced", "blockies", "nextdao"],
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
                $('.vue-bread .bread-title').css('font-size', '20px');
                $('.vue-bread .bread-subtitle').css('font-size', '12px');
            }
        }
    };
</script>
