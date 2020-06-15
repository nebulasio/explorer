import { get } from "@/utils/http";

const home = {
  getNasMarket() {
    return get("explorer/market/nas");
  },
  getNaxMarket() {
    return get("explorer/market/nax");
  }
};

export default home;
