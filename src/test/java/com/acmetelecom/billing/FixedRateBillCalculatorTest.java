package com.acmetelecom.billing;

import com.acmetelecom.billing.BillCalculator;
import com.acmetelecom.billing.DaytimePeakPeriod;
import com.acmetelecom.billing.FixedRateBillCalulator;
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
public class FixedRateBillCalculatorTest {

    @Test
    public void testCallsWithNoOnPeakPeriod() {
        CallStart callStart = new CallStart("Dan", "Javad", Timestamp.valueOf("2011-11-11 05:00:00").getTime());
        CallEnd callEnd = new CallEnd("Dan", "Javad", Timestamp.valueOf("2011-11-11 06:00:00").getTime());
        Call call = new Call(callStart, callEnd);
        DaytimePeakPeriod peakPeriod = new DaytimePeakPeriod();
        BillCalculator billCalulator = new FixedRateBillCalulator();
        assertTrue(billCalulator.getCallCost(call, Tariff.Standard, peakPeriod).intValue() == 720);
    }

    @Test
    public void testCallsWithNoOffPeakPeriod() {
        CallStart callStart = new CallStart("Dan", "Javad", Timestamp.valueOf("2011-11-11 08:00:00").getTime());
        CallEnd callEnd = new CallEnd("Dan", "Javad", Timestamp.valueOf("2011-11-11 09:00:00").getTime());
        Call call = new Call(callStart, callEnd);
        DaytimePeakPeriod peakPeriod = new DaytimePeakPeriod();
        BillCalculator billCalulator = new FixedRateBillCalulator();
        assertTrue(billCalulator.getCallCost(call, Tariff.Standard, peakPeriod).intValue() == 1800);
    }

    @Test
    public void testCallsWithMixedPeakPeriod() {
        CallStart callStart = new CallStart("Dan", "Javad", Timestamp.valueOf("2011-11-11 06:00:00").getTime());
        CallEnd callEnd = new CallEnd("Dan", "Javad", Timestamp.valueOf("2011-11-11 08:00:00").getTime());
        Call call = new Call(callStart, callEnd);
        DaytimePeakPeriod peakPeriod = new DaytimePeakPeriod();
        BillCalculator billCalulator = new FixedRateBillCalulator();
        assertTrue(billCalulator.getCallCost(call, Tariff.Standard, peakPeriod).intValue() == 3600);
    }

}
