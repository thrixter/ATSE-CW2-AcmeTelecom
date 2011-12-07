package com.acmetelecom;

import com.acmetelecom.customer.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 
 * @author dc408, ra808, je08, jm308
 */
public class BillingSystem {

    private List<CallEvent> callLog = new ArrayList<CallEvent>();

    private BillGenerator billGenerator;
    private CustomerDatabase customerDatabase;
    private TariffLibrary tariffDatabase;

    public BillingSystem(CustomerDatabase customerDatabase,
                         TariffLibrary tariffDatabase,
                         BillGenerator billGenerator) {
        this.billGenerator = billGenerator;
        this.customerDatabase = customerDatabase;
        this.tariffDatabase = tariffDatabase;
    }

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
        List<Customer> customers = customerDatabase.getCustomers();
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
        List<Call> calls = getCallsFor(customer);

        Tariff tariff = tariffDatabase.tarriffFor(customer);

        List<LineItem> items = calculateCostOf(calls, tariff);

        BigDecimal totalBill = calculateTotalBill(items);

        billGenerator.send(customer, items, MoneyFormatter.penceToPounds(totalBill));
    }

    private List<Call> getCallsFor(Customer customer) {
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
        return calls;
    }

    private List<LineItem> calculateCostOf(List<Call> calls, Tariff tariff) {
        List<LineItem> items = new ArrayList<LineItem>();

        for (Call call : calls) {

            BigDecimal callCost = calculateCostOf(call, tariff);
            items.add(new LineItem(call, callCost));
        }
        return items;
    }

    private BigDecimal calculateCostOf(Call call, Tariff tariff) {
        BigDecimal cost;

        DaytimePeakPeriod peakPeriod = new DaytimePeakPeriod();
        if (peakPeriod.offPeak(call.startTime()) && peakPeriod.offPeak(call.endTime()) && call.durationSeconds() < 12 * 60 * 60) {
            cost = new BigDecimal(call.durationSeconds()).multiply(tariff.offPeakRate());
        } else {
            cost = new BigDecimal(call.durationSeconds()).multiply(tariff.peakRate());
        }

        cost = cost.setScale(0, RoundingMode.HALF_UP);
        return cost;
    }

    private BigDecimal calculateTotalBill(List<LineItem> items) {
        BigDecimal totalBill = new BigDecimal(0);

        for (LineItem lineItem : items) {
            totalBill = totalBill.add(lineItem.cost());
        }

        return totalBill;
    }

}
