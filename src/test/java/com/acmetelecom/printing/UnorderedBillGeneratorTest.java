package com.acmetelecom.printing;

import com.acmetelecom.billing.BillItem;
import com.acmetelecom.calling.Call;
import com.acmetelecom.calling.CallEnd;
import com.acmetelecom.calling.CallStart;
import com.acmetelecom.customer.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class UnorderedBillGeneratorTest {

    BillGenerator billGenerator;

    Customer customer;
    String callerName = "John Smith";
    String callerNumber = "447722113434";
    String calleeNumber = "447777765432";
    String pricePlan = "Business";

    long startTime = Timestamp.valueOf("2011-11-30 08:00:00").getTime();
    long endTime = Timestamp.valueOf("2011-11-30 09:00:00").getTime();

    List<? extends LineItem> lineItems;

    @Mock Printer printer;

    @Before
    public void setUpCustomerCalls() {
        MockitoAnnotations.initMocks(this);

        customer = new Customer(callerName, callerNumber, pricePlan);

        Call call = new Call(
                new CallStart(callerNumber, calleeNumber, startTime),
                new CallEnd(callerNumber, calleeNumber, endTime)
        );

        LineItem billItem = new BillItem(call, BigDecimal.valueOf(25.0));

        lineItems = Arrays.asList(billItem, billItem);

        billGenerator = new UnorderedBillGenerator(printer);
    }

    @Test
    public void testSendBill() {
        billGenerator.send(customer, lineItems);

        verify(printer, times(1)).printHeading(callerName, callerNumber, pricePlan);
        verify(printer, times(2)).printItem("30/11/11 08:00", calleeNumber, "60:00", "0.25");
        verify(printer, times(1)).printTotal("0.50");
    }
}
