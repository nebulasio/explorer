
module.exports = {
    apiPrefixes: {
        // http://192.168.1.168:8080/api/
        // https://52.53.225.118/api/
        // https://explorer.nebulas.io/api/
        // first item is default
        mainnet: {
            name: "Mainnet",
            // url: "http://172.16.15.94:8080/api/", // 旭存
            // url: "http://172.16.15.124:8080/api/", // xihao
            // url: "http://39.96.38.130:8081/api/", //aliyun
            url: "https://explorer.nebulas.io/main/api/", //正式
            atp: "n1zUNqeBPvsyrw5zxp9mKcDdLTjuaEL7s39"
        },
        testnet: {
            name: "Testnet",
            // url: "http://172.16.15.124:8080/api/", // xihao
            // url: "http://18.188.124.226:8080/api/", // 公司测试服务器的ip
            // url: "https://explorer.nebulas.io/test/api/", //正式
            url: "http://39.96.38.130:8080/api/", //aliyun
            // url: "http://172.16.15.94:8080/api/", // xucun
            atp: "n1rR5uiy4vDUn7TPMAtJ8Y1Eo54K6EYvSJ6"
        }
    }
};
