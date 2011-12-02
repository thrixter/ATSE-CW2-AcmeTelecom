package com.acmetelecom;

/**
 * 
 * @author dc408, ra808, je08, jm308
 */
public interface Printer {

    /**
     * 
     * @param name
     * @param phoneNumber
     * @param pricePlan
     */
    void printHeading(String name, String phoneNumber, String pricePlan);

    /**
     * 
     * @param time
     * @param callee
     * @param duration
     * @param cost
     */
    void printItem(String time, String callee, String duration, String cost);

    /**
     * 
     * @param total
     */
    void printTotal(String total);
}
