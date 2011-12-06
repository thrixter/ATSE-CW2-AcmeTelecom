package com.acmetelecom.fixtures;

import fit.ColumnFixture;
import fit.Parse;

/**
 * User: javad
 * Date: 06/12/2011
 */
public class WhoMakeTheFollowingCalls extends ColumnFixture {

    public String From;
    public String To;
    public String StartTime;
    public String EndTime;

    @Override
	public void doRows(Parse rows) {
//		SystemUnderTest.reset();
		super.doRows(rows);
	}

	@Override
	public void reset() throws Exception {
		From = null;
        To = null;
        StartTime = null;
        EndTime = null;
	}

	@Override
	public void execute() throws Exception {
//		SystemUnderTest.billingSystem.callInitiated(caller, callee);
//        SystemUnderTest.billingSystem.callCompleted(caller, callee);
	}

}
