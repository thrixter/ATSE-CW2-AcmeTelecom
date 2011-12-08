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
public class CallEndTest {

    private CallEnd callEnd;

    private String caller;
    private String callee;
    private DateTime endTime;

    @Before
    public void setUpCall() {
        caller = "1";
        callee = "2";
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        endTime = formatter.parseDateTime("2011-11-30 11:30");

        callEnd = new CallEnd(caller, callee, endTime);
    }

    @Test
    public void testSameCallIsEqual() {
        assertThat(callEnd, is(equalTo(callEnd)));
    }

    @Test
    public void testCallStartIsEqual() {
        CallEnd otherEnd = new CallEnd(caller, callee, endTime);

        assertThat(callEnd, is(equalTo(otherEnd)));
        assertThat(otherEnd, is(equalTo(callEnd)));
    }

    @Test
    public void testObjectIsNotEqual() {
        Object object = new Object();

        assertThat(callEnd, is(not(equalTo(object))));
    }

    @Test
    public void testNullIsNotEqual() {
        assertNotEqualTo(callEnd, null);
    }

    @Test
    public void testCallEventWithMissingCallerIsNotEqual() {
        CallEnd otherEnd = new CallEnd(null, callee, endTime);

        assertNotEqualTo(callEnd, otherEnd);
    }

    @Test
    public void testCallEventWithMissingCalleeIsNotEqual() {
        CallEnd otherEnd = new CallEnd(caller, null, endTime);

        assertNotEqualTo(callEnd, otherEnd);
    }

    @Test
    public void testCallWithEmptyDataIsNotEqual() {
        CallEnd otherEnd = new CallEnd(null, null, null);

        assertNotEqualTo(callEnd, otherEnd);
    }

    @Test
    public void testCallEventWithDifferentCallerIsNotEqual() {
        String otherCaller = "4";
        CallEnd otherEnd = new CallEnd(otherCaller, callee, endTime);

        assertNotEqualTo(callEnd, otherEnd);
    }

    @Test
    public void testCallEventWithDifferentCalleeIsNotEqual() {
        String otherCallee = "4";
        CallEnd otherEnd= new CallEnd(caller, otherCallee, endTime);

        assertNotEqualTo(callEnd, otherEnd);
    }

    @Test
    public void testCallEventWithDifferentStartTimeIsNotEqual() {
        CallEnd otherEnd = new CallEnd(caller, callee, new DateTime());

        assertNotEqualTo(callEnd, otherEnd);
    }

    private void assertNotEqualTo(CallEnd theEnd, CallEnd otherEnd) {
        assertThat(theEnd, is(not(equalTo(otherEnd))));
        assertThat(otherEnd, is(not(equalTo(theEnd))));
    }

}
