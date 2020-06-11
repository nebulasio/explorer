<template>
  <div id="app">
    <LocaleSwitcher />
    <Header></Header>
    <router-view />
    <Footer></Footer>
    <Modal class="loading" v-show="showModalLoading">
      <div slot="l1" class="progress">
        <div
          class="progress-bar progress-bar-striped progress-bar-animated"
          role="progressbar"
          aria-valuenow="100"
          aria-valuemin="0"
          aria-valuemax="100"
        ></div>
      </div>
    </Modal>
  </div>
</template>

<script>
import LocaleSwitcher from "@/components/LocaleSwitcher.vue";

import Header from "@/components/vue-header";
import Footer from "@/components/vue-footer";
import Modal from "@/components/vue-modal";

export default {
  name: "App",
  components: {
    LocaleSwitcher,
    Header,
    Footer,
    Modal
  }
};
</script>
