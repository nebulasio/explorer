package io.nebulas.explorer.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ContractListItemVo {
    private String hash;
    private String alias;
    private String balance;
    private Date createdAt;
    private ContractType contractType;

    public enum ContractType {
        NRC20_TOKEN, NORMAL
    }
}
