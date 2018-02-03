
module.exports = [{
    component: require("@/routes/address").default,
    meta: { headerActive: 3 },
    path: "/address/:id"
}, {
    component: require("@/routes/accounts").default,
    meta: { headerActive: 3 },
    path: "/accounts"
}, {
    component: require("@/routes/block").default,
    meta: { headerActive: 2 },
    path: "/block/:id"
}, {
    component: require("@/routes/blocks").default,
    meta: { headerActive: 2 },
    path: "/blocks"
}, {
    component: require("@/routes/oops").default,
    path: "/oops"
}, {
    component: require("@/routes/tx").default,
    meta: { headerActive: 2 },
    path: "/tx/:id"
}, {
    component: require("@/routes/txs").default,
    meta: { headerActive: 2 },
    path: "/txs"
},
// {
//     component: require("@/routes/txs-internal").default,
//     meta: { headerActive: 2 },
//     path: "/txs/internal/:id"
// },
{
    component: require("@/routes/txs-pending").default,
    meta: { headerActive: 2 },
    path: "/txs/pending"
}, {
    component: require("@/routes/home").default,
    meta: { headerActive: 1 },
    path: ""
}, {
    component: require("@/routes/404").default,
    path: "*",
}];
