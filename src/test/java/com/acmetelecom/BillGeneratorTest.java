package com.acmetelecom;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * User: javad
 * Date: 06/12/2011
 */
public class BillGeneratorTest {

    BillingSystem billingSystem = new BillingSystem();

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
        billingSystem.callInitiated("447711232343", "447711111111");
        // wait some time
        billingSystem.callCompleted("447711232343", "447711111111");

        // call by John Smith - Business Price Plan
        billingSystem.callInitiated("447722113434", "447711111111");
        // wait some time
        billingSystem.callCompleted("447722113434", "447711111111");

        // call by Jane Dixon - Leisure Price Plan
        billingSystem.callInitiated("447799555444", "447711111111");
        // wait some time
        billingSystem.callCompleted("447799555444", "447711111111");
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
            "<tr><td>11/11/11 08:00</td><td>447711111111</td><td>1:00</td><td>0.30</td></tr>\n" +
            "</table>\n" +
            "<h2>Total: 0.30</h2>\n" +
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
            "<tr><td>11/11/11 08:00</td><td>447711111111</td><td>1:00</td><td>0.18</td></tr>\n" +
            "</table>\n" +
            "<h2>Total: 0.18</h2>\n" +
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
            "<tr><td>11/11/11 08:00</td><td>447711111111</td><td>1:00</td><td>0.48</td></tr>\n" +
            "</table>\n" +
            "<h2>Total: 0.48</h2>\n" +
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
