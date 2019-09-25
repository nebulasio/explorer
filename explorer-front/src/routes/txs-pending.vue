<style>
	.vue-bread {
		background-color: #f7f7f7;
		overflow: auto;
		padding: 10px 0;
	}
	.vue-txs-pending {
		background-color: white;
	}

	.vue-txs-pending .block {
		margin-right: 8px;
	}
</style>
<template>
	<!-- https://etherscan.io/txsPending -->
	<div class="vue-txs-pending fullfill">
		<div class="vue-bread">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-auto bread-title font-40 font-bold font-color-000000 localizable" id="pendingTxTitle"></div>
					<div class="col-auto bread-subtitle font-16 font-bold font-color-000000 align-baseline"></div>
				</div>
			</div>
		</div>
		<div v-if="arr && arr.length" class="container mt20">
			<div class="align-items-center info-and-pagination mt20 row">
				<div class="col info font-color-000000 font-24 font-bold">{{ numberAddComma(totalTxs) }} <span id="pendingTxPendingPrefix" class="localizable"></span> {{ totalTxs > 1 ? 'txns' : 'txn' }} <span id="pendingTxPendingSuffix" class="localizable"></span></div>
			</div>
			<div class="explorer-table-container">
				<table class="mt20 explorer-table list-table">
					<tr class="list-header font-12 font-bold font-color-000000">
						<th class="pl-3 localizable" id="pendingTxTxHash"></th>
						<th class="localizable" id="pendingTxTxLastSeen"></th>
						<th class="localizable" id="pendingTxTxGasLimit"></th>
						<th class="localizable" id="pendingTxTxGasPrice"></th>
						<th class="localizable" id="pendingTxTxFrom"></th>
						<th></th>
						<th class="localizable" id="pendingTxTxTo"></th>
						<th class="text-right pr-3 localizable" id="pendingTxTxValue"></th>
					</tr>
					<tr v-for="(o, i) in arr" :key="i">
						<td class="tdxxxwddd pl-3">
							<router-link v-bind:to='fragApi + "/tx/" + o.hash'>
								<span class="font-14 monospace">{{ o.hash }}</span>
							</router-link>
						</td>
						<td class="font-14 font-color-555555">
							<div>
								<div><span name="transactionsTableAgoPrefix" class="multilocalizable"></span> {{ timeConversion(o.timeDiff) }} <span name="transactionsTableAgoSuffix" class="multilocalizable"></span></div>
								<div class="down-arrow-tip" style="display: none;">{{ new Date(o.timestamp).toString().replace('GMT', 'UTC').replace(/\(.+\)/gi, '') }} | {{ o.timestamp }}</div>
							</div>
						</td>
						<td class="font-14 font-color-555555">{{ numberAddComma(o.gasLimit) }}</td>
						<td class="font-14 font-color-555555">{{ toWei(o.gasPrice) }}</td>
						<td class=tdxxxwddd>
							<vue-blockies v-bind:address='o.from.alias || o.from.hash'></vue-blockies>
							<router-link v-bind:to='fragApi + "/address/" + o.from.hash'>
								<span class="font-14  monospace">{{ o.from.alias || o.from.hash }}</span>
							</router-link>
						</td>
						<td>
							<img class="icon16" src="../../static/img/ic_arrow_right.png"/>
						</td>
						<td class=tdxxxwddd>
							<div v-if="o.type==='call'" class="container-tip">
								<span class="tip down-arrow-tip font-15 shadow">Smart Contract</span>
								<img class="icon24" src="../../static/img/icon_tx_type_contract.png" />
							</div>
							<vue-blockies v-bind:address='o.to.alias || o.to.hash'></vue-blockies>
							<router-link v-bind:to='fragApi + "/address/" + o.to.hash'>
								<span class="font-14  monospace">{{ o.to.alias || o.to.hash }}</span>
							</router-link>
						</td>
						<td class="text-right font-14 font-color-000000 pr-3">
							{{ tokenAmount(o.value) }} NAS
						</td>
					</tr>
				</table>
			</div>
			<vue-pagination v-bind:current=currentPage right=1 v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext v-on:prev=onPrev v-on:to=onTo></vue-pagination>
		</div>
		<vue-nothing v-if="arr && arr.length === 0" title="0 pending txn found"></vue-nothing>
	</div>
