package com.acmetelecom.fixtures;

import com.acmetelecom.BillGenerator;
import com.acmetelecom.BillingSystem;
import com.acmetelecom.HTMLBillGenerator;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.CustomerDatabase;
import com.acmetelecom.customer.TariffLibrary;

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
        BillGenerator billGenerator = new HTMLBillGenerator();

        billingSystem = new BillingSystem(customerDatabase, tariffDatabase, billGenerator);
    }

}
