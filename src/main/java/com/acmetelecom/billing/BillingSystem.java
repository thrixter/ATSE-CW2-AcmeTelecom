package com.acmetelecom.billing;

import com.acmetelecom.calling.Call;
import com.acmetelecom.calling.CallLogger;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.CustomerDatabase;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.customer.TariffLibrary;
import com.acmetelecom.printing.BillGenerator;
import com.acmetelecom.printing.LineItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author dc408, ra808, je08, jm308
 */
public class BillingSystem {

    private CallLogger callLogger;
    private CustomerDatabase customerDatabase;
    private TariffLibrary tariffDatabase;
    private BillCalculator billCalculator;
    private BillGenerator billGenerator;

    public BillingSystem(CallLogger callLogger,
                         CustomerDatabase customerDatabase,
                         TariffLibrary tariffDatabase,
                         BillCalculator billCalculator,
                         BillGenerator billGenerator) {
        this.callLogger = callLogger;
        this.customerDatabase = customerDatabase;
        this.tariffDatabase = tariffDatabase;
        this.billCalculator = billCalculator;
        this.billGenerator = billGenerator;
    }

    /**
     * 
     */
    public void createCustomerBills() {
        List<Customer> customers = customerDatabase.getCustomers();
        for (Customer customer : customers) {
            createBillFor(customer);
        }
    }

    /**
     *
     * @param customer
     */
    private void createBillFor(Customer customer) {
            List<Call> calls = callLogger.getCallsFor(customer);

        Tariff tariff = tariffDatabase.tarriffFor(customer);

        List<LineItem> items = calculateCostOf(calls, tariff);

        billGenerator.send(customer, items);
    }

    private List<LineItem> calculateCostOf(List<Call> calls, Tariff tariff) {
        List<LineItem> items = new ArrayList<LineItem>();

        for (Call call : calls) {
            BigDecimal callCost = billCalculator.getCallCost(call, tariff);
            items.add(new BillItem(call, callCost));
        }
        return items;
    }

}