</template>
<script>
	import { EventBus } from '../events.js';
	import { jsonStrings } from '../l10nstrings.js';
	var api = require("@/assets/api"),
		utility = require("@/assets/utility"),
		BigNumber = require("bignumber.js");

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
				totalPage: 0,
				totalTxs: 0
			};
		},
		methods: {
			removeTempInterval() {
				clearInterval(this.tempInterval);
			},
			checkStaticTranslations() {
				// Unique elements, identified by id attr
				var myLocalizableElements = document.getElementsByClassName("localizable");
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
			checkDynamicTranslations() {
				// Multiple elements, identified with name attr
				var myMultiLocalizableElements = document.getElementsByClassName("multilocalizable");
				var totalElements = myMultiLocalizableElements.length;
				var i;
				for (i = 0; i < totalElements; i++) {
					var elementName = myMultiLocalizableElements[i].getAttribute("name");
					if (myMultiLocalizableElements[i].getAttribute("localize")) {
						var elementAttribute = myMultiLocalizableElements[i].getAttribute("localize");
						myMultiLocalizableElements[i].setAttribute(elementAttribute, jsonStrings[this.$selectedLanguage][elementName]);
					}
					else {
						myMultiLocalizableElements[i].innerText = jsonStrings[this.$selectedLanguage][elementName];
					}
				}
				// Other specific methods for unique elements.
			},
			nthPage() {
				var p = this.$route.query.p || 1;

				if (p == this.currentPage)
					console.log("nthPage - 请求的第", p, "页正是当前页, 忽略此次调用");
				else {
					this.$root.showModalLoading = true;

					api.getTx({ isPending: true, p }, o => {
						this.$root.showModalLoading = false;
						this.arr = o.txnList;
						this.currentPage = o.currentPage;
						this.totalPage = o.totalPage;
						this.totalTxs = o.txnCnt;

						if (this.arr.length) {
							this.heightFrom = this.arr[0].height;
							this.heightTo = this.arr[this.arr.length - 1].height;
						} else {
							this.heightFrom = 0;
							this.heightTo = 0;
						}
					}, xhr => {
						this.$root.showModalLoading = false;
						this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404");
					});
				}
			},
			onFirst() {
				this.$router.push({
					path: this.$route.path,
					query: { p: 1 }
				});
			},
			onLast() {
				this.$router.push({
					path: this.$route.path,
					query: { p: this.totalPage }
				});
			},
			onNext() {
				this.$router.push({
					path: this.$route.path,
					query: { p: this.currentPage + 1 }
				});
			},
			onPrev() {
				this.$router.push({
					path: this.$route.path,
					query: { p: this.currentPage - 1 }
				});
			},
			onTo(n) {
				this.$router.push({
					path: this.$route.path,
					query: { p: n }
				});
			},
			timeConversion(ms) {
				return utility.timeConversion(ms);
			},
			numberAddComma(n) {
				return utility.numberAddComma(n);
			},
			toWei(n) {
				return utility.toWei(n);
			},
			easyNumber(n){
				return utility.easyNumber(n);
			},
			tokenAmount(n) {
				BigNumber.config({ DECIMAL_PLACES: 18 })
				var amount = BigNumber(n);
				var decimals = BigNumber('1e+18');
				return amount.div(decimals).toFormat().shortAmount();
			}
		},
		mounted() {
			if (typeof this.$selectedLanguage != 'undefined') {
				this.checkStaticTranslations();
			}
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
