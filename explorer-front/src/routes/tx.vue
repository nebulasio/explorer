<style>
	div .vue-tx {
		background-color: white;
	}

	.vue-tx .table tbody tr td {
		border: 0;
	}

	.vue-tx td.code {
		background-color: #f8f9fa;
	}

	.vue-tx td.text {
		white-space: pre-line;
	}
	.vue-tx .fail {
		color:red;
	}
	.vue-tx .success {
		color:green;
	}
	.vue-tx .atpAddress {
		width: 134px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		display: inline-block;
		vertical-align: bottom;
		margin: auto 10px;
	}
	.vue-tx .td-left {
		width: 25%;
	}

	.vue-tx .atlaspAds-bottom>a>img {
		margin-top: 30px;
	}

	.vue-tx #atlaspAds-mobile {
		display: none;
	}

	.vue-tx #atlaspAds-side {
		position: fixed;
		top: 211px;
		left: calc((100% - 1140px) * 0.5 + 1140px + 5px);
		width: 300px;
		max-width: calc((100% - 1140px) * 0.5 - 25px);
	}

	@media (max-width: 767.98px) {

		.vue-tx .atpAddress {
			margin: auto 0px;
		}

		.vue-tx .atlaspAds-bottom>a>img {
			margin-top: 15px;
		}

		.vue-tx #atlaspAds-bottom {
			display: none;
		}

		.vue-tx #atlaspAds-mobile {
			display: block;
		}
	}

	@media (max-width: 1199.98px) {
		#atlaspAds-side {
			display: none;
		}
	}

