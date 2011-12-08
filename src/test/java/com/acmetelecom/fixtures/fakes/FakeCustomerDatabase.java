package com.acmetelecom.fixtures.fakes;

import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.CustomerDatabase;

import java.util.List;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class FakeCustomerDatabase implements CustomerDatabase {

    private List<Customer> customers;

    public FakeCustomerDatabase(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
