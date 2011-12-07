package com.acmetelecom.billing;

import com.acmetelecom.calling.CallLogger;
import com.acmetelecom.calling.SyncCallLogger;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.CustomerDatabase;
import com.acmetelecom.customer.TariffLibrary;
import com.acmetelecom.printing.BillGenerator;
import com.acmetelecom.printing.HtmlPrinter;
import com.acmetelecom.printing.UnorderedBillGenerator;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

/**
 * User: javad
 * Date: 06/12/2011
 */
public class BillingSystemIntegrationTest {

    private CallLogger callLogger = new SyncCallLogger();
    private CustomerDatabase customerDatabase = CentralCustomerDatabase.getInstance();
    private TariffLibrary tariffDatabase = CentralTariffDatabase.getInstance();
    private BillCalculator billCalculator = new FixedRateBillCalulator(new DaytimePeakPeriod());
    private BillGenerator billGenerator = new UnorderedBillGenerator(HtmlPrinter.getInstance());

    BillingSystem billingSystem = new BillingSystem(callLogger, customerDatabase, tariffDatabase, billCalculator, billGenerator);

    @Test
    public void testBillingSystem() {

        setUpCalls();

        String actualBill = createCustomerBills();

        assertEquals("Bill is not as expected", expectedBill, actualBill);

    }

    private String createCustomerBills() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        billingSystem.createCustomerBills();

