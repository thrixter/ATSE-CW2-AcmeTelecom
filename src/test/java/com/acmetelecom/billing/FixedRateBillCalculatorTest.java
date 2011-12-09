package com.acmetelecom.billing;

import com.acmetelecom.calling.Call;
import com.acmetelecom.calling.CallEnd;
import com.acmetelecom.calling.CallStart;
import com.acmetelecom.customer.Tariff;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class FixedRateBillCalculatorTest {

    private BillCalculator billCalulator = new FixedRateBillCalculator(new DaytimePeakPeriod());

    private final String caller = "Dan";
    private final String callee = "Javad";
    private final Tariff tariff = Tariff.Standard;

    private final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    @Test
    public void testCallDuringOffPeakPeriodOnly() {
        DateTime startTime = formatter.parseDateTime("2011-11-14 05:00");
        DateTime endTime = formatter.parseDateTime("2011-11-14 06:00");

        CallStart callStart = new CallStart(caller, callee, startTime);
        CallEnd callEnd = new CallEnd(caller, callee, endTime);
        Call call = new Call(callStart, callEnd);

        BigDecimal callCost = billCalulator.getCallCost(call, tariff);
        BigDecimal expectedCallCost = new BigDecimal(720);

        assertThat(callCost, is(equalTo(expectedCallCost)));
    }

    @Test
    public void testCallDuringOffPeakPeriodOnlyEndingTheNextDay() {
        DateTime startTime = formatter.parseDateTime("2011-11-14 23:00");
        DateTime endTime = formatter.parseDateTime("2011-11-15 01:00");

        CallStart callStart = new CallStart(caller, callee, startTime);
        CallEnd callEnd = new CallEnd(caller, callee, endTime);
        Call call = new Call(callStart, callEnd);

        BigDecimal callCost = billCalulator.getCallCost(call, tariff);
        BigDecimal expectedCallCost = new BigDecimal(1440);

        assertThat(callCost, is(equalTo(expectedCallCost)));
    }

    @Test
    public void testCallDuringPeakPeriodOnly() {
        DateTime startTime = formatter.parseDateTime("2011-11-11 08:00");
        DateTime endTime = formatter.parseDateTime("2011-11-11 09:00");

        CallStart callStart = new CallStart(caller, callee, startTime);
        CallEnd callEnd = new CallEnd(caller, callee, endTime);
        Call call = new Call(callStart, callEnd);

        BigDecimal callCost = billCalulator.getCallCost(call, tariff);
        BigDecimal expectedCallCost = new BigDecimal(1800);

        assertThat(callCost, is(equalTo(expectedCallCost)));
    }

    @Test
    public void testCallsWithMixedDaytimePeriod() {
        DateTime startTime = formatter.parseDateTime("2011-11-11 06:00");
        DateTime endTime = formatter.parseDateTime("2011-11-11 08:00");

        CallStart callStart = new CallStart(caller, callee, startTime);
        CallEnd callEnd = new CallEnd(caller, callee, endTime);
        Call call = new Call(callStart, callEnd);

        BigDecimal callCost = billCalulator.getCallCost(call, tariff);
        BigDecimal expectedCallCost = new BigDecimal(3600);

        assertThat(callCost, is(equalTo(expectedCallCost)));
    }

}
