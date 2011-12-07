package com.acmetelecom.printing;

import com.acmetelecom.billing.BillItem;
import com.acmetelecom.calling.Call;
import com.acmetelecom.calling.CallEnd;
import com.acmetelecom.calling.CallStart;
import com.acmetelecom.customer.Customer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class UnorderedBillGeneratorIntegrationTest {

    BillGenerator billGenerator
            = new UnorderedBillGenerator(HtmlPrinter.getInstance());

    Customer customer;
    List<? extends LineItem> lineItems;

    @Before
    public void setUpCustomerCalls() {
        MockitoAnnotations.initMocks(this);

        long startTime = Timestamp.valueOf("2011-11-30 08:00:00").getTime();
        long endTime = Timestamp.valueOf("2011-11-30 09:00:00").getTime();

        String callerName = "John Smith";
        String callerNumber = "447722113434";
        String calleeNumber = "447777765432";
        String pricePlan = "Business";

        customer = new Customer(callerName, callerNumber, pricePlan);

        lineItems = Arrays.asList(
                new BillItem(new Call(
                        new CallStart(callerNumber, calleeNumber, startTime),
                        new CallEnd(callerNumber, calleeNumber, endTime)
                ), BigDecimal.valueOf(100.0))
        );
    }

    @Test
    public void testSendBill() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        billGenerator.send(customer, lineItems);

        String bill = out.toString().trim();

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        assertThat(bill, equalTo(expectedBill));
    }

    private String expectedBill = "<html>\n" +
            "<head></head>\n" +
            "<body>\n" +
            "<h1>\n" +
            "Acme Telecom\n" +
            "</h1>\n" +
            "<h2>John Smith/447722113434 - Price Plan: Business</h2>\n" +
            "<table border=\"1\">\n" +
            "<tr><th width=\"160\">Time</th><th width=\"160\">Number</th><th width=\"160\">Duration</th><th width=\"160\">Cost</th></tr>\n" +
            "<tr><td>30/11/11 08:00</td><td>447777765432</td><td>60:00</td><td>1.00</td></tr>\n" +
            "</table>\n" +
            "<h2>Total: 1.00</h2>\n" +
            "</body>\n" +
            "</html>";
}
