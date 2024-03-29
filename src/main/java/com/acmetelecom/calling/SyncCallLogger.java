package com.acmetelecom.calling;

import com.acmetelecom.customer.Customer;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * User: javad
 * Date: 07/12/2011
 */
public class SyncCallLogger implements CallLogger {

    private List<CallEvent> callLog;

    public SyncCallLogger() {
       callLog = new ArrayList<CallEvent>();
    }

    public void callInitiated(String caller, String callee, DateTime time) {
        callLog.add(new CallStart(caller, callee, time));
    }

    public void callCompleted(String caller, String callee, DateTime time) {
        callLog.add(new CallEnd(caller, callee, time));
    }

    public void clear() {
        callLog.clear();
    }

    public List<Call> getCallsFor(Customer customer) {
        List<CallEvent> customerEvents = new ArrayList<CallEvent>();
        for (CallEvent callEvent : callLog) {
            if (callEvent.getCaller().equals(customer.getPhoneNumber())) {
                customerEvents.add(callEvent);
            }
        }
        List<Call> calls = new ArrayList<Call>();
        CallEvent start = null;
        for (CallEvent event : customerEvents) {
            if (event instanceof CallStart) {
                start = event;
            } else if(start != null) {
                calls.add(new Call(start, event));
                start = null;
            }
        }
        return calls;
    }

}
