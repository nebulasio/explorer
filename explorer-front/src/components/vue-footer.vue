<style>
    .vue-footer {
        background-color: #272727;
        margin-top: 30px;
    }

    .vue-footer .container {
        background-color: transparent;
    }

    .vue-footer .footer_left {
        height: 300px;
    }

    .vue-footer .footer_left_up {
        height: 100px;
        text-align: center;
        margin-top: 6%;
    }

    .vue-footer .footer_left_down {
        color: #fff;
    }

    .vue-footer .footer_middle {
        height: 300px;
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
</style>
<template>
    <div class="vue-footer">
        <div class="container">
            <div class="row">
                <div class="footer_left col-md-3 ">
                    <div class="footer_left_up ">
                        <img src="/static/img/logo.png ">
                    </div>
                    <div class="footer_left_down ">
                        <p>Nebulas is a Block Explorer and Analytics Platform for Ethereum, a decentralized smart contracts platform.</p>
                    </div>
                </div>

                <div class="footer_right col-md-9 ">
                    <div class="footer_banner_tab ">
                        <h4>Links</h4>
                    </div>
                    <div class="footer_link-list ">
                        <div class="footer_link-list_banner ">
                            <a href="https://nebulas.io "> Nebulas.io </a>
                        </div>
                        <div class="footer_link-list_banner ">
                            <a href="https://github.com/nebulasio">github</a>
                        </div>
                        <div class="footer_link-list_banner ">
                            <a href="https://testnet.nebulas.io/   ">testnet</a>
                        </div>
                        <div class="footer_link-list_banner ">
                            <a href="# ">mainnet</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>