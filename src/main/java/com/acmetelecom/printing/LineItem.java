package com.acmetelecom.printing;

import java.math.BigDecimal;

/**
 * User: javad
 * Date: 07/12/2011
 */
public interface LineItem {
    String date();

    String callee();

    String durationMinutes();

    BigDecimal cost();
}
