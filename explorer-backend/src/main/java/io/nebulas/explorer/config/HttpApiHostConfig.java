package io.nebulas.explorer.config;

import lombok.Data;
import lombok.ToString;

/**
 * Desc:
 * User: nathan
 * Date: 2018-02-23
 */
@Data
@ToString
public class HttpApiHostConfig {
    private String coinmarketcap;
    private String nebulas;
}
