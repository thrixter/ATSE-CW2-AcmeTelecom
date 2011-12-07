package com.acmetelecom;

import com.acmetelecom.billing.BillItemTest;
import com.acmetelecom.billing.BillingSystemTest;
import com.acmetelecom.billing.FixedRateBillCalculatorTest;
import com.acmetelecom.billing.VariableRateBillCalculatorTest;
import com.acmetelecom.calling.CallEndTest;
import com.acmetelecom.calling.CallStartTest;
import com.acmetelecom.calling.CallTest;
import com.acmetelecom.calling.SyncCallLoggerTest;
import com.acmetelecom.printing.UnorderedBillGeneratorTest;
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
        FixedRateBillCalculatorTest.class,
        VariableRateBillCalculatorTest.class,
        BillItemTest.class,
        CallTest.class,
        CallStartTest.class,
        CallEndTest.class,
        UnorderedBillGeneratorTest.class
})
public class AcmeTelecomTestSuite {}
