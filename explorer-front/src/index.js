import VueTranslate from 'vue-translate-plugin';
import { EventBus } from './events.js';

var api = require("@/assets/api");
var appConfig = require("@/assets/app-config");

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

Vue.use(VueTranslate);

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

Vue.prototype.$selectedLanguage = "en_US";

// La estructura es muy sencilla: cada lenguaje contiene una serie de cadenas
// identificadas con el ID de cada elemento del DOM de cada componente.
// La idea es emparejar, mediante un bucle for in, el ID hallado con la cadena
// correspondiente al lenguaje seleccionado.

// Además, hay que iterar (dentro de cada componente) por los elementos que
// contienen la clase .localizable, y revisar el atributo localize. Si este no
// está vacío (lo cual implica modificar el innerHTML), el mismo contendrá el
// atributo sobre el cual aplicar la cadena localizada.

// Así, por ejemplo, para un elemento DOM cuyo ID es "blocksTitle", la cadena a
// buscar sería (para español): es_ES.blocksTitle.
Vue.prototype.$myJSON = {
		"en_US": {
		"headerToggleNavigation": "Toggle Navigation",
		"headerSearchTool": "Search by address, txhash, block or token",
		"headerHomeTitle": "Home",
		"headerHomeSubtitle": "current",
		"headerTransactionsSubmenu": "Transactions",
		"headerPendingTransactionsSubmenu": "Pending transactions",
		"headerBlocksSubmenu": "Blocks",
		"headerAccountsSubmenu": "Accounts",
		"headerDipWinners": "DIP Winners",
		"dashboardDailyTransactionsTitle": "Daily Transactions",
		"dashboardDailyTransactionsSubtitle": "Transactions: ",
		"dashboardNasPriceTitle": "NAS Value",
		"dashboardNasPriceUpdateTimePrefix": "Updated",
		"dashboardNasPriceUpdateTimeSuffix": " ago",
		"dashboardNasMarketCap": "Market Cap",
		"dashboardNasMarketVol": "Market Vol",
		"dashboardBlocksTitle": "Blocks",
		"dashboardBlocksSubtitle": "Current status",
		"dashboardBlocksTransactions": "Transactions:",
		"dashboardBlocksInterval": "Block interval: 15s"
	},
	"es_ES": {
		"headerToggleNavigation": "Cambiar navegación",
		"headerSearchTool": "Buscar por dirección, txhash, bloque o token",
		"headerHomeTitle": "Inicio",
		"headerHomeSubtitle": "actual",
		"headerTransactionsSubmenu": "Transacciones",
		"headerPendingTransactionsSubmenu": "Transacciones pendientes",
		"headerBlocksSubmenu": "Bloques",
		"headerAccountsSubmenu": "Cuentas",
		"headerDipWinners": "Ganadores DIP",
		"dashboardDailyTransactionsTitle": "Transacciones diarias",
		"dashboardDailyTransactionsSubtitle": "Transacciones: ",
		"dashboardNasPriceTitle": "Valor del NAS",
		"dashboardNasPriceUpdateTimePrefix": "Actualizado hace",
		"dashboardNasPriceUpdateTimeSuffix": "",
		"dashboardNasMarketCap": "Cap. de mercado",
		"dashboardNasMarketVol": "Vol. de mercado",
		"dashboardBlocksTitle": "Bloques",
		"dashboardBlocksSubtitle": "Estado actual",
		"dashboardBlocksTransactions": "Transacciones:",
		"dashboardBlocksInterval": "Intervalo: 15s"
	}
};

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
	}
	else if (to.params.api) {
		if (to.params.api in vAppConfig.apiPrefixes) {
			if (to.params.api == first) {
				// mainnet/xxx -> /xxx
				to.params.api = undefined;
				path = vRouter.resolve({ params: to.params }, to).resolved.fullPath;
			}
			else {
				apiPrefix = vAppConfig.apiPrefixes[to.params.api].url;
			}
		}
		else {
			path = (from.params.api ? "/" + from.params.api : "") + "/404";
		}
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

// Localization bar
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
			Vue.prototype.$selectedLanguage = "es_ES";
			this.$translate.setLang(Vue.prototype.$selectedLanguage);
			EventBus.$emit('changeLanguage');

		},
		translateToEnglish() {
			Vue.prototype.$selectedLanguage = "en_US";
			this.$translate.setLang(Vue.prototype.$selectedLanguage);
			EventBus.$emit('changeLanguage');
		},
		initializeApp() {
			EventBus.$emit('changeLanguage');
			clearInterval(this.longIntervalID);
		}
	},
	mounted() {
		this.longIntervalID = setInterval(() => {
			this.initializeApp();
		}, 1500);
	}
});

