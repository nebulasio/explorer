<style>
	.vue-bread {
		background-color: #f7f7f7;
		overflow: auto;
		padding: 10px 0;
	}
	.vue-txs-internal .info-and-pagination .info a {
		color: inherit;
	}

	.vue-txs-internal tr>.ne:nth-child(1),
	.vue-txs-internal tr>.ne:nth-child(2) {
		background-color: gainsboro;
	}

	.vue-txs-internal tr>td:nth-child(3) {
		background-color: #eaeaea;
	}

	.vue-txs-internal td,
	.vue-txs-internal th {
		border-top-color: #ddd;
	}
</style>
<template>
	<!-- https://etherscan.io/txsInternal -->
	<div class="vue-txs-internal fullfill" v-bind:triggerComputed=urlChange>
		<div class="vue-bread">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-auto bread-title font-40 font-bold font-color-000000 txsinternallocalizable" id="txsinternalTitle"></div>
					<div class="col-auto bread-subtitle font-16 font-bold font-color-000000 align-baseline"></div>
				</div>
			</div>
		</div>

		<div class="container mt20">
			<div class="align-items-center info-and-pagination row">
				<div class="col info">
					<div><span class="txsinternallocalizable" id="txsinternalInternalTxFoundPrefix"></span> %1 <span class="txsinternallocalizable" id="txsinternalInternalTxFoundSuffix"></span></div>
					<div>
						(<span class="txsinternallocalizable" id="txsinternalShowingLastPrefix"></span> %2 <span class="txsinternallocalizable" id="txsinternalShowingLastSuffix"></span>)
						<label>
							<a href="?type=all">
								<input type=radio name=type value=all>
								<b class="txsinternallocalizable" id="txsinternalAllTypes"></b>
							</a>
						</label>
						<label>
							<a href="?type=noerror">
								<input type=radio name=type value=noerror>
								<b class="txsinternallocalizable" id="txsinternalNoErrors"></b>
							</a>
						</label>
						<label>
							<a href="?type=error">
								<input type=radio name=type value=error>
								<b class="txsinternallocalizable" id="txsinternalWithErrors"></b>
							</a>
						</label>
					</div>
				</div>
				<vue-pagination class=col-auto></vue-pagination>
			</div>

			<!--	the table
				============================================================ -->
			<table class="mt20 table">
				<tr>
					<th class="txsinternallocalizable" id="txsinternalTableBlock"></th>
					<th class="txsinternallocalizable" id="txsinternalTableAge"></th>
					<th class="txsinternallocalizable" id="txsinternalTableParentTxHash"></th>
					<th class="txsinternallocalizable" id="txsinternalTableType"></th>
					<th class="txsinternallocalizable" id="txsinternalTableFrom"></th>
					<th></th>
					<th class="txsinternallocalizable" id="txsinternalTableTo"></th>
					<th class="txsinternallocalizable" id="txsinternalTableValue"></th>
				</tr>

				<tr>
					<!-- ne = none empty, this class is required to set background-color -->
					<td class=ne>
						<a href="block.html?id=%123456">%Block</a>
					</td>
					<td class=ne>%Age</td>
					<td>
						<span class="fa fa-check" aria-hidden=true></span>
						<a href="tx.html?id=%ParentTxHash">%ParentTxHash</a>
					</td>
					<td>%Type</td>
					<td>
						<a href="address.html?id=%From">%From</a>
					</td>
					<td>
						<span class="fa fa-arrow-right" aria-hidden=true></span>
					</td>
					<td>
						<a href="address.html?id=%To">%To</a>
					</td>
					<td>%Value</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td>
						<span class="fa fa-btc" aria-hidden=true></span>
						<a href="tx.html?id=%ParentTxHash">%ParentTxHash</a>
					</td>
					<td>%Type</td>
					<td>
						<a href="address.html?id=%From">%From</a>
					</td>
					<td>
						<span class="fa fa-arrow-right" aria-hidden=true></span>
					</td>
					<td>
						<a href="address.html?id=%To">%To</a>
					</td>
					<td>%Value</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td>
						<span class="fa fa-check" aria-hidden=true></span>
						<a href="tx.html?id=%ParentTxHash">%ParentTxHash</a>
					</td>
					<td>%Type</td>
					<td>
						<a href="address.html?id=%From">%From</a>
					</td>
					<td>
						<span class="fa fa-arrow-right" aria-hidden=true></span>
					</td>
					<td>
						<a href="address.html?id=%To">%To</a>
					</td>
					<td>%Value</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td>
						<span class="fa fa-check" aria-hidden=true></span>
						<a href="tx.html?id=%ParentTxHash">%ParentTxHash</a>
					</td>
					<td>%Type</td>
					<td>
						<a href="address.html?id=%From">%From</a>
					</td>
					<td>
						<span class="fa fa-arrow-right" aria-hidden=true></span>
					</td>
					<td>
						<a href="address.html?id=%To">%To</a>
					</td>
					<td>%Value</td>
				</tr>
				<tr>
					<td class=ne>
						<a href="block.html?b=123456">%Block</a>
					</td>
					<td class=ne>%Age</td>
					<td>
						<span class="fa fa-remove" aria-hidden=true></span>
						<a href="tx.html?id=%ParentTxHash">%ParentTxHash</a>
					</td>
					<td>%Type</td>
					<td>
						<a href="address.html?id=%From">%From</a>
					</td>
					<td>
						<span class="fa fa-arrow-right" aria-hidden=true></span>
					</td>
					<td>
						<a href="address.html?id=%To">%To</a>
					</td>
					<td>%Value</td>
				</tr>
				<tr>
					<td class=ne>
						<a href="block.html?b=123456">%Block</a>
					</td>
					<td class=ne>%Age</td>
					<td>
						<span class="fa fa-check" aria-hidden=true></span>
						<a href="tx.html?id=%ParentTxHash">%ParentTxHash</a>
					</td>
					<td>%Type</td>
					<td>
						<a href="address.html?id=%From">%From</a>
					</td>
					<td>
						<span class="fa fa-arrow-right" aria-hidden=true></span>
					</td>
					<td>
						<a href="address.html?id=%To">%To</a>
					</td>
					<td>%Value</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td>
						<span class="fa fa-check" aria-hidden=true></span>
						<a href="tx.html?id=%ParentTxHash">%ParentTxHash</a>
					</td>
					<td>%Type</td>
					<td>
						<a href="address.html?id=%From">%From</a>
					</td>
					<td>
						<span class="fa fa-arrow-right" aria-hidden=true></span>
					</td>
					<td>
						<a href="address.html?id=%To">%To</a>
					</td>
					<td>%Value</td>
				</tr>
			</table>

			<vue-pagination right=1></vue-pagination>
		</div>
	</div>
</template>
<script>
	import { EventBus } from '../events.js';
	import { jsonStrings } from '../l10nstrings.js';
	var api = require("@/assets/api");

	module.exports = {
		components: {
			"vue-bread": require("@/components/vue-bread").default,
			"vue-pagination": require("@/components/vue-pagination").default
		},
		computed: {
			urlChange() {
				console.log("在这里下载 txs-internal 信息, 目前的 txs-internal id 是", this.$route.params.id);

				// api.getShowcases(this.$root.apiLang, t => {
				//	 this.arr = t;
				// });
			}
		},
		data() {
			return {
				type: "all" // all, ok, error
			};
		},
		methods: {
			removeTempInterval() {
				clearInterval(this.tempInterval);
			},
			checkStaticTranslations() {
				// Unique elements, identified by id attr
				var myLocalizableElements = document.getElementsByClassName("txsinternallocalizable");
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
				var myMultiLocalizableElements = document.getElementsByClassName("txsinternalmultilocalizable");
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
			}, 1000);
		}
	};
</script>