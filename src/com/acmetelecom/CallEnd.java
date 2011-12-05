package com.acmetelecom;

import java.sql.Timestamp;

/**
 * 
 * @author dc408, ra808, je08, jm308
 */
public class CallEnd extends CallEvent {
    /**
     * 
     * @param caller
     * @param callee
     */
    public CallEnd(String caller, String callee) {
        // todo: inject call end time
        super(caller, callee, Timestamp.valueOf("2011-11-11 11:30:00").getTime()); //System.currentTimeMillis());
    }
}
