package io.nebulas.explorer.enums;

/**
 * Desc:
 * User: HaiNan.Wang
 * Date: 2018/1/30
 */
public enum NebAddressTypeEnum {
    NORMAL(0),
    CONTRACT(1);

    private int value;

    NebAddressTypeEnum(int value) {
        this.value = value;
    }
}
