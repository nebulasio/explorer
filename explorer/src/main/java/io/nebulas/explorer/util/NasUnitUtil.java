package io.nebulas.explorer.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * Desc:
 * User: HaiNan.Wang
 * Date: 2018/2/5
 */
@Slf4j
public class NasUnitUtil {
    private static final BigDecimal UNIT = BigDecimal.valueOf(Math.pow(10, 18));

    /**
     * Convert wei to nas
     *
     * @param s value
     * @return value
     */
    public static String convert2Nas(String s) {
        if (StringUtils.isEmpty(s)) {
            log.warn("parameter s is empty");
            return "0";
        }
        try {
            return new BigDecimal(s).divide(UNIT, 18, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "0";
        }
    }

}
