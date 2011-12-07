package com.acmetelecom;

import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.CustomerDatabase;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.customer.TariffLibrary;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class BillingSystemTest {

    BillingSystem billingSystem;
    CallLogger callLogger;

    Customer anne, john;
    Tariff tariff = Tariff.Business;

    @Mock CustomerDatabase customerDatabase;
    @Mock TariffLibrary tariffDatabase;
    @Mock BillGenerator billGenerator;

    @Before
    public void setUpCustomers() {
        // todo: Customer factory with TinyTypes
        MockitoAnnotations.initMocks(this);
        
        john = new Customer("John Smith", "447722113434", "Business");
        anne = new Customer("Anne Jones", "447777765432", "Leisure");

        when(customerDatabase.getCustomers()).thenReturn(Arrays.asList(john));
        when(tariffDatabase.tarriffFor(john)).thenReturn(tariff);

        callLogger = new SyncCallLogger();
        billingSystem = new BillingSystem(callLogger, customerDatabase, tariffDatabase, billGenerator);
    }

    @Test
    public void testCustomerBillIsGeneratedForACall() {

        // todo: CallLogger with TinyTypes
        callLogger.callInitiated("447722113434", "447777765432", Timestamp.valueOf("2011-11-29 15:00:00").getTime());
        callLogger.callCompleted("447722113434", "447777765432", Timestamp.valueOf("2011-11-29 15:01:00").getTime());

        billingSystem.createCustomerBills();

        List<LineItem> lineItems = Arrays.asList(
                new LineItem(new Call(new CallStart("447722113434", "447777765432"), new CallEnd("447722113434", "447777765432")),
                        BigDecimal.valueOf(18))
        );

        verify(billGenerator).send(john, lineItems, "0.18");
    }

}
