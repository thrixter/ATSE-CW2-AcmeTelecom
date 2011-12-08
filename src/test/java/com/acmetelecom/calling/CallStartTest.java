package com.acmetelecom.calling;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class CallStartTest {

    private CallStart callStart;

    private String caller;
    private String callee;
    private DateTime startTime;

    @Before
    public void setUpCall() {
        caller = "1";
        callee = "2";
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        startTime = formatter.parseDateTime("2011-11-30 11:30");

        callStart = new CallStart(caller, callee, startTime);
    }

    @Test
    public void testSameCallIsEqual() {
        assertThat(callStart, is(equalTo(callStart)));
    }

    @Test
    public void testCallStartIsEqual() {
        CallStart otherStart = new CallStart(caller, callee, startTime);

        assertThat(callStart, is(equalTo(otherStart)));
        assertThat(otherStart, is(equalTo(callStart)));
    }

    @Test
    public void testObjectIsNotEqual() {
        Object object = new Object();

        assertThat(callStart, is(not(equalTo(object))));
    }

    @Test
    public void testNullIsNotEqual() {
        assertNotEqualTo(callStart, null);
    }

    @Test
    public void testCallEventWithMissingCallerIsNotEqual() {
        CallStart otherStart = new CallStart(null, callee, startTime);

        assertNotEqualTo(callStart, otherStart);
    }

    @Test
    public void testCallEventWithMissingCalleeIsNotEqual() {
        CallStart otherStart = new CallStart(caller, null, startTime);

        assertNotEqualTo(callStart, otherStart);
    }

    @Test
    public void testCallWithEmptyDataIsNotEqual() {
        CallStart otherStart = new CallStart(null, null, null);

        assertNotEqualTo(callStart, otherStart);
    }

    @Test
    public void testCallEventWithDifferentCallerIsNotEqual() {
        String otherCaller = "4";
        CallStart otherStart = new CallStart(otherCaller, callee, startTime);

        assertNotEqualTo(callStart, otherStart);
    }

    @Test
    public void testCallEventWithDifferentCalleeIsNotEqual() {
        String otherCallee = "4";
        CallStart otherStart = new CallStart(caller, otherCallee, startTime);

        assertNotEqualTo(callStart, otherStart);
    }

    @Test
    public void testCallEventWithDifferentStartTimeIsNotEqual() {
        CallStart otherStart = new CallStart(caller, callee, new DateTime());

        assertNotEqualTo(callStart, otherStart);
    }

    private void assertNotEqualTo(CallStart theStart, CallStart otherStart) {
        assertThat(theStart, is(not(equalTo(otherStart))));
        assertThat(otherStart, is(not(equalTo(theStart))));
    }

}
