package com.acmetelecom;

import com.acmetelecom.customer.Customer;

import java.util.List;

/**
 * User: javad
 * Date: 07/12/2011
 */
public interface CallLogger {
    void callInitiated(String caller, String callee, long timestamp);

    void callCompleted(String caller, String callee, long timestamp);

    void clear();

    List<Call> getCalls(Customer customer);
}
