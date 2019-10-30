package io.nebulas.explorer.service.thirdpart.infra.bean;

public class NRC20Tx {
    public String hash;
    public BlockInfo block;
    public AddressInfo from;
    public AddressInfo to;
    public int status;
    public String value;
    public long nonce;
    public String type;
    public long timestamp;
    public String gasPrice;
    public String gasLimit;
    public String gasUsed;
    public String data;
    public String contractAddress;
    public String executeError;
    public String tokenName;
    public int decimal;
    public String txFee;
}
