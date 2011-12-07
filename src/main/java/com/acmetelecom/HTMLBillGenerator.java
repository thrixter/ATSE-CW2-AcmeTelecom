package com.acmetelecom;

import com.acmetelecom.customer.Customer;

import java.util.List;

/**
 * 
 * @author dc408, ra808, je08, jm308
 */
public class HTMLBillGenerator implements BillGenerator {

    /**
     * 
     * @param customer
     * @param calls
     * @param totalBill
     */
    public void send(Customer customer, List<LineItem> calls, String totalBill) {

        Printer printer = HtmlPrinter.getInstance();
        printer.printHeading(customer.getFullName(), customer.getPhoneNumber(), customer.getPricePlan());
        for (LineItem call : calls) {
            printer.printItem(call.date(), call.callee(), call.durationMinutes(), MoneyFormatter.penceToPounds(call.cost()));
        }
        printer.printTotal(totalBill);
    }

}
