<style>
    .vue-oops .image {
        height: 400px;
        background: url(/static/img/oops.jpg) center no-repeat;
    }
    .vue-oops pre{
        font-size: 44px;
    }

</style>
<template>
    <div class="container vue-oops">
        <div class=text-center>
            <h1>Oops!</h1>
            <h3>The search string you entered was:</h3>
            <pre>{{ $root.search }}</pre>
            <h4>Sorry! This is an invalid search string.</h4>
        </div>
        <div class=image></div>
    </div>
</template>