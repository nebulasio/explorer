package io.nebulas.explorer.service.thirdpart.nebulas.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Desc:
 * User: nathan
 * Date: 2018-02-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBlockByHashRequest {
    private String hash;
    private boolean fullTransaction;
}
