import { post } from "@/utils/http";

const monitor = {
  getAddressBalance(addrs) {
    return post("explorer/address/batch", { addresses: JSON.stringify(addrs) });
  }
};

export default monitor;
