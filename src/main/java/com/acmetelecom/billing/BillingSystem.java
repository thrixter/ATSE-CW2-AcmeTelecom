package com.acmetelecom.billing;

import com.acmetelecom.calling.Call;
import com.acmetelecom.calling.CallLogger;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.CustomerDatabase;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.customer.TariffLibrary;
import com.acmetelecom.printing.BillGenerator;
import com.acmetelecom.printing.LineItem;
import com.acmetelecom.printing.MoneyFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author dc408, ra808, je08, jm308
 */
public class BillingSystem {

    private CallLogger callLogger;
    private BillGenerator billGenerator;
    private CustomerDatabase customerDatabase;
    private TariffLibrary tariffDatabase;

    public BillingSystem(CallLogger callLogger,
                         CustomerDatabase customerDatabase,
                         TariffLibrary tariffDatabase,
                         BillGenerator billGenerator) {
        this.callLogger = callLogger;
        this.billGenerator = billGenerator;
        this.customerDatabase = customerDatabase;
        this.tariffDatabase = tariffDatabase;
    }

    /**
     * 
     */
    public void createCustomerBills() {
        List<Customer> customers = customerDatabase.getCustomers();
        for (Customer customer : customers) {
            createBillFor(customer);
        }
        callLogger.clear();
    }

    /**
     *
     * @param customer
     */
    private void createBillFor(Customer customer) {
        List<Call> calls = callLogger.getCallsFor(customer);

        Tariff tariff = tariffDatabase.tarriffFor(customer);

        List<LineItem> items = calculateCostOf(calls, tariff);

        BigDecimal totalBill = calculateTotalBill(items);

        billGenerator.send(customer, items, MoneyFormatter.penceToPounds(totalBill));
    }

    private List<LineItem> calculateCostOf(List<Call> calls, Tariff tariff) {
        List<LineItem> items = new ArrayList<LineItem>();

        for (Call call : calls) {
            BigDecimal callCost = calculateCostOf(call, tariff);
            items.add(new BillItem(call, callCost));
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
