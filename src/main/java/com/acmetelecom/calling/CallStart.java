package com.acmetelecom.calling;

import org.joda.time.DateTime;

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
    public CallStart(String caller, String callee, DateTime timestamp) {
        super(caller, callee, timestamp);
    }
}
