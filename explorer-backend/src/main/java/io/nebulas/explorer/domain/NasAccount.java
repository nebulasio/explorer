package io.nebulas.explorer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * explorer_prod.nas_account  nas主网地址数量变化量
 *
 * @author liaoxihao
 * @date 2018-12-3
 *
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NasAccount implements Serializable {
    /**  */
    private Long id;

    /** 当前地址数量 */
    private Integer addressCount;

    /** 增加的地址数(对比一个小时前,or上一次快照前) */
    private Integer addressIncrement;

    /** 合约账户数量 */
    private Integer contractCount;

    /** 增加的合约数 */
    private Integer contractIncrement;

    /** 快照时间 */
    private Date timestamp;

    /**  */
    private Date createdAt;

    /**  */
    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAddressCount() {
        return addressCount;
    }

    public void setAddressCount(Integer addressCount) {
        this.addressCount = addressCount;
    }

    public Integer getAddressIncrement() {
        return addressIncrement;
    }

    public void setAddressIncrement(Integer addressIncrement) {
        this.addressIncrement = addressIncrement;
    }

    public Integer getContractCount() {
        return contractCount;
    }

    public void setContractCount(Integer contractCount) {
        this.contractCount = contractCount;
    }

    public Integer getContractIncrement() {
        return contractIncrement;
    }

    public void setContractIncrement(Integer contractIncrement) {
        this.contractIncrement = contractIncrement;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}