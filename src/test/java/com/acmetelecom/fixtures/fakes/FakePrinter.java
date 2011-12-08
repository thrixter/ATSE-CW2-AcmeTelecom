package com.acmetelecom.fixtures.fakes;

import com.acmetelecom.customer.Customer;
import com.acmetelecom.printing.MoneyFormatter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.acmetelecom.fixtures.TheBillShows.BillRow;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class FakePrinter {

    private List<BillRow> billRows = new ArrayList<BillRow>();

    public void printRow(Customer customer, String calledNumber, String callTime, String duration, BigDecimal cost) {
        billRows.add(new BillRow(
                customer.getFullName(),
                customer.getPhoneNumber(),
                customer.getPricePlan(),
                calledNumber,
                callTime,
                duration,
                MoneyFormatter.penceToPounds(cost)
        ));
    }

    public List<BillRow> getRows() {
        return billRows;
    }

    public void clear() {
        billRows.clear();
    }
}
