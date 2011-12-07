package com.acmetelecom.calling;

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
    public CallEnd(String caller, String callee, long timestamp) {
        super(caller, callee, timestamp);
    }
}
