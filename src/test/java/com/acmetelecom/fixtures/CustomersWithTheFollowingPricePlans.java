package com.acmetelecom.fixtures;

import com.acmetelecom.customer.Customer;
import fit.ColumnFixture;
import fit.Parse;

/**
 * User: javad
 * Date: 06/12/2011
 */
public class CustomersWithTheFollowingPricePlans extends ColumnFixture {

    public String Name;
    public String Number;
    public String PricePlan;

    @Override
	public void doRows(Parse rows) {
        SystemUnderTest.customers.clear();
		super.doRows(rows);
	}

	@Override
	public void reset() throws Exception {
		Name = null;
        Number = null;
        PricePlan = null;
        super.reset();
	}

	@Override
	public void execute() throws Exception {
        SystemUnderTest.customers.add(new Customer(Name, Number, PricePlan));
        super.execute();
	}

}
