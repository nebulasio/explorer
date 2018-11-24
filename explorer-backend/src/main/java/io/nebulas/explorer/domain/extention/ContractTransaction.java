package io.nebulas.explorer.domain.extention;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Base64;

import io.nebulas.explorer.domain.NebTransaction;

public class ContractTransaction extends NebTransaction {

    private static final Base64.Decoder DECODER = Base64.getDecoder();

    private String contractTo;
    private BigDecimal contractValue;
    private String decodedData;
    private String contractFunction;
    private String tokenName;

    public String getContractTo() {
        return contractTo;
    }

    public void setContractTo(String contractTo) {
        this.contractTo = contractTo;
    }

    public BigDecimal getContractValue() {
        return contractValue;
    }

    public void setContractValue(BigDecimal contractValue) {
        this.contractValue = contractValue;
    }

    public String getDecodedData() {
        return decodedData;
    }

    public void setDecodedData(String decodedData) {
        this.decodedData = decodedData;
    }

    public String getContractFunction() {
        return contractFunction;
    }

    public void setContractFunction(String contractFunction) {
        this.contractFunction = contractFunction;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public static ContractTransaction fromNebTransaction(NebTransaction nebTransaction) {
        ContractTransaction tx = new ContractTransaction();
        try {
            PropertyUtils.copyProperties(tx, nebTransaction);
            tx.parseContractArgs();
            return tx;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void parseContractArgs() {
        String originData = getData();
        if (StringUtils.isEmpty(originData)) {
            return;
        }
        String decodedData = decodeData(getData());

        JSONObject jsonObject = JSONObject.parseObject(decodedData);
        this.decodedData = decodedData;
        this.contractFunction = jsonObject.getString("Function");
        if (!"transfer".equals(this.contractFunction) && !"proposeMinting".equals(this.contractFunction)) {
            this.contractValue = BigDecimal.ZERO;
            return;
        }
        JSONArray args = jsonObject.getJSONArray("Args");
        if (args.size() < 2) {
            return;
        }

        try{
            this.contractTo = args.getString(0);
            String val = args.getString(1);
            val = val.replace("\"", "").trim();
            this.contractValue = new BigDecimal(val);
        } catch (Exception e){
            this.contractValue = BigDecimal.ZERO;
        }

    }

    private String decodeData(String data) {
        try {
            return new String(DECODER.decode(data), "UTF-8");
        } catch (Exception e) {
            return data;
        }
    }
}
