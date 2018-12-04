<style>
    .vue-header a {
        color: inherit;
    }

    .vue-header .dev-version {
        color: #2a88ff;
        left: -15px;
        padding: 5px;
        position: relative;
        top: 10px;
    }

    .vue-header .btn-group-toggle {
        margin-left: 20px;
    }

    .vue-header .btn-group-toggle label {
        padding: 0 10px;
    }

    .vue-header .visibility-hidden {
        visibility: hidden;
    }

    .vue-header form {
        border: none;
        margin-left: 80px;
        padding: 6px 0px 6px 10px;
        width: 360px;
        background-color: white;
    }

    .vue-header .form-inline img {
        margin-right: 6px;
    }

    .vue-header .form-inline input {
        border: none;
        flex: 1;
    }

    .vue-header .dropdown-menu {
        font-size: 14px;
        border-radius: 0px;
    }

    @media (min-width: 992px) {
        .vue-header .navbar-nav>li>a {
            border-bottom: 2px solid transparent;
        }

        .vue-header .navbar-nav>li>a:hover,
        .vue-header .navbar-nav>li.active>a,
        .vue-header .navbar-nav>li.show>a {
            border-bottom-color: white;
        }

        .vue-header.navbar.navbar-default .navbar-nav>li>a {
            background-color: initial;
        }
    }

    @media (max-width: 991.98px) {
        .vue-header .form-inline {
            align-items: center;
            display: flex;
            width: 100%;
            margin: 10px 0px;
        }
    }
</style>
<template>
    <nav class="bg-black navbar navbar-expand-lg navbar-dark vue-header">
        <div class=container>
            <div>
                <router-link v-bind:to="fragApi + '/'" class=navbar-brand>
                    <img src=/static/img/explorer-logo.png width=150 alt="">
                </router-link>
                <!-- <a href=https://github.com/nebulasio/explorer/issues target=_blank class=dev-version data-toggle=tooltip data-placement=bottom data-html=true title='
<span class="fa fa-flask" aria-hidden=true></span>
<span class=c777>This website is under heavy construction</span><br>
<div>Feel free to submit issues by clicking this link 👍</div>
                '>alpha</a> -->
            </div>

            <button class=navbar-toggler type=button data-toggle=collapse data-target=#navbarSupportedContent aria-controls=navbarSupportedContent aria-expanded=false aria-label="Toggle navigation">
                <span class=navbar-toggler-icon></span>
            </button>
            <div class="collapse navbar-collapse mr-28" id=navbarSupportedContent>
                <form class=form-inline v-on:submit.prevent=onSubmit>
                    <img src=/static/img/icon_search.png width=16 alt="">
                    <input class="form--control mr-sm-2" v-model=search type=search placeholder="Search by Address / Txhash / Block / Token">
                    <!-- <button class="btn btn-outline-success" type=submit>GO</button> -->
                </form>
                <ul class="navbar-nav ml-auto">
                    <li class=nav-item v-bind:class="{ active: $route.meta.headerActive == 1 }">
                        <router-link v-bind:to="fragApi + '/'" class=nav-link>HOME
                            <span class=sr-only>(current)</span>
                        </router-link>
                    </li>
                    <li class="dropdown nav-item" v-bind:class="{ active: $route.meta.headerActive == 2 }">
                        <a class="nav-link dropdown-toggle" href=# id=header-dropdown-blockchain role=button data-toggle=dropdown aria-haspopup=true aria-expanded=false>BLOCKCHAIN</a>
                        <div class=dropdown-menu aria-labelledby=header-dropdown-blockchain>
                            <router-link class=dropdown-item v-bind:to="fragApi + '/txs'">Transactions</router-link>
                            <router-link class=dropdown-item v-bind:to="fragApi + '/txs/pending'">Pending Transactions</router-link>
                            <div class="dropdown-divider"></div>
                            <router-link class=dropdown-item v-bind:to="fragApi + '/blocks'">Blocks</router-link>
                            <div class="dropdown-divider"></div>
                            <router-link class=dropdown-item v-bind:to="fragApi + '/accounts'">Accounts</router-link>
                        </div>
                    </li>
                    <!-- <li class=nav-item v-bind:class="{ active: $route.meta.headerActive == 3 }">
                        <router-link class=nav-link v-bind:to="fragApi + '/accounts'">ACCOUNT</router-link>
                    </li> -->
                    <li class="dropdown nav-item">
                        <a class="nav-link dropdown-toggle" href=# id=header-dropdown-misc role=button data-toggle=dropdown aria-haspopup=true aria-expanded=false>{{ MenuMisc }}</a>
                        <div class=dropdown-menu aria-labelledby=header-dropdown-misc>
                            <a v-for="(o, i) in apiPrefixes" :key="i" class="nav-link text-dark" href=# v-on:click.prevent=apiSwitch(i)>
                                <span class="fa fa-check" v-bind:class="{ 'visibility-hidden': paramsApi != i }" aria-hidden=true></span>
                                {{ o.name }}
                            </a>
                        </div>
                    </li>
                </ul>
                
            </div>
        </div>
    </nav>
