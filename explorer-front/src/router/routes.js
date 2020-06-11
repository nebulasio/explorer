function load(component) {
  // '@' is aliased to src/components
  return () => import(`@/views/${component}.vue`);
}

const routes = [
  {
    component: load("address"),
    meta: { headerActive: 2, uaview: "Explorer_Address_Show" },
    path: "/:api?/address/:id"
  },
  {
    component: load("accounts"),
    meta: { headerActive: 2 },
    path: "/:api?/accounts"
  },
  {
    component: load("block"),
    meta: { headerActive: 2 },
    path: "/:api?/block/:id"
  },
  {
    component: load("blocks"),
    meta: { headerActive: 2 },
    path: "/:api?/blocks"
  },
  {
    component: load("tx"),
    meta: { headerActive: 2, uaview: "Explorer_TxnsDetails_Show" },
    path: "/:api?/tx/:id"
  },
  {
    component: load("txs"),
    meta: { headerActive: 2 },
    path: "/:api?/txs"
  },
  {
    component: load("txs-nrc20"),
    meta: { headerActive: 2 },
    path: "/:api?/txs-nrc20"
  },
  // {
  //	 component: load("txs-internal"),
  //	 meta: { headerActive: 2 },
  //	 path: "/txs/internal/:id"
  // },
  {
    component: load("txs-pending"),
    meta: { headerActive: 2 },
    path: "/:api?/txs/pending"
  },
  {
    component: load("dip-leaderboard"),
    meta: { headerActive: 3 },
    path: "/:api?/dip-leaderboard"
  },
  {
    component: load("404"),
    path: "/:api?/404"
  },
  {
    component: load("nothing"),
    path: "/:api?/nothing"
  },
  {
    component: load("contract"),
    meta: { headerActive: 2, uaview: "Explorer_NRC20token_Show" },
    path: "/:api?/token/:id"
  },
  {
    component: load("contract"),
    meta: { headerActive: 2, uaview: "Explorer_NRC20token_Show" },
    path: "/:api?/contract/:id"
  },
  {
    component: load("contracts"),
    meta: { headerActive: 2 },
    path: "/:api?/contracts"
  },
  {
    component: load("contract-txs"),
    meta: { headerActive: 2 },
    path: "/:api?/contract-txs"
  },
  {
    component: load("dstaking"),
    meta: { headerActive: 3 },
    path: "/:api?/dstaking"
  },
  {
    component: load("dstaking-history"),
    meta: { headerActive: 3 },
    path: "/:api?/dstaking-history"
  },
  // monitor address
  {
    component: load("monitor"),
    meta: { headerActive: 3 },
    path: "/:api?/monitor"
  },
  // this route must put last
  {
    component: load("dashboard"),
    meta: { headerActive: 1, uaview: "Explorer_Home_Show" },
    path: "/:api?",
    name: "home"
  },
  {
    component: load("404"),
    name: "*",
    path: "*"
  }
];

export default routes;
