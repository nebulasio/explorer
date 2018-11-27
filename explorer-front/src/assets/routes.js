
module.exports = [{
    component: require("@/routes/address").default,
    meta: { headerActive: 3 },
    path: "/:api?/address/:id"
}, {
    component: require("@/routes/accounts").default,
    meta: { headerActive: 3 },
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
    component: require("@/routes/oops").default,
    path: "/:api?/oops"
}, {
    component: require("@/routes/tx").default,
    meta: { headerActive: 2 },
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
    component: require("@/routes/contract").default,
    meta: { headerActive: 2 },
    path: "/:api?/contract/:id"
}, {
    component: require("@/routes/contract-txs").default,
    meta: { headerActive: 2 },
    path: "/:api?/contract-txs"
}, {
    component: require("@/routes/dashboard").default,
    meta: { headerActive: 1 },
    path: "/:api?"
}, {
    component: require("@/routes/404").default,
    name: "*",
    path: "*"
}];