</style>
<template>
	<div class="vue-tx fullfill" v-bind:triggerComputed=urlChange>
		<vue-bread
			:title='nothing'
			:subtitle="$route.params.id"
			:subtitlemonospaced="$route.params.id">
		</vue-bread>
		<div v-if="tx" class="container">
			<div class="font-24 font-bold font-color-000000 table-title">
				<span class="txlocalizable" id="txOverview"></span>
			</div>
			<div class="explorer-table-container d-none d-md-block">
				<table class="explorer-table">
					<tr>
						<td class="td-left font-16 font-color-555555 txlocalizable" style="padding-left: 24px;" id="txTxHash"></td>
						<td class="font-16 font-color-000000 monospace">{{ tx.hash }}</td>
					</tr>
					<tr class="font-16">
						<td class="font-color-555555 txlocalizable" style="padding-left: 24px;" id="txTxReceiptStatusTitle"></td>
						<td class="d-flex align-items-center" v-if="tx.status === 0" style="height: inherit">
							<img class="icon18" src="../../static/img/ic_tx_status_failed.png?v=20190110" />
							<span class="font-color-F04434 txlocalizable" style="margin-left: 10px;" id="txFail"> ( {{ tx.executeError }} )</span>
						</td>
						<td class="d-flex align-items-center" v-else-if="tx.status === 1" style="height: inherit">
							<img class="icon18" src="../../static/img/ic_tx_status_success.png" />
							<span class="font-color-07A656 txlocalizable" style="margin-left: 10px;" id="txSuccess"></span>
						</td>
						<td class="d-flex align-items-center" v-else style="height: inherit">
							<img class="icon18" src="../../static/img/ic_tx_status_pending.png" />
							<span class="font-color-F8BB08 txlocalizable" style="margin-left: 10px;" id="txPending"></span>
						</td>
					</tr>
					<tr>
						<td class="font-16 font-color-555555 txlocalizable" style="padding-left: 24px;" id="txBlockHeight"></td>
						<td>
							<template v-if=tx.isPending>
								<span class="font-color-000000 font-16"> <span id="txPendingText" class="txlocalizable"></span> </span>
							</template>
							<template v-else>
								<router-link v-if=tx.block v-bind:to='fragApi +"/block/" + tx.block.height'>
									<span class="font-16">{{tx.block.height}}</span>
								</router-link>
							</template>
						</td>
					</tr>
					<tr>
						<td class="font-16 font-color-555555 txlocalizable" style="padding-left: 24px;" id="txTimeStamp"></td>
						<td class="font-16 font-color-000000"><span id="txTimeAgoPrefix" class="txlocalizable"></span> {{ timeConversion(tx.timeDiff) }} <span id="txTimeAgoSuffix" class="txlocalizable"></span> ({{ new Date(tx.timestamp).toString().replace('GMT', 'UTC').replace(/\(.+\)/gi, '') }} | {{ tx.timestamp }})</td>
					</tr>
					<tr>
						<td class="font-16 font-color-555555 txlocalizable" style="padding-left: 24px;" id="txFromText"></td>
						<td>
							<router-link v-if=tx.from v-bind:to='fragApi +"/address/" + tx.from.hash'>
								<span class="font-16 monospace">{{ tx.from.hash }}</span>
							</router-link>
						</td>
					</tr>
					<tr>
						<td class="font-16 font-color-555555 txlocalizable" style="padding-left: 24px;" id="txToText"></td>
						<td v-if="tx.type === 'call'">
							<span class="font-color-000000 font-16 txlocalizable" id="txContract"></span>
							<router-link v-if=tx.to v-bind:to='fragApi +"/address/" + tx.to.hash'>
								<span style="margin-left: 20px;" class="font-16 monospace">{{ tx.to.hash }}</span>
							</router-link>
							<div class="token-name font-16 font-color-000000" style="margin-left: 14px;" v-if="isTokenTransfer && tx.tokenName">(<span id="txTextTokenPrefix" class="txlocalizable"></span>{{ tx.tokenName }}<span id="txTextTokenSuffix" class="txlocalizable"></span>)</div>
						</td>
						<td v-else>
							<router-link v-if=tx.to v-bind:to='fragApi +"/address/" + tx.to.hash'>
								<span class="font-16 monospace">{{ tx.to.hash }}</span>
							</router-link>
						</td>
					</tr>
					<tr  v-if=isTokenTransfer class="font-16">
						<td class="font-color-555555 txlocalizable" style="padding-left: 24px;" id="txTokenTransferred"></td>
						<td>
							<span class="font-color-000000 txlocalizable" id="txTransferredFrom"></span>
							<router-link class=atpAddress v-if=tx.to v-bind:to='fragApi +"/address/" + tx.from.hash'>
								<span class="monospace">{{ tx.from.hash }}</span>
							</router-link>
							<span class="font-color-000000 txlocalizable" id="txTransferredTo"></span>
							<router-link  class=atpAddress v-if=tx.to v-bind:to='fragApi +"/address/" + JSON.parse(JSON.parse(tx.data).Args)[0]'>
								<span class="monospace">{{ JSON.parse(JSON.parse(tx.data).Args)[0] }} </span>
							</router-link>
							<span class="font-color-000000"><span id="txFor" class="txlocalizable"></span> {{ tokenAmount }}</span>
							<div class="token-name" v-if="tx.tokenName">
								<a href=# @click="search(tx.tokenName)">{{ tx.tokenName }}</a>
							</div>
						</td>
					</tr>
					<tr>
						<td class="font-16 font-color-555555 txlocalizable" style="padding-left: 24px;" id="txValue"></td>
						<td v-if=isNatVoteTransfer class="font-16 font-color-000000">{{ natAmount }} NAT</td>
						<td v-else class="font-16 font-color-000000">{{ nasAmount(tx.value) }} NAS</td>
					</tr>

				</table>
			</div>

			<div class="mobile-detail d-md-none">
				<div>
					<span class="txlocalizable" id="txHashTitle"></span>
					<div class="detail monospace">{{ tx.hash }}</div>
				</div>
				<div>
					<span class="txlocalizable" id="txReceiptStatusTitle"></span>
					<td class="detail d-flex align-items-center" v-if="tx.status === 0" style="height: inherit">
							<img class="icon18" src="../../static/img/ic_tx_status_failed.png?v=20190110" />
							<span class="font-color-F04434" style="margin-left: 10px;"><span class="txlocalizable" id="txTxFailStatus"></span> ( {{ tx.executeError }} )</span>
						</td>
						<td class="detail d-flex align-items-center" v-else-if="tx.status === 1" style="height: inherit">
							<img class="icon18" src="../../static/img/ic_tx_status_success.png" />
							<span class="font-color-07A656" style="margin-left: 10px;"><span class="txlocalizable" id="txTxSuccessStatus"></span></span>
						</td>
						<td class="detail d-flex align-items-center" v-else style="height: inherit">
							<img class="icon18" src="../../static/img/ic_tx_status_pending.png" />
							<span class="font-color-F8BB08" style="margin-left: 10px;"><span class="txlocalizable" id="txTxPendingStatus"></span></span>
						</td>
				</div>
				<div>
					<span class="txlocalizable" id="txBlockHeightText"></span>
					<div class="detail">
						<template v-if=tx.isPending>
							<span class="font-color-000000"> <span class="txlocalizable" id="txPendingStatus"></span> </span>
						</template>
						<template v-else>
							<router-link v-if=tx.block v-bind:to='fragApi +"/block/" + tx.block.height'>
								<span>{{tx.block.height}}</span>
							</router-link>
						</template>
					</div>
				</div>
				<div>
					<span class="txlocalizable" id="txTimestampText"></span>
					<div class="detail">{{ timeConversion(tx.timeDiff) }} ago ({{ new Date(tx.timestamp).toString().replace('GMT', 'UTC').replace(/\(.+\)/gi, '') }} | {{ tx.timestamp }})</div>
				</div>
				<div>
					<span class="txlocalizable" id="txFromText2"></span>
					<div class="detail">
						<router-link v-if=tx.from v-bind:to='fragApi +"/address/" + tx.from.hash'>
							<span class="monospace">{{ tx.from.hash }}</span>
						</router-link>
					</div>
				</div>
				<div>
					<span class="txlocalizable" id="txToText2"></span>
					<div v-if="tx.type == 'call'" class="detail">
						<span class="font-color-000000 txlocalizable" style="margin-right: 20px;" id="txContractText"></span>
						<router-link v-if=tx.to v-bind:to='fragApi +"/address/" + tx.to.hash' style="margin-right: 14px;">
							<span class="monospace">{{ tx.to.hash }}</span>
						</router-link>
						<div class="token-name font-color-000000" v-if="isTokenTransfer && tx.tokenName">(<span class="txlocalizable" id="txTokenTextPrefix"></span>){{ tx.tokenName }}<span class="txlocalizable" id="txTokenTextSuffix"></span>)</div>
					</div>
					<div v-else class="detail">
						<router-link v-if=tx.to v-bind:to='fragApi +"/address/" + tx.to.hash'>
							<span class="monospace">{{ tx.to.hash }}</span>
						</router-link>
					</div>
				</div>
				<div v-if=isTokenTransfer>
					<span class="txlocalizable" id="txTokenTransferred2"></span>
					<div class="detail">
						<span class="font-color-000000 txlocalizable" id="txFromText3"></span>
						<router-link class=atpAddress v-if=tx.to v-bind:to='fragApi +"/address/" + tx.from.hash'>
							<span class="monospace">{{ tx.from.hash }}</span>
						</router-link>
						<span class="font-color-000000 txlocalizable" id="txToText3"></span>
						<router-link class=atpAddress v-if=tx.to v-bind:to='fragApi +"/address/" + JSON.parse(JSON.parse(tx.data).Args)[0]'>
							<span class="monospace">{{ JSON.parse(JSON.parse(tx.data).Args)[0] }} </span>
						</router-link>
						<span class="font-color-000000"><span class="txlocalizable" id="txFor2"></span> {{ tokenAmount }}</span>
						<div class="token-name" v-if="tx.tokenName">
							<a href=# @click="search(tx.tokenName)">{{ tx.tokenName }}</a>
						</div>
					</div>
				</div>
				<div>
					Value:
					<div v-if=isNatVoteTransfer class="detail">{{ natAmount }} NAT</div>
					<div v-else class="detail">{{ nasAmount(tx.value) }} NAS</div>
				</div>
			</div>

			<div class="font-24 font-bold font-color-000000 table-title">
				Misc
			</div>
			<div class="explorer-table-container">
				<table class="explorer-table">
					<tr>
						<td class="td-left font-16 font-color-555555" style="padding-left: 24px;"><span id="txGasLimitTitle" class="txlocalizable"></span></td>
						<td class="font-color-000000 font-16">{{ numberAddComma(tx.gasLimit) }}</td>
					</tr>
					<tr>
						<td class="font-16 font-color-555555" style="padding-left: 24px;"><span id="txGasUsedByTx" class="txlocalizable"></span></td>
						<td class="font-color-000000 font-16"><span v-if="tx.isPending === true" id="txPendingText2"></span><span v-else>{{ numberAddComma(tx.gasUsed) }}</span></td>
					</tr>
					<tr>
						<td class="font-16 font-color-555555" style="padding-left: 24px;"><span id="txGasPrice" class="txlocalizable"></span></td>
						<td class="font-color-000000 font-16">{{ toWei(tx.gasPrice) }}</td>
					</tr>
					<tr>
						<td class="font-16 font-color-555555" style="padding-left: 24px;"><span id="txActualTxCostFee" class="txlocalizable"></span></td>
						<td class="font-color-000000 font-16">{{ toWei(tx.txFee) }}</td>
					</tr>
					<tr>
						<td class="font-16 font-color-555555" style="padding-left: 24px;"><span id="txNonce" class="txlocalizable"></span></td>
						<td class="font-color-000000 font-16">{{ tx.nonce }}</td>
					</tr>
					<tr>
						<td class="font-16 font-color-555555" style="padding-left: 24px;"><span id="txTxType" class="txlocalizable"></span></td>
						<td class="font-color-000000 font-16" v-if=" tx.type === 'deploy'">{{ txType }} ( <span id="txContractAddress" class="txlocalizable"></span> <router-link v-if=tx.to v-bind:to='fragApi +"/address/" + tx.contractAddress'> <span> {{tx.contractAddress}}</span> </router-link>)</td>
						<td class="font-color-000000 font-16" v-else>{{ txType }}</td>
					</tr>
					<tr>
						<td class="font-16 font-color-555555" style="padding-left: 24px;"><span id="txSourceType" class="txlocalizable"></span></td>
						<td class="font-color-000000 font-16" v-if=" tx.type === 'deploy'">{{ JSON.parse(tx.data).SourceType }}</td>
						<td v-else></td>
					</tr>
					<tr>
						<td class="font-16 font-color-555555" style="padding-left: 24px;"><span id="txArgs" class="txlocalizable"></span></td>
						<td class="font-color-000000 font-16" v-if=" tx.type === 'deploy'">{{ JSON.parse(tx.data).Args }}</td>
						<td v-else></td>
					</tr>
					<tr>
						<td class="font-16 font-color-555555" style="padding-left: 24px;"><span id="txPayloadData" class="txlocalizable"></span></td>
						<td v-if="tx.type === 'binary'" class=text>
							{{ tx.data }}
						</td>
						<td v-else class=code>
							<a href=# v-on:click.prevent="showOrHidePayload()" style="text-decoration: none;">
								<span class="align-middle font-16 txlocalizable" id="txViewAll"></span>
								<img style="margin-left: 12px;" class="icon16" v-bind:src="isShowPayload ? '../../static/img/ic_payload_arrow_up.png' : '../../static/img/ic_payload_arrow_down.png'" />
							</a>
						</td>
					</tr>
					<tr v-show="isShowPayload === true">
						<td></td>
						<td style="max-width: 10px;">
							<pre><code class="language-javascript" v-html=formatCode></code></pre>
						</td>
					</tr>
				</table>
			</div>
			<!--ATP底部广告位-->
			<div class="flex atlaspAds-bottom" id="atlaspAds-bottom"></div>
			<div class="flex atlaspAds-bottom" id="atlaspAds-mobile"></div>
		</div>
		<!--ATP侧边栏广告位-->
		<div class="flex atlaspAds" id="atlaspAds-side"></div>
	</div>
