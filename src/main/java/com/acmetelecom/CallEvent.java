package com.acmetelecom;

/**
 * 
 * @author dc408, ra808, je08, jm308
 */
public abstract class CallEvent {
    private String caller;
    private String callee;
    private long time;

    /**
     * 
     * @param caller
     * @param callee
     * @param timeStamp
     */
    public CallEvent(String caller, String callee, long timeStamp) {
        this.caller = caller;
        this.callee = callee;
        this.time = timeStamp;
    }

    /**
     * 
     * @return
     */
    public String getCaller() {
        return caller;
    }

    /**
     * 
     * @return
     */
    public String getCallee() {
        return callee;
    }

    /**
     * 
     * @return
     */
    public long time() {
        return time;
    }
}
