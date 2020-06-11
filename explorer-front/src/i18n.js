import Vue from "vue";
import VueI18n from "vue-i18n";
import en from "@/locales/en.json";
import { i18nConfig } from "@/config";

Vue.use(VueI18n);

export const i18n = new VueI18n({
  locale: i18nConfig.locale || "en",
  fallbackLocale: i18nConfig.fallback_locale || "en",
  messages: { en }
});
