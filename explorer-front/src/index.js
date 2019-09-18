import VueTranslate from 'vue-translate-plugin';

var api = require("@/assets/api"),
appConfig = require("@/assets/app-config");

var Vue = require("vue").default,
	VueRouter = require("vue-router").default,
	vApp = {},
	vAppConfig = require("@/assets/app-config"),
	vRouter = new VueRouter({ routes: require("@/assets/routes") }),
	gaPage = require('vue-analytics').page;

// Expose jQuery to the global object
const jQuery = require('jquery');
window.jQuery = window.$ = jQuery;

require("bootstrap");
require("bootstrap/dist/css/bootstrap.min.css");
require("font-awesome/css/font-awesome.min.css");
require("./index.css");

function isIE() {
	if (!!window.ActiveXObject || "ActiveXObject" in window)
		return true;
	else
		return false;
}
window.isIE = isIE;

const isProd = process.env.NODE_ENV === 'production';
const VueAnalytics = require('vue-analytics').default;
Vue.use(VueAnalytics, {
	id: 'UA-101203737-4',
	customResourceURL: 'https://www.google-analytics.com/analytics.js',
	debug: {
		enabled: !isProd,
		sendHitTask: isProd
	}
});

Vue.config.productionTip = false;
Vue.use(VueRouter);
vRouter.beforeEach(onBeforeEach);
vRouter.afterEach(onAfterEach);

Number.prototype.pad = function (size) {
	var s = String(this);
	while (s.length < (size || 2)) { s = "0" + s; }
	return s;
}

String.prototype.shortAmount = function () {
	let dot_index = this.indexOf('.');
	if (dot_index === -1) return this + '.0000';
	if (this.length - 1 - dot_index > 4) {
		return this.slice(0, dot_index + 4 + 1);
	} else if (this.length - 1 - dot_index < 4) {
		return this.padEnd(5 + dot_index, '0');
	}
	return this;
}

String.prototype.padDecimal = function () {
	let dot_index = this.indexOf('.');
	if (dot_index === -1) return this + '.0000';
	if (this.length - 1 - dot_index > 4) {
		return this;
	} else if (this.length - 1 - dot_index < 4) {
		return this.padEnd(5 + dot_index, '0');
	}
	return this;
}

String.prototype.shortHash = function () {
	if (this.length > 12) {
		return this.slice(0, 6) + '...' + this.slice(-6);
	}
	return this;
}

Date.prototype.getWeekNumber = function () {
	var d = new Date(Date.UTC(this.getFullYear(), this.getMonth(), this.getDate()));
	var dayNum = d.getUTCDay() || 7;
	d.setUTCDate(d.getUTCDate() + 4 - dayNum);
	var yearStart = new Date(Date.UTC(d.getUTCFullYear(), 0, 1));
	return Math.ceil((((d - yearStart) / 86400000) + 1) / 7)
};

vApp = new Vue({
	components: {
		//"vue-popmsg": require("@/components/vue-popmsg").default,
		"vue-footer": require("@/components/vue-footer").default,
		"vue-header": require("@/components/vue-header").default,
		"vue-modal": require("@/components/vue-modal").default
	},
	data: {
		timestamp: Date.now(),
		showModalLoading: false,
		showAtpAds: true,
		mainnetDipStarted: true,
		mainnetGotDipWinners: true,
		testnetDipStarted: true,
		testnetGotDipWinners: true
	},
	el: ".vue",
	router: vRouter

});

setInterval(() => {
	vApp.timestamp = Date.now();
}, 1000);

////////////////////////////////////////////////////////////
//
// API PREFIX
//
////////////////////////////////////////////////////////////

function onBeforeEach(to, from, next) {
	window.scrollTo(0, 0);

	vApp.showModalLoading = false;

	var apiPrefix, first, path;

	for (first in vAppConfig.apiPrefixes) break;

	if (to.name == "*") {
		path = (from.params.api ? "/" + from.params.api : "") + "/404";
	} else if (to.params.api)
		if (to.params.api in vAppConfig.apiPrefixes)
			if (to.params.api == first) {
				// mainnet/xxx -> /xxx
				to.params.api = undefined;
				path = vRouter.resolve({ params: to.params }, to).resolved.fullPath;
			} else
				apiPrefix = vAppConfig.apiPrefixes[to.params.api].url;
		else {
			path = (from.params.api ? "/" + from.params.api : "") + "/404";
		}
	else {
		apiPrefix = vAppConfig.apiPrefixes[first].url;
	}

	sessionStorage.apiPrefix = apiPrefix;
	next(path);
}

