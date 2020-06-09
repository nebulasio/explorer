// https://eslint.org/docs/user-guide/configuring
module.exports = {
    root: true,
    env: {
      node: true
    },
    extends: ["plugin:vue/essential", "@vue/prettier"],
    rules: {
      "no-console": process.env.NODE_ENV === "production" ? "warn" : "off",
      "no-debugger": process.env.NODE_ENV === "production" ? "error" : "off",
    },
    parserOptions: {
      parser: "babel-eslint"
    }
  };
  