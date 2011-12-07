package com.acmetelecom;

import com.acmetelecom.printing.BillGenerator;
import com.acmetelecom.billing.BillingSystem;
import com.acmetelecom.calling.CallLogger;
import com.acmetelecom.calling.SyncCallLogger;
import com.acmetelecom.customer.*;
import com.acmetelecom.printing.UnorderedBillGenerator;

import java.sql.Timestamp;

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

        CustomerDatabase customerDatabase = CentralCustomerDatabase.getInstance();
        TariffLibrary tariffDatabase = CentralTariffDatabase.getInstance();
        BillGenerator billGenerator = new UnorderedBillGenerator();

        CallLogger callLogger = new SyncCallLogger();
        BillingSystem billingSystem = new BillingSystem(callLogger, customerDatabase, tariffDatabase, billGenerator);

        callLogger.callInitiated("447722113434", "447766814143", Timestamp.valueOf("2011-11-30 05:00:00").getTime());
        callLogger.callCompleted("447722113434", "447766814143", Timestamp.valueOf("2011-11-30 06:00:00").getTime());

        callLogger.callInitiated("447722113434", "447777765432", Timestamp.valueOf("2011-11-30 04:00:00").getTime());
        callLogger.callCompleted("447722113434", "447777765432", Timestamp.valueOf("2011-11-30 04:30:00").getTime());

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
