<template>
  <ul>
    <li
      v-for="locale in supportedLocales"
      :key="locale"
      @click="switchLocale(locale)"
    >
      {{ $t("menu." + locale) }}
    </li>
  </ul>
</template>

<script>
import { Trans } from "@/plugins/Translation";

export default {
  name: "LocaleSwitcher",
  computed: {
    supportedLocales() {
      return Trans.supportedLocales;
    }
  },
  methods: {
    switchLocale(locale) {
      if (this.$i18n.locale !== locale) {
        return Trans.changeLocale(locale);
        // const to = this.$router.resolve({ params: { locale } });
        // return Trans.changeLocale(locale).then(() => {
        //   this.$router.push(to.location);
        // });
      }
    }
  }
};
</script>

<style scoped>
li {
  text-decoration: underline;
  color: #459ce7;
  cursor: pointer;
}
</style>
