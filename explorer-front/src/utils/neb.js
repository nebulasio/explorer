import Nebulas from "nebulas";
const Unit = Nebulas.Unit;

export const convert2NasStr = (value, suffix = "NAS") => {
  return `${convert2NasNumber(value).toLocaleString()} ${suffix}`;
};

export const convert2NasNumber = value => {
  return Number(parseFloat(Unit.fromBasic(value, "nas")).toFixed(2));
};

export const convert2NaxStr = (value, suffix = "NAX") => {
  return `${convert2NaxNumber(value).toLocaleString()} ${suffix}`;
};

export const convert2NaxNumber = value => {
  return Number(parseFloat(Unit.fromBasic(value, "gwei")).toFixed(2));
};

export const convert2NaxBasic = value => {
  return parseInt(Unit.toBasic(value, "gwei"));
};

export const convert2NasBasic = value => {
  return parseInt(Unit.toBasic(value, "nas"));
};

export const netStatus = route => {
  const paramsApi = route.params.api;
  let netStatusText;
  if (paramsApi === "testnet") {
    netStatusText = "TESTNET";
  } else {
    netStatusText = "MAINNET";
  }

  return netStatusText;
};

export const isMainnet = route => {
  const paramsApi = route.params.api;
  if (paramsApi === "testnet") {
    return false;
  } else {
    return true;
  }
};

export const urlPrefix = route => {
  const paramsApi = route.params.api;
  const fragApi = paramsApi ? "/" + paramsApi : "";
  return fragApi;
};

export const baseUrl = (route, url) => {
  const prefix = urlPrefix(route);
  return prefix + url;
};
