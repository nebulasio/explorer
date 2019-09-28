<style>
	.vue-bread {
		background-color: #f7f7f7;
		overflow: auto;
		padding: 10px 0;
	}
	.vue-txs {
		background-color: white;
	}
	.vue-txs .tip a {
		color: rgb(76, 32, 133);
	}

	.vue-txs td,
	.vue-txs th {
		border-top-color: #ddd;
	}

	.vue-txs .fail {
		background: url(../../static/img/warning_icon.png)no-repeat 0 10px;
		padding-left: 28px;
	}

	.vue-txs .fail a {
		display: inline-block;
		max-width: 142px;
		overflow: hidden;
		text-overflow: ellipsis;
	}
	.vue-txs .hash-normal {
		height: 20px;
		font-size: 14px;
		/* font-family: OpenSans; */
		color: rgba(0, 87, 255, 1);
		line-height: 20px;
	}

	.vue-txs .hash-failed {
		height: 20px;
		font-size: 14px;
		/* font-family: OpenSans; */
		line-height: 20px;
		color: rgba(240, 68, 52, 1);
	}

	.vue-txs .txs-hash {
		max-width: 185px;
		overflow: hidden;
		text-overflow: ellipsis;
		vertical-align: center;
		padding: 0;
	}

	.vue-txs .txs-block {
		max-width: 120px;
		overflow: hidden;
		text-overflow: ellipsis;
		vertical-align: center;
		padding: 0;
	}

	.vue-txs .fromTo {
		line-height: 24px;
	}

	.vue-txs .block {
		margin-right: 8px;
	}

	@media (max-width: 767.98px) {
		.vue-txs .title {
			font-size: 20px;
		}
	}

