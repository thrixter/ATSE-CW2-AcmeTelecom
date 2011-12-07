package com.acmetelecom;

import com.acmetelecom.customer.*;

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
        BillGenerator billGenerator = new HTMLBillGenerator();

        CallLogger callLogger = new SyncCallLogger();
        callLogger.callInitiated("447722113434", "447766814143", System.currentTimeMillis());
        sleepSeconds(20);
        callLogger.callCompleted("447722113434", "447766814143", System.currentTimeMillis());
        callLogger.callInitiated("447722113434", "447711111111", System.currentTimeMillis());
        sleepSeconds(30);
        callLogger.callCompleted("447722113434", "447711111111", System.currentTimeMillis());
        callLogger.callInitiated("447777765432", "447711111111", System.currentTimeMillis());
        sleepSeconds(60);
        callLogger.callCompleted("447777765432", "447711111111", System.currentTimeMillis());

        BillingSystem billingSystem = new BillingSystem(callLogger, customerDatabase, tariffDatabase, billGenerator);
        billingSystem.createCustomerBills();
    }

    private static void sleepSeconds(int n) throws InterruptedException {
        Thread.sleep(n * 1000);
    }
}
