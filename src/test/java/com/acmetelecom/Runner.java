package com.acmetelecom;

import com.acmetelecom.billing.BillCalculator;
import com.acmetelecom.billing.BillingSystem;
import com.acmetelecom.billing.DaytimePeakPeriod;
import com.acmetelecom.billing.FixedRateBillCalculator;
import com.acmetelecom.calling.CallLogger;
import com.acmetelecom.calling.SyncCallLogger;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.CustomerDatabase;
import com.acmetelecom.customer.TariffLibrary;
import com.acmetelecom.printing.BillGenerator;
import com.acmetelecom.printing.HtmlPrinter;
import com.acmetelecom.printing.UnorderedBillGenerator;
import org.joda.time.DateTime;

/**
 * User: javad
 * Date: 30/11/2011
 */
public class Runner {

    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Running...");

        CallLogger callLogger = new SyncCallLogger();
        CustomerDatabase customerDatabase = CentralCustomerDatabase.getInstance();
        TariffLibrary tariffDatabase = CentralTariffDatabase.getInstance();
        BillCalculator billCalculator = new FixedRateBillCalculator(new DaytimePeakPeriod());
        BillGenerator billGenerator = new UnorderedBillGenerator(HtmlPrinter.getInstance());

        BillingSystem billingSystem = new BillingSystem(callLogger, customerDatabase, tariffDatabase, billGenerator);
        billingSystem.setBillCalculator(billCalculator);

        callLogger.callInitiated("447722113434", "447766814143", new DateTime());
        sleepSeconds(20);
        callLogger.callCompleted("447722113434", "447766814143", new DateTime());
        callLogger.callInitiated("447722113434", "447711111111", new DateTime());
        sleepSeconds(30);
        callLogger.callCompleted("447722113434", "447711111111", new DateTime());
        callLogger.callInitiated("447777765432", "447711111111", new DateTime());
        sleepSeconds(60);
        callLogger.callCompleted("447777765432", "447711111111", new DateTime());

        billingSystem.createCustomerBills();
    }

    private static void sleepSeconds(int n) throws InterruptedException {
        Thread.sleep(n * 1000);
    }
}
