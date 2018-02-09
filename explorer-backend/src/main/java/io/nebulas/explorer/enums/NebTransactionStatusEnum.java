package io.nebulas.explorer.enums;

import lombok.Getter;

/**
 * Desc:
 * User: HaiNan.Wang
 * Date: 2018/1/29
 */
@Getter
public enum NebTransactionStatusEnum {
    FAIL(0),
    SUCCESS(1),
    PENDING(2);

    private int value;

    NebTransactionStatusEnum(int value) {
        this.value = value;
    }
}