function onAfterEach(to, from) {
	if (to.meta && to.meta.uaview) {
		gaPage(to.meta.uaview);
	}
}

////////////////////////////////////////////////////////////
//
// LOCALIZATION METHODS
//
////////////////////////////////////////////////////////////

Vue.use(VueTranslate);

// ==================================[ HEADER ]=================================
var myComp = Vue.extend({
	template:	`<div>
						<a class="nav-link" href=# id=language-selector role=button data-toggle=dropdown aria-haspopup=true aria-expanded=false>
							{{ t('Languages') }}
							<img src=/static/img/icon_arrow_down.png width=12 alt="">
						</a>
						<div class=dropdown-menu aria-labelledby=language-selector>
							<div>
								<a href="#" class="dropdown-item" v-on:click.prevent=translateToEnglish()><img src=/static/img/icon-engflag.png width=12 alt=""> {{ t('English') }}</a>
								<a href="#" class="dropdown-item" v-on:click.prevent=translateToSpanish()><img src=/static/img/icon-espflag.png width=12 alt=""> {{ t('Spanish') }}</a>
							</div>
						</div>
					</div>`,
	mounted() {
		this.$translate.setLang('en_US');
	},
	locales: {
		es_ES: {
			'Languages': 'Idiomas',
			'English': 'Inglés',
			'Spanish': 'Español'
		},
		en_US: {
			'Languages': 'Languages',
			'English': 'English',
			'Spanish': 'Spanish'
		}
	},
	methods: {
		translateToSpanish() {
			this.$translate.setLang('es_ES');
		},
		translateToEnglish() {
			this.$translate.setLang('en_US');
		}
	}
});
var header0 = new Vue({
	el: '#lang-sel',
	components: {myComp},
	template: `<div><my-comp></my-comp></div>`
});

// Home link
var HomeIndicator = Vue.extend({
	template: `<span>{{ t('home') }}<span class=sr-only>({{ t('current') }})</span></span>`,
	locales: {
		es_ES: {
			'home': 'Inicio',
			'current': 'actual'
		},
		en_US: {
			'home': 'Home',
			'current': 'current'
		}
	}
});
var header1 = new Vue({
	el: '#homeindicator',
	components: {HomeIndicator},
	template: `<div><home-indicator></home-indicator></div>`
});

// Search tool
var SearchTool = Vue.extend({
	template: `<input class="mr-sm-2 font-12" name=search id=buscador type=search :placeholder="t('calatrava')" />`,
	locales: {
		es_ES: {
			'calatrava': 'Buscar por dirección, txhash, bloque o token'
		},
		en_US: {
			'calatrava': 'Search by address, txhash, block or token'
		}
	}
});
var header2 = new Vue({
	el: '#buscador',
	components: {SearchTool},
	template: `<search-tool></search-tool>`
});

// DIP Winners
var DipWinners = Vue.extend({
	template: `<span>{{ t('dipwinners') }}</span>`,
	locales: {
		es_ES: {
			'dipwinners': 'Ganadores del DIP'
		},
		en_US: {
			'dipwinners': 'DIP Winners'
		}
	}
});
var header3 = new Vue({
	el: '#dipwinners',
	components: {DipWinners},
	template: `<dip-winners></dip-winners>`
});

// Blockchain submenues
// Transactions
var TransactionsSubmenu = Vue.extend({
	template: `<span>{{ t('transactionssubmenu') }}</span>`,
	locales: {
		es_ES: {
			'transactionssubmenu': 'Transacciones'
		},
		en_US: {
			'transactionssubmenu': 'Transactions'
		}
	}
});
var header4 = new Vue({
	el: '#transactionssubmenu',
	components: {TransactionsSubmenu},
	template: `<transactions-submenu></transactions-submenu>`
});

// Pending transactions
var PendingtransactionsSubmenu = Vue.extend({
	template: `<span>{{ t('pendingtransactionssubmenu') }}</span>`,
	locales: {
		es_ES: {
			'pendingtransactionssubmenu': 'Transacciones pendientes'
		},
		en_US: {
			'pendingtransactionssubmenu': 'Pending Transactions'
		}
	}
});
var header5 = new Vue({
	el: '#pendingtransactionssubmenu',
	components: {PendingtransactionsSubmenu},
	template: `<pendingtransactions-submenu></pendingtransactions-submenu>`
});

