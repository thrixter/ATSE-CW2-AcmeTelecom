package com.acmetelecom.fixtures;

import fit.ColumnFixture;
import fit.Parse;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * User: javad
 * Date: 06/12/2011
 */
public class WhoMakeTheFollowingCalls extends ColumnFixture {

    public String From;
    public String To;
    public DateTime StartTime;
    public DateTime EndTime;

    @Override
	public void doRows(Parse rows) {
        SystemUnderTest.callLogger.clear();
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
        SystemUnderTest.callLogger.callInitiated(From, To, StartTime);
        SystemUnderTest.callLogger.callCompleted(From, To, EndTime);
	}

    @Override
    public Object parse(String s, Class<?> type) throws Exception {
		if(type == DateTime.class) {
			DateTimeFormatter format = DateTimeFormat.forPattern("dd/MM/yy HH:mm");
            return DateTime.parse(s, format);
		}
		return super.parse(s, type);
	}
    
}