</style>
<template>
	<!-- https://etherscan.io/txs -->
	<div class="vue-txs fullfill">
		<!--
		 <vue-bread :title='"Transactions" + (($route.query.a || $route.query.block) ? " of" : "")' :subtitle='$route.query.block ? ("Block #" + $route.query.block) : $route.query.a' :subtitlemonospaced='!!$route.query.a' :blockies='$route.query.a'></vue-bread>
		-->

		<div class="vue-bread">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-auto bread-title font-40 font-bold font-color-000000 txslocalizable" id="transactionsTxPrefix">
						<div v-if="($route.query.a || $route.query.block)" id="transactionsTxSuffix" class="txslocalizable">
						</div>
					</div>
					<div v-if="$route.query.block">
						<div class="col-auto bread-subtitle font-16 font-bold font-color-000000 align-baseline">
							<span id="transactionsBlockNumber" class="txslocalizable"></span> {{ $route.query.block }}
						</div>
					</div>
					<div v-else>
						<div class="col-auto bread-subtitle font-16 font-bold font-color-000000 align-baseline">{{ $route.query.a }}</div>
					</div>
				</div>
			</div>
		</div>

		<div v-if="arr && arr.length" class="container mt20">
			<div class="d-block d-md-flex flex-row align-items-center mt20">
				<span class="col-auto pl-0 pr-2 info font-color-000000 font-24 font-bold title">
					<span v-if="(totalTxs > 0 && !$route.query.a && !$route.query.block)">
						<span id="transactionsMoreThan" class="txslocalizable"></span>
					</span>
					<span v-if="(totalTxs > 1000000)">
						{{ (Math.floor(totalTxs / 1000000)) }}
						<span v-if="(Math.floor(totalTxs / 1000000) > 2)">
							<span id="transactionsMillions" class="txslocalizable"></span>
						</span>
						<span v-else>
							<span id="transactionsMillion" class="txslocalizable"></span>
						</span> <span id="transactionsOf" class="txslocalizable"></span>
					</span>
					<span v-else>
					{{ numberAddComma(totalTxs) }}
					</span> <span id="transactionsFound" class="txslocalizable"></span>
				</span>
				<span v-if="(totalTxs > 500)" class="col-auto pl-0 font-color-555555 font-16 align-text-bottom subtitle">(<span id="transactionsShowingLast" class="txslocalizable"></span>)</span>
			</div>

			<div class="explorer-table-container">
				<table class="mt20 explorer-table list-table">
					<tr class="list-header font-12 font-bold font-color-000000">
						<th></th>
						<th class="txslocalizable" id="transactionsTableTxHash"></th>
						<th class="txslocalizable" id="transactionsTableBlock"></th>
						<th class="txslocalizable" id="transactionsTableAge"></th>
						<th class="txslocalizable" id="transactionsTableFrom"></th>
						<th></th>
						<th class="txslocalizable" id="transactionsTableTo"></th>
						<th class=text-right txslocalizable id="transactionsTableValue"></th>
						<th class="text-right pr-3 txslocalizable" id="transactionsTableTxFee"></th>
					</tr>

					<tr v-for="(o, i) in arr" :key="i">
						<td>
							<img v-if="o.status===0" class="icon40" src="../../static/img/ic_tx_failed.png"/>
						</td>
						<td class="txs-hash">
							<router-link v-bind:to='fragApi + "/tx/" + o.hash'>
								<span v-bind:class="[o.status===0 ? 'hash-failed' : 'hash-normal', 'monospace']">{{ o.hash }}</span>
							</router-link>
						</td>

						<td class="txs-block">
							<router-link class="font-14" v-if='o.block && o.block.height' v-bind:to='fragApi + "/block/" + o.block.height'>
								<span>{{ o.block.height }}</span>
							</router-link>
							<i class="font-14 font-color-000000" v-else><span name="transactionsTablePending" class="txsmultilocalizable"></span></i>
						</td>
						<td class="font-14 font-color-555555">
							<div>
								<span>
									<span name="transactionsTableAgoPrefix" class="txsmultilocalizable"></span> {{ timeConversion(o.timeDiff) }} <span name="transactionsTableAgoSuffix" class="txsmultilocalizable"></span>
								</span>
								<div class="down-arrow-tip" style="display:none;">{{ new Date(o.timestamp).toString().replace('GMT', 'UTC').replace(/\(.+\)/gi, '') }} | {{ o.timestamp }}</div>
							</div>
						</td>
						<td class="tdxxxwddd txs-from-to">
							<vue-blockies v-bind:address='o.from.alias || o.from.hash'></vue-blockies>
							<!-- <span class="fromTo font-color-000000 font-14" v-if="o.from.hash === $route.query.a">{{ o.from.alias || o.from.hash }}</span> -->
							<router-link v-bind:to='fragApi + "/address/" + o.from.hash'>
								<span class="fromTo font-14  monospace">{{ o.from.hash }}</span>
							</router-link>
						</td>
						<td style="padding: 10px;">
							<img class="icon16" src="../../static/img/ic_arrow_right.png"/>
							<div style="width: 10px;"></div>
						</td>
						<td class="tdxxxwddd txs-from-to">
							<div v-if="o.type==='call'" class="container-tip">
								<span class="tip down-arrow-tip font-15 shadow txslocalizable" id="transactionsSmartcontract"></span>
								<img class="icon24" src="../../static/img/icon_tx_type_contract.png" />
							</div>
							<vue-blockies v-bind:address='o.to.alias || o.to.hash'></vue-blockies>
							<!-- <span class="fromTo font-color-000000 font-14" v-if="o.to.hash === $route.query.a">{{ o.to.alias || o.to.hash }}</span> -->
							<router-link v-bind:to='fragApi + "/address/" + o.to.hash'>
								<span class="fromTo font-14  monospace">{{ o.to.hash }}</span>
							</router-link>
						</td>
						<td v-if=isNatVoteTransfer(o) class="text-right font-color-000000 font-14">{{ natAmount(o) }} NAT</td>
						<td v-else class="text-right font-color-000000 font-14">{{ tokenAmount(o.value) }} NAS</td>
						<td class="text-right font-14 font-color-555555 pr-3">{{ toWei(o.txFee) }}</td>
					</tr>
				</table>
			</div>

			<vue-pagination v-bind:current=currentPage right=1 v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext
				v-on:prev=onPrev v-on:to=onTo></vue-pagination>
		</div>
		<div v-else class="container mt-20">
			<div class="d-block d-md-flex flex-row align-items-center mt20">
				<span class="col-auto pl-0 pr-2 info font-color-000000 font-24 font-bold title txslocalizable" id="transactionsNoTxFound"></span>
			</div>
		</div>
	</div>