</template>
<script>
	import { EventBus } from '../events.js';
	import { jsonStrings } from '../l10nstrings.js';
	var jsBeautify = require("js-beautify").js_beautify,
		prism = require("prismjs"),
		api = require("@/assets/api"),
		utility = require("@/assets/utility"),
		appConfig = require("@/assets/app-config"),
		BigNumber = require("bignumber.js"),
		base64 = require("js-base64").Base64;

	require("prismjs/themes/prism.css");

	module.exports = {
		components: {
			"vue-bread": require("@/components/vue-bread").default,
		   "vue-tab-buttons": require("@/components/vue-tab-buttons").default
		},
		computed: {

			formatCode() {
				var lang = prism.languages.javascript;
				// console.log(Object.keys(prism.languages))
				if (this.tx.data) {
					if (this.tx.type === "deploy"){
						return prism.highlight(jsBeautify(JSON.parse(this.tx.data).Source), lang);
					} else if (this.tx.type === "call") {
						return prism.highlight(jsBeautify(this.tx.data), lang);
					} else if (this.tx.type === "protocol") {
						var data = JSON.parse(this.tx.data);
						var code = base64.decode(data.Data);
						var beginIndex = code.indexOf('//');
						var endIndex = code.lastIndexOf('}');
						code = code.substring(beginIndex, endIndex + 1);
						return prism.highlight(code, lang);
					}
				}
				return "";
			},
			txType() {
				// type=binary	  【前端显示：Normal】
				// type=deploy	  【前端显示：deploy contract】
				// type=call		【前端显示：call contract】
				// type=candidate   【前端显示：dpos candidate】
				// type=delegate	【前端显示：dpos delegate】
				if (this.tx) switch (this.tx.type) {
					default:
					case"binary": return"normal";
					case"deploy": return"deploy contract";
					case"call": return"call contract";
					case"candidate": return"dpos candidate";
					case"delegate": return"dpos delegate";
					case"protocol": return"protocol";
				} else
					return"";
			},
			urlChange() {
				if (this.$route.path.indexOf('/tx/') === -1 || !this.$route.params.id) {
					return;
				}
				this.$root.showModalLoading = true;
				api.getTx(this.$route.params.id, o => {
					this.$root.showModalLoading = false;
					this.tx = o;
					// if (!o.tokenName || o.tokenName.length == 0) {
					//	 if (o.to.hash == this.atpAddress()) {
					//		 this.tx.tokenName ="ATP";
					//	 }
					// }
				}, xhr => {
					this.$root.showModalLoading = false;
					this.$router.replace((this.$route.params.api ?"/" + this.$route.params.api :"") +"/404");
				});
			},
			isTokenTransfer() {
				try {
					if (this.tx.type === 'call' && JSON.parse(this.tx.data).Function === 'transfer' && JSON.parse(JSON.parse(this.tx.data).Args).length >= 2) {
						return true;
					}
				} catch (error) {
				}
				return false;
			},
			tokenAmount() {
				BigNumber.config({ DECIMAL_PLACES: this.tx.decimal })
				var amount = BigNumber(JSON.parse(JSON.parse(this.tx.data).Args)[1]);
				var decimals = BigNumber('1e+' + this.tx.decimal);
				return amount.div(decimals).toFormat();
			},
			isNatVoteTransfer() {
				try {
					if (this.tx.type === 'call' && this.tx.to.hash === 'n1pADU7jnrvpPzcWusGkaizZoWgUywMRGMY' && JSON.parse(this.tx.data).Function === 'vote' && JSON.parse(JSON.parse(this.tx.data).Args).length >= 4) {
						return true;
					}
				} catch (error) {
				}
				return false;
			},
			natAmount() {
				BigNumber.config({ DECIMAL_PLACES: 18 })
				var amount = BigNumber(JSON.parse(JSON.parse(this.tx.data).Args)[3]);
				var decimals = BigNumber('1e+18');
				return amount.div(decimals).toFormat();
			},
			// errMsg() {
			//	 if (this.tx.executeError === 'insufficient balance') {
			//		 return 'Insufficient Balance of Transfer Address';
			//	 } else if (this.tx.executeError === 'insufficient gas') {
			//		 return 'Out of Gas';
			//	 } else {
			//		 return 'Contract Execution Failed';
			//	 }
			// }
		},
		data() {
			return {
				fragApi: this.$route.params.api ?"/" + this.$route.params.api :"",
				tab: 0,
				tabButtons: ["Overview"],
				tx: null,
				isShowPayload: false,
				nothing: ""
			};
		},
		methods: {
			removeTempInterval() {
				clearInterval(this.tempInterval);
			},
			checkStaticTranslations() {
				// Unique elements, identified by id attr
				var myLocalizableElements = document.getElementsByClassName("txlocalizable");
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

				myLocalizableElements = document.getElementsByClassName("bread-title");

				totalElements = myLocalizableElements.length;
				for (i = 0; i < totalElements; i++) {
					myLocalizableElements[i].innerText = jsonStrings[this.$selectedLanguage]["txTitle"];
				}
			},
			checkDynamicTranslations() {
				// Multiple elements, identified with name attr
				var myMultiLocalizableElements = document.getElementsByClassName("txmultilocalizable");
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
			},
			showOrHidePayload(){
				this.isShowPayload = !this.isShowPayload;
				// setTimeout(() => {
				//	 window.scrollTo(0, 10000);
				// }, 0);
			},
			numberAddComma(n) {
				return utility.numberAddComma(n);
			},
			timeConversion(ms) {
				return utility.timeConversion(ms);
			},
			toWei(n) {
				return utility.toWei(n);
			},
			nasAmount(n) {
				BigNumber.config({ DECIMAL_PLACES: 18 })
				var amount = BigNumber(n);
				var decimals = BigNumber('1e+18');
				return amount.div(decimals).toFormat();
			},
			atpAddress() {
				var api = this.$route.params.api ? this.$route.params.api :"mainnet";
				return appConfig.apiPrefixes[api].atp;
			},
			search(keyword) {
				if (keyword.trim().length === 0) {
					return;
				}
				this.$root.showModalLoading = true;
				api.getSearch(keyword.trim(), o => {
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
						this.$router.push((this.$route.params.api ? "/" + this.$route.params.api : "") + "/nothing");
					}
				}, () => {
					this.$root.showModalLoading = false;
					this.$router.push((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404");
				});
			}
		},
		mounted() {
			EventBus.$on('changeLanguage', foo => {this.checkStaticTranslations()});
			if (typeof this.$selectedLanguage != 'undefined') {
				this.checkStaticTranslations();
			}
			this.translationsInterval = setInterval(() => {
				this.checkDynamicTranslations();
			}, 1000);
			this.tempInterval = setInterval(() => {
				this.checkStaticTranslations();
				this.removeTempInterval();
			}, 1500);
			if (this.$root.showAtpAds) {
				/*初始化ATPSDK，并设置partnerID (init ATP-SDK ,Set partnerID)*/
				var atpAds = AtlasAds('pbg91eenif2mbsoo3g1qg');

				//获取广告 传入div containerId和广告的宽高（getAd set the containerId and dimension wide high）
				atpAds.getAd('#atlaspAds-bottom', 'nas_1200x100_002');
				atpAds.getAd('#atlaspAds-side', 'nas_360x300_002');
				atpAds.getAd('#atlaspAds-mobile', 'nas_720x200_002');

				//侧栏广告尺寸限制
				window.onresize = function () {
					if (window.innerWidth >= 1600) {
						$('#atlaspAds-side').show();
					} else {
						$('#atlaspAds-side').hide();
					}
				}
				window.onresize();
			}
		},
	};
</script>
