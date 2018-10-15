package io.nebulas.explorer.service.thirdpart.nebulas.bean;

import lombok.Data;

@Data
public class CallContractResponse {
    private String result;
    private String execute_err;
    private String estimate_gas;
}
