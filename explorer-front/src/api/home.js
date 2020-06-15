import { get } from "@/utils/http";

const home = {
  getNaxMarket() {
    return get("explorer/market/nax");
  }
};

export default home;
