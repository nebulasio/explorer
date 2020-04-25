package io.nebulas.explorer.service.blockchain;

import io.nebulas.explorer.domain.NebAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * description
 *
 * @author ：Larry Wang
 * @date ：Created in 2020-04-22
 * @version: 1.0
 */
@Service
public class NasCirculationService {

    private static final  String DESTORY_ADDRESS = "n1gczhpkT54RaT4PB55CNoYbqmEQcfo4hqq";
    private static final  String LOCKED_ADDRESS1 = "n1X3imkL6ZtHYJvHZdTuhBAGafYCj6cqnY6";
    private static final  String LOCKED_ADDRESS2 = "n1VjCXwMdKisAzpCoGCPEWW23c8xoFcCaYE";
    private static final  String LOCKED_ADDRESS3 = "n1ZbXBzCqmSRsidsD27RL2qcJa4DdwghX5t";

    @Autowired
    private NebBlockService nebBlockService;

    @Autowired
    private NebAddressService nebAddressService;


    /**
     * nas总量，Unit: WEI
     * @return
     */
    public BigInteger totalNAS() {
        //1.42694*current_height+0.47565*(4201999-2306999) + 100000000
        long maxBlockHeight = nebBlockService.getMaxHeight();

        BigDecimal total = new BigDecimal("100000000")
                .add(new BigDecimal("1.42694").multiply(new BigDecimal(maxBlockHeight)))
                .add(new BigDecimal("0.47565").multiply(new BigDecimal(4201999 - 2306999)));

        return total.multiply(BigDecimal.TEN.pow(18)).toBigInteger();
    }

    /**
     * nas总发行量，Unit: WEI
     * @return
     */
    public BigInteger totalSupplyNAS() {
        // totalNAS - destoryNAS
        NebAddress destoryAddr = nebAddressService.getNebAddressByHashRpc(DESTORY_ADDRESS);

        return this.totalNAS().subtract(new BigInteger(destoryAddr.getBalance()));
    }

    /**
     * nas总流通量，Unit: WEI
     * @return
     */
    public BigInteger circulationNAS() {
        // totalNAS - destoryNAS - lockedNAS
        NebAddress lockedAddr1 = nebAddressService.getNebAddressByHashRpc(LOCKED_ADDRESS1);
        NebAddress lockedAddr2 = nebAddressService.getNebAddressByHashRpc(LOCKED_ADDRESS2);
        NebAddress lockedAddr3 = nebAddressService.getNebAddressByHashRpc(LOCKED_ADDRESS3);

        return this.totalSupplyNAS()
                .subtract(new BigInteger(lockedAddr1.getBalance()))
                .subtract(new BigInteger(lockedAddr2.getBalance()))
                .subtract(new BigInteger(lockedAddr3.getBalance()));
    }
}
