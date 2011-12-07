package com.acmetelecom;

import com.acmetelecom.customer.Customer;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class SyncCallLoggerTest {

    private CallLogger callLog = new SyncCallLogger();

    private String callerNumber = "1";
    private Customer caller = new Customer("Dan Cooke", callerNumber, "Standard");

    @Before
    public void setUpCustomer() {

    }

    @Test
    public void testCallIsLogged() {
        String calleeNumber = "2";

        callLog.callInitiated(callerNumber, calleeNumber, Timestamp.valueOf("2011-11-11 06:50:00").getTime());
        callLog.callCompleted(callerNumber, calleeNumber, Timestamp.valueOf("2011-11-11 07:00:00").getTime());

        List<Call> calls = callLog.getCallsFor(caller);
        assertTrue(calls.size() == 1);
    }

    @Test
    public void testMultipleCallsBySameCallerAreNotRecorded() {
        String firstCalleeNumber = "2";
        String secondCalleeNumber = "3";

        callLog.callInitiated(callerNumber, firstCalleeNumber, Timestamp.valueOf("2011-11-11 06:50:00").getTime());
        callLog.callInitiated(callerNumber, secondCalleeNumber, Timestamp.valueOf("2011-11-11 06:55:00").getTime());

        callLog.callCompleted(callerNumber, firstCalleeNumber, Timestamp.valueOf("2011-11-11 07:00:00").getTime());
        callLog.callCompleted(callerNumber, secondCalleeNumber, Timestamp.valueOf("2011-11-11 07:10:00").getTime());

        List<Call> calls = callLog.getCallsFor(caller);
        assertTrue(calls.size() == 1);
    }

    @Test
    public void testClear() {
        List<Call> calls;
        String calleeNumber = "2";

        callLog.callInitiated(callerNumber, calleeNumber, Timestamp.valueOf("2011-11-11 06:50:00").getTime());
        callLog.callCompleted(callerNumber, calleeNumber, Timestamp.valueOf("2011-11-11 07:00:00").getTime());

        calls = callLog.getCallsFor(caller);
        assertTrue(calls.size() == 1);
        callLog.clear();

        calls = callLog.getCallsFor(caller);
        assertTrue(calls.size() == 0);
    }

}
