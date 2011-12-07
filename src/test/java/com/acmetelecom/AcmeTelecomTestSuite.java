package com.acmetelecom;

import com.acmetelecom.billing.BillingSystemTest;
import com.acmetelecom.billing.VariableRateBillCalculatorTest;
import com.acmetelecom.calling.SyncCallLoggerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * User: javad
 * Date: 07/12/2011
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BillingSystemTest.class,
        SyncCallLoggerTest.class,
        VariableRateBillCalculatorTest.class
})
public class AcmeTelecomTestSuite {}
