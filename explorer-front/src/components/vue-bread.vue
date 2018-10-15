<style>
    .vue-bread {
        background-color: #f8f8f8;
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

    .vue-bread ol> :last-child {
        color: #3498db;
    }

    .vue-bread .title {
        display: inline;
        font-size: 24px;
    }

    .vue-bread .subtitle {
        display: inline;
        font-size: 20px;
    }

</style>
<template>
    <div class=vue-bread>
        <div class=container>
            <div class="align-items-center row">
                <div class="col">
                    <div class=title>{{ title }}</div>
                    <div class=subtitle>{{ subtitle }}</div>
                </div>
                <nav class=col-auto aria-label=breadcrumb>
                    <ol class=breadcrumb>
                        <li class=breadcrumb-item v-bind:class="{ active: i == arr.length - 1 }" v-for="(o, i) in arr" :key="i">
                            <template v-if="i == arr.length - 1">{{ o.text }}</template>
                            <router-link v-else v-bind:to='fragApi + o.to'>{{ o.text }}</router-link>
                        </li>
                    </ol>
                </nav>
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
        props: ["arr", "title", "subtitle"]
    };
</script>