</template>
<script>
    var api = require("@/assets/api"),
        appConfig = require("@/assets/app-config");

    module.exports = {
        data() {
            return {
                apiPrefixes: null,
                fragApi: "",
                paramsApi: "",
                search: "",
                MenuMisc:"MISC"
            };
        },
        methods: {
            apiSwitch(s) {
                var api = this.$route.params.api || "";
                if (api != s) {
                    this.$router.replace("/" + s);
                    location.reload();
                }
            },
            onSubmit() {
                // if (this.search.trim().toLowerCase() == 'atp') {
                //     this.showATP();
                //     return;
                // }
                this.$root.showModalLoading = true;
                api.getSearch(this.search, o => {
                    this.$root.showModalLoading = false;
                    this.search = "";

                    if (o.type == "block")
                        this.$router.push(this.fragApi + "/block/" + o.q);
                    else if (o.type == "address")
                        this.$router.push(this.fragApi + "/address/" + o.q);
                    else if (o.type == "tx")
                        this.$router.push(this.fragApi + "/tx/" + o.q);
                    else if (o.type == "contract")
                        this.$router.push(this.fragApi + "/contract/" + o.q);
                    else {
                        this.$root.search = o.q;
                        this.$router.push((this.$route.params.api ? "/" + this.$route.params.api : "") + "/oops");
                    }
                }, () => {
                    this.$root.search = this.search;
                    this.$root.showModalLoading = false;
                    this.search = "";
                    this.$router.push((this.$route.params.api ? "/" + this.$route.params.api : "") + "/oops");
                });
            },
            atpAddress() {
                var api = this.$route.params.api ? this.$route.params.api : "mainnet";
                return appConfig.apiPrefixes[api].atp;
            },
            showATP() {
                // 搜索框进入 ATP 的临时方案！！！
                this.$router.push((this.$route.params.api ? "/" + this.$route.params.api : "") + "/contract/" + this.atpAddress());
            }
        },
        mounted() {
            var paramsApi = this.$route.params.api, apiPrefixes = {}, i, first = true;

            for (i in appConfig.apiPrefixes)
                if (first) {
                    apiPrefixes[""] = appConfig.apiPrefixes[i];
                    first = false;
                } else
                    apiPrefixes[i] = appConfig.apiPrefixes[i];

            if (!(paramsApi in apiPrefixes))
                paramsApi = "";

            paramsApi == 'testnet' ? this.MenuMisc = 'TESTNET' : this.MenuMisc = 'MAINNET';
            this.apiPrefixes = apiPrefixes;
            this.fragApi = paramsApi ? "/" + paramsApi : "";
            this.paramsApi = paramsApi;

            require("jquery")("[data-toggle=tooltip]").tooltip();
        }
    };
</script>
