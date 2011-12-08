package com.acmetelecom.billing;

import com.acmetelecom.calling.Call;
import com.acmetelecom.calling.CallEnd;
import com.acmetelecom.calling.CallStart;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class BillItemTest {

    private BillItem billItem;

    private Call call;
    private BigDecimal callCost;

    @Before
    public void setUpCall() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

        String caller = "1";
        String callee = "2";
        DateTime startTime = formatter.parseDateTime("2011-11-30 11:30");
        DateTime endTime = formatter.parseDateTime("2011-11-30 12:00");

        call = new Call(
                new CallStart(caller, callee, startTime),
                new CallEnd(caller, callee, endTime)
        );

        callCost = BigDecimal.valueOf(1.0);

        billItem = new BillItem(call, callCost);
    }

    @Test
    public void testSameBillItemIsEqual() {
        assertThat(billItem, is(equalTo(billItem)));
    }

    @Test
    public void testBillItemIsEqual() {
        BillItem otherBillItem = new BillItem(call, callCost);

        assertThat(billItem, is(equalTo(otherBillItem)));
        assertThat(otherBillItem, is(equalTo(billItem)));
    }

    @Test
    public void testObjectIsNotEqual() {
        Object object = new Object();

        assertThat(billItem, is(not(equalTo(object))));
    }

    @Test
    public void testNullIsNotEqual() {
        assertNotEqualTo(billItem, null);
    }

    @Test
    public void testBillItemWithMissingCallIsNotEqual() {
        BillItem otherBillItem = new BillItem(null, callCost);

        assertNotEqualTo(billItem, otherBillItem);
    }

    @Test
    public void testBillItemWithMissingCallCostIsNotEqual() {
        BillItem otherBillItem = new BillItem(call, null);

        assertNotEqualTo(billItem, otherBillItem);
    }

    @Test
    public void testBillItemWithEmptyDataIsNotEqual() {
        BillItem otherBillItem = new BillItem(null, null);

        assertNotEqualTo(billItem, otherBillItem);
    }

    @Test
    public void testBillItemWithDifferentCallIsNotEqual() {
        Call otherCall = new Call(null, null);
        BillItem otherBillItem = new BillItem(otherCall, callCost);

        assertNotEqualTo(billItem, otherBillItem);
    }

    @Test
    public void testBillItemWithDifferentCallCostIsNotEqual() {
        BigDecimal otherCallCost = BigDecimal.valueOf(2.0);
        BillItem otherBillItem = new BillItem(call, otherCallCost);

        assertNotEqualTo(billItem, otherBillItem);
    }

    private void assertNotEqualTo(BillItem theBillItem, BillItem otherBillItem) {
        assertThat(theBillItem, is(not(equalTo(otherBillItem))));
        assertThat(otherBillItem, is(not(equalTo(theBillItem))));
    }

}
