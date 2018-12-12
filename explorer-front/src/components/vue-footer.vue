<style>
    .vue-footer {
        background: rgba(255, 255, 255, 1);
        box-shadow: 0px 1px 0px 0px rgba(221, 221, 221, 1);
        /* margin-top: 30px; */
        font-size: 16px;
        color: black;
    }

    .vue-footer a {
        color: black;
        vertical-align: bottom;
    }

    @media (min-width: 768px) { 
        .vue-footer .row {
            align-items: center;
            height: 82px;
        }

        .vue-footer .row>div:nth-of-type(1) {
            text-align: left;
        }

        .vue-footer .row>div:nth-of-type(2) {
            text-align: center;
        }

        .vue-footer .row>div:nth-of-type(3) {
            text-align: right;
        }
    }

    @media (max-width: 767.98px) { 
        .vue-footer .row {
            align-items: center;
            height: 182px;
        }

        .vue-footer .row>div {
            text-align: center;
        }
    }

</style>
<template>
    <div class=vue-footer>
        <div class=container>
            <div class="row">
                <div class="col-xs-12 col-md-4">
                    <a target=_blank href=https://nebulas.io>
                        <img style="height: 34px; width: 156px;display: inline; vertical-align: middle;" src="../../static/img/img_footer_logo.png"/>
                    </a>
                </div>

                <div class="col-xs-12 col-md-4">
                    <div>Copyright © 2017-2018 Nebulas.io</div>
                </div>

                <div class="col-xs-12 col-md-4">
                    <div>
                        <a target=_blank href=https://github.com/nebulasio/explorer/issues>
                            Submit a issue
                        </a>
                        <a target=_blank href=https://github.com/nebulasio/explorer/issues>
                            <img class="icon24" src="../../static/img/ic_github.png"/>
                        </a>
                        <a target=_blank href=https://twitter.com/nebulasio>
                            <img class="icon24" src="../../static/img/ic_twitter.png"/>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
