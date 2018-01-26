package io.nebulas.explorer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * nebulas address model
 * <p>
 * Description.
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-01-25
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NebAddress implements Serializable {
    private String id;
    private String hash;
    private Integer type;
    private String alias;
    private Date createdAt;
}
