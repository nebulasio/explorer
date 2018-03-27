package io.nebulas.explorer.service.thirdpart.nebulas.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * Desc:
 * User: nathan
 * Date: 2018-03-27
 */
@Setter
@Getter
public class NebResponse<T> {
    private String error;
    private T result;
}