// Blocks
var BlocksSubmenu = Vue.extend({
	template: `<span>{{ t('blockssubmenu') }}</span>`,
	locales: {
		es_ES: {
			'blockssubmenu': 'Bloques'
		},
		en_US: {
			'blockssubmenu': 'Blocks'
		}
	}
});
var header6 = new Vue({
	el: '#blockssubmenu',
	components: {BlocksSubmenu},
	template: `<blocks-submenu></blocks-submenu>`
});

// Accounts
var AccountsSubmenu = Vue.extend({
	template: `<span>{{ t('accountssubmenu') }}</span>`,
	locales: {
		es_ES: {
			'accountssubmenu': 'Cuentas'
		},
		en_US: {
			'accountssubmenu': 'Accounts'
		}
	}
});
var header7 = new Vue({
	el: '#accountssubmenu',
	components: {AccountsSubmenu},
	template: `<accounts-submenu></accounts-submenu>`
});

// ===========================[ MAIN SCREEN MODULES ]===========================
// ----------------------------[ DAILY TRANSACTIONS ]---------------------------
// Title
var DailyTx = Vue.extend({
	template: `<span>{{ t('dailytxs') }}</span>`,
	locales: {
		es_ES: {
			'dailytxs': 'Transacciones diarias'
		},
		en_US: {
			'dailytxs': 'Daily Transactions'
		}
	}
});
var dailytx0 = new Vue({
	el: '#dailytransactions',
	components: {DailyTx},
	template: `<daily-tx></daily-tx>`
});

// Subtitle
var TodaysTx = Vue.extend({
	template: `<span>{{ t('todaystransactions') }}</span>`,
	locales: {
		es_ES: {
			'todaystransactions': 'Hoy'
		},
		en_US: {
			'todaystransactions': 'Today'
		}
	}
});
var dailytx1 = new Vue({
	el: '#todaytxs',
	components: {TodaysTx},
	template: `<todays-tx></todays-tx>`
});
// --------------------------------[ NAS PRICE ]--------------------------------
// Title
var NasPrice = Vue.extend({
	template: `<span>{{ t('nasprice') }}</span>`,
	locales: {
		es_ES: {
			'nasprice': 'Valor del NAS'
		},
		en_US: {
			'nasprice': 'NAS Price'
		}
	}
});
var nasprice0 = new Vue({
	el: '#nasprice',
	components: {NasPrice},
	template: `<nas-price></nas-price>`
});

// Subtitle
var UpdatetimePrefix = Vue.extend({
	template: `<span>{{ t('naspriceprefix') }}</span>`,
	locales: {
		es_ES: {
			'naspriceprefix': 'Actualizado hace: '
		},
		en_US: {
			'naspriceprefix': 'Update Time: '
		}
	}
});
var nasprice1 = new Vue({
	el: '#updatetimeprefix',
	components: {UpdatetimePrefix},
	template: `<updatetime-prefix></updatetime-prefix>`
});

// Subtitle suffix
var UpdatetimeSuffix = Vue.extend({
	template: `<span>{{ t('naspricesuffix') }}</span>`,
	locales: {
		es_ES: {
			'naspricesuffix': ' '
		},
		en_US: {
			'naspricesuffix': ' ago'
		}
	}
});
var nasprice2 = new Vue({
	el: '#updatetimesuffix',
	components: {UpdatetimeSuffix},
	template: `<updatetime-suffix></updatetime-suffix>`
});

// Market cap
var MarketCap = Vue.extend({
	template: `<span>{{ t('marketcap') }}</span>`,
	locales: {
		es_ES: {
			'marketcap': 'Cap. de mercado'
		},
		en_US: {
			'marketcap': 'Market Cap'
		}
	}
});
var nasprice3 = new Vue({
	el: '#marketcap',
	components: {MarketCap},
	template: `<market-cap></market-cap>`
});

// Market vol
var MarketVol = Vue.extend({
	template: `<span>{{ t('marketvol') }}</span>`,
	locales: {
		es_ES: {
			'marketvol': 'Vol. de mercado'
		},
		en_US: {
			'marketvol': 'Market Vol'
		}
	}
});
var nasprice4 = new Vue({
	el: '#marketvol',
	components: {MarketVol},
	template: `<market-vol></market-vol>`
});

