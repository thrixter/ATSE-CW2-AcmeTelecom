package com.acmetelecom.billing;

import com.acmetelecom.calling.*;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.CustomerDatabase;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.customer.TariffLibrary;
import com.acmetelecom.printing.BillGenerator;
import com.acmetelecom.printing.LineItem;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
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
    BillCalculator billCalculator;

    Customer anne, john;
    Tariff tariff = Tariff.Business;

    @Mock CustomerDatabase customerDatabase;
    @Mock TariffLibrary tariffDatabase;
    @Mock BillGenerator billGenerator;

    @Before
    public void setUpCustomers() {
        MockitoAnnotations.initMocks(this);

        john = new Customer("John Smith", "447722113434", "Business");
        anne = new Customer("Anne Jones", "447777765432", "Leisure");

        when(customerDatabase.getCustomers()).thenReturn(Arrays.asList(john));
        when(tariffDatabase.tarriffFor(john)).thenReturn(tariff);

        callLogger = new SyncCallLogger();
        billCalculator = new FixedRateBillCalculator(new DaytimePeakPeriod());

        billingSystem = new BillingSystem(callLogger, customerDatabase, tariffDatabase, billGenerator);
    }

    @Test
    public void testCustomerBillIsGeneratedForACall() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

        DateTime startTime = formatter.parseDateTime("2011-11-29 15:00");
        DateTime endTime = formatter.parseDateTime("2011-11-29 15:01");

        callLogger.callInitiated("447722113434", "447777765432", startTime);
        callLogger.callCompleted("447722113434", "447777765432", endTime);

        billingSystem.createCustomerBills();

        List<? extends LineItem> lineItems = Arrays.asList(
                new BillItem(new Call(new CallStart("447722113434", "447777765432", startTime), new CallEnd("447722113434", "447777765432", endTime)),
                        BigDecimal.valueOf(18))
        );

        verify(billGenerator).send(john, lineItems);
    }

}
