package com.acmetelecom.fixtures;

import fit.Fixture;
import fit.Parse;

/**
 * User: javad
 * Date: 06/12/2011
 */
public class GenerateBillForCustomers extends Fixture {

    @Override
    public void doTable(Parse table) {
        SystemUnderTest.printer.clear();
        SystemUnderTest.billingSystem.createCustomerBills();
        super.doTable(table);
    }
}
