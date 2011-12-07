package com.acmetelecom.calling;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 7;
        hash = prime * hash + ((caller == null) ? 0 : caller.hashCode());
        hash = prime * hash + ((callee == null) ? 0 : callee.hashCode());
        hash = prime * hash + (int) time;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof CallEvent) {
            // obj must be CallEvent at this point
            CallEvent other = (CallEvent) obj;

            if (caller == null && other.callee != null
                    || callee == null && other.callee != null) {
                return false;
            }

            return caller.equals(other.caller)
                    && callee.equals(other.callee)
                    && time == other.time;
        }

        return false;
    }
}
