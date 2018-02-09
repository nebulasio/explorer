package io.nebulas.explorer.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * explorer.neb_dynasty  nebulas dynasty
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-2-3
 */
@Data
@ToString
public class NebDynasty implements Serializable {
    private Long id;

    /**
     * block height
     */
    private Long blockHeight;

    /**
     * delegate address
     */
    private String delegate;

    private Date createdAt;

    private static final long serialVersionUID = 1L;

    public NebDynasty() {
    }

    public NebDynasty(Long blockHeight, String delegate) {
        this.blockHeight = blockHeight;
        this.delegate = delegate;
    }
}