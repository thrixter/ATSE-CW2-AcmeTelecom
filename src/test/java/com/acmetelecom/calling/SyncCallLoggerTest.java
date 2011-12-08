package com.acmetelecom.calling;

import com.acmetelecom.customer.Customer;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

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

    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    @Test
    public void testCallIsLogged() {
        String calleeNumber = "2";

        callLog.callInitiated(callerNumber, calleeNumber, formatter.parseDateTime("2011-11-11 06:50"));
        callLog.callCompleted(callerNumber, calleeNumber, formatter.parseDateTime("2011-11-11 07:00"));

        List<Call> calls = callLog.getCallsFor(caller);
        assertTrue(calls.size() == 1);
    }

    @Test
    public void testMultipleCallsBySameCallerAreNotRecorded() {
        String firstCalleeNumber = "2";
        String secondCalleeNumber = "3";

        callLog.callInitiated(callerNumber, firstCalleeNumber, formatter.parseDateTime("2011-11-11 06:50"));
        callLog.callInitiated(callerNumber, secondCalleeNumber, formatter.parseDateTime("2011-11-11 06:55"));

        callLog.callCompleted(callerNumber, firstCalleeNumber, formatter.parseDateTime("2011-11-11 07:00"));
        callLog.callCompleted(callerNumber, secondCalleeNumber, formatter.parseDateTime("2011-11-11 07:10"));

        List<Call> calls = callLog.getCallsFor(caller);
        assertTrue(calls.size() == 1);
    }

    @Test
    public void testClear() {
        List<Call> calls;
        String calleeNumber = "2";

        callLog.callInitiated(callerNumber, calleeNumber, formatter.parseDateTime("2011-11-11 06:50"));
        callLog.callCompleted(callerNumber, calleeNumber, formatter.parseDateTime("2011-11-11 07:00"));

        calls = callLog.getCallsFor(caller);
        assertTrue(calls.size() == 1);
        callLog.clear();

        calls = callLog.getCallsFor(caller);
        assertTrue(calls.size() == 0);
    }

}
