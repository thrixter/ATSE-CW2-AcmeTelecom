package com.acmetelecom.fixtures;

import com.acmetelecom.BillingSystem;

/**
 * User: javad
 * Date: 06/12/2011
 */
public class SystemUnderTest {

    public static BillingSystem billingSystem;

    static {
        reset();
    }

    public static void reset() {
        billingSystem = new BillingSystem();
    }

}
