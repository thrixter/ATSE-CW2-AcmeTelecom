package com.acmetelecom.fixtures;

import com.acmetelecom.billing.BillingSystem;
import com.acmetelecom.calling.CallLogger;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.CustomerDatabase;
import com.acmetelecom.customer.TariffLibrary;
import com.acmetelecom.fixtures.fakes.FakeBillGenerator;
import com.acmetelecom.fixtures.fakes.FakeCallLogger;
import com.acmetelecom.fixtures.fakes.FakeCustomerDatabase;
import com.acmetelecom.fixtures.fakes.FakePrinter;
import com.acmetelecom.printing.BillGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * User: javad
 * Date: 06/12/2011
 */
public class SystemUnderTest {


    static CallLogger callLogger = new FakeCallLogger();
    static List<Customer> customers = new ArrayList<Customer>();
    static FakePrinter printer = new FakePrinter();
    private static CustomerDatabase customerDatabase = new FakeCustomerDatabase(customers);
    private static TariffLibrary tariffDatabase = CentralTariffDatabase.getInstance();
    private static BillGenerator billGenerator = new FakeBillGenerator(printer);

    static BillingSystem billingSystem = new BillingSystem(callLogger,
            customerDatabase, tariffDatabase, billGenerator);
}
