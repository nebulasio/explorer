import { get } from "@/utils/http";

const home = {
  getNasMarket() {
    return get("explorer/market/nas");
  },
  getNaxMarket() {
    return get("explorer/market/nax");
  },
  getNodeSummary() {
    return get("node/summary");
  },
  getNaxHistory() {
    return get("nax/history");
  }
};

export default home;
