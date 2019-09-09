package io.nebulas.explorer.service.thirdpart.nebulas.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Desc:
 * User: nathan
 * Date: 2018-03-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract implements Serializable {
    private String source;
    private String sourceType;
    private String function;
    private String args;
    /**
     * 备注
     */
    private String binary;
    /**
     * binary,deploy,call
     */
    private String type;

    public Contract(String function, String args) {
        this.function = function;
        this.args = args;
    }
}
