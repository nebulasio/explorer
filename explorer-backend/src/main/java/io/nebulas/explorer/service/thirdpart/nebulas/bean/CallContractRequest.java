package io.nebulas.explorer.service.thirdpart.nebulas.bean;

import com.alibaba.fastjson.JSON;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallContractRequest {
/*
{
    "from":"n1JvS1LDTJRxSdq4F5cDd1x78ihHTTRyWif",
    "to":"n1rR5uiy4vDUn7TPMAtJ8Y1Eo54K6EYvSJ6",
    "value":"0",
    "nonce":480,
    "gasPrice":"1000000",
    "gasLimit":"2000000",
    "contract":{
        "function":"balanceOf",
        "args":"[\"n1JvS1LDTJRxSdq4F5cDd1x78ihHTTRyWif\"]"
    }
}
*/
    private String from;
    private String to;
    private String value;
    private Long nonce;
    private String gasPrice;
    private String gasLimit;
    private ContractCallParameter contract;

    public static CallContractRequest buildQueryBalanceRequest(String address, String contract){
        ContractCallParameter contractCallParameter = new ContractCallParameter("balanceOf", JSON.toJSONString(Collections.singletonList(address)));
        CallContractRequest request = new CallContractRequest();
        request.from = address;
        request.to = contract;
        request.value = "0";
        request.nonce = 0L;
        request.gasPrice = "1";
        request.gasLimit = "1";
        request.contract = contractCallParameter;
        return request;
    }
}
