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
  border: 1px solid rgba(0, 0, 0, 1);
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
  <div class="vue-404 ">
    <div class="container vertical-center">
      <div class="msg font-bold">
        {{ $t("404NotFoundMessage") }}
      </div>
      <img src="/static/img/404.png?v=20190116" alt="" width="308px" />
      <router-link :to="$route.params.api ? '/' + $route.params.api : '/'"
        ><span>
          {{ $t("404BackHome") }}
        </span></router-link
      >
    </div>
  </div>
</template>
