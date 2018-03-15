package io.nebulas.explorer.enums;

/**
 * Desc:
 * User: nathan
 * Date: 2018-03-09
 */
public enum NebTransactionTypeEnum {
    BINARY("binary"),
    CALL("call"),
    DEPLOY("deploy");

    NebTransactionTypeEnum(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
