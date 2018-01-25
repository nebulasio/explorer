package io.nebulas.explorer.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill
 * @version 1.0
 * @since 2018-01-22
 */
@Data
@ToString
public class Country implements Serializable {
    private String id;
    private String code;
    private String enname;
    private String cnname;
}