</template>
<script>
	import { EventBus } from '../events.js';
	import { jsonStrings } from '../l10nstrings.js';
	var api = require("@/assets/api");
	var utility = require("@/assets/utility");
	var BigNumber = require("bignumber.js");

	module.exports = {
		components: {
			"vue-bread": require("@/components/vue-bread").default,
			"vue-pagination": require("@/components/vue-pagination").default,
			"vue-blockies": require("@/components/vue-blockies").default,
			"vue-nothing": require("@/components/vue-nothing").default
		},
		data() {
			return {
				arr: null,
				currentPage: 0,
				fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
				maxDisplayCnt: 0,
				totalPage: 0,
				totalTxs: 0
			};
		},
		methods: {
			removeTempInterval() {
				clearInterval(this.tempInterval);
			},
			checkStaticTranslations() {
				var myTxsElements = document.getElementsByClassName("txslocalizable");
				var totalElements = myTxsElements.length;
				var i;
				for (i = 0; i < totalElements; i++) {
					var elementId = myTxsElements[i].getAttribute("id");
					if (myTxsElements[i].getAttribute("localize")) {
						var elementAttribute = myTxsElements[i].getAttribute("localize");
						myTxsElements[i].setAttribute(elementAttribute, jsonStrings[this.$selectedLanguage][elementId]);
					}
					else {
						myTxsElements[i].innerText = jsonStrings[this.$selectedLanguage][elementId];
					}
				}
			},
			checkDynamicTranslations() {
				var myTxsMultiElements = document.getElementsByClassName("txsmultilocalizable");
				var totalElements = myTxsMultiElements.length;
				var i;
				for (i = 0; i < totalElements; i++) {
					var elementName = myTxsMultiElements[i].getAttribute("name");
					if (myTxsMultiElements[i].getAttribute("localize")) {
						var elementAttribute = myTxsMultiElements[i].getAttribute("localize");
						myTxsMultiElements[i].setAttribute(elementAttribute, jsonStrings[this.$selectedLanguage][elementName]);
					}
					else {
						myTxsMultiElements[i].innerText = jsonStrings[this.$selectedLanguage][elementName];
					}
				}
			},
			nav(n) {
				var query = JSON.parse(window.JSON.stringify(this.$route.query));

				query.p = n;
				this.$router.push({ path: this.$route.path, query });
			},
			nthPage() {
				this.$root.showModalLoading = true;

				api.getTx({
					a: this.$route.query.a,
					block: this.$route.query.block,
					p: this.$route.query.p || 1,
					isPending: this.$route.query.isPending
				}, o => {
					this.$root.showModalLoading = false;
					this.arr = o.txnList;
					this.currentPage = o.currentPage;
					this.maxDisplayCnt = o.maxDisplayCnt;
					this.totalPage = o.totalPage;
					this.totalTxs = o.txnCnt;
				}, xhr => {
					this.$root.showModalLoading = false;
					this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404");
				});
			},
			numberAddComma(n) {
				return utility.numberAddComma(n);
			},
			onFirst() {
				this.nav(1);
			},
			onLast() {
				this.nav(this.totalPage);
			},
			onNext() {
				this.nav(this.currentPage + 1);
			},
			onPrev() {
				this.nav(this.currentPage - 1);
			},
			onTo(n) {
				this.nav(n);
			},
			timeConversion(ms) {
				return utility.timeConversion(ms);
			},
			toWei(n) {
				return utility.toWei(n);
			},
			easyNumber(n) {
				return utility.easyNumber(n);
			},
			tokenAmount(n) {
				BigNumber.config({ DECIMAL_PLACES: 18 })
				var amount = BigNumber(n);
				var decimals = BigNumber('1e+18');
				return amount.div(decimals).toFormat().shortAmount();
			},
			isNatVoteTransfer(tx) {
				try {
					if (tx.type === 'call' && tx.to.hash === 'n1pADU7jnrvpPzcWusGkaizZoWgUywMRGMY' && JSON.parse(tx.data).Function === 'vote' && JSON.parse(JSON.parse(tx.data).Args).length >= 4) {
						return true;
					}
				} catch (error) {
				}
				return false;
			},
			natAmount(tx) {
				BigNumber.config({ DECIMAL_PLACES: 18 })
				var amount = BigNumber(JSON.parse(JSON.parse(tx.data).Args)[3]);
				var decimals = BigNumber('1e+18');
				return amount.div(decimals).toFormat().shortAmount();
			}
		},
		mounted() {
			EventBus.$on('changeLanguage', foo => {this.checkStaticTranslations()});
			this.translationsInterval = setInterval(() => {
				this.checkDynamicTranslations();
			}, 1000);
			this.tempInterval = setInterval(() => {
				this.checkStaticTranslations();
				this.removeTempInterval();
			}, 1500);

			this.nthPage();
		},
		watch: {
			$route() {
				this.nthPage();
			}
		}
	};
</script>
