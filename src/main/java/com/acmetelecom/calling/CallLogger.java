package com.acmetelecom.calling;

import com.acmetelecom.customer.Customer;
import org.joda.time.DateTime;

import java.util.List;

/**
 * User: javad
 * Date: 07/12/2011
 */
public interface CallLogger {
    void callInitiated(String caller, String callee, DateTime time);

    void callCompleted(String caller, String callee, DateTime time);

    void clear();

    List<Call> getCallsFor(Customer customer);
}
