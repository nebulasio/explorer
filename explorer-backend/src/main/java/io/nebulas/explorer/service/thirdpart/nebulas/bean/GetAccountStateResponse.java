package io.nebulas.explorer.service.thirdpart.nebulas.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Desc:
 * User: nathan
 * Date: 2018-02-24
 */
@Data
public class GetAccountStateResponse implements Serializable {
    private String balance;
}
