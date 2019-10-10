<style>
	.vue-404 {
		position: relative;
		text-align: center;
	}

	.vertical-center {
		margin: 0 auto;
		padding-top: 80px;
	}

	.vue-404 img {
		margin-top: 50px;
	}

	.vue-404 .msg {
		color: #555555;
		font-size: 18px;
	}

	.vue-404 a {
		display: block;
		color: black;
		font-size: 16px;
		width: 160px;
		border:1px solid rgba(0,0,0,1);
		padding: 7px 7px;
		margin: 46px auto;
	}

	.vue-404 a:hover {
		background-color: black;
		color: white;
		text-decoration: none;
	}

</style>
<template>
	<div class="vue-404 fullfill">
		<div class="container vertical-center">
			<div class="msg font-bold 404localizable" id="404NotFoundMessage"></div>
			<img src="/static/img/404.png?v=20190116" alt="" width="308px">
			<router-link :to='$route.params.api ? "/" + $route.params.api : "/"'><span id="404BackHome" class="404localizable"></span></router-link>
		</div>
	</div>
</template>
<script>
	import { EventBus } from '../events.js';
	import { jsonStrings } from '../l10nstrings.js';
	module.exports = {
		methods: {
			removeTempInterval() {
				clearInterval(this.tempInterval);
			},
			checkStaticTranslations() {
				// Unique elements, identified by id attr
				var myLocalizableElements = document.getElementsByClassName("404localizable");
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
			}
		},
		mounted() {
			EventBus.$on('changeLanguage', foo => {this.checkStaticTranslations()});
			if (typeof this.$selectedLanguage != 'undefined') {
				this.checkStaticTranslations();
			}
			this.tempInterval = setInterval(() => {
				this.checkStaticTranslations();
				this.removeTempInterval();
			}, 1500);
		}
	}
</script>