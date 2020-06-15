import numeral from "numeral";

export const toLocaleString = value => {
  return Number(parseFloat(value).toFixed(2)).toLocaleString();
};

export const toBigNumString = (value, precision = 1) => {
  let format = "0.0a";

  if (precision == 2) {
    format = "0.00a";
  }

  return numeral(value).format(format);
};
