<style>
	.vue-header .container {
		padding-top: 5px;
		padding-bottom: 5px;
	}

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
		margin-left: 20px;
		padding: 6px 0px 6px 10px;
		width: 350px;
		background-color: white;
	}

	.vue-header .form-inline img {
		margin-right: 6px;
	}

	.vue-header .form-inline input {
		outline: none;
		border: none;
		flex: 1;
	}

	.vue-header input::placeholder {
		font-size: 13px;
		line-height: 20px;
	}

	.vue-header .navbar-nav .nav-item:nth-child(2) a img {
		margin-top: -5px;
	}

	.vue-header .navbar-nav .nav-item:nth-child(3) a img {
		margin-top: -1.5px;
	}

	@media (min-width: 992px) {

		.vue-header .navbar-nav>li>a {
			border-bottom: 2px solid transparent;
		}

		/* .vue-header .navbar-nav>li>a:hover, */
		.vue-header .navbar-nav>li.active>a,
		.vue-header .navbar-nav>li.show>a {
			border-bottom-color: white;
		}

		.vue-header .navbar-nav a:hover img {
			opacity: 0.6;
		}

		.vue-header.navbar.navbar-default .navbar-nav>li>a {
			background-color: initial;
		}

		.vue-header .dropdown-menu {
			margin-left: 30px;
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

	@media (max-width: 767.98px) {
		input {
			font-size: 16px;
		}
	}

</style>

<template>
	<nav class="bg-black navbar navbar-expand-lg navbar-dark vue-header">
		<div class=container>
			<div>
				<router-link v-bind:to="fragApi + '/'" class=navbar-brand>
					<img src="/static/img/logo_beta.png?v=20190117" width=210 alt="">
				</router-link>
			</div>

			<button class="navbar-toggler headerlocalizable" localize="aria-label" type=button data-toggle=collapse data-target=#navbarSupportedContent aria-controls=navbarSupportedContent aria-expanded=false aria-label="Toggle navigation" id="headerToggleNavigation">
				<span class=navbar-toggler-icon></span>
			</button>
			<div class="collapse navbar-collapse mr-28" id=navbarSupportedContent>
				<form class=form-inline v-on:submit.prevent=onSubmit>
					<img src=/static/img/icon_search.png width=16 alt="" />
					<input class="mr-sm-2 font-12 headerlocalizable" localize="placeholder" id="headerSearchTool" type=search placeholder="" v-model=search>
				</form>
				<ul class="navbar-nav ml-auto">
					<li class=nav-item v-bind:class="{ active: $route.meta.headerActive == 1 }">
						<router-link v-bind:to="fragApi + '/'" class=nav-link>
							<span>
								<span id="headerHomeTitle" class="headerlocalizable"></span>
								<span class=sr-only>
									<span id="headerHomesubtitle" class="headerlocalizable"></span>
								</span>
							</span>
						</router-link>
					</li>
					<!-- Menú Blockchain -->
					<li class="dropdown nav-item" v-bind:class="{ active: $route.meta.headerActive == 2 }" id="blockchain-menu">
						<a class="nav-link" href=# id=header-dropdown-blockchain role=button data-toggle=dropdown aria-haspopup=true aria-expanded=false>
							BLOCKCHAIN
							<img src=/static/img/icon_arrow_down.png width=12 alt="">
						</a>
						<div class=dropdown-menu aria-labelledby=header-dropdown-blockchain>
							<router-link class=dropdown-item v-bind:to="fragApi + '/txs'"><span id="headerTransactionsSubmenu" class="headerlocalizable"></span></router-link>
							<router-link class=dropdown-item v-bind:to="fragApi + '/txs/pending'"><span id="headerPendingTransactionsSubmenu" class="headerlocalizable"></span></router-link>
							<div class="dropdown-divider"></div>
							<router-link class=dropdown-item v-bind:to="fragApi + '/blocks'"><span id="headerBlocksSubmenu" class="headerlocalizable"></span></router-link>
							<div class="dropdown-divider"></div>
							<router-link class=dropdown-item v-bind:to="fragApi + '/accounts'"><span id="headerAccountsSubmenu" class="headerlocalizable"></span></router-link>
						</div>
					</li>
					<!-- // Menú Blockchain -->
					<!-- <li v-if="($route.params.api !== 'testnet' && $root.mainnetGotDipWinners) || ($route.params.api == 'testnet' && $root.testnetGotDipWinners)" class=nav-item v-bind:class="{ active: $route.meta.headerActive == 3 }">
						<router-link class=nav-link v-bind:to="fragApi + '/dip-leaderboard'"><span id="headerDipWinners" class="headerlocalizable"></span></router-link>
					</li> -->
					<li class="nav-item">
						<a class="nav-link" href=# role=button v-on:click.prevent=apiSwitch()>{{ MenuMisc }}
							<img src=/static/img/icon_switcher.png width=12 alt="">
						</a>
					</li>
					<!-- Selector de idiomas -->
					<li class="dropdown nav-item">
						<div id="lang-sel"></div>
					</li>
					<!-- // Selector de idiomas -->
				</ul>
			</div>
		</div>
	</nav>
</template>
<script>
	import { EventBus } from '../events.js';
	import { jsonStrings } from '../l10nstrings.js';
	var api = require("@/assets/api"),
	appConfig = require("@/assets/app-config");

	module.exports = {
		data() {
			return {
				apiPrefixes: null,
				fragApi: "",
				paramsApi: "",
				MenuMisc: "MAINNET",
				search: ""
			};
		},
		mounted() {
			if (typeof this.$selectedLanguage != 'undefined') {
				this.checkHeaderTranslations();
			}
			this.tempInterval = setInterval(() => {
				this.checkHeaderTranslations();
				//this.removeTempInterval();//Commented to let the page fully charge
			}, 1900);

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

			if (!!navigator.userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/)) {
				// avoid auto zoom under iOS Safari when font size is less than 16px
				$('.vue-header input').css('font-size', '16px');
			}
		},
		methods: {
			removeTempInterval() {
				clearInterval(this.tempInterval);
			},
			checkHeaderTranslations() {
				// Unique elements, identified by id attr
				var myLocalizableElements = document.getElementsByClassName("headerlocalizable");
				var totalElements = myLocalizableElements.length;
				var i;
				for (i = 0; i < totalElements; i++) {
					var elementId = myLocalizableElements[i].getAttribute("id");
					if (myLocalizableElements[i].getAttribute("localize")) {
						var elementAttribute = myLocalizableElements[i].getAttribute("localize");
						myLocalizableElements[i].setAttribute(elementAttribute, jsonStrings[this.$selectedLanguage][elementId]);
					}
					else {
						myLocalizableElements[i].innerText = jsonStrings[this.$selectedLanguage][elementId];
					}

				}
			},
			onSubmit() {
				this.$root.showModalLoading = true;
				api.getSearch(this.search.trim(), o => {
					this.$root.showModalLoading = false;
					this.search = "";

					if (o.type == "block")
						this.$router.push(this.fragApi + "/block/" + o.q);
					else if (o.type == "address")
						this.$router.push(this.fragApi + "/address/" + o.q);
					else if (o.type == "tx")
						this.$router.push(this.fragApi + "/tx/" + o.q);
					else if (o.type == "contract")
						this.$router.push(this.fragApi + "/token/" + o.q);
					else {
						this.$root.search = o.q;
						this.$router.push((this.$route.params.api ? "/" + this.$route.params.api : "") + "/nothing");
					}
				}, () => {
					this.$root.search = this.search;
					this.$root.showModalLoading = false;
					this.search = "";
					this.$router.push((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404");
				});
			},
			apiSwitch() {
				if (this.$route.params.api === 'testnet') {
					this.$router.replace("/");
				} else {
					this.$router.replace("/testnet");
				}
				location.reload();
			},
			atpAddress() {
				var api = this.$route.params.api ? this.$route.params.api : "mainnet";
				return appConfig.apiPrefixes[api].atp;
			},
			showATP() {
				// 搜索框进入 ATP 的临时方案！！！
				this.$router.push((this.$route.params.api ? "/" + this.$route.params.api : "") + "/token/" + this.atpAddress());
			}
		}
	};
</script>