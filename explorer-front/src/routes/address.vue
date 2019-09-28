<style>
	.vue-address td.out {
		width: 50px;
	}

	.vue-address td.in::before,
	.vue-address td.out::before {
		border-radius: 4px;
		color: white;
		padding: 3px 5px;
	}

	.vue-address td.in::before {
		background-color: var(--green);
		content: "in";
	}

	.vue-address td.out::before {
		background-color: var(--orange);
		content: "out";
	}

	.vue-address {
		background-color: white;
	}

	.vue-address .container .table th {
		border-top: 0;
	}

	.vue-address .container .title {
		overflow: auto;
	}

	.vue-address .txfee {
		font-size: 14px;
		/* font-family: OpenSans; */
		color: rgba(85, 85, 85, 1);
		line-height: 20px;
	}

	.vue-address .fail {
		background: url(../../static/img/warning_icon.png) no-repeat 0 10px;
		padding-left: 28px;
	}

	.vue-address .fail a {
		display: inline-block;
		max-width: 142px;
		overflow: hidden;
		text-overflow: ellipsis;
	}

	.vue-address .tdxxxwddd {
		padding: .75rem 0.4rem;
	}

	/* .contract-creator a {
		max-width: 160px;
		overflow: hidden;
		text-overflow: ellipsis;
	} */

	.contract-creator a {
		position: relative;
	}

	.contract-creator .popover {
		background-color: rgba(0, 0, 0, .8);
		color: white;
		display: none;
		left: 50%;
		top: -50px;
		padding: 10px;
		border-radius: 0px;
		border: none;
		pointer-events: none;
		position: absolute;
		-webkit-transform: translateX(-50%);
		transform: translateX(-50%);
		white-space: nowrap;
		z-index: 1;
	}

	.contract-creator a:hover .popover {
		display: block;
	}

	#dropdown-tokens a {
		margin-right: 5px;
	}

	#dropdown-tokens>* {
		vertical-align: baseline;
	}

	.c000 {
		font-size: 16px;
		/* font-family: OpenSans-Semibold; */
		font-weight: 600;
		color: rgba(0, 0, 0, 1);
		line-height: 20px;
	}

	.txs-hash {
		max-width: 185px;
		overflow: hidden;
		text-overflow: ellipsis;
		vertical-align: center;
		padding: 0;
	}

	.txs-block {
		max-width: 120px;
		overflow: hidden;
		text-overflow: ellipsis;
		vertical-align: center;
		padding: 0;
	}

	/*.txs-from-to {*/
		/*max-width: 168px;*/
	/*}*/

	.txs-from-to a {
		/*max-width: 150px;*/
	}

	.hash-normal {
		height: 20px;
		font-size: 14px;
		/* font-family: OpenSans; */
		color: rgba(0, 87, 255, 1);
		line-height: 20px;
	}

	.hash-failed {
		height: 20px;
		font-size: 14px;
		/* font-family: OpenSans; */
		line-height: 20px;
		color: rgba(240, 68, 52, 1);
	}

	.fromTo {
		/*max-width: 150px;*/
		/* margin-left: 10px; */
		line-height: 24px;
	}

	.amount {
		font-size: 14px;
		/* font-family: OpenSans; */
		color: rgba(0, 0, 0, 1);
		line-height: 20px;
	}

	.overview {
		margin-top: 60px;
		margin-bottom: 30px;
		height: 30px;
		font-size: 24px;
		/* font-family: OpenSans-Semibold; */
		font-weight: 600;
		color: rgba(0, 0, 0, 1);
		line-height: 30px;
	}

	.base-info-key {
		width: 23%;
	}

	.base-info-value-normal {
		height: 20px;
		font-size: 16px;
		/* font-family: OpenSans; */
		color: rgba(0, 0, 0, 1);
		line-height: 20px;
	}

	.base-info-value-num-of-tx {
		height: 20px;
		font-size: 16px;
		/* font-family: OpenSans; */
		color: rgba(0, 87, 255, 1);
		line-height: 20px;
	}

	.base-info-value-token-name {
		height: 20px;
		font-size: 16px;
		/* font-family: OpenSans-Semibold; */
		font-weight: 600;
		color: rgba(0, 87, 255, 1);
		line-height: 20px;
	}

	.text-no-content {
		height: 17px;
		font-size: 12px;
		/* font-family: OpenSans-Semibold; */
		font-weight: 600;
		color: rgba(155, 155, 155, 1);
		line-height: 17px;
	}

	.vue-address .dropdown-menu {
		min-width: initial;
	}

	.vue-address .block {
		margin-right: 8px;
	}

	.vue-address .pl-16 {
		padding-left: 16px;
	}

	.vue-address .pt-10 {
		padding-top: 10px;
	}

	.weight-400 {
		font-weight: 400;
	}