        String bill = out.toString();

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        return bill;
    }

    private void setUpCalls() {
        // call by Fred Bloggs - Standard Price Plan
        callLogger.callInitiated("447711232343", "447711111111", Timestamp.valueOf("2011-11-30 05:00:00").getTime());
        // wait some time
        callLogger.callCompleted("447711232343", "447711111111", Timestamp.valueOf("2011-11-30 05:30:00").getTime());

        // call by John Smith - Business Price Plan
        callLogger.callInitiated("447722113434", "447711111111", Timestamp.valueOf("2011-11-30 14:00:00").getTime());
        // wait some time
        callLogger.callCompleted("447722113434", "447711111111", Timestamp.valueOf("2011-11-30 15:00:00").getTime());

        // call by Jane Dixon - Leisure Price Plan
        callLogger.callInitiated("447799555444", "447711111111", Timestamp.valueOf("2011-11-30 19:00:00").getTime());
        // wait some time
        callLogger.callCompleted("447799555444", "447711111111", Timestamp.valueOf("2011-11-30 20:00:00").getTime());
    }

    String expectedBill = "<html>\n" +
            "<head></head>\n" +
            "<body>\n" +
            "<h1>\n" +
            "Acme Telecom\n" +
            "</h1>\n" +
            "<h2>Fred Bloggs/447711232343 - Price Plan: Standard</h2>\n" +
            "<table border=\"1\">\n" +
            "<tr><th width=\"160\">Time</th><th width=\"160\">Number</th><th width=\"160\">Duration</th><th width=\"160\">Cost</th></tr>\n" +
            "<tr><td>30/11/11 05:00</td><td>447711111111</td><td>30:00</td><td>3.60</td></tr>\n" +
            "</table>\n" +
            "<h2>Total: 3.60</h2>\n" +
            "</body>\n" +
            "</html>\n" +
            "<html>\n" +
            "<head></head>\n" +
            "<body>\n" +
            "<h1>\n" +
            "Acme Telecom\n" +
            "</h1>\n" +
            "<h2>John Smith/447722113434 - Price Plan: Business</h2>\n" +
            "<table border=\"1\">\n" +
            "<tr><th width=\"160\">Time</th><th width=\"160\">Number</th><th width=\"160\">Duration</th><th width=\"160\">Cost</th></tr>\n" +
            "<tr><td>30/11/11 14:00</td><td>447711111111</td><td>60:00</td><td>10.80</td></tr>\n" +
            "</table>\n" +
            "<h2>Total: 10.80</h2>\n" +
            "</body>\n" +
            "</html>\n" +
            "<html>\n" +
            "<head></head>\n" +
            "<body>\n" +
            "<h1>\n" +
            "Acme Telecom\n" +
            "</h1>\n" +
            "<h2>Jane Dixon/447799555444 - Price Plan: Leisure</h2>\n" +
            "<table border=\"1\">\n" +
            "<tr><th width=\"160\">Time</th><th width=\"160\">Number</th><th width=\"160\">Duration</th><th width=\"160\">Cost</th></tr>\n" +
            "<tr><td>30/11/11 19:00</td><td>447711111111</td><td>60:00</td><td>3.60</td></tr>\n" +
            "</table>\n" +
            "<h2>Total: 3.60</h2>\n" +
            "</body>\n" +
            "</html>\n" +
            "<html>\n" +
            "<head></head>\n" +
            "<body>\n" +
            "<h1>\n" +
            "Acme Telecom\n" +
            "</h1>\n" +
            "<h2>Mike Davies/447799888888 - Price Plan: Business</h2>\n" +
            "<table border=\"1\">\n" +
            "<tr><th width=\"160\">Time</th><th width=\"160\">Number</th><th width=\"160\">Duration</th><th width=\"160\">Cost</th></tr>\n" +
            "</table>\n" +
            "<h2>Total: 0.00</h2>\n" +
            "</body>\n" +
            "</html>\n" +
            "<html>\n" +
            "<head></head>\n" +
            "<body>\n" +
            "<h1>\n" +
            "Acme Telecom\n" +
            "</h1>\n" +
            "<h2>Anne Jones/447777765432 - Price Plan: Standard</h2>\n" +
            "<table border=\"1\">\n" +
            "<tr><th width=\"160\">Time</th><th width=\"160\">Number</th><th width=\"160\">Duration</th><th width=\"160\">Cost</th></tr>\n" +
            "</table>\n" +
            "<h2>Total: 0.00</h2>\n" +
            "</body>\n" +
            "</html>\n" +
            "<html>\n" +
            "<head></head>\n" +
            "<body>\n" +
            "<h1>\n" +
            "Acme Telecom\n" +
            "</h1>\n" +
            "<h2>Sarah Thomas/447721232123 - Price Plan: Standard</h2>\n" +
            "<table border=\"1\">\n" +
            "<tr><th width=\"160\">Time</th><th width=\"160\">Number</th><th width=\"160\">Duration</th><th width=\"160\">Cost</th></tr>\n" +
            "</table>\n" +
            "<h2>Total: 0.00</h2>\n" +
            "</body>\n" +
            "</html>\n" +
            "<html>\n" +
            "<head></head>\n" +
            "<body>\n" +
            "<h1>\n" +
            "Acme Telecom\n" +
            "</h1>\n" +
            "<h2>Tina Black/447795195195 - Price Plan: Leisure</h2>\n" +
            "<table border=\"1\">\n" +
            "<tr><th width=\"160\">Time</th><th width=\"160\">Number</th><th width=\"160\">Duration</th><th width=\"160\">Cost</th></tr>\n" +
            "</table>\n" +
            "<h2>Total: 0.00</h2>\n" +
            "</body>\n" +
            "</html>\n" +
            "<html>\n" +
            "<head></head>\n" +
            "<body>\n" +
            "<h1>\n" +
            "Acme Telecom\n" +
            "</h1>\n" +
            "<h2>John Watkins/447132435245 - Price Plan: Business</h2>\n" +
            "<table border=\"1\">\n" +
            "<tr><th width=\"160\">Time</th><th width=\"160\">Number</th><th width=\"160\">Duration</th><th width=\"160\">Cost</th></tr>\n" +
            "</table>\n" +
            "<h2>Total: 0.00</h2>\n" +
            "</body>\n" +
            "</html>\n" +
            "<html>\n" +
            "<head></head>\n" +
            "<body>\n" +
            "<h1>\n" +
            "Acme Telecom\n" +
            "</h1>\n" +
            "<h2>Giorgia Davidson/447132435245 - Price Plan: Standard</h2>\n" +
            "<table border=\"1\">\n" +
            "<tr><th width=\"160\">Time</th><th width=\"160\">Number</th><th width=\"160\">Duration</th><th width=\"160\">Cost</th></tr>\n" +
            "</table>\n" +
            "<h2>Total: 0.00</h2>\n" +
            "</body>\n" +
            "</html>\n" +
            "<html>\n" +
            "<head></head>\n" +
            "<body>\n" +
            "<h1>\n" +
            "Acme Telecom\n" +
            "</h1>\n" +
            "<h2>Jonathan Carr/447223432532 - Price Plan: Standard</h2>\n" +
            "<table border=\"1\">\n" +
            "<tr><th width=\"160\">Time</th><th width=\"160\">Number</th><th width=\"160\">Duration</th><th width=\"160\">Cost</th></tr>\n" +
            "</table>\n" +
            "<h2>Total: 0.00</h2>\n" +
            "</body>\n" +
            "</html>\n";

}
