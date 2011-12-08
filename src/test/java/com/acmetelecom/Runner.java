package com.acmetelecom;

import com.acmetelecom.billing.BillCalculator;
import com.acmetelecom.billing.BillingSystem;
import com.acmetelecom.billing.DaytimePeakPeriod;
import com.acmetelecom.billing.FixedRateBillCalulator;
import com.acmetelecom.calling.CallLogger;
import com.acmetelecom.calling.SyncCallLogger;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.CustomerDatabase;
import com.acmetelecom.customer.TariffLibrary;
import com.acmetelecom.printing.BillGenerator;
import com.acmetelecom.printing.HtmlPrinter;
import com.acmetelecom.printing.UnorderedBillGenerator;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
        BillCalculator billCalculator = new FixedRateBillCalulator(new DaytimePeakPeriod());
        BillGenerator billGenerator = new UnorderedBillGenerator(HtmlPrinter.getInstance());

        BillingSystem billingSystem = new BillingSystem(callLogger, customerDatabase, tariffDatabase, billGenerator);
        billingSystem.setBillCalculator(billCalculator);

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

        callLogger.callInitiated("447722113434", "447766814143", formatter.parseDateTime("2011-11-30 05:00:00"));
        callLogger.callCompleted("447722113434", "447766814143", formatter.parseDateTime("2011-11-30 06:00:00"));

        callLogger.callInitiated("447722113434", "447777765432", formatter.parseDateTime("2011-11-30 04:00:00"));;
        callLogger.callCompleted("447722113434", "447777765432", formatter.parseDateTime("2011-11-30 05:00:00"));

        /*callLogger.callInitiated("447722113434", "447766814143", System.currentTimeMillis());
        sleepSeconds(20);
        callLogger.callCompleted("447722113434", "447766814143", System.currentTimeMillis());
        callLogger.callInitiated("447722113434", "447711111111", System.currentTimeMillis());
        sleepSeconds(30);
        callLogger.callCompleted("447722113434", "447711111111", System.currentTimeMillis());
        callLogger.callInitiated("447777765432", "447711111111", System.currentTimeMillis());
        sleepSeconds(60);
        callLogger.callCompleted("447777765432", "447711111111", System.currentTimeMillis());*/

        billingSystem.createCustomerBills();
    }

    private static void sleepSeconds(int n) throws InterruptedException {
        Thread.sleep(n * 1000);
    }
}