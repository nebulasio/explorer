<style>
    .vue-footer {
        height: 82px;
        background: rgba(255, 255, 255, 1);
        box-shadow: 0px 1px 0px 0px rgba(221, 221, 221, 1);
        margin-top: 30px;
        overflow: auto;
    }

    .vue-footer a {
        color: inherit;
    }

    .vue-footer h4 {
        margin-bottom: 20px;
        padding: 20px 0 10px;
        text-align: center;
    }

    .vue-footer .container {
        height: 82px;
    }

    .vue-footer .fa {
        text-align: center;
        width: 14px;
    }

    .div-left {
        float: left;
        width: 33.33%;
        height: 100%;
        vertical-align: middle;
    }

    .div-middle {
        float: left;
        width: 33.33%;
        height: 100%;
        text-align: center;
        vertical-align: middle;
    }

    .div3 {
        float: left;
        width: 33.34%;
        vertical-align: middle;
    }
</style>
<template>
    <div class=vue-footer>
        <div class=container>
            <div class="div-left">
                <span style="height: 100%;display: inline-block;vertical-align: middle;"></span>
                <img style="height: 34px; width: 156px;display: inline; vertical-align: middle;"
                     src="../../static/img/img_footer_logo.png"/>
            </div>
            <div class="div-middle">
                <span class="font-size-16-normal font-color-000000" style="line-height:82px;">
                    Copyright &#169 2018 The Nebulas Team
                </span>
            </div>
            <div class="div3" style="text-align: right">
                <span class="font-color-000000 font-size-16-normal" style="line-height: 82px;">
                    Submit a issue
                </span>
                <a target=_blank href=https://github.com/nebulasio/explorer/issues>
                    <img class="icon24" style="vertical-align: middle;"
                         src="../../static/img/ic_github.png"/>
                </a>
                <a target=_blank href=https://twitter.com/nebulasio>
                    <img class="icon24" style="vertical-align: middle;"
                         src="../../static/img/ic_twitter.png"/>
                </a>
            </div>
        </div>
    </div>
</template>
