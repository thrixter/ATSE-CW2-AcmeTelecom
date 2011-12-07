package com.acmetelecom;

import com.acmetelecom.customer.Customer;

import java.util.List;

/**
 * User: javad
 * Date: 06/12/2011
 */
public interface BillGenerator {
    public void send(Customer customer, List<LineItem> calls, String totalBill);
}
