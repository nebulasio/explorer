<template>
  <div id="app">
    <main class="main-container">
      <Header></Header>
      <router-view />
    </main>
    <Footer></Footer>
    <Modal class="loading" v-show="this.$root.showModalLoading">
      <template v-slot:l1>
        <b-spinner label="Loading..."></b-spinner>
      </template>
    </Modal>
  </div>
</template>

<script>
import Header from "@/components/vue-header";
import Footer from "@/components/vue-footer";
import Modal from "@/components/vue-modal";

export default {
  name: "App",
  components: {
    Header,
    Footer,
    Modal
  }
};
</script>
