package com.acmetelecom.calling;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class CallTest {

    private Call call;

    private CallStart callStart;
    private CallEnd callEnd;

    @Before
    public void setUpCall() {
        String caller = "1";
        String callee = "2";
        long startTime = Timestamp.valueOf("2011-11-30 11:30:00").getTime();
        long endTime = Timestamp.valueOf("2011-11-30 12:00:00").getTime();

        callStart = new CallStart(caller, callee, startTime);
        callEnd = new CallEnd(caller, callee, endTime);

        call = new Call(callStart, callEnd);
    }

    @Test
    public void testSameCallIsEqual() {
        assertThat(call, is(equalTo(call)));
    }

    @Test
    public void testCallIsEqual() {
        Call otherCall = new Call(callStart, callEnd);

        assertThat(call, is(equalTo(otherCall)));
        assertThat(otherCall, is(equalTo(call)));
    }

    @Test
    public void testObjectIsNotEqual() {
        Object object = new Object();

        assertThat(call, is(not(equalTo(object))));
    }

    @Test
    public void testNullIsNotEqual() {
        assertNotEqualTo(call, null);
    }

    @Test
    public void testCallWithMissingStartIsNotEqual() {
        Call otherCall = new Call(null, callEnd);

        assertNotEqualTo(call, otherCall);
    }

    @Test
    public void testCallWithMissingEndIsNotEqual() {
        Call otherCall = new Call(callStart, null);

        assertNotEqualTo(call, otherCall);
    }

    @Test
    public void testCallWithEmptyDataIsNotEqual() {
        CallStart otherStart = new CallStart(null, null, 0);
        CallEnd otherEnd = new CallEnd(null, null, 0);
        Call otherCall = new Call(otherStart, otherEnd);

        assertNotEqualTo(call, otherCall);
    }

    @Test
    public void testCallWithDifferentStartIsNotEqual() {
        CallStart otherStart = new CallStart("1", "3", 5);
        Call otherCall = new Call(otherStart, callEnd);

        assertNotEqualTo(call, otherCall);
    }

    @Test
    public void testCallWithDifferentEndIsNotEqual() {
        CallEnd otherEnd = new CallEnd("1", "3", 5);
        Call otherCall = new Call(callStart, otherEnd);

        assertNotEqualTo(call, otherCall);
    }

    private void assertNotEqualTo(Call theCall, Call otherCall) {
        assertThat(theCall, is(not(equalTo(otherCall))));
        assertThat(otherCall, is(not(equalTo(theCall))));
    }

}
