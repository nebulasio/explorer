
module.exports = {
    apiPrefixes: {
        // http://192.168.1.168:8080/api/
        // https://52.53.225.118/api/
        // https://explorer.nebulas.io/api/
        // first item is default
        mainnet: {
            name: "Mainnet",
            // url: "http://172.16.15.94:8080/api/" // 旭存的ip
            url: "https://explorer.nebulas.io/main/api/",
            atp: "n1zUNqeBPvsyrw5zxp9mKcDdLTjuaEL7s39"
        },
        testnet: {
            name: "Testnet",
            url: "https://explorer.nebulas.io/test/api/",
            atp: "n1rR5uiy4vDUn7TPMAtJ8Y1Eo54K6EYvSJ6"
        }
    }
};
