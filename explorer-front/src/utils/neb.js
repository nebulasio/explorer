import Nebulas from "nebulas";
const Unit = Nebulas.Unit;

export const convert2NasStr = (value, suffix = "NAS") => {
  return `${convert2NasNumber(value).toLocaleString()} ${suffix}`;
};

export const convert2NasNumber = value => {
  return parseFloat(Unit.fromBasic(value, "nas"));
};

export const convert2NaxStr = (value, suffix = "NAX") => {
  return `${convert2NaxNumber(value).toLocaleString()} ${suffix}`;
};

export const convert2NaxNumber = value => {
  return parseFloat(Unit.fromBasic(value, "gwei"));
};

export const convert2NaxBasic = value => {
  return parseInt(Unit.toBasic(value, "gwei"));
};

export const convert2NasBasic = value => {
  return parseInt(Unit.toBasic(value, "nas"));
};