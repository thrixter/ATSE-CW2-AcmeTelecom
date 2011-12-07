package com.acmetelecom.billing;

import com.acmetelecom.calling.Call;
import com.acmetelecom.calling.CallEnd;
import com.acmetelecom.calling.CallStart;
import com.acmetelecom.customer.Tariff;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.assertTrue;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class VariableRateBillCalculatorTest {

    private DaytimePeakPeriod peakPeriod = new DaytimePeakPeriod();

    @Test
    public void testCallsWithNoOnPeakPeriod() {
        CallStart callStart = new CallStart("Dan", "Javad", Timestamp.valueOf("2011-11-11 05:00:00").getTime());
        CallEnd callEnd = new CallEnd("Dan", "Javad", Timestamp.valueOf("2011-11-11 06:00:00").getTime());
        Call call = new Call(callStart, callEnd);

        VariableRateBillCalculator billCalulator = new VariableRateBillCalculator();
        assertTrue(billCalulator.getCallCost(call, Tariff.Standard, peakPeriod).intValue() == 720);
    }

    @Test
    public void testCallsWithNoOffPeakPeriod() {
        CallStart callStart = new CallStart("Dan", "Javad", Timestamp.valueOf("2011-11-11 08:00:00").getTime());
        CallEnd callEnd = new CallEnd("Dan", "Javad", Timestamp.valueOf("2011-11-11 09:00:00").getTime());
        Call call = new Call(callStart, callEnd);

        VariableRateBillCalculator billCalulator = new VariableRateBillCalculator();
        assertTrue(billCalulator.getCallCost(call, Tariff.Standard, peakPeriod).intValue() == 1800);
    }

    @Test
    public void testCallsWithMixedDaytimePeriod() {
        CallStart callStart = new CallStart("Dan", "Javad", Timestamp.valueOf("2011-11-11 06:00:00").getTime());
        CallEnd callEnd = new CallEnd("Dan", "Javad", Timestamp.valueOf("2011-11-11 08:00:00").getTime());
        Call call = new Call(callStart, callEnd);
        
        VariableRateBillCalculator billCalulator = new VariableRateBillCalculator();
        assertTrue(billCalulator.getCallCost(call, Tariff.Standard, peakPeriod).intValue() == 2520);
    }

}
