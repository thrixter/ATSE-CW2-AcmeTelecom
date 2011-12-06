package com.acmetelecom;

import java.math.BigDecimal;

/**
 *
 * @author dc408, ra808, je08, jm308
 */
class MoneyFormatter {
    /**
     *
     * @param pence
     * @return
     */
    public static String penceToPounds(BigDecimal pence) {
        BigDecimal pounds = pence.divide(new BigDecimal(100));
        return String.format("%.2f", pounds.doubleValue());
    }
}
