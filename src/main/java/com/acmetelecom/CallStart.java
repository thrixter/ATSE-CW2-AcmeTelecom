package com.acmetelecom;

import java.sql.Timestamp;

/**
 * 
 * @author dc408, ra808, je08, jm308
 */
public class CallStart extends CallEvent {
    /**
     * 
     * @param caller
     * @param callee
     */
    public CallStart(String caller, String callee) {
        // todo: inject call start time
        super(caller, callee, Timestamp.valueOf("2011-11-11 11:11:00").getTime());//System.currentTimeMillis());
    }
}
