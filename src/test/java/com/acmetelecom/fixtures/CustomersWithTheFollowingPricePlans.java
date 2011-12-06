package com.acmetelecom.fixtures;

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
//		SystemUnderTest.reset();
		super.doRows(rows);
	}

	@Override
	public void reset() throws Exception {
		Name = null;
        Number = null;
        PricePlan = null;
	}

	@Override
	public void execute() throws Exception {
//		SystemUnderTest.billingSystem.callInitiated(caller, callee);
//        SystemUnderTest.billingSystem.callCompleted(caller, callee);
	}

}
