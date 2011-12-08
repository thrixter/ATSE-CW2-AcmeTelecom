package com.acmetelecom.fixtures.fakes;

import com.acmetelecom.customer.Customer;
import com.acmetelecom.printing.BillGenerator;
import com.acmetelecom.printing.LineItem;

import java.util.List;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class FakeBillGenerator implements BillGenerator {

    private FakePrinter printer;

    public FakeBillGenerator(FakePrinter printer) {
        this.printer = printer;
    }

    public void send(Customer customer, List<? extends LineItem> calls) {
        for(LineItem call : calls) {
        	printer.printRow(customer, call.callee(), call.date(),
                    call.durationMinutes(), call.cost());
        }
    }
}
