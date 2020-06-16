<template>
  <div>
    <a
      class="nav-link"
      href="#"
      id="language-selector"
      role="button"
      data-toggle="dropdown"
      aria-haspopup="true"
      aria-expanded="false"
    >
      <i class="iconfont icon-language"></i>
      {{ $t("Languages") }}
      <i class="iconfont icon-down"></i>
      <!-- <img src=/static/img/icon_arrow_down.png width=12 alt=""> -->
    </a>
    <div class="dropdown-menu" aria-labelledby="language-selector">
      <div>
        <a href="#" class="dropdown-item" @click.prevent="switchLocale('en')"
          ><img src=/static/img/icon-engflag.png width=12 alt="">
          {{ $t("English") }}</a
        >
        <a href="#" class="dropdown-item" @click.prevent="switchLocale('es')"
          ><img src=/static/img/icon-espflag.png width=12 alt="">
          {{ $t("Spanish") }}</a
        >
      </div>
    </div>
  </div>
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

<style lang="scss" scoped>
li {
  text-decoration: underline;
  cursor: pointer;
  a {
    color: #333333;
  }
}

.icon-language {
  font-size: 14px;
}

.icon-down {
  font-size: 12px;
}
</style>