</style>
<template>
	<!-- https://etherscan.io/address/0xea674fdde714fd979de3edf0f56aa9716b898ec8 -->
	<div class="vue-address fullfill" v-bind:triggerComputed=urlChange>

		<vue-bread
			v-bind:title="navTitle"
			v-bind:subtitle="$route.params.id"
			v-bind:subtitlemonospaced="!!$route.params.id"
			v-bind:blockies="$route.params.id">
		</vue-bread>

		<div v-if=obj class="container explorer-table-container">
			<div class="font-24 font-bold font-color-000000 table-title">
				<span id="addressOverview" class="addresslocalizable"></span>
				<span class=c777 v-show=obj.address.alias> | {{ obj.address.alias }}</span>
			</div>
			<table class="explorer-table d-none d-md-table">
				<tr>
					<td class="base-info-key font-16 font-color-555555 pl-16"><span id="addressBalance" class="addresslocalizable"></span>:
					</td>
					<td class="font-16 font-color-000000">
						{{ nasAmount(obj.address.balance) }} NAS
					</td>
				</tr>
				<tr v-if="creator && deployTxHash">
					<td class="base-info-key font-16 font-color-555555 pl-16">
						<span id="addressContractCreator" class="addresslocalizable"></span>:
					</td>
					<td class="contract-creator font-16 font-color-000000">
						<router-link v-bind:to='fragApi + "/address/" + creator'>
							<span>{{ toShortStr(creator) }}</span>
							<div class="popover down-arrow-tip"><span id="addressContractCreatorAddress" class="addresslocalizable"></span></div>
						</router-link>
						, txn
						<router-link v-bind:to='fragApi + "/tx/" + deployTxHash'>
							<span>{{ toShortStr(deployTxHash) }}</span>
							<div class="popover down-arrow-tip"><span id="addressCreatorTxHash" class="addresslocalizable"></span></div>
						</router-link>
					</td>
				</tr>
				<tr>
					<td class="base-info-key font-16 font-color-555555 pl-16"><span id="addressNonceTitle" class="addresslocalizable"></span>:</td>
					<td class="font-16 font-color-000000">{{ obj.address.nonce }}</td>
				</tr>
				<tr>
					<td class="base-info-key font-16 font-color-555555 pl-16">
						<span id="addressTransactionsTitle" class="addresslocalizable"></span>:
					</td>
					<td class="font-16 font-color-000000">{{ obj.txCnt }}</td>
				</tr>
				<tr>
					<td class="base-info-key font-16 font-color-555555 pl-16"><span id="addressMintedTitle" class="addresslocalizable"></span>:</td>
					<td class="font-16 font-color-000000">{{ obj.mintedBlkCnt }}</td>
				</tr>
				<tr v-if="obj.tokenName">
					<td class="base-info-key font-16 font-color-555555 pl-16"><span id="addressTokenTrackerTitle" class="addresslocalizable"></span>:
					</td>
					<td class="font-16 font-color-000000">
						<router-link v-bind:to='fragApi + "/token/" + $route.params.id'>
							<span>{{ obj.tokenName }}</span>
						</router-link>
					</td>
				</tr>
				<tr v-if="!isContract && displayToken">
					<td class="base-info-key font-16 font-color-555555 pl-16"><span id="addressNRC20TokensTitle" class="addresslocalizable"></span>:
					</td>
					<td>
						<div id="dropdown-tokens" data-toggle=dropdown>
							<span class="font-16 font-color-000000">{{ tokenAmount(displayToken.balance, displayToken.decimal) }}</span>
							<router-link v-bind:to='fragApi + "/token/" + displayToken.contract'>
								<span class="font-16 font-bold">{{ displayToken.tokenName }}</span>
							</router-link>
							<img src="../../static/img/icon_arrow_down_black.png" alt="" width="12">
						</div>
						<div class="dropdown-menu">
							<div class="dropdown-item text-right" v-for="(token, i) in validTokens" :key=i
							@click='displayToken = token;'>
								{{ tokenAmount(token.balance, token.decimal) }} {{ token.tokenName }}
							</div>
						</div>
					</td>
				</tr>
				<tr v-if="!isContract">
					<td class="base-info-key font-16 font-color-555555 pl-16" style="vertical-align: top; padding-top: 12px;"><span id="addressQRCodeTitle" class="addresslocalizable"></span>:</td>
					<td style="vertical-align: top; padding-top: 12px;">
						<a class="d-flex font-16 align-items-center" href=# v-on:click="showOrHideQRCode()" style="text-decoration: none;" data-toggle="collapse" data-target="#collapse-mobile" aria-expanded="false" aria-controls="collapseExample">
							<span class="font-16" v-show="isShowQRCode === false"><span id="addressViewToPayTitle" class="addresslocalizable"></span></span>
							<span class="font-16" v-show="isShowQRCode === true"><span id="addressHide" class="addresslocalizable"></span></span>
							<img style="margin-left: 12px; margin-top: 3px; vertical-align: middle;" class="icon16" v-bind:src="isShowQRCode ? '../../static/img/ic_payload_arrow_up.png' : '../../static/img/ic_payload_arrow_down.png'" />
						</a>
						<div class="collapse" id="collapse-mobile">
							<div class="pt-10">
								<qrcode-vue :value="$route.params.id" :size="220" level="H"></qrcode-vue>
								<span class="font-16 detail"><span id="addressScanPrefix" class="addresslocalizable"></span> <a href="https://nano.nebulas.io/index_en.html" target="_blank"> <span id="addressScanSuffix" class="addresslocalizable"></span></a></span>
							</div>
						</div>
					</td>
				</tr>
			</table>

			<div class="mobile-detail d-md-none">
				<div>
					<span id="addressNASBalance" class="addresslocalizable"></span>:
					<div class="detail">{{ nasAmount(obj.address.balance) }} NAS</div>
				</div>
				<div v-if="creator && deployTxHash">
					<span id="addressContractCreatorTitle" class="addresslocalizable"></span>:
					<div class="detail contract-creator font-color-000000">
						<router-link v-bind:to='fragApi + "/address/" + creator'>
							<span>{{ toShortStr(creator) }}</span>
							<!-- <div class="popover">Creator Address</div> -->
						</router-link>
						at txn
						<router-link v-bind:to='fragApi + "/tx/" + deployTxHash'>
							<span>{{ toShortStr(deployTxHash) }}</span>
							<!-- <div class="popover">Creator TxHash</div> -->
						</router-link>
					</div>
				</div>
				<div>
					<span id="addressNonceTitle" class="addresslocalizable"></span>:
					<div class="detail">{{ obj.address.nonce }}</div>
				</div>
				<div>
					<span id="addressNumberOfTransactions" class="addresslocalizable"></span>:
					<div class="detail">{{ obj.txCnt }}</div>
				</div>
				<div>
					<span id="addressMinted" class="addresslocalizable"></span>:
					<div class="detail">{{ obj.mintedBlkCnt }}</div>
				</div>
				<div v-if="obj.tokenName">
					<span id="addressTokenTracker" class="addresslocalizable"></span>:
					<div class="detail">
						<router-link v-bind:to='fragApi + "/token/" + $route.params.id'>
							<span>{{ obj.tokenName }}</span>
						</router-link>
					</div>
				</div>
				<div v-if="!isContract && displayToken">
					<span id="addressNRC20Tokens" class="addresslocalizable"></span>:
					<div class="detail">
						<div id="dropdown-tokens" data-toggle=dropdown>
							<span class="font-color-000000">{{ tokenAmount(displayToken.balance, displayToken.decimal) }}</span>
							<router-link v-bind:to='fragApi + "/token/" + displayToken.contract'>
								<span class="font-bold">{{ displayToken.tokenName }}</span>
							</router-link>
							<img src="../../static/img/icon_arrow_down_black.png" alt="" width="12">
						</div>
						<div class="dropdown-menu">
							<div class="dropdown-item text-right" v-for="(token, i) in validTokens" :key=i
							@click='displayToken = token;'>
								{{ tokenAmount(token.balance, token.decimal) }} {{ token.tokenName }}
							</div>
						</div>
					</div>
				</div>
				<div v-if="!isContract">
					<span id="addressQRCode" class="addresslocalizable"></span>:
					<div>
						<a href=# v-on:click.prevent="showOrHideQRCode()" style="text-decoration: none;">
							<span class="align-middle font-16 weight-400" v-show="isShowQRCode === false"><span id="addressViewToPay" class="addresslocalizable"></span></span>
							<span class="align-middle font-16 weight-400" v-show="isShowQRCode === true"><span id="addressHideTitle" class="addresslocalizable"></span></span>
							<img style="margin-left: 12px;" class="icon16" v-bind:src="isShowQRCode ? '../../static/img/ic_payload_arrow_up.png' : '../../static/img/ic_payload_arrow_down.png'" />
						</a>
						<div v-show="isShowQRCode === true">
							<div class="pt-10">
								<qrcode-vue :value="$route.params.id" :size="220" level="H"></qrcode-vue>
								<span class="font-16 detail"><span id="addressScanUsing" class="addresslocalizable"></span> <a href="https://nano.nebulas.io/index_en.html" target="_blank"> <span id="addressNASNano" class="addresslocalizable"></span></a></span>
							</div>
						</div>
					</div>
				</div>
			</div>

			<vue-tab-buttons class=mt50 v-bind:arr=tabButtons v-bind:tab.sync=tab></vue-tab-buttons>
			<div class=mt20></div>

			<!-- ============================ Transactions ================================ -->
			<div class="tab" v-show="tab == 1">
				<div v-if="txs.length" class="d-block d-md-flex flex-row align-items-center">
					<div class="col mr-auto px-0 font-16 font-bold font-color-000000">
						<span id="addressLatestTitle" class="addresslocalizable"></span> {{ txs.length }} {{ txs.length > 1 ? 'txns' : 'txn' }} <span id="addressFromTotal" class="addresslocalizable"></span> {{ numberAddComma(obj.txCnt) }} <span v-if="obj.txCnt > 1" id="addressTextTransactions" class="addresslocalizable"></span><span v-else id="addressTextTransaction" class="addresslocalizable"></span>

						<span v-if="obj.pendingTxCnt != 0">({{ obj.pendingTxCnt }} <span v-if="obj.pendingTxCnt > 1" id="addressPendingTxsText" class="addresslocalizable"></span><span v-else id="addressPendingTxText" class="addresslocalizable"></span>)</span>

					</div>
					<div class="col-auto px-0">
						<router-link class="d-block d-md-inline-block align-middle mt-1 mt-md-0"
									 v-bind:to='fragApi + "/txs?a=" + $route.params.id'>
							<span class="font-16 addresslocalizable" id="addressViewAll"></span>
						</router-link>
						<span v-if="obj.pendingTxCnt != 0">
							<span class="d-none d-md-inline-block px-2">|</span>
							<router-link class="d-block d-md-inline-block align-middle mt-1 mt-md-0"
										v-bind:to='fragApi + "/txs?a=" + $route.params.id + "&isPending=true" '>
								<span class="font-16"><span class="addressViewAll2"></span> {{ obj.pendingTxCnt }} <span v-if="obj.pendingTxCnt > 1" id="addressPendingTxTextPlural" class="addresslocalizable"></span><span v-else id="addressPendingTxTextSingular" class="addresslocalizable"></span></span>
							</router-link>
						</span>
					</div>
				</div>

				<div class="explorer-table-container">
					<table v-if="txs.length" class="mt20 explorer-table list-table">
						<tr class="font-12 font-bold font-color-000000" style="height: 46px; background-color: #e8e8e8;">
							<th v-if="isContract"></th>
							<th v-else style="width: 50px;"></th>
							<th class="addresslocalizable" id="addressTableTxHash"></th>
							<th class="addresslocalizable" id="addressTableTxBlock"></th>
							<th class="addresslocalizable" id="addressTableTxAge"></th>
							<th class="addresslocalizable" id="addressTableTxFrom"></th>
							<th></th>
							<th class="addresslocalizable" id="addressTableTxTo"></th>
							<th class="align-right addresslocalizable" id="addressTableTxValue"></th>
							<th class="align-right pr-3 addresslocalizable" id="addressTableTxTxFee"></th>
						</tr>

						<tr v-for="(o, i) in txs" :key="i" v-bind:class="{'tr-dark' : isDark(i)}">
							<td v-if="isContract">
								<img v-if="o.status===0" class="icon40"
									src="../../static/img/ic_tx_failed.png"/>
							</td>
							<td v-else>
								<img class="icon40" v-bind:src="statusIcon(o,o.status)"/>
							</td>
							<td class="txs-hash">
								<router-link v-bind:to='fragApi + "/tx/" + o.hash'>
									<span v-bind:class="[o.status===0 ? 'hash-failed' : 'hash-normal', 'monospace']">{{ o.hash }}</span>
								</router-link>
							</td>
							<td class="txs-block">
								<router-link class="font-14"
											v-if=o.block.height
											v-bind:to='fragApi + "/block/" + o.block.height'>
									<span>{{ o.block.height }}</span>
								</router-link>
								<i class="font-14 font-color-000000 addresslocalizable" v-else id="addressPendingText"></i>
							</td>
							<td class="time font-color-555555 font-14">
								<div>
									<div><span id="addressTimeDiffPrefix" class="addresslocalizable"></span>{{ timeConversion(o.timeDiff) }}<span id="addressTimeDiffSuffix" class="addresslocalizable"></span></div>
									<div class="down-arrow-tip">{{ new Date(o.timestamp).toString().replace('GMT', 'UTC').replace(/\(.+\)/gi, '') }} | {{ o.timestamp }}</div>
								</div>
							</td>
							<td class="tdxxxwddd txs-from-to monospace">
								<vue-blockies v-bind:address='o.from.alias || o.from.hash'></vue-blockies>
								<span class="fromTo font-14 font-color-000000" v-if="o.from.hash == $route.params.id">{{ o.from.alias || o.from.hash }}</span>
								<router-link v-else v-bind:to='fragApi + "/address/" + o.from.hash'>
									<span class="fromTo font-14">{{ o.from.alias || o.from.hash }}</span>
								</router-link>
							</td>
							<td style="padding: 10px;">
								<img class="icon16" src="../../static/img/ic_arrow_right.png"/>
							</td>
							<td class="tdxxxwddd txs-from-to monospace">
								<div v-if="o.type==='call'" class="container-tip">
									<span class="tip down-arrow-tip font-15 shadow addresslocalizable" id="addressSmartContract"></span>
									<img class="icon24" src="../../static/img/icon_tx_type_contract.png" />
								</div>
								<vue-blockies v-bind:address='o.to.alias || o.to.hash'></vue-blockies>
								<span class="fromTo font-14 font-color-000000" v-if="o.to.hash == $route.params.id">{{ o.to.alias || o.to.hash }}</span>
								<router-link v-else v-bind:to='fragApi + "/address/" + o.to.hash'>
									<span class="fromTo font-14 ">{{ o.to.alias || o.to.hash }}</span>
								</router-link>
							</td>
							<td v-if=isNatVoteTransfer(o) class="amount align-right">{{ natAmount(o) }} NAT</td>
							<td v-else class="amount align-right">{{ tokenAmount(o.value, o.decimal) }} NAS</td>
							<td class="txfee align-right pr-3">
								<span v-if=o.block.height>{{ toWei(o.txFee) }}</span>
								<i v-else class="addresslocalizable" id="addressPendingText2"></i>
							</td>
						</tr>
					</table>
				</div>

				<div v-if="txs.length" class="d-block d-md-flex flex-row align-items-center justify-content-end mt20">
					<div class="col-auto px-0">
						<router-link class="d-block d-md-inline-block align-middle mt-1 mt-md-0"
									 v-bind:to='fragApi + "/txs?a=" + $route.params.id'>
							<span class="font-16"><span class="addresslocalizable" id="addressViewAll3"></span> {{ obj.txCnt }} <span v-if="obj.txCnt > 1" class="addresslocalizable" id="addressTxs"></span><span v-else class="addresslocalizable" id="addressTx"></span></span>
						</router-link>
						<span v-if="obj.pendingTxCnt != 0">
							<span class="d-none d-md-inline-block px-2">|</span>
							<router-link class="d-block d-md-inline-block align-middle mt-1 mt-md-0"
										v-bind:to='fragApi + "/txs?a=" + $route.params.id + "&isPending=true" '>
								<span class="font-16"><span id="addressViewAll4" class="addresslocalizable"></span> {{ obj.pendingTxCnt }} <span v-if="obj.pendingTxCnt > 1" id="addressPendingTextPlural" class="addresslocalizable"></span><span v-else id="addressPendingTextSingular" class="addresslocalizable"></span></span>
							</router-link>
						</span>
					</div>
				</div>

				<div v-if="txs.length===0" v-show="tab===1"
					 style="left: 0;right:0;text-align:center; padding-top: 76px; padding-bottom: 80px;">
					<img style="width: 131px; height: 142px;" src="/static/img/no_content.png?v=20190117"/>
					<br/>
					<div style="margin-top: 12px;">
						<span class="text-no-content addresslocalizable" id="addressNoContent"></span>
					</div>
				</div>
			</div>

			<!-- ============================ NRC20 Transactions ================================ -->
			<div class="tab" v-show="tab === 2 && !isContract">
				<div v-if="nrc20TxList.length" class="d-block d-md-flex flex-row align-items-center">
					<div class="col mr-auto px-0 font-16 font-bold font-color-000000">
						<span v-if="nrc20TxCnt > 1" id="addressLatestTitlePlural" class="addresslocalizable"></span><span v-else id="addressLatestTitleSingular" class="addresslocalizable"></span> {{ nrc20TxList.length }} {{ nrc20TxList.length > 1 ? 'txs' : 'tx' }} <span id="addressFromTotal2" class="addresslocalizable"></span> {{ numberAddComma(nrc20TxCnt) }} <span v-if="nrc20TxCnt > 1" class="addresslocalizable" id="addressTransactionsPlural"></span><span v-else class="addresslocalizable" id="addressTransactionsSingular"></span>
					</div>
					<div class="col-auto px-0">
						<router-link class="d-block d-md-inline-block align-middle mt-1 mt-md-0"
									 v-bind:to='fragApi + "/txs-nrc20?a=" + $route.params.id'>
							<span class="font-16"><span id="addressViewAll5" class="addresslocalizable"></span> {{ nrc20TxCnt }} {{ nrc20TxCnt > 1 ? 'txs' : 'tx'}}</span>
						</router-link>
					</div>
				</div>

				<div class="explorer-table-container">
					<table v-if="nrc20TxList.length" class="mt20 explorer-table list-table">
						<tr class="font-12 font-bold font-color-000000" style="height: 46px; background-color: #e8e8e8;">
							<th style="width: 50px;"></th>
							<th class="addresslocalizable" id="addressTableTxHash2"></th>
							<th class="addresslocalizable" id="addressTableBlock2"></th>
							<th class="addresslocalizable" id="addressTableAge2"></th>
							<th class="addresslocalizable" id="addressTableFrom2"></th>
							<th></th>
							<th class="addresslocalizable" id="addressTableTo2"></th>
							<th class="align-right addresslocalizable" id="addressTableValue2"></th>
							<th class="align-right pr-3 addresslocalizable" id="addressTableTxFee2"></th>
						</tr>

						<tr v-for="(o, i) in nrc20TxList" :key="i">
							<td><img class="icon40" v-bind:src="statusIcon(o,o.status)"/></td>
							<td class="txs-hash">
								<router-link v-bind:to='fragApi + "/tx/" + o.hash'>
									<span v-bind:class="[o.status===0 ? 'hash-failed' : 'hash-normal', 'monospace']">{{ o.hash }}</span>
								</router-link>
							</td>

							<td class="txs-block">
								<router-link class="font-14"
											v-if=o.block.height
											v-bind:to='fragApi + "/block/" + o.block.height'>
									<span>{{ o.block.height }}</span>
								</router-link>
								<i class="font-14 font-color-000000 addresslocalizable" v-else id="addressPendingText3"></i>
							</td>
							<td class="time font-color-555555 font-14">
								<div>
									<div>{{ timeConversion(o.timeDiff) }} ago</div>
									<div class="down-arrow-tip">{{ new Date(o.timestamp).toString().replace('GMT', 'UTC').replace(/\(.+\)/gi, '') }} | {{ o.timestamp }}</div>
								</div>
							</td>
							<td class="tdxxxwddd txs-from-to monospace">
								<vue-blockies v-bind:address='o.from.alias || o.from.hash'></vue-blockies>
								<span class="fromTo font-14 font-color-000000" v-if="o.from.hash == $route.params.id">{{ o.from.alias || o.from.hash }}</span>
								<router-link v-else v-bind:to='fragApi + "/address/" + o.from.hash'>
									<span class="fromTo font-14 ">{{ o.from.alias || o.from.hash }}</span>
								</router-link>
							</td>
							<td style="padding: 10px;">
								<img class="icon16" src="../../static/img/ic_arrow_right.png"/>
							</td>
							<td class="tdxxxwddd txs-from-to monospace">
								<vue-blockies v-bind:address='o.to.alias || o.to.hash'></vue-blockies>
								<span class="fromTo font-14 font-color-000000" v-if="o.to.hash == $route.params.id">{{ o.to.alias || o.to.hash }}</span>
								<router-link v-else v-bind:to='fragApi + "/address/" + o.to.hash'>
									<span class="fromTo font-14 ">{{ o.to.alias || o.to.hash }}</span>
								</router-link>
							</td>
							<td class="amount align-right">{{ tokenAmount(o.value, o.decimal) }} {{ o.tokenName || '' }}
							</td>
							<td class="txfee align-right pr-3">
								<span v-if=o.block.height>{{ toWei(o.txFee) }}</span>
								<i v-else>(pending)</i>
							</td>
						</tr>
					</table>
				</div>

				<div v-if="nrc20TxList.length" class="d-block d-md-flex flex-row align-items-center justify-content-end mt20">
					<div class="col-auto px-0">
						<router-link class="d-block d-md-inline-block align-middle mt-1 mt-md-0"
									 v-bind:to='fragApi + "/txs-nrc20?a=" + $route.params.id'>
							<span class="font-16"><span class="addresslocalizable" id="addressViewAll6"></span> {{ nrc20TxCnt }} {{ nrc20TxCnt > 1 ? 'txs' : 'tx'}}</span>
						</router-link>
					</div>
				</div>

				<div v-if=isNoNrc20Tx
					 style="left: 0;right:0;text-align:center; padding-top: 76px; padding-bottom: 80px;">
					<img style="width: 131px; height: 142px;" src="/static/img/no_content.png?v=20190117"/>
					<br/>
					<div style="margin-top: 12px;">
						<span class="text-no-content addresslocalizable" id="addressNoContent2"></span>
					</div>
				</div>
			</div>


			<!-- =========================== NAX Changes ================================= -->
			<div class="tab" v-show="tab === 3 && !isContract">
				<div class="explorer-table-container">
					<table v-if="naxChangeList.length" class="mt20 explorer-table list-table">
						<tr class="font-12 font-bold font-color-000000" style="height: 46px; background-color: #e8e8e8;">
							<th style="width: 100px;"></th>
							<th class="addresslocalizable" id="addressTableValue3"></th>
							<th class="addresslocalizable" id="addressTableAge3"></th>
							<th class="addresslocalizable" id="addressTableBlock3"></th>
							<th style="width: 25%;" class="addresslocalizable" id="addressTableSource"></th>
						</tr>
						<tr v-for="(o, i) in naxChangeList" :key="i">
							<td class="text-center"><img :src="natIcon(o.profit)" width="30px"/></td>
							<td class="amount">{{ tokenAmount(o.profit, 9) }} NAX</td>
							<td class="time font-color-555555 font-14">
								<div>
									<div><span class="addresslocalizable" id="addressTimeStampPrefix"></span>{{ timeConversion(new Date() - o.timestamp) }} <span class="addresslocalizable" id="addressTimeStampSuffix"></span></div>
									<div class="down-arrow-tip">{{ new Date(o.timestamp).toString().replace('GMT', 'UTC').replace(/\(.+\)/gi, '') }} | {{ o.timestamp }}</div>
								</div>
							</td>
							<td class="txs-block">
								<router-link class="font-14"
											v-if=o.block
											v-bind:to='fragApi + "/block/" + o.block'>
									<span>{{ o.block }}</span>
								</router-link>
								<i class="font-14 font-color-000000" v-else><span id="addressPendingText4" class="addresslocalizable"></span></i>
							</td>
							<td class="font-14">
								<div v-if="o.source === 0" class="addresslocalizable" id="addressPledgeRewards"></div>
							</td>
						</tr>
					</table>
				</div>

				<vue-pagination v-if="naxChangeList.length" v-bind:current=currentPage right=1 v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext
				v-on:prev=onPrev v-on:to=onTo></vue-pagination>

				<div v-if=isNoNaxChanges
					 style="left: 0;right:0;text-align:center; padding-top: 76px; padding-bottom: 80px;">
					<img style="width: 131px; height: 142px;" src="/static/img/no_content.png?v=20190117"/>
					<br/>
					<div style="margin-top: 12px;">
						<span class="text-no-content addresslocalizable" id="addressNoContent3"></span>
					</div>
				</div>
			</div>

			<!-- =========================== NAT Changes ================================= -->
			<div class="tab" v-show="tab === 4 && !isContract">
				<div class="explorer-table-container">
					<table v-if="natChangeList.length" class="mt20 explorer-table list-table">
						<tr class="font-12 font-bold font-color-000000" style="height: 46px; background-color: #e8e8e8;">
							<th style="width: 100px;"></th>
							<th class="addresslocalizable" id="addressTableValue4"></th>
							<th class="addresslocalizable" id="addressTableAge4"></th>
							<th class="addresslocalizable" id="addressTableBlock4"></th>
							<th style="width: 25%;" class="addresslocalizable" id="addressTableSource2"></th>
						</tr>
						<tr v-for="(o, i) in natChangeList" :key="i">
							<td class="text-center"><img :src="natIcon(o.amount)" width="30px"/></td>
							<td class="amount">{{ tokenAmount(o.amount, 18) }} NAT</td>
							<td class="time font-color-555555 font-14">
								<div>
									<div><span class="addresslocalizable" id="addressTimeStampPrefix2"></span>{{ timeConversion(new Date() - o.timestamp) }}<span class="addresslocalizable" id="addressTimeStampSuffix2"></span></div>
									<div class="down-arrow-tip">{{ new Date(o.timestamp).toString().replace('GMT', 'UTC').replace(/\(.+\)/gi, '') }} | {{ o.timestamp }}</div>
								</div>
							</td>
							<td class="txs-block">
								<router-link class="font-14"
											v-if=o.block
											v-bind:to='fragApi + "/block/" + o.block'>
									<span>{{ o.block }}</span>
								</router-link>
								<i class="font-14 font-color-000000 addresslocalizable" v-else id="addressPendingText5"></i>
							</td>
							<td class="font-14">
								<div v-if="o.source === 0">
									<router-link v-bind:to='fragApi + "/tx/" + o.txHash'>
										<span>tx# {{o.txHash.slice(0, 6) + '...' + o.txHash.slice(o.txHash.length - 6)}}</span>
									</router-link>
								</div>
								<div v-if="o.source === 1" class="addresslocalizable" id="addressNRIncentive"></div>
								<div v-if="o.source === 2" class="addresslocalizable" id="addressPledgeRewards"></div>
								<div v-if="o.source === 3">
									<span class="addresslocalizable" id="addressNATVote"></span>
									<router-link v-bind:to='fragApi + "/tx/" + o.txHash' class="ml-2">
										<span>tx# {{o.txHash.slice(0, 6) + '...' + o.txHash.slice(o.txHash.length - 6)}}</span>
									</router-link>
								</div>
							</td>
						</tr>
					</table>
				</div>

				<vue-pagination v-if="natChangeList.length" v-bind:current=currentPage right=1 v-bind:total=totalPage v-on:first=onFirst v-on:last=onLast v-on:next=onNext
				v-on:prev=onPrev v-on:to=onTo></vue-pagination>

				<div v-if=isNoNatChanges
					 style="left: 0;right:0;text-align:center; padding-top: 76px; padding-bottom: 80px;">
					<img style="width: 131px; height: 142px;" src="/static/img/no_content.png?v=20190117"/>
					<br/>
					<div style="margin-top: 12px;">
						<span class="text-no-content addresslocalizable" id="addressNoContent4"></span>
					</div>
				</div>
			</div>


			<!-- ============================== code ============================== -->
			<div class=tab v-show="tab == 2 && isContract">
				<table class="mt20 table">
					<tr>
						<pre><code class=language-javascript v-html=formatCode></code></pre>
					</tr>
				</table>
			</div>

		</div>
	</div>
