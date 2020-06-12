import Vue from "vue";
import VueI18n from "vue-i18n";
import en from "@/locales/en.json";
import { i18nConfig } from "@/config";

Vue.use(VueI18n);

export const i18n = new VueI18n({
  locale: i18nConfig.locale || "en",
  fallbackLocale: i18nConfig.fallback_locale || "en",
  messages: { en },
  // 默认情况下回退到 fallbackLocale 会产生两个控制台警告：
  // [vue-i18n] Value of key 'message' is not a string!
  // [vue-i18n] Fall back to translate the keypath 'message' with 'en' locale.
  // set silentTranslationWarn=true, 隐藏警告
  silentTranslationWarn: true
});
