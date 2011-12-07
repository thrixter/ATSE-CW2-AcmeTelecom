package com.acmetelecom.fixtures;

import com.acmetelecom.printing.BillGenerator;
import com.acmetelecom.billing.BillingSystem;
import com.acmetelecom.calling.CallLogger;
import com.acmetelecom.calling.SyncCallLogger;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.CustomerDatabase;
import com.acmetelecom.customer.TariffLibrary;
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
        CustomerDatabase customerDatabase = CentralCustomerDatabase.getInstance();
        TariffLibrary tariffDatabase = CentralTariffDatabase.getInstance();
        BillGenerator billGenerator = new UnorderedBillGenerator();
        CallLogger callLogger = new SyncCallLogger();
        
        billingSystem = new BillingSystem(callLogger, customerDatabase, tariffDatabase, billGenerator);
    }

}
