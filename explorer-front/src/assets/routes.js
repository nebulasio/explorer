
module.exports = [{
    component: require("@/routes/address").default,
    meta: { headerActive: 2, uaview: "Explorer_Address_Show" },
    path: "/:api?/address/:id"
}, {
    component: require("@/routes/accounts").default,
    meta: { headerActive: 2 },
    path: "/:api?/accounts"
}, {
    component: require("@/routes/block").default,
    meta: { headerActive: 2 },
    path: "/:api?/block/:id"
}, {
    component: require("@/routes/blocks").default,
    meta: { headerActive: 2 },
    path: "/:api?/blocks"
}, {
    component: require("@/routes/404").default,
    path: "/:api?/oops"
}, {
    component: require("@/routes/tx").default,
    meta: { headerActive: 2, uaview: "Explorer_TxnsDetails_Show" },
    path: "/:api?/tx/:id"
}, {
    component: require("@/routes/txs").default,
    meta: { headerActive: 2 },
    path: "/:api?/txs"
}, {
    component: require("@/routes/txs-nrc20").default,
    meta: { headerActive: 2 },
    path: "/:api?/txs-nrc20"
},
// {
//     component: require("@/routes/txs-internal").default,
//     meta: { headerActive: 2 },
//     path: "/txs/internal/:id"
// },
{
    component: require("@/routes/txs-pending").default,
    meta: { headerActive: 2 },
    path: "/:api?/txs/pending"
}, {
    component: require("@/routes/404").default,
    path: "/:api?/404"
}, {
    component: require("@/routes/nothing").default,
    path: "/:api?/nothing"
}, {
    component: require("@/routes/contract").default,
    meta: { headerActive: 2, uaview: "Explorer_NRC20token_Show" },
    path: "/:api?/contract/:id"
}, {
    component: require("@/routes/contracts").default,
    meta: { headerActive: 2 },
    path: "/:api?/contracts"
}, {
    component: require("@/routes/contract-txs").default,
    meta: { headerActive: 2 },
    path: "/:api?/contract-txs"
}, {
    component: require("@/routes/dashboard").default,
    meta: { headerActive: 1, uaview: "Explorer_Home_Show" },
    path: "/:api?",
    name: "home"
}, {
    component: require("@/routes/404").default,
    name: "*",
    path: "*"
}];
