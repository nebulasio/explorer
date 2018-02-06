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
    .vue-footer .footer_link-list_banner a{
        color: #fff;
    }
</style>
<template>
    <div class="vue-footer">
        <div class="container">
            <div class="row">

                <div class="footer_right col-md-12 ">
                    <div class="footer_banner_tab ">
                        <h4>Links</h4>
                    </div>
                    <div class="footer_link-list ">
                        <div class="footer_link-list_banner ">
                            <a href="https://nebulas.io " target=view_blank > Nebulas.io </a>
                        </div>
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="footer_link-list_banner col-md-3 ">
                                <a href="https://github.com/nebulasio" target=view_blank >github</a>
                            </div>
                            <div class="footer_link-list_banner col-md-3 ">
                                <a href="https://testnet.nebulas.io" target=view_blank >testnet</a>
                            </div>
                            <div class="col-md-3"></div>
                        </div>
                        <div class="footer_link-list_banner ">
                            <a href="#">mainnet</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>