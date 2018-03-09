package io.nebulas.explorer.enums;

/**
 * Desc:
 * User: nathan
 * Date: 2018-03-09
 */
public enum NebAddressTypeEnum {
    NORMAL(0, "normal"),
    CONTRACT(1, "contract");

    NebAddressTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private int value;
    private String desc;

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
