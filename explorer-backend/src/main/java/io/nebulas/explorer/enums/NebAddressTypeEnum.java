package io.nebulas.explorer.enums;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

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

    private static final Map<Integer, NebAddressTypeEnum> map = Maps.newHashMap();

    static {
        Arrays.asList(NebAddressTypeEnum.values()).forEach(it -> map.put(it.getValue(), it));
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static NebAddressTypeEnum of(Integer value) {
        return map.get(value);
    }
}