// ------------------------------[ BLOCKS - MAIN ]------------------------------
// Title
var BlocksTitle = Vue.extend({
	template: `<div class="item-title">{{ t('blockstitle') }}</div>`,
	locales: {
		es_ES: {
			'blockstitle': 'Bloques'
		},
		en_US: {
			'blockstitle': 'Blocks'
		}
	}
});
var blocks0 = new Vue({
	el: '#blockstitle',
	components: {BlocksTitle},
	template: `<blocks-title></blocks-title>`
});

// Status
var BlocksStatus = Vue.extend({
	template: `<div class="subtitle font-12 text-gray">{{ t('blocksstatus') }}</div>`,
	locales: {
		es_ES: {
			'blocksstatus': 'Estado'
		},
		en_US: {
			'blocksstatus': 'Block Status'
		}
	}
});
var blocks1 = new Vue({
	el: '#blocksstatus',
	components: {BlocksStatus},
	template: `<blocks-status></blocks-status>`
});

// Transactions
var BlocksTransactions = Vue.extend({
	template: `<span id="blockstransactions" style="display: none;">{{ t('blockstx') }}</span>`,
	locales: {
		es_ES: {
			'blockstx': 'Transacciones: '
		},
		en_US: {
			'blockstx': 'Transactions: '
		}
	}
});
var blocks2 = new Vue({
	el: '#blockstransactions',
	components: {BlocksTransactions},
	template: `<blocks-transactions></blocks-transactions>`
});

// Interval
var BlocksInterval = Vue.extend({
	template: `<span id="blocksinterval" style="display: none;">{{ t('blocksinterval') }}</span>`,
	locales: {
		es_ES: {
			'blocksinterval': 'Intervalo de bloque: 15 s'
		},
		en_US: {
			'blocksinterval': 'Block interval: 15s'
		}
	}
});
var blocks3 = new Vue({
	el: '#blocksinterval',
	components: {BlocksInterval},
	template: `<blocks-interval></blocks-interval>`
});
// ------------------------------[ BLOCKS - TOOLS ]-----------------------------
// Height
var BlocksHeight = Vue.extend({
	template: `<div style="display: none;" id="blocksheighttext">{{ t('blocksheight') }}</div>`,
	locales: {
		es_ES: {
			'blocksheight': 'Altura de bloque >'
		},
		en_US: {
			'blocksheight': 'Block Height >'
		}
	}
});
var blocktools0 = new Vue({
	el: '#blockheight',
	components: {BlocksHeight},
	template: `<blocks-height></blocks-height>`
});
// Total tx
var BlocksTotaltx = Vue.extend({
	template: `<div style="display: none;" id="blockstotaltxtext">{{ t('totaltx') }}</div>`,
	locales: {
		es_ES: {
			'totaltx': 'Total de transacciones >'
		},
		en_US: {
			'totaltx': 'Total Transactions >'
		}
	}
});
var blocktools1 = new Vue({
	el: '#blocktotaltx',
	components: {BlocksTotaltx},
	template: `<blocks-totaltx></blocks-totaltx>`
});
// Total smart contracts
var BlocksTotalsc = Vue.extend({
	template: `<div style="display: none;" id="blockstotalsmartcontracts">{{ t('blocktotalsmartcontracts') }}</div>`,
	locales: {
		es_ES: {
			'blocktotalsmartcontracts': 'Total de contratos inteligentes >'
		},
		en_US: {
			'blocktotalsmartcontracts': 'Total Smart Contracts >'
		}
	}
});
var blocktools2 = new Vue({
	el: '#blocktotalsmartcontracts',
	components: {BlocksTotalsc},
	template: `<blocks-totalsc></blocks-totalsc>`
});
// Total addresses
var BlocksTotalad = Vue.extend({
	template: `<div style="display: none;" id="blockstotaladdresses">{{ t('blockstotaladdresses') }}</div>`,
	locales: {
		es_ES: {
			'blockstotaladdresses': 'Total de direcciones >'
		},
		en_US: {
			'blockstotaladdresses': 'Total Addresses >'
		}
	}
});
var blocktools3 = new Vue({
	el: '#blocktotaladdresses',
	components: {BlocksTotalad},
	template: `<blocks-totalad></blocks-totalad>`
});