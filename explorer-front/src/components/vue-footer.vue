<style>
    .vue-footer {
        background-color: #222;
        margin-top: 30px;
        overflow: auto;
    }

    .vue-footer a {
        color: inherit;
    }

    .vue-footer h4 {
        border-bottom: 1px solid #777;
        margin-bottom: 20px;
        padding: 20px 0 10px;
        text-align: center;
    }

    .vue-footer .container {
        margin-bottom: 30px;
    }
</style>
<template>
    <div class=vue-footer>
        <div class=container>
            <h4>About</h4>
            <div class="justify-content-center row">
                <div class=col-1></div>
                <div class=col-2>
                    <div>
                        <a target=_blank href=https://nebulas.io>Nebulas.io</a>
                    </div>
                    <div>
                        <a target=_blank href=https://www.facebook.com/nebulasproject>Facebook</a>
                    </div>
                    <div>
                        <a target=_blank href=https://nebulasio.herokuapp.com>#Slack</a>
                    </div>
                    <div>
                        <a target=_blank href=https://twitter.com/nebulasio>Twitter</a>
                    </div>
                </div>
                <div class=col-1></div>
                <div class=col-2>
                    <div>
                        <a target=_blank href=https://github.com/nebulasio>Github.com/nebulasio</a>
                    </div>
                    <div>
                        <a target=_blank href=https://github.com/nebulasio/explorer/issues>Issue</a>
                    </div>
                    <div>Commit:
                        <a target=_blank href=https://github.com/nebulasio/explorer/commit/cf54099633db072aef15cec338a0ddaf21bb182a>cf54099</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>