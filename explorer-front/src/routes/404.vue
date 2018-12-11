<style>
    .vue-404 {
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

    .vue-404 img {
        margin-top: 50px;
    }

    .vue-404 .ohno {
        color: black;
        font-size: 38px;
        font-weight: bold;
    }

    .vue-404 .msg {
        color: #555555;
        font-size: 18px;
        font-weight: bold;
    }

    .vue-404 a {
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
    <div class="vue-404 fullfill">
        <div class="container vertical-center">
            <div class="msg">Sorry...The network is currently unavailable. Please try again later.</div>
            <img src="/static/img/404.png" alt="" width="308px">
            <router-link :to='this.$route.params.api ? "/" + this.$route.params.api : ""'>Back home</router-link>
        </div>
    </div>
</template>