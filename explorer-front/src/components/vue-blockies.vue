<template>
    <div class="block">
        <img :src="getDataUrl()" alt="">
    </div>
</template>
<script>
var blockies = require("@/assets/blockies.min.js");
module.exports = {
    props: ['address'],
    methods:{
        getDataUrl() {
            var icon = blockies.create({
                seed: this.address.toLowerCase()
            })
            return icon.toDataURL()
        }
    }
}
</script>