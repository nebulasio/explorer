<style>
    .vue-404 .img {
        height: 600px;
        background: url(/static/img/404.jpg) center no-repeat;
    }

    .vue-404 .url {
        font-size: 31px;
        line-height: 42px;
        text-align: center;
    }
</style>
<template>
    <div class=vue-404>
        <div class=url>
            <template v-if=$root.urlBefore404>
                Such page was not found:
                <br>{{ $root.urlBefore404 }}
            </template>
            <template v-else>
                <br>
                <br>
            </template>
        </div>
        <div class=img></div>
    </div>
</template>