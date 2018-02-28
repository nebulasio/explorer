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
        height: 60px;
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
                <!-- <div class="footer_left col-md-6 mt50">
                    <a href=https://github.com/nebulasio/explorer/commits/3a6cfadd6d75f9159a98c72dfaa26d413ee914ce target=_blank>Commit : 3a6cfad</a>
                    <br>
                    <a href=https://github.com/nebulasio/explorer/issues target=_blank>issue</a>
                </div> -->
                <div class="footer_right col-md-12">
                    <div class=footer_banner_tab>
                        <h4>about</h4>
                    </div>
                    <div class="footer_link-list row">
                        <div class="footer_link-list_banner col-md-2">
                            <a href=https://nebulas.io target=_blank>Nebulas.io</a>
                        </div>
                        <div class="col-md-2 footer_link-list_banner">
                            <a href="https://github.com/nebulasio" target=_blank>Github.com/nebulasio</a>
                        </div>
                        <div class="col-md-2 footer_link-list_banner">
                            <a href=https://nebulasio.herokuapp.com target=_blank>#Slack</a>
                        </div>
                        <div class="footer_link-list_banner col-md-2">
                            <a href="https://www.facebook.com/nebulasproject" target=_blank>Facebook</a>
                        </div>
                        <div class="footer_link-list_banner col-md-2">
                            <a href="https://twitter.com/nebulasio" target=_blank>Twitter</a>
                        </div>
                        <div class="footer_link-list_banner col-md-2">
                            <a href="https://github.com/nebulasio/explorer/issues" target=_blank>Issue</a>
                        </div>
                    </div>
                    <div class="link">
                        <a href=https://github.com/nebulasio/explorer/commits/2125072eb48e954c73fd84ae34a263ddf37f117d target=_blank>Commit : 2125072</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>