var header0 = new Vue({
	el: '#lang-sel',
	components: {myComp},
	template: `<div><my-comp></my-comp></div>`
});

/*

// Height
var BlocksHeight = Vue.extend({
	template: `<div style="display: none;" id="blocksheighttext">{{ t('blocksheight') }}</div>`,
	locales: {
		es_ES: {
			'blocksheight': 'Altura de bloque'
		},
		en_US: {
			'blocksheight': 'Block Height'
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
			'totaltx': 'Total de transacciones'
		},
		en_US: {
			'totaltx': 'Total Transactions'
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
			'blocktotalsmartcontracts': 'Total de contratos inteligentes'
		},
		en_US: {
			'blocktotalsmartcontracts': 'Total Smart Contracts'
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
			'blockstotaladdresses': 'Total de direcciones'
		},
		en_US: {
			'blockstotaladdresses': 'Total Addresses'
		}
	}
});
var blocktools3 = new Vue({
	el: '#blocktotaladdresses',
	components: {BlocksTotalad},
	template: `<blocks-totalad></blocks-totalad>`
});
// ---------------------------------[ ADDRESSES ]-------------------------------
// New addresses percentage
var NewAddressesPercentage = Vue.extend({
	template: `<span>{{ t('newaddressespercentage') }}</span>`,
	locales: {
		es_ES: {
			'newaddressespercentage': 'Porcentaje de nuevas direcciones'
		},
		en_US: {
			'newaddressespercentage': 'New Addresses Percentage'
		}
	}
});
var addresstools0 = new Vue({
	el: '#newaddressespercentage',
	components: {NewAddressesPercentage},
	template: `<new-addresses-percentage></new-addresses-percentage>`
});

// New addresses subtitle
var NewAddressesSubtitle = Vue.extend({
	template: `<span>{{ t('newaddressessubtitle') }}</span>`,
	locales: {
		es_ES: {
			'newaddressessubtitle': 'Son aquellas creadas en los últimos 90 días'
		},
		en_US: {
			'newaddressessubtitle': 'New addresses are those created within 90 days.'
		}
	}
});
var addresstools1 = new Vue({
	el: '#newaddressessubtitle',
	components: {NewAddressesSubtitle},
	template: `<new-addresses-subtitle></new-addresses-subtitle>`
});

// New addresses text
var NewAddressesText = Vue.extend({
	template: `<div style="display: none" id="newaddressestxt">{{ t('newaddressestext') }}</div>`,
	locales: {
		es_ES: {
			'newaddressestext': 'Nuevas direcciones '
		},
		en_US: {
			'newaddressestext': 'New Addresses '
		}
	}
});
var addresstools2 = new Vue({
	el: '#newaddressestxt',
	components: {NewAddressesText},
	template: `<new-addresses-text></new-addresses-text>`
});

// Addresses growth
var AddressesGrowth = Vue.extend({
	template: `<span>{{ t('addressesgrowth') }}</span>`,
	locales: {
		es_ES: {
			'addressesgrowth': 'Crecimiento de las direcciones'
		},
		en_US: {
			'addressesgrowth': 'Addresses Growth'
		}
	}
});
var addresstools3 = new Vue({
	el: '#addressesgrowth',
	components: {AddressesGrowth},
	template: `<addresses-growth></addresses-growth>`
});

// Amount text
var AmountText = Vue.extend({
	template: `<span id="amounttext" style="display: none;">{{ t('amounttext') }}</span>`,
	locales: {
		es_ES: {
			'amounttext': 'Cantidad: '
		},
		en_US: {
			'amounttext': 'Amount: '
		}
	}
});
var addresstools4 = new Vue({
	el: '#amounttxt',
	components: {AmountText},
	template: `<amount-text></amount-text>`
});
// -----------------------------[ BLOCK INDICATOR ]-----------------------------
// Title
var BlocksIndicatorTitle = Vue.extend({
	template: `<span>{{ t('blocksindicatortitle') }}</span>`,
	locales: {
		es_ES: {
			'blocksindicatortitle': 'Bloques'
		},
		en_US: {
			'blocksindicatortitle': 'Blocks'
		}
	}
});
var blocksindicator0 = new Vue({
	el: '#blocksindicatortitle',
	components: {BlocksIndicatorTitle},
	template: `<blocks-indicator-title></blocks-indicator-title>`
});
// View all
var BlocksIndicatorViewAll = Vue.extend({
	template: `<div style="display: none;" id="indicatorviewall">{{ t('blocksindicatorviewall') }}</div>`,
	locales: {
		es_ES: {
			'blocksindicatorviewall': 'Ver todo >'
		},
		en_US: {
			'blocksindicatorviewall': 'View All >'
		}
	}
});
var blocksindicator1 = new Vue({
	el: '#blocksindicatorviewall',
	components: {BlocksIndicatorViewAll},
	template: `<blocks-indicator-view-all></blocks-indicator-view-all>`
});
// View all
var BlocksIndicatorBlockNumber = Vue.extend({
	template: `<span id="blocksnumbertext" style="display: none;">{{ t('blocksindicatorblocknumber') }}</span>`,
	locales: {
		es_ES: {
			'blocksindicatorblocknumber': 'Bloque nro. '
		},
		en_US: {
			'blocksindicatorblocknumber': 'Block# '
		}
	}
});
var blocksindicator2 = new Vue({
	el: '#blocknumbertxt',
	components: {BlocksIndicatorBlockNumber},
	template: `<blocks-indicator-block-number></blocks-indicator-block-number>`
});
// No transaction
var NoTransaction = Vue.extend({
	template: `<span id="notransactiontext" style="display: none;">{{ t('notransactiontext') }}</span>`,
	locales: {
		es_ES: {
			'notransactiontext': 'Sin transacciones'
		},
		en_US: {
			'notransactiontext': 'No transactions'
		}
	}
});
var blocksindicator3 = new Vue({
	el: '#notransactiontxt',
	components: {NoTransaction},
	template: `<no-transaction></no-transaction>`
});
// One transaction
var OneTransaction = Vue.extend({
	template: `<span id="onetransactiontext" style="display: none;">{{ t('onetransactiontext') }}</span>`,
	locales: {
		es_ES: {
			'onetransactiontext': 'transacción'
		},
		en_US: {
			'onetransactiontext': 'transaction'
		}
	}
});
var blocksindicator4 = new Vue({
	el: '#onetransactiontxt',
	components: {OneTransaction},
	template: `<one-transaction></one-transaction>`
});
// Several transactions
var SeveralTransactions = Vue.extend({
	template: `<span id="severaltransactionstext" style="display: none;">{{ t('severaltransactionstext') }}</span>`,
	locales: {
		es_ES: {
			'severaltransactionstext': 'transacciones'
		},
		en_US: {
			'severaltransactionstext': 'transactions'
		}
	}
});
var blocksindicator5 = new Vue({
	el: '#severaltransactionstxt',
	components: {SeveralTransactions},
	template: `<several-transactions></several-transactions>`
});
// ------------------------------[ TRANSACTIONS ]-------------------------------
// Title
var TransactionsTitle = Vue.extend({
	template: `<span>{{ t('transactionstitle') }}</span>`,
	locales: {
		es_ES: {
			'transactionstitle': 'Transacciones'
		},
		en_US: {
			'transactionstitle': 'Transactions'
		}
	}
});
var transactions0 = new Vue({
	el: '#transactionstitle',
	components: {TransactionsTitle},
	template: `<transactions-title></transactions-title>`
});
// Tx#
var TransactionNumber = Vue.extend({
	template: `<div id="transactionnumber" style="display: none;">{{ t('transactionnumber') }}</div>`,
	locales: {
		es_ES: {
			'transactionnumber': 'Transacción nro.'
		},
		en_US: {
			'transactionnumber': 'Tx#'
		}
	}
});
var transactions1 = new Vue({
	el: '#txnumber',
	components: {TransactionNumber},
	template: `<transaction-number></transaction-number>`
});
// From
var FromText = Vue.extend({
	template: `<div id="fromtext" style="display: none;">{{ t('fromtext') }}</div>`,
	locales: {
		es_ES: {
			'fromtext': 'De'
		},
		en_US: {
			'fromtext': 'From'
		}
	}
});
var transactions2 = new Vue({
	el: '#fromtxt',
	components: {FromText},
	template: `<from-text></from-text>`
});
// To
var ToText = Vue.extend({
	template: `<div id="totext" style="display: none;">{{ t('totext') }}</div>`,
	locales: {
		es_ES: {
			'totext': 'a'
		},
		en_US: {
			'totext': 'To'
		}
	}
});
var transactions3 = new Vue({
	el: '#totxt',
	components: {ToText},
	template: `<to-text></to-text>`
});
*/