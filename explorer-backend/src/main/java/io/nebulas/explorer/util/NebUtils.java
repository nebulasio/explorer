package io.nebulas.explorer.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NebUtils {

    /**
     * calculateTotalNas Unit: WEI
     *
     * @param blockHeight block height
     * @return WEI
     */
    public static BigInteger calculateTotalNas(long blockHeight) {
        // 1.42694*height+0.47565*(height-2306999)
        BigDecimal height = new BigDecimal(String.valueOf(blockHeight));
        BigDecimal nas = new BigDecimal("1.42694")
                .multiply(height)
                .add(new BigDecimal("0.47565")
                        .multiply((height.subtract(new BigDecimal("2306999")))))
                .add(new BigDecimal("50000000"));
        return nas.multiply(BigDecimal.TEN.pow(18)).toBigInteger();
    }
}
