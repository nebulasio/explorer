<style>
    .vue-nothing {
        position: relative;
        text-align: center;
    }

    .vertical-center {
        margin: 0 auto;
        margin-top: 80px;
        /* position: absolute; */
        /* top: 50%; */
        /* left: 50%;
        transform: translateX(-50%); */
    }

    .vue-nothing img {
        margin-top: 50px;
    }

    .vue-nothing .ohno {
        color: black;
        font-size: 38px;
        font-weight: bold;
    }

    .vue-nothing .msg {
        color: #555555;
        font-size: 18px;
        font-weight: bold;
    }

    .vue-nothing a {
        display: block;
        color: black;
        text-decoration: none;
        font-size: 16px;
        width: 160px;
        border:1px solid rgba(0,0,0,1);
        padding: 7px 7px;
        margin: 46px auto;
    }

</style>
<template>
    <div class="vue-nothing fullfill">
        <div class="container vertical-center">
            <div class="msg">Sorry... No search results found.</div>
            <img src="/static/img/nothing.png" alt="" width="282px">
            <router-link :to='$route.params.api ? "/" + $route.params.api : ""'>Back home</router-link>
        </div>
    </div>
</template>