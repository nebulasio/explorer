<style>
    .vue-oops .image {
        height: 400px;
        background: url(/static/img/oops.jpg) center no-repeat;
    }

    .vue-oops .keyword {
        font-size: 44px;
        overflow-wrap: break-word;
    }
</style>
<template>
    <div class="container vue-oops">
        <div class=text-center>
            <h1>{{$lang.messages.oops_header}}</h1>
            <h3>{{$lang.messages.oops_search_string_entered}}</h3>
            <div class=keyword>{{ $root.search }}</div>
            <h4>{{$lang.messages.oops_search_string_invalid}}</h4>
        </div>
        <div class=image></div>
    </div>
</template>