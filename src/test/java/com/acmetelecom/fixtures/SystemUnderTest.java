package com.acmetelecom.fixtures;

import com.acmetelecom.billing.BillCalculator;
import com.acmetelecom.billing.DaytimePeakPeriod;
import com.acmetelecom.billing.FixedRateBillCalulator;
import com.acmetelecom.billing.BillingSystem;
import com.acmetelecom.calling.CallLogger;
import com.acmetelecom.calling.SyncCallLogger;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.CustomerDatabase;
import com.acmetelecom.customer.TariffLibrary;
import com.acmetelecom.printing.BillGenerator;
import com.acmetelecom.printing.HtmlPrinter;
import com.acmetelecom.printing.UnorderedBillGenerator;

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
        CallLogger callLogger = new SyncCallLogger();
        CustomerDatabase customerDatabase = CentralCustomerDatabase.getInstance();
        TariffLibrary tariffDatabase = CentralTariffDatabase.getInstance();
        BillCalculator billCalculator = new FixedRateBillCalulator(new DaytimePeakPeriod());
        BillGenerator billGenerator = new UnorderedBillGenerator(HtmlPrinter.getInstance());

        billingSystem = new BillingSystem(callLogger, customerDatabase, tariffDatabase, billCalculator, billGenerator);
    }

}
