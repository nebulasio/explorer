<style>
.vue-nothing {
  position: relative;
  text-align: center;
}

.vertical-center {
  margin: 0 auto;
  padding-top: 80px;
}

.vue-nothing img {
  margin-top: 50px;
}

.vue-nothing .msg {
  color: #555555;
  font-size: 18px;
}

.vue-nothing a {
  display: block;
  color: black;
  text-decoration: none;
  font-size: 16px;
  width: 160px;
  border: 1px solid rgba(0, 0, 0, 1);
  padding: 7px 7px;
  margin: 46px auto;
}

.vue-nothing a:hover {
  background-color: black;
  color: white;
  text-decoration: none;
}
</style>
<template>
  <div class="vue-nothing ">
    <div class="container vertical-center">
      <div class="msg font-bold">
        {{ $t("nothingNoSearchResults") }}
      </div>
      <img src="/static/img/nothing.png" alt="" width="282px" />
      <router-link :to="$route.params.api ? '/' + $route.params.api : '/'">
        {{ $t("nothingBackHome") }}
      </router-link>
    </div>
  </div>
</template>
