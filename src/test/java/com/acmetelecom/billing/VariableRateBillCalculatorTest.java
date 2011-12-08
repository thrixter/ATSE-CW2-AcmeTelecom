package com.acmetelecom.billing;

import com.acmetelecom.calling.Call;
import com.acmetelecom.calling.CallEnd;
import com.acmetelecom.calling.CallStart;
import com.acmetelecom.customer.Tariff;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class VariableRateBillCalculatorTest {

    private VariableRateBillCalculator billCalulator
            = new VariableRateBillCalculator(new DaytimePeakPeriod());

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    @Test
    public void testCallsWithNoOnPeakPeriod() {
        CallStart callStart = new CallStart("Dan", "Javad", formatter.parseDateTime("2011-11-11 05:00"));
        CallEnd callEnd = new CallEnd("Dan", "Javad", formatter.parseDateTime("2011-11-11 06:00"));
        Call call = new Call(callStart, callEnd);

        assertTrue(billCalulator.getCallCost(call, Tariff.Standard).intValue() == 720);
    }

    @Test
    public void testCallsWithNoOffPeakPeriod() {
        CallStart callStart = new CallStart("Dan", "Javad", formatter.parseDateTime("2011-11-11 08:00"));
        CallEnd callEnd = new CallEnd("Dan", "Javad", formatter.parseDateTime("2011-11-11 09:00"));
        Call call = new Call(callStart, callEnd);

        assertTrue(billCalulator.getCallCost(call, Tariff.Standard).intValue() == 1800);
    }

    @Test
    public void testCallsWithMixedDaytimePeriod() {
        CallStart callStart = new CallStart("Dan", "Javad", formatter.parseDateTime("2011-11-11 06:00"));
        CallEnd callEnd = new CallEnd("Dan", "Javad", formatter.parseDateTime("2011-11-11 08:00"));
        Call call = new Call(callStart, callEnd);
        
        assertTrue(billCalulator.getCallCost(call, Tariff.Standard).intValue() == 2520);
    }

}
