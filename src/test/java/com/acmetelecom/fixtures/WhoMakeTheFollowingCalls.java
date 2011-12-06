package com.acmetelecom.fixtures;

import com.acmetelecom.SystemUnderTest;
import fit.ColumnFixture;
import fit.Parse;

/**
 * User: javad
 * Date: 06/12/2011
 */
public class WhoMakeTheFollowingCalls extends ColumnFixture {

    public String time;
    public String caller;
    public String callee;
    public String duration;

    @Override
	public void doRows(Parse rows) {
		SystemUnderTest.reset();
		super.doRows(rows);
	}

	@Override
	public void reset() throws Exception {
		time = null;
        caller = null;
        callee = null;
        duration = null;
	}

	@Override
	public void execute() throws Exception {
		SystemUnderTest.billingSystem.callInitiated(caller, callee);
        SystemUnderTest.billingSystem.callCompleted(caller, callee);
	}

}