</template>
<script>
	import { EventBus } from '../events.js';
	import { jsonStrings } from '../l10nstrings.js';
	var api = require("@/assets/api"),
		prism = require("prismjs"),
		jsBeautify = require("js-beautify").js_beautify,
		utility = require("@/assets/utility"),
		BigNumber = require("bignumber.js"),
		base64 = require("js-base64").Base64;
	import QrcodeVue from 'qrcode.vue'

	module.exports = {
		components: {
			"vue-bread": require("@/components/vue-bread").default,
			"vue-pagination": require("@/components/vue-pagination").default,
			"vue-tab-buttons": require("@/components/vue-tab-buttons").default,
			"vue-blockies": require("@/components/vue-blockies").default,
			QrcodeVue
		},
		computed: {
			tabButtons() {
				var buttons = [jsonStrings[this.$selectedLanguage]["addressButtonsTransactions"], jsonStrings[this.$selectedLanguage]["addressButtonsNRC20Tokens"], jsonStrings[this.$selectedLanguage]["addressButtonsNax"], jsonStrings[this.$selectedLanguage]["addressButtonsNat"]];
				if (this.$route.params.api === 'testnet') {
					buttons = [jsonStrings[this.$selectedLanguage]["addressButtonsTransactions"], jsonStrings[this.$selectedLanguage]["addressButtonsNRC20Tokens"]];//
				}
				if (this.isContract) {
					buttons = [jsonStrings[this.$selectedLanguage]["addressButtonsTransactions"], jsonStrings[this.$selectedLanguage]["addressButtonsContractCode"]];
				}
				return buttons;
			},
			formatCode() {
				var lang = prism.languages.javascript;

				if (this.contractCode) {
					var code = JSON.parse(this.contractCode);
					if (code.Source) {
						return prism.highlight(jsBeautify(code.Source), lang);
					}
				}
				return "0x0";
			},
			urlChange() {
				if (!this.$route.path.startsWith('/address/') || !this.$route.params.id) {
					return;
				}
				this.obj = null;
				this.tab = 1;
				this.txs = [];
				this.tokens = null;
				this.displayToken = null;
				this.decimal = null;
				this.isContract = false;
				this.contract = null;
				this.creator = null;
				this.deployTxHash = null;
				this.contractCode = null;
				this.nrc20TxList = [];
				this.nrc20TxCnt = 0;
				this.isNoNrc20Tx = false;

				this.natChangeList = [];
				this.isNoNatChanges = false;
				this.totalPage = 0;
				this.currentPage = 0;

				this.naxChangeList = [];
				this.isNoNaxChanges = false;

				this.$root.showModalLoading = true;
				api.getAddress(this.$route.params.id, o => {
					this.$root.showModalLoading = false;
					this.minted = o.mintedBlkList;
					this.obj = o;
					this.decimal = o.decimal;
					this.tokens = o.tokens;
					this.txs = o.txList;
					this.contractCode = o.contractCode;
					this.creator = o.creator;
					this.deployTxHash = o.deployTxHash;
					this.isContract = o.address.type == 1;
					if (o.address.type == 1) {// this is a smart contract address
						api.getTransactionByContract({address: o.address.hash}, this.$route.params.api, (data) => {
							var data = JSON.parse(data);
							if (data && data.result && data.result.data) {
								this.contract = data.result;
								this.creator = this.contract.from;
								this.deployTxHash = this.contract.hash;
								this.contractCode = base64.decode(data.result.data);
							}
						})
					}

					var token = this.tokens[0];
					for (var index in this.tokens) {
						if (this.tokens[index].tokenName === 'NAX') {
							token = this.tokens[index];
							break;
						}
					}
					this.displayToken = token;

				}, xhr => {
					this.$root.showModalLoading = false;
					this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404");
				});
			},
			navTitle() {
				return this.isContract ? "Contract" : "Address";
			},
			validTokens() {
				let tokens = this.tokens.filter(item => {return item.balance !== 0 || item.tokenName === 'NAX'});
				return tokens.sort((a, b) => {
					if (a.tokenName === 'NAX' || b.tokenName === 'NAX') {
						return a.tokenName === 'NAX' ? -1 : 1;
					} else if (a.tokenName === 'NAT' || b.tokenName === 'NAT') {
						return a.tokenName === 'NAT' ? -1 : 1;
					} else if (a.tokenName === 'ATP' || b.tokenName === 'ATP') {
						return a.tokenName === 'ATP' ? 1 : -1;
					}
					return a.tokenName < b.tokenName;
				});
			}
		},
		data() {
			return {
				fragApi: this.$route.params.api ? "/" + this.$route.params.api : "",
				minted: [],
				obj: null,
				tab: 0,
				txs: [],
				tokens: null,
				displayToken: null,
				decimal: null,
				isContract: false,
				contract: null,
				creator: null,
				deployTxHash: null,
				contractCode: null,
				nrc20TxList: [],
				nrc20TxCnt: 0,
				isNoNrc20Tx: false,
				natChangeList: [],
				isNoNatChanges: false,
				naxChangeList: [],
				isNoNaxChanges: false,
				totalPage: 0,
				currentPage: 0,
				isShowQRCode: false
			};
		},
		methods: {
			removeTempInterval() {
				clearInterval(this.tempInterval);
			},
			checkStaticTranslations() {
				// Unique elements, identified by id attr
				var myLocalizableElements = document.getElementsByClassName("addresslocalizable");
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
				// Other specific methods for unique elements.

				var foo = this.$route.fullPath.split("/");
				if(foo[1]=="address") {
					myLocalizableElements = document.getElementsByClassName("bread-title");

					totalElements = myLocalizableElements.length;
					for (i = 0; i < totalElements; i++) {
						myLocalizableElements[i].innerText = jsonStrings[this.$selectedLanguage]["addressTitle"];
					}
				}
			},
			checkDynamicTranslations() {
				var myMultiLocalizableElements = document.getElementsByClassName("addressmultilocalizable");
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
			showOrHideQRCode(){
				this.isShowQRCode = !this.isShowQRCode;
			},
			isDark(i) {
				return (i % 2 === 0);
			},
			statusIcon(tx, status) {
				let imgPath = "/static/img/";
				var inOrOut;
				if (tx.from.hash === this.$route.params.id)
					inOrOut = "out";
				else if (tx.to.hash === this.$route.params.id)
					inOrOut = "in";
				else
					inOrOut = "";

				if (status === 1) {
					if (inOrOut === "out") {
						return imgPath + "ic_tx_sent.png"
					} else if (inOrOut === "in") {
						return imgPath + "ic_tx_received.png"
					} else {
						return ""
					}
				} else if (status === null) {
					if (inOrOut === "out") {
						return imgPath + "ic_tx_send_pending.png"
					} else if (inOrOut === "in") {
						return imgPath + "ic_tx_receive_pending.png"
					} else {
						return ""
					}
				} else {
					return imgPath + "ic_tx_failed.png"
				}
			},
			failClass(o) {
				if (o.status == 0)
					return "!";
				else
					return " ";
			},
			numberAddComma(n) {
				return utility.numberAddComma(n);
			},
			timeConversion(ms) {
				return utility.timeConversion(ms);
			},
			timeConversionSec(ms) {
				return utility.timeConversionSec(ms);
			},
			toWei(n) {
				return utility.toWei(n);
			},
			easyNumber(n) {
				return utility.easyNumber(n);
			},
			nasAmount(n) {
				BigNumber.config({ DECIMAL_PLACES: 18 })
				var amount = BigNumber(n);
				var decimals = BigNumber('1e+18');
				return amount.div(decimals).toFormat().padDecimal();
			},
			tokenAmount(n, decimals) {
				decimals = decimals || 18;
				BigNumber.config({ DECIMAL_PLACES: decimals })
				var amount = BigNumber(n).abs();
				var decimals = BigNumber('1e+' + decimals);
				return amount.div(decimals).toFormat().shortAmount();
			},
			toShortStr(s) {
				if (s.length > 20) {
					return s.substring(0, 17) + '...';
				}
				return s;
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
			},
			natIcon(data) {
				var str = data + '';
				if (str.startsWith('-')) {
					return "/static/img/icon_nat_out.png";
				}
				return "/static/img/icon_nat_in.png";
			},
			nav(n) {
				this.$root.showModalLoading = true;

				if (this.tab === 3) {
					api.getNaxChanges(this.$route.params.id, n || 1, 25, o => {
						this.$root.showModalLoading = false;
						this.naxChangeList = o.list || [];
						this.isNoNaxChanges = this.naxChangeList.length === 0;
						this.totalPage = o.totalPage;
						this.currentPage = o.currentPage;
					}, xhr => {
						this.$root.showModalLoading = false;
						this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404");
					});
				} else if (this.tab === 4) {
					api.getNatChanges(this.$route.params.id, n || 1, 25, o => {
						this.$root.showModalLoading = false;
						this.natChangeList = o.list || [];
						this.isNoNatChanges = this.natChangeList.length === 0;
						this.totalPage = o.totalPage;
						this.currentPage = o.currentPage;
					}, xhr => {
						this.$root.showModalLoading = false;
						this.$router.replace((this.$route.params.api ? "/" + this.$route.params.api : "") + "/404");
					});
				}
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
				//this.removeTempInterval();
			}, 1000);
		},
		watch: {
			tab: function (newTab, oldTab) {
				if (!this.isContract && newTab == 2 && this.nrc20TxList.length === 0) {
					this.$root.showModalLoading = true;
					api.getNrc20Txs(this.$route.params.id, 1, o => {
						this.$root.showModalLoading = false;
						this.nrc20TxList = o.txnList || [];
						this.nrc20TxCnt = o.txnCnt;
						this.isNoNrc20Tx = this.nrc20TxCnt === 0;
					}, xhr => {
						this.$root.showModalLoading = false;
					});
				} else if (!this.isContract && newTab == 3) {
					this.naxChangeList = [];
					this.nav(1);
				} else if (!this.isContract && newTab == 4) {
					this.natChangeList = [];
					this.nav(1);
				}
			}
		}
	};
</script>
