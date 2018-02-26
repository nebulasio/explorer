package io.nebulas.explorer.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * explorer.block_sync_record
 *
 * @author hainan.wang
 * @date 2018-2-26
 */
@Getter
@Setter
public class BlockSyncRecord implements Serializable {
    /**  */
    private Integer id;

    /**  */
    private Long blockHeight;

    /**  */
    private Long txCnt;

    /**  */
    private Long confirm;

    /**  */
    private Date createdAt;

    /**  */
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}