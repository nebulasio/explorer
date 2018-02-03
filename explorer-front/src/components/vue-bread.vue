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
</style>
<template>
    <div class=vue-bread>
        <div class=container>
            <div class="align-items-center row">
                <h4 name=title class=col>{{ title }}</h4>
                <nav class=col-auto aria-label=breadcrumb>
                    <ol class=breadcrumb>
                        <li class=breadcrumb-item v-bind:class="{ active: i == arr.length - 1 }" v-for="(o, i) in arr">
                            <template v-if="i == arr.length - 1">{{ o.text }}</template>
                            <router-link v-else v-bind:to=o.to>{{ o.text }}</router-link>
                        </li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
</template>
<script>
    module.exports = {
        props: ["arr", "title"]
    };
</script>