<style>
    .vue-footer {
        background-color: #3C3F44;
        margin-top: 30px;
        padding-top: 40px;
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
        margin-bottom: 30px;
    }

    .vue-footer .fa {
        font-size: 24px;
        text-align: center;
    }

    .vue-footer .footer-icon {
        display: inline-block;
        border-radius: 50%;
        height: 50px; width: 50px;
        border: 1px solid #88929b;
        padding-top: 12px;
    }

</style>
<template>
    <div class=vue-footer>
        <div class=container>
            <div class="justify-content-center text-center row">
                <div class="col-3 col-md-1">
                    
                    <!-- FACEBOOK -->

                    <a target=_blank href=https://www.facebook.com/nebulasproject>
                        <div class="footer-icon mb-2">
                            <span class="fa fa-facebook-f" aria-hidden=true></span>                      
                        </div>
                        <div>
                            Facebook
                        </div>
                    </a>

                </div>

                <div class="col-3 col-md-1">
                    
                    <!-- SLACK -->

                    <a target=_blank href=https://nebulasio.herokuapp.com>
                        <div class="footer-icon mb-2">
                            <span class="fa fa-slack" aria-hidden=true></span>                      
                        </div>
                        <div>
                            Slack
                        </div>
                    </a>

                </div>

                <div class="col-3 col-md-1">
                    
                    <!-- TWITTER -->

                    <a target=_blank href=https://twitter.com/nebulasio>
                        <div class="footer-icon mb-2">
                            <span class="fa fa-twitter" aria-hidden=true></span>                      
                        </div>
                        <div>
                            Twitter
                        </div>
                    </a>
                    
                </div>

                <div class="col-3 col-md-1">
                    
                    <!-- GITHUB -->

                    <a target=_blank href=https://github.com/nebulasio>
                        <div class="footer-icon mb-2">
                            <span class="fa fa-github" aria-hidden=true></span>                      
                        </div>
                        <div>
                            Github
                        </div>
                    </a>

                </div>
            </div>
            <div class="justify-content-center row text-center mt-4">
                <div class="col-10">
                    <a target=_blank href=https://nebulas.io>Nebulas.io</a> -
                    <a target=_blank href=https://github.com/nebulasio/explorer/issues>Issue</a> - 
                    <a target=_blank href=https://github.com/nebulasio/explorer/commit/cf54099633db072aef15cec338a0ddaf21bb182a>Commit: cf54099</a>
                </div>
             </div>

        </div>
    </div>
</template>