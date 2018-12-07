<style>
    .vue-404 {
        position: relative;
    }

    .vertical-center {
        margin: 0;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }

    .vue-404>div {
        text-align: center;
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
</style>
<template>
    <div class="vue-404 fullfill">
        <div class="vertical-center">
            <img src="/static/img/404.png" alt="" width="360px">
            <div class="ohno">Oh no!</div>
            <div class="msg">You’re either misspelling the URL <br> or requesting a page that’s no longer here.</div>
        </div>
    </div>
</template>