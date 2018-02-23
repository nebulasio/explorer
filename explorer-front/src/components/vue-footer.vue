<style>
    .vue-footer {
        background-color: #272727;
        margin-top: 30px;
    }

    .vue-footer .container {
        background-color: transparent;
    }

    .vue-footer .footer_banner_tab {
        border-bottom: 1px dashed #fff;
        color: #fff;
        height: 40px;
        margin-top: 24px;
    }

    .vue-footer .footer_list {
        height: 200px;
        margin-top: 30px;
    }

    .vue-footer .footer_list_banner {
        color: #fff;
        height: 30px;
    }

    .vue-footer .footer_right {
        height: 300px;
        text-align: center;
    }

    .vue-footer .footer_link-list {
        height: 200px;
        margin-top: 20px;
    }

    .vue-footer .footer_link-list_banner {
        height: 30px;
    }

    .vue-footer .footer_link-list_banner a {
        color: #fff;
    }

    .vue-footer .footer_left a {
        color: #fff;
    }
</style>
<template>
    <div class=vue-footer>
        <div class=container>
            <div class=row>
                <div class="footer_left col-md-6 mt50">
                    <a href=https://github.com/nebulasio/explorer/commits/2bed941ff228b722a1136aca9f41d92bdcf19519 target=_blank>Commit : 2bed941</a>
                    <br>
                    <a href=https://github.com/nebulasio/explorer/issues target=_blank>issue</a>
                </div>
                <div class="footer_right col-md-6">
                    <div class=footer_banner_tab>
                        <h4>Links</h4>
                    </div>
                    <div class=footer_link-list>
                        <div class=footer_link-list_banner>
                            <a href=https://nebulas.io target=_blank>Nebulas.io</a>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-md-3 footer_link-list_banner">
                                <a href="https://github.com/nebulasio" target=_blank>github</a>
                            </div>
                            <div class="col-md-3 footer_link-list_banner">
                                <a href=https://testnet.nebulas.io target=_blank>testnet</a>
                            </div>
                        </div>
                        <div class=footer_link-list_banner>
                            <a href=#>mainnet</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>