package com.acmetelecom.printing;

import com.acmetelecom.customer.Customer;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author dc408, ra808, je08, jm308
 */
public class UnorderedBillGenerator implements BillGenerator {

    private Printer printer;

    public UnorderedBillGenerator(Printer printer) {
        this.printer = printer;
    }

    /**
     *
     * @param customer
     * @param calls
     */
    public void send(Customer customer, List<? extends LineItem> calls) {

        this.printer.printHeading(customer.getFullName(), customer.getPhoneNumber(), customer.getPricePlan());
        for (LineItem call : calls) {
            this.printer.printItem(call.date(), call.callee(), call.durationMinutes(), MoneyFormatter.penceToPounds(call.cost()));
        }
        String totalBill = MoneyFormatter.penceToPounds(calculateTotalBill(calls));
        this.printer.printTotal(totalBill);
    }

    private BigDecimal calculateTotalBill(List<? extends LineItem> items) {
        BigDecimal totalBill = new BigDecimal(0);

        for (LineItem lineItem : items) {
            totalBill = totalBill.add(lineItem.cost());
        }

        return totalBill;
    }

}
