package com.acmetelecom;

import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 
 * @author dc408, ra808, je08, jm308
 */
public class BillingSystem {

    private List<CallEvent> callLog = new ArrayList<CallEvent>();

    /**
     * 
     * @param caller
     * @param callee
     */
    public void callInitiated(String caller, String callee) {
        callLog.add(new CallStart(caller, callee));
    }

    /**
     * 
     * @param caller
     * @param callee
     */
    public void callCompleted(String caller, String callee) {
        callLog.add(new CallEnd(caller, callee));
    }

    /**
     * 
     */
    public void createCustomerBills() {
        // todo: can't inject customers, needed for tests
        List<Customer> customers = CentralCustomerDatabase.getInstance().getCustomers();
        for (Customer customer : customers) {
            createBillFor(customer);
        }
        callLog.clear();
    }

    /**
     *
     * @param customer
     */
    private void createBillFor(Customer customer) {
        List<CallEvent> customerEvents = new ArrayList<CallEvent>();
        for (CallEvent callEvent : callLog) {
            if (callEvent.getCaller().equals(customer.getPhoneNumber())) {
                customerEvents.add(callEvent);
            }
        }

        List<Call> calls = new ArrayList<Call>();

        CallEvent start = null;
        // todo: remove instanceof checks for CallStart and CallEnd
        for (CallEvent event : customerEvents) {
            if (event instanceof CallStart) {
                start = event;
            }
            if (event instanceof CallEnd && start != null) {
                calls.add(new Call(start, event));
                start = null;
            }
        }

        BigDecimal totalBill = new BigDecimal(0);
        List<LineItem> items = new ArrayList<LineItem>();

        for (Call call : calls) {

            // todo: can't inject tarriffs, needed for tests
            Tariff tariff = CentralTariffDatabase.getInstance().tarriffFor(customer);

            BigDecimal cost;

            DaytimePeakPeriod peakPeriod = new DaytimePeakPeriod();
            if (peakPeriod.offPeak(call.startTime()) && peakPeriod.offPeak(call.endTime()) && call.durationSeconds() < 12 * 60 * 60) {
                cost = new BigDecimal(call.durationSeconds()).multiply(tariff.offPeakRate());
            } else {
                cost = new BigDecimal(call.durationSeconds()).multiply(tariff.peakRate());
            }

            cost = cost.setScale(0, RoundingMode.HALF_UP);
            BigDecimal callCost = cost;
            totalBill = totalBill.add(callCost);
            items.add(new LineItem(call, callCost));
        }
        // todo: pull all the above into a method so can sense line items given some calls

        // todo: need a fitnesse test for checking bill format
        new BillGenerator().send(customer, items, MoneyFormatter.penceToPounds(totalBill));
    }


    static class LineItem {
        private Call call;
        private BigDecimal callCost;

        /**
         *
         * @param call
         * @param callCost
         */
        public LineItem(Call call, BigDecimal callCost) {
            this.call = call;
            this.callCost = callCost;
        }

        /**
         *
         * @return
         */
        public String date() {
            return call.date();
        }

        /**
         *
         * @return
         */
        public String callee() {
            return call.callee();
        }

        /**
         *
         * @return
         */
        public String durationMinutes() {
            return "" + call.durationSeconds() / 60 + ":" + String.format("%02d", call.durationSeconds() % 60);
        }

        /**
         *
         * @return
         */
        public BigDecimal cost() {
            return callCost;
        }
    }
}