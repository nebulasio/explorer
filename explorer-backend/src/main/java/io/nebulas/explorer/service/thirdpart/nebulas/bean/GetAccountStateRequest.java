package io.nebulas.explorer.service.thirdpart.nebulas.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Desc:
 * User: nathan
 * Date: 2018-02-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAccountStateRequest implements Serializable {
    private String address;
    private String block = "latest";

    public GetAccountStateRequest(String address) {
        this.address = address;
    }
